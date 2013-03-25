package com.ytk.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.ytk.models.ListConnection;
import com.ytk.models.ListContact;
import com.ytk.models.ResultDoc;
import com.ytk.models.ListCommunity.CommunitiesOut;
import com.ytk.models.ListConnection.Connections;
import com.ytk.models.ListContact.Contacts;
import com.ytk.models.ListContact.ContactsOut;
import com.ytk.models.ListMember.Members;
import com.ytk.utility.Adder;
import com.ytk.utility.ServerurlConstants;


@Controller
@RequestMapping("/searchContacts/*")
public class SearchContactsController 
{
	@Autowired
	ServerurlConstants serverurlConstants;
	
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
			
			String[] memberNameArr  = memberName.split("@");
			//Gson gson = new GsonBuilder().serializeNulls().create();
			//String queryText = "(firstname:"+memberNameArr[0] +"*)+ OR +"+"(lastname:"+memberNameArr[0] + "*)+ OR +"+"(email:"+memberNameArr[0]+")";
			String queryText = "";
			int pageCount = page * 10;
			List<Contacts> contactsQuery = null;
			String numFound  = "0";
			CharSequence charseq = "@";
			if(memberName.contains(charseq))
			{
				String encodedurl = "";
				//queryText = "(email:"+memberNameArr[0]+"*)";
				queryText = "(email:"+memberName+")";
				contactsQuery = this.queryCheck(queryText,pageCount,10);
				
				
			}
			else
			{
				queryText = "(displayname:"+memberNameArr[0] +"*)";
				contactsQuery = this.queryCheck(queryText,pageCount,10);
				//ListContact contactList = this.getContacts(queryText,pageCount);
				//contactsQuery = contactList.getResponse().getDocs();
				//numFound = contactList.getResponse().getNumFound().toString();
				
			}
			
			
			if(memberNameArr.length == 0)
			{
				mav.addObject(new ContactsOut());
				return mav;
			}
			ListContact myContactsList  = getUserContacts(memberId,memberNameArr[0],0);
			ListContact myFullContacts = getUserAllContacts(memberId,memberNameArr[0],0);
			
