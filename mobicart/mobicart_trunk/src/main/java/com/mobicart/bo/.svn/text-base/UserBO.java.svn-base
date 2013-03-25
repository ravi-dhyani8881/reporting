package com.mobicart.bo;


import javax.servlet.http.HttpServletRequest;

import com.mobicart.model.User;


/**
 * Get logged in user
 * @author jasdeep.singh
 *
 */
public class UserBO {
	

	/**
	 * get logged in user
	 * @param request
	 * @return {@link User} object
	 */
	public static User getLoggedInUser(HttpServletRequest request) {
		try {
			User user = (User) request.getSession().getAttribute("user");
			return user;
		} catch (Exception e) {
			return new User(0L);
		}

	}
	public static String androidAppPackageName(String appName,Long userId){
		
		return ("App"+appName+"_"+userId).replace(" ", "_").replace(".", "_").replace("-", "_");
		
		
		
		 
		
	}
	
	

	
}
