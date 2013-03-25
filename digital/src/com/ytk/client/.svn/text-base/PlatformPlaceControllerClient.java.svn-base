package com.ytk.client;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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

import com.ytk.models.Place;
import com.ytk.models.PlaceFilter;
import com.ytk.models.PlaceReview;
import com.ytk.utility.Adder;
import com.ytk.utility.LogDetails;
import com.ytk.utility.ServerurlConstants;
import com.ytk.models.HeaderPlace;;




@Component("platformPlaceControllerClient")
public class PlatformPlaceControllerClient {
	
	private static final Logger logger = LoggerFactory.getLogger(PlatformPlaceControllerClient.class);
	
	@Autowired
	ServerurlConstants serverurlConstants;
	
	@Autowired
	LogDetails logDetails;
	
	public  Object[] queryCheck(String query,int pageCount,int rows ,HttpServletRequest request )
	{
	    try 
	    {
	    	
	    	SolrServer server =  Adder.getSolrServer(serverurlConstants.ADD_PLACES_URL);
		    ModifiableSolrParams params = new ModifiableSolrParams();
		    params.set("q", query);
		    params.set("start", ""+pageCount);
		    params.set("version", "2.2");
		    params.set("wt", "json");
		 //   params.set("sort", "Name desc");
		    params.set("indent", "on");
		    params.set("rows", ""+rows);
		   
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   
		    String CreatedDate = null;
			 String ApprovalDate = null;
			 String UpdatedDate = null;
			 String DeletionDate = null;
			 String LastLoginDate = null;
			
			 
			 Date createdDate=null;
			 Date approvalDate=null;
			 Date updatedDate=null;
			 Date deletionDate=null;
		     Date lastLoginDate=null;
			
		    Object[] resultArr = new Object[2];
		    QueryResponse response = server.query(params, SolrRequest.METHOD.POST);
		    String   numFound = response.getResults().getNumFound()+"";
		    List <Place> places = new ArrayList<Place>();
	            for (SolrDocument doc : response.getResults())
	            {	
	            	
	            	 dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
	            	 createdDate=(Date)doc.getFieldValue("CreatedDate");
	            	 approvalDate=(Date)doc.getFieldValue("ApprovalDate");
	            	 updatedDate=(Date)doc.getFieldValue("UpdatedDate");
	            	 deletionDate=(Date)doc.getFieldValue("DeletionDate");
	            	 lastLoginDate=(Date)doc.getFieldValue("LastLoginDate");
	            	 
	    			Place place = new Place();
	            	place.setID(doc.getFieldValue("ID").toString());	            
	            	place.setCategory1ID(doc.getFieldValue("Category1ID").toString());	            	
	            	place.setCategory1ID(doc.getFieldValue("Category1ID").toString());
	            	place.setCategory2ID(doc.getFieldValue("Category2ID").toString());
	            	place.setCategory3ID(doc.getFieldValue("Category3ID").toString());
	            	place.setSubCategory1ID(doc.getFieldValue("SubCategory1ID").toString());
	            	place.setSubCategory2ID(doc.getFieldValue("SubCategory2ID").toString());
	            	place.setSubCategory3ID(doc.getFieldValue("SubCategory3ID").toString());
	            	place.setCategory1Name(doc.getFieldValue("Category1Name").toString());
	            	place.setSubCategory2ID(doc.getFieldValue("SubCategory2ID").toString());
	            	place.setCategory2Name(doc.getFieldValue("Category2Name").toString());	            	
	            	place.setCategory3Name(doc.getFieldValue("Category3Name").toString());
	            	place.setSubCategory1Name(doc.getFieldValue("SubCategory1Name").toString());
	            	place.setSubCategory2Name(doc.getFieldValue("SubCategory2Name").toString());
	            	place.setSubCategory3Name(doc.getFieldValue("SubCategory3Name").toString());
	             	place.setName(doc.getFieldValue("Name").toString());	  
	             	place.setScreenName(doc.getFieldValue("ScreenName").toString());	
	             	place.setScreenNameStatus(doc.getFieldValue("ScreenNameStatus").toString());
	             	place.setProfileImage(doc.getFieldValue("ProfileImage").toString());
	             	place.setProfileFileID(doc.getFieldValue("ProfileFileID").toString());
	             	place.setEmail(doc.getFieldValue("Email").toString());	
	             	place.setAddress1(doc.getFieldValue("Address1").toString());	
	             	place.setAddress2(doc.getFieldValue("Address2").toString());
	             	place.setCity(doc.getFieldValue("City").toString());
	             	place.setState(doc.getFieldValue("State").toString());	
	             	place.setCountry(doc.getFieldValue("Country").toString());
	             	place.setCityID(doc.getFieldValue("CityID").toString());
	             	place.setStateID(doc.getFieldValue("StateID").toString());
	             	place.setCountryID(doc.getFieldValue("CountryID").toString());	
	             	place.setZipCode(doc.getFieldValue("ZipCode").toString());
	             	place.setPhone(doc.getFieldValue("Phone").toString());	
	             	place.setWebAddress(doc.getFieldValue("WebAddress").toString());
	             	place.setBlog(doc.getFieldValue("Blog").toString());	
	             	place.setNeighbourHoods(doc.getFieldValue("NeighbourHoods").toString());	
	             	place.setNearestTransits(doc.getFieldValue("NearestTransits").toString());
	             	place.setRating(doc.getFieldValue("Rating").toString());	
	             	place.setTotalReviews(doc.getFieldValue("TotalReviews").toString());	
	             	place.setDescription(doc.getFieldValue("Description").toString());
	             	place.setProducts(doc.getFieldValue("Products").toString());	
	            	place.setPolicy(doc.getFieldValue("Policy").toString());	
	              	place.setRole(doc.getFieldValue("Role").toString());	
	              	place.setPriceRange(doc.getFieldValue("PriceRange").toString());
	              	place.setAcceptsCreditCards(doc.getFieldValue("AcceptsCreditCards").toString());
	              	place.setWheelChairAccessible(doc.getFieldValue("WheelChairAccessible").toString());
	            	place.setGoodForKids(doc.getFieldValue("GoodForKids").toString());
	            	place.setGoodForGroups(doc.getFieldValue("GoodForGroups").toString());
	            	place.setWaiterService(doc.getFieldValue("WaiterService").toString());
	            	place.setAttire(doc.getFieldValue("Attire").toString());
	            	place.setOutdoorSeating(doc.getFieldValue("OutdoorSeating").toString());
	            //	place.setAlcohol(doc.getFieldValue("Alcohol").toString());
	            	place.setTakeOut(doc.getFieldValue("TakeOut").toString());
	            	place.setTakesReservation(doc.getFieldValue("TakesReservation").toString());
	            	place.setByAppointmentOnly(doc.getFieldValue("ByAppointmentOnly").toString());
	            	place.setMusic(doc.getFieldValue("Music").toString());
	            	place.setBestNights(doc.getFieldValue("BestNights").toString());
	            	place.setSmoking(doc.getFieldValue("Smoking").toString());
	            	place.setCoatCheck(doc.getFieldValue("CoatCheck").toString());
	            	place.setHappyHour(doc.getFieldValue("HappyHour").toString());
	            	place.setWiFi(doc.getFieldValue("WiFi").toString());
	            	place.setIsParkingStreet(doc.getFieldValue("IsParkingStreet").toString());
	            	place.setIsParkingGarage(doc.getFieldValue("IsParkingGarage").toString());
	            	place.setIsParkingValet(doc.getFieldValue("IsParkingValet").toString());
	            	place.setIsParkingPrivateLot(doc.getFieldValue("IsParkingPrivateLot").toString());
	            	place.setIsParkingValidated(doc.getFieldValue("IsParkingValidated").toString());
	            	place.setIsMealBreakfast(doc.getFieldValue("IsMealBreakfast").toString());
	            	place.setIsMealBrunch(doc.getFieldValue("IsMealBrunch").toString());
	            	place.setIsMealLunch(doc.getFieldValue("IsMealLunch").toString());
	            	place.setIsMealDinner(doc.getFieldValue("IsMealDinner").toString());
	            	place.setIsMealLateNight(doc.getFieldValue("IsMealLateNight").toString());
	            	place.setIsMealDessert(doc.getFieldValue("IsMealDessert").toString());
	            	place.setIsGoodForMealBreakfast(doc.getFieldValue("IsGoodForMealBreakfast").toString());
	            	place.setIsGoodForMealBrunch(doc.getFieldValue("IsGoodForMealBrunch").toString());
	            	place.setIsGoodForMealLunch(doc.getFieldValue("IsGoodForMealLunch").toString());
	            	place.setIsGoodForMealDinner(doc.getFieldValue("IsGoodForMealDinner").toString());
	            	place.setIsGoodForMealLateNight(doc.getFieldValue("IsGoodForMealLateNight").toString());
	            	place.setIsGoodForMealDessert(doc.getFieldValue("IsGoodForMealDessert").toString());
	              	place.setCreatedDate(dateFormat.format(createdDate));	
	              	place.setApprovalDate(dateFormat.format(approvalDate));	
	              	place.setUpdatedDate(dateFormat.format(updatedDate));	
	              	place.setDeletionDate(dateFormat.format(deletionDate));	
	              	place.setLastLoginDate(dateFormat.format(lastLoginDate));	
	              	place.setIsFeatured(doc.getFieldValue("IsFeatured").toString());
	              	place.setOnlineStatus(doc.getFieldValue("OnlineStatus").toString());	
	              	place.setWhoCanSeeInSearch(doc.getFieldValue("WhoCanSeeInSearch").toString());	
	              	place.setShowWelcomePage(doc.getFieldValue("ShowWelcomePage").toString());	
	              	place.setShowCompleteStatus(doc.getFieldValue("ShowCompleteStatus").toString());
	              	place.setIsFundermailSent(doc.getFieldValue("IsFundermailSent").toString());
	              	place.setContactDB(doc.getFieldValue("ContactDB").toString());
	              	place.setUpdateDB(doc.getFieldValue("UpdateDB").toString());
	              	place.setMemberInfoDB(doc.getFieldValue("MemberInfoDB").toString());
	              	place.setCommentDB(doc.getFieldValue("CommentDB").toString());
	              	place.setMemberContentDB(doc.getFieldValue("MemberContentDB").toString());
	              	place.setMessageDB(doc.getFieldValue("MessageDB").toString());
	              	place.setFolderDB(doc.getFieldValue("FolderDB").toString());
	              	place.setNotificationSettingDB(doc.getFieldValue("NotificationSettingDB").toString());
	              	place.setSearchDB(doc.getFieldValue("SearchDB").toString());
	              	place.setStatus(doc.getFieldValue("Status").toString());
	              	place.setRestrictAge(doc.getFieldValue("RestrictAge").toString());
	              	place.setRestrictCountry(doc.getFieldValue("RestrictCountry").toString());
	              	place.setRestrictPost(doc.getFieldValue("RestrictPost").toString());
	              	
	              	place.setProfileFileJson(doc.getFieldValue("ProfileFileJson").toString());
	              	
	              	places.add(place);
	            }
	            resultArr[0] = places;
	            resultArr[1] = numFound;
	         return  resultArr;  
	    } 
	    catch (SolrServerException e) 
	    {
	    	logDetails.getException(e , logger , request);
	        throw new DataAccessResourceFailureException(e.getMessage(), e);
	    }
	} 	
	
	
	public  Object[] queryCheckSort(String query,int pageCount,int rows , String sort1 , String sort2 , String sort3, String order1 , String order2 , String order3 ,  HttpServletRequest request )
	{
	    try 
	    {
	    	String comma=",";
	    //	String sort=""+sort1+" "+order1+" "+comma+" "+sort2+" "+order2+" "+comma+" "+sort3+" "+order3+"";
	    	String sorts=sort1 +" "+order1+","+sort2+" "+order2+","+sort3+" "+order3;
	    	
	    	SolrServer server =  Adder.getSolrServer(serverurlConstants.ADD_PLACES_URL);
		    ModifiableSolrParams params = new ModifiableSolrParams();
		    params.set("q", query);
		    params.set("start", ""+pageCount);
		 //  params.set("sort", ""+sort1+" "+order1+" , "+sort2+" "+order2+" , "+sort3+" "+order3+"");
		 //   params.set("sort", "TotalReviews asc");
		    params.set("version", "2.2");
		    params.set("wt", "json");
		 
//		     params.set("sort", ""+sort1+" "+order1+"");
//		     params.set("sort", ""+sort2+" "+order2+"");
//		     params.set("sort", ""+sort3+" "+order3+"");
	//	params.set("sort", sorts);		   
		//  params.set("sort", ""+sort1+" "+order1+" , "+sort2+" "+order2+" , "+sort3+" "+order3+"");
		
		    params.set("indent", "on");
		    params.set("rows", ""+rows);
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			   
		    String CreatedDate = null;
			 String ApprovalDate = null;
			 String UpdatedDate = null;
			 String DeletionDate = null;
			 String LastLoginDate = null;
			
			 Date createdDate=null;
			 Date approvalDate=null;
			 Date updatedDate=null;
			 Date deletionDate=null;
		     Date lastLoginDate=null;
			
		    Object[] resultArr = new Object[2];
		    QueryResponse response = server.query(params, SolrRequest.METHOD.POST);
		    String   numFound = response.getResults().getNumFound()+"";
		    List <Place> places = new ArrayList<Place>();
	            for (SolrDocument doc : response.getResults())
	            {	
	            	
	            	 dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
	            	 createdDate=(Date)doc.getFieldValue("CreatedDate");
	            	 approvalDate=(Date)doc.getFieldValue("ApprovalDate");
	            	 updatedDate=(Date)doc.getFieldValue("UpdatedDate");
	            	 deletionDate=(Date)doc.getFieldValue("DeletionDate");
	            	 lastLoginDate=(Date)doc.getFieldValue("LastLoginDate");
	            	 
	    			Place place = new Place();
	            	place.setID(doc.getFieldValue("ID").toString());	            
	            	place.setCategory1ID(doc.getFieldValue("Category1ID").toString());	            	
	            	place.setCategory1ID(doc.getFieldValue("Category1ID").toString());
	            	place.setCategory2ID(doc.getFieldValue("Category2ID").toString());
	            	place.setCategory3ID(doc.getFieldValue("Category3ID").toString());
	            	place.setSubCategory1ID(doc.getFieldValue("SubCategory1ID").toString());
	            	place.setSubCategory2ID(doc.getFieldValue("SubCategory2ID").toString());
	            	place.setSubCategory3ID(doc.getFieldValue("SubCategory3ID").toString());
	            	place.setCategory1Name(doc.getFieldValue("Category1Name").toString());
	            	place.setSubCategory2ID(doc.getFieldValue("SubCategory2ID").toString());
	            	place.setCategory2Name(doc.getFieldValue("Category2Name").toString());	            	
	            	place.setCategory3Name(doc.getFieldValue("Category3Name").toString());
	            	place.setSubCategory1Name(doc.getFieldValue("SubCategory1Name").toString());
	            	place.setSubCategory2Name(doc.getFieldValue("SubCategory2Name").toString());
	            	place.setSubCategory3Name(doc.getFieldValue("SubCategory3Name").toString());
	             	place.setName(doc.getFieldValue("Name").toString());	  
	             	place.setScreenName(doc.getFieldValue("ScreenName").toString());	
	             	place.setScreenNameStatus(doc.getFieldValue("ScreenNameStatus").toString());
	             	place.setProfileImage(doc.getFieldValue("ProfileImage").toString());
	             	place.setProfileFileID(doc.getFieldValue("ProfileFileID").toString());
	             	place.setEmail(doc.getFieldValue("Email").toString());	
	             	place.setAddress1(doc.getFieldValue("Address1").toString());	
	             	place.setAddress2(doc.getFieldValue("Address2").toString());
	             	place.setCity(doc.getFieldValue("City").toString());
	             	place.setState(doc.getFieldValue("State").toString());	
	             	place.setCountry(doc.getFieldValue("Country").toString());
	             	place.setCityID(doc.getFieldValue("CityID").toString());
	             	place.setStateID(doc.getFieldValue("StateID").toString());
	             	place.setCountryID(doc.getFieldValue("CountryID").toString());	
	             	place.setZipCode(doc.getFieldValue("ZipCode").toString());
	             	place.setPhone(doc.getFieldValue("Phone").toString());	
	             	place.setWebAddress(doc.getFieldValue("WebAddress").toString());
	             	place.setBlog(doc.getFieldValue("Blog").toString());	
	             	place.setNeighbourHoods(doc.getFieldValue("NeighbourHoods").toString());	
	             	place.setNearestTransits(doc.getFieldValue("NearestTransits").toString());
	             	place.setRating(doc.getFieldValue("Rating").toString());	
	             	place.setTotalReviews(doc.getFieldValue("TotalReviews").toString());	
	             	place.setDescription(doc.getFieldValue("Description").toString());
	             	place.setProducts(doc.getFieldValue("Products").toString());	
	            	place.setPolicy(doc.getFieldValue("Policy").toString());	
	              	place.setRole(doc.getFieldValue("Role").toString());	
	              	place.setPriceRange(doc.getFieldValue("PriceRange").toString());
	              	place.setAcceptsCreditCards(doc.getFieldValue("AcceptsCreditCards").toString());
	              	place.setWheelChairAccessible(doc.getFieldValue("WheelChairAccessible").toString());
	            	place.setGoodForKids(doc.getFieldValue("GoodForKids").toString());
	            	place.setGoodForGroups(doc.getFieldValue("GoodForGroups").toString());
	            	place.setWaiterService(doc.getFieldValue("WaiterService").toString());
	            	place.setAttire(doc.getFieldValue("Attire").toString());
	            	place.setOutdoorSeating(doc.getFieldValue("OutdoorSeating").toString());
	           // 	place.setAlcohol(doc.getFieldValue("Alcohol").toString());
	            	place.setTakeOut(doc.getFieldValue("TakeOut").toString());
	            	place.setTakesReservation(doc.getFieldValue("TakesReservation").toString());
	            	place.setByAppointmentOnly(doc.getFieldValue("ByAppointmentOnly").toString());
	            	place.setMusic(doc.getFieldValue("Music").toString());
	            	place.setBestNights(doc.getFieldValue("BestNights").toString());
	            	place.setSmoking(doc.getFieldValue("Smoking").toString());
	            	place.setCoatCheck(doc.getFieldValue("CoatCheck").toString());
	            	place.setHappyHour(doc.getFieldValue("HappyHour").toString());
	            	place.setWiFi(doc.getFieldValue("WiFi").toString());
	            	place.setIsParkingStreet(doc.getFieldValue("IsParkingStreet").toString());
	            	place.setIsParkingGarage(doc.getFieldValue("IsParkingGarage").toString());
	            	place.setIsParkingValet(doc.getFieldValue("IsParkingValet").toString());
	            	place.setIsParkingPrivateLot(doc.getFieldValue("IsParkingPrivateLot").toString());
	            	place.setIsParkingValidated(doc.getFieldValue("IsParkingValidated").toString());
	            	place.setIsMealBreakfast(doc.getFieldValue("IsMealBreakfast").toString());
	            	place.setIsMealBrunch(doc.getFieldValue("IsMealBrunch").toString());
	            	place.setIsMealLunch(doc.getFieldValue("IsMealLunch").toString());
	            	place.setIsMealDinner(doc.getFieldValue("IsMealDinner").toString());
	            	place.setIsMealLateNight(doc.getFieldValue("IsMealLateNight").toString());
	            	place.setIsMealDessert(doc.getFieldValue("IsMealDessert").toString());
	            	place.setIsGoodForMealBreakfast(doc.getFieldValue("IsGoodForMealBreakfast").toString());
	            	place.setIsGoodForMealBrunch(doc.getFieldValue("IsGoodForMealBrunch").toString());
	            	place.setIsGoodForMealLunch(doc.getFieldValue("IsGoodForMealLunch").toString());
	            	place.setIsGoodForMealDinner(doc.getFieldValue("IsGoodForMealDinner").toString());
	            	place.setIsGoodForMealLateNight(doc.getFieldValue("IsGoodForMealLateNight").toString());
	            	place.setIsGoodForMealDessert(doc.getFieldValue("IsGoodForMealDessert").toString());
	              	place.setCreatedDate(dateFormat.format(createdDate));	
	              	place.setApprovalDate(dateFormat.format(approvalDate));	
	              	place.setUpdatedDate(dateFormat.format(updatedDate));	
	              	place.setDeletionDate(dateFormat.format(deletionDate));	
	              	place.setLastLoginDate(dateFormat.format(lastLoginDate));	
	              	place.setIsFeatured(doc.getFieldValue("IsFeatured").toString());
	              	place.setOnlineStatus(doc.getFieldValue("OnlineStatus").toString());	
	              	place.setWhoCanSeeInSearch(doc.getFieldValue("WhoCanSeeInSearch").toString());	
	              	place.setShowWelcomePage(doc.getFieldValue("ShowWelcomePage").toString());	
	              	place.setShowCompleteStatus(doc.getFieldValue("ShowCompleteStatus").toString());
	              	place.setIsFundermailSent(doc.getFieldValue("IsFundermailSent").toString());
	              	place.setContactDB(doc.getFieldValue("ContactDB").toString());
	              	place.setUpdateDB(doc.getFieldValue("UpdateDB").toString());
	              	place.setMemberInfoDB(doc.getFieldValue("MemberInfoDB").toString());
	              	place.setCommentDB(doc.getFieldValue("CommentDB").toString());
	              	place.setMemberContentDB(doc.getFieldValue("MemberContentDB").toString());
	              	place.setMessageDB(doc.getFieldValue("MessageDB").toString());
	              	place.setFolderDB(doc.getFieldValue("FolderDB").toString());
	              	place.setNotificationSettingDB(doc.getFieldValue("NotificationSettingDB").toString());
	              	place.setSearchDB(doc.getFieldValue("SearchDB").toString());
	              	place.setStatus(doc.getFieldValue("Status").toString());
	              	place.setRestrictAge(doc.getFieldValue("RestrictAge").toString());
	              	place.setRestrictCountry(doc.getFieldValue("RestrictCountry").toString());
	              	place.setRestrictPost(doc.getFieldValue("RestrictPost").toString());
	              	places.add(place);
	            }
	            resultArr[0] = places;
	            resultArr[1] = numFound;
	         return  resultArr;  
	    } 
	    catch (SolrServerException e) 
	    {
	    	logDetails.getException(e , logger , request);
	        throw new DataAccessResourceFailureException(e.getMessage(), e);
	    }
	    
	   // return response;
	} 	
	
	
	public  Object[] queryCheckForSpecificfield(String query,int pageCount,int rows , HttpServletRequest request)
	{
	    try 
	    {
	    	
	    	SolrServer server =  Adder.getSolrServer(serverurlConstants.ADD_PLACES_URL);
		    ModifiableSolrParams params = new ModifiableSolrParams();
		    params.set("q", query);
		    params.set("start", ""+pageCount);
		    params.set("version", "2.2");
		    params.set("wt", "json");
		    params.set("indent", "on");
		    params.set("fl", "Email , ProfileFileID , DisplayAlternateName , ScreenName , ProfileImage , ProfileFileID");
		    params.set("rows", ""+rows);
		   
		    Object[] resultArr = new Object[2];
		    QueryResponse response = server.query(params, SolrRequest.METHOD.POST);
		    String   numFound = response.getResults().getNumFound()+"";
	        resultArr[0] = response.getResults();
	    
	        resultArr[1] = numFound;
	        return  resultArr;  
	    } 
	    catch (SolrServerException e) 
	    {
	    	logDetails.getException(e , logger , request);
	        throw new DataAccessResourceFailureException(e.getMessage(), e);
	    }
	} 	
	
	
	
	
	
	
	public  Object[] HeaderPlace(String query,int pageCount,int rows , HttpServletRequest request)
	{
	    try 
	    {
	    	
	    	SolrServer server =  Adder.getSolrServer(serverurlConstants.ADD_PLACES_URL);
		    ModifiableSolrParams params = new ModifiableSolrParams();
		    params.set("q", query);
		    params.set("start", ""+pageCount);
		    params.set("version", "2.2");
		    params.set("wt", "json");
		    params.set("indent", "on");
		    params.set("fl", "ID , ScreenName , ProfileImage , Category1Name , City , State , TotalReviews , Address1 , ZipCode , Phone , NeighbourHoods , Name ,  ProfileFileJson");
		    params.set("rows", ""+rows);
		   
		    
			
		    Object[] resultArr = new Object[2];
		    QueryResponse response = server.query(params, SolrRequest.METHOD.POST);
		    String   numFound = response.getResults().getNumFound()+"";
		    List <HeaderPlace> places = new ArrayList<HeaderPlace>();
	            for (SolrDocument doc : response.getResults())
	            {	
	            	 
	    			HeaderPlace place = new HeaderPlace();
	            	place.setID(doc.getFieldValue("ID").toString());	            
	            	
	            	place.setCategory1Name(doc.getFieldValue("Category1Name").toString());
	            	place.setName(doc.getFieldValue("Name").toString());	  
	             	place.setScreenName(doc.getFieldValue("ScreenName").toString());	
	             	place.setProfileImage(doc.getFieldValue("ProfileImage").toString());
	             	place.setAddress1(doc.getFieldValue("Address1").toString());	
	             	place.setCity(doc.getFieldValue("City").toString());
	             	place.setState(doc.getFieldValue("State").toString());	
	             	place.setZipCode(doc.getFieldValue("ZipCode").toString());
	             	place.setPhone(doc.getFieldValue("Phone").toString());	
	             	place.setNeighbourHoods(doc.getFieldValue("NeighbourHoods").toString());	
	             	place.setTotalReviews(doc.getFieldValue("TotalReviews").toString());
	             	place.setProfileFileJson(doc.getFieldValue("ProfileFileJson").toString());
	             	 
	             	places.add(place);
	            }
	            resultArr[0] = places;
	            resultArr[1] = numFound;
	         return  resultArr;  
	    } 
	    catch (SolrServerException e) 
	    {
	    	logDetails.getException(e , logger , request);
	        throw new DataAccessResourceFailureException(e.getMessage(), e);
	    }
	} 	
	
	
	public  Object[] queryChecksortBy(String query,int pageCount,int rows ,  String sortBy ,HttpServletRequest request )
	{
	    try 
	    {
	    	
	    	SolrServer server =  Adder.getSolrServer(serverurlConstants.ADD_PLACES_URL);
		    ModifiableSolrParams params = new ModifiableSolrParams();
		    params.set("q", query);
		    params.set("start", ""+pageCount);
		    params.set("version", "2.2");
		    params.set("wt", "json");
		    
		    params.set("sort", "UpdatedDate desc");
		    
		    
		 /*  if(sortBy.equals("Name"))
		    params.set("sort", "Name asc");
		    if(sortBy.equals("Rating"))
			params.set("sort", "Rating desc");
		    if(sortBy.equals("Reviews"))
			params.set("sort", "TotalReviews desc");
		    if(sortBy.equals("TotalReviews"))
				params.set("sort", "TotalReviews desc");
		    
		 */
	
		    params.set("indent", "on");
		    params.set("rows", ""+rows);
		   
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   
		    String CreatedDate = null;
			 String ApprovalDate = null;
			 String UpdatedDate = null;
			 String DeletionDate = null;
			 String LastLoginDate = null;
			
			 
			 Date createdDate=null;
			 Date approvalDate=null;
			 Date updatedDate=null;
			 Date deletionDate=null;
		     Date lastLoginDate=null;
			
		    Object[] resultArr = new Object[2];
		    QueryResponse response = server.query(params, SolrRequest.METHOD.POST);
		    String   numFound = response.getResults().getNumFound()+"";
		    List <Place> places = new ArrayList<Place>();
	            for (SolrDocument doc : response.getResults())
	            {	
	            	
	            	 dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
	            	 createdDate=(Date)doc.getFieldValue("CreatedDate");
	            	 approvalDate=(Date)doc.getFieldValue("ApprovalDate");
	            	 updatedDate=(Date)doc.getFieldValue("UpdatedDate");
	            	 deletionDate=(Date)doc.getFieldValue("DeletionDate");
	            	 lastLoginDate=(Date)doc.getFieldValue("LastLoginDate");
	            	 
	    			Place place = new Place();
	            	place.setID(doc.getFieldValue("ID").toString());	            
	            	place.setCategory1ID(doc.getFieldValue("Category1ID").toString());	            	
	            	place.setCategory1ID(doc.getFieldValue("Category1ID").toString());
	            	place.setCategory2ID(doc.getFieldValue("Category2ID").toString());
	            	place.setCategory3ID(doc.getFieldValue("Category3ID").toString());
	            	place.setSubCategory1ID(doc.getFieldValue("SubCategory1ID").toString());
	            	place.setSubCategory2ID(doc.getFieldValue("SubCategory2ID").toString());
	            	place.setSubCategory3ID(doc.getFieldValue("SubCategory3ID").toString());
	            	place.setCategory1Name(doc.getFieldValue("Category1Name").toString());
	            	place.setSubCategory2ID(doc.getFieldValue("SubCategory2ID").toString());
	            	place.setCategory2Name(doc.getFieldValue("Category2Name").toString());	            	
	            	place.setCategory3Name(doc.getFieldValue("Category3Name").toString());
	            	place.setSubCategory1Name(doc.getFieldValue("SubCategory1Name").toString());
	            	place.setSubCategory2Name(doc.getFieldValue("SubCategory2Name").toString());
	            	place.setSubCategory3Name(doc.getFieldValue("SubCategory3Name").toString());
	             	place.setName(doc.getFieldValue("Name").toString());	  
	             	place.setScreenName(doc.getFieldValue("ScreenName").toString());	
	             	place.setScreenNameStatus(doc.getFieldValue("ScreenNameStatus").toString());
	             	place.setProfileImage(doc.getFieldValue("ProfileImage").toString());
	             	place.setProfileFileID(doc.getFieldValue("ProfileFileID").toString());
	             	place.setEmail(doc.getFieldValue("Email").toString());	
	             	place.setAddress1(doc.getFieldValue("Address1").toString());	
	             	place.setAddress2(doc.getFieldValue("Address2").toString());
	             	place.setCity(doc.getFieldValue("City").toString());
	             	place.setState(doc.getFieldValue("State").toString());	
	             	place.setCountry(doc.getFieldValue("Country").toString());
	             	place.setCityID(doc.getFieldValue("CityID").toString());
	             	place.setStateID(doc.getFieldValue("StateID").toString());
	             	place.setCountryID(doc.getFieldValue("CountryID").toString());	
	             	place.setZipCode(doc.getFieldValue("ZipCode").toString());
	             	place.setPhone(doc.getFieldValue("Phone").toString());	
	             	place.setWebAddress(doc.getFieldValue("WebAddress").toString());
	             	place.setBlog(doc.getFieldValue("Blog").toString());	
	             	place.setNeighbourHoods(doc.getFieldValue("NeighbourHoods").toString());	
	             	place.setNearestTransits(doc.getFieldValue("NearestTransits").toString());
	             	place.setRating(doc.getFieldValue("Rating").toString());	
	             	place.setTotalReviews(doc.getFieldValue("TotalReviews").toString());	
	             	place.setDescription(doc.getFieldValue("Description").toString());
	             	place.setProducts(doc.getFieldValue("Products").toString());	
	            	place.setPolicy(doc.getFieldValue("Policy").toString());	
	              	place.setRole(doc.getFieldValue("Role").toString());	
	              	place.setPriceRange(doc.getFieldValue("PriceRange").toString());
	              	place.setAcceptsCreditCards(doc.getFieldValue("AcceptsCreditCards").toString());
	              	place.setWheelChairAccessible(doc.getFieldValue("WheelChairAccessible").toString());
	            	place.setGoodForKids(doc.getFieldValue("GoodForKids").toString());
	            	place.setGoodForGroups(doc.getFieldValue("GoodForGroups").toString());
	            	place.setWaiterService(doc.getFieldValue("WaiterService").toString());
	            	place.setAttire(doc.getFieldValue("Attire").toString());
	            	place.setOutdoorSeating(doc.getFieldValue("OutdoorSeating").toString());
	            //	place.setAlcohol(doc.getFieldValue("Alcohol").toString());
	            	place.setTakeOut(doc.getFieldValue("TakeOut").toString());
	            	place.setTakesReservation(doc.getFieldValue("TakesReservation").toString());
	            	place.setByAppointmentOnly(doc.getFieldValue("ByAppointmentOnly").toString());
	            	place.setMusic(doc.getFieldValue("Music").toString());
	            	place.setBestNights(doc.getFieldValue("BestNights").toString());
	            	place.setSmoking(doc.getFieldValue("Smoking").toString());
	            	place.setCoatCheck(doc.getFieldValue("CoatCheck").toString());
	            	place.setHappyHour(doc.getFieldValue("HappyHour").toString());
	            	place.setWiFi(doc.getFieldValue("WiFi").toString());
	            	place.setIsParkingStreet(doc.getFieldValue("IsParkingStreet").toString());
	            	place.setIsParkingGarage(doc.getFieldValue("IsParkingGarage").toString());
	            	place.setIsParkingValet(doc.getFieldValue("IsParkingValet").toString());
	            	place.setIsParkingPrivateLot(doc.getFieldValue("IsParkingPrivateLot").toString());
	            	place.setIsParkingValidated(doc.getFieldValue("IsParkingValidated").toString());
	            	place.setIsMealBreakfast(doc.getFieldValue("IsMealBreakfast").toString());
	            	place.setIsMealBrunch(doc.getFieldValue("IsMealBrunch").toString());
	            	place.setIsMealLunch(doc.getFieldValue("IsMealLunch").toString());
	            	place.setIsMealDinner(doc.getFieldValue("IsMealDinner").toString());
	            	place.setIsMealLateNight(doc.getFieldValue("IsMealLateNight").toString());
	            	place.setIsMealDessert(doc.getFieldValue("IsMealDessert").toString());
	            	place.setIsGoodForMealBreakfast(doc.getFieldValue("IsGoodForMealBreakfast").toString());
	            	place.setIsGoodForMealBrunch(doc.getFieldValue("IsGoodForMealBrunch").toString());
	            	place.setIsGoodForMealLunch(doc.getFieldValue("IsGoodForMealLunch").toString());
	            	place.setIsGoodForMealDinner(doc.getFieldValue("IsGoodForMealDinner").toString());
	            	place.setIsGoodForMealLateNight(doc.getFieldValue("IsGoodForMealLateNight").toString());
	            	place.setIsGoodForMealDessert(doc.getFieldValue("IsGoodForMealDessert").toString());
	              	place.setCreatedDate(dateFormat.format(createdDate));	
	              	place.setApprovalDate(dateFormat.format(approvalDate));	
	              	place.setUpdatedDate(dateFormat.format(updatedDate));	
	              	place.setDeletionDate(dateFormat.format(deletionDate));	
	              	place.setLastLoginDate(dateFormat.format(lastLoginDate));	
	              	place.setIsFeatured(doc.getFieldValue("IsFeatured").toString());
	              	place.setOnlineStatus(doc.getFieldValue("OnlineStatus").toString());	
	              	place.setWhoCanSeeInSearch(doc.getFieldValue("WhoCanSeeInSearch").toString());	
	              	place.setShowWelcomePage(doc.getFieldValue("ShowWelcomePage").toString());	
	              	place.setShowCompleteStatus(doc.getFieldValue("ShowCompleteStatus").toString());
	              	place.setIsFundermailSent(doc.getFieldValue("IsFundermailSent").toString());
	              	place.setContactDB(doc.getFieldValue("ContactDB").toString());
	              	place.setUpdateDB(doc.getFieldValue("UpdateDB").toString());
	              	place.setMemberInfoDB(doc.getFieldValue("MemberInfoDB").toString());
	              	place.setCommentDB(doc.getFieldValue("CommentDB").toString());
	              	place.setMemberContentDB(doc.getFieldValue("MemberContentDB").toString());
	              	place.setMessageDB(doc.getFieldValue("MessageDB").toString());
	              	place.setFolderDB(doc.getFieldValue("FolderDB").toString());
	              	place.setNotificationSettingDB(doc.getFieldValue("NotificationSettingDB").toString());
	              	place.setSearchDB(doc.getFieldValue("SearchDB").toString());
	              	place.setStatus(doc.getFieldValue("Status").toString());
	              	place.setRestrictAge(doc.getFieldValue("RestrictAge").toString());
	              	place.setRestrictCountry(doc.getFieldValue("RestrictCountry").toString());
	              	place.setRestrictPost(doc.getFieldValue("RestrictPost").toString());
	              	
	              	place.setProfileFileJson(doc.getFieldValue("ProfileFileJson").toString());
	              	
	              	places.add(place);
	            }
	            resultArr[0] = places;
	            resultArr[1] = numFound;
	         return  resultArr;  
	    } 
	    catch (SolrServerException e) 
	    {
	    	logDetails.getException(e , logger , request);
	        throw new DataAccessResourceFailureException(e.getMessage(), e);
	    }
	} 	
	

