	package com.mobicart.web.account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mobicart.dao.BillingDAO;
import com.mobicart.dao.DiskSpacePricingDAO;
import com.mobicart.dao.UserDAO;
import com.mobicart.model.Address;
import com.mobicart.model.Api;
import com.mobicart.model.ApiPartner;
import com.mobicart.model.Billing;
import com.mobicart.model.DiskSpacePricing;
import com.mobicart.model.DiskSpacePricingExample;
import com.mobicart.model.MerchantPartner;
import com.mobicart.model.MerchantPartnerExample;
import com.mobicart.model.Order;
import com.mobicart.model.PartnerBean;
import com.mobicart.model.Plans;
import com.mobicart.model.SiteConstant;
import com.mobicart.model.Store;
import com.mobicart.model.Territory;
import com.mobicart.model.User;
import com.mobicart.model.UserExample;
import com.mobicart.service.AdminService;
import com.mobicart.service.ApiService;
import com.mobicart.service.AppService;
import com.mobicart.service.StoreService;
import com.mobicart.service.user.UserService;
import com.mobicart.util.CacheManager;
import com.mobicart.util.CouchBaseCacheManager;
import com.mobicart.util.EmailGenerator;
import com.mobicart.util.Pager;
import com.mobicart.util.PathLocator;
import com.mobicart.util.ResourceProperties;
import com.mobicart.util.Sender;



@Controller
@RequestMapping("/admin/**")
public class AdminController {

	@Autowired
	UserService userService;

	@Autowired
	AppService appService;

	@Autowired
	StoreService storeService;
	
	@Autowired
	UserDAO userDAO;

	@Autowired
	AdminService adminService;
	

	@Autowired
	Sender sender;
	@Autowired
	BillingDAO billingDa0;
	
	@Autowired
	ApiService apiSercvice;
	
	@Autowired
	DiskSpacePricingDAO diskSpacePriceDao;
	/**
	 * Logger for this class
	 */
	 private final Logger logger = LoggerFactory.getLogger(this.getClass());
	

	private static final String ADMIN_USERS_VIEW_KEY = "admin/users";
	private static final String ADMIN_USER_EDIT_KEY = "admin/userEdit";
	private static final String ADMIN_ORDERS_VIEW_KEY = "admin/orders";
	private static final String ADMIN_PARTNERS_VIEW_KEY = "admin/partners";
	private static final String ADMIN_ORDER_DETAIL_VIEW_KEY = "admin/orderDetail";
	private static final String ADMIN_LOGIN_VIEW_KEY = "admin/login";
	private static final String ADMIN_PAYMENTS_VIEW_KEY = "admin/siteConstant";

	/**
	 * Show admin login view
	 * 
	 * @return
	 */
	@RequestMapping("/login")
	public String login() {
		return ADMIN_LOGIN_VIEW_KEY;
	}

