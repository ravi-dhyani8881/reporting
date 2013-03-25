package com.mobicart.web.external;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jsx3.chart.CartesianChart;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mobicart.bo.ApiBO;
import com.mobicart.bo.Error;
import com.mobicart.bo.Message;
import com.mobicart.model.Category;
import com.mobicart.model.Department;
import com.mobicart.model.Product;
import com.mobicart.model.Store;
import com.mobicart.model.User;
import com.mobicart.model.api.CategoryApi;
import com.mobicart.model.api.DepartmentApi;
import com.mobicart.model.api.MainCategoryApi;
import com.mobicart.model.api.MainDepartmentApi;
import com.mobicart.service.StoreService;
import com.mobicart.util.CacheManager;
import com.mobicart.util.exception.CategoriesExistInDepartmentException;
import com.mobicart.util.exception.DuplicateDepartmentException;
import com.mobicart.util.exception.ProductsExistInDepartmentException;

@Controller
@RequestMapping("/api/**")
public class DepartmentApiWebServiceController {
	
	
	@Autowired
    StoreService storeService;
	@Autowired
    ApiBO apiBO;
	
	
	private static final String ERROR = "error";
	private static final String VALID = "valid";
	private static final String USER = "user";
	private static final String MESSAGE = "message";
	private static final String STORE_DEPARTMENTS = "DepartmentList";
	private static final String DEPARTMENT_CATEGORIES = "CategoryList";
	
	
	
	/**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory
            .getLogger(DepartmentApiWebServiceController.class);
	
    
    
    
    
    

    /**
     * Service to fetch departments under store
     *
     * @param user_name
     * @param api_key
     * @param store_id
     * @return List of Departments in Store in JSON or XML
     */

