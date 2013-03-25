package com.mobicart.web;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.im4java.core.ImageMagickCmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.mobicart.bo.PushNotificationBO;
import com.mobicart.bo.UserBO;
import com.mobicart.dao.AppDAO;
import com.mobicart.dao.GalleryImageDAO;
import com.mobicart.dao.ProductDAO;
import com.mobicart.dao.UserDAO;
import com.mobicart.dto.ProductDto;
import com.mobicart.model.Api;
import com.mobicart.model.App;
import com.mobicart.model.AppDeviceTokens;
import com.mobicart.model.AppExample;
import com.mobicart.model.Category;
import com.mobicart.model.Department;
import com.mobicart.model.GalleryImage;
import com.mobicart.model.GalleryImageExample;
import com.mobicart.model.News;
import com.mobicart.model.Plans;
import com.mobicart.model.Product;
import com.mobicart.model.ProductExample;
import com.mobicart.model.ProductImage;
import com.mobicart.model.ProductOption;
import com.mobicart.model.PushNotification;
import com.mobicart.model.Shipping;
import com.mobicart.model.StaticPage;
import com.mobicart.model.Store;
import com.mobicart.model.Tax;
import com.mobicart.model.Territory;
import com.mobicart.model.User;
import com.mobicart.model.UserExample;
import com.mobicart.service.AdminService;
import com.mobicart.service.ApiService;
import com.mobicart.service.AppService;
import com.mobicart.service.LabelService;
import com.mobicart.service.StoreService;
import com.mobicart.service.user.UserService;
import com.mobicart.util.CouchBaseCacheManager;
import com.mobicart.util.Constants;
import com.mobicart.util.CustomPagedListHolder;
import com.mobicart.util.DateTimeUtils;
import com.mobicart.util.FileUtils;
import com.mobicart.util.ImageSize;
import com.mobicart.util.ImageUtils;
import com.mobicart.util.ImgMagicCmdExcecutor;
import com.mobicart.util.MagicalPower;
import com.mobicart.util.Pager;
import com.mobicart.util.PathLocator;
import com.mobicart.util.ResourceProperties;
import com.mobicart.util.ValidationUtils;
import com.mobicart.util.exception.DuplicateDepartmentException;




/**
 * Controller for Mobicart 
 * store operations
 * 
 */

@Controller
@RequestMapping("/store/**")
@SessionAttributes({ "store" })
public class StoreController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(StoreController.class);

	
	/**
	 * Instantiate {@link StoreService}
	 */
	@Autowired
	StoreService storeService;

	/**
	 * Instantiate {@link UserService}
	 */
	@Autowired
	UserService userService;

	/**
	 * {@link AppService} Instance
	 */
	@Autowired
	AppService appService;

	/**
	 * {@link AdminService} instance
	 */
	@Autowired
	AdminService adminService;
	
	
	/**
	 * {@link PushNotificationBO} instance
	 */
	@Autowired
	PushNotificationBO pushNotificationBO;
	
	/**
	 * Instantiate {@link LabelService}
	 */
	@Autowired
	LabelService labelService;
	
	@Autowired
	ApiService apiService;
    /*
     * Instantiate {@link P}
     */
	 
	
	/**
	 * Forward Path Strings
	 */
	public static final String STORE_SETTINGS_VIEW_KEY = "store/settings";
	
	public static final String STORE_LANGUAGES_VIEW_KEY = "store/languages";

	public static final String STORE_SETTINGS_REDIRECT_VIEW_KEY = "redirect:settings";

 
	public static final String STORE_CONTENT_VIEW_KEY = "store/content";

	public static final String STORE_CONTENT_PRODUCT_EDIT_KEY = "store/productAdd";

	public static final String STORE_CONTENT_PRODUCT_ADD_KEY = "store/productAdd";

	public static final String STORE_CONTENT_REDIRECT_KEY = "redirect:content";
	 

	public static final String GALLERY_HOME_VIEW_KEY = "gallery/home";

	public static final String GALLERY_HOME_REDIRECT_VIEW_KEY = "redirect:gallery/home";

	public static final String LOGIN_VIEW = "login";

	public static final String ERROR_VIEW = "error";

	private static final String USER_DASHBOARD_REDIRECT_VIEW="redirect:/account/dashboard";


	/**
	 * Fetch {@link Store} object from session and set it in session variables
	 * @param session
	 * @return
	 */
	public Store createStore(HttpSession session){
		return (Store) session.getAttribute("store");
	}




	/**
	 * Show {@link Store} settings/ {@link Tax} / {@link Shipping} tab
	 * 
	 * @param store
	 * @param result
	 * @param modelMap
	 * @param request {@link HttpServletRequest} 
	 * @return forward string for path to /views/store/settings.jsp
	 */

	@RequestMapping("settings")
	public String settings(@ModelAttribute("store") Store store,
			BindingResult result, ModelMap modelMap, HttpServletRequest request) {
		modelMap.put("tabSelect", 0);

		/**
		 * get logged in user
		 */
		User user = UserBO.getLoggedInUser(request);
		store=(Store) request.getSession().getAttribute("store");

		try {

			if (String.valueOf(user.getId()).equalsIgnoreCase(
					String.valueOf(store.getUserId()))) {
				List<Territory> countries = appService.findAllTerritories();
				List<Shipping> shippingList = storeService
				.findShippingByStore(store.getId());
				List<Tax> taxList = storeService.findTaxByStore(store.getId());
				modelMap.put("countries", countries);
				modelMap.put("shippingList", shippingList);
				modelMap.put("taxList", taxList);

				return STORE_SETTINGS_VIEW_KEY;
			} else {
				return ERROR_VIEW;
			}
		} catch (Exception e) {
			logger.error("settings",e);
			return USER_DASHBOARD_REDIRECT_VIEW;
		}

	}
	
	
	