	/**
	 * Show list of users
	 * @param pager {@link Pager}
	 * @param request 
	 * @param keyword
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/users")
	public String getAllUsers(@ModelAttribute Pager pager,HttpServletRequest request,
			@RequestParam(value = "keyword", required = false) String keyword,
			ModelMap modelMap ) throws Exception {
		
		PagedListHolder<List<User>> pagedListHolder = null;
		Integer totalUsers=0;
		try {
			// call to procedure
			Map<String, Object> userMap = new HashMap<String, Object>(
					userService.findUsers(pager));
			totalUsers=(Integer) userMap.get("count");
			pagedListHolder = new PagedListHolder(
					(List) userMap.get("userList"));
			pagedListHolder.setPageSize(50);
			
			String page = (String) request.getParameter("page");
			if ("next".equals(page)) {
				pagedListHolder.nextPage();
			} else if ("previous".equals(page)) {
				pagedListHolder.previousPage();
			} else {
				try {
					int pageNumber = Integer.parseInt(page);
					pagedListHolder.setPage(pageNumber);
				} catch (NumberFormatException e) {
					// skip
				}
			}
		} catch (Exception e) {
			logger.error("some error in fecthing users", e);
		}
		request.getSession().setAttribute("customerList", pagedListHolder);
		modelMap.put("customerList", pagedListHolder);
		modelMap.put("totalUsers", totalUsers);
		modelMap.put("keyword", keyword);

		return ADMIN_USERS_VIEW_KEY;
	}

	
	/**
	 * Show all orders
	 * 
	 * @param request
	 * @param pager
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/orders")
	public String getOrders(@ModelAttribute Pager pager, BindingResult result,
			HttpServletRequest request, ModelMap modelMap) {

		try{
		PagedListHolder<List<Order>> pagedListHolder = (PagedListHolder<List<Order>>) request
				.getSession().getAttribute("orderList");
		
		Map<String, Object> ordersMap=appService.findAllOrders(pager);
		
		long totalOrders=(Long) ordersMap.get("totalCount");
		List<Order> orders=  (List<Order>) ordersMap.get("orderList");
		
		
		pagedListHolder = new PagedListHolder(orders);
		pagedListHolder.setPageSize(50);

		String page = (String) request.getParameter("page");
		if ("next".equals(page)) {
				pagedListHolder.nextPage();
		} else if ("previous".equals(page)) {
				pagedListHolder.previousPage();
		}
		
		
		request.getSession().setAttribute("orderList", pagedListHolder);
		request.getSession().setAttribute("totalOrders", totalOrders);
		
		modelMap.put("orderList", pagedListHolder);
		modelMap.put("totalOrders", totalOrders);
		}catch (Exception e) {
			logger.error("error in fetching orders",e); 
		}
		return ADMIN_ORDERS_VIEW_KEY;
	}

	
	
	
	/**
	 * Show list of users
	 * @param pager {@link Pager}
	 * @param request 
	 * @param keyword
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/partners")
	public String getAllPartners(@ModelAttribute Pager pager,HttpServletRequest request,
			@RequestParam(value = "keyword", required = false) String keyword,
			ModelMap modelMap ) throws Exception {
		
		PagedListHolder<List<User>> pagedListHolder = null;
		Integer totalPartners=0;
		try {
			// call to procedure
			List<ApiPartner> partners= userService.findAllPartners(pager);
			totalPartners=(Integer) partners.size();
			pagedListHolder = new PagedListHolder(partners);
			pagedListHolder.setPageSize(50);
			
			String page = (String) request.getParameter("page");
			if ("next".equals(page)) {
				pagedListHolder.nextPage();
			} else if ("previous".equals(page)) {
				pagedListHolder.previousPage();
			} else {
				try {
					int pageNumber = Integer.parseInt(page);
					pagedListHolder.setPage(pageNumber);
				} catch (NumberFormatException e) {
					// skip
				}
			}
		} catch (Exception e) {
			logger.error("some error in fecthing users", e);
		}
		request.getSession().setAttribute("partnerList", pagedListHolder);
		modelMap.put("partnerList", pagedListHolder);
		modelMap.put("totalPartners", totalPartners);
		modelMap.put("keyword", keyword);

		return ADMIN_PARTNERS_VIEW_KEY;
	}
	
	
	
	
	/**
	 * Search Users
	 * 
	 * @param request
	 * @param keyword
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/users/search/", method = RequestMethod.GET)
	public String findUsersByKeyword(HttpServletRequest request,
			@RequestParam("keyword") String keyword, ModelMap modelMap) {

		PagedListHolder<List<User>> pagedListHolder = (PagedListHolder<List<User>>) request
				.getSession().getAttribute("customerList");

		if (pagedListHolder == null) {
			pagedListHolder = new PagedListHolder(
					userService.findUsersByKeyword(keyword));
		} else {

			pagedListHolder = new PagedListHolder(
					userService.findUsersByKeyword(keyword));

			String page = (String) request.getParameter("page");
			if ("next".equals(page)) {
				pagedListHolder.nextPage();
			} else if ("previous".equals(page)) {
				pagedListHolder.previousPage();
			}
		}

		request.getSession().setAttribute("customerList", pagedListHolder);
		modelMap.put("customerList", pagedListHolder);

		return ADMIN_USERS_VIEW_KEY;
	}

	@RequestMapping(value = "/user/{userId}/edit", method = RequestMethod.GET)
	public String editUser(@ModelAttribute RegisterForm registerForm,
			BindingResult result, @PathVariable("userId") Long userId,
			HttpServletRequest request, ModelMap modelMap
			
			 
	        ) {

		User user = userService.find(userId);
		Plans plans=null;
		boolean brandingFlag=user.isbCustomCopyrightPage();
		try {
			if (user != null) {
				registerForm.setUserId(userId);
				registerForm.setFirstName(user.getsFirstName());
				registerForm.setLastName(user.getsLastName());
				registerForm.setUsername(user.getUsername());
				registerForm.setHeardFrom(user.getsHeardFrom());
				registerForm.setPasswordConfirmation(user.getPassword());
				registerForm.setEnabled(user.isEnabled());
				registerForm.setProductCount(user.getiProductCount());
				registerForm.setStoreProductLimit(user.getStoreProductLimit());
			}

			Address address = userService.findAddressByUserId(userId);
			if (address != null) {
				registerForm.setAddress(address.getsAddress());
				registerForm.setCity(address.getsCity());
				registerForm.setState(address.getsState());
				registerForm.setCountry(address.getsCountry());
				registerForm.setZip(address.getsZip());
			}

			Store store = storeService.findStoreByUserId(user.getId());
			registerForm.setStoreName(store.getsSName());

			Api api =storeService.findApiByUserId(user.getId());
			if(api!=null){
			registerForm.setThresholdGeneralCount(api.getThresholdGeneralCount());
			registerForm.setThresholdStoreCount(api.getThresholdStoreCount());
			registerForm.setThresholdRefreshCount(api.getThresholdRefreshCount());
			}
			
			 plans=userService.findServicesByUserId(userId);
			  
			
		} catch (Exception e) {
			logger.error("error",e);
		}
       
		
		modelMap.put("plans", plans);
		modelMap.put("brandingFlag", brandingFlag);
		modelMap.put("registerForm", registerForm);
		List<Territory> countries = appService.findAllTerritories();
		modelMap.put("countries", countries);

		return ADMIN_USER_EDIT_KEY;

	}

	@RequestMapping(value = "/user/{userId}/send/activation", method = RequestMethod.GET)
	public String sendActivationUser(@ModelAttribute RegisterForm registerForm,
			BindingResult result, @PathVariable("userId") Long userId,
			HttpServletRequest request, ModelMap modelMap) {

		User user = userService.find(userId);
		try {
			if (user != null) {
				
				
					registerForm.setUserId(userId);
					registerForm.setFirstName(user.getsFirstName());
					registerForm.setLastName(user.getsLastName());
					registerForm.setUsername(user.getUsername());
					registerForm.setHeardFrom(user.getsHeardFrom());
					registerForm.setPasswordConfirmation(user.getPassword());
					registerForm.setEnabled(user.isEnabled());
					registerForm.setProductCount(user.getiProductCount());
					// Store store=storeService.findStoreByUserId(userId);
					// registerForm.setStoreName(store.getsSName());
				

				Address address = userService.findAddressByUserId(user.getId());
				if (address != null) {
					registerForm.setAddress(address.getsAddress());
					registerForm.setCity(address.getsCity());
					registerForm.setState(address.getsState());
					registerForm.setCountry(address.getsCountry());
					registerForm.setZip(address.getsZip());
				}
				Api api =storeService.findApiByUserId(user.getId());
				if(api!=null){
					registerForm.setThresholdGeneralCount(api.getThresholdGeneralCount());
					registerForm.setThresholdStoreCount(api.getThresholdStoreCount());
					registerForm.setThresholdRefreshCount(api.getThresholdRefreshCount());
					}
				

				String authKey=user.getsActivationKey();
				
				boolean newActivationKey=false;
				
				if(authKey==null ){
					newActivationKey=true;
				}else if(authKey.length()<=0){
					newActivationKey=true;
				}
				
				if(newActivationKey){
					UUID idOne = UUID.randomUUID(); 
					authKey = idOne.toString();
					user.setsActivationKey(authKey);
					userService.updateUser(user);
				}
				
				
				// Code to send the authentication email to registering user
				EmailGenerator email = new EmailGenerator();
				email.setFromEmail(ResourceProperties.getString("adminEmail"));
				email.setToEmail(registerForm.getUsername());
				email.setSubject("MobiCart - Confirmation needed");
				PathLocator path = new PathLocator();

				email.setTemplateName(path.getRealPath()
						+ "emailTemplate/REGISTERING_USER_AUTHENTICATION");

				HashMap<String, String> param = new HashMap<String, String>();

				String name = "";
				name = registerForm.getFirstName() != null ? registerForm
						.getFirstName()
						: "" + registerForm.getLastName() != null ? " "
								+ registerForm.getLastName() : " ";
				param.put("_NAME_", name);
				param.put("_BASEPATH_", ResourceProperties.getString("appUrl"));
				param.put("_ACTIVATIONLINK_",
						ResourceProperties.getString("appUrl")
								+ "/account/verify?authKey="
								+ authKey.trim());
				email.setParam(param);
				sender.sendMail(email);

				modelMap.put("success",
						"Activation mail has been sent successfully");
			}


			
			
		} catch (Exception e) {
			logger.warn("error",e);
		}

		modelMap.put("registerForm", registerForm);
		List<Territory> countries = appService.findAllTerritories();
		modelMap.put("countries", countries);

		return ADMIN_USER_EDIT_KEY;

	}

	/**
	 * Save/Update Personal / Address information of the user
	 * 
	 * @param registerForm
	 * @param result
	 * @param modelMap
	 * @return path to views/personalDetails.jsp
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/user/update")
	public String myAccountSave(
			@ModelAttribute @Valid RegisterForm registerForm,
			BindingResult result, @RequestParam("userId") Long userId,
			HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "removeBranding", required = false) boolean removeBranding,
			@RequestParam(value = "Plans", required = false) String Plans
	   ) {

		User oldUser = userService.find(userId);
		boolean activate = false;
		boolean error=false;
		
		if (result.hasErrors()) {
			error=true;

		} else {

			try{
					
			if (registerForm.isEnabled() && !oldUser.isEnabled()) {
				activate = true;
			}

			oldUser.setsFirstName(registerForm.getFirstName());
			oldUser.setsLastName(registerForm.getLastName());
			//oldUser.setStoreProductLimit(registerForm.getStoreProductLimit());
			if (activate) {
				oldUser.setEnabled(registerForm.isEnabled());
			}
			
			//userService.updateUser(oldUser);

			// save address
			Address newAddress = registerForm.getAddressModel();
			newAddress.setUserId(oldUser.getId());
			userService.saveAddress(newAddress);

			if(registerForm.getThresholdGeneralCount()!=null){
				Api api=storeService.findApiByUserId(oldUser.getId());
				api.setThresholdGeneralCount(registerForm.getThresholdGeneralCount());
				api.setThresholdStoreCount(registerForm.getThresholdStoreCount());
				api.setThresholdRefreshCount(registerForm.getThresholdRefreshCount());
				storeService.updateApi(api);
			}
			
			if (registerForm.getStoreName() != null) {
				Store store=null;
				try {
					store = storeService.findStoreByUserId(userId);
					store.setsSName(registerForm.getStoreName());
					storeService.updateStore(store);
				} catch (Exception e) {
					logger.error("Cant update store",e);
				}
				
			}
			
			}catch (Exception e) {
				logger.error("Cant update user",e);
				error=true;
			}
			

			if (activate) {

				// code to send the new user welcome email
				EmailGenerator email = new EmailGenerator();
				email.setFromEmail(ResourceProperties.getString("adminEmail"));
				email.setToEmail(oldUser.getUsername());
				email.setSubject("MobiCart - Signup confirmed");
				PathLocator path = new PathLocator();

				email.setTemplateName(path.getRealPath()
						+ "emailTemplate/NEW_USER");

				HashMap<String, String> param = new HashMap<String, String>();
				String name = "";
				name = oldUser.getsFirstName() != null ? oldUser
						.getsFirstName()
						: "" + oldUser.getsLastName() != null ? " "
								+ oldUser.getsLastName() : " ";

				param.put("_NAME_", name);
				param.put("_BASEPATH_", ResourceProperties.getString("appUrl"));
				param.put("_LOGINLINK_", ResourceProperties.getString("appUrl")
						+ "/account/login");
				email.setParam(param);

				sender.sendMail(email);

			}
			
			

			List<Territory> countries = appService.findAllTerritories();
			modelMap.put("countries", countries);

			modelMap.put("success",
					"Account settings have been updated successfully");
		}
		
		
		if(error){
		result.addError(new ObjectError("error",
				"Some error occured in update process."));
		
		
		User user=oldUser;
		if (user != null) {
			registerForm.setUserId(userId);
			registerForm.setFirstName(user.getsFirstName());
			registerForm.setLastName(user.getsLastName());
			registerForm.setUsername(user.getUsername());
			registerForm.setHeardFrom(user.getsHeardFrom());
			registerForm.setPasswordConfirmation(user.getPassword());
			registerForm.setEnabled(user.isEnabled());
			registerForm.setProductCount(user.getiProductCount());
			//registerForm.setStoreProductLimit(user.getStoreProductLimit());
		}

		Address address = userService.findAddressByUserId(user.getId());
		if (address != null) {
			registerForm.setAddress(address.getsAddress());
			registerForm.setCity(address.getsCity());
			registerForm.setState(address.getsState());
			registerForm.setCountry(address.getsCountry());
			registerForm.setZip(address.getsZip());
		}

		Api api =storeService.findApiByUserId(user.getId());
		if(api!=null){
			registerForm.setThresholdGeneralCount(api.getThresholdGeneralCount());
			registerForm.setThresholdStoreCount(api.getThresholdStoreCount());
			registerForm.setThresholdRefreshCount(api.getThresholdRefreshCount());
			}
		modelMap.put("error", "Some error occured in user  update");
		}

		 System.out.println("hi m there ");
			Long id=oldUser.getDiskSpacePricingId();
			DiskSpacePricing diskprice=null;
			try{
			if(id>4)//default pricing	
			  diskprice=diskSpacePriceDao.findDiskSpacePricingById(id);
			
			if(diskprice==null){
				diskprice=new DiskSpacePricing();
				diskprice.setfPrice(new BigDecimal(0));
				diskprice.setiMinLimit(0L);
				diskprice.setiMaxLimit(registerForm.getStoreProductLimit().longValue());
				Long  diskId=diskSpacePriceDao.save(diskprice);
				diskprice.setId(diskId);
				
			}
			else{
				diskprice.setiMaxLimit(registerForm.getStoreProductLimit().longValue());
				diskSpacePriceDao.updateDiskSpacePricing(diskprice);
			}
				
			oldUser.setDiskSpacePricingId(diskprice.getId());
			//userService.updateUser(oldUser);
			}
			catch (Exception e) {
				 e.printStackTrace();
				 
			}
		
		modelMap.put("registerForm", registerForm);
		List<Territory> countries = appService.findAllTerritories();
		modelMap.put("countries", countries);

		PagedListHolder<List<User>> pagedListHolder = null;
		Integer totalUsers=0;
		Pager pager=new Pager();
		try {
			// call to procedure
			
			Map<String, Object> userMap = new HashMap<String, Object>(
					userService.findUsers(pager));
			totalUsers=(Integer) userMap.get("count");
			pagedListHolder = new PagedListHolder(
					(List) userMap.get("userList"));
			pagedListHolder.setPageSize(50);
			
			String page = (String) request.getParameter("page");
			if ("next".equals(page)) {
				pagedListHolder.nextPage();
			} else if ("previous".equals(page)) {
				pagedListHolder.previousPage();
			} else {
				try {
					int pageNumber = Integer.parseInt(page);
					pagedListHolder.setPage(pageNumber);
				} catch (NumberFormatException e) {
					// skip
				}
			}
		} catch (Exception e) {
			logger.error("some error in fetching users", e);
		}
		
		/*update user services*/
		 Plans plans=new Plans();
		 boolean brandingFlag=removeBranding;
		try{
			
		      
			  //depricated.
			 //userService.updateBrandingByUserId(userId,removeBranding);	
			 
			 oldUser.setbCustomCopyrightPage(removeBranding);
			
				 //userService.findServicesByUserId(userId);		      
		      if("starter".equals(Plans)){
		    	  billingDa0.updateCreatPlansByUserId(userId, "starter");
		    	  
		    	  /*User user=userDAO.find(userId);
		    	      user.setStoreProductLimit(Integer.parseInt(ResourceProperties.getString("product.limit.starter")));
		    	      userDAO.update(user);*/
		    	  //oldUser.setDiskSpacePricingId(2l);
		    	      plans.setStarter(true);
		      }
		      else if("pro".equals(Plans)){
		    	  billingDa0.updateCreatPlansByUserId(userId, "pro"); 
		    	  /*User user=userDAO.find(userId);
	    	      user.setStoreProductLimit(Integer.parseInt(ResourceProperties.getString("product.limit.pro")));
	    	      userDAO.update(user);*/
		    	 // oldUser.setDiskSpacePricingId(3l);
		    	  plans.setPro(true);
		      }
		      else if("free".equals(Plans)){
		    	  billingDa0.updateCreatPlansByUserId(userId, "free");  
		    	 /* User user=userDAO.find(userId);
	    	      user.setStoreProductLimit(Integer.parseInt(ResourceProperties.getString("product.limit.free")));
	    	      userDAO.update(user);*/
		    	//  oldUser.setDiskSpacePricingId(1);
		    	  plans.setFree(true);
		      }
		      userService.updateUser(oldUser);
		      
		}catch (Exception e) {
			logger.error("some error in upsdating user services", e);
		}
		
