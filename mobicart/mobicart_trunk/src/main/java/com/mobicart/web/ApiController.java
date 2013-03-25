package com.mobicart.web;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
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
import com.mobicart.dto.IphoneLabelsDto;
import com.mobicart.dto.LabelsDto;
import com.mobicart.model.Address;
import com.mobicart.model.App;
import com.mobicart.model.Category;
import com.mobicart.model.Department;
import com.mobicart.model.DiskSpacePricing;
import com.mobicart.model.GalleryImage;
import com.mobicart.model.Labels;
import com.mobicart.model.Product;
import com.mobicart.model.ProductBean;
import com.mobicart.model.ProductImage;
import com.mobicart.model.ProductOption;
import com.mobicart.model.ProductOrder;
import com.mobicart.model.ProductOrderItem;
import com.mobicart.model.ProductOrderShippingDetail;
import com.mobicart.model.ProductReview;
import com.mobicart.model.Shipping;
import com.mobicart.model.State;
import com.mobicart.model.Store;
import com.mobicart.model.Tax;
import com.mobicart.model.Territory;
import com.mobicart.model.User;
import com.mobicart.model.api.CategoryApi;
import com.mobicart.model.api.DepartmentApi;
import com.mobicart.model.api.ImageApi;
import com.mobicart.model.api.MainCategoryApi;
import com.mobicart.model.api.MainDepartmentApi;
import com.mobicart.model.api.MainImageApi;
import com.mobicart.model.api.MainOrderApi;
import com.mobicart.model.api.MainProductApi;
import com.mobicart.model.api.ProductApi;
import com.mobicart.model.api.ProductImageApi;
import com.mobicart.model.api.ProductOptionApi;
import com.mobicart.model.api.ProductOrderApi;
import com.mobicart.model.api.ProductOrderItemApi;
import com.mobicart.model.api.ProductReviewApi;
import com.mobicart.model.api.ShippingApi;
import com.mobicart.model.api.StateApi;
import com.mobicart.model.api.StoreApi;
import com.mobicart.model.api.TaxApi;
import com.mobicart.model.api.TerritoryApi;
import com.mobicart.model.api.UserApi;
import com.mobicart.service.AdminService;
import com.mobicart.service.AppService;
import com.mobicart.service.LabelService;
import com.mobicart.service.StoreService;
import com.mobicart.service.user.UserService;
import com.mobicart.util.DateTimeUtils;
import com.mobicart.util.FileUtils;
import com.mobicart.util.ImageUtils;
import com.mobicart.util.PathLocator;
import com.mobicart.util.ResourceProperties;


/**
 * @author jasdeep.singh
 */

@Controller
@RequestMapping("/apddddi/**")
public class ApiController {

    @Autowired
    StoreService storeService;

    @Autowired
    AppService appService;

    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ApiBO apiBO;
    
	@Autowired
    LabelService labelService;

    private static final String PRODUCTS = "products";
    private static final String STORE_DEPARTMENTS = "DepartmentList";
    private static final String DEPARTMENT_CATEGORIES = "CategoryList";
    private static final String HOME_GALLERY_IMAGES = "GalleryImageList";
    private static final String STORE = "store";
    private static final String COUNTRIES = "countries";
    private static final String STATES = "states";
    private static final String PRODUCT_ORDER = "order-details";
    private static final String APP_MERCHANT = "merchant";
    private static final String PRODUCT_ORDER_LIST = "OrderList";
    private static final String PRODUCT_ORDER_LIST_SIZE = "product-order-list-size";
    private static final String SHIPPING = "Shipping";
   

    private static final String ERROR = "error";
    private static final String ERRORS = "errors";
    private static final String MESSAGE = "message";


    private static final String VALID = "valid";
    private static final String USER = "user";
//	private static final String ERROR = "error";
    private static final String LABEL = "Labels";


    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory
            .getLogger(ApiController.class);

/*******************************PRODUCT SERVICES START************************************/


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