			for (Contacts cons : contactsQuery)
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
				ListContact userContactsList  = getUserAllContacts(cons.getId(),"",0);
				String[] contactListName  = checkMutualNames(userContactsList,myFullContacts);
				cons.setContactList(contactListName[0]);
				cons.setMutualCount(contactListName[1]);
				//cons.setTotalRecords(numFound);
				
			}
			
			List <Contacts> fullContacts = contactsQuery;
			if(fullContacts.size() > 0)
			{
				Collections.sort(fullContacts);
				List <ContactsOut> outContacts = CreateListing(fullContacts);
				if(search == null)
				{
					mav.addObject(outContacts);
				}
				else
				{
					ResultDoc resultDoc = new ResultDoc();
					resultDoc.setContacts(outContacts);
					resultDoc.setSize(outContacts.size());
					mav.addObject(resultDoc);
				}
			}
			else
			{
				mav.addObject(new ContactsOut());	
			}
				return mav;
		}
	}
	
	@RequestMapping(value = "/findUserMembers")
	public ModelAndView findUserMembers(
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
			int pageCount = page * 10;
			ListContact contactList = this.getUserContacts(memberId,memberName,pageCount);
			ListContact contactfullList = this.getUserAllContacts(memberId,memberName,pageCount);
			List <Contacts> fullContacts = contactList.getResponse().getDocs();
				
			String numFound  =  contactList.getResponse().getNumFound().toString();
			if(fullContacts.size() > 0)
			{
				for (Contacts cons : fullContacts)
				{
					ListContact userContactsList  = getUserAllContacts(cons.getId(),"",0);
					String[] contactListName  = checkMutualNames(userContactsList,contactfullList);
					cons.setContactList(contactListName[0]);
					cons.setMutualCount(contactListName[1]);
					cons.setTotalRecords(numFound);
				}
				Collections.sort(fullContacts);
				List <ContactsOut> outContacts = CreateListing(fullContacts);
				if(search == null)
				{
					mav.addObject(outContacts);
				}
				else
				{
					ResultDoc resultDoc = new ResultDoc();
					resultDoc.setContacts(outContacts);
					resultDoc.setSize(outContacts.size());
					mav.addObject(resultDoc);
				}
				
				
				mav.addObject(outContacts);

				//mav.addObject(fullContacts);
			}
			else
			{
				mav.addObject(new ContactsOut());	
			}
				return mav;
		}
	}

	// USERS OWN CONTACTS
	private ListContact getUserAllContacts(String memberId,String memberName, int pageCount ){
		ListConnection listConnection = getUserConnections(memberId);
		List<Connections> myConnections = listConnection.getResponse().getDocs();
		String queryText = "";//"firstname:"+  memberName +" + id:(";
		for(Connections connectionComing : myConnections)
		{
			queryText = queryText + " (id:"+connectionComing.getContactid()+" ) OR";
			//queryText = queryText+ " "+connectionComing.getContactid()+" OR ";
		}
		if(queryText.length() > 4)
		queryText = queryText.substring(0,queryText.length()-3);
		//queryText = queryText+ " )";
		if(queryText.equals(""))
		return new ListContact();	
		return getContacts(queryText,pageCount);
	}
	
	
	// USERS OWN CONTACTS
	public ListContact getUserContacts(String memberId,String memberName, int pageCount ){
		ListConnection listConnection = getUserConnections(memberId);
		List<Connections> myConnections = listConnection.getResponse().getDocs();
		String queryText = "";//"firstname:"+  memberName +" + id:(";
		for(Connections connectionComing : myConnections)
		{
			queryText = queryText + " (id:"+connectionComing.getContactid()+" AND "+"firstname:"+  memberName+"*) OR";
			//queryText = queryText+ " "+connectionComing.getContactid()+" OR ";
		}
		if(queryText.length() > 4)
		queryText = queryText.substring(0,queryText.length()-3);
		//queryText = queryText+ " )";
		if(queryText.equals(""))
		return new ListContact();	
		return getContacts(queryText,pageCount);
	}
	
	public  List <Contacts> queryCheck(String query,int pageCount,int rows) {
		SolrQuery solrQuery = null;
		//QueryResponse response = null;
	    try {
	    	String url = "http://localhost:8983/solr";
	    	SolrServer server =  Adder.getSolrServer(serverurlConstants.ADD_CONTACTS_URL);
		    ModifiableSolrParams params = new ModifiableSolrParams();
		    params.set("q", query);
		    params.set("start", ""+pageCount);
		    params.set("version", "2.2");
		    params.set("wt", "json");
		    params.set("indent", "on");
		    params.set("rows", rows);

		    QueryResponse response = server.query(params, SolrRequest.METHOD.GET);
		    
		    List <Contacts> fullContacts = new ArrayList<Contacts>();
	            for (SolrDocument doc : response.getResults())
	            {
	            	Contacts con = new Contacts();
	            	con.setId(doc.getFieldValue("id").toString());
	            	con.setAddress(doc.getFieldValue("address").toString());
	            	con.setCity(doc.getFieldValue("city").toString());
	            	con.setDisplayname(doc.getFieldValue("displayname").toString());
	            	con.setEmail(doc.getFieldValue("email").toString());
	            	con.setImage(doc.getFieldValue("image").toString());
	            	con.setScreenname(doc.getFieldValue("screenname").toString());
	            	con.setState(doc.getFieldValue("state").toString());
	            	con.setZip(doc.getFieldValue("zip").toString());
	            	con.setTotalRecords(response.getResults().getNumFound()+"");
	            	//con.setContactList(doc.getFieldValue("connectionList").toString());
    				//con.setTotalRecords(doc.getFieldValue("totalRecords").toString());
	            	fullContacts.add(con);
	            }
	         return  fullContacts;  
	    } catch (SolrServerException e) {
	        throw new DataAccessResourceFailureException(e.getMessage(), e);
	    }
	    
	    
	    
	    
	    
	   // return response;
	} 	
	
	
	

	// ALL CONTACTS
	private ListContact getContacts(String memberName, int page){
		
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("memberName", memberName);
		vars.put("page", page+"");
		String result = restTemplate
		.getForObject(
				"http://localhost:8983/solr/select/?q={memberName}&version=2.2&start={page}&rows=10&indent=on&wt=json",
				String.class, vars);
		ListContact contactList = new Gson().fromJson(result, ListContact.class);
		return contactList;
	}
	
	private ListConnection getUserConnections(String memberId )
	{
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("memberId", memberId);
		String result = restTemplate
		.getForObject(
				"http://localhost:8984/solr/select/?q=memberid:{memberId}*&version=2.2&start=0&rows=60&indent=on&wt=json",
				String.class, vars);
		ListConnection connectionList = new Gson().fromJson(result, ListConnection.class);
		return connectionList;
		
	}
	
	
	@RequestMapping(value = "/addMember")
	public ModelAndView addMember(
			@RequestParam(value = "member_id", required = false) String memberId,
			@RequestParam(value = "first_name", required = false) String firstName,
			@RequestParam(value = "last_name", required = false) String lastName,
			@RequestParam(value = "middle_name", required = false) String middleName,
			@RequestParam(value = "screenname", required = false) String screenName,
			@RequestParam(value = "displayname", required = false) String displayName,
			@RequestParam(value = "address", required = false) String address,
			@RequestParam(value = "birthday", required = false) String birthday,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "zip", required = false) String zip,
			@RequestParam(value = "website", required = false) String website,
			@RequestParam(value = "image", required = false) String image,
			@RequestParam(value = "isemailnoteenable", required = false) String emailNoteEnable,
			@RequestParam(value = "isviewconnections", required = false) String isViewConnection
			){
		
		ModelAndView mav = new ModelAndView();
		SolrInputDocument contacts = new SolrInputDocument();

		//Contacts contacts = new Contacts();
		contacts.addField("id", memberId);
		contacts.addField("firstname",firstName);
		contacts.addField("lastname",lastName);
		contacts.addField("middlename",middleName);
		contacts.addField("address",address);
		contacts.addField("city",city);
		contacts.addField("state",state);
		contacts.addField("website",website);
		contacts.addField("zip",zip);
		contacts.addField("screenname",screenName);
		contacts.addField("displayname",displayName);
		contacts.addField("birthday",birthday);
		contacts.addField("email",email);
		contacts.addField("image",image);
		contacts.addField("isemailnoteenable",emailNoteEnable);
		contacts.addField("isviewconnections",isViewConnection);
		Adder.addContacts(serverurlConstants.ADD_CONTACTS_URL , contacts);
		String result = "success";
		mav.addObject("result",result);
		return mav;
	}
	
	@RequestMapping(value = "/addConnection")
	public ModelAndView addConnection(
			@RequestParam(value = "connectionid", required = false) String connectionId,
			@RequestParam(value = "active", required = false) String active,
			@RequestParam(value = "contactid", required = false) String contactId,
			@RequestParam(value = "hideupdates", required = false) String hideUpdates,
			@RequestParam(value = "invitation", required = false) String invitation,
			@RequestParam(value = "memberid", required = false) String memberId){
		
		ModelAndView mav = new ModelAndView();
		SolrInputDocument connection = new SolrInputDocument();
		connection.addField("connectionid", connectionId);
		connection.addField("active", active);
		connection.addField("contactid", contactId);
		connection.addField("hideupdates", hideUpdates);
		connection.addField("invitation", invitation);
		connection.addField("memberid", memberId);
		Adder.addConnection(serverurlConstants.ADD_CONNECTION_URL ,connection);
		String result = "success";
		mav.addObject("result",result);
		return mav;
	}
	
	public List<ContactsOut> CreateListing(List<Contacts> fullList)
	{
		List<ContactsOut> outContacts = new ArrayList<ContactsOut>();
		ContactsOut contacts = null;
		for (Contacts contactsComing : fullList)
		{
			contacts = new ContactsOut();
			if(contactsComing.getId() != null)
			contacts.setID(contactsComing.getId());
			else
			contacts.setID("");	
			contacts.setAddress(contactsComing.getAddress());
			contacts.setCity(contactsComing.getCity());
			contacts.setDisplayName(contactsComing.getDisplayname());
			contacts.setEmailAddress(contactsComing.getEmail());
			contacts.setProfileImageName(contactsComing.getImage());
			contacts.setScreenName(contactsComing.getScreenname());
			contacts.setState(contactsComing.getCity());
			contacts.setZipCode(contactsComing.getZip());
			contacts.setCountry(contactsComing.getState());
			contacts.setContactList(contactsComing.getContactList());
			if(contactsComing.getContactList() == null)
			{
				contacts.setContactList("");
			}
			else
			{
				contacts.setContactList(contactsComing.getContactList());	
			}
			
			if(contactsComing.getMutualCount() ==null)
			{
				contacts.setMutualFriendCount("0");
			}
			else
			{
				contacts.setMutualFriendCount(contactsComing.getMutualCount());	
			}
			contacts.setIsConnection("true");
			contacts.setIsEmailNoteEnalbe("true");
			contacts.setIsViewConnections("true");
			contacts.setTotalRecords(contactsComing.getTotalRecords());
			outContacts.add(contacts);
		}
		return outContacts;
		
	}
	
	private String[] checkMutualNames(ListContact userContactsList,ListContact myContactsList)
	{
		String[] anArray = new String[2];  
		String nameStr = "";
		if(userContactsList.getResponse() == null) return anArray;
		 int count = 0;
		 if(myContactsList.getResponse() != null)
		 {
			for (Contacts myCons : myContactsList.getResponse().getDocs())
			{
				for (Contacts cons  :  userContactsList.getResponse().getDocs())
				{
					if(cons.getId().equals(myCons.getId()))
					{
						if(nameStr.equals(""))
						nameStr = nameStr +cons.getDisplayname();
						else
						nameStr = nameStr + ","+cons.getDisplayname();
						
						count++;	
					}
				}
			}
			anArray[0] = nameStr;
			anArray[1] = count+"";
		 }
		return anArray;
	}

	@RequestMapping(value = "/deleteMember")
	public ModelAndView deleteMember(
			@RequestParam(value = "memberid", required = false) String memberId)
	{
		ModelAndView mav = new ModelAndView();
		SolrInputDocument connection = new SolrInputDocument();
		connection.addField("id", memberId);	
		Adder.deleteMember(serverurlConstants.DELETE_MEMBER_URL, connection);
		String result = "success";
		mav.addObject("result",result);
		return mav;
	}

	@RequestMapping(value = "/deleteConnection")
	public ModelAndView deleteConnection(
			@RequestParam(value = "connectionid", required = false) String connectionId)
	{
		ModelAndView mav = new ModelAndView();
		SolrInputDocument connection = new SolrInputDocument();
		connection.addField("connectionid", connectionId);	
		Adder.deleteConnection(serverurlConstants.DELETE_CONNECTION_URL , connection);
		String result = "success";
		mav.addObject("result",result);
		return mav;
	}
	

}
