package com.ytk.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ytk.client.DateClient;
import com.ytk.client.MamCacheClient;
import com.ytk.client.SearchDiscussionQuestionClient;
import com.ytk.models.AutoCompleteResponse;
import com.ytk.models.CollectionList;
import com.ytk.models.DiscussionQuestion;
import com.ytk.models.Place;
import com.ytk.models.Updates;
import com.ytk.utility.Adder;
import com.ytk.utility.ServerurlConstants;

@Controller
@RequestMapping("/searchquestion/*")
public class SearchDiscussionQuestionController {
	
	@Autowired
	ServerurlConstants serverurlConstants;
	
	@Autowired
	DateClient dateClient;

	@Autowired
	SearchDiscussionQuestionClient discussionQuestionClient;
	
	@Autowired
	MamCacheClient mamCacheClient;
	
	 /**
     * This method checks if a String contains only numbers
     */
    public boolean containsOnlyNumbers(String str) {
        
        //It can't contain only numbers if it's null or empty...
        if (str == null || str.length() == 0)
            return false;
        
        for (int i = 0; i < str.length(); i++) {

            //If we find a non-digit character we return false.
            if (Character.isDigit(str.charAt(i)))
                return true;
        }
        
        return false;
    }
	
	/**
	 * Service to Search Updates with Relevance
	 * @param member_id
	 * @return a List of Updates in response
	 */

	@RequestMapping(value = "/findtags")
	public  @ResponseBody AutoCompleteResponse findTags(
			@RequestParam(value = "query", required = false) String Keyword){
			ModelAndView mav = new ModelAndView();
			List<DiscussionQuestion> questions = null;
			Boolean checkNumber = containsOnlyNumbers(Keyword);
			Object[] resultArrDiscussion = null;
			long numFoundDiscussion  = 0;
			String queryText ="";
			if(checkNumber == true)	
				queryText = "(Tags:"+Keyword.toLowerCase()+")";
			else
				queryText = "(Tags:"+Keyword.toLowerCase()+"*)";
			resultArrDiscussion = this.fetchDiscussionData(queryText,0,10);
			numFoundDiscussion  = Integer.parseInt(resultArrDiscussion[1].toString());
			questions      = (List<DiscussionQuestion>) resultArrDiscussion[0];
			
			
			List data = new ArrayList();
			List suggestions = new ArrayList();
			for (DiscussionQuestion question :  questions)
			{
				 String delimiter = ",";
				String[] tags = question.getTags().split(delimiter);
				for(int i =0; i < tags.length ; i++)
				{
					Boolean checkBool = false;
					for (Object tg : data)
					{
						if(tg.toString().equals(tags[i].toString()))
							checkBool = true;
					}
					if(checkBool == false)
					{
						if((tags[i]).toLowerCase().contains(Keyword.toLowerCase()))
						{
							data.add(tags[i].toString());
							suggestions.add(tags[i].toString());
						}
					}
				}
			}
			AutoCompleteResponse autoComplete = new AutoCompleteResponse();
			autoComplete.setQuery(Keyword);
			autoComplete.setData(data);
			autoComplete.setSuggestions(suggestions);
			//mav.addObject(autoComplete);
			return autoComplete;
	}	
/**
 * 
 * @param Keyword
 * @return
 * Sorting relevancy
  	- Answers Count
	- Last Replied Date
	- Rating
	- Logged In User Participated
	- Logged In User Connection Discussions
	- Logged In User Connections of connections Discussions
 */
	
	@RequestMapping(value = "/findquestion")
	public  ModelAndView findQuestions(
			HttpServletRequest request,
			@RequestParam(value = "query", required = false) String Keyword){
			ModelAndView mav = new ModelAndView();
			List<DiscussionQuestion> questions = new ArrayList<DiscussionQuestion>();
			
			String queryText = "(QuestionText:"+Keyword.toLowerCase()+"*) OR (Tags:"+Keyword.toLowerCase()+"*)";
			Object[] resultArrDiscussion = null;
			long numFoundDiscussion  = 0;
			
		//	resultArrDiscussion = this.fetchDiscussionData(queryText,0,10);
	
			resultArrDiscussion = discussionQuestionClient.fetchDiscussionData(queryText,0,10 , request);
			
			
			numFoundDiscussion  = Integer.parseInt(resultArrDiscussion[1].toString());
			questions      = (List<DiscussionQuestion>) resultArrDiscussion[0];
			
			List<DiscussionQuestion> fullQuestions = new ArrayList<DiscussionQuestion>();
			for (DiscussionQuestion question :  questions)
			{
				if(question.getQuestionText().toLowerCase().contains(Keyword.toLowerCase()))
					fullQuestions.add(question);
			}
			//Collections.sort(questions);
			mav.addObject("TotalRecords",fullQuestions.size());
			mav.addObject("StatusOutput","0");
			mav.addObject("Collection",fullQuestions);
			return mav;
	}
	
