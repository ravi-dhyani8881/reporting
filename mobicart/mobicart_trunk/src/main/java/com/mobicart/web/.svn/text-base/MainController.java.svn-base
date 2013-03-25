package com.mobicart.web;


import java.math.BigDecimal;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobicart.model.App;
import com.mobicart.model.Contact;
import com.mobicart.model.DiskSpacePricing;
import com.mobicart.model.SiteConstant;
import com.mobicart.model.User;
import com.mobicart.service.AdminService;
import com.mobicart.service.AppService;
import com.mobicart.service.user.UserService;
import com.mobicart.util.EmailGenerator;
import com.mobicart.util.PathLocator;
import com.mobicart.util.PaypalResponse;
import com.mobicart.util.ResourceProperties;
import com.mobicart.util.Sender;
import com.mobicart.web.account.RegisterForm;


@Controller
public class MainController {
	
	/**
	 * Logger for this class
	 */
	 private final Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	 
	 private static final String MY_ACCOUNT_SERVICES_SUCCESS_KEY ="account/personalDetailsServicesSuccess";
	 
	 private static final String USER_VIEW_KEY = "login";
	 
	

	@Autowired
	AdminService adminService;

	@Autowired
	Sender sender;
	
	
	@Autowired
	AppService appService;
	
	
	@Autowired
    UserService userService;
	
	/**
	 * Index
	 * 
	 * @return path to views/faqs.jsp
	 */

	@RequestMapping("/index")
	public String index(@ModelAttribute RegisterForm registerForm,
			BindingResult result, ModelMap modelMap) {
		return "index";
	}

	
	/**
	 * Index
	 * 
	 * @return path to views/faqs.jsp
	 */

	/*@RequestMapping("/indexinplace")
	public String indexInplace(@ModelAttribute RegisterForm registerForm,
			BindingResult result, ModelMap modelMap) {
		return "index";
	}*/
	
	/**
	 * timeout
	 * 
	 * @return path to timeout
	 */
	@RequestMapping("/home")
	public String index() {
		return "index";
	}

	/**
	 * timeout
	 * 
	 * @return path to timeout
	 */
	/*@RequestMapping("/timeout")
	public String timout() {
		return "timeout";
	}*/

	/**
	 * frequently asked questions
	 * 
	 * @return path to views/faqs.jsp
	 */

	/*@RequestMapping("/faqs")
	public String faqs(ModelMap modelMap) {
		return "faqs";
	}*/

	/*@RequestMapping("/tour")
	public String tour(ModelMap modelMap) {
		return "tour";
	}*/
	
	
	
	
	/*@RequestMapping("/pricing")
	public String pricing(ModelMap modelMap) {
		return "pricing";
	}*/
	
	/*@RequestMapping("/gallery")
	public String gallery(ModelMap modelMap) {
		return "gallery";
	}*/
	/**
	 * Terms and conditions
	 * 
	 * @return path to views/terms.jsp
	 */

	/*@RequestMapping("/terms")
	public String terms(ModelMap modelMap) {
		return "terms";
	}*/

	/**
	 * Contact page
	 * 
	 * @return path to views/faqs.jsp
	 */

	/*@RequestMapping("/contact")
	public String contact(@ModelAttribute Contact contact, ModelMap modelMap) {
		return "contact";
	}*/

	
	/**
	 * Credits
	 * 
	 * @return path to views/faqs.jsp
	 */

	/*@RequestMapping("/credits")
	public String credits(ModelMap modelMap) {
		return "credits";
	}*/

	/**
	 * Blog
	 * 
	 * @return path to views/blog.jsp
	 */

	/*@RequestMapping("/blog")
	public String blog() {
		return "blog";
	}*/

	/**
	 * Addons
	 * 
	 * @return path to views/addons.jsp
	 */

	/*@RequestMapping("/addons")
	public String addons(ModelMap modelMap) {
		return "addons";
	}*/

	/**
	 * Features
	 * 
	 * @return path to views/features.jsp
	 */

	/*@RequestMapping("/features")
	public String features(ModelMap modelMap) {
		return "features";
	}*/

	/**
	 * Press
	 * 
	 * @return path to views/press.jsp
	 */

