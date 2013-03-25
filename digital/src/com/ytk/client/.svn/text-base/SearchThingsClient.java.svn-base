package com.ytk.client;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.solr.client.solrj.SolrQuery;
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
import com.ytk.models.HeaderThings;
import com.ytk.models.Things;
import com.ytk.models.ThingsFilter;
import com.ytk.utility.Adder;
import com.ytk.utility.LogDetails;
import com.ytk.utility.ServerurlConstants;

@Component("searchThingsClient")
public class SearchThingsClient {

	private static final Logger logger = LoggerFactory.getLogger(SearchThingsClient.class);
	
	@Autowired
	ServerurlConstants serverurlConstants;
	
	@Autowired
	LogDetails logDetails;
	
	public  Object[]  queryCheck(String query,int pageCount,int rows, HttpServletRequest request) 
	{
		
		
	    try {   
	    	
	    	SolrServer server =  Adder.getSolrServer(serverurlConstants.ADD_THINGS_URL);
		    ModifiableSolrParams params = new ModifiableSolrParams();
		    params.set("q", query);
		    params.set("start", ""+pageCount);
		    params.set("version", "2.2");
		    params.set("wt", "json");
		    params.set("indent", "on");
		//    params.set("sort", "Name desc");
		    params.set("rows", rows);
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  
		    
		    String  DeletionDate=null;
		    String LastLoginDate=null;
			 String ActivationDate=null;
			 String UpdatedDate=null;
			 String CreatedDate=null;		    
			 String ReleaseDate=null;
		    
			 Date  deletionDate=null;
			 Date lastLoginDate=null;
			 Date activationDate=null;
			 Date updatedDate=null;
			 Date createdDate=null;		    
			 Date releaseDate=null;
			 
			 
		    QueryResponse response = server.query(params, SolrRequest.METHOD.POST);
		    List <Things> things = new ArrayList<Things>();
		    for (SolrDocument thingsDoc : response.getResults())
            {
		    	deletionDate=(Date)thingsDoc.getFieldValue("DeletionDate");
		    	lastLoginDate=(Date)thingsDoc.getFieldValue("LastLoginDate");
		    	activationDate=(Date)thingsDoc.getFieldValue("ActivationDate");
		    	updatedDate=(Date)thingsDoc.getFieldValue("UpdatedDate");
		    	createdDate=(Date)thingsDoc.getFieldValue("CreatedDate");
		    	releaseDate=(Date)thingsDoc.getFieldValue("ReleaseDate");
		    	
		    	DeletionDate= dateFormat.format(deletionDate);
		    	LastLoginDate= dateFormat.format(lastLoginDate);
		    	ActivationDate= dateFormat.format(activationDate);
		    	UpdatedDate= dateFormat.format(updatedDate);
		    	CreatedDate= dateFormat.format(createdDate);
		    	ReleaseDate= dateFormat.format(releaseDate);
		    	
		    	
		    	
		    	Things mythings = new Things();		    
		    	if(thingsDoc.getFieldValue("ID").toString()!=null)
		    	mythings.setID(thingsDoc.getFieldValue("ID").toString());	
		    	if(thingsDoc.getFieldValue("ScreenName")!=null)
				mythings.setScreenName(thingsDoc.getFieldValue("ScreenName").toString());
		    	if(thingsDoc.getFieldValue("ScreenNameStatus")!=null)
				mythings.setScreenNameStatus(thingsDoc.getFieldValue("ScreenNameStatus").toString());
		    	if(thingsDoc.getFieldValue("Name")!=null)
				mythings.setName(thingsDoc.getFieldValue("Name").toString());
		    	if(thingsDoc.getFieldValue("CategoryID")!=null)
				mythings.setCategoryID(thingsDoc.getFieldValue("CategoryID").toString());
		    	if(thingsDoc.getFieldValue("SubCategoryID")!=null)
				mythings.setSubCategoryID(thingsDoc.getFieldValue("SubCategoryID").toString());
		    	if(thingsDoc.getFieldValue("CategoryName")!=null)
				mythings.setCategoryName(thingsDoc.getFieldValue("CategoryName").toString());
		    	if(thingsDoc.getFieldValue("SubCategoryName")!=null)
				mythings.setSubCategoryName(thingsDoc.getFieldValue("SubCategoryName").toString());
		    	if(thingsDoc.getFieldValue("Address")!=null)
				mythings.setAddress(thingsDoc.getFieldValue("Address").toString());
		    	if(thingsDoc.getFieldValue("Email")!=null)
				mythings.setEmail(thingsDoc.getFieldValue("Email").toString());
		    	if(thingsDoc.getFieldValue("City")!=null)
				mythings.setCity(thingsDoc.getFieldValue("City").toString());
		    	if(thingsDoc.getFieldValue("State")!=null)
				mythings.setState(thingsDoc.getFieldValue("State").toString());
		    	if(thingsDoc.getFieldValue("Country")!=null)
				mythings.setCountry(thingsDoc.getFieldValue("Country").toString());
		    	if(thingsDoc.getFieldValue("CityID")!=null)
				mythings.setCityID(thingsDoc.getFieldValue("CityID").toString());
		    	if(thingsDoc.getFieldValue("StateID")!=null)
				mythings.setStateID(thingsDoc.getFieldValue("StateID").toString());
		    	if(thingsDoc.getFieldValue("CountryID")!=null)
				mythings.setCountryID(thingsDoc.getFieldValue("CountryID").toString());
		    	if(thingsDoc.getFieldValue("ZipCode")!=null)
				mythings.setZipCode(thingsDoc.getFieldValue("ZipCode").toString());
		    	if(thingsDoc.getFieldValue("Affiliation")!=null)
				mythings.setAffiliation(thingsDoc.getFieldValue("Affiliation").toString());
		    	if(thingsDoc.getFieldValue("About")!=null)
				mythings.setAbout(thingsDoc.getFieldValue("About").toString());
		    	if(thingsDoc.getFieldValue("Description")!=null)
				mythings.setDescription(thingsDoc.getFieldValue("Description").toString());
		    	if(thingsDoc.getFieldValue("Biography")!=null)
				mythings.setBiography(thingsDoc.getFieldValue("Biography").toString());
		    	if(thingsDoc.getFieldValue("Awards")!=null)
				mythings.setAwards(thingsDoc.getFieldValue("Awards").toString());
		    	if(thingsDoc.getFieldValue("Phone")!=null)
				mythings.setPhone(thingsDoc.getFieldValue("Phone").toString());
		    	if(thingsDoc.getFieldValue("Website")!=null)
				mythings.setWebsite(thingsDoc.getFieldValue("Website").toString());
		    	if(thingsDoc.getFieldValue("YouTube")!=null)
				mythings.setYouTube(thingsDoc.getFieldValue("YouTube").toString());
		    	if(ReleaseDate!=null)
				mythings.setReleaseDate(ReleaseDate);
		    	if(thingsDoc.getFieldValue("Genre")!=null)
				mythings.setGenre(thingsDoc.getFieldValue("Genre").toString());
		    	if(thingsDoc.getFieldValue("ISBN")!=null)
				mythings.setISBN(thingsDoc.getFieldValue("ISBN").toString());
		    	if(thingsDoc.getFieldValue("Publisher")!=null)
				mythings.setPublisher(thingsDoc.getFieldValue("Publisher").toString());
		    	if(thingsDoc.getFieldValue("Founded")!=null)
				mythings.setFounded(thingsDoc.getFieldValue("Founded").toString());
		    	if(thingsDoc.getFieldValue("Products")!=null)
				mythings.setProducts(thingsDoc.getFieldValue("Products").toString());
		    	if(thingsDoc.getFieldValue("CompanyOverview")!=null)
				mythings.setCompanyOverview(thingsDoc.getFieldValue("CompanyOverview").toString());
		    	if(thingsDoc.getFieldValue("Mission")!=null)
				mythings.setMission(thingsDoc.getFieldValue("Mission").toString());
		    	if(thingsDoc.getFieldValue("Built")!=null)
				mythings.setBuilt(thingsDoc.getFieldValue("Built").toString());
		    	if(thingsDoc.getFieldValue("Features")!=null)
				mythings.setFeatures(thingsDoc.getFieldValue("Features").toString());
		    	if(thingsDoc.getFieldValue("MPG")!=null)
				mythings.setMPG(thingsDoc.getFieldValue("MPG").toString());
		    	if(thingsDoc.getFieldValue("GeneralInformation")!=null)
				mythings.setGeneralInformation(thingsDoc.getFieldValue("GeneralInformation").toString());
		    	if(thingsDoc.getFieldValue("Studio")!=null)
				mythings.setStudio(thingsDoc.getFieldValue("Studio").toString());
		    	if(thingsDoc.getFieldValue("PlotOutline")!=null)
				mythings.setPlotOutline(thingsDoc.getFieldValue("PlotOutline").toString());
		    	if(thingsDoc.getFieldValue("Starring")!=null)
				mythings.setStarring(thingsDoc.getFieldValue("Starring").toString());
		    	if(thingsDoc.getFieldValue("DirectedBy")!=null)
				mythings.setDirectedBy(thingsDoc.getFieldValue("DirectedBy").toString());
		    	if(thingsDoc.getFieldValue("WrittenBy")!=null)
				mythings.setWrittenBy(thingsDoc.getFieldValue("WrittenBy").toString());
		    	if(thingsDoc.getFieldValue("ScreenplayBy")!=null)
				mythings.setScreenplayBy(thingsDoc.getFieldValue("ScreenplayBy").toString());
		    	if(thingsDoc.getFieldValue("ProducedBy")!=null)
				mythings.setProducedBy(thingsDoc.getFieldValue("ProducedBy").toString());
		    	if(thingsDoc.getFieldValue("Members")!=null)
				mythings.setMembers(thingsDoc.getFieldValue("Members").toString());
		    	if(thingsDoc.getFieldValue("RecordLabel")!=null)
				mythings.setRecordLabel(thingsDoc.getFieldValue("RecordLabel").toString());
		    	if(thingsDoc.getFieldValue("History")!=null)
				mythings.setHistory(thingsDoc.getFieldValue("History").toString());
		    	if(thingsDoc.getFieldValue("Network")!=null)
				mythings.setNetwork(thingsDoc.getFieldValue("Network").toString());
		    	if(thingsDoc.getFieldValue("Season")!=null)
				mythings.setSeason(thingsDoc.getFieldValue("Season").toString());
		    	if(thingsDoc.getFieldValue("ImageName")!=null)
				mythings.setImageName(thingsDoc.getFieldValue("ImageName").toString());
		    
			//	mythings.setHasImage(thingsDoc.getFieldValue("HasImage").toString());
				mythings.setCreatedDate(CreatedDate);
				mythings.setActivationDate(ActivationDate);
				mythings.setUpdatedDate(UpdatedDate);
				mythings.setDeletionDate(DeletionDate);
				mythings.setLastLoginDate(LastLoginDate);
			//	mythings.setIsActive(thingsDoc.getFieldValue("IsActive").toString());
			//	mythings.setIsDeleted(thingsDoc.getFieldValue("IsDeleted").toString());
				if(thingsDoc.getFieldValue("IsFeatured")!=null)
				mythings.setIsFeatured(thingsDoc.getFieldValue("IsFeatured").toString());
				if(thingsDoc.getFieldValue("IsCrawlerAllow")!=null)
				mythings.setIsCrawlerAllow(thingsDoc.getFieldValue("IsCrawlerAllow").toString());
				if(thingsDoc.getFieldValue("Role")!=null)
				mythings.setRole(thingsDoc.getFieldValue("Role").toString());
				if(thingsDoc.getFieldValue("OnlineStatus")!=null)
				mythings.setOnlineStatus(thingsDoc.getFieldValue("OnlineStatus").toString());
				if(thingsDoc.getFieldValue("WhoCanSeeInSearch")!=null)
				mythings.setWhoCanSeeInSearch(thingsDoc.getFieldValue("WhoCanSeeInSearch").toString());
				if(thingsDoc.getFieldValue("ShowWelcomePage")!=null)
				mythings.setShowWelcomePage(thingsDoc.getFieldValue("ShowWelcomePage").toString());
				if(thingsDoc.getFieldValue("ShowCompleteStatus")!=null)
				mythings.setShowCompleteStatus(thingsDoc.getFieldValue("ShowCompleteStatus").toString());
				if(thingsDoc.getFieldValue("IsFundermailSent")!=null)
				mythings.setIsFundermailSent(thingsDoc.getFieldValue("IsFundermailSent").toString());
				if(thingsDoc.getFieldValue("Status")!=null)
				mythings.setStatus(thingsDoc.getFieldValue("Status").toString());
				if(thingsDoc.getFieldValue("ProfileImage")!=null)
				mythings.setProfileImage(thingsDoc.getFieldValue("ProfileImage").toString());
				if(thingsDoc.getFieldValue("ProfileFileID")!=null)
				mythings.setProfileFileID(thingsDoc.getFieldValue("ProfileFileID").toString());
				
				if(thingsDoc.getFieldValue("OldWikiID")!=null)
				mythings.setOldWikiID(Integer.parseInt(thingsDoc.getFieldValue("OldWikiID").toString()));
				if(thingsDoc.getFieldValue("WikiInfo")!=null)
				mythings.setWikiInfo(thingsDoc.getFieldValue("WikiInfo").toString());
				if(thingsDoc.getFieldValue("RestrictAge")!=null)
				mythings.setRestrictAge(thingsDoc.getFieldValue("RestrictAge").toString());
				if(thingsDoc.getFieldValue("RestrictCountry")!=null)
				mythings.setRestrictCountry(thingsDoc.getFieldValue("RestrictCountry").toString());
				if(thingsDoc.getFieldValue("RestrictPost")!=null)
				mythings.setRestrictPost(thingsDoc.getFieldValue("RestrictPost").toString());
			//	mythings.setContactDB(thingsDoc.getFieldValue("ContactDB").toString());
			//	mythings.setUpdateDB(thingsDoc.getFieldValue("UpdateDB").toString());
			//	mythings.setMemberInfoDB(thingsDoc.getFieldValue("MemberInfoDB").toString());
			//	mythings.setCommentDB(thingsDoc.getFieldValue("CommentDB").toString());
			//	mythings.setMemberContentDB(thingsDoc.getFieldValue("MemberContentDB").toString());
			//	mythings.setMessageDB(thingsDoc.getFieldValue("MessageDB").toString());
			//	mythings.setFolderDB(thingsDoc.getFieldValue("FolderDB").toString());
		//		mythings.setNotificationSettingDB(thingsDoc.getFieldValue("NotificationSettingDB").toString());
		//		mythings.setSearchDB(thingsDoc.getFieldValue("SearchDB").toString());	    	
				things.add(mythings);
            }
		    
		    Object[] resultArr = new Object[2];
		    String   numFound = response.getResults().getNumFound()+"";
		    resultArr[0] = things;
            resultArr[1] = numFound;
            return resultArr;
	    } catch (SolrServerException e) {
	    	logDetails.getException(e , logger, request);
	        throw new DataAccessResourceFailureException(e.getMessage(), e);
	    }
	    
	   // return response;
	} 	
	
	
	
	
	public  Object[]  queryCheckNotWikiInfo(String query,int pageCount,int rows, HttpServletRequest request) 
	{
		
		
	    try {   
	    	
	    	SolrServer server =  Adder.getSolrServer(serverurlConstants.ADD_THINGS_URL);
		    ModifiableSolrParams params = new ModifiableSolrParams();
		    params.set("q", query);
		    params.set("start", ""+pageCount);
		    params.set("version", "2.2");
		    params.set("wt", "json");
		    params.set("indent", "on");
		    params.set("rows", rows);
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  
		    
		    String  DeletionDate=null;
		    String LastLoginDate=null;
			 String ActivationDate=null;
			 String UpdatedDate=null;
			 String CreatedDate=null;		    
			 String ReleaseDate=null;
		    
			 Date  deletionDate=null;
			 Date lastLoginDate=null;
			 Date activationDate=null;
			 Date updatedDate=null;
			 Date createdDate=null;		    
			 Date releaseDate=null;
			 
			 
		    QueryResponse response = server.query(params, SolrRequest.METHOD.POST);
		    List <Things> things = new ArrayList<Things>();
		    for (SolrDocument thingsDoc : response.getResults())
            {
		    	deletionDate=(Date)thingsDoc.getFieldValue("DeletionDate");
		    	lastLoginDate=(Date)thingsDoc.getFieldValue("LastLoginDate");
		    	activationDate=(Date)thingsDoc.getFieldValue("ActivationDate");
		    	updatedDate=(Date)thingsDoc.getFieldValue("UpdatedDate");
		    	createdDate=(Date)thingsDoc.getFieldValue("CreatedDate");
		    	releaseDate=(Date)thingsDoc.getFieldValue("ReleaseDate");
		    	
		    	DeletionDate= dateFormat.format(deletionDate);
		    	LastLoginDate= dateFormat.format(lastLoginDate);
		    	ActivationDate= dateFormat.format(activationDate);
		    	UpdatedDate= dateFormat.format(updatedDate);
		    	CreatedDate= dateFormat.format(createdDate);
		    	ReleaseDate= dateFormat.format(releaseDate);
		    	
		    	
		    	
		    	Things mythings = new Things();		    
		    	if(thingsDoc.getFieldValue("ID").toString()!=null)
		    	mythings.setID(thingsDoc.getFieldValue("ID").toString());	
		    	if(thingsDoc.getFieldValue("ScreenName")!=null)
				mythings.setScreenName(thingsDoc.getFieldValue("ScreenName").toString());
		    	if(thingsDoc.getFieldValue("ScreenNameStatus")!=null)
				mythings.setScreenNameStatus(thingsDoc.getFieldValue("ScreenNameStatus").toString());
		    	if(thingsDoc.getFieldValue("Name")!=null)
				mythings.setName(thingsDoc.getFieldValue("Name").toString());
		    	if(thingsDoc.getFieldValue("CategoryID")!=null)
				mythings.setCategoryID(thingsDoc.getFieldValue("CategoryID").toString());
		    	if(thingsDoc.getFieldValue("SubCategoryID")!=null)
				mythings.setSubCategoryID(thingsDoc.getFieldValue("SubCategoryID").toString());
		    	if(thingsDoc.getFieldValue("CategoryName")!=null)
				mythings.setCategoryName(thingsDoc.getFieldValue("CategoryName").toString());
		    	if(thingsDoc.getFieldValue("SubCategoryName")!=null)
				mythings.setSubCategoryName(thingsDoc.getFieldValue("SubCategoryName").toString());
		    	if(thingsDoc.getFieldValue("Address")!=null)
				mythings.setAddress(thingsDoc.getFieldValue("Address").toString());
		    	if(thingsDoc.getFieldValue("Email")!=null)
				mythings.setEmail(thingsDoc.getFieldValue("Email").toString());
		    	if(thingsDoc.getFieldValue("City")!=null)
				mythings.setCity(thingsDoc.getFieldValue("City").toString());
		    	if(thingsDoc.getFieldValue("State")!=null)
				mythings.setState(thingsDoc.getFieldValue("State").toString());
		    	if(thingsDoc.getFieldValue("Country")!=null)
				mythings.setCountry(thingsDoc.getFieldValue("Country").toString());
		    	if(thingsDoc.getFieldValue("CityID")!=null)
				mythings.setCityID(thingsDoc.getFieldValue("CityID").toString());
		    	if(thingsDoc.getFieldValue("StateID")!=null)
				mythings.setStateID(thingsDoc.getFieldValue("StateID").toString());
		    	if(thingsDoc.getFieldValue("CountryID")!=null)
				mythings.setCountryID(thingsDoc.getFieldValue("CountryID").toString());
		    	if(thingsDoc.getFieldValue("ZipCode")!=null)
				mythings.setZipCode(thingsDoc.getFieldValue("ZipCode").toString());
		    	if(thingsDoc.getFieldValue("Affiliation")!=null)
				mythings.setAffiliation(thingsDoc.getFieldValue("Affiliation").toString());
		    	if(thingsDoc.getFieldValue("About")!=null)
				mythings.setAbout(thingsDoc.getFieldValue("About").toString());
		    	if(thingsDoc.getFieldValue("Description")!=null)
				mythings.setDescription(thingsDoc.getFieldValue("Description").toString());
		    	if(thingsDoc.getFieldValue("Biography")!=null)
				mythings.setBiography(thingsDoc.getFieldValue("Biography").toString());
		    	if(thingsDoc.getFieldValue("Awards")!=null)
				mythings.setAwards(thingsDoc.getFieldValue("Awards").toString());
		    	if(thingsDoc.getFieldValue("Phone")!=null)
				mythings.setPhone(thingsDoc.getFieldValue("Phone").toString());
		    	if(thingsDoc.getFieldValue("Website")!=null)
				mythings.setWebsite(thingsDoc.getFieldValue("Website").toString());
		    	if(thingsDoc.getFieldValue("YouTube")!=null)
				mythings.setYouTube(thingsDoc.getFieldValue("YouTube").toString());
		    	if(ReleaseDate!=null)
				mythings.setReleaseDate(ReleaseDate);
		    	if(thingsDoc.getFieldValue("Genre")!=null)
				mythings.setGenre(thingsDoc.getFieldValue("Genre").toString());
		    	if(thingsDoc.getFieldValue("ISBN")!=null)
				mythings.setISBN(thingsDoc.getFieldValue("ISBN").toString());
		    	if(thingsDoc.getFieldValue("Publisher")!=null)
				mythings.setPublisher(thingsDoc.getFieldValue("Publisher").toString());
		    	if(thingsDoc.getFieldValue("Founded")!=null)
				mythings.setFounded(thingsDoc.getFieldValue("Founded").toString());
		    	if(thingsDoc.getFieldValue("Products")!=null)
				mythings.setProducts(thingsDoc.getFieldValue("Products").toString());
		    	if(thingsDoc.getFieldValue("CompanyOverview")!=null)
				mythings.setCompanyOverview(thingsDoc.getFieldValue("CompanyOverview").toString());
		    	if(thingsDoc.getFieldValue("Mission")!=null)
				mythings.setMission(thingsDoc.getFieldValue("Mission").toString());
		    	if(thingsDoc.getFieldValue("Built")!=null)
				mythings.setBuilt(thingsDoc.getFieldValue("Built").toString());
		    	if(thingsDoc.getFieldValue("Features")!=null)
				mythings.setFeatures(thingsDoc.getFieldValue("Features").toString());
		    	if(thingsDoc.getFieldValue("MPG")!=null)
				mythings.setMPG(thingsDoc.getFieldValue("MPG").toString());
		    	if(thingsDoc.getFieldValue("GeneralInformation")!=null)
				mythings.setGeneralInformation(thingsDoc.getFieldValue("GeneralInformation").toString());
		    	if(thingsDoc.getFieldValue("Studio")!=null)
				mythings.setStudio(thingsDoc.getFieldValue("Studio").toString());
		    	if(thingsDoc.getFieldValue("PlotOutline")!=null)
				mythings.setPlotOutline(thingsDoc.getFieldValue("PlotOutline").toString());
		    	if(thingsDoc.getFieldValue("Starring")!=null)
				mythings.setStarring(thingsDoc.getFieldValue("Starring").toString());
		    	if(thingsDoc.getFieldValue("DirectedBy")!=null)
				mythings.setDirectedBy(thingsDoc.getFieldValue("DirectedBy").toString());
		    	if(thingsDoc.getFieldValue("WrittenBy")!=null)
				mythings.setWrittenBy(thingsDoc.getFieldValue("WrittenBy").toString());
		    	if(thingsDoc.getFieldValue("ScreenplayBy")!=null)
				mythings.setScreenplayBy(thingsDoc.getFieldValue("ScreenplayBy").toString());
		    	if(thingsDoc.getFieldValue("ProducedBy")!=null)
				mythings.setProducedBy(thingsDoc.getFieldValue("ProducedBy").toString());
		    	if(thingsDoc.getFieldValue("Members")!=null)
				mythings.setMembers(thingsDoc.getFieldValue("Members").toString());
		    	if(thingsDoc.getFieldValue("RecordLabel")!=null)
				mythings.setRecordLabel(thingsDoc.getFieldValue("RecordLabel").toString());
		    	if(thingsDoc.getFieldValue("History")!=null)
				mythings.setHistory(thingsDoc.getFieldValue("History").toString());
		    	if(thingsDoc.getFieldValue("Network")!=null)
				mythings.setNetwork(thingsDoc.getFieldValue("Network").toString());
		    	if(thingsDoc.getFieldValue("Season")!=null)
				mythings.setSeason(thingsDoc.getFieldValue("Season").toString());
		    	if(thingsDoc.getFieldValue("ImageName")!=null)
				mythings.setImageName(thingsDoc.getFieldValue("ImageName").toString());
		    
			//	mythings.setHasImage(thingsDoc.getFieldValue("HasImage").toString());
				mythings.setCreatedDate(CreatedDate);
				mythings.setActivationDate(ActivationDate);
				mythings.setUpdatedDate(UpdatedDate);
				mythings.setDeletionDate(DeletionDate);
				mythings.setLastLoginDate(LastLoginDate);
			//	mythings.setIsActive(thingsDoc.getFieldValue("IsActive").toString());
			//	mythings.setIsDeleted(thingsDoc.getFieldValue("IsDeleted").toString());
				if(thingsDoc.getFieldValue("IsFeatured")!=null)
				mythings.setIsFeatured(thingsDoc.getFieldValue("IsFeatured").toString());
				if(thingsDoc.getFieldValue("IsCrawlerAllow")!=null)
				mythings.setIsCrawlerAllow(thingsDoc.getFieldValue("IsCrawlerAllow").toString());
				if(thingsDoc.getFieldValue("Role")!=null)
				mythings.setRole(thingsDoc.getFieldValue("Role").toString());
				if(thingsDoc.getFieldValue("OnlineStatus")!=null)
				mythings.setOnlineStatus(thingsDoc.getFieldValue("OnlineStatus").toString());
				if(thingsDoc.getFieldValue("WhoCanSeeInSearch")!=null)
				mythings.setWhoCanSeeInSearch(thingsDoc.getFieldValue("WhoCanSeeInSearch").toString());
				if(thingsDoc.getFieldValue("ShowWelcomePage")!=null)
				mythings.setShowWelcomePage(thingsDoc.getFieldValue("ShowWelcomePage").toString());
				if(thingsDoc.getFieldValue("ShowCompleteStatus")!=null)
				mythings.setShowCompleteStatus(thingsDoc.getFieldValue("ShowCompleteStatus").toString());
				if(thingsDoc.getFieldValue("IsFundermailSent")!=null)
				mythings.setIsFundermailSent(thingsDoc.getFieldValue("IsFundermailSent").toString());
				if(thingsDoc.getFieldValue("Status")!=null)
				mythings.setStatus(thingsDoc.getFieldValue("Status").toString());
				if(thingsDoc.getFieldValue("ProfileImage")!=null)
				mythings.setProfileImage(thingsDoc.getFieldValue("ProfileImage").toString());
				if(thingsDoc.getFieldValue("ProfileFileID")!=null)
				mythings.setProfileFileID(thingsDoc.getFieldValue("ProfileFileID").toString());
				
				if(thingsDoc.getFieldValue("OldWikiID")!=null)
				mythings.setOldWikiID(Integer.parseInt(thingsDoc.getFieldValue("OldWikiID").toString()));
			//	if(thingsDoc.getFieldValue("WikiInfo")!=null)
			//	mythings.setWikiInfo(thingsDoc.getFieldValue("WikiInfo").toString());
				if(thingsDoc.getFieldValue("RestrictAge")!=null)
				mythings.setRestrictAge(thingsDoc.getFieldValue("RestrictAge").toString());
				if(thingsDoc.getFieldValue("RestrictCountry")!=null)
				mythings.setRestrictCountry(thingsDoc.getFieldValue("RestrictCountry").toString());
				if(thingsDoc.getFieldValue("RestrictPost")!=null)
				mythings.setRestrictPost(thingsDoc.getFieldValue("RestrictPost").toString());
			//	mythings.setContactDB(thingsDoc.getFieldValue("ContactDB").toString());
			//	mythings.setUpdateDB(thingsDoc.getFieldValue("UpdateDB").toString());
			//	mythings.setMemberInfoDB(thingsDoc.getFieldValue("MemberInfoDB").toString());
			//	mythings.setCommentDB(thingsDoc.getFieldValue("CommentDB").toString());
			//	mythings.setMemberContentDB(thingsDoc.getFieldValue("MemberContentDB").toString());
			//	mythings.setMessageDB(thingsDoc.getFieldValue("MessageDB").toString());
			//	mythings.setFolderDB(thingsDoc.getFieldValue("FolderDB").toString());
		//		mythings.setNotificationSettingDB(thingsDoc.getFieldValue("NotificationSettingDB").toString());
		//		mythings.setSearchDB(thingsDoc.getFieldValue("SearchDB").toString());	    	
				things.add(mythings);
            }
		    
		    Object[] resultArr = new Object[2];
		    String   numFound = response.getResults().getNumFound()+"";
		    resultArr[0] = things;
            resultArr[1] = numFound;
            return resultArr;
	    } catch (SolrServerException e) {
	    	logDetails.getException(e , logger , request);
	        throw new DataAccessResourceFailureException(e.getMessage(), e);
	    }
	    
	   // return response;
	} 	
	
	
	
	
	public  Object[] updateThings(String query, HttpServletRequest request) {
		SolrQuery solrQuery = null;
		String rows="1";
		 try {   
		    	
		    	SolrServer server =  Adder.getSolrServer(serverurlConstants.ADD_THINGS_URL);
			    ModifiableSolrParams params = new ModifiableSolrParams();
			    params.set("q", query);
			    
			    params.set("version", "2.2");
			    params.set("wt", "json");
			    params.set("indent", "on");
			    params.set("rows", rows);
			    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			  
			    
			    String  DeletionDate=null;
			    String LastLoginDate=null;
				 String ActivationDate=null;
				 String UpdatedDate=null;
				 String CreatedDate=null;		    
				 String ReleaseDate=null;
			    
				 Date  deletionDate=null;
				 Date lastLoginDate=null;
				 Date activationDate=null;
				 Date updatedDate=null;
				 Date createdDate=null;		    
				 Date releaseDate=null;
				 
				 
			    QueryResponse response = server.query(params, SolrRequest.METHOD.POST);
			    List <Things> things = new ArrayList<Things>();
			    for (SolrDocument thingsDoc : response.getResults())
	            {
			    	deletionDate=(Date)thingsDoc.getFieldValue("DeletionDate");
			    	lastLoginDate=(Date)thingsDoc.getFieldValue("LastLoginDate");
			    	activationDate=(Date)thingsDoc.getFieldValue("ActivationDate");
			    	updatedDate=(Date)thingsDoc.getFieldValue("UpdatedDate");
			    	createdDate=(Date)thingsDoc.getFieldValue("CreatedDate");
			    	releaseDate=(Date)thingsDoc.getFieldValue("ReleaseDate");
			    	
			    	DeletionDate= dateFormat.format(deletionDate);
			    	LastLoginDate= dateFormat.format(lastLoginDate);
			    	ActivationDate= dateFormat.format(activationDate);
			    	UpdatedDate= dateFormat.format(updatedDate);
			    	CreatedDate= dateFormat.format(createdDate);
			    	ReleaseDate= dateFormat.format(releaseDate);
			    	
			    	
			    	
			    	Things mythings = new Things();		    
			    	if(thingsDoc.getFieldValue("ID").toString()!=null)
			    	mythings.setID(thingsDoc.getFieldValue("ID").toString());	
			    	if(thingsDoc.getFieldValue("ScreenName")!=null)
					mythings.setScreenName(thingsDoc.getFieldValue("ScreenName").toString());
			    	if(thingsDoc.getFieldValue("ScreenNameStatus")!=null)
					mythings.setScreenNameStatus(thingsDoc.getFieldValue("ScreenNameStatus").toString());
			    	if(thingsDoc.getFieldValue("Name")!=null)
					mythings.setName(thingsDoc.getFieldValue("Name").toString());
			    	if(thingsDoc.getFieldValue("CategoryID")!=null)
					mythings.setCategoryID(thingsDoc.getFieldValue("CategoryID").toString());
			    	if(thingsDoc.getFieldValue("SubCategoryID")!=null)
					mythings.setSubCategoryID(thingsDoc.getFieldValue("SubCategoryID").toString());
			    	if(thingsDoc.getFieldValue("CategoryName")!=null)
					mythings.setCategoryName(thingsDoc.getFieldValue("CategoryName").toString());
			    	if(thingsDoc.getFieldValue("SubCategoryName")!=null)
					mythings.setSubCategoryName(thingsDoc.getFieldValue("SubCategoryName").toString());
			    	if(thingsDoc.getFieldValue("Address")!=null)
					mythings.setAddress(thingsDoc.getFieldValue("Address").toString());
			    	if(thingsDoc.getFieldValue("Email")!=null)
					mythings.setEmail(thingsDoc.getFieldValue("Email").toString());
			    	if(thingsDoc.getFieldValue("City")!=null)
					mythings.setCity(thingsDoc.getFieldValue("City").toString());
			    	if(thingsDoc.getFieldValue("State")!=null)
					mythings.setState(thingsDoc.getFieldValue("State").toString());
			    	if(thingsDoc.getFieldValue("Country")!=null)
					mythings.setCountry(thingsDoc.getFieldValue("Country").toString());
			    	if(thingsDoc.getFieldValue("CityID")!=null)
					mythings.setCityID(thingsDoc.getFieldValue("CityID").toString());
			    	if(thingsDoc.getFieldValue("StateID")!=null)
					mythings.setStateID(thingsDoc.getFieldValue("StateID").toString());
			    	if(thingsDoc.getFieldValue("CountryID")!=null)
					mythings.setCountryID(thingsDoc.getFieldValue("CountryID").toString());
			    	if(thingsDoc.getFieldValue("ZipCode")!=null)
					mythings.setZipCode(thingsDoc.getFieldValue("ZipCode").toString());
			    	if(thingsDoc.getFieldValue("Affiliation")!=null)
					mythings.setAffiliation(thingsDoc.getFieldValue("Affiliation").toString());
			    	if(thingsDoc.getFieldValue("About")!=null)
					mythings.setAbout(thingsDoc.getFieldValue("About").toString());
			    	if(thingsDoc.getFieldValue("Description")!=null)
					mythings.setDescription(thingsDoc.getFieldValue("Description").toString());
			    	if(thingsDoc.getFieldValue("Biography")!=null)
					mythings.setBiography(thingsDoc.getFieldValue("Biography").toString());
			    	if(thingsDoc.getFieldValue("Awards")!=null)
					mythings.setAwards(thingsDoc.getFieldValue("Awards").toString());
			    	if(thingsDoc.getFieldValue("Phone")!=null)
					mythings.setPhone(thingsDoc.getFieldValue("Phone").toString());
			    	if(thingsDoc.getFieldValue("Website")!=null)
					mythings.setWebsite(thingsDoc.getFieldValue("Website").toString());
			    	if(thingsDoc.getFieldValue("YouTube")!=null)
					mythings.setYouTube(thingsDoc.getFieldValue("YouTube").toString());
			    	if(ReleaseDate!=null)
					mythings.setReleaseDate(ReleaseDate);
			    	if(thingsDoc.getFieldValue("Genre")!=null)
					mythings.setGenre(thingsDoc.getFieldValue("Genre").toString());
			    	if(thingsDoc.getFieldValue("ISBN")!=null)
					mythings.setISBN(thingsDoc.getFieldValue("ISBN").toString());
			    	if(thingsDoc.getFieldValue("Publisher")!=null)
					mythings.setPublisher(thingsDoc.getFieldValue("Publisher").toString());
			    	if(thingsDoc.getFieldValue("Founded")!=null)
					mythings.setFounded(thingsDoc.getFieldValue("Founded").toString());
			    	if(thingsDoc.getFieldValue("Products")!=null)
					mythings.setProducts(thingsDoc.getFieldValue("Products").toString());
			    	if(thingsDoc.getFieldValue("CompanyOverview")!=null)
					mythings.setCompanyOverview(thingsDoc.getFieldValue("CompanyOverview").toString());
			    	if(thingsDoc.getFieldValue("Mission")!=null)
					mythings.setMission(thingsDoc.getFieldValue("Mission").toString());
			    	if(thingsDoc.getFieldValue("Built")!=null)
					mythings.setBuilt(thingsDoc.getFieldValue("Built").toString());
			    	if(thingsDoc.getFieldValue("Features")!=null)
					mythings.setFeatures(thingsDoc.getFieldValue("Features").toString());
			    	if(thingsDoc.getFieldValue("MPG")!=null)
					mythings.setMPG(thingsDoc.getFieldValue("MPG").toString());
			    	if(thingsDoc.getFieldValue("GeneralInformation")!=null)
					mythings.setGeneralInformation(thingsDoc.getFieldValue("GeneralInformation").toString());
			    	if(thingsDoc.getFieldValue("Studio")!=null)
					mythings.setStudio(thingsDoc.getFieldValue("Studio").toString());
			    	if(thingsDoc.getFieldValue("PlotOutline")!=null)
					mythings.setPlotOutline(thingsDoc.getFieldValue("PlotOutline").toString());
			    	if(thingsDoc.getFieldValue("Starring")!=null)
					mythings.setStarring(thingsDoc.getFieldValue("Starring").toString());
			    	if(thingsDoc.getFieldValue("DirectedBy")!=null)
					mythings.setDirectedBy(thingsDoc.getFieldValue("DirectedBy").toString());
			    	if(thingsDoc.getFieldValue("WrittenBy")!=null)
					mythings.setWrittenBy(thingsDoc.getFieldValue("WrittenBy").toString());
			    	if(thingsDoc.getFieldValue("ScreenplayBy")!=null)
					mythings.setScreenplayBy(thingsDoc.getFieldValue("ScreenplayBy").toString());
			    	if(thingsDoc.getFieldValue("ProducedBy")!=null)
					mythings.setProducedBy(thingsDoc.getFieldValue("ProducedBy").toString());
			    	if(thingsDoc.getFieldValue("Members")!=null)
					mythings.setMembers(thingsDoc.getFieldValue("Members").toString());
			    	if(thingsDoc.getFieldValue("RecordLabel")!=null)
					mythings.setRecordLabel(thingsDoc.getFieldValue("RecordLabel").toString());
			    	if(thingsDoc.getFieldValue("History")!=null)
					mythings.setHistory(thingsDoc.getFieldValue("History").toString());
			    	if(thingsDoc.getFieldValue("Network")!=null)
					mythings.setNetwork(thingsDoc.getFieldValue("Network").toString());
			    	if(thingsDoc.getFieldValue("Season")!=null)
					mythings.setSeason(thingsDoc.getFieldValue("Season").toString());
			    	if(thingsDoc.getFieldValue("ImageName")!=null)
					mythings.setImageName(thingsDoc.getFieldValue("ImageName").toString());
			    
				//	mythings.setHasImage(thingsDoc.getFieldValue("HasImage").toString());
					mythings.setCreatedDate(CreatedDate);
					mythings.setActivationDate(ActivationDate);
					mythings.setUpdatedDate(UpdatedDate);
					mythings.setDeletionDate(DeletionDate);
					mythings.setLastLoginDate(LastLoginDate);
				//	mythings.setIsActive(thingsDoc.getFieldValue("IsActive").toString());
				//	mythings.setIsDeleted(thingsDoc.getFieldValue("IsDeleted").toString());
					if(thingsDoc.getFieldValue("IsFeatured")!=null)
					mythings.setIsFeatured(thingsDoc.getFieldValue("IsFeatured").toString());
					if(thingsDoc.getFieldValue("IsCrawlerAllow")!=null)
					mythings.setIsCrawlerAllow(thingsDoc.getFieldValue("IsCrawlerAllow").toString());
					if(thingsDoc.getFieldValue("Role")!=null)
					mythings.setRole(thingsDoc.getFieldValue("Role").toString());
					if(thingsDoc.getFieldValue("OnlineStatus")!=null)
					mythings.setOnlineStatus(thingsDoc.getFieldValue("OnlineStatus").toString());
					if(thingsDoc.getFieldValue("WhoCanSeeInSearch")!=null)
					mythings.setWhoCanSeeInSearch(thingsDoc.getFieldValue("WhoCanSeeInSearch").toString());
					if(thingsDoc.getFieldValue("ShowWelcomePage")!=null)
					mythings.setShowWelcomePage(thingsDoc.getFieldValue("ShowWelcomePage").toString());
					if(thingsDoc.getFieldValue("ShowCompleteStatus")!=null)
					mythings.setShowCompleteStatus(thingsDoc.getFieldValue("ShowCompleteStatus").toString());
					if(thingsDoc.getFieldValue("IsFundermailSent")!=null)
					mythings.setIsFundermailSent(thingsDoc.getFieldValue("IsFundermailSent").toString());
					if(thingsDoc.getFieldValue("Status")!=null)
					mythings.setStatus(thingsDoc.getFieldValue("Status").toString());
					if(thingsDoc.getFieldValue("ProfileImage")!=null)
					mythings.setProfileImage(thingsDoc.getFieldValue("ProfileImage").toString());
					if(thingsDoc.getFieldValue("ProfileFileID")!=null)
					mythings.setProfileFileID(thingsDoc.getFieldValue("ProfileFileID").toString());
					
					if(thingsDoc.getFieldValue("OldWikiID")!=null)
					mythings.setOldWikiID(Integer.parseInt(thingsDoc.getFieldValue("OldWikiID").toString()));
				//	if(thingsDoc.getFieldValue("WikiInfo")!=null)
				//	mythings.setWikiInfo(thingsDoc.getFieldValue("WikiInfo").toString());
					if(thingsDoc.getFieldValue("RestrictAge")!=null)
					mythings.setRestrictAge(thingsDoc.getFieldValue("RestrictAge").toString());
					if(thingsDoc.getFieldValue("RestrictCountry")!=null)
					mythings.setRestrictCountry(thingsDoc.getFieldValue("RestrictCountry").toString());
					if(thingsDoc.getFieldValue("RestrictPost")!=null)
					mythings.setRestrictPost(thingsDoc.getFieldValue("RestrictPost").toString());
				//	mythings.setContactDB(thingsDoc.getFieldValue("ContactDB").toString());
				//	mythings.setUpdateDB(thingsDoc.getFieldValue("UpdateDB").toString());
				//	mythings.setMemberInfoDB(thingsDoc.getFieldValue("MemberInfoDB").toString());
				//	mythings.setCommentDB(thingsDoc.getFieldValue("CommentDB").toString());
				//	mythings.setMemberContentDB(thingsDoc.getFieldValue("MemberContentDB").toString());
				//	mythings.setMessageDB(thingsDoc.getFieldValue("MessageDB").toString());
				//	mythings.setFolderDB(thingsDoc.getFieldValue("FolderDB").toString());
			//		mythings.setNotificationSettingDB(thingsDoc.getFieldValue("NotificationSettingDB").toString());
			//		mythings.setSearchDB(thingsDoc.getFieldValue("SearchDB").toString());	    	
					things.add(mythings);
	            }
			    
			    Object[] resultArr = new Object[2];
			    String   numFound = response.getResults().getNumFound()+"";
			    resultArr[0] = things;
	            resultArr[1] = numFound;
	            return resultArr;
		    } catch (SolrServerException e) {
		    	logDetails.getException(e , logger , request);
		        throw new DataAccessResourceFailureException(e.getMessage(), e);
		    }
	   // return response;
	} 	
	
	
	
	
	
	
	
