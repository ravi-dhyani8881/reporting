package com.ytk.controller;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.solr.client.solrj.SolrQuery;
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
import com.ytk.client.MamCacheClient;
import com.ytk.client.PlatformPlaceControllerClient;
import com.ytk.client.PrivacyClient;
import com.ytk.client.SearchContactsClient;
import com.ytk.models.CollectionList;
import com.ytk.models.DiscussionQuestion;
import com.ytk.models.Faces;
import com.ytk.models.Place;
import com.ytk.models.Plans;
import com.ytk.models.ResultDoc;
import com.ytk.utility.Adder;
import com.ytk.utility.ServerurlConstants;


@Controller
@RequestMapping("/searchplace/*")
public class PlatformPlaceController {
	
	@Autowired
	ServerurlConstants serverurlConstants;
	
	@Autowired
	PrivacyClient privacyClient;
	
	@Autowired
	MamCacheClient mamCacheClient;
	
	@Autowired
	DateClient dateClient;	
	
	@Autowired
	PlatformPlaceControllerClient platformPlaceControllerClient;
	
	@RequestMapping(value = "/addplace")
	public ModelAndView addPlace(
			HttpServletRequest request,
			@RequestParam(value = "ID", required = false) String ID,
			@RequestParam(value = "Category1ID", required = false , defaultValue = "0" ) String Category1ID,
			@RequestParam(value = "Category2ID", required = false , defaultValue = "0") String Category2ID,
			@RequestParam(value = "Category3ID", required = false , defaultValue = "0") String Category3ID,
			@RequestParam(value = "SubCategory1ID", required = false , defaultValue = "0") String SubCategory1ID,
			@RequestParam(value = "SubCategory2ID", required = false , defaultValue = "0") String SubCategory2ID,
			@RequestParam(value = "SubCategory3ID", required = false , defaultValue = "0") String SubCategory3ID,
			@RequestParam(value = "Category1Name", required = false , defaultValue = "") String Category1Name,
			@RequestParam(value = "Category2Name", required = false , defaultValue = "") String Category2Name,
			@RequestParam(value = "Category3Name", required = false , defaultValue = "") String Category3Name,
			@RequestParam(value = "SubCategory1Name", required = false , defaultValue = "") String SubCategory1Name,
			@RequestParam(value = "SubCategory2Name", required = false , defaultValue = "") String SubCategory2Name,
			@RequestParam(value = "SubCategory3Name", required = false , defaultValue = "") String SubCategory3Name,
			@RequestParam(value = "Name", required = false , defaultValue = "") String Name,
			@RequestParam(value = "ScreenName", required = false , defaultValue = "") String ScreenName,
			@RequestParam(value = "ScreenNameStatus", required = false , defaultValue = "0") String ScreenNameStatus,
			@RequestParam(value = "SafeName", required = false , defaultValue = "") String SafeName,
			@RequestParam(value = "Email", required = false , defaultValue = "") String Email,
			@RequestParam(value = "Address1", required = false , defaultValue = "") String Address1,
			@RequestParam(value = "Address2", required = false , defaultValue = "") String Address2,
			@RequestParam(value = "City", required = false , defaultValue = "") String City,
			@RequestParam(value = "State", required = false , defaultValue = "") String State,
			@RequestParam(value = "Country", required = false , defaultValue = "") String Country,
			@RequestParam(value = "CityID", required = false , defaultValue = "0") String CityID,			
			@RequestParam(value = "StateID", required = false , defaultValue = "") String StateID,
			@RequestParam(value = "CountryID", required = false , defaultValue = "0") String CountryID,
			@RequestParam(value = "ZipCode", required = false , defaultValue = "") String ZipCode,
			@RequestParam(value = "Phone", required = false , defaultValue = "") String Phone,
			@RequestParam(value = "WebAddress", required = false , defaultValue = "") String WebAddress,
			@RequestParam(value = "Rating", required = false , defaultValue = "0") String Rating,
			@RequestParam(value = "TotalReviews", required = false , defaultValue = "0") String TotalReviews,
			@RequestParam(value = "Description", required = false , defaultValue = "") String Description,
			@RequestParam(value = "Role", required = false , defaultValue = "Member") String Role,
			@RequestParam(value = "PriceRange", required = false , defaultValue = "0") String PriceRange,
			@RequestParam(value = "AcceptsCreditCards", required = false , defaultValue = "0") String AcceptsCreditCards,
			@RequestParam(value = "WheelChairAccessible", required = false , defaultValue = "0") String WheelChairAccessible,
			@RequestParam(value = "GoodForKids", required = false , defaultValue = "0") String GoodForKids,
			@RequestParam(value = "GoodForGroups", required = false , defaultValue = "0") String GoodForGroups,
			@RequestParam(value = "WaiterService", required = false , defaultValue = "0") String WaiterService,
			@RequestParam(value = "Attire", required = false , defaultValue = "0") String Attire,
			@RequestParam(value = "OutdoorSeating", required = false , defaultValue = "0") String OutdoorSeating,
		//	@RequestParam(value = "Alcohol", required = false , defaultValue = "") String Alcohol,
			@RequestParam(value = "TakeOut", required = false , defaultValue = "0") String TakeOut,
			@RequestParam(value = "TakesReservation", required = false , defaultValue = "0") String TakesReservation,
			@RequestParam(value = "ByAppointmentOnly", required = false , defaultValue = "0") String ByAppointmentOnly,
			@RequestParam(value = "Music", required = false , defaultValue = "0") String Music,
			@RequestParam(value = "BestNights", required = false , defaultValue = "0") String BestNights,
			@RequestParam(value = "Smoking", required = false , defaultValue = "0") String Smoking,
			@RequestParam(value = "CoatCheck", required = false , defaultValue = "0") String CoatCheck,
			@RequestParam(value = "HappyHour", required = false , defaultValue = "0") String HappyHour,
			@RequestParam(value = "WiFi", required = false , defaultValue = "0") String WiFi,
			@RequestParam(value = "IsParkingStreet", required = false , defaultValue = "0") String IsParkingStreet,
			@RequestParam(value = "IsParkingGarage", required = false , defaultValue = "0") String IsParkingGarage,
			@RequestParam(value = "IsParkingValet", required = false , defaultValue = "0") String IsParkingValet,
			@RequestParam(value = "IsParkingPrivateLot", required = false , defaultValue = "0") String IsParkingPrivateLot,
			@RequestParam(value = "IsParkingValidated", required = false , defaultValue = "0") String IsParkingValidated,
			@RequestParam(value = "IsMealBreakfast", required = false , defaultValue = "0") String IsMealBreakfast,
			@RequestParam(value = "IsMealBrunch", required = false , defaultValue = "0") String IsMealBrunch,
			@RequestParam(value = "IsMealLunch", required = false , defaultValue = "0") String IsMealLunch,
			@RequestParam(value = "IsMealDinner", required = false , defaultValue = "0") String IsMealDinner,
			@RequestParam(value = "IsMealLateNight", required = false , defaultValue = "0") String IsMealLateNight,
			@RequestParam(value = "IsMealDessert", required = false , defaultValue = "0") String IsMealDessert,
			@RequestParam(value = "IsGoodForMealBreakfast", required = false , defaultValue = "0") String IsGoodForMealBreakfast,
			@RequestParam(value = "IsGoodForMealBrunch", required = false , defaultValue = "0") String IsGoodForMealBrunch,
			@RequestParam(value = "IsGoodForMealLunch", required = false , defaultValue = "0") String IsGoodForMealLunch,
			@RequestParam(value = "IsGoodForMealDinner", required = false , defaultValue = "0") String IsGoodForMealDinner,
			@RequestParam(value = "IsGoodForMealLateNight", required = false , defaultValue = "0") String IsGoodForMealLateNight,
			@RequestParam(value = "IsGoodForMealDessert", required = false , defaultValue = "0") String IsGoodForMealDessert,
			@RequestParam(value = "CreatedDate", required = false , defaultValue = "1900-01-01 00:00:00") String CreatedDate,
			@RequestParam(value = "ApprovalDate", required = false , defaultValue = "1900-01-01 00:00:00") String ApprovalDate,
			@RequestParam(value = "UpdatedDate", required = false , defaultValue = "1900-01-01 00:00:00") String UpdatedDate,
			@RequestParam(value = "DeletionDate", required = false ,defaultValue = "1900-01-01 00:00:00") String DeletionDate,
			@RequestParam(value = "LastLoginDate", required = false , defaultValue = "1900-01-01 00:00:00") String LastLoginDate,
			@RequestParam(value = "IsActive", required = false , defaultValue = "") String IsActive,
			@RequestParam(value = "IsDeleted", required = false , defaultValue = "") String IsDeleted,			
			@RequestParam(value = "IsFeatured", required = false , defaultValue = "") String IsFeatured,
			@RequestParam(value = "IsClosed", required = false , defaultValue = "") String IsClosed,
			@RequestParam(value = "ImageName", required = false , defaultValue = "") String ImageName,
			@RequestParam(value = "HasImage", required = false , defaultValue = "") String HasImage,
			@RequestParam(value = "OnlineStatus", required = false , defaultValue = "") String OnlineStatus,
			@RequestParam(value = "WhoCanSeeInSearch", required = false , defaultValue = "") String WhoCanSeeInSearch,
			@RequestParam(value = "ShowWelcomePage", required = false , defaultValue = "") String ShowWelcomePage,
			@RequestParam(value = "ShowCompleteStatus", required = false , defaultValue = "") String ShowCompleteStatus,
			@RequestParam(value = "IsFundermailSent", required = false , defaultValue = "") String IsFundermailSent,
			@RequestParam(value = "ContactDB", required = false , defaultValue = "0") String ContactDB,
			@RequestParam(value = "UpdateDB", required = false , defaultValue = "0") String UpdateDB,
			@RequestParam(value = "MemberInfoDB", required = false , defaultValue = "0") String MemberInfoDB,
			@RequestParam(value = "CommentDB", required = false , defaultValue = "") String CommentDB,
			@RequestParam(value = "MemberContentDB", required = false , defaultValue = "") String MemberContentDB,
			@RequestParam(value = "MessageDB", required = false , defaultValue = "0") String MessageDB,
			@RequestParam(value = "FolderDB", required = false , defaultValue = "0") String FolderDB,
			@RequestParam(value = "NotificationSettingDB", required = false , defaultValue = "0") String NotificationSettingDB,
			@RequestParam(value = "SearchDB", required = false , defaultValue = "0") String SearchDB,
			@RequestParam(value = "Status", required = false , defaultValue = "") String Status,
			@RequestParam(value = "ProfileImage", required = false , defaultValue = "") String ProfileImage,
			
			
			@RequestParam(value = "ProfileFileJson", required = false , defaultValue = "") String ProfileFileJson,
			
			
			@RequestParam(value = "ProfileFileID", required = false , defaultValue = "") String ProfileFileID,
			@RequestParam(value = "Blog", required = false , defaultValue = "") String Blog,
			@RequestParam(value = "NeighbourHoods", required = false , defaultValue = "") String NeighbourHoods,
			@RequestParam(value = "Products", required = false , defaultValue = "") String Products,
			@RequestParam(value = "Policy", required = false , defaultValue = "") String Policy,
			@RequestParam(value = "RestrictAge", required = false , defaultValue = "0") String RestrictAge,
			@RequestParam(value = "RestrictCountry", required = false , defaultValue = "") String RestrictCountry,
			@RequestParam(value = "RestrictPost", required = false , defaultValue = "0") String RestrictPost,
		//	@RequestParam(value = "Alcoholic", required = false , defaultValue = "") String Alcoholic,
			@RequestParam(value = "NearestTransits", required = false , defaultValue = "") String NearestTransits
			
			){
		
		ModelAndView mav = new ModelAndView();
		SolrInputDocument place = new SolrInputDocument();

		//Contacts contacts = new Contacts();
		place.addField("ID", ID);
		place.addField("Category1ID", Category1ID);
		place.addField("Category2ID", Category2ID);
		place.addField("Category3ID", Category3ID);
		place.addField("SubCategory1ID", SubCategory1ID);
		place.addField("SubCategory2ID", SubCategory2ID);
		place.addField("SubCategory3ID", SubCategory3ID);
		place.addField("Category1Name", Category1Name);
		place.addField("Category2Name", Category2Name);
		place.addField("Category3Name", Category3Name);
		place.addField("SubCategory1Name", SubCategory1Name);
		place.addField("SubCategory2Name", SubCategory2Name);
		place.addField("SubCategory3Name", SubCategory3Name);
		place.addField("Name", Name);
		place.addField("ScreenName", ScreenName);
		place.addField("ScreenNameStatus", ScreenNameStatus);
		place.addField("SafeName", SafeName);
		place.addField("Email", Email);
		place.addField("Address1", Address1);
		place.addField("Address2", Address2);
		place.addField("City", City);
		place.addField("State", State);
		place.addField("Country", Country);
		place.addField("CityID", CityID);
		place.addField("StateID", StateID);
		place.addField("CountryID", CountryID);
		place.addField("ZipCode", ZipCode);
		place.addField("Phone", Phone);
		place.addField("WebAddress", WebAddress);
		place.addField("Rating", Rating);
		place.addField("TotalReviews", TotalReviews);
		place.addField("Description", Description);
		place.addField("Role", Role);
		place.addField("PriceRange", PriceRange);
		place.addField("AcceptsCreditCards", AcceptsCreditCards);
		place.addField("WheelChairAccessible", WheelChairAccessible);
		place.addField("GoodForKids", GoodForKids);
		place.addField("GoodForGroups", GoodForGroups);
		place.addField("WaiterService", WaiterService);
		place.addField("Attire", Attire);
		place.addField("OutdoorSeating", OutdoorSeating);
	//	place.addField("Alcohol", Alcohol);
		place.addField("TakeOut", TakeOut);
		place.addField("TakesReservation", TakesReservation);
		place.addField("ByAppointmentOnly", ByAppointmentOnly);
		place.addField("Music", Music);
		place.addField("BestNights", BestNights);
		place.addField("Smoking", Smoking);
		place.addField("CoatCheck", CoatCheck);
		place.addField("HappyHour", HappyHour);
		place.addField("WiFi", WiFi);
		place.addField("IsParkingStreet", IsParkingStreet);
		place.addField("IsParkingGarage", IsParkingGarage);
		place.addField("IsParkingValet", IsParkingValet);		
		place.addField("IsParkingPrivateLot", IsParkingPrivateLot);
		place.addField("IsParkingValidated", IsParkingValidated);
		place.addField("IsMealBreakfast", IsMealBreakfast);
		place.addField("IsMealBrunch", IsMealBrunch);
		place.addField("IsMealLunch", IsMealLunch);
		place.addField("IsMealDinner", IsMealDinner);
		place.addField("IsMealLateNight", IsMealLateNight);
		place.addField("IsMealDessert", IsMealDessert);
		place.addField("IsGoodForMealBreakfast", IsGoodForMealBreakfast);
		place.addField("IsGoodForMealBrunch", IsGoodForMealBrunch);
		place.addField("IsGoodForMealLunch", IsGoodForMealLunch);
		place.addField("IsGoodForMealDinner", IsGoodForMealDinner);
		place.addField("IsGoodForMealLateNight", IsGoodForMealLateNight);
		place.addField("IsGoodForMealDessert", IsGoodForMealDessert);
		place.addField("CreatedDate", dateClient.addDateToSolr(CreatedDate , request));
		place.addField("ApprovalDate", dateClient.addDateToSolr(ApprovalDate , request));
		place.addField("UpdatedDate", dateClient.addDateToSolr(UpdatedDate , request));
		place.addField("DeletionDate", dateClient.addDateToSolr(DeletionDate , request));
		place.addField("LastLoginDate", dateClient.addDateToSolr(LastLoginDate , request));
		place.addField("IsActive", IsActive);
		place.addField("IsDeleted", IsDeleted);
		place.addField("IsFeatured", IsFeatured);
		place.addField("IsClosed", IsClosed);
		place.addField("ImageName", ImageName);
		place.addField("HasImage", HasImage);
		place.addField("OnlineStatus", OnlineStatus);
		place.addField("WhoCanSeeInSearch", WhoCanSeeInSearch);
		place.addField("ShowWelcomePage", ShowWelcomePage);
		place.addField("ShowCompleteStatus", ShowCompleteStatus);
		place.addField("IsFundermailSent", IsFundermailSent);
		place.addField("ContactDB", ContactDB);
		place.addField("UpdateDB", UpdateDB);
		place.addField("MemberInfoDB", MemberInfoDB);
		place.addField("CommentDB", CommentDB);
		place.addField("MemberContentDB", MemberContentDB);
		place.addField("MessageDB", MessageDB);
		place.addField("FolderDB", FolderDB);
		place.addField("NotificationSettingDB", NotificationSettingDB);
		place.addField("SearchDB", SearchDB);
		place.addField("Status", Status);
		place.addField("ProfileImage", ProfileImage);
		place.addField("ProfileFileID", ProfileFileID);
		
		
		
		place.addField("ProfileFileJson", ProfileFileJson);
		
		
		
		place.addField("Blog", Blog);
		place.addField("NeighbourHoods", NeighbourHoods);
		place.addField("Products", Products);
		place.addField("Policy", Policy);
		place.addField("NearestTransits", NearestTransits);
		place.addField("RestrictAge", RestrictAge);
		place.addField("RestrictCountry", RestrictCountry);
	//	place.addField("Alcoholic", Alcoholic);
		place.addField("RestrictPost", RestrictPost);
		
		// Adding only for the dummy search not in usa in finddirectoryfaces.json
		place.addField("NameForDirectorySearch", Name.replaceAll("\\s",""));
		
		place.addField("NameCopy", Name.toLowerCase());
			
		Adder.addPlaces(serverurlConstants.ADD_PLACES_URL , place);
		String result = "success";
		mav.addObject("result",result);
		return mav;
	}
	
	
	@RequestMapping(value = "/deleteplace")
	public ModelAndView deletePlace(
			@RequestParam(value = "ID", required = false) String ID)
	{
		ModelAndView mav = new ModelAndView();
		SolrInputDocument connection = new SolrInputDocument();
		connection.addField("ID", ID);	
		Adder.deletePlace(serverurlConstants.ADD_PLACES_URL ,connection);
		String result = "success";
		mav.addObject("result",result);
		return mav;
	}
	
	
	@RequestMapping(value = "/deleteallplaces")
	public ModelAndView deleteAllPlaces()
	{
		
		ModelAndView mav = new ModelAndView();
		Adder.deleteAllInstances(ServerurlConstants.ADD_PLACES_URL);
		String result = "success";
		mav.addObject("result",result);
		return mav;
	}
	
