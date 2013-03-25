package com.ytk.client;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.apache.solr.client.solrj.SolrQuery;
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

import com.ytk.models.DiscussionQuestion;
import com.ytk.models.PCommunity;
import com.ytk.utility.Adder;
import com.ytk.utility.LogDetails;
import com.ytk.utility.ServerurlConstants;
import com.ytk.client.DateClient;
import com.ytk.controller.demoControler;


@Component("connectionClient")
public class ConnectionClient {

	
	private static final Logger logger = LoggerFactory.getLogger(ConnectionClient.class);
	
	@Autowired
	DateClient dateClient;
	
	@Autowired
	LogDetails logDetails;
	
	public  Object[] queryConnection(String solrUrl, String query,int pageCount,int rows , HttpServletRequest request) {
		SolrQuery solrQuery = null;
		//QueryResponse response = null;
	    try {
	
	    	SolrServer server =  Adder.getSolrServer(solrUrl);
		    ModifiableSolrParams params = new ModifiableSolrParams();
		    params.set("q", query);
		    params.set("start", ""+pageCount);
		    params.set("version", "2.2");
		    params.set("wt", "json");
		    params.set("indent", "on");
		    params.set("rows", ""+rows);
		    Object[] resultArr = new Object[2];
		    QueryResponse response = server.query(params, SolrRequest.METHOD.POST);
		    List <PCommunity> communityList = new ArrayList<PCommunity>();
		    for (SolrDocument connectionDocs : response.getResults())
            {
		    	PCommunity connection = new PCommunity();
		    	connection.setID(connectionDocs.getFieldValue("ID").toString());
		    	connection.setMemberID(connectionDocs.getFieldValue("MemberID").toString());
		    	connection.setMemberType(connectionDocs.getFieldValue("MemberType").toString());
		    	connection.setMemberDisplayName(connectionDocs.getFieldValue("MemberDisplayName").toString());
		    	connection.setMemberScreenName(connectionDocs.getFieldValue("MemberScreenName").toString());
		    	connection.setMemberEmailAddress(connectionDocs.getFieldValue("MemberEmailAddress").toString());
		    	connection.setContactID(connectionDocs.getFieldValue("ContactID").toString());
		    	connection.setContactType(connectionDocs.getFieldValue("ContactType").toString());
		    	connection.setContactDisplayName(connectionDocs.getFieldValue("ContactDisplayName").toString());
		    	connection.setContactScreenName(connectionDocs.getFieldValue("ContactScreenName").toString());
		    	connection.setContactEmailAddress(connectionDocs.getFieldValue("ContactEmailAddress").toString());
		    	connection.setContactProfileImageName(connectionDocs.getFieldValue("ContactProfileImageName").toString());
		    	connection.setContactBirthDay(dateClient.addDateFromSolrToServiceforPdate(connectionDocs.getFieldValue("ContactBirthDay"), request));
		    	connection.setContactGender(connectionDocs.getFieldValue("ContactGender").toString());
		    	connection.setContactCity(connectionDocs.getFieldValue("ContactCity").toString());
		    	connection.setContactState(connectionDocs.getFieldValue("ContactState").toString());
		    	connection.setContactCountry(connectionDocs.getFieldValue("ContactCountry").toString());
		    	
		    	connection.setContactProfileImageJson(connectionDocs.getFieldValue("ContactProfileImageJson").toString());
		    	
		    	connection.setContactDefaultAlbumID(connectionDocs.getFieldValue("ContactDefaultAlbumID").toString());
		    	connection.setContactDefaultAlbumsPhotoCount(connectionDocs.getFieldValue("ContactDefaultAlbumsPhotoCount").toString());
		    	connection.setContactZodiacSignID(connectionDocs.getFieldValue("ContactZodiacSignID").toString());
		    	connection.setContactZodiacAnimalID(connectionDocs.getFieldValue("ContactZodiacAnimalID").toString());
		    	connection.setFolderID(connectionDocs.getFieldValue("FolderID").toString());
		    	connection.setFolderName(connectionDocs.getFieldValue("FolderName").toString());
		    	connection.setIsUpdatesHide(connectionDocs.getFieldValue("IsUpdatesHide").toString());
		    	connection.setStatus(connectionDocs.getFieldValue("Status").toString());
		    	connection.setCreatedDate(dateClient.addDateFromSolrToServiceforPdate(connectionDocs.getFieldValue("CreatedDate"), request));
		    	connection.setAcceptedDate(dateClient.addDateFromSolrToServiceforPdate(connectionDocs.getFieldValue("AcceptedDate"), request));
		    	connection.setUpdatedDate(dateClient.addDateFromSolrToServiceforPdate(connectionDocs.getFieldValue("UpdatedDate"), request));
		    	communityList.add(connection);
		    	  }
		    
		    String   numFound = response.getResults().getNumFound()+"";
		    resultArr[0] = communityList;
		    
	        resultArr[1] = numFound;
	        return  resultArr;  
	            
	    }
	    catch (SolrServerException e) {
	    	logDetails.getException(e , logger , request);
	    	
	        throw new DataAccessResourceFailureException(e.getMessage(), e);
	    }
	}
	
	
	