	public  Object[] findplaceautocompleteCheck(String query,int pageCount,int rows ,HttpServletRequest request )
	{
	    try 
	    {
	    	
	    	SolrServer server =  Adder.getSolrServer(serverurlConstants.ADD_PLACES_URL);
		    ModifiableSolrParams params = new ModifiableSolrParams();
		    params.set("q", query);
		    params.set("start", ""+pageCount);
		    params.set("version", "2.2");
		    params.set("wt", "json");
		 //   params.set("sort", "Name desc");
		    params.set("indent", "on");
		    params.set("rows", ""+rows);
		   
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   
		    String CreatedDate = null;
			 String ApprovalDate = null;
			 String UpdatedDate = null;
			 String DeletionDate = null;
			 String LastLoginDate = null;
			
			 
			 Date createdDate=null;
			 Date approvalDate=null;
			 Date updatedDate=null;
			 Date deletionDate=null;
		     Date lastLoginDate=null;
			
		    Object[] resultArr = new Object[2];
		    QueryResponse response = server.query(params, SolrRequest.METHOD.POST);
		    String   numFound = response.getResults().getNumFound()+"";
		    List <PlaceFilter> places = new ArrayList<PlaceFilter>();
	            for (SolrDocument doc : response.getResults())
	            {	
	            	
	            	 dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
	            	 createdDate=(Date)doc.getFieldValue("CreatedDate");
	            	 approvalDate=(Date)doc.getFieldValue("ApprovalDate");
	            	 updatedDate=(Date)doc.getFieldValue("UpdatedDate");
	            	 deletionDate=(Date)doc.getFieldValue("DeletionDate");
	            	 lastLoginDate=(Date)doc.getFieldValue("LastLoginDate");
	            	 
	            	 PlaceFilter PlaceFilter = new PlaceFilter();
	            	 PlaceFilter.setID(doc.getFieldValue("ID").toString());	            
	            	 PlaceFilter.setCategory1ID(doc.getFieldValue("Category1ID").toString());	            	
	            	 PlaceFilter.setCategory1ID(doc.getFieldValue("Category1ID").toString());
	            	
	            	 PlaceFilter.setCategory1Name(doc.getFieldValue("Category1Name").toString());
	            	 PlaceFilter.setName(doc.getFieldValue("Name").toString());	  
	            	 PlaceFilter.setScreenName(doc.getFieldValue("ScreenName").toString());	
	             	
	              	
	            	 PlaceFilter.setProfileFileJson(doc.getFieldValue("ProfileFileJson").toString());
	              	
	              	places.add(PlaceFilter);
	            }
	            resultArr[0] = places;
	            resultArr[1] = numFound;
	         return  resultArr;  
	    } 
	    catch (SolrServerException e) 
	    {
	    	logDetails.getException(e , logger , request);
	        throw new DataAccessResourceFailureException(e.getMessage(), e);
	    }
	} 	
	
	
	
}
