package com.mobicart.web.external;



import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mobicart.bo.Error;
import com.mobicart.bo.Message;

import com.mobicart.model.ApiPartner;
import com.mobicart.model.ApiPartnerExample;
import com.mobicart.model.MerchantPartner;
import com.mobicart.model.MerchantPartnerExample;
import com.mobicart.model.User;
import com.mobicart.service.AdminService;
import com.mobicart.service.StoreService;
import com.mobicart.service.user.UserService;

@Controller
@RequestMapping("/api/**")
public class PartnerApiWebServiceController {
	
	
	@Autowired
	AdminService adminService;
	@Autowired
	StoreService storeService;
	@Autowired
	UserService userService;
	
	
	private static final String ERROR = "error";
	private static final String MESSAGE = "message";
	
	
	/**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory
            .getLogger(PartnerApiWebServiceController.class);
	
	
	
    /**
     * Service to register merchant who is using partner's plugin
     *
     * @param partner_email
     * @param merchant_email
     * @param api_key
     * @return Id of the merchant
     */

    @RequestMapping(value = "/register-merchant", method = RequestMethod.POST)
    public ModelAndView registerMerchantForPartner(
            @RequestParam(value = "partner_email", required = false) String partnerEmail,
            @RequestParam(value = "merchant_email", required = false) String merchantEmail,
            @RequestParam(value = "api_key", required = false) String apiKey) {
    	
    	ModelAndView mav = new ModelAndView();
    	ApiPartner apiPartner =null;
    	Error error = new Error();
    	User user = null;
    	Message message = new Message();
    	
    	if(StringUtils.isEmpty(partnerEmail)){
        	mav.addObject(ERROR, error.generateError(1003));
            return mav;
        }
    	if(StringUtils.isEmpty(apiKey)){
        	mav.addObject(ERROR, error.generateError(1003));
            return mav;
        }
    	if(StringUtils.isEmpty(merchantEmail)){
        	mav.addObject(ERROR, error.generateError(1001));
            return mav;
        }
    	
    	List<String> missingParamList=new ArrayList<String>();
        
        if(StringUtils.isEmpty(merchantEmail) ){
        	missingParamList.add("merchant_email");
        }
        
  
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
    	
    	ApiPartnerExample partnerExample = new ApiPartnerExample();
    	partnerExample.createCriteria().andPartnerEmailEqualTo(partnerEmail).andApiKeyEqualTo(apiKey);
    	
    	try{
    		apiPartner = adminService.findApiPartner(partnerExample);
    	}catch(Exception e){
    		logger.error("Error in finding ApiPartner", e);    		
    		mav.addObject(ERROR, error.generateError(1004));
        	//return mav;
    	}
    	if(apiPartner == null){
    		mav.addObject(ERROR, error.generateError(1002));
        	return mav;
    	}
    	
    	try{
    		user = userService.findByEmail(merchantEmail);
    		 
    	}catch(Exception e){
    		logger.error("Error in finding ApiPartner", e);
    		mav.addObject(ERROR, error.generateError(1004));
        	//return mav;
    	}
    	if(user == null){
    		mav.addObject(ERROR, error.generateError(9001));
        	return mav;
    	}
    	
    	MerchantPartner merchantPartner = new MerchantPartner();
    	merchantPartner.setMerchantEmail(merchantEmail);
    	merchantPartner.setPartnerEmail(partnerEmail);
    	merchantPartner.setId(0);
    	Integer merchantId = null;
    	try{
    		merchantId = adminService.saveMerchantPartner(merchantPartner);
    		message.setId(merchantId.toString());
        	message.setMessage("Merchant added successfully to your account.");
        	mav.addObject(MESSAGE, message);
    	}catch(Exception e){
    		logger.error("Error in saving merchant.", e);
    		if(merchantId == null){
    		mav.addObject(ERROR, error.generateError(9002));
    		return mav;
    		}
    		mav.addObject(ERROR, error.generateError(1004));
        	return mav;
    	}
    return mav;	
    }

    
    
    
    
    /**
     * Service to register merchant who is using partner's plugin
     *
     * @param partner_email
     * @param merchant_email
     * @param api_key
     * @return Id of the merchant
     */

    @RequestMapping(value = "/find-merchants", method = RequestMethod.POST)
    public ModelAndView findMerchant(
            @RequestParam(value = "partner_email", required = false) String partnerEmail,
            @RequestParam(value = "api_key", required = false) String apiKey) {
    	
    	ModelAndView mav = new ModelAndView();
    	ApiPartner apiPartner =null;
    	Error error = new Error();
    	Message message = new Message();
    	
    	if(StringUtils.isEmpty(partnerEmail)){
        	mav.addObject(ERROR, error.generateError(1003));
            return mav;
        }
    	if(StringUtils.isEmpty(apiKey)){
        	mav.addObject(ERROR, error.generateError(1003));
            return mav;
        }
    	
    	ApiPartnerExample partnerExample = new ApiPartnerExample();
    	partnerExample.createCriteria().andPartnerEmailEqualTo(partnerEmail).andApiKeyEqualTo(apiKey);
    	
    	try{
    		apiPartner = adminService.findApiPartner(partnerExample);
    	}catch(Exception e){
    		logger.error("Error in finding ApiPartner", e);
    		mav.addObject(ERROR, error.generateError(1004));
        	return mav;
    	}
    	if(apiPartner == null){
    		mav.addObject(ERROR, error.generateError(1002));
        	return mav;
    	}
    	
    	MerchantPartnerExample merchantPartnerExample = new MerchantPartnerExample();
    	merchantPartnerExample.createCriteria().andPartnerEmailEqualTo(partnerEmail);
    	
    	try{
    		List<MerchantPartner> merchantPartnerList = adminService.findMerchantsForPartner(merchantPartnerExample);
    		mav.addObject(merchantPartnerList);
    	}catch(Exception e){
    		logger.error("Error in saving merchant.", e);
    		mav.addObject(ERROR, error.generateError(1004));
        	return mav;
    	}
    return mav;	
    }


}

