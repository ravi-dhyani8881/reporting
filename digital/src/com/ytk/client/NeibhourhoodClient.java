package com.ytk.client;

import java.util.ArrayList;
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

import com.ytk.models.Neibhourhood;
import com.ytk.utility.Adder;
import com.ytk.utility.LogDetails;
import com.ytk.utility.ServerurlConstants;



@Component("neibhourhoodClient")
public class NeibhourhoodClient {

	private static final Logger logger = LoggerFactory.getLogger(NeibhourhoodClient.class);
	
	@Autowired
	ServerurlConstants serverurlConstants;
	
	@Autowired
	LogDetails logDetails;
	
	private List <Neibhourhood> fetchNeibhourhood(String query,int pageCount,int rows , HttpServletRequest request) {
	    try {
	   
	    	SolrServer server =  Adder.getSolrServer(serverurlConstants.ADD_NEIBHOURHOOD_URL);
		    ModifiableSolrParams params = new ModifiableSolrParams();
		    params.set("q", query);
		   // params.set("sort", "TimeStamp desc");
		    params.set("start", ""+pageCount);
		    params.set("version", "2.2");
		    params.set("wt", "json");
		    params.set("indent", "on");
		    params.set("rows", rows);

		    QueryResponse response = server.query(params, SolrRequest.METHOD.POST);
		    
		    List <Neibhourhood> neibhourhoodList = new ArrayList<Neibhourhood>();
	            for (SolrDocument updatesDoc : response.getResults())
	            {
	            	Neibhourhood neibhour = new Neibhourhood();
	            	neibhour.setID(updatesDoc.getFieldValue("ID").toString());
	            	neibhour.setName(updatesDoc.getFieldValue("Name").toString());
	            	neibhour.setType(updatesDoc.getFieldValue("Type").toString());
	            	neibhour.setCityID(updatesDoc.getFieldValue("CityID").toString());
	            	neibhour.setCityName(updatesDoc.getFieldValue("CityName").toString());
	            	neibhour.setStatus(updatesDoc.getFieldValue("Status").toString());
	            	neibhour.setCreatedDate(updatesDoc.getFieldValue("CreatedDate").toString());
	            	neibhourhoodList.add(neibhour);
	            }
	
	    return  neibhourhoodList;  
    } catch (SolrServerException e) {
    	logDetails.getException(e , logger , request);
    	
        throw new DataAccessResourceFailureException(e.getMessage(), e);
    }
	}
	
	
}