//	/**
//	 * Show {@link Store} languages
//	 *	
//	 * @param modelMap
//	 * @param request {@link HttpServletRequest} 
//	 * @return forward string for path to /views/store/languages.jsp
//	 */
//
//	@RequestMapping("languages")
//	public String languages(ModelMap modelMap, HttpServletRequest request) {
//		modelMap.put("tabSelect", 0);
//
//		/**
//		 * get logged in user
//		 */
//		try {
//			User user = UserBO.getLoggedInUser(request);
//			HashMap<String, String> labelsMap =null;			
//			labelsMap = labelService.getAllWebLabels(user.getId());
//			request.setAttribute("labelsMap", labelsMap);
//		} catch (Exception e) {
//			logger.error("settings",e);
//		}
//		
//		return STORE_LANGUAGES_VIEW_KEY;
//
//	}

	@RequestMapping("languages")
	public String languages(ModelMap modelMap, HttpServletRequest request) {
		modelMap.put("tabSelect", 0);

		/**
		 * get logged in user
		 */
		try {
			
			HashMap<String, String> returnWebMap = new HashMap<String, String>();
			HashMap<String, String> returnIphoneMap = new HashMap<String, String>();
			
			User user = UserBO.getLoggedInUser(request);
			HashMap<String, String> labelsMap =null;			
			labelsMap = labelService.getAllWebLabels(user.getId());
			
			for (String key : labelsMap.keySet()) {
				returnWebMap.put(key, labelsMap.get(key).replaceAll("\"", "&#34;"));
			}
			
			request.setAttribute("labelsMap", returnWebMap);
			
			HashMap<String, String> iphonelabelsMap =null;			
			iphonelabelsMap = labelService.getAllIphoneLabels(user.getId());
			
			for (String key : iphonelabelsMap.keySet()) {
				returnIphoneMap.put(key, iphonelabelsMap.get(key).replaceAll("\"", "&#34;"));
			}
			
			
			request.setAttribute("iphonelabelsMap", returnIphoneMap);
			
			HashMap<String, String> defaultlabelsMap = labelService.selectAllDefaultLabels();
			request.setAttribute("defaultlabelsMap", defaultlabelsMap);
			
			
		} catch (Exception e) {
			logger.error("settings",e);
		}
	
		return STORE_LANGUAGES_VIEW_KEY;

	}

	/**
	 * Save Basic {@link Store} Settings
	 * 
	 * @param store
	 * @param result
	 * @param modelMap
	 * @return redirect string for path to /views/store/settings.jsp
	 */

	@RequestMapping("settings/save")
	public String saveSettings(@ModelAttribute("store") @Valid Store store,
			BindingResult result, ModelMap modelMap,
			final HttpServletRequest request,final HttpServletResponse response) {
		modelMap.put("tabSelect", 0);
		/**
		 * get logged in user
		 */
		User loggedInUser = UserBO.getLoggedInUser(request);
		try{
			if(!String.valueOf(loggedInUser.getId()).equalsIgnoreCase(String.valueOf(store.getUserId()))){
				throw new AccessDeniedException("You do not have authority to accomplish this.");
			}

			store.setdUpdatedOn(new Date());
			Long storeId = store.getId();
			try{
				if (storeId != null) {
					Store stre = storeService.findStoreById(storeId);
					Long userId = stre.getUserId();
					User  user = userService.find(userId);
					user.setsPaypalAddress(store.getsPaypalEmail());
					
					userService.updateUser(user);
					storeService.updateStore(store);
				} else {
					storeId = storeService.createStore(store);
				}
			}catch(Exception e){
				logger.error("saveSettings",e);
			}

			List<Territory> countries = appService.findAllTerritories();
			List<Shipping> shippingList = storeService.findShippingByStore(store
					.getId());
			List<Tax> taxList = storeService.findTaxByStore(store.getId());

			modelMap.put("countries", countries);
			modelMap.put("shippingList", shippingList);
			modelMap.put("taxList", taxList);
			request.getSession().setAttribute("store", store);
			User user = userService.findByEmail(store.getsUserEmail());
			request.getSession().setAttribute("user", user);
			user = labelService.setDefaultLabelKeyValuesInUser(user);
			modelMap.put("success", "Your settings have been updated successfully.");
			return STORE_SETTINGS_VIEW_KEY;
		}catch(Exception e){
			logger.error("saveSettings ",e);
			return USER_DASHBOARD_REDIRECT_VIEW;
		}

	}

	/**
	 * Save {@link Shipping} details
	 * 
	 * @param store
	 * @param result
	 * @param modelMap
	 * @return redirect string for path to /views/store/settings.jsp
	 */

	@RequestMapping("shipping/save")
	public String saveShipping(
			@ModelAttribute("shipping") @Valid Shipping shipping,
			BindingResult result, HttpServletRequest request, ModelMap modelMap) {
		modelMap.put("tabSelect", 1);
		Store store = (Store) request.getSession().getAttribute("store");

		User loggedInUser=UserBO.getLoggedInUser(request); 
		try{
			/*
			 * check user ownership		
			 */
			if(!String.valueOf(loggedInUser.getId()).equalsIgnoreCase(String.valueOf(store.getUserId()))){
				throw new AccessDeniedException("You do not have authority to accomplish this.");
			}
			Territory territory = storeService.findTerritoryById(shipping
					.getTerritoryId());
			shipping.setsCountry(territory.getsName());
			storeService.createShipping(shipping);
			List<Territory> countries = appService.findAllTerritories();
			List<Shipping> shippingList = storeService.findShippingByStore(store
					.getId());
			List<Tax> taxList = storeService.findTaxByStore(store.getId());
			modelMap.put("countries", countries);
			modelMap.put("shippingList", shippingList);
			modelMap.put("taxList", taxList);
			request.getSession().setAttribute("store", store);
			modelMap.put("success", "Your settings have been updated successfully.");
			return STORE_SETTINGS_VIEW_KEY;
		}catch(Exception e){
			logger.error("saveShipping",e);
			return USER_DASHBOARD_REDIRECT_VIEW;
		}
	}

	/**
	 * Save {@link Tax} details
	 * 
	 * @param store
	 * @param result
	 * @param modelMap
	 * @return redirect string for path to /views/store/settings.jsp
	 */

	@RequestMapping("tax/save")
	public String saveTax(@ModelAttribute("tax") @Valid Tax tax,
			BindingResult result,HttpServletRequest request, ModelMap modelMap) {
		modelMap.put("tabSelect", 2);
		Store store = (Store) request.getSession().getAttribute("store");

		User loggedInUser=UserBO.getLoggedInUser(request); 
		try{
			/**
			 * check user ownership		
			 */
			if(!String.valueOf(loggedInUser.getId()).equalsIgnoreCase(String.valueOf(store.getUserId()))){
				throw new AccessDeniedException("You do not have authority to accomplish this.");
			}
			if(tax.getsType().equalsIgnoreCase("Eg: VAT/GST")||tax.getsType().trim().length()<1){
				result.reject("Required.setting.taxtype","Tax type is required");
			}			
			tax.setStoreId(store.getId());
			Territory territory = storeService.findTerritoryById(tax
					.getTerritoryId());
			tax.setsCountry(territory.getsName());
			if (!result.hasErrors()) {
				storeService.createTax(tax);
				modelMap.put("success", "Your settings have been updated successfully.");
			}
			List<Territory> countries = appService.findAllTerritories();
			List<Shipping> shippingList = storeService.findShippingByStore(store
					.getId());
			List<Tax> taxList = storeService.findTaxByStore(store.getId());
			modelMap.put("countries", countries);
			modelMap.put("shippingList", shippingList);
			modelMap.put("taxList", taxList);
			request.getSession().setAttribute("store", store);
			return STORE_SETTINGS_VIEW_KEY;
		}catch(Exception e){
			logger.error("saveTax",e);
			return USER_DASHBOARD_REDIRECT_VIEW;
		}
	}

	/**
	 * Show Add/Edit/Delete {@link Department}/Category /Product view
	 * 
	 * @param store
	 * @param product
	 * @param result
	 * @param modelMap
	 * @return forward path for views/store/content.jsp
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("content")
	public String manageContent(@ModelAttribute Product product,
			BindingResult result, @ModelAttribute("store") Store store,
			HttpServletRequest request,Pager pager, ModelMap modelMap) {

		Integer tabSelect = 0;
		List<Department> departments=null;
		List<Category> categories =null;
		List<Product> products =null;
		Plans plans=null;
		int productshowing=0;
		Integer totalCount=null;
		
		CustomPagedListHolder<Product> pagedListHolder=null;
		User user = UserBO.getLoggedInUser(request);
		
	try{	
		departments = storeService.findDepartmentsByStore(store.getId());
		
		
		/*if (departments.size() > 0) {
			try {
				categories = storeService
						.findCategoriesByDepartments(departments);
			} catch (Exception e) {
				logger.debug("No categories found {}", e);
			}
		}*/

		//set pagination per page limit to 10
		pager.setDefaultLimit(10);
		if(user.getStoreProductLimit()>pager.getDefaultLimit()){ 
			int  page=pager.getPage();
			int limit=pager.getDefaultLimit();
			if(page>0){
				limit=(page+1)*pager.getDefaultLimit();
				 if(limit>user.getStoreProductLimit())
					 limit=user.getStoreProductLimit()-page*pager.getDefaultLimit();
				 else
					 limit=pager.getDefaultLimit(); 
				  
			}
			
			 
		pager.setResults(limit);
		
		}
		else
			pager.setResults(user.getStoreProductLimit());
		
		
		 
		
		//get result Map
		Map<String,Object> resultMap = storeService.findStoreProductsWithPagination(store.getId(),pager);
		//get products
		  plans=userService.findServicesByUserId(user.getId());
		//int totalProduct=user.getiProductCount();
		products =(List<Product>) resultMap.get("productList");
		  totalCount= (Integer)resultMap.get("count");
		
		
		 
		
		 
		
		pagedListHolder = new CustomPagedListHolder();
		 
		 if(totalCount>user.getStoreProductLimit()){
			 pagedListHolder.setNrOfElements(user.getStoreProductLimit()); 
			 totalCount=user.getStoreProductLimit();
		 }
		 else{
			 pagedListHolder.setNrOfElements(totalCount);
		 }
		pagedListHolder.setSource(products);
		//pagedListHolder.setNrOfElements(totalCount);
		pagedListHolder.setPage(pager.getPage());
		

	}catch (Exception e) {
		logger.error("Error in fetching content",e);
	}
		modelMap.put("departments", departments);
		//modelMap.put("categories", categories);
		modelMap.put("products", products);
		modelMap.put("tabSelect", tabSelect);
		modelMap.put("productList", pagedListHolder);
		modelMap.put("plans", plans);
		modelMap.put("totalCount", totalCount);
		modelMap.put("maxLimit", user.getStoreProductLimit());
		return STORE_CONTENT_VIEW_KEY;
	}

	/**
	 * Edit {@link Product} View
	 * 
	 * @param store
	 * @param product
	 * @param result
	 * @param productId
	 * @param modelMap
	 * @return forward path string for views/store/productEdit.jsp
	 */
	@RequestMapping("product/{productId}/edit")
	public String editProduct(@ModelAttribute("store") Store store,
			@ModelAttribute("product") Product product, BindingResult result,
			@PathVariable Long productId, ModelMap modelMap,
			HttpServletRequest request) {

		User loggedInUser = UserBO.getLoggedInUser(request);
		try {

			String page = (String) request.getParameter("page");

			List<Department> departments = storeService.findDepartmentsByStore(store.getId());
			modelMap.put("departments", departments);
			product = storeService.findProduct(productId);
			/*find imidiate parent of this product*/
			long parentId=storeService.findParentDepartmentId(product.getDepartmentId());
			product.setDepartmentId((parentId>0L)?parentId:product.getDepartmentId());
			// check product ownership
			if (!String.valueOf(loggedInUser.getId()).equalsIgnoreCase(
					String.valueOf(product.getUserId()))) {
				throw new AccessDeniedException("You do not have authority to accomplish this.");
			}
			modelMap.put("page", page);
			modelMap.put("product", product);
			return STORE_CONTENT_PRODUCT_EDIT_KEY;
		} catch (Exception e) {
			logger.error("editProduct ", e);
			return USER_DASHBOARD_REDIRECT_VIEW;
		}
	}

	/**
	 * add {@link Product} show
	 * 
	 * @param store
	 * @param product
	 * @param result
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("product/add")
	public String productAddShow(@ModelAttribute("store") Store store,
			@ModelAttribute Product product, BindingResult result,
			ModelMap modelMap) {
		Integer tabSelect = 1;
		List<Product> products =null;//storeService.findProductsByStore(store.getId());
		List<Department> departments = storeService.findDepartmentsByStore(store.getId());
		List<Category> categories = null;
		//Plans plans=userService.findServicesByUserId(userId);
		
		 
		CustomPagedListHolder<Product> pagedListHolder=null;
		
		
		
		 if (departments.size() > 0) {
			try {
				storeService.findCategoriesByDepartments(departments);
			} catch (Exception e) {
				logger.debug("No category found", e);
			}
		}

		 /*try{
		//set pagination per page limit to 10
		Pager pager=new Pager();
		//pager.setResults(10)
		
		//get result Map
		Map<String,Object> resultMap = storeService.findStoreProductsWithPagination(store.getId(),pager);
		//get products
		products =(List<Product>) resultMap.get("productList");
		Integer totalCount= (Integer)resultMap.get("count");
		
	    pagedListHolder = new CustomPagedListHolder();
		pagedListHolder.setSource(products);
		pagedListHolder.setNrOfElements(totalCount);
		pagedListHolder.setPage(pager.getPage());
		

	}
	catch (Exception e) {
		logger.error("Error in fetching content",e);
	}
		modelMap.put("productList", pagedListHolder);
		modelMap.put("products", products);*/
		modelMap.put("departments", departments);
		modelMap.put("categories", categories);
		
		modelMap.put("tabSelect", tabSelect);
		
		return STORE_CONTENT_PRODUCT_ADD_KEY;
	}


	/**
	 * Add New {@link Product}
	 * 
	 * @param store
	 * @param product
	 * @param result
	 * @param departmentId
	 * @param categoryId
	 * @param productOptionIds
	 * @param modelMap
	 * @return redirect to store content view /views/store/content.jsp
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("product/save")
	public String saveProduct(
			@ModelAttribute Product product,
			BindingResult result,
			@ModelAttribute("store") Store store,
			@RequestParam("productId") Long productId,
			@RequestParam("departmentId") Long departmentId,
			@RequestParam(value = "categoryId", required = false) Long categoryId,
			@RequestParam(value = "productOptionIds", required = false) Long[] productOptionIds,
			@RequestParam(value = "productImageIds", required = false) Long[] productImageIds,
			@RequestParam(value = "productOptionAmt", required = false) Integer[] productOptionAmt,

			HttpServletRequest request, ModelMap modelMap) {
		
		logger.debug("categoryId:"+categoryId+",departmentId"+departmentId);
		 if(product.getiAggregateQuantity()==null)
		  product.setiAggregateQuantity(new Integer(-1));
			 
			    
		
		 boolean  productaddPage=false;
		modelMap.put("tabSelect", 1);
		
		String page=null;

		User loggedInUser=UserBO.getLoggedInUser(request); 
		String  apiKey=apiService.getAuthSecretByUserId(loggedInUser.getId());
		/*clear cache*/
		CouchBaseCacheManager.deleteObject(apiKey);
		try{
			//check user ownership		
			if(!String.valueOf(loggedInUser.getId()).equalsIgnoreCase(String.valueOf(store.getUserId()))){
				throw new AccessDeniedException("You do not have authority to accomplish this.");
			}

			String msg = "Product has been created successfully";
			if (product.getsName() == null) {

				result.reject("Required.product.sName",
						labelService.getKeyValueByMerchantID("key.Required.product.sName", loggedInUser.getId()));
			}

			if (product.getfPrice() == null) {

				result.reject("Required.product.fPrice",
						labelService.getKeyValueByMerchantID("key.Required.product.fPrice", loggedInUser.getId()));
			}
			if (!(product.getfPrice() instanceof BigDecimal)) {

				result.reject("Numeric.product.fPrice",
						labelService.getKeyValueByMerchantID("key.validation.product.numeric", loggedInUser.getId()));
			}

			if (departmentId == null) {
				result.reject("Required.product.departmentId",
				"Department has to be selected.");
			}


			if (product.getfDiscount() != null) {
				if (product.getfDiscount().compareTo(new BigDecimal("100.00")) == 1) {
					result.reject("RangeError.product.fDiscount",
					"Discount can not exceed 100 %");

				}
			}

			if (result.hasErrors()) {
				List<Department> departments = storeService
				.findDepartmentsByStore(store.getId());
				modelMap.put("departments", departments);
				if (productId != 0) {
					product.setId(productId);
				}
				modelMap.put("product", product);
				return STORE_CONTENT_PRODUCT_ADD_KEY;

			} else {
				try {
						
					product.setUserId(store.getUserId());
					product.setStoreId(store.getId());
					
				
					/*check whether sub department exist(business rule) */
					List<Category> subdeptList=storeService.findCategoriesByDepartment((categoryId!=null)?categoryId:departmentId);
					 if(subdeptList!=null){
						  if(subdeptList.size()>0){
							    List<Department> departments = storeService.findDepartmentsByStore(product.getStoreId());
								List<Category> categories = storeService.findCategoriesByDepartments(departments);
							    
								 
								modelMap.put("categories", categories);
								modelMap.put("departments", departments);
								 
								modelMap.put("error","Products already exist under this department.");	
								return STORE_CONTENT_PRODUCT_ADD_KEY; 
							  
						  }
						 
					 }
					
					
					 product.setDepartmentId((categoryId!=null)?categoryId:departmentId);
					Long appId = appService.findAppByUser(
							new User(store.getUserId())).getId();
					product.setAppId(appId);
			        //product.setDepartmentId(departmentId);
					
					if (categoryId == null) {
						categoryId = 0L;
					}
					//product.setCategoryId(categoryId);
					product.setCategoryId(null);
					
					
					/*check whether department already have a subdepartment**/

					/*
					 *  change stock control based on aggregate quantity
					 */

					if (product.getiAggregateQuantity() == null&&product.getbStockControl()==true){

						product.setiAggregateQuantity(-1);
					}

					if(product.getbStockControl()==false){
						product.setbUseOptions(true);
					}else{
						product.setbUseOptions(false);
					}

				

					if (product.getsStatus() == null) {
						product.setsStatus("active");
					}

					
					
					
					
					
					if (productId != 0) {
						product.setId(productId);
						storeService.updateProduct(product);
						
						msg = "Product has been updated successfully";

						page=request.getParameter("page");
						modelMap.put("page", page);
						
						
					} else {
						productaddPage=true;
						//DiskSpacePricing diskSpacePricing =userService.findDiskSpacePricingById(loggedInUser.getDiskSpacePricingId());
						//long maxLimit = adminService.findSiteConstants().getiStoreProductLimit();
						long maxLimit =loggedInUser.getStoreProductLimit();
						int productCount= storeService.findProductCountByStore(store.getId());
						
						
						
						if (productCount < maxLimit) {
							boolean isDublicateProduct=false;
									try{
									List<Product> productlist=storeService.findProductsByName(product.getsName(), product.getDepartmentId()); 
									if(productlist!=null && productlist.size()>0){
										isDublicateProduct=true;
									}
									}
									catch (Exception e) {
										logger.error("productAdd",e);
									}
							 
							
							
							 
							 List<Department> departments = storeService.findDepartmentsByStore(store.getId());		
								
							if(!isDublicateProduct){		
							productId = storeService.createProduct(product);
							if (productId == null) {
								result.addError(new ObjectError("error","There is some error in product addition, Please enter valid values."));
							
								
								
								modelMap.put("departments", departments);
								modelMap.put("error","There is some error in product addition, Please enter valid values.");
								return STORE_CONTENT_PRODUCT_ADD_KEY;

							}
							
							}
							else{
								 
								List<Department> departments2 = storeService.findDepartmentsByStore(product.getStoreId());
								List<Category> categories = storeService.findCategoriesByDepartments(departments2);
								modelMap.put("categories", categories);
								modelMap.put("departments", departments2);
								modelMap.put("error","Product already exist .");	
								return STORE_CONTENT_PRODUCT_ADD_KEY;
							}
						} else {
							result.addError(new ObjectError("error",
									"You have exceeded your product limit of "
									+ maxLimit
									+ " products."));
							List<Department> departments = storeService
							.findDepartmentsByStore(store.getId());
							modelMap.put("departments", departments);

							modelMap.put("error",
									"You have exceeded your product limit of "
									+ maxLimit
									+ " products.");
							return STORE_CONTENT_PRODUCT_ADD_KEY;
						}
					}
					for (Long productOptionsId : productOptionIds) {
						ProductOption productOption = storeService.findProductOption(productOptionsId);
						if(productOption!=null){
							productOption.setProductId(productId);
							storeService.updateProductOption(productOption);
						}
					}
					for (Long productImageId : productImageIds) {
						ProductImage productImage = new ProductImage();
						productImage.setId(productImageId);
						productImage.setProductId(productId);
						storeService.updateProductImage(productImage);
					}
					
					
					
					
					
					
					
					
				} catch (Exception e) {
					logger.error("productAdd",e);
					result.addError(new ObjectError("error",
					"Some Error Occured, Please reenter"));
					List<Department> departments = storeService
					.findDepartmentsByStore(store.getId());
					modelMap.put("departments", departments);
					return STORE_CONTENT_PRODUCT_ADD_KEY;
				}

			}

			List<Product> products =null;//storeService.findProductsByStore(product.getStoreId(),pager);
			//get result Map
			User user = UserBO.getLoggedInUser(request);
			Pager pager=new Pager();
			if(user.getStoreProductLimit()>10)
				pager.setResults(10);
			else
				pager.setResults(user.getStoreProductLimit());
			
			Map<String,Object> resultMap = storeService.findStoreProductsWithPagination(store.getId(),pager);
			//get products
			products =(List<Product>) resultMap.get("productList");
			Integer totalCount= (Integer)resultMap.get("count");
			CustomPagedListHolder  pagedListHolder = new CustomPagedListHolder();
			 if(totalCount>user.getStoreProductLimit()){
				 pagedListHolder.setNrOfElements(user.getStoreProductLimit()); 
				 totalCount=user.getStoreProductLimit();
			 }
			 else{
				 pagedListHolder.setNrOfElements(totalCount);
			 }
					
		     pagedListHolder.setSource(products);	
			List<Department> departments = storeService.findDepartmentsByStore(product.getStoreId());
			List<Category> categories = storeService
			.findCategoriesByDepartments(departments);
			modelMap.put("departments", departments);
			modelMap.put("categories", categories);

			//@SuppressWarnings({ "unchecked", "rawtypes" })
			//PagedListHolder pagedListHolder = new PagedListHolder(products);
			//request.getSession().setAttribute("productList", pagedListHolder);
		
			
	 

			
			modelMap.put("departments", departments);
			modelMap.put("categories", categories);
			modelMap.put("products", products);
			modelMap.put("productList", pagedListHolder);
			modelMap.put("totalCount", totalCount);
			modelMap.put("maxLimit", user.getStoreProductLimit());
			modelMap.put("productaddPage", productaddPage);
			
			//if(modelMap.get("error")==null || "".equals(modelMap.get("error")))
			modelMap.put("success", msg);
			
			if(page!=null){
				return "redirect:store/content?page="+page;
			}else{
				return STORE_CONTENT_VIEW_KEY;
			}
			
		}catch(Exception e){
			logger.error(":"+e.getMessage());
			return USER_DASHBOARD_REDIRECT_VIEW;
		}
	}

	/**
	 * Add New {@link Department}
	 * 
	 * @param department
	 * @param result
	 * @param modelMap
	 * @return redirect to store content view /views/store/content.jsp
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "department/save", method = RequestMethod.POST)
	public String addDepartment(
			@ModelAttribute @Valid Department department,
			BindingResult result, ModelMap modelMap, 
			HttpServletRequest request) {
		if(logger.isDebugEnabled()) logger.debug("adding department{}",department);
		User loggedInUser=UserBO.getLoggedInUser(request); 
		try{
			//check user ownership		
			if(!String.valueOf(loggedInUser.getId()).equalsIgnoreCase(String.valueOf(department.getUserId()))){
				throw new AccessDeniedException("You do not have authority to accomplish this.");
			}
			try {
				Long departmentId = storeService.createDepartment(department);
				department.setId(departmentId);
			} catch (DuplicateDepartmentException e) {
				logger.error("addDepartment ",e);
				modelMap.put("error", labelService.getKeyValueByMerchantID("key.validation.dept.already.exists", loggedInUser.getId()));
				//return STORE_CONTENT_VIEW_KEY;
			}

			
			
			 
			//get products
			logger.debug("addDepartment for store  :"+department.getStoreId());
		   List<Product> products = null;//storeService.findProductsByStore(department.getStoreId());
			//logger.debug("products:"+products.size());
		   CustomPagedListHolder<Product> pagedListHolder=null;
		  List<Department> departments = storeService
			.findDepartmentsByStore(department.getStoreId());
			
			List<Category> categories = storeService
			.findCategoriesByDepartments(departments);

			Pager pager=new Pager();
			pager.setResults(10);
			
			//get result Map
			Map<String,Object> resultMap = storeService.findStoreProductsWithPagination(department.getStoreId(),pager);
			//get products
			products =(List<Product>) resultMap.get("productList");
			Integer totalCount= (Integer)resultMap.get("count");
			
			pagedListHolder = new CustomPagedListHolder();
			pagedListHolder.setSource(products);
			pagedListHolder.setNrOfElements(totalCount);
			pagedListHolder.setPage(pager.getPage());
			
			
			
			
			modelMap.put("departments", departments);
			modelMap.put("categories", categories);
			modelMap.put("products", products);
			modelMap.put("productList", pagedListHolder);
			modelMap.put("success", labelService.getKeyValueByMerchantID("key.validation.dept.created.sucess", loggedInUser.getId()));

			return STORE_CONTENT_VIEW_KEY;

		}catch(Exception e){
			logger.error("addDepartment ",e);
			return USER_DASHBOARD_REDIRECT_VIEW;
		}

	}

	/**
	 * Show Gallery Home View
	 * 
	 * @param galleryImage
	 * @param result
	 * @param modelMap
	 * @return forward to store views/gallery/home.jsp
	 * 
	 */
	@RequestMapping("gallery/home")
	public String home(@ModelAttribute("store") Store store,
			@ModelAttribute GalleryImage galleryImage, BindingResult result,
			ModelMap modelMap) {

		if (store == null) {
			modelMap.put("error", "Invalid session, Please login again");
			return LOGIN_VIEW;
		}
		List<GalleryImage> galleryImages = storeService
		.findGalleryImagesByStore(store.getId());
		modelMap.put("galleryImages", galleryImages);

		return GALLERY_HOME_VIEW_KEY;
	}

	/**
	 * Save gallery image
	 * 
	 * @param galleryImage
	 * @param result
	 * @param modelMap
	 * @return redirect forward to store views/gallery/home.jsp
	 * 
	 */
    	@RequestMapping(value = "gallery/save", method = RequestMethod.POST)
	public String showGallerySuccessView(@ModelAttribute("store") Store store,
			@ModelAttribute GalleryImage galleryImage, BindingResult result,
			ModelMap modelMap,HttpServletRequest request) {

		try {

			if (!galleryImage.getfGalleryImage().getContentType()
					.contains("image")) {
				User user = (User) request.getSession().getAttribute("user");
				try{
					modelMap.put("error", labelService.getKeyValueByMerchantID("key.validation.valid.image.file", user.getId()));
				} catch(SQLException e) {
					logger.error(" exxception in find app by id {} ",e);
				}
				List<GalleryImage> galleryImages = storeService
				.findGalleryImagesByStore(store.getId());
				modelMap.put("galleryImages", galleryImages);
				return GALLERY_HOME_VIEW_KEY;

			}
			PathLocator pathLocator = new PathLocator();
			
			/*
			 *  change to online server for the time being
			 */
			
			String rootPath = pathLocator.getContextPath();
		 
			/*System.out.println("rootPath1:"+rootPath1);
			String contxt=request.getContextPath().substring(1);			
			String rootPath=request.getSession().getServletContext().getRealPath("/");
			       rootPath=rootPath.substring(0,rootPath.length()-1);
			       rootPath=rootPath.substring(0, rootPath.indexOf(contxt));*/
 
			 
 
			String mobicartImagesFolderPath = "mobicartimages";
			FileUtils.makeDirectoryIfItsNotThere(rootPath
					+ mobicartImagesFolderPath);

			String galleryFolderPath = "/gallery";
			FileUtils.makeDirectoryIfItsNotThere(rootPath
					+ mobicartImagesFolderPath + galleryFolderPath);

			String storeFolderPath = "/" + store.getId().toString();
			FileUtils.makeDirectoryIfItsNotThere(rootPath
					+ mobicartImagesFolderPath + galleryFolderPath
					+ storeFolderPath);

			String dateWiseFolderPath = "/" + DateTimeUtils.getCurrentDate();
			FileUtils.makeDirectoryIfItsNotThere(rootPath
					+ mobicartImagesFolderPath + galleryFolderPath
					+ storeFolderPath + dateWiseFolderPath);

			String finalGalleryImagePath = mobicartImagesFolderPath
			+ galleryFolderPath + storeFolderPath + dateWiseFolderPath;

			MultipartFile galleryImageFile = galleryImage.getfGalleryImage();

			String galleryImageFileName=FileUtils.cleanSpecialChars(galleryImageFile.getOriginalFilename());
			       galleryImageFileName=FileUtils.stuffedFilename(galleryImageFileName, "");
			
			FileUtils.makeDirectoryIfItsNotThere(rootPath+finalGalleryImagePath);
			String pathToSaveGalleryImage=rootPath+finalGalleryImagePath +"/";
			
			finalGalleryImagePath += "/"+ galleryImageFileName;
               
			 
			InputStream galleryInputStream = null;
			/**/
			
			/**/
			if (galleryImage.getfGalleryImage().getSize() > 0) {
				
				/*
				galleryInputStream = galleryImageFile.getInputStream();
				BufferedImage bufferedImage = ImageIO.read(galleryInputStream);
				MagicalPower magicalPower=new MagicalPower();
				//save for old file name
				magicalPower.resizeAndSave(bufferedImage, pathToSaveGalleryImage+galleryImageFileName, ImageSize.parse(ResourceProperties.getImageString(Constants.GALLERY_IMAGE_IPHONE_KEY)));
				//new files according to excel sheet
				magicalPower.resizeAndSave(bufferedImage,pathToSaveGalleryImage,galleryImageFileName ,Constants.GALLERY_IMAGE_ANDROID3_KEY);
				magicalPower.resizeAndSave(bufferedImage,pathToSaveGalleryImage,galleryImageFileName ,Constants.GALLERY_IMAGE_ANDROID4_KEY);
				magicalPower.resizeAndSave(bufferedImage,pathToSaveGalleryImage,galleryImageFileName ,Constants.GALLERY_IMAGE_ANDROID6_KEY);
				magicalPower.resizeAndSave(bufferedImage,pathToSaveGalleryImage,galleryImageFileName ,Constants.GALLERY_IMAGE_IPAD_KEY);
				magicalPower.resizeAndSave(bufferedImage,pathToSaveGalleryImage,galleryImageFileName ,Constants.GALLERY_IMAGE_IPHONE_KEY);
				galleryInputStream.close(); 
				*/
				
			 
			     String tempCopy=ImgMagicCmdExcecutor.saveTempFile(galleryImageFile.getInputStream(), rootPath+finalGalleryImagePath);
			   
				//new files according to excel sheet
				 ImgMagicCmdExcecutor.resizeAndSaveFile(tempCopy, pathToSaveGalleryImage,galleryImageFileName,Constants.GALLERY_IMAGE_IPHONE_KEY,true);
				//new files according to excel sheet
				 ImgMagicCmdExcecutor.resizeAndSaveFile(tempCopy,pathToSaveGalleryImage,galleryImageFileName ,Constants.GALLERY_IMAGE_ANDROID3_KEY,true);
				 ImgMagicCmdExcecutor.resizeAndSaveFile(tempCopy,pathToSaveGalleryImage,galleryImageFileName ,Constants.GALLERY_IMAGE_ANDROID4_KEY,true);
				 ImgMagicCmdExcecutor.resizeAndSaveFile(tempCopy,pathToSaveGalleryImage,galleryImageFileName ,Constants.GALLERY_IMAGE_ANDROID6_KEY,true);
				 ImgMagicCmdExcecutor.resizeAndSaveFile(tempCopy,pathToSaveGalleryImage,galleryImageFileName ,Constants.GALLERY_IMAGE_IPAD_KEY,true);
				 ImgMagicCmdExcecutor.resizeAndSaveFile(tempCopy,pathToSaveGalleryImage,galleryImageFileName ,Constants.GALLERY_IMAGE_IPHONE_KEY,true);
				 ImgMagicCmdExcecutor.resizeAndSaveFile(tempCopy,pathToSaveGalleryImage,galleryImageFileName ,Constants.GALLERY_IMAGE_IPHONE4_KEY,true);
			
			}

			logger.info("path" + rootPath + finalGalleryImagePath);
           
			galleryImage.setsThumbnail("/" + finalGalleryImagePath);

			/*
			 *  add store id and user id
			 */
			galleryImage.setStoreId(store.getId());
			galleryImage.setUserId(store.getUserId());
			galleryImage.setsTitle(galleryImageFile.getOriginalFilename());
			App app = appService.findAppByUser(new User(store.getUserId()));
			galleryImage.setAppId(app.getId());
			storeService.saveGalleryImage(galleryImage);

			List<GalleryImage> galleryImages = storeService
			.findGalleryImagesByStore(store.getId());
			modelMap.put("galleryImages", galleryImages);

		} catch (Exception e) {
	        e.printStackTrace();
			logger.error("Gallery Image Upload Error :" + e); //$NON-NLS-1$
			ObjectError error = new ObjectError("error",
					"Some error occured : " + e.getLocalizedMessage());
			result.addError(error);
			modelMap.put("error", "Gallery image could not be saved.");
			List<GalleryImage> galleryImages = storeService.findGalleryImagesByStore(store.getId());
			modelMap.put("galleryImages", galleryImages);
			return GALLERY_HOME_VIEW_KEY;
		}

		modelMap.put("success",
		"New gallery image has been uploaded successfully.");

		return GALLERY_HOME_VIEW_KEY;
	}

	/**
	 * Show news home
	 * 
	 * @param news
	 * @param result
	 * @param modelMap
	 * @return path to views/news/home.jsp
	 * 
	 */
	@RequestMapping("/news")
	public String showNewsHome(ModelMap modelMap,HttpServletRequest request ) {

		modelMap.put("tabSelect", 0);

		Store store = (Store) request.getSession().getAttribute("store");
		User user = (User) request.getSession().getAttribute("user");

		try{
			App app=appService.findAppByUser(user);

			List<News> newsItems = storeService.findNewsItemsByStore(store.getId());
			List <PushNotification> notification= appService.getNotificationsByApp(app.getId());

			Boolean bShowNewsTable = false;
			News feedItem = null;
			News twitterItem = null;
			for (Iterator<News> i = newsItems.iterator(); i.hasNext();) {
				News item = i.next();
				if (item.getsType().equals("custom")) {
					bShowNewsTable = true;
				} else if (item.getsType().equals("feed")) {
					feedItem = item;
					i.remove();
				} else if (item.getsType().equals("twitter")) {
					twitterItem = item;
					i.remove();
				}
			}
			modelMap.put("newsItems", newsItems);
			modelMap.put("bShowNewsTable", bShowNewsTable);
			modelMap.put("feedItem", feedItem);
			modelMap.put("twitterItem", twitterItem);
			modelMap.put("notifications", notification);
			
		   String crtificateFileName=app.getsPnCertificatePath();
		   if(crtificateFileName!=null){
		   crtificateFileName=crtificateFileName.substring(crtificateFileName.lastIndexOf("/")+1);
		         
		        }
		   
			
			modelMap.put("crtificateFileName",crtificateFileName);

			modelMap.put("news", new News());
			modelMap.put("androidpAppPackage",UserBO.androidAppPackageName(app.getsName(), app.getUserId()));
			modelMap.put("pushNotification", new PushNotification());
			
		}catch(Exception e){
			logger.error("showNewsHome",e);
		}
		return "news/home";
	}

	/**
	 * Edit news
	 * 
	 * @param news
	 * @param result
	 * @param newsId
	 * @param modelMap
	 * @return path to views/news/editNews.jsp
	 */
	@RequestMapping(value = "/news/{newsId}/edit")
	public String showEditNews(@ModelAttribute News news, BindingResult result,
			@PathVariable Long newsId, ModelMap modelMap,HttpServletRequest request) {

		try{
			news = storeService.findNewsById(newsId);
			Store store = (Store) request.getSession().getAttribute("store");
			
			/**
			 * check news ownership		
			 */
			if(!String.valueOf(store.getId()).equalsIgnoreCase(String.valueOf(news.getStoreId()))){
				throw new AccessDeniedException("You do not have authority to accomplish this.");
			}
			modelMap.put("news", news);
			return "news/editNews";


		}catch(Exception e){
			logger.error("showEditNews",e);
			return USER_DASHBOARD_REDIRECT_VIEW;
		}


	}

	/**
	 * Save /update news item
	 * 
	 * @param store
	 * @param news
	 * @param result
	 * @param modelMap
	 * @return
	 * 
	 */
	@RequestMapping(value = "/news/save", method = RequestMethod.POST)
	public String saveNews(@ModelAttribute News news, BindingResult result,
			@RequestParam("storeId") Long storeId, ModelMap modelMap,
			HttpServletRequest request) {
		modelMap.put("tabSelect", 1);

		try{
			User loggedInUser=UserBO.getLoggedInUser(request); 
			Store store = (Store) request.getSession().getAttribute("store");
			App app=appService.findAppByUser(loggedInUser);

			/**
			 * check store ownership
			 */
			if(!String.valueOf(storeId).equalsIgnoreCase(String.valueOf(store.getId()))){
				throw new AccessDeniedException("You do not have authority to accomplish this.");
			}

			List <PushNotification> notification= appService.getNotificationsByApp(app.getId());
			try {
				
				MultipartFile newsImageFile = news.getfNewsImage();
				PathLocator pathLocator = new PathLocator();
				String rootPath = pathLocator.getContextPath();

				if (newsImageFile.getSize() > 0) {
					if (!newsImageFile.getContentType().contains("image")) {
						modelMap.put("error", "Please upload valid icon image file");
						return "news/home";
					}

					
				String mobicartImagesFolderPath = "mobicartimages";
				FileUtils.makeDirectoryIfItsNotThere(rootPath
						+ mobicartImagesFolderPath);

				String storeFolderPath = "/" + store.getId();
				FileUtils.makeDirectoryIfItsNotThere(rootPath
						+ mobicartImagesFolderPath + storeFolderPath);

			
					String newsFolderPath = "/news";
					FileUtils.makeDirectoryIfItsNotThere(rootPath
							+ mobicartImagesFolderPath + newsFolderPath);

					
					FileUtils.makeDirectoryIfItsNotThere(rootPath
							+ mobicartImagesFolderPath + newsFolderPath
							+ storeFolderPath);

					String finalNewsImagePath = mobicartImagesFolderPath
					+ newsFolderPath
					+ storeFolderPath
					+ "/"
					+ FileUtils.cleanSpecialChars(newsImageFile
							.getOriginalFilename());

					InputStream newsInputStream = null;
					OutputStream newsOutputStream = null;
					newsInputStream = newsImageFile.getInputStream();
					BufferedImage bufferedNewsImage = ImageIO.read(newsInputStream);
					BufferedImage bufferedNewsImageResized= ImageUtils.resize(bufferedNewsImage, Constants.NEWS_IMAGE_SMALL_SIZE_HEIGHT,Constants.NEWS_IMAGE_SMALL_SIZE_WIDTH, true);
					newsOutputStream = new FileOutputStream(rootPath
							+ finalNewsImagePath);
					String format = (newsImageFile.getOriginalFilename()
							.endsWith(".png")) ? "png" : "jpg";
					ImageIO.write(bufferedNewsImageResized, format, newsOutputStream);
					newsInputStream.close();
					newsOutputStream.close();
					news.setsImage("/" + finalNewsImagePath);
				}

				news.setStoreId(storeId);
				news.setAppId(app.getId());
				news.setsType("custom");
				news.setdDate(DateTimeUtils.getCurrentTimestamp());
				news.setbFeedStatus(news.getbFeedStatus() == null ? false : news
						.getbFeedStatus());
				news.setbTwitterStatus(news.getbTwitterStatus() == null ? false
						: news.getbTwitterStatus());
				storeService.saveNewsItem(news);
				if(logger.isDebugEnabled())logger.debug("News Items saved");
				if (news.getId() != null) {
					modelMap.put("success",
					"News item has been created successfully");
				} else {
					modelMap.put("success",
					"News item has been updated successfully");
				}
				modelMap.put("success",
				"Your settings have been updated successfully.");
			} catch (Exception e) {
				logger.error("News Image Upload Error :" + e); //$NON-NLS-1$
				ObjectError error = new ObjectError("error",
						"Some error occured : " + e.getLocalizedMessage());
				result.addError(error);
				modelMap.put("error", "News item could not be saved");
			}

			List<News> newsItems = storeService.findNewsItemsByStore(storeId);
			Boolean bShowNewsTable = false;
			News feedItem = null;
			News twitterItem = null;
			for (Iterator<News> i = newsItems.iterator(); i.hasNext();) {
				News item = i.next();
				if (item.getsType().equals("custom")) {
					bShowNewsTable = true;
				} else if (item.getsType().equals("feed")) {
					feedItem = item;
					twitterItem = item;
					i.remove();
				} else if (item.getsType().equals("twitter")) {
					twitterItem = item;
					i.remove();
				}
			}
			modelMap.put("newsItems", newsItems);
			modelMap.put("bShowNewsTable", bShowNewsTable);
			modelMap.put("feedItem", feedItem);
			modelMap.put("twitterItem", twitterItem);
			modelMap.put("notifications", notification);

			modelMap.put("news", new News());
			modelMap.put("pushNotification", new PushNotification());
			
			return "news/home";

		}catch(Exception e){
			logger.error("saveNews ",e);
			return USER_DASHBOARD_REDIRECT_VIEW;
		}
	}


