package com.ytk.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ytk.models.AutoCompleteResponse;
import com.ytk.models.DiscussionQuestion;
import com.ytk.models.PlanGuest;
import com.ytk.models.Plans;
import com.ytk.utility.Adder;
import com.ytk.utility.ServerurlConstants;
import com.ytk.client.DateClient;
import com.ytk.client.MamCacheClient;
import com.ytk.client.SearchPlansClient;

@Controller
@RequestMapping("/searchplans/*")
public class SearchPlansController {
	
	@Autowired
	ServerurlConstants serverurlConstants;
	
	@Autowired
	SearchPlansClient searchPlansClient;
	
	@Autowired
	DateClient dateClient;
	
	@Autowired
	MamCacheClient mamCacheClient;
	
	/**
	 * Service to Search Updates with Relevance
	 * @param member_id
	 * @return a List of Updates in response
	 */

	@RequestMapping(value = "/findtitle")
	public  @ResponseBody AutoCompleteResponse findTitle(
			HttpServletRequest request,
			@RequestParam(value = "query", required = false) String Keyword,
			@RequestParam(value = "id", required = false) String AccountId){
			ModelAndView mav = new ModelAndView();
			
			String myPlansQueryText=null;
			String queryText =null;
			String friendListResult2 = null;
			String 	folderListResult2 =null;
			String 	blockListResult2 = null;
			String 	canSendMessagesResult2=null;
		
			
			String friendListResult = null;
			String 	folderListResult =null;
			String 	blockListResult = null;
			String 	canSendMessagesResult=null;
			
		    try{
		    friendListResult2=mamCacheClient.mamCachefriendList(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT, AccountId, request);
		    if (friendListResult2!=null)
		    friendListResult=friendListResult2.replace("," , " , ");
		    
		    folderListResult2=mamCacheClient.mamCachefolderList(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT, AccountId, request);
		    if (folderListResult2!=null)
		    folderListResult=folderListResult2.replace("," , " , ");
		    
		    blockListResult2=mamCacheClient.mamCacheblockList(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT, AccountId, request);
		    if (blockListResult2!=null)
		    blockListResult=blockListResult2.replace("," , " , ");
		    
		    canSendMessagesResult2=mamCacheClient.mamCachecanSendMessages(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT,AccountId, request);
		    if (canSendMessagesResult2!=null)
		    canSendMessagesResult=canSendMessagesResult2.replace("," , " , ");
		    
		    }catch (Exception e) {
		    	e.printStackTrace();
			}
			
			
			
			
			List<Plans> plans = new ArrayList<Plans>();
			List<Plans> guestPlans = new ArrayList<Plans>();
			List<Plans> myPlans = new ArrayList<Plans>();
			String hyphn = "-";
			String rep =  "&#45;";
			Keyword = Keyword.replaceAll(hyphn,rep);
			
			if(blockListResult!=null && blockListResult!=""){
				 myPlansQueryText = "((Title:"+Keyword.toLowerCase()+"*) AND (IsPublished:1) AND (AccountID:"+AccountId+") -AccountID:"+blockListResult+" )";
			}else{
				myPlansQueryText = "(Title:"+Keyword.toLowerCase()+"*) AND (IsPublished:1) AND (AccountID:"+AccountId+")";
			}
			
			
			
			
			myPlans =  this.fetchPlanData(myPlansQueryText,0,10, request);
			myPlans.addAll(guestPlans);
			guestPlans = fetchPlansOnGuests(AccountId, request);
			
			if(blockListResult!=null && blockListResult!=""){
				 queryText = "((Title:"+Keyword.toLowerCase()+"*) AND (IsPublished:1) AND (IsPublic:1) NOT (AccountID:"+AccountId+")-AccountID:"+blockListResult+")";
			}else{
				queryText = "(Title:"+Keyword.toLowerCase()+"*) AND (IsPublished:1) AND (IsPublic:1) NOT (AccountID:"+AccountId+")";
			}
			System.out.println("queryText----->"+queryText);
			plans = this.fetchPlanData(queryText,0,10, request);
			
			List id = new ArrayList();
			List title = new ArrayList();
			for (Plans myplan :  myPlans)
			{
				id.add(myplan.getID());
				title.add(myplan.getTitle());
			}
			for (Plans plan : plans)
			{
				id.add(plan.getID());
				title.add(plan.getTitle());
			}
			
			AutoCompleteResponse autoComplete = new AutoCompleteResponse();
			autoComplete.setQuery(Keyword);
			autoComplete.setId(id);
			autoComplete.setTitle(title);
			//mav.addObject(autoComplete);
			return autoComplete;
	}	
	
	
	@RequestMapping(value = "/findmain")
	public ModelAndView findMain(
			HttpServletRequest request,
			@RequestParam(value = "query", required = false) String Keyword,
			@RequestParam(value = "id", required = false) String AccountId,
			@RequestParam(value = "page", required = false) int page){
			ModelAndView mav = new ModelAndView();
			List<Plans> plans = new ArrayList<Plans>();
			List<Plans> guestPlans = new ArrayList<Plans>();
			List<Plans> myPlans = new ArrayList<Plans>();
			String hyphn = "-";
			String rep =  "&#45;";
			Keyword = Keyword.replaceAll(hyphn,rep);
			String myPlansQueryText = "((Title:"+Keyword.toLowerCase()+"*) OR (Location:"+Keyword.toLowerCase()+"*) ) AND  (IsPublished:1) AND (AccountID:"+AccountId+")";
			myPlans =  this.fetchPlanData(myPlansQueryText,0,2, request);
			//myPlans =  this.fetchPlanData(myPlansQueryText,0,2);
			myPlans.addAll(guestPlans);
			String queryText = "((Title:"+Keyword.toLowerCase()+"*) OR (Location:"+Keyword.toLowerCase()+"*) ) AND (IsPublished:1) AND (IsPublic:1) NOT (AccountID:"+AccountId+")";
			plans = this.fetchPlanData(queryText,page,2, request);
			for (Plans plan : plans)
			{
				myPlans.add(plan);
			}
			
			//Collections.sort(questions);
			mav.addObject("TotalRecords",myPlans.size());
			mav.addObject("StatusOutput","0");
			mav.addObject("Collection",myPlans);
			
			return mav;
	}	
	