	@RequestMapping(value = "/findplaces")
	public @ResponseBody CollectionList findPlaces(HttpServletRequest request,
			@RequestParam(value = "member_id", required = false) String memberId,
			@RequestParam(value = "member_name", required = false) String memberName,
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "Size", required = false ,  defaultValue = "10") int Size,
			@RequestParam(value = "jsoncallback", required = false) String search)
	{
		
		ModelAndView mav = new ModelAndView();
		
			memberName = memberName.toLowerCase();
			String[] memberNameArr  = memberName.split("@");
			String queryText = "";
			int pageCount = page * 10;
			List<Place> contactsQuery = null;
			String numFound  = "0";
			CharSequence charseq = "@";
			Object[] resultArr = null;
			if(memberName.contains(charseq))
			{
				String encodedurl = "";
				queryText = "(Email:"+memberName+"*)";
				resultArr = platformPlaceControllerClient.queryCheck(queryText,pageCount,Size , request);
				numFound = resultArr[1].toString();
				contactsQuery =(List<Place>) resultArr[0] ;
			}
			else
			{
				queryText = "(Name:"+memberNameArr[0] +"*)";
				resultArr = platformPlaceControllerClient.queryCheck(queryText,pageCount,Size , request);
				numFound = resultArr[1].toString();//contactList.getResponse().getNumFound().toString();
				contactsQuery =(List<Place>) resultArr[0] ;
			}
			
			List <Place> fullContacts = contactsQuery;
			if(fullContacts.size() > 0)
			{
					CollectionList coll = new CollectionList();
					coll.setCollection(contactsQuery);
					coll.setStatusOutput("true");
					coll.setTotalRecords(Long.parseLong(resultArr[1].toString()));
					//String res = JsonUtils.jsonFromObject(coll);
					return coll;
					//mav.addObject(res);
			}
			else
			{
				contactsQuery = new ArrayList<Place>();
				CollectionList coll = new CollectionList();
				coll.setCollection(contactsQuery);
				coll.setStatusOutput("true");
				coll.setTotalRecords(0L);
				//String res = JsonUtils.jsonFromObject(coll);
				return coll;
			}
		
	}
	
	@RequestMapping(value = "/finddirectoryplace")
	public @ResponseBody CollectionList  findDirectoryPlaces(HttpServletRequest request,
			@RequestParam(value = "starting_letter", required = false) String memberName,
			@RequestParam(value = "ProfileID", required = false , defaultValue = "0") String memberId,
			@RequestParam(value = "Age", required = false) String Age,
			@RequestParam(value = "CountryId", required = false  , defaultValue = "0") String CountryId,
			@RequestParam(value = "IsAlcohlic", required = false ,  defaultValue = "0") int IsAlcohlic, 
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "size", required = false) int size,
			@RequestParam(value = "jsoncallback", required = false) String search){
		
		ModelAndView mav = new ModelAndView();
		
		String privayQuery=privacyClient.commonPrivacyQuery(Age, CountryId, IsAlcohlic , memberId);
		
		String friendListResult2 = null;
		String 	folderListResult2 =null;
		String 	blockListResult2 = null;
		String 	canSendMessagesResult2=null;

		
		String friendListResult = null;
		String 	folderListResult =null;
		String 	blockListResult = null;
		String 	canSendMessagesResult=null;
		
	    try{
	    friendListResult2=mamCacheClient.mamCachefriendList(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT, memberId, request);
	    if (friendListResult2!=null)
	    friendListResult=friendListResult2.replace("," , " , ");
	    
	    folderListResult2=mamCacheClient.mamCachefolderList(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT, memberId, request);
	    if (folderListResult2!=null)
	    folderListResult=folderListResult2.replace("," , " , ");
	    
	    blockListResult2=mamCacheClient.mamCacheblockList(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT, memberId, request);
	    if (blockListResult2!=null)
	    blockListResult=blockListResult2.replace("," , " , ");
	    
	    canSendMessagesResult2=mamCacheClient.mamCachecanSendMessages(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT,memberId, request);
	    if (canSendMessagesResult2!=null)
	    canSendMessagesResult=canSendMessagesResult2.replace("," , " , ");
	    
	    }catch (Exception e) {
	    	e.printStackTrace();
		}
			

		
		
		
			memberName = memberName.toLowerCase();
			String[] memberNameArr  = memberName.split("@");
			String queryText = "";
			int pageCount = page * size;
			List contactsQuery = null;
			String numFound  = "0";
			Object[] resultArr = null;
		//	queryText = "(Name:"+memberNameArr[0] +"*)";
			
			//New query only for the directory searh
			
			queryText = "(NameForDirectorySearch:"+memberNameArr[0] +"*)";
			
			if(memberName.equals("0123456789")){
				queryText="(NameForDirectorySearch:1* OR NameForDirectorySearch:2* OR NameForDirectorySearch:3* OR NameForDirectorySearch:4* OR NameForDirectorySearch:5* OR NameForDirectorySearch:6* OR NameForDirectorySearch:7* OR NameForDirectorySearch:8* OR NameForDirectorySearch:9* OR NameForDirectorySearch:0*)";
				}
			
			
			queryText="("+queryText+privayQuery+")";
			
			System.out.println("queryText------->"+queryText);
			
			resultArr = platformPlaceControllerClient.queryCheck(queryText,pageCount,size , request);
			numFound = resultArr[1].toString();//contactList.getResponse().getNumFound().toString();
			contactsQuery =(List) resultArr[0] ;
			
			List  fullContacts = contactsQuery;
			if(fullContacts.size() > 0)
			{
				if(search == null)
				{
					CollectionList coll = new CollectionList();
					coll.setCollection(contactsQuery);
					coll.setStatusOutput("true");
					coll.setTotalRecords(Long.parseLong(resultArr[1].toString()));
					return coll;
				}
				else
				{
					ResultDoc resultDoc = new ResultDoc();
					resultDoc.setSize(contactsQuery.size());
					mav.addObject(resultDoc);
				}
			}
			else
			{
				contactsQuery = new ArrayList();
				CollectionList coll = new CollectionList();
				coll.setCollection(contactsQuery);
				coll.setStatusOutput("true");
				coll.setTotalRecords(0L);
				return coll;
			}
				return null;
	}
	
	
	
	
	@RequestMapping(value = "/getplacebycategoryandcity")
	public ModelAndView GetPlaceByCategoryAndCity(
			HttpServletRequest request,
			@RequestParam(value = "ProfileID", required = false , defaultValue = "0" ) String ProfileID,
			@RequestParam(value = "CategoryID", required = false , defaultValue = "0" ) String CategoryID,
			@RequestParam(value = "CityID", required = false , defaultValue = "0" ) String CityID,
			@RequestParam(value = "Age", required = false) String RestrictAge,
			@RequestParam(value = "CountryId", required = false , defaultValue = "0") String RestrictCountry,
			@RequestParam(value = "IsAlcohlic", required = false ,  defaultValue = "0") int Alcohol,
			@RequestParam(value = "PageNo", required = false) int page,
			@RequestParam(value = "PageSize", required = false) int size,
			@RequestParam(value = "SortBy", required = false) String sortBy,
			@RequestParam(value = "jsoncallback", required = false) String search){
		
		ModelAndView mav = new ModelAndView();
		int pageCount = page * size;
		String queryText = "";
		String numFound  = "0";
		Object[] resultArr = null;
		List<Place> contactsQuery = null;
		
      /*  if(Alcohol!=1){
		if(ProfileID!=null || ProfileID!="" || ProfileID!="0"){
			queryText = "(((Category1ID:"+CategoryID+") OR (Category2ID:"+CategoryID+") OR (Category3ID:"+CategoryID+") OR (SubCategory1ID:"+CategoryID+") OR (SubCategory2ID:"+CategoryID+") OR (SubCategory3ID:"+CategoryID+") OR (Category1Name:"+CategoryID+") OR (Category2Name:"+CategoryID+") OR (Category3Name:"+CategoryID+") OR (SubCategory1Name:"+CategoryID+") OR (SubCategory2Name:"+CategoryID+") OR (SubCategory3Name:"+CategoryID+")) AND (CityID:"+CityID+") AND (RestrictAge:[* TO "+RestrictAge+" ]) OR (Alcoholic:"+Alcohol+" AND -RestrictCountry:"+RestrictCountry+")  )";	
		}else{
			queryText = "(((Category1ID:"+CategoryID+") OR (Category2ID:"+CategoryID+") OR (Category3ID:"+CategoryID+") OR (SubCategory1ID:"+CategoryID+") OR (SubCategory2ID:"+CategoryID+") OR (SubCategory3ID:"+CategoryID+") OR (Category1Name:"+CategoryID+") OR (Category2Name:"+CategoryID+") OR (Category3Name:"+CategoryID+") OR (SubCategory1Name:"+CategoryID+") OR (SubCategory2Name:"+CategoryID+") OR (SubCategory3Name:"+CategoryID+")) AND (CityID:"+CityID+") AND (RestrictAge:[* TO 0 ]) OR (Alcoholic:"+Alcohol+" AND -RestrictCountry:"+RestrictCountry+")  )";
		}
        }else{
        	if(ProfileID!=null || ProfileID!="" || ProfileID!="0"){
    			queryText = "(((Category1ID:"+CategoryID+") OR (Category2ID:"+CategoryID+") OR (Category3ID:"+CategoryID+") OR (SubCategory1ID:"+CategoryID+") OR (SubCategory2ID:"+CategoryID+") OR (SubCategory3ID:"+CategoryID+") OR (Category1Name:"+CategoryID+") OR (Category2Name:"+CategoryID+") OR (Category3Name:"+CategoryID+") OR (SubCategory1Name:"+CategoryID+") OR (SubCategory2Name:"+CategoryID+") OR (SubCategory3Name:"+CategoryID+")) AND (CityID:"+CityID+") OR (RestrictAge:[* TO "+RestrictAge+" ]) AND (Alcoholic:"+Alcohol+" AND -RestrictCountry:"+RestrictCountry+" ) )";	
    		}else{
    			queryText = "(((Category1ID:"+CategoryID+") OR (Category2ID:"+CategoryID+") OR (Category3ID:"+CategoryID+") OR (SubCategory1ID:"+CategoryID+") OR (SubCategory2ID:"+CategoryID+") OR (SubCategory3ID:"+CategoryID+") OR (Category1Name:"+CategoryID+") OR (Category2Name:"+CategoryID+") OR (Category3Name:"+CategoryID+") OR (SubCategory1Name:"+CategoryID+") OR (SubCategory2Name:"+CategoryID+") OR (SubCategory3Name:"+CategoryID+")) AND (CityID:"+CityID+") OR (RestrictAge:[* TO 0 ]) AND (Alcoholic:"+Alcohol+" AND -RestrictCountry:"+RestrictCountry+" ) )";
    		}
        }*/
		
	/*	String RestAge="((RestrictAge:[* TO "+RestrictAge+"])";
		String Not=" NOT (RestrictCountry:"+RestrictCountry+")";
		String IsAlcohol=" OR (RestrictAge:1000 )";
		if(Alcohol==1)
		{
			RestAge=RestAge+IsAlcohol;
		}*/
		String MainQuery="((Category1ID:"+CategoryID+")  OR (Category2ID:"+CategoryID+") OR (Category3ID:"+CategoryID+") OR (SubCategory1ID:"+CategoryID+") OR (SubCategory2ID:"+CategoryID+") OR (SubCategory3ID:"+CategoryID+") OR (Category1Name:"+CategoryID+") OR (Category2Name:"+CategoryID+") OR (Category3Name:"+CategoryID+") OR (SubCategory1Name:"+CategoryID+") OR (SubCategory2Name:"+CategoryID+") OR (SubCategory3Name:"+CategoryID+")) AND (CityID:"+CityID+")";
		
		String privayQuery=privacyClient.commonPrivacyQuery(RestrictAge, RestrictCountry, Alcohol , ProfileID);
		
		 queryText="("+MainQuery+privayQuery+")";
			
	/*	if(ProfileID!=null && ProfileID!="" && ProfileID!="0" && Alcohol==1){
			queryText = "(((Category1ID:"+CategoryID+")  OR (Category2ID:"+CategoryID+") OR (Category3ID:"+CategoryID+") OR (SubCategory1ID:"+CategoryID+") OR (SubCategory2ID:"+CategoryID+") OR (SubCategory3ID:"+CategoryID+") OR (Category1Name:"+CategoryID+") OR (Category2Name:"+CategoryID+") OR (Category3Name:"+CategoryID+") OR (SubCategory1Name:"+CategoryID+") OR (SubCategory2Name:"+CategoryID+") OR (SubCategory3Name:"+CategoryID+")) AND (CityID:"+CityID+") AND ((RestrictAge:[* TO "+RestrictAge+"]) OR (RestrictAge:1000 ) NOT (RestrictCountry:"+RestrictCountry+")))";	
		}else {
			queryText = "(((Category1ID:"+CategoryID+")  OR (Category2ID:"+CategoryID+") OR (Category3ID:"+CategoryID+") OR (SubCategory1ID:"+CategoryID+") OR (SubCategory2ID:"+CategoryID+") OR (SubCategory3ID:"+CategoryID+") OR (Category1Name:"+CategoryID+") OR (Category2Name:"+CategoryID+") OR (Category3Name:"+CategoryID+") OR (SubCategory1Name:"+CategoryID+") OR (SubCategory2Name:"+CategoryID+") OR (SubCategory3Name:"+CategoryID+")) AND (CityID:"+CityID+") AND ((RestrictAge:[* TO "+RestrictAge+"]) NOT (RestrictCountry:"+RestrictCountry+")))";
		}*/
		
		System.out.println("QueryTEXT---->"+queryText);
		resultArr = platformPlaceControllerClient.queryChecksortBy(queryText,pageCount,size ,sortBy , request);
		numFound = resultArr[1].toString();
		contactsQuery=(List)resultArr[0];
		mav.addObject("TotalRecords",Integer.parseInt(numFound));
		mav.addObject("Collection",contactsQuery);
		String result = "success";
		mav.addObject("result",result);
		mav.addObject("IsConnected",1);
		
		return mav;
	}
	
	
	
	@RequestMapping(value = "/getplaceoftheday")
	public ModelAndView Getplaceoftheday(		HttpServletRequest request,
			@RequestParam(value = "ProfileID", required = false , defaultValue = "0" ) String ProfileID,
			@RequestParam(value = "CityID", required = false , defaultValue = "0" ) String CityID,
			@RequestParam(value = "Age", required = false  ) String RestrictAge,
			@RequestParam(value = "CountryId", required = false  ) String RestrictCountry,
			@RequestParam(value = "IsAlcohlic", required = false  ) int Alcohol){
		
		ModelAndView mav = new ModelAndView();
		
String privayQuery=privacyClient.commonPrivacyQuery(RestrictAge, RestrictCountry, Alcohol , ProfileID);
	
		
		String friendListResult2 = null;
		String 	folderListResult2 =null;
		String 	blockListResult2 = null;
		String 	canSendMessagesResult2=null;
	
		
		String friendListResult = null;
		String 	folderListResult =null;
		String 	blockListResult = null;
		String 	canSendMessagesResult=null;
		
	    try{
	    friendListResult2=mamCacheClient.mamCachefriendList(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT, ProfileID, request);
	    if (friendListResult2!=null)
	    friendListResult=friendListResult2.replace("," , " , ");
	    
	    folderListResult2=mamCacheClient.mamCachefolderList(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT, ProfileID, request);
	    if (folderListResult2!=null)
	    folderListResult=folderListResult2.replace("," , " , ");
	    
	    blockListResult2=mamCacheClient.mamCacheblockList(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT, ProfileID, request);
	    if (blockListResult2!=null)
	    blockListResult=blockListResult2.replace("," , " , ");
	    
	    canSendMessagesResult2=mamCacheClient.mamCachecanSendMessages(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT,ProfileID, request);
	    if (canSendMessagesResult2!=null)
	    canSendMessagesResult=canSendMessagesResult2.replace("," , " , ");
	    
	    }catch (Exception e) {
	    	e.printStackTrace();
		}
		
		
		
		
		String Status="3";
		String queryText = "";
		String numFound  = "0";
		Object[] resultArr = null;
		List<Place> contactsQuery = null;
		
		privayQuery=privacyClient.commonPrivacyQuery(RestrictAge, RestrictCountry, Alcohol , ProfileID);
		
		if(blockListResult!=null && blockListResult!=""){
			
			queryText = "((CityID:"+CityID+") AND (Status:"+Status+")  -ID:"+blockListResult+" )";
			
		}else{
			queryText = "((CityID:"+CityID+") AND (Status:"+Status+") )";
		}
			 queryText="("+queryText+privayQuery+")";
		
		resultArr = platformPlaceControllerClient.queryCheck(queryText,0,5 , request);
		numFound = resultArr[1].toString();
		contactsQuery=(List)resultArr[0];
		
		mav.addObject("TotalRecords",Integer.parseInt(numFound));
		mav.addObject("Collection",contactsQuery);
		String result = "success";
		mav.addObject("result",result);
		mav.addObject("IsConnected",1);
		
		return mav;
	}
	
	
	@RequestMapping(value = "/getplacebyneighbourhood")
	public ModelAndView GetPlaceByNeighbourhood(	
			HttpServletRequest request,
			@RequestParam(value = "ProfileID", required = false , defaultValue = "0" ) String ProfileID,
			@RequestParam(value = "CategoryID", required = false) String Category1ID,
			@RequestParam(value = "CityID", required = false  , defaultValue = "0" ) String CityID,
			@RequestParam(value = "PageNo", required = false) int PageNo,
			@RequestParam(value = "Age", required = false  ) String Age,
			@RequestParam(value = "CountryId", required = false ) String CountryId,
			@RequestParam(value = "IsAlcohlic", required = false  ) int Alcohol,
			@RequestParam(value = "PageSize", required = false) int PageSize,
			@RequestParam(value = "Neighbourhood", required = false) String NeighbourHoods){
		
		ModelAndView mav = new ModelAndView();
		String Status="3";
		String queryText = "";
		String numFound  = "0";
		Object[] resultArr = null;
		List<Place> contactsQuery = null;
		
	/*	if(ProfileID!=null || ProfileID!="" || ProfileID!="0"  ){
			if(Category1ID=="" || Category1ID==null ){
				queryText = "((CityID:"+CityID+")  AND (NeighbourHoods:"+NeighbourHoods+"*) AND (RestrictAge:[* TO "+RestrictAge+" ]) AND Alcoholic:"+Alcohol+" AND -RestrictCountry:"+RestrictCountry+" )";
			}else{
				queryText = "((Category1ID:"+Category1ID+") AND (CityID:"+CityID+")  AND (NeighbourHoods:"+NeighbourHoods+"*) AND (RestrictAge:[* TO "+RestrictAge+" ]) AND Alcoholic:"+Alcohol+" AND -RestrictCountry:"+RestrictCountry+" )";	
			}	
		}else{
			queryText = "((Category1ID:"+Category1ID+") AND (CityID:"+CityID+")  AND (NeighbourHoods:"+NeighbourHoods+"*) AND (RestrictAge:[* TO 0 ]) AND Alcoholic:0 AND -RestrictCountry:0 )";
		}
		*/
		
		String privayQuery=privacyClient.commonPrivacyQuery(Age, CountryId, Alcohol , ProfileID);
		
		queryText = "((CityID:"+CityID+") AND (NeighbourHoods:"+NeighbourHoods+"*))";
		
		 queryText="("+queryText+privayQuery+")";
		 
		 System.out.println("queryText-->"+queryText);
		 
		resultArr = platformPlaceControllerClient.queryCheck(queryText,PageNo,PageSize , request);
		numFound = resultArr[1].toString();
		contactsQuery=(List)resultArr[0];
		
		mav.addObject("TotalRecords",numFound);
		mav.addObject("Collection",contactsQuery);
		String result = "success";
		mav.addObject("result",result);
		mav.addObject("IsConnected",1);
		
		return mav;
	}
	
	
	
	@RequestMapping(value = "/getPlaceByFeatureType")
	public ModelAndView GetPlaceByFeatureType(			
			HttpServletRequest request,
			@RequestParam(value = "CategoryID", required = false) String Category1ID,
			@RequestParam(value = "CityID", required = false  , defaultValue = "0" ) String CityID,
			@RequestParam(value = "member_id", required = false , defaultValue = "0") String memberId,
			@RequestParam(value = "Age", required = false) String Age,
			@RequestParam(value = "CountryId", required = false  , defaultValue = "0") String CountryId,
			@RequestParam(value = "IsAlcohlic", required = false ,  defaultValue = "0") int IsAlcohlic,
			@RequestParam(value = "PageNo", required = false) int PageNo,
			@RequestParam(value = "PageSize", required = false) int PageSize,
			@RequestParam(value = "Type", required = false) int Type,
			@RequestParam(value = "SortBy", required = false) String SortBy){
		
		ModelAndView mav = new ModelAndView();
		
		String privayQuery=privacyClient.commonPrivacyQuery(Age, CountryId, IsAlcohlic , memberId);
		
		String friendListResult2 = null;
		String 	folderListResult2 =null;
		String 	blockListResult2 = null;
		String 	canSendMessagesResult2=null;
	
		
		String friendListResult = null;
		String 	folderListResult =null;
		String 	blockListResult = null;
		String 	canSendMessagesResult=null;
		
	    try{
	    friendListResult2=mamCacheClient.mamCachefriendList(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT, memberId, request);
	    if (friendListResult2!=null)
	    friendListResult=friendListResult2.replace("," , " , ");
	    
	    folderListResult2=mamCacheClient.mamCachefolderList(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT, memberId, request);
	    if (folderListResult2!=null)
	    folderListResult=folderListResult2.replace("," , " , ");
	    
	    blockListResult2=mamCacheClient.mamCacheblockList(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT, memberId, request);
	    if (blockListResult2!=null)
	    blockListResult=blockListResult2.replace("," , " , ");
	    
	    canSendMessagesResult2=mamCacheClient.mamCachecanSendMessages(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT,memberId, request);
	    if (canSendMessagesResult2!=null)
	    canSendMessagesResult=canSendMessagesResult2.replace("," , " , ");
	    
	    }catch (Exception e) {
	    	e.printStackTrace();
		}
		
		String Status="3";
		String queryText = "";
		String numFound  = "0";
		Object[] resultArr = null;
		List<Place> contactsQuery = null;
		String sort1="";
		String sort2="";
		String sort3="";
		String order1="";
		String order2="";
		String order3="";
		
		int getType = Type;
        String PlaceFetaureType;
		int value=0;
		switch (getType) {
        case 1:  PlaceFetaureType = "AcceptsCreditCards";    value=1; 	 break;
        case 2:  PlaceFetaureType = "WheelChairAccessible";  value=1;    break;
        case 3:  PlaceFetaureType = "GoodForKids";       	 value=1; 	 break;
        case 4:  PlaceFetaureType = "GoodForGroups";         value=1; 	 break;
        case 5:  PlaceFetaureType = "PriceRange";            value=1;	 break;
        case 6:  PlaceFetaureType = "PriceRange";         	 value=2;	 break;
        case 7:  PlaceFetaureType = "PriceRange";         	 value=3;	 break;
        case 8:  PlaceFetaureType = "PriceRange";        	 value=4;	 break;
        case 9:  PlaceFetaureType = "IsParkingStreet";     	 value=1;	 break;
        case 10: PlaceFetaureType = "IsParkingGarage";       value=1;    break;
        case 11: PlaceFetaureType = "IsParkingValet";        value=1;    break;
        case 12: PlaceFetaureType = "IsParkingPrivateLot";   value=1;    break;
        case 13: PlaceFetaureType = "IsParkingValidated";    value=1;    break;
        case 14: PlaceFetaureType = "ByAppointmentOnly";     value=1;    break;
        default: PlaceFetaureType = "Invalid TypeID";        value=1;    break;
    }
		if(Category1ID=="" || Category1ID==null ){
			
			queryText = "((CityID:"+CityID+")  AND ("+PlaceFetaureType+":"+Type+") )";				
			privayQuery=privacyClient.commonPrivacyQuery(Age, CountryId, IsAlcohlic , memberId);
			queryText="("+queryText+privayQuery+")";
			
		}else{
			
			queryText = "((Category1ID:"+Category1ID+")  AND (CityID:"+CityID+") AND ("+PlaceFetaureType+":"+value+") )";			
			privayQuery=privacyClient.commonPrivacyQuery(Age, CountryId, IsAlcohlic , memberId);
			queryText="("+queryText+privayQuery+")";
		}
		if(SortBy.equals("Name")){
			sort1="Name";			
			sort2="Rating";
			sort3="TotalReviews";
			order1="asc";
			order2="desc";
			order3="desc";
			
		}else if(SortBy.equals("Rating")){
			
			sort1="Rating";
			sort2="Name";
			sort3="TotalReviews";
			order1="desc";
			order2="asc";
			order3="desc";
		}else{
			sort1="TotalReviews";
			sort2="Name";
			sort3="Rating";
			order1="desc";
			order2="asc";
			order3="desc";
		}
		
		resultArr = platformPlaceControllerClient.queryCheckSort(queryText,PageNo,PageSize , sort1 , sort2 ,sort3 ,order1, order2 , order3 , request );
		numFound = resultArr[1].toString();
		contactsQuery=(List)resultArr[0];
		
		mav.addObject("TotalRecords",numFound);
		mav.addObject("Collection",contactsQuery);
		String result = "success";
		mav.addObject("result",result);
		mav.addObject("IsConnected",1);
		
		return mav;
	}
	
	
	
	
	@RequestMapping(value = "/getplacebyname")
	public ModelAndView GetPlaceByName(
			HttpServletRequest request,
			@RequestParam(value = "CategoryID", required = false) String CategoryID,
			@RequestParam(value = "member_id", required = false , defaultValue = "0") String memberId,
			@RequestParam(value = "Age", required = false) String Age,
			@RequestParam(value = "CityID", required = false) String CityID,
			@RequestParam(value = "PageNo", required = false) int page,
			@RequestParam(value = "PageSize", required = false) int size,
			@RequestParam(value = "IsAlcohlic", required = false ,  defaultValue = "0") int IsAlcohlic,
			@RequestParam(value = "CountryId", required = false  , defaultValue = "0") String CountryId,
			@RequestParam(value = "Keyword", required = false) String memberName,	
			@RequestParam(value = "SortBy", required = false) String SortBy,
			@RequestParam(value = "jsoncallback", required = false) String search){
		
		ModelAndView mav = new ModelAndView();
		
		String privayQuery=privacyClient.commonPrivacyQuery(Age, CountryId, IsAlcohlic , memberId);
	
		
		String friendListResult2 = null;
		String 	folderListResult2 =null;
		String 	blockListResult2 = null;
		String 	canSendMessagesResult2=null;
	
		
		String friendListResult = null;
		String 	folderListResult =null;
		String 	blockListResult = null;
		String 	canSendMessagesResult=null;
		
	    try{
	    friendListResult2=mamCacheClient.mamCachefriendList(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT, memberId, request);
	    if (friendListResult2!=null)
	    friendListResult=friendListResult2.replace("," , " , ");
	    
	    folderListResult2=mamCacheClient.mamCachefolderList(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT, memberId, request);
	    if (folderListResult2!=null)
	    folderListResult=folderListResult2.replace("," , " , ");
	    
	    blockListResult2=mamCacheClient.mamCacheblockList(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT, memberId, request);
	    if (blockListResult2!=null)
	    blockListResult=blockListResult2.replace("," , " , ");
	    
	    canSendMessagesResult2=mamCacheClient.mamCachecanSendMessages(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT,memberId, request);
	    if (canSendMessagesResult2!=null)
	    canSendMessagesResult=canSendMessagesResult2.replace("," , " , ");
	    
	    }catch (Exception e) {
	    	e.printStackTrace();
		}
	
		
		
			memberName = memberName.toLowerCase();
			String[] memberNameArr  = memberName.split("@");
			String queryText = "";
			int pageCount = page * size;
			List contactsQuery = null;
			String numFound  = "0";
			Object[] resultArr = null;
			String sort1="";
			String sort2="";
			String sort3="";
			String order1="";
			String order2="";
			String order3="";
			
			
			if(SortBy.equals("Name")){
				sort1="Name";			
				sort2="Rating";
				sort3="TotalReviews";
				order1="asc";
				order2="desc";
				order3="desc";
				
			}else if(SortBy.equals("Rating")){
				
				sort1="Rating";
				sort2="Name";
				sort3="TotalReviews";
				order1="desc";
				order2="asc";
				order3="desc";
			}else{
				sort1="TotalReviews";
				sort2="Name";
				sort3="Rating";
				order1="desc";
				order2="asc";
				order3="desc";
			}
			
			String numberCheck="(NameCopy:1* OR NameCopy:2* OR NameCopy:3* OR NameCopy:4* OR NameCopy:5* OR NameCopy:6* OR NameCopy:7* OR NameCopy:8* OR NameCopy:9* OR NameCopy:0*)";
			
			if(memberName.equals("0123456789")){
			
				if(CategoryID.equals("") || !CategoryID.equals(null)){
					
					
					queryText = "((NameCopy:1* AND CityID:"+CityID+") OR (NameCopy:2* AND CityID:"+CityID+") OR (NameCopy:3* AND CityID:"+CityID+")OR (NameCopy:4* AND CityID:"+CityID+")OR (NameCopy:5* AND CityID:"+CityID+")OR (NameCopy:6* AND CityID:"+CityID+")OR (NameCopy:7* AND CityID:"+CityID+")OR (NameCopy:8* AND CityID:"+CityID+")OR (NameCopy:9* AND CityID:"+CityID+")OR (NameCopy:0* AND CityID:"+CityID+") )";
					privayQuery=privacyClient.commonPrivacyQuery(Age, CountryId, IsAlcohlic , memberId);
					
					queryText="("+queryText+privayQuery+")";
					
				
				}else{
				//	queryText = "(((Name:"+memberNameArr[0] +"*) AND (CityID:"+CityID+")) OR (Category1ID:"+CategoryID+")  OR (Category2ID:"+CategoryID+") OR (Category3ID:"+CategoryID+") OR (SubCategory1ID:"+CategoryID+") OR (SubCategory2ID:"+CategoryID+") OR (SubCategory3ID:"+CategoryID+") )";
					
					queryText = "(("+numberCheck+" AND CityID:"+CityID+" AND Category1ID:"+CategoryID+") OR ("+numberCheck+" AND CityID:"+CityID+" AND Category2ID:"+CategoryID+")   OR ("+numberCheck+" AND CityID:"+CityID+" AND Category3ID:"+CategoryID+") OR ("+numberCheck+" AND CityID:"+CityID+" AND SubCategory1ID:"+CategoryID+") OR  ("+numberCheck+" AND CityID:"+CityID+" AND SubCategory2ID:"+CategoryID+")OR ("+numberCheck+" AND CityID:"+CityID+" AND SubCategory3ID:"+CategoryID+"))";
					
					privayQuery=privacyClient.commonPrivacyQuery(Age, CountryId, IsAlcohlic , memberId);
					
					queryText="("+queryText+privayQuery+")";
					
					System.out.println("--------->"+queryText);
				}			
				
			}else{			
			if(CategoryID=="" || CategoryID==null){
				queryText = "((NameCopy:"+memberNameArr[0] +"*) AND (CityID:"+CityID+") )";
			System.out.println("--------->"+queryText);
			}else{
				queryText = "((NameCopy:"+memberNameArr[0] +"* AND CityID:"+CityID+" AND Category1ID:"+CategoryID+")  OR  ((NameCopy:"+memberNameArr[0] +"* AND CityID:"+CityID+" AND Category2ID:"+CategoryID+")) OR ((NameCopy:"+memberNameArr[0] +"* AND CityID:"+CityID+" AND Category3ID:"+CategoryID+")) OR  ((NameCopy:"+memberNameArr[0] +"* AND CityID:"+CityID+" AND SubCategory1ID:"+CategoryID+")) OR ((NameCopy:"+memberNameArr[0] +"* AND CityID:"+CityID+" AND SubCategory2ID:"+CategoryID+")) OR ((NameCopy:"+memberNameArr[0] +"* AND CityID:"+CityID+" AND SubCategory3ID:"+CategoryID+")))";
				
				privayQuery=privacyClient.commonPrivacyQuery(Age, CountryId, IsAlcohlic , memberId);
				
				queryText="("+queryText+privayQuery+")";
				System.out.println("--------->"+queryText);
			}
			
			}
			resultArr = platformPlaceControllerClient.queryChecksortBy(queryText,pageCount,size ,SortBy , request);
			numFound = resultArr[1].toString();//contactList.getResponse().getNumFound().toString();
			contactsQuery =(List) resultArr[0] ;
			
			List  fullContacts = contactsQuery;
			mav.addObject("TotalRecords",Integer.parseInt(numFound));
			mav.addObject("Collection",contactsQuery);
			String result = "success";
			mav.addObject("result",result);
			mav.addObject("IsConnected","true");
				return mav;
	}
	
	
	

	@RequestMapping(value = "/getbyplaceId")
	public ModelAndView getByPlaceId(
			HttpServletRequest request,
			@RequestParam(value = "PlaceId", required = false) String PlaceId		
			){
			ModelAndView mav = new ModelAndView();
			Object[] resultArr = null;
			List<Place> place = new ArrayList<Place>();
			String myPlansQueryText = "(SearchDB:"+PlaceId+") ";
			List<Place> questions = new ArrayList<Place>();
			resultArr =  platformPlaceControllerClient.queryCheck(myPlansQueryText,0,10 , request);
			
			place      = (List<Place>) resultArr[0];
			
				try{
					mav.addObject("ID",place.get(0).getID());	            
		        	mav.addObject("Category1ID",place.get(0).getCategory1ID());	            	
		        	mav.addObject("Category1ID",place.get(0).getCategory1ID());
		        	mav.addObject("Tags",place.get(0).getCategory2ID());
		        	mav.addObject("Category3ID",place.get(0).getCategory3ID());
		        	mav.addObject("Category3ID",place.get(0).getSubCategory1ID());
		        	mav.addObject("SubCategory2ID",place.get(0).getSubCategory2ID());
		        	mav.addObject("SubCategory3ID",place.get(0).getSubCategory3ID());
		        	mav.addObject("Category1Name",place.get(0).getCategory1Name());
		        	mav.addObject("SubCategory2ID",place.get(0).getSubCategory2ID());
		        	mav.addObject("Category2Name",place.get(0).getCategory2Name());	            	
		        	mav.addObject("Category3Name",place.get(0).getCategory3Name());
		        	mav.addObject("SubCategory1Name",place.get(0).getSubCategory1Name());
		        	mav.addObject("SubCategory2Name",place.get(0).getSubCategory2Name());
		        	mav.addObject("SubCategory3Name",place.get(0).getSubCategory3Name());
		         	mav.addObject("Name",place.get(0).getName());	  
		         	mav.addObject("ScreenName",place.get(0).getScreenName());	
		         	mav.addObject("ScreenNameStatus",place.get(0).getScreenNameStatus());
		         	mav.addObject("ProfileImage",place.get(0).getProfileImage());
		         	mav.addObject("ProfileFileID",place.get(0).getProfileFileID());
		         	mav.addObject("Email",place.get(0).getEmail());	
		         	mav.addObject("Address1",place.get(0).getAddress1());	
		         	mav.addObject("Address2",place.get(0).getAddress2());
		         	mav.addObject("City",place.get(0).getCity());
		         	mav.addObject("State",place.get(0).getState());	
		         	mav.addObject("Country",place.get(0).getCountry());
		         	mav.addObject("CityID",place.get(0).getCityID());
		         	mav.addObject("StateID",place.get(0).getStateID());
		         	mav.addObject("CountryID",place.get(0).getCountryID());	
		         	mav.addObject("ZipCode",place.get(0).getZipCode());
		         	mav.addObject("Phone",place.get(0).getPhone());	
		         	mav.addObject("WebAddress",place.get(0).getWebAddress());
		         	mav.addObject("Blog",place.get(0).getBlog());	
		         	mav.addObject("NeighbourHoods",place.get(0).getNeighbourHoods());	
		         	mav.addObject("NearestTransits",place.get(0).getNearestTransits());
		         	mav.addObject("Rating",place.get(0).getRating());	
		         	mav.addObject("TotalReviews",place.get(0).getTotalReviews());	
		         	mav.addObject("Description",place.get(0).getDescription());
		         	mav.addObject("Products",place.get(0).getProducts());	
		        	mav.addObject("Policy",place.get(0).getPolicy());	
		          	mav.addObject("Role",place.get(0).getRole());	
		          	mav.addObject("PriceRange",place.get(0).getPriceRange());
		          	mav.addObject("AcceptsCreditCards",place.get(0).getAcceptsCreditCards());
		          	mav.addObject("WheelChairAccessible",place.get(0).getWheelChairAccessible());
		        	mav.addObject("GoodForKids",place.get(0).getGoodForKids());
		        	mav.addObject("GoodForGroups",place.get(0).getGoodForGroups());
		        	mav.addObject("WaiterService",place.get(0).getWaiterService());
		        	mav.addObject("Attire",place.get(0).getAttire());
		        	mav.addObject("OutdoorSeating",place.get(0).getOutdoorSeating());
		        //	mav.addObject("Alcohol",place.get(0).getAlcohol());
		        	mav.addObject("TakeOut",place.get(0).getTakeOut());
		        	mav.addObject("TakesReservation",place.get(0).getTakesReservation());
		        	mav.addObject("ByAppointmentOnly",place.get(0).getByAppointmentOnly());
		        	mav.addObject("Music",place.get(0).getMusic());
		        	mav.addObject("BestNights",place.get(0).getBestNights());
		        	mav.addObject("Smoking",place.get(0).getSmoking());
		        	mav.addObject("CoatCheck",place.get(0).getCoatCheck());
		        	mav.addObject("HappyHour",place.get(0).getHappyHour());
		        	mav.addObject("WiFi",place.get(0).getWiFi());
		        	mav.addObject("IsParkingStreet",place.get(0).getIsParkingStreet());
		        	mav.addObject("IsParkingGarage",place.get(0).getIsParkingGarage());
		        	mav.addObject("IsParkingValet",place.get(0).getIsParkingValet());
		        	mav.addObject("IsParkingPrivateLot",place.get(0).getIsParkingPrivateLot());
		        	mav.addObject("IsParkingValidated",place.get(0).getIsParkingValidated());
		        	mav.addObject("IsMealBreakfast",place.get(0).getIsMealBreakfast());
		        	mav.addObject("IsMealBrunch",place.get(0).getIsMealBrunch());
		        	mav.addObject("IsMealLunch",place.get(0).getIsMealLunch());
		        	mav.addObject("IsMealDinner",place.get(0).getIsMealDinner());
		        	mav.addObject("IsMealLateNight",place.get(0).getIsMealLateNight());
		        	mav.addObject("IsMealDessert",place.get(0).getIsMealDessert());
		        	mav.addObject("IsGoodForMealBreakfast",place.get(0).getIsGoodForMealBreakfast());
		        	mav.addObject("IsGoodForMealBrunch",place.get(0).getIsGoodForMealBrunch());
		        	mav.addObject("IsGoodForMealLunch",place.get(0).getIsGoodForMealLunch());
		        	mav.addObject("IsGoodForMealDinner",place.get(0).getIsGoodForMealDinner());
		        	mav.addObject("IsGoodForMealLateNight",place.get(0).getIsGoodForMealLateNight());
		        	mav.addObject("IsGoodForMealDessert",place.get(0).getIsGoodForMealDessert());
		          	mav.addObject("CreatedDate",place.get(0).getCreatedDate());	
		          	mav.addObject("ApprovalDate",place.get(0).getApprovalDate());	
		          	mav.addObject("UpdatedDate",place.get(0).getUpdatedDate());	
		          	mav.addObject("DeletionDate",place.get(0).getDeletionDate());	
		          	mav.addObject("LastLoginDate",place.get(0).getLastLoginDate());	
		          	mav.addObject("IsFeatured",place.get(0).getIsFeatured());
		          	mav.addObject("OnlineStatus",place.get(0).getOnlineStatus());	
		          	mav.addObject("WhoCanSeeInSearch",place.get(0).getWhoCanSeeInSearch());	
		          	mav.addObject("ShowWelcomePage",place.get(0).getShowWelcomePage());	
		          	mav.addObject("ShowCompleteStatus",place.get(0).getShowCompleteStatus());
		          	mav.addObject("IsFundermailSent",place.get(0).getIsFundermailSent());
		          	mav.addObject("ContactDB",place.get(0).getContactDB());
		          	mav.addObject("UpdateDB",place.get(0).getUpdateDB());
		          	mav.addObject("MemberInfoDB",place.get(0).getMemberInfoDB());
		          	
		          	
		          	mav.addObject("ProfileFileJson",place.get(0).getProfileFileJson());
		          	
		          	mav.addObject("CommentDB",place.get(0).getCommentDB());
		          	mav.addObject("MemberContentDB",place.get(0).getMemberContentDB());
		          	mav.addObject("MessageDB",place.get(0).getMessageDB());
		          	mav.addObject("FolderDB",place.get(0).getFolderDB());
		          	mav.addObject("NotificationSettingDB",place.get(0).getNotificationSettingDB());
		          	mav.addObject("SearchDB",place.get(0).getSearchDB());
		          	mav.addObject("Status",place.get(0).getStatus());
		          	mav.addObject("RestrictAge",place.get(0).getRestrictAge());
		          	mav.addObject("RestrictCountry",place.get(0).getRestrictCountry());
		          	mav.addObject("RestrictPost",place.get(0).getRestrictPost());				

				}catch (Exception e) {
					e.printStackTrace();
					return mav;
				}
			
				
				return mav;
	}
	
	@RequestMapping(value = "/findplacesAll")
	public @ResponseBody CollectionList findPlacesAll(HttpServletRequest request,
			@RequestParam(value = "member_id", required = false) String memberId,
			@RequestParam(value = "member_name", required = false) String memberName,
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "Size", required = false ,  defaultValue = "10") int Size,
			@RequestParam(value = "jsoncallback", required = false) String search)
	{
		
		ModelAndView mav = new ModelAndView();
		
			memberName = memberName.toLowerCase();
			String[] memberNameArr  = memberName.split("@");
			String queryText = "";
			int pageCount = page * 10;
			List<Place> contactsQuery = null;
			String numFound  = "0";
			CharSequence charseq = "@";
			Object[] resultArr = null;
			if(memberName.contains(charseq))
			{
				String encodedurl = "";
				queryText = "(Email:"+memberName+"*)";
				resultArr = platformPlaceControllerClient.HeaderPlace(queryText,pageCount,Size , request);
				numFound = resultArr[1].toString();
				contactsQuery =(List<Place>) resultArr[0] ;
			}
			else
			{
				queryText = "(NameCopy:"+memberNameArr[0] +"*)";
				resultArr = platformPlaceControllerClient.HeaderPlace(queryText,pageCount,Size , request);
				numFound = resultArr[1].toString();//contactList.getResponse().getNumFound().toString();
				contactsQuery =(List<Place>) resultArr[0] ;
			}
			
			List <Place> fullContacts = contactsQuery;
			if(fullContacts.size() > 0)
			{
					CollectionList coll = new CollectionList();
					coll.setCollection(contactsQuery);
					coll.setStatusOutput("true");
					coll.setTotalRecords(Long.parseLong(resultArr[1].toString()));
					//String res = JsonUtils.jsonFromObject(coll);
					return coll;
					//mav.addObject(res);
			}
			else
			{
				contactsQuery = new ArrayList<Place>();
				CollectionList coll = new CollectionList();
				coll.setCollection(contactsQuery);
				coll.setStatusOutput("true");
				coll.setTotalRecords(0L);
				//String res = JsonUtils.jsonFromObject(coll);
				return coll;
			}
		
	}
	
	
	
	
	
	@RequestMapping(value = "/findplaceautocomplete")
	public @ResponseBody CollectionList  findplaceautocomplete(HttpServletRequest request,
			@RequestParam(value = "starting_letter", required = false) String memberName,
			@RequestParam(value = "ProfileID", required = false , defaultValue = "0") String memberId,
			@RequestParam(value = "Age", required = false) String Age,
			@RequestParam(value = "CountryId", required = false  , defaultValue = "0") String CountryId,
			@RequestParam(value = "IsAlcohlic", required = false ,  defaultValue = "0") int IsAlcohlic, 
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "size", required = false) int size,
			@RequestParam(value = "jsoncallback", required = false) String search){
		
		ModelAndView mav = new ModelAndView();
		
		String privayQuery=privacyClient.commonPrivacyQuery(Age, CountryId, IsAlcohlic , memberId);
		
		String friendListResult2 = null;
		String 	folderListResult2 =null;
		String 	blockListResult2 = null;
		String 	canSendMessagesResult2=null;

		
		String friendListResult = null;
		String 	folderListResult =null;
		String 	blockListResult = null;
		String 	canSendMessagesResult=null;
		
	    try{
	    friendListResult2=mamCacheClient.mamCachefriendList(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT, memberId, request);
	    if (friendListResult2!=null)
	    friendListResult=friendListResult2.replace("," , " , ");
	    
	    folderListResult2=mamCacheClient.mamCachefolderList(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT, memberId, request);
	    if (folderListResult2!=null)
	    folderListResult=folderListResult2.replace("," , " , ");
	    
	    blockListResult2=mamCacheClient.mamCacheblockList(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT, memberId, request);
	    if (blockListResult2!=null)
	    blockListResult=blockListResult2.replace("," , " , ");
	    
	    canSendMessagesResult2=mamCacheClient.mamCachecanSendMessages(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT,memberId, request);
	    if (canSendMessagesResult2!=null)
	    canSendMessagesResult=canSendMessagesResult2.replace("," , " , ");
	    
	    }catch (Exception e) {
	    	e.printStackTrace();
		}
			

		
		
		
			memberName = memberName.toLowerCase();
			String[] memberNameArr  = memberName.split("@");
			String queryText = "";
			int pageCount = page * size;
			List contactsQuery = null;
			String numFound  = "0";
			Object[] resultArr = null;
		//	queryText = "(Name:"+memberNameArr[0] +"*)";
			
			//New query only for the directory searh
			
			queryText = "(NameForDirectorySearch:"+memberNameArr[0] +"*)";
			
			if(memberName.equals("0123456789")){
				queryText="(NameForDirectorySearch:1* OR NameForDirectorySearch:2* OR NameForDirectorySearch:3* OR NameForDirectorySearch:4* OR NameForDirectorySearch:5* OR NameForDirectorySearch:6* OR NameForDirectorySearch:7* OR NameForDirectorySearch:8* OR NameForDirectorySearch:9* OR NameForDirectorySearch:0*)";
				}
			
			
			queryText="("+queryText+privayQuery+")";
			
			System.out.println("queryText------->"+queryText);
			
			resultArr = platformPlaceControllerClient.findplaceautocompleteCheck(queryText,pageCount,size , request);
			numFound = resultArr[1].toString();//contactList.getResponse().getNumFound().toString();
			contactsQuery =(List) resultArr[0] ;
			
			List  fullContacts = contactsQuery;
			if(fullContacts.size() > 0)
			{
				if(search == null)
				{
					CollectionList coll = new CollectionList();
					coll.setCollection(contactsQuery);
					coll.setStatusOutput("true");
					coll.setTotalRecords(Long.parseLong(resultArr[1].toString()));
					return coll;
				}
				else
				{
					ResultDoc resultDoc = new ResultDoc();
					resultDoc.setSize(contactsQuery.size());
					mav.addObject(resultDoc);
				}
			}
			else
			{
				contactsQuery = new ArrayList();
				CollectionList coll = new CollectionList();
				coll.setCollection(contactsQuery);
				coll.setStatusOutput("true");
				coll.setTotalRecords(0L);
				return coll;
			}
				return null;
	}
	
	
	
	@RequestMapping(value = "/findplacebyid")
	public @ResponseBody CollectionList  findplacebyid(HttpServletRequest request,
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "ProfileID", required = false , defaultValue = "0") String memberId,
			@RequestParam(value = "Age", required = false) String Age,
			@RequestParam(value = "CountryId", required = false  , defaultValue = "0") String CountryId,
			@RequestParam(value = "IsAlcohlic", required = false ,  defaultValue = "0") int IsAlcohlic, 
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "size", required = false) int size,
			@RequestParam(value = "jsoncallback", required = false) String search){
		
		ModelAndView mav = new ModelAndView();
		
		String privayQuery=privacyClient.commonPrivacyQuery(Age, CountryId, IsAlcohlic , memberId);
		
		String friendListResult2 = null;
		String 	folderListResult2 =null;
		String 	blockListResult2 = null;
		String 	canSendMessagesResult2=null;

		
		String friendListResult = null;
		String 	folderListResult =null;
		String 	blockListResult = null;
		String 	canSendMessagesResult=null;
		
	    try{
	    friendListResult2=mamCacheClient.mamCachefriendList(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT, memberId, request);
	    if (friendListResult2!=null)
	    friendListResult=friendListResult2.replace("," , " , ");
	    
	    folderListResult2=mamCacheClient.mamCachefolderList(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT, memberId, request);
	    if (folderListResult2!=null)
	    folderListResult=folderListResult2.replace("," , " , ");
	    
	    blockListResult2=mamCacheClient.mamCacheblockList(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT, memberId, request);
	    if (blockListResult2!=null)
	    blockListResult=blockListResult2.replace("," , " , ");
	    
	    canSendMessagesResult2=mamCacheClient.mamCachecanSendMessages(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT,memberId, request);
	    if (canSendMessagesResult2!=null)
	    canSendMessagesResult=canSendMessagesResult2.replace("," , " , ");
	    
	    }catch (Exception e) {
	    	e.printStackTrace();
		}
			

		
		
		
			
			String queryText = "";
			int pageCount = page * size;
			List contactsQuery = null;
			String numFound  = "0";
			Object[] resultArr = null;
		//	queryText = "(Name:"+memberNameArr[0] +"*)";
			
			//New query only for the directory searh
			
			queryText = "((Category1ID:"+id +") OR (Category2ID:"+id +") OR (Category3ID:"+id +") )";
			
			
			
			queryText="("+queryText+privayQuery+")";
			
			System.out.println("queryText------->"+queryText);
			
			resultArr = platformPlaceControllerClient.findplaceautocompleteCheck(queryText,pageCount,size , request);
			numFound = resultArr[1].toString();//contactList.getResponse().getNumFound().toString();
			contactsQuery =(List) resultArr[0] ;
			
			List  fullContacts = contactsQuery;
			if(fullContacts.size() > 0)
			{
				if(search == null)
				{
					CollectionList coll = new CollectionList();
					coll.setCollection(contactsQuery);
					coll.setStatusOutput("true");
					coll.setTotalRecords(Long.parseLong(resultArr[1].toString()));
					return coll;
				}
				else
				{
					ResultDoc resultDoc = new ResultDoc();
					resultDoc.setSize(contactsQuery.size());
					mav.addObject(resultDoc);
				}
			}
			else
			{
				contactsQuery = new ArrayList();
				CollectionList coll = new CollectionList();
				coll.setCollection(contactsQuery);
				coll.setStatusOutput("true");
				coll.setTotalRecords(0L);
				return coll;
			}
				return null;
	}
	
	
	
	@RequestMapping(value = "/findeducationschool")
	public @ResponseBody CollectionList  findeducationschool(HttpServletRequest request,
			@RequestParam(value = "type", required = false , defaultValue = "0") String type,
			@RequestParam(value = "name", required = false , defaultValue = "0") String name,
			@RequestParam(value = "ProfileID", required = false , defaultValue = "0") String memberId,
			@RequestParam(value = "Age", required = false) String Age,
			@RequestParam(value = "CountryId", required = false  , defaultValue = "0") String CountryId,
			@RequestParam(value = "IsAlcohlic", required = false ,  defaultValue = "0") int IsAlcohlic, 
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "size", required = false) int size,
			@RequestParam(value = "jsoncallback", required = false) String search){
		
		ModelAndView mav = new ModelAndView();
		
		String privayQuery=privacyClient.commonPrivacyQuery(Age, CountryId, IsAlcohlic , memberId);
		
		String friendListResult2 = null;
		String 	folderListResult2 =null;
		String 	blockListResult2 = null;
		String 	canSendMessagesResult2=null;

		
		String friendListResult = null;
		String 	folderListResult =null;
		String 	blockListResult = null;
		String 	canSendMessagesResult=null;
		
	    try{
	    friendListResult2=mamCacheClient.mamCachefriendList(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT, memberId, request);
	    if (friendListResult2!=null)
	    friendListResult=friendListResult2.replace("," , " , ");
	    
	    folderListResult2=mamCacheClient.mamCachefolderList(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT, memberId, request);
	    if (folderListResult2!=null)
	    folderListResult=folderListResult2.replace("," , " , ");
	    
	    blockListResult2=mamCacheClient.mamCacheblockList(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT, memberId, request);
	    if (blockListResult2!=null)
	    blockListResult=blockListResult2.replace("," , " , ");
	    
	    canSendMessagesResult2=mamCacheClient.mamCachecanSendMessages(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT,memberId, request);
	    if (canSendMessagesResult2!=null)
	    canSendMessagesResult=canSendMessagesResult2.replace("," , " , ");
	    
	    }catch (Exception e) {
	    	e.printStackTrace();
		}
			

		
		
		
			
			String queryText = "";
			int pageCount = page * size;
			List contactsQuery = null;
			String numFound  = "0";
			Object[] resultArr = null;
			String 	Name = "(NameCopy:"+name.toLowerCase()+"*) AND";
			
			//New query only for the directory searh
			
			String SubCategory1ID="(SubCategory1ID:98 OR SubCategory1ID:99 OR SubCategory1ID:442 OR SubCategory1ID:444 OR SubCategory1ID:446 OR SubCategory1ID:447 OR SubCategory1ID:448 OR SubCategory1ID:617 OR SubCategory1ID:625)";
			String SubCategory2ID="(SubCategory2ID:98 OR SubCategory2ID:99 OR SubCategory2ID:442 OR SubCategory2ID:444 OR SubCategory2ID:446 OR SubCategory2ID:447 OR SubCategory2ID:448 OR SubCategory2ID:617 OR SubCategory2ID:625)";
			String SubCategory3ID="(SubCategory3ID:98 OR SubCategory3ID:99 OR SubCategory3ID:442 OR SubCategory3ID:444 OR SubCategory3ID:446 OR SubCategory3ID:447 OR SubCategory3ID:448 OR SubCategory3ID:617 OR SubCategory3ID:625)";
			
			
			String SubCategory1IDSchool="(SubCategory1ID:97 OR SubCategory1ID:100 OR SubCategory1ID:441 OR SubCategory1ID:443 OR SubCategory1ID:445 OR SubCategory1ID:616 OR SubCategory1ID:625 )";
			String SubCategory2IDSchool="(SubCategory2ID:97 OR SubCategory2ID:100 OR SubCategory2ID:441 OR SubCategory2ID:443 OR SubCategory2ID:445 OR SubCategory2ID:616 OR SubCategory2ID:625 )";
			String SubCategory3IDSchool="(SubCategory3ID:97 OR SubCategory3ID:100 OR SubCategory3ID:441 OR SubCategory3ID:443 OR SubCategory3ID:445 OR SubCategory3ID:616 OR SubCategory3ID:625 )";
			
			if(type.equals("1"))
			queryText=Name+SubCategory1IDSchool+SubCategory2IDSchool+SubCategory3IDSchool;
			else
				queryText=Name+SubCategory1ID+SubCategory2ID+SubCategory3ID;
			
			
		//	queryText="("+queryText+privayQuery+")";
			
			queryText="("+queryText+")";
			
			System.out.println("queryText------->"+queryText);
			
			resultArr = platformPlaceControllerClient.findplaceautocompleteCheck(queryText,pageCount,size , request);
			numFound = resultArr[1].toString();//contactList.getResponse().getNumFound().toString();
			contactsQuery =(List) resultArr[0] ;
			
			List  fullContacts = contactsQuery;
			if(fullContacts.size() > 0)
			{
				if(search == null)
				{
					CollectionList coll = new CollectionList();
					coll.setCollection(contactsQuery);
					coll.setStatusOutput("true");
					coll.setTotalRecords(Long.parseLong(resultArr[1].toString()));
					return coll;
				}
				else
				{
					ResultDoc resultDoc = new ResultDoc();
					resultDoc.setSize(contactsQuery.size());
					mav.addObject(resultDoc);
				}
			}
			else
			{
				contactsQuery = new ArrayList();
				CollectionList coll = new CollectionList();
				coll.setCollection(contactsQuery);
				coll.setStatusOutput("true");
				coll.setTotalRecords(0L);
				return coll;
			}
				return null;
	}


	@RequestMapping(value = "/findplacesbyCityId")
	public @ResponseBody CollectionList findplacesbyCityId(HttpServletRequest request,
			@RequestParam(value = "CityID", required = false) String CityID,
			@RequestParam(value = "CategoryID", required = false) String CategoryID,
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "Size", required = false ,  defaultValue = "10") int Size,
			@RequestParam(value = "jsoncallback", required = false) String search)
	{
		
		ModelAndView mav = new ModelAndView();
		
			
			
			String queryText = "";
			int pageCount = page * 10;
			List<Place> contactsQuery = null;
			String numFound  = "0";
			CharSequence charseq = "@";
			Object[] resultArr = null;
			
				queryText = "(CityID:"+CityID +" AND (Category1ID:"+CategoryID+" OR Category2ID:"+CategoryID+" OR Category3ID:"+CategoryID+" ))";
				resultArr = platformPlaceControllerClient.HeaderPlace(queryText,pageCount,Size , request);
				numFound = resultArr[1].toString();//contactList.getResponse().getNumFound().toString();
				contactsQuery =(List<Place>) resultArr[0] ;
			
			
			List <Place> fullContacts = contactsQuery;
			if(fullContacts.size() > 0)
			{
					CollectionList coll = new CollectionList();
					coll.setCollection(contactsQuery);
					coll.setStatusOutput("true");
					coll.setTotalRecords(Long.parseLong(resultArr[1].toString()));
					//String res = JsonUtils.jsonFromObject(coll);
					return coll;
					//mav.addObject(res);
			}
			else
			{
				contactsQuery = new ArrayList<Place>();
				CollectionList coll = new CollectionList();
				coll.setCollection(contactsQuery);
				coll.setStatusOutput("true");
				coll.setTotalRecords(0L);
				//String res = JsonUtils.jsonFromObject(coll);
				return coll;
			}
		
	}
	
	
	
	
	
	
}
