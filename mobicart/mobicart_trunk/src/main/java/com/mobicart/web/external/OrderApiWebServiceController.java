package com.mobicart.web.external;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;
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

import com.mobicart.bo.ApiBO;
import com.mobicart.bo.Error;
import com.mobicart.bo.Message;
import com.mobicart.dao.ShippingDAO;
import com.mobicart.dao.StateDAO;
import com.mobicart.dao.TaxDAO;
import com.mobicart.dao.TerritoryDAO;
import com.mobicart.model.App;
import com.mobicart.model.Product;
import com.mobicart.model.ProductOption;
import com.mobicart.model.ProductOrder;
import com.mobicart.model.ProductOrderItem;
import com.mobicart.model.ProductOrderShippingDetail;
import com.mobicart.model.Shipping;
import com.mobicart.model.ShippingExample;
import com.mobicart.model.State;
import com.mobicart.model.StateExample;
import com.mobicart.model.Store;
import com.mobicart.model.Tax;
import com.mobicart.model.TaxExample;
import com.mobicart.model.Territory;
import com.mobicart.model.TerritoryExample;
import com.mobicart.model.User;
import com.mobicart.model.Tax;
import com.mobicart.model.api.MainOrderApi;
import com.mobicart.model.api.ProductOrderApi;
import com.mobicart.service.AdminService;
import com.mobicart.service.StoreService;
import com.mobicart.util.EmailGenerator;
import com.mobicart.util.PathLocator;
import com.mobicart.util.Sender;
import com.mobicart.model.Territory;

@Controller
@RequestMapping("/api/**")
public class OrderApiWebServiceController {
	
	
	
	@Autowired
    StoreService storeService;
	@Autowired
	TaxDAO taxDAO;	
	@Autowired
    ApiBO apiBO;
	@Autowired
    AdminService adminService;
	@Autowired
	Sender sender;
	
	
	