	public  Object[] queryConnectionForUpdate(String solrUrl, String query , HttpServletRequest request) {
		SolrQuery solrQuery = null;
		//QueryResponse response = null;
	    try {
	
	    	SolrServer server =  Adder.getSolrServer(solrUrl);
		    ModifiableSolrParams params = new ModifiableSolrParams();
		    params.set("q", query);
		   // params.set("start", ""+pageCount);
		    params.set("version", "2.2");
		    params.set("wt", "json");
		    params.set("indent", "on");
		   // params.set("rows", ""+rows);
		    Object[] resultArr = new Object[2];
		    QueryResponse response = server.query(params, SolrRequest.METHOD.POST);
		    List <PCommunity> communityList = new ArrayList<PCommunity>();
		    for (SolrDocument connectionDocs : response.getResults())
            {
		    	PCommunity connection = new PCommunity();
		    	connection.setID(connectionDocs.getFieldValue("ID").toString());
		    	connection.setMemberID(connectionDocs.getFieldValue("MemberID").toString());
		    	connection.setMemberType(connectionDocs.getFieldValue("MemberType").toString());
		    	connection.setMemberDisplayName(connectionDocs.getFieldValue("MemberDisplayName").toString());
		    	connection.setMemberScreenName(connectionDocs.getFieldValue("MemberScreenName").toString());
		    	connection.setMemberEmailAddress(connectionDocs.getFieldValue("MemberEmailAddress").toString());
		    	connection.setContactID(connectionDocs.getFieldValue("ContactID").toString());
		    	connection.setContactType(connectionDocs.getFieldValue("ContactType").toString());
		    	connection.setContactDisplayName(connectionDocs.getFieldValue("ContactDisplayName").toString());
		    	connection.setContactScreenName(connectionDocs.getFieldValue("ContactScreenName").toString());
		    	connection.setContactEmailAddress(connectionDocs.getFieldValue("ContactEmailAddress").toString());
		    	connection.setContactProfileImageName(connectionDocs.getFieldValue("ContactProfileImageName").toString());
		    	connection.setContactBirthDay(dateClient.addDateFromSolrToServiceforPdate(connectionDocs.getFieldValue("ContactBirthDay"), request));
		    	connection.setContactGender(connectionDocs.getFieldValue("ContactGender").toString());
		    	connection.setContactCity(connectionDocs.getFieldValue("ContactCity").toString());
		    	connection.setContactState(connectionDocs.getFieldValue("ContactState").toString());
		    	connection.setContactCountry(connectionDocs.getFieldValue("ContactCountry").toString());
		    	
		    	connection.setContactProfileImageJson(connectionDocs.getFieldValue("ContactProfileImageJson").toString());
		    	
		    	connection.setContactDefaultAlbumID(connectionDocs.getFieldValue("ContactDefaultAlbumID").toString());
		    	connection.setContactDefaultAlbumsPhotoCount(connectionDocs.getFieldValue("ContactDefaultAlbumsPhotoCount").toString());
		    	connection.setContactZodiacSignID(connectionDocs.getFieldValue("ContactZodiacSignID").toString());
		    	connection.setContactZodiacAnimalID(connectionDocs.getFieldValue("ContactZodiacAnimalID").toString());
		    	connection.setFolderID(connectionDocs.getFieldValue("FolderID").toString());
		    	connection.setFolderName(connectionDocs.getFieldValue("FolderName").toString());
		    	connection.setIsUpdatesHide(connectionDocs.getFieldValue("IsUpdatesHide").toString());
		    	connection.setStatus(connectionDocs.getFieldValue("Status").toString());
		    	connection.setCreatedDate(dateClient.addDateFromSolrToServiceforPdate(connectionDocs.getFieldValue("CreatedDate"), request));
		    	connection.setAcceptedDate(dateClient.addDateFromSolrToServiceforPdate(connectionDocs.getFieldValue("AcceptedDate"), request));
		    	connection.setUpdatedDate(dateClient.addDateFromSolrToServiceforPdate(connectionDocs.getFieldValue("UpdatedDate"), request));
		    	communityList.add(connection);
		    	  }
		    
		    String   numFound = response.getResults().getNumFound()+"";
		    resultArr[0] = communityList;
		    
	        resultArr[1] = numFound;
	        return  resultArr;  
	            
	    }
	    catch (SolrServerException e) {
	    	logDetails.getException(e , logger , request);
	    	
	        throw new DataAccessResourceFailureException(e.getMessage(), e);
	    }
	}
	
}