	@RequestMapping(value = "/findmainautocomplete")
	public  @ResponseBody AutoCompleteResponse findQuestionsMainAutoComplete(
			HttpServletRequest request,
			@RequestParam(value = "ProfileId", required = false) String ProfileId,
			@RequestParam(value = "query", required = false) String Keyword){
			ModelAndView mav = new ModelAndView();
			
			String friendListResult2 = null;
			String 	folderListResult2 =null;
			String 	blockListResult2 = null;
			String 	canSendMessagesResult2=null;
		
			
			String friendListResult = null;
			String 	folderListResult =null;
			String 	blockListResult = null;
			String 	canSendMessagesResult=null;
			
		    try{
		    friendListResult2=mamCacheClient.mamCachefriendList(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT, ProfileId, request);
		    if (friendListResult2!=null)
		    friendListResult=friendListResult2.replace("," , " , ");
		    
		    folderListResult2=mamCacheClient.mamCachefolderList(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT, ProfileId, request);
		    if (folderListResult2!=null)
		    folderListResult=folderListResult2.replace("," , " , ");
		    
		    blockListResult2=mamCacheClient.mamCacheblockList(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT, ProfileId, request);
		    if (blockListResult2!=null)
		    blockListResult=blockListResult2.replace("," , " , ");
		    
		    canSendMessagesResult2=mamCacheClient.mamCachecanSendMessages(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT,ProfileId, request);
		    if (canSendMessagesResult2!=null)
		    canSendMessagesResult=canSendMessagesResult2.replace("," , " , ");
		    
		    }catch (Exception e) {
		    	e.printStackTrace();
			}
		    
			
		    
		    if(friendListResult.equals("") || friendListResult==null)
		    	friendListResult="1000000000";
			
			
			List<DiscussionQuestion> questions = new ArrayList<DiscussionQuestion>();
			Object[] resultArrDiscussion = null;
			String queryText=null;
			long numFoundDiscussion  = 0;
			
	//**********	Older query  ************/ 
			//	queryText = "(QuestionText:"+Keyword.toLowerCase()+"*) OR (Tags:"+Keyword.toLowerCase()+"*)";
			
			
			if((Keyword!="" || Keyword!=null ) && ProfileId!="0" && (blockListResult!=null && blockListResult!="" && blockListResult!="0") && (folderListResult!=null && folderListResult!="" ) ){
//				if((SubCategoryName!="" || SubCategoryName!=null )& ProfileId!="0" ){	
						
						queryText = "(((QuestionText:"+Keyword.toLowerCase()+"*) OR (Tags:"+Keyword.toLowerCase()+"*)) AND -CreatedUserID:"+ProfileId+" AND (Everyone:1 OR((CreatedUserID:"+friendListResult+") AND (ContactsShowList:0) AND (FoldersShowList:"+folderListResult+" OR FoldersShowList:0))) NOT ((Onlyme:1) OR (ContactsHideList:"+ProfileId+") OR (FoldersHideList:"+folderListResult+"))) OR ((QuestionText:"+Keyword.toLowerCase()+"*) OR (Tags:"+Keyword.toLowerCase()+"*) AND CreatedUserID:"+ProfileId+") OR    ((QuestionText:"+Keyword.toLowerCase()+"*) OR (Tags:"+Keyword.toLowerCase()+"*)  AND CreatedUserID:"+friendListResult+" AND ContactsShowList:"+ProfileId+") -CreatedUserID:"+blockListResult+" ";
						
					}else if((Keyword!="" || Keyword!=null) && ProfileId!="0"){
						queryText = "(((QuestionText:"+Keyword.toLowerCase()+"*) OR (Tags:"+Keyword.toLowerCase()+"*)) AND -CreatedUserID:"+ProfileId+" AND (Everyone:1 OR((CreatedUserID:"+friendListResult+") AND (ContactsShowList:0) AND (FoldersShowList:0))) NOT ((Onlyme:1) OR (ContactsHideList:"+ProfileId+"))) OR ((QuestionText:"+Keyword.toLowerCase()+"*) OR (Tags:"+Keyword.toLowerCase()+"*)  AND CreatedUserID:"+ProfileId+") OR    ((QuestionText:"+Keyword.toLowerCase()+"*) OR (Tags:"+Keyword.toLowerCase()+"*)  AND ContactsShowList:"+ProfileId+")";
					}else{
						
						queryText = "(((QuestionText:"+Keyword.toLowerCase()+"*) OR (Tags:"+Keyword.toLowerCase()+"*)) AND Everyone:1)";
						
					}
			if(ProfileId.equals("0")){
				queryText = "((QuestionText:"+Keyword.toLowerCase()+"*) OR (Tags:"+Keyword.toLowerCase()+"*) AND Everyone:1)";
				
				
			}
			
			System.out.println("Query-->"+queryText);
			
			resultArrDiscussion = this.fetchDiscussionData(queryText,0,10);
			numFoundDiscussion  = Integer.parseInt(resultArrDiscussion[1].toString());
			questions      = (List<DiscussionQuestion>) resultArrDiscussion[0];
			List data = new ArrayList();
			List suggestions = new ArrayList();
			for (DiscussionQuestion question :  questions)
			{
					data.add(question.getID());
					suggestions.add(question.getQuestionText());
			}
			AutoCompleteResponse autoComplete = new AutoCompleteResponse();
			autoComplete.setQuery(Keyword);
			autoComplete.setData(data);
			autoComplete.setSuggestions(suggestions);
			//mav.addObject(autoComplete);
			return autoComplete;
	}	
	
