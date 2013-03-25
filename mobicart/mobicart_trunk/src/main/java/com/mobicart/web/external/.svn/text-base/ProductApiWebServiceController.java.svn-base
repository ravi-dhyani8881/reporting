package com.mobicart.web.external;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.supercsv.cellprocessor.constraint.StrMinMax;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.exception.SuperCSVException;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import com.mobicart.bo.ApiBO;
import com.mobicart.bo.Error;
import com.mobicart.bo.Message;
import com.mobicart.model.App;
import com.mobicart.model.Category;
import com.mobicart.model.Department;
import com.mobicart.model.DiskSpacePricing;
import com.mobicart.model.Product;
import com.mobicart.model.ProductBean;
import com.mobicart.model.ProductImage;
import com.mobicart.model.ProductOption;
import com.mobicart.model.ProductReview;
import com.mobicart.model.Store;
import com.mobicart.model.User;
import com.mobicart.model.api.MainProductApi;
import com.mobicart.model.api.ProductApi;
import com.mobicart.model.api.ProductImageApi;
import com.mobicart.model.api.ProductOptionApi;
import com.mobicart.model.api.ProductReviewApi;
import com.mobicart.service.AppService;
import com.mobicart.service.StoreService;
import com.mobicart.service.user.UserService;
import com.mobicart.util.CacheManager;
import com.mobicart.util.CouchBaseCacheManager;
import com.mobicart.util.Constants;
import com.mobicart.util.DateTimeUtils;
import com.mobicart.util.FileUtils;
import com.mobicart.util.ImageUtils;
import com.mobicart.util.ImgMagicCmdExcecutor;
import com.mobicart.util.MagicalPower;
import com.mobicart.util.Pager;
import com.mobicart.util.PathLocator;
import com.mobicart.util.ResourceProperties;



@Controller
@RequestMapping("/api/**")
public class ProductApiWebServiceController {

	
	
	@Autowired
    ApiBO apiBO;
	@Autowired
    StoreService storeService;
	@Autowired
    UserService userService;
	@Autowired
    AppService appService;
	
	
	private static final String ERROR = "error";
	private static final String VALID = "valid";
	private static final String USER = "user";
	private static final String MESSAGE = "message";
	private static final String PRODUCTS = "products";
	
	
	
	/**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory
            .getLogger(ProductApiWebServiceController.class);

	

    /**
     * Service to get Products under Store
     *
     * @param user_name
     * @param api_key
     * @param store_id
     * @return List of Products under store in JSON or XML
     */