	@RequestMapping(value = "/findneibhourhood")
	public ModelAndView findNeibhourHood(
			HttpServletRequest request,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "location", required = false) String location,
			@RequestParam(value = "id", required = false) String AccountId,
			@RequestParam(value = "page", required = false) int page){
			ModelAndView mav = new ModelAndView();
			List<Plans> plans = new ArrayList<Plans>();
			List<Plans> myPlans = new ArrayList<Plans>();
			String hyphn = "-";
			String rep =  "&#45;";
			title = title.replaceAll(hyphn,rep);
			String myPlansQueryText = "((Title:"+title.toLowerCase()+"*) AND ((Location:"+location.toLowerCase()+"*) OR (Address:"+location.toLowerCase()+"*) ) ) AND  (IsPublished:1) AND (AccountID:"+AccountId+")";
			myPlans =  this.fetchPlanData(myPlansQueryText,0,2, request);
			String queryText = "((Title:"+title.toLowerCase()+"*) AND (Location:"+location.toLowerCase()+"*) ) AND (IsPublished:1) AND (IsPublic:1) NOT (AccountID:"+AccountId+")";
			plans = this.fetchPlanData(queryText,page,2, request);
			for (Plans plan : plans)
			{
				myPlans.add(plan);
			}
			
			//Collections.sort(questions);
			mav.addObject("TotalRecords",numberFound);
			mav.addObject("StatusOutput","0");
			mav.addObject("Collection",myPlans);
			
			return mav;
	}	
	
	
	@RequestMapping(value = "/addplan")
	public ModelAndView addPlans(
			HttpServletRequest request,
			@RequestParam(value = "ID", required = false) String ID,
			@RequestParam(value = "DBID", required = false) String DBID,
			@RequestParam(value = "Title", required = false) String Title,
			@RequestParam(value = "TypeID", required = false) String TypeID,
			@RequestParam(value = "CategoryID", required = false) String CategoryID,
			@RequestParam(value = "CategoryName", required = false) String CategoryName,
			@RequestParam(value = "AccountName", required = false) String AccountName,
			@RequestParam(value = "AccountType", required = false) String AccountType,
			@RequestParam(value = "AccountID", required = false) String AccountID,
			@RequestParam(value = "Description", required = false) String Description,
			@RequestParam(value = "ThemeID", required = false) String ThemeID,
			@RequestParam(value = "ThemeName", required = false) String ThemeName,
			@RequestParam(value = "ThemeImage", required = false) String ThemeImage,
			@RequestParam(value = "StartDate", required = false) String StartDate,
			@RequestParam(value = "EndDate", required = false) String EndDate,
			@RequestParam(value = "Location", required = false) String Location,
			@RequestParam(value = "Address", required = false) String Address,
			@RequestParam(value = "City", required = false) String City,
			@RequestParam(value = "State", required = false) String State,
			@RequestParam(value = "ZIP", required = false) String ZIP,
			@RequestParam(value = "CountryID", required = false) String CountryID,
			@RequestParam(value = "CountryName", required = false) String CountryName,
			@RequestParam(value = "AccountPhone", required = false) String AccountPhone,
			@RequestParam(value = "AccountEmail", required = false) String AccountEmail,
			@RequestParam(value = "IsPublic", required = false) String IsPublic,
			@RequestParam(value = "CreatedDate", required = false) String CreatedDate,
			@RequestParam(value = "UpdatedDate", required = false) String UpdatedDate,
			@RequestParam(value = "ISDCode", required = false) String ISDCode,
			@RequestParam(value = "ISDName", required = false) String ISDName,
			@RequestParam(value = "TotalGuests", required = false) String TotalGuests,
			@RequestParam(value = "TotalComments", required = false) String TotalComments,
			@RequestParam(value = "Status", required = false) String Status,
			@RequestParam(value = "IsPublished", required = false) String IsPublished,
			@RequestParam(value = "IsGuestsResponseEmailRequired", required = false) String IsGuestsResponseEmailRequired,
			@RequestParam(value = "IsHideGuestList", required = false) String IsHideGuestList,
			@RequestParam(value = "IsGuestsInviteOther", required = false) String IsGuestsInviteOther,
			@RequestParam(value = "IsGuestsBringOther", required = false) String IsGuestsBringOther,
			@RequestParam(value = "LimitPerGuests", required = false) String LimitPerGuests,
			@RequestParam(value = "TotalAdults", required = false) String TotalAdults,
			@RequestParam(value = "TotalKids", required = false) String TotalKids,
			@RequestParam(value = "TotalAttending", required = false) String TotalAttending,
			
			
			@RequestParam(value = "AccountProfileJson", required = false) String AccountProfileJson,
			
			
			@RequestParam(value = "TotalMaybe", required = false) String TotalMaybe,
			@RequestParam(value = "TotalNo", required = false) String TotalNo,
			@RequestParam(value = "TotalNotResponding", required = false) String TotalNotResponding,
			@RequestParam(value = "AccountProfileImage", required = false) String AccountProfileImage,
			@RequestParam(value = "AccountScreenName", required = false) String AccountScreenName
			){
		
		ModelAndView mav = new ModelAndView();
		SolrInputDocument plans = new SolrInputDocument();

		plans.addField("ID", ID);
		plans.addField("DBID", DBID);
		plans.addField("Title", Title);
		plans.addField("TypeID", TypeID);
		plans.addField("CategoryID", CategoryID);
		plans.addField("CategoryName", CategoryName);
		plans.addField("AccountName", AccountName);
		plans.addField("AccountType", AccountType);
		plans.addField("AccountID", AccountID);
		plans.addField("Description", Description);
		plans.addField("ThemeID", ThemeID);
		plans.addField("ThemeName", ThemeName);
		plans.addField("ThemeImage", ThemeImage);
		plans.addField("StartDate", dateClient.addDateToSolr(StartDate, request));
		plans.addField("EndDate", dateClient.addDateToSolr(EndDate, request));
		plans.addField("Location", Location);
		plans.addField("Address", Address);
		plans.addField("City", City);
		plans.addField("State", State);
		plans.addField("ZIP", ZIP);
		plans.addField("CountryID", CountryID);
		plans.addField("CountryName", CountryName);
		plans.addField("AccountPhone", AccountPhone);
		plans.addField("AccountEmail", AccountEmail);
		plans.addField("IsPublic", IsPublic);
		plans.addField("CreatedDate", dateClient.addDateToSolr(CreatedDate, request));
		plans.addField("UpdatedDate", dateClient.addDateToSolr(UpdatedDate, request));
		plans.addField("ISDCode", ISDCode);
		plans.addField("ISDName", ISDName);
		plans.addField("TotalGuests", TotalGuests);
		plans.addField("TotalComments", TotalComments);
		plans.addField("Status", Status);
		plans.addField("IsPublished", IsPublished);
		plans.addField("IsGuestsResponseEmailRequired", IsGuestsResponseEmailRequired);
		plans.addField("IsHideGuestList", IsHideGuestList);
		plans.addField("IsGuestsInviteOther", IsGuestsInviteOther);
		plans.addField("IsGuestsBringOther", IsGuestsBringOther);
		plans.addField("LimitPerGuests", LimitPerGuests);
		plans.addField("TotalAdults", TotalAdults);
		plans.addField("TotalKids", TotalKids);
		
		
		plans.addField("AccountProfileJson", AccountProfileJson);
		
		
		
		
		plans.addField("TotalAttending", TotalAttending);
		plans.addField("TotalMaybe", TotalMaybe);
		plans.addField("TotalNo", TotalNo);
		plans.addField("TotalNotResponding", TotalNotResponding);
		plans.addField("AccountProfileImage", AccountProfileImage);
		plans.addField("AccountScreenName", AccountScreenName);
			
		Adder.addPlans(serverurlConstants.ADD_PLANS_URL , plans);
		String result = "success";
		mav.addObject("result",result);
		return mav;
	}

	
	/***
	 * 
	 * @param ID
	 * @return Status for deleted Plan
	 */
	@RequestMapping(value = "/deleteplan")
	public ModelAndView deletePlans(
			@RequestParam(value = "ID", required = false) String ID){
		ModelAndView mav = new ModelAndView();
		SolrInputDocument updates = new SolrInputDocument();
		updates.addField("ID", ID);	
		Adder.deletePlans(serverurlConstants.ADD_PLANS_URL , ID);
		String result = "success";
		mav.addObject("result",result);
		return mav;
			
	}
	
	
	@RequestMapping(value = "/addplanguest")
	public ModelAndView addPlansGuest(
			@RequestParam(value = "ID", required = false) String ID,
			@RequestParam(value = "DBID", required = false) String DBID,
			@RequestParam(value = "PlanID", required = false) String PlanID,
			@RequestParam(value = "Title", required = false) String Title,
			@RequestParam(value = "AccountID", required = false) String AccountID,
			@RequestParam(value = "AccountDisplayName", required = false) String AccountDisplayName,
			@RequestParam(value = "AccountType", required = false) String AccountType,
			@RequestParam(value = "Email", required = false) String Email,
			@RequestParam(value = "AccountScreenName", required = false) String AccountScreenName,
			@RequestParam(value = "AccountProfileImage", required = false) String AccountProfileImage,
			@RequestParam(value = "IsYtkMember", required = false) String IsYtkMember,
			@RequestParam(value = "Response", required = false) String Response,
			@RequestParam(value = "IsGuestsResponseEmailRequired", required = false) String IsGuestsResponseEmailRequired,
			@RequestParam(value = "IsGuestsCommentsEmailRequired", required = false) String IsGuestsCommentsEmailRequired,
			@RequestParam(value = "TotalAdults", required = false) String TotalAdults,
			@RequestParam(value = "TotalKids", required = false) String TotalKids,
			@RequestParam(value = "IsInviteEmailSent", required = false) String IsInviteEmailSent,
			@RequestParam(value = "IsReminderEmailSent", required = false) String IsReminderEmailSent,
			@RequestParam(value = "IsInvited", required = false) String IsInvited){
		
		ModelAndView mav = new ModelAndView();
		SolrInputDocument plans = new SolrInputDocument();

		plans.addField("ID", ID);
		plans.addField("DBID", DBID);
		plans.addField("PlanID", PlanID);
		plans.addField("Title", Title);
		plans.addField("AccountID", AccountID);
		plans.addField("AccountDisplayName", AccountDisplayName);
		plans.addField("AccountType", AccountType);
		plans.addField("Email", Email);
		plans.addField("AccountScreenName", AccountScreenName);
		plans.addField("AccountProfileImage", AccountProfileImage);
		plans.addField("IsYtkMember", IsYtkMember);
		plans.addField("Response", Response);
		plans.addField("IsGuestsResponseEmailRequired", IsGuestsResponseEmailRequired);
		plans.addField("IsGuestsCommentsEmailRequired", IsGuestsCommentsEmailRequired);
		plans.addField("TotalAdults", TotalAdults);
		plans.addField("TotalKids", TotalKids);
		plans.addField("IsInviteEmailSent", IsInviteEmailSent);
		plans.addField("IsReminderEmailSent", IsReminderEmailSent);
		plans.addField("IsInvited", IsInvited);
			
		Adder.addPlanGuest(serverurlConstants.ADD_PLAN_GUEST_URL , plans);
		String result = "success";
		mav.addObject("result",result);
		return mav;
	}


	
	
	/***
	 * 
	 * @param ID
	 * @return Status for deleted PlanGuest
	 */
	@RequestMapping(value = "/deleteplanguest")
	public ModelAndView deletePlanGuest(
			@RequestParam(value = "ID", required = false) String ID){
		ModelAndView mav = new ModelAndView();
		SolrInputDocument updates = new SolrInputDocument();
		updates.addField("ID", ID);	
		Adder.deletePlans(serverurlConstants.ADD_PLAN_GUEST_URL ,ID);
		String result = "success";
		mav.addObject("result",result);
		return mav;
			
	}
	
	
	/***
	 * 
	 * @param query
	 * @param pageCount
	 * @param rows
	 * @returns the list of plans 
	 */
	
	public Long numberFound ;
	private List <Plans> fetchPlanData(String query,int pageCount,int rows, HttpServletRequest request) {

		 String  StartDate=null;
		    String EndDate=null;
			 String CreatedDate=null;
			 String UpdatedDate=null;
			 
			 Date  startDate=null;
			 Date endDate=null;
			 Date createdDate=null;
			 Date updatedDate=null;
			   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
	    	String url = serverurlConstants.ADD_PLANS_URL;
	    	SolrServer server =  Adder.getSolrServer(url);
		    ModifiableSolrParams params = new ModifiableSolrParams();
		    params.set("q", query);
		    //params.set("sort", "AccountID desc");
		    params.set("start", ""+pageCount);
		    params.set("version", "2.2");
		    params.set("wt", "json");
		    params.set("indent", "on");
		    params.set("rows", rows);

		    QueryResponse response = server.query(params, SolrRequest.METHOD.POST);
		    numberFound = response.getResults().getNumFound();
		    List <Plans> plans = new ArrayList<Plans>();
	            for (SolrDocument updatesDoc : response.getResults())
	            {
	            	
	            	startDate=(Date)updatesDoc.getFieldValue("StartDate");
	            	endDate=(Date)updatesDoc.getFieldValue("EndDate");
	            	createdDate=(Date)updatesDoc.getFieldValue("CreatedDate");
	            	updatedDate=(Date)updatesDoc.getFieldValue("UpdatedDate");
			    	
			    	
			    	
	            	StartDate= dateFormat.format(startDate);
	            	EndDate= dateFormat.format(startDate);
	            	CreatedDate= dateFormat.format(createdDate);
	            	UpdatedDate= dateFormat.format(updatedDate);
			    	
			    	
			   
	            	
	            	
	            	
	            	Plans plan = new Plans();
	            	plan.setID(updatesDoc.getFieldValue("ID").toString());
	            	plan.setDBID(updatesDoc.getFieldValue("DBID").toString());
	            	plan.setTitle(updatesDoc.getFieldValue("Title").toString());
	            	plan.setTypeID(updatesDoc.getFieldValue("TypeID").toString());
	            	plan.setCategoryID(updatesDoc.getFieldValue("CategoryID").toString());
	            	plan.setCategoryName(updatesDoc.getFieldValue("CategoryName").toString());
	            	plan.setAccountName(updatesDoc.getFieldValue("AccountName").toString());	            	
	            	plan.setAccountType(updatesDoc.getFieldValue("AccountType").toString());
	            	plan.setAccountID(updatesDoc.getFieldValue("AccountID").toString());
	            	plan.setDescription(updatesDoc.getFieldValue("Description").toString());
	            	plan.setThemeID(updatesDoc.getFieldValue("ThemeID").toString());
	            	plan.setThemeName(updatesDoc.getFieldValue("ThemeName").toString());
	            	plan.setThemeImage(updatesDoc.getFieldValue("ThemeImage").toString());
	            	
	            	plan.setStartDate(StartDate);
	            	plan.setEndDate(EndDate);
	            	plan.setLocation(updatesDoc.getFieldValue("Location").toString());
	            	plan.setAddress(updatesDoc.getFieldValue("Address").toString());
	            	plan.setCity(updatesDoc.getFieldValue("City").toString());
	            	plan.setState(updatesDoc.getFieldValue("State").toString());
	            	plan.setZIP(updatesDoc.getFieldValue("ZIP").toString());
	            	plan.setCountryID(updatesDoc.getFieldValue("CountryID").toString());
	            	plan.setCountryName(updatesDoc.getFieldValue("CountryName").toString());
	            	plan.setAccountPhone(updatesDoc.getFieldValue("AccountPhone").toString());
	            	plan.setAccountEmail(updatesDoc.getFieldValue("AccountEmail").toString());
	            	plan.setIsPublic(updatesDoc.getFieldValue("IsPublic").toString());
	            	
	            	
	            	
	            	
	            	plan.setCreatedDate(CreatedDate);
	            	plan.setUpdatedDate(UpdatedDate);
	            
	            	
	            	plan.setISDCode(updatesDoc.getFieldValue("ISDCode").toString());
	            	plan.setISDName(updatesDoc.getFieldValue("ISDName").toString());
	            	plan.setTotalGuests(updatesDoc.getFieldValue("TotalGuests").toString());
	            	plan.setTotalComments(updatesDoc.getFieldValue("TotalComments").toString());
	            	plan.setStatus(updatesDoc.getFieldValue("Status").toString());
	            	plan.setIsPublished(updatesDoc.getFieldValue("IsPublished").toString());
	            	plan.setIsGuestsResponseEmailRequired(updatesDoc.getFieldValue("IsGuestsResponseEmailRequired").toString());
	            	plan.setIsHideGuestList(updatesDoc.getFieldValue("IsHideGuestList").toString());
	            	plan.setIsGuestsInviteOther(updatesDoc.getFieldValue("IsGuestsInviteOther").toString());
	            	plan.setIsGuestsBringOther(updatesDoc.getFieldValue("IsGuestsBringOther").toString());
	            	plan.setLimitPerGuests(updatesDoc.getFieldValue("LimitPerGuests").toString());
	            	plan.setTotalAdults(updatesDoc.getFieldValue("TotalAdults").toString());
	            	plan.setTotalKids(updatesDoc.getFieldValue("TotalKids").toString());
	            	plan.setTotalAttending(updatesDoc.getFieldValue("TotalAttending").toString());
	            	
	            	
	            	plan.setAccountProfileJson(updatesDoc.getFieldValue("AccountProfileJson").toString());
	            	
	            	
	            	
	            	plan.setTotalMaybe(updatesDoc.getFieldValue("TotalMaybe").toString());
	            	plan.setTotalNo(updatesDoc.getFieldValue("TotalNo").toString());
	            	plan.setTotalNotResponding(updatesDoc.getFieldValue("TotalNotResponding").toString());
	            	plan.setAccountProfileImage(updatesDoc.getFieldValue("AccountProfileImage").toString());
	            	plan.setAccountScreenName(updatesDoc.getFieldValue("AccountScreenName").toString());
	            	plans.add(plan);
	            }
	
	    return  plans;  
    } catch (SolrServerException e) {
        throw new DataAccessResourceFailureException(e.getMessage(), e);
    }
	}
	
	
	private List <Plans> fetchPlansOnGuests(String AccountId, HttpServletRequest request ) {
		 List <Plans> plans = new ArrayList<Plans>();
		 List <PlanGuest> planGuests = new ArrayList<PlanGuest>();
			String myPlansGuestQueryText = "(AccountID:"+AccountId+") AND (Response:1)";
			planGuests =  this.fetchPlanGuestData(myPlansGuestQueryText,0,100);
			
			for (PlanGuest planGuest : planGuests)
			{
				String myPlansQueryText = "(AccountID:"+AccountId+")";
				plans.add(this.fetchPlanData(myPlansQueryText,0,10, request).get(0));
			}
		 
		return plans;
	}
	
	/***
	 * 
	 * @param query
	 * @param pageCount
	 * @param rows
	 * @returns the list of plans 
	 */
	private List <PlanGuest> fetchPlanGuestData(String query,int pageCount,int rows) {
	    try {
	    	String url = serverurlConstants.ADD_PLAN_GUEST_URL;
	    	SolrServer server =  Adder.getSolrServer(url);
		    ModifiableSolrParams params = new ModifiableSolrParams();
		    params.set("q", query);
		   // params.set("sort", "AccountID desc");
		    params.set("start", ""+pageCount);
		    params.set("version", "2.2");
		    params.set("wt", "json");
		    params.set("indent", "on");
		    params.set("rows", rows);

		    QueryResponse response = server.query(params, SolrRequest.METHOD.POST);
		    
		    List <PlanGuest> planguests = new ArrayList<PlanGuest>();
	            for (SolrDocument updatesDoc : response.getResults())
	            {
	            	PlanGuest plan = new PlanGuest();
	            	plan.setID(updatesDoc.getFieldValue("ID").toString());
	            	planguests.add(plan);
	            }
	
	    return  planguests;  
    } catch (SolrServerException e) {
        throw new DataAccessResourceFailureException(e.getMessage(), e);
    }
	}
	
	
	
	
	@RequestMapping(value = "/fetchplanbycityid")
	public ModelAndView fetchPlanbyCityid(
			HttpServletRequest request,
			@RequestParam(value = "CityID", required = false) String CityID,
			@RequestParam(value = "member_id", required = false , defaultValue = "0") String memberId,
			@RequestParam(value = "Top", required = false) int Top,
			@RequestParam(value = "Status", required = false) String Status
			){
			ModelAndView mav = new ModelAndView();
			
			String myPlansQueryText=null;
			
			String friendListResult2 = null;
			String 	folderListResult2 =null;
			String 	blockListResult2 = null;
			String 	canSendMessagesResult2=null;
		
			
			String friendListResult = null;
			String 	folderListResult =null;
			String 	blockListResult = null;
			String 	canSendMessagesResult=null;
			
		    try{
		    friendListResult2=mamCacheClient.mamCachefriendList(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT, memberId,request );
		    if (friendListResult2!=null)
		    friendListResult=friendListResult2.replace("," , " , ");
		    
		    folderListResult2=mamCacheClient.mamCachefolderList(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT, memberId, request);
		    if (folderListResult2!=null)
		    folderListResult=folderListResult2.replace("," , " , ");
		    
		    blockListResult2=mamCacheClient.mamCacheblockList(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT, memberId ,request);
		    if (blockListResult2!=null)
		    blockListResult=blockListResult2.replace("," , " , ");
		    
		    canSendMessagesResult2=mamCacheClient.mamCachecanSendMessages(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT,memberId, request);
		    if (canSendMessagesResult2!=null)
		    canSendMessagesResult=canSendMessagesResult2.replace("," , " , ");
		    
		    }catch (Exception e) {
		    	e.printStackTrace();
			}
			
			
			List<Plans> plans = new ArrayList<Plans>();
			List<Plans> myPlans = new ArrayList<Plans>();
			if(blockListResult!=null && blockListResult!=""){
				 myPlansQueryText = "(((CityID:"+CityID+") AND (Status:"+Status+")) -ID:"+blockListResult+")";
			}else{
				 myPlansQueryText = "((CityID:"+CityID+") AND (Status:"+Status+") )";	
			}
			
			
			System.out.println("myPlansQueryText---->"+myPlansQueryText);
			myPlans =  searchPlansClient.fetchPlanDataByDateDesc(myPlansQueryText,0,Top, request);
			mav.addObject("TotalRecords",myPlans.size());
			mav.addObject("StatusOutput","true");
			mav.addObject("Collection",myPlans);
			
			return mav;
	}	
	
	

	
	
	
	
}
