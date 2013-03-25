package com.ytk.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ytk.client.DateClient;
import com.ytk.client.MamCacheClient;
import com.ytk.client.PrivacyClient;
import com.ytk.client.SearchThingsClient;
import com.ytk.models.CollectionList;
import com.ytk.models.Folder;
import com.ytk.models.ResultDoc;
import com.ytk.models.Things;
import com.ytk.utility.Adder;
import com.ytk.utility.ServerurlConstants;

@Controller
@RequestMapping("/searchthing/*")
public class SearchThingsController {
	
	@Autowired
	SearchThingsClient searchThingsClient;

	@Autowired
	ServerurlConstants serverurlConstants;
	
	@Autowired
	DateClient dateClient;
	
	@Autowired
	MamCacheClient mamCacheClient;
	
	@Autowired
	PrivacyClient privacyClient;
	
	public String totalRecord  = "0";
	
	@RequestMapping(value = "/findThings")
	public ModelAndView findThings(
			HttpServletRequest request,
			@RequestParam(value = "member_id", required = false) String memberId,
			@RequestParam(value = "member_name", required = false) String messageName,
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "size", required = false) int size,
			@RequestParam(value = "jsoncallback", required = false) String search){
		
		ModelAndView mav = new ModelAndView();
		messageName = messageName.toLowerCase();
		int pageCount = page * 10;
		Object[] resultArrThings = null;
		long numFoundThings  = 0;
		List thingsList = null;		
		String queryText =  "(Name:"+messageName+"*)";
		resultArrThings =  searchThingsClient.queryCheck(queryText,pageCount,size, request);
		numFoundThings  = Integer.parseInt(resultArrThings[1].toString());
		thingsList      = (List) resultArrThings[0];
		
		
		if(thingsList.size() > 0)
		{
			//Collections.sort(fullContacts);
			if(search == null)
			{
				mav.addObject("StatusOutput","true");
				mav.addObject("TotalRecords",numFoundThings);
				mav.addObject("Collection",thingsList);
			}
			else
			{
				mav.addObject("StatusOutput","true");
				mav.addObject("TotalRecords",numFoundThings);
				mav.addObject("Collection",thingsList);
			}
		}
		else
		{
			mav.addObject("StatusOutput","true");
			mav.addObject("TotalRecords",numFoundThings);
			mav.addObject("Collection",new ArrayList());
		}
		return mav;
	}
	
	
	
	
	@RequestMapping(value = "/addthings")
	public ModelAndView addThings(
			HttpServletRequest request,
			@RequestParam(value = "ID", required = false, defaultValue = "") String ID,
		//	@RequestParam(value = "DBID", required = false, defaultValue = "") String DBID,
			@RequestParam(value = "ScreenName", required = false, defaultValue = "") String ScreenName,
			@RequestParam(value = "ScreenNameStatus", required = false, defaultValue = "0") String ScreenNameStatus,
			@RequestParam(value = "Name", required = false, defaultValue = "0") String Name,
			@RequestParam(value = "CategoryID", required = false, defaultValue = "") String CategoryID,
			@RequestParam(value = "SubCategoryID", required = false, defaultValue = "0") String SubCategoryID,
		//	@RequestParam(value = "ParentCategoryID", required = false, defaultValue = "") String ParentCategoryID,
			@RequestParam(value = "CategoryName", required = false, defaultValue = "") String CategoryName,
			
			@RequestParam(value = "SubCategoryName", required = false, defaultValue = "") String SubCategoryName,
			@RequestParam(value = "ParentCategoryName", required = false, defaultValue = "") String ParentCategoryName,
			@RequestParam(value = "Address", required = false, defaultValue = "") String Address,
			@RequestParam(value = "Email", required = false, defaultValue = "") String Email,
			@RequestParam(value = "City", required = false, defaultValue = "") String City,
			@RequestParam(value = "State", required = false, defaultValue = "") String State,
			@RequestParam(value = "Country", required = false, defaultValue = "") String Country,
			@RequestParam(value = "CityID", required = false, defaultValue = "0") String CityID,
			@RequestParam(value = "StateID", required = false, defaultValue = "0") String StateID,
			@RequestParam(value = "CountryID", required = false, defaultValue = "0") String CountryID,
			@RequestParam(value = "ZipCode", required = false, defaultValue = "") String ZipCode,
			@RequestParam(value = "Affiliation", required = false, defaultValue = "") String Affiliation,
			@RequestParam(value = "About", required = false, defaultValue = "") String About,			
			@RequestParam(value = "Description", required = false, defaultValue = "") String Description,
			@RequestParam(value = "Biography", required = false, defaultValue = "") String Biography,
			@RequestParam(value = "Awards", required = false, defaultValue = "") String Awards,
			@RequestParam(value = "Phone", required = false, defaultValue = "") String Phone,
			@RequestParam(value = "Website", required = false, defaultValue = "") String Website,
			@RequestParam(value = "YouTube", required = false, defaultValue = "") String YouTube,
			@RequestParam(value = "ReleaseDate", required = false, defaultValue = "1900-01-01 00:00:00") String ReleaseDate,
			@RequestParam(value = "Genre", required = false, defaultValue = "") String Genre,
			@RequestParam(value = "ISBN", required = false, defaultValue = "") String ISBN,
			@RequestParam(value = "Publisher", required = false, defaultValue = "") String Publisher,
			@RequestParam(value = "Founded", required = false, defaultValue = "") String Founded,
			@RequestParam(value = "Products", required = false, defaultValue = "") String Products,
			@RequestParam(value = "CompanyOverview", required = false, defaultValue = "") String CompanyOverview,
			@RequestParam(value = "Mission", required = false, defaultValue = "") String Mission,
			@RequestParam(value = "Built", required = false, defaultValue = "") String Built,
			@RequestParam(value = "Features", required = false, defaultValue = "") String Features,
			@RequestParam(value = "MPG", required = false, defaultValue = "") String MPG,
			@RequestParam(value = "GeneralInformation", required = false, defaultValue = "") String GeneralInformation,
			@RequestParam(value = "Studio", required = false, defaultValue = "") String Studio,
			@RequestParam(value = "PlotOutline", required = false, defaultValue = "") String PlotOutline,
			@RequestParam(value = "Starring", required = false, defaultValue = "") String Starring,
			@RequestParam(value = "DirectedBy", required = false, defaultValue = "") String DirectedBy,
			@RequestParam(value = "WrittenBy", required = false, defaultValue = "") String WrittenBy,
			
			// 2 ProducedBy , ProducedBy new field added on 25/11/2011
			
			@RequestParam(value = "ScreenplayBy", required = false, defaultValue = "") String ScreenplayBy,
			@RequestParam(value = "ProducedBy", required = false, defaultValue = "") String ProducedBy,
			
			
			@RequestParam(value = "Members", required = false, defaultValue = "") String Members,
			@RequestParam(value = "RecordLabel", required = false, defaultValue = "") String RecordLabel,
			@RequestParam(value = "History", required = false, defaultValue = "") String History,
			@RequestParam(value = "Network", required = false, defaultValue = "") String Network,
			@RequestParam(value = "Season", required = false, defaultValue = "") String Season,
			@RequestParam(value = "ImageName", required = false, defaultValue = "") String ImageName,
			
			@RequestParam(value = "CreatedDate", required = false, defaultValue = "1900-01-01 00:00:00") String CreatedDate,
			@RequestParam(value = "ActivationDate", required = false, defaultValue = "1900-01-01 00:00:00") String ActivationDate,
			@RequestParam(value = "UpdatedDate", required = false, defaultValue = "1900-01-01 00:00:00") String UpdatedDate,
			@RequestParam(value = "DeletionDate", required = false, defaultValue = "1900-01-01 00:00:00") String DeletionDate,
			@RequestParam(value = "LastLoginDate", required = false, defaultValue = "1900-01-01 00:00:00") String LastLoginDate,
		//	@RequestParam(value = "IsActive", required = false) String IsActive,
		//	@RequestParam(value = "IsDeleted", required = false) String IsDeleted,
			@RequestParam(value = "IsFeatured", required = false, defaultValue = "0") String IsFeatured,
			@RequestParam(value = "IsCrawlerAllow", required = false, defaultValue = "0") String IsCrawlerAllow,
			@RequestParam(value = "Role", required = false, defaultValue = "Member") String Role,
			@RequestParam(value = "OnlineStatus", required = false, defaultValue = "0") String OnlineStatus,
			@RequestParam(value = "WhoCanSeeInSearch", required = false, defaultValue = "0") String WhoCanSeeInSearch,
			@RequestParam(value = "ShowWelcomePage", required = false, defaultValue = "0") String ShowWelcomePage,
			@RequestParam(value = "ShowCompleteStatus", required = false, defaultValue = "0") String ShowCompleteStatus,
			@RequestParam(value = "IsFundermailSent", required = false, defaultValue = "0") String IsFundermailSent,
			@RequestParam(value = "Status", required = false, defaultValue = "0") String Status,
			@RequestParam(value = "ProfileImage", required = false, defaultValue = "") String ProfileImage,
			@RequestParam(value = "ProfileFileID", required = false, defaultValue = "") String ProfileFileID,
			
			@RequestParam(value = "ImageJson", required = false, defaultValue = "") String ImageJson,
			@RequestParam(value = "ProfileImageJson", required = false, defaultValue = "") String ProfileImageJson,
			
			
			
			
			@RequestParam(value = "OldWikiID", required = false, defaultValue = "0") String OldWikiID,
			@RequestParam(value = "WikiInfo", required = false, defaultValue = "") String WikiInfo,
			
			@RequestParam(value = "RestrictAge", required = false, defaultValue = "0") String RestrictAge,
			@RequestParam(value = "RestrictCountry", required = false, defaultValue = "") String RestrictCountry,
			@RequestParam(value = "RestrictPost", required = false, defaultValue = "0") String RestrictPost
			
		//	@RequestParam(value = "ContactDB", required = false) String ContactDB,
		//	@RequestParam(value = "UpdateDB", required = false) String UpdateDB,
		//	@RequestParam(value = "MemberInfoDB", required = false) String MemberInfoDB,
		//	@RequestParam(value = "CommentDB", required = false) String CommentDB,
		//	@RequestParam(value = "MemberContentDB", required = false) String MemberContentDB,
		//	@RequestParam(value = "MessageDB", required = false) String MessageDB,
		//	@RequestParam(value = "FolderDB", required = false) String FolderDB,
		//	@RequestParam(value = "NotificationSettingDB", required = false) String NotificationSettingDB,
		//	@RequestParam(value = "SearchDB", required = false) String SearchDB
			
			
			){

		
		
		
		ModelAndView mav = new ModelAndView();
		SolrInputDocument thing = new SolrInputDocument();
		thing.addField("ID", ID);
	//	thing.addField("DBID", DBID);
		thing.addField("ScreenName", ScreenName);
		thing.addField("ScreenNameStatus", ScreenNameStatus);
		thing.addField("Name", Name);
		thing.addField("CategoryID", CategoryID);
		thing.addField("SubCategoryID", SubCategoryID);

		
	//	thing.addField("ParentCategoryID", ParentCategoryID);
		
		
		thing.addField("CategoryName", CategoryName);
		thing.addField("SubCategoryName", SubCategoryName);
	//	thing.addField("ParentCategoryName", ParentCategoryName);
		thing.addField("Address", Address);
		thing.addField("Email", Email);
		thing.addField("City", City);
		thing.addField("State", State);
		thing.addField("Country", Country);
		thing.addField("CityID", CityID);
		thing.addField("StateID", StateID);
		thing.addField("CountryID", CountryID);
		thing.addField("ZipCode", ZipCode);
		thing.addField("Affiliation", Affiliation);
		thing.addField("About", About);
		thing.addField("Description", Description);
		thing.addField("Biography", Biography);
		thing.addField("Awards", Awards);
		thing.addField("Phone", Phone);
		thing.addField("Website", Website);
		thing.addField("YouTube", YouTube);
		thing.addField("ReleaseDate", dateClient.addDateToSolr(ReleaseDate, request));
		thing.addField("Genre", Genre);
		thing.addField("ISBN", ISBN);
		thing.addField("Publisher", Publisher);
		thing.addField("Founded", Founded);
		thing.addField("Products", Products);
		thing.addField("CompanyOverview", CompanyOverview);
		thing.addField("Mission", Mission);
		thing.addField("Built", Built);
		thing.addField("Features", Features);
		thing.addField("MPG", MPG);
		thing.addField("GeneralInformation", GeneralInformation);
		thing.addField("Studio", Studio);
		thing.addField("PlotOutline", PlotOutline);
		thing.addField("Starring", Starring);
		thing.addField("DirectedBy", DirectedBy);
		thing.addField("WrittenBy", WrittenBy);
		
		thing.addField("ScreenplayBy", ScreenplayBy);
		thing.addField("ProducedBy", ProducedBy);
		
		
		thing.addField("ProfileImageJson", ProfileImageJson);
		thing.addField("ImageJson", ImageJson);
		
		
		
		
		thing.addField("Members", Members);
		thing.addField("RecordLabel", RecordLabel);
		thing.addField("History", History);
		thing.addField("Network", Network);
		thing.addField("Season", Season);
		thing.addField("ImageName", ImageName);
		
		thing.addField("CreatedDate", dateClient.addDateToSolr(CreatedDate, request));
		thing.addField("ActivationDate", dateClient.addDateToSolr(ActivationDate, request));
		thing.addField("UpdatedDate", dateClient.addDateToSolr(UpdatedDate , request));
		thing.addField("DeletionDate", dateClient.addDateToSolr(DeletionDate, request));
		thing.addField("LastLoginDate", dateClient.addDateToSolr(LastLoginDate, request));
	//	thing.addField("IsActive", IsActive);
	//	thing.addField("IsDeleted", IsDeleted);
		thing.addField("IsFeatured", IsFeatured);
		thing.addField("IsCrawlerAllow", IsCrawlerAllow);
		thing.addField("Role", Role);
		thing.addField("OnlineStatus", OnlineStatus);
		thing.addField("WhoCanSeeInSearch", WhoCanSeeInSearch);
		thing.addField("ShowWelcomePage", ShowWelcomePage);
		thing.addField("ShowCompleteStatus", ShowCompleteStatus);
		thing.addField("IsFundermailSent", IsFundermailSent);
		thing.addField("Status", Status);
		thing.addField("ProfileImage", ProfileImage);
		thing.addField("ProfileFileID", ProfileFileID);
		
		thing.addField("OldWikiID", OldWikiID);
		thing.addField("WikiInfo", WikiInfo);
		
		thing.addField("RestrictAge", RestrictAge);
		thing.addField("RestrictCountry", RestrictCountry);
		thing.addField("RestrictPost", RestrictPost);
		
		// Adding only for the dummy search not in usa in finddirectoryfaces.json
		thing.addField("NameForDirectorySearch", Name.replaceAll("\\s",""));
		
		
	//	thing.addField("ContactDB", ContactDB);
	//	thing.addField("UpdateDB", UpdateDB);
	//	thing.addField("MemberInfoDB", MemberInfoDB);
	//	thing.addField("CommentDB", CommentDB);
	//	thing.addField("MemberContentDB", MemberContentDB);
	//	thing.addField("MessageDB", MessageDB);
	//	thing.addField("FolderDB", FolderDB);
	//	thing.addField("NotificationSettingDB", NotificationSettingDB);
//		thing.addField("SearchDB", SearchDB);
		Adder.addPThings(serverurlConstants.ADD_THINGS_URL ,thing);
		String result = "success";
		mav.addObject("result",result);
		return mav;
	}
	
	
	@RequestMapping(value = "/deleteThing")
	public ModelAndView deleteThing(
			@RequestParam(value = "thingid", required = false) String thingid)
	{
		ModelAndView mav = new ModelAndView();
		SolrInputDocument connection = new SolrInputDocument();
		connection.addField("ThingsId", thingid);	
		Adder.deleteConnection(ServerurlConstants.ADD_THINGS_URL ,connection);
		String result = "success";
		mav.addObject("result",result);
		return mav;
	}
	
	
	@RequestMapping(value = "/searchbyid")
	public ModelAndView getSearchById(
			HttpServletRequest request,
			@RequestParam(value = "Id", required = false) String Id,
			@RequestParam(value = "ProfileID", required = false , defaultValue = "0" ) String ProfileID,
			@RequestParam(value = "Age", required = false , defaultValue = "0" ) String RestrictAge,
			@RequestParam(value = "CountryId", required = false , defaultValue = "0") String RestrictCountry,
			@RequestParam(value = "IsAlcohlic", required = false ,  defaultValue = "0") int Alcohol
			){
			ModelAndView mav = new ModelAndView();
			Object[] resultArr = null;
			try{
			String privayQuery=privacyClient.commonPrivacyQuery(RestrictAge, RestrictCountry, Alcohol , ProfileID);
				
			List<Things> mythings = new ArrayList<Things>();
			
			String queryText = "(OldWikiID:"+Id+") ";
			
			queryText="("+queryText+privayQuery+")";
			
			System.out.println("Query------->"+queryText);
			resultArr =  searchThingsClient.queryCheck(queryText,0,10, request);
			mythings=(List<Things>) resultArr[0];			
			try{
				mav.addObject("ID",mythings.get(0).getID());			
				mav.addObject("ScreenName",mythings.get(0).getScreenName());
				mav.addObject("ScreenNameStatus",mythings.get(0).getScreenNameStatus());
				mav.addObject("Name",mythings.get(0).getName());
				mav.addObject("CategoryID",mythings.get(0).getCategoryID());
				mav.addObject("SubCategoryID",mythings.get(0).getSubCategoryID());
				mav.addObject("CategoryName",mythings.get(0).getCategoryName());
				mav.addObject("SubCategoryName",mythings.get(0).getSubCategoryName());
				mav.addObject("Address",mythings.get(0).getAddress());
				mav.addObject("Email",mythings.get(0).getEmail());
				mav.addObject("City",mythings.get(0).getCity());
				mav.addObject("State",mythings.get(0).getState());
				mav.addObject("Country",mythings.get(0).getCountry());
				mav.addObject("CityID",mythings.get(0).getCityID());
				mav.addObject("StateID",mythings.get(0).getStateID());
				mav.addObject("CountryID",mythings.get(0).getCountryID());
				mav.addObject("ZipCode",mythings.get(0).getZipCode());
				mav.addObject("Affiliation",mythings.get(0).getAffiliation());
				mav.addObject("About",mythings.get(0).getAbout());
				mav.addObject("Description",mythings.get(0).getDescription());
				mav.addObject("Biography",mythings.get(0).getBiography());
				mav.addObject("Awards",mythings.get(0).getAwards());
				mav.addObject("Phone",mythings.get(0).getPhone());
				mav.addObject("Website",mythings.get(0).getWebsite());
				mav.addObject("YouTube",mythings.get(0).getYouTube());
				mav.addObject("ReleaseDate",mythings.get(0).getReleaseDate());
				mav.addObject("Genre",mythings.get(0).getGenre());
				mav.addObject("ISBN",mythings.get(0).getISBN());
				mav.addObject("Publisher",mythings.get(0).getPublisher());
				mav.addObject("Founded",mythings.get(0).getFounded());
				mav.addObject("Products",mythings.get(0).getProducts());		
				mav.addObject("CompanyOverview",mythings.get(0).getCompanyOverview());
				mav.addObject("Mission",mythings.get(0).getMission());
				mav.addObject("Built",mythings.get(0).getBuilt());
				mav.addObject("Features",mythings.get(0).getFeatures());
				mav.addObject("MPG",mythings.get(0).getMPG());
				mav.addObject("GeneralInformation",mythings.get(0).getGeneralInformation());
				mav.addObject("Studio",mythings.get(0).getStudio());
				mav.addObject("PlotOutline",mythings.get(0).getPlotOutline());
				mav.addObject("Starring",mythings.get(0).getStarring());
				mav.addObject("DirectedBy",mythings.get(0).getDirectedBy());
				mav.addObject("WrittenBy",mythings.get(0).getWrittenBy());
				mav.addObject("ScreenplayBy",mythings.get(0).getScreenplayBy());
				mav.addObject("ProducedBy",mythings.get(0).getProducedBy());
				mav.addObject("Members",mythings.get(0).getMembers());
				mav.addObject("RecordLabel",mythings.get(0).getRecordLabel());
				mav.addObject("History",mythings.get(0).getHistory());
				mav.addObject("Network",mythings.get(0).getNetwork());
				mav.addObject("Season",mythings.get(0).getSeason());
				mav.addObject("ImageName",mythings.get(0).getImageName());
			//	mav.addObject("HasImage",mythings.get(0).getHasImage());
				mav.addObject("CreatedDate",mythings.get(0).getCreatedDate());
				mav.addObject("ActivationDate",mythings.get(0).getActivationDate());
				mav.addObject("UpdatedDate",mythings.get(0).getUpdatedDate());
				mav.addObject("DeletionDate",mythings.get(0).getDeletionDate());
				mav.addObject("LastLoginDate",mythings.get(0).getLastLoginDate());
			//	mav.addObject("IsActive",mythings.get(0).getIsActive());
			//	mav.addObject("IsDeleted",mythings.get(0).getIsDeleted());			
				mav.addObject("IsFeatured",mythings.get(0).getIsFeatured());
				mav.addObject("IsCrawlerAllow",mythings.get(0).getIsCrawlerAllow());
				mav.addObject("Role",mythings.get(0).getRole());
				mav.addObject("OnlineStatus",mythings.get(0).getOnlineStatus());
				mav.addObject("WhoCanSeeInSearch",mythings.get(0).getWhoCanSeeInSearch());
				mav.addObject("ShowWelcomePage",mythings.get(0).getShowWelcomePage());
				mav.addObject("ShowCompleteStatus",mythings.get(0).getShowCompleteStatus());
				mav.addObject("IsFundermailSent",mythings.get(0).getIsFundermailSent());
				mav.addObject("Status",mythings.get(0).getStatus());
				mav.addObject("ProfileImage",mythings.get(0).getProfileImage());
				mav.addObject("ProfileFileID",mythings.get(0).getProfileFileID());
				mav.addObject("OldWikiID",mythings.get(0).getOldWikiID());
				mav.addObject("WikiInfo",mythings.get(0).getWikiInfo());
				
				
				mav.addObject("ImageJson",mythings.get(0).getImageJson());
				mav.addObject("ProfileImageJson",mythings.get(0).getProfileImageJson());
				
				
				mav.addObject("RestrictAge",mythings.get(0).getRestrictAge());
				mav.addObject("RestrictCountry",mythings.get(0).getRestrictCountry());
				mav.addObject("RestrictPost",mythings.get(0).getRestrictPost());
				
			//	mav.addObject("ContactDB",mythings.get(0).getContactDB());
//				mav.addObject("UpdateDB",mythings.get(0).getUpdateDB());
//				mav.addObject("MemberInfoDB",mythings.get(0).getMemberInfoDB());
//				mav.addObject("CommentDB",mythings.get(0).getCommentDB());
//				mav.addObject("MemberContentDB",mythings.get(0).getMemberContentDB());
//				mav.addObject("MessageDB",mythings.get(0).getMessageDB());
//				mav.addObject("FolderDB",mythings.get(0).getFolderDB());
//				mav.addObject("NotificationSettingDB",mythings.get(0).getNotificationSettingDB());
//				mav.addObject("SearchDB",mythings.get(0).getSearchDB());
			}catch (Exception e) {
				e.printStackTrace();
				return mav;
			}
			
			return mav;
			}catch(Exception e){
				e.printStackTrace();
				return mav;
			}
	}	
	
	
	
	@RequestMapping(value = "/finddirectorythings")
	public @ResponseBody CollectionList  findDirectoryThings(
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
			resultArr = searchThingsClient.queryCheck(queryText,pageCount,size, request);
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

	
	
	@RequestMapping(value = "/updatethings")
	public @ResponseBody String  updateThings( HttpServletRequest request,
				@RequestParam(value = "ID", required = false) String ID,
				@RequestParam(value = "ScreenName", required = false) String ScreenName,
				@RequestParam(value = "ScreenNameStatus", required = false) String ScreenNameStatus,
				@RequestParam(value = "Name", required = false) String Name,
				@RequestParam(value = "CategoryID", required = false) String CategoryID,
				@RequestParam(value = "SubCategoryID", required = false) String SubCategoryID,
				@RequestParam(value = "CategoryName", required = false) String CategoryName,
				@RequestParam(value = "SubCategoryName", required = false) String SubCategoryName,
				@RequestParam(value = "ParentCategoryName", required = false) String ParentCategoryName,
				@RequestParam(value = "Address", required = false) String Address,
				@RequestParam(value = "Email", required = false) String Email,
				@RequestParam(value = "City", required = false) String City,
				@RequestParam(value = "State", required = false) String State,
				@RequestParam(value = "Country", required = false) String Country,
				@RequestParam(value = "CityID", required = false) String CityID,
				@RequestParam(value = "StateID", required = false) String StateID,
				@RequestParam(value = "CountryID", required = false) String CountryID,
				@RequestParam(value = "ZipCode", required = false) String ZipCode,
				@RequestParam(value = "Affiliation", required = false) String Affiliation,
				@RequestParam(value = "About", required = false) String About,			
				@RequestParam(value = "Description", required = false) String Description,
				@RequestParam(value = "Biography", required = false) String Biography,
				@RequestParam(value = "Awards", required = false) String Awards,
				@RequestParam(value = "Phone", required = false) String Phone,
				@RequestParam(value = "Website", required = false) String Website,
				@RequestParam(value = "YouTube", required = false) String YouTube,
				@RequestParam(value = "ReleaseDate", required = false) String ReleaseDate,
				@RequestParam(value = "Genre", required = false) String Genre,
				@RequestParam(value = "ISBN", required = false) String ISBN,
				@RequestParam(value = "Publisher", required = false) String Publisher,
				@RequestParam(value = "Founded", required = false) String Founded,
				@RequestParam(value = "Products", required = false) String Products,
				@RequestParam(value = "CompanyOverview", required = false) String CompanyOverview,
				@RequestParam(value = "Mission", required = false) String Mission,
				@RequestParam(value = "Built", required = false) String Built,
				@RequestParam(value = "Features", required = false) String Features,
				@RequestParam(value = "MPG", required = false) String MPG,
				@RequestParam(value = "GeneralInformation", required = false) String GeneralInformation,
				@RequestParam(value = "Studio", required = false) String Studio,
				@RequestParam(value = "PlotOutline", required = false) String PlotOutline,
				@RequestParam(value = "Starring", required = false) String Starring,
				@RequestParam(value = "DirectedBy", required = false) String DirectedBy,
				@RequestParam(value = "WrittenBy", required = false) String WrittenBy,
				
				// 2 ProducedBy , ProducedBy new field added on 25/11/2011
				
				@RequestParam(value = "ScreenplayBy", required = false) String ScreenplayBy,
				@RequestParam(value = "ProducedBy", required = false) String ProducedBy,
				@RequestParam(value = "Members", required = false) String Members,
				@RequestParam(value = "RecordLabel", required = false) String RecordLabel,
				@RequestParam(value = "History", required = false) String History,
				@RequestParam(value = "Network", required = false) String Network,
				@RequestParam(value = "Season", required = false) String Season,
				@RequestParam(value = "ImageName", required = false) String ImageName,
				@RequestParam(value = "CreatedDate", required = false) String CreatedDate,
				@RequestParam(value = "ActivationDate", required = false) String ActivationDate,
				@RequestParam(value = "UpdatedDate", required = false) String UpdatedDate,
				@RequestParam(value = "DeletionDate", required = false) String DeletionDate,
				@RequestParam(value = "LastLoginDate", required = false) String LastLoginDate,
				@RequestParam(value = "IsFeatured", required = false) String IsFeatured,
				@RequestParam(value = "IsCrawlerAllow", required = false) String IsCrawlerAllow,
				@RequestParam(value = "Role", required = false) String Role,
				@RequestParam(value = "OnlineStatus", required = false) String OnlineStatus,
				@RequestParam(value = "WhoCanSeeInSearch", required = false) String WhoCanSeeInSearch,
				@RequestParam(value = "ShowWelcomePage", required = false) String ShowWelcomePage,
				@RequestParam(value = "ShowCompleteStatus", required = false) String ShowCompleteStatus,
				@RequestParam(value = "IsFundermailSent", required = false) String IsFundermailSent,
				@RequestParam(value = "Status", required = false) String Status,
				@RequestParam(value = "ProfileImage", required = false) String ProfileImage,
				@RequestParam(value = "ProfileFileID", required = false) String ProfileFileID,
				@RequestParam(value = "OldWikiID", required = false) String OldWikiID,
				@RequestParam(value = "WikiInfo", required = false) String WikiInfo,
				@RequestParam(value = "RestrictAge", required = false) String RestrictAge,
				@RequestParam(value = "RestrictCountry", required = false) String RestrictCountry,
				@RequestParam(value = "RestrictPost", required = false) String RestrictPost,
				@RequestParam(value = "jsoncallback", required = false) String search){
		
			ModelAndView mav = new ModelAndView();
			Object[] resultArr = null;
			String queryText=null;
			String[] paramNameget=null;
			
			ArrayList<String> requestParam=new ArrayList<String>();
			
			List<Things> thingsFromSolr = null;
			Enumeration paramNames = request.getParameterNames();
			queryText = "(ID:"+ID+")";
			resultArr=searchThingsClient.updateThings(queryText, request);
			thingsFromSolr= (List<Things>) resultArr[0];

			
			while(paramNames.hasMoreElements()) {
			String params=(String) paramNames.nextElement();
			requestParam.add(params);
			}  
			
			
			SolrInputDocument thing = new SolrInputDocument();
			thing.addField("ID", ID);

			if(requestParam.contains("ScreenName")){
				thing.addField("ScreenName", ScreenName);
			}else{
				thing.addField("ScreenName", thingsFromSolr.get(0).getScreenName());	
			}
			
			if(requestParam.contains("ScreenNameStatus")){
				thing.addField("ScreenNameStatus", ScreenNameStatus);
			}else{
				thing.addField("ScreenNameStatus", thingsFromSolr.get(0).getScreenNameStatus().toString());	
			}
			
			if(requestParam.contains("Name")){
				thing.addField("Name", Name);
			}else{
				thing.addField("Name", thingsFromSolr.get(0).getName());	
			}
			
			if(requestParam.contains("CategoryID")){
				thing.addField("CategoryID", CategoryID);
			}else{
				thing.addField("CategoryID", thingsFromSolr.get(0).getCategoryID());	
			}
			

			if(requestParam.contains("SubCategoryID")){
				thing.addField("SubCategoryID", SubCategoryID);
			}else{
				thing.addField("SubCategoryID", thingsFromSolr.get(0).getSubCategoryID());	
			}

			
			if(requestParam.contains("CategoryName")){
				thing.addField("CategoryName", CategoryName);
			}else{
				thing.addField("CategoryName", thingsFromSolr.get(0).getCategoryName());	
			}
	
			
			if(requestParam.contains("SubCategoryName")){
				thing.addField("SubCategoryName", SubCategoryName);
			}else{
				thing.addField("SubCategoryName", thingsFromSolr.get(0).getSubCategoryName());	
			}
			
			
			if(requestParam.contains("Address")){
				thing.addField("Address", Address);
			}else{
				thing.addField("Address", thingsFromSolr.get(0).getAddress());	
			}
			
			if(requestParam.contains("Email")){
				thing.addField("Email", Email);
			}else{
				thing.addField("Email", thingsFromSolr.get(0).getEmail());	
			}
			
			
			if(requestParam.contains("City")){
				thing.addField("City", City);
			}else{
				thing.addField("City", thingsFromSolr.get(0).getCity());	
			}
			
			
			if(requestParam.contains("State")){
				thing.addField("State", State);
			}else{
				thing.addField("State", thingsFromSolr.get(0).getState());	
			}
			
			
			if(requestParam.contains("Country")){
				thing.addField("Country", Country);
			}else{
				thing.addField("Country", thingsFromSolr.get(0).getCountry());	
			}

			
			if(requestParam.contains("CityID")){
				thing.addField("CityID", CityID);
			}else{
				thing.addField("CityID", thingsFromSolr.get(0).getCityID());	
			}
			
			
			if(requestParam.contains("StateID")){
				thing.addField("StateID", StateID);
			}else{
				thing.addField("StateID", thingsFromSolr.get(0).getStateID());	
			}
			
			if(requestParam.contains("CountryID")){
				thing.addField("CountryID", CountryID);
			}else{
				thing.addField("CountryID", thingsFromSolr.get(0).getCountryID());	
			}
			
			if(requestParam.contains("ZipCode")){
				thing.addField("ZipCode", ZipCode);
			}else{
				thing.addField("ZipCode", thingsFromSolr.get(0).getZipCode());	
			}
			
			if(requestParam.contains("Affiliation")){
				thing.addField("Affiliation", Affiliation);
			}else{
				thing.addField("Affiliation", thingsFromSolr.get(0).getAffiliation());	
			}
			
			
			if(requestParam.contains("About")){
				thing.addField("About", About);
			}else{
				thing.addField("About", thingsFromSolr.get(0).getAbout());	
			}
			
			
			if(requestParam.contains("Description")){
				thing.addField("Description", Description);
			}else{
				thing.addField("Description", thingsFromSolr.get(0).getDescription());	
			}
			
			if(requestParam.contains("Biography")){
				thing.addField("Biography", Biography);
			}else{
				thing.addField("Biography", thingsFromSolr.get(0).getBiography());	
			}
			
			if(requestParam.contains("Awards")){
				thing.addField("Awards", Awards);
			}else{
				thing.addField("Awards", thingsFromSolr.get(0).getAwards());	
			}
			
			
			if(requestParam.contains("Phone")){
				thing.addField("Phone", Phone);
			}else{
				thing.addField("Phone", thingsFromSolr.get(0).getPhone());	
			}
			
			
			if(requestParam.contains("Website")){
				thing.addField("Website", Website);
			}else{
				thing.addField("Website", thingsFromSolr.get(0).getWebsite());	
			}
			
			
			if(requestParam.contains("YouTube")){
				thing.addField("YouTube", YouTube);
			}else{
				thing.addField("YouTube", thingsFromSolr.get(0).getYouTube());	
			}
			
			
			if(requestParam.contains("ReleaseDate")){
				thing.addField("ReleaseDate", dateClient.addDateToSolr(ReleaseDate, request));
			}else{
				thing.addField("ReleaseDate", dateClient.addDateToSolr(thingsFromSolr.get(0).getReleaseDate(), request));	
			}
			
			
			if(requestParam.contains("Genre")){
				thing.addField("Genre", Genre);
			}else{
				thing.addField("Genre", thingsFromSolr.get(0).getGenre());	
			}
			
			
			if(requestParam.contains("ISBN")){
				thing.addField("ISBN", ISBN);
			}else{
				thing.addField("ISBN", thingsFromSolr.get(0).getISBN());	
			}
			
			
			if(requestParam.contains("Publisher")){
				thing.addField("Publisher", Publisher);
			}else{
				thing.addField("Publisher", thingsFromSolr.get(0).getPublisher());	
			}
			
			if(requestParam.contains("Founded")){
				thing.addField("Founded", Founded);
			}else{
				thing.addField("Founded", thingsFromSolr.get(0).getFounded());	
			}
			
			
			if(requestParam.contains("Products")){
				thing.addField("Products", Products);
			}else{
				thing.addField("Products", thingsFromSolr.get(0).getProducts());	
			}
		
			
			if(requestParam.contains("CompanyOverview")){
				thing.addField("CompanyOverview", CompanyOverview);
			}else{
				thing.addField("CompanyOverview", thingsFromSolr.get(0).getCompanyOverview());	
			}
			
			if(requestParam.contains("Mission")){
				thing.addField("Mission", Mission);
			}else{
				thing.addField("Mission", thingsFromSolr.get(0).getMission());	
			}
			
			
			if(requestParam.contains("Built")){
				thing.addField("Built", Built);
			}else{
				thing.addField("Built", thingsFromSolr.get(0).getBuilt());	
			}
			
			
			if(requestParam.contains("Features")){
				thing.addField("Features", Features);
			}else{
				thing.addField("Features", thingsFromSolr.get(0).getFeatures());	
			}
			
			
			if(requestParam.contains("MPG")){
				thing.addField("MPG", MPG);
			}else{
				thing.addField("MPG", thingsFromSolr.get(0).getMPG());	
			}
			
			
			if(requestParam.contains("GeneralInformation")){
				thing.addField("GeneralInformation", GeneralInformation);
			}else{
				thing.addField("GeneralInformation", thingsFromSolr.get(0).getGeneralInformation());	
			}
			
			if(requestParam.contains("Studio")){
				thing.addField("Studio", Studio);
			}else{
				thing.addField("Studio", thingsFromSolr.get(0).getStudio());	
			}
			
			if(requestParam.contains("PlotOutline")){
				thing.addField("PlotOutline", PlotOutline);
			}else{
				thing.addField("PlotOutline", thingsFromSolr.get(0).getPlotOutline());	
			}
			
			if(requestParam.contains("Starring")){
				thing.addField("Starring", Starring);
			}else{
				thing.addField("Starring", thingsFromSolr.get(0).getStarring());	
			}
			
			

			if(requestParam.contains("DirectedBy")){
				thing.addField("DirectedBy", DirectedBy);
			}else{
				thing.addField("DirectedBy", thingsFromSolr.get(0).getDirectedBy());	
			}

			
			if(requestParam.contains("WrittenBy")){
				thing.addField("WrittenBy", WrittenBy);
			}else{
				thing.addField("WrittenBy", thingsFromSolr.get(0).getWrittenBy());	
			}
			
			if(requestParam.contains("ScreenplayBy")){
				thing.addField("ScreenplayBy", ScreenplayBy);
			}else{
				thing.addField("ScreenplayBy", thingsFromSolr.get(0).getScreenplayBy());	
			}
			
			
			if(requestParam.contains("ProducedBy")){
				thing.addField("ProducedBy", ProducedBy);
			}else{
				thing.addField("ProducedBy", thingsFromSolr.get(0).getProducedBy());	
			}
			
			
			if(requestParam.contains("Members")){
				thing.addField("Members", Members);
			}else{
				thing.addField("Members", thingsFromSolr.get(0).getMembers());	
			}
			
			if(requestParam.contains("RecordLabel")){
				thing.addField("RecordLabel", RecordLabel);
			}else{
				thing.addField("RecordLabel", thingsFromSolr.get(0).getRecordLabel());	
			}
			
			if(requestParam.contains("History")){
				thing.addField("History", History);
			}else{
				thing.addField("History", thingsFromSolr.get(0).getHistory());	
			}
			
			
			if(requestParam.contains("Network")){
				thing.addField("Network", Network);
			}else{
				thing.addField("Network", thingsFromSolr.get(0).getNetwork());	
			}
			
			
			if(requestParam.contains("Season")){
				thing.addField("Season", Season);
			}else{
				thing.addField("Season", thingsFromSolr.get(0).getSeason());	
			}
			
			
			if(requestParam.contains("ImageName")){
				thing.addField("ImageName", ImageName);
			}else{
				thing.addField("ImageName", thingsFromSolr.get(0).getImageName());	
			}
			
			
			if(requestParam.contains("CreatedDate")){
				thing.addField("CreatedDate", dateClient.addDateToSolr(CreatedDate, request));
			}else{
				thing.addField("CreatedDate", dateClient.addDateToSolr(thingsFromSolr.get(0).getCreatedDate(), request));	
			}
			
			
			if(requestParam.contains("ActivationDate")){
				thing.addField("ActivationDate", dateClient.addDateToSolr(ActivationDate, request));
			}else{
				thing.addField("ActivationDate", dateClient.addDateToSolr(thingsFromSolr.get(0).getActivationDate(), request));	
			}
			
			
			if(requestParam.contains("DeletionDate")){
				thing.addField("DeletionDate", dateClient.addDateToSolr(DeletionDate, request));
			}else{
				thing.addField("DeletionDate", dateClient.addDateToSolr(thingsFromSolr.get(0).getDeletionDate(), request));	
			}
			
			
			if(requestParam.contains("LastLoginDate")){
				thing.addField("LastLoginDate", dateClient.addDateToSolr(LastLoginDate, request));
			}else{
				thing.addField("LastLoginDate", dateClient.addDateToSolr(thingsFromSolr.get(0).getLastLoginDate(), request));	
			}
			
			
			if(requestParam.contains("IsFeatured")){
				thing.addField("IsFeatured", IsFeatured);
			}else{
				thing.addField("IsFeatured", thingsFromSolr.get(0).getIsFeatured());	
			}
			
			if(requestParam.contains("IsCrawlerAllow")){
				thing.addField("IsCrawlerAllow", IsCrawlerAllow);
			}else{
				thing.addField("IsCrawlerAllow", thingsFromSolr.get(0).getIsCrawlerAllow());	
			}
			
			
			if(requestParam.contains("Role")){
				thing.addField("Role", Role);
			}else{
				thing.addField("Role", thingsFromSolr.get(0).getRole());	
			}
		
			
			if(requestParam.contains("OnlineStatus")){
				thing.addField("OnlineStatus", OnlineStatus);
			}else{
				thing.addField("OnlineStatus", thingsFromSolr.get(0).getOnlineStatus());	
			}
			
			
			if(requestParam.contains("WhoCanSeeInSearch")){
				thing.addField("WhoCanSeeInSearch", WhoCanSeeInSearch);
			}else{
				thing.addField("WhoCanSeeInSearch", thingsFromSolr.get(0).getWhoCanSeeInSearch());	
			}
			
			
			if(requestParam.contains("ShowWelcomePage")){
				thing.addField("ShowWelcomePage", ShowWelcomePage);
			}else{
				thing.addField("ShowWelcomePage", thingsFromSolr.get(0).getShowWelcomePage());	
			}

			if(requestParam.contains("ShowCompleteStatus")){
				thing.addField("ShowCompleteStatus", ShowCompleteStatus);
			}else{
				thing.addField("ShowCompleteStatus", thingsFromSolr.get(0).getShowCompleteStatus());	
			}
			
			
			if(requestParam.contains("IsFundermailSent")){
				thing.addField("IsFundermailSent", IsFundermailSent);
			}else{
				thing.addField("IsFundermailSent", thingsFromSolr.get(0).getIsFundermailSent());	
			}
			
			
			if(requestParam.contains("Status")){
				thing.addField("Status", Status);
			}else{
				thing.addField("Status", thingsFromSolr.get(0).getStatus());	
			}
			
			
			if(requestParam.contains("ProfileImage")){
				thing.addField("ProfileImage", ProfileImage);
			}else{
				thing.addField("ProfileImage", thingsFromSolr.get(0).getProfileImage());	
			}
			
			
			
			if(requestParam.contains("ProfileFileID")){
				thing.addField("ProfileFileID", ProfileFileID);
			}else{
				thing.addField("ProfileFileID", thingsFromSolr.get(0).getProfileFileID());	
			}
			
			if(requestParam.contains("OldWikiID")){
				thing.addField("OldWikiID", OldWikiID);
			}else{
				thing.addField("OldWikiID", thingsFromSolr.get(0).getOldWikiID());	
			}
			
			
			if(requestParam.contains("WikiInfo")){
				thing.addField("WikiInfo", WikiInfo);
			}else{
				thing.addField("WikiInfo", thingsFromSolr.get(0).getWikiInfo());	
			}
			
			
			if(requestParam.contains("RestrictAge")){
				thing.addField("RestrictAge", RestrictAge);
			}else{
				thing.addField("RestrictAge", thingsFromSolr.get(0).getRestrictAge());	
			}
			
			if(requestParam.contains("RestrictCountry")){
				thing.addField("RestrictCountry", RestrictCountry);
			}else{
				thing.addField("RestrictCountry", thingsFromSolr.get(0).getRestrictCountry());	
			}
			
			
			if(requestParam.contains("RestrictPost")){
				thing.addField("RestrictPost", RestrictPost);
			}else{
				thing.addField("RestrictPost", thingsFromSolr.get(0).getRestrictPost());	
			}
			
			
			if(requestParam.contains("NameForDirectorySearch")){
				thing.addField("NameForDirectorySearch", Name.replaceAll("\\s",""));
			}else{
				thing.addField("NameForDirectorySearch", thingsFromSolr.get(0).getName());	
			}
			
				
			Adder.addPThings(serverurlConstants.ADD_THINGS_URL ,thing);
			String result = "success";
			mav.addObject("result",result);
			
			return "sucess";
	}
	
	
	
	@RequestMapping(value = "/findThingsAll")
	public ModelAndView findThingsAll(
			HttpServletRequest request,
			@RequestParam(value = "member_id", required = false) String memberId,
			@RequestParam(value = "member_name", required = false) String messageName,
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "size", required = false) int size,
			@RequestParam(value = "jsoncallback", required = false) String search){
		
		ModelAndView mav = new ModelAndView();
		messageName = messageName.toLowerCase();
		int pageCount = page * 10;
		Object[] resultArrThings = null;
		long numFoundThings  = 0;
		List thingsList = null;		
		String queryText =  "(Name:"+messageName+"*)";
		resultArrThings =  searchThingsClient.thingsHeaderSearch(queryText,pageCount,size, request);
		numFoundThings  = Integer.parseInt(resultArrThings[1].toString());
		thingsList      = (List) resultArrThings[0];
		
		
		if(thingsList.size() > 0)
		{
			//Collections.sort(fullContacts);
			if(search == null)
			{
				mav.addObject("StatusOutput","true");
				mav.addObject("TotalRecords",numFoundThings);
				mav.addObject("Collection",thingsList);
			}
			else
			{
				mav.addObject("StatusOutput","true");
				mav.addObject("TotalRecords",numFoundThings);
				mav.addObject("Collection",thingsList);
			}
		}
		else
		{
			mav.addObject("StatusOutput","true");
			mav.addObject("TotalRecords",numFoundThings);
			mav.addObject("Collection",new ArrayList());
		}
		return mav;
	}
	
	
	
	@RequestMapping(value = "/findthingsbycategory")
	public @ResponseBody CollectionList  findthingsbycategory(
			HttpServletRequest request,
			@RequestParam(value = "starting_letter", required = false) String memberName,
			@RequestParam(value = "type", required = false , defaultValue = "0") String type,
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
			
			String queryText = "";
			int pageCount = page * size;
			List contactsQuery = null;
			String numFound  = "0";
			Object[] resultArr = null;
		//	queryText = "(Name:"+memberNameArr[0] +"*)";
			
				
			//New query only for the directory searh		
			
			if(type.equals("1"))			
			queryText = "(NameForDirectorySearch:"+memberName+"* AND  CategoryID:3 )";
			if(type.equals("2"))			
				queryText = "(NameForDirectorySearch:"+memberName+"* AND  CategoryID:4 )";
			if(type.equals("3"))			
				queryText = "(NameForDirectorySearch:"+memberName+"* AND  CategoryID:1 )";
			if(type.equals("4"))			
				queryText = "(NameForDirectorySearch:"+memberName+"* AND  CategoryID:6 )";
			if(type.equals("0"))			
				queryText = "(NameForDirectorySearch:"+memberName+"* AND  CategoryID:85 )";
			
			queryText="("+queryText+privayQuery+")";
			resultArr = searchThingsClient.findthingsbycategory(queryText,pageCount,size, request);
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

	
	
	
	}
	
	
	

