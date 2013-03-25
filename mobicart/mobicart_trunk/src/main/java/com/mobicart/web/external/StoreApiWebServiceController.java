package com.mobicart.web.external;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.mobicart.model.Shipping;
import com.mobicart.model.State;
import com.mobicart.model.Store;
import com.mobicart.model.Tax;
import com.mobicart.model.Territory;
import com.mobicart.model.User;
import com.mobicart.model.api.ShippingApi;
import com.mobicart.model.api.StateApi;
import com.mobicart.model.api.StoreApi;
import com.mobicart.model.api.TaxApi;
import com.mobicart.model.api.TerritoryApi;
import com.mobicart.service.AppService;
import com.mobicart.service.StoreService;

@Controller
@RequestMapping("/api/**")
public class StoreApiWebServiceController {
	
	
	@Autowired
    StoreService storeService;
	@Autowired
    ApiBO apiBO;
	@Autowired
    AppService appService;
	
	

	private static final String ERROR = "error";
	private static final String VALID = "valid";
	private static final String USER = "user";
	private static final String STORE = "store";
	private static final String SHIPPING = "Shipping";
	private static final String MESSAGE = "message";
	private static final String COUNTRIES = "countries";
	private static final String STATES = "states";
	
	
	
	
	
	private static final Logger logger = LoggerFactory
    .getLogger(StoreApiWebServiceController.class);

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
        if(storeId.length() > 10 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(StringUtils.isEmpty(countryId) ){
        	missingParamList.add("country_id");
        }
        if(countryId.length() > 10 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(StringUtils.isEmpty(stateId) ){
        	missingParamList.add("state_id");
        }
        if(stateId.length() > 10 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
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
        if(storeId.length() > 10 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(StringUtils.isEmpty(countryId) ){
        	missingParamList.add("country_id");
        }
        if(countryId.length() > 10 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(StringUtils.isEmpty(stateId) ){
        	missingParamList.add("state_id");
        }
        if(stateId.length() > 10 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(StringUtils.isEmpty(shippingSingle) ){
        	missingParamList.add("shipping_single");
        }
        if(shippingSingle.length() > 10 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(StringUtils.isEmpty(shippingMultiple) ){
        	missingParamList.add("shipping_multiple");
        }
        if(shippingMultiple.length() > 10 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
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
     * Service to delete Shipping Rate
     *
     * @param user_name
     * @param api_key
     * @param shipping_id
     * @return Status message regarding deletion of shipping
     */

    @RequestMapping(value = "/delete-shipping-rate", method = RequestMethod.DELETE)
    public ModelAndView deleteShippingRate(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "shipping_id", required = false) String shippingId) {

        ModelAndView mav = new ModelAndView();
        Error error = new Error();
        Message message = new Message();
        Shipping shipping = null;
        User user = null;
        Long shippngId = null;
        
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
        if(StringUtils.isEmpty(shippingId) ){
        	missingParamList.add("shipping_id");
        }
        if(shippingId.length() > 10 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        

        try {
        	shippngId = Long.parseLong(shippingId);
        } catch (NumberFormatException nfe) {
                mav.addObject(ERROR, error.generateError(2013));
                return mav;
        }
            
        
    	try {
                // get user
                user = (User) key_response.get(USER);
        } catch (Exception e) {
        	error = error.generateError(1004);
        	mav.addObject(ERROR, error);
        	return mav;
    	}
        
        try{
        	shipping = storeService.findShippingById(shippngId);
        }catch(Exception e){
        	error = error.generateError(1004);
        	mav.addObject(ERROR, error);
        	return mav;
        }
        
        if(shipping == null){
        	error = error.generateError(2014);
        	mav.addObject(ERROR, error);
        	return mav;
        }
        
        if(shipping.getStoreId().longValue() != user.getStoreId().longValue()){
        	error = error.generateError(2015);
        	mav.addObject(ERROR, error);
        	return mav;
        }
        
        try{
        	storeService.deleteShipping(shipping);
        	
        }catch(Exception e){
        	error = error.generateError(1004);
        	mav.addObject(ERROR, error);
        	return mav;
        }
        
        message.setMessage("Shipping rate deleted successfully.");
        mav.addObject(message);
        return mav;
    }


   
    
    
    
    
    
    
    /**
     * Service to fetch Country Details
     *
     * @param user_name
     * @param api_key
     * @return List of 'TerritoryApi' objects in JSON or XML
     */

    @SuppressWarnings("unused")
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

    @SuppressWarnings("unused")
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
        if(territoryId.length() > 10 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
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