    @RequestMapping(value = "/store-products", method = RequestMethod.GET)
    public ModelAndView getProductsForStore(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "store_id", required = false) String storeId) {

        List<Product> products = null;
        Store store = null;
        ModelAndView mav = new ModelAndView();
        Error error = new Error();
        Long streId;
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
        if(StringUtils.isEmpty(storeId) ){
        	missingParamList.add("store_id");
        }
        if(storeId.length() > 10 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(missingParamList.size()>0){
        	error=error.generateError(1001);
        	error.appendToMessage(missingParamList.toString());
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
            // validate user

            store = storeService.findStoreById(streId);
            if(store == null){
				mav.addObject(ERROR, error.generateError(2003));
				return mav;
            }
			if(store.getUserId().longValue() != user.getId().longValue()){
				mav.addObject(ERROR, error.generateError(2004));
				return mav;
        	}

			
			int productsCounts= storeService.findProductCountByStore(streId);
			if(productsCounts>user.getStoreProductLimit()){
				Pager page=new Pager();
				page.setPage(0);
				page.setResults(user.getStoreProductLimit()); 
				
				/*implementing caching.look object in couchbase cache first*/
				Object object=CouchBaseCacheManager.getObject(apiKey);
				if(object!=null){
					try
					{
					products=(List<Product>)object;
					}
					catch (Exception e) {
						logger.error("Error while fetching object from cache",e);
					}
					
				}
				
				if(products==null){
				products = storeService.findAPIProductsByStore(streId,page);
				CouchBaseCacheManager.setObject(apiKey,CouchBaseCacheManager.EXP_TIME,products);
				}
				
			}else{
				
				
				/*implementing caching.look object in couchbase cache first*/
				Object object=CouchBaseCacheManager.getObject(apiKey);
				if(object!=null){
					try
					{
					products=(List<Product>)object;
					}
					catch (Exception e) {
						logger.error("Error while fetching object from cache",e);
					}
					
				}	
		    if(products==null){
			products = storeService.findAPIProductsByStore(streId);
			CouchBaseCacheManager.setObject(apiKey,CouchBaseCacheManager.EXP_TIME,products);
					}
			}
			
			
        	if (products == null || products.size() <= 0) {
        		mav.addObject(ERROR, error.generateError(3002));
        		return mav;
            	}

            } catch (Exception e) {
                error = error.generateError(1004);
                mav.addObject(ERROR, error);
                return mav;
            }
            List<ProductApi> productsApiList = new ArrayList<ProductApi>();
            for (Product product : products) {
                ProductApi productApi = new ProductApi();
                productApi.setProductId(product.getId());
                productApi.setProductName(product.getsName());
                productApi.setProductDescription(product.getsDescription());
                productApi.setDepartmentId(product.getDepartmentId());
                productApi.setDepartmentName(product.getDepartmentName());
                productApi.setSubDepartmentId(product.getCategoryId());
                productApi.setSubDepartmentName(product.getCategoryName());
                productApi.setProductPrice(product.getfPrice());
                productApi.setProductStatus(product.getsStatus());
                productApi.setProductDiscount(product.getfDiscount());
                productApi.setProductSKU(product.getsSaleLabel());
                productApi.setIsFeaturedProduct(product.getbFeatured());
                productApi.setAggregateQuantity(product.getiAggregateQuantity());
                productApi.setUseStockQuantityOnOptions(product.getbUseOptions());
                //productApi.setUseStockQuantityOnProduct(product.getbStockControl());
                productApi.setUseStockQuantityOnProduct(!product.getbUseOptions());
                List<ProductOptionApi> productOptionApiList = new ArrayList<ProductOptionApi>();
                if (product.getProductOptions() != null) {
                    for (ProductOption productOption : product.getProductOptions()) {
                        ProductOptionApi productOptionApi = new ProductOptionApi();
                        productOptionApi.setOptionId(productOption.getId());
                        productOptionApi.setOptionName(productOption.getsName());
                        productOptionApi.setQuantityInStock(productOption.getiAvailableQuantity());
                        productOptionApi.setOptionPrice(productOption.getpPrice());
                        productOptionApiList.add(productOptionApi);

                    }
                }
                productApi.setProductOptions(productOptionApiList);

                List<ProductImageApi> productImageApiList = new ArrayList<ProductImageApi>();
                if (product.getProductImages() != null) {
                    for (ProductImage productImage : product.getProductImages()) {
                        ProductImageApi productImageApi = new ProductImageApi();
                        productImageApi.setImageId(productImage.getId());
                        productImageApi.setLocation(ResourceProperties.getString("appUrl") + productImage.getsLocation());
                        productImageApi.setTitle(productImage.getsTitle());
                        productImageApi.setLocationMedium(ResourceProperties.getString("appUrl") + productImage.getsLocationMedium());
                        productImageApi.setLocationSmall(ResourceProperties.getString("appUrl") + productImage.getsLocationSmall());
                        productImageApiList.add(productImageApi);
                    }
                }
                productApi.setProductImages(productImageApiList);

                List<ProductReviewApi> productReviewApiList = new ArrayList<ProductReviewApi>();
                if (product.getProductReviews() != null) {
                    for (ProductReview productReview : product.getProductReviews()) {
                        ProductReviewApi producReviewApi = new ProductReviewApi();
                        producReviewApi.setReviewId(productReview.getId());
                        producReviewApi.setReviewerName(productReview.getsReveiwerName());
                        producReviewApi.setReviewerEmail(productReview.getsReviewerEmail());
                        producReviewApi.setReviewerRating(productReview.getiRating());
                        producReviewApi.setReviewerComment(productReview.getsReview());
                        productReviewApiList.add(producReviewApi);
                    }
                }
                productApi.setProductReviews(productReviewApiList);

                productsApiList.add(productApi);
            }
            MainProductApi mainProduct = new MainProductApi();
            mainProduct.setProducts(productsApiList);
            mainProduct.setStoreId(store.getId());
            mainProduct.setStoreName(store.getsSName());
            mav.addObject(PRODUCTS, mainProduct);
            return mav;

        
    }


    
    
    /**
     * Service to get Products under Store
     *
     * @param user_name
     * @param api_key
     * @param store_id
     * @return List of Products under store in JSON or XML
     */

    @RequestMapping(value = "/department-products", method = RequestMethod.GET)
    public ModelAndView getProductsForDepartment(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "department_id", required = false) String departmentId) {

        List<Product> products = null;
        Department department = null;
        ModelAndView mav = new ModelAndView();
        Error error = new Error();
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
    			if(department.getUserId() != user.getId().longValue()){
    				mav.addObject(ERROR, error.generateError(6007));
    				return mav;
            	}

    			//products = storeService.findProductsByDepartment(depttId);
    			Product product=new Product();
    			product.setDepartmentId(depttId);
    			product.setMaxRowNum(Long.parseLong(user.getStoreProductLimit().toString()));
    			product.setUserId(user.getId());
    			products = storeService.findProductsByDepartmentAndMaxRowNum(product);
    			 
    			
            	if (products == null || products.size() <= 0) {
            		mav.addObject(ERROR, error.generateError(3003));
            		return mav;
                	}

                } catch (Exception e) {
                    error = error.generateError(1004);
                    mav.addObject(ERROR, error);
                    e.printStackTrace();
                    return mav;
                }

            List<ProductApi> productsApiList = new ArrayList<ProductApi>();
            for (Product product : products) {
                ProductApi productApi = new ProductApi();
                productApi.setProductId(product.getId());
                productApi.setProductName(product.getsName());
                productApi.setProductDescription(product.getsDescription());
                productApi.setSubDepartmentName(product.getCategoryName());
                productApi.setProductPrice(product.getfPrice());
                productApi.setProductStatus(product.getsStatus());
                productApi.setProductDiscount(product.getfDiscount());
                productApi.setProductSKU(product.getsSaleLabel());
                productApi.setIsFeaturedProduct(product.getbFeatured());
                productApi.setAggregateQuantity(product.getiAggregateQuantity());
                productApi.setUseStockQuantityOnOptions(product.getbUseOptions());
                productApi.setUseStockQuantityOnProduct(!product.getbUseOptions());
                //productApi.setUseStockQuantityOnProduct(product.getbStockControl());

                List<ProductOptionApi> productOptionApiList = new ArrayList<ProductOptionApi>();
                for (ProductOption productOption : product.getProductOptions()) {
                    ProductOptionApi productOptionApi = new ProductOptionApi();
                    productOptionApi.setOptionId(productOption.getId());
                    productOptionApi.setOptionName(productOption.getsName());
                    productOptionApi.setQuantityInStock(productOption.getiAvailableQuantity());
                    productOptionApiList.add(productOptionApi);

                }
                productApi.setProductOptions(productOptionApiList);

                List<ProductImageApi> productImageApiList = new ArrayList<ProductImageApi>();
                for (ProductImage productImage : product.getProductImages()) {
                    ProductImageApi productImageApi = new ProductImageApi();
                    productImageApi.setImageId(productImage.getId());
                    productImageApi.setLocation(ResourceProperties.getString("appUrl") + productImage.getsLocation());
                    productImageApi.setTitle(productImage.getsTitle());
                    productImageApi.setLocationMedium(ResourceProperties.getString("appUrl") + productImage.getsLocationMedium());
                    productImageApi.setLocationSmall(ResourceProperties.getString("appUrl") + productImage.getsLocationSmall());
                    productImageApiList.add(productImageApi);
                }
                productApi.setProductImages(productImageApiList);

                List<ProductReviewApi> productReviewApiList = new ArrayList<ProductReviewApi>();
                for (ProductReview productReview : product.getProductReviews()) {
                    ProductReviewApi producReviewApi = new ProductReviewApi();
                    producReviewApi.setReviewId(productReview.getId());
                    producReviewApi.setReviewerName(productReview.getsReveiwerName());
                    producReviewApi.setReviewerEmail(productReview.getsReviewerEmail());
                    producReviewApi.setReviewerRating(productReview.getiRating());
                    producReviewApi.setReviewerComment(productReview.getsReview());
                    productReviewApiList.add(producReviewApi);
                }
                productApi.setProductReviews(productReviewApiList);


                productsApiList.add(productApi);
            }
            MainProductApi mainProduct = new MainProductApi();
            mainProduct.setProducts(productsApiList);
            mainProduct.setDepartmentId(department.getId());
            mainProduct.setDepartmentName(department.getsName());
            mav.addObject(PRODUCTS, mainProduct);
            return mav;
        
    }


    
    /**
     * Service to get Products under Store
     *
     * @param user_name
     * @param api_key
     * @param store_id
     * @return List of Products under store in JSON or XML
     */

    @RequestMapping(value = "/category-products", method = RequestMethod.GET)
    public ModelAndView getProductsForCategory(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "category_id", required = false) String categoryId) {

        List<Product> products = null;
        ModelAndView mav = new ModelAndView();
        Error error = new Error();
        Department department = null;
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
        
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
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
    			department = storeService.findDepartment(ctgryId);
                if(department == null){
    				mav.addObject(ERROR, error.generateError(7003));
    				return mav;
                }
                
                if(department.getParentDepartmentId() == 0){
                	mav.addObject(ERROR, error.generateError(7017));
    				return mav;
                }
                User  user = (User) key_response.get(USER);
                Product product=new Product();
    			product.setDepartmentId(ctgryId);
    			product.setMaxRowNum(Long.parseLong(user.getStoreProductLimit().toString()));
    			product.setUserId(user.getId());
                products = storeService.findProductsByDepartmentAndMaxRowNum(product);
    			//products = storeService.findProductsByCategory(ctgryId);
    			System.out.println(products.size());
    			
            	if (products == null || products.size() <= 0) {
            		products = storeService.findProductsByDepartment(ctgryId);
            		}
            	if(products == null || products.size() <= 0){
            		mav.addObject(ERROR, error.generateError(3004));
            		return mav;
            	}

                } catch (Exception e) {
                    error = error.generateError(1004);
                    mav.addObject(ERROR, error);
                    return mav;
                }


            List<ProductApi> productsApiList = new ArrayList<ProductApi>();
            for (Product product : products) {
                ProductApi productApi = new ProductApi();
                productApi.setProductId(product.getId());
                productApi.setProductName(product.getsName());
                productApi.setProductDescription(product.getsDescription());
                productApi.setProductPrice(product.getfPrice());
                productApi.setProductStatus(product.getsStatus());
                productApi.setProductDiscount(product.getfDiscount());
                productApi.setProductSKU(product.getsSaleLabel());
                productApi.setIsFeaturedProduct(product.getbFeatured());
                productApi.setAggregateQuantity(product.getiAggregateQuantity());
                productApi.setUseStockQuantityOnOptions(product.getbUseOptions());
                productApi.setUseStockQuantityOnProduct(!product.getbUseOptions());
                //productApi.setUseStockQuantityOnProduct(product.getbStockControl());

                List<ProductOptionApi> productOptionApiList = new ArrayList<ProductOptionApi>();
                for (ProductOption productOption : product.getProductOptions()) {
                    ProductOptionApi productOptionApi = new ProductOptionApi();
                    productOptionApi.setOptionId(productOption.getId());
                    productOptionApi.setOptionName(productOption.getsName());
                    productOptionApi.setQuantityInStock(productOption.getiAvailableQuantity());
                    productOptionApiList.add(productOptionApi);

                }
                productApi.setProductOptions(productOptionApiList);

                List<ProductImageApi> productImageApiList = new ArrayList<ProductImageApi>();
                for (ProductImage productImage : product.getProductImages()) {
                    ProductImageApi productImageApi = new ProductImageApi();
                    productImageApi.setImageId(productImage.getId());
                    productImageApi.setLocation(ResourceProperties.getString("appUrl") + productImage.getsLocation());
                    productImageApi.setTitle(productImage.getsTitle());
                    productImageApi.setLocationMedium(ResourceProperties.getString("appUrl") + productImage.getsLocationMedium());
                    productImageApi.setLocationSmall(ResourceProperties.getString("appUrl") + productImage.getsLocationSmall());
                    productImageApiList.add(productImageApi);
                }
                productApi.setProductImages(productImageApiList);

                List<ProductReviewApi> productReviewApiList = new ArrayList<ProductReviewApi>();
                for (ProductReview productReview : product.getProductReviews()) {
                    ProductReviewApi producReviewApi = new ProductReviewApi();
                    producReviewApi.setReviewId(productReview.getId());
                    producReviewApi.setReviewerName(productReview.getsReveiwerName());
                    producReviewApi.setReviewerEmail(productReview.getsReviewerEmail());
                    producReviewApi.setReviewerRating(productReview.getiRating());
                    producReviewApi.setReviewerComment(productReview.getsReview());
                    productReviewApiList.add(producReviewApi);
                }
                productApi.setProductReviews(productReviewApiList);

                productsApiList.add(productApi);
            }
            MainProductApi mainProduct = new MainProductApi();
            mainProduct.setProducts(productsApiList);
            mainProduct.setDepartmentId(department.getParentDepartmentId());
            mainProduct.setSubDepartmentId(ctgryId);
            mainProduct.setSubDepartmentName(department.getsName());
            mav.addObject(PRODUCTS, mainProduct);
            return mav;
        
    }


    /**
     * Service to get Products Details
     *
     * @param user_name
     * @param api_key
     * @param product_id
     * @return 'Product' object containing product details in JSON or XML
     */

    @RequestMapping(value = "/product-details", method = RequestMethod.GET)
    public ModelAndView getProductDetails(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "product_id", required = false) String productId) {

        Product product = null;
        ModelAndView mav = new ModelAndView();
        Error error = new Error();
        Long prodId = null;
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
        if(StringUtils.isEmpty(productId) ){
        	missingParamList.add("product_id");
        }
        if(productId.length() > 10 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        try {
        	prodId = Long.parseLong(productId);
    		} catch (NumberFormatException nfe) {
    			mav.addObject(ERROR, error.generateError(3001));
    			return mav;
    		}

        	try {
    			// get user
                user = (User) key_response.get(USER);

                product = storeService.findProduct(prodId);
                
                if(product == null){
    				mav.addObject(ERROR, error.generateError(3005));
    				return mav;
                }
                
                Long depttId = product.getDepartmentId();
                Department department = storeService.findDepartment(depttId);
                
    			if(department.getUserId() != user.getId().longValue()){
    				mav.addObject(ERROR, error.generateError(3010));
    				return mav;
            		}
    			
                } catch (Exception e) {
                    error = error.generateError(1004);
                    mav.addObject(ERROR, error);
                    return mav;
                }

            ProductApi productApi = new ProductApi();
            productApi.setProductId(product.getId());
            productApi.setProductName(product.getsName());
            productApi.setProductDescription(product.getsDescription());
            productApi.setDepartmentId(product.getDepartmentId());
            productApi.setDepartmentName(product.getDepartmentName());
            productApi.setSubDepartmentId(product.getCategoryId());
            productApi.setSubDepartmentName(product.getCategoryName());
            productApi.setProductPrice(product.getfPrice());
            productApi.setProductStatus(product.getsStatus());
            productApi.setProductDiscount(product.getfDiscount());
            productApi.setProductSKU(product.getsSaleLabel());
            productApi.setIsFeaturedProduct(product.getbFeatured());
            productApi.setVideoUrl(product.getsVideoUrl());
            productApi.setAggregateQuantity(product.getiAggregateQuantity());
            productApi.setUseStockQuantityOnOptions(product.getbUseOptions());
            productApi.setUseStockQuantityOnProduct(!product.getbUseOptions());
           // productApi.setUseStockQuantityOnProduct(product.getbStockControl());
            List<ProductOptionApi> productOptionApiList = new ArrayList<ProductOptionApi>();
            for (ProductOption productOption : product.getProductOptions()) {
                ProductOptionApi productOptionApi = new ProductOptionApi();
                productOptionApi.setOptionId(productOption.getId());
                productOptionApi.setOptionName(productOption.getsName());
                productOptionApi.setOptionTitle(productOption.getsTitle());
                productOptionApi.setOptionSKU(productOption.getsSaleLabel());
                productOptionApi.setQuantityInStock(productOption.getiAvailableQuantity());
                productOptionApi.setOptionPrice(productOption.getpPrice());
                productOptionApiList.add(productOptionApi);

            }
            productApi.setProductOptions(productOptionApiList);

            List<ProductImageApi> productImageApiList = new ArrayList<ProductImageApi>();
            for (ProductImage productImage : product.getProductImages()) {
                ProductImageApi productImageApi = new ProductImageApi();
                productImageApi.setImageId(productImage.getId());
                productImageApi.setLocation(ResourceProperties.getString("appUrl") + productImage.getsLocation());
                productImageApi.setTitle(productImage.getsTitle());
                productImageApi.setLocationMedium(ResourceProperties.getString("appUrl") + productImage.getsLocationMedium());
                productImageApi.setLocationSmall(ResourceProperties.getString("appUrl") + productImage.getsLocationSmall());
                productImageApiList.add(productImageApi);
            }
            productApi.setProductImages(productImageApiList);

            List<ProductReviewApi> productReviewApiList = new ArrayList<ProductReviewApi>();
            for (ProductReview productReview : product.getProductReviews()) {
                ProductReviewApi producReviewApi = new ProductReviewApi();
                producReviewApi.setReviewId(productReview.getId());
                producReviewApi.setReviewerName(productReview.getsReveiwerName());
                producReviewApi.setReviewerEmail(productReview.getsReviewerEmail());
                producReviewApi.setReviewerRating(productReview.getiRating());
                producReviewApi.setReviewerComment(productReview.getsReview());
                productReviewApiList.add(producReviewApi);
            }
            productApi.setProductReviews(productReviewApiList);
            mav.addObject("Product", productApi);
            return mav;
        
    }

    /**
     * Service to delete Product
     *
     * @param user_name
     * @param api_key
     * @param product_id
     * @return Delete Product Status String
     */
    @RequestMapping(value = "/delete-product", method = {RequestMethod.DELETE, RequestMethod.GET})
    public ModelAndView deleteProduct(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "product_id", required = false) String productId) {
        Product product = null;
        Message message = new Message();
        ModelAndView mav = new ModelAndView();
        Error error = new Error();
        Long prodId = null;
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
        if(StringUtils.isEmpty(productId) ){
        	missingParamList.add("product_id");
        }
        if(productId.length() > 10 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        try {
        	prodId = Long.parseLong(productId);
    		} catch (NumberFormatException nfe) {
    			mav.addObject(ERROR, error.generateError(3001));
    			return mav;
    		}

        	try {
    			// get user
                user = (User) key_response.get(USER);
             

                product = storeService.findProduct(prodId);
                
                if(product == null){
    				mav.addObject(ERROR, error.generateError(3005));
    				return mav;
                }
                
                Long depttId = product.getDepartmentId();
                Department department = storeService.findDepartment(depttId);
                
    			if(department.getUserId() != user.getId().longValue()){
    				mav.addObject(ERROR, error.generateError(3010));
    				return mav;
            		}
    			
                } catch (Exception e) {
                    error = error.generateError(1004);
                    mav.addObject(ERROR, error);
                    return mav;
                }
                
                try{
                	storeService.deleteProductAPI(product);
                	/*clear cache*/
            		CacheManager.setToCache(apiKey,null);
            		CouchBaseCacheManager.deleteObject(apiKey);
                }catch(Exception e){
                	error = error.generateError(1004);
                    mav.addObject(ERROR, error);
                    return mav;
                }
                message.setMessage("Product deleted successfully.");
                mav.addObject(message);
                return mav;
                
    }

    
    
    
    /**
     * Service to add Product
     *
     * @param user_name
     * @param api_key
     * @param user_id
     * @param department_id
     * @param category_id
     * @param product_name
     * @param product_description
     * @param product_price
     * @param status
     * @param discount
     * @param product_image_url
     * @param video_title
     * @param sale_label
     * @param video_url
     * @param aggregate_quantity
     * @param purchase_count
     * @param taxable
     * @param featured
     * @return Add Product Status String
     */
    @RequestMapping(value = "/add-product", method = RequestMethod.POST)
    public ModelAndView addProduct(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "department_id", required = false) String departmentId,
            @RequestParam(value = "product_name", required = false) String productName,
            @RequestParam(value = "product_description", required = false) String productDescription,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "f_price", required = false) String fPrice,
            @RequestParam(value = "discount", required = false) String discount,
            @RequestParam(value = "product_image_url", required = false) String productImageUrl,
            @RequestParam(value = "product_image_path", required = false) MultipartFile productImagePath,
            @RequestParam(value = "sale_label", required = false) String saleLabel,
            @RequestParam(value = "video_url", required = false) String videoUrl,
            @RequestParam(value = "aggregate_quantity", required = false) String aggregateQuantity,
            @RequestParam(value = "featured", required = false) String featured,
            @RequestParam(value = "stock_on", required = false) String stockOn
            
            
    		) {
       
    	
    	Product product = new Product();
        ModelAndView mav = new ModelAndView();
        Message message = new Message();
        List<Error> errors = new ArrayList<Error>();
        Error error = new Error();
        User user = null;
        Long depttId = null;
        Double price = null;
        Double dscount = null;
        Long aggrtQuntity = null;
        Integer ftured = null;
        Long productId = null;
        ProductImage productImage = null;
        
        
        //Long productImageId = null;

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
        
        if(StringUtils.isEmpty(productName) ){
        	missingParamList.add("proct_name");
        }
        if(productName.length() > 100 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
  
        if(StringUtils.isEmpty(fPrice) ){
        	missingParamList.add("f_price");
        }
        if(fPrice.length() > 10 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        
        if(StringUtils.isEmpty(status) ) status = "active";
        if(StringUtils.isEmpty(discount) ) discount = "0";
        if(StringUtils.isEmpty(aggregateQuantity) ) aggregateQuantity = "0";
        if(StringUtils.isEmpty(featured) ) featured = "0";
        
        try {
        	depttId = Long.parseLong(departmentId);
        	} catch (NumberFormatException nfe) {
                mav.addObject(ERROR, error.generateError(6002));
                return mav;
            }
        
    	
		if( ! (status.equalsIgnoreCase("active") || status.equalsIgnoreCase("hidden") 
				|| status.equalsIgnoreCase("sold") || status.equalsIgnoreCase("coming"))){
			mav.addObject(ERROR, error.generateError(3021));
			return mav;
		}
		
		if( ! StringUtils.isEmpty(productImageUrl) ){
			if( ! productImageUrl.startsWith("http://")){
				mav.addObject(ERROR, error.generateError(3015));
				return mav;
	    	}
	    		
	    	if(!(productImageUrl.endsWith("png") || productImageUrl.endsWith("jpg") || productImageUrl.endsWith("jpeg")
	    			|| productImageUrl.endsWith("bmp") || productImageUrl.endsWith("gif"))){
	    		mav.addObject(ERROR, error.generateError(3016));
	    		return mav;
	    	}
		}
		
		try {
			price = Double.parseDouble(fPrice);
        	} catch (NumberFormatException nfe) {
                mav.addObject(ERROR, error.generateError(3006));
                return mav;
            }
        fPrice=	new DecimalFormat("0.00").format(price);

        try {
        	dscount = Double.parseDouble(discount);
            } catch (NumberFormatException nfe) {
                mav.addObject(ERROR, error.generateError(3007));
                return mav;
            }
        discount=	new DecimalFormat("0.00").format(dscount);   
        if(dscount > 100){
        	mav.addObject(ERROR, error.generateError(3027));
            return mav;
        }

        try {
        	aggrtQuntity = Long.parseLong(aggregateQuantity);
        	} catch (NumberFormatException nfe) {
                mav.addObject(ERROR, error.generateError(3008));
                return mav;
            }
        try {
        	ftured = Integer.parseInt(featured);
        	if (!(ftured == 0 || ftured == 1)) {
        		mav.addObject(ERROR, error.generateError(3022));
        		return mav;
            	}
            } catch (NumberFormatException nfe) {
                mav.addObject(ERROR, error.generateError(3022));
                return mav;
            }


        try {
        	// get user
        	user = (User) key_response.get(USER);
        	
        	
        	
            Department department = null;
            // fetch department 
            department = storeService.findDepartmentForApi(depttId);
            
            if (department == null) {
            	mav.addObject(ERROR,error.generateError(6003));
            	return mav;
            	}
        	// Match user's department 
            if (!String.valueOf(user.getId()).equalsIgnoreCase(
            		String.valueOf(department.getUserId()))) {
            	mav.addObject(ERROR, error.generateError(6007));
            	return mav;
            	}
            List<Department> subDeptts = department.getSubDepartments();
            if(subDeptts != null && subDeptts.size() > 0){
            	mav.addObject(ERROR, error.generateError(3026));
            	return mav;
            }
            
            if(user.getStoreProductLimit() <= user.getiProductCount()){
            	mav.addObject(ERROR, error.generateError(3023));
            	return mav;
            }
        	// fetch user's Application
            App app = storeService.findAPIAppByUser(user.getId());
            
            
        	// add product  
        	product.setAppId(app.getId());
        	product.setStoreId(department.getStoreId());
        	product.setUserId(user.getId());
        	product.setDepartmentId(depttId);
        	product.setsName(productName);
        	if (productDescription != null)
        		product.setsDescription(productDescription);
        	if (status != null)
        		product.setsStatus(status);
        	product.setfPrice(new BigDecimal(fPrice));
        	product.setfDiscount(new BigDecimal(discount));
        	
        	
        	if("products".equals(stockOn)){
        		product.setbStockControl(true);
        	}else if("options".equals(stockOn)){
        		product.setbUseOptions(true);
        	}
        	 
        		
        	 
        	

        	if (videoUrl != null)
        		product.setsVideoUrl(videoUrl);
        	if (saleLabel != null)
        		product.setsSaleLabel(saleLabel);
        	product.setiAggregateQuantity(aggrtQuntity.intValue());
        	if (ftured == 1) product.setbFeatured(true);
        	else product.setbFeatured(false);
        	
        	
        	List<Product> productlist=storeService.findProductsByName(product.getsName(), product.getDepartmentId());
        	if(productlist != null && productlist.size() > 0){
        		mav.addObject(ERROR, error.generateError(3029));
        		return mav;
        	}

            /////////////   NEW CHANGES///////////////////////////
        	Long diskPricingId = user.getDiskSpacePricingId();
        	DiskSpacePricing diskSpacePricing = userService.findDiskSpacePricingById(diskPricingId);
        	//
        	long maxLimit =user.getStoreProductLimit();//diskSpacePricing.getiMaxLimit(); ;
    		long productCount = user.getiProductCount();
        	long restLimit = maxLimit - productCount;
        	
        	if (restLimit > 0) {
        		productId = storeService.createProduct(product);
        		
        		/*clear cache*/
        		CacheManager.setToCache(apiKey,null);
        		CouchBaseCacheManager.deleteObject(apiKey);
        		} else {
            		mav.addObject(ERROR, error.generateError(3023));
            		return mav;
                	}
        	/////////////   NEW CHANGES///////////////////////////
        	if(productId == null){
        		message.setMessage("Product could not be added.");
        		mav.addObject(MESSAGE, message);
        		return mav;
        	}
        	if (productId != null && ! StringUtils.isEmpty(productImageUrl)) {
        		
        		 PathLocator pathLocator = new PathLocator();
        		// change to online server for the time being
        		String rootPath = pathLocator.getContextPath();

        		String mobicartImagesFolderPath = "mobicartimages";
        		FileUtils.makeDirectoryIfItsNotThere(rootPath + mobicartImagesFolderPath);

        		String productFolderPath = "/productimages";
        		FileUtils.makeDirectoryIfItsNotThere(rootPath + mobicartImagesFolderPath + productFolderPath);

        		//hard code
        		String storeFolderPath = "/"+product.getStoreId();
        		FileUtils.makeDirectoryIfItsNotThere(rootPath + mobicartImagesFolderPath + productFolderPath + storeFolderPath);

        		String dateWiseFolderPath = "/" + DateTimeUtils.getCurrentDate();
        		FileUtils.makeDirectoryIfItsNotThere(rootPath + mobicartImagesFolderPath + productFolderPath + storeFolderPath + dateWiseFolderPath);

        		String finalGalleryImagePath = mobicartImagesFolderPath + productFolderPath + storeFolderPath + dateWiseFolderPath;

        		int fileNameIndex = productImageUrl.lastIndexOf("/");
        		String fileName = productImageUrl.substring(fileNameIndex);
            
        		String productImageFileName = FileUtils.cleanSpecialChars(fileName);
        		String mediumGalleryImagePath = rootPath + finalGalleryImagePath + "/" + FileUtils.stuffedFilename(productImageFileName, "_medium");
        		String smallGalleryImagePath = rootPath + finalGalleryImagePath + "/" + FileUtils.stuffedFilename(productImageFileName, "_small");

        		
        	String	pathForImageStorage= rootPath + finalGalleryImagePath+"/";
        		finalGalleryImagePath += "/"+ productImageFileName;
            
        		URL urlOfImage = null;
        		File outputFile = null;
            
        		BufferedInputStream in = null;
        		FileOutputStream out = null;
        		try {
        			outputFile = new File(rootPath + finalGalleryImagePath);
        			urlOfImage = new URL(productImageUrl);
    							
        			in = new BufferedInputStream(urlOfImage.openStream());
        			BufferedImage bufferedGalleryImage = ImageIO.read(in);
        			out = new FileOutputStream(outputFile);
        			String format = (productImageUrl.endsWith(".png")) ? "png" : "jpg";
        			ImageIO.write(bufferedGalleryImage, format, out);
            	
        			BufferedImage bufferedMediumProductImage = ImageUtils.resize(bufferedGalleryImage, 130, 150, true);
        			BufferedImage bufferedSmallProductImage = ImageUtils.resize(bufferedGalleryImage, 60, 60, true);
            	
        			ImageUtils.saveImage(bufferedMediumProductImage, mediumGalleryImagePath);
        			ImageUtils.saveImage(bufferedSmallProductImage, smallGalleryImagePath);
        			in.close();
        			out.close();
        			
        			///
        			
        			
        			 
    				/*bufferedImage = ImageIO.read(productInputStream);
    				ImageUtils.saveImage(bufferedImage, rootPath+ finalProductImagePath);
    				productInputStream.close();*/
    				String targetFileName=rootPath + finalGalleryImagePath;
    				logger.debug("targetFileName:"+targetFileName+"\n"+"productImageFileName:"+productImageFileName);
    				 
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage,productImageFileName,"small",true);
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage,productImageFileName,"medium",true);
     
    				//ImgMagicCmdExcecutor.resizeAndSaveFile(tempFile, rootPath, originalImageFileName, propertyKey,true)
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_SMALL_ANDROID3_KEY ,true);
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_SMALL_ANDROID4_KEY ,true);
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_SMALL_ANDROID6_KEY ,true);
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_SMALL_IPAD_KEY ,true);
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_SMALL_IPHONE_KEY ,true);
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_SMALL_IPHONE4_KEY ,true);
    				
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_MEDIUM_ANDROID3_KEY ,true);
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_MEDIUM_ANDROID4_KEY ,true);
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_MEDIUM_ANDROID6_KEY ,true);
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_MEDIUM_IPAD_KEY ,true);
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_MEDIUM_IPHONE_KEY ,true);
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_MEDIUM_IPHONE4_KEY ,true);
    				
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_COVER_FLOW_ANDROID3_KEY ,true);
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_COVER_FLOW_ANDROID4_KEY ,true);
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_COVER_FLOW_ANDROID6_KEY ,true);
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_COVER_FLOW_IPAD_KEY ,true);
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_COVER_FLOW_IPHONE_KEY ,true);	
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_COVER_FLOW_IPHONE4_KEY ,true);
    				
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_DETAIL ,true);				
        			///
        			
        			
        			
        			
        			
        			
            	
        			productImage = new ProductImage();
        			productImage.setProductId(productId);
        			productImage.setsTitle(FileUtils.getJustTheFileName(productImageUrl));
        			productImage.setsLocation("/" + finalGalleryImagePath);
        			try {
        				Long productImageId = storeService.createProductImage(productImage);
        				//message.setId(productImageId.toString());
        				//message.setMessage("Product image added successfully");
        			//	mav.addObject(MESSAGE, message);
            		} catch (Exception exp) {
            			 exp.printStackTrace();
            			logger.error("while saving", exp);
            			error = error.generateError(1004);
                        errors.add(error);
                    }
    			} catch (MalformedURLException e) {
    				 e.printStackTrace();
    				logger.error("Wrong URL", e);
    				errors.add(error.generateError(3017));
    				
    			} catch (Exception e) {
    				 
    				logger.error("IOException"+e.getMessage());
    				error = error.generateError(1004);
    				errors.add(error);
    			}
    			finally{
                	if (in != null){in.close(); in = null;}
                	if (out != null){out.close(); out = null;}
    	        }
               
        		
        		 
        		 
        		
        		message.setId(productId.toString());
        		message.setMessage("Product added successfully.");
        		if(errors.size()>0)
        		message.setErrors(errors);
        		mav.addObject(MESSAGE, message);
        	}else if(productId != null && productImagePath != null){
        		
        		if (productImagePath != null && productImagePath.getSize() > 0) {
                    String contentType = productImagePath.getContentType();
                    if ((contentType.equals("image/png")) || contentType.equals("image/jpg") || contentType.equals("image/jpeg") || contentType.equals("image/bmp") || contentType.equals("image/gif")) {
                    } else {
                    	error = error.generateError(3016);
        				errors.add(error);
                    }

                    try {
                        MultipartFile productImageFile = productImagePath;
                        
                        PathLocator pathLocator = new PathLocator();
                        // change to online server for the time being
                        String rootPath = pathLocator.getContextPath();

                        String mobicartImagesFolderPath = "mobicartimages";
                        FileUtils.makeDirectoryIfItsNotThere(rootPath
                                + mobicartImagesFolderPath);

                        String productFolderPath = "/productimages";
                        FileUtils.makeDirectoryIfItsNotThere(rootPath
                                + mobicartImagesFolderPath + productFolderPath);

                        //hard code
                        String storeFolderPath = "/"+product.getStoreId();
                        FileUtils.makeDirectoryIfItsNotThere(rootPath
                                + mobicartImagesFolderPath + productFolderPath
                                + storeFolderPath);

                        String dateWiseFolderPath = "/" + DateTimeUtils.getCurrentDate();
                        FileUtils.makeDirectoryIfItsNotThere(rootPath
                                + mobicartImagesFolderPath + productFolderPath
                                + storeFolderPath + dateWiseFolderPath);

                        String finalGalleryImagePath = mobicartImagesFolderPath
                                + productFolderPath + storeFolderPath + dateWiseFolderPath;

                        String productImageFileName = FileUtils.cleanSpecialChars(productImageFile.getOriginalFilename());
                        String mediumGalleryImagePath = rootPath + finalGalleryImagePath + "/" + FileUtils.stuffedFilename(productImageFileName, "_medium");
                        String smallGalleryImagePath = rootPath + finalGalleryImagePath + "/" + FileUtils.stuffedFilename(productImageFileName, "_small");

                        finalGalleryImagePath += "/"
                                + productImageFileName;


                        InputStream productInputStream = null;
                        OutputStream productOutputStream = null;
                        if (productImageFile.getSize() > 0) {
                            productInputStream = productImageFile.getInputStream();
                            BufferedImage bufferedGalleryImage = ImageIO
                                    .read(productInputStream);
                            productOutputStream = new FileOutputStream(rootPath
                                    + finalGalleryImagePath);
                            String format = (productImageFile.getOriginalFilename()
                                    .endsWith(".png")) ? "png" : "jpg";
                            ImageIO.write(bufferedGalleryImage, format, productOutputStream);
                            //resize image
                            BufferedImage bufferedMediumProductImage = ImageUtils.resize(bufferedGalleryImage, 130, 150, true);
                            BufferedImage bufferedSmallProductImage = ImageUtils.resize(bufferedGalleryImage, 60, 60, true);

                            ImageUtils.saveImage(bufferedMediumProductImage, mediumGalleryImagePath);
                            ImageUtils.saveImage(bufferedSmallProductImage, smallGalleryImagePath);

                            productInputStream.close();
                            productOutputStream.close();
                        }

                        productImage = new ProductImage();
                        productImage.setProductId(productId);
                        productImage.setsTitle(productImagePath.getName());
                        productImage.setsLocation("/" + finalGalleryImagePath);
                        try {
                            Long productImageId = storeService.createProductImage(productImage);
                            message.setId(productId.toString());
                    		message.setMessage("Product added successfully.");
                    		if(errors.size()>0)
                    		message.setErrors(errors);
                    		mav.addObject(MESSAGE, message);

                        } catch (Exception exp) {
                            mav.addObject(ERROR, "Could not add product image");
                            return mav;

                        }

                    } catch (IOException ioe) {
                    }
                }
        		
        		
        	} else {
        		message.setId(productId.toString());
        		message.setMessage("Product added successfully.");
        		if(errors.size()>0)
        		message.setErrors(errors);
        		mav.addObject(MESSAGE, message);
        		}

        	
        } catch (Exception e) {
        	error = error.generateError(1004);
            mav.addObject(ERROR, error);
            return mav;
    		}
        
        return mav;
    }


    
    
    /**
     * Service to update Product
     *
     * @param user_name
     * @param api_key
     * @param product_id
     * @param product_name
     * @param product_description
     * @param product_price
     * @param status
     * @param discount
     * @param product_url
     * @param video_title
     * @param sale_label
     * @param video_url
     * @param aggregate_quantity
     * @param purchase_count
     * @param taxable
     * @param featured
     * @return Update Product Status String
     */
    @RequestMapping(value = "/update-product", method = RequestMethod.POST)
    public ModelAndView updateProduct(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "product_id", required = false) String productId,
            @RequestParam(value = "product_name", required = false) String productName,
            @RequestParam(value = "product_description", required = false) String productDescription,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "f_price", required = false) String fPrice,
            @RequestParam(value = "discount", required = false) String discount,
            @RequestParam(value = "product_image_url", required = false) String productImageUrl,
            @RequestParam(value = "product_image_path", required = false) MultipartFile productImagePath,
            @RequestParam(value = "sale_label", required = false) String saleLabel,
            @RequestParam(value = "video_url", required = false) String videoUrl,
            @RequestParam(value = "aggregate_quantity", required = false) String aggregateQuantity,
            @RequestParam(value = "featured", required = false) String featured,
            @RequestParam(value = "stock_on", required = false) String stockOn
    		) {

    	
    	Product product = null;
        ModelAndView mav = new ModelAndView();
        Message message = new Message();
        List<Error> errors = new ArrayList<Error>();
        Error error = new Error();
        Long prodId = null;
        User user = null;
        Double price = null;
        Double dscount = null;
        Long aggrtQuntity = null;
        Integer ftured = null;
        ProductImage productImage = null;

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
        
        if(StringUtils.isEmpty(productId) ){
        	missingParamList.add("product_id");
        }
        
        if(productId.length() > 10 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(StringUtils.isEmpty(productName) ){
        	missingParamList.add("product_name");
        }
        if(productName.length() > 100 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
  
        if(StringUtils.isEmpty(fPrice) ){
        	missingParamList.add("f_price");
        }
        if(fPrice.length() > 10 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(!StringUtils.isEmpty(aggregateQuantity) && aggregateQuantity.length() > 8 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
       
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        
        
        try {
        	prodId = Long.parseLong(productId);
        	} catch (NumberFormatException nfe) {
                mav.addObject(ERROR, error.generateError(3001));
                return mav;
            }
        
		if(! StringUtils.isEmpty(status) && ! (status.equalsIgnoreCase("active") || status.equalsIgnoreCase("hidden") 
				|| status.equalsIgnoreCase("sold") || status.equalsIgnoreCase("coming"))){
			mav.addObject(ERROR, error.generateError(3021));
			return mav;
		}
		
		if( ! StringUtils.isEmpty(productImageUrl) ){
			if( ! productImageUrl.startsWith("http://")){
				mav.addObject(ERROR, error.generateError(3015));
				return mav;
	    	}
	    		
	    	if(!(productImageUrl.endsWith("png") || productImageUrl.endsWith("jpg") || productImageUrl.endsWith("jpeg")
	    			|| productImageUrl.endsWith("bmp") || productImageUrl.endsWith("gif"))){
	    		mav.addObject(ERROR, error.generateError(3016));
	    		return mav;
	    	}
		}
		
		
		try {
			price = Double.parseDouble(fPrice);
			
        	} catch (NumberFormatException nfe) {
                mav.addObject(ERROR, error.generateError(3006));
                return mav;
            }
        fPrice=	new DecimalFormat("0.00").format(price);
	    
        if(!StringUtils.isEmpty(discount)){
        	try {
	        	dscount = Double.parseDouble(discount);
            } catch (NumberFormatException nfe) {
	                mav.addObject(ERROR, error.generateError(3007));
	                return mav;
            }
           
            discount=	new DecimalFormat("0.00").format(dscount);   
            if(dscount > 100){
            	mav.addObject(ERROR, error.generateError(3027));
            	return mav;
            }
        }

       if(!StringUtils.isEmpty(aggregateQuantity)){
        	try {
	        	aggrtQuntity = Long.parseLong(aggregateQuantity);
	    	} catch (NumberFormatException nfe) {
	            mav.addObject(ERROR, error.generateError(3008));
	            return mav;
	        }
       }
        
       if(!StringUtils.isEmpty(featured)){
   		try {
	        	ftured = Integer.parseInt(featured);
	        	if (!(ftured == 0 || ftured == 1)) {
	        		mav.addObject(ERROR, error.generateError(3022));
	        		return mav;
	            	}
	        } catch (NumberFormatException nfe) {
	        		mav.addObject(ERROR, error.generateError(3022));
	                return mav;
	        }
       }


        try {
        	// get user
        	user = (User) key_response.get(USER);
        	
            
            // fetch product 
        	product = storeService.findProduct(prodId);
            
            if (product == null) {
            	mav.addObject(ERROR, error.generateError(3005));
            	return mav;
            	}
            
        	// Match user's ID 
            if (product.getUserId().longValue() != user.getId().longValue()) {
            	mav.addObject(ERROR, error.generateError(3010));
            	return mav;
            	}
        	// fetch user's Application
            App app = storeService.findAPIAppByUser(user.getId());
            
            
        	// update product  
        	product.setAppId(app.getId());
        	product.setUserId(user.getId());
        	product.setsName(productName);
        	if (!StringUtils.isEmpty(productDescription))
        		product.setsDescription(productDescription);
        	if (!StringUtils.isEmpty(status))
        		product.setsStatus(status);
        	product.setfPrice(new BigDecimal(fPrice));
        	if(dscount != null)
        		product.setfDiscount(new BigDecimal(dscount));
        	if (videoUrl != null)
        		product.setsVideoUrl(videoUrl);
        	if (!StringUtils.isEmpty(saleLabel))
        		product.setsSaleLabel(saleLabel);
        	if(aggrtQuntity != null)
        	product.setiAggregateQuantity(aggrtQuntity.intValue());
        	if(ftured != null){
	        	if (ftured == 1) product.setbFeatured(true);
	        	else product.setbFeatured(false);
        	}
        	
        	 
        	
        	if("products".equals(stockOn)){
        		product.setbStockControl(true);
        	}else if("options".equals(stockOn)){
        		product.setbUseOptions(true);
        	}	 
        		
        	List<Product> productlist=storeService.findProductsByName(product.getsName(), product.getDepartmentId());
        	if(productlist != null && productlist.size() > 1){
        		mav.addObject(ERROR, error.generateError(3031));
        		return mav;
        	} 

        	storeService.updateProduct(product);
        	/*clear cache*/
        	CouchBaseCacheManager.deleteObject(apiKey);
        	if(prodId == null){
        		message.setMessage("Product could not be updated.");
        		mav.addObject(MESSAGE, message);
        		return mav;
        	}
        	
        	if (prodId != null && ! StringUtils.isEmpty(productImageUrl)) {
        		
        		PathLocator pathLocator = new PathLocator();
        		// change to online server for the time being
        		String rootPath = pathLocator.getContextPath();

        		String mobicartImagesFolderPath = "mobicartimages";
        		FileUtils.makeDirectoryIfItsNotThere(rootPath + mobicartImagesFolderPath);

        		String productFolderPath = "/productimages";
        		FileUtils.makeDirectoryIfItsNotThere(rootPath + mobicartImagesFolderPath + productFolderPath);

        		//hard code
        		String storeFolderPath = "/"+product.getStoreId();
        		FileUtils.makeDirectoryIfItsNotThere(rootPath + mobicartImagesFolderPath + productFolderPath + storeFolderPath);

        		String dateWiseFolderPath = "/" + DateTimeUtils.getCurrentDate();
        		FileUtils.makeDirectoryIfItsNotThere(rootPath + mobicartImagesFolderPath + productFolderPath + storeFolderPath + dateWiseFolderPath);

        		String finalGalleryImagePath = mobicartImagesFolderPath + productFolderPath + storeFolderPath + dateWiseFolderPath;

        		int fileNameIndex = productImageUrl.lastIndexOf("/");
        		String fileName = productImageUrl.substring(fileNameIndex);
            
        		String productImageFileName = FileUtils.cleanSpecialChars(fileName);
        		String mediumGalleryImagePath = rootPath + finalGalleryImagePath + "/" + FileUtils.stuffedFilename(productImageFileName, "_medium");
        		String smallGalleryImagePath = rootPath + finalGalleryImagePath + "/" + FileUtils.stuffedFilename(productImageFileName, "_small");

        		String	pathForImageStorage= rootPath + finalGalleryImagePath+"/";
        		finalGalleryImagePath += "/"+ productImageFileName;
            
        		URL urlOfImage = null;
        		File outputFile = null;
            
        		BufferedInputStream in = null;
        		FileOutputStream out = null;
        		try {
        			outputFile = new File(rootPath + finalGalleryImagePath);
        			urlOfImage = new URL(productImageUrl);
    							
        			in = new BufferedInputStream(urlOfImage.openStream());
        			BufferedImage bufferedGalleryImage = ImageIO.read(in);
        			out = new FileOutputStream(outputFile);
        			String format = (productImageUrl.endsWith(".png")) ? "png" : "jpg";
        			ImageIO.write(bufferedGalleryImage, format, out);
            	
        			/*BufferedImage bufferedMediumProductImage = ImageUtils.resize(bufferedGalleryImage, 130, 150, true);
        			BufferedImage bufferedSmallProductImage = ImageUtils.resize(bufferedGalleryImage, 60, 60, true);
            	
        			ImageUtils.saveImage(bufferedMediumProductImage, mediumGalleryImagePath);
        			ImageUtils.saveImage(bufferedSmallProductImage, smallGalleryImagePath);*/
            	
        			in.close();
        			out.close();
            	
        			/*productImage = new ProductImage();
        			productImage.setProductId(prodId);
        			productImage.setsTitle(FileUtils.getJustTheFileName(productImageUrl));
        			productImage.setsLocation("/" + finalGalleryImagePath);
        			try {
        				storeService.createProductImage(productImage);*/
        			
        			//
        			
        			String targetFileName=rootPath + finalGalleryImagePath;
    				logger.debug("targetFileName:"+targetFileName+"\n"+"productImageFileName:"+productImageFileName);
    				 
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage,productImageFileName,"small",true);
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage,productImageFileName,"medium",true);
     
    				//ImgMagicCmdExcecutor.resizeAndSaveFile(tempFile, rootPath, originalImageFileName, propertyKey,true)
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_SMALL_ANDROID3_KEY ,true);
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_SMALL_ANDROID4_KEY ,true);
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_SMALL_ANDROID6_KEY ,true);
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_SMALL_IPAD_KEY ,true);
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_SMALL_IPHONE_KEY ,true);
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_SMALL_IPHONE4_KEY ,true);
    				
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_MEDIUM_ANDROID3_KEY ,true);
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_MEDIUM_ANDROID4_KEY ,true);
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_MEDIUM_ANDROID6_KEY ,true);
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_MEDIUM_IPAD_KEY ,true);
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_MEDIUM_IPHONE_KEY ,true);
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_MEDIUM_IPHONE4_KEY ,true);
    				
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_COVER_FLOW_ANDROID3_KEY ,true);
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_COVER_FLOW_ANDROID4_KEY ,true);
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_COVER_FLOW_ANDROID6_KEY ,true);
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_COVER_FLOW_IPAD_KEY ,true);
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_COVER_FLOW_IPHONE_KEY ,true);	
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_COVER_FLOW_IPHONE4_KEY ,true);
    				
    				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_DETAIL ,true);				
        			///
        			
        			
        		 
        			
        			
        			
        			
        			
        			 List<ProductImage> imagesList=storeService.findProductImages(prodId);
                     productImage = new ProductImage();
                     System.out.println("To be set sLocatio:"+"/" + finalGalleryImagePath);
                     for(ProductImage proimg:imagesList){
                     	
                    	 System.out.println("Id:"+proimg.getId()+",sLocation:"+proimg.getsLocation());
                     	if(proimg.getsLocation().endsWith("/" + finalGalleryImagePath)) 
                     		{
                     		System.out.println("url matched:---- "+proimg.getsLocation());
                     		productImage.setId(proimg.getId());
                     		
                     		
                     		}
                     }
                     
                    
                     productImage.setProductId(prodId);
                     productImage.setsTitle(FileUtils.getJustTheFileName(productImageUrl));
                     productImage.setsLocation("/" + finalGalleryImagePath);
                     System.out.println("productImage.getId():"+productImage.getId());
                     try {
                     	
                     	if(productImage.getId()==null)
                     		{
                     		//System.out.println("ImageUpdate");
                     		
                     		storeService.createProductImage(productImage);
                     		}
                     	else
                     		{
                     		//System.out.println("image created");
                     		storeService.updateProductImage(productImage);
                     		
                     		}
        			
        			
        			
        				message.setId(productImage.getId().toString());
        				message.setMessage("Product updated successfully.");
        				//mav.addObject(MESSAGE, message);
            		} catch (Exception exp) {
            			logger.error("while saving", exp);
            			error = error.generateError(1004);
                        errors.add(error);
                    }
    			} catch (MalformedURLException e) {
    				logger.error("Wrong URL", e);
    				errors.add(error.generateError(3017));
    				
    			} catch (Exception e) {
    				logger.error("IOException", e);
    				error = error.generateError(1004);
    				errors.add(error);
    			}
    			finally{
                	if (in != null) in = null;
                	if (out != null) out = null;
    	        }
        		
        		message.setId(product.getId().toString());
        		message.setMessage("Product updated successfully.");
        		if(errors.size()>0)
        		message.setErrors(errors);
        		mav.addObject(MESSAGE, message);
        	} else if(productId != null && productImagePath != null){
        		
        		if (productImagePath != null && productImagePath.getSize() > 0) {
                    String contentType = productImagePath.getContentType();
                    if ((contentType.equals("image/png")) || contentType.equals("image/jpg") || contentType.equals("image/jpeg") || contentType.equals("image/bmp") || contentType.equals("image/gif")) {
                    } else {
                    	error = error.generateError(3016);
        				errors.add(error);
                    }

                    try {
                        MultipartFile productImageFile = productImagePath;
                        
                        PathLocator pathLocator = new PathLocator();
                        // change to online server for the time being
                        String rootPath = pathLocator.getContextPath();

                        String mobicartImagesFolderPath = "mobicartimages";
                        FileUtils.makeDirectoryIfItsNotThere(rootPath
                                + mobicartImagesFolderPath);

                        String productFolderPath = "/productimages";
                        FileUtils.makeDirectoryIfItsNotThere(rootPath
                                + mobicartImagesFolderPath + productFolderPath);

                        //hard code
                        String storeFolderPath = "/"+product.getStoreId();
                        FileUtils.makeDirectoryIfItsNotThere(rootPath
                                + mobicartImagesFolderPath + productFolderPath
                                + storeFolderPath);

                        String dateWiseFolderPath = "/" + DateTimeUtils.getCurrentDate();
                        FileUtils.makeDirectoryIfItsNotThere(rootPath
                                + mobicartImagesFolderPath + productFolderPath
                                + storeFolderPath + dateWiseFolderPath);

                        String finalGalleryImagePath = mobicartImagesFolderPath
                                + productFolderPath + storeFolderPath + dateWiseFolderPath;

                        String productImageFileName = FileUtils.cleanSpecialChars(productImageFile.getOriginalFilename());
                        String mediumGalleryImagePath = rootPath + finalGalleryImagePath + "/" + FileUtils.stuffedFilename(productImageFileName, "_medium");
                        String smallGalleryImagePath = rootPath + finalGalleryImagePath + "/" + FileUtils.stuffedFilename(productImageFileName, "_small");

                        finalGalleryImagePath += "/"
                                + productImageFileName;


                        InputStream productInputStream = null;
                        OutputStream productOutputStream = null;
                        if (productImageFile.getSize() > 0) {
                            productInputStream = productImageFile.getInputStream();
                            BufferedImage bufferedGalleryImage = ImageIO
                                    .read(productInputStream);
                            productOutputStream = new FileOutputStream(rootPath
                                    + finalGalleryImagePath);
                            String format = (productImageFile.getOriginalFilename()
                                    .endsWith(".png")) ? "png" : "jpg";
                            ImageIO.write(bufferedGalleryImage, format, productOutputStream);
                            //resize image
                            BufferedImage bufferedMediumProductImage = ImageUtils.resize(bufferedGalleryImage, 130, 150, true);
                            BufferedImage bufferedSmallProductImage = ImageUtils.resize(bufferedGalleryImage, 60, 60, true);

                            ImageUtils.saveImage(bufferedMediumProductImage, mediumGalleryImagePath);
                            ImageUtils.saveImage(bufferedSmallProductImage, smallGalleryImagePath);

                            productInputStream.close();
                            productOutputStream.close();
                        }

                        
                        List<ProductImage> imagesList=storeService.findProductImages(prodId);
                        productImage = new ProductImage();
                        for(ProductImage proimg:imagesList){
                        	
                        	if(proimg.getsLocation().endsWith("/" + finalGalleryImagePath)) 
                        		productImage.setId(proimg.getId());
                        }
                        
                       
                        productImage.setProductId(prodId);
                        productImage.setsTitle(productImagePath.getName());
                        productImage.setsLocation("/" + finalGalleryImagePath);
                        try {
                        	
                        	if(productImage.getId()==0)
                        		{
                        		System.out.println("ImageUpdate");
                        		//storeService.updateProductImage(productImage);
                        		}
                        	else
                        		{
                        		System.out.println("image created");
                        		
                        		//storeService.createProductImage(productImage);
                        		}
                        	
                        	message.setId(product.getId().toString());
                    		message.setMessage("Product updated successfully.");
                    		if(errors.size()>0)
                    		message.setErrors(errors);
                    		mav.addObject(MESSAGE, message);

                        } catch (Exception exp) {
                            mav.addObject(ERROR, "Could not add product image");
                            return mav;

                        }

                    } catch (IOException ioe) {
                    }
                }
        		
        		
        	}else {
        		message.setId(productId.toString());
        		message.setMessage("Product updated successfully.");
        		if(errors.size()>0)
            	message.setErrors(errors);
        		mav.addObject(MESSAGE, message);
        		}

        	
        } catch (Exception e) {
        	logger.error("IOException", e);
        	error = error.generateError(1004);
            mav.addObject(ERROR, error);
            return mav;
    		}
        
        return mav;
    }


    
    /**
     * Service to add Product Option
     *
     * @param user_name
     * @param api_key
     * @param product_id
     * @param option_title
     * @param option_quantity
     * @param avaliable_quantity
     * @param shipping_quantity
     * @return Add Product Option Status String
     */
    @RequestMapping(value = "/add-productOption", method = RequestMethod.POST)
    public ModelAndView addProductOption(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "product_id", required = false) String productId,
            @RequestParam(value = "sku_id", required = false) String skuId,
            @RequestParam(value = "option_title", required = false) String optionTitle,
            @RequestParam(value = "option_name", required = false) String optionName,
            @RequestParam(value = "option_quantity", required = false) String optionQuantity,
            @RequestParam(value = "stock_on_options", required = false) boolean  stockOnOptions
             )
    		 {
    	
    	ProductOption productOption = new ProductOption();
        ModelAndView mav = new ModelAndView();
        Message message = new Message();
        Error error = new Error();
        User user = null;
        Long prodId;
        Integer optionQuntity;

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
        
        if(StringUtils.isEmpty(productId) ){
        	missingParamList.add("product_id");
        }
        if(productId.length() > 10 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
       
        
        if(StringUtils.isEmpty(optionTitle) ){
        	missingParamList.add("option_title");
        }
        if(optionTitle.length() > 100 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
  
        if(StringUtils.isEmpty(optionName) ){
        	missingParamList.add("option_name");
        }
        if(optionName.length() > 100 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        if(optionQuantity.length() > 8 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        if (StringUtils.isEmpty(optionQuantity)) optionQuantity = "0";
        
        try {
        	prodId = Long.parseLong(productId);
    		} catch (NumberFormatException nfe) {
    			mav.addObject(ERROR, error.generateError(3001));
    			return mav;
    		}

        try {
        	optionQuntity = Integer.parseInt(optionQuantity);
        	} catch (NumberFormatException nfe) {
                mav.addObject(ERROR, error.generateError(3009));
                return mav;
            }


    	try {
            // get user
    		user = (User) key_response.get(USER);
    		
            Product product = null;
            // fetch product 
            product = storeService.findProduct(prodId);
            
            
           if(stockOnOptions){
            	product.setbUseOptions(stockOnOptions);
            	product.setbStockControl(false);
            	storeService.updateProduct(product);
            }

        	if (product == null) {
        		mav.addObject(ERROR, error.generateError(3005));
        		return mav;
                }
        	
        	if(product.getUserId().longValue() != user.getId().longValue()){
        		mav.addObject(ERROR, error.generateError(3010));
        		return mav;
        	}
        	

        	// add product option images 
        	productOption.setProductId(prodId);
        	if (skuId != null && !skuId.equals(""))
        		productOption.setsSaleLabel(skuId);
            productOption.setsTitle(optionTitle);
            productOption.setsName(optionName);
            productOption.setiAvailableQuantity(optionQuntity);
            
            
            Long productOptionId = storeService.createProductOption(productOption);
            if (productOptionId != null) {
            	message.setId(productOption.getId().toString());
            	message.setMessage("Product option added successfully");
            	mav.addObject(MESSAGE, message);
            	} else {
                    mav.addObject(ERROR, error.generateError(3011));
                }

            } catch (Exception e) {
            	error = error.generateError(1004);
                mav.addObject(ERROR, error);
                return mav;
            }

            return mav;
        
    }


    /**
     * Service to update Product Option
     *
     * @param user_name
     * @param api_key
     * @param product_option_id
     * @param option_title
     * @param option_quantity
     * @param avaliable_quantity
     * @param shipping_quantity
     * @return Add Product Option Status String
     */
    @RequestMapping(value = "/update-productOption", method = RequestMethod.POST)
    public ModelAndView updateProductOption(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "product_option_id", required = false) String productOptionId,
            @RequestParam(value = "option_title", required = false) String optionTitle,
            @RequestParam(value = "option_name", required = false) String optionName,
            @RequestParam(value = "option_quantity", required = false) String optionQuantity,
            @RequestParam(value = "sku_id", required = false) String skuId) {
        ModelAndView mav = new ModelAndView();
        Message message = new Message();
        Error error = new Error();

        User user = null;
        Long prodOptionId = null;
        Integer optionQuntity;

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
        
        if(StringUtils.isEmpty(productOptionId) ){
        	missingParamList.add("product_option_id");
        }
        if(productOptionId.length() > 10 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(StringUtils.isEmpty(optionTitle) ){
        	missingParamList.add("option_title");
        }
        if(optionTitle.length() > 100 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
  
        if(StringUtils.isEmpty(optionName) ){
        	missingParamList.add("option_name");
        }
        if(optionName.length() > 100 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        if(optionQuantity.length() > 8 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        if (StringUtils.isEmpty(optionQuantity)) optionQuantity = "0";
        
        try {
        	prodOptionId = Long.parseLong(productOptionId);
    		} catch (NumberFormatException nfe) {
    			mav.addObject(ERROR, error.generateError(3012));
    			return mav;
    		}

        try {
        	optionQuntity = Integer.parseInt(optionQuantity);
        	} catch (NumberFormatException nfe) {
                mav.addObject(ERROR, error.generateError(3009));
                return mav;
            }


    	try {
            // get user
    		user = (User) key_response.get(USER);
    		

    		ProductOption productOption = null;
            
            // fetch product 
    		productOption = storeService.findProductOption(prodOptionId);

        	if (productOption == null) {
        		mav.addObject(ERROR, error.generateError(3013));
        		return mav;
                }
        	Long productId = productOption.getProductId();
        	Product product = storeService.findProduct(productId);
        	if(product.getUserId().longValue() != user.getId().longValue()){
        		mav.addObject(ERROR, error.generateError(3014));
        		return mav;
        	}

        	// add product option images 
        	if (skuId != null && !skuId.equals(""))
        		productOption.setsSaleLabel(skuId);
            productOption.setsTitle(optionTitle);
            productOption.setsName(optionName);
            productOption.setiAvailableQuantity(optionQuntity);
            
            
            productOption = storeService.updateProductOption(productOption);
            /*clear cache*/
            CouchBaseCacheManager.deleteObject(apiKey);
            if (productOption != null) {
            	message.setId(productOption.getId().toString());
            	message.setMessage("Product option updated successfully.");
            	mav.addObject(MESSAGE, message);
            	} else {
            		mav.addObject(ERROR, error.generateError(3024));
                }

            } catch (Exception e) {
            	error = error.generateError(1004);
                mav.addObject(ERROR, error);
                return mav;
            }

            return mav;
    }


    /**
     * Service to delete Product Option
     *
     * @param user_name
     * @param api_key
     * @param product_option_id
     * @return Delete Product Option Status String
     */
    @RequestMapping(value = "/delete-productOption", method = RequestMethod.DELETE)
    public ModelAndView deleteProductOption(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "product_option_id", required = false) String productOptionId) {
        ModelAndView mav = new ModelAndView();
        Message message = new Message();
        Error error = new Error();

        User user = null;
        Long prodOptionId = null;

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
        
        if(StringUtils.isEmpty(productOptionId) ){
        	missingParamList.add("product_option_id");
        }
        if(productOptionId.length() > 10 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        try {
        	prodOptionId = Long.parseLong(productOptionId);
    		} catch (NumberFormatException nfe) {
    			mav.addObject(ERROR, error.generateError(3012));
    			return mav;
    		}

        try {
            // get user
    		user = (User) key_response.get(USER);
    		

    		ProductOption productOption = null;
            
            // fetch product 
    		productOption = storeService.findProductOption(prodOptionId);

        	if (productOption == null) {
        		mav.addObject(ERROR, error.generateError(3013));
        		return mav;
                }
        	Long productId = productOption.getProductId();
        	Product product = storeService.findProduct(productId);
        	if(product.getUserId().longValue() != user.getId().longValue()){
        		mav.addObject(ERROR, error.generateError(3014));
        		return mav;
        	}

        		storeService.deleteProductOption(productOption);
            	message.setMessage("Product option deleted successfully.");
            	mav.addObject(MESSAGE, message);
        } catch (Exception e) {
            	error = error.generateError(1004);
                mav.addObject(ERROR, error);
                return mav;
        }

            return mav;
    }

    

    
    /**
     * Service to add Product Image
     *
     * @param user_name
     * @param api_key
     * @param product_id
     * @return Add Product Image Status String
     */
    @RequestMapping(value = "/add-product-image", method = RequestMethod.POST)
    public ModelAndView addStoreProductImage(
    		HttpServletRequest request,
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "product_id", required = false) String productId,
            @RequestParam(value = "product_image_url", required = false) String productImageUrl,
            @RequestParam(value = "product_image_path", required = false) MultipartFile productImagePath) {
        ModelAndView mav = new ModelAndView();
        Message message = new Message();
        Error error = new Error();
        List<Error> errors = new ArrayList<Error>();
        User user = null;
        Long prodId;
        Long productImageId;
        ProductImage productImage = null;
        
        
        
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
        
        if(StringUtils.isEmpty(productId) ){
        	missingParamList.add("product_id");
        }
        if(productId.length() > 10 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(StringUtils.isEmpty(productImageUrl) && productImagePath == null){
        	missingParamList.add("product_image_url or product_image_path");
        }
  
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        try {
        	prodId = Long.parseLong(productId);
    		} catch (NumberFormatException nfe) {
    			mav.addObject(ERROR, error.generateError(3001));
    			return mav;
    		}
    		
    	
    		
    	
    	
        try {
			// get user
    		user = (User) key_response.get(USER);
    		
    		Product product = storeService.findProduct(prodId);
    		if(product == null){
    			mav.addObject(ERROR, error.generateError(3005));
    			return mav;
    		}
		
    		if(product.getUserId().longValue() != user.getId().longValue()){
    			mav.addObject(ERROR, error.generateError(3010));
    			return mav;
    		}

    		if( ! StringUtils.isEmpty(productImageUrl)){
    			
    			if( ! productImageUrl.startsWith("http://")){
    				mav.addObject(ERROR, error.generateError(3015));
    				return mav;
    	    	}
    			if(!(productImageUrl.endsWith("png") || productImageUrl.endsWith("jpg") || productImageUrl.endsWith("jpeg")
    	    			|| productImageUrl.endsWith("bmp") || productImageUrl.endsWith("gif"))){
    	    		mav.addObject(ERROR, error.generateError(3016));
    	    		return mav;
    	    	}
    			
    			PathLocator pathLocator = new PathLocator();
    			//change to online server for the time being
    			String rootPath = pathLocator.getContextPath();

    			String mobicartImagesFolderPath = "mobicartimages";
    			FileUtils.makeDirectoryIfItsNotThere(rootPath + mobicartImagesFolderPath);

    			String productFolderPath = "/productimages";
    			FileUtils.makeDirectoryIfItsNotThere(rootPath + mobicartImagesFolderPath + productFolderPath);

    			//hard code
    			String storeFolderPath = "/"+product.getStoreId();
    			FileUtils.makeDirectoryIfItsNotThere(rootPath + mobicartImagesFolderPath + productFolderPath + storeFolderPath);

    			String dateWiseFolderPath = "/" + DateTimeUtils.getCurrentDate();
    			FileUtils.makeDirectoryIfItsNotThere(rootPath + mobicartImagesFolderPath + productFolderPath + storeFolderPath + dateWiseFolderPath);

    			String finalGalleryImagePath = mobicartImagesFolderPath + productFolderPath + storeFolderPath + dateWiseFolderPath;

    			int fileNameIndex = productImageUrl.lastIndexOf("/");
    			String fileName = productImageUrl.substring(fileNameIndex);
        
    			String productImageFileName = FileUtils.cleanSpecialChars(fileName);
    			String mediumGalleryImagePath = rootPath + finalGalleryImagePath + "/" + FileUtils.stuffedFilename(productImageFileName, "_medium");
    			String smallGalleryImagePath = rootPath + finalGalleryImagePath + "/" + FileUtils.stuffedFilename(productImageFileName, "_small");

    			finalGalleryImagePath += "/"+ productImageFileName;
    			
    			URL urlOfImage = null;
    			File outputFile = null;
    			
    			BufferedInputStream in = null;
    			FileOutputStream out = null;
    			try {
    				outputFile = new File(rootPath + finalGalleryImagePath);
    				urlOfImage = new URL(productImageUrl);
								
    				in = new BufferedInputStream(urlOfImage.openStream());
    				BufferedImage bufferedGalleryImage = ImageIO.read(in);
    				out = new FileOutputStream(outputFile);
    				String format = (productImageUrl.endsWith(".png")) ? "png" : "jpg";
    				ImageIO.write(bufferedGalleryImage, format, out);
    				
    				BufferedImage bufferedMediumProductImage = ImageUtils.resize(bufferedGalleryImage, 130, 150, true);
    				BufferedImage bufferedSmallProductImage = ImageUtils.resize(bufferedGalleryImage, 60, 60, true);
        	
    				ImageUtils.saveImage(bufferedMediumProductImage, mediumGalleryImagePath);
    				ImageUtils.saveImage(bufferedSmallProductImage, smallGalleryImagePath);
    				
    				in.close();
    				out.close();
    				
    				productImage = new ProductImage();
    				productImage.setProductId(prodId);
    				productImage.setsTitle(FileUtils.getJustTheFileName(productImageUrl));
    				productImage.setsLocation("/" + finalGalleryImagePath);
    				try {
    					productImageId = storeService.createProductImage(productImage);
    					message.setId(productImageId.toString());
    					message.setMessage("Product image added successfully.");
    					mav.addObject(MESSAGE, message);
    				} catch (Exception exp) {
    					logger.error("while saving", exp);
    					error = error.generateError(1004);
    					mav.addObject(ERROR, error);
    					return mav;
    				}
    			} catch (MalformedURLException e) {
    				logger.error("Wrong URL", e);
    				mav.addObject(ERROR, error.generateError(3017));
    				return mav;
    			} catch (Exception e) {
				logger.error("IOException", e);
				error = error.generateError(1004);
	            mav.addObject(ERROR, error);
	            return mav;
			}
			finally{
            	if (in != null) in = null;
            	if (out != null) out = null;
	        }
        }
    		else if(productImagePath != null){
    		if (productImagePath != null && productImagePath.getSize() > 0) {
                String contentType = productImagePath.getContentType();
                if ((contentType.equals("image/png")) || contentType.equals("image/jpg") || contentType.equals("image/jpeg") || contentType.equals("image/bmp") || contentType.equals("image/gif")) {
                } else {
                	error = error.generateError(3016);
    				errors.add(error);
                }

                try {
                    MultipartFile productImageFile = productImagePath;
                    
                    PathLocator pathLocator = new PathLocator();
                    // change to online server for the time being
                    String rootPath = pathLocator.getContextPath();

                    String mobicartImagesFolderPath = "mobicartimages";
                    FileUtils.makeDirectoryIfItsNotThere(rootPath
                            + mobicartImagesFolderPath);

                    String productFolderPath = "/productimages";
                    FileUtils.makeDirectoryIfItsNotThere(rootPath
                            + mobicartImagesFolderPath + productFolderPath);

                    //hard code
                    String storeFolderPath = "/"+product.getStoreId();
                    FileUtils.makeDirectoryIfItsNotThere(rootPath
                            + mobicartImagesFolderPath + productFolderPath
                            + storeFolderPath);

                    String dateWiseFolderPath = "/" + DateTimeUtils.getCurrentDate();
                    FileUtils.makeDirectoryIfItsNotThere(rootPath
                            + mobicartImagesFolderPath + productFolderPath
                            + storeFolderPath + dateWiseFolderPath);

                    String finalGalleryImagePath = mobicartImagesFolderPath
                            + productFolderPath + storeFolderPath + dateWiseFolderPath;

                    String productImageFileName = FileUtils.cleanSpecialChars(productImageFile.getOriginalFilename());
                    String mediumGalleryImagePath = rootPath + finalGalleryImagePath + "/" + FileUtils.stuffedFilename(productImageFileName, "_medium");
                    String smallGalleryImagePath = rootPath + finalGalleryImagePath + "/" + FileUtils.stuffedFilename(productImageFileName, "_small");

                    finalGalleryImagePath += "/"
                            + productImageFileName;


                    InputStream productInputStream = null;
                    OutputStream productOutputStream = null;
                    if (productImageFile.getSize() > 0) {
                        productInputStream = productImageFile.getInputStream();
                        BufferedImage bufferedGalleryImage = ImageIO
                                .read(productInputStream);
                        productOutputStream = new FileOutputStream(rootPath
                                + finalGalleryImagePath);
                        String format = (productImageFile.getOriginalFilename()
                                .endsWith(".png")) ? "png" : "jpg";
                        ImageIO.write(bufferedGalleryImage, format, productOutputStream);
                        //resize image
                        BufferedImage bufferedMediumProductImage = ImageUtils.resize(bufferedGalleryImage, 130, 150, true);
                        BufferedImage bufferedSmallProductImage = ImageUtils.resize(bufferedGalleryImage, 60, 60, true);

                        ImageUtils.saveImage(bufferedMediumProductImage, mediumGalleryImagePath);
                        ImageUtils.saveImage(bufferedSmallProductImage, smallGalleryImagePath);

                        productInputStream.close();
                        productOutputStream.close();
                    }

                    productImage = new ProductImage();
                    productImage.setProductId(prodId);
                    productImage.setsTitle(FileUtils.getJustTheFileName(productImagePath.getOriginalFilename()));
                    productImage.setsLocation("/" + finalGalleryImagePath);
                    try {
                    	productImageId = storeService.createProductImage(productImage);
    					message.setId(productImageId.toString());
    					message.setMessage("Product image added successfully.");
    					mav.addObject(MESSAGE, message);

                    } catch (Exception exp) {
                        mav.addObject(ERROR, "Could not add product image");
                        return mav;

                    }

                } catch (IOException ioe) {
                }
            }
        	
        }
        } catch (Exception e) {
        	logger.error("Exception", e);
        	error = error.generateError(1004);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        return mav;
    }    
   

    
    /**
     * Service to add Product Image
     *
     * @param user_name
     * @param api_key
     * @param product_id
     * @return Add Product Image Status String
     */
    @RequestMapping(value = "/update-product-image", method = RequestMethod.POST)
    public ModelAndView updateStoreProductImage(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "product_image_id", required = false) String productImageId,
            @RequestParam(value = "product_image_url", required = false) String productImageUrl,
            @RequestParam(value = "product_image_path", required = false) MultipartFile productImagePath) {
        ModelAndView mav = new ModelAndView();
        Message message = new Message();
        Error error = new Error();
        List<Error> errors = new ArrayList<Error>();
        User user = null;
        Long prodImageId = null;
        ProductImage productImage = null;
        
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
        
        if(StringUtils.isEmpty(productImageId) ){
        	missingParamList.add("product_image_id");
        }
        if(productImageId.length() > 10 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(StringUtils.isEmpty(productImageUrl) && productImagePath == null){
        	missingParamList.add("product_image_url or product_image_path");
        }
  
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        try {
        	prodImageId = Long.parseLong(productImageId);
    		} catch (NumberFormatException nfe) {
    			mav.addObject(ERROR, error.generateError(3018));
    			return mav;
    		}
    		
    	
        try {
			// get user
    		user = (User) key_response.get(USER);
    		
    		
    		productImage = storeService.findProductImage(prodImageId);
    		if(productImage == null){
        		mav.addObject(ERROR, error.generateError(3019));
        		return mav;
    		}
    		
    		Long productId = productImage.getProductId();
    		Product product = storeService.findProduct(productId);
    		if(product.getUserId().longValue() != user.getId().longValue()){
        		mav.addObject(ERROR, error.generateError(3020));
        		return mav;
    		}

    		if( ! StringUtils.isEmpty(productImageUrl)){
    			if( ! productImageUrl.startsWith("http://")){
    				mav.addObject(ERROR, error.generateError(3015));
    				return mav;
    	    	}
    	    		
    	    	if(!(productImageUrl.endsWith("png") || productImageUrl.endsWith("jpg") || productImageUrl.endsWith("jpeg")
    	    			|| productImageUrl.endsWith("bmp") || productImageUrl.endsWith("gif"))){
    	    		mav.addObject(ERROR, error.generateError(3016));
    	    		return mav;
    	    	}
    			PathLocator pathLocator = new PathLocator();
    			// change to online server for the time being
    			String rootPath = pathLocator.getContextPath();
    		
    			String mobicartImagesFolderPath = "mobicartimages";
    			FileUtils.makeDirectoryIfItsNotThere(rootPath+ mobicartImagesFolderPath);

    			String productFolderPath = "/productimages";
    			FileUtils.makeDirectoryIfItsNotThere(rootPath + mobicartImagesFolderPath + productFolderPath);

    			//hard code
    			String storeFolderPath = "/"+product.getStoreId();
    			FileUtils.makeDirectoryIfItsNotThere(rootPath+ mobicartImagesFolderPath + productFolderPath + storeFolderPath);

    			String dateWiseFolderPath = "/" + DateTimeUtils.getCurrentDate();
    			FileUtils.makeDirectoryIfItsNotThere(rootPath + mobicartImagesFolderPath + productFolderPath + storeFolderPath + dateWiseFolderPath);

    			String finalGalleryImagePath = mobicartImagesFolderPath + productFolderPath + storeFolderPath + dateWiseFolderPath;

    			int fileNameIndex = productImageUrl.lastIndexOf("/");
    			String fileName = productImageUrl.substring(fileNameIndex);
        
    			String productImageFileName = FileUtils.cleanSpecialChars(fileName);
    			String mediumGalleryImagePath = rootPath + finalGalleryImagePath + "/" + FileUtils.stuffedFilename(productImageFileName, "_medium");
    			String smallGalleryImagePath = rootPath + finalGalleryImagePath + "/" + FileUtils.stuffedFilename(productImageFileName, "_small");
    			
    			finalGalleryImagePath += "/"+ productImageFileName;
    			
    			URL urlOfImage = null;
    			File outputFile = null;
    			
    			BufferedInputStream in = null;
    			FileOutputStream out = null;
    			try {
    				outputFile = new File(rootPath + finalGalleryImagePath);
    				urlOfImage = new URL(productImageUrl);
								
    				in = new BufferedInputStream(urlOfImage.openStream());
    				BufferedImage bufferedGalleryImage = ImageIO.read(in);
    				out = new FileOutputStream(outputFile);
    				String format = (productImageUrl.endsWith(".png")) ? "png" : "jpg";
    				ImageIO.write(bufferedGalleryImage, format, out);
    				
    				BufferedImage bufferedMediumProductImage = ImageUtils.resize(bufferedGalleryImage, 130, 150, true);
    				BufferedImage bufferedSmallProductImage = ImageUtils.resize(bufferedGalleryImage, 60, 60, true);
        	
    				ImageUtils.saveImage(bufferedMediumProductImage, mediumGalleryImagePath);
    				ImageUtils.saveImage(bufferedSmallProductImage, smallGalleryImagePath);
        	
    				in.close();
    				out.close();
    				
    				productImage.setsTitle(FileUtils.getJustTheFileName(productImageUrl));
    				productImage.setsLocation("/" + finalGalleryImagePath);
    				try {
    					storeService.updateProductImage(productImage);
    					/*clear cache*/
    		            CouchBaseCacheManager.deleteObject(apiKey);
    					message.setId(productImage.getId().toString());
    					message.setMessage("Product image updated successfully.");
    					mav.addObject(MESSAGE, message);
    				} catch (Exception exp) {
    					logger.error("Something went wrong", exp);
    					error = error.generateError(1004);
    					mav.addObject(ERROR, error);
    					return mav;
    				}
    			} catch (MalformedURLException e) {
    				logger.error("Something went wrong", e);
    				mav.addObject(ERROR, error.generateError(3017));
    				return mav;
    			} catch (Exception e) {
    				logger.error("Something went wrong", e);
    				error = error.generateError(1004);
    				mav.addObject(ERROR, error);
    				return mav;
    			}
    			finally{
    				if (in != null) in = null;
    				if (out != null) out = null;
    			}
    		}
    		
    		else if(productImagePath != null){
        		if (productImagePath != null && productImagePath.getSize() > 0) {
                    String contentType = productImagePath.getContentType();
                    if ((contentType.equals("image/png")) || contentType.equals("image/jpg") || contentType.equals("image/jpeg") || contentType.equals("image/bmp") || contentType.equals("image/gif")) {
                    } else {
                    	error = error.generateError(3016);
        				errors.add(error);
                    }

                    try {
                        MultipartFile productImageFile = productImagePath;
                        
                        PathLocator pathLocator = new PathLocator();
                        // change to online server for the time being
                        String rootPath = pathLocator.getContextPath();

                        String mobicartImagesFolderPath = "mobicartimages";
                        FileUtils.makeDirectoryIfItsNotThere(rootPath
                                + mobicartImagesFolderPath);

                        String productFolderPath = "/productimages";
                        FileUtils.makeDirectoryIfItsNotThere(rootPath
                                + mobicartImagesFolderPath + productFolderPath);

                        //hard code
                        String storeFolderPath = "/"+product.getStoreId();
                        FileUtils.makeDirectoryIfItsNotThere(rootPath
                                + mobicartImagesFolderPath + productFolderPath
                                + storeFolderPath);

                        String dateWiseFolderPath = "/" + DateTimeUtils.getCurrentDate();
                        FileUtils.makeDirectoryIfItsNotThere(rootPath
                                + mobicartImagesFolderPath + productFolderPath
                                + storeFolderPath + dateWiseFolderPath);

                        String finalGalleryImagePath = mobicartImagesFolderPath
                                + productFolderPath + storeFolderPath + dateWiseFolderPath;

                        String productImageFileName = productImageFile.getOriginalFilename();
                        String mediumGalleryImagePath = rootPath + finalGalleryImagePath + "/" + FileUtils.stuffedFilename(productImageFileName, "_medium");
                        String smallGalleryImagePath = rootPath + finalGalleryImagePath + "/" + FileUtils.stuffedFilename(productImageFileName, "_small");

                        finalGalleryImagePath += "/"+ productImageFileName;


                        InputStream productInputStream = null;
                        OutputStream productOutputStream = null;
                        if (productImageFile.getSize() > 0) {
                            productInputStream = productImageFile.getInputStream();
                            BufferedImage bufferedGalleryImage = ImageIO
                                    .read(productInputStream);
                            productOutputStream = new FileOutputStream(rootPath
                                    + finalGalleryImagePath);
                            String format = (productImageFile.getOriginalFilename()
                                    .endsWith(".png")) ? "png" : "jpg";
                            ImageIO.write(bufferedGalleryImage, format, productOutputStream);
                            //resize image
                            BufferedImage bufferedMediumProductImage = ImageUtils.resize(bufferedGalleryImage, 130, 150, true);
                            BufferedImage bufferedSmallProductImage = ImageUtils.resize(bufferedGalleryImage, 60, 60, true);

                            ImageUtils.saveImage(bufferedMediumProductImage, mediumGalleryImagePath);
                            ImageUtils.saveImage(bufferedSmallProductImage, smallGalleryImagePath);

                            productInputStream.close();
                            productOutputStream.close();
                        }

                        
                        productImage.setsTitle(productImageFile.getOriginalFilename());
                        productImage.setsLocation("/" + finalGalleryImagePath);
                        try {
                        	storeService.updateProductImage(productImage);
        					message.setId(prodImageId.toString());
        					message.setMessage("Product image updated successfully.");
        					mav.addObject(MESSAGE, message);

                        } catch (Exception exp) {
                            mav.addObject(ERROR, "Could not add product image");
                            return mav;

                        }

                    } catch (IOException ioe) {
                    }
                }
            	
            }
        } catch (Exception e) {
        	logger.error("Something has gone wrong", e);
        	error = error.generateError(1004);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        return mav;
    }    
   

    
    /**
     * Service to delete Product Image
     *
     * @param user_name
     * @param api_key
     * @param product_image_id
     * @return Delete Product Image Status String
     */
    @RequestMapping(value = "/delete-product-image", method = RequestMethod.DELETE)
    public ModelAndView deleteStoreProductImage(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "product_image_id", required = false) String productImageId) {
        ModelAndView mav = new ModelAndView();
        Message message = new Message();
        Error error = new Error();
        User user = null;
        Long prodImageId;
        ProductImage productImage = null;
        
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
        
        if(StringUtils.isEmpty(productImageId) ){
        	missingParamList.add("product_image_id");
        }
        if(productImageId.length() > 10 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        try {
        	prodImageId = Long.parseLong(productImageId);
    		} catch (NumberFormatException nfe) {
    			mav.addObject(ERROR, error.generateError(3018));
    			return mav;
    		}
    		
    	
			// get user
    		user = (User) key_response.get(USER);
    		
    		
    		productImage = storeService.findProductImage(prodImageId);
    		if(productImage == null){
        		mav.addObject(ERROR, error.generateError(3019));
        		return mav;
    		}
    		
    		Long productId = productImage.getProductId();
    		Product product = storeService.findProduct(productId);
    		if(product.getUserId().longValue() != user.getId().longValue()){
        		mav.addObject(ERROR, error.generateError(3020));
        		return mav;
    		}

    	try {
    		storeService.deleteProductImage(productImage);
    		message.setMessage("Product image deleted successfully.");
    		mav.addObject(MESSAGE, message);
    		return mav;
    	} catch (Exception exp) {
    		logger.error("Something went wrong", exp);
    		error = error.generateError(1004);
    		mav.addObject(ERROR, error);
    		return mav;
    	}     
        
    }    
   


    
    
    /**
     * Service to upload Products to Store
     *
     * @param user_name
     * @param api_key
     * @return List of Products under store in JSON or XML
     */
    @RequestMapping(value = "/products-csv-upload", method = RequestMethod.POST)
    public ModelAndView addProductsThroughCSV(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "store_id", required = false) String storeId,
            @RequestParam(value = "product_file", required = false) MultipartFile productFile) {
    	
    	ModelAndView mav = new ModelAndView();
        Message message = new Message();
        Error error = new Error();
        Long streId = null;
        User user = null;
        Product product = new Product();
        List<Error> errors = new ArrayList<Error>();
        
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
        
        if(productFile == null || productFile.isEmpty()){
        	missingParamList.add("product_file");
        }
  
        if(missingParamList.size()>0){
        	error = error.generateError(1001);
        	error.appendToMessage(missingParamList.toString());
            mav.addObject(ERROR, error);
            return mav;
        }

    	// get user
		user = (User) key_response.get(USER);
		
		try {
        	streId = Long.parseLong(storeId);
        	} catch (NumberFormatException nfe) {
        		mav.addObject(ERROR, error.generateError(2001));
        		return mav;
        	}
            
    	Store store = null;
    	try{
    		store = storeService.findStoreById(streId);
        	}catch(Exception e){
                mav.addObject(ERROR, error.generateError(1004));
                return mav;
            }
        if(store == null){
        	mav.addObject(ERROR, error.generateError(2003));
        	return mav;
            }
        if(store.getUserId().longValue() != user.getId().longValue()){
        	mav.addObject(ERROR, error.generateError(2004));
        	return mav;
            }
        
        long restLimit = 0;
        try{
        	Long diskPricingId = user.getDiskSpacePricingId();
        	DiskSpacePricing diskSpacePricing = userService.findDiskSpacePricingById(diskPricingId);
        	long maxLimit = diskSpacePricing.getiMaxLimit();
        	long productCount = user.getiProductCount();
        	restLimit = maxLimit - productCount;
        	
            }catch(Exception e){
                mav.addObject(ERROR, error.generateError(1004));
                return mav;
            }
            
        if(restLimit <= 0){
        	mav.addObject(ERROR, error.generateError(3023));
        	return mav;
        	}
        
        File csvProductFile = null;
        try{
        	csvProductFile = File.createTempFile( Long.toString(System.nanoTime()),"temp.csv");
        	productFile.transferTo(csvProductFile);
        }catch(IOException ioe){
        	productFile = null;
        	csvProductFile = null;
        	logger.error("Error", ioe);
            mav.addObject(ERROR, error.generateError(3025));
            return mav;
        }
        
        final CellProcessor[] processors = new CellProcessor[] {
        		new StrMinMax(0, 20),
        	    //new StrMinMax(0, 20),
        	    new StrMinMax(0, 50),
        	    new StrMinMax(0, 200),
        	    new StrMinMax(0, 10),
        	    new StrMinMax(0, 10),
        	    new StrMinMax(0, 10),
        	    new StrMinMax(0, 20),
        	    new StrMinMax(0, 10),
        	    new StrMinMax(0, 10),
        	    new StrMinMax(0, 10)
        };
        
        ICsvBeanReader csvProductReader = null;
        int counter = 1;
        int addedProduct = 0;
        
        try {
        	csvProductReader = new CsvBeanReader(new FileReader(csvProductFile), CsvPreference.EXCEL_PREFERENCE);
        	final String[] header = csvProductReader.getCSVHeader(true);
            ProductBean productBean;
            List <Long> addedProductIdList = new ArrayList<Long>();
            
            while( (productBean = csvProductReader.read(ProductBean.class, header, processors)) != null
					&& addedProduct <= restLimit) {
            	if(addedProduct >= restLimit){
            		error = error.generateError(3023);
            		error.appendToMessage(" Line no. - "+counter);
            		errors.add(error);
					mav.addObject(ERROR, errors);
					break;
            	}
            	
            	if(user.getStoreProductLimit() <= user.getiProductCount()){
            		error = error.generateError(3023);
            		error.appendToMessage(" Line no. - "+counter);
            		errors.add(error);
					mav.addObject(ERROR, errors);
					break;
                }
				
				String departmentId = productBean.getDepartmentId();
				if(StringUtils.isEmpty(departmentId)){
					error = error.generateError(1001);
					error.appendToMessage(" Line no. - "+counter);
					errors.add(error);
					counter++;
					continue;
				}
				/*String categoryId = productBean.getSubDeparmentId();
				if(StringUtils.isEmpty(categoryId)){
					categoryId = "0";
				}*/
				
				String productName = productBean.getProductName();
				if(StringUtils.isEmpty(productName)){
					error = error.generateError(1001);
					error.appendToMessage(" Line no. - "+counter);
					errors.add(error);
					counter++;
					continue;
				}
				String productDescription = productBean.getProductDescription();
				String productPrice = productBean.getProductPrice();
				if(StringUtils.isEmpty(productPrice)){
					error = error.generateError(1001);
					error.appendToMessage(" Line no. - "+counter);
					errors.add(error);
					counter++;
					continue;
				}
				
				String productStatus = productBean.getProductStatus();
				if(StringUtils.isEmpty(productStatus)){
					productStatus = "active";
				}else if( ! (productStatus.equalsIgnoreCase("active") || productStatus.equalsIgnoreCase("hidden") 
						|| productStatus.equalsIgnoreCase("sold") || productStatus.equalsIgnoreCase("coming"))){
					error = error.generateError(3021);
					error.appendToMessage(" Line no. - "+counter);
					errors.add(error);
					counter++;
					continue;
				}
				
				String productDiscount = productBean.getProductDiscount();
				if(StringUtils.isEmpty(productDiscount)){
					productDiscount = "0";
				}
				String productSKU = productBean.getProductSKU();
				String isFeaturedProduct = productBean.getIsFeaturedProduct();
				if(StringUtils.isEmpty(isFeaturedProduct)){
					isFeaturedProduct = "0";
				}
				String aggregateQuantity = productBean.getAggregateQuantity();
				if(StringUtils.isEmpty(aggregateQuantity)){
					aggregateQuantity = "0";
				}
				String useProductOption = productBean.getUseProductOption();
				if(StringUtils.isEmpty(useProductOption)){
					useProductOption = "0";
				}
				
				Long depttId = null;
				//Long ctgryId = null;
				BigDecimal price = null;
				BigDecimal discount = null;
				Integer isFturdProduct = null;
				Integer aggteQuantity = null;
				Integer usePrductOptin = null;
				try{
					depttId = Long.parseLong(departmentId);
				}catch(NumberFormatException nfe){
					error = error.generateError(6002);
					error.appendToMessage(" Line no. - "+counter);
					errors.add(error);
					counter++;
					continue;
				}
				/*try{
					ctgryId = Long.parseLong(categoryId);
				}catch(NumberFormatException nfe){
					error = error.generateError(7002);
					error.appendToMessage(" Line no. - "+counter);
					errors.add(error);
					counter++;
					continue;
				}*/
				try{
					price = new BigDecimal(productPrice);
				}catch(NumberFormatException nfe){
					error = error.generateError(3006);
					error.appendToMessage(" Line no. - "+counter);
					errors.add(error);
					counter++;
					continue;
				}
				try{
					discount = new BigDecimal(productDiscount);
				}catch(NumberFormatException nfe){
					error = error.generateError(3007);
					error.appendToMessage(" Line no. - "+counter);
					errors.add(error);
					counter++;
					continue;
				}
				try {
					isFturdProduct = Integer.parseInt(isFeaturedProduct);
		        	if (!(isFturdProduct == 0 || isFturdProduct == 1)) {
		        		error = error.generateError(3022);
		        		error.appendToMessage(" Line no. - "+counter);
		        		errors.add(error);
						counter++;
						continue;
		            	}
		            } catch (NumberFormatException nfe) {
		            	error = error.generateError(3022);
		            	error.appendToMessage(" Line no. - "+counter);
		            	errors.add(error);
						counter++;
						continue;
		            }
				
				try{
					aggteQuantity = Integer.parseInt(aggregateQuantity);
				}catch(NumberFormatException nfe){
					error = error.generateError(3008);
					error.appendToMessage(" Line no. - "+counter);
					errors.add(error);
					counter++;
					continue;
				}
				
				
				try {
					usePrductOptin = Integer.parseInt(useProductOption);
		        	if (!(usePrductOptin == 0 || usePrductOptin == 1)) {
		        		error = error.generateError(3030);
		        		error.appendToMessage(" Line no. - "+counter);
		        		errors.add(error);
						counter++;
						continue;
		            	}
		            } catch (NumberFormatException nfe) {
		            	error = error.generateError(3030);
		            	error.appendToMessage(" Line no. - "+counter);
		            	errors.add(error);
						counter++;
						continue;
		            }
				
				Department department = storeService.findDepartment(depttId);
				List<Category> depttCategories = storeService.findActiveCategoriesByDepartment(depttId);
				Category category = null;
				/*if(ctgryId != 0){
					category = storeService.findCategory(ctgryId);
				}*/
				if(department == null){
					error = error.generateError(6003);
					error.appendToMessage(" Line no. - "+counter);
					errors.add(error);
					counter++;
					continue;
				}else if(department.getUserId() != user.getId().longValue()){
					error = error.generateError(6007);
					error.appendToMessage(" Line no. - "+counter);
					errors.add(error);
					counter++;
					continue;
				}/*else if(ctgryId != 0 && category == null){
					error = error.generateError(7003);
					error.appendToMessage(" Line no. - "+counter);
					counter++;
					errors.add(error);
					continue;
				}else if(ctgryId != 0 && category.getDepartmentId().longValue() != depttId.longValue()){
					error = error.generateError(7005);
					error.appendToMessage(" Line no. - "+counter);
					counter++;
					errors.add(error);
					continue;
				}else if(ctgryId != 0 && category.getCategories().size() > 0){
					error = error.generateError(7006);
					error.appendToMessage(" Line no. - "+counter);
					counter++;
					errors.add(error);
					continue;
				}*/else if(depttCategories != null && depttCategories.size() > 0){
					error = error.generateError(3026);
					error.appendToMessage(" Line no. - "+counter);
					counter++;
					errors.add(error);
					continue;
				}
				
				product.reset();
				product.setUserId(user.getId());
				product.setStoreId(streId);
				App app = appService.findAppByUser(user);
				product.setAppId(app.getId());
				product.setDepartmentId(depttId);
				//product.setCategoryId(ctgryId);
				product.setsName(productName);
				product.setsDescription(productDescription);
				product.setfPrice(price);
				product.setsStatus(productStatus);
				product.setfDiscount(discount);
				product.setsSaleLabel(productSKU);
				if(isFturdProduct == 1)
					product.setbFeatured(true);
				else 
					product.setbFeatured(false);
				product.setiAggregateQuantity(aggteQuantity);
				if(usePrductOptin == 1){
					product.setbUseOptions(true);
					product.setbStockControl(false);
				}
				else{
					product.setbUseOptions(false);
					product.setbStockControl(true);
				}
				
				Long productCount = storeService.findProductsCountByDepartmentAndName(product.getsName(), product.getDepartmentId());
	        	if(productCount.longValue() > 0){
	        		error = error.generateError(3029);
					error.appendToMessage(" Line no. - "+counter);
					errors.add(error);
					counter++;
					continue;
	        	}
				
				
				Long addedProductId = storeService.createProduct(product);
				
			counter++;
			addedProduct++;
			addedProductIdList.add(addedProductId);
			continue;	
			}
            
            message.setId(addedProductIdList.toString());
            message.setMessage(addedProduct + " Product added successfully.");
        	mav.addObject(MESSAGE, message);
        	csvProductFile.delete();
        	csvProductReader.close();
        
    	} catch(SuperCSVException e){
    		logger.error("SuperCSVException", e);
    		message.setMessage(e.getMessage());
    		mav.addObject(MESSAGE, message);
    	}catch(IOException e){
    		logger.error("IOException", e);
    		message.setMessage(e.getMessage());
    		mav.addObject(MESSAGE, message);
    	}catch(SQLException e){
    		logger.error("SQLException", e);
    		error = error.generateError(1004);
    		mav.addObject(ERROR, errors);
    	}catch(Exception e){
    		logger.error("Exception", e);
    		error = error.generateError(1004);
    		mav.addObject(ERROR, errors);
    	}finally{
    		csvProductFile.delete();
    		if(csvProductReader!=null){
    			try{
    			csvProductReader.close();}
    			catch (Exception e) {
    				csvProductReader = null;
				}
    		}
    		
    	}
    	if(errors != null && errors.size() > 0){
        	message.setErrors(errors);
        	
        }
        return mav;
    }
    
    
    
    
  /**
  * Service to get Products under Store as CSV
  *
  * @param user_name
  * @param api_key
  * @param store_id
  * @return List of Products under store in CSV
  */

 @RequestMapping(value = "/product-csv", method = RequestMethod.GET)
 public String getProductsForStoreCSV(ModelMap modelMap, HttpServletRequest
         request, HttpServletResponse response,
                                      @RequestParam(value = "user_name", required = false) String userName,
                                      @RequestParam(value = "api_key", required = false) String apiKey,
                                      @RequestParam(value = "store_id", required = false) String storeId) {

     List<Product> products = null;
     List<Store> stores = null;
     ModelAndView mav = new ModelAndView();
     Error error = new Error();
     Long streId;
     User user = null;
     response.setContentType("application/vnd.ms-csv");
     response.setHeader("Content-Disposition", "attachment;filename=products.csv");
     PrintWriter writer = null;
     try {
         writer = response.getWriter();
     } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
     }

     if (userName == null || userName.equals("") || apiKey == null || apiKey.equals("") || storeId == null || storeId.equals("")) {
         error = new Error(1001, Error.MISSING_PARAMETERS);
         mav.addObject(ERROR, error);
         writer.write("1001, Error.MISSING_PARAMETERS");
         writer.flush();
         writer.close();

         return "";
     }else {
         try {
             storeId = storeId.trim();
             streId = Long.parseLong(storeId);
         } catch (NumberFormatException nfe) {
             error = new Error(2001, "StoreId must be numeric.");
             mav.addObject(ERROR, error);
             writer.write("2001,StoreId must be numeric.");
             writer.flush();
             writer.close();

             return "";

         }

         try {
             // validate key
             Map<String, Object> key_response = apiBO.validateApiKey(apiKey, userName);

             if (!(Boolean) key_response.get(VALID)) {
                 error = (Error) key_response.get(ERROR);// get error
                 mav.addObject(ERROR, error);
                 writer.write("1002,Authentication Failed");
                 writer.flush();
                 writer.close();

                 return "";
             }

             // get user
             user = (User) key_response.get(USER);

             // validate user
             if (user == null) {
                 error = error.generateError(1002);
                 mav.addObject(ERROR, error);
                 writer.write("1002,Authentication Failed");
                 writer.flush();
                 writer.close();

                 return "";

             }


             try {

                 // fetch products for Store
                 stores = storeService.findAPIStoresByUserId(user.getId());
                // products = storeService.findAPIProductsByStore(streId);
                 
                int productsCounts= storeService.findProductCountByStore(streId);
     			if(productsCounts>user.getStoreProductLimit()){
     				Pager page=new Pager();
     				page.setPage(0);
     				page.setResults(user.getStoreProductLimit()); 
     				products = storeService.findAPIProductsByStore(streId,page);
     				
     			}else
                 products = storeService.findAPIProductsByStore(streId);
             } catch (SQLException e) {
                 String errorMessage = " No Product found within the Store with StoreId " + streId;
                 error.setMessage(errorMessage);
                 mav.addObject(ERROR, error);
                 writer.write("1002,No Product found within the Store with StoreId " + streId);
                 writer.flush();
                 writer.close();

                 return "";


             }
             if (products != null && products.size() > 0) {
                 if (!String.valueOf(user.getId()).equalsIgnoreCase(
                         String.valueOf(products.get(0).getUserId()))) {
                     error = error.generateError(1002);// return error message
                     mav.addObject(ERROR, error);
                     writer.write("1002,No Product found within the Store with StoreId " + streId);
                     writer.flush();
                     writer.close();

                     return "";

                 }
             } else {
                 String errorMessage = " No Product found within the Store with StoreId " + streId;
                 error.setMessage(errorMessage);
                 mav.addObject(ERROR, error);
                 writer.write("1002,No Product found within the Store with StoreId " + streId);
                 writer.flush();
                 writer.close();

                 return "";
             }

         } catch (Exception e) {
             error = error.generateError(1002);
             mav.addObject(ERROR, error);
             writer.write("1002, Error While Exporting to CSV");
             writer.flush();
             writer.close();

             return "";

         }
         List<ProductApi> productsApiList = new ArrayList<ProductApi>();
         for (Product product : products) {
             ProductApi productApi = new ProductApi();
             productApi.setProductId(product.getId());
             productApi.setProductName(product.getsName());
             productApi.setProductDescription(product.getsDescription());
             productApi.setDepartmentId(product.getDepartmentId());
             productApi.setDepartmentName(product.getDepartmentName());
             productApi.setSubDepartmentId(product.getCategoryId());
             productApi.setSubDepartmentName(product.getCategoryName());
             productApi.setProductPrice(product.getfPrice());
             productApi.setProductStatus(product.getsStatus());
             productApi.setProductDiscount(product.getfDiscount());
             productApi.setProductSKU(product.getsSaleLabel());
             productApi.setIsFeaturedProduct(product.getbFeatured());
             productApi.setAggregateQuantity(product.getiAggregateQuantity());
             productApi.setUseStockQuantityOnOptions(product.getbUseOptions());
             productApi.setUseStockQuantityOnProduct(!product.getbUseOptions());
            // productApi.setUseStockQuantityOnProduct(product.getbStockControl());
             List<ProductOptionApi> productOptionApiList = new ArrayList<ProductOptionApi>();
             if (product.getProductOptions() != null) {
                 for (ProductOption productOption : product.getProductOptions()) {
                     ProductOptionApi productOptionApi = new ProductOptionApi();
                     productOptionApi.setOptionId(productOption.getId());
                     productOptionApi.setOptionName(productOption.getsName());
                     productOptionApi.setQuantityInStock(productOption.getiAvailableQuantity());
                     productOptionApiList.add(productOptionApi);

                 }
             }
             productApi.setProductOptions(productOptionApiList);

             List<ProductImageApi> productImageApiList = new ArrayList<ProductImageApi>();
             if (product.getProductImages() != null) {
                 for (ProductImage productImage : product.getProductImages()) {
                     ProductImageApi productImageApi = new ProductImageApi();
                     productImageApi.setImageId(productImage.getId());
                     productImageApi.setLocation(productImage.getsLocation());
                     productImageApi.setTitle(productImage.getsTitle());
                     productImageApi.setLocationMedium(productImage.getsLocationMedium());
                     productImageApi.setLocationSmall(productImage.getsLocationSmall());
                     productImageApiList.add(productImageApi);
                 }
             }
             productApi.setProductImages(productImageApiList);

             List<ProductReviewApi> productReviewApiList = new ArrayList<ProductReviewApi>();
             if (product.getProductReviews() != null) {
                for (ProductReview productReview : product.getProductReviews()) {
                     ProductReviewApi producReviewApi = new ProductReviewApi();
                     producReviewApi.setReviewId(productReview.getId());
                     producReviewApi.setReviewerName(productReview.getsReveiwerName());
                     producReviewApi.setReviewerEmail(productReview.getsReviewerEmail());
                     producReviewApi.setReviewerRating(productReview.getiRating());
                     producReviewApi.setReviewerComment(productReview.getsReview());
                     productReviewApiList.add(producReviewApi);
                }
             }
             productApi.setProductReviews(productReviewApiList);

             productsApiList.add(productApi);
         }
         MainProductApi mainProduct = new MainProductApi();
         mainProduct.setProducts(productsApiList);
         mainProduct.setStoreId(stores.get(0).getId());
         mainProduct.setStoreName(stores.get(0).getsSName());
         mav.addObject(PRODUCTS, mainProduct);

         Iterator<ProductApi> itr = productsApiList.iterator();
         ProductApi productApic = new ProductApi();


         writer.write("DepartmentId , ProductName, ProductDescription, ProductPrice, ProductStatus, ProductDiscount, ProductSKU, isFeaturedProduct, AggregateQuantity, UseProductOption");
         writer.append('\n');
         while (itr.hasNext()) {

             productApic = (ProductApi) itr.next();
             writer.append(productApic.getDepartmentId() != null ? productApic.getDepartmentId().toString() : "");
             /*writer.append(',');
             writer.append(productApic.getSubDeparmentId() != null ? productApic.getSubDeparmentId().toString() : "");*/
             writer.append(',');
             writer.append(productApic.getProductName() != null ? productApic.getProductName().toString() : "");
             writer.append(',');
             writer.append(productApic.getProductDescription());
             writer.append(',');
             writer.append(productApic.getProductPrice() != null ? productApic.getProductPrice().toString() : "");
             writer.append(',');
             writer.append(productApic.getProductStatus());
             writer.append(',');
             writer.append(productApic.getProductDiscount() != null ? productApic.getProductDiscount().toString() : "");
             writer.append(',');
             writer.append(productApic.getProductSKU());
             writer.append(',');
             writer.append(productApic.getIsFeaturedProduct() != null ? productApic.getIsFeaturedProduct().toString() : "");
             writer.append(',');
             writer.append(productApic.getAggregateQuantity() != null ? productApic.getAggregateQuantity().toString() : "");
             writer.append(',');
             writer.append(productApic.getUseStockQuantityOnOptions() != null ? productApic.getUseStockQuantityOnOptions().toString() : "");
             writer.append('\n');
         }
         writer.append('\n');
         writer.flush();
         writer.close();

         return "";
     }

 }
   
 
 
@RequestMapping(value = "/store-product-template", method = RequestMethod.GET)
public void getProductsForStoreCSVEmpty(ModelMap modelMap, HttpServletRequest
       request, HttpServletResponse response ) {

   response.setContentType("application/vnd.ms-csv");
   response.setHeader("Content-Disposition", "attachment;filename=products.csv");
   PrintWriter writer = null;
   try {
       writer = response.getWriter();
   } catch (IOException e) {
       e.printStackTrace();
   }

   writer.write("DepartmentId, ProductName, ProductDescription, ProductPrice, ProductStatus, ProductDiscount, ProductSKU, isFeaturedProduct, AggregateQuantity, UseProductOption");
   writer.append('\n');
   writer.flush();
   writer.close();

   
}
 
}
