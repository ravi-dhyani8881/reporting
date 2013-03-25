package com.mobicart.web.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mobicart.bo.ProductBO;
import com.mobicart.bo.ProductResponse;
import com.mobicart.dto.NewsDto;
import com.mobicart.model.Category;
import com.mobicart.model.Department;
import com.mobicart.model.GalleryImage;
import com.mobicart.model.Product;
import com.mobicart.model.ProductImage;
import com.mobicart.model.Shipping;
import com.mobicart.model.Store;
import com.mobicart.model.Tax;
import com.mobicart.model.TaxShipping;
import com.mobicart.model.User;
import com.mobicart.model.api.ProductImageApi;
import com.mobicart.service.AppService;
import com.mobicart.service.StoreService;
import com.mobicart.service.user.UserService;
import com.mobicart.util.Pager;
import com.mobicart.util.ResourceProperties;

/**
 * This controller class handles web service calls related to Store entity 
 * @author jasdeep.singh
 *
 */
@Controller
public class StoreWebServiceController {
	
	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	AppService appService;
	
	@Autowired
	StoreService storeService;


	@Autowired
	UserService userService;
	
	
	@Autowired
	ProductBO productBO;
	
	private static final String STORE_SETTINGS = "store";
	private static final String STORE_DEPARTMENTS = "departments";
	private static final String DEPARTMENT_CATEGORIES = "categories";
	private static final String PRODUCTS = "products";
	private static final String PRODUCTS_RESPONSE = "productsResponse";
	private static final String HOME_GALLERY_IMAGES = "gallery-images";
	private static final String HOME_GALLERY_IMAGES_WEBAPP = "galleryImages";
	
	
	/**
	 * Service to fetch Store Details
	 * 
	 * @param storeId
	 * @return Store object in JSON
	 */
	@RequestMapping(value = "/store/{storeId}/settings")
	public ModelAndView getStoreSettings(@PathVariable Long storeId) {
		Store store = null;
		try {
			store = storeService.findStoreById(storeId);
		} catch (Exception e) {
			logger.error("error in fetching store settings ",e);
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject(STORE_SETTINGS, store);
		return mav;
	}

	/**
	 * Service to fetch departments in a store
	 * 
	 * @param storeId
	 * @return List of 'Department' objects in JSON
	 */

	@RequestMapping(value = "/store/{storeId}/departments")
	public ModelAndView getStoreDepartments(@PathVariable Long storeId) {
		
		List<Department> departments=null;
		try{
		departments = storeService
				.findActiveDepartmentsByStoreWithoutSubdepartments(storeId);
		}catch (Exception e) {
			logger.error("no department found",e);
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject(STORE_DEPARTMENTS, departments);
		return mav;
	}

	/**
	 * Service to sub-departments in a store
	 * 
	 * @param storeId
	 * @param departmentId
	 * @return List of 'Category' objects in JSON
	 */
	@RequestMapping(value = "/store/{storeId}/department/{departmentId}/categories")
	public ModelAndView getStoreSubDepartments(@PathVariable Long storeId, @PathVariable Long departmentId) {
		List<Category> categories = null;
		try{
			//get store			
			Store store = storeService.findStoreById(storeId);		
			//get owner of store
			User user= userService.find(store.getUserId());		 
			Department department=new Department();
			department.setUserId(user.getId());
			department.setId(departmentId);
			//storeService.findActiveCategoriesByDepartment(departmentId);
			categories =storeService.findActiveCategoriesByDepartment(department);
		}catch (Exception e) {
			logger.error("no category found",e);
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject(DEPARTMENT_CATEGORIES, categories);
		return mav;
	}

	/**
	 * List of products in a sub departments
	 * 
	 * @param categoryId
	 * @return List of 'Product' objects in JSON
	 */
	@RequestMapping(value = "/store/{storeId}/department/{departmentId}/category/{categoryId}/products")
	public ModelAndView getSubDepartmentProducts(@PathVariable Long categoryId,
			@PathVariable Long storeId,
			@RequestParam(value="c",required=false) Long countryId,
			@RequestParam(value="s",required=false) Long stateId,
			HttpServletRequest request) {
		
		
		Store store = null;
		User user = null;
		ModelAndView mav = new ModelAndView();
		
		try{
			
		//get store			
		store = storeService.findStoreById(storeId);
		
		//get owner of store
		user= userService.find(store.getUserId());
		
		//get products
		//List<Product> products = storeService.findActiveProductsByCategory(categoryId);
		    List<Product> products = null;
			Product product=new Product();
			product.setUserId(store.getUserId());
			product.setDepartmentId(categoryId);
			product.setMaxRowNum(Long.parseLong(user.getStoreProductLimit().toString()));
			products = storeService.findProductsByDepartmentAndMaxRowNum(product);
		//calculate tax for product
			products=productBO.calculateTax(products, store, countryId, stateId, user);
		
		mav.addObject(PRODUCTS, products);
		
		}catch(Exception e){
			logger.error("getCategoryProducts",e);
		}
		
		return mav;
	}

	
	
	/**
	 * List of products in a sub departments Paginated
	 * 
	 * @param categoryId
	 * @return List of 'Product' objects in JSON
	 */
	@RequestMapping(value = "/store/{storeId}/department/{departmentId}/category/{categoryId}/products-paginated")
	public ModelAndView getSubDepartmentProductsPaginated(@PathVariable Long categoryId,
			@PathVariable Long storeId,
			@RequestParam(value="c",required=false) Long countryId,
			@RequestParam(value="s",required=false) Long stateId,
			@RequestParam(value="start",required=false) Integer start,
			@RequestParam(value="maxLimit",required=false) Integer maxLimit,
			HttpServletRequest request) {
		
		
		Store store = null;
		User user = null;
		ModelAndView mav = new ModelAndView();
		
		try{
			
		//get store			
		store = storeService.findStoreById(storeId);
		
		//get owner of store
		user= userService.find(store.getUserId());
		
		ProductResponse productResponse=new ProductResponse();
		
		if(start == null || "".equals(start) || start<=0){
			start=0;
		}
		if(maxLimit == null || "".equals(maxLimit) || maxLimit<=0){
			maxLimit=50;
		}
		//get store			
		store = storeService.findStoreById(storeId);
		
		//get owner of store
		user= userService.find(store.getUserId());
		
		Pager pager=new Pager();
		
		//get product response
		productResponse  = storeService.findActiveProductsByCategory(categoryId,pager);
		
		//calculate tax for product
		
		if(productResponse!=null ){
			List<Product> products=productBO.calculateTax(productResponse.getProductList() , store, countryId, stateId, user);
			productResponse.setProductList(products);
		
		}
		mav.addObject(PRODUCTS_RESPONSE, productResponse);
		
		}catch(Exception e){
			logger.error("getCategoryProducts",e);
		}
		
		return mav;
	}

	
	
	/**
	 * List of products directly under Department
	 * 
	 * @param categoryId
	 * @return List of 'Product' objects in JSON
	 */
	@RequestMapping(value = "/store/{storeId}/department/{departmentId}/products")
	public ModelAndView getDepartmentProducts(@PathVariable Long departmentId,
			@PathVariable Long storeId,
			@RequestParam(value="c",required=false) Long countryId,
			@RequestParam(value="s",required=false) Long stateId,
			HttpServletRequest request		
	) {
		

		Store store = null;
		User user = null;
		ModelAndView mav = new ModelAndView();
		
		try{
			
		//get store			
		store = storeService.findStoreById(storeId);
		
		//get owner of store
		user= userService.find(store.getUserId());
		
		//get products
		List<Product> products =null; //storeService.findActiveProductsByDepartment(departmentId);
		
		 Product product=new Product();
		 product.setDepartmentId(departmentId);
		 product.setUserId(store.getUserId());
		 product.setMaxRowNum(Long.parseLong(user.getStoreProductLimit().toString()));
		products = storeService.findProductsByDepartmentAndMaxRowNum(product);
		
		//calculate tax on products
		products=productBO.calculateTax(products, store, countryId, stateId	, user);
		
		mav.addObject(PRODUCTS, products);
		
		}catch(Exception e){
			logger.error("getDepartmentProducts",e);
		}

		return mav;
	}


	
	
	/**
	 * List of products directly under Department Paginated
	 * 
	 * @param categoryId
	 * @return List of 'Product' objects in JSON
	 */
	@RequestMapping(value = "/store/{storeId}/department/{departmentId}/products-paginated")
	public ModelAndView getDepartmentProductsPaginated(@PathVariable Long departmentId,
			@PathVariable Long storeId,
			@RequestParam(value="c",required=false) Long countryId,
			@RequestParam(value="s",required=false) Long stateId,
			@RequestParam(value="start",required=false) Integer start,
			@RequestParam(value="maxLimit",required=false) Integer maxLimit,
			HttpServletRequest request		
	) {
		

		Store store = null;
		User user = null;
		ModelAndView mav = new ModelAndView();
		
		try{
			
		//get store			
		store = storeService.findStoreById(storeId);
		
		//get owner of store
		user= userService.find(store.getUserId());
		
		ProductResponse productResponse=new ProductResponse();
		
		if(start == null || "".equals(start) || start<=0){
			start=0;
		}
		if(maxLimit == null || "".equals(maxLimit) || maxLimit<=0){
			maxLimit=50;
		}
		//get store			
		store = storeService.findStoreById(storeId);
		
		//get owner of store
		user= userService.find(store.getUserId());
		
		Pager pager=new Pager();
		
		//get product response
		productResponse  = storeService.findActiveProductsByDepartment(departmentId, pager);
		
		//calculate tax for product
		
		if(productResponse!=null ){
			List<Product> products=productBO.calculateTax(productResponse.getProductList() , store, countryId, stateId, user);
			productResponse.setProductList(products);
		
		}
		mav.addObject(PRODUCTS_RESPONSE, productResponse);
		
		
		}catch(Exception e){
			logger.error("getDepartmentProducts",e);
		}

		return mav;
	}
	
	
	
	
	/**
	 * List of gallery images
	 * 
	 * @param storeId
	 */
	@RequestMapping(value = "/store/{storeId}/gallery-images")
	public ModelAndView getHomeGalleriesByStore(@PathVariable Long storeId) {
		List<GalleryImage> galleryImages = storeService
				.findGalleryImagesByStore(storeId);
		ModelAndView mav = new ModelAndView();
		mav.addObject(HOME_GALLERY_IMAGES, galleryImages);
		return mav;
	}
	
	
	
	/**
	 * List of gallery images
	 * 
	 * @param storeId
	 */
	@RequestMapping(value = "/store/{storeId}/gallery-images-webapp")
	public ModelAndView getHomeGalleriesByStoreWebApp(@PathVariable Long storeId) {
		List<GalleryImage> galleryImages = storeService
				.findGalleryImagesByStore(storeId);
		ModelAndView mav = new ModelAndView();
		mav.addObject(HOME_GALLERY_IMAGES_WEBAPP, galleryImages);
		return mav;
	}
	
	
	/**
	 * Get tax for a country and state
	 * 
	 * @return  'Tax' object
	 */
	@RequestMapping(value = "/store/{storeId}/country/{countryId}/state/{stateId}/tax")
	public @ResponseBody
	Tax findTaxByCountryStateStore(@PathVariable Long storeId,
			@PathVariable Long countryId, @PathVariable Long stateId) {
		return storeService.findTaxByStoreCountryState(storeId, countryId,
				stateId);
	}

	/**
	 * Get Shipping details for a country and state
	 * 
	 * @return 'Shipping object
	 */
	@RequestMapping(value = "/store/{storeId}/country/{countryId}/state/{stateId}/shipping")
	public @ResponseBody
	Shipping findShippingByCountryStateStore(@PathVariable Long storeId,
			@PathVariable Long countryId, @PathVariable Long stateId) {
		return storeService.findShippingByStoreCountryState(storeId, countryId,
				stateId);
	}

	/**
	 * Get Tax and Shipping details for a country and state
	 * 
	 * @return 'TaxShipping' object
	 */
	@RequestMapping(value = "/store/{storeId}/country/{countryId}/state/{stateId}/tax-shipping")
	public @ResponseBody
	TaxShipping findTaxShippingByCountryStateStore(@PathVariable Long storeId,
			@PathVariable Long countryId, @PathVariable Long stateId) {
		
		TaxShipping taxShipping = new TaxShipping();
		taxShipping.setShipping(storeService.findShippingByStoreCountryState(
				storeId, countryId, stateId));
		taxShipping.setTax(storeService.findTaxByStoreCountryState(storeId,
				countryId, stateId));
		return taxShipping;
	}
	
	/**
	 * List of all the products in a store
	 * 
	 * @param storesId
	 * @return List of 'Product' objects in JSON 
	 */
	@RequestMapping(value = "/store/{storeId}/products")
	public ModelAndView getStoreProducts(
			@PathVariable Long storeId,
			@RequestParam(value="c",required=false) Long countryId,
			@RequestParam(value="start",required=false) Integer start,
			@RequestParam(value="maxLimit",required=false) Integer maxLimit,
			@RequestParam(value="s",required=false) Long stateId,
			HttpServletRequest request) {
		
		Store store = null;
		User user = null;
		ModelAndView mav = new ModelAndView();
		try{
			ProductResponse productResponse=new ProductResponse();
			
		if(start == null || "".equals(start) || start<=0){
			start=0;
		}
		if(maxLimit == null || "".equals(maxLimit) || maxLimit<=0){
			maxLimit=50;
		}
		//get store			
		store = storeService.findStoreById(storeId);
		
		//get owner of store
		user= userService.find(store.getUserId());
		
		Pager pager=new Pager();
		pager.setLowerLimit(start);
		pager.setDefaultLimit(maxLimit);
		pager.setUpperLimit(maxLimit);
		pager.setSortBy("s_name");
		pager.setSortOrder("DESC");
		pager.setKeyword("");
		//get result Map
		
		//get products
		@SuppressWarnings("unchecked")
		Map<String,Object> resultMap = null; 		
		List<Product> productList=null;
		//Integer totalCount=0;
		int productsCounts= storeService.findProductCountByStore(storeId);
		if(productsCounts>user.getStoreProductLimit()){
			Pager page=new Pager();
			page.setPage(0);
			page.setResults(user.getStoreProductLimit()); 
			//productList = storeService.findAPIProductsByStore(storeId,page);
			resultMap = storeService.findStoreProductsWithPagination(storeId,pager);		 		
		}else{
			//productList = storeService.findAPIProductsByStore(storeId,pager);
			resultMap = storeService.findStoreProductsWithPagination(storeId,pager);	
		}
		
		
		productList =(List<Product>) resultMap.get("productList");
		Integer totalCount= (Integer)resultMap.get("count");
		
		
		productList = productBO.calculateTax(productList, store, countryId,
				stateId, user);
		productResponse.setMaxLimit(maxLimit);
		productResponse.setStart(start);
		productResponse.setTotalCount(totalCount);
		productResponse.setProductList(productList);
		
	
		mav.addObject(PRODUCTS_RESPONSE, productResponse);
		
		}catch(Exception e){
			logger.error("getCategoryProducts",e);
		}
		
		return mav;
	}
	
	/**
	 * Service to fetch list of products in a sub departments
	 * 
	 * @param categoryId
	 * @return List of 'Product' objects in JSON
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/app/{storeId}/search/{keyword}/{page}/{results}/{sortOrder}/{sortBy}/products")
	public ModelAndView getProductsBySearch(@PathVariable Long storeId,
			@PathVariable String keyword,
			@PathVariable int page,
			@PathVariable int results,
			@PathVariable String sortOrder,
			@PathVariable String sortBy,
			@RequestParam(value="c",required=false) Long countryId,
			@RequestParam(value="s",required=false) Long stateId,
			HttpServletRequest request) {
		
		
		Store store = null;
		User user = null;
		ModelAndView mav = new ModelAndView();
		
		try{
		List<Product> products=null;
		//get app
		
		Pager pager=new Pager();
		ProductResponse productResponse=new ProductResponse();
		
		if(storeId!=null){
			
		//get store			
		store = storeService.findStoreById(storeId);

		//get owner of store
		user= userService.find(store.getUserId());
		appService.findAppByUser(user);
		
		if(!"searchallrecords".equals(keyword)){
		pager.setKeyword(keyword);
		}
		pager.setPage(page);
		pager.setResults(results);
		pager.setSortBy(sortBy);
		pager.setSortOrder(sortOrder);
		
		//get products
		Map<String,Object> resultMap = storeService.findStoreProductsWithPagination(storeId, pager);
		
		products =(List<Product>) resultMap.get("productList");
		Integer totalCount= (Integer)resultMap.get("count");
		
				// calculate tax on products
				products = productBO.calculateTax(products, store, countryId,
						stateId, user);
				productResponse.setMaxLimit(pager.getUpperLimit());
				productResponse.setStart(pager.getLowerLimit());
				productResponse.setTotalCount(totalCount);
				productResponse.setProductList(products);
			}
	
		mav.addObject(PRODUCTS_RESPONSE, productResponse);
		
       }catch(Exception e){
    	   logger.error("getProductsBySearch",e);
		}
		
		return mav;
	}

	
	
	
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.HEAD}, value = "/store/{storeId}/news/{keyword}/search")
	public ModelAndView searchNews(@PathVariable String keyword,@PathVariable Long storeId, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		List<NewsDto> newsList =storeService.findNewsItemsByStore(storeId,keyword);
		mav.addObject("newsList", newsList);
		return mav;
	}
	
	
	
	
	
}

