package com.ytk.client;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Component;

import com.ytk.utility.Adder;
import com.ytk.utility.ServerurlConstants;


@Component("demoClient")

public class DemoClient {
	
	@Autowired
	ServerurlConstants serverurlConstants;

	
	public  Object[] getData(String query,int pageCount ,int rows) {
		SolrQuery solrQuery = null;
		//QueryResponse response = null;
	    try {
	    //	String url = "http://localhost:8992/solr";
	    	
	    	
	    	SolrServer server =  Adder.getSolrServer(ServerurlConstants.ADD_DEMO_URL);
		    ModifiableSolrParams params = new ModifiableSolrParams();
		    params.set("q", query);
		    params.set("start", ""+pageCount);
		    params.set("version", "2.2"); 
		    params.set("wt", "json");
		   // params.set("sort", ""+sortBy+" desc"); 
		    params.set("indent", "on");
		    params.set("rows", ""+rows);
		    Object[] resultArr = new Object[2];
		    QueryResponse response = server.query(params, SolrRequest.METHOD.POST);
		    String   numFound = response.getResults().getNumFound()+"";
	        resultArr[0] = response.getResults();
	        resultArr[1] = numFound;
	        return  resultArr;  
	    } catch (SolrServerException e) {
	        throw new DataAccessResourceFailureException(e.getMessage(), e);
	    }
	   // return response;
	} 	
	
	
	
	
}