			products = storeService.findAPIProductsByStore(streId);
			
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
                productApi.setUseStockQuantityOnProduct(product.getbStockControl());
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
        } else {
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
                productApi.setUseStockQuantityOnProduct(product.getbStockControl());
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


            writer.write("Department Id , SubDeparment Id, Product Name, Product Description, Product Price, Product Status, Product Discount, Product SKU, Is Featured Product, Aggregate Quantity");
            writer.append('\n');
            while (itr.hasNext()) {

                productApic = (ProductApi) itr.next();
                writer.append(productApic.getDepartmentId() != null ? productApic.getDepartmentId().toString() : "");
                writer.append(',');
                writer.append(productApic.getSubDepartmentId() != null ? productApic.getSubDepartmentId().toString() : "");
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
                writer.append('\n');
            }
            writer.append('\n');
            writer.flush();
            writer.close();

            return "";
        }

    }


    @RequestMapping(value = "/store-product-template", method = RequestMethod.GET)
    public String getProductsForStoreCSVEmpty(ModelMap modelMap, HttpServletRequest
            request, HttpServletResponse response
    ) {

        response.setContentType("application/vnd.ms-csv");
        response.setHeader("Content-Disposition", "attachment;filename=products.csv");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        writer.write(" Department Id , Sub-Deparment Id, Product Name, Product Description, Product Price, Product Status, Product Discount, Product SKU, Is Featured Product, Aggregate Quantity");
        writer.append('\n');
        writer.flush();
        writer.close();

        return "";
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
        	csvProductFile = new File("/tmp/csvFile.csv");
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
        	    new StrMinMax(0, 20),
        	    new StrMinMax(0, 50),
        	    new StrMinMax(0, 200),
        	    new StrMinMax(0, 10),
        	    new StrMinMax(0, 10),
        	    new StrMinMax(0, 10),
        	    new StrMinMax(0, 20),
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
				
				String departmentId = productBean.getDepartmentId();
				if(StringUtils.isEmpty(departmentId)){
					error = error.generateError(1001);
					error.appendToMessage(" Line no. - "+counter);
					errors.add(error);
					counter++;
					continue;
				}
				String categoryId = null;///productBean.getSubDepartmentId();
				if(StringUtils.isEmpty(categoryId)){
					categoryId = "0";
				}
				
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
				
				Long depttId = null;
				Long ctgryId = null;
				BigDecimal price = null;
				BigDecimal discount = null;
				Integer isFturdProduct = null;
				Integer aggteQuantity = null;
				try{
					depttId = Long.parseLong(departmentId);
				}catch(NumberFormatException nfe){
					error = error.generateError(6002);
					error.appendToMessage(" Line no. - "+counter);
					errors.add(error);
					counter++;
					continue;
				}
				try{
					ctgryId = Long.parseLong(categoryId);
				}catch(NumberFormatException nfe){
					error = error.generateError(7002);
					error.appendToMessage(" Line no. - "+counter);
					errors.add(error);
					counter++;
					continue;
				}
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
				
				Department department = storeService.findDepartment(depttId);
				List<Category> depttCategories = storeService.findActiveCategoriesByDepartment(depttId);
				Category category = null;
				if(ctgryId != 0){
					category = storeService.findCategory(ctgryId);
				}
				if(department == null){
					error = error.generateError(6003);
					error.appendToMessage(" Line no. - "+counter);
					errors.add(error);
					counter++;
					continue;
				}else if(department.getUserId().longValue() != user.getId().longValue()){
					error = error.generateError(6007);
					error.appendToMessage(" Line no. - "+counter);
					errors.add(error);
					counter++;
					continue;
				}else if(ctgryId != 0 && category == null){
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
				}else if(depttCategories != null && depttCategories.size() > 0){
					error = error.generateError(3026);
					error.appendToMessage(" Line no. - "+counter);
					counter++;
					errors.add(error);
					continue;
				}
				
				Product product = new Product();
				product.setUserId(user.getId());
				product.setStoreId(streId);
				App app = appService.findAppByUser(user);
				product.setAppId(app.getId());
				product.setDepartmentId(depttId);
				product.setCategoryId(ctgryId);
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
    		csvProductReader = null;
    	}
    	if(errors != null && errors.size() > 0){
        	message.setErrors(errors);
        	
        }
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
    			if(department.getUserId().longValue() != user.getId().longValue()){
    				mav.addObject(ERROR, error.generateError(6007));
    				return mav;
            	}

    			products = storeService.findProductsByDepartment(depttId);
    			//System.out.println(products.size());
    			
            	if (products == null || products.size() <= 0) {
            		mav.addObject(ERROR, error.generateError(6003));
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
                productApi.setSubDepartmentName(product.getCategoryName());
                productApi.setProductPrice(product.getfPrice());
                productApi.setProductStatus(product.getsStatus());
                productApi.setProductDiscount(product.getfDiscount());
                productApi.setProductSKU(product.getsSaleLabel());
                productApi.setIsFeaturedProduct(product.getbFeatured());
                productApi.setAggregateQuantity(product.getiAggregateQuantity());
                productApi.setUseStockQuantityOnOptions(product.getbUseOptions());
                productApi.setUseStockQuantityOnProduct(product.getbStockControl());

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
        Category category = null;
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
    			// get user
                user = (User) key_response.get(USER);
              
                category = storeService.findCategory(ctgryId);
                if(category == null){
    				mav.addObject(ERROR, error.generateError(7003));
    				return mav;
                }
                
                Long depttId = category.getDepartmentId();
                Department department = storeService.findDepartment(depttId);
                
    			if(department.getUserId().longValue() != user.getId().longValue()){
    				mav.addObject(ERROR, error.generateError(7004));
    				return mav;
            	}

    			products = storeService.findProductsByCategory(ctgryId);
    			//System.out.println(products.size());
    			
            	if (products == null || products.size() <= 0) {
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
                productApi.setUseStockQuantityOnProduct(product.getbStockControl());

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
            mainProduct.setDepartmentId(category.getDepartmentId());
            mainProduct.setSubDepartmentId(category.getId());
            mainProduct.setSubDepartmentName(category.getsName());
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
                
    			if(department.getUserId().longValue() != user.getId().longValue()){
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
            productApi.setAggregateQuantity(product.getiAggregateQuantity());
            productApi.setUseStockQuantityOnOptions(product.getbUseOptions());
            productApi.setUseStockQuantityOnProduct(product.getbStockControl());
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
    @RequestMapping(value = "/delete-product", method = RequestMethod.DELETE)
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
                
    			if(department.getUserId().longValue() != user.getId().longValue()){
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
     * @param product_url
     * @param video_title
     * @param sale_label
     * @param video_url
     * @param aggregate_quantity
     * @param purchase_count
     * @param taxable
     * @param featured
     * @return Add Product Status String
     */
//    @RequestMapping(value = "/add-deptt-product", method = RequestMethod.POST)
//    public ModelAndView addDepartmentProduct(
//            @RequestParam(value = "user_name", required = false) String userName,
//            @RequestParam(value = "api_key", required = false) String apiKey,
//            @RequestParam(value = "department_id", required = false) String departmentId,
//            @RequestParam(value = "category_id", required = false) String categoryId,
//            @RequestParam(value = "product_name", required = false) String productName,
//            @RequestParam(value = "product_description", required = false) String productDescription,
//            @RequestParam(value = "status", required = false) String status,
//            @RequestParam(value = "f_price", required = false) String fPrice,
//            @RequestParam(value = "discount", required = false) String discount,
//            @RequestParam(value = "sale_label", required = false) String saleLabel,
//            @RequestParam(value = "product_url", required = false) MultipartFile productUrl,
//            @RequestParam(value = "video_url", required = false) String videoUrl,
//            @RequestParam(value = "aggregate_quantity", required = false) String aggregateQuantity,
//            @RequestParam(value = "featured", required = false) String featured) {
//        Product product = new Product();
//        ModelAndView mav = new ModelAndView();
//        Message message = new Message();
//        Error error = new Error();
//
//        User user = null;
//        Long depttId;
//        Long ctgryId;
//        Double price;
//        Double dscount;
//        Long aggrtQuntity;
//        Integer ftured;
//        ProductImage productImage = null;
//        Long prodId;
//        Long productImageId;
//
//        if (userName == null || userName.equals("") || apiKey == null || apiKey.equals("") || departmentId == null || departmentId.equals("")
//                || productName == null || productName.equals("") || status == null || status.equals("")) {
//
//            error = new Error(1001, Error.MISSING_PARAMETERS);
//            mav.addObject(ERROR, error);
//            return mav;
//        } else {
//            if (categoryId == null || categoryId.equals("")) categoryId = "0";
//            if (fPrice == null || fPrice.equals("")) fPrice = "0.0";
//            if (discount == null || discount.equals("")) discount = "0";
//            if (aggregateQuantity == null || aggregateQuantity.equals("")) aggregateQuantity = "0";
//            try {
//                depttId = Long.parseLong(departmentId);
//            } catch (NumberFormatException nfe) {
//                error = new Error(6002, "DepartmentId must be numeric.");
//                mav.addObject(ERROR, error);
//                return mav;
//            }
//            try {
//                if (!categoryId.equals("0")) {
//                    try {
//                        Category cat = storeService.findCategory(Long.parseLong(categoryId));
//                        if (cat == null) {
//                            error = new Error(7002, "Sub-Department does not exists.");
//                            mav.addObject(ERROR, error);
//                            return mav;
//                        }
//                    } catch (Exception exp) {
//                        error = new Error(7002, "Sub-Department does not exists.");
//                        mav.addObject(ERROR, error);
//                        return mav;
//                    }
//                }
//
//                ctgryId = Long.parseLong(categoryId);
//            } catch (NumberFormatException nfe) {
//                error = new Error(7002, "Sub-DepartmentId must be numeric.");
//                mav.addObject(ERROR, error);
//                return mav;
//            }
//
//            try {
//                price = Double.parseDouble(fPrice);
//            } catch (NumberFormatException nfe) {
//                error = new Error(3006, "Price must be a Decimal Number.");
//                mav.addObject(ERROR, error);
//                return mav;
//            }
//
//            try {
//                dscount = Double.parseDouble(discount);
//                ;
//            } catch (NumberFormatException nfe) {
//                error = new Error(3007, "Discount must be a Decimal Number.");
//                mav.addObject(ERROR, error);
//                return mav;
//            }
//
//            try {
//                aggrtQuntity = Long.parseLong(aggregateQuantity);
//            } catch (NumberFormatException nfe) {
//                error = new Error(3008, "Aggregate Quantity must be a Number.");
//                mav.addObject(ERROR, error);
//                return mav;
//            }
//            try {
//                ftured = Integer.parseInt(featured);
//                if (!(ftured == 0 || ftured == 1)) {
//                    error = new Error(3012, "Featured must be Either 0 Or 1.");
//                    mav.addObject(ERROR, error);
//                    return mav;
//                }
//            } catch (NumberFormatException nfe) {
//                error = new Error(3011, "Featured must be a Number Either 0 Or 1.");
//                mav.addObject(ERROR, error);
//                return mav;
//            }
//
//
//            try {
//                // validate key
//                Map<String, Object> key_response = apiBO.validateApiKey(apiKey, userName);
//
//                if (!(Boolean) key_response.get(VALID)) {
//                    error = (Error) key_response.get(ERROR);// get error
//                    mav.addObject(ERROR, error);
//                    return mav;
//                }
//
//                // get user
//                user = (User) key_response.get(USER);
//
//               
//                Department department = null;
//                try {
//                    // fetch department 
//                    department = storeService.findDepartment(depttId);
//                } catch (Exception e) {
//                    String errorMessage = "Department with  id " + depttId + " does not exist";
//                    error.setErrorcode(104);
//                    mav.addObject(ERROR, errorMessage);
//                    return mav;
//                }
//                if (department == null) {
//                    String errorMessage = "Department with  id " + depttId + " does not exist";
//                    error.setErrorcode(104);
//                    mav.addObject(ERROR, errorMessage);
//                    return mav;
//                }
//                // Match user's department 
//                if (!String.valueOf(user.getId()).equalsIgnoreCase(
//                        String.valueOf(department.getUserId()))) {
//                    error = error.generateError(1002);// return error message
//                    mav.addObject(ERROR, error);
//                    return mav;
//                }
//                // fetch user's Application
//                App app = storeService.findAPIAppByUser(user.getId());
//
//
//                /////////////   NEW CHANGES///////////////////////////
//                if (ctgryId != 0) {
//
//                    Category category = storeService.findCategory(ctgryId);
//
//                    if (depttId.longValue() != category.getDepartmentId().longValue()) {
//                        error = new Error(9999, "CategoryId does not belong to specified DepartmentId.");
//                        mav.addObject(ERROR, error);
//                        return mav;
//                    }
//
//                }
//
//                /////////////   NEW CHANGES///////////////////////////
//
//
//                // add product  
//                product.setAppId(app.getId());
//                product.setStoreId(department.getStoreId());
//                product.setUserId(user.getId());
//                product.setDepartmentId(depttId);
//                product.setCategoryId(ctgryId);
//                product.setsName(productName);
//                if (productDescription != null)
//                    product.setsDescription(productDescription);
//                if (status != null)
//                    product.setsStatus(status);
//                product.setfPrice(new BigDecimal(price));
//                product.setfDiscount(new BigDecimal(dscount));
//
//
//                if (videoUrl != null)
//                    product.setsVideoUrl(videoUrl);
//                if (saleLabel != null)
//                    product.setsSaleLabel(saleLabel);
//                product.setiAggregateQuantity(aggrtQuntity.intValue());
//                if (ftured == 1) product.setbFeatured(true);
//                else product.setbFeatured(false);
//
//
//                /////////////   NEW CHANGES///////////////////////////
//                //Long diskPricingId = user.getDiskSpacePricingId();
//                //DiskSpacePricing diskSpacePricing = userService.findDiskSpacePricingById(diskPricingId);
//                //long maxLimit = diskSpacePricing.getiMaxLimit();
//
//                long maxLimit = 1000;
//
//                long productCount = user.getiProductCount();
//
//                long restLimit = maxLimit - productCount;
//
//                Long productId = null;
//                if (restLimit > 0) {
//                    productId = storeService.createProduct(product);
//                } else {
//                    message.setMessage("Product could not be added as you have reached your max limit.");
//                    mav.addObject(MESSAGE, message);
//                    return mav;
//                }
//                /////////////   NEW CHANGES///////////////////////////
//
//                if (productId != null) {
//                    message.setMessage("Product added successfully");
//                    mav.addObject(MESSAGE, message);
//                } else {
//                    message.setMessage("Product could not be added successfully");
//                    mav.addObject(MESSAGE, message);
//                }
//
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                mav.addObject(ERROR, "Could not add product ");
//                return mav;
//            }
//
//            return mav;
//        }
//    }


    
    
    
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
            @RequestParam(value = "category_id", required = false) String categoryId,
            @RequestParam(value = "product_name", required = false) String productName,
            @RequestParam(value = "product_description", required = false) String productDescription,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "f_price", required = false) String fPrice,
            @RequestParam(value = "discount", required = false) String discount,
            @RequestParam(value = "product_image_url", required = false) String productImageUrl,
            @RequestParam(value = "sale_label", required = false) String saleLabel,
            @RequestParam(value = "video_url", required = false) String videoUrl,
            @RequestParam(value = "aggregate_quantity", required = false) String aggregateQuantity,
            @RequestParam(value = "featured", required = false) String featured) {
       
    	
    	Product product = new Product();
        ModelAndView mav = new ModelAndView();
        Message message = new Message();
        List<Error> errors = new ArrayList<Error>();
        Error error = new Error();
        User user = null;
        Long depttId = null;
        Long ctgryId = null;
        Double price = null;
        Double dscount = null;
        Long aggrtQuntity = null;
        Integer ftured = null;
        ProductImage productImage = null;
        Long productImageId = null;

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
        
        if(StringUtils.isEmpty(productName) ){
        	missingParamList.add("product_name");
        }
  
        if(StringUtils.isEmpty(fPrice) ){
        	missingParamList.add("f_price");
        }
        
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        
        if(StringUtils.isEmpty(categoryId) ) categoryId = "0";
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
        
    	try {
    		ctgryId = Long.parseLong(categoryId);
    		} catch (NumberFormatException nfe) {
    			mav.addObject(ERROR, error.generateError(7002));
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

        try {
        	dscount = Double.parseDouble(discount);
            } catch (NumberFormatException nfe) {
                mav.addObject(ERROR, error.generateError(3007));
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
            department = storeService.findDepartment(depttId);
            
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
        	// fetch user's Application
            App app = storeService.findAPIAppByUser(user.getId());
            
            
            /////////////   NEW CHANGES///////////////////////////
            if(ctgryId != 0){
            	Category category = storeService.findCategory(ctgryId);
            	
            	if(category == null){
            		mav.addObject(ERROR, error.generateError(7003));
            		return mav;
                	}
            	Long departId = category.getDepartmentId();
            	Department deptt = storeService.findDepartment(departId);
            	if(deptt.getUserId().longValue() != user.getId().longValue()){
            		mav.addObject(ERROR, error.generateError(7004));
            		return mav;
            	}
            	if (depttId.longValue() != category.getDepartmentId().longValue()) {
            		mav.addObject(ERROR, error.generateError(7005));
            		return mav;
            	}


            }
            

            /////////////   NEW CHANGES///////////////////////////


        	// add product  
        	product.setAppId(app.getId());
        	product.setStoreId(department.getStoreId());
        	product.setUserId(user.getId());
        	product.setDepartmentId(depttId);
        	product.setCategoryId(ctgryId);
        	product.setsName(productName);
        	if (productDescription != null)
        		product.setsDescription(productDescription);
        	if (status != null)
        		product.setsStatus(status);
        	product.setfPrice(new BigDecimal(price));
        	product.setfDiscount(new BigDecimal(dscount));
        	

        	if (videoUrl != null)
        		product.setsVideoUrl(videoUrl);
        	if (saleLabel != null)
        		product.setsSaleLabel(saleLabel);
        	product.setiAggregateQuantity(aggrtQuntity.intValue());
        	if (ftured == 1) product.setbFeatured(true);
        	else product.setbFeatured(false);


            /////////////   NEW CHANGES///////////////////////////
        	Long diskPricingId = user.getDiskSpacePricingId();
        	DiskSpacePricing diskSpacePricing = userService.findDiskSpacePricingById(diskPricingId);
        	long maxLimit = diskSpacePricing.getiMaxLimit();
    		long productCount = user.getiProductCount();
        	long restLimit = maxLimit - productCount;
        	Long productId = null;
        	if (restLimit > 0) {
        		productId = storeService.createProduct(product);
        		} else {
            		mav.addObject(ERROR, error.generateError(3023));
            		return mav;
                	}
        	/////////////   NEW CHANGES///////////////////////////
        	
			if (productId == null){
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
        			productImage.setProductId(productId);
        			productImage.setsTitle(FileUtils.getJustTheFileName(productImageUrl));
        			productImage.setsLocation("/" + finalGalleryImagePath);
        			try {
        				productImageId = storeService.createProductImage(productImage);
        				message.setId(productImageId.toString());
        				message.setMessage("Product image added successfully");
        			//	mav.addObject(MESSAGE, message);
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
        	} 

        	message.setId(productId.toString());
    		message.setMessage("Product added successfully.");
    		if(errors.size()>0)
    		message.setErrors(errors);
    		mav.addObject(MESSAGE, message);
    		
        	
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
//    @RequestMapping(value = "/update-department-product", method = RequestMethod.POST)
//    public ModelAndView updateDepartmentProduct(
//            @RequestParam(value = "user_name", required = false) String userName,
//            @RequestParam(value = "api_key", required = false) String apiKey,
//            @RequestParam(value = "product_id", required = false) String productId,
//            @RequestParam(value = "product_name", required = false) String productName,
//            @RequestParam(value = "product_description", required = false) String productDescription,
//            @RequestParam(value = "status", required = false) String status,
//            @RequestParam(value = "f_price", required = false) String fPrice,
//            @RequestParam(value = "discount", required = false) String discount,
//            @RequestParam(value = "sale_label", required = false) String saleLabel,
//            @RequestParam(value = "video_url", required = false) String videoUrl,
//            @RequestParam(value = "aggregate_quantity", required = false) String aggregateQuantity,
//            @RequestParam(value = "featured", required = false) String featured) {
//        ModelAndView mav = new ModelAndView();
//        Message message = new Message();
//        Error error = new Error();
//        User user = null;
//        Long prodId;
//        Double price;
//        Double dscount;
//        Long aggrtQuntity;
//        Integer ftured;
//
//        if (userName == null || userName.equals("") || apiKey == null || apiKey.equals("") || productId == null || productId.equals("")
//                || productName == null || productName.equals("") || status == null || status.equals("")) {
//
//            error = new Error(1001, Error.MISSING_PARAMETERS);
//            mav.addObject(ERROR, error);
//            return mav;
//        } else {
//
//            if (fPrice == null || fPrice.equals("")) fPrice = "0.0";
//            if (discount == null || discount.equals("")) discount = "0";
//            if (aggregateQuantity == null || aggregateQuantity.equals("")) aggregateQuantity = "0";
//            try {
//                prodId = Long.parseLong(productId);
//            } catch (NumberFormatException nfe) {
//                error = new Error(6002, "productId must be numeric.");
//                mav.addObject(ERROR, error);
//                return mav;
//            }
//
//            try {
//                price = Double.parseDouble(fPrice);
//            } catch (NumberFormatException nfe) {
//                error = new Error(3006, "Price must be a Decimal Number.");
//                mav.addObject(ERROR, error);
//                return mav;
//            }
//
//            try {
//                dscount = Double.parseDouble(discount);
//            } catch (NumberFormatException nfe) {
//                error = new Error(3007, "Discount must be a Decimal Number.");
//                mav.addObject(ERROR, error);
//                return mav;
//            }
//
//            try {
//                aggrtQuntity = Long.parseLong(aggregateQuantity);
//            } catch (NumberFormatException nfe) {
//                error = new Error(3008, "Aggregate Quantity must be a Number.");
//                mav.addObject(ERROR, error);
//                return mav;
//            }
//            try {
//                ftured = Integer.parseInt(featured);
//                if (!(ftured == 0 || ftured == 1)) {
//                    error = new Error(3012, "Featured must be Either 0 Or 1.");
//                    mav.addObject(ERROR, error);
//                    return mav;
//                }
//            } catch (NumberFormatException nfe) {
//                error = new Error(3011, "Featured must be a Number Either 0 Or 1.");
//                mav.addObject(ERROR, error);
//                return mav;
//            }
//
//
//            try {
//                // validate key
//                Map<String, Object> key_response = apiBO.validateApiKey(apiKey, userName);
//
//                if (!(Boolean) key_response.get(VALID)) {
//                    error = (Error) key_response.get(ERROR);// get error
//                    mav.addObject(ERROR, error);
//                    return mav;
//                }
//
//                // get user
//                user = (User) key_response.get(USER);
//
//                // validate user
//                if (user == null) {
//                    error = error.generateError(1002);
//                    mav.addObject(ERROR, error);
//                    return mav;
//                }
//
//                Product product = null;
//                try {
//                    // fetch product 
//                    product = storeService.findProduct(prodId);
//                } catch (Exception e) {
//                    String errorMessage = "Product with  id " + prodId + " does not exist";
//                    error.setErrorcode(104);
//                    error.setMessage(errorMessage);
//                    mav.addObject(ERROR, error);
//                    return mav;
//                }
//                if (product == null) {
//                    String errorMessage = "Product with  id " + prodId + " does not exist";
//                    error.setErrorcode(104);
//                    error.setMessage(errorMessage);
//                    mav.addObject(ERROR, error);
//                    return mav;
//                }
//                // Match user's department 
//                if (!String.valueOf(user.getId()).equalsIgnoreCase(
//                        String.valueOf(product.getUserId()))) {
//                    error = error.generateError(1002);// return error message
//                    mav.addObject(ERROR, error);
//                    return mav;
//                }
//
//                // update product  
//                if (productName != null)
//                    product.setsName(productName);
//                if (productDescription != null)
//                    product.setsDescription(productDescription);
//                if (status != null)
//                    product.setsStatus(status);
//                if (fPrice != null)
//                    product.setfPrice(new BigDecimal(fPrice));
//                if (discount != null)
//                    product.setfDiscount(new BigDecimal(discount));
//                //product.setsProductUrl(productUrl);
//                /*if(videoUrl != null && videoUrl.getSize() > 0){
//				String videoUrlPath = videoUrl.getOriginalFilename();
//				if(!(videoUrlPath.contains("www.youtube.com"))){
//					error = new Error(3014, "We accept only youtube URL.");
//					mav.addObject(ERROR, error);
//					return mav;
//				}
//				product.setsProductUrl(videoUrlPath);
//			}*/
//                if (videoUrl != null)
//                    product.setsVideoUrl(videoUrl);
//                if (saleLabel != null)
//                    product.setsSaleLabel(saleLabel);
//                product.setiAggregateQuantity(aggrtQuntity.intValue());
//                if (ftured == 1) product.setbFeatured(true);
//                else product.setbFeatured(false);
//
//                try {
//                    storeService.updateProduct(product);
//                    message.setMessage("Product updated successfully");
//                    mav.addObject(MESSAGE, message);
//
//                } catch (Exception e) {
//                    mav.addObject(ERROR, "Could not update product ");
//                    return mav;
//
//                }
//
//
//            } catch (Exception e) {
//
//                mav.addObject(ERROR, "Could not update product ");
//                return mav;
//            }
//
//            return mav;
//        }
//    }


    
    
    
    
    
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
            @RequestParam(value = "sale_label", required = false) String saleLabel,
            @RequestParam(value = "video_url", required = false) String videoUrl,
            @RequestParam(value = "aggregate_quantity", required = false) String aggregateQuantity,
            @RequestParam(value = "featured", required = false) String featured) {

    	
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
        
        if(StringUtils.isEmpty(productName) ){
        	missingParamList.add("product_name");
        }
  
        if(StringUtils.isEmpty(fPrice) ){
        	missingParamList.add("f_price");
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
        	prodId = Long.parseLong(productId);
        	} catch (NumberFormatException nfe) {
                mav.addObject(ERROR, error.generateError(3001));
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

        try {
        	dscount = Double.parseDouble(discount);
            } catch (NumberFormatException nfe) {
                mav.addObject(ERROR, error.generateError(3007));
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
        	if (productDescription != null)
        		product.setsDescription(productDescription);
        	if (status != null)
        		product.setsStatus(status);
        	product.setfPrice(new BigDecimal(price));
        	product.setfDiscount(new BigDecimal(dscount));
        	if (videoUrl != null)
        		product.setsVideoUrl(videoUrl);
        	if (saleLabel != null)
        		product.setsSaleLabel(saleLabel);
        	product.setiAggregateQuantity(aggrtQuntity.intValue());
        	if (ftured == 1) product.setbFeatured(true);
        	else product.setbFeatured(false);

        	storeService.updateProduct(product);
        	
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
        				storeService.createProductImage(productImage);
        				message.setId(productImage.getId().toString());
        				message.setMessage("Product image added successfully.");
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
        	} else {
        		message.setMessage("Product could not be updated.");
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
     * Service to add Product Image
     *
     * @param user_name
     * @param api_key
     * @param product_id
     * @return Add Product Image Status String
     */
//    @RequestMapping(value = "/add-productImage", method = RequestMethod.POST)
//    public ModelAndView addProductImage(
//            @RequestParam(value = "user_name", required = false) String userName,
//            @RequestParam(value = "api_key", required = false) String apiKey,
//            @RequestParam(value = "product_id", required = false) String productId,
//            @RequestParam(value = "product_url", required = false) MultipartFile productUrl) {
//        ModelAndView mav = new ModelAndView();
//        Message message = new Message();
//        Error error = new Error();
//
//        User user = null;
//        Long prodId;
//        Long productImageId;
//        ProductImage productImage = null;
//
//        
//        Boolean authParamsEmpty=false;
//        
//        if(StringUtils.isEmpty(userName)){
//        	authParamsEmpty=true;
//        }
//        
//        if(StringUtils.isEmpty(apiKey)){
//        	authParamsEmpty=true;
//        }
//        
//        if(authParamsEmpty){
//        	error = new Error(1001, Error.MISSING_AUTH_PARAMETERS);
//            mav.addObject(ERROR, error);
//            return mav;
//        }
//        
//        List<String> missingParamList=new ArrayList<String>();
//        
//        if(StringUtils.isEmpty(productId) ){
//        	missingParamList.add("product_id");
//        }
//        
//        if(productUrl==null){
//        	missingParamList.add("product_url");
//        }
//        
//        if(missingParamList.size()>0){
//        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
//            mav.addObject(ERROR, error);
//            return mav;
//        }
//        
//        
//        if (userName == null || userName.equals("") || apiKey == null || apiKey.equals("") || productId == null || productId.equals("")) {
//            error = new Error(1001, Error.MISSING_PARAMETERS);
//            mav.addObject(ERROR, error);
//            return mav;
//        } else {
//
//
//            try {
//                // validate key
//                Map<String, Object> key_response = apiBO.validateApiKey(apiKey, userName);
//
//                if (!(Boolean) key_response.get(VALID)) {
//                    error = (Error) key_response.get(ERROR);// get error
//                    mav.addObject(ERROR, error);
//                    return mav;
//                }
//
//                // get user
//                user = (User) key_response.get(USER);
//
//                // validate user
//                if (user == null) {
//                    error = error.generateError(1002);
//                    mav.addObject(ERROR, error);
//                    return mav;
//                }
//                try {
//                    prodId = Long.parseLong(productId);
//                } catch (NumberFormatException nfe) {
//                    error = new Error(6002, "productId must be numeric.");
//                    mav.addObject(ERROR, error);
//                    return mav;
//                }
//
//                Product product = null;
//                try {
//                    // fetch product 
//                    product = storeService.findProduct(prodId);
//                } catch (Exception e) {
//                    String errorMessage = "Product with  id " + prodId + " does not exist";
//                    error.setErrorcode(104);
//                    error.setMessage(errorMessage);
//                    mav.addObject(ERROR, error);
//                    return mav;
//                }
//                if (product == null) {
//                    String errorMessage = "Product with  id " + prodId + " does not exist";
//                    error.setErrorcode(104);
//                    error.setMessage(errorMessage);
//                    mav.addObject(ERROR, error);
//                    return mav;
//                }
//
//                // add product option images 
//                if (productUrl != null && productUrl.getSize() > 0) {
//                    String productUrlPath = productUrl.getOriginalFilename();
//                    String contentType = productUrl.getContentType();
//                    if ((contentType.equals("image/png")) || contentType.equals("image/jpg") || contentType.equals("image/jpeg") || contentType.equals("image/bmp") || contentType.equals("image/gif")) {
//                    } else {
//                        error = new Error(3013, "Given File is not an Image.");
//                        mav.addObject(ERROR, error);
//                        return mav;
//                    }
//
//                    try {
//                        MultipartFile productImageFile = productUrl;
//                        if (productImageFile == null) {
//                            System.out.println("User Did not upload file");
//                        } else {
//                            System.out.println("Uploaded File Name is :"
//                                    + productImageFile.getOriginalFilename());
//                        }
//
//                        PathLocator pathLocator = new PathLocator();
//                        // change to online server for the time being
//                        String rootPath = pathLocator.getContextPath();
//
//                        String mobicartImagesFolderPath = "mobicartimages";
//                        FileUtils.makeDirectoryIfItsNotThere(rootPath
//                                + mobicartImagesFolderPath);
//
//                        String productFolderPath = "/productimages";
//                        FileUtils.makeDirectoryIfItsNotThere(rootPath
//                                + mobicartImagesFolderPath + productFolderPath);
//
//                        //hard code
//                        String storeFolderPath = "/1";
//                        FileUtils.makeDirectoryIfItsNotThere(rootPath
//                                + mobicartImagesFolderPath + productFolderPath
//                                + storeFolderPath);
//
//                        String dateWiseFolderPath = "/" + DateTimeUtils.getCurrentDate();
//                        FileUtils.makeDirectoryIfItsNotThere(rootPath
//                                + mobicartImagesFolderPath + productFolderPath
//                                + storeFolderPath + dateWiseFolderPath);
//
//                        String finalGalleryImagePath = mobicartImagesFolderPath
//                                + productFolderPath + storeFolderPath + dateWiseFolderPath;
//
//                        String productImageFileName = FileUtils.cleanSpecialChars(productImageFile.getOriginalFilename());
//                        String mediumGalleryImagePath = rootPath + finalGalleryImagePath + "/" + FileUtils.stuffedFilename(productImageFileName, "_medium");
//                        String smallGalleryImagePath = rootPath + finalGalleryImagePath + "/" + FileUtils.stuffedFilename(productImageFileName, "_small");
//
//                        finalGalleryImagePath += "/"
//                                + productImageFileName;
//
//
//                        InputStream productInputStream = null;
//                        OutputStream productOutputStream = null;
//                        if (productImageFile.getSize() > 0) {
//                            productInputStream = productImageFile.getInputStream();
//                            BufferedImage bufferedGalleryImage = ImageIO
//                                    .read(productInputStream);
//                            productOutputStream = new FileOutputStream(rootPath
//                                    + finalGalleryImagePath);
//                            String format = (productImageFile.getOriginalFilename()
//                                    .endsWith(".png")) ? "png" : "jpg";
//                            ImageIO.write(bufferedGalleryImage, format, productOutputStream);
//                            //resize image
//                            BufferedImage bufferedMediumProductImage = ImageUtils.resize(bufferedGalleryImage, 130, 150, true);
//                            BufferedImage bufferedSmallProductImage = ImageUtils.resize(bufferedGalleryImage, 60, 60, true);
//
//                            ImageUtils.saveImage(bufferedMediumProductImage, mediumGalleryImagePath);
//                            ImageUtils.saveImage(bufferedSmallProductImage, smallGalleryImagePath);
//
//                            productInputStream.close();
//                            productOutputStream.close();
//                        }
//
//                        productImage = new ProductImage();
//                        productImage.setProductId(prodId);
//                        productImage.setsTitle(productUrl.getName());
//                        productImage.setsLocation("/" + finalGalleryImagePath);
//                        try {
//                            productImageId = storeService.createProductImage(productImage);
//                            message.setMessage("Product Image added successfully");
//                            mav.addObject(MESSAGE, message);
//
//                        } catch (Exception exp) {
//                            mav.addObject(ERROR, "Could not add product image");
//                            return mav;
//
//                        }
//
//                    } catch (IOException ioe) {
//                    }
//                }
//
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                mav.addObject(ERROR, "Could not add product image");
//                return mav;
//            }
//
//            return mav;
//        }
//    }



    
    
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
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "product_id", required = false) String productId,
            @RequestParam(value = "product_image_url", required = false) String productImageUrl) {
        ModelAndView mav = new ModelAndView();
        Message message = new Message();
        Error error = new Error();
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
        
        if(StringUtils.isEmpty(productImageUrl) ){
        	missingParamList.add("product_image_url");
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
    		
    	if( ! productImageUrl.startsWith("http://")){
			mav.addObject(ERROR, error.generateError(3015));
			return mav;
    	}
    		
    	if(!(productImageUrl.endsWith("png") || productImageUrl.endsWith("jpg") || productImageUrl.endsWith("jpeg")
    			|| productImageUrl.endsWith("bmp") || productImageUrl.endsWith("gif"))){
    		mav.addObject(ERROR, error.generateError(3016));
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

    		PathLocator pathLocator = new PathLocator();
    		// change to online server for the time being
    		String rootPath = pathLocator.getContextPath();

    		String mobicartImagesFolderPath = "mobicartimages";
    		FileUtils.makeDirectoryIfItsNotThere(rootPath + mobicartImagesFolderPath);

    		String productFolderPath = "/productimages";
    		FileUtils.makeDirectoryIfItsNotThere(rootPath + mobicartImagesFolderPath + productFolderPath);

    		//hard code
    		String storeFolderPath = "/1";
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
        } catch (Exception e) {
        	logger.error("Exception", e);
        	error = error.generateError(1004);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        return mav;
    }    
   
    
    
    
    
    
    
    
    /**
     * Service to update Product Image
     *
     * @param user_name
     * @param api_key
     * @param product_id
     * @return Update Product Image Status String
     */
//    @RequestMapping(value = "/update-productImage", method = RequestMethod.POST)
//    public ModelAndView updateProductImage(
//            @RequestParam(value = "user_name", required = false) String userName,
//            @RequestParam(value = "api_key", required = false) String apiKey,
//            @RequestParam(value = "productImage_id", required = false) String productImageId,
//            @RequestParam(value = "product_url", required = false) MultipartFile productUrl) {
//        ModelAndView mav = new ModelAndView();
//        Message message = new Message();
//        Error error = new Error();
//
//        User user = null;
//        Long prodId;
//        Integer optionQuntity;
//        ProductImage productImage = null;
//
//        if (userName == null || userName.equals("") || apiKey == null || apiKey.equals("") || productImageId == null || productImageId.equals("")) {
//            error = new Error(1001, Error.MISSING_PARAMETERS);
//            mav.addObject(ERROR, error);
//            return mav;
//        } else {
//
//
//            try {
//                // validate key
//                Map<String, Object> key_response = apiBO.validateApiKey(apiKey, userName);
//
//                if (!(Boolean) key_response.get(VALID)) {
//                    error = (Error) key_response.get(ERROR);// get error
//                    mav.addObject(ERROR, error);
//                    return mav;
//                }
//
//                // get user
//                user = (User) key_response.get(USER);
//
//                // validate user
//                if (user == null) {
//                    error = error.generateError(1002);
//                    mav.addObject(ERROR, error);
//                    return mav;
//                }
//                try {
//                    prodId = Long.parseLong(productImageId);
//                } catch (NumberFormatException nfe) {
//                    error = new Error(6002, "productImageId must be numeric.");
//                    mav.addObject(ERROR, error);
//                    return mav;
//                }
//
//                Product product = null;
//                try {
//                    // fetch product 
//
//                    // add product option images 
//                    if (productUrl != null && productUrl.getSize() > 0) {
//                        String productUrlPath = productUrl.getOriginalFilename();
//                        String contentType = productUrl.getContentType();
//                        if ((contentType.equals("image/png")) || contentType.equals("image/jpg") || contentType.equals("image/jpeg") || contentType.equals("image/bmp") || contentType.equals("image/gif")) {
//                        } else {
//                            error = new Error(3013, "Given File is not an Image.");
//                            mav.addObject(ERROR, error);
//                            return mav;
//                        }
//
//                        try {
//                            MultipartFile productImageFile = productUrl;
//                            if (productImageFile == null) {
//                                System.out.println("User Did not upload file");
//                            } else {
//                                System.out.println("Uploaded File Name is :"
//                                        + productImageFile.getOriginalFilename());
//                            }
//
//                            PathLocator pathLocator = new PathLocator();
//                            // change to online server for the time being
//                            String rootPath = pathLocator.getContextPath();
//
//                            String mobicartImagesFolderPath = "mobicartimages";
//                            FileUtils.makeDirectoryIfItsNotThere(rootPath
//                                    + mobicartImagesFolderPath);
//
//                            String productFolderPath = "/productimages";
//                            FileUtils.makeDirectoryIfItsNotThere(rootPath
//                                    + mobicartImagesFolderPath + productFolderPath);
//
//                            //hard code
//                            String storeFolderPath = "/1";
//                            FileUtils.makeDirectoryIfItsNotThere(rootPath
//                                    + mobicartImagesFolderPath + productFolderPath
//                                    + storeFolderPath);
//
//                            String dateWiseFolderPath = "/" + DateTimeUtils.getCurrentDate();
//                            FileUtils.makeDirectoryIfItsNotThere(rootPath
//                                    + mobicartImagesFolderPath + productFolderPath
//                                    + storeFolderPath + dateWiseFolderPath);
//
//                            String finalGalleryImagePath = mobicartImagesFolderPath
//                                    + productFolderPath + storeFolderPath + dateWiseFolderPath;
//
//                            String productImageFileName = FileUtils.cleanSpecialChars(productImageFile.getOriginalFilename());
//                            String mediumGalleryImagePath = rootPath + finalGalleryImagePath + "/" + FileUtils.stuffedFilename(productImageFileName, "_medium");
//                            String smallGalleryImagePath = rootPath + finalGalleryImagePath + "/" + FileUtils.stuffedFilename(productImageFileName, "_small");
//
//                            finalGalleryImagePath += "/"
//                                    + productImageFileName;
//
//
//                            InputStream productInputStream = null;
//                            OutputStream productOutputStream = null;
//                            if (productImageFile.getSize() > 0) {
//                                productInputStream = productImageFile.getInputStream();
//                                BufferedImage bufferedGalleryImage = ImageIO
//                                        .read(productInputStream);
//                                productOutputStream = new FileOutputStream(rootPath
//                                        + finalGalleryImagePath);
//                                String format = (productImageFile.getOriginalFilename()
//                                        .endsWith(".png")) ? "png" : "jpg";
//                                ImageIO.write(bufferedGalleryImage, format, productOutputStream);
//                                //resize image
//                                BufferedImage bufferedMediumProductImage = ImageUtils.resize(bufferedGalleryImage, 130, 150, true);
//                                BufferedImage bufferedSmallProductImage = ImageUtils.resize(bufferedGalleryImage, 60, 60, true);
//
//                                ImageUtils.saveImage(bufferedMediumProductImage, mediumGalleryImagePath);
//                                ImageUtils.saveImage(bufferedSmallProductImage, smallGalleryImagePath);
//
//                                productInputStream.close();
//                                productOutputStream.close();
//                            }
//
//                            productImage = storeService.findProductImage(prodId);
//                            productImage.setsTitle(productUrl.getName());
//                            productImage.setsLocation("/" + finalGalleryImagePath);
//                            try {
//                                storeService.updateProductImage(productImage);
//                                message.setMessage("ProductImage updated successfully");
//                                mav.addObject(MESSAGE, message);
//                                return mav;
//
//                            } catch (Exception exp) {
//                                mav.addObject(ERROR, "Could not update product image");
//                                return mav;
//
//                            }
//
//                        } catch (IOException ioe) {
//                            mav.addObject(ERROR, "Could not update product image");
//                            return mav;
//
//                        }
//
//                    }
//                } catch (Exception e) {
//                    mav.addObject(ERROR, "Could not update product image");
//                    return mav;
//
//                }
//
//            } catch (Exception e) {
//                mav.addObject(ERROR, "Could not update product image");
//                return mav;
//
//            }
//
//        }
//
//        return mav;
//    }

    
    
    
    
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
            @RequestParam(value = "product_image_url", required = false) String productImageUrl) {
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
        
        if(StringUtils.isEmpty(productImageUrl) ){
        	missingParamList.add("product_image_url");
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
    		
    	if( ! productImageUrl.startsWith("http://")){
			mav.addObject(ERROR, error.generateError(3015));
			return mav;
    	}
    		
    	if(!(productImageUrl.endsWith("png") || productImageUrl.endsWith("jpg") || productImageUrl.endsWith("jpeg")
    			|| productImageUrl.endsWith("bmp") || productImageUrl.endsWith("gif"))){
    		mav.addObject(ERROR, error.generateError(3016));
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

    		PathLocator pathLocator = new PathLocator();
    		// change to online server for the time being
    		String rootPath = pathLocator.getContextPath();
    		
    		String mobicartImagesFolderPath = "mobicartimages";
    		FileUtils.makeDirectoryIfItsNotThere(rootPath+ mobicartImagesFolderPath);

    		String productFolderPath = "/productimages";
    		FileUtils.makeDirectoryIfItsNotThere(rootPath + mobicartImagesFolderPath + productFolderPath);

    		//hard code
    		String storeFolderPath = "/1";
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
        } catch (Exception e) {
        	logger.error("Something has gone wrong", e);
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
            @RequestParam(value = "option_quantity", required = false) String optionQuantity) {
    	
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
        
        if(StringUtils.isEmpty(optionTitle) ){
        	missingParamList.add("option_title");
        }
  
        if(StringUtils.isEmpty(optionName) ){
        	missingParamList.add("option_name");
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
        
        if(StringUtils.isEmpty(optionTitle) ){
        	missingParamList.add("option_title");
        }
  
        if(StringUtils.isEmpty(optionName) ){
        	missingParamList.add("option_name");
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

/*******************************PRODUCT SERVICES END************************************/


/*******************************GALLERY IMAGES SERVICES START************************************/


    /**
     * Service to fetch gallery images for store
     *
     * @param user_name
     * @param api_key
     * @param store_id
     * @return List of Gallery Images in Store
     */

    @RequestMapping(value = "/store-gallery-images", method = RequestMethod.GET)
    public ModelAndView getGalleryImagesForStore(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "store_id", required = false) String storeId) {
        List<GalleryImage> galleryImages = null;
        Store store = null;
        ModelAndView mav = new ModelAndView();
        Error error = new Error();
        Long streId = null;
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

			galleryImages = storeService.findAPIGalleryImagesByStore(streId);
			
        	if (galleryImages == null || galleryImages.size() <= 0) {
        		mav.addObject(ERROR, error.generateError(4002));
        		return mav;
            	}

            } catch (Exception e) {
                error = error.generateError(1004);
                mav.addObject(ERROR, error);
                return mav;
            }

            List<ImageApi> listImageApi = new ArrayList<ImageApi>();
            if (galleryImages.size() > 0) {
                for (GalleryImage objGalleryImage : galleryImages) {
                    ImageApi imgApi = new ImageApi();
                    imgApi.setimageId(objGalleryImage.getId());
                    imgApi.setimageName(objGalleryImage.getsTitle());
                    imgApi.setimageUrl(ResourceProperties.getString("appUrl") + objGalleryImage.getsThumbnail());
                    listImageApi.add(imgApi);
                }
            }
            MainImageApi mainImage = new MainImageApi();
            mainImage.setSize(listImageApi.size());
            mainImage.setStoreId(store.getId());
            mainImage.setImages(listImageApi);
            mav.addObject(HOME_GALLERY_IMAGES, mainImage);
            return mav;
        
    }


    /**
     * Service to add Gallery Image
     *
     * @param user_name
     * @param api_key
     * @param store-id
     * @return in JSON or XML
     */
    @RequestMapping(value = "/add-gallery-image", method = RequestMethod.POST)
    public ModelAndView addGalleryImages(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "store_id", required = false) String storeId,
            @RequestParam(value = "gallery_image_url", required = false) String galleryImageUrl) {
        
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
        
        if(StringUtils.isEmpty(galleryImageUrl) ){
        	missingParamList.add("gallery_image_url");
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
    		
    	if( ! galleryImageUrl.startsWith("http://")){
			mav.addObject(ERROR, error.generateError(3015));
			return mav;
    	}
    		
    	if(!(galleryImageUrl.endsWith("png") || galleryImageUrl.endsWith("jpg") || galleryImageUrl.endsWith("jpeg")
    			|| galleryImageUrl.endsWith("bmp") || galleryImageUrl.endsWith("gif"))){
    		mav.addObject(ERROR, error.generateError(3016));
    		return mav;
    	}
    	
        try {
			// get user
    		user = (User) key_response.get(USER);
    		Store store = storeService.findStoreById(streId);
    		if(store == null){
    			mav.addObject(ERROR, error.generateError(2003));
    			return mav;
    		}
		
    		if(store.getUserId().longValue() != user.getId().longValue()){
    			mav.addObject(ERROR, error.generateError(2004));
    			return mav;
    		}

            PathLocator pathLocator = new PathLocator();
            // change to online server for the time being
            String rootPath = pathLocator.getContextPath();

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
            
    		int fileNameIndex = galleryImageUrl.lastIndexOf("/");
    		String fileName = galleryImageUrl.substring(fileNameIndex);
    		
        
    		String galleryImageFileName = FileUtils.getJustTheFileName(fileName);
    		
    		finalGalleryImagePath += "/"+galleryImageFileName;
        
    		URL urlOfImage = null;
    		File outputFile = null;
        
    		BufferedInputStream in = null;
    		FileOutputStream out = null;
    		try {
    			outputFile = new File(rootPath + finalGalleryImagePath);
    			urlOfImage = new URL(galleryImageUrl);
							
    			in = new BufferedInputStream(urlOfImage.openStream());
    			BufferedImage bufferedGalleryImage = ImageIO.read(in);
    			out = new FileOutputStream(outputFile);
    			String format = (galleryImageUrl.endsWith(".png")) ? "png" : "jpg";
    			ImageIO.write(bufferedGalleryImage, format, out);
        	
    			bufferedGalleryImage = ImageUtils.resize(bufferedGalleryImage, 320, 235, true);
    			
    			ImageUtils.saveImage(bufferedGalleryImage, rootPath + finalGalleryImagePath);
        	
    			in.close();
    			out.close();
        	
    			GalleryImage galleryImage = new GalleryImage();
    			galleryImage.setsTitle(FileUtils.getJustTheFileName(galleryImageUrl));
    			galleryImage.setStoreId(store.getId());
                galleryImage.setUserId(store.getUserId());
                App app = appService.findAppByUser(new User(store.getUserId()));
                galleryImage.setAppId(app.getId());
                galleryImage.setsThumbnail("/"+finalGalleryImagePath);
    			try {
    				Long galleryImageId = storeService.saveGalleryImage(galleryImage);
    				message.setId(galleryImageId.toString());
    				message.setMessage("Gallery image added successfully.");
    				mav.addObject(MESSAGE, message);
        		} catch (Exception exp) {
        			logger.error("While saving image", exp);
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
        } catch (Exception e) {
        	logger.error("Exception", e);
        	error = error.generateError(1004);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        return mav;
    }


    /**
     * Service to delete Gallery Image
     *
     * @param user_name
     * @param api_key
     * @param galleryImage-id
     * @return Delete Image Status String
     */
    @RequestMapping(value = "/delete-gallery-image", method = RequestMethod.DELETE)
    public ModelAndView deleteGalleryImages(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "image_id", required = false) String imageId) {
        GalleryImage gImage = null;
        ModelAndView mav = new ModelAndView();
        Message message = new Message();
        Error error = new Error();
        Long imgId;
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
        if(StringUtils.isEmpty(imageId) ){
        	missingParamList.add("image_id");
        }
        
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        try {
        	imgId = Long.parseLong(imageId);
    		} catch (NumberFormatException nfe) {
    			mav.addObject(ERROR, error.generateError(4003));
    			return mav;
    		}

        	try {
    			// get user
                user = (User) key_response.get(USER);
                gImage = storeService.findGalleryImage(imgId);
                
                if(gImage == null){
    				mav.addObject(ERROR, error.generateError(4004));
    				return mav;
                }
                
    			if(gImage.getUserId().longValue() != user.getId().longValue()){
    				mav.addObject(ERROR, error.generateError(4005));
    				return mav;
            		}
    			
                } catch (Exception e) {
                    error = error.generateError(1004);
                    mav.addObject(ERROR, error);
                    return mav;
                }
                
                try{
                	storeService.deleteGalleryImage(gImage);
                }catch(Exception e){
                	error = error.generateError(1004);
                    mav.addObject(ERROR, error);
                    return mav;
                }
                message.setMessage("Image deleted successfully.");
                mav.addObject(message);
                return mav;
                
    }


/*******************************GALLERY IMAGES SERVICES END************************************/


/*******************************ORDER SERVICES START************************************/


    /**
     * Service to fetch order details
     *
     * @param user_name
     * @param api_key
     * @param order_id
     * @return 'ProductOrder' object containing Order Details and Order Items in
     *         JSON or XML
     */

    @RequestMapping(value = "/order-details", method = RequestMethod.GET)
    public ModelAndView getOrderDetails(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "order_id", required = false) String orderId) {

        ProductOrder productOrder = null;
        ModelAndView mav = new ModelAndView();
        Error error = new Error();
        Long odrId;
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
        if(StringUtils.isEmpty(orderId) ){
        	missingParamList.add("order_id");
        }
        
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        try {
        	odrId = Long.parseLong(orderId);
    		} catch (NumberFormatException nfe) {
    			mav.addObject(ERROR, error.generateError(5001));
    			return mav;
    		}

        	try {
    			// get user
                user = (User) key_response.get(USER);
                productOrder = storeService.findProductOrder(odrId);
                
                if(productOrder == null){
    				mav.addObject(ERROR, error.generateError(5002));
    				return mav;
                }
                
    			if(productOrder.getMerchantId().longValue() != user.getId().longValue()){
    				mav.addObject(ERROR, error.generateError(5004));
    				return mav;
            		}
    			
                } catch (Exception e) {
                    error = error.generateError(1004);
                    mav.addObject(ERROR, error);
                    return mav;
                }

            List<ProductOrderItemApi> productOrderItems = new ArrayList<ProductOrderItemApi>();
            for (ProductOrderItem productOption : productOrder.getProductOrderItems()) {
            	ProductOrderItemApi productOrderItemApi = new ProductOrderItemApi();
            	productOrderItemApi.setOrderItemId(productOption.getId());
            	productOrderItemApi.setOrderProductId(productOption.getProductId());
            	productOrderItemApi.setOrderProductAmount(productOption.getfAmount());
            	productOrderItemApi.setOrderProductQuantity(productOption.getiQuantity());
            	productOrderItems.add(productOrderItemApi);
            	
            }


            ProductOrderApi productApi = new ProductOrderApi();
            productApi.setOrderId(productOrder.getId());
            productApi.setStoreId(productOrder.getStoreId());
            productApi.setMerchantPayPalEmail(productOrder.getsMerchantPaypalEmail());
            productApi.setOrderAmount(productOrder.getfAmount());
            productApi.setOrderTaxAmount(productOrder.getfTaxAmount());
            productApi.setOrderGrandTotalAmount(productOrder.getfTotalAmount());
            productApi.setBuyerName(productOrder.getsBuyerName());
            productApi.setBuyerEmail(productOrder.getsBuyerEmail());
            productApi.setShippingStreet(productOrder.getsShippingStreet());
            productApi.setShippingCity(productOrder.getsShippingCity());
            productApi.setShippingState(productOrder.getsShippingState());
            productApi.setShippingPostalCode(productOrder.getsShippingPostalCode());
            productApi.setShippingCountry(productOrder.getsShippingCountry());
            productApi.setBillingStreet(productApi.getBillingStreet());
            productApi.setBillingCity(productApi.getBillingCity());
            productApi.setBillingState(productApi.getBillingState());
            productApi.setBillingPostalCode(productApi.getBillingPostalCode());
            productApi.setBillingCountry(productApi.getBillingCountry());
            productApi.setOrderStatus(productApi.getOrderStatus());
            productApi.setOrderDate(productApi.getOrderDate());
            productApi.setProductOrderItems(productOrderItems);
            mav.addObject(PRODUCT_ORDER, productApi);
            return mav;
    }

    /**
     * Service to fetch orders between dates
     *
     * @param user_name
     * @param api_key
     * @param from-date
     * @param to-date
     * @return List of Product Orders in JSON or XML
     */

    @RequestMapping(value = "/orders-by-date", method = RequestMethod.GET)
    public ModelAndView getOrdersByDate(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "from_date", required = false) String from,
            @RequestParam(value = "to_date", required = false) String to) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fromDate = null;
        Date toDate = null;
        List<ProductOrder> productOrders = null;
        ModelAndView mav = new ModelAndView();
        Error error = new Error();
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
        if(StringUtils.isEmpty(from) ){
        	missingParamList.add("from_date");
        }
        
        if(StringUtils.isEmpty(to) ){
        	missingParamList.add("to_date");
        }
        
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        try {
        	fromDate = simpleDateFormat.parse(from);
        	toDate = simpleDateFormat.parse(to);
    		} catch (ParseException pe) {
    			mav.addObject(ERROR, error.generateError(5005));
    			return mav;
    		}

        	try {
    			// get user
                user = (User) key_response.get(USER);
             
                productOrders = adminService.findAPIProductOrderByDate(fromDate, toDate, user.getId());
                
                if(productOrders == null || productOrders.size() <= 0){
    				mav.addObject(ERROR, error.generateError(5006));
    				return mav;
                }
  			
                } catch (Exception e) {
                    error = error.generateError(1004);
                    mav.addObject(ERROR, error);
                    return mav;
                }

            List<ProductOrderApi> productOrderApiList = new ArrayList<ProductOrderApi>();
            for (ProductOrder productOrder : productOrders) {
                ProductOrderApi productApi = new ProductOrderApi();
                productApi.setOrderId(productOrder.getId());
                productApi.setStoreId(productOrder.getStoreId());
                productApi.setMerchantPayPalEmail(productOrder.getsMerchantPaypalEmail());
                productApi.setOrderAmount(productOrder.getfAmount());
                productApi.setOrderTaxAmount(productOrder.getfTaxAmount());
                productApi.setOrderGrandTotalAmount(productOrder.getfTotalAmount());
                productApi.setBuyerName(productOrder.getsBuyerName());
                productApi.setBuyerEmail(productOrder.getsBuyerEmail());
                productApi.setShippingStreet(productOrder.getsShippingStreet());
                productApi.setShippingCity(productOrder.getsShippingCity());
                productApi.setShippingState(productOrder.getsShippingState());
                productApi.setShippingPostalCode(productOrder.getsShippingPostalCode());
                productApi.setShippingCountry(productOrder.getsShippingCountry());
                productApi.setBillingStreet(productApi.getBillingStreet());
                productApi.setBillingCity(productApi.getBillingCity());
                productApi.setBillingState(productApi.getBillingState());
                productApi.setBillingPostalCode(productApi.getBillingPostalCode());
                productApi.setBillingCountry(productApi.getBillingCountry());
                productApi.setOrderStatus(productApi.getOrderStatus());
                productApi.setOrderDate(productApi.getOrderDate());
                productOrderApiList.add(productApi);

            }
            MainOrderApi mainOrder = new MainOrderApi();
            mainOrder.setOrders(productOrderApiList);
            mainOrder.setSize(productOrders.size());
            mav.addObject(PRODUCT_ORDER_LIST, mainOrder);
            return mav;
        
    }


    /**
     * Service to add Product Order
     *
     * @param user_name
     * @param api_key                 //* @param product_order_id
     * @param merchant_id
     * @param store_id
     * @param app_id
     * @param s_merchant_paypal_email
     * @param f_amount
     * @param f_shipping_amount
     * @param f_tax_amount
     * @param f_total_amount
     * @param s_buyer_name
     * @param s_buyer_email
     * @param i_buyer_phone
     * @param s_shipping_street
     * @param s_shipping_city
     * @param s_shipping_state
     * @param s_shipping_postal_code
     * @param s_shipping_country
     * @param s_billing_street
     * @param s_billing_city
     * @param s_billing_state
     * @param s_billing_postal_code
     * @param s_billing_country
     * @param s_status
     * @param d_order_date            An ArrayList of JSON Objects that holds all the Product_Order_Item details
     * @return Add Product Order Status String
     */
    @RequestMapping(value = "/add-product-order", method = RequestMethod.POST)
    public ModelAndView addOrderForProduct(
            @RequestParam(value = "user_name", required = true) String userName,
            @RequestParam(value = "api_key", required = true) String apiKey,
            @RequestParam(value = "s_merchant_paypal_email", required = false) String sMerchantPaypalEmail,
            @RequestParam(value = "s_buyer_name", required = false) String sBuyerName,
            @RequestParam(value = "s_buyer_email", required = false) String sBuyerEmail,
            @RequestParam(value = "i_buyer_phone", required = false) String iBuyerPhone,
            @RequestParam(value = "s_shipping_street", required = false) String sShippingStreet,
            @RequestParam(value = "s_shipping_city", required = false) String sShippingCity,
            @RequestParam(value = "s_shipping_state", required = false) String sShippingState,
            @RequestParam(value = "s_shipping_postal_code", required = false) String sShippingPostalCode,
            @RequestParam(value = "s_shipping_country", required = false) String sShippingCountry,
            @RequestParam(value = "s_billing_street", required = false) String sBillingStreet,
            @RequestParam(value = "s_billing_city", required = false) String sBillingCity,
            @RequestParam(value = "s_billing_state", required = false) String sBillingState,
            @RequestParam(value = "s_billing_postal_code", required = false) String sBillingPostalCode,
            @RequestParam(value = "s_billing_country", required = false) String sBillingCountry,
            @RequestParam(value = "d_order_date", required = false) String dOrderDate,
            @RequestParam(value = "s_status", required = false) String sStatus) {


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ProductOrder productOrder = new ProductOrder();
        ModelAndView mav = new ModelAndView();
        Message message = new Message();
        Error error = new Error();
        Date orderDate = null;
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
        
        if(StringUtils.isEmpty(sMerchantPaypalEmail) ){
        	missingParamList.add("s_merchant_paypal_email");
        }
        
        if(StringUtils.isEmpty(sBuyerName) ){
        	missingParamList.add("s_buyer_name");
        }
        
        if(StringUtils.isEmpty(sBuyerEmail) ){
        	missingParamList.add("s_buyer_email");
        }
        
        if(StringUtils.isEmpty(iBuyerPhone) ){
        	missingParamList.add("i_buyer_phone");
        }
        
        if(StringUtils.isEmpty(sShippingStreet) ){
        	missingParamList.add("s_shipping_street");
        }
        
        if(StringUtils.isEmpty(sShippingCity) ){
        	missingParamList.add("s_shipping_city");
        }
        
        if(StringUtils.isEmpty(sShippingState) ){
        	missingParamList.add("s_shipping_state");
        }
        
        if(StringUtils.isEmpty(sShippingPostalCode) ){
        	missingParamList.add("s_shipping_postal_code");
        }
        
        if(StringUtils.isEmpty(sShippingCountry) ){
        	missingParamList.add("s_shipping_country");
        }
        
        if(StringUtils.isEmpty(sBillingStreet) ){
        	missingParamList.add("s_billing_street");
        }
        
        if(StringUtils.isEmpty(sBillingCity) ){
        	missingParamList.add("s_billing_city");
        }
        
        if(StringUtils.isEmpty(sBillingState) ){
        	missingParamList.add("s_billing_state");
        }
        
        if(StringUtils.isEmpty(sBillingPostalCode) ){
        	missingParamList.add("s_billing_postal_code");
        }
        
        if(StringUtils.isEmpty(sBillingCountry) ){
        	missingParamList.add("s_billing_country");
        }
        
        if(StringUtils.isEmpty(dOrderDate) ){
        	missingParamList.add("d_order_date");
        }
        
        if(StringUtils.isEmpty(sStatus) ){
        	missingParamList.add("s_status");
        }
  
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        Long iBuyerPhoneLong;
        try {
            iBuyerPhone = iBuyerPhone.trim();
            iBuyerPhoneLong = Long.parseLong(iBuyerPhone);
        } catch (NumberFormatException nfe) {
            mav.addObject(ERROR, error.generateError(5011));
            return mav;
        }
        
        try {
            orderDate = simpleDateFormat.parse(dOrderDate);
        } catch (ParseException e) {
            mav.addObject(ERROR, error.generateError(5005));
            return mav;
        }

		try {
			// get user
    		user = (User) key_response.get(USER);
    		
    		App app = storeService.findAPIAppByUser(user.getId());
            Store store = storeService.findStoreByUserId(user.getId());
            productOrder.setMerchantId(user.getId());
            productOrder.setStoreId(store.getId());
            productOrder.setAppId(app.getId());

         // add product-order 
            productOrder.setsMerchantPaypalEmail(sMerchantPaypalEmail);
            productOrder.setsBuyerName(sBuyerName);
            productOrder.setsBuyerEmail(sBuyerEmail);
            productOrder.setiBuyerPhone(iBuyerPhoneLong);
            productOrder.setsShippingStreet(sShippingStreet);
            productOrder.setsShippingCity(sShippingCity);
            productOrder.setsShippingState(sShippingState);
            productOrder.setsShippingPostalCode(sShippingPostalCode);
            productOrder.setsShippingCountry(sShippingCountry);
            productOrder.setsBillingStreet(sBillingStreet);
            productOrder.setsBillingCity(sBillingCity);
            productOrder.setsBillingState(sBillingState);
            productOrder.setsBillingPostalCode(sBillingPostalCode);
            productOrder.setsBillingCountry(sBillingCountry);
            productOrder.setfAmount(new BigDecimal(0.0));
            productOrder.setfTotalAmount(new BigDecimal(0.0));
            productOrder.setsStatus(sStatus);
            productOrder.setdOrderDate(orderDate);
            
            Long orderId = adminService.saveProductOrder(productOrder);
            
            if(orderId == null){
            	message.setMessage("Order could not be saved.");
            	mav.addObject(message);
            	return mav;
            }
            message.setId(orderId.toString());
            message.setMessage("Product order added successfully.");
            mav.addObject(message);
		}catch(Exception e){
			mav.addObject(ERROR, error.generateError(1004));
            return mav;
        
		}
		return mav;
	}



    /**
     * Service to add Order Item
     *
     * @param user_name
     * @param api_key
     * @param product_id
     * @param order_id
     * @param product_option_id
     * @param quantity
     * @param amount
     * @return Add Order Item Status String
     */
    @RequestMapping(value = "/add-OrderItem", method = RequestMethod.POST)
    public ModelAndView addOrderItem(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "product_id", required = false) String productId,
            @RequestParam(value = "order_id", required = false) String orderId,
            @RequestParam(value = "product_option_id", required = false) String optionId,
            @RequestParam(value = "amount", required = false) String amount,
            @RequestParam(value = "quantity", required = false) String quantity) {

    	
    	ModelAndView mav = new ModelAndView();
        Message message = new Message();
        Error error = new Error();
        User user = null;
        Long prodId = null;
        Long ordrId = null;
        Long optId = null;
        BigDecimal amont = null;
        Integer quntity = null;

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
        
        if(StringUtils.isEmpty(orderId) ){
        	missingParamList.add("order_id");
        }
  
        if(StringUtils.isEmpty(optionId) ){
        	missingParamList.add("product_option_id");
        }
        
        if(StringUtils.isEmpty(amount) ){
        	missingParamList.add("amount");
        }
        
        if(StringUtils.isEmpty(quantity) ){
        	missingParamList.add("quantity");
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
        	ordrId = Long.parseLong(orderId);
        	} catch (NumberFormatException nfe) {
                mav.addObject(ERROR, error.generateError(5001));
                return mav;
            }

    	try {
    		optId = Long.parseLong(optionId);
    		} catch (NumberFormatException nfe) {
    			mav.addObject(ERROR, error.generateError(3012));
    			return mav;
            }
		
		try {
			amont = new BigDecimal(Double.parseDouble(amount));
			} catch (NumberFormatException nfe) {
				mav.addObject(ERROR, error.generateError(5012));
				return mav;
            }

		try {
			quntity = Integer.parseInt(quantity);
			} catch (NumberFormatException nfe) {
				mav.addObject(ERROR, error.generateError(5013));
				return mav;
            }


    	try {
            // get user
    		user = (User) key_response.get(USER);
    		
            Product product = null;
            // fetch product 
            product = storeService.findProduct(prodId);

        	if (product == null) {
        		mav.addObject(ERROR, error.generateError(3005));
        		return mav;
                }
        	
        	if(product.getUserId().longValue() != user.getId().longValue()){
        		mav.addObject(ERROR, error.generateError(3010));
        		return mav;
        	}
        	
        	ProductOrder productOrder = storeService.findProductOrder(ordrId);
        	if(productOrder == null){
        		mav.addObject(ERROR, error.generateError(5002));
        		return mav;
        	}
        	
        	if(productOrder.getMerchantId().longValue() != user.getId().longValue()){
        		mav.addObject(ERROR, error.generateError(5004));
        		return mav;
        	}
        	
        	ProductOption productOption = storeService.findProductOption(optId);
        	if(productOption == null){
        		mav.addObject(ERROR, error.generateError(3013));
        		return mav;
        	}
        	Long prductId = productOption.getProductId();
        	Product prduct = storeService.findProduct(prductId);
        	if(prduct.getId().longValue() != prodId.longValue()){
        		mav.addObject(ERROR, error.generateError(3014));
        		return mav;
        	}
        	
            ProductOrderItem prodOrdrItem = new ProductOrderItem();
            prodOrdrItem.setOrderId(ordrId);
            prodOrdrItem.setProductId(prodId);
            prodOrdrItem.setProductOptionId(optId);
            prodOrdrItem.setfAmount(amont);
            prodOrdrItem.setiQuantity(quntity);
            
            Long prodOrdrItemId = adminService.saveProductOrderItem(prodOrdrItem);
            if(prodOrdrItemId == null){
            	message.setMessage("Product order item could not be saved.");
            	mav.addObject(message);
            	return mav;
            }
            
            message.setId(prodOrdrItemId.toString());
            message.setMessage("Product order item saved successfully.");
            mav.addObject(message);
            } catch (Exception e) {
            	error = error.generateError(1004);
                mav.addObject(ERROR, error);
                return mav;
            }

            return mav;
    }


    /**
     * Service to update Order Item
     *
     * @param user_name
     * @param api_key
     * @param option_item_id
     * @param quantity
     * @param amount
     * @return Update Order Item Status String
     */
    @RequestMapping(value = "/update-OrderItem", method = RequestMethod.POST)
    public ModelAndView updateOrderItem(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "order_item_id", required = false) String orderItemId,
            @RequestParam(value = "amount", required = false) String amount,
            @RequestParam(value = "quantity", required = false) String quantity) {

    	
    	ModelAndView mav = new ModelAndView();
        Message message = new Message();
        Error error = new Error();
        User user = null;
        Long ordrItemId = null;
        BigDecimal amont = null;
        Integer quntity = null;

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
        
        if(StringUtils.isEmpty(orderItemId) ){
        	missingParamList.add("order_item_id");
        }
        
        if(StringUtils.isEmpty(amount) ){
        	missingParamList.add("amount");
        }
        
        if(StringUtils.isEmpty(quantity) ){
        	missingParamList.add("quantity");
        }
        
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        try {
        	ordrItemId = Long.parseLong(orderItemId);
    		} catch (NumberFormatException nfe) {
    			mav.addObject(ERROR, error.generateError(5014));
    			return mav;
    		}

		try {
			amont = new BigDecimal(Double.parseDouble(amount));
			} catch (NumberFormatException nfe) {
				mav.addObject(ERROR, error.generateError(5012));
				return mav;
            }

		try {
			quntity = Integer.parseInt(quantity);
			} catch (NumberFormatException nfe) {
				mav.addObject(ERROR, error.generateError(5013));
				return mav;
            }


    	try {
            // get user
    		user = (User) key_response.get(USER);
    		
            ProductOrderItem productOrderItem = null;
            // fetch product 
            productOrderItem = adminService.findProductOrderItem(ordrItemId);

        	if (productOrderItem == null) {
        		mav.addObject(ERROR, error.generateError(5015));
        		return mav;
                }
        	Long orderId = productOrderItem.getOrderId();//.getProductId();
        	ProductOrder productOrder = storeService.findProductOrder(orderId);
        	
        	if(productOrder.getMerchantId().longValue() != user.getId().longValue()){
        		mav.addObject(ERROR, error.generateError(5016));
        		return mav;
        	}
        	
        	
        	productOrderItem.setfAmount(amont);
        	productOrderItem.setiQuantity(quntity);
            
            Long prodOrdrItemId = storeService.updateOrderItem(productOrderItem);
            if(prodOrdrItemId == null){
            	message.setMessage("Product order item could not be updated.");
            	mav.addObject(message);
            	return mav;
            }
            
            message.setId(prodOrdrItemId.toString());
            message.setMessage("Product order item updated successfully.");
            mav.addObject(message);
            } catch (Exception e) {
            	error = error.generateError(1004);
                mav.addObject(ERROR, error);
                return mav;
            }

            return mav;
    }


    /**
     * Service to fetch orders and it's count of a Product
     *
     * @param user_name
     * @param api_key
     * @param product_id
     * @return List of 'ProductOrder' object and it's size
     */

    @RequestMapping(value = "/product-orders", method = RequestMethod.GET)
    public ModelAndView getProductOrdersDetails(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "product_id", required = false) String productId) {

        List<ProductOrder> productOrderList = new ArrayList<ProductOrder>();
        Integer sizeOfProductOrderList = 0;
        ModelAndView mav = new ModelAndView();
        Error error = new Error();
        Long prodId;
        User user = null;

        if (userName == null || userName.equals("") || apiKey == null || apiKey.equals("") || productId == null || productId.equals("")) {
            error = new Error(1001, Error.MISSING_PARAMETERS);
            mav.addObject(ERROR, error);
            return mav;
        } else {
            try {
                productId = productId.trim();
                prodId = Long.parseLong(productId);
            } catch (NumberFormatException nfe) {
                error = new Error(3001, "ProductId must be numeric.");
                mav.addObject(ERROR, error);
                return mav;
            }


            try {
                // validate key
                Map<String, Object> key_response = apiBO.validateApiKey(apiKey, userName);

                if (!(Boolean) key_response.get(VALID)) {
                    error = (Error) key_response.get(ERROR);// get error
                    mav.addObject(ERROR, error);
                    return mav;
                }

                // get user
                user = (User) key_response.get(USER);

                // validate user
                if (user == null) {
                    error = error.generateError(1002);
                    mav.addObject(ERROR, error);
                    return mav;
                }

                List<Long> orderIds = new ArrayList<Long>();
                try {
                    // fetch Order Details for Product
                    orderIds = adminService.findOrderIdsByProductId(prodId);
                } catch (Exception e) {
                    String errorMessage = "No Orders Found.";
                    error.setMessage(errorMessage);
                    error.setErrorcode(1001);
                    mav.addObject(ERROR, error);
                    return mav;
                }
                if (orderIds != null) {

                    for (Long orderId : orderIds) {

                        ProductOrder productOrder = adminService.findAPIProductOrder(orderId);
                        productOrderList.add(productOrder);
                    }

                    sizeOfProductOrderList = productOrderList.size();
                } else {
                    error = new Error(5002, "No Order found for specified Product.");
                    mav.addObject(ERROR, error);
                    return mav;

                }


            } catch (Exception e) {
                error = error.generateError(1002);
                mav.addObject(ERROR, error);
                return mav;
            }
            List<ProductOrderApi> productOrderApiList = new ArrayList<ProductOrderApi>();
            for (ProductOrder productOrder : productOrderList) {
                ProductOrderApi productApi = new ProductOrderApi();
                productApi.setOrderId(productOrder.getId());
                productApi.setStoreId(productOrder.getStoreId());
                productApi.setMerchantPayPalEmail(productOrder.getsMerchantPaypalEmail());
                productApi.setOrderAmount(productOrder.getfAmount());
                productApi.setOrderTaxAmount(productOrder.getfTaxAmount());
                productApi.setOrderGrandTotalAmount(productOrder.getfTotalAmount());
                productApi.setBuyerName(productOrder.getsBuyerName());
                productApi.setBuyerEmail(productOrder.getsBuyerEmail());
                productApi.setShippingStreet(productOrder.getsShippingStreet());
                productApi.setShippingCity(productOrder.getsShippingCity());
                productApi.setShippingState(productOrder.getsShippingState());
                productApi.setShippingPostalCode(productOrder.getsShippingPostalCode());
                productApi.setShippingCountry(productOrder.getsShippingCountry());
                productApi.setBillingStreet(productApi.getBillingStreet());
                productApi.setBillingCity(productApi.getBillingCity());
                productApi.setBillingState(productApi.getBillingState());
                productApi.setBillingPostalCode(productApi.getBillingPostalCode());
                productApi.setBillingCountry(productApi.getBillingCountry());
                productApi.setOrderStatus(productApi.getOrderStatus());
                productApi.setOrderDate(productApi.getOrderDate());
                productOrderApiList.add(productApi);

            }
            MainOrderApi mainOrder = new MainOrderApi();
            mainOrder.setOrders(productOrderApiList);
            mainOrder.setSize(sizeOfProductOrderList);
            mav.addObject(PRODUCT_ORDER_LIST, mainOrder);
            return mav;
        }
    }


    /**
     * Service to get Shipping Details
     *
     * @param user_name
     * @param api_key
     * @param order_id
     * @return 'Product' object containing product details in JSON or XML
     */

    @RequestMapping(value = "/shipping-status", method = RequestMethod.GET)
    public ModelAndView getShippingStatus(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "tracking_number", required = false) String trackingNumber) {

        ProductOrderShippingDetail shippingDetail = null;
        ModelAndView mav = new ModelAndView();
        Error error = new Error();
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
        if(StringUtils.isEmpty(trackingNumber) ){
        	missingParamList.add("tracking_number");
        }
        
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        try {
        	// get user
        	user = (User) key_response.get(USER);
        	// validate user
        	
        	shippingDetail = storeService.findProductOrderShippingDetail(trackingNumber);
                
        	if(shippingDetail == null){
        		mav.addObject(ERROR, error.generateError(5003));
        		return mav;
        	}
                
        	Long ordrId = shippingDetail.getProductOrderId();
        	ProductOrder productOrder = storeService.findProductOrder(ordrId);
        	
        	if(productOrder.getMerchantId().longValue() != user.getId().longValue()){
        		mav.addObject(ERROR, error.generateError(5010));
        		return mav;
        	}
        	
        	shippingDetail.setsShippingCarrier(shippingDetail.getsShippingCarrier().toUpperCase());
        	shippingDetail.setsShippingStatus(shippingDetail.getsShippingStatus().toUpperCase());
        	mav.addObject(shippingDetail);
        	
        } catch (Exception e) {
        	error = error.generateError(1004);
        	mav.addObject(ERROR, error);
        	return mav;
        }
        return mav;
    }


    /**
     * Service to add Shipping Details Item
     *
     * @param user_name
     * @param api_key
     * @param order_id
     * @param tracking_number
     * @param shipping_carrier
     * @param shipping_remarks
     * @param shipping_status
     * @return Order Shipping Status String
     */
    @RequestMapping(value = "/add-shipping-status", method = RequestMethod.POST)
    public ModelAndView addShippingStatus(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "order_id", required = false) String orderId,
            @RequestParam(value = "tracking_number", required = false) String trackingNumber,
            @RequestParam(value = "shipping_carrier", required = false) String shippingCarrier,
            @RequestParam(value = "shipping_remarks", required = false) String shippingRemarks,
            @RequestParam(value = "shipping_status", required = false) String shippingStatus) {
        
    	ModelAndView mav = new ModelAndView();
        Message message = new Message();
        Error error = new Error();
        ProductOrder productOrder = null;
        ProductOrderShippingDetail shippingDetail = null;
        User user = null;
        Long ordrId;

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
        
        if(StringUtils.isEmpty(orderId) ){
        	missingParamList.add("order_id");
        }
        
        if(StringUtils.isEmpty(trackingNumber) ){
        	missingParamList.add("tracking_number");
        }
  
        if(StringUtils.isEmpty(shippingCarrier) ){
        	missingParamList.add("shipping_carrier");
        }

        if(StringUtils.isEmpty(shippingStatus) ){
        	missingParamList.add("shipping_status");
        }
        
        if(missingParamList.size()>0){
            mav.addObject(ERROR, error.generateError(1001));
            return mav;
        }
        
        if( ! (shippingCarrier.equals("fedex") || shippingCarrier.equals("ups") || shippingCarrier.equals("other"))){
        	mav.addObject(ERROR, error.generateError(5008));
            return mav;
        }
        
        if( ! (shippingStatus.equals("pickedup") || shippingStatus.equals("intransit") 
        		|| shippingStatus.equals("arrived") || shippingStatus.equals("delivered"))){
        	mav.addObject(ERROR, error.generateError(5009));
            return mav;
        }
        
        try {
        	orderId = orderId.trim();
        	ordrId = Long.parseLong(orderId);
        } catch (NumberFormatException nfe) {
        	mav.addObject(ERROR, error.generateError(5001));
        	return mav;
        }
        
        
        try {
        	// get user
        	user = (User) key_response.get(USER);
        	try {
        		// fetch product 
        		productOrder = storeService.findProductOrder(ordrId);
        	} catch (Exception e) {
        		mav.addObject(ERROR, error.generateError(1004));
        		return mav;
            }
        	if (productOrder == null) {
        		mav.addObject(ERROR, error.generateError(5002));
        		return mav;
            }
        	
        	if(productOrder.getMerchantId().longValue() != user.getId().longValue()){
        		mav.addObject(ERROR, error.generateError(5004));
        		return mav;
            }
        	
        	shippingDetail = new ProductOrderShippingDetail();
        	shippingDetail.setProductOrderId(ordrId);
        	shippingDetail.setsTrackingNumber(trackingNumber);
        	shippingDetail.setsShippingCarrier(shippingCarrier);
        	shippingDetail.setsShippingStatus(shippingStatus);
        	if( ! StringUtils.isEmpty(shippingRemarks))
        		shippingDetail.setsShippingRemarks(shippingRemarks);
        	
        	try {
        		storeService.createProductOrderShippingDetail(shippingDetail);
        		message.setMessage("Shipping status added successfully");
        		mav.addObject(MESSAGE, message);
            } catch (Exception e) {
            	mav.addObject(ERROR, error.generateError(1004));
            	return mav;
            }
            
            return mav;
            
            
        } catch (Exception e) {
        	mav.addObject(ERROR, error.generateError(1004));
        	return mav;
        }
        
        
    }


    /**
     * Service to add Shipping Details Item
     *
     * @param user_name
     * @param api_key
     * @param order_id
     * @param tracking_number
     * @param shipping_carrier
     * @param shipping_remarks
     * @param shipping_status
     * @return Order Shipping Status String
     */
    @RequestMapping(value = "/update-shipping-status", method = RequestMethod.POST)
    public ModelAndView updateShippingStatus(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "tracking_number", required = false) String trackingNumber,
            @RequestParam(value = "shipping_remarks", required = false) String shippingRemarks,
            @RequestParam(value = "shipping_status", required = false) String shippingStatus) {
        ModelAndView mav = new ModelAndView();
        Message message = new Message();
        Error error = new Error();
        ProductOrderShippingDetail shippingDetail = null;

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
        
        if(StringUtils.isEmpty(trackingNumber) ){
        	missingParamList.add("tracking_number");
        }
        
        if(StringUtils.isEmpty(shippingRemarks) ){
        	missingParamList.add("shipping_remarks");
        }
  
        if(StringUtils.isEmpty(shippingStatus) ){
        	missingParamList.add("shipping_status");
        }
        
        if( ! (shippingStatus.equals("pickedup") || shippingStatus.equals("intransit") 
        		|| shippingStatus.equals("arrived") || shippingStatus.equals("delivered"))){
        	mav.addObject(ERROR, error.generateError(5009));
            return mav;
        }
        
        
        if(missingParamList.size()>0){
            mav.addObject(ERROR, error.generateError(1001));
            return mav;
        }



        try {
        	shippingDetail = storeService.findProductOrderShippingDetail(trackingNumber);
        		
            if (shippingDetail == null) {
            	mav.addObject(ERROR, error.generateError(5010));
            	return mav;
            } else {
            	shippingDetail.setsShippingStatus(shippingStatus);
            	shippingDetail.setsShippingRemarks(shippingRemarks);
            	
            	try {
            		storeService.updateProductOrderShippingDetail(shippingDetail);
            		message.setMessage("Shipping status updated successfully");
            		mav.addObject(MESSAGE, message);
            	} catch (Exception e) {
            		mav.addObject(ERROR, error.generateError(1004));
            		return mav;
                }
        	}
                return mav;


        } catch (Exception e) {
        	mav.addObject(ERROR, error.generateError(1004));
        	return mav;
        }
        
        	
    }


/*******************************ORDER SERVICES END************************************/


/*******************************STORE SERVICES START************************************/

    /**
     * Service to fetch stores of merchant
     *
     * @param user_name
     * @param api_key
     * @return list of 'Store' objects in JSON
     */

    @RequestMapping(value = "/stores", method = RequestMethod.GET)
    public ModelAndView getStoresByUser(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey) {
        List<Store> stores = null;
        ModelAndView mav = new ModelAndView();
        Error error = new Error();
        User user = null;
        
        try {

        	// validate key
        	Map<String, Object> key_response = apiBO.validateApiKey(apiKey, userName);
        
        	if (!(Boolean) key_response.get(VALID)) {
        		error = (Error) key_response.get(ERROR);// get error
        		mav.addObject(ERROR, error);
        		return mav;
        	}
        	
        	boolean hits = true;
            try{
            	hits = apiBO.validateStoreApiHits(apiKey);
            }catch(Exception e){
            	logger.error("While validating hits", e);
            	mav.addObject(ERROR, error.generateError(1004));
            	return mav;
            }
            
            if(hits == false){
            	mav.addObject(ERROR, error.generateError(1006));
            	return mav;
            }

        	// get user
        	user = (User) key_response.get(USER);


        	try {
        		// fetch stores for user
        		stores = storeService.findAPIStoresByUserId(user.getId());
        		if(stores == null){
            		mav.addObject(ERROR, error.generateError(2002));
            		return mav;
        			}
        		} catch (Exception e) {
        			mav.addObject(ERROR, error.generateError(1004));
        			return mav;
					}

            } catch (Exception e) {
                error = error.generateError(1004);
                mav.addObject(ERROR, error);
                return mav;
                //e.printStackTrace();
            	}
            
            List<StoreApi> storesapi = new ArrayList<StoreApi>();
            StoreApi strApi = new StoreApi();
            if (stores.size() > 0) {
                Store storeObj = stores.get(0);
                strApi.setStoreId(storeObj.getId());
                strApi.setStoreName(storeObj.getsSName());
                strApi.setCurrency(storeObj.getsCurrency());
                storesapi.add(strApi);
            	}

            mav.addObject(STORE, strApi);
        	return mav;
        
	}

    /**
     * Service to fetch store settings of merchant
     *
     * @param user_name
     * @param api_key
     * @param store_id
     * @return 'Store' object in JSON
     */

    @RequestMapping(value = "/store-settings", method = RequestMethod.GET)
    public ModelAndView getStoreSettings(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "store_id", required = false) String storeId) {
        Store store = null;
        Store storeShipping = null;
        Store storeTax = null;
        ModelAndView mav = new ModelAndView();
        Error error = new Error();
        Long strId;
        User user = null;
        
        
        Map<String, Object> keyResponse = apiBO.validateApiKey(apiKey, userName);

        if (!(Boolean) keyResponse.get(VALID)) {
            error = (Error) keyResponse.get(ERROR);// get error
            mav.addObject(ERROR, error);
            return mav;
        }
        
        boolean hits = true;
        try{
        	hits = apiBO.validateStoreApiHits(apiKey);
        }catch(Exception e){
        	logger.error("While validating hits", e);
        	mav.addObject(ERROR, error.generateError(1004));
        	return mav;
        }
        
        if(hits == false){
        	mav.addObject(ERROR, error.generateError(1006));
        	return mav;
        }
        
        List<String> missingParamList=new ArrayList<String>();
        if(StringUtils.isEmpty(storeId) ){
        	missingParamList.add("store_id");
        }
        
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        try {
        	strId = Long.parseLong(storeId);
    	} catch (NumberFormatException nfe) {
    		mav.addObject(ERROR, error.generateError(2001));
    		return mav;
        }


    	try {
    		// get user
    		user = (User) keyResponse.get(USER);
    		

    		try {
    			// fetch store
    			Store cacheStore = storeService.findStoreById(strId);//.findAPIStoresByUserId(user.getId());
    			if(cacheStore == null){
    				mav.addObject(ERROR, error.generateError(2003));
    				return mav;
                }
    			if(cacheStore.getUserId().longValue() != user.getId().longValue()){
    				mav.addObject(ERROR, error.generateError(2004));
    				return mav;
            	}
                    
    			storeShipping = storeService.findAPIStoreShipping(cacheStore.getId());
    			storeTax = storeService.findAPIStoreTax(cacheStore.getId());
    			store = storeService.findAPIStoreById(cacheStore.getId());
            	} catch (Exception e) {
                    error = error.generateError(1004);
                    mav.addObject(ERROR, error);
                    return mav;
            	}
               
        	} catch (Exception e) {
                error = error.generateError(1004);
                mav.addObject(ERROR, error);
                return mav;
            }
            StoreApi strApi = new StoreApi();
            strApi.setStoreId(store.getId());
            strApi.setStoreName(store.getsSName());
            strApi.setCurrency(store.getsCurrency());
            strApi.setIncludetax(store.getbIncludeTax());
            strApi.setIncludeshipping(store.getbTaxShipping());
            List<ShippingApi> spList = new ArrayList<ShippingApi>();
            for (Shipping shipping : storeShipping.getShippingList()) {
                ShippingApi shpingApi = new ShippingApi();
                shpingApi.setShippingId(shipping.getId());
                shpingApi.setCountryId(shipping.getTerritoryId());
                shpingApi.setShippingState(shipping.getsState());
                shpingApi.setStateId(shipping.getStateId());
                shpingApi.setShippingCountry(shipping.getsCountry());
                shpingApi.setShippingState(shipping.getsState());
                shpingApi.setShippingAmountSingleProduct(shipping.getfAlone());
                shpingApi.setShippingAmountMultipleProduct(shipping.getfOthers());
                spList.add(shpingApi);
            }

            strApi.setShippingList(spList);
            List<TaxApi> txList = new ArrayList<TaxApi>();
            for (Tax tax : storeTax.getTaxList()) {
                TaxApi taxApi = new TaxApi();
                taxApi.setTaxId(tax.getId());
                taxApi.setTaxForCountry(tax.getsCountry());
                taxApi.setTaxForState(tax.getsState());
                taxApi.setTaxType(tax.getsType());
                taxApi.setTaxRate(tax.getfTax());
                txList.add(taxApi);
            }
            strApi.setTaxList(txList);
            mav.addObject(STORE, strApi);
            return mav;
       
    }

    /**
     * Service to fetch store shipping
     *
     * @param user_name
     * @param api_key
     * @param store_id
     * @return 'Store' object containing List of 'Shipping' objects in JSON or
     *         XML
     */

    @RequestMapping(value = "/store-shipping", method = RequestMethod.GET)
    public ModelAndView getStoreShipping(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "store_id", required = false) String storeId) {
        Store store = null;
        Store storeMain = null;
        ModelAndView mav = new ModelAndView();
        Error error = new Error();
        Long strId;
        User user = null;

        Map<String, Object> keyResponse = apiBO.validateApiKey(apiKey, userName);

        if (!(Boolean) keyResponse.get(VALID)) {
            	error = (Error) keyResponse.get(ERROR);// get error
            	mav.addObject(ERROR, error);
            	return mav;
        	}

        boolean hits = true;
        try{
        	hits = apiBO.validateStoreApiHits(apiKey);
        }catch(Exception e){
        	logger.error("While validating hits", e);
        	mav.addObject(ERROR, error.generateError(1004));
        	return mav;
        }
        
        if(hits == false){
        	mav.addObject(ERROR, error.generateError(1006));
        	return mav;
        }
        
        List<String> missingParamList=new ArrayList<String>();
        if(StringUtils.isEmpty(storeId) ){
        		missingParamList.add("store_id");
        	}
        
        if(missingParamList.size()>0){
        		error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
        		mav.addObject(ERROR, error);
        		return mav;
        	}
         
        try {
        	strId = Long.parseLong(storeId);
    		} catch (NumberFormatException nfe) {
    		mav.addObject(ERROR, error.generateError(2001));
    		return mav;
    		}

    	try {
    		// get user
    		user = (User) keyResponse.get(USER);
    		
    		// validate user
    		if (user == null) {
    			error = error.generateError(1003);
    			mav.addObject(ERROR, error);
    			return mav;
            	}

                try {
                    // fetch store
                	Store cacheStore = storeService.findStoreById(strId);//.findAPIStoresByUserId(user.getId());
        			if(cacheStore == null){
        				error.setErrorcode(2003);
        				error.setMessage("No store found with the specified store ID");
        				mav.addObject(ERROR, error);
        				return mav;
                    }
        			if(cacheStore.getUserId().longValue() != user.getId().longValue()){
        				mav.addObject(ERROR, error.generateError(2004));
        				return mav;
                	}
                    storeMain = storeService.findAPIStoreById(cacheStore.getId());
                    store = storeService.findAPIStoreShipping(cacheStore.getId());
                } catch (Exception e) {
                    error = error.generateError(1004);
                    mav.addObject(ERROR, error);
                    return mav;
                }
                
            } catch (Exception e) {
                error = error.generateError(1004);
                mav.addObject(ERROR, error);
                return mav;
            }
            StoreApi strApi = new StoreApi();
            strApi.setStoreId(store.getId());
            strApi.setStoreName(store.getsSName());
            strApi.setIncludeshipping(storeMain.getbTaxShipping());

            List<ShippingApi> spList = new ArrayList<ShippingApi>();
            for (Shipping shipping : store.getShippingList()) {
                ShippingApi shpingApi = new ShippingApi();
                shpingApi.setShippingId(shipping.getId());
                shpingApi.setCountryId(shipping.getTerritoryId());
                shpingApi.setStateId(shipping.getStateId());
                shpingApi.setShippingState(shipping.getsState());
                shpingApi.setShippingCountry(shipping.getsCountry());
                shpingApi.setShippingState(shipping.getsState());
                shpingApi.setShippingAmountSingleProduct(shipping.getfAlone());
                shpingApi.setShippingAmountMultipleProduct(shipping.getfOthers());
                spList.add(shpingApi);
            }

            strApi.setShippingList(spList);
            mav.addObject(STORE, strApi);
            return mav;
        
    }

    /**
     * Service to fetch store tax
     *
     * @param user_name
     * @param api_key
     * @param store_id
     * @return 'Store' object containing List of 'Tax' objects in JSON or XML
     */

    @RequestMapping(value = "/store-tax", method = RequestMethod.GET)
    public ModelAndView getStoreTax(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "store_id", required = false) String storeId) {
        Store store = null;
        Store storeMain = null;
        ModelAndView mav = new ModelAndView();
        Error error = new Error();
        Long strId;
        User user = null;
        
        Map<String, Object> keyResponse = apiBO.validateApiKey(apiKey, userName);

        if (!(Boolean) keyResponse.get(VALID)) {
            	error = (Error) keyResponse.get(ERROR);// get error
            	mav.addObject(ERROR, error);
            	return mav;
        	}
        
        boolean hits = true;
        try{
        	hits = apiBO.validateStoreApiHits(apiKey);
        }catch(Exception e){
        	logger.error("While validating hits", e);
        	mav.addObject(ERROR, error.generateError(1004));
        	return mav;
        }
        
        if(hits == false){
        	mav.addObject(ERROR, error.generateError(1006));
        	return mav;
        }
        
        List<String> missingParamList=new ArrayList<String>();
        if(StringUtils.isEmpty(storeId) ){
        		missingParamList.add("store_id");
        	}
        
        if(missingParamList.size()>0){
        		error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
        		mav.addObject(ERROR, error);
        		return mav;
        	}

        try {
        		strId = Long.parseLong(storeId);
        	} catch (NumberFormatException nfe) {
        		mav.addObject(ERROR, error.generateError(2001));
                return mav;
            }


        	try {
                // get user
                user = (User) keyResponse.get(USER);
                try {
                    // fetch store
                	Store cacheStore = storeService.findStoreById(strId);//.findAPIStoresByUserId(user.getId());
        			if(cacheStore == null){
        				mav.addObject(ERROR, error.generateError(2003));
        				return mav;
                    }
        			if(cacheStore.getUserId().longValue() != user.getId().longValue()){
        				mav.addObject(ERROR, error.generateError(2004));
        				return mav;
                	}
                    
                    storeMain = storeService.findAPIStoreById(cacheStore.getId());
                    store = storeService.findAPIStoreTax(cacheStore.getId());
                	} catch (Exception e) {
                		error.setErrorcode(1004);
                		mav.addObject(ERROR, error);
                		return mav;
                		}
              } catch (Exception e) {
            	  	error.setErrorcode(1004);
          			mav.addObject(ERROR, error);
          			return mav;
            }

            StoreApi strApi = new StoreApi();
            strApi.setStoreId(store.getId());
            strApi.setStoreName(store.getsSName());
            strApi.setIncludetax(storeMain.getbIncludeTax());
            List<TaxApi> txList = new ArrayList<TaxApi>();
            for (Tax tax : store.getTaxList()) {
                TaxApi taxApi = new TaxApi();
                taxApi.setTaxId(tax.getId());
                taxApi.setTaxForCountry(tax.getsCountry());
                taxApi.setTaxForState(tax.getsState());
                taxApi.setTaxType(tax.getsType());
                taxApi.setTaxRate(tax.getfTax());
                txList.add(taxApi);
            }
            strApi.setTaxList(txList);
            mav.addObject(STORE, strApi);
            return mav;
    }