/**
 * Schedule push notifications
 * @param pushNotification
 * @param result
 * @param modelMap
 * @param request
 * @return
 */
	@RequestMapping(value = "push-notify")
	public String schedulePushNotification(@ModelAttribute PushNotification pushNotification, BindingResult result, ModelMap modelMap,HttpServletRequest request) {
		try{
			modelMap.put("tabSelect", 0);
			
			User user = (User) request.getSession().getAttribute("user");
			
			String zipcode=pushNotification.getZipcode();
			Integer radius=pushNotification.getRadius();
			String units=pushNotification.getUnits();
	
			Store store = (Store) request.getSession().getAttribute("store");

			App app=appService.findAppByUser(user);

			List<News> newsItems = storeService.findNewsItemsByStore(store.getId());
			Boolean bShowNewsTable = false;
			News feedItem = null;
			News twitterItem = null;
			for (Iterator<News> i = newsItems.iterator(); i.hasNext();) {
				News item = i.next();
				if (item.getsType().equals("custom")) {
					bShowNewsTable = true;
				} else if (item.getsType().equals("feed")) {
					feedItem = item;
					i.remove();
				} else if (item.getsType().equals("twitter")) {
					twitterItem = item;
					i.remove();
				}
			}
			
			String crtificateFileName=app.getsPnCertificatePath();
			if(crtificateFileName!=null)
			 {
			   crtificateFileName=crtificateFileName.substring(crtificateFileName.lastIndexOf("/")+1);
					         
					        }
				
			
			modelMap.put("newsItems", newsItems);
			modelMap.put("bShowNewsTable", bShowNewsTable);
			modelMap.put("feedItem", feedItem);
			modelMap.put("twitterItem", twitterItem);
			List <PushNotification> notification= appService.getNotificationsByApp(app.getId());
			modelMap.put("notifications", notification);
			modelMap.put("news", new News());
			modelMap.put("crtificateFileName",crtificateFileName);
			
			
			if(pushNotification.getSendTo().equalsIgnoreCase("A")){
				zipcode=null;
				radius=null;
				units=null;
			}else{
				
				if(StringUtils.isEmpty(zipcode)  && StringUtils.isEmpty(units)){
					modelMap.put("error",
					"Please enter valid values for radius and distance");
					return "news/home";
				}else{
					if(!StringUtils.isAlphanumericSpace(zipcode)){
						modelMap.put("error",
						"Please enter valid values for Zip/Postalcode");
						return "news/home";
					}
				}
			}

			String payerStatus = (String)request.getParameter("payer_status");//== verified
			String paymentStatus = (String)request.getParameter("payment_status");// == completed
			
				//System.out.println("payerStatus:"+payerStatus+",paymentStatus:"+paymentStatus);
			/*if((!CsastoreStringUtil.isNullorEmpty(payerStatus)) && (!CsastoreStringUtil.isNullorEmpty(paymentStatus))){
		 		if(payerStatus.equalsIgnoreCase("verified") && paymentStatus.equalsIgnoreCase("completed")){
		 			
		 		}
		 		}*/
			
			if(user!=null){

				pushNotification.setAppId(app.getId());
				pushNotification.setDate(new Date());
				pushNotification.setNotifiedCount(0);
				pushNotification.setStatus("pending");

				
				// validate zip code
				if (zipcode != null && zipcode.length() > 1) {
					// get devices by radius and
					ArrayList<Double> points = pushNotificationBO.getLatitude(zipcode);

					Double latitude=0.0;
					Double longitude=0.0;
					if(points!=null && points.size()>1){
						 latitude = points.get(0);
						 longitude = points.get(1);
					}else{
						modelMap.put("error",
						"There are no devices in this radius, Please enter valid details.");
						return "news/home";
					}
					float actualRadius = 0;
					if (units != null && units.equalsIgnoreCase("M")) {
						actualRadius = 1.6f * radius;
					}else{
						actualRadius=1f*radius;
					}
					List<AppDeviceTokens> device_tokens=null;
					if(longitude!=0.0 && latitude!=0.0){
					device_tokens = appService
							.getDevicesByRadius(latitude, longitude,
									String.valueOf(actualRadius), app.getId());
					}

					if (device_tokens != null && device_tokens.size() > 0) {
						appService.scheduleNotification(pushNotification);
						modelMap.put("success", "Your notification has been scheduled successfully.");
						notification= appService.getNotificationsByApp(app.getId());
						modelMap.put("notifications", notification);
						modelMap.put("pushNotification", new PushNotification());
						
					}else{
						modelMap.put("error",
						"There are no devices in this area, Please enter valid details.");
				return "news/home";
					}
				}else{
					//save notification for all
					appService.scheduleNotification(pushNotification);
					modelMap.put("success", "Your notification has been scheduled successfully.");
					notification= appService.getNotificationsByApp(app.getId());
					modelMap.put("notifications", notification);
					modelMap.put("pushNotification", new PushNotification());

				}
				
			}else{
				modelMap.put("error", "Session expired, please login again.");
			}

		}catch(Exception e){
			logger.error("notify",e);
			modelMap.put("error", "Error in scheduling message. Please try again.");
		}
		return "news/home";
	}


	@RequestMapping(value = "deletenotification")
	public @ResponseBody String deletenotification(@RequestParam ("id") Long id, ModelMap modelMap,HttpServletRequest request) {

		try{
			PushNotification pushNotification = appService.findNotificationById(id);    	
			pushNotification.setStatus("deleted");	
			appService.updateNotification(pushNotification);    	
			return "success";

		}catch(Exception e){
			logger.error("deletenotification",e);
			return "error";
		}

	}


	/**
	 * Show feed view
	 * 
	 * @param news
	 * @param result
	 * @param modelMap
	 * @return
	 * 
	 */

	@RequestMapping("/feeds")
	public String showFeedsHome(@ModelAttribute("store") Store store,
			@ModelAttribute News news, BindingResult result, ModelMap modelMap) {
		modelMap.put("tabSelect", 1);
		List<News> newsItems = storeService.findNewsItemsByStore(store.getId());

		Boolean bShowNewsTable = false;
		News feedItem = null;
		News twitterItem = null;
		for (Iterator<News> i = newsItems.iterator(); i.hasNext();) {
			News item = i.next();
			if (item.getsType().equals("custom")) {
				bShowNewsTable = true;
			} else if (item.getsType().equals("feed")) {
				feedItem = item;
				twitterItem = item;
				i.remove();
			} else if (item.getsType().equals("twitter")) {
				twitterItem = item;
				i.remove();
			}
		}
		modelMap.put("newsItems", newsItems);
		modelMap.put("bShowNewsTable", bShowNewsTable);
		modelMap.put("feedItem", feedItem);
		modelMap.put("twitterItem", twitterItem);

		return "feed/feed";

	}

	/**
	 * Edit news
	 * 
	 * @param news
	 * @param result
	 * @param newsId
	 * @param modelMap
	 * @return path to views/news/editNews.jsp
	 */
	@RequestMapping(value = "/feed/{newsId}/edit")
	public String showEditFeed(

			@ModelAttribute News news, 
			BindingResult result,
			@PathVariable Long newsId, 
			ModelMap modelMap,
			HttpServletRequest request) {

		news = storeService.findNewsById(newsId);

		/*
		 * User loggedInUser=UserBO.getLoggedInUser(request); 
		 */
		try {
			Store store = (Store) request.getSession().getAttribute("store");
			/*
			 * check store ownership
			 */
			if(!String.valueOf(store.getId()).equalsIgnoreCase(String.valueOf(news.getStoreId()))){
				throw new AccessDeniedException("You do not have authority to accomplish this.");
			}

			modelMap.put("news", news);
			return "news/editFeed";

		}catch(Exception e){
			logger.error("showEditFeed",e);
			return USER_DASHBOARD_REDIRECT_VIEW;
		}

	}

	/**
	 * Save feed
	 * 
	 * @param news
	 * @param result
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/feeds/save", method = RequestMethod.POST)
	public String showFeedsResponseView(@ModelAttribute News news,
			BindingResult result, @RequestParam("storeId") Long storeId,
			ModelMap modelMap, HttpServletRequest request) {
		modelMap.put("tabSelect", 1);
		news.setsType("feed");
		news.setStoreId(storeId);
		
		User user = (User) request.getSession().getAttribute("user");

		App app = null;
		try {
			app = appService.findAppByUser(user);
		} catch (SQLException e) {
			logger.error("showFeedsResponseView",e);
		}
		if (app != null) {
			news.setAppId(app.getId());
			news.setdDate(DateTimeUtils.getCurrentTimestamp());
			
			//validate feed
			boolean validFeed=false;
			if(news.getsFeedUrl()!=null && news.getsFeedUrl().length()>0){
				if(ValidationUtils.validateFeed(news.getsFeedUrl())){
					validFeed=true;
				}else{
					modelMap.put("error", "Please enter a valid RSS feed URL.");
				}
			}else{
				//if feed is not entered save twitter username on
				validFeed=true;
			}
			
			news.setbFeedStatus(news.getbFeedStatus() == null ? false : news
					.getbFeedStatus());
			news.setbTwitterStatus(news.getbTwitterStatus() == null ? false
					: news.getbTwitterStatus());
			if(validFeed){
			storeService.saveNewsItem(news);
			modelMap.put("success",
			"Your settings have been updated successfully.");
			}
		} else {
			modelMap.put("error", "Your settings could not be updated.");
		}

		List<News> newsItems = storeService.findNewsItemsByStore(storeId);
		Boolean bShowNewsTable = false;
		News feedItem = null;
		News twitterItem = null;
		for (Iterator<News> i = newsItems.iterator(); i.hasNext();) {
			News item = i.next();
			if (item.getsType().equals("custom")) {
				bShowNewsTable = true;
			} else if (item.getsType().equals("feed")) {
				feedItem = item;
				twitterItem = item;
				i.remove();
			} else if (item.getsType().equals("twitter")) {
				twitterItem = item;
				i.remove();
			}
		}
		modelMap.put("newsItems", newsItems);
		modelMap.put("bShowNewsTable", bShowNewsTable);
		modelMap.put("feedItem", feedItem);
		modelMap.put("twitterItem", twitterItem);

		return "feed/feed";
	}

	/**
	 * Save feed
	 * 
	 * @param news
	 * @param result
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/twitter/save", method = RequestMethod.POST)
	public String showTwitterResponseView(@ModelAttribute News news,
			BindingResult result, @RequestParam("storeId") Long storeId,
			ModelMap modelMap, HttpServletRequest request) {
		modelMap.put("tabSelect", 2);
		news.setsType("twitter");
		news.setsTitle(news.getsTwitterUsername());
		news.setStoreId(storeId);
		User user = (User) request.getSession().getAttribute("user");
		App app = null;
		try {
			app = appService.findAppByUser(user);
		} catch (SQLException e) {
			logger.error("showTwitterResponseView", e);
		}
		if (app != null) {
			news.setAppId(app.getId());
			news.setdDate(DateTimeUtils.getCurrentTimestamp());
			news.setbFeedStatus(news.getbFeedStatus() == null ? false : news
					.getbFeedStatus());
			news.setbTwitterStatus(news.getbTwitterStatus() == null ? false
					: news.getbTwitterStatus());
			storeService.saveNewsItem(news);
			modelMap.put("success",
			"Your settings have been updated successfully.");
		} else {
			logger.info("settings can not be updated");
			modelMap.put("error", "Your settings can not be updated.");
		}
		List<News> newsItems = storeService.findNewsItemsByStore(storeId);
		Boolean bShowNewsTable = false;
		News feedItem = null;
		News twitterItem = null;
		for (Iterator<News> i = newsItems.iterator(); i.hasNext();) {
			News item = i.next();
			if (item.getsType().equals("custom")) {
				bShowNewsTable = true;
			} else if (item.getsType().equals("feed")) {
				feedItem = item;
				twitterItem = item;
				i.remove();
			} else if (item.getsType().equals("twitter")) {
				twitterItem = item;
				i.remove();
			}
		}
		modelMap.put("newsItems", newsItems);
		modelMap.put("bShowNewsTable", bShowNewsTable);
		modelMap.put("feedItem", feedItem);
		modelMap.put("twitterItem", twitterItem);

		return "news/home";
	}

	/**
	 * List all the pages
	 * 
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/pages/list", method = RequestMethod.GET)
	public String showListPage(ModelMap modelMap, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		List<StaticPage> pages = appService.findStaticPageByUser(user.getId());
		Collections.sort(pages, StaticPage.PRIORITY_ORDER);
		modelMap.put("pages", pages);
		return "pages/listPage";
	}

	/**
	 * Edit Page
	 * 
	 * @param staticPage
	 * @param result
	 * @param pageId
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/page/edit/{pageId}", method = RequestMethod.GET)
	public String showEditPage(
			@ModelAttribute("staticPage") StaticPage staticPage,
			BindingResult result, @PathVariable Long pageId, ModelMap modelMap,HttpServletRequest request) {

		staticPage = appService.findStaticPageById(pageId);
		User loggedInUser=UserBO.getLoggedInUser(request); 

		try {
			/**
			 * check store ownership
			 */
			if(!String.valueOf(loggedInUser.getId()).equalsIgnoreCase(String.valueOf(staticPage.getUserId()))){
				throw new AccessDeniedException("You do not have authority to accomplish this.");
			}
			modelMap.put("savedContent", staticPage.getsDescription());
			modelMap.put("staticPage", staticPage);
			return "pages/editPage";

		}catch(Exception e){
			logger.error("showEditPage",e);
			return USER_DASHBOARD_REDIRECT_VIEW;
		}
	}

	/**
	 * save success page
	 * @param staticPage
	 * @param result
	 * @param modelMap
	 * @return
	 */

	@RequestMapping(value = "/page/save", method = RequestMethod.POST)
	public String showEditSuccessPage(
			@ModelAttribute("staticPage") StaticPage staticPage,
			BindingResult result, @RequestParam("pageId") Long pageId,
			ModelMap modelMap, HttpServletRequest request) {
		staticPage.setId(pageId);
		appService.updateStaticPage(staticPage);
	
		if (staticPage.getsName().equals("copyright")) {
			User user = (User) request.getSession().getAttribute("user");
			user.setbCustomCopyrightPage(staticPage.getbCustomCopyrightPage());
			userService.updateUser(user);
		}

		modelMap.put("success", "Your settings have been updated successfully.");

		return "pages/editPage";
	}

 @RequestMapping(value = "/create/productImages", method = RequestMethod.GET)
	public String generateImagesForProduct(
			@RequestParam(value = "su", required = true) Long su,
			@RequestParam(value ="eu",required=true) Long eu,
			ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response
			) {
	 try{
		 
		 
		 
		 PathLocator pathLocator = new PathLocator();
		 String rootPath = pathLocator.getContextPath();
		 int index=rootPath.lastIndexOf("\\");
	    	if(index==-1)
	    		index=rootPath.lastIndexOf("/");
	    	rootPath=rootPath.substring(0,index);
	    	
	 /*List of product images*/
	 for(Long userId=su;userId<=eu;userId++){
		List<String> bulkListForProductsImages=new  ArrayList<String>(); 
	    
		  /* ProductExample proexmp=new ProductExample();
		      proexmp.createCriteria().andUserIdEqualTo(userId);
		      List<Product> productList=productDao.findByExample(proexmp);
	     
	     for(Product product:productList){
	   
	     
	      if(product==null)
	    	  continue;
	      List<ProductImage> pimg=product.getProductImages();
	      
	           for(ProductImage proimg:pimg){
	        	   if(proimg==null)
	        		   continue;
	        	   bulkListForProductsImages.add(proimg.getsLocation());
	           }
	     }*/
	     
		bulkListForProductsImages=storeService.getProductImagesListByUserId(userId);
	     
	     for(String pi:bulkListForProductsImages){
	    	 
		    	String tempCopy=pi;
		    	System.out.println("tempCopy:"+tempCopy);
		    	if((tempCopy==null) || "".equals(tempCopy)){
		    		continue;
		    	}
		    	
		    	
		    	 
		    	ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath+tempCopy, rootPath,tempCopy,"small",true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath+tempCopy, rootPath,tempCopy,"medium",true);
 
				//ImgMagicCmdExcecutor.resizeAndSaveFile(tempFile, rootPath, originalImageFileName, propertyKey,true)
				  ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopy,Constants.PRODUCT_IMAGE_SMALL_ANDROID3_KEY ,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopy,Constants.PRODUCT_IMAGE_SMALL_ANDROID4_KEY ,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopy,Constants.PRODUCT_IMAGE_SMALL_ANDROID6_KEY ,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopy,Constants.PRODUCT_IMAGE_SMALL_IPAD_KEY ,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopy,Constants.PRODUCT_IMAGE_SMALL_IPHONE_KEY ,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopy,Constants.PRODUCT_IMAGE_SMALL_IPHONE4_KEY ,true);
				
				ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopy,Constants.PRODUCT_IMAGE_MEDIUM_ANDROID3_KEY ,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopy,Constants.PRODUCT_IMAGE_MEDIUM_ANDROID4_KEY ,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopy,Constants.PRODUCT_IMAGE_MEDIUM_ANDROID6_KEY ,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopy,Constants.PRODUCT_IMAGE_MEDIUM_IPAD_KEY ,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopy,Constants.PRODUCT_IMAGE_MEDIUM_IPHONE_KEY ,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopy,Constants.PRODUCT_IMAGE_MEDIUM_IPHONE4_KEY ,true);
				
				ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopy,Constants.PRODUCT_IMAGE_COVER_FLOW_ANDROID3_KEY ,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopy,Constants.PRODUCT_IMAGE_COVER_FLOW_ANDROID4_KEY ,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopy,Constants.PRODUCT_IMAGE_COVER_FLOW_ANDROID6_KEY ,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopy,Constants.PRODUCT_IMAGE_COVER_FLOW_IPAD_KEY ,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopy,Constants.PRODUCT_IMAGE_COVER_FLOW_IPHONE_KEY ,true);	
				ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopy,Constants.PRODUCT_IMAGE_COVER_FLOW_IPHONE4_KEY ,true);
				
				ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopy,Constants.PRODUCT_IMAGE_DETAIL ,true);				
			 
		    	 
		    	
		    	
		    	}
	 }
	     response.getWriter().append("Successfully croped");
	 }
	 catch (DataAccessException de) {
		 de.printStackTrace();
		// TODO: handle exception
	}
	 catch (Exception e) {
		 e.printStackTrace();
		 try{
		 response.getWriter().append(e.getStackTrace().toString());}
		 catch (Exception e1) {
			 e1.printStackTrace();
		}
	}
	
	 finally{
		 
	 }
	     
	 
	 return null;
 }
    
 
 @RequestMapping(value = "/create/galleryImages", method = RequestMethod.GET)
	public String generateGallaryImagesForUSer(
			@RequestParam(value = "su", required = true) Long su,
			@RequestParam(value ="eu",required=true) Long eu,
			ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response
			) {
		 try{
			 
			 PathLocator pathLocator = new PathLocator();
			 String rootPath = pathLocator.getContextPath();
			 int index=rootPath.lastIndexOf("\\");
		    	if(index==-1)
		    		index=rootPath.lastIndexOf("/");
		    	rootPath=rootPath.substring(0,index);
			 /*list of galary images*/
		    	
		    	for(Long userId=su;userId<=eu;userId++){
		    		/*GalleryImageExample gime=new GalleryImageExample();   
			    gime.createCriteria().andUserIdEqualTo(userId);
			    List<GalleryImage> bulkListForGallary=galleryImgDao.findByExample(gime);*/
		     
		    	List<GalleryImage> bulkListForGallary=storeService.getGalleryImageByUserId(userId);
			    /*Generate images for gallery images*/
			    for(GalleryImage gi:bulkListForGallary){
			    	
			    	
			    	
			    	String tempCopy=gi.getsThumbnail();//fd
			    	if((tempCopy==null) || "".equals(tempCopy)){
			    		continue;
			    	}
			    	
			    	 /*
				    ImgMagicCmdExcecutor.resizeAndSaveFile(tempCopy, pathToSaveGalleryImage,galleryImageFileName,Constants.GALLERY_IMAGE_IPHONE_KEY,true);
					//new files according to excel sheet
					 *  */
					  ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopy,Constants.GALLERY_IMAGE_ANDROID3_KEY,true);
					 ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopy,Constants.GALLERY_IMAGE_ANDROID4_KEY,true);
					 ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopy ,Constants.GALLERY_IMAGE_ANDROID6_KEY,true);
					 ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopy,Constants.GALLERY_IMAGE_IPAD_KEY,true);
					 ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopy ,Constants.GALLERY_IMAGE_IPHONE_KEY,true);
				     ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopy,Constants.GALLERY_IMAGE_IPHONE4_KEY,true);
						 
				    
			    
			    
			    }
		    	}
			    response.getWriter().append("Successfully croped");
		 }
		 catch (DataAccessException de) {
			 de.printStackTrace();
			// TODO: handle exception
		}
		 catch (Exception e) {
			 e.printStackTrace();
			 try{
			 response.getWriter().append(e.getStackTrace().toString());}
			 catch (Exception e1) {
				 e1.printStackTrace();
			}
		}
		
		 finally{
			 
		 }
		 
		 return null;
		 }
 
 
 @RequestMapping(value = "/create/comapnyLogo", method = RequestMethod.GET)
	public String generateImagesForUSer(
			@RequestParam(value = "su", required = true) Long su,
			@RequestParam(value ="eu",required=true) Long eu,
			ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response
			) {
		 try{
			 
			 PathLocator pathLocator = new PathLocator();
			 String rootPath = pathLocator.getContextPath();
			 
			 int index=rootPath.lastIndexOf("\\");
		    	if(index==-1)
		    		index=rootPath.lastIndexOf("/");
		    	
		    	rootPath=rootPath.substring(0,index);
			 /*List for company logo*/
		    	for(Long userId=su;userId<=eu;userId++){
		    		/*  UserExample exmp=new UserExample();
			    exmp.createCriteria().andIdEqualTo(userId);
			     List<User> usersList=userDao.findByExample(exmp);
			    
			    for(User user:usersList){
			    	
			    	String tempCopy=user.getsCompanyLogo();
			    	if((tempCopy==null) || "".equals(tempCopy)){
			    		continue;
			    	}*/
			    /*new code*/
			   User user=userService.find(userId);
			    if(user!=null){	 
					
			    	String tempCopy=user.getsCompanyLogo();
			    	if((tempCopy==null) || "".equals(tempCopy)){
			    		continue;
			    	}
			    	
			    	String websiteCompanyLogoImagePath=FileUtils.stuffedFilename(rootPath+tempCopy, "_website");
	    			String ipadCompanyLogoImagePath=FileUtils.stuffedFilename(rootPath+tempCopy, "_ipad");
	    			
			    	
				    ImgMagicCmdExcecutor.resizeAndSave(rootPath+tempCopy,rootPath+tempCopy,Constants.COMPANY_LOGO_WIDTH,Constants.COMPANY_LOGO_HEIGHT,true);
				    ImgMagicCmdExcecutor.resizeAndSave(rootPath+tempCopy,websiteCompanyLogoImagePath,Constants.COMPANY_LOGO_IPHONE_WIDTH, Constants.COMPANY_LOGO_IPHONE_HEIGHT,false);
					ImgMagicCmdExcecutor.resizeAndSave(rootPath+tempCopy,ipadCompanyLogoImagePath,Constants.COMPANY_LOGO_IPHONE_WIDTH, Constants.COMPANY_LOGO_IPHONE_HEIGHT,false);
					 
					
					ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopy,Constants.COMPANY_LOGO_ANDROID3_KEY,true);
					ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopy,Constants.COMPANY_LOGO_ANDROID4_KEY,true);
					ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopy,Constants.COMPANY_LOGO_ANDROID6_KEY,true);
					ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopy,Constants.COMPANY_LOGO_IPAD_KEY,true);
					ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopy,Constants.COMPANY_LOGO_IPHONE_KEY,true);
					ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopy,Constants.COMPANY_LOGO_IPHONE4_KEY,true);
					ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopy,Constants.COMPANY_LOGO_WEBSITE_KEY,true);	
				    
			    	
			    	
			    }
		    	}
			    response.getWriter().append("Successfully croped");
					 }
					 catch (DataAccessException de) {
						 de.printStackTrace();
						// TODO: handle exception
					}
					 catch (Exception e) {
						 e.printStackTrace();
						 try{
						 response.getWriter().append(e.getStackTrace().toString());}
						 catch (Exception e1) {
							 e1.printStackTrace();
						}
					}
					
					 finally{
						 
					 }
					 
					 return null;
					 
		 }
			 
 
	@RequestMapping(value = "/create/appVitalImages", method = RequestMethod.GET)
	public String generateImagesForCompanyLogo(
			@RequestParam(value = "su", required = true) Long su,
			@RequestParam(value ="eu",required=true) Long eu,
			ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response
			) {
		
		//String msg=cropProductImages(Long statingUser,Long endingUser){}
		
		
		 try{
			 
			 PathLocator pathLocator = new PathLocator();
			 String rootPath = pathLocator.getContextPath();
             
			 int index=rootPath.lastIndexOf("\\");
		    	if(index==-1)
		    		index=rootPath.lastIndexOf("/");
			 rootPath=rootPath.substring(0,index);
	    /*List of icon images  and loader images */
			 
			 for(Long userId=su;userId<=eu;userId++){
	   /* AppExample aexmp=new AppExample();
	    aexmp.createCriteria().andUserIdEqualTo(userId);
	    
	    
	    List<App> appList=appDao.findAppsByExample(aexmp);*/
	    
	   List<App> appList=appService.findAppsByUserId(userId);
	    for(App app:appList){
	    	
	    	String tempCopyIcon=app.getsIconImage();
	    	String tempCopyLoader=app.getsLoaderImage();
	    	if(tempCopyIcon!=null && !"".equals(tempCopyIcon)){
	    		
	    		 
	    		 /*For app loader*/
	    	    /*String tempFile=ImgMagicCmdExcecutor.saveTempFile(iconImageFile.getInputStream(), pathToSaveIcons+iconFileName);
	    	   */
	    		ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopyIcon,Constants.APP_ICON_ANDROID3_KEY,true);
	    		ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopyIcon,Constants.APP_ICON_ANDROID4_KEY,true);
	    		ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopyIcon,Constants.APP_ICON_ANDROID6_KEY,true);
	    		ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopyIcon ,Constants.APP_ICON_IPAD_KEY,true);
	    		ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopyIcon,Constants.APP_ICON_IPHONE_KEY,true);
	    		ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopyIcon,Constants.APP_ICON_IPHONE4_KEY,true);
	    	     
	    	}
	    	if((tempCopyLoader!=null) && !"".equals(tempCopyLoader)){
	    		 /*for app icon*/
	   	     /*String tempFile=ImgMagicCmdExcecutor.saveTempFile(loaderImageFile.getInputStream(), rootPath+finalLoaderImagePath);
	   			*/	
	   				
	   				//ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopyLoader,Constants.APP_ICON_ANDROID3_KEY,true);
	   				ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopyLoader,Constants.APP_LOADER_ANDROID3_KEY,true);
	   				ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopyLoader,Constants.APP_LOADER_ANDROID4_KEY,true);
	   				ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopyLoader,Constants.APP_LOADER_ANDROID6_KEY,true);
	   				ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopyLoader,Constants.APP_LOADER_IPAD_KEY,true);
	   				ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopyLoader,Constants.APP_LOADER_IPHONE_KEY,true);
	   				ImgMagicCmdExcecutor.resizeAndSaveFile(rootPath,tempCopyLoader,Constants.APP_LOADER_IPHONE4_KEY,true);
	   	     
	   	     
	    	}
	    	
	    }
	    
	    
			 }
	   
	    response.getWriter().append("Successfully croped");
		 }
		 catch (DataAccessException de) {
			 de.printStackTrace();
			// TODO: handle exception
		}
		 catch (Exception e) {
			 e.printStackTrace();
			 try{
			 response.getWriter().append(e.getStackTrace().toString());}
			 catch (Exception e1) {
				 e1.printStackTrace();
			}
		}
		
		 finally{
			 
		 }
	    
	    
	    
		return null;
	}
	
}
