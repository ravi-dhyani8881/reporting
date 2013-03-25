package com.ytk.controller;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.ytk.client.SearchCommunityClient;
import com.ytk.client.SearchThingsClient;
import com.ytk.models.ListCommunity;
import com.ytk.models.ResultDoc;
import com.ytk.models.ListCommunity.Communities;
import com.ytk.models.ListCommunity.CommunitiesOut;
import com.ytk.models.ListContact.Contacts;
import com.ytk.models.ListContact.ContactsOut;


@Controller
@RequestMapping("/searchCommunity/*")
public class SearchCommunityController {
	
	@Autowired
	SearchCommunityClient searchCommunityClient;
	
	
	public String totalRecord  = "0";
	
	@RequestMapping(value = "/findMembers")
	public ModelAndView findMembers(
			@RequestParam(value = "member_name", required = false) String commName,
			@RequestParam(value = "page", required = false) int limit,
			@RequestParam(value = "nearby", required = false) String nearby,
			@RequestParam(value = "jsoncallback", required = false) String search){
		
		ModelAndView mav = new ModelAndView();
		int pageCount = limit * 10;
		commName = commName.replace(" ", "+");
		//String  queryText =  " (nm:*"+commName+" ) OR"+	 "(ct:"+commName+" ) OR"+	 "(st:"+commName+" ) OR"+	 "(web:"+commName+" ) OR"+	 "(pcat:"+commName+" ) ";
		String  queryText ="";
		commName = commName.toLowerCase();
		if(nearby == null)	
		{
			queryText =  " (nm:"+commName+"* )";
		}
		else
		{
			queryText =  " (nm:*"+commName+" ) AND ((neigh:*"+nearby+" ) OR"+ "(ct:"+nearby+" ) OR" + "(st:"+nearby+" ) OR" + "(add1:"+nearby+" ) OR" + "(z:"+nearby+" ))";
		}
			ListCommunity contactList = searchCommunityClient.getCommunity(queryText,pageCount,10);
			List <Communities> fullContacts = contactList.getResponse().getDocs();
			totalRecord =  contactList.getResponse().getNumFound().toString();
			if(fullContacts.size() > 0)
			{
				//Collections.sort(fullContacts);
				List <CommunitiesOut> outCommunities = searchCommunityClient.CreateListing(fullContacts);
				if(search == null)
				{
					mav.addObject(outCommunities);
				}
				else
				{
					ResultDoc resultDoc = new ResultDoc();
					resultDoc.setCommunity(outCommunities);
					resultDoc.setSize(outCommunities.size());
					mav.addObject(resultDoc);
				}
			}
			else
			{
				List <CommunitiesOut> outCommunities = new ArrayList<CommunitiesOut>();
				CommunitiesOut commOut = new CommunitiesOut();
				outCommunities.add(commOut);
				ResultDoc resultDoc = new ResultDoc();
				resultDoc.setCommunity(outCommunities);
				resultDoc.setSize(0);
				mav.addObject(resultDoc);
	
			}
				return mav;
		}
	
	
	
}
	


