package com.ytk.client;

import java.util.ArrayList;
import java.util.List;

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
import com.ytk.models.Custom;
import com.ytk.utility.Adder;
import com.ytk.utility.LogDetails;


@Component("customClient")
public class CustomClient {

	private static final Logger logger = LoggerFactory.getLogger(CustomClient.class);
	
	@Autowired
	LogDetails logDetails;
	
	public  Object[] queryCheckForSpecificfieldOutput(String Url ,String query,int pageCount,int size , int Type , HttpServletRequest request) 
	{
	    try 
	    {	    
	    	SolrServer server =  Adder.getSolrServer(Url);
		    ModifiableSolrParams params = new ModifiableSolrParams();
		    params.set("q", query);
		    params.set("fl", "Email , ProfileFileID , Name , ScreenName , ProfileImage");
		    params.set("start", ""+pageCount);
		    params.set("version", "2.2");
		    params.set("wt", "json");
		    params.set("indent", "on");
		    params.set("rows", ""+size);
		   
		    QueryResponse response = server.query(params, SolrRequest.METHOD.POST);
		    
		    List <Custom> customList = new ArrayList<Custom>();
	            for (SolrDocument updatesDoc : response.getResults())
	            {
	            	Custom custom = new Custom();
	            	custom.setEmail(updatesDoc.getFieldValue("Email").toString());
	            	custom.setProfileFileID(updatesDoc.getFieldValue("ProfileFileID").toString());
	            	custom.setName(updatesDoc.getFieldValue("Name").toString());	            	
	            	custom.setScreenName(updatesDoc.getFieldValue("ScreenName").toString());
	            	custom.setProfileImage(updatesDoc.getFieldValue("ProfileImage").toString());
	            	custom.setType(Type);     	
	            	customList.add(custom);
	            }
	            Object[] resultArr = new Object[2];
	            String   numFound = response.getResults().getNumFound()+"";
	            resultArr[0] = customList;
	            resultArr[1] = numFound;
	
	    return  resultArr;  
	    } 
	    catch (SolrServerException e) 
	    {
	    	logDetails.getException(e , logger , request);
	        throw new DataAccessResourceFailureException(e.getMessage(), e);
	    }
	    catch (NullPointerException e) 
	    {
	    	logDetails.getException(e , logger , request);
	    	throw new DataAccessResourceFailureException(e.getMessage(), e);
		}
	   // ret
	   // return response;
	} 	
	
	
}
