package com.ytk.controller;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ytk.client.SearchCommunityClient;
import com.ytk.client.SearchContactsClient;
import com.ytk.client.SearchThingsClient;
import com.ytk.models.ListCommunity;
import com.ytk.models.ListContact;
import com.ytk.models.ResultDoc;
import com.ytk.models.Things;
import com.ytk.models.ListCommunity.Communities;
import com.ytk.models.ListCommunity.CommunitiesOut;
import com.ytk.models.ListContact.Contacts;
import com.ytk.models.ListContact.ContactsOut;


@Controller
@RequestMapping("/searchMain/*")
public class MainSearchController {
	
	/**
	 * Service to Search Members , Things, Places
	 * 
	 * @param member_id
	 * 
	 * @return a List of Users in response
	 */
	
	@Autowired
	SearchContactsClient searchContactsClient;
	
	@Autowired
	SearchThingsClient searchThingsClient;
	
	@Autowired
	SearchCommunityClient searchCommunityClient;	

	
	@RequestMapping(value = "/findMain")
	public ModelAndView findMain(HttpServletRequest 
			request, HttpServletResponse response,
			@RequestParam(value = "member_id", required = false) String memberId,
			@RequestParam(value = "member_name", required = false) String memberName,
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "jsoncallback", required = false) String search){
		
		ModelAndView mav = new ModelAndView();
		
		if(memberId == null || memberId.equals("")){
			String ErrorMessage = "Member-Id is required";
			mav.addObject("Error", ErrorMessage);
			return mav;	
		}else {
			memberName = memberName.toLowerCase();
			String queryText = "";
	       // response.setHeader("Pragma", "No-cache");
	        response.setHeader("Cache-Control", "public, max-age=1200");
	        response.setDateHeader("Expires", 1);
			
			//response.setHeader("Cache-Control","private ,max-age=120");
			//response.setHeader("Cache-Control","max-age=120");

			//PROFILES
			List <Contacts> fullContacts = this.getContacts(memberName, page);
			ListContact myContactsList  = searchContactsClient.getUserContacts(memberId,memberName,0);
			for (Contacts cons : fullContacts)
			{
				if( myContactsList.getResponse() != null)
				{
					for (Contacts myCons  :  myContactsList.getResponse().getDocs())
					{
						if(cons.getId().equals(myCons.getId()))
						{
							cons.setIs_myFriend("true");
						}
					}
				}
			}
			Collections.sort(fullContacts);
			List <ContactsOut> outContacts = CreateListing(fullContacts);
			
			// COMMUNITIES
			queryText =  " (nm:"+memberName+"* )";
			ListCommunity contactList = searchCommunityClient.getCommunity(queryText,page,8);
			List <Communities>fullCommunity = contactList.getResponse().getDocs();
			List <CommunitiesOut> outCommunities = CreateCommunityListing(fullCommunity);
			
			//THINGS
			 queryText =  "(KeyWord:"+memberName+"*)";
			List <Things> thingsList =  new ArrayList<Things>();//searchThingsClient.queryCheck(queryText,page,8);
			
			if(fullContacts.size() > 0 || outCommunities.size() > 0 || thingsList.size() > 0)
			{
				int countContacts  = 6;
				int countCommunities    = 1;
				int countThings         = 1;
				if(search == null)
				{
					ResultDoc resultDoc = new ResultDoc();
					resultDoc.setContacts(outContacts);
					resultDoc.setCommunity(outCommunities);
					resultDoc.setThings(thingsList);
					resultDoc.setSize(outContacts.size());
					mav.addObject(resultDoc);
				}
				else
				{
					
/*****
 * 0,4,4
 * 1,4,3
 * 2,3,3
 * 3,3,2
 * 4,2,2
 * 5,2,1
 * 6,1,1
 * 7,1,0
 * 8,0,0					
 */
					
					
					
					
					if(outContacts.size() < 8)
					{
						countContacts = outContacts.size();
						countCommunities = (8-countContacts);
					}
						if(outCommunities.size() < countCommunities)
							countCommunities = outCommunities.size();
						countThings = 8-(countCommunities+countContacts);
						
						if(thingsList.size() < countThings || thingsList.size() == 0)
							countThings = thingsList.size();
						
						if((countContacts+countCommunities) >= 8)
							countThings = 0;	
					
					ResultDoc resultDoc = new ResultDoc();
					resultDoc.setContacts(outContacts.subList(0, countContacts));
					resultDoc.setCommunity(outCommunities.subList(0, countCommunities));
					resultDoc.setThings(thingsList.subList(0, countThings));
					resultDoc.setSize(outContacts.size());
					mav.addObject(resultDoc);
				}
			}
			else
			{
				ResultDoc resultDoc = new ResultDoc();
				resultDoc.setSize(0);
				mav.addObject(resultDoc);	
			}
		
		}
		
		return mav;
	}
	
	private List<Contacts>  getContacts(String memberName , int page)
	{
		String[] memberNameArr  = memberName.split("@");
		String queryText = "";
		int pageCount = page * 10;
		List<Contacts> contacts = null;
		CharSequence charseq = "@";
		
		if(memberName.contains(charseq))
		{
			queryText = "(email:"+memberName+")";
			contacts = searchContactsClient.queryCheck(queryText,pageCount,8);
		}
		else
		{
			queryText = "(displayname:"+memberNameArr[0] +")";
			contacts = searchContactsClient.queryCheck(queryText,pageCount,8);
		}
		return contacts;
	}
	
	public List<ContactsOut> CreateListing(List<Contacts> fullList)
	{
		List<ContactsOut> outContacts = new ArrayList<ContactsOut>();
		ContactsOut contacts = null;
		for (Contacts contactsComing : fullList)
		{
			contacts = new ContactsOut();
			contacts.setDisplayName(contactsComing.getDisplayname());
			contacts.setProfileImageName(contactsComing.getImage());
			contacts.setScreenName(contactsComing.getScreenname());
			contacts.setState(contactsComing.getCity());
			contacts.setCountry(contactsComing.getState());
			outContacts.add(contacts);
		}
		return outContacts;
	}

	public List<CommunitiesOut> CreateCommunityListing(List<Communities> fullCommunity)
	{
		List<CommunitiesOut> outCommunities = new ArrayList<CommunitiesOut>();
		CommunitiesOut community = null;
		for (Communities communityComing : fullCommunity)
		{
			community = new CommunitiesOut();
			community.setID(communityComing.getId());
			community.setName(communityComing.getNm());
			community.setSafeName(communityComing.getSfn());
			community.setImageName(communityComing.getImg());
			community.setParentCategoryList(communityComing.getPcat());
			community.setSubCategoryList(communityComing.getScat());
			
			outCommunities.add(community);
		}
		return outCommunities;
	}


}
