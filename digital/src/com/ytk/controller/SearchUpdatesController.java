package com.ytk.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ytk.client.ConnectionClient;
import com.ytk.client.DateClient;
import com.ytk.client.MamCacheClient;
import com.ytk.client.PrivacyClient;
import com.ytk.client.UpdateClient;
import com.ytk.client.UpdatePrivacyClient;
import com.ytk.models.PCommunity;
import com.ytk.models.Updates;
import com.ytk.utility.Adder;
import com.ytk.utility.ServerurlConstants;

@Controller
@RequestMapping("/searchUpdates/*")
public class SearchUpdatesController {

	@Autowired
	UpdateClient updateClient;

	@Autowired
	DateClient dateClient;

	@Autowired
	UpdatePrivacyClient updatePrivacyClient;

	@Autowired
	PrivacyClient privacyClient;

	@Autowired
	ConnectionClient connectionClient;

	@Autowired
	MamCacheClient mamCacheClient;

	@Autowired
	ServerurlConstants serverurlConstants;

	/**
	 * Service to Search Updates with Relevance
	 * 
	 * @param member_id
	 * @return a List of Updates in response
	 */

	@RequestMapping(value = "/findupdatesold")
	public ModelAndView findUpdates(
			HttpServletRequest request,
			@RequestParam(value = "AccountID", required = false) String AccountID,
			@RequestParam(value = "LoginID", required = false) String LoginID,
			@RequestParam(value = "Page", required = false) int Page) {
		ModelAndView mav = new ModelAndView();
		List<Updates> updated = new ArrayList<Updates>();
		String queryText = AccountID;
		updated = updateClient.fetchUpdates(queryText, 0, 350, request);
		mav.addObject(updated);
		return mav;
	}

	@RequestMapping(value = "/findupdatesbysection_bak")
	public ModelAndView findUpdatesBysection(
			HttpServletRequest request,
			@RequestParam(value = "AccountID", required = false) String AccountID,
			@RequestParam(value = "LoginID", required = false) String LoginID,
			@RequestParam(value = "GeneratorAccountType", required = false) String GeneratorAccountType,
			@RequestParam(value = "GeneratorAccountID", required = false) String GeneratorAccountID,
			@RequestParam(value = "Page", required = false) int Page) {
		ModelAndView mav = new ModelAndView();
		String queryText = null;
		List<Updates> updated = new ArrayList<Updates>();

		// On Satender request at Nov-04-2011 at 5:44 pm

		// queryText =
		// "(AccountID:"+AccountID+" AND GeneratorAccountType:"+GeneratorAccountType+" AND GeneratorAccountID:"+GeneratorAccountID+")";

		queryText = "(GeneratorAccountType:" + GeneratorAccountType
				+ " AND GeneratorAccountID:" + GeneratorAccountID + ")";

		updated = updateClient.fetchUpdates(queryText, 0, 350, request);
		mav.addObject(updated);
		return mav;
	}

