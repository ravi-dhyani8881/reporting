package com.ytk.utility;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.util.LogErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ytk.controller.demoControler;

@Component("logDetails")
public class LogDetails {

	public void getException( Exception e , Logger logger , HttpServletRequest request){
		
		
		String serviceUrl=request.getRequestURI();
		String remoteUrl=request.getHeader("X-FORWARDED-FOR");
		
		if(remoteUrl==null)
			remoteUrl=request.getRemoteAddr();
		
		 serviceUrl=serviceUrl;
		 
		 
		String log="These are System Genrated Email Please Donot Reply On That"+"\n"+"\n"+"Error Message:"+e+"\n"+"\n"+"Service Url:"+serviceUrl+"\n"+"\n"+"RemoteIp:"+remoteUrl+"\n"+"\n"+"Have a Nice Day";
		
	//	System.out.println(log);
		
		logger.error(log);
		
		
		
		
		
		System.out.println(request.getRequestURI());
		System.out.println(request.getRemoteAddr());
		System.out.println(request.getRemoteHost());
		System.out.println(request.getRemoteUser());
		System.out.println(request.getParameterNames());
		System.out.println(request.getContextPath());
		System.out.println(request.getLocalName());
		System.out.println(request.getMethod());
		System.out.println(request.getQueryString());
		System.out.println(request.getLocale());
		System.out.println(request.getParameterMap());
		
		System.out.println(request.getHeader("VIA"));
		System.out.println(request.getHeader("X-FORWARDED-FOR"));
		
		

	}
	
}