    @RequestMapping(value = "/store-departments", method = RequestMethod.GET)
    public ModelAndView getDeparmentsForStore(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "store_id", required = false) String storeId) {

        //List<DepartmentApi> departmentApiList = new ArrayList<DepartmentApi>();
        List<DepartmentApi> departments = null;
        Store store = null;
        ModelAndView mav = new ModelAndView();
        Error error = new Error();
        Long streId;
        User user = null;
        Integer totalCount = null;

        Map<String, Object> key_response = apiBO.validateApiKey(apiKey, userName);

        if (!(Boolean) key_response.get(VALID)) {
            error = (Error) key_response.get(ERROR);// get error
            mav.addObject(ERROR, error);
            return mav;
        }
        
        boolean hits = true;
        try{
        	hits = apiBO.validateGeneralApiHits(apiKey);
        }catch(Exception e){
        	mav.addObject(ERROR, error.generateError(1004));
        	return mav;
        }
        
        if(hits == false){
        	mav.addObject(ERROR, error.generateError(1005));
        	return mav;
        }
        
        List<String> missingParamList=new ArrayList<String>();
        if(StringUtils.isEmpty(storeId) ){
        	missingParamList.add("store_id");
        }
        if(storeId.length() > 10 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        try {
        	streId = Long.parseLong(storeId);
    		} catch (NumberFormatException nfe) {
    			mav.addObject(ERROR, error.generateError(2001));
    			return mav;
    		}

    	try {
			// get user
            user = (User) key_response.get(USER);
            store = storeService.findStoreById(streId);
            if(store == null){
				mav.addObject(ERROR, error.generateError(2003));
				return mav;
            }
			if(store.getUserId().longValue() != user.getId().longValue()){
				mav.addObject(ERROR, error.generateError(2004));
				return mav;
        	}

			departments = storeService.findDepartmentsByStoreForApi(streId,null);
			
			
			
			
        	if (departments == null || departments.size() <= 0) {
        		mav.addObject(ERROR, error.generateError(6001));
        		return mav;
            	}

            } catch (Exception e) {
            	logger.error("Error", e);
                error = error.generateError(1004);
                mav.addObject(ERROR, error);
                return mav;
            }

            MainDepartmentApi mainDep = new MainDepartmentApi();
            mainDep.setStoreId(store.getId());
            mainDep.setSize(departments.size());
            mainDep.setDepartments(departments);
            mav.addObject(STORE_DEPARTMENTS, mainDep);
            return mav;

    }

    
    
    /**
     * Service to add department under store
     *
     * @param user_name
     * @param api_key
     * @param store_id
     * @param department_name
     * @param department_status
     * @add department under a given store
     */
    @RequestMapping(value = "/add-department", method = RequestMethod.POST)
    public ModelAndView addDepartmentUnderStore(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "store_id", required = false) String storeId,
            @RequestParam(value = "department_name", required = false) String departmentName,
            @RequestParam(value = "department_status", required = false) String departmentStatus) {
       
    	Department department = new Department();
        ModelAndView mav = new ModelAndView();
        Message message = new Message();
        Error error = new Error();
        User user = null;
        Long streId = null;

        Map<String, Object> key_response = apiBO.validateApiKey(apiKey, userName);

        if (!(Boolean) key_response.get(VALID)) {
            error = (Error) key_response.get(ERROR);// get error
            mav.addObject(ERROR, error);
            return mav;
        }
        
        boolean hits = true;
        try{
        	hits = apiBO.validateGeneralApiHits(apiKey);
        }catch(Exception e){
        	mav.addObject(ERROR, error.generateError(1004));
        	return mav;
        }
        
        if(hits == false){
        	mav.addObject(ERROR, error.generateError(1005));
        	return mav;
        }
        
        List<String> missingParamList=new ArrayList<String>();
        
        if(StringUtils.isEmpty(storeId) ){
        	missingParamList.add("store_id");
        }
        if(storeId.length() > 10 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(StringUtils.isEmpty(departmentName) ){
        	missingParamList.add("department_name");
        }
        if(departmentName.length() > 50 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
  
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        if(StringUtils.isEmpty(departmentStatus) )
        	departmentStatus = "active";
        
        try {
        	streId = Long.parseLong(storeId);
    		} catch (NumberFormatException nfe) {
    			mav.addObject(ERROR, error.generateError(2001));
    			return mav;
    		}

		try {
			// get user
    		user = (User) key_response.get(USER);
    		
    	
            Store store = null;
            
            // fetch product 
            store = storeService.findStoreById(streId);

        	if (store == null) {
        		mav.addObject(ERROR, error.generateError(2003));
        		return mav;
                }
        	
        	if(store.getUserId().longValue() != user.getId().longValue()){
        		mav.addObject(ERROR, error.generateError(2004));
        		return mav;
        	}
        	

        	// add department 
        	department.setStoreId(streId);
        	department.setUserId(user.getId());
        	department.setParentDepartmentId(0L);
        	department.setsName(departmentName);
        	department.setsStatus(departmentStatus);
        	
        	
        	Long departmentId = null;
        	try{
        		departmentId = storeService.createDepartment(department);
        		
        		/*clear cache*/
        		CacheManager.setToCache(apiKey,null);
        		
        		message.setId(departmentId.toString());
            	message.setMessage("Department added successfully");
            	mav.addObject(MESSAGE, message);
            	
        	}catch(Exception e){
                mav.addObject(ERROR, error.generateError(6010));
                return mav;
        	}
            
			} catch (Exception e) {
            	error = error.generateError(1004);
                mav.addObject(ERROR, error);
                return mav;
            }

            return mav;
        
    }


    
    
    /**
     * Service to update department
     *
     * @param user_name
     * @param api_key
     * @param department_id
     * @param department_name
     * @param department_status
     * @update department details and return updated department
     */

    @RequestMapping(value = "/update-department", method = RequestMethod.POST)
    public ModelAndView updateDepartmentUnderStore(
            @RequestParam(value = "user_name", required = true) String userName,
            @RequestParam(value = "api_key", required = true) String apiKey,
            @RequestParam(value = "department_id", required = true) String departmentId,
            @RequestParam(value = "department_name", required = true) String departmentName,
            @RequestParam(value = "department_status", required = false) String departmentStatus) {

        Department department = null;
        ModelAndView mav = new ModelAndView();
        Error error = new Error();
        Message message = new Message();
        User user = null;
        Long depttId;

        Map<String, Object> key_response = apiBO.validateApiKey(apiKey, userName);

        if (!(Boolean) key_response.get(VALID)) {
            error = (Error) key_response.get(ERROR);// get error
            mav.addObject(ERROR, error);
            return mav;
        }
        
        boolean hits = true;
        try{
        	hits = apiBO.validateGeneralApiHits(apiKey);
        }catch(Exception e){
        	mav.addObject(ERROR, error.generateError(1004));
        	return mav;
        }
        
        if(hits == false){
        	mav.addObject(ERROR, error.generateError(1005));
        	return mav;
        }
        
        List<String> missingParamList=new ArrayList<String>();
        
        if(StringUtils.isEmpty(departmentId) ){
        	missingParamList.add("department_id");
        }
        if(departmentId.length() > 10 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(StringUtils.isEmpty(departmentName) ){
        	missingParamList.add("department_name");
        }
        if(departmentName.length() > 50 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
  
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        if(StringUtils.isEmpty(departmentStatus) )
        	departmentStatus = "active";
        
        try {
        	depttId = Long.parseLong(departmentId);
    		} catch (NumberFormatException nfe) {
    			mav.addObject(ERROR, error.generateError(6002));
    			return mav;
    		}

		try {
			// get user
    		user = (User) key_response.get(USER);
    		
            // fetch product 
    		department = storeService.findDepartment(depttId);

        	if (department == null) {
        		mav.addObject(ERROR, error.generateError(6003));
        		return mav;
                }
        	
        	if(department.getUserId() != user.getId().longValue()){
        		mav.addObject(ERROR, error.generateError(6007));
        		return mav;
        	}

        	// update department 
        	department.setsName(departmentName);
        	department.setsStatus(departmentStatus);
        	
        	try{
        		department = storeService.updateDepartment(department);
        		message.setId(departmentId.toString());
            	message.setMessage("Department updated successfully.");
            	mav.addObject(MESSAGE, message);
        	}catch(Exception e){
                mav.addObject(ERROR, error.generateError(6011));
                return mav;
        	}
            
            } catch (Exception e) {
            	error = error.generateError(1004);
                mav.addObject(ERROR, error);
                return mav;
            }

            return mav;
    }


    /**
     * Service to delete department
     *
     * @param user_name
     * @param api_key
     * @param departmentid
     * @return Status message regarding deletion of department
     */

    @RequestMapping(value = "delete-department", method = RequestMethod.DELETE)
    public ModelAndView deleteDepartment(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "department_id", required = false) String departmentId) {

        Department department = null;
        ModelAndView mav = new ModelAndView();
        Error error = new Error();
        Message message = new Message();
        Long depttId;
        User user = null;

        Map<String, Object> key_response = apiBO.validateApiKey(apiKey, userName);

        if (!(Boolean) key_response.get(VALID)) {
            error = (Error) key_response.get(ERROR);// get error
            mav.addObject(ERROR, error);
            return mav;
        }
        
        boolean hits = true;
        try{
        	hits = apiBO.validateGeneralApiHits(apiKey);
        }catch(Exception e){
        	mav.addObject(ERROR, error.generateError(1004));
        	return mav;
        }
        
        if(hits == false){
        	mav.addObject(ERROR, error.generateError(1005));
        	return mav;
        }
        
        List<String> missingParamList=new ArrayList<String>();
        if(StringUtils.isEmpty(departmentId) ){
        	missingParamList.add("department_id");
        }
        if(departmentId.length() > 10 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        try {
        	depttId = Long.parseLong(departmentId);
    		} catch (NumberFormatException nfe) {
    			mav.addObject(ERROR, error.generateError(6002));
    			return mav;
    		}

        	try {
    			// get user
                user = (User) key_response.get(USER);
                department = storeService.findDepartment(depttId);
                
                if(department == null){
    				mav.addObject(ERROR, error.generateError(6003));
    				return mav;
                }
                
                if(department.getParentDepartmentId() != 0){
                	mav.addObject(ERROR, error.generateError(6013));
    				return mav;
                }
                
    			if(department.getUserId() != user.getId().longValue()){
    				mav.addObject(ERROR, error.generateError(6007));
    				return mav;
            		}
    			List<CategoryApi> categoriesApiList = storeService.findCategoriesByDepartmentForApi(depttId);
    			if(categoriesApiList != null && categoriesApiList.size() > 0){
    				mav.addObject(ERROR, error.generateError(6008));
    				return mav;
    			}
                
                List<Product> products = storeService.findProductsByDepartment(depttId);
                System.out.println(products.size());
                if(products != null && products.size() > 0){
    				mav.addObject(ERROR, error.generateError(6009));
    				return mav;
            		
                }
                
                } catch (Exception e) {
                	logger.error("Error in finding", e);
                    error = error.generateError(1004);
                    mav.addObject(ERROR, error);
                    return mav;
                }
                
                try{
                	storeService.deleteDepartment(department);
                	/*clear cache*/
            		CacheManager.setToCache(apiKey,null);
                }catch(CategoriesExistInDepartmentException ce){
                	logger.error("Error in finding", ce);
                	error = error.generateError(6008);
                    mav.addObject(ERROR, error);
                    return mav;
                }catch(ProductsExistInDepartmentException pe){
                	logger.error("Error in finding", pe);
                	error = error.generateError(6009);
                    mav.addObject(ERROR, error);
                    return mav;
                }catch(Exception e){
                	logger.error("Error in finding", e);
                	error = error.generateError(1004);
                    mav.addObject(ERROR, error);
                    return mav;
                }
                message.setMessage("Department deleted successfully.");
                mav.addObject(message);
                return mav;
    }


    
    
    
    /**
     * Service to fetch categories under Department
     *
     * @param user_name
     * @param api_key
     * @param department_id
     * @return List of Sub-Departments under Department in JSON or XML
     */

    @RequestMapping(value = "/sub-departments", method = RequestMethod.GET)
    public ModelAndView getSubDeparmentsUnderDepartment(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "department_id", required = false) String departmentId) {

        Department department = null;
        List<Category> categories = null;
        List<Department> deparments = null;
        List<CategoryApi> categoriesApiList = new ArrayList<CategoryApi>();
        ModelAndView mav = new ModelAndView();
        Error error = new Error();
        Long depttId = null;
        User user = null;

        Map<String, Object> key_response = apiBO.validateApiKey(apiKey, userName);

        if (!(Boolean) key_response.get(VALID)) {
            error = (Error) key_response.get(ERROR);// get error
            mav.addObject(ERROR, error);
            return mav;
        }
        
        boolean hits = true;
        try{
        	hits = apiBO.validateGeneralApiHits(apiKey);
        }catch(Exception e){
        	mav.addObject(ERROR, error.generateError(1004));
        	return mav;
        }
        
        if(hits == false){
        	mav.addObject(ERROR, error.generateError(1005));
        	return mav;
        }
        
        List<String> missingParamList=new ArrayList<String>();
        if(StringUtils.isEmpty(departmentId) ){
        	missingParamList.add("department_id");
        }
        if(departmentId.length() > 10 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        try {
        	depttId = Long.parseLong(departmentId);
    		} catch (NumberFormatException nfe) {
    			mav.addObject(ERROR, error.generateError(6002));
    			return mav;
    		}

    	try {
			// get user
            user = (User) key_response.get(USER);
           department = storeService.findDepartment(depttId);

           if(department == null){
				mav.addObject(ERROR, error.generateError(6003));
				return mav;
            }
			if(department.getUserId() != user.getId().longValue()){
				mav.addObject(ERROR, error.generateError(6007));
				return mav;
        	}

			categoriesApiList = storeService.findCategoriesByDepartmentForApi(depttId);
			
        	if (categoriesApiList == null || categoriesApiList.size() <= 0) {
        		mav.addObject(ERROR, error.generateError(6004));
        		return mav;
            	}

            } catch (Exception e) {
            	logger.error("Error", e);
                error = error.generateError(1004);
                mav.addObject(ERROR, error);
                return mav;
            }
            MainCategoryApi mainCategory = new MainCategoryApi();
            /*for (Category category : categories) {
                CategoryApi categoryApi = new CategoryApi();
                categoryApi.setSubDepartmentId(category.getId());
                categoryApi.setSubDepartmentName(category.getsName());
                categoryApi.setSubDepartmentStatus(category.getsStatus());
                categoryApi.setProductCount(category.getiProductCount());
                categoryApi.setParentSubDepartmentId(category.getParentCategoryId());
                //categoryApi.setChildSubDepartments(category.getCategories());
                categoriesApiList.add(categoryApi);

            }*/
            mainCategory.setSize(categoriesApiList.size());
            mainCategory.setDepartmentId(department.getId());
            mainCategory.setStoreId(department.getStoreId());
            mainCategory.setSubDepartments(categoriesApiList);
            mav.addObject(DEPARTMENT_CATEGORIES, mainCategory);
            return mav;
    }


    /**
     * Service to add categories under department
     *
     * @param user_name
     * @param api_key
     * @param department_id
     * @param category_name
     * @param category_status
     * @add category under a given department
     */
    @RequestMapping(value = "/add-sub-department", method = RequestMethod.POST)
    public ModelAndView addSubDepartment(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "department_id", required = false) String departmentId,
            @RequestParam(value = "category_name", required = false) String categoryName,
            @RequestParam(value = "category_status", required = false) String categoryStatus) {
        
    	Category category = new Category();
        Department department = null;
    	ModelAndView mav = new ModelAndView();
        Message message = new Message();
        Error error = new Error();
        User user = null;
        Long depttId;

        Map<String, Object> key_response = apiBO.validateApiKey(apiKey, userName);

        if (!(Boolean) key_response.get(VALID)) {
            error = (Error) key_response.get(ERROR);// get error
            mav.addObject(ERROR, error);
            return mav;
        }
        
        boolean hits = true;
        try{
        	hits = apiBO.validateGeneralApiHits(apiKey);
        }catch(Exception e){
        	mav.addObject(ERROR, error.generateError(1004));
        	return mav;
        }
        
        if(hits == false){
        	mav.addObject(ERROR, error.generateError(1005));
        	return mav;
        }
        
        List<String> missingParamList=new ArrayList<String>();
        
        if(StringUtils.isEmpty(departmentId) ){
        	missingParamList.add("department_id");
        }
        if(departmentId.length() > 10 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(StringUtils.isEmpty(categoryName) ){
        	missingParamList.add("category_name");
        }
        if(categoryName.length() > 50 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
  
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        if(StringUtils.isEmpty(categoryStatus) )
        	categoryStatus = "true";
        
        try {
        	depttId = Long.parseLong(departmentId);
    		} catch (NumberFormatException nfe) {
    			mav.addObject(ERROR, error.generateError(6002));
    			return mav;
    		}

		try {
			// get user
    		user = (User) key_response.get(USER);

            // fetch department 
    		department = storeService.findDepartment(depttId);

        	if (department == null) {
        		mav.addObject(ERROR, error.generateError(6003));
        		return mav;
                }
        	
        	if(department.getUserId() != user.getId().longValue()){
        		mav.addObject(ERROR, error.generateError(6007));
        		return mav;
        	}
        	
        	List<Product> products = storeService.findProductsByDepartment(depttId);
        	if(products != null && products.size() > 0){
				mav.addObject(ERROR, error.generateError(6005));
				return mav;
        		
            }

        	// add sub-department 
        	category.setDepartmentId(depttId);
        	category.setsName(categoryName);
        	if (categoryStatus.equalsIgnoreCase("true")) {
                category.setsStatus("active");
            } else {
                category.setsStatus("inactive");
            }

        	Long categoryId = null;
        	try{
        		categoryId = storeService.createCategory(category);
        		message.setId(categoryId.toString());
            	message.setMessage("Sub-department added successfully.");
            	mav.addObject(MESSAGE, message);
            	
        	}catch(Exception e){
                mav.addObject(ERROR, error.generateError(7008));
                return mav;
        	}
            
			} catch (Exception e) {
            	error = error.generateError(1004);
                mav.addObject(ERROR, error);
                return mav;
            }

            return mav;
        
        
    }


    /**
     * Service to add categories under department or a parent category
     *
     * @param user_name
     * @param api_key
     * @param department_id
     * @param parent_category_id
     * @param category_name
     * @param category_status
     * @add category under a given department
     */
    @RequestMapping(value = "/add-sub-department-nested", method = RequestMethod.POST)
    public ModelAndView addSubDepartmentNested(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "department_id", required = false) String departmentId,
            @RequestParam(value = "category_name", required = false) String categoryName,
            @RequestParam(value = "category_status", required = false) String categoryStatus) {
        
    	Department department = null;
        ModelAndView mav = new ModelAndView();
        Message message = new Message();
        Error error = new Error();
        User user = null;
        Long depttId;

        Map<String, Object> key_response = apiBO.validateApiKey(apiKey, userName);

        if (!(Boolean) key_response.get(VALID)) {
            error = (Error) key_response.get(ERROR);// get error
            mav.addObject(ERROR, error);
            return mav;
        }
        
        boolean hits = true;
        try{
        	hits = apiBO.validateGeneralApiHits(apiKey);
        }catch(Exception e){
        	System.out.println(e);
        	mav.addObject(ERROR, error.generateError(1004));
        	return mav;
        }
        
        if(hits == false){
        	mav.addObject(ERROR, error.generateError(1005));
        	return mav;
        }
        
        List<String> missingParamList=new ArrayList<String>();
        
        if(StringUtils.isEmpty(departmentId) ){
        	missingParamList.add("department_id");
        }
        if(departmentId.length() > 10 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(StringUtils.isEmpty(categoryName) ){
        	missingParamList.add("category_name");
        }
        if(categoryName.length() > 50 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
  
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        if(StringUtils.isEmpty(categoryStatus) )
        	categoryStatus = "true";
        
        try {
        	depttId = Long.parseLong(departmentId);
    		} catch (NumberFormatException nfe) {
    			mav.addObject(ERROR, error.generateError(6002));
    			return mav;
    		}
		try {
			// get user
    		user = (User) key_response.get(USER);
    		            // fetch department 
    		department = storeService.findDepartment(depttId);

        	if (department == null) {
        		mav.addObject(ERROR, error.generateError(6003));
        		return mav;
                }
        	
        	if(department.getUserId() != user.getId().longValue()){
        		mav.addObject(ERROR, error.generateError(6007));
        		return mav;
        	}
        	
        	List<Product> depttProducts = storeService.findProductsDirectUnderDepartment(depttId);
        	List<Product> catProducts = storeService.findProductsByCategory(depttId);
        	if((depttProducts != null && depttProducts.size() > 0) || (catProducts !=null && catProducts.size() > 0)){
				mav.addObject(ERROR, error.generateError(7011));
				return mav;
        		
            }
        	// add sub-department 
        	Department departmentToCreate = new Department();
        	departmentToCreate.setParentDepartmentId(depttId);
        	departmentToCreate.setStoreId(user.getStoreId());
        	departmentToCreate.setUserId(user.getId());
        	departmentToCreate.setsName(categoryName);
        	if (categoryStatus.equalsIgnoreCase("true")) {
        		departmentToCreate.setsStatus("active");
            } else {
            	departmentToCreate.setsStatus("inactive");
            }

        	Long createdDepartmentId = null;
        	try{
        		createdDepartmentId = storeService.createDepartment(departmentToCreate);
        		message.setId(createdDepartmentId.toString());
            	message.setMessage("Sub-department added successfully.");
            	mav.addObject(MESSAGE, message);
            	
        	}catch(DuplicateDepartmentException de){
        		System.out.println(de);
                mav.addObject(ERROR, error.generateError(7018));
                return mav;
        	}catch(Exception e){
        		System.out.println(e);
                mav.addObject(ERROR, error.generateError(7008));
                return mav;
        	}
            
			} catch (Exception e) {
				System.out.println(e);
            	error = error.generateError(1004);
                mav.addObject(ERROR, error);
                return mav;
            }

            return mav;
        
        
    }


    /**
     * Service to update sub-department
     *
     * @param user_name
     * @param api_key
     * @param category_id
     * @param category_name
     * @param category_status
     * @update category details and return updated category
     */

    @RequestMapping(value = "/update-sub-department", method = RequestMethod.POST)
    public ModelAndView updateSubDepartment(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "category_id", required = false) String categoryId,
            @RequestParam(value = "category_name", required = false) String categoryName,
            @RequestParam(value = "category_status", required = false) String categoryStatus) {

        Category category = null;
        ModelAndView mav = new ModelAndView();
        Error error = new Error();
        Message message = new Message();
        User user = null;
        Long ctgryId;

        Map<String, Object> key_response = apiBO.validateApiKey(apiKey, userName);

        if (!(Boolean) key_response.get(VALID)) {
            error = (Error) key_response.get(ERROR);// get error
            mav.addObject(ERROR, error);
            return mav;
        }
        
        boolean hits = true;
        try{
        	hits = apiBO.validateGeneralApiHits(apiKey);
        }catch(Exception e){
        	mav.addObject(ERROR, error.generateError(1004));
        	return mav;
        }
        
        if(hits == false){
        	mav.addObject(ERROR, error.generateError(1005));
        	return mav;
        }
        
        List<String> missingParamList=new ArrayList<String>();
        
        if(StringUtils.isEmpty(categoryId) ){
        	missingParamList.add("category_id");
        }
        if(categoryId.length() > 10 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(StringUtils.isEmpty(categoryName) ){
        	missingParamList.add("categoryName");
        }
        if(categoryName.length() > 50 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
  
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        if(StringUtils.isEmpty(categoryStatus) )
        	categoryStatus = "true";
        
        try {
        	ctgryId = Long.parseLong(categoryId);
    		} catch (NumberFormatException nfe) {
    			mav.addObject(ERROR, error.generateError(7002));
    			return mav;
    		}

		try {
			// get user
    		user = (User) key_response.get(USER);
    		
            // fetch sub-department 
    		category = storeService.findCategory(ctgryId);

        	if (category == null) {
        		mav.addObject(ERROR, error.generateError(7003));
        		return mav;
                }
        	
        	Long departmentId = category.getDepartmentId();
        	Department department =storeService.findDepartment(departmentId);
        	
        	if(department.getUserId() != user.getId().longValue()){
        		mav.addObject(ERROR, error.generateError(7004));
        		return mav;
        	}

        	// update sub-department 
        	category.setsName(categoryName);
        	if (categoryStatus.equalsIgnoreCase("true")) {
                category.setsStatus("active");
            } else {
                category.setsStatus("inactive");
            }

        	try{
        		category = storeService.updateCategory(category);
        		message.setId(category.getId().toString());
            	message.setMessage("Sub-department updated successfully.");
            	mav.addObject(MESSAGE, message);
            	
        	}catch(Exception e){
                mav.addObject(ERROR, error.generateError(7009));
                return mav;
        	}
            
			} catch (Exception e) {
            	error = error.generateError(1004);
                mav.addObject(ERROR, error);
                return mav;
            }

            return mav;
    }


    /**
     * Service to update sub-department
     *
     * @param user_name
     * @param api_key
     * @param category_id
     * @param parent_category_id
     * @param category_name
     * @param category_status
     * @update category details and return updated category
     */

    @RequestMapping(value = "/update-sub-department-nested", method = RequestMethod.POST)
    public ModelAndView updateSubDepartmentNested(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "category_id", required = false) String categoryId,
            @RequestParam(value = "category_name", required = false) String categoryName,
            @RequestParam(value = "category_status", required = false) String categoryStatus) {


    	Department department = null;
    	ModelAndView mav = new ModelAndView();
        Message message = new Message();
        Error error = new Error();
        User user = null;
        Long ctgryId;

        Map<String, Object> key_response = apiBO.validateApiKey(apiKey, userName);

        if (!(Boolean) key_response.get(VALID)) {
            error = (Error) key_response.get(ERROR);// get error
            mav.addObject(ERROR, error);
            return mav;
        }
        
        boolean hits = true;
        try{
        	hits = apiBO.validateGeneralApiHits(apiKey);
        }catch(Exception e){
        	mav.addObject(ERROR, error.generateError(1004));
        	return mav;
        }
        
        if(hits == false){
        	mav.addObject(ERROR, error.generateError(1005));
        	return mav;
        }
        
        List<String> missingParamList=new ArrayList<String>();
        
        if(StringUtils.isEmpty(categoryId) ){
        	missingParamList.add("category_id");
        }
        if(categoryId.length() > 10 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        
        if(StringUtils.isEmpty(categoryName) ){
        	missingParamList.add("category_name");
        }
        if(categoryName.length() > 50 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
  
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        if(StringUtils.isEmpty(categoryStatus) )
        	categoryStatus = "true";
        
        try {
        	ctgryId = Long.parseLong(categoryId);
    		} catch (NumberFormatException nfe) {
    			mav.addObject(ERROR, error.generateError(7002));
    			return mav;
    		}
		
		try {
			// get user
    		user = (User) key_response.get(USER);
    	
            // fetch department 
    		department = storeService.findDepartment(ctgryId);

        	if (department == null) {
        		mav.addObject(ERROR, error.generateError(7003));
        		return mav;
                }
        	
        	if(department.getUserId() != user.getId().longValue()){
        		mav.addObject(ERROR, error.generateError(7004));
        		return mav;
        	}
        	
        	
        	// add sub-department 
        	 department.setsName(categoryName);
        	if (categoryStatus.equalsIgnoreCase("true")) {
        		department.setsStatus("active");
            } else {
            	department.setsStatus("inactive");
            }

        	try{
        		department = storeService.updateDepartment(department);
        		message.setId(department.getId().toString());
            	message.setMessage("Sub-department updated successfully.");
            	mav.addObject(MESSAGE, message);
            	
        	}catch(Exception e){
                mav.addObject(ERROR, error.generateError(7009));
                return mav;
        	}
            
			} catch (Exception e) {
            	error = error.generateError(1004);
                mav.addObject(ERROR, error);
                return mav;
            }

            return mav;
        
        
    }


    /**
     * Service to delete sub-department
     *
     * @param user_name
     * @param api_key
     * @param category_id
     * @return Status message regarding deletion of category
     */

    @RequestMapping(value = "/delete-sub-department", method = RequestMethod.DELETE)
    public ModelAndView deleteSubDepartmentUnderDepartment(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "category_id", required = false) String categoryId) {

        Department department = null;
        ModelAndView mav = new ModelAndView();
        Error error = new Error();
        Message message = new Message();
        Long ctgryId = null;
        User user = null;

        Map<String, Object> key_response = apiBO.validateApiKey(apiKey, userName);

        if (!(Boolean) key_response.get(VALID)) {
            error = (Error) key_response.get(ERROR);// get error
            mav.addObject(ERROR, error);
            return mav;
        }
        
        boolean hits = true;
        try{
        	hits = apiBO.validateGeneralApiHits(apiKey);
        }catch(Exception e){
        	mav.addObject(ERROR, error.generateError(1004));
        	return mav;
        }
        
        if(hits == false){
        	mav.addObject(ERROR, error.generateError(1005));
        	return mav;
        }
        
        List<String> missingParamList=new ArrayList<String>();
        if(StringUtils.isEmpty(categoryId) ){
        	missingParamList.add("category_id");
        }
        if(categoryId.length() > 10 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(missingParamList.size() > 0){
        	error = new Error(1003, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        try {
        	ctgryId = Long.parseLong(categoryId);
    		} catch (NumberFormatException nfe) {
    			mav.addObject(ERROR, error.generateError(7002));
    			return mav;
    		}

        	try {
    			// get user
                user = (User) key_response.get(USER);
                department = storeService.findDepartment(ctgryId);
                
                if(department == null){
    				mav.addObject(ERROR, error.generateError(7003));
    				return mav;
                }
                
                if(department.getParentDepartmentId() == 0){
                	mav.addObject(ERROR, error.generateError(7017));
    				return mav;
                }
                
                if(department.getUserId() != user.getId().longValue()){
    				mav.addObject(ERROR, error.generateError(7004));
    				return mav;
            		}
                
                } catch (Exception e) {
                	logger.error("Error in finding", e);
                    error = error.generateError(1004);
                    mav.addObject(ERROR, error);
                    return mav;
                }
                
                try{
                	storeService.deleteDepartment(department);
                }catch(CategoriesExistInDepartmentException ce){
                	logger.error("Error in finding", ce);
                	error = error.generateError(6008);
                    mav.addObject(ERROR, error);
                    return mav;
                }catch(ProductsExistInDepartmentException pe){
                	logger.error("Error in finding", pe);
                	error = error.generateError(6009);
                    mav.addObject(ERROR, error);
                    return mav;
                }catch(Exception e){
                	logger.error("Error in finding", e);
                	error = error.generateError(1004);
                    mav.addObject(ERROR, error);
                    return mav;
                }
                message.setMessage("Sub-department deleted successfully.");
                mav.addObject(message);
                return mav;
    }


    
    
    
    
    
    
}