	@RequestMapping(value = "/addupdate")
	public ModelAndView addConnection(

			@RequestParam(value = "ID", required = false) String ID,
			@RequestParam(value = "Type", required = false) String Type,
			@RequestParam(value = "Section", required = false) String Section,
			@RequestParam(value = "EventID", required = false) String EventID,
			@RequestParam(value = "EventSectionID", required = false) String EventSectionID,
			@RequestParam(value = "AccountType", required = false) String AccountType,
			@RequestParam(value = "AccountID", required = false) String AccountID,

			@RequestParam(value = "GeneratorAccountType", required = false) String GeneratorAccountType,
			@RequestParam(value = "GeneratorAccountID", required = false) String GeneratorAccountID,

			@RequestParam(value = "IsRelatedToUpdates", required = false) String IsRelatedToUpdates,
			@RequestParam(value = "IsRelatedToSearch", required = false) String IsRelatedToSearch,
			@RequestParam(value = "CommentSection", required = false) String CommentSection,

			@RequestParam(value = "AccountRestrictAge", required = false) String AccountRestrictAge,
			@RequestParam(value = "AccountRestrictCountry", required = false) String AccountRestrictCountry,
			@RequestParam(value = "GeneratorAccountRestrictAge", required = false) String GeneratorAccountRestrictAge,
			@RequestParam(value = "GeneratorAccountRestrictCountry", required = false) String GeneratorAccountRestrictCountry,

			@RequestParam(value = "Everyone", required = false, defaultValue = "1") String Everyone,
			@RequestParam(value = "OnlyMe", required = false, defaultValue = "0") String OnlyMe,
			@RequestParam(value = "FoldersHideList", required = false, defaultValue = "") String FoldersHideList,
			@RequestParam(value = "ContactsHideList", required = false, defaultValue = "0") String ContactsHideList,
			@RequestParam(value = "ContactsShowList", required = false, defaultValue = "0") String ContactsShowList,
			@RequestParam(value = "FoldersShowList", required = false, defaultValue = "") String FoldersShowList,
			@RequestParam(value = "IsCustom", required = false, defaultValue = "0") String IsCustom,
			@RequestParam(value = "Status", required = false) String Status,

			@RequestParam(value = "HideBy", required = false, defaultValue = "0") String HideBy,
			@RequestParam(value = "CommentCount", required = false, defaultValue = "0") String CommentCount,
			@RequestParam(value = "CommentJson", required = false, defaultValue = "0") String CommentJson,

			@RequestParam(value = "PeopleComented", required = false, defaultValue = "0") String PeopleComented,

			@RequestParam(value = "TimeStamp", required = false) String TimeStamp) {

		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat dateFormat1 = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Object formatdate = null;

		try {
			formatdate = dateFormat.parse(TimeStamp);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Date FormatTimeStamp = (Date) formatdate;

		ModelAndView mav = new ModelAndView();
		SolrInputDocument updates = new SolrInputDocument();
		updates.addField("ID", ID);
		updates.addField("Type", Type);
		updates.addField("Section", Section);
		updates.addField("EventID", EventID);
		updates.addField("EventSectionID", EventSectionID);
		updates.addField("AccountType", AccountType);
		updates.addField("AccountID", AccountID);

		updates.addField("GeneratorAccountType", GeneratorAccountType);
		updates.addField("GeneratorAccountID", GeneratorAccountID);

		updates.addField("IsRelatedToUpdates", IsRelatedToUpdates);
		updates.addField("IsRelatedToSearch", IsRelatedToSearch);
		updates.addField("CommentSection", CommentSection);
		updates.addField("TimeStamp", dateFormat1.format(FormatTimeStamp));
		updates.addField("Everyone", Everyone);
		updates.addField("OnlyMe", OnlyMe);

		updates.addField("AccountRestrictAge", AccountRestrictAge);
		updates.addField("AccountRestrictCountry", AccountRestrictCountry);
		updates.addField("GeneratorAccountRestrictAge",	GeneratorAccountRestrictAge);
		updates.addField("GeneratorAccountRestrictCountry",	GeneratorAccountRestrictCountry);

		if (FoldersHideList.equals(""))
			updates.addField("FoldersHideList", "0");
		else
			updates.addField("FoldersHideList", FoldersHideList);

		
		
		updates.addField("ContactsHideList", ContactsHideList);

		if (ContactsShowList.equals(""))
			updates.addField("ContactsShowList", "0");
		else
			updates.addField("ContactsShowList", ContactsShowList);

		if (FoldersShowList.equals(""))
			updates.addField("FoldersShowList", "0");
		else
			updates.addField("FoldersShowList", FoldersShowList);

		updates.addField("HideBy", HideBy);

		updates.addField("CommentCount", CommentCount);
		updates.addField("CommentJson", CommentJson);
		updates.addField("PeopleComented", PeopleComented);

		updates.addField("IsCustom", IsCustom);
		updates.addField("Status", Status);

		Adder.addUpdates(serverurlConstants.ADD_UPDATE_URL, updates);
		String result = "success";
		mav.addObject("result", result);
		return mav;
	}

	/**
	 * Service to DELETE Updates
	 * 
	 * @param ID
	 * @return result String
	 */

	@RequestMapping(value = "/deleteupdate")
	public ModelAndView deleteUpdates(
			@RequestParam(value = "ID", required = false) String ID) {
		ModelAndView mav = new ModelAndView();
		SolrInputDocument updates = new SolrInputDocument();
		updates.addField("ID", ID);
		Adder.deleteUpdates(serverurlConstants.DELETE_UPDATES_URL, ID);
		String result = "success";
		mav.addObject("result", result);
		return mav;
	}

	/***
	 * 
	 * @param AccountID
	 * @param Type
	 * @return
	 */
	@RequestMapping(value = "/deletebyaccounttypeupdate")
	public ModelAndView deleteByAccountAndType(
			@RequestParam(value = "AccountID", required = false) String AccountID,
			@RequestParam(value = "Type", required = false) String Type) {
		ModelAndView mav = new ModelAndView();
		SolrInputDocument updates = new SolrInputDocument();
		updates.addField("AccountID", AccountID);
		updates.addField("Type", Type);
		String query = "AccountID:" + AccountID + " AND Type:" + Type;
		Adder.deleteUpdatesByAccountType(
				serverurlConstants.DELETE_UPDATES_BY_ACCOUNT_TYPE_URL, updates,
				query);
		String result = "success";
		mav.addObject("result", result);
		return mav;
	}

	/***
	 * 
	 * @param AccountID
	 * @param Type
	 * @return
	 */
	@RequestMapping(value = "/deletebyeventaccounttype")
	public ModelAndView deleteByEventAccountAndType(
			HttpServletRequest request,
			@RequestParam(value = "AccountID", required = false) String AccountID,
			@RequestParam(value = "Type", required = false) String Type,
			@RequestParam(value = "EventID", required = false) String EventID) {
		ModelAndView mav = new ModelAndView();
		SolrInputDocument updates = new SolrInputDocument();
		updates.addField("AccountID", AccountID);
		updates.addField("Type", Type);
		updates.addField("EventID", EventID);
		String query = "(EventID:" + EventID + ")";
		List<Updates> updated = new ArrayList<Updates>();
		updated = updateClient.fetchUpdates(query, 0, 350, request);
		for (Updates update : updated) {
			Adder.deleteUpdates(serverurlConstants.DELETE_UPDATES_URL,
					update.getID());
		}

		String result = "success";
		mav.addObject("result", result);
		return mav;
	}

	@RequestMapping(value = "/deletebyaccount")
	public ModelAndView deleteByAccount(
			HttpServletRequest request,
			@RequestParam(value = "AccountID", required = false) String AccountID) {
		ModelAndView mav = new ModelAndView();
		SolrInputDocument updates = new SolrInputDocument();
		updates.addField("AccountID", AccountID);
		// String query = "AccountID:"+updates.get("AccountID").toString();
		String query = "(AccountID:" + AccountID + ") OR (GeneratorAccountID:"
				+ AccountID + ")";
		List<Updates> updated = new ArrayList<Updates>();
		updated = updateClient.fetchUpdates(query, 0, 350, request);
		for (Updates update : updated) {
			Adder.deleteUpdates(serverurlConstants.DELETE_UPDATES_URL,
					update.getID());
		}

		// Adder.deleteUpdatesByAccountType(serverurlConstants.DELETE_UPDATES_BY_ACCOUNT_TYPE_URL
		// ,updates,query);
		String result = "success";
		mav.addObject("result", result);
		return mav;
	}

	@RequestMapping(value = "/findupdates2")
	public ModelAndView findUpdates(
			HttpServletRequest request,
			@RequestParam(value = "AccountID", required = false) String AccountID,
			@RequestParam(value = "LoginID", required = false) String LoginID,
			@RequestParam(value = "Age", required = false) String Age,
			@RequestParam(value = "CountryId", required = false, defaultValue = "0") String CountryId,
			@RequestParam(value = "IsAlcohlic", required = false, defaultValue = "0") int IsAlcohlic,
			@RequestParam(value = "Status", required = false, defaultValue = "0") String Status,
			@RequestParam(value = "PageSize", required = false) int PageSize) {
		ModelAndView mav = new ModelAndView();
		List<Updates> updated = new ArrayList<Updates>();

		String friendListResult2 = null;
		String folderListResult2 = null;
		String blockListResult2 = null;
		String canSendMessagesResult2 = null;

		String friendListResult = null;
		String folderListResult = null;
		String blockListResult = null;
		String canSendMessagesResult = null;

		try {
			friendListResult2 = mamCacheClient.mamCachefriendList(
					ServerurlConstants.MEMCACHE_URL,
					ServerurlConstants.MEMCACHE_PORT, LoginID, request);
			if (friendListResult2 != null)
				friendListResult = friendListResult2.replace(",", " , ");

			folderListResult2 = mamCacheClient.mamCachefolderList(
					ServerurlConstants.MEMCACHE_URL,
					ServerurlConstants.MEMCACHE_PORT, LoginID, request);
			if (folderListResult2 != null)
				folderListResult = folderListResult2.replace(",", " , ");

			blockListResult2 = mamCacheClient.mamCacheblockList(
					ServerurlConstants.MEMCACHE_URL,
					ServerurlConstants.MEMCACHE_PORT, LoginID, request);
			if (blockListResult2 != null)
				blockListResult = blockListResult2.replace(",", " , ");

			canSendMessagesResult2 = mamCacheClient.mamCachecanSendMessages(
					ServerurlConstants.MEMCACHE_URL,
					ServerurlConstants.MEMCACHE_PORT, LoginID, request);
			if (canSendMessagesResult2 != null)
				canSendMessagesResult = canSendMessagesResult2.replace(",",
						" , ");

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (friendListResult.equals("") || friendListResult == null)
			friendListResult = "1000000000";

		Object[] resultConnectionArr = null;
		String connectionNumFound = "0";
		String connectionQuery = null;
		String updateQuery = null;
		StringBuilder builder = new StringBuilder();
		int ConnectionSize = 0;
		List<PCommunity> connectionList = new ArrayList<PCommunity>();

		String privayAccountRestrictAge = privacyClient
				.privacyUpdateAccountRestrictAge(Age, CountryId, IsAlcohlic,
						LoginID);

		String privayGeneratorAccountRestrictAge = privacyClient
				.privacyUpdateGeneratorAccountRestrictAge(Age, CountryId,
						IsAlcohlic, LoginID);

		connectionQuery = "(MemberID:" + AccountID + ") AND (IsUpdatesHide:0)";

		resultConnectionArr = connectionClient
				.queryConnectionForUpdate(
						ServerurlConstants.ADD_CONNECTION_URL, connectionQuery,
						request);

		connectionNumFound = resultConnectionArr[1].toString();
		connectionList = (List<PCommunity>) resultConnectionArr[0];

		ConnectionSize = connectionList.size() - 1;
		for (int i = 0; i <= ConnectionSize; i++) {
			// updateQuery
			// ="((AccountID:"+connectionList.get(ConnectionSize).getContactID()+" OR AccountID:"+AccountID+" ) AND Status:"+Status+" )";

			builder.append(connectionList.get(i).getContactID());
			builder.append(",");
			// ContactID=connectionList.get(ConnectionSize).getContactID();
		}
		// String chkbuilder=builder.toString().replace("," , " , ");

		// Status hide for some time
		// updateQuery
		// ="((AccountID:"+builder+" OR AccountID:"+AccountID+" ) AND Status:"+Status+" )";

		updateQuery = "(AccountID:" + builder.toString().replace(",", " , ")
				+ " OR AccountID:" + AccountID + " )";

		updateQuery = "(" + updateQuery + privayAccountRestrictAge
				+ privayGeneratorAccountRestrictAge + ")";

		// updateQuery="("+updateQuery+")";

		updated = updateClient
				.fetchUpdatesPrivacy(updateQuery, 0, 350, request);
		mav.addObject(updated);
		return mav;
	}

	@RequestMapping(value = "/findupdates")
	public ModelAndView findUpdates2(
			HttpServletRequest request,
			@RequestParam(value = "AccountID", required = false) String AccountID,
			@RequestParam(value = "LoginID", required = false) String LoginID,
			@RequestParam(value = "Age", required = false) String Age,
			@RequestParam(value = "CountryId", required = false, defaultValue = "0") String CountryId,
			@RequestParam(value = "IsAlcohlic", required = false, defaultValue = "0") int IsAlcohlic,

			@RequestParam(value = "StartDate", required = false, defaultValue = "0") String StartDate,
			@RequestParam(value = "EndDate", required = false, defaultValue = "0") String EndDate,

			@RequestParam(value = "FaceToFace", required = false, defaultValue = "0") String FaceToFace,
			@RequestParam(value = "ConnectionEnum", required = false, defaultValue = "0") String ConnectionEnum,

			@RequestParam(value = "Status", required = false, defaultValue = "0") String Status,
			@RequestParam(value = "PageSize", required = false) int PageSize) {
		ModelAndView mav = new ModelAndView();
		List<Updates> updated = new ArrayList<Updates>();

		StartDate = dateClient.addDateToSolr(StartDate, request);

		EndDate = dateClient.addDateToSolr(EndDate, request);

		// UpdatedDate= dateClient.addDateToSolr(UpdatedDate);
		// UpdatedDate=Adder.escapeQueryChars(UpdatedDate);
		// String
		// timespan="AND TimeStamp [* TO "+UpdatedDate+"] AND  TimeStamp ["+StartDate+" TO *] AND TimeStamp[* TO "+EndDate+"]";

		// String
		// timespan="OR TimeStamp [* TO "+UpdatedDate+"] AND  (TimeStamp ["+StartDate+" TO *] OR TimeStamp:"+StartDate+") AND (TimeStamp[* TO "+EndDate+"] OR TimeStamp:"+EndDate+" )";

		String timespan = "AND (TimeStamp: [" + StartDate + " TO " + EndDate
				+ "])";
		String IsRelatedToUpdates = "AND (IsRelatedToUpdates:1)";
		String HideBy = "-HideBy:" + LoginID + "";

		// String
		// timespan="AND (TimeStamp: ["+StartDate+" TO "+EndDate+"] -TimeStamp:["+EndDate+" TO *] )";

		// timespan="";

		String friendListResult2 = null;
		String folderListResult2 = null;
		String blockListResult2 = null;
		String canSendMessagesResult2 = null;

		String connectionList2 = null;

		String friendListResult = null;
		String folderListResult = null;
		String blockListResult = null;
		String canSendMessagesResult = null;

		String connectionListUpdate = null;

		try {
			friendListResult2 = mamCacheClient.mamCachefriendList(
					ServerurlConstants.MEMCACHE_URL,
					ServerurlConstants.MEMCACHE_PORT, LoginID, request);
			if (friendListResult2 != null)
				friendListResult = friendListResult2.replace(",", " , ");

			folderListResult2 = mamCacheClient.mamCachefolderList(
					ServerurlConstants.MEMCACHE_URL,
					ServerurlConstants.MEMCACHE_PORT, LoginID, request);
			if (folderListResult2 != null)
				folderListResult = folderListResult2.replace(",", " , ");

			blockListResult2 = mamCacheClient.mamCacheblockList(
					ServerurlConstants.MEMCACHE_URL,
					ServerurlConstants.MEMCACHE_PORT, LoginID, request);
			if (blockListResult2 != null)
				blockListResult = blockListResult2.replace(",", " , ");

			canSendMessagesResult2 = mamCacheClient.mamCachecanSendMessages(
					ServerurlConstants.MEMCACHE_URL,
					ServerurlConstants.MEMCACHE_PORT, LoginID, request);
			if (canSendMessagesResult2 != null)
				canSendMessagesResult = canSendMessagesResult2.replace(",",
						" , ");

			connectionList2 = mamCacheClient.mamCacheConnectionList(
					ServerurlConstants.MEMCACHE_URL,
					ServerurlConstants.MEMCACHE_PORT, AccountID, request);
			if (connectionList2 != null)
				connectionListUpdate = connectionList2.replace(",", " , ");

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (friendListResult.equals("") || friendListResult == null)
			friendListResult = "1000000000";

		String updateQuery = null;

		/*
		 * Object[] resultConnectionArr = null; String connectionNumFound = "0";
		 * String connectionQuery=null; String updateQuery=null; StringBuilder
		 * builder=new StringBuilder(); int ConnectionSize=0; List<PCommunity>
		 * connectionList = new ArrayList<PCommunity>();
		 * 
		 * String
		 * privayAccountRestrictAge=privacyClient.privacyUpdateAccountRestrictAge2
		 * (Age, CountryId, IsAlcohlic , LoginID);
		 * 
		 * String privayGeneratorAccountRestrictAge=privacyClient.
		 * privacyUpdateGeneratorAccountRestrictAge2(Age, CountryId, IsAlcohlic
		 * , LoginID);
		 * 
		 * connectionQuery="(MemberID:"+AccountID+") AND (IsUpdatesHide:0)";
		 * 
		 * resultConnectionArr=connectionClient.queryConnectionForUpdate(
		 * ServerurlConstants.ADD_CONNECTION_URL,connectionQuery);
		 * 
		 * connectionNumFound = resultConnectionArr[1].toString();
		 * connectionList =(List<PCommunity>) resultConnectionArr[0] ;
		 * 
		 * 
		 * ConnectionSize=connectionList.size()-1; for(int
		 * i=0;i<=ConnectionSize;i++){ // updateQuery
		 * ="((AccountID:"+connectionList
		 * .get(ConnectionSize).getContactID()+" OR AccountID:"
		 * +AccountID+" ) AND Status:"+Status+" )";
		 * 
		 * builder.append(connectionList.get(i).getContactID());
		 * builder.append(","); //
		 * ContactID=connectionList.get(ConnectionSize).getContactID(); } String
		 * chkbuilder=builder.toString().replace("," , " , ");
		 */
		updateQuery = "(AccountID:" + AccountID + ")";

		// Status hide for some time for the pricay setting
		// updateQuery
		// ="((AccountID:"+builder+" OR AccountID:"+AccountID+" ) AND Status:"+Status+" )";

		if (!connectionListUpdate.equals(""))
			// updateQuery = "(AccountID:"+ builder.toString().replace(",",
			// " , ") + " OR AccountID:"+ AccountID + ") "+timespan+" ";

			// Before PeopleComented added

			// updateQuery = "(AccountID:"+ connectionListUpdate +
			// " OR AccountID:"+ AccountID +
			// ") "+timespan+" "+IsRelatedToUpdates+" "+HideBy+" ";

			updateQuery = "((AccountID:" + connectionListUpdate
					+ " OR AccountID:" + AccountID + ") OR (PeopleComented:"
					+ AccountID + " OR PeopleComented:" + connectionListUpdate
					+ " )) " + timespan + " " + IsRelatedToUpdates + " "
					+ HideBy + " ";

		else
			// updateQuery = "(AccountID:" + AccountID + ") "+timespan+" ";

			// Before PeopleComented added

			// updateQuery = "(AccountID:" + AccountID +
			// " ) "+timespan+" "+IsRelatedToUpdates+" "+HideBy+" ";

			updateQuery = "((AccountID:" + AccountID + " ) OR (PeopleComented:"
					+ AccountID + ")) " + timespan + " " + IsRelatedToUpdates
					+ " " + HideBy + " ";

		// new changes for face to face and ConnectionEnum

		if (FaceToFace.equals("1")) {

			updateQuery = "((AccountID:" + AccountID
					+ " AND GeneratorAccountID:" + LoginID + " AND -Type:"
					+ ConnectionEnum + ") OR (AccountID:" + LoginID
					+ " AND GeneratorAccountID:" + AccountID + " AND -Type:"
					+ ConnectionEnum + ") ) " + timespan + " "
					+ IsRelatedToUpdates + " " + HideBy + "";
		}

		String updateQuery2 = updatePrivacyClient.commonPrivacyQuery(
				updateQuery, friendListResult, folderListResult,
				blockListResult, LoginID, AccountID);

		// updateQuery="("+updateQuery+privayAccountRestrictAge+privayGeneratorAccountRestrictAge+")";

		// updateQuery=updateQuery+updateQuery2;

		updated = updateClient.fetchUpdatesPrivacy(updateQuery2, 0, PageSize,
				request);
		mav.addObject(updated);
		return mav;
	}

	@RequestMapping(value = "/findupdatescopy")
	public ModelAndView findUpdatescopy(
			HttpServletRequest request,
			@RequestParam(value = "AccountID", required = false) String AccountID,
			@RequestParam(value = "LoginID", required = false) String LoginID,
			@RequestParam(value = "Age", required = false) String Age,
			@RequestParam(value = "CountryId", required = false, defaultValue = "0") String CountryId,
			@RequestParam(value = "IsAlcohlic", required = false, defaultValue = "0") int IsAlcohlic,

			@RequestParam(value = "StartDate", required = false, defaultValue = "0") String StartDate,
			@RequestParam(value = "EndDate", required = false, defaultValue = "0") String EndDate,
			@RequestParam(value = "UpdatedDate", required = false, defaultValue = "0") String UpdatedDate,

			@RequestParam(value = "Status", required = false, defaultValue = "0") String Status,
			@RequestParam(value = "PageSize", required = false) int PageSize) {
		ModelAndView mav = new ModelAndView();
		List<Updates> updated = new ArrayList<Updates>();

		StartDate = dateClient.addDateToSolr(StartDate, request);

		EndDate = dateClient.addDateToSolr(EndDate, request);

		// UpdatedDate= dateClient.addDateToSolr(UpdatedDate);
		// UpdatedDate=Adder.escapeQueryChars(UpdatedDate);
		// String
		// timespan="AND TimeStamp [* TO "+UpdatedDate+"] AND  TimeStamp ["+StartDate+" TO *] AND TimeStamp[* TO "+EndDate+"]";

		// String
		// timespan="OR TimeStamp [* TO "+UpdatedDate+"] AND  (TimeStamp ["+StartDate+" TO *] OR TimeStamp:"+StartDate+") AND (TimeStamp[* TO "+EndDate+"] OR TimeStamp:"+EndDate+" )";

		// String timespan="AND (TimeStamp: ["+StartDate+" TO "+EndDate+"])";

		String timespan = "AND (TimeStamp: [" + StartDate + " TO " + EndDate
				+ "] -TimeStamp:[" + EndDate + " TO *] )";

		// timespan="";

		String friendListResult2 = null;
		String folderListResult2 = null;
		String blockListResult2 = null;
		String canSendMessagesResult2 = null;

		String friendListResult = null;
		String folderListResult = null;
		String blockListResult = null;
		String canSendMessagesResult = null;

		try {
			friendListResult2 = mamCacheClient.mamCachefriendList(
					ServerurlConstants.MEMCACHE_URL,
					ServerurlConstants.MEMCACHE_PORT, LoginID, request);
			if (friendListResult2 != null)
				friendListResult = friendListResult2.replace(",", " , ");

			folderListResult2 = mamCacheClient.mamCachefolderList(
					ServerurlConstants.MEMCACHE_URL,
					ServerurlConstants.MEMCACHE_PORT, LoginID, request);
			if (folderListResult2 != null)
				folderListResult = folderListResult2.replace(",", " , ");

			blockListResult2 = mamCacheClient.mamCacheblockList(
					ServerurlConstants.MEMCACHE_URL,
					ServerurlConstants.MEMCACHE_PORT, LoginID, request);
			if (blockListResult2 != null)
				blockListResult = blockListResult2.replace(",", " , ");

			canSendMessagesResult2 = mamCacheClient.mamCachecanSendMessages(
					ServerurlConstants.MEMCACHE_URL,
					ServerurlConstants.MEMCACHE_PORT, LoginID, request);
			if (canSendMessagesResult2 != null)
				canSendMessagesResult = canSendMessagesResult2.replace(",",
						" , ");

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (friendListResult.equals("") || friendListResult == null)
			friendListResult = "1000000000";

		Object[] resultConnectionArr = null;
		String connectionNumFound = "0";
		String connectionQuery = null;
		String updateQuery = null;
		StringBuilder builder = new StringBuilder();
		int ConnectionSize = 0;
		List<PCommunity> connectionList = new ArrayList<PCommunity>();

		String privayAccountRestrictAge = privacyClient
				.privacyUpdateAccountRestrictAge2(Age, CountryId, IsAlcohlic,
						LoginID);

		String privayGeneratorAccountRestrictAge = privacyClient
				.privacyUpdateGeneratorAccountRestrictAge2(Age, CountryId,
						IsAlcohlic, LoginID);

		connectionQuery = "(MemberID:" + AccountID + ") AND (IsUpdatesHide:0)";

		resultConnectionArr = connectionClient
				.queryConnectionForUpdate(
						ServerurlConstants.ADD_CONNECTION_URL, connectionQuery,
						request);

		connectionNumFound = resultConnectionArr[1].toString();
		connectionList = (List<PCommunity>) resultConnectionArr[0];

		ConnectionSize = connectionList.size() - 1;
		for (int i = 0; i <= ConnectionSize; i++) {
			// updateQuery
			// ="((AccountID:"+connectionList.get(ConnectionSize).getContactID()+" OR AccountID:"+AccountID+" ) AND Status:"+Status+" )";

			builder.append(connectionList.get(i).getContactID());
			builder.append(",");
			// ContactID=connectionList.get(ConnectionSize).getContactID();
		}
		String chkbuilder = builder.toString().replace(",", " , ");

		updateQuery = "(AccountID:" + AccountID + ")";

		// Status hide for some time for the pricay setting
		// updateQuery
		// ="((AccountID:"+builder+" OR AccountID:"+AccountID+" ) AND Status:"+Status+" )";

		if (!chkbuilder.equals(""))
			updateQuery = "(AccountID:"
					+ builder.toString().replace(",", " , ") + " OR AccountID:"
					+ AccountID + ") " + timespan + " ";
		else
			updateQuery = "(AccountID:" + AccountID + ") " + timespan + " ";

		String updateQuery2 = updatePrivacyClient.commonPrivacyQuery(
				updateQuery, friendListResult, folderListResult,
				blockListResult, LoginID, AccountID);

		// updateQuery="("+updateQuery+privayAccountRestrictAge+privayGeneratorAccountRestrictAge+")";

		// updateQuery=updateQuery+updateQuery2;

		updated = updateClient.fetchUpdatesPrivacy(updateQuery2, 0, PageSize,
				request);
		mav.addObject(updated);
		return mav;
	}

	@RequestMapping(value = "/findupdatesbysection")
	public ModelAndView findupdatesbysection(
			HttpServletRequest request,
			@RequestParam(value = "AccountID", required = false) String AccountID,
			@RequestParam(value = "LoginID", required = false) String LoginID,
			@RequestParam(value = "GeneratorAccountType", required = false) String GeneratorAccountType,
			@RequestParam(value = "GeneratorAccountID", required = false) String GeneratorAccountID,

			@RequestParam(value = "Age", required = false) String Age,
			@RequestParam(value = "CountryId", required = false, defaultValue = "0") String CountryId,
			@RequestParam(value = "IsAlcohlic", required = false, defaultValue = "0") int IsAlcohlic,

			@RequestParam(value = "StartDate", required = false, defaultValue = "0") String StartDate,
			@RequestParam(value = "EndDate", required = false, defaultValue = "0") String EndDate,
			
			@RequestParam(value = "PostedPlanEnum", required = false, defaultValue = "0") String PostedPlanEnum,
			
			@RequestParam(value = "CommetsSize", required = false, defaultValue = "0") String CommetsSize,

			@RequestParam(value = "PageSize", required = false) int PageSize) {

		ModelAndView mav = new ModelAndView();
		List<Updates> updated = new ArrayList<Updates>();

		StartDate = dateClient.addDateToSolr(StartDate, request);

		EndDate = dateClient.addDateToSolr(EndDate, request);

		String timespan = "AND (TimeStamp: [" + StartDate + " TO " + EndDate
				+ "])";
		String IsRelatedToUpdates = "AND (IsRelatedToUpdates:1)";
		String HideBy = "-HideBy:" + LoginID + "";

		String friendListResult2 = null;
		String folderListResult2 = null;
		String blockListResult2 = null;
		String canSendMessagesResult2 = null;

		String connectionList2 = null;

		String friendListResult = null;
		String folderListResult = null;
		String blockListResult = null;
		String canSendMessagesResult = null;

		String connectionListUpdate = null;

		try {
			friendListResult2 = mamCacheClient.mamCachefriendList(
					ServerurlConstants.MEMCACHE_URL,
					ServerurlConstants.MEMCACHE_PORT, LoginID, request);
			if (friendListResult2 != null)
				friendListResult = friendListResult2.replace(",", " , ");

			folderListResult2 = mamCacheClient.mamCachefolderList(
					ServerurlConstants.MEMCACHE_URL,
					ServerurlConstants.MEMCACHE_PORT, LoginID, request);
			if (folderListResult2 != null)
				folderListResult = folderListResult2.replace(",", " , ");

			blockListResult2 = mamCacheClient.mamCacheblockList(
					ServerurlConstants.MEMCACHE_URL,
					ServerurlConstants.MEMCACHE_PORT, LoginID, request);
			if (blockListResult2 != null)
				blockListResult = blockListResult2.replace(",", " , ");

			canSendMessagesResult2 = mamCacheClient.mamCachecanSendMessages(
					ServerurlConstants.MEMCACHE_URL,
					ServerurlConstants.MEMCACHE_PORT, LoginID, request);
			if (canSendMessagesResult2 != null)
				canSendMessagesResult = canSendMessagesResult2.replace(",",
						" , ");

			connectionList2 = mamCacheClient.mamCacheConnectionList(
					ServerurlConstants.MEMCACHE_URL,
					ServerurlConstants.MEMCACHE_PORT, LoginID, request);
			if (connectionList2 != null)
				connectionListUpdate = connectionList2.replace(",", " , ");

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (friendListResult.equals("") || friendListResult == null)
			friendListResult = "1000000000";

		String updateQuery = null;
		// On Satender request at Nov-04-2011 at 5:44 pm
		// queryText =
		// "(AccountID:"+AccountID+" AND GeneratorAccountType:"+GeneratorAccountType+" AND GeneratorAccountID:"+GeneratorAccountID+")";

		updateQuery = "((GeneratorAccountType:" + GeneratorAccountType
				+ " AND GeneratorAccountID:" + GeneratorAccountID + " AND -Type:"
					+ PostedPlanEnum + ")  ) "
				+ timespan + " " + IsRelatedToUpdates + " " + HideBy + " ";

		String updateQuery2 = updatePrivacyClient.commonPrivacyQuery(
				updateQuery, friendListResult, folderListResult,
				blockListResult, LoginID, AccountID);

		updated = updateClient.fetchUpdatesPrivacy(updateQuery2, 0, PageSize,
				request);
		mav.addObject(updated);
		return mav;
	}

}