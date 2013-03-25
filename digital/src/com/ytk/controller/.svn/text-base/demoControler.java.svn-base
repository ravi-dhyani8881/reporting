package com.ytk.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.solr.common.SolrInputDocument;
import org.directwebremoting.util.LogErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



import com.ytk.client.DemoClient;
import com.ytk.models.DiscussionQuestion;
import com.ytk.utility.Adder;
import com.ytk.utility.LogDetails;
import com.ytk.utility.ServerurlConstants;


@Controller
@RequestMapping("/demo/*")
public class demoControler{

	private static final Logger logger = LoggerFactory.getLogger(demoControler.class);
	
	@Autowired
	ServerurlConstants serverurlConstants;
	
	@Autowired
	LogDetails logDetails;
	
	
	@Autowired
	DemoClient demoClient;
	
	@RequestMapping(value = "/addDemo")
	public ModelAndView addQuestion(
			@RequestParam(value = "ID", required = false) int ID,
			@RequestParam(value = "Type", required = false) String Type,
			@RequestParam(value = "Section", required = false) String Section
		)
			{
		
		ModelAndView mav = new ModelAndView();
		SolrInputDocument question = new SolrInputDocument();
		
		question.addField("ID", ID);
		question.addField("Type", "Type");
		
		question.addField("Section", "Section");
		
		System.out.println("Added---->"+ID);
		
		Adder.addDiscussionQuestions(serverurlConstants.ADD_DEMO_URL ,question);
		String result = "success";
		mav.addObject("result",result);
		return mav;
	}
	
	
	@RequestMapping(value = "/getDemo")
	public ModelAndView getByOldId(	HttpServletRequest request, HttpClientParams param,		
			@RequestParam(value = "query", required = false) String query,
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "rows", required = false) int rows
	){
		logger.info("Log starat"+page);
		
		
		ModelAndView mav = new ModelAndView();
		int numFound  = 0;
		Object[] resultArr = null;
		String queryText = "";
		
		
	    ArrayList<String> al=new ArrayList<String>();
	    al.add("ravi");
		List<DiscussionQuestion> questions = new ArrayList<DiscussionQuestion>();
		Object[] resultArrDiscussion = null;
	
		queryText = "(Que:"+query+")";	
		System.out.println("Que--->"+queryText);
		try{
	//		resultArr = demoClient.getData(queryText,page,rows);
	//		numFound  = Integer.parseInt(resultArr[1].toString());
	//	questions=(List<DiscussionQuestion>) resultArrDiscussion[0];
		
			al.get(10);
	
	//	mav.addObject("Collection",resultArr[0]);
	//	mav.addObject("TotalRecords",numFound);
		
		mav.addObject("IsConnected","true");
		}
		catch (Exception e) {
			e.printStackTrace();
		//	logger.debug("error"+e);
		//	logger.error("User ip"+request.getRequestURI());
		//	logger.error("Request parameter"+request);
			
			
			logDetails.getException(e , logger , request);
			
		//	logger.error("These are System Genrated Email Please Donot Reply On That"+"\n"+"\n"+e+"\n"+"\n"+"Have a Nice Day");
			
	//	logger.error("These are System Genrated Email Please Donot Reply On That"+"\5n"+e+"\5n"+"Have a Nice Day");
						
		//	logger.error("Remote Ip"+param.getConnectionManagerClass().getTypeParameters());
		//	logger.error("Remote Ip"+request.getRequestURL());
		//	logger.error("error"+e);
			
		}
		return mav;
	}
	
	
	@RequestMapping(value = "/deletealldemo")
	public ModelAndView deleteAllFaces()
	{
		
		ModelAndView mav = new ModelAndView();
		Adder.deleteAllInstances(ServerurlConstants.DELETE_ALL_DEMO_URL);
		String result = "success";
		mav.addObject("result",result);
		return mav;
	}
	
	
	
}
