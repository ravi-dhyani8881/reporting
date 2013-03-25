package com.ytk.client;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Component;

import com.ytk.controller.demoControler;
import com.ytk.models.Updates;
import com.ytk.utility.Adder;
import com.ytk.utility.LogDetails;
import com.ytk.utility.ServerurlConstants;

@Component("updateClient")
public class UpdateClient {
	
	private static final Logger logger = LoggerFactory.getLogger(UpdateClient.class);
	
	@Autowired
	ServerurlConstants serverurlConstants;
	
	@Autowired
	LogDetails logDetails;
	
	public List <Updates> fetchUpdates(String query,int pageCount,int rows , HttpServletRequest request) {
	    try {
	    	
	    	System.out.println("query----->"+query);
	    	
	    	String url = serverurlConstants.ADD_UPDATE_URL;
	    //	String url = "http://localhost:8993/solr";
	    	SolrServer server =  Adder.getSolrServer(url);
		    ModifiableSolrParams params = new ModifiableSolrParams();
		    params.set("q", query);
		   // params.set("sort", "TimeStamp desc");
		    params.set("start", ""+pageCount);
		    params.set("version", "2.2");
		    params.set("wt", "json");
		    params.set("indent", "on");
		    params.set("rows", rows);

		    QueryResponse response = server.query(params, SolrRequest.METHOD.POST);
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Date timeStamp=null;
		    String stimeStamp=null;
		    List <Updates> updates = new ArrayList<Updates>();
	            for (SolrDocument updatesDoc : response.getResults())
	            {
	            	dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
	            	timeStamp=(Date)updatesDoc.getFieldValue("TimeStamp");
	            	stimeStamp= dateFormat.format(timeStamp);
	            	Updates update = new Updates();
	            	update.setID(updatesDoc.getFieldValue("ID").toString());
	            	update.setType(updatesDoc.getFieldValue("Type").toString());
	            	update.setSection(updatesDoc.getFieldValue("Section").toString());
	            	update.setEventID(updatesDoc.getFieldValue("EventID").toString());
	            	update.setEventSectionID(updatesDoc.getFieldValue("EventSectionID").toString());
	            	update.setAccountType(updatesDoc.getFieldValue("AccountType").toString());
	            	update.setAccountID(updatesDoc.getFieldValue("AccountID").toString());
	            	
	            	update.setGeneratorAccountType(updatesDoc.getFieldValue("GeneratorAccountType").toString());
	            	update.setGeneratorAccountID(updatesDoc.getFieldValue("GeneratorAccountID").toString());
	            	
	            	update.setIsRelatedToUpdates(updatesDoc.getFieldValue("IsRelatedToUpdates").toString());
	            	update.setIsRelatedToSearch(updatesDoc.getFieldValue("IsRelatedToSearch").toString());
	            	update.setCommentSection(updatesDoc.getFieldValue("CommentSection").toString());
	            	
	            	update.setAccountRestrictAge(updatesDoc.getFieldValue("AccountRestrictAge").toString());
	            	update.setAccountRestrictCountry(updatesDoc.getFieldValue("AccountRestrictCountry").toString());
	            	update.setGeneratorAccountRestrictAge(updatesDoc.getFieldValue("GeneratorAccountRestrictAge").toString());
	            	update.setGeneratorAccountRestrictCountry(updatesDoc.getFieldValue("GeneratorAccountRestrictCountry").toString());
	            	
	            	
	            	

	            	update.setFoldersHideList(updatesDoc.getFieldValue("FoldersHideList").toString());
	            	update.setContactsHideList(updatesDoc.getFieldValue("ContactsHideList").toString());
	            	update.setContactsShowList(updatesDoc.getFieldValue("ContactsShowList").toString());
	            	update.setFoldersShowList(updatesDoc.getFieldValue("FoldersShowList").toString());
	            	update.setEveryone(updatesDoc.getFieldValue("Everyone").toString());
	            	update.setOnlyMe(updatesDoc.getFieldValue("OnlyMe").toString());
	            	update.setIsCustom(updatesDoc.getFieldValue("IsCustom").toString());
	            //	update.setStatus(updatesDoc.getFieldValue("Status").toString());
	            	update.setHideBy(updatesDoc.getFieldValue("HideBy").toString());
	            	
	            	update.setCommentCount(updatesDoc.getFieldValue("CommentCount").toString());
	            	update.setCommentJson(updatesDoc.getFieldValue("CommentJson").toString());
	            	
	            	//Date dateString = (Date) updatesDoc.getFieldValue("TimeStamp");
	            	//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            		//System.out.println("datechange"+dateString);
	            		//System.out.println("datechange"+dateFormat.format(dateString));
	            	update.setTimeStamp(stimeStamp);
	            	updates.add(update);
	            }
	
	    return  updates;  
    } catch (SolrServerException e) {
    	logDetails.getException(e , logger, request);
        throw new DataAccessResourceFailureException(e.getMessage(), e);
        
    	
        
    }
	}

	
	