	private static final String ERROR = "error";
	private static final String VALID = "valid";
	private static final String USER = "user";
	private static final String PRODUCT_ORDER_LIST = "OrderList";
	private static final String PRODUCT_ORDER = "order-details";
	private static final String MESSAGE = "message";
	
	
	/**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory
            .getLogger(OrderApiWebServiceController.class);
    
    
	
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
    @RequestMapping(value = "/add-product-order", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView addOrderForProduct(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
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
        if(sMerchantPaypalEmail.length() > 50 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(StringUtils.isEmpty(sBuyerName) ){
        	missingParamList.add("s_buyer_name");
        }
        if(sBuyerName.length() > 50 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(StringUtils.isEmpty(sBuyerEmail) ){
        	missingParamList.add("s_buyer_email");
        }
        if(sBuyerEmail.length() > 100 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(StringUtils.isEmpty(iBuyerPhone) ){
        	missingParamList.add("i_buyer_phone");
        }
        if(iBuyerPhone.length() > 15 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(StringUtils.isEmpty(sShippingStreet) ){
        	missingParamList.add("s_shipping_street");
        }
        if(sShippingStreet.length() > 100 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(StringUtils.isEmpty(sShippingCity) ){
        	missingParamList.add("s_shipping_city");
        }
        if(sShippingCity.length() > 100 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(StringUtils.isEmpty(sShippingState) ){
        	missingParamList.add("s_shipping_state");
        }
        if(sShippingState.length() > 100 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(StringUtils.isEmpty(sShippingPostalCode) ){
        	missingParamList.add("s_shipping_postal_code");
        }
        if(sShippingPostalCode.length() > 100 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(StringUtils.isEmpty(sShippingCountry) ){
        	missingParamList.add("s_shipping_country");
        }
        if(sShippingCountry.length() > 100 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(StringUtils.isEmpty(sBillingStreet) ){
        	missingParamList.add("s_billing_street");
        }
        if(sBillingStreet.length() > 100 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(StringUtils.isEmpty(sBillingCity) ){
        	missingParamList.add("s_billing_city");
        }
        if(sBillingCity.length() > 100 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(StringUtils.isEmpty(sBillingState) ){
        	missingParamList.add("s_billing_state");
        }
        if(sBillingState.length() > 100 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(StringUtils.isEmpty(sBillingPostalCode) ){
        	missingParamList.add("s_billing_postal_code");
        }
        if(sBillingPostalCode.length() > 100 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(StringUtils.isEmpty(sBillingCountry) ){
        	missingParamList.add("s_billing_country");
        }
        if(sBillingCountry.length() > 100 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
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
            try{
            Shipping shipping = storeService.findShippingByStoreIdCountryNameStateName(store.getId(), sShippingCountry, sShippingState);
            if(shipping == null){
            	mav.addObject(ERROR, error.generateError(5017));
            	return mav;
            }
            }catch(Exception e){
            	mav.addObject(ERROR, error.generateError(5017));
            	return mav;
            }
            

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
            productOrder.setfTaxAmount(new BigDecimal(0.0));
            productOrder.setfShippingAmount(new BigDecimal("0.0"));
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
                productApi.setBillingStreet(productOrder.getsBillingStreet());
                productApi.setBillingCity(productOrder.getsBillingCity());
                productApi.setBillingState(productOrder.getsBillingState());
                productApi.setBillingPostalCode(productOrder.getsBillingPostalCode());
                productApi.setBillingCountry(productOrder.getsBillingCountry());
                productApi.setOrderStatus(productOrder.getsStatus());
                productApi.setOrderDate(productOrder.getFormattedOrderDate());
                productOrderApiList.add(productApi);

            }
            MainOrderApi mainOrder = new MainOrderApi();
            mainOrder.setOrders(productOrderApiList);
            mainOrder.setSize(productOrders.size());
            mav.addObject(PRODUCT_ORDER_LIST, mainOrder);
            return mav;
    }

    
    

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

        ProductOrderApi productOrderApi = null;
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
        	logger.error("Error", e);
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
        if(orderId.length() > 100 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
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
                productOrderApi = storeService.findProductOrderForApi(odrId);
                
                if(productOrderApi == null){
    				mav.addObject(ERROR, error.generateError(5002));
    				return mav;
                }
                
    			if(productOrderApi.getMerchantId() != user.getId().longValue()){
    				mav.addObject(ERROR, error.generateError(5004));
    				return mav;
            		}
    			
                } catch (Exception e) {
                	logger.error("Error", e);
                    error = error.generateError(1004);
                    mav.addObject(ERROR, error);
                    return mav;
                }

          
            mav.addObject(PRODUCT_ORDER, productOrderApi);
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
                productApi.setBillingStreet(productOrder.getsBillingStreet());
                productApi.setBillingCity(productOrder.getsBillingCity());
                productApi.setBillingState(productOrder.getsBillingState());
                productApi.setBillingPostalCode(productOrder.getsBillingPostalCode());
                productApi.setBillingCountry(productOrder.getsBillingCountry());
                productApi.setOrderStatus(productOrder.getsStatus());
                productApi.setOrderDate(productOrder.getFormattedOrderDate());
                productOrderApiList.add(productApi);

            }
            MainOrderApi mainOrder = new MainOrderApi();
            mainOrder.setOrders(productOrderApiList);
            mainOrder.setSize(productOrders.size());
            mav.addObject(PRODUCT_ORDER_LIST, mainOrder);
            return mav;
        
    }


    

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
                productApi.setBillingStreet(productOrder.getsBillingStreet());
                productApi.setBillingCity(productOrder.getsBillingCity());
                productApi.setBillingState(productOrder.getsBillingState());
                productApi.setBillingPostalCode(productOrder.getsBillingPostalCode());
                productApi.setBillingCountry(productOrder.getsBillingCountry());
                productApi.setOrderStatus(productOrder.getsStatus());
                productApi.setOrderDate(productOrder.getFormattedOrderDate());
                productOrderApiList.add(productApi);

            }
            MainOrderApi mainOrder = new MainOrderApi();
            mainOrder.setOrders(productOrderApiList);
            mainOrder.setSize(productOrders.size());
            mav.addObject(PRODUCT_ORDER_LIST, mainOrder);
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
    @RequestMapping(value = "/add-OrderItem")
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
        BigDecimal orderRetunfAmount=null;
        

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
        if(productId.length() > 100 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(StringUtils.isEmpty(orderId) ){
        	missingParamList.add("order_id");
        }
        if(orderId.length() > 100 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
  
        if(StringUtils.isEmpty(optionId) ){
        	missingParamList.add("product_option_id");
        }
        if(optionId.length() > 100 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(StringUtils.isEmpty(amount) ){
        	missingParamList.add("amount");
        }
        if(amount.length() > 100 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(StringUtils.isEmpty(quantity) ){
        	missingParamList.add("quantity");
        }
        if(quantity.length() > 100 ){
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
        	
        	BigDecimal price = product.getfPrice();
        	Double priceValue = price.doubleValue();
        	BigDecimal disccount = product.getfDiscount();
        	Double discountValue = disccount.doubleValue(); 	
        	
        	Territory territory = storeService.getTerritoryForPaymentTotals(productOrder.getsShippingCountry());        	
        	if(territory == null){
        		mav.addObject(ERROR, error.generateError(5017));
        		return mav;
        	}
        	
        	State stte = storeService.getStateForPaymentTotals(productOrder.getsShippingState(), territory.getId());
        	if(stte == null){
        		mav.addObject(ERROR, error.generateError(5017));
        		return mav;
        	} 
        	
        	Long storeId = productOrder.getStoreId(); 
        	BigDecimal shippingAmount = new BigDecimal(storeService.getShippingAmountForPayment(productOrder.getfShippingAmount(), storeId, territory, quntity, stte).doubleValue());
        	productOrder.setfShippingAmount(shippingAmount);
        	
        	Store store = storeService.findStoreById(storeId);        	
        	
        	BigDecimal fTaxAmount = new BigDecimal(storeService.getFTaxAmount(storeId,territory.getId(),stte.getId(),priceValue,quntity,productOrder.getfTaxAmount()).doubleValue());
        	productOrder.setfTaxAmount(fTaxAmount);
        	
        	BigDecimal fAmount = new BigDecimal((quntity * priceValue) - ((quntity * priceValue*discountValue)/100));
        	productOrder.setfAmount(new BigDecimal(productOrder.getfAmount().doubleValue() + fAmount.doubleValue()));
        	productOrder.setfTotalAmount(new BigDecimal(productOrder.getfAmount().doubleValue() + productOrder.getfTaxAmount().doubleValue() + productOrder.getfShippingAmount().doubleValue()));  	
        	
        	if(optId !=0){
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
        	}
        	
            ProductOrderItem prodOrdrItem = new ProductOrderItem();
            prodOrdrItem.setOrderId(ordrId);
            prodOrdrItem.setProductId(prodId);
            prodOrdrItem.setProductOptionId(optId);
            prodOrdrItem.setfAmount(product.getfPrice());
            prodOrdrItem.setiQuantity(quntity);
            orderRetunfAmount=product.getfPrice();
            
            //Long prodOrdrItemId = null;
            
            
            Long prodOrdrItemId = adminService.saveProductOrderItem(prodOrdrItem);
            storeService.updateProductOrder(productOrder);
            
            /*
             *   Order return url will be hitted with get Request GetRequest with the product order specific parameter                        
             */
            