	@RequestMapping(value = "/findmainsearch")
	public  ModelAndView  findQuestionsMainSearch(
			@RequestParam(value = "query", required = false) String Keyword,
			@RequestParam(value = "ProfileId", required = false) String ProfileId,
			@RequestParam(value = "page", required = false) int page){
			ModelAndView mav = new ModelAndView();
			page = page*10;
			List<DiscussionQuestion> questions = new ArrayList<DiscussionQuestion>();
			Object[] resultArrDiscussion = null;
			long numFoundDiscussion  = 0;
			
			String queryText = "(QuestionText:"+Keyword.toLowerCase()+"*) OR (Tags:"+Keyword.toLowerCase()+"*)";
			resultArrDiscussion = this.fetchDiscussionData(queryText,page,10);
			numFoundDiscussion  = Integer.parseInt(resultArrDiscussion[1].toString());
			questions      = (List<DiscussionQuestion>) resultArrDiscussion[0];
			//Collections.sort(questions);
			mav.addObject("TotalRecords",numFoundDiscussion);
			mav.addObject("StatusOutput","0");
			mav.addObject("Collection",questions);
			return mav;	
			
	}	
	
	
	/***
	 * 
	 * @param query
	 * @param pageCount
	 * @param rows
	 * @returns the list of discussion question 
	 */
	public  Object[] fetchDiscussionData(String query,int pageCount,int rows) {
	    try {
	    
	    	SolrServer server =  Adder.getSolrServer(serverurlConstants.ADD_DISSCUSSION_QUESTION_URL);
		    ModifiableSolrParams params = new ModifiableSolrParams();
		    params.set("q", query);
		    params.set("sort", "AnswerCount desc,LastRepliedDate desc,Rating desc");
		    params.set("start", ""+pageCount);
		    params.set("version", "2.2");
		    params.set("wt", "json");
		    params.set("indent", "on");
		    params.set("rows", rows);

		    QueryResponse response = server.query(params, SolrRequest.METHOD.POST);
		    
		    List <DiscussionQuestion> questions = new ArrayList<DiscussionQuestion>();
	            for (SolrDocument updatesDoc : response.getResults())
	            {
	            	DiscussionQuestion discussionQuestion = new DiscussionQuestion();
	            	discussionQuestion.setID(updatesDoc.getFieldValue("ID").toString());
	            	discussionQuestion.setCreatedUserID(updatesDoc.getFieldValue("CreatedUserID").toString());
	            	discussionQuestion.setQuestionText(updatesDoc.getFieldValue("QuestionText").toString());
	            	discussionQuestion.setCreatedDate(updatesDoc.getFieldValue("CreatedDate").toString());
	            	discussionQuestion.setTags(updatesDoc.getFieldValue("Tags").toString());
	            	discussionQuestion.setRating(updatesDoc.getFieldValue("Rating").toString());
	            	discussionQuestion.setCreatedUserDisplayName(updatesDoc.getFieldValue("CreatedUserDisplayName").toString());
	            	discussionQuestion.setCreatedUserScreenName(updatesDoc.getFieldValue("CreatedUserScreenName").toString());
	            	discussionQuestion.setCategoryName(updatesDoc.getFieldValue("CategoryName").toString());
	            	discussionQuestion.setAnswerCount(updatesDoc.getFieldValue("AnswerCount").toString());
	            	discussionQuestion.setCreatedUserType(updatesDoc.getFieldValue("CreatedUserType").toString());
	            	//Date dateString = (Date) updatesDoc.getFieldValue("LastRepliedDate");
	            	//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            	discussionQuestion.setLastRepliedDate(updatesDoc.getFieldValue("LastRepliedDate").toString());
	            	questions.add(discussionQuestion);
	            }
	            Object[] resultArr = new Object[2];
	            String   numFound = response.getResults().getNumFound()+"";
	            resultArr[0] = questions;
	            resultArr[1] = numFound;
	
	    return  resultArr;  
    } catch (SolrServerException e) {
        throw new DataAccessResourceFailureException(e.getMessage(), e);
    }
	}
	

