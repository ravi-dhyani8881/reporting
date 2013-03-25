package com.ytk.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.ytk.models.ListConnection;
import com.ytk.models.ListContact;
import com.ytk.models.ListConnection.Connections;
import com.ytk.models.ListContact.Contacts;
import com.ytk.utility.Adder;


@Component("searchContactsClient")
public class SearchContactsClient {

	
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
	
	public  List <Contacts> queryCheck(String query,int pageCount,int rows) {
		SolrQuery solrQuery = null;
		//QueryResponse response = null;
	    try {
	    	String url = "http://localhost:8983/solr";
	    	SolrServer server =  Adder.getSolrServer(url);
		    ModifiableSolrParams params = new ModifiableSolrParams();
		    params.set("q", query);
		    params.set("start", ""+pageCount);
		    params.set("version", "2.2");
		    params.set("wt", "json");
		    params.set("indent", "on");
		    params.set("rows", rows);

		    QueryResponse response = server.query(params, SolrRequest.METHOD.POST);
		    
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
	
}
