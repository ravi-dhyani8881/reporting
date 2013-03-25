package com.ytk.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ytk.client.DateClient;
import com.ytk.client.PlatformPlaceControllerClient;
import com.ytk.client.PrivacyClient;
import com.ytk.client.SearchThingsClient;
import com.ytk.models.CollectionList;
import com.ytk.models.DiscussionQuestion;
import com.ytk.models.Place;
import com.ytk.models.ResultDoc;
import com.ytk.models.Things;
import com.ytk.utility.Adder;
import com.ytk.utility.ServerurlConstants;

@Controller
@RequestMapping("/searchthing2/*")
public class PlatformThingsController {
	
	@Autowired
	ServerurlConstants serverurlConstants;
	
	@Autowired
	DateClient dateClient;
	
	
	
	
	@Autowired
	SearchThingsClient searchThingsClient;
	
	
	
	
	@RequestMapping(value = "/deletething")
	public ModelAndView deleteThing(
			@RequestParam(value = "ID", required = false) String ID)
	{
		ModelAndView mav = new ModelAndView();
		SolrInputDocument connection = new SolrInputDocument();
		connection.addField("ID", ID);	
		Adder.deleteThings(serverurlConstants.DELETE_THINGS2_URL , connection);
		String result = "success";
		mav.addObject("result",result);
		return mav;
	}
	
	
	@RequestMapping(value = "/deleteallthings")
	public ModelAndView deleteAllThings()
	{
		String url = serverurlConstants.DELETE_THINGS2_URL;
		ModelAndView mav = new ModelAndView();
		Adder.deleteAllInstances(url);
		String result = "success";
		mav.addObject("result",result);
		return mav;
	}
	

	@RequestMapping(value = "/finddirectorythings")
	public @ResponseBody CollectionList  findDirectoryThings(
			HttpServletRequest request,
			@RequestParam(value = "starting_letter", required = false) String memberName,
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "size", required = false) int size,
			@RequestParam(value = "jsoncallback", required = false) String search){
		
		ModelAndView mav = new ModelAndView();
		
			memberName = memberName.toLowerCase();
			String[] memberNameArr  = memberName.split("@");
			String queryText = "";
			int pageCount = page * size;
			List contactsQuery = null;
			String numFound  = "0";
			Object[] resultArr = null;
			queryText = "(Name:"+memberNameArr[0] +"*)";
			resultArr = searchThingsClient.queryCheck(queryText,pageCount,size, request);
			numFound = resultArr[1].toString();//contactList.getResponse().getNumFound().toString();
			contactsQuery =(List) resultArr[0] ;
			
			List  fullContacts = contactsQuery;
			if(fullContacts.size() > 0)
			{
				if(search == null)
				{
					CollectionList coll = new CollectionList();
					coll.setCollection(contactsQuery);
					coll.setStatusOutput("true");
					coll.setTotalRecords(Long.parseLong(resultArr[1].toString()));
					return coll;
				}
				else
				{
					ResultDoc resultDoc = new ResultDoc();
					resultDoc.setSize(contactsQuery.size());
					mav.addObject(resultDoc);
				}
			}
			else
			{
				contactsQuery = new ArrayList();
				CollectionList coll = new CollectionList();
				coll.setCollection(contactsQuery);
				coll.setStatusOutput("true");
				coll.setTotalRecords(0L);
				return coll;
			}
				return null;
	}
	
	
	
	
	
	
}
