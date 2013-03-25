package com.ytk.controller;

import java.util.ArrayList;
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

import com.ytk.client.DateClient;
import com.ytk.client.NeibhourhoodClient;
import com.ytk.models.AutoCompleteResponse;
import com.ytk.models.DiscussionQuestion;
import com.ytk.models.Plans;
import com.ytk.models.Updates;
import com.ytk.models.Neibhourhood;
import com.ytk.utility.Adder;
import com.ytk.utility.ServerurlConstants;


@Controller
@RequestMapping("/neighbourhood/*")
public class NeibhourhoodController {

	@Autowired
	ServerurlConstants serverurlConstants;
	
	@Autowired
	DateClient dateClient;	
	
	@Autowired
	NeibhourhoodClient neibhourhoodClient;
	
	
	/**
	 * Service to Search Updates with Relevance
	 * @param member_id
	 * @return a List of Updates in response
	 */

	@RequestMapping(value = "/findneighbourhood")
	public   @ResponseBody AutoCompleteResponse findNeibhourhood(
			HttpServletRequest request,
			@RequestParam(value = "query", required = false) String Keyword,
			@RequestParam(value = "type", required = false) String Type){
			ModelAndView mav = new ModelAndView();
			List<Neibhourhood> updated = new ArrayList<Neibhourhood>();
			String queryText = "(Name:"+Keyword.toLowerCase()+"*) AND (Type:"+Type+")";
			updated = this.fetchNeibhourhood(queryText,0,350, request);
			List id = new ArrayList();
			List title = new ArrayList();
			
			for (Neibhourhood neibhourhood :  updated)
			{
					id.add(neibhourhood.getID());
					title.add(neibhourhood.getName());
			}
			
			AutoCompleteResponse autoComplete = new AutoCompleteResponse();
			autoComplete.setQuery(Keyword);
			autoComplete.setData(id);
			autoComplete.setSuggestions(title);
			//mav.addObject(autoComplete);
			return autoComplete;
	}	
	

	@RequestMapping(value = "/addneibhourhood")
	public ModelAndView addNeibhourhood(
			HttpServletRequest request,
			@RequestParam(value = "ID", required = false) String ID,
			@RequestParam(value = "Name", required = false) String Name,
			@RequestParam(value = "Type", required = false) String Type,
			@RequestParam(value = "CityID", required = false) String CityID,
			@RequestParam(value = "CityName", required = false) String CityName,
			@RequestParam(value = "Status", required = false) String Status,
			@RequestParam(value = "CreatedDate", required = false) String CreatedDate)
			{
		List<Neibhourhood> updated = new ArrayList<Neibhourhood>();
		String queryText = "(Name:"+Name.toLowerCase()+") AND (Type:"+Type+")";
		updated = this.fetchNeibhourhood(queryText,0,350, request);
		ModelAndView mav = new ModelAndView();
		if(updated.size() == 0)
		{
			
			SolrInputDocument neibhourhood = new SolrInputDocument();
			neibhourhood.addField("ID", ID);
			neibhourhood.addField("Name", Name);
			neibhourhood.addField("Type", Type);
			neibhourhood.addField("CityID", CityID);
			neibhourhood.addField("CityName", CityName);
			neibhourhood.addField("Status", Status);
			neibhourhood.addField("CreatedDate", CreatedDate);
			Adder.addNeibhourhood(serverurlConstants.ADD_NEIBHOURHOOD_URL , neibhourhood);
			String result = "success";
			mav.addObject("result",result);
		}
		return mav;
	}
	
	/**
	 * Service to DELETE Neibhourhood
	 * @param ID
	 * @return result String
	 */
	
	@RequestMapping(value = "/deleteneibhourhood")
	public ModelAndView deleteNeibhourhood(
			@RequestParam(value = "ID", required = false) String ID)
	{
		ModelAndView mav = new ModelAndView();
		SolrInputDocument updates = new SolrInputDocument();
		updates.addField("ID", ID);	
		Adder.deleteNeibhourhood(serverurlConstants.ADD_NEIBHOURHOOD_URL ,ID);
		String result = "success";
		mav.addObject("result",result);
		return mav;
	}
	
	
	/***
	 * 
	 * @param query
	 * @param pageCount
	 * @param rows
	 * @returns the list of date sorted updates 
	 */
	private List <Neibhourhood> fetchNeibhourhood(String query,int pageCount,int rows, HttpServletRequest request) {
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

		    QueryResponse response = server.query(params, SolrRequest.METHOD.GET);
		    
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
	            	neibhour.setCreatedDate(dateClient.addDateFromSolrToService(updatesDoc.getFieldValue("CreatedDate").toString(), request));
	            	neibhourhoodList.add(neibhour);
	            }
	
	    return  neibhourhoodList;  
    } catch (SolrServerException e) {
        throw new DataAccessResourceFailureException(e.getMessage(), e);
    }
	}
	
	
	
	
	
	@RequestMapping(value = "/getallneighbourhoodbycityid")
	public ModelAndView getAllNeighbourhoodByCityid(	
			HttpServletRequest request,
			@RequestParam(value = "Type", required = false , defaultValue = "0") String Type,
			@RequestParam(value = "CityID", required = false ,  defaultValue = "0") String CityID)		
			{
		List<Neibhourhood> neibourhood = new ArrayList<Neibhourhood>();

		String queryText = "(CityID:"+CityID.toLowerCase()+") AND (Type:"+Type+")";
	
		neibourhood = this.fetchNeibhourhood(queryText,0,10000, request);
		ModelAndView mav = new ModelAndView();		
		mav.addObject("TotalRecords",neibourhood.size());
			mav.addObject("Collection",neibourhood);
			mav.addObject("StatusOutput","1");
			mav.addObject("IsConnected","1");
		return mav;
	}
	
	
	@RequestMapping(value = "/getneighbourhoodbycityidandname")
	public ModelAndView getAllNeighbourhoodByCityidandState(	
			HttpServletRequest request,
			@RequestParam(value = "Name", required = false , defaultValue = "0") String Name,
			@RequestParam(value = "CityID", required = false ,  defaultValue = "0") String CityID)		
			{
		List<Neibhourhood> neibourhood = new ArrayList<Neibhourhood>();
	
		String queryText = "(CityID:"+CityID.toLowerCase()+") AND (Name:"+Name+")";
		neibourhood = this.fetchNeibhourhood(queryText,0,10000, request);
		ModelAndView mav = new ModelAndView();		
	try{
		mav.addObject("ID",neibourhood.get(0).getID());
		mav.addObject("Name",neibourhood.get(0).getName());
		mav.addObject("Type",neibourhood.get(0).getType());
		mav.addObject("CityID",neibourhood.get(0).getCityID());
		mav.addObject("CityName",neibourhood.get(0).getCityName());
		mav.addObject("Status",neibourhood.get(0).getStatus());
		mav.addObject("CreatedDate",neibourhood.get(0).getCreatedDate());
		}catch (Exception e) {
			e.printStackTrace();
			return mav;
		}
		
//		mav.addObject("TotalRecords",neibourhood.size());
//			mav.addObject("Collection",neibourhood);
//			mav.addObject("StatusOutput","1");
//			mav.addObject("IsConnected","1");
		return mav;
	}
	
	
	
	
	
}