            try
            {
               	String url= user.getOrderReturnUrl();
				if(url!=null && !url.equals(""))
				{
					url=url+ "?" + "merchantId=" +user.getId()
							+ "&productId=" +prodId
							+ "&orderId=" + ordrId
							+ "&productOptionId=" + optId
							+"&itemQuantity=" + quntity;
					logger.info(url);	
					HttpClient client = new HttpClient();
  				   GetMethod method = new GetMethod(url);
  				  
				try {
						int statusCode = client.executeMethod(method);
					} catch (Exception e) {
    					e.printStackTrace();
    				}
				
				}
				                       	
            }
            catch(Exception e)
            {
            	e.printStackTrace();
            }
            
            /*try{
            	prodOrdrItemId = transactionDAO.saveProductOrderInTransaction(prodOrdrItem, productOrder);
            }catch(Exception e){
            	error = error.generateError(1004);
                mav.addObject(ERROR, error);
                return mav;
            }*/
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
                logger.error("Error", e);
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
        if(orderItemId.length() > 100 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        
        if(StringUtils.isEmpty(amount) ){
        	missingParamList.add("amount");
        }
        if(amount.length() > 100 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        
        if(StringUtils.isEmpty(quantity) ){
        	missingParamList.add("quantity");
        }
        if(quantity.length() > 100 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
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
        	
        	
        	//productOrderItem.setfAmount(amont);
        	productOrderItem.setiQuantity(quntity);
            
            Long prodOrdrItemId = storeService.updateOrderItem(productOrderItem);
 
            /*           
            	Order return url will be hit with the specified parameter
            */
            
            try
            {
               	String url= user.getOrderReturnUrl();
				if(url!=null && !url.equals(""))
				{
					
					url=url+ "?" + "merchantId=" +user.getId()
								 + "&productId=" +productOrderItem.getProductId()
								 + "&orderId=" + productOrderItem.getOrderId()
								 + "&productOptionId=" + productOrderItem.getProductOptionId()
								 +"&itemQuantity=" + quntity;
					  
					  logger.info(url);
					  HttpClient client = new HttpClient();
	  				  GetMethod method = new GetMethod(url);
							
					try {
						int statusCode = client.executeMethod(method);
						if (statusCode == HttpStatus.SC_OK) {
						System.out.println(new
						String(method.getResponseBody()));
						}
					} catch (Exception e) {
	    					e.printStackTrace();
	    				}
					
					}
					                       	
	            }
	            catch(Exception e)
	            {
	            	e.printStackTrace();
	            }
            
            
            
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
        if(trackingNumber.length() > 100 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
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
        if(orderId.length() > 10 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(StringUtils.isEmpty(trackingNumber) ){
        	missingParamList.add("tracking_number");
        }
        if(trackingNumber.length() > 100 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
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
        if(trackingNumber.length() > 100 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
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
    
}
