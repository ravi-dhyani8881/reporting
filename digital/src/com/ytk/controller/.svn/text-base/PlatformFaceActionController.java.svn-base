package com.ytk.controller;


import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jsx3.gui.Tree;

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
import com.ytk.client.PlatformFaceActionControllerClient;
import com.ytk.client.PlatformPlaceControllerClient;
import com.ytk.client.PrivacyClient;
import com.ytk.client.SearchDiscussionQuestionClient;

import com.ytk.client.CustomClient;
import com.ytk.models.CollectionList;
import com.ytk.models.DiscussionQuestion;
import com.ytk.client.SearchThingsClient;
import com.ytk.models.Faces;
import com.ytk.models.HeaderFace;
import com.ytk.models.HeaderPlace;
import com.ytk.models.HeaderQuestion;
import com.ytk.models.HeaderThings;
import com.ytk.models.PCommunity;
import com.ytk.models.Place;
import com.ytk.models.ResultDoc;
import com.ytk.models.Things;
import com.ytk.utility.Adder;
import com.ytk.utility.ServerurlConstants;
import com.ytk.client.ConnectionClient;


@Controller
@RequestMapping("/searchfaces/*")
public class PlatformFaceActionController {
	
	@Autowired
	ServerurlConstants serverurlConstants;	
	
	@Autowired
	SearchThingsClient searchThingsClient;
	
	@Autowired
	ConnectionClient connectionClient;	
	
	@Autowired
	DateClient dateClient;	
	
	@Autowired
	PrivacyClient privacyClient;
	
	@Autowired
	MamCacheClient mamCacheClient;
	
	@Autowired
	CustomClient customClient;	
	
	@Autowired
	PlatformPlaceControllerClient platformPlaceControllerClient; 
	
	@Autowired
	PlatformFaceActionControllerClient platformFaceActionControllerClient;
	
	@Autowired
	SearchDiscussionQuestionController platformDiscussionActionController;
	
	@Autowired
	SearchDiscussionQuestionClient discussionQuestionClient;
	
	/*******
	 * 
	 * @param memberId
	 * @param memberName
	 * @param page
	 * @param search
	 * @return all faces  
	 */
	
	@RequestMapping(value = "/findfacesplaces")
	public ModelAndView  findMembers(
			HttpServletRequest request,
			@RequestParam(value = "member_id", required = false , defaultValue = "0") String memberId,
			@RequestParam(value = "member_name", required = false) String memberName,
			@RequestParam(value = "Age", required = false) String Age,
			@RequestParam(value = "CountryId", required = false  , defaultValue = "0") String CountryId,
			@RequestParam(value = "IsAlcohlic", required = false ,  defaultValue = "0") int IsAlcohlic,
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "isDetail", required = false) Boolean isDetail,
			@RequestParam(value = "jsoncallback", required = false) String search){
		
		ModelAndView mav = new ModelAndView();
		
		String privayQuery=privacyClient.commonPrivacyQueryHeaderSearch(Age, CountryId, IsAlcohlic , memberId);
	
		
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
	    
	    canSendMessagesResult2=mamCacheClient.mamCachecanSendMessages(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT,memberId , request);
	    if (canSendMessagesResult2!=null)
	    canSendMessagesResult=canSendMessagesResult2.replace("," , " , ");
	    
	    }catch (Exception e) {
	    	e.printStackTrace();
		}
		
		
		
		//if(memberId == null || memberId.equals("")){