	/*@RequestMapping("/press")
	public String press(ModelMap modelMap) {
		return "press";
	}*/

	/**
	 * Buzz
	 * 
	 * @return path to views/buzz.jsp
	 */

	/*@RequestMapping("/buzz")
	public String buzz(ModelMap modelMap) {
		return "buzz";
	}*/

	/**
	 * Help
	 * 
	 * @return path to views/help.jsp
	 */

	/*@RequestMapping("/help")
	public String help(ModelMap modelMap) {
		return "help";
	}*/

	/**
	 * Documentations
	 * 
	 * @return path to views/help.jsp
	 */

	/*@RequestMapping("/documentation")
	public String documentation(ModelMap modelMap) {
		return "documentation";
	}*/

	/**
	 * 404
	 * 
	 * @return path to views/404.jsp
	 */

	@RequestMapping("/404")
	public String chaarSauChaar() {
		return "404";
	}



	
	
	
	
	@RequestMapping("/payment/return")
	public String showPaypalReturnPost(ModelMap modelMap, HttpServletRequest request) throws Exception {

		@SuppressWarnings("rawtypes")
		Enumeration paramNames = request.getParameterNames();
		while(paramNames.hasMoreElements()) {
			String paramName = (String)paramNames.nextElement();
			logger.info("paramname is "+paramName);
			String[] paramValues = request.getParameterValues(paramName);
			if (paramValues.length == 1) {
				String paramValue = paramValues[0];
				if (paramValue.length() == 0){
					logger.info("--- No Value---");
				}else{
					logger.info("paramValue is :"+paramValue);
				}
			} else {
				logger.info("paramValues are :");
				for(int i=0; i<paramValues.length; i++) {
					logger.info(paramValues[i]);
				}

			}

		}
		
		HttpSession session=request.getSession();
		
		User user = (User) session.getAttribute("user");
		
		String custom=request.getParameter("cm");
		
		String userAppPair=request.getParameter("cm");
		if(userAppPair==null){
			userAppPair=request.getParameter("custom");
		}

		SiteConstant siteConstant=	adminService.findSiteConstants();

		BigDecimal removeBrandingFee =	siteConstant.getfRemoveBrandingFee();
	
		String status =request.getParameter("st");
		String amount = request.getParameter("amt");
	
		logger.info("custom value is :"+custom);
		
		String[] userApp = userAppPair.split(",");
		String username = userApp[0];
		
		logger.info("length is :->"+userApp.length);
		
		if(userApp == null || userApp.length <=1){
			session.setAttribute("user", null);
		return "redirect:/redirect/login";
		}
		
		String customPaymentType =null;
		customPaymentType = userApp[1];
		
		if(user== null){
		user=userService.findByEmail(username);
		session.setAttribute("user", user);
		}
		
		
		String diskSpacePricingId = null;
		if(userApp.length > 2){
			diskSpacePricingId = userApp[2];	
		}
		 
		logger.info("Custom Payment Type is :->"+customPaymentType);
		
		BigDecimal expectedAmount=new BigDecimal(0);
		
		if(customPaymentType.indexOf("RemoveBranding")>=0){
			expectedAmount=	expectedAmount.add(removeBrandingFee);
		}
		if(customPaymentType.indexOf("DiskSpace")>=0){
			if(diskSpacePricingId !=null){
			DiskSpacePricing diskSpacePricing=userService.findDiskSpacePricingById(new Long(diskSpacePricingId));
			expectedAmount= expectedAmount.add(diskSpacePricing.getfPrice());
			}
		}
		BigDecimal paypalAmount=new BigDecimal(amount);
		Boolean paymentTinckered=false;
		logger.info("expectedAmount is :-->"+expectedAmount);
		logger.info("paypalAmount is:->"+paypalAmount);
		logger.info("compare status is:-> "+paypalAmount.compareTo(expectedAmount));
		
		if(paypalAmount.compareTo(expectedAmount)!=0 ){
			paymentTinckered=true;
		}
		
		if(custom !=null){
			
			if(status.equalsIgnoreCase("Pending")&& !paymentTinckered){
				user.setbCustomCopyrightPage(true);
				session.setAttribute("user", user);
				modelMap.put("paymentSuccess","Payment has been sent");
				modelMap.put("thanks", "none");
				modelMap.put("payment", "none");
				modelMap.put("paymentTinkered", "none");
				modelMap.put("paymentThanks", "none");
				modelMap.put("paymentSent", "block");
				modelMap.put("paymentMessage", "Payment is Done Successfully.");
				logger.info("payment done");
			}else{
				logger.info("payment tinkered");
				modelMap.put("paymentError","Payment failed");
				modelMap.put("thanks", "none");
				modelMap.put("payment", "none");
				modelMap.put("paymentTinkered", "block");
				modelMap.put("paymentThanks", "none");
				modelMap.put("paymentSent", "none");
				modelMap.put("paymentMessage", "Payment is Failed due to some network problem or Payment is Tinkered.");
			}
			
			modelMap.put("tabSelect", 4);
			modelMap.put("redirectedFromPayment", "YES");
			return MY_ACCOUNT_SERVICES_SUCCESS_KEY; 
		} 
		
		
		
		return MY_ACCOUNT_SERVICES_SUCCESS_KEY;
	}
	
	
	/**
	 * User login view
	 * @return path to views/login.jsp
	 */
	@RequestMapping("redirect/login")
	public String login(HttpServletRequest request) {
		request.setAttribute("error", "Your session has expired.Please login again and check your billing history to validate your payment.");
		return USER_VIEW_KEY;
	}