		request.getSession().setAttribute("customerList", pagedListHolder);
		
		/*clear cache*/
		
		String apiKey=apiSercvice.getAuthSecretByUserId(oldUser.getId());
		CacheManager.setToCache(apiKey,null);
		CouchBaseCacheManager.deleteObject(apiKey);
		modelMap.put("plans", plans);		
		modelMap.put("brandingFlag", brandingFlag);
		modelMap.put("customerList", pagedListHolder);
		modelMap.put("totalUsers", totalUsers);
		return ADMIN_USER_EDIT_KEY;
	}



	
	/**
	 * Show order details
	 * 
	 * @param orderId
	 * @param modelMap
	 * @return
	 */

	@RequestMapping("/order/{orderId}/details")
	public String getOrderDetails(@ModelAttribute("order") Order order,
			BindingResult result, @PathVariable("orderId") Long orderId,
			ModelMap modelMap) {
		order = appService.findOrder(orderId);
		modelMap.put("order", order);
		return ADMIN_ORDER_DETAIL_VIEW_KEY;
	}

	/**
	 * 
	 * @param order
	 * @param result
	 * @param modelMap
	 * @return
	 */

	@RequestMapping(value = "/order/update", method = RequestMethod.POST)
	public String updateOrder(@ModelAttribute("order") Order order,
			BindingResult result, ModelMap modelMap) {
		appService.updateOrder(order);
		order = appService.findOrder(order.getId());
		modelMap.put("order", order);
		modelMap.put("success", "Your details have been saved successfully");
		return ADMIN_ORDER_DETAIL_VIEW_KEY;
	}

	/**
	 * Show payments views
	 * 
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/payment")
	public String showPayments(@ModelAttribute SiteConstant siteConstant,
			ModelMap modelMap) {
		siteConstant = adminService.findSiteConstants();
		modelMap.put("siteConstant", siteConstant);
		try{
		List<DiskSpacePricing> diskSpacePricingList=userService.findDiskSpacePricingByExample(new DiskSpacePricingExample());
		if(diskSpacePricingList !=null && diskSpacePricingList.size() == 4){
			modelMap.put("additionalSpaceAmount1", diskSpacePricingList.get(0).getfPrice());
			modelMap.put("additionalSpaceAmount2", diskSpacePricingList.get(1).getfPrice());
			modelMap.put("additionalSpaceAmount3", diskSpacePricingList.get(2).getfPrice());
			modelMap.put("additionalSpaceAmount4", diskSpacePricingList.get(3).getfPrice());
		}
		}catch (Exception e) {
			logger.error("showPayments",e);
		}
		return ADMIN_PAYMENTS_VIEW_KEY;
	}
	

	/**
	 * Show payments views
	 * 
	 * @param modelMap
	 * @return
	 */
	
	@RequestMapping("/additionalDiskSpacePricing/update")
	public String saveAdditionalDiskSpacePricing(HttpServletRequest request,
			ModelMap modelMap) {
		
		for(int i=1;i<5;i++){
			try{
			DiskSpacePricing diskSpacePricing=userService.findDiskSpacePricingById(new Long(i));
			diskSpacePricing.setfPrice(new BigDecimal(request.getParameter("additionalSpaceAmount"+i)));
			userService.update(diskSpacePricing);
			}catch (Exception e) {
				logger.error("saveAdditionalDiskSpacePricing",e);
			}
		}
		
		try{
			List<DiskSpacePricing> diskSpacePricingList=userService.findDiskSpacePricingByExample(new DiskSpacePricingExample());
			if(diskSpacePricingList !=null && diskSpacePricingList.size() == 4){
				modelMap.put("additionalSpaceAmount1", diskSpacePricingList.get(0).getfPrice());
				modelMap.put("additionalSpaceAmount2", diskSpacePricingList.get(1).getfPrice());
				modelMap.put("additionalSpaceAmount3", diskSpacePricingList.get(2).getfPrice());
				modelMap.put("additionalSpaceAmount4", diskSpacePricingList.get(3).getfPrice());
			}
			}catch (Exception e) {
				logger.error("saveAdditionalDiskSpacePricing",e);
			}
		
		SiteConstant siteConstant = adminService.findSiteConstants();
		modelMap.put("siteConstant", siteConstant);
		modelMap.put("success", "Additional disk space changes have been saved successfully.");
		return ADMIN_PAYMENTS_VIEW_KEY;
	}
	

	/**
	 * Show payments views
	 * 
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/payment/update")
	public String savePayments(@ModelAttribute SiteConstant siteConstant,
			ModelMap modelMap) {
		adminService.updateSiteConstants(siteConstant);
		siteConstant = adminService.findSiteConstants();
		modelMap.put("siteConstant", siteConstant);
		modelMap.put("success", "Your details have been saved successfully");
		
		try{
			List<DiskSpacePricing> diskSpacePricingList=userService.findDiskSpacePricingByExample(new DiskSpacePricingExample());
			if(diskSpacePricingList !=null && diskSpacePricingList.size() == 4){
				modelMap.put("additionalSpaceAmount1", diskSpacePricingList.get(0).getfPrice());
				modelMap.put("additionalSpaceAmount2", diskSpacePricingList.get(1).getfPrice());
				modelMap.put("additionalSpaceAmount3", diskSpacePricingList.get(2).getfPrice());
				modelMap.put("additionalSpaceAmount4", diskSpacePricingList.get(3).getfPrice());
			}
			}catch (Exception e) {
				logger.error("saveAdditionalDiskSpacePricing",e);
			}
			
		return ADMIN_PAYMENTS_VIEW_KEY;
	}
	
	
	
	
	@RequestMapping(value = "/register-partner", method = RequestMethod.POST)
    public String registerPartner(
            @RequestParam(value = "partner_name", required = true) String partnerName,
            @RequestParam(value = "partner_email", required = true) String partnerEmail) {
    	
    	String apiKey = null;
    	
    	try{
    		apiKey = storeService.generatePartnerAPIKey(partnerName, partnerEmail);
    	}catch(Throwable t){
    		logger.error("Key generation error.", t);
    	}
    	ApiPartner apiPartner = new ApiPartner();
    	apiPartner.setPartnerName(partnerName);
    	apiPartner.setPartnerEmail(partnerEmail);
    	apiPartner.setApiKey(apiKey);
    	apiPartner.setDateOfJoining(new Date());
    	
    	try{
    		adminService.saveApiPartner(apiPartner);
    	}catch(Exception e){
    		logger.error("Error in saving ApiPartner.", e);
    	}
    	
    	return "redirect:/admin/partners";
                
    }
	
	
	
	
	
	@RequestMapping(value = "/partner/report", method = RequestMethod.GET)
	public String generatePartnerReport(HttpServletRequest request, ModelMap modelMap)throws Exception {

		
		List<PartnerBean> partnerBeans = new ArrayList<PartnerBean>();
		PartnerBean pb = null;
		Integer freeUsers = 0;
		Integer starter = 0;
		Integer pro = 0;
		
		List<ApiPartner> partners= userService.findAllPartners();
		for(ApiPartner partner : partners){
			pb = new PartnerBean();
			List<MerchantPartner> merchants = null;
			MerchantPartnerExample example = new MerchantPartnerExample();
			example.createCriteria().andPartnerEmailEqualTo(partner.getPartnerEmail());
			try{
				merchants = adminService.findMerchantsForPartner(example);
			}catch(Exception e){
				logger.error("Error in finding merchants.", e);
			}
			
			for(MerchantPartner merchant:merchants){
								
				UserExample userExample = new UserExample();
				userExample.createCriteria().andUsernameEqualTo(merchant.getMerchantEmail());
				
				User user = userDAO.findByExample(userExample).get(0);
				Long userId = user.getId();
				
				
				
				
				List<Billing> bills = userService.findAllBillingDetails(userId);
				if(bills != null){
					for(Billing bill:bills){
						if(bill.getsType().equals("free"))freeUsers+=1;
						if(bill.getsType().equals("starter"))starter+=1;
						if(bill.getsType().equals("pro"))pro+=1;
					}
				}else{
					freeUsers = 0;
					starter = 0;
					pro = 0;
				}
				pb.setFreeUsers(freeUsers);
				pb.setStarterUsers(starter);
				pb.setProUsers(pro);
				
			}
			freeUsers = 0;
			starter = 0;
			pro = 0;
			pb.setName(partner.getPartnerName());
			
			partnerBeans.add(pb);
			modelMap.put("partnerBeans", partnerBeans);
		}
		return "/admin/report";
	}



}