//			mav.addObject("TotalRecord","0");
//			mav.addObject("Faces",new ArrayList());
//			mav.addObject("Places",new ArrayList());
//			mav.addObject("Things",new ArrayList());
//			mav.addObject("Discussion",new ArrayList());
			//return mav;
		//}else {
			int size;
			if(isDetail == false)
				size = 8;
			else
				size = 2;	
			memberName = memberName;
			//String[] memberNameArr  = memberName.split("@");
		//	memberName             = Adder.escapeQueryChars(memberName);
			//memberName             = memberName.replace(" ", " \\ ");
			
			String queryText = "";
			int pageCount = page * 10;
			long numFoundFaces   = 0;
			long numFoundPlaces  = 0;
			long numFoundThings  = 0;
			String queryTextThings="";
			long numFoundDiscussion  = 0;
			CharSequence charseq = "@";
			Object[] resultArr = null;
			Object[] resultArrPlces = null;
			Object[] resultArrThings = null;
			Object[] resultArrDiscussion = null;
			
			List<HeaderFace> listFaces     = null;
			List<HeaderPlace> listPlaces    = null;
			List<HeaderThings> thingsList    = null;
			List<HeaderQuestion> questions     = null;
			if(memberName.contains("@"))
			{
				queryTextThings = "(Email:"+memberName+" AND Status:3)";
			}else{
				queryTextThings = "(Name:"+memberName+"* AND Status:3)";	
			}
			queryTextThings="("+queryTextThings+privayQuery+")";
			System.out.println("----------------->"+queryTextThings);
			resultArrThings        = searchThingsClient.thingsHeaderSearch(queryTextThings,pageCount,size, request);
			numFoundThings         = Integer.parseInt(resultArrThings[1].toString());
			thingsList             = (List) resultArrThings[0];
			long TotalRecordFound  = 0;
			
			String queryDiscusiionText = "(QuestionText:"+memberName+"*) OR (Tags:"+memberName+")";
			resultArrDiscussion        = discussionQuestionClient.discussionHeaderSearch(queryDiscusiionText,page,size, request);
			numFoundDiscussion         = Integer.parseInt(resultArrDiscussion[1].toString());
			questions                  = (List) resultArrDiscussion[0];
			
			memberName=memberName.toLowerCase();
			
			if(memberName.contains("@"))
			{
				//original query
				
//				if(blockListResult2!=null && blockListResult!=""  && friendListResult2!=null && friendListResult2!=""){
//					queryText       = "(Email:"+memberName+" AND (YTKSearch:1 OR ID:"+friendListResult2+" OR ID:"+memberId+") -ID:"+blockListResult+"  )";
//				}else{
//					queryText       = "(Email:"+memberName+" AND (YTKSearch:1 OR ID:"+memberId+"))";
//				}
				
				//Modify query
				if(blockListResult2!=null && blockListResult!=""  && friendListResult2!=null && friendListResult2!=""){
					queryText       = "(Email:"+memberName+" AND (YTKSearch:1 OR ID:"+friendListResult2+" OR ID:"+memberId+") -ID:"+blockListResult+"  )";	
				}else if(blockListResult2!=null && blockListResult!=""){
					queryText       = "(Email:"+memberName+" AND (YTKSearch:1 OR ID:"+memberId+") -ID:"+blockListResult+"  )";
				}else if(friendListResult2!=null && friendListResult2!=""){
					queryText       = "(Email:"+memberName+" AND (YTKSearch:1 OR ID:"+friendListResult2+" OR ID:"+memberId+"))";
				}else{
					queryText       = "(Email:"+memberName+" AND (YTKSearch:1 OR ID:"+memberId+"))";
				}
				
			
				
				resultArr       = platformFaceActionControllerClient.queryCheck(queryText,pageCount,size, request);
				
				queryText       = "(Email:"+memberName+")";
				
				privayQuery=privacyClient.commonPrivacyQueryHeaderSearch(Age, CountryId, IsAlcohlic , memberId);
				
				queryText="("+queryText+privayQuery+")";
				
				resultArrPlces  = platformPlaceControllerClient.queryCheck(queryText,pageCount,size, request);
				numFoundFaces   = Integer.parseInt(resultArr[1].toString());
				numFoundPlaces  = Integer.parseInt(resultArrPlces[1].toString());
				
				listFaces       = (List) resultArr[0] ;
				listPlaces      = (List)resultArrPlces[0];
			}
			else
			{	
				
//				if(blockListResult2!=null && blockListResult!="" && friendListResult2!=null && friendListResult2!="" ){
//					queryText = "(Name:"+memberName +"* AND (YTKSearch:1 OR ID:"+friendListResult2+" OR ID:"+memberId+") -ID:"+blockListResult+" )";
//				}else{
//					queryText = "(Name:"+memberName +"* AND (YTKSearch:1 OR ID:"+memberId+"))";
//				}
				
				
				if(blockListResult2!=null && blockListResult!="" && friendListResult2!=null && friendListResult2!="" ){
					queryText = "(Name:"+memberName +"* AND (YTKSearch:1 OR ID:"+friendListResult2+" OR ID:"+memberId+") -ID:"+blockListResult+" )";
				}else if(blockListResult2!=null && blockListResult!="" ){
					queryText = "(Name:"+memberName +"* AND (YTKSearch:1 OR ID:"+memberId+") -ID:"+blockListResult+" )";
				}else if(friendListResult2!=null && friendListResult2!=""){
					queryText = "(Name:"+memberName +"* AND (YTKSearch:0 OR ID:"+friendListResult2+" OR ID:"+memberId+"))";
				}else{
					queryText = "(Name:"+memberName +"* AND (YTKSearch:1 OR ID:"+memberId+"))";
				}
				
				
				
				privayQuery=privacyClient.commonPrivacyQueryHeaderSearch(Age, CountryId, IsAlcohlic , memberId);
				
				queryText="("+queryText+privayQuery+")";
				
				resultArr        = platformFaceActionControllerClient.headerFace(queryText,pageCount,size, request);
				
				queryText = "(Name:"+memberName +"*)";
				
				queryText="("+queryText+privayQuery+")";
				
				resultArrPlces   = platformPlaceControllerClient.HeaderPlace(queryText,pageCount,size, request);
				numFoundFaces    = Integer.parseInt(resultArr[1].toString());
				numFoundPlaces   = Integer.parseInt(resultArrPlces[1].toString());
				
				listFaces        = (List) resultArr[0] ;
				listPlaces       = (List)resultArrPlces[0];
			}
			
			List  fullContacts      = listFaces;
			List  fullContactsPlace = listPlaces;
			if(isDetail == false)
			{
				int countFaces      = 5;
				int countPlaces     = 1;
				int countThings     = 1;
				int countDiscussion = 1;
				
				if(fullContacts.size() > 0 || fullContactsPlace.size() >0)
				{
						
						if(listFaces.size() < 8)
						{
							countFaces = listFaces.size();
							countPlaces = (8-countFaces);
							
						}
						if(countPlaces == 8)
							countPlaces = 4;	
						
							if(listPlaces.size() < countPlaces)
								countPlaces = listPlaces.size();
							countThings = 8-(countFaces+countPlaces);
							
						if(countThings == 8)
							countThings = 4;
							if(thingsList.size() < countThings)
								countThings = thingsList.size();
							countDiscussion = 8-(countFaces+countPlaces+countThings);
							
							
							if(questions.size() < countDiscussion || questions.size() == 0)
								countDiscussion = questions.size();
							
							if((countFaces+countPlaces+countThings) >= 8)
								countDiscussion = 0;
							if(countDiscussion <= 8)
							{
								int allOthers  = countFaces+countPlaces+countThings;
									if(allOthers <= 8)
									{
										countDiscussion = 8 - allOthers;
									}
										
							}
						TotalRecordFound= countFaces+countPlaces+countThings+countDiscussion;	
						long Total = numFoundFaces+numFoundPlaces+numFoundThings+numFoundDiscussion;
						mav.addObject("Total",Total);
						mav.addObject("TotalRecord",TotalRecordFound);
						if(listFaces.size() >=  countFaces)
							mav.addObject("Faces",listFaces.subList(0, countFaces));
						else
							mav.addObject("Faces",new ArrayList());
						if(listPlaces.size() >=  countPlaces)
							mav.addObject("Places",listPlaces.subList(0, countPlaces));
						else
							mav.addObject("Places",new ArrayList());
						if(thingsList.size() >=  countThings)
							mav.addObject("Things",thingsList.subList(0,countThings));
						else
							mav.addObject("Things",new ArrayList());
						if(questions.size() >=  countDiscussion)
							mav.addObject("Discussion",questions.subList(0, countDiscussion));
						else
							mav.addObject("Discussion",new ArrayList());
						return mav;
				}
				else
				{
					
					TotalRecordFound = numFoundFaces+numFoundPlaces+numFoundThings+numFoundDiscussion;
					long Total = numFoundFaces+numFoundPlaces+numFoundThings+numFoundDiscussion;
					mav.addObject("Total",Total);
					mav.addObject("TotalRecord",TotalRecordFound);
					mav.addObject("Faces",listFaces);
					mav.addObject("Places",listPlaces);
					mav.addObject("Things",thingsList);
					mav.addObject("Discussion",questions);
					return mav;
					
				}
			}
			else
			{
				TotalRecordFound = numFoundFaces+numFoundPlaces+numFoundThings+numFoundDiscussion;
				long Total = numFoundFaces+numFoundPlaces+numFoundThings+numFoundDiscussion;
				mav.addObject("Total",Total);

				mav.addObject("TotalFaces",numFoundFaces);
				mav.addObject("TotalPlaces",numFoundPlaces);
				mav.addObject("TotalThings",numFoundThings);
				mav.addObject("TotalDiscussions",numFoundDiscussion);
				mav.addObject("TotalRecord",TotalRecordFound);
				mav.addObject("Faces",listFaces);
				mav.addObject("Places",listPlaces);
				mav.addObject("Things",thingsList);
				mav.addObject("Discussion",questions);
				return mav;
			}		
				
		
	}
	
	
	@RequestMapping(value = "/finddirectoryfaces")
	public @ResponseBody CollectionList  findDirectoryFaces(
			HttpServletRequest request,
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
    
    folderListResult2=mamCacheClient.mamCachefolderList(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT, memberId , request);
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
	//		queryText = "(Name:"+memberNameArr[0] +"*)";
		
			//New query only for the directory searh
			
			queryText = "(NameForDirectorySearch:"+memberNameArr[0] +"*)";
			
			if(memberName.equals("0123456789")){
				queryText="(NameForDirectorySearch:1* OR NameForDirectorySearch:2* OR NameForDirectorySearch:3* OR NameForDirectorySearch:4* OR NameForDirectorySearch:5* OR NameForDirectorySearch:6* OR NameForDirectorySearch:7* OR NameForDirectorySearch:8* OR NameForDirectorySearch:9* OR NameForDirectorySearch:0*)";
				}
			
			queryText="("+queryText+privayQuery+")";
			System.out.println("Query--------->"+queryText);
			resultArr = platformFaceActionControllerClient.queryCheck(queryText,pageCount,size, request);
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
	

/**************************************************************************
 * 	
 * @param ID
 * @param DBID
 * @param CategoryID
 * @param CategoryName
 * @param ScreenName
 * @param ScreenNameStatus
 * @param FirstName
 * @param MiddleName
 * @param LastName
 * @param Name
 * @param AlternateName
 * @param DisplayAlternateName
 * @param Email
 * @param ZipCode
 * @param BirthDay
 * @param Gender
 * @param City
 * @param State
 * @param Country
 * @param CityID
 * @param StateID
 * @param CountryID
 * @param Timezone
 * @param ProfileImage
 * @param RelationshipStatus
 * @param BodyType
 * @param Height
 * @param HeightViewType
 * @param HairColor
 * @param EyeColor
 * @param Sexuality
 * @param BestFeature
 * @param Exercise
 * @param Smoke
 * @param Drink
 * @param DrugUser
 * @param BodyArt
 * @param Profession
 * @param AnnualIncome
 * @param ReligiousAffiliations
 * @param HaveChildren
 * @param WantChildren
 * @param HavePets
 * @param CreatedDate
 * @param ActivationDate
 * @param UpdatedDate
 * @param DeletionDate
 * @param LastLoginDate
 * @param Status
 * @param IsCrawlerAllow
 * @param Role
 * @param OnlineStatus
 * @param ZodiacSign
 * @param ZodiacAnimal
 * @param InMyOwnWords
 * @param WhoCanSeeInSearch
 * @param Address
 * @param Neighborhood
 * @param Website
 * @param ShowWelcomePage
 * @param ShowCompleteStatus
 * @param IsFundermailSent
 * @param MusicListen
 * @param MoviesWatch
 * @param BooksRead
 * @param Passions
 * @param OtherInterests
 * @param GetsMeExcited
 * @param ContactDB
 * @param UpdateDB
 * @param InfoDB
 * @param CommentDB
 * @param ContentDB
 * @param MessageDB
 * @param FolderDB
 * @param NotificationDB
 * @param SearchDB
 * @return
 */
	@RequestMapping(value = "/addFace")
	public ModelAndView addMember(
			HttpServletRequest request,
			@RequestParam(value = "ID", required = false) String ID,
			@RequestParam(value = "DBID", required = false , defaultValue = "1") String DBID,
			@RequestParam(value = "CategoryID", required = false , defaultValue = "0") String CategoryID,
			@RequestParam(value = "CategoryName", required = false , defaultValue = "") String CategoryName,
			@RequestParam(value = "ScreenName", required = false , defaultValue = "") String ScreenName,
			@RequestParam(value = "ScreenNameStatus", required = false, defaultValue = "0") String ScreenNameStatus,
			@RequestParam(value = "FirstName", required = false , defaultValue = "") String FirstName,
			@RequestParam(value = "MiddleName", required = false , defaultValue = "") String MiddleName,
			@RequestParam(value = "LastName", required = false , defaultValue = "") String LastName,
			@RequestParam(value = "Name", required = false , defaultValue = "") String Name,
			@RequestParam(value = "AlternateName", required = false , defaultValue = "") String AlternateName,
			@RequestParam(value = "DisplayAlternateName", required = false, defaultValue = "0") String DisplayAlternateName,
			@RequestParam(value = "Email", required = false, defaultValue = "") String Email,
			@RequestParam(value = "ZipCode", required = false , defaultValue = "") String ZipCode,
			@RequestParam(value = "BirthDay", required = false , defaultValue = "1900-01-01 00:00:00") String BirthDay,
			@RequestParam(value = "BirthdayOptions", required = false, defaultValue = "0") String BirthdayOptions,
			@RequestParam(value = "AnniversaryDay", required = false , defaultValue = "1900-01-01 00:00:00") String AnniversaryDay,			
			@RequestParam(value = "Gender", required = false, defaultValue = "0") String Gender,
			@RequestParam(value = "City", required = false , defaultValue = "") String City,
			@RequestParam(value = "State", required = false , defaultValue = "") String State,
			@RequestParam(value = "Country", required = false , defaultValue = "") String Country,
			@RequestParam(value = "CityID", required = false , defaultValue = "0") String CityID,
			@RequestParam(value = "StateID", required = false , defaultValue = "") String StateID,
			@RequestParam(value = "CountryID", required = false, defaultValue = "0") String CountryID,			
			@RequestParam(value = "CityCurrent", required = false , defaultValue = "") String CityCurrent,
			@RequestParam(value = "StateCurrent", required = false , defaultValue = "") String StateCurrent,
			@RequestParam(value = "CountryCurrent", required = false , defaultValue = "") String CountryCurrent,
			@RequestParam(value = "CityIDCurrent", required = false, defaultValue = "0") String CityIDCurrent,
			@RequestParam(value = "StateIDCurrent", required = false , defaultValue = "") String StateIDCurrent,
			@RequestParam(value = "CountryIDCurrent", required = false, defaultValue = "0") String CountryIDCurrent,
			@RequestParam(value = "CityHome", required = false , defaultValue = "") String CityHome,
			@RequestParam(value = "StateHome", required = false , defaultValue = "") String StateHome,
			@RequestParam(value = "CountryHome", required = false , defaultValue = "") String CountryHome,
			@RequestParam(value = "CityIDHome", required = false, defaultValue = "0") String CityIDHome,
			@RequestParam(value = "StateIDHome", required = false , defaultValue = "") String StateIDHome,
			@RequestParam(value = "CountryIDHome", required = false, defaultValue = "0") String CountryIDHome,
			@RequestParam(value = "Timezone", required = false , defaultValue = "") String Timezone,
			@RequestParam(value = "ProfileImage", required = false , defaultValue = "") String ProfileImage,
			@RequestParam(value = "RelationshipStatus", required = false, defaultValue = "0") String RelationshipStatus,
			@RequestParam(value = "BodyType", required = false, defaultValue = "0") String BodyType,
			@RequestParam(value = "Height", required = false, defaultValue = "0") String Height,
			@RequestParam(value = "HeightViewType", required = false, defaultValue = "1") String HeightViewType,			
			@RequestParam(value = "HairColor", required = false, defaultValue = "0") String HairColor,
			@RequestParam(value = "EyeColor", required = false, defaultValue = "0") String EyeColor,
			@RequestParam(value = "Sexuality", required = false, defaultValue = "0") String Sexuality,
			@RequestParam(value = "BestFeature", required = false, defaultValue = "0") String BestFeature,
			@RequestParam(value = "Exercise", required = false, defaultValue = "0") String Exercise,
			@RequestParam(value = "Smoke", required = false, defaultValue = "0") String Smoke,
			@RequestParam(value = "Drink", required = false, defaultValue = "0") String Drink,
			@RequestParam(value = "DrugUser", required = false, defaultValue = "0") String DrugUser,
			@RequestParam(value = "BodyArt", required = false, defaultValue = "0") String BodyArt,
			@RequestParam(value = "Profession", required = false, defaultValue = "0") String Profession,
			@RequestParam(value = "AnnualIncome", required = false, defaultValue = "0") String AnnualIncome,
			@RequestParam(value = "ReligiousAffiliations", required = false, defaultValue = "0") String ReligiousAffiliations,
			@RequestParam(value = "HaveChildren", required = false, defaultValue = "0") String HaveChildren,
			@RequestParam(value = "WantChildren", required = false, defaultValue = "0") String WantChildren,
			@RequestParam(value = "HavePets", required = false, defaultValue = "0") String HavePets,
			@RequestParam(value = "CreatedDate", required = false, defaultValue = "1900-01-01 00:00:00") String CreatedDate,
			@RequestParam(value = "ActivationDate", required = false, defaultValue = "1900-01-01 00:00:00") String ActivationDate,
			@RequestParam(value = "UpdatedDate", required = false, defaultValue = "1900-01-01 00:00:00") String UpdatedDate,
			@RequestParam(value = "DeletionDate", required = false, defaultValue = "1900-01-01 00:00:00") String DeletionDate,
			@RequestParam(value = "LastLoginDate", required = false, defaultValue = "1900-01-01 00:00:00") String LastLoginDate,
			@RequestParam(value = "Status", required = false, defaultValue = "0") String Status,
			@RequestParam(value = "IsCrawlerAllow", required = false, defaultValue = "0") String IsCrawlerAllow,
			@RequestParam(value = "Role", required = false , defaultValue = "") String Role,
			@RequestParam(value = "OnlineStatus", required = false, defaultValue = "0") String OnlineStatus,
			@RequestParam(value = "ZodiacSign", required = false, defaultValue = "0") String ZodiacSign,
			@RequestParam(value = "ZodiacAnimal", required = false, defaultValue = "0") String ZodiacAnimal,
			@RequestParam(value = "InMyOwnWords", required = false , defaultValue = "") String InMyOwnWords,
			@RequestParam(value = "WhoCanSeeInSearch", required = false, defaultValue = "0") String WhoCanSeeInSearch,
			@RequestParam(value = "Address", required = false , defaultValue = "") String Address,
			@RequestParam(value = "Neighborhood", required = false , defaultValue = "") String Neighborhood,
			@RequestParam(value = "Website", required = false , defaultValue = "") String Website,
			@RequestParam(value = "ShowWelcomePage", required = false, defaultValue = "0") String ShowWelcomePage,
			@RequestParam(value = "ShowCompleteStatus", required = false, defaultValue = "0") String ShowCompleteStatus,
			@RequestParam(value = "IsFundermailSent", required = false, defaultValue = "0") String IsFundermailSent,
			@RequestParam(value = "MusicListen", required = false , defaultValue = "") String MusicListen,
			@RequestParam(value = "MoviesWatch", required = false , defaultValue = "") String MoviesWatch,
			@RequestParam(value = "BooksRead", required = false , defaultValue = "") String BooksRead,
			@RequestParam(value = "Passions", required = false , defaultValue = "") String Passions,
			@RequestParam(value = "OtherInterests", required = false , defaultValue = "") String OtherInterests,
			@RequestParam(value = "GetsMeExcited", required = false , defaultValue = "") String GetsMeExcited,
			@RequestParam(value = "ContactDB", required = false, defaultValue = "0") String ContactDB,
			@RequestParam(value = "UpdateDB", required = false, defaultValue = "0") String UpdateDB,
			@RequestParam(value = "InfoDB", required = false, defaultValue = "0") String InfoDB,
			@RequestParam(value = "CommentDB", required = false, defaultValue = "0") String CommentDB,
			@RequestParam(value = "ContentDB", required = false, defaultValue = "0") String ContentDB,
			@RequestParam(value = "MessageDB", required = false, defaultValue = "1") String MessageDB,
			@RequestParam(value = "FolderDB", required = false, defaultValue = "1") String FolderDB,
			@RequestParam(value = "NotificationDB", required = false, defaultValue = "1") String NotificationDB,
			@RequestParam(value = "ProfileFileID", required = false, defaultValue = "") String ProfileFileID,			
			@RequestParam(value = "SearchDB", required = false, defaultValue = "1") String SearchDB ,
			@RequestParam(value = "YTKSearch", required = false, defaultValue = "1") String YTKSearch ,
			
			@RequestParam(value = "ProfileFileJson", required = false, defaultValue = "1") String ProfileFileJson ,
			
			@RequestParam(value = "PublicSearch", required = false, defaultValue = "0") String PublicSearch,
			@RequestParam(value = "RestrictAge", required = false, defaultValue = "") String RestrictAge,
			@RequestParam(value = "RestrictCountry", required = false, defaultValue = "") String RestrictCountry
			
			){
		
		ModelAndView mav = new ModelAndView();
		SolrInputDocument contacts = new SolrInputDocument();

		//Contacts contacts = new Contacts();
		contacts.addField("ID", ID);
		contacts.addField("DBID", DBID);
		contacts.addField("CategoryID", CategoryID);
		contacts.addField("CategoryName", CategoryName);
		contacts.addField("ScreenName", ScreenName);
		contacts.addField("ScreenNameStatus", ScreenNameStatus);
		contacts.addField("FirstName", FirstName);
		contacts.addField("MiddleName", MiddleName);
		contacts.addField("LastName", LastName);
		contacts.addField("Name", Name);
		contacts.addField("AlternateName", AlternateName);
		contacts.addField("DisplayAlternateName", DisplayAlternateName);
		contacts.addField("Email", Email);
		contacts.addField("ZipCode", ZipCode);
		contacts.addField("BirthDay", dateClient.addDateToSolr(BirthDay, request));
		contacts.addField("BirthdayOptions", BirthdayOptions);
		contacts.addField("AnniversaryDay", dateClient.addDateToSolr(AnniversaryDay , request));
		contacts.addField("Gender", Gender);
		contacts.addField("City", City);
		contacts.addField("State", State);
		contacts.addField("Country", Country);
		contacts.addField("CityID", CityID);
		contacts.addField("StateID", StateID);
		contacts.addField("CountryID", CountryID);
		contacts.addField("CityCurrent", CityCurrent);
		contacts.addField("StateCurrent", StateCurrent);
		contacts.addField("CountryCurrent", CountryCurrent);
		contacts.addField("CityIDCurrent", CityIDCurrent);
		contacts.addField("StateIDCurrent", StateIDCurrent);
		contacts.addField("CountryIDCurrent", CountryIDCurrent);
		contacts.addField("CityHome", CityHome);
		contacts.addField("StateHome", StateHome);
		contacts.addField("CountryHome", CountryHome);
		contacts.addField("CityIDHome", CityIDHome);
		contacts.addField("StateIDHome", StateIDHome);
		contacts.addField("CountryIDHome", CountryIDHome);
		contacts.addField("Timezone", Timezone);
		contacts.addField("ProfileImage", ProfileImage);
		contacts.addField("RelationshipStatus", RelationshipStatus);
		contacts.addField("BodyType", BodyType);
		contacts.addField("Height", Height);
		contacts.addField("HeightViewType", HeightViewType);
		contacts.addField("HairColor", HairColor);
		contacts.addField("EyeColor", EyeColor);
		contacts.addField("Sexuality", Sexuality);
		contacts.addField("BestFeature", BestFeature);
		contacts.addField("Exercise", Exercise);
		contacts.addField("Smoke", Smoke);
		contacts.addField("Drink", Drink);
		contacts.addField("DrugUser", DrugUser);
		contacts.addField("BodyArt", BodyArt);
		contacts.addField("Profession", Profession);
		contacts.addField("AnnualIncome", AnnualIncome);
		contacts.addField("ReligiousAffiliations", ReligiousAffiliations);
		contacts.addField("HaveChildren", HaveChildren);
		contacts.addField("WantChildren", WantChildren);
		contacts.addField("HavePets", HavePets);
		contacts.addField("CreatedDate", dateClient.addDateToSolr(CreatedDate, request));
		contacts.addField("ActivationDate", dateClient.addDateToSolr(ActivationDate, request));
		contacts.addField("UpdatedDate", dateClient.addDateToSolr(UpdatedDate, request));
		contacts.addField("DeletionDate", dateClient.addDateToSolr(DeletionDate, request));
		contacts.addField("LastLoginDate", dateClient.addDateToSolr(LastLoginDate, request));
		contacts.addField("Status", Status);
		contacts.addField("IsCrawlerAllow", IsCrawlerAllow);
		contacts.addField("Role", Role);
		contacts.addField("OnlineStatus", OnlineStatus);
		contacts.addField("ZodiacSign", ZodiacSign);
		contacts.addField("ZodiacAnimal", ZodiacAnimal);
		contacts.addField("InMyOwnWords", InMyOwnWords);
		contacts.addField("WhoCanSeeInSearch", WhoCanSeeInSearch);
		contacts.addField("Address", Address);
		contacts.addField("Neighborhood", Neighborhood);
		contacts.addField("Website", Website);
		contacts.addField("ShowWelcomePage", ShowWelcomePage);
		contacts.addField("ShowCompleteStatus", ShowCompleteStatus);
		contacts.addField("IsFundermailSent", IsFundermailSent);
		contacts.addField("MusicListen", MusicListen);
		contacts.addField("MoviesWatch", MoviesWatch);
		contacts.addField("BooksRead", BooksRead);
		contacts.addField("Passions", Passions);
		contacts.addField("OtherInterests", OtherInterests);
		contacts.addField("GetsMeExcited", GetsMeExcited);
		contacts.addField("ContactDB", ContactDB);
		contacts.addField("UpdateDB", UpdateDB);
		contacts.addField("InfoDB", InfoDB);
		contacts.addField("CommentDB", CommentDB);
		contacts.addField("ContentDB", ContentDB);		
		contacts.addField("MessageDB", MessageDB);
		contacts.addField("FolderDB", FolderDB);
		contacts.addField("NotificationDB", NotificationDB);
		contacts.addField("SearchDB", SearchDB);
		contacts.addField("YTKSearch", YTKSearch);
		contacts.addField("ProfileFileID", ProfileFileID);
		
		contacts.addField("ProfileFileJson", ProfileFileJson);
		
		
		contacts.addField("PublicSearch", PublicSearch);
		contacts.addField("RestrictAge", RestrictAge);
		contacts.addField("RestrictCountry", RestrictCountry);
		
		// Adding only for the dummy search not in usa in finddirectoryfaces.json
		contacts.addField("NameForDirectorySearch", Name.replaceAll("\\s",""));
		
		Adder.addFaces(serverurlConstants.ADD_FACES_URL, contacts);
		String result = "success";
		mav.addObject("result",result);
		return mav;
	}

	@RequestMapping(value = "/addconnection")
	public ModelAndView addConnection(
			HttpServletRequest request,
			@RequestParam(value = "ID", required = false) String ID,
			@RequestParam(value = "MemberID", required = false) String MemberID,
			@RequestParam(value = "MemberType", required = false) String MemberType,			
			@RequestParam(value = "MemberDisplayName", required = false) String MemberDisplayName,
			@RequestParam(value = "MemberScreenName", required = false) String MemberScreenName,
			@RequestParam(value = "MemberEmailAddress", required = false) String MemberEmailAddress,
			@RequestParam(value = "ContactID", required = false) String ContactID,
			@RequestParam(value = "ContactType", required = false) String ContactType,
			@RequestParam(value = "ContactDisplayName", required = false) String ContactDisplayName,
			@RequestParam(value = "ContactScreenName", required = false) String ContactScreenName,
			@RequestParam(value = "ContactEmailAddress", required = false) String ContactEmailAddress,
			@RequestParam(value = "ContactProfileImageName", required = false) String ContactProfileImageName,
			@RequestParam(value = "ContactBirthDay", required = false) String ContactBirthDay,			
			@RequestParam(value = "ContactAge", required = false) String ContactAge,
			@RequestParam(value = "ContactGender", required = false) String ContactGender,
			@RequestParam(value = "ContactCity", required = false) String ContactCity,
			@RequestParam(value = "ContactState", required = false, defaultValue = "") String ContactState,
			@RequestParam(value = "ContactCountry", required = false , defaultValue = "") String ContactCountry,			
			@RequestParam(value = "ContactDefaultAlbumID", required = false) String ContactDefaultAlbumID,
			@RequestParam(value = "ContactDefaultAlbumsPhotoCount", required = false) String ContactDefaultAlbumsPhotoCount,
			@RequestParam(value = "ContactZodiacSignID", required = false) String ContactZodiacSignID,
			@RequestParam(value = "ContactZodiacAnimalID", required = false) String ContactZodiacAnimalID,
			
			@RequestParam(value = "ContactProfileImageJson", required = false) String ContactProfileImageJson,
			
			
			@RequestParam(value = "FolderID", required = false) String FolderID,
			@RequestParam(value = "FolderName", required = false) String FolderName,
			@RequestParam(value = "IsUpdatesHide", required = false) String IsUpdatesHide,
			@RequestParam(value = "Status", required = false) String Status,
			@RequestParam(value = "CreatedDate", required = false) String CreatedDate,
			@RequestParam(value = "AcceptedDate", required = false) String AcceptedDate,
			@RequestParam(value = "UpdatedDate", required = false) String UpdatedDate
			){
		
		ModelAndView mav = new ModelAndView();
		SolrInputDocument connection = new SolrInputDocument();

		//Contacts contacts = new Contacts();
		connection.addField("ID", ID);
		connection.addField("MemberID", MemberID);
		connection.addField("MemberType", MemberType);
		connection.addField("MemberDisplayName", MemberDisplayName);
		connection.addField("MemberScreenName", MemberScreenName);
		connection.addField("MemberEmailAddress", MemberEmailAddress);
		connection.addField("ContactID", ContactID);
		connection.addField("ContactDisplayName", ContactDisplayName);
		connection.addField("ContactScreenName", ContactScreenName);
		connection.addField("ContactEmailAddress", ContactEmailAddress);
		connection.addField("ContactProfileImageName", ContactProfileImageName);		
		connection.addField("ContactBirthDay", dateClient.addDateToSolr(ContactBirthDay, request));		
		
		connection.addField("ContactGender", ContactGender);
		connection.addField("ContactCity", ContactCity);
		connection.addField("ContactState", ContactState);
		connection.addField("ContactCountry", ContactCountry);
		
		connection.addField("ContactProfileImageJson", ContactProfileImageJson);
		
		connection.addField("ContactDefaultAlbumID", ContactDefaultAlbumID);
		connection.addField("ContactDefaultAlbumsPhotoCount", ContactDefaultAlbumsPhotoCount);
		connection.addField("ContactZodiacSignID", ContactZodiacSignID);
		connection.addField("ContactZodiacAnimalID", ContactZodiacAnimalID);
		connection.addField("FolderID", FolderID);
		connection.addField("FolderName", FolderName);
		connection.addField("IsUpdatesHide", IsUpdatesHide);
		connection.addField("Status", Status);
		connection.addField("CreatedDate", dateClient.addDateToSolr(CreatedDate, request));
		connection.addField("AcceptedDate", dateClient.addDateToSolr(AcceptedDate, request));
		connection.addField("UpdatedDate", dateClient.addDateToSolr(UpdatedDate, request));
		connection.addField("ContactType", ContactType);
			
		Adder.addPConnection(serverurlConstants.ADD_CONNECTION_URL , connection);
		String result = "success";
		mav.addObject("result",result);
		return mav;
	}
	
	
	@RequestMapping(value = "/deleteface")
	public ModelAndView deleteFace(
			@RequestParam(value = "FaceID", required = false) String FaceID)
	{
		ModelAndView mav = new ModelAndView();
		SolrInputDocument connection = new SolrInputDocument();
		connection.addField("ID", FaceID);	
		Adder.deleteFace(serverurlConstants.ADD_FACES_URL , connection);
		String result = "success";
		mav.addObject("result",result);
		return mav;
	}
	
	@RequestMapping(value = "/deleteconnection")
	public ModelAndView deleteConnection(
			@RequestParam(value = "ID", required = false) String ID)
	{
		ModelAndView mav = new ModelAndView();
		SolrInputDocument connection = new SolrInputDocument();
		connection.addField("ID", ID);	
		Adder.deletePConnection(serverurlConstants.DELETE_PCONNECTION_URL , ID);
		String result = "success";
		mav.addObject("result",result);
		return mav;
	}
	
	
	@RequestMapping(value = "/deleteallfaces")
	public ModelAndView deleteAllFaces()
	{
		
		ModelAndView mav = new ModelAndView();
		Adder.deleteAllInstances(ServerurlConstants.ADD_FACES_URL);
		String result = "success";
		mav.addObject("result",result);
		return mav;
	}

	@RequestMapping(value = "/deleteallconnections")
	public ModelAndView deleteAllConnections()
	{
		
		ModelAndView mav = new ModelAndView();
		Adder.deleteAllInstances(ServerurlConstants.ADD_CONNECTION_URL);
		String result = "success";
		mav.addObject("result",result);
		return mav;
	}
	
	/*********
	 * 
	 * @param memberId
	 * @param memberName
	 * @param page
	 * @param search
	 * @return List of Users in Connections
	 */
	@RequestMapping(value = "/finduserfaces")
	public @ResponseBody CollectionList  findUserMembers(
			HttpServletRequest request,
			@RequestParam(value = "member_id", required = false) String memberId,
			@RequestParam(value = "member_name", required = false) String memberName,
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "jsoncallback", required = false) String search){
		
		ModelAndView mav = new ModelAndView();
		CollectionList userFaces = new CollectionList();
		String queryText = "";
		int pageCount = page * 10;
		List<PCommunity> connectionQuery = null;
		List<Faces> facesList = null;
		String numFound  = "0";
		CharSequence charseq = "@";
		Object[] resultArr = null;
		if(memberName.contains(charseq))
		{
			queryText = "(MemberEmailAddress:"+memberName+")  AND (ContactID:"+memberId+")  ";
			resultArr = connectionClient.queryConnection(ServerurlConstants.ADD_CONNECTION_URL, queryText,pageCount,10, request);
			numFound = resultArr[1].toString();
			connectionQuery =(List<PCommunity>) resultArr[0] ;
			facesList = getProfilesList(connectionQuery, request);
		}
		else
		{
			queryText = "(MemberDisplayName:"+memberName +"*) AND (ContactID:"+memberId+")  ";
			resultArr = connectionClient.queryConnection(ServerurlConstants.ADD_CONNECTION_URL, queryText,pageCount,10, request);
			numFound = resultArr[1].toString();//contactList.getResponse().getNumFound().toString();
			connectionQuery =(List<PCommunity>) resultArr[0] ;
			facesList = getProfilesList(connectionQuery, request);
			
		}
		userFaces.setCollection(facesList);
		return userFaces;
	
	}
	
	
	/*********
	 * 
	 * @param memberId
	 * @param memberName
	 * @param page
	 * @param search
	 * @return List of Users in Connections
	 */
	@RequestMapping(value = "/finduserconnections")
	public @ResponseBody CollectionList  findUserConnections(
			HttpServletRequest request,
			@RequestParam(value = "AccountID", required = false) String memberId,
			@RequestParam(value = "Keyword", required = false) String memberName,
			@RequestParam(value = "Page", required = false) int page,
			@RequestParam(value = "ContactType", required = false) String ContactType,
			@RequestParam(value = "Size", required = false) int size){
		
		ModelAndView mav = new ModelAndView();
		CollectionList userFaces = new CollectionList();
		String queryText = "";
		
		List<PCommunity> connectionQuery = null;
		String numFound  = "0";
		CharSequence charseq = "@";
		Object[] resultArr = null;
		if(memberName.contains(charseq))
		{
			queryText = "(ContactEmailAddress:"+memberName+")  AND (MemberID:"+memberId+")   AND (Status:5)";
			resultArr = connectionClient.queryConnection(ServerurlConstants.ADD_CONNECTION_URL, queryText,page,size, request);
			numFound = resultArr[1].toString();
			connectionQuery =(List<PCommunity>) resultArr[0] ;
			
		}
		else
		{
			queryText = "(ContactDisplayName:"+memberName +"*) AND (MemberID:"+memberId+")  AND (Status:5)";
			resultArr = connectionClient.queryConnection(ServerurlConstants.ADD_CONNECTION_URL,queryText,page,size, request);
			numFound = resultArr[1].toString();//contactList.getResponse().getNumFound().toString();
			connectionQuery =(List<PCommunity>) resultArr[0] ;
		}
		if(ContactType!=null && ContactType!=""){
			queryText = "(ContactDisplayName:"+memberName +"*) AND (MemberID:"+memberId+")  AND (Status:5) AND (ContactType:"+ContactType+")";
			resultArr = connectionClient.queryConnection(ServerurlConstants.ADD_CONNECTION_URL,queryText,page,size, request);
			numFound = resultArr[1].toString();//contactList.getResponse().getNumFound().toString();
			connectionQuery =(List<PCommunity>) resultArr[0] ;
			
		}
		userFaces.setCollection(connectionQuery);
		return userFaces;
	
	}
	
	
	@RequestMapping(value = "/finduserconnection")
	public ModelAndView  findUserConnection(
			HttpServletRequest request,
			@RequestParam(value = "AccountID", required = false) String memberId,
			@RequestParam(value = "Keyword", required = false) String memberName,
			@RequestParam(value = "Page", required = false) int page,
			@RequestParam(value = "Size", required = false) int size){
		
		ModelAndView mav = new ModelAndView();
		CollectionList userFaces = new CollectionList();
		String queryText = "";
		
		List<PCommunity> connectionQuery = new ArrayList<PCommunity>();
		
		String numFound  = "0";
		CharSequence charseq = "@";
		Object[] resultArr = null;
		if(memberName.contains(charseq))
		{
			queryText = "(ContactEmailAddress:"+memberName+")  AND (MemberID:"+memberId+")   AND (Status:5)";
			resultArr = connectionClient.queryConnection(ServerurlConstants.ADD_CONNECTION_URL,queryText,page,size, request);
			numFound = resultArr[1].toString();
			connectionQuery =(List<PCommunity>) resultArr[0] ;
			
		}
		else
		{
			queryText = "(ContactDisplayName:"+memberName +"*) AND (MemberID:"+memberId+")  AND (Status:5)";
			resultArr = connectionClient.queryConnection(ServerurlConstants.ADD_CONNECTION_URL,queryText,page,size, request);
			numFound = resultArr[1].toString();//contactList.getResponse().getNumFound().toString();
			connectionQuery =(List<PCommunity>) resultArr[0] ;
		}
		
		mav.addObject("ID",connectionQuery.get(0).getID());
		mav.addObject("MemberID",connectionQuery.get(0).getMemberID());
		mav.addObject("MemberType",connectionQuery.get(0).getMemberType());
		mav.addObject("MemberDisplayName",connectionQuery.get(0).getMemberDisplayName());
		mav.addObject("MemberScreenName",connectionQuery.get(0).getMemberScreenName());
		mav.addObject("MemberEmailAddress",connectionQuery.get(0).getMemberEmailAddress());
		mav.addObject("ContactID",connectionQuery.get(0).getContactID());
		mav.addObject("ContactType",connectionQuery.get(0).getContactType());
		mav.addObject("ContactDisplayName",connectionQuery.get(0).getContactDisplayName());
		mav.addObject("ContactScreenName",connectionQuery.get(0).getContactScreenName());
		mav.addObject("ContactEmailAddress",connectionQuery.get(0).getContactEmailAddress());
		mav.addObject("ContactProfileImageName",connectionQuery.get(0).getContactProfileImageName());
		mav.addObject("ContactBirthDay",connectionQuery.get(0).getContactBirthDay());
		mav.addObject("ContactGender",connectionQuery.get(0).getContactGender());
		mav.addObject("ContactCity",connectionQuery.get(0).getContactCity());
		mav.addObject("ContactDefaultAlbumID",connectionQuery.get(0).getContactDefaultAlbumID());
		mav.addObject("ContactDefaultAlbumsPhotoCount",connectionQuery.get(0).getContactDefaultAlbumsPhotoCount());
		mav.addObject("ContactZodiacSignID",connectionQuery.get(0).getContactZodiacSignID());
		mav.addObject("ContactZodiacAnimalID",connectionQuery.get(0).getContactZodiacAnimalID());
		mav.addObject("FolderID",connectionQuery.get(0).getFolderID());
		mav.addObject("FolderName",connectionQuery.get(0).getFolderName());
		
		mav.addObject("ContactProfileImageJson",connectionQuery.get(0).getContactProfileImageJson());
		
		
		mav.addObject("IsUpdatesHide",connectionQuery.get(0).getIsUpdatesHide());
		mav.addObject("Status",connectionQuery.get(0).getStatus());
		mav.addObject("CreatedDate",connectionQuery.get(0).getCreatedDate());
		mav.addObject("AcceptedDate",connectionQuery.get(0).getAcceptedDate());
		mav.addObject("UpdatedDate",connectionQuery.get(0).getUpdatedDate());
		
		
		return mav;
	
	}
	
	
	
	@RequestMapping(value = "/finduserconn")
	public ModelAndView  findUserConn(
			HttpServletRequest request,
			@RequestParam(value = "AccountID", required = false) String memberId,
			@RequestParam(value = "Keyword", required = false) String memberName,
			@RequestParam(value = "Page", required = false) int page,
			@RequestParam(value = "Size", required = false) int size){
		
		ModelAndView mav = new ModelAndView();
		CollectionList userFaces = new CollectionList();
		String queryText = "";
		
		List<PCommunity> connectionQuery = new ArrayList<PCommunity>();
		
		String numFound  = "0";
		CharSequence charseq = "@";
		Object[] resultArr = null;
		if(memberName.contains(charseq))
		{
			queryText = "(ContactEmailAddress:"+memberName+")  AND (MemberID:"+memberId+")   AND (Status:5)";
			resultArr = connectionClient.queryConnection(ServerurlConstants.ADD_CONNECTION_URL,queryText,page,size, request);
			numFound = resultArr[1].toString();
			connectionQuery =(List<PCommunity>) resultArr[0] ;
			
		}
		else
		{
			queryText = "(ContactDisplayName:"+memberName +"*) AND (MemberID:"+memberId+")  AND (Status:5)";
			resultArr = connectionClient.queryConnection(ServerurlConstants.ADD_CONNECTION_URL,queryText,page,size, request);
			numFound = resultArr[1].toString();//contactList.getResponse().getNumFound().toString();
			connectionQuery =(List<PCommunity>) resultArr[0] ;
		}
		
		mav.addObject("TotalRecords",connectionQuery.size());
		mav.addObject("StatusOutput","");
		mav.addObject("IsConnected ","1");
		mav.addObject("Collection",connectionQuery);
		
		return mav;
	
	}
	
	
	
	private List getProfilesList(List<PCommunity> connectionList, HttpServletRequest request)
	{
		List facesList = null;
		String numFound  = "0";
		Object[] resultArr = null;
		String queryText  = "";
		   for(PCommunity connection : connectionList)
		   {
			    queryText = "(ID:"+connection.getMemberID()+")";
			    resultArr = platformFaceActionControllerClient.queryCheck(queryText,10,10, request);
				numFound = resultArr[1].toString();
				List listFace =(List) resultArr[0] ;
				if(listFace.size() > 0)
				facesList.add(listFace.get(0));
		   }
		
		return facesList;
	}
	
	
	@RequestMapping(value = "/findfaces")
	public @ResponseBody CollectionList  findFacs(
			HttpServletRequest request,
			@RequestParam(value = "member_id", required = false) String memberId,
			@RequestParam(value = "member_name", required = false) String memberName,
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "Age", required = false) String Age,
			@RequestParam(value = "CountryId", required = false  , defaultValue = "0") String CountryId,
			@RequestParam(value = "IsAlcohlic", required = false ,  defaultValue = "0") int IsAlcohlic,
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
			int pageCount = page * 10;
			List<Faces> contactsQuery = null;
			String numFound  = "0";
			CharSequence charseq = "@";
			Object[] resultArr = null;
			if(memberName.contains(charseq))
			{
				queryText = "(Email:"+memberName+")";
				
				queryText="("+queryText+privayQuery+")";
				resultArr = platformFaceActionControllerClient.queryCheck(queryText,pageCount,10, request);
				numFound = resultArr[1].toString();
				contactsQuery =(List) resultArr[0] ;
			}
			else
			{
				queryText = "(Name:"+memberNameArr[0] +"*)";
				
				queryText="("+queryText+privayQuery+")";
				resultArr = platformFaceActionControllerClient.queryCheck(queryText,pageCount,10, request);
				numFound = resultArr[1].toString();//contactList.getResponse().getNumFound().toString();
				contactsQuery =(List) resultArr[0] ;
			}
			
			List  fullContacts = contactsQuery;
			if(fullContacts.size() > 0)
			{
				if(search == null)
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

	
	
	
	
	@RequestMapping(value = "/findfacesplacesthings")
	public ModelAndView  findMembersfaceplacethings(
			HttpServletRequest request,
			@RequestParam(value = "member_id", required = false) String memberId,
			@RequestParam(value = "member_name", required = false) String memberName,
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "isDetail", required = false) Boolean isDetail,
			@RequestParam(value = "jsoncallback", required = false) String search){
		
		ModelAndView mav = new ModelAndView();
		try{		
	
			int size;
			if(isDetail == false)
				size = 50;
			else
				size = 50;	
			memberName = memberName.toLowerCase();
			//String[] memberNameArr  = memberName.split("@");
			memberName             = Adder.escapeQueryChars(memberName);
			//memberName             = memberName.replace(" ", " \\ ");
			
			String queryText = "";
			int pageCount = page * 10;
			int numFoundFaces   = 0;
			int numFoundPlaces  = 0;
			int numFoundThings  = 0;
		
			CharSequence charseq = "@";
			Object[] resultArr = null;
			Object[] resultArrPlces = null;
			Object[] resultArrThings = null;
			Object[] resultArrDiscussion = null;
			
			List listFaces     = null;
			List listPlaces    = null;
			List thingsList    = null;
			List collection		   = null;
			List commonList		=null;
		
			String queryTextThings = "(Name:"+memberName.toLowerCase()+"*)";
			resultArrThings        = customClient.queryCheckForSpecificfieldOutput(serverurlConstants.ADD_THINGS_URL, queryTextThings,pageCount,size , 3, request);
			numFoundThings         = Integer.parseInt(resultArrThings[1].toString());
			thingsList             = (List) resultArrThings[0];
			mav.addObject("Collection",thingsList);
			int TotalRecordFound  = 0;
			
						
			if(memberName.contains(charseq))
			{
				queryText       = "(Email:"+memberName+")";
				resultArr       = customClient.queryCheckForSpecificfieldOutput(serverurlConstants.ADD_FACES_URL, queryText,pageCount,size , 1, request);
				resultArrPlces  = customClient.queryCheckForSpecificfieldOutput(serverurlConstants.ADD_PLACES_URL, queryText,pageCount,size , 2 , request);
				numFoundFaces   = Integer.parseInt(resultArr[1].toString());
				numFoundPlaces  = Integer.parseInt(resultArrPlces[1].toString());
				
				listFaces       = (List) resultArr[0] ;
				listPlaces      = (List)resultArrPlces[0];
				mav.addObject("Collection",listFaces);
				mav.addObject("Collection",listPlaces);
			}
			else
			{
				queryText = "(Name:"+memberName +"*)";

				resultArr        = customClient.queryCheckForSpecificfieldOutput(serverurlConstants.ADD_FACES_URL, queryText,pageCount,size , 1, request);
				
				resultArrPlces   = customClient.queryCheckForSpecificfieldOutput(serverurlConstants.ADD_PLACES_URL, queryText,pageCount,size , 2, request);
				numFoundFaces    = Integer.parseInt(resultArr[1].toString());
				numFoundPlaces   = Integer.parseInt(resultArrPlces[1].toString());
				
				listFaces        = (List) resultArr[0] ;
				listPlaces       = (List)resultArrPlces[0];
				mav.addObject("Collection",listPlaces);
				mav.addObject("Collection",listFaces);
			}
			
			List  fullContacts      = listFaces;
			List  fullContactsPlace = listPlaces;
			
			int Total = numFoundFaces+numFoundPlaces+numFoundThings;
			TotalRecordFound = numFoundFaces+numFoundPlaces+numFoundThings;
			
			//	mav.addObject("Total",Total);
			
				mav.addObject("TotalRecords",TotalRecordFound);
				mav.addObject("StatusOutput","");
				mav.addObject("IsConnected ","1");
			//	mav.addObject("Collections",commonList);
				
				
				
		}catch (Exception e) {
			e.printStackTrace();


		}
			return mav;	
	}	
	
	
	
	
	
	@RequestMapping(value = "/findbyScreenname")
	public ModelAndView  findByScreenName(
			HttpServletRequest request,
			@RequestParam(value = "Screenname", required = false) String Screenname	){
		
		ModelAndView mav = new ModelAndView();
		CollectionList userFaces = new CollectionList();
		String queryText = "";
		List<Faces> faces = new ArrayList<Faces>();
		Object[] resultArrFaces = null;		
	
		
		queryText = "(ScreenName:"+Screenname+")";
		
		resultArrFaces = platformFaceActionControllerClient.queryCheck(queryText,0,10, request);			
			faces= (List<Faces>) resultArrFaces[0];
		
			
			
			mav.addObject("TotalRecords",faces.size());
			mav.addObject("StatusOutput","");
			mav.addObject("IsConnected ","1");
			mav.addObject("Collection",faces);
			
		return mav;
	
	}
	
	
	
	@RequestMapping(value = "/seeallfacesplaces")
	public ModelAndView  Seeallfacesplaces(
			HttpServletRequest request,
			@RequestParam(value = "member_id", required = false , defaultValue = "0") String memberId,
			@RequestParam(value = "member_name", required = false) String memberName,
			@RequestParam(value = "Age", required = false) String Age,
			@RequestParam(value = "CountryId", required = false  , defaultValue = "0") String CountryId,
			@RequestParam(value = "IsAlcohlic", required = false ,  defaultValue = "0") int IsAlcohlic,
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "isDetail", required = false) Boolean isDetail,
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
	    
	    canSendMessagesResult2=mamCacheClient.mamCachecanSendMessages(ServerurlConstants.MEMCACHE_URL, ServerurlConstants.MEMCACHE_PORT,memberId , request);
	    if (canSendMessagesResult2!=null)
	    canSendMessagesResult=canSendMessagesResult2.replace("," , " , ");
	    
	    }catch (Exception e) {
	    	e.printStackTrace();
		}
		
		
	
			int size=10;
			
			memberName = memberName;
			//String[] memberNameArr  = memberName.split("@");
		//	memberName             = Adder.escapeQueryChars(memberName);
			//memberName             = memberName.replace(" ", " \\ ");
			
			String queryText = "";
			int pageCount = page * 10;
			long numFoundFaces   = 0;
			long numFoundPlaces  = 0;
			long numFoundThings  = 0;
			String queryTextThings="";
			long numFoundDiscussion  = 0;
			CharSequence charseq = "@";
			Object[] resultArr = null;
			Object[] resultArrPlces = null;
			Object[] resultArrThings = null;
			Object[] resultArrDiscussion = null;
			
			List<Faces> listFaces     = null;
			List<Place> listPlaces    = null;
			List<Things> thingsList    = null;
			List<DiscussionQuestion> questions     = null;
			if(memberName.contains("@"))
			{
				queryTextThings = "(Email:"+memberName+")";
			}else{
				queryTextThings = "(Name:"+memberName+"*)";	
			}
			queryTextThings="("+queryTextThings+privayQuery+")";
			System.out.println("----------------->"+queryTextThings);
			resultArrThings        = searchThingsClient.thingsHeaderSearch(queryTextThings,pageCount,size, request);
			numFoundThings         = Integer.parseInt(resultArrThings[1].toString());
			thingsList             = (List) resultArrThings[0];
			long TotalRecordFound  = 0;
			
			String queryDiscusiionText = "(QuestionText:"+memberName+"*) OR (Tags:"+memberName+")";
			resultArrDiscussion        = discussionQuestionClient.discussionHeaderSearch(queryDiscusiionText,page,size, request);
			numFoundDiscussion         = Integer.parseInt(resultArrDiscussion[1].toString());
			questions                  = (List) resultArrDiscussion[0];
			
			if(memberName.contains("@"))
			{
				if(blockListResult2!=null && blockListResult!=""  && friendListResult2!=null && friendListResult2!=""){
					queryText       = "(Email:"+memberName+" AND (YTKSearch:1 OR ID:"+friendListResult2+" OR ID:"+memberId+") -ID:"+blockListResult+"  )";
				}else{
					queryText       = "(Email:"+memberName+" AND (YTKSearch:1 OR ID:"+memberId+"))";
				}
					
				resultArr       = platformFaceActionControllerClient.headerFace(queryText,pageCount,size, request);
				
				queryText       = "(Email:"+memberName+")";
				
				privayQuery=privacyClient.commonPrivacyQuery(Age, CountryId, IsAlcohlic , memberId);
				
				queryText="("+queryText+privayQuery+")";
				
				resultArrPlces  = platformPlaceControllerClient.HeaderPlace(queryText,pageCount,size, request);
				numFoundFaces   = Integer.parseInt(resultArr[1].toString());
				numFoundPlaces  = Integer.parseInt(resultArrPlces[1].toString());
				
				listFaces       = (List) resultArr[0] ;
				listPlaces      = (List)resultArrPlces[0];
			}
			else
			{	
				
				if(blockListResult2!=null && blockListResult!="" && friendListResult2!=null && friendListResult2!="" ){
					queryText = "(Name:"+memberName +"* AND (YTKSearch:1 OR ID:"+friendListResult2+" OR ID:"+memberId+") -ID:"+blockListResult+" )";
				}else{
					queryText = "(Name:"+memberName +"* AND (YTKSearch:1 OR ID:"+memberId+"))";
				}
				
				privayQuery=privacyClient.commonPrivacyQuery(Age, CountryId, IsAlcohlic , memberId);
				
				resultArr        = platformFaceActionControllerClient.headerFace(queryText,pageCount,size, request);
				
				queryText = "(NameCopy:"+memberName.toLowerCase() +"*)";
				
				queryText="("+queryText+privayQuery+")";
				
				resultArrPlces   = platformPlaceControllerClient.HeaderPlace(queryText,pageCount,size, request);
				numFoundFaces    = Integer.parseInt(resultArr[1].toString());
				numFoundPlaces   = Integer.parseInt(resultArrPlces[1].toString());
				
				listFaces        = (List) resultArr[0] ;
				listPlaces       = (List)resultArrPlces[0];
			}
			
			List  fullContacts      = listFaces;
			List  fullContactsPlace = listPlaces;
			
			TotalRecordFound = numFoundFaces+numFoundPlaces+numFoundThings+numFoundDiscussion;
			long Total = numFoundFaces+numFoundPlaces+numFoundThings+numFoundDiscussion;
			mav.addObject("Total",Total);
			mav.addObject("TotalRecord",TotalRecordFound);
			mav.addObject("Faces",listFaces);
			mav.addObject("Places",listPlaces);
			mav.addObject("Things",thingsList);
			
			mav.addObject("TotalThings",numFoundThings);
			mav.addObject("TotalFaces",numFoundFaces);
			mav.addObject("TotalPlaces",numFoundPlaces);
			mav.addObject("TotalDiscussions",numFoundDiscussion);
			mav.addObject("Discussion",questions);
				
		return mav;
	}
	
	
	
	@RequestMapping(value = "/updateFace")
	public ModelAndView updateFace(HttpServletRequest request,
			@RequestParam(value = "ID", required = false) String ID,
			@RequestParam(value = "DBID", required = false) String DBID,
			@RequestParam(value = "CategoryID", required = false) String CategoryID,
			@RequestParam(value = "CategoryName", required = false ) String CategoryName,
			@RequestParam(value = "ScreenName", required = false ) String ScreenName,
			@RequestParam(value = "ScreenNameStatus", required = false) String ScreenNameStatus,
			@RequestParam(value = "FirstName", required = false ) String FirstName,
			@RequestParam(value = "MiddleName", required = false) String MiddleName,
			@RequestParam(value = "LastName", required = false ) String LastName,
			@RequestParam(value = "Name", required = false ) String Name,
			@RequestParam(value = "AlternateName", required = false) String AlternateName,
			@RequestParam(value = "DisplayAlternateName", required = false) String DisplayAlternateName,
			@RequestParam(value = "Email", required = false) String Email,
			@RequestParam(value = "ZipCode", required = false ) String ZipCode,
			@RequestParam(value = "BirthDay", required = false ) String BirthDay,
			@RequestParam(value = "BirthdayOptions", required = false) String BirthdayOptions,
			@RequestParam(value = "AnniversaryDay", required = false ) String AnniversaryDay,			
			@RequestParam(value = "Gender", required = false) String Gender,
			@RequestParam(value = "City", required = false) String City,
			@RequestParam(value = "State", required = false ) String State,
			@RequestParam(value = "Country", required = false ) String Country,
			@RequestParam(value = "CityID", required = false) String CityID,
			@RequestParam(value = "StateID", required = false ) String StateID,
			@RequestParam(value = "CountryID", required = false) String CountryID,			
			@RequestParam(value = "CityCurrent", required = false ) String CityCurrent,
			@RequestParam(value = "StateCurrent", required = false ) String StateCurrent,
			@RequestParam(value = "CountryCurrent", required = false ) String CountryCurrent,
			@RequestParam(value = "CityIDCurrent", required = false) String CityIDCurrent,
			@RequestParam(value = "StateIDCurrent", required = false) String StateIDCurrent,
			@RequestParam(value = "CountryIDCurrent", required = false) String CountryIDCurrent,
			@RequestParam(value = "CityHome", required = false ) String CityHome,
			@RequestParam(value = "StateHome", required = false) String StateHome,
			@RequestParam(value = "CountryHome", required = false ) String CountryHome,
			@RequestParam(value = "CityIDHome", required = false) String CityIDHome,
			@RequestParam(value = "StateIDHome", required = false) String StateIDHome,
			@RequestParam(value = "CountryIDHome", required = false) String CountryIDHome,
			@RequestParam(value = "Timezone", required = false ) String Timezone,
			@RequestParam(value = "ProfileImage", required = false ) String ProfileImage,
			@RequestParam(value = "RelationshipStatus", required = false) String RelationshipStatus,
			@RequestParam(value = "BodyType", required = false) String BodyType,
			@RequestParam(value = "Height", required = false) String Height,
			@RequestParam(value = "HeightViewType", required = false) String HeightViewType,			
			@RequestParam(value = "HairColor", required = false) String HairColor,
			@RequestParam(value = "EyeColor", required = false) String EyeColor,
			@RequestParam(value = "Sexuality", required = false) String Sexuality,
			@RequestParam(value = "BestFeature", required = false) String BestFeature,
			@RequestParam(value = "Exercise", required = false) String Exercise,
			@RequestParam(value = "Smoke", required = false) String Smoke,
			@RequestParam(value = "Drink", required = false) String Drink,
			@RequestParam(value = "DrugUser", required = false) String DrugUser,
			@RequestParam(value = "BodyArt", required = false) String BodyArt,
			@RequestParam(value = "Profession", required = false) String Profession,
			@RequestParam(value = "AnnualIncome", required = false) String AnnualIncome,
			@RequestParam(value = "ReligiousAffiliations", required = false) String ReligiousAffiliations,
			@RequestParam(value = "HaveChildren", required = false) String HaveChildren,
			@RequestParam(value = "WantChildren", required = false) String WantChildren,
			@RequestParam(value = "HavePets", required = false) String HavePets,
			@RequestParam(value = "CreatedDate", required = false) String CreatedDate,
			@RequestParam(value = "ActivationDate", required = false) String ActivationDate,
			@RequestParam(value = "UpdatedDate", required = false) String UpdatedDate,
			@RequestParam(value = "DeletionDate", required = false) String DeletionDate,
			@RequestParam(value = "LastLoginDate", required = false) String LastLoginDate,
			@RequestParam(value = "Status", required = false) String Status,
			@RequestParam(value = "IsCrawlerAllow", required = false) String IsCrawlerAllow,
			@RequestParam(value = "Role", required = false) String Role,
			@RequestParam(value = "OnlineStatus", required = false) String OnlineStatus,
			@RequestParam(value = "ZodiacSign", required = false) String ZodiacSign,
			@RequestParam(value = "ZodiacAnimal", required = false) String ZodiacAnimal,
			@RequestParam(value = "InMyOwnWords", required = false) String InMyOwnWords,
			@RequestParam(value = "WhoCanSeeInSearch", required = false) String WhoCanSeeInSearch,
			@RequestParam(value = "Address", required = false ) String Address,
			@RequestParam(value = "Neighborhood", required = false) String Neighborhood,
			@RequestParam(value = "Website", required = false ) String Website,
			@RequestParam(value = "ShowWelcomePage", required = false) String ShowWelcomePage,
			@RequestParam(value = "ShowCompleteStatus", required = false) String ShowCompleteStatus,
			@RequestParam(value = "IsFundermailSent", required = false) String IsFundermailSent,
			@RequestParam(value = "MusicListen", required = false) String MusicListen,
			@RequestParam(value = "MoviesWatch", required = false ) String MoviesWatch,
			@RequestParam(value = "BooksRead", required = false ) String BooksRead,
			@RequestParam(value = "Passions", required = false ) String Passions,
			@RequestParam(value = "OtherInterests", required = false ) String OtherInterests,
			@RequestParam(value = "GetsMeExcited", required = false) String GetsMeExcited,
			@RequestParam(value = "ContactDB", required = false) String ContactDB,
			@RequestParam(value = "UpdateDB", required = false) String UpdateDB,
			@RequestParam(value = "InfoDB", required = false) String InfoDB,
			@RequestParam(value = "CommentDB", required = false) String CommentDB,
			@RequestParam(value = "ContentDB", required = false) String ContentDB,
			@RequestParam(value = "MessageDB", required = false) String MessageDB,
			@RequestParam(value = "FolderDB", required = false) String FolderDB,
			@RequestParam(value = "NotificationDB", required = false) String NotificationDB,
			@RequestParam(value = "SearchDB", required = false) String SearchDB ,
			@RequestParam(value = "YTKSearch", required = false) String YTKSearch ,
			@RequestParam(value = "ProfileFileID", required = false) String ProfileFileID ,			
			@RequestParam(value = "PublicSearch", required = false) String PublicSearch,
			@RequestParam(value = "RestrictAge", required = false) String RestrictAge,
			
			@RequestParam(value = "ProfileFileJson", required = false) String ProfileFileJson,
			
			@RequestParam(value = "RestrictCountry", required = false) String RestrictCountry
			
			){
		
		ModelAndView mav = new ModelAndView();
		
		
		Object[] resultArr = null;
		String queryText=null;
		String[] paramNameget=null;
		
		ArrayList<String> requestParam=new ArrayList<String>();
		
		List<Faces> faceFromSolr = null;
		Enumeration paramNames = request.getParameterNames();
		queryText = "(ID:"+ID+")";
		resultArr=platformFaceActionControllerClient.updateFace(queryText, request);
		faceFromSolr= (List<Faces>) resultArr[0];

		
		while(paramNames.hasMoreElements()) {
		String params=(String) paramNames.nextElement();
		requestParam.add(params);
		}  
		
		
		SolrInputDocument faces = new SolrInputDocument();
		faces.addField("ID", ID);

		
		SolrInputDocument contacts = new SolrInputDocument();

		
		if(requestParam.contains("DBID")){
			faces.addField("DBID", DBID);
		}else{
			faces.addField("DBID", faceFromSolr.get(0).getDBID());	
		}
		
		
		if(requestParam.contains("CategoryID")){
			faces.addField("CategoryID", CategoryID);
		}else{
			faces.addField("CategoryID", faceFromSolr.get(0).getCategoryID());	
		}
		
		if(requestParam.contains("CategoryName")){
			faces.addField("CategoryName", CategoryName);
		}else{
			faces.addField("CategoryName", faceFromSolr.get(0).getCategoryName());	
		}
		
		
		if(requestParam.contains("ScreenName")){
			faces.addField("ScreenName", ScreenName);
		}else{
			faces.addField("ScreenName", faceFromSolr.get(0).getScreenName());	
		}
		
		if(requestParam.contains("ScreenNameStatus")){
			faces.addField("ScreenNameStatus", ScreenNameStatus);
		}else{
			faces.addField("ScreenNameStatus", faceFromSolr.get(0).getScreenNameStatus());	
		}
		
		
		if(requestParam.contains("FirstName")){
			faces.addField("FirstName", FirstName);
		}else{
			faces.addField("FirstName", faceFromSolr.get(0).getFirstName());	
		}
		
		if(requestParam.contains("MiddleName")){
			faces.addField("MiddleName", MiddleName);
		}else{
			faces.addField("MiddleName", faceFromSolr.get(0).getMiddleName());	
		}
		
	
		if(requestParam.contains("LastName")){
			faces.addField("LastName", LastName);
		}else{
			faces.addField("LastName", faceFromSolr.get(0).getLastName());	
		}
		
		
		if(requestParam.contains("Name")){
			faces.addField("Name", Name);
		}else{
			faces.addField("Name", faceFromSolr.get(0).getName());	
		}
		
		
		if(requestParam.contains("AlternateName")){
			faces.addField("AlternateName", AlternateName);
		}else{
			faces.addField("AlternateName", faceFromSolr.get(0).getAlternateName());	
		}
		
		
		if(requestParam.contains("DisplayAlternateName")){
			faces.addField("DisplayAlternateName", DisplayAlternateName);
		}else{
			faces.addField("DisplayAlternateName", faceFromSolr.get(0).getDisplayAlternateName());	
		}
		
		
		if(requestParam.contains("Email")){
			faces.addField("Email", Email);
		}else{
			faces.addField("Email", faceFromSolr.get(0).getEmail());	
		}
		
		
		if(requestParam.contains("ZipCode")){
			faces.addField("ZipCode", ZipCode);
		}else{
			faces.addField("ZipCode", faceFromSolr.get(0).getZipCode());	
		}
	
		if(requestParam.contains("BirthDay")){
			faces.addField("BirthDay", dateClient.addDateToSolr(BirthDay, request));
		}else{
			faces.addField("BirthDay", dateClient.addDateToSolr(faceFromSolr.get(0).getBirthDay() , request));	
		}
		
	
		if(requestParam.contains("BirthdayOptions")){
			faces.addField("BirthdayOptions", BirthdayOptions);
		}else{
			faces.addField("BirthdayOptions", faceFromSolr.get(0).getBirthdayOptions());	
		}
		
		
		if(requestParam.contains("AnniversaryDay")){
			faces.addField("AnniversaryDay", dateClient.addDateToSolr(AnniversaryDay , request));
		}else{
			faces.addField("AnniversaryDay", dateClient.addDateToSolr(faceFromSolr.get(0).getAnniversaryDay(), request));	
		}
	
		
		if(requestParam.contains("Gender")){
			faces.addField("Gender", Gender);
		}else{
			faces.addField("Gender", faceFromSolr.get(0).getGender());	
		}
	
		
		if(requestParam.contains("City")){
			faces.addField("City", City);
		}else{
			faces.addField("City", faceFromSolr.get(0).getCity());	
		}
		
		
		if(requestParam.contains("State")){
			faces.addField("State", State);
		}else{
			faces.addField("State", faceFromSolr.get(0).getState());	
		}
		
		
		if(requestParam.contains("Country")){
			faces.addField("Country", Country);
		}else{
			faces.addField("Country", faceFromSolr.get(0).getCountry());	
		}
		
		
		if(requestParam.contains("CityID")){
			faces.addField("CityID", CityID);
		}else{
			faces.addField("CityID", faceFromSolr.get(0).getCityID());	
		}
		
		
		
		if(requestParam.contains("StateID")){
			faces.addField("StateID", StateID);
		}else{
			faces.addField("StateID", faceFromSolr.get(0).getStateID());	
		}
		
		
		if(requestParam.contains("CountryID")){
			faces.addField("CountryID", CountryID);
		}else{
			faces.addField("CountryID", faceFromSolr.get(0).getCountryID());	
		}
		
		
		if(requestParam.contains("CityCurrent")){
			faces.addField("CityCurrent", CityCurrent);
		}else{
			faces.addField("CityCurrent", faceFromSolr.get(0).getCityCurrent());	
		}
		
		
		if(requestParam.contains("StateCurrent")){
			faces.addField("StateCurrent", StateCurrent);
		}else{
			faces.addField("StateCurrent", faceFromSolr.get(0).getStateCurrent());	
		}
		
		if(requestParam.contains("CountryCurrent")){
			faces.addField("CountryCurrent", CountryCurrent);
		}else{
			faces.addField("CountryCurrent", faceFromSolr.get(0).getCountryCurrent());	
		}
		
		
		if(requestParam.contains("CityIDCurrent")){
			faces.addField("CityIDCurrent", CityIDCurrent);
		}else{
			faces.addField("CityIDCurrent", faceFromSolr.get(0).getCityIDCurrent());	
		}
		
		if(requestParam.contains("StateIDCurrent")){
			faces.addField("StateIDCurrent", StateIDCurrent);
		}else{
			faces.addField("StateIDCurrent", faceFromSolr.get(0).getStateIDCurrent());	
		}
		
		
		if(requestParam.contains("CountryIDCurrent")){
			faces.addField("CountryIDCurrent", StateIDCurrent);
		}else{
			faces.addField("CountryIDCurrent", faceFromSolr.get(0).getCountryIDCurrent());	
		}
		
		
		if(requestParam.contains("CityHome")){
			faces.addField("CityHome", CityHome);
		}else{
			faces.addField("CityHome", faceFromSolr.get(0).getCityHome());	
		}
		
		
		if(requestParam.contains("StateHome")){
			faces.addField("StateHome", StateHome);
		}else{
			faces.addField("StateHome", faceFromSolr.get(0).getStateHome());	
		}
		
		
		if(requestParam.contains("CountryHome")){
			faces.addField("CountryHome", CountryHome);
		}else{
			faces.addField("CountryHome", faceFromSolr.get(0).getCountryHome());	
		}
		
		
		if(requestParam.contains("CityIDHome")){
			faces.addField("CityIDHome", CityIDHome);
		}else{
			faces.addField("CityIDHome", faceFromSolr.get(0).getCityIDHome());	
		}
		
		
		if(requestParam.contains("StateIDHome")){
			faces.addField("StateIDHome", StateIDHome);
		}else{
			faces.addField("StateIDHome", faceFromSolr.get(0).getStateIDHome());	
		}
		
		
		if(requestParam.contains("CountryIDHome")){
			faces.addField("CountryIDHome", CountryIDHome);
		}else{
			faces.addField("CountryIDHome", faceFromSolr.get(0).getCountryIDHome());	
		}
		
		if(requestParam.contains("Timezone")){
			faces.addField("Timezone", Timezone);
		}else{
			faces.addField("Timezone", faceFromSolr.get(0).getTimezone());	
		}
		
		
		if(requestParam.contains("ProfileImage")){
			faces.addField("ProfileImage", ProfileImage);
		}else{
			faces.addField("ProfileImage", faceFromSolr.get(0).getProfileImage());	
		}
		
		
		if(requestParam.contains("RelationshipStatus")){
			faces.addField("RelationshipStatus", RelationshipStatus);
		}else{
			faces.addField("RelationshipStatus", faceFromSolr.get(0).getRelationshipStatus());	
		}
		
		
		if(requestParam.contains("BodyType")){
			faces.addField("BodyType", BodyType);
		}else{
			faces.addField("BodyType", faceFromSolr.get(0).getBodyType());	
		}
		
		
		if(requestParam.contains("Height")){
			faces.addField("Height", Height);
		}else{
			faces.addField("Height", faceFromSolr.get(0).getHeight());	
		}
		
		
		
		if(requestParam.contains("HeightViewType")){
			faces.addField("HeightViewType", HeightViewType);
		}else{
			faces.addField("HeightViewType", faceFromSolr.get(0).getHeightViewType());	
		}
		
		if(requestParam.contains("HairColor")){
			faces.addField("HairColor", HairColor);
		}else{
			faces.addField("HairColor", faceFromSolr.get(0).getHairColor());	
		}
		
		
		
		if(requestParam.contains("EyeColor")){
			faces.addField("EyeColor", EyeColor);
		}else{
			faces.addField("EyeColor", faceFromSolr.get(0).getEyeColor());	
		}
		
		
		
		if(requestParam.contains("Sexuality")){
			faces.addField("Sexuality", Sexuality);
		}else{
			faces.addField("Sexuality", faceFromSolr.get(0).getSexuality());	
		}
		
		
		if(requestParam.contains("BestFeature")){
			faces.addField("BestFeature", BestFeature);
		}else{
			faces.addField("BestFeature", faceFromSolr.get(0).getBestFeature());	
		}
		
		
		if(requestParam.contains("Exercise")){
			faces.addField("Exercise", Exercise);
		}else{
			faces.addField("Exercise", faceFromSolr.get(0).getExercise());	
		}
		
		
		if(requestParam.contains("Smoke")){
			faces.addField("Smoke", Smoke);
		}else{
			faces.addField("Smoke", faceFromSolr.get(0).getSmoke());	
		}
		
		
		
		if(requestParam.contains("Drink")){
			faces.addField("Drink", Drink);
		}else{
			faces.addField("Drink", faceFromSolr.get(0).getDrink());	
		}

		
		if(requestParam.contains("DrugUser")){
			faces.addField("DrugUser", DrugUser);
		}else{
			faces.addField("DrugUser", faceFromSolr.get(0).getDrugUser());	
		}
		
		if(requestParam.contains("BodyArt")){
			faces.addField("BodyArt", BodyArt);
		}else{
			faces.addField("BodyArt", faceFromSolr.get(0).getBodyArt());	
		}

		
		if(requestParam.contains("Profession")){
			faces.addField("Profession", Profession);
		}else{
			faces.addField("Profession", faceFromSolr.get(0).getProfession());	
		}
		
		
		if(requestParam.contains("AnnualIncome")){
			faces.addField("AnnualIncome", AnnualIncome);
		}else{
			faces.addField("AnnualIncome", faceFromSolr.get(0).getAnnualIncome());	
		}


		if(requestParam.contains("ReligiousAffiliations")){
			faces.addField("ReligiousAffiliations", ReligiousAffiliations);
		}else{
			faces.addField("ReligiousAffiliations", faceFromSolr.get(0).getReligiousAffiliations());	
		}
		
		if(requestParam.contains("HaveChildren")){
			faces.addField("HaveChildren", HaveChildren);
		}else{
			faces.addField("HaveChildren", faceFromSolr.get(0).getHaveChildren());	
		}
		
		
		if(requestParam.contains("WantChildren")){
			faces.addField("WantChildren", WantChildren);
		}else{
			faces.addField("WantChildren", faceFromSolr.get(0).getWantChildren());	
		}
		
		
		if(requestParam.contains("HavePets")){
			faces.addField("HavePets", HavePets);
		}else{
			faces.addField("HavePets", faceFromSolr.get(0).getHavePets());	
		}
		
		if(requestParam.contains("CreatedDate")){
			faces.addField("CreatedDate", dateClient.addDateToSolr(CreatedDate, request));
		}else{
			faces.addField("CreatedDate", dateClient.addDateToSolr(faceFromSolr.get(0).getCreatedDate(), request));	
		}
		
		
		if(requestParam.contains("ActivationDate")){
			faces.addField("ActivationDate", dateClient.addDateToSolr(ActivationDate, request));
		}else{
			faces.addField("ActivationDate", dateClient.addDateToSolr(faceFromSolr.get(0).getActivationDate(), request));	
		}
		
		
		if(requestParam.contains("UpdatedDate")){
			faces.addField("UpdatedDate", dateClient.addDateToSolr(UpdatedDate, request));
		}else{
			faces.addField("UpdatedDate", dateClient.addDateToSolr(faceFromSolr.get(0).getUpdatedDate(), request));	
		}
		
		
		
		if(requestParam.contains("DeletionDate")){
			faces.addField("DeletionDate", dateClient.addDateToSolr(DeletionDate, request));
		}else{
			faces.addField("DeletionDate", dateClient.addDateToSolr(faceFromSolr.get(0).getDeletionDate(), request));	
		}

		if(requestParam.contains("LastLoginDate")){
			faces.addField("LastLoginDate", dateClient.addDateToSolr(LastLoginDate, request));
		}else{
			faces.addField("LastLoginDate", dateClient.addDateToSolr(faceFromSolr.get(0).getLastLoginDate(), request));	
		}
		
		if(requestParam.contains("Status")){
			faces.addField("Status", Status);
		}else{
			faces.addField("Status", faceFromSolr.get(0).getStatus());	
		}
		
		
		if(requestParam.contains("IsCrawlerAllow")){
			faces.addField("IsCrawlerAllow", IsCrawlerAllow);
		}else{
			faces.addField("IsCrawlerAllow", faceFromSolr.get(0).getIsCrawlerAllow());	
		}
		
		
		if(requestParam.contains("Role")){
			faces.addField("Role", Role);
		}else{
			faces.addField("Role", faceFromSolr.get(0).getRole());	
		}
		
		if(requestParam.contains("OnlineStatus")){
			faces.addField("OnlineStatus", OnlineStatus);
		}else{
			faces.addField("OnlineStatus", faceFromSolr.get(0).getOnlineStatus());	
		}
		
		
		if(requestParam.contains("ZodiacSign")){
			faces.addField("ZodiacSign", ZodiacSign);
		}else{
			faces.addField("ZodiacSign", faceFromSolr.get(0).getZodiacSign());	
		}
		
		
		if(requestParam.contains("ZodiacAnimal")){
			faces.addField("ZodiacAnimal", ZodiacAnimal);
		}else{
			faces.addField("ZodiacAnimal", faceFromSolr.get(0).getZodiacAnimal());	
		}
		
		if(requestParam.contains("InMyOwnWords")){
			faces.addField("InMyOwnWords", InMyOwnWords);
		}else{
			faces.addField("InMyOwnWords", faceFromSolr.get(0).getInMyOwnWords());	
		}
		
		
		
		if(requestParam.contains("WhoCanSeeInSearch")){
			faces.addField("WhoCanSeeInSearch", WhoCanSeeInSearch);
		}else{
			faces.addField("WhoCanSeeInSearch", faceFromSolr.get(0).getWhoCanSeeInSearch());	
		}
		
		
		if(requestParam.contains("Address")){
			faces.addField("Address", Address);
		}else{
			faces.addField("Address", faceFromSolr.get(0).getAddress());	
		}
		
		
		if(requestParam.contains("Neighborhood")){
			faces.addField("Neighborhood", Neighborhood);
		}else{
			faces.addField("Neighborhood", faceFromSolr.get(0).getNeighborhood());	
		}
		
		
		if(requestParam.contains("Website")){
			faces.addField("Website", Website);
		}else{
			faces.addField("Website", faceFromSolr.get(0).getWebsite());	
		}
		
		
		if(requestParam.contains("ShowWelcomePage")){
			faces.addField("ShowWelcomePage", ShowWelcomePage);
		}else{
			faces.addField("ShowWelcomePage", faceFromSolr.get(0).getShowWelcomePage());	
		}
		
		
		
		if(requestParam.contains("ShowCompleteStatus")){
			faces.addField("ShowCompleteStatus", ShowCompleteStatus);
		}else{
			faces.addField("ShowCompleteStatus", faceFromSolr.get(0).getShowCompleteStatus());	
		}
		
		
		if(requestParam.contains("IsFundermailSent")){
			faces.addField("IsFundermailSent", IsFundermailSent);
		}else{
			faces.addField("IsFundermailSent", faceFromSolr.get(0).getIsFundermailSent());	
		}
		
		if(requestParam.contains("MusicListen")){
			faces.addField("MusicListen", MusicListen);
		}else{
			faces.addField("MusicListen", faceFromSolr.get(0).getMusicListen());	
		}
		
		
		
		if(requestParam.contains("MoviesWatch")){
			faces.addField("MoviesWatch", MoviesWatch);
		}else{
			faces.addField("MoviesWatch", faceFromSolr.get(0).getMoviesWatch());	
		}
		
		
		
		if(requestParam.contains("BooksRead")){
			faces.addField("BooksRead", BooksRead);
		}else{
			faces.addField("BooksRead", faceFromSolr.get(0).getBooksRead());	
		}
		
		if(requestParam.contains("Passions")){
			faces.addField("Passions", Passions);
		}else{
			faces.addField("Passions", faceFromSolr.get(0).getPassions());	
		}
		
		
		if(requestParam.contains("OtherInterests")){
			faces.addField("OtherInterests", OtherInterests);
		}else{
			faces.addField("OtherInterests", faceFromSolr.get(0).getOtherInterests());	
		}

		
		if(requestParam.contains("GetsMeExcited")){
			faces.addField("GetsMeExcited", GetsMeExcited);
		}else{
			faces.addField("GetsMeExcited", faceFromSolr.get(0).getGetsMeExcited());	
		}
		
		
		if(requestParam.contains("ContactDB")){
			faces.addField("ContactDB", ContactDB);
		}else{
			faces.addField("ContactDB", faceFromSolr.get(0).getContactDB());	
		}
		
		
		if(requestParam.contains("UpdateDB")){
			faces.addField("UpdateDB", UpdateDB);
		}else{
			faces.addField("UpdateDB", faceFromSolr.get(0).getUpdateDB());	
		}
		if(requestParam.contains("InfoDB")){
			faces.addField("InfoDB", InfoDB);
		}else{
			faces.addField("InfoDB", faceFromSolr.get(0).getInfoDB());	
		}
		if(requestParam.contains("CommentDB")){
			faces.addField("CommentDB", CommentDB);
		}else{
			faces.addField("CommentDB", faceFromSolr.get(0).getCommentDB());	
		}
		
		
		if(requestParam.contains("ContentDB")){
			faces.addField("ContentDB", ContentDB);
		}else{
			faces.addField("ContentDB", faceFromSolr.get(0).getContentDB());	
		}
		if(requestParam.contains("MessageDB")){
			faces.addField("MessageDB", MessageDB);
		}else{
			faces.addField("MessageDB", faceFromSolr.get(0).getMessageDB());	
		}
		if(requestParam.contains("FolderDB")){
			faces.addField("FolderDB", FolderDB);
		}else{
			faces.addField("FolderDB", faceFromSolr.get(0).getFolderDB());	
		}
		if(requestParam.contains("NotificationDB")){
			faces.addField("NotificationDB", NotificationDB);
		}else{
			faces.addField("NotificationDB", faceFromSolr.get(0).getNotificationDB());	
		}
		
		
		if(requestParam.contains("SearchDB")){
			faces.addField("SearchDB", SearchDB);
		}else{
			faces.addField("SearchDB", faceFromSolr.get(0).getSearchDB());	
		}
		
		if(requestParam.contains("ProfileFileID")){
			faces.addField("ProfileFileID", ProfileFileID);
		}else{
			faces.addField("ProfileFileID", faceFromSolr.get(0).getProfileFileID());	
		}
		
		
		
		if(requestParam.contains("YTKSearch")){
			faces.addField("YTKSearch", YTKSearch);
		}else{
			faces.addField("YTKSearch", faceFromSolr.get(0).getYTKSearch());	
		}
		if(requestParam.contains("PublicSearch")){
			faces.addField("PublicSearch", PublicSearch);
		}else{
			faces.addField("PublicSearch", faceFromSolr.get(0).getPublicSearch());	
		}
		
		
		if(requestParam.contains("ProfileFileJson")){
			faces.addField("ProfileFileJson", ProfileFileJson);
		}else{
			faces.addField("ProfileFileJson", faceFromSolr.get(0).getProfileFileJson());	
		}
		
		
		
		
		// Adding only for the dummy search not in usa in finddirectoryfaces.json
		
		if(requestParam.contains("NameForDirectorySearch")){
			faces.addField("NameForDirectorySearch", Name.replaceAll("\\s",""));
		}else{
			faces.addField("NameForDirectorySearch", faceFromSolr.get(0).getName());	
		}
		
		
		
		
		
		Adder.addFaces(serverurlConstants.ADD_FACES_URL, faces);
		String result = "success";
		mav.addObject("result",result);
		return mav;
	}
	
	
	@RequestMapping(value = "/findtagfaces")
	public ModelAndView  findtagfaces(
			HttpServletRequest request,
			@RequestParam(value = "AccountID", required = false) String AccountID	,
			@RequestParam(value = "keyword", required = false) String keyword	,
			@RequestParam(value = "Page", required = false) int PageNo,
			@RequestParam(value = "CsvExcludeContactIds", required = false) String CsvExcludeContactIds,
			
			@RequestParam(value = "PageSize", required = false) int PageSize)
	{
		
		ModelAndView mav = new ModelAndView();
		CollectionList userFaces = new CollectionList();
		String queryText = "";
		List<PCommunity> connectionQuery = null;
		
		CsvExcludeContactIds=CsvExcludeContactIds.replace("," , " , ");
		
		Object[] resultArr = null;		
	
		keyword=keyword.toLowerCase();
		
		String numFound  = "0";
		CharSequence charseq = "@";
		
		if(keyword.contains(charseq))
		{
			if(!CsvExcludeContactIds.equals("")){
			queryText = "(ContactEmailAddress:"+keyword+" AND  MemberID:"+AccountID+" AND ContactType: 1 AND Status:5 -ContactID:"+CsvExcludeContactIds+")";
			}else{
			
			queryText = "(ContactEmailAddress:"+keyword+" AND  MemberID:"+AccountID+" AND ContactType: 1 AND Status:5)";
			}
		}else{
			if(!CsvExcludeContactIds.equals("")){
			queryText = "(ContactDisplayName:"+keyword+"* AND  MemberID:"+AccountID+" AND ContactType: 1 AND Status:5 -ContactID:"+CsvExcludeContactIds+")";
			}else{
			
			queryText = "(ContactDisplayName:"+keyword+"* AND  MemberID:"+AccountID+" AND ContactType: 1 AND Status:5)";
			}
			}
		
		resultArr = connectionClient.queryConnection(serverurlConstants.ADD_CONNECTION_URL,  queryText,PageNo,PageSize, request);			

		numFound = resultArr[1].toString();
		connectionQuery =(List<PCommunity>) resultArr[0] ;
		
		mav.addObject("TotalRecords",connectionQuery.size());
			mav.addObject("StatusOutput","");
			mav.addObject("IsConnected ","1");
			mav.addObject("Collection",connectionQuery);
			
		return mav;
	
	}
	
	
	@RequestMapping(value = "/findfacesAll")
	public @ResponseBody CollectionList  findFacsAll(
			HttpServletRequest request,
			@RequestParam(value = "member_id", required = false) String memberId,
			@RequestParam(value = "member_name", required = false) String memberName,
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "Age", required = false) String Age,
			@RequestParam(value = "CountryId", required = false  , defaultValue = "0") String CountryId,
			@RequestParam(value = "IsAlcohlic", required = false ,  defaultValue = "0") int IsAlcohlic,
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
			int pageCount = page * 10;
			List<Faces> contactsQuery = null;
			String numFound  = "0";
			CharSequence charseq = "@";
			Object[] resultArr = null;
			if(memberName.contains(charseq))
			{
				queryText = "(Email:"+memberName+")";
				
				queryText="("+queryText+privayQuery+")";
				resultArr = platformFaceActionControllerClient.headerFace(queryText,pageCount,10, request);
				numFound = resultArr[1].toString();
				contactsQuery =(List) resultArr[0] ;
			}
			else
			{
				queryText = "(Name:"+memberNameArr[0] +"*)";
				
				queryText="("+queryText+privayQuery+")";
				resultArr = platformFaceActionControllerClient.headerFace(queryText,pageCount,10, request);
				numFound = resultArr[1].toString();//contactList.getResponse().getNumFound().toString();
				contactsQuery =(List) resultArr[0] ;
			}
			
			List  fullContacts = contactsQuery;
			if(fullContacts.size() > 0)
			{
				if(search == null)
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
	
	
	
}