	/**
	 * 
	 * @param modelMap
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping("/payment/notification")
	public @ResponseBody String showPaypalNotifyPost(ModelMap modelMap,HttpServletRequest request) throws Exception {
		
		String message="Success";
		logger.info("paypal notify hit");

		try{
			BigDecimal paymentGross=BigDecimal.valueOf(0L);
			BigDecimal paymentFee=BigDecimal.valueOf(0L);

			String paymentStatus=request.getParameter("payment_status");
			String payerId=request.getParameter("payer_id");
			String userAppPair=request.getParameter("custom");
			
			String payerStatus=request.getParameter("payer_status");

			String paymentDate=request.getParameter("payment_date");
			String txnId=request.getParameter("txn_id");


			try{
				paymentGross=BigDecimal.valueOf(Double.valueOf(request.getParameter("payment_gross")));
			}catch(Exception e){
				logger.error("gross payment",e);
			}
			try{
				paymentFee=BigDecimal.valueOf(Double.valueOf(request.getParameter("payment_fee")));
			}catch(Exception e){
				logger.error("payment fee",e);
			}
			logger.info("payer_status:" + payerStatus);
			logger.info("payment_status:" + paymentStatus);
			logger.info("custom:" + userAppPair);
			logger.info("payment_gross:" + paymentGross);
			logger.info("payment_date:" + paymentDate);
			logger.info("txn_id:" + txnId);

			@SuppressWarnings("rawtypes")
			Enumeration paramNames = request.getParameterNames();
			while(paramNames.hasMoreElements()) {
				String paramName = (String)paramNames.nextElement();

				logger.info("Notifications paramName:" + paramName);

				String[] paramValues = request.getParameterValues(paramName);
				if (paramValues.length == 1) {
					String paramValue = paramValues[0];
					if (paramValue.length() == 0){
						logger.info("No Value in paramValue of Notifications");
					}
					else{
						logger.info("Notifications paramValue :" , paramValue);
					}
				} else {
					logger.info("Notifications paramValues are :");
					for(int i=0; i<paramValues.length; i++) {
						logger.info(paramValues[i]);
					}

				}

			}
			String[] userApp = userAppPair.split(",");
			String username = userApp[0];
			String customPaymentType = userApp[1];
			String diskSpacePricingId = null;
			if(userApp.length > 2){
				diskSpacePricingId = userApp[2];	
			}
			 
			logger.info("Custom Payment Type is :->"+customPaymentType);
			
			BigDecimal totalAmount = paymentGross;
			
			User user = userService.findByEmail(username);
			logger.info("in showPaypalNotifyPost status is :"+paymentStatus+ " User is :"+username);
			
			//Checking for Payment Tinkering
			
			BigDecimal expectedAmount=new BigDecimal(0);
			
			SiteConstant siteConstant=	adminService.findSiteConstants();
			
			BigDecimal removeBrandingFee =	siteConstant.getfRemoveBrandingFee();
			
			
			if(customPaymentType.indexOf("RemoveBranding")>=0){
				if(!user.isbCustomCopyrightPage()){
					expectedAmount= expectedAmount.add(removeBrandingFee);
				}
			}
			DiskSpacePricing diskSpacePricing=null;
			if(customPaymentType.indexOf("DiskSpace")>=0){
				if(diskSpacePricingId !=null){
				diskSpacePricing=userService.findDiskSpacePricingById(new Long(diskSpacePricingId));
				expectedAmount= expectedAmount.add(diskSpacePricing.getfPrice());
				}
			}
			BigDecimal paypalAmount=paymentGross;
			Boolean paymentTinckered=false;
			logger.info("expectedAmount is :-->"+expectedAmount);
			logger.info("paypalAmount is:->"+paypalAmount);
			logger.info("compare status is:-> "+paypalAmount.compareTo(expectedAmount));
			if(paypalAmount.compareTo(expectedAmount)!=0 ){
				paymentTinckered=true;
			}
			logger.info("Payment tinckered status is:-> "+paymentTinckered);
			
			if((paymentStatus.equalsIgnoreCase("Pending") || paymentStatus.equalsIgnoreCase("Completed")) && !paymentTinckered)
			{
			PaypalResponse paypalResponse=new PaypalResponse();	
			App app=appService.findAppByUser(user);
			paypalResponse.setUser(user);
			paypalResponse.setApp(app);
			paypalResponse.setAppId(app.getId());
			paypalResponse.setPaymentGross(paymentGross);
			paypalResponse.setPaymentFee(paymentFee);
			paypalResponse.setStoreId(user.getStoreId());
			paypalResponse.setPayerId(payerId);
			paypalResponse.setPaymentStatus("completed");
			paypalResponse.setTxnId(txnId);
			paypalResponse.setSiteConstant(siteConstant);
			paypalResponse.setPaymentDate(new Date(paymentDate));	
			paypalResponse.setDiskSpacePricing(diskSpacePricing);
			String emailSubjectToMerchant="";
			String emailSubjectToAdmin="";
			String serviceType="branding-diskspace";
			logger.info("customPaymentType.indexOf(\"RemoveBranding\") is :->"+customPaymentType.indexOf("RemoveBranding"));
			logger.info("customPaymentType.indexOf(\"DiskSpace\") is :->"+customPaymentType.indexOf("DiskSpace"));
			if(customPaymentType.indexOf("RemoveBranding")>=0 && customPaymentType.indexOf("DiskSpace")>=0){
				paypalResponse.setRemarks("Remove branding and DiskSpace");
				emailSubjectToMerchant="MobiCart Remove Branding and Additional Disk Space Submission";
				emailSubjectToAdmin="Remove Branding and Additional disk space Submission";
				sendEmailForServices(user,"","MobiCart Remove Branding Submission","Remove Branding Submission",totalAmount,"Remove Branding","has Removed Branding from app.");
				sendEmailForServices(user,"","MobiCart Additional Disk Space Submission","Additional disk space Submission",totalAmount,"Additional Disk Space","bought Additional Disk Space for his store");
				logger.info("coming here in First IF");
				try{
				serviceType="branding";
				paypalResponse.setServiceType(serviceType);
				userService.saveMerchantOrderDetails(paypalResponse);
				serviceType="diskspace";
				paypalResponse.setServiceType(serviceType);
				userService.saveMerchantOrderDetails(paypalResponse);
				}catch(Exception e){
					logger.info("saveMerchantOrderDetails in MainController",e);
				}
			}else if(customPaymentType.indexOf("RemoveBranding")>=0){
				paypalResponse.setRemarks("Remove Branding");
				serviceType="branding";
				paypalResponse.setServiceType(serviceType);
				emailSubjectToMerchant="MobiCart Remove Branding Submission";
				emailSubjectToAdmin="Remove Branding Submission";
				sendEmailForServices(user,"",emailSubjectToMerchant,emailSubjectToAdmin,totalAmount,"Remove Branding","has Removed Branding from app.");
				logger.info("coming here in Second IF");
				try{
				userService.saveMerchantOrderDetails(paypalResponse);
				}catch(Exception e){
					logger.info("saveMerchantOrderDetails in MainController",e);
				}
			}else if(customPaymentType.indexOf("DiskSpace")>=0){
				paypalResponse.setRemarks("Additional DiskSpace");
				serviceType="diskspace";
				paypalResponse.setServiceType("diskspace");
				emailSubjectToMerchant="MobiCart Additional Disk Space Submission";
				emailSubjectToAdmin="Additional disk space Submission";
				logger.info("coming here in Third IF");
				sendEmailForServices(user,"",emailSubjectToMerchant,emailSubjectToAdmin,totalAmount,"Additional Disk Space","bought Additional Disk Space for his store.");
				try{
				userService.saveMerchantOrderDetails(paypalResponse);
				}catch(Exception e){
					logger.info("saveMerchantOrderDetails in MainController",e);
				}
			}
			user.setbCustomCopyrightPage(true);
			logger.info("diskSpacePricingId is :->"+diskSpacePricingId);
			
			if(diskSpacePricingId != null){
				user.setDiskSpacePricingId(new Long(diskSpacePricingId));
				
			}else{
				logger.info("DiskPricingId is :->"+diskSpacePricingId);
			}
			userService.updateUser(user);
			userService.saveRecurringPaymentDetails(paypalResponse);
	}
		}catch(Exception e){
			logger.error("some error occured in post payment procesing or payment is tinkered",e);
			message="some error occured in post payment procesing";
		}
		
		return message; // return
	}
	
	public void sendEmailForServices(User user,String messageBody, String subjectForMerchant,String emailSubjectToAdmin, BigDecimal totalAmount, String serviceType,String adminMessage){
		
		
		try {
			PathLocator path = new PathLocator();
			/**  mail to merchant**/
			EmailGenerator emailToMerchent = new EmailGenerator();
			emailToMerchent.setFromEmail(ResourceProperties
					.getString("adminEmail"));
			emailToMerchent.setToEmail(user.getUsername());
			emailToMerchent.setSubject(subjectForMerchant);
			emailToMerchent.setTemplateName(path.getRealPath()
					+ "emailTemplate/REMOVE_BRANDING_NOTIFICATION");
			String name = user.getsFirstName() != null ? user
					.getsFirstName()
					: "" + user.getsLastName() != null ? " "
							+ user.getsLastName() : " ";
							HashMap<String, String> param = new HashMap<String, String>();
							param.put("_NAME_", name);
							param.put("_AMOUNT_", totalAmount.toString());
							param.put("_BASEPATH_", ResourceProperties.getString("appUrl"));
							param.put("_SERVICETYPE_", serviceType);
							param.put("_ADMINMESSAGE_", adminMessage);
							emailToMerchent.setParam(param);
							sender.sendMail(emailToMerchent);
							logger.info("app submitted to merchant");

							/**  mail to super admin**/
						
							EmailGenerator emailToAdmin = new EmailGenerator();
							emailToAdmin.setFromEmail(ResourceProperties
									.getString("adminEmail"));
							emailToAdmin.setSubject(emailSubjectToAdmin);
							emailToAdmin.setTemplateName(path.getRealPath()
									+ "emailTemplate/REMOVE_BRANDING_NOTIFICATION_TO_ADMIN");
							emailToAdmin.setToEmail(ResourceProperties
									.getString("adminEmail"));
							emailToAdmin.setBccEmail(ResourceProperties
									.getString("bccEmail"));

							param.put("_MERCHANTNAME_", name);
							param.put("_AMOUNT_", totalAmount.toString());
							param.put("_DEVEMAIL_",
									ResourceProperties.getString("devEmail"));
							param.put("_MERCHANTEMAIL_", user.getUsername());
												param.put("_BASEPATH_", ResourceProperties.getString("appUrl"));

													emailToAdmin.setParam(param);
													sender.sendMail(emailToAdmin);

													logger.info("app submitted to admin");

							logger.info("remove Branding and Additional disk space changes mail sent to developer");

		} catch (Exception e) {
			logger.error("some error occured in sending email",e);
		}
		
	}
}



