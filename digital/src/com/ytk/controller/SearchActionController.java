package com.ytk.controller;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.comparators.ReverseComparator;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.JsonSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.ytk.models.Contact;
import com.ytk.models.ListCollege;
import com.ytk.models.ListContact;
import com.ytk.models.ListMember;
import com.ytk.models.ResultDoc;
import com.ytk.models.ListCollege.College;
import com.ytk.models.ListContact.Contacts;
import com.ytk.models.ListMember.Members;


@Controller
@RequestMapping("/search/*")
public class SearchActionController {

	private static org.apache.log4j.Logger log = Logger.getLogger(UserActionController.class);
	
	
	/**
	 * Service to Search Members and Connections with Relevance
	 * 
	 * @param member_id
	 * 
	 * @return a List of Users in response
	 */

	@RequestMapping(value = "/findMembers")
	public ModelAndView findMembers(
			@RequestParam(value = "member_id", required = false) String memberId,
			@RequestParam(value = "member_name", required = false) String memberName){
		
		ModelAndView mav = new ModelAndView();
		
		if(memberId == null || memberId.equals("")){
			String ErrorMessage = "Member-Id is required";
			mav.addObject("Error", ErrorMessage);
			return mav;	
		}else {
			//Gson gson = new GsonBuilder().serializeNulls().create();
			
			ListContact contactList = this.getUserContacts(memberId);
			for (Contacts myCon : contactList.getResponse().getDocs())
			{
				//myCon.setIs_myFriend("true");
			}
			List<ListContact> friendsList = getFriendsContact(contactList.getResponse().getDocs());
			friendsList.add(contactList);
			
			List <Contacts> contactsFull = new ArrayList<Contacts>();
			for (ListContact contactList1 : friendsList)
			{
				List <Contacts> contactsSingle = contactList1.getResponse().getDocs();
				for (Contacts con : contactsSingle)
				{
					Boolean checkBool = false;
					for (Contacts conCheck : contactsFull)
					{
//						if(conCheck.getMember_id().equals(con.getMember_id()))
//						{
//							checkBool = true;
//							if(con.getIs_myFriend().equals("true"))
//							{
//								conCheck.setIs_myFriend("true");
//							}
//								
//						}
					}
					if(checkBool == false)
					contactsFull.add(con);
				}
			}
			List <Members> allMembers  = getUserDetails(contactsFull,memberName);
//			//Collections.sort(allMembers,Collections.reverseOrder());
//			//Collections.reverse(allMembers);
			//List <Members> getMembers = removeDuplicates(allMembers);
			Collections.sort(allMembers);
//			Collections.reverse(allMembers);
			
			mav.addObject(allMembers);
			return mav;
		}
		
		
	}
	
	// USERS OWN CONTACTS
	private ListContact getUserContacts(String memberId){
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("memberId", memberId);
		
		String result = restTemplate
		.getForObject(
				"http://192.168.0.153:8983/solr/select/?q={memberId}&version=2.2&start=0&rows=1000&indent=on&wt=json",
				String.class, vars);
		ListContact contactList = new Gson().fromJson(result, ListContact.class);
		return contactList;
	}

	// USER'S FRIEND'S CONTACTS
	private List<ListContact> getFriendsContact(List<Contacts> friendsContact){
		
		List<ListContact> friendsList  = new ArrayList<ListContact>();
		for (Contacts contact : friendsContact)
		{
			//ListContact contactLsit = getUserContacts(contact.getMember_id());
			//friendsList.add(contactLsit);
		}
		return friendsList;
	}
	
	private List<Members> getUserDetails(List<Contacts> contactsFull,String memberName){
		List <Members> memberList = new ArrayList<Members>();
		for (Contacts contact : contactsFull)
		{
//			Members member =  getMemberDetails(contact.getMember_id());
//			member.setIsConnected(contact.getIs_connected());
//			member.setIsBlocked(contact.getIs_blocked());
//			member.setIsMyFriend(contact.getIs_myFriend());
//			member.setIsMessageSent(contact.getIs_message_sent());
//			if(member.getMember_id() != null)
//			{
//				if(member.getFirst_name().toLowerCase().startsWith(memberName.toLowerCase()) )
//				memberList.add(member);
//			}
		}
		return memberList;
	}
	
	
	// GET MEMBER DETAILS
	private Members getMemberDetails(String memberId){
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("memberId", memberId);
		
		String result = restTemplate
		.getForObject(
				"http://192.168.0.153:8984/solr/select/?q={memberId}&version=2.2&start=0&rows=1000&indent=on&wt=json",
				String.class, vars);
		ListMember memberList = new Gson().fromJson(result, ListMember.class);
		if(memberList.getResponse().getDocs().size() == 0){return new Members();}
		return memberList.getResponse().getDocs().get(0);
	}
	
	public List<Members> removeDuplicates(List <Members> items) {
		HashSet<Members> hs = new HashSet<Members>();
		hs.addAll(items);
		return new ArrayList<Members>(hs);
		}
	
	@RequestMapping(value = "/findCollege")
	public ModelAndView findColleges(
			@RequestParam(value = "name", required = false) String name){
		RestTemplate restTemplate = new RestTemplate();
		ModelAndView mav = new ModelAndView();
		Map<String, String> vars = new HashMap<String, String>();
		name = name.replace("\\b\\s{2,}\\b", "+");
		vars.put("name", name);
		
		String result = restTemplate
		.getForObject(
				"http://192.168.0.153:8985/solr/select/?q={name}*&version=2.2&start=0&rows=100&indent=on&wt=json&sort=collegeId desc",
				String.class, vars);
		ListCollege collegeFullList = new Gson().fromJson(result, ListCollege.class);
		List <College> allCollege = collegeFullList.getResponse().getDocs();
		//Collections.sort(allCollege);
		if(allCollege.size() == 0)
		{
			College col = new College();
			allCollege.add(col);
		}
		ResultDoc resultDoc = new ResultDoc();
		resultDoc.setSize(allCollege.size());
		//resultDoc.setColleges(allCollege);
		mav.addObject(resultDoc);
		return mav;
	}
	
	
}
