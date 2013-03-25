package com.ytk.utility;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ytk.client.CustomClient;



@Controller
@RequestMapping("/delete/*")
public class DeleteSolrUtil {

	
	private static final Logger logger = LoggerFactory.getLogger(DeleteSolrUtil.class);
	
	@Autowired
	LogDetails logDetails;
	
	
	private static final String DELETE_SOLR__PAGE="deleteSolr";
	
	
	@Autowired
	ServerurlConstants serverurlConstants;
	
	@RequestMapping(value = "/deleteAll")
	public ModelAndView deleteSolrHives( HttpServletRequest request,
			@RequestParam(value = "Type", required = false) String Type
	
	){
		  ModelAndView mav = new ModelAndView();
		
		try{
			String url=null;
			
			if(Type.equals("Connection"))
				url=serverurlConstants.ADD_CONNECTION_URL;
			if(Type.equals("DiscussionQuestion"))
				url=serverurlConstants.ADD_DISSCUSSION_QUESTION_URL;
			if(Type.equals("Faces"))
				url=serverurlConstants.ADD_FACES_URL;
			if(Type.equals("Folder"))
				url=serverurlConstants.ADD_FOLDER_URL;
			if(Type.equals("Messages"))
				url=serverurlConstants.ADD_MESSAGES_URL;
			if(Type.equals("Neibhorhood"))
				url=serverurlConstants.ADD_NEIBHOURHOOD_URL;
			if(Type.equals("Placereview"))
				url=serverurlConstants.ADD_PLACES_REVIEW_URL;
			if(Type.equals("Places"))
				url=serverurlConstants.ADD_PLACES_URL;
			if(Type.equals("PlanGuest"))
				url=serverurlConstants.ADD_PLAN_GUEST_URL;
			if(Type.equals("Plans"))
				url=serverurlConstants.ADD_PLANS_URL;
			
			if(Type.equals("Things"))
				url=serverurlConstants.ADD_THINGS_URL;
			if(Type.equals("Updates"))
				url=serverurlConstants.ADD_UPDATE_URL;
			
			
				Adder.deleteAllInstances(url);
				String result = "success";
				mav.addObject("result",result);
			
			
			
		}catch (Exception e) {
			logDetails.getException(e, logger, request);
			e.printStackTrace();
		}
		return mav;
		
	
	}
	
	
	
	@RequestMapping(value="/doGetSolrDelete" , method=RequestMethod.GET)
	public String getcartTaxes( HttpServletRequest request){
		
		return DELETE_SOLR__PAGE;
	}
	
	
	
	
	
}