	public  Object[]  thingsHeaderSearch(String query,int pageCount,int rows , HttpServletRequest request) 
	{
		try {
	    	SolrServer server =  Adder.getSolrServer(serverurlConstants.ADD_THINGS_URL);
		    ModifiableSolrParams params = new ModifiableSolrParams();
		    params.set("q", query);
		    params.set("start", ""+pageCount);
		    params.set("fl", "ID , ScreenName , ProfileImage , CategoryName , SubCategoryName , Name , ProfileFileJson");
		    params.set("version", "2.2");
		    params.set("wt", "json");
		    params.set("indent", "on");
		    params.set("rows", rows);
		    QueryResponse response = server.query(params, SolrRequest.METHOD.POST);
		    List <HeaderThings> things = new ArrayList<HeaderThings>();
		    for (SolrDocument thingsDoc : response.getResults())
            {	
		    	HeaderThings mythings = new HeaderThings();		    
		    	if(thingsDoc.getFieldValue("ID").toString()!=null)
		    	mythings.setID(thingsDoc.getFieldValue("ID").toString());	
		    	if(thingsDoc.getFieldValue("ScreenName")!=null)
				mythings.setScreenName(thingsDoc.getFieldValue("ScreenName").toString());
		    	if(thingsDoc.getFieldValue("ProfileImage")!=null)
				mythings.setProfileImage(thingsDoc.getFieldValue("ProfileImage").toString());
		    	if(thingsDoc.getFieldValue("CategoryName")!=null)
				mythings.setCategoryName(thingsDoc.getFieldValue("CategoryName").toString());
		    	if(thingsDoc.getFieldValue("SubCategoryName")!=null)
				mythings.setSubCategoryName(thingsDoc.getFieldValue("SubCategoryName").toString());
		    	if(thingsDoc.getFieldValue("ProfileFileJson")!=null)
				mythings.setProfileFileJson(thingsDoc.getFieldValue("ProfileFileJson").toString());
		    	if(thingsDoc.getFieldValue("Name")!=null)
				mythings.setName(thingsDoc.getFieldValue("Name").toString());
		    	things.add(mythings);
            }
		    
		    Object[] resultArr = new Object[2];
		    String   numFound = response.getResults().getNumFound()+"";
		    resultArr[0] = things;
            resultArr[1] = numFound;
            return resultArr;
	    } catch (SolrServerException e) {
	    	logDetails.getException(e , logger , request);
	        throw new DataAccessResourceFailureException(e.getMessage(), e);
	    }
	    
	   // return response;
	} 	
	
	
	
	
	
