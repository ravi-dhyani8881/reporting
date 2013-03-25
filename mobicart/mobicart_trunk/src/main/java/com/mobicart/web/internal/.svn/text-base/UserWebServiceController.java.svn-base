package com.mobicart.web.internal;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mobicart.dto.IphoneLabelsDto;
import com.mobicart.dto.LatLong;
import com.mobicart.model.Address;
import com.mobicart.model.App;
import com.mobicart.model.AppStoreUser;
import com.mobicart.model.Feature;
import com.mobicart.model.Plans;
import com.mobicart.model.Store;
import com.mobicart.model.User;
import com.mobicart.model.UserDetail;
import com.mobicart.model.WebappMerchantDetail;
import com.mobicart.service.ApiService;
import com.mobicart.service.AppService;
import com.mobicart.service.LabelService;
import com.mobicart.service.StoreService;
import com.mobicart.service.user.UserService;
import com.mobicart.util.MapUtils;

/**
 * This controller class handles web service calls related to User entity 
 * @author jasdeep.singh
 *
 */
@Controller
public class UserWebServiceController {

	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	
	/**
	 * {@link UserService} autowired instance
	 */
	@Autowired
	UserService userService;

	/**
	 * {@link AppService} autowired instance
	 */
	@Autowired
	AppService appService;

	/**
	 * {@link StoreService} autowired instance
	 */
	@Autowired
	StoreService storeService;
	
	/**
	 * Instantiate {@link LabelService}
	 */
	@Autowired
	LabelService labelService;

	/**
	 * {@link StoreService} autowired instance
	 */
	@Autowired
	ApiService apiservice;

	
	private static final String APP_STORE_USER = "app-store-user";
	private static final String WEB_APP_DETAIL = "webappMerchantDetail";
	private static final String USER_DETAIL = "userDetail";
	private static final String USER_ADDRESS = "user-address";
	private static final String USER_ADDRESS_WEBAPP = "userAddress";
	 private static final String LABEL = "Labels";
	
	/**
	 * Get storeId,userId,appId for a user 
	 * @param username
	 * @return {@link AppStoreUser} object
	 */
	
