package com.mobicart.web.internal;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mobicart.bo.PushNotificationBO;
import com.mobicart.model.AppDeviceTokens;
import com.mobicart.service.AppService;

/**
 * This controller class handles web service calls related to Push Notification  
 * @author jasdeep.singh
 *
 */
@Controller
public class PushNotifyWebServiceController {

	
	/**
	 * Logger for this class
	 */
	private final Logger logger=LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	AppService appService;
	
	@Autowired
	PushNotificationBO pushNotificationBO;
	
	
	private static final String APP_TOKEN="app-token";
	
	/**
	 * Service to send pending push notifications
	 * 
	 * @param appId
	 * @param Token
	 * @return 'AppDeviceTokens' object
	 */

	
	@RequestMapping(value = "/push-notify")
	public String PushNotify(ModelMap modelMap,
			@RequestParam(value = "apptype", required = false,defaultValue="ios") String appType) {
		
		try{
			if("ios".equals(appType))
			pushNotificationBO.sendNotifications();
			modelMap.put("success", "Success");
		}catch (Exception e) {
			logger.error("Cound not push notification :", e);
			modelMap.put("error", "Some error occured "+ e.getLocalizedMessage());
		}
		return "pushNotification";
	}
	
	
	
	/**
	 * Service to register device for push notification
	 * 
	 * @param appId
	 * @param Token
	 * @return 'AppDeviceTokens' object
	 */

	
	@RequestMapping(value = "/user/{appId}/{token}/{longitude}/{latitude}/register")
	public ModelAndView registerForPushNotification(
			@PathVariable Long appId,
			@PathVariable String token,
			@PathVariable Double longitude,
			@PathVariable Double latitude,
			@RequestParam(value = "apptype", required = false,defaultValue="ios") String appType
	) {
		
		AppDeviceTokens appDeviceTokens=null;
		
		try{
			appDeviceTokens=new AppDeviceTokens();
			
			appDeviceTokens.setAppId(appId);
			appDeviceTokens.setRegdate(new Date());
			appDeviceTokens.setTokenId(token.trim().replaceAll(" ", "").replaceAll("%20", ""));
			appDeviceTokens.setLatitude(latitude);
			appDeviceTokens.setLongitude(longitude);
			appDeviceTokens.setAppType(appType);
			
			appDeviceTokens=appService.registerAppForNotifiction(appDeviceTokens);
			
		}catch(Exception e){
			logger.error("Cound not register devices for push notification {}", e );
		}
		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject(APP_TOKEN, appDeviceTokens);
		return mav;
	}
}