/*******************************STORE SERVICES END************************************/


/*******************************DEPARTMENT SERVICES START************************************/

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

        List<Department> departments = null;
        List<DepartmentApi> departmentApiList = new ArrayList<DepartmentApi>();
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

			departments = storeService.findDepartmentsByStore(streId);
			
        	if (departments == null || departments.size() <= 0) {
        		mav.addObject(ERROR, error.generateError(6001));
        		return mav;
            	}

            } catch (Exception e) {
                error = error.generateError(1004);
                mav.addObject(ERROR, error);
                return mav;
            }

            MainDepartmentApi mainDep = new MainDepartmentApi();
            for (Department department : departments) {
                DepartmentApi departmentApi = new DepartmentApi();
               // departmentApi.setDepartmentId(department.getId());
                departmentApi.setDepartmentName(department.getsName());
                departmentApi.setDepartmentStatus(department.getsStatus());
                departmentApiList.add(departmentApi);
            }
            mainDep.setStoreId(store.getId());
            mainDep.setSize(departmentApiList.size());
            mainDep.setDepartments(departmentApiList);
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
        
        if(StringUtils.isEmpty(departmentName) ){
        	missingParamList.add("department_name");
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
        	department.setsName(departmentName);
        	department.setsStatus(departmentStatus);
        	
        	Long departmentId = null;
        	try{
        		departmentId = storeService.createDepartment(department);
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
        
        if(StringUtils.isEmpty(departmentName) ){
        	missingParamList.add("department_name");
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
        	
        	if(department.getUserId().longValue() != user.getId().longValue()){
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
                
    			if(department.getUserId().longValue() != user.getId().longValue()){
    				mav.addObject(ERROR, error.generateError(6007));
    				return mav;
            		}
    			
    			List<Category> categories = storeService.findCategoriesByDepartment(depttId);
                if(categories != null && categories.size() > 0){
    				mav.addObject(ERROR, error.generateError(6008));
    				return mav;
            		
                }
                
                List<Product> products = storeService.findProductsByDepartment(depttId);
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


/*******************************DEPARTMENT SERVICES END************************************/

/*******************************USER SERVICES**********************************************/


    /**
     * Service to get Merchant Profile
     *
     * @param user_name
     * @param api_key
     * @return List of Product Orders in JSON or XML
     */

    @RequestMapping(value = "/merchant-profile", method = RequestMethod.GET)
    public ModelAndView getMerchantProfile(
            @RequestParam(value = "user_name", required = true) String userName,
            @RequestParam(value = "api_key", required = true) String apiKey) {

        ModelAndView mav = new ModelAndView();
        Error error = new Error();
        User user = null;


        try {

        	// validate key
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

        	// get user
        	user = (User) key_response.get(USER);


            } catch (Exception e) {
                error = error.generateError(1004);
                mav.addObject(ERROR, error);
                return mav;
                //e.printStackTrace();
            	}
            
            Address address = null;
            Store store = null;
            try{    
	            address = userService.findAddressByUserId(user.getId());
	            store = storeService.findStoreByUserId(user.getId());
            }catch(Exception e){
            	error = error.generateError(1004);
                mav.addObject(ERROR, error);
                return mav;
            }
	            UserApi userApi = new UserApi();
	            userApi.setMerchantId(user.getId());
	            userApi.setFirstName(user.getsFirstName());
	            userApi.setLastName(user.getsLastName());
	            userApi.setMerchantEmail(user.getUsername());
	            userApi.setAddress(address.getsAddress());
	        	userApi.setCity(address.getsCity());
	        	userApi.setState(address.getsState());
	        	userApi.setZipCode(address.getsZip());
	        	userApi.setCountry(address.getsCountry());
	        	userApi.setCompanyRegNo(user.getsCompanyRegNo());
	        	userApi.setCompanyTaxRegNo(user.getsTaxRegNo());
	        	userApi.setCompanyLogoUrl(user.getsCompanyLogo());
	        	userApi.setMerchantCurrency(user.getsCurrency());
	        	userApi.setMerchantStoreName(user.getStoreName());
	    		userApi.setPayPalEmailAddress(user.getsPaypalAddress());
	    		userApi.setOrderEmailAddress(store.getsOrderEmail());


            mav.addObject(APP_MERCHANT, userApi);
            return mav;
        
    }

//
//    /**
//     * Service to get Merchant Profile
//     *
//     * @param user_name
//     * @param api_key
//     * @return List of Product Orders in JSON or XML
//     */
//
//    @RequestMapping(value = "/update-merchant-profile-logo", method = RequestMethod.POST)
//    public ModelAndView updateMerchantProfileWithComLogo(
//            @RequestParam(value = "user_name", required = false) String userName,
//            @RequestParam(value = "api_key", required = false) String apiKey,
//            @RequestParam(value = "firstName", required = false) String firstName,
//            @RequestParam(value = "lastName", required = false) String lastName,
//            @RequestParam(value = "companyLogo", required = false) MultipartFile companyLogo,
//            @RequestParam(value = "companyWebsite", required = false) String companyWebsite,
//            @RequestParam(value = "payPalAddress", required = false) String payPalAddress,
//            @RequestParam(value = "companyRegNumber", required = false) String companyRegNumber,
//            @RequestParam(value = "taxRegNumber", required = false) String taxRegNumber) {
//
//        ModelAndView mav = new ModelAndView();
//        Message message = new Message();
//        Error error = new Error();
//
//        User user = null;
//
//        if (userName == null || userName.equals("") || apiKey == null || apiKey.equals("") || firstName == null || firstName.equals("")) {
//            error = new Error(1001, Error.MISSING_PARAMETERS);
//            mav.addObject(ERROR, error);
//            return mav;
//        } else {
//
//
//            try {
//
//                // validate key
//                Map<String, Object> key_response = apiBO.validateApiKey(apiKey, userName);
//
//                if (!(Boolean) key_response.get(VALID)) {
//                    error = (Error) key_response.get(ERROR);// get error
//                    mav.addObject(ERROR, error);
//                    return mav;
//                }
//
//                // get user
//                user = (User) key_response.get(USER);
//
//                // validate user
//                if (user == null) {
//                    error = error.generateError(1002);
//                    mav.addObject(ERROR, error);
//                    return mav;
//                }
//                if (firstName != null && !firstName.equals(""))
//                    user.setsFirstName(firstName);
//                if (lastName != null && !lastName.equals(""))
//                    user.setsLastName(lastName);
//                if (companyWebsite != null && !companyWebsite.equals(""))
//                    user.setsCompanyLogoWebsite(companyWebsite);
//                if (payPalAddress != null && !payPalAddress.equals(""))
//                    user.setsPaypalAddress(payPalAddress);
//                if (companyRegNumber != null && !companyRegNumber.equals(""))
//                    user.setsCompanyRegNo(companyRegNumber);
//                if (taxRegNumber != null && !taxRegNumber.equals(""))
//                    user.setsTaxRegNo(taxRegNumber);
//                try {
//                    //user.setsCompanyLogo(companyLogo);
//
//                    if (companyLogo != null && companyLogo.getSize() > 0) {
//
//                        MultipartFile companyLogoFile = companyLogo;
//
//                        PathLocator pathLocator = new PathLocator();
//                        // change to online server for the time being
//                        String rootPath = pathLocator.getContextPath();
//
//                        String mobicartImagesFolderPath = "mobicartimages";
//                        FileUtils.makeDirectoryIfItsNotThere(rootPath
//                                + mobicartImagesFolderPath);
//
//                        String companyFolderPath = "/company";
//                        FileUtils.makeDirectoryIfItsNotThere(rootPath
//                                + mobicartImagesFolderPath + companyFolderPath);
//
//                        //hard code
//                        String userFolderPath = "/" + user.getId();
//                        FileUtils.makeDirectoryIfItsNotThere(rootPath
//                                + mobicartImagesFolderPath + companyFolderPath
//                                + userFolderPath);
//
//                        String dateWiseFolderPath = "/" + DateTimeUtils.getCurrentDate();
//                        FileUtils.makeDirectoryIfItsNotThere(rootPath
//                                + mobicartImagesFolderPath + companyFolderPath
//                                + userFolderPath + dateWiseFolderPath);
//
//                        String finalCompanyLogoImagePath = mobicartImagesFolderPath
//                                + companyFolderPath + userFolderPath + dateWiseFolderPath;
//
//                        String companyLogoImageFileName = FileUtils.cleanSpecialChars(companyLogoFile.getOriginalFilename());
//
//                        String websiteCompanyLogoImagePath = finalCompanyLogoImagePath + "/" + FileUtils.stuffedFilename(companyLogoImageFileName, "_website");
//
//                        finalCompanyLogoImagePath += "/"
//                                + companyLogoImageFileName;
//
//                        try {
//                            InputStream companyLogoInputStream = null;
//                            OutputStream companyLogoOutputStream = null;
//                            OutputStream companyLogoWebsiteOutputStream = null;
//                            if (companyLogoFile.getSize() > 0) {
//                                companyLogoInputStream = companyLogoFile.getInputStream();
//                                BufferedImage bufferedImage = ImageIO
//                                        .read(companyLogoInputStream);
//                                BufferedImage websiteBufferedImage = ImageUtils.resize(bufferedImage,
//                                        220, 90, true);
//
//                                BufferedImage iphoneBufferedImage = ImageUtils.resize(bufferedImage,
//                                        110, 30, true);
//
//                                companyLogoOutputStream = new FileOutputStream(rootPath
//                                        + finalCompanyLogoImagePath);
//                                companyLogoWebsiteOutputStream = new FileOutputStream(rootPath
//                                        + websiteCompanyLogoImagePath);
//                                String format = (companyLogoFile.getOriginalFilename()
//                                        .endsWith(".png")) ? "png" : "jpg";
//                                ImageIO.write(iphoneBufferedImage, format, companyLogoOutputStream);
//                                ImageIO.write(websiteBufferedImage, format, companyLogoWebsiteOutputStream);
//
//
//                                ImageUtils.saveImage(iphoneBufferedImage, rootPath
//                                        + finalCompanyLogoImagePath);
//
//                                ImageUtils.saveImage(websiteBufferedImage, rootPath
//                                        + websiteCompanyLogoImagePath);
//
//                                companyLogoInputStream.close();
//                                companyLogoOutputStream.close();
//                                companyLogoWebsiteOutputStream.close();
//                                user.setsCompanyLogo("/" + finalCompanyLogoImagePath);
//                            }
//                        } catch (IOException ioe) {
//                            ioe.printStackTrace();
//                            String errorMessage = "Some error occured saving company image";
//                            error.setMessage(errorMessage);
//                            mav.addObject(ERROR, error);
//                            return mav;
//
//                        } catch (Exception e) {
//                            String errorMessage = "Some error occured saving company image";
//                            error.setMessage(errorMessage);
//                            mav.addObject(ERROR, error);
//                            return mav;
//                        }
//                    }
//
//                    adminService.updateMercahntProfile(user);
//                } catch (Exception e) {
//                    String errorMessage = "Profile Could Not Be Updated.";
//                    error.setMessage(errorMessage);
//                    mav.addObject(ERROR, error);
//                    return mav;
//                }
//
//            } catch (Exception e) {
//                error = error.generateError(1002);
//                mav.addObject(ERROR, error);
//                return mav;
//            }
//
//            message.setMessage("Profile Updated Successfully");
//            mav.addObject(MESSAGE, message);
//            return mav;
//        }
//    }


    
    
    
    
    
    /**
     * Service to update Merchant Profile
     *
     * @param user_name
     * @param api_key
     * @param firstName
     * @param lastName
     * @param companyLogoUrl
     * @param companyWebsite
     * @param payPalAddress
     * @param companyRegNumber
     * @param taxRegNumber
     * @return Update Merchant Profile Status String
     */

    @RequestMapping(value = "/update-merchant-profile", method = RequestMethod.POST)
    public ModelAndView updateMerchantProfile(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "companyLogoUrl", required = false) String companyLogoUrl,
            @RequestParam(value = "companyWebsite", required = false) String companyWebsite,
            @RequestParam(value = "payPalAddress", required = false) String payPalAddress,
            @RequestParam(value = "companyRegNumber", required = false) String companyRegNumber,
            @RequestParam(value = "taxRegNumber", required = false) String taxRegNumber) {

        ModelAndView mav = new ModelAndView();
        Message message = new Message();
        Error error = new Error();
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
        
        if(StringUtils.isEmpty(firstName) ){
        	missingParamList.add("firstName");
        }
        
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        if( ! StringUtils.isEmpty(companyLogoUrl) ){
			if( ! companyLogoUrl.startsWith("http://")){
				mav.addObject(ERROR, error.generateError(3015));
				return mav;
	    	}
	    		
	    	if(!(companyLogoUrl.endsWith("png") || companyLogoUrl.endsWith("jpg") || companyLogoUrl.endsWith("jpeg")
	    			|| companyLogoUrl.endsWith("bmp") || companyLogoUrl.endsWith("gif"))){
	    		mav.addObject(ERROR, error.generateError(3016));
	    		return mav;
	    	}
		}
		

            try {
                // get user
                user = (User) key_response.get(USER);
                if (firstName != null && !firstName.equals(""))
                    user.setsFirstName(firstName);
                if (lastName != null && !lastName.equals(""))
                    user.setsLastName(lastName);
                if (companyWebsite != null && !companyWebsite.equals(""))
                    user.setsCompanyLogoWebsite(companyWebsite);
                if (payPalAddress != null && !payPalAddress.equals(""))
                    user.setsPaypalAddress(payPalAddress);
                if (companyRegNumber != null && !companyRegNumber.equals(""))
                    user.setsCompanyRegNo(companyRegNumber);
                if (taxRegNumber != null && !taxRegNumber.equals(""))
                    user.setsTaxRegNo(taxRegNumber);
                try {
                    //user.setsCompanyLogo(companyLogo);

                    if ( ! StringUtils.isEmpty(companyLogoUrl)) {

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
                        String userFolderPath = "/" + user.getId();
                        FileUtils.makeDirectoryIfItsNotThere(rootPath
                                + mobicartImagesFolderPath + companyFolderPath
                                + userFolderPath);

                        String dateWiseFolderPath = "/" + DateTimeUtils.getCurrentDate();
                        FileUtils.makeDirectoryIfItsNotThere(rootPath
                                + mobicartImagesFolderPath + companyFolderPath
                                + userFolderPath + dateWiseFolderPath);

                        String finalCompanyLogoImagePath = mobicartImagesFolderPath
                                + companyFolderPath + userFolderPath + dateWiseFolderPath;
                        
                        int fileNameIndex = companyLogoUrl.lastIndexOf("/");
                		String fileName = companyLogoUrl.substring(fileNameIndex);

                        String companyLogoImageFileName = FileUtils.cleanSpecialChars(fileName);

                        String websiteCompanyLogoImagePath = finalCompanyLogoImagePath + "/" + FileUtils.stuffedFilename(companyLogoImageFileName, "_website");

                        finalCompanyLogoImagePath += "/"
                                + companyLogoImageFileName;

                        
                        URL urlOfImage = null;
                        try {
                        	urlOfImage = new URL(companyLogoUrl);
                        	
                            InputStream companyLogoInputStream = null;
                            OutputStream companyLogoOutputStream = null;
                            OutputStream companyLogoWebsiteOutputStream = null;
                            companyLogoInputStream = urlOfImage.openStream();
                            BufferedImage bufferedImage = ImageIO.read(companyLogoInputStream);
                        	BufferedImage websiteBufferedImage = ImageUtils.resize(bufferedImage, 220, 90, true);
                        	BufferedImage iphoneBufferedImage = ImageUtils.resize(bufferedImage, 110, 30, true);

                                companyLogoOutputStream = new FileOutputStream(rootPath
                                        + finalCompanyLogoImagePath);
                                companyLogoWebsiteOutputStream = new FileOutputStream(rootPath
                                        + websiteCompanyLogoImagePath);
                                String format = (companyLogoUrl.endsWith(".png")) ? "png" : "jpg";
                                ImageIO.write(iphoneBufferedImage, format, companyLogoOutputStream);
                                ImageIO.write(websiteBufferedImage, format, companyLogoWebsiteOutputStream);


                                ImageUtils.saveImage(iphoneBufferedImage, rootPath
                                        + finalCompanyLogoImagePath);

                                ImageUtils.saveImage(websiteBufferedImage, rootPath
                                        + websiteCompanyLogoImagePath);

                                companyLogoInputStream.close();
                                companyLogoOutputStream.close();
                                companyLogoWebsiteOutputStream.close();
                                user.setsCompanyLogo("/" + finalCompanyLogoImagePath);
                         
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                            mav.addObject(ERROR, error.generateError(8001));
                            return mav;

                        } catch (Exception e) {
                            mav.addObject(ERROR, error.generateError(8001));
                            return mav;
                        }
                    }

                    adminService.updateMercahntProfile(user);
                } catch (Exception e) {
                    mav.addObject(ERROR, error.generateError(1004));
                    return mav;
                }

            } catch (Exception e) {
                mav.addObject(ERROR, error.generateError(1004));
                return mav;
            }

            message.setMessage("Profile Updated Successfully.");
            mav.addObject(MESSAGE, message);
            return mav;
        
    }

	
	
	
	

    
    
    
    
    
    
    
    
    
    /**
     * Service to fetch orders for user
     *
     * @param user_name
     * @param api_key
     * @return List of Product Orders in JSON or XML
     */

    @RequestMapping(value = "/all-orders", method = RequestMethod.GET)
    public ModelAndView getAllOrders(
            @RequestParam(value = "user_name", required = true) String userName,
            @RequestParam(value = "api_key", required = true) String apiKey) {

        List<ProductOrder> productOrders = null;
        ModelAndView mav = new ModelAndView();
        Error error = new Error();

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
        
    	try {
			// get user
            user = (User) key_response.get(USER);
        

            productOrders = adminService.findAPIProductOrderByUser(user.getId());
			
        	if (productOrders == null || productOrders.size() <= 0) {
        		mav.addObject(ERROR, error.generateError(5006));
        		return mav;
            	}

            } catch (Exception e) {
                error = error.generateError(1004);
                mav.addObject(ERROR, error);
                return mav;
            }


            List<ProductOrderApi> productOrderApiList = new ArrayList<ProductOrderApi>();
            for (ProductOrder productOrder : productOrders) {
                ProductOrderApi productApi = new ProductOrderApi();
                productApi.setOrderId(productOrder.getId());
                productApi.setStoreId(productOrder.getStoreId());
                productApi.setMerchantPayPalEmail(productOrder.getsMerchantPaypalEmail());
                productApi.setOrderAmount(productOrder.getfAmount());
                productApi.setOrderTaxAmount(productOrder.getfTaxAmount());
                productApi.setOrderGrandTotalAmount(productOrder.getfTotalAmount());
                productApi.setBuyerName(productOrder.getsBuyerName());
                productApi.setBuyerEmail(productOrder.getsBuyerEmail());
                productApi.setShippingStreet(productOrder.getsShippingStreet());
                productApi.setShippingCity(productOrder.getsShippingCity());
                productApi.setShippingState(productOrder.getsShippingState());
                productApi.setShippingPostalCode(productOrder.getsShippingPostalCode());
                productApi.setShippingCountry(productOrder.getsShippingCountry());
                productApi.setBillingStreet(productApi.getBillingStreet());
                productApi.setBillingCity(productApi.getBillingCity());
                productApi.setBillingState(productApi.getBillingState());
                productApi.setBillingPostalCode(productApi.getBillingPostalCode());
                productApi.setBillingCountry(productApi.getBillingCountry());
                productApi.setOrderStatus(productApi.getOrderStatus());
                productApi.setOrderDate(productApi.getOrderDate());
                productOrderApiList.add(productApi);

            }
            MainOrderApi mainOrder = new MainOrderApi();
            mainOrder.setOrders(productOrderApiList);
            mainOrder.setSize(productOrders.size());
            mav.addObject(PRODUCT_ORDER_LIST, mainOrder);
            return mav;
    }

/*******************************USER SERVICES END**********************************************/


    // Jaxb2Marshaller unmarshaller = new Jaxb2Marshaller();
    //   unmarshaller.setContextPath("com.mobicart.model");
    //   unmarshaller.setSchema(new ClassPathResource("com/mobicart/flight.xsd"));
    //   try {
    //		unmarshaller.afterPropertiesSet();
    //	} catch (Exception e1) {
    //		// TODO Auto-generated catch block
    //		e1.printStackTrace();
    //	}


    /*			
	   @RequestMapping(value = "/image-check",headers="Accept=application/xml,plain/text")
	    public ModelAndView addPerson(@RequestBody String body) throws XMLStreamException {
		   ModelAndView mav = new ModelAndView(); 
		   Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		   PathLocator pathLocator = new PathLocator();
		   //newInstance( contextPath, Thread.currentThread().getContextClassLoader() ); 
		   //String rootPath = pathLocator.getContextPath()+"mobicart";
		   //try {
			   
		        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		        XMLStreamReader streamReader;
					JAXBContext context;
				        org.springframework.core.io.Resource schemaResource = new ClassPathResource("com/mobicart/model/Flights.xsd");
				        org.springframework.core.io.Resource schemaResource1 = new ClassPathResource("com/mobicart/model/Flights.xml");
					      
				        File schema;
				        File schema1;
						try {
							schema = schemaResource.getFile();
							schema1 = schemaResource1.getFile();
							context = JAXBContext.newInstance(Flights.class);
							Unmarshaller unmarshaller = context.createUnmarshaller();
							SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
					        unmarshaller.setSchema(sf.newSchema(schema));						
							streamReader = inputFactory.createXMLStreamReader(new StringReader(body));
							StaxSource source = new StaxSource(streamReader);
							Flights person = (Flights) unmarshaller.unmarshal(schema1);
					        //Flights project = (Project) unmarshaller.unmarshal(new File("src/test/resources/com/acme/model/project/Project.xml"));						
						
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		        
			   
			  // Source source = new StreamSource(new StringReader(body));
			URL url;
			try {
				url = new URL( "http://localhost:8080/mobicart/api/store-tax.xml?api_key=wqoiweruwer46Ht&store_id=1");
				
			*/
    /* DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			       dbf.setNamespaceAware(true);
			       DocumentBuilder db = dbf.newDocumentBuilder();
			       Document doc = db.parse(new File( "nosferatu.xml"));
			       */
    /*			
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		//} catch (JAXBException e2) {
			// TODO Auto-generated catch block
		//	e2.printStackTrace();
		//}
		   //jaxb2Marshaller.setContextPath(Billing.class.toString());
		   try {
			//jaxb2Marshaller.afterPropertiesSet();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		   
		   
		  // Source source = new StreamSource(new StringReader(body));
			//Object e =  jaxb2Marshaller.unmarshal(source);

			//System.out.println(source);
	        mav.addObject("message", "created xml");
	        return mav;
	    }    

	*/


    /*******************************SUB DEPARTMENT SERVICES**********************************************/


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
			if(department.getUserId().longValue() != user.getId().longValue()){
				mav.addObject(ERROR, error.generateError(6007));
				return mav;
        	}

			categories = storeService.findCategoriesByDepartment(depttId);
			
        	if (categories == null || categories.size() <= 0) {
        		mav.addObject(ERROR, error.generateError(6004));
        		return mav;
            	}

            } catch (Exception e) {
                error = error.generateError(1004);
                mav.addObject(ERROR, error);
                return mav;
            }
            MainCategoryApi mainCategory = new MainCategoryApi();
            for (Category category : categories) {
                CategoryApi categoryApi = new CategoryApi();
                categoryApi.setSubDepartmentId(category.getId());
                categoryApi.setSubDepartmentName(category.getsName());
                categoryApi.setSubDepartmentStatus(category.getsStatus());
                categoryApi.setProductCount(category.getiProductCount());
                categoryApi.setParentSubDepartmentId(category.getParentCategoryId());
                //categoryApi.setChildSubDepartments(category.getCategories());
                categoriesApiList.add(categoryApi);

            }
            mainCategory.setSize(categoriesApiList.size());
            mainCategory.setDepartmentId(department.getId());
            mainCategory.setStoreId(department.getStoreId());
            mainCategory.setSubDepartments(categoriesApiList);
            mav.addObject(DEPARTMENT_CATEGORIES, categories);
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
        
        if(StringUtils.isEmpty(categoryName) ){
        	missingParamList.add("category_name");
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
        	
        	if(department.getUserId().longValue() != user.getId().longValue()){
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
            @RequestParam(value = "parent_category_id", required = false) String parentCategoryId,
            @RequestParam(value = "category_name", required = false) String categoryName,
            @RequestParam(value = "category_status", required = false) String categoryStatus) {
        
    	Department department = null;
        ModelAndView mav = new ModelAndView();
        Message message = new Message();
        Error error = new Error();
        User user = null;
        Long depttId;
        Long prntCtgryId;

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
        
        if(StringUtils.isEmpty(categoryName) ){
        	missingParamList.add("category_name");
        }
  
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        if(StringUtils.isEmpty(parentCategoryId) )
        	parentCategoryId = "0";
        
        if(StringUtils.isEmpty(categoryStatus) )
        	categoryStatus = "true";
        
        try {
        	depttId = Long.parseLong(departmentId);
    		} catch (NumberFormatException nfe) {
    			mav.addObject(ERROR, error.generateError(6002));
    			return mav;
    		}
		try {
			prntCtgryId = Long.parseLong(parentCategoryId);
			} catch (NumberFormatException nfe) {
				mav.addObject(ERROR, error.generateError(7010));
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
        	
        	if(department.getUserId().longValue() != user.getId().longValue()){
        		mav.addObject(ERROR, error.generateError(6007));
        		return mav;
        	}
        	
        	List<Product> depttProducts = storeService.findProductsByDepartment(depttId);
        	if(depttProducts != null && depttProducts.size() > 0){
				mav.addObject(ERROR, error.generateError(7011));
				return mav;
        		
            }
        	if(prntCtgryId != 0){
        		Category parentCategory = storeService.findCategory(prntCtgryId);
            	if (parentCategory == null) {
            		mav.addObject(ERROR, error.generateError(7013));
            		return mav;
                    }
            	if(parentCategory.getDepartmentId().longValue() != depttId.longValue()){
            		mav.addObject(ERROR, error.generateError(7014));
            		return mav;
            	}
            	
            	List<Product> prntCtgryProducts = storeService.findProductsByCategory(prntCtgryId);
            	if(prntCtgryProducts != null && prntCtgryProducts.size() > 0){
    				mav.addObject(ERROR, error.generateError(7012));
    				return mav;
            		
                }
            	
        	}
        	
        	// add sub-department 
        	Category category = new Category();
        	category.setDepartmentId(depttId);
        	category.setParentCategoryId(prntCtgryId);
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
        
        if(StringUtils.isEmpty(categoryName) ){
        	missingParamList.add("categoryName");
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
        	
        	if(department.getUserId().longValue() != user.getId().longValue()){
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
            @RequestParam(value = "parent_category_id", required = false) String parentCategoryId,
            @RequestParam(value = "category_name", required = false) String categoryName,
            @RequestParam(value = "category_status", required = false) String categoryStatus) {


    	Category category = null;
    	ModelAndView mav = new ModelAndView();
        Message message = new Message();
        Error error = new Error();
        User user = null;
        Long ctgryId;
        Long prntCtgryId;

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
        
        if(StringUtils.isEmpty(categoryName) ){
        	missingParamList.add("category_name");
        }
  
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        if(StringUtils.isEmpty(parentCategoryId) )
        	parentCategoryId = "0";
        
        if(StringUtils.isEmpty(categoryStatus) )
        	categoryStatus = "true";
        
        try {
        	ctgryId = Long.parseLong(categoryId);
    		} catch (NumberFormatException nfe) {
    			mav.addObject(ERROR, error.generateError(7002));
    			return mav;
    		}
		try {
			prntCtgryId = Long.parseLong(parentCategoryId);
			} catch (NumberFormatException nfe) {
				mav.addObject(ERROR, error.generateError(7010));
				return mav;
			}


		try {
			// get user
    		user = (User) key_response.get(USER);
    	
            // fetch department 
    		category = storeService.findCategory(ctgryId);

        	if (category == null) {
        		mav.addObject(ERROR, error.generateError(7003));
        		return mav;
                }
        	
        	Long departmentId = category.getDepartmentId();
        	Department department = storeService.findDepartment(departmentId);
        	
        	if(department.getUserId().longValue() != user.getId().longValue()){
        		mav.addObject(ERROR, error.generateError(7004));
        		return mav;
        	}
        	
        	if(prntCtgryId != 0){
        		Category parentCategory = storeService.findCategory(prntCtgryId);
            	if (parentCategory == null) {
            		mav.addObject(ERROR, error.generateError(7013));
            		return mav;
                    }
            	if(parentCategory.getDepartmentId().longValue() != departmentId.longValue()){
            		mav.addObject(ERROR, error.generateError(7015));
            		return mav;
            		}
            	
            	List<Product> parentCategoryProducts = storeService.findProductsByCategory(prntCtgryId);
            	if(parentCategoryProducts != null && parentCategoryProducts.size() > 0){
            		mav.addObject(ERROR, error.generateError(7016));
            		return mav;
            	}
            	
        	}
        	
        	
        	// add sub-department 
        	category.setParentCategoryId(prntCtgryId);
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

        Category category = null;
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
                category = storeService.findCategory(ctgryId);
                
                if(category == null){
    				mav.addObject(ERROR, error.generateError(7003));
    				return mav;
                }
                
                Long depttId = category.getDepartmentId();
                Department department = storeService.findDepartment(depttId);
                
    			if(department.getUserId().longValue() != user.getId().longValue()){
    				mav.addObject(ERROR, error.generateError(7004));
    				return mav;
            		}
    			
    			List<Category> categories = category.getCategories();
                if(categories != null && categories.size() > 0){
    				mav.addObject(ERROR,	 error.generateError(7006));
    				return mav;
            		
                }
                
                List<Product> products = storeService.findProductsByCategory(ctgryId);
                if(products != null && products.size() > 0){
    				mav.addObject(ERROR, error.generateError(7007));
    				return mav;
            		
                }
                
                } catch (Exception e) {
                	logger.error("Error in finding", e);
                    error = error.generateError(1004);
                    mav.addObject(ERROR, error);
                    return mav;
                }
                
                try{
                	storeService.deleteCategory(category);
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
     * Service to delete sub-department
     *
     * @param user_name
     * @param api_key
     * @param category_id
     * @return Status message regarding deletion of category
     */

//    @RequestMapping(value = "/delete-subDepartment", method = RequestMethod.DELETE)
//    public ModelAndView deleteSubDepartment(
//            @RequestParam(value = "user_name", required = false) String userName,
//            @RequestParam(value = "api_key", required = false) String apiKey,
//            @RequestParam(value = "category_id", required = false) String categoryId) {
//
//        Category category = null;
//        ModelAndView mav = new ModelAndView();
//        Error error = new Error();
//        Message message = new Message();
//        Long ctgryId;
//        User user = null;
//
//        if (userName == null || userName.equals("") || apiKey == null || apiKey.equals("") || categoryId == null || categoryId.equals("")) {
//            error = new Error(1001, Error.MISSING_PARAMETERS);
//            mav.addObject(ERROR, error);
//            return mav;
//        } else {
//            try {
//                ctgryId = Long.parseLong(categoryId);
//            } catch (NumberFormatException nfe) {
//                error = new Error(7002, "Sub-DepartmentId must be numeric.");
//                mav.addObject(ERROR, error);
//                return mav;
//            }
//
//
//            try {
//                // validate key
//                Map<String, Object> key_response = apiBO.validateApiKey(apiKey, userName);
//
//                if (!(Boolean) key_response.get(VALID)) {
//                    error = (Error) key_response.get(ERROR);// get error
//                    mav.addObject(ERROR, error);
//                    return mav;
//                }
//
//                // get user
//                user = (User) key_response.get(USER);
//
//                // validate user
//                if (user == null) {
//                    error = error.generateError(1002);
//                    mav.addObject(ERROR, error);
//                    return mav;
//                }
//
//                // fetch department
//                category = storeService.findCategory(ctgryId);
//
//                if (category == null) {
//                    String errorMessage = "Sub-Department with id " + ctgryId + " does not exist";
//                    error = new Error(7004, errorMessage);
//                    mav.addObject(ERROR, error);
//                    return mav;
//                }
//
//
//                Long departmentId = category.getDepartmentId();
//                Department department = storeService.findDepartment(departmentId);
//                List<Category> categories = storeService.findCategoriesByDepartment(departmentId);
//                for (Category cat : categories) {
//                    if (cat.getParentCategoryId().longValue() == category.getId().longValue()) {
//                        error = error.generateError(7005);// return error message
//                        String errorMessage = "This SubDepartment has got some child SubDepartments.";
//                        error = new Error(7005, errorMessage);
//                        mav.addObject(ERROR, error);
//                        return mav;
//                    }
//                }
//                if (String.valueOf(user.getId()).equalsIgnoreCase(String.valueOf(department.getUserId()))) {
//
//                    //delete department
//                    try {
//                        storeService.deleteCategory(category);
//                    } catch (Exception exp) {
//                        error = error.generateError(7005);// return error message
//                        String errorMessage = "Products with sub-department id " + ctgryId + " exists, please delete them first.";
//                        error = new Error(7005, errorMessage);
//                        mav.addObject(ERROR, error);
//                        return mav;
//                    }
//
//                    message.setMessage("Sub-Department Deleted Successfully");
//                    mav.addObject(MESSAGE, message);
//                    return mav;
//                } else {
//                    error = error.generateError(1002);// return error message
//                    mav.addObject(ERROR, error);
//                    return mav;
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                String errorMessage = "Products exists under this sub-department.";
//                error = new Error(7004, errorMessage);
//                mav.addObject(ERROR, mav);
//                return mav;
//            }
//
//        }
//    }


    /*******************************SUB DEPARTMENT SERVICES ENDS**********************************************/


    /**
     * Service to get Order History
     *
     * @param user_name
     * @param api_key
     */

    @RequestMapping(value = "/order-history", method = RequestMethod.GET)
    public ModelAndView getOrderHistory(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey) {

        ModelAndView mav = new ModelAndView();
        Error error = new Error();
        User user = null;
        Store store = null;
        List<ProductOrder> productOrders = null;

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
        
    	try {
			// get user
            user = (User) key_response.get(USER);
            // validate user
            if (user == null) {
            	error = error.generateError(1003);
            	mav.addObject(ERROR, error);
            	return mav;
                }

            store = storeService.findStoreByUserId(user.getId());
            productOrders = adminService.getProductOrderHistoryAPI(store.getId());
			
        	if (productOrders == null || productOrders.size() <= 0) {
        		mav.addObject(ERROR, error.generateError(5006));
        		return mav;
            	}

            } catch (Exception e) {
                error = error.generateError(1004);
                mav.addObject(ERROR, error);
                return mav;
            }
            
            List<ProductOrderApi> productOrderApiList = new ArrayList<ProductOrderApi>();
            for (ProductOrder productOrder : productOrders) {
                ProductOrderApi productApi = new ProductOrderApi();
                productApi.setOrderId(productOrder.getId());
                productApi.setStoreId(productOrder.getStoreId());
                productApi.setMerchantPayPalEmail(productOrder.getsMerchantPaypalEmail());
                productApi.setOrderAmount(productOrder.getfAmount());
                productApi.setOrderTaxAmount(productOrder.getfTaxAmount());
                productApi.setOrderGrandTotalAmount(productOrder.getfTotalAmount());
                productApi.setBuyerName(productOrder.getsBuyerName());
                productApi.setBuyerEmail(productOrder.getsBuyerEmail());
                productApi.setShippingStreet(productOrder.getsShippingStreet());
                productApi.setShippingCity(productOrder.getsShippingCity());
                productApi.setShippingState(productOrder.getsShippingState());
                productApi.setShippingPostalCode(productOrder.getsShippingPostalCode());
                productApi.setShippingCountry(productOrder.getsShippingCountry());
                productApi.setBillingStreet(productApi.getBillingStreet());
                productApi.setBillingCity(productApi.getBillingCity());
                productApi.setBillingState(productApi.getBillingState());
                productApi.setBillingPostalCode(productApi.getBillingPostalCode());
                productApi.setBillingCountry(productApi.getBillingCountry());
                productApi.setOrderStatus(productApi.getOrderStatus());
                productApi.setOrderDate(productApi.getOrderDate());
                productOrderApiList.add(productApi);

            }
            MainOrderApi mainOrder = new MainOrderApi();
            mainOrder.setOrders(productOrderApiList);
            mainOrder.setSize(productOrders.size());
            mav.addObject(PRODUCT_ORDER_LIST, mainOrder);
            return mav;

    }


    /**
     * Service to fetch Shipping Rate
     *
     * @param user_name
     * @param api_key
     * @param store_id
     * @return 'Shipping' object in JSON or XML
     */

    @RequestMapping(value = "/shipping-rate", method = RequestMethod.GET)
    public ModelAndView getShippingRate(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "store_id", required = false) String storeId,
            @RequestParam(value = "country_id", required = false) String countryId,
            @RequestParam(value = "state_id", required = false) String stateId) {

        ModelAndView mav = new ModelAndView();
        Error error = new Error();
        Shipping shipping = null;
        Long strId;
        Long cntryId;
        Long stteId;
        User user = null;

        
        Map<String, Object> key_response = apiBO.validateApiKey(apiKey, userName);

        if (!(Boolean) key_response.get(VALID)) {
            error = (Error) key_response.get(ERROR);// get error
            mav.addObject(ERROR, error);
            return mav;
        }
        
        boolean hits = true;
        try{
        	hits = apiBO.validateStoreApiHits(apiKey);
        }catch(Exception e){
        	logger.error("While validating hits", e);
        	mav.addObject(ERROR, error.generateError(1004));
        	return mav;
        }
        
        if(hits == false){
        	mav.addObject(ERROR, error.generateError(1006));
        	return mav;
        }
        
        List<String> missingParamList=new ArrayList<String>();
        if(StringUtils.isEmpty(storeId) ){
        	missingParamList.add("store_id");
        }
        
        if(StringUtils.isEmpty(countryId) ){
        	missingParamList.add("country_id");
        }
        
        if(StringUtils.isEmpty(stateId) ){
        	missingParamList.add("state_id");
        }
        
        
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        try {
        	strId = Long.parseLong(storeId);
        	} catch (NumberFormatException nfe) {
                mav.addObject(ERROR, error.generateError(2001));
                return mav;
            	}

    	try {
    		cntryId = Long.parseLong(countryId);
            } catch (NumberFormatException nfe) {
                mav.addObject(ERROR, error.generateError(2005));
                return mav;
            	}
        try {
        	stteId = Long.parseLong(stateId);
            } catch (NumberFormatException nfe) {
                mav.addObject(ERROR, error.generateError(2008));
                return mav;
            	}
            
      
       try {
    	   // get user
    	   user = (User) key_response.get(USER);
    	   
    	   try {
    		   
    		   Store store = null;
    	       Territory country = null;
    	       State state = null;
    	       try{
    	    	   store = storeService.findStoreById(strId);
    	    	   if(store == null){
    					mav.addObject(ERROR, error.generateError(2003));
    					return mav;
    	           }
    				if(store.getUserId().longValue() != user.getId().longValue()){
    					mav.addObject(ERROR, error.generateError(2004));
    					return mav;
    				}
    	           
    	    	   country = storeService.findTerritoryById(cntryId);
    	    	   if(country == null){
                       mav.addObject(ERROR, error.generateError(2006));
                       return mav;
    	    	   }
    	    	   
    	    	   state = storeService.findStateById(stteId);
    	    	   if(state == null){
                       mav.addObject(ERROR, error.generateError(2009));
                       return mav;
    	    		   
    	    	   }
    	    	   if(state.getTerritoryId().longValue() != country.getId().longValue()){
                       mav.addObject(ERROR, error.generateError(2010));
                       return mav;
    	    	   }
    	           }catch(Exception e){
    	        	   error = error.generateError(1004);
    	        	   mav.addObject(ERROR, error);
    	        	   return mav;
    	           	   }

    	       shipping = storeService.findShippingByStoreIdCountryIdStateId(strId, cntryId, stteId);
    		   
    		   if (shipping == null) {
    			   mav.addObject(ERROR, error.generateError(2011));
    			   return mav;
    		   	   } else {
    		   		   ShippingApi shippingApi = new ShippingApi();
    		   		   shippingApi.setStoreId(shipping.getStoreId());
    		   		   shippingApi.setShippingCountry(shipping.getsCountry());
    		   		   shippingApi.setShippingState(shipping.getsState());
    		   		   shippingApi.setShippingAmountSingleProduct(shipping.getfAlone());
    		   		   shippingApi.setShippingAmountMultipleProduct(shipping.getfOthers());
    		   		   mav.addObject(SHIPPING, shippingApi);
    		   	   	   }
                } catch (Exception e) {
                    error.generateError(1004);
                    mav.addObject(ERROR, error);
                    return mav;
                	}
                
            	} catch (Exception e) {
            		error.generateError(1004);
                    mav.addObject(ERROR, error);
                    return mav;
                	}
            return mav;

    }


    /**
     * Service to update or create Shipping Rate
     *
     * @param user_name
     * @param api_key
     * @param store_id
     * @return 'Shipping' object in JSON or XML
     */

    @RequestMapping(value = "/set-shipping-rate", method = RequestMethod.POST)
    public ModelAndView setShippingRate(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "store_id", required = false) String storeId,
            @RequestParam(value = "country_id", required = false) String countryId,
            @RequestParam(value = "state_id", required = false) String stateId,
            @RequestParam(value = "shipping_single", required = false) String shippingSingle,
            @RequestParam(value = "shipping_multiple", required = false) String shippingMultiple) {

        ModelAndView mav = new ModelAndView();
        Error error = new Error();
        Message message = new Message();
        Shipping shipping = null;
        Long strId;
        Long cntryId;
        Long stteId;
        Double shipSingle;
        Double shipMultiple;
        User user = null;

        Map<String, Object> key_response = apiBO.validateApiKey(apiKey, userName);

        if (!(Boolean) key_response.get(VALID)) {
            error = (Error) key_response.get(ERROR);// get error
            mav.addObject(ERROR, error);
            return mav;
        }
        
        boolean hits = true;
        try{
        	hits = apiBO.validateStoreApiHits(apiKey);
        }catch(Exception e){
        	logger.error("While validating hits", e);
        	mav.addObject(ERROR, error.generateError(1004));
        	return mav;
        }
        
        if(hits == false){
        	mav.addObject(ERROR, error.generateError(1006));
        	return mav;
        }
        
        List<String> missingParamList=new ArrayList<String>();
        if(StringUtils.isEmpty(storeId) ){
        	missingParamList.add("store_id");
        }
        
        if(StringUtils.isEmpty(countryId) ){
        	missingParamList.add("country_id");
        }
        
        if(StringUtils.isEmpty(stateId) ){
        	missingParamList.add("state_id");
        }
        
        if(StringUtils.isEmpty(shippingSingle) ){
        	missingParamList.add("shipping_single");
        }
        
        if(StringUtils.isEmpty(shippingMultiple) ){
        	missingParamList.add("shipping_multiple");
        }
        
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        

        try {
        	strId = Long.parseLong(storeId);
            } catch (NumberFormatException nfe) {
                mav.addObject(ERROR, error.generateError(2001));
                return mav;
            	}
            
        try {
    	   	cntryId = Long.parseLong(countryId);
       		} catch (NumberFormatException nfe) {
                mav.addObject(ERROR, error.generateError(2005));
                return mav;
            }
       try {
    	   stteId = Long.parseLong(stateId);
       		} catch (NumberFormatException nfe) {
                mav.addObject(ERROR, error.generateError(2008));
                return mav;
       			}
		
        try {
        	shipSingle = Double.parseDouble(shippingSingle);
        	shipMultiple = Double.parseDouble(shippingMultiple);
        	} catch (NumberFormatException nfe) {
                mav.addObject(ERROR, error.generateError(2012));
                return mav;
            	}


    	try {
                // get user
                user = (User) key_response.get(USER);

            try {

            	Store store = null;
     	       	Territory country = null;
     	       	State state = null;
     	       	try{
     	       		store = storeService.findStoreById(strId);
     	       		if(store == null){
     					mav.addObject(ERROR, error.generateError(2003));
     					return mav;
     	           }
     				if(store.getUserId().longValue() != user.getId().longValue()){
     					mav.addObject(ERROR, error.generateError(2004));
     					return mav;
     				}
     	           
     	    	   country = storeService.findTerritoryById(cntryId);
     	    	   if(country == null){
                        mav.addObject(ERROR, error.generateError(2005));
                        return mav;
     	    	   }
     	    	   
     	    	   state = storeService.findStateById(stteId);
     	    	   if(state == null){
                        mav.addObject(ERROR, error.generateError(2009));
                        return mav;
     	    		   
     	    	   }
     	    	   if(state.getTerritoryId().longValue() != country.getId().longValue()){
                        mav.addObject(ERROR, error.generateError(2010));
                        return mav;
     	    	   }
     	           }catch(Exception e){
     	        	   mav.addObject(ERROR, error.generateError(1004));
     	        	   return mav;
     	           	   }


                    shipping = storeService.findShippingByStoreIdCountryIdStateId(strId, cntryId, stteId);

                    if (shipping != null) {
                        shipping.setfAlone(new BigDecimal(shipSingle));
                        shipping.setfOthers(new BigDecimal(shipMultiple));

                        storeService.updateShipping(shipping);

                        message.setMessage("Shipping rate updated successfully.");
                        mav.addObject(MESSAGE, message);

                    } else {
                        shipping = new Shipping();

                        shipping.setStoreId(strId);
                        shipping.setTerritoryId(cntryId);
                        shipping.setStateId(stteId);
                        shipping.setsCountry(country.getsName());
                        shipping.setfAlone(new BigDecimal(shipSingle));
                        shipping.setfOthers(new BigDecimal(shipMultiple));

                        shipping = storeService.createShipping(shipping);

                        message.setMessage("Shipping rate created successfully.");
                        mav.addObject(MESSAGE, message);
                    	}

                } catch (Exception e) {
                	error = error.generateError(1004);
  	        	   mav.addObject(ERROR, error);
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
     * Service to fetch Country Details
     *
     * @param user_name
     * @param api_key
     * @return List of 'TerritoryApi' objects in JSON or XML
     */

    @RequestMapping(value = "/countries", method = RequestMethod.GET)
    public ModelAndView getCountries(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey) {

        ModelAndView mav = new ModelAndView();
        Error error = new Error();
        List<Territory> territories = null;
        List<TerritoryApi> listTerritoryApi = new ArrayList<TerritoryApi>();
        User user = null;

        Map<String, Object> key_response = apiBO.validateApiKey(apiKey, userName);
        if (!(Boolean) key_response.get(VALID)) {
            error = (Error) key_response.get(ERROR);// get error
            mav.addObject(ERROR, error);
            return mav;
        	}
        
        boolean hits = true;
        try{
        	hits = apiBO.validateStoreApiHits(apiKey);
        }catch(Exception e){
        	logger.error("While validating hits", e);
        	mav.addObject(ERROR, error.generateError(1004));
        	return mav;
        }
        
        if(hits == false){
        	mav.addObject(ERROR, error.generateError(1006));
        	return mav;
        }
        
        try {
        	// get user
        	user = (User) key_response.get(USER);
 
            try {
            	//find all countries
            	territories = appService.findAllTerritories();
            	//mav.addObject(territories);
            	
            	for (Territory ter : territories) {
            		TerritoryApi territoryApi = new TerritoryApi();
            		
            		territoryApi.setCountryId(ter.getId());
            		territoryApi.setCountryName(ter.getsName());
            		territoryApi.setCountryCode(ter.getsCode());
            		territoryApi.setCountryCurrency(ter.getsCurrency());
            		
            		listTerritoryApi.add(territoryApi);
                	}
                    
            		mav.addObject(COUNTRIES, listTerritoryApi);
            	} catch (Exception e) {
                    error.generateError(1004);
                    mav.addObject(ERROR, error);
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
     * Service to fetch States Details
     *
     * @param user_name
     * @param api_key
     * @return List of 'States' objects in JSON or XML
     */

    @RequestMapping(value = "/states", method = RequestMethod.GET)
    public ModelAndView getStates(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "territory_id", required = false) String territoryId) {

        ModelAndView mav = new ModelAndView();
        Error error = new Error();
        List<Territory> territories = null;
        List<Long> listTerritoryId = new ArrayList<Long>();
        List<StateApi> listStateApi = new ArrayList<StateApi>();
        List<State> states = null;
        Long tertryId;
        User user = null;

        Map<String, Object> key_response = apiBO.validateApiKey(apiKey, userName);

        if (!(Boolean) key_response.get(VALID)) {
            error = (Error) key_response.get(ERROR);// get error
            mav.addObject(ERROR, error);
            return mav;
        }
        
        boolean hits = true;
        try{
        	hits = apiBO.validateStoreApiHits(apiKey);
        }catch(Exception e){
        	logger.error("While validating hits", e);
        	mav.addObject(ERROR, error.generateError(1004));
        	return mav;
        }
        
        if(hits == false){
        	mav.addObject(ERROR, error.generateError(1006));
        	return mav;
        }
        
        List<String> missingParamList=new ArrayList<String>();
        if(StringUtils.isEmpty(territoryId) ){
        	missingParamList.add("territory_id");
        }
        
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        try {
        	tertryId = Long.parseLong(territoryId);
        	} catch (NumberFormatException nfe) {
                mav.addObject(ERROR, error.generateError(2005));
                return mav;
            	}


            try {
                // get user
                user = (User) key_response.get(USER);
                try {
                    territories = appService.findAllTerritories();

                    for (Territory ter : territories) {
                        listTerritoryId.add(ter.getId());
                    }

                    if (!listTerritoryId.contains(tertryId)) {
                        mav.addObject(ERROR, error.generateError(2006));
                        return mav;

                    }

                    states = storeService.findStatesByTerritory(tertryId);
                    if (states == null || states.size() <= 0) {
                        mav.addObject(ERROR, error.generateError(2007));
                        return mav;
                    } else {
                        for (State state : states) {
                            StateApi stateApi = new StateApi();
                            stateApi.setStateId(state.getId());
                            stateApi.setStateName(state.getsName());
                            stateApi.setStateCode(state.getsCode());
                            stateApi.setCountryId(state.getTerritoryId());
                            listStateApi.add(stateApi);
                        }
                        mav.addObject(STATES, listStateApi);
                    }
                } catch (Exception e) {
                    error.generateError(1004);
                    mav.addObject(ERROR, error);
                    return mav;
                }

            } catch (Exception e) {
            	error.generateError(1004);
                mav.addObject(ERROR, error);
                return mav;
            }

            return mav;

    }


    
}
