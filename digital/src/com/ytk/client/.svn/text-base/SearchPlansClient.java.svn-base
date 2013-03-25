package com.ytk.client;

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

import com.ytk.models.Plans;
import com.ytk.utility.Adder;
import com.ytk.utility.LogDetails;
import com.ytk.utility.ServerurlConstants;


@Component("searchPlansClient")
public class SearchPlansClient {

	private static final Logger logger = LoggerFactory.getLogger(SearchPlansClient.class);
	
	@Autowired
	ServerurlConstants serverurlConstants;
	
	@Autowired
	LogDetails logDetails;
	
	
	public Long numberFound ;
	public List <Plans> fetchPlanDataByDateDesc(String query,int pageCount,int rows, HttpServletRequest request) {
		
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    try {	String url = serverurlConstants.ADD_PLANS_URL;
	    	SolrServer server =  Adder.getSolrServer(url);
		    ModifiableSolrParams params = new ModifiableSolrParams();
		    params.set("q", query);
		    params.set("sort", "StartDate desc");
		    params.set("start", ""+pageCount);
		    params.set("version", "2.2");
		    params.set("wt", "json");
		    params.set("indent", "on");
		    params.set("rows", rows);

		    QueryResponse response = server.query(params, SolrRequest.METHOD.POST);
		    numberFound = response.getResults().getNumFound();
		    
		    Date startDate=null;
		    Date endDate=null;
		    
		    List <Plans> plans = new ArrayList<Plans>();
	            for (SolrDocument updatesDoc : response.getResults())           {
	            	
	            	startDate=(Date)updatesDoc.getFieldValue("StartDate");
	            	endDate=(Date)updatesDoc.getFieldValue("EndDate");              	
	              	
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
	            	plan.setStartDate(dateFormat.format(startDate));
	            	plan.setEndDate(dateFormat.format(endDate));
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
	            	plan.setCreatedDate(updatesDoc.getFieldValue("CreatedDate").toString());
	            	plan.setUpdatedDate(updatesDoc.getFieldValue("UpdatedDate").toString());
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
	            	plan.setTotalMaybe(updatesDoc.getFieldValue("TotalMaybe").toString());
	            	plan.setTotalNo(updatesDoc.getFieldValue("TotalNo").toString());
	            	
	            	plan.setAccountProfileJson(updatesDoc.getFieldValue("AccountProfileJson").toString());
	            	
	            	plan.setTotalNotResponding(updatesDoc.getFieldValue("TotalNotResponding").toString());
	            	plan.setAccountProfileImage(updatesDoc.getFieldValue("AccountProfileImage").toString());
	            	plan.setAccountScreenName(updatesDoc.getFieldValue("AccountScreenName").toString());
	            	plans.add(plan);
	            }
	
	    return  plans;  
    } catch (SolrServerException e) {
    	logDetails.getException(e , logger , request);
        throw new DataAccessResourceFailureException(e.getMessage(), e);
    }
	}
	
	
}