	@RequestMapping(value = "/addquestion")
	public ModelAndView addQuestion(
			HttpServletRequest request,
			@RequestParam(value = "ID", required = false) String ID,
			@RequestParam(value = "QuestionText", required = false) String QuestionText,
			@RequestParam(value = "CreatedDate", required = false) String CreatedDate,			
			@RequestParam(value = "CreatedUserID", required = false) String CreatedUserID,
			@RequestParam(value = "CreatedUserDisplayName", required = false) String CreatedUserDisplayName,
			@RequestParam(value = "CreatedUserScreenName", required = false) String CreatedUserScreenName,
			@RequestParam(value = "CreatedUserEmailAddress", required = false) String CreatedUserEmailAddress,
			@RequestParam(value = "CreatedUserImage", required = false) String CreatedUserImage,
			@RequestParam(value = "CategoryId", required = false) String CategoryId,	
			@RequestParam(value = "CategoryName", required = false) String CategoryName,
			@RequestParam(value = "SubCategoryID", required = false) String SubCategoryID,
			@RequestParam(value = "SubCategoryName", required = false) String SubCategoryName,
			@RequestParam(value = "SubCategorySafeName", required = false) String SubCategorySafeName,
			@RequestParam(value = "AnswerCount", required = false) String AnswerCount,
			@RequestParam(value = "Rating", required = false) String Rating,			
			@RequestParam(value = "Tags", required = false) String Tags,
			@RequestParam(value = "LastRepliedUserId", required = false) String LastRepliedUserId,			
			@RequestParam(value = "LastRepliedUserDisplayName", required = false , defaultValue = "") String LastRepliedUserDisplayName,			
			@RequestParam(value = "LastRepliedUserScreenName", required = false) String LastRepliedUserScreenName,
			@RequestParam(value = "LastRepliedUserEmailAddress", required = false) String LastRepliedUserEmailAddress,			
			@RequestParam(value = "LastRepliedUserImage", required = false) String LastRepliedUserImage,
			@RequestParam(value = "LastRepliedDate", required = false) String LastRepliedDate,
			@RequestParam(value = "InitialAnswer", required = false) String InitialAnswer,			
			@RequestParam(value = "CreatedUserType", required = false) String CreatedUserType,	
			@RequestParam(value = "LastRepliedUserType", required = false) String LastRepliedUserType,				
			@RequestParam(value = "IsFeatured", required = false) String IsFeatured,
			
			
			@RequestParam(value = "CreatedUserImageJson", required = false) String CreatedUserImageJson,
			@RequestParam(value = "LastRepliedUserImageJson", required = false) String LastRepliedUserImageJson,
			
			
			
			@RequestParam(value = "Everyone", required = false , defaultValue = "0") int Everyone,
			@RequestParam(value = "Onlyme", required = false) int Onlyme,
			@RequestParam(value = "IsCustom", required = false) int IsCustom,
			@RequestParam(value = "FoldersShowList", required = false) String FoldersShowList,
			@RequestParam(value = "FoldersHideList", required = false) String FoldersHideList,
			@RequestParam(value = "ContactsShowList", required = false) String ContactsShowList,
			@RequestParam(value = "ContactsHideList", required = false) String ContactsHideList,
			
			
			@RequestParam(value = "OldID", required = false) String OldID)
			{
		
		ModelAndView mav = new ModelAndView();
		SolrInputDocument question = new SolrInputDocument();
		question.addField("ID", ID);
		question.addField("QuestionText", QuestionText);
		question.addField("CreatedDate", dateClient.addDateToSolr(CreatedDate , request));
		question.addField("CreatedUserID", CreatedUserID);
		question.addField("CreatedUserDisplayName", CreatedUserDisplayName);
		question.addField("CreatedUserScreenName", CreatedUserScreenName);
		question.addField("CreatedUserEmailAddress", CreatedUserEmailAddress);
		question.addField("CreatedUserImage", CreatedUserImage);
		question.addField("CategoryId", CategoryId);	
		question.addField("CategoryName", CategoryName);
		question.addField("SubCategoryID", SubCategoryID);
		question.addField("SubCategoryName", SubCategoryName);
		question.addField("SubCategorySafeName", SubCategorySafeName);
		question.addField("AnswerCount", AnswerCount);		
		question.addField("Rating", Rating);
		question.addField("Tags", Tags);	
		question.addField("LastRepliedUserId", LastRepliedUserId);
		question.addField("LastRepliedUserDisplayName", LastRepliedUserDisplayName);
		question.addField("LastRepliedUserScreenName", LastRepliedUserScreenName);
		question.addField("LastRepliedUserEmailAddress", LastRepliedUserEmailAddress);
		question.addField("LastRepliedUserImage", LastRepliedUserImage);		
		question.addField("InitialAnswer", InitialAnswer);
		question.addField("CreatedUserType", CreatedUserType);
		question.addField("LastRepliedUserType", LastRepliedUserType);		
		question.addField("IsFeatured", IsFeatured);
		question.addField("OldID", OldID);
    	question.addField("LastRepliedDate", dateClient.addDateToSolr(LastRepliedDate , request));
		
    	
    	question.addField("CreatedUserImageJson", CreatedUserImageJson);
    	question.addField("LastRepliedUserImageJson", LastRepliedUserImageJson);
    	
    	
    	question.addField("Everyone", Everyone);
    	question.addField("Onlyme", Onlyme);
    	question.addField("FoldersShowList", FoldersShowList);
    	question.addField("FoldersHideList", FoldersHideList);
    	question.addField("ContactsShowList", ContactsShowList);
    	question.addField("ContactsHideList", ContactsHideList);
    	question.addField("IsCustom", IsCustom);
    	question.addField("QuestionStaus", QuestionText.replaceAll("\\s",""));
    	
    	
		Adder.addDiscussionQuestions(serverurlConstants.ADD_DISSCUSSION_QUESTION_URL ,question);
		String result = "success";
		mav.addObject("result",result);
		return mav;
	}
	
	
	@RequestMapping(value = "/getAllQuestionsPaged")
	public ModelAndView GetAllQuestionsPaged(			
			HttpServletRequest request,
			@RequestParam(value = "SubCategoryName", required = false) String SubCategoryName,		
			@RequestParam(value = "PageNo", required = false) int PageNo,
			@RequestParam(value = "ProfileId", required = false) String ProfileId,
			@RequestParam(value = "PageSize", required = false) int PageSize){
		
		ModelAndView mav = new ModelAndView();
		String Status="3";
		String queryText = "";
		String numFound  = "0";
	
		String friendListResult2 = null;
		String 	folderListResult2 =null;
		String 	blockListResult2 = null;
		String 	canSendMessagesResult2=null;
	
		
		String friendListResult = null;
		String 	folderListResult =null;
		String 	blockListResult = null;
		String 	canSendMessagesResult=null;
		
	    try{
	    friendListResult2=mamCacheClient.mamCachefriendList(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT, ProfileId, request);
	    if (friendListResult2!=null)
	    friendListResult=friendListResult2.replace("," , " , ");
	    
	    folderListResult2=mamCacheClient.mamCachefolderList(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT, ProfileId, request);
	    if (folderListResult2!=null)
	    folderListResult=folderListResult2.replace("," , " , ");
	    
	    blockListResult2=mamCacheClient.mamCacheblockList(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT, ProfileId, request);
	    if (blockListResult2!=null)
	    blockListResult=blockListResult2.replace("," , " , ");
	    
	    canSendMessagesResult2=mamCacheClient.mamCachecanSendMessages(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT,ProfileId, request);
	    if (canSendMessagesResult2!=null)
	    canSendMessagesResult=canSendMessagesResult2.replace("," , " , ");
	    
	    }catch (Exception e) {
	    	e.printStackTrace();
		}
	    
		List<DiscussionQuestion> questions = new ArrayList<DiscussionQuestion>();
		Object[] resultArrDiscussion = null;
		long numFoundDiscussion  = 0;
	/*	if((SubCategoryName!="" || SubCategoryName!=null )& ProfileId!="0" & (blockListResult!=null || blockListResult!="") ){
		//	queryText = "(SubCategorySafeName:"+SubCategoryName+")";
			
			queryText = "(SubCategorySafeName:"+SubCategoryName+" OR CreatedUserID:"+ProfileId+" AND(Everyone:1 OR (CreatedUserID:"+friendListResult+" AND FoldersShowList:"+folderListResult+" AND ContactsShowList:"+ProfileId+"))" +
			" NOT (Onlyme:1 OR ContactsHideList:"+ProfileId+"))";
			
		}else if((SubCategoryName!="" || SubCategoryName!=null) & ProfileId!="0"){
			queryText = "(SubCategorySafeName:"+SubCategoryName+" OR CreatedUserID:"+ProfileId+"  AND(Everyone:1 OR (CreatedBy:"+friendListResult+" AND FoldersShowList:"+folderListResult+" AND ContactsShowList:"+ProfileId+"))" +
			" NOT (Onlyme:1 OR FoldersHideList:"+blockListResult+" OR ContactsHideList:"+ProfileId+" OR CreatedBy:"+blockListResult+" ))";
		}else{
			
			queryText = "(SubCategorySafeName:"+SubCategoryName+" AND Everyone:1)";
			
		}*/
		
	
		
/*	if((SubCategoryName!="" || SubCategoryName!=null ) && ProfileId!="0" && (blockListResult!=null || blockListResult!="" || blockListResult!="0") ){
//		if((SubCategoryName!="" || SubCategoryName!=null )& ProfileId!="0" ){	
				
				queryText = "(((SubCategorySafeName:"+SubCategoryName+" AND ( Everyone:1 OR (CreatedUserID:"+friendListResult+" AND FoldersShowList:"+folderListResult+" AND ContactsShowList:"+ProfileId+")) NOT (Onlyme:1 OR ContactsHideList:"+ProfileId+")) OR (CreatedUserID:"+ProfileId+" OR SubCategorySafeName:"+SubCategoryName+")) NOT (ContactsHideList:"+ProfileId+" OR Onlyme:1 ))";
				
			}else if((SubCategoryName!="" || SubCategoryName!=null) && ProfileId!="0"){
				queryText = "(((SubCategorySafeName:"+SubCategoryName+" AND ( Everyone:1 OR (CreatedUserID:"+friendListResult+" AND FoldersShowList:"+folderListResult+" AND ContactsShowList:"+ProfileId+")) NOT (Onlyme:1 OR ContactsHideList:"+ProfileId+")) OR (CreatedUserID:"+ProfileId+" OR SubCategorySafeName:"+SubCategoryName+")) NOT (ContactsHideList:"+ProfileId+" OR Onlyme:1 ))";
			}else{
				
				queryText = "(SubCategorySafeName:"+SubCategoryName+" AND Everyone:1)";
				
			}*/
		
		if(blockListResult.equals("") || blockListResult==null)
			blockListResult="1";
		
		if(folderListResult.equals("") || folderListResult==null)
			folderListResult="0";
		
		if(friendListResult.equals("") || friendListResult==null)
			friendListResult="0";
		
		
		
		if((SubCategoryName!="" && SubCategoryName!=null ) && ProfileId!="0" && (blockListResult!=null && blockListResult!="" && blockListResult!="0") && (folderListResult!=null && folderListResult!="" ) ){
//			if((SubCategoryName!="" || SubCategoryName!=null )& ProfileId!="0" ){	
					
					queryText = "(SubCategorySafeName:"+SubCategoryName+" AND -CreatedUserID:"+ProfileId+" AND (Everyone:1 OR((CreatedUserID:"+friendListResult+") AND (ContactsShowList:0) AND (FoldersShowList:"+folderListResult+" OR FoldersShowList:0))) NOT ((Onlyme:1) OR (ContactsHideList:"+ProfileId+") OR (FoldersHideList:"+folderListResult+"))) OR (SubCategorySafeName:"+SubCategoryName+"  AND CreatedUserID:"+ProfileId+") OR    (SubCategorySafeName:"+SubCategoryName+"  AND CreatedUserID:"+friendListResult+" AND ContactsShowList:"+ProfileId+") -CreatedUserID:"+blockListResult+" ";
					
				}else if((SubCategoryName!="" && SubCategoryName!=null) && ProfileId!="0"){
					queryText = "(SubCategorySafeName:"+SubCategoryName+" AND -CreatedUserID:"+ProfileId+" AND (Everyone:1 OR((ContactsShowList:0) AND (FoldersShowList:0))) NOT ((Onlyme:1) OR (ContactsHideList:"+ProfileId+"))) OR (SubCategorySafeName:"+SubCategoryName+"  AND CreatedUserID:"+ProfileId+") OR    (SubCategorySafeName:"+SubCategoryName+"  AND ContactsShowList:"+ProfileId+") ";
				}else{
					
					queryText = "(SubCategorySafeName:"+SubCategoryName+" AND Everyone:1)";
					
				}
		
		
		
		
	if(ProfileId.equals("0")){
		queryText = "(SubCategorySafeName:"+SubCategoryName+" AND Everyone:1)";
	}
		
		
		System.out.println("------------->Query"+queryText);
		
	//	Earlier
	//	resultArrDiscussion = discussionQuestionClient.fetchAllQuestionsPaged(queryText,PageNo,PageSize , "LastRepliedDate","desc" , request);
		
		// Changed
		
		resultArrDiscussion = 	discussionQuestionClient.fetchAllQuestionsPaged(queryText, PageNo, PageSize, "LastRepliedDate", "desc", request);
		numFoundDiscussion  = Integer.parseInt(resultArrDiscussion[1].toString());
		questions      = (List<DiscussionQuestion>) resultArrDiscussion[0];
		//Collections.sort(questions);
		mav.addObject("TotalRecords",numFoundDiscussion);
		mav.addObject("StatusOutput",SubCategoryName);
		mav.addObject("Collection",questions);
		mav.addObject("IsConnected","1");
		
		
		
		return mav;
	}
	
	
	@RequestMapping(value = "/getAllQuestionsByUserIdPaged" , method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView GetAllQuestionsByUserIdPaged(			
			HttpServletRequest request,
			@RequestParam(value = "CreatedUserID", required = false) String CreatedUserID,		
			@RequestParam(value = "PageNo", required = false) int PageNo,
			@RequestParam(value = "PageSize", required = false) int PageSize){
		
		ModelAndView mav = new ModelAndView();
		String Status="3";
		String queryText = "";
		String numFound  = "0";
		List<DiscussionQuestion> questions = new ArrayList<DiscussionQuestion>();
		Object[] resultArrDiscussion = null;
		long numFoundDiscussion  = 0;
		if(CreatedUserID=="" || CreatedUserID==null ){
			queryText = "(CreatedUserID:"+CreatedUserID+")";
		}else{
			queryText = "(CreatedUserID:"+CreatedUserID+")";	
		}
		try{
		resultArrDiscussion = discussionQuestionClient.fetchAllQuestionsPaged(queryText,PageNo,PageSize,"CreatedDate","desc" , request);
		numFoundDiscussion  = Integer.parseInt(resultArrDiscussion[1].toString());
		questions      = (List<DiscussionQuestion>) resultArrDiscussion[0];
		//Collections.sort(questions);
		mav.addObject("TotalRecords",numFoundDiscussion);
		mav.addObject("StatusOutput","");
		mav.addObject("Collection",questions);
		mav.addObject("IsConnected","1");
		}catch (Exception e) {
			e.printStackTrace();
			mav.addObject("TotalRecords",0);
			mav.addObject("StatusOutput","");
			mav.addObject("Collection",new ArrayList<DiscussionQuestion>());
			mav.addObject("IsConnected","1");
			
		}
		
		
		return mav;
	}
	
	@RequestMapping(value = "/getprivacysetting")
	public ModelAndView GetAllPrivacySetting(		
			HttpServletRequest request,
			@RequestParam(value = "ProfileId", required = false) String ProfileId,		
			@RequestParam(value = "PageNo", required = false) int PageNo,
			@RequestParam(value = "PageSize", required = false) int PageSize){
		
		ModelAndView mav = new ModelAndView();
		
		
		String queryText = "";
		String numFound  = "0";
		String myFolders ="123,125,0";
		String myFriendlist = "123,125,0";
		String myBlocklist = "123,125";

		List<DiscussionQuestion> questions = new ArrayList<DiscussionQuestion>();
		Object[] resultArrDiscussion = null;
		long numFoundDiscussion  = 0;
		if(ProfileId=="" || ProfileId==null ){
			queryText = "(((Everyone:1)OR(((CreatedBy:"+ProfileId+") AND (FoldersPermitted:123* OR FoldersPermitted:125 OR FoldersPermitted:0))))NOT ((Onlyme:1 OR FoldersBanned:123* OR FoldersBanned:125 OR IndividualBanned:127)))";
		}else{
			//queryText = "(((Everyone:1)OR(((CreatedBy:"+CategoryId+") AND (FoldersPermitted:"+myFolders+"))))NOT ((Onlyme:1 OR FoldersBanned:123* OR FoldersBanned:125 OR IndividualBanned:127)))";	
			queryText = "((Everyone:1 OR (CreatedBy:"+myFriendlist+" AND FoldersPermitted:"+myFolders+" AND IndvidualPermitted:"+ProfileId+"))" +
					"NOT (Onlyme:1 OR FoldersBanned:"+myFolders+" OR IndividualBanned:"+ProfileId+" OR CreatedBy:"+myBlocklist+" ))";
		}
		
		resultArrDiscussion = discussionQuestionClient.fetchPrivacySetting(queryText,PageNo,PageSize, request);
		numFoundDiscussion  = Integer.parseInt(resultArrDiscussion[1].toString());
		questions      = (List<DiscussionQuestion>) resultArrDiscussion[0];
		//Collections.sort(questions);
		mav.addObject("TotalRecords",numFoundDiscussion);
		mav.addObject("StatusOutput","0");
		mav.addObject("Collection",questions);
		mav.addObject("IsConnected","true");
		return mav;
	}
	
	
	
	@RequestMapping(value = "/getbyoldid")
	public ModelAndView getByOldId(			
			HttpServletRequest request,
			@RequestParam(value = "OldID", required = false) String OldID){
		
		ModelAndView mav = new ModelAndView();
		String Status="3";
		String queryText = "";
		String numFound  = "0";
		List<DiscussionQuestion> questions = new ArrayList<DiscussionQuestion>();
		Object[] resultArrDiscussion = null;
		long numFoundDiscussion  = 0;
		queryText = "(OldID:"+OldID+")";	
		resultArrDiscussion = discussionQuestionClient.fetchAllQuestionsPaged(queryText,0,1,"CreatedDate","desc" , request);
		numFoundDiscussion  = Integer.parseInt(resultArrDiscussion[1].toString());
		questions=(List<DiscussionQuestion>) resultArrDiscussion[0];
		try{
			
	
			
		mav.addObject("Tags",questions.get(0).getTags());
		mav.addObject("QuestionText",questions.get(0).getQuestionText());
		mav.addObject("CategoryName",questions.get(0).getCategoryName());
		mav.addObject("CreatedUserID",questions.get(0).getCreatedUserID());
		mav.addObject("CreatedDate",questions.get(0).getCreatedDate());
		mav.addObject("Rating",Integer.parseInt(questions.get(0).getRating().toString()));
		mav.addObject("CreatedUserDisplayName",questions.get(0).getCreatedUserDisplayName());
		mav.addObject("CreatedUserScreenName",questions.get(0).getCreatedUserScreenName());
		mav.addObject("AnswerCount",Integer.parseInt(questions.get(0).getAnswerCount().toString()));
		mav.addObject("LastRepliedDate",questions.get(0).getLastRepliedDate());
		mav.addObject("CreatedUserEmailAddress",questions.get(0).getCreatedUserEmailAddress());
		mav.addObject("CreatedUserImage",questions.get(0).getCreatedUserImage());
		mav.addObject("CategoryId",Integer.parseInt(questions.get(0).getCategoryId().toString()));
		mav.addObject("SubCategoryID",Integer.parseInt(questions.get(0).getSubCategoryID().toString()));
		mav.addObject("SubCategoryName",questions.get(0).getSubCategoryName());
		mav.addObject("SubCategorySafeName",questions.get(0).getSubCategorySafeName());
		mav.addObject("LastRepliedUserId",questions.get(0).getLastRepliedUserId());
		mav.addObject("LastRepliedUserDisplayName",questions.get(0).getLastRepliedUserDisplayName());
		mav.addObject("LastRepliedUserScreenName",questions.get(0).getLastRepliedUserScreenName());
		mav.addObject("LastRepliedUserEmailAddress",questions.get(0).getLastRepliedUserEmailAddress());
		mav.addObject("LastRepliedUserImage",questions.get(0).getLastRepliedUserImage());		
		mav.addObject("InitialAnswer",questions.get(0).getInitialAnswer());
		
		mav.addObject("CreatedUserImageJson",questions.get(0).getCreatedUserImageJson());
		mav.addObject("LastRepliedUserImageJson",questions.get(0).getLastRepliedUserImageJson());
		
		
		mav.addObject("LastRepliedUserType",Integer.parseInt(questions.get(0).getLastRepliedUserType().toString()));
		mav.addObject("IsFeatured",questions.get(0).getIsFeatured());
		mav.addObject("CreatedUserType",Integer.parseInt(questions.get(0).getCreatedUserType().toString()));
		mav.addObject("IsCustom",questions.get(0).getIsCustom());
		mav.addObject("ID",questions.get(0).getID());
		}catch (Exception e) {
			e.printStackTrace();
			return mav;
		}
		return mav;
	}
	
	@RequestMapping(value = "/getQuestionStatus")
	public ModelAndView getQuestionStatus(		
			HttpServletRequest request,
			@RequestParam(value = "QuestionText", required = true , defaultValue = "abcd") String QuestionText){
	
		ModelAndView mav = new ModelAndView();
		Object[] resultArrDiscussion = null;
		String Query=null;
		long numFoundDiscussion  = 0;
		List<DiscussionQuestion> questions = new ArrayList<DiscussionQuestion>();
		QuestionText=QuestionText.replaceAll("\\s","");
		
		QuestionText=Adder.escapeQueryChars(QuestionText);
		
		Query="(QuestionStaus:"+QuestionText+")";
		
		resultArrDiscussion = discussionQuestionClient.fetchQuestionStatus(Query,serverurlConstants.ADD_DISSCUSSION_QUESTION_URL ,request);
		
		numFoundDiscussion  = Integer.parseInt(resultArrDiscussion[1].toString());
		questions=(List<DiscussionQuestion>) resultArrDiscussion[0];
		
		if(questions.size()>0)
			mav.addObject("Status", "Active");
		else
			mav.addObject("Status", "NotActive");
		
		return mav;
	}

	
	
}