	@RequestMapping(value = "/user/{username}/app-store-user")
	public ModelAndView getStoreIdAndAppIdByUserName(
			@PathVariable String username) {
		AppStoreUser appStoreUser = null;
		App app = null;
		Store store = null;
		User user = null;
		
		try {
			user = userService.findByEmail(username);
			if(user!=null){
				app = appService.findAppByUser(user);
				store = storeService.findStoreByUserId(user.getId());
			    Plans plans=userService.findServicesByUserId(user.getId());
			    
			    String usersplan=(plans.isFree())?"free":(plans.isStarter())?"starter":"pro";
				appStoreUser = new AppStoreUser(app.getId(), store.getId(),
					user.getId(),usersplan);
			}
			
		} catch (Exception e) {
			logger.error("getStoreIdAndAppIdByUserName",e);
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject(APP_STORE_USER, appStoreUser);
		return mav;
	}

	/**
	 * Get Address for a username
	 * @param username
	 * @return 'UserAddress' object
	 */
	@RequestMapping(value = "/user/{username}/address")
	public ModelAndView getUserAddress(@PathVariable String username) {
		Address address = null;
		
		try{
		User user = userService.findByEmail(username);
		if(user!=null){
			address = userService.findAddressByUserId(user.getId());
		}
		}catch (Exception e) {
			logger.error("logger",e);
		}
	    
		ModelAndView mav = new ModelAndView();
		mav.addObject(USER_ADDRESS, address);
		return mav;
	}
	
	
	
	/**
	 * Get Address for a username
	 * @param username
	 * @return 'UserAddress' object
	 */
	@RequestMapping(value = "/user/{username}/address-webapp")
	public ModelAndView getUserAddressWebapp(@PathVariable String username) {
		Address address = null;
		
		try{
		User user = userService.findByEmail(username);
		if(user!=null){
			address = userService.findAddressByUserId(user.getId());
			
			// populate latlong
			
			String cityState=address.getsCity()+","+address.getsState();
			LatLong latLong=MapUtils.getLatLong(cityState);
			address.setLatLong(latLong);
		}
		}catch (Exception e) {
			logger.error("logger",e);
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject(USER_ADDRESS_WEBAPP, address);
		return mav;
	}
	
	
	/**
	 * Get storeId,userId,appId for a user 
	 * @param username
	 * @return 'AppStoreUser' object
	 */
	
	@RequestMapping(value = "/user/{username}/details")
	public ModelAndView getUserDetailsbyUsername(
			@PathVariable String username) {
		UserDetail userDetail=null;
		try {
			userDetail = userService.findUserDetailByUsername(username);
		} catch (Exception e) {
			logger.error("getUserDetailsbyUsername",e);
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject(USER_DETAIL, userDetail);
		return mav;
	}

	/**
	 * Get storeId,userId,appId for a user 
	 * @param username
	 * @return {@link AppStoreUser} object
	 */
	
	@RequestMapping(value = "/user/{domainUrl}/webapp-store-user")
	public ModelAndView getStoreAndAppInformationByDomainURL(
			@PathVariable String domainUrl) {
		WebappMerchantDetail webappMerchantDetail = null;
		App app = null;
		Store store = null;
		User user = null;
		String oauthSecret=null;
		try {
			user = userService.findUserByDomainURL(domainUrl);
			if(user!=null){
				app = appService.findAppByUser(user);
				store = storeService.findStoreByUserId(user.getId());
				oauthSecret=apiservice.getAuthSecretByUserId(user.getId());
				boolean showNewsTab=false;
				List<Feature> features = appService.findAppFeatures(app.getId());
				// check if news tab is enabled
				Feature feature=new Feature();
				feature.setId(3L);
				if(features.contains(feature))showNewsTab=true;
				
				webappMerchantDetail = new WebappMerchantDetail( store.getId(),user.getId(),app.getId(),user.getUsername(),
						domainUrl,store.getsPaypalEmail(),user.getsCompanyLogoWebsite(),user.getsCompanyLogo(),showNewsTab,oauthSecret);
			}
			
		} catch (Exception e) {
			logger.error("getStoreAndAppInformationByDomainURL",e);
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject(WEB_APP_DETAIL, webappMerchantDetail);
		return mav;
	}
	
	
	
	/**
	 * Get storeId,userId,appId for a user 
	 * @param username
	 * @return {@link AppStoreUser} object
	 */
	
	@RequestMapping(value = "/user/{username}/webapp-merchant-detail")
	public ModelAndView getWebappMerchantDetailByUsername(
			@PathVariable String username) {
		WebappMerchantDetail webappMerchantDetail = null;
		App app = null;
		Store store = null;
		User user = null;
		String oauthSecret=null;
		try {
			user = userService.findByEmail(username);
			if(user!=null){
				app = appService.findAppByUser(user);
				store = storeService.findStoreByUserId(user.getId());
				oauthSecret=apiservice.getAuthSecretByUserId(user.getId());
				boolean showNewsTab=false;
				List<Feature> features = appService.findAppFeatures(app.getId());
				// check if news tab is enabled
				Feature feature=new Feature();
				feature.setId(3L);
				if(features.contains(feature))showNewsTab=true;
				
				 
				webappMerchantDetail = new WebappMerchantDetail( store.getId(),user.getId(),app.getId(),user.getUsername(),
						user.getsWebappDomainName(),store.getsPaypalEmail(),user.getsCompanyLogoWebsite(),user.getsCompanyLogo(),showNewsTab,oauthSecret);
			}
			
		} catch (Exception e) {
			logger.error("getStoreAndAppInformationByDomainURL",e);
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject(WEB_APP_DETAIL, webappMerchantDetail);
		return mav;
	}

		
	/**
	 * @author siddhartha.bhatia
     * Service to fetch all the iphone labels of a particular merchant.
     * If no new label is specified then the default label replaces the NULL value.
     * 
     * @param merchantId
     * @return
     */
    @RequestMapping(value = "/user/iphone/{merchantId}/labels", method = RequestMethod.GET)
    public ModelAndView getAllIphoneLabels(@PathVariable String merchantId) {
    	
    	ModelAndView mav = new ModelAndView();
    	List<IphoneLabelsDto> iphoneLabelsDtoList = null;
	    	
    		try{
    			iphoneLabelsDtoList = labelService.getAllExclusiveIphoneLabels(Long.parseLong(merchantId));
	    	}catch (Exception e) {
				logger.error("e {}"+e.getMessage());
			}
    	
    	mav.addObject(LABEL, iphoneLabelsDtoList);
    	return mav;
    	
    }
    
    
    @RequestMapping(value = "/user/iphone/insync/{merchantId}/{dateAdded}/labels", method = RequestMethod.GET)
    public ModelAndView getAllIphoneLabels(@PathVariable String merchantId,@PathVariable String dateAdded) {
    	
    	Boolean b = false;
    	ModelAndView mav = new ModelAndView();
	    	
    		try{
    			b = labelService.isLabelInSync(Long.parseLong(merchantId), dateAdded);
	    	}catch (Exception e) {
				logger.error("e {}"+e.getMessage());
			}
    	
    	mav.addObject(LABEL, b);
    	return mav;
    	
    }
    

	
	
	
}