	public  Object[]  findthingsbycategory(String query,int pageCount,int rows , HttpServletRequest request) 
	{
		try {
	    	SolrServer server =  Adder.getSolrServer(serverurlConstants.ADD_THINGS_URL);
		    ModifiableSolrParams params = new ModifiableSolrParams();
		    params.set("q", query);
		    params.set("start", ""+pageCount);
		    params.set("fl", "ID , Name , CategoryName , CategoryID , ScreenName , ProfileFileJson");
		    params.set("version", "2.2");
		    params.set("wt", "json");
		    params.set("indent", "on");
		    params.set("rows", rows);
		    QueryResponse response = server.query(params, SolrRequest.METHOD.POST);
		    List <ThingsFilter> things = new ArrayList<ThingsFilter>();
		    for (SolrDocument thingsDoc : response.getResults())
            {	
		    	ThingsFilter mythings = new ThingsFilter();		    
		    	if(thingsDoc.getFieldValue("ID").toString()!=null)
		    	mythings.setID(thingsDoc.getFieldValue("ID").toString());	
		    	
		    	if(thingsDoc.getFieldValue("Name")!=null)
					mythings.setName(thingsDoc.getFieldValue("Name").toString());
		    	
		    
		    	if(thingsDoc.getFieldValue("CategoryName")!=null)
					mythings.setCategoryName(thingsDoc.getFieldValue("CategoryName").toString());
		    
		    	if(thingsDoc.getFieldValue("CategoryID")!=null)
					mythings.setCategoryID(thingsDoc.getFieldValue("CategoryID").toString());
		    
		    	
		    	
		    	if(thingsDoc.getFieldValue("ScreenName")!=null)
				mythings.setScreenName(thingsDoc.getFieldValue("ScreenName").toString());
		    	
		    	if(thingsDoc.getFieldValue("ProfileFileJson")!=null)
				mythings.setProfileFileJson(thingsDoc.getFieldValue("ProfileFileJson").toString());
		    	
		    	things.add(mythings);
            }
		    
		    Object[] resultArr = new Object[2];
		    String   numFound = response.getResults().getNumFound()+"";
		    resultArr[0] = things;
            resultArr[1] = numFound;
            return resultArr;
	    } catch (SolrServerException e) {
	    	logDetails.getException(e , logger , request);
	        throw new DataAccessResourceFailureException(e.getMessage(), e);
	    }
	    
	   // return response;
	} 	
	
	
	
	
	
	
	
}