	public List <Updates> fetchUpdatesPrivacy(String query,int pageCount,int rows, HttpServletRequest request) {
	    try {
	    	
	    	System.out.println("query----->"+query);
	    	
	    	String url = serverurlConstants.ADD_UPDATE_URL;
	    //	String url = "http://localhost:8993/solr";
	    	SolrServer server =  Adder.getSolrServer(url);
		    ModifiableSolrParams params = new ModifiableSolrParams();
		    params.set("q", query);
		  // 
		    params.set("start", ""+pageCount);
		    params.set("version", "2.2");
		    params.set("wt", "json");
		    params.set("indent", "on");
		    params.set("rows", rows);
		    params.set("sort", "TimeStamp desc");

		    QueryResponse response = server.query(params, SolrRequest.METHOD.POST);
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Date timeStamp=null;
		    String stimeStamp=null;
		    List <Updates> updates = new ArrayList<Updates>();
	            for (SolrDocument updatesDoc : response.getResults())
	            {
	            	dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
	            	timeStamp=(Date)updatesDoc.getFieldValue("TimeStamp");
	            	stimeStamp= dateFormat.format(timeStamp);
	            	Updates update = new Updates();
	            	update.setID(updatesDoc.getFieldValue("ID").toString());
	            	update.setType(updatesDoc.getFieldValue("Type").toString());
	            	update.setSection(updatesDoc.getFieldValue("Section").toString());
	            	update.setEventID(updatesDoc.getFieldValue("EventID").toString());
	            	update.setEventSectionID(updatesDoc.getFieldValue("EventSectionID").toString());
	            	update.setAccountType(updatesDoc.getFieldValue("AccountType").toString());
	            	update.setAccountID(updatesDoc.getFieldValue("AccountID").toString());
	           
	            	update.setGeneratorAccountType(updatesDoc.getFieldValue("GeneratorAccountType").toString());
	            	update.setGeneratorAccountID(updatesDoc.getFieldValue("GeneratorAccountID").toString());
	          
	            	update.setIsRelatedToUpdates(updatesDoc.getFieldValue("IsRelatedToUpdates").toString());
	            	update.setIsRelatedToSearch(updatesDoc.getFieldValue("IsRelatedToSearch").toString());
	            	update.setCommentSection(updatesDoc.getFieldValue("CommentSection").toString());
	            	
	            	
	            	update.setAccountRestrictAge(updatesDoc.getFieldValue("AccountRestrictAge").toString());
	            	update.setAccountRestrictCountry(updatesDoc.getFieldValue("AccountRestrictCountry").toString());
	            	update.setGeneratorAccountRestrictAge(updatesDoc.getFieldValue("GeneratorAccountRestrictAge").toString());
	            	update.setGeneratorAccountRestrictCountry(updatesDoc.getFieldValue("GeneratorAccountRestrictCountry").toString());
	            	
	            	
	            	
	            	update.setFoldersHideList(updatesDoc.getFieldValue("FoldersHideList").toString());
	            	update.setContactsHideList(updatesDoc.getFieldValue("ContactsHideList").toString());
	            	update.setContactsShowList(updatesDoc.getFieldValue("ContactsShowList").toString());
	            	update.setFoldersShowList(updatesDoc.getFieldValue("FoldersShowList").toString());
	            	update.setEveryone(updatesDoc.getFieldValue("Everyone").toString());
	            	update.setOnlyMe(updatesDoc.getFieldValue("OnlyMe").toString());
	            	update.setIsCustom(updatesDoc.getFieldValue("IsCustom").toString());
	        
	            	update.setHideBy(updatesDoc.getFieldValue("HideBy").toString());
	            	
	            	update.setCommentCount(updatesDoc.getFieldValue("CommentCount").toString());
	            	update.setCommentJson(updatesDoc.getFieldValue("CommentJson").toString());
	            	
	            	//    	update.setStatus(updatesDoc.getFieldValue("Status").toString());
	            	
	            	
	            	
	            	//Date dateString = (Date) updatesDoc.getFieldValue("TimeStamp");
	            	//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            		//System.out.println("datechange"+dateString);
	            		//System.out.println("datechange"+dateFormat.format(dateString));
	            	update.setTimeStamp(stimeStamp);
	            	updates.add(update);
	            }
	
	    return  updates;  
    } catch (SolrServerException e) {
    	
    	logDetails.getException(e , logger , request);
    	
        throw new DataAccessResourceFailureException(e.getMessage(), e);
        
        
        
    }
	}
	
	
	public List <Updates> fetchUpdatesbysection(String query , int rows , HttpServletRequest request) {
	    try {
	    	
	    	System.out.println("query----->"+query);
	    	
	    	String url = serverurlConstants.ADD_UPDATE_URL;
	    //	String url = "http://localhost:8993/solr";
	    	SolrServer server =  Adder.getSolrServer(url);
		    ModifiableSolrParams params = new ModifiableSolrParams();
		    params.set("q", query);
		   // params.set("sort", "TimeStamp desc");
		 //   params.set("start", ""+pageCount);
		    params.set("version", "2.2");
		    params.set("wt", "json");
		    params.set("indent", "on");
		    params.set("rows", rows);

		    QueryResponse response = server.query(params, SolrRequest.METHOD.POST);
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Date timeStamp=null;
		    String stimeStamp=null;
		    List <Updates> updates = new ArrayList<Updates>();
	            for (SolrDocument updatesDoc : response.getResults())
	            {
	            	dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
	            	timeStamp=(Date)updatesDoc.getFieldValue("TimeStamp");
	            	stimeStamp= dateFormat.format(timeStamp);
	            	Updates update = new Updates();
	            	update.setID(updatesDoc.getFieldValue("ID").toString());
	            	update.setType(updatesDoc.getFieldValue("Type").toString());
	            	update.setSection(updatesDoc.getFieldValue("Section").toString());
	            	update.setEventID(updatesDoc.getFieldValue("EventID").toString());
	            	update.setEventSectionID(updatesDoc.getFieldValue("EventSectionID").toString());
	            	update.setAccountType(updatesDoc.getFieldValue("AccountType").toString());
	            	update.setAccountID(updatesDoc.getFieldValue("AccountID").toString());
	            	
	            	update.setGeneratorAccountType(updatesDoc.getFieldValue("GeneratorAccountType").toString());
	            	update.setGeneratorAccountID(updatesDoc.getFieldValue("GeneratorAccountID").toString());
	            	
	            	update.setIsRelatedToUpdates(updatesDoc.getFieldValue("IsRelatedToUpdates").toString());
	            	update.setIsRelatedToSearch(updatesDoc.getFieldValue("IsRelatedToSearch").toString());
	            	update.setCommentSection(updatesDoc.getFieldValue("CommentSection").toString());
	            	
	            	update.setAccountRestrictAge(updatesDoc.getFieldValue("AccountRestrictAge").toString());
	            	update.setAccountRestrictCountry(updatesDoc.getFieldValue("AccountRestrictCountry").toString());
	            	update.setGeneratorAccountRestrictAge(updatesDoc.getFieldValue("GeneratorAccountRestrictAge").toString());
	            	update.setGeneratorAccountRestrictCountry(updatesDoc.getFieldValue("GeneratorAccountRestrictCountry").toString());
	            	
	            	
	            	

	            	update.setFoldersHideList(updatesDoc.getFieldValue("FoldersHideList").toString());
	            	update.setContactsHideList(updatesDoc.getFieldValue("ContactsHideList").toString());
	            	update.setContactsShowList(updatesDoc.getFieldValue("ContactsShowList").toString());
	            	update.setFoldersShowList(updatesDoc.getFieldValue("FoldersShowList").toString());
	            	update.setEveryone(updatesDoc.getFieldValue("Everyone").toString());
	            	update.setOnlyMe(updatesDoc.getFieldValue("OnlyMe").toString());
	            	update.setIsCustom(updatesDoc.getFieldValue("IsCustom").toString());
	            //	update.setStatus(updatesDoc.getFieldValue("Status").toString());
	            	update.setHideBy(updatesDoc.getFieldValue("HideBy").toString());
	            	
	            	update.setCommentCount(updatesDoc.getFieldValue("CommentCount").toString());
	            	update.setCommentJson(updatesDoc.getFieldValue("CommentJson").toString());
	            	
	            	//Date dateString = (Date) updatesDoc.getFieldValue("TimeStamp");
	            	//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            		//System.out.println("datechange"+dateString);
	            		//System.out.println("datechange"+dateFormat.format(dateString));
	            	update.setTimeStamp(stimeStamp);
	            	updates.add(update);
	            }
	
	    return  updates;  
    } catch (SolrServerException e) {
    	logDetails.getException(e , logger , request);
        throw new DataAccessResourceFailureException(e.getMessage(), e);
        
    	
        
    }
	
	
	}
}
