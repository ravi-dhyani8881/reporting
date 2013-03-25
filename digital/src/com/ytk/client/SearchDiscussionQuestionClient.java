package com.ytk.client;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Component;

import com.ytk.models.DiscussionQuestion;
import com.ytk.models.HeaderQuestion;
import com.ytk.utility.Adder;
import com.ytk.utility.LogDetails;
import com.ytk.utility.ServerurlConstants;
import com.ytk.client.DateClient;

@Component("discussionQuestionClient")
public class SearchDiscussionQuestionClient {

	private static final Logger logger = LoggerFactory.getLogger(SearchDiscussionQuestionClient.class);
	
	@Autowired
	ServerurlConstants serverurlConstants;
	
	@Autowired
	LogDetails logDetails;
	
	@Autowired
	DateClient dateClient;
	
	
	public  Object[] fetchDiscussionData(String query,int pageCount,int rows , HttpServletRequest request) {
	    try {
	    	
	    	String url = serverurlConstants.ADD_DISSCUSSION_QUESTION_URL;	    	
	    	SolrServer server =  Adder.getSolrServer(url);
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
	            	if(updatesDoc.getFieldValue("QuestionText")!=null)
	            	discussionQuestion.setQuestionText(updatesDoc.getFieldValue("QuestionText").toString());
	            	
	            	if(updatesDoc.getFieldValue("CreatedDate")!=null)
	            	discussionQuestion.setCreatedDate(updatesDoc.getFieldValue("CreatedDate").toString());	            	
	            	
	            	if(updatesDoc.getFieldValue("CreatedUserID")!=null)
	            	discussionQuestion.setCreatedUserID(updatesDoc.getFieldValue("CreatedUserID").toString());
	            	
	            	if(updatesDoc.getFieldValue("CreatedUserDisplayName")!=null)
	            	discussionQuestion.setCreatedUserDisplayName(updatesDoc.getFieldValue("CreatedUserDisplayName").toString());
	            	
	            	if(updatesDoc.getFieldValue("CreatedUserScreenName")!=null)
	            	discussionQuestion.setCreatedUserScreenName(updatesDoc.getFieldValue("CreatedUserScreenName").toString());
	               	
	            	if(updatesDoc.getFieldValue("CreatedUserEmailAddress")!=null)
	            	discussionQuestion.setCreatedUserEmailAddress(updatesDoc.getFieldValue("CreatedUserEmailAddress").toString());
	             	
	            	if(updatesDoc.getFieldValue("CreatedUserImage")!=null)
	            	discussionQuestion.setCreatedUserImage(updatesDoc.getFieldValue("CreatedUserImage").toString());
	            	
	            	if(updatesDoc.getFieldValue("CategoryId")!=null)
	            	discussionQuestion.setCategoryId(updatesDoc.getFieldValue("CategoryId").toString());
	            	
	            	if(updatesDoc.getFieldValue("CategoryName")!=null)
	            	discussionQuestion.setCategoryName(updatesDoc.getFieldValue("CategoryName").toString());
	            	
	            	if(updatesDoc.getFieldValue("SubCategoryID")!=null)
	            	discussionQuestion.setSubCategoryID(updatesDoc.getFieldValue("SubCategoryID").toString());
	            	
	            	if(updatesDoc.getFieldValue("SubCategoryName")!=null)
	            	discussionQuestion.setSubCategoryName(updatesDoc.getFieldValue("SubCategoryName").toString());
	            	
	            	if(updatesDoc.getFieldValue("SubCategorySafeName")!=null)
	              	discussionQuestion.setSubCategorySafeName(updatesDoc.getFieldValue("SubCategorySafeName").toString());
	            	
	            	if(updatesDoc.getFieldValue("AnswerCount")!=null)
	              	discussionQuestion.setAnswerCount(updatesDoc.getFieldValue("AnswerCount").toString());
	            	
	            	if(updatesDoc.getFieldValue("Rating")!=null)
	              	discussionQuestion.setRating(updatesDoc.getFieldValue("Rating").toString());
	            	
	            	if(updatesDoc.getFieldValue("Tags")!=null)
	            	discussionQuestion.setTags(updatesDoc.getFieldValue("Tags").toString());
	            	
	            	if(updatesDoc.getFieldValue("LastRepliedUserId")!=null)
	            	discussionQuestion.setLastRepliedUserId(updatesDoc.getFieldValue("LastRepliedUserId").toString());
	            	
	            	if(updatesDoc.getFieldValue("LastRepliedUserDisplayName")!=null)
	            	discussionQuestion.setLastRepliedUserDisplayName(updatesDoc.getFieldValue("LastRepliedUserDisplayName").toString());
	            	
	            	if(updatesDoc.getFieldValue("LastRepliedUserScreenName")!=null)
	            	discussionQuestion.setLastRepliedUserScreenName(updatesDoc.getFieldValue("LastRepliedUserScreenName").toString());
	            	
	            	if(updatesDoc.getFieldValue("LastRepliedUserEmailAddress")!=null)
	            	discussionQuestion.setLastRepliedUserEmailAddress(updatesDoc.getFieldValue("LastRepliedUserEmailAddress").toString());
	            	
	            	if(updatesDoc.getFieldValue("LastRepliedUserImage")!=null)
	            	discussionQuestion.setLastRepliedUserImage(updatesDoc.getFieldValue("LastRepliedUserImage").toString());
	            	
	            	if(updatesDoc.getFieldValue("LastRepliedDate")!=null)
	            	discussionQuestion.setLastRepliedDate(updatesDoc.getFieldValue("LastRepliedDate").toString());            	
	             	
	            	if(updatesDoc.getFieldValue("InitialAnswer")!=null)
	            	discussionQuestion.setInitialAnswer(updatesDoc.getFieldValue("InitialAnswer").toString());
	             	
	            	if(updatesDoc.getFieldValue("CreatedUserType")!=null)
	            	discussionQuestion.setCreatedUserType(updatesDoc.getFieldValue("CreatedUserType").toString());
	             	
	            	if(updatesDoc.getFieldValue("LastRepliedUserType")!=null)
	            	discussionQuestion.setLastRepliedUserType(updatesDoc.getFieldValue("LastRepliedUserType").toString());
	          //   	discussionQuestion.setIsFeatured(updatesDoc.getFieldValue("IsFeatured").toString());
	            	
	            	if(updatesDoc.getFieldValue("CreatedUserImageJson")!=null)
	            	discussionQuestion.setCreatedUserImageJson(updatesDoc.getFieldValue("CreatedUserImageJson").toString());
	            	
	            	if(updatesDoc.getFieldValue("LastRepliedUserImageJson")!=null)
	             	discussionQuestion.setLastRepliedUserImageJson(updatesDoc.getFieldValue("LastRepliedUserImageJson").toString());
	             
	             	
	            	if(updatesDoc.getFieldValue("Everyone")!=null)
	             	discussionQuestion.setEveryone(updatesDoc.getFieldValue("Everyone").toString());
	             	
	            	if(updatesDoc.getFieldValue("Onlyme")!=null)
	            	discussionQuestion.setOnlyme(updatesDoc.getFieldValue("Onlyme").toString());
	             	
	            	if(updatesDoc.getFieldValue("FoldersShowList")!=null)
	            	discussionQuestion.setFoldersShowList(updatesDoc.getFieldValue("FoldersShowList").toString());
	            	
	            	if(updatesDoc.getFieldValue("FoldersHideList")!=null)
	             	discussionQuestion.setFoldersHideList(updatesDoc.getFieldValue("FoldersHideList").toString());
	             	
	            	if(updatesDoc.getFieldValue("ContactsShowList")!=null)
	            	discussionQuestion.setContactsShowList(updatesDoc.getFieldValue("ContactsShowList").toString());
	             	
	            	if(updatesDoc.getFieldValue("ContactsHideList")!=null)
	            	discussionQuestion.setContactsHideList(updatesDoc.getFieldValue("ContactsHideList").toString());
	             	
	            	if(updatesDoc.getFieldValue("IsCustom")!=null)
	            	discussionQuestion.setIsCustom(updatesDoc.getFieldValue("IsCustom").toString());
	             	
	            	//Date dateString = (Date) updatesDoc.getFieldValue("LastRepliedDate");
	            	//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	            	
	                       	questions.add(discussionQuestion);
	            }
	            Object[] resultArr = new Object[2];
	            String   numFound = response.getResults().getNumFound()+"";
	            resultArr[0] = questions;
	            resultArr[1] = numFound;
	
	    return  resultArr;  
    } catch (SolrServerException e) {
    	logDetails.getException(e , logger , request);
        throw new DataAccessResourceFailureException(e.getMessage(), e);
    }
	
	}
	
	
	
	public Object[] fetchPrivacySetting(String query,int pageCount,int rows , HttpServletRequest request) {
	    try {
	    	String url = "http://localhost:8999/solr";
	    	SolrServer server =  Adder.getSolrServer(url);
		    ModifiableSolrParams params = new ModifiableSolrParams();
		    params.set("q", query);
		    params.set("start", ""+pageCount);
		    params.set("version", "2.2");
		    params.set("wt", "json");
		    params.set("indent", "on");
		    params.set("rows", rows);

		    QueryResponse response = server.query(params, SolrRequest.METHOD.POST);
		    	            Object[] resultArr = new Object[2];
			    String   numFound = response.getResults().getNumFound()+"";
		        resultArr[0] = response.getResults();
		        resultArr[1] = numFound;
	            
	    return  resultArr;  
    } catch (SolrServerException e) {
    	logDetails.getException(e , logger , request);
        throw new DataAccessResourceFailureException(e.getMessage(), e);
    }
	}
	
	public  Object[] fetchAllQuestionsPaged(String query,int pageCount,int rows , String field, String sortOrder , HttpServletRequest request) {
	    try {
	    	
	    	String url = serverurlConstants.ADD_DISSCUSSION_QUESTION_URL;	    	
	    	SolrServer server =  Adder.getSolrServer(url);
		    ModifiableSolrParams params = new ModifiableSolrParams();
		    params.set("q", query);
		    params.set("sort", ""+field+" "+sortOrder+"");
		    params.set("start", ""+pageCount);
		    params.set("version", "2.2");
		    params.set("wt", "json");
		    params.set("indent", "on");
		    params.set("rows", rows);

		    QueryResponse response = server.query(params, SolrRequest.METHOD.POST);
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String lreplyDate = null;
		 String cdDate = null;
		 Date lastRepliedDate=null;
		 Date createdDate=null;
		    List <DiscussionQuestion> questions = new ArrayList<DiscussionQuestion>();
	            for (SolrDocument updatesDoc : response.getResults())
	            {   	
	            	DiscussionQuestion discussionQuestion = new DiscussionQuestion();
	            	discussionQuestion.setID(updatesDoc.getFieldValue("ID").toString());
	            	if(updatesDoc.getFieldValue("QuestionText")!=null)
	            	discussionQuestion.setQuestionText(updatesDoc.getFieldValue("QuestionText").toString());
	            	if(updatesDoc.getFieldValue("CreatedDate")!=null)
	            	discussionQuestion.setCreatedDate(dateClient.addDateFromSolrToService(updatesDoc.getFieldValue("CreatedDate") , request));	            	
	            	if(updatesDoc.getFieldValue("CreatedUserID")!=null)
	            	discussionQuestion.setCreatedUserID(updatesDoc.getFieldValue("CreatedUserID").toString());
	            	if(updatesDoc.getFieldValue("CreatedUserDisplayName")!=null)
	            	discussionQuestion.setCreatedUserDisplayName(updatesDoc.getFieldValue("CreatedUserDisplayName").toString());
	            	if(updatesDoc.getFieldValue("CreatedUserScreenName")!=null)
	            	discussionQuestion.setCreatedUserScreenName(updatesDoc.getFieldValue("CreatedUserScreenName").toString());
	            	if(updatesDoc.getFieldValue("CreatedUserEmailAddress")!=null)
	            	discussionQuestion.setCreatedUserEmailAddress(updatesDoc.getFieldValue("CreatedUserEmailAddress").toString());
	            	if(updatesDoc.getFieldValue("CreatedUserImage")!=null)
	            	discussionQuestion.setCreatedUserImage(updatesDoc.getFieldValue("CreatedUserImage").toString());
	            	if(updatesDoc.getFieldValue("CategoryId")!=null)
	            	discussionQuestion.setCategoryId(updatesDoc.getFieldValue("CategoryId").toString());
	            	if(updatesDoc.getFieldValue("CategoryName")!=null)
	            	discussionQuestion.setCategoryName(updatesDoc.getFieldValue("CategoryName").toString());
	            	if(updatesDoc.getFieldValue("SubCategoryID")!=null)
	            	discussionQuestion.setSubCategoryID(updatesDoc.getFieldValue("SubCategoryID").toString());
	            	if(updatesDoc.getFieldValue("SubCategoryName")!=null)
	            	discussionQuestion.setSubCategoryName(updatesDoc.getFieldValue("SubCategoryName").toString());
	            	if(updatesDoc.getFieldValue("SubCategorySafeName")!=null)
	              	discussionQuestion.setSubCategorySafeName(updatesDoc.getFieldValue("SubCategorySafeName").toString());
	            	if(updatesDoc.getFieldValue("AnswerCount")!=null)
	            	discussionQuestion.setAnswerCount(updatesDoc.getFieldValue("AnswerCount").toString());
	            	if(updatesDoc.getFieldValue("Rating")!=null)
	            	discussionQuestion.setRating(updatesDoc.getFieldValue("Rating").toString());	            
	            	if(updatesDoc.getFieldValue("Tags")!=null)
	            	discussionQuestion.setTags(updatesDoc.getFieldValue("Tags").toString());
	            	if(updatesDoc.getFieldValue("LastRepliedUserId")!=null)
	            	discussionQuestion.setLastRepliedUserId(updatesDoc.getFieldValue("LastRepliedUserId").toString());
	            	if(updatesDoc.getFieldValue("LastRepliedUserDisplayName")!=null)
	            	discussionQuestion.setLastRepliedUserDisplayName(updatesDoc.getFieldValue("LastRepliedUserDisplayName").toString());
	            	if(updatesDoc.getFieldValue("LastRepliedUserScreenName")!=null)
	            	discussionQuestion.setLastRepliedUserScreenName(updatesDoc.getFieldValue("LastRepliedUserScreenName").toString());
	            	if(updatesDoc.getFieldValue("LastRepliedUserEmailAddress")!=null)
	            	discussionQuestion.setLastRepliedUserEmailAddress(updatesDoc.getFieldValue("LastRepliedUserEmailAddress").toString());
	            	if(updatesDoc.getFieldValue("LastRepliedUserImage")!=null)
	            	discussionQuestion.setLastRepliedUserImage(updatesDoc.getFieldValue("LastRepliedUserImage").toString());
	            	if(updatesDoc.getFieldValue("LastRepliedDate")!=null)
	            	discussionQuestion.setLastRepliedDate(dateClient.addDateFromSolrToService(updatesDoc.getFieldValue("LastRepliedDate") , request));
	            	if(updatesDoc.getFieldValue("InitialAnswer")!=null)
	            	discussionQuestion.setInitialAnswer(updatesDoc.getFieldValue("InitialAnswer").toString());
	            	if(updatesDoc.getFieldValue("CreatedUserType")!=null)
	            	discussionQuestion.setCreatedUserType(updatesDoc.getFieldValue("CreatedUserType").toString());
	            	if(updatesDoc.getFieldValue("LastRepliedUserType")!=null)
	            	discussionQuestion.setLastRepliedUserType(updatesDoc.getFieldValue("LastRepliedUserType").toString());
	           //  	discussionQuestion.setIsFeatured(updatesDoc.getFieldValue("IsFeatured").toString());
	             	
	            	if(updatesDoc.getFieldValue("CreatedUserImageJson")!=null)
	             	discussionQuestion.setCreatedUserImageJson(updatesDoc.getFieldValue("CreatedUserImageJson").toString());
	            	if(updatesDoc.getFieldValue("LastRepliedUserImageJson")!=null)
	            	discussionQuestion.setLastRepliedUserImageJson(updatesDoc.getFieldValue("LastRepliedUserImageJson").toString());
	             	
	            	if(updatesDoc.getFieldValue("Everyone")!=null)
	            	discussionQuestion.setEveryone(updatesDoc.getFieldValue("Everyone").toString());
	            	if(updatesDoc.getFieldValue("Onlyme")!=null)
	            	discussionQuestion.setOnlyme(updatesDoc.getFieldValue("Onlyme").toString());
	            	if(updatesDoc.getFieldValue("FoldersShowList")!=null)
	            	discussionQuestion.setFoldersShowList(updatesDoc.getFieldValue("FoldersShowList").toString());
	            	if(updatesDoc.getFieldValue("FoldersHideList")!=null)
	            	discussionQuestion.setFoldersHideList(updatesDoc.getFieldValue("FoldersHideList").toString());
	            	if(updatesDoc.getFieldValue("ContactsShowList")!=null)
	            	discussionQuestion.setContactsShowList(updatesDoc.getFieldValue("ContactsShowList").toString());
	            	if(updatesDoc.getFieldValue("ContactsHideList")!=null)
	            	discussionQuestion.setContactsHideList(updatesDoc.getFieldValue("ContactsHideList").toString());
	            	if(updatesDoc.getFieldValue("IsCustom")!=null)
	            	discussionQuestion.setIsCustom(updatesDoc.getFieldValue("IsCustom").toString());
	            	
	             	
	                questions.add(discussionQuestion);
	            }
	            Object[] resultArr = new Object[2];
	            String   numFound = response.getResults().getNumFound()+"";
	            resultArr[0] = questions;
	            resultArr[1] = numFound;
	
	    return  resultArr;  
    } catch (SolrServerException e) {
    	logDetails.getException(e , logger ,request);
        throw new DataAccessResourceFailureException(e.getMessage(), e);
    }
	
	}

	
	public  Object[] discussionHeaderSearch(String query,int pageCount,int rows , HttpServletRequest request) { 
	    try {
	    
	    	SolrServer server =  Adder.getSolrServer(serverurlConstants.ADD_DISSCUSSION_QUESTION_URL);
		    ModifiableSolrParams params = new ModifiableSolrParams();
		    params.set("q", query);
		    params.set("sort", "AnswerCount desc");
		    params.set("start", ""+pageCount);
		    params.set("fl", "ID , QuestionText , CreatedUserDisplayName , CategoryName , AnswerCount , ProfileFileJson");
		    params.set("version", "2.2");
		    params.set("wt", "json");
		    params.set("indent", "on");
		    params.set("rows", rows);

		    QueryResponse response = server.query(params, SolrRequest.METHOD.POST);
		    
		    List <HeaderQuestion> questions = new ArrayList<HeaderQuestion>();
	            for (SolrDocument updatesDoc : response.getResults())
	            {
	            	HeaderQuestion discussionQuestion = new HeaderQuestion();
	            	discussionQuestion.setID(updatesDoc.getFieldValue("ID").toString());
	            	if(updatesDoc.getFieldValue("QuestionText")!=null)
	            	discussionQuestion.setQuestionText(updatesDoc.getFieldValue("QuestionText").toString());
	            	if(updatesDoc.getFieldValue("CreatedUserDisplayName")!=null)
	            	discussionQuestion.setCreatedUserDisplayName(updatesDoc.getFieldValue("CreatedUserDisplayName").toString());
	            	if(updatesDoc.getFieldValue("CategoryName")!=null)
	            	discussionQuestion.setCategoryName(updatesDoc.getFieldValue("CategoryName").toString());
	            	if(updatesDoc.getFieldValue("AnswerCount")!=null)
	            	discussionQuestion.setAnswerCount(updatesDoc.getFieldValue("AnswerCount").toString());
	            	if(updatesDoc.getFieldValue("ProfileFileJson")!=null)
		            	discussionQuestion.setProfileFileJson(updatesDoc.getFieldValue("ProfileFileJson").toString());
	            	
	            	questions.add(discussionQuestion);
	            }
	            Object[] resultArr = new Object[2];
	            String   numFound = response.getResults().getNumFound()+"";
	            resultArr[0] = questions;
	            resultArr[1] = numFound;
	
	    return  resultArr;  
    } catch (SolrServerException e) {
    	logDetails.getException(e , logger ,request);
        throw new DataAccessResourceFailureException(e.getMessage(), e);
    }
	}
	
	public Object[] fetchQuestionStatus(String query , String url , HttpServletRequest request) {
	    try {
	    	
	    	SolrServer server =  Adder.getSolrServer(url);
		    ModifiableSolrParams params = new ModifiableSolrParams();
		    params.set("q", query);
		    params.set("version", "2.2");
		    params.set("wt", "json");
		    params.set("indent", "on");
		  

		    QueryResponse response = server.query(params, SolrRequest.METHOD.POST);
		    	Object[] resultArr = new Object[2];
			    String   numFound = response.getResults().getNumFound()+"";
		        resultArr[0] = response.getResults();
		        resultArr[1] = numFound;
	            
	    return  resultArr;  
    } catch (SolrServerException e) {
    	logDetails.getException(e , logger ,request);
        throw new DataAccessResourceFailureException(e.getMessage(), e);
    }
	}
	
	
}
