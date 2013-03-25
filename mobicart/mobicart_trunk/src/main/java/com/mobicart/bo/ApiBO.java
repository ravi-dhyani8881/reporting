package com.mobicart.bo;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.axis.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mobicart.model.Api;
import com.mobicart.model.User;
import com.mobicart.service.StoreService;
import com.mobicart.util.CacheManager;

/**
 * Class to authenticate API 
 * @author jasdeep.singh
 *
 */
public class ApiBO {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(ApiBO.class);
	
	private static final String VALID = "valid";
	private static final String USER = "user";
	private static final String ERROR = "error";

	@Autowired
	StoreService storeService;

	/**
	 *  Authenticates api key and username
	 * @param apiKey
	 * @param userName
	 * @return HashMap ('valid': true or false, 'user', User object or null, 'error': Error object)
	 */
	public Map<String, Object> validateApiKey(String apiKey, String userName) {

		Map<String, Object> response = new HashMap<String, Object>();
		Error error = new Error();
		boolean errorOccured=false;
		
		if (StringUtils.isEmpty(apiKey) || StringUtils.isEmpty(userName)) {// key is null
			error = error.generateError(1003);
			errorOccured=true;
		} else {
			response.put(VALID, true);// 0
			// add user of key
		try {
			 
			
				User user = (User)CacheManager.getFromCache(apiKey);
				CacheManager.setToCache(apiKey,user = null);
				
				if(user == null){
					user = storeService.findUserByAPIKey(apiKey);
					CacheManager.setToCache(apiKey,user,1*1*24*60*60*1000,1*1*24*60*60*1000);//one day
				}
				
				
				if (user!=null &&userName.equals(user.getUsername())) {
					response.put(USER, user);// 1
					errorOccured=false;
				} else {
					error = error.generateError(1002);
					errorOccured=true;
				}
				
				
			} catch (Exception e) {
				/**
				 * as the error is fairly common hence it should be put in debug mode
				 */
				if(logger.isDebugEnabled())
					logger.debug("Error in validating user",e);
				error = error.generateError(1002);
				errorOccured=true;
				
			}
		}
		
		
		if(errorOccured){
			response.put(VALID, false);// 0
			response.put(USER, null);// 1
			response.put(ERROR, error);// 2
		}
		
		return response;
	}
	
	
	
	
	public Boolean validateGeneralApiHits(String apiKey)throws Exception{
		Api api = storeService.findApiByAPIKey(apiKey);
		int thresholdGeneralCount = api.getThresholdGeneralCount();
		int thresholdRefreshCount = api.getThresholdRefreshCount();
		int generalCount = api.getGeneralCounter();
		Date lastRefreshDate = api.getLastRefreshTime();
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, -thresholdRefreshCount);
		Date beforeThresholdRefresh = calendar.getTime();
		
		if(beforeThresholdRefresh.compareTo(lastRefreshDate) > 0){
			api.setGeneralCounter(1);
			api.setStoreCounter(0);
			api.setLastRefreshTime(Calendar.getInstance().getTime());
			storeService.updateApi(api);
			return true;
		}else if(generalCount < thresholdGeneralCount){
			api.setGeneralCounter(generalCount+1);
			storeService.updateApi(api);
			return true;
		}
		return false;
	}
	
	
	public Boolean validateStoreApiHits(String apiKey)throws Exception{
		Api api = storeService.findApiByAPIKey(apiKey);
		int thresholdGeneralCount = api.getThresholdGeneralCount();
		int thresholdStoreCount = api.getThresholdStoreCount();
		int thresholdRefreshCount = api.getThresholdRefreshCount();
		int generalCount = api.getGeneralCounter();
		int storeCount = api.getStoreCounter();
		Date lastRefreshDate = api.getLastRefreshTime();
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, -thresholdRefreshCount);
		Date beforeThresholdRefresh = calendar.getTime();
		
		if(beforeThresholdRefresh.compareTo(lastRefreshDate) > 0){
			api.setGeneralCounter(1);
			api.setStoreCounter(1);
			api.setLastRefreshTime(Calendar.getInstance().getTime());
			storeService.updateApi(api);
			return true;
		}else if(generalCount < thresholdGeneralCount && storeCount < thresholdStoreCount){
			api.setGeneralCounter(generalCount+1);
			api.setStoreCounter(storeCount+1);
			storeService.updateApi(api);
			return true;
		}
		return false;
	}


}
