package com.ytk.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.servlet.ModelAndView;

import com.ytk.models.Profile;
import com.ytk.models.ListContact.Contacts;
import com.ytk.utility.Adder;
import com.ytk.utility.ServerurlConstants;


@Controller
@RequestMapping("/searchProfiles/*")
public class SearchProfileController {

	
	@Autowired
	ServerurlConstants serverurlConstants;
	
	@RequestMapping(value = "/findProfiles")
	public ModelAndView findMembers(
			@RequestParam(value = "member_id", required = false) String memberId,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "zip", required = false) String zip,
			@RequestParam(value = "bday", required = false) String bday,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "gender", required = false) String gender,
			@RequestParam(value = "country", required = false) String country,
			@RequestParam(value = "relationStatus", required = false) String relationStatus,
			@RequestParam(value = "bodyType", required = false) String bodyType,
			@RequestParam(value = "height", required = false) String height,
			@RequestParam(value = "eatingHabit", required = false) String eatingHabit,
			@RequestParam(value = "language", required = false) String language,
			@RequestParam(value = "ethinic", required = false) String ethinic,
			@RequestParam(value = "hairColor", required = false) String hairColor,
			@RequestParam(value = "eyeColor", required = false) String eyeColor,
			@RequestParam(value = "sexuality", required = false) String sexuality,
			@RequestParam(value = "bestFeature", required = false) String bestFeature,
			@RequestParam(value = "education", required = false) String education,
			@RequestParam(value = "exercise", required = false) String exercise,
			@RequestParam(value = "smoke", required = false) String smoke,
			@RequestParam(value = "drink", required = false) String drink,
			@RequestParam(value = "bodyArt", required = false) String bodyArt,
			@RequestParam(value = "profession", required = false) String profession,
			@RequestParam(value = "annualIncome", required = false) String annualIncome,
			@RequestParam(value = "religiousAffiliation", required = false) String religiousAffiliation,
			@RequestParam(value = "haveChildred", required = false) String haveChildred,
			@RequestParam(value = "havePet", required = false) String havePet,
			@RequestParam(value = "activation", required = false) String activation,
			@RequestParam(value = "lastLogin", required = false) String lastLogin,
			@RequestParam(value = "active", required = false) String active,
			@RequestParam(value = "deleted", required = false) String deleted,
			@RequestParam(value = "photoAvaliable", required = false) String photoAvaliable,
			@RequestParam(value = "drug", required = false) String drug,
			@RequestParam(value = "wantChildren", required = false) String wantChildren,
			@RequestParam(value = "hasContactId", required = false) String hasContactId,
			@RequestParam(value = "contactId", required = false) String contactId,
			@RequestParam(value = "whoCanSee", required = false) String whoCanSee,
			@RequestParam(value = "address", required = false) String address,
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "jsoncallback", required = false) String search){
		
		ModelAndView mav = new ModelAndView();
		String queryText = "";
		if(zip != null)
		{
			queryText  = queryText + "( sZipCode:"+zip+" ) AND ";
		}
		if(bday != null)
		{
			if(bday.equals("") == false)
			queryText  = queryText + "(dBirthDay:"+bday+" ) AND ";
		}
		if(city != null)
		{
			queryText  = queryText + "(sCity:"+city+" ) AND ";
		}
	   if(state != null)
		{
			queryText  = queryText + "(sState:"+state+" ) AND ";
		}
		if(gender != null)
		{
			queryText  = queryText + "(iGender:"+gender+" ) AND ";
		}
		if(country != null)
		{
			queryText  = queryText + "(iCountryID_FK:"+country+" ) AND ";
		}
		if(relationStatus != null)
		{
			queryText  = queryText + "(iRelationshipStatusID_FK:"+relationStatus+" ) AND ";
		}
		if(bodyType != null)
		{
			queryText  = queryText + "(iBodyTypeID_FK:"+bodyType+"  ) AND ";
		}
		if(height != null)
		{
			queryText  = queryText + "(iHeight:"+height+" ) AND ";
		}
		if(eatingHabit != null)
		{
			queryText  = queryText + "(sEatingHabbits:"+eatingHabit+" AND )";
		}
		if(language != null)
		{
			queryText  = queryText + "(sLanguage:"+language+" AND )";
		}
		if(ethinic != null)
		{
			queryText  = queryText + "(sEthnicBackground:"+ethinic+" AND )";
		}
		if(hairColor != null)
		{
			queryText  =  queryText + "(iHairColorID_FK:"+hairColor+" ) AND";
		}
		if(eyeColor != null)
		{
			queryText  =  queryText + "(iEyeColorID_FK:"+eyeColor+" ) AND";
		}
		if(sexuality != null)
		{
			queryText  =  queryText + "(iSexualityID_FK:"+sexuality+" ) AND";
		}
		if(bestFeature != null)
		{
			queryText  =  queryText + "(iBestFeatureID_FK:"+bestFeature+" ) AND";
		}
		if(education != null)
		{
			queryText  =  queryText + "(iEducationID_FK:"+education+" ) AND";
		}
		if(exercise != null)
		{
			queryText  =  queryText + "(iExerciseID_FK:"+exercise+" ) AND";
		}
		if(smoke != null)
		{
			queryText  =  queryText + "(iSmokeID_FK:"+smoke+" ) AND";
		}
		if(drink != null)
		{
			queryText  =  queryText + "(iDrinkID_FK:"+drink+" ) AND";
		}
		if(drink != null)
		{
			queryText  =  queryText + "(iBodyArtID_FK:"+drink+" ) AND";
		}
		if(profession != null)
		{
			queryText  =  queryText + "(iProfessionID_FK:"+profession+" ) AND";
		}
		if(annualIncome != null)
		{
			queryText  =  queryText + "(iAnnualIncomeID_FK:"+annualIncome+" ) AND";
		}
		if(religiousAffiliation != null)
		{
			queryText  =  queryText + "(iReligiousAffiliationsID_FK:"+religiousAffiliation+" ) AND";
		}
		if(haveChildred != null)
		{
			queryText  =  queryText + "(iHaveChildrenID_FK:"+haveChildred+" ) AND";
		}
		if(havePet != null)
		{
			queryText  =  queryText + "(iHavePetsID_FK:"+havePet+" ) AND";
		}
		if(activation != null)
		{
			queryText  =  queryText + "(dActivationDate:"+activation+" ) AND";
		}
		if(lastLogin != null)
		{
			queryText  =  queryText + "(dLastLoginDate:"+lastLogin+" ) AND";
		}
		if(active != null)
		{
			queryText  =  queryText + "(bIsActive:"+active+" ) AND";
		}
		if(deleted != null)
		{
			queryText  =  queryText + "(bIsDeleted:"+deleted+" ) AND";
		}
		if(photoAvaliable != null)
		{
			queryText  =  queryText + "(bIsProfilePhotoAvailable:"+photoAvaliable+" ) AND";
		}
		if(drug != null)
		{
			queryText  =  queryText + "(iDrugUserID_FK:"+drug+" ) AND";
		}
		if(wantChildren != null)
		{
			queryText  =  queryText + "(iWantChildrenID_FK:"+wantChildren+" ) AND";
		}
		
		if(queryText.length() > 4)
			queryText = queryText.substring(0,queryText.length()-4);
		
		List <Profile> resultProfile = queryCheck(queryText,0,10);
		mav.addObject(resultProfile);
		return mav;
	}
	
	public  List <Profile> queryCheck(String query,int pageCount,int rows) {
		SolrQuery solrQuery = null;
		//QueryResponse response = null;
		List <Profile> fullContacts = new ArrayList<Profile>();
		try {
	    	String url = "http://localhost:8989/solr";
	    	SolrServer server =  Adder.getSolrServer(serverurlConstants.ADD_PROFILES_URL);
		    ModifiableSolrParams params = new ModifiableSolrParams();
		    params.set("q", query);
		    params.set("start", ""+pageCount);
		    params.set("version", "2.2");
		    params.set("wt", "json");
		    params.set("indent", "on");
		    params.set("rows", rows);

		    QueryResponse response = server.query(params, SolrRequest.METHOD.GET);
            for (SolrDocument doc : response.getResults())
            {
            	Profile pro = new Profile();
            	pro.setiMemberID_PK(doc.getFieldValue("iMemberID_PK").toString());
            	pro.setsEmailAddress(doc.getFieldValue("sEmailAddress").toString());
            	fullContacts.add(pro);
            }		    
		    return  fullContacts;  
	    } catch (SolrServerException e) {
	        throw new DataAccessResourceFailureException(e.getMessage(), e);
	    }
	}
	
	@RequestMapping(value = "/addProfile")
	public ModelAndView addMember(
			@RequestParam(value = "iMemberID_PK", required = false) String iMemberID_PK,
			@RequestParam(value = "sScreenName", required = false) String sScreenName,
			@RequestParam(value = "sEmailAddress", required = false) String sEmailAddress,
			@RequestParam(value = "sPassword", required = false) String sPassword,
			@RequestParam(value = "sZipCode", required = false) String sZipCode,
			@RequestParam(value = "dBirthDay", required = false) String dBirthDay,
			@RequestParam(value = "sCity", required = false) String sCity,
			@RequestParam(value = "sState", required = false) String sState,
			@RequestParam(value = "iGender", required = false) String iGender,
			@RequestParam(value = "sProfileImageName", required = false) String sProfileImageName,
			@RequestParam(value = "iCountryID_FK", required = false) String iCountryID_FK,
			@RequestParam(value = "iRelationshipStatusID_FK", required = false) String iRelationshipStatusID_FK,
			@RequestParam(value = "iBodyTypeID_FK", required = false) String iBodyTypeID_FK,
			@RequestParam(value = "iHeight", required = false) String iHeight,
			@RequestParam(value = "sEatingHabbits", required = false) String sEatingHabbits,
			@RequestParam(value = "sLanguage", required = false) String sLanguage,
			@RequestParam(value = "sEthnicBackground", required = false) String sEthnicBackground,
			@RequestParam(value = "iHairColorID_FK", required = false) String iHairColorID_FK,
			@RequestParam(value = "iEyeColorID_FK", required = false) String iEyeColorID_FK,
			@RequestParam(value = "iSexualityID_FK", required = false) String iSexualityID_FK,
			@RequestParam(value = "iBestFeatureID_FK", required = false) String iBestFeatureID_FK,
			@RequestParam(value = "iEducationID_FK", required = false) String iEducationID_FK,
			@RequestParam(value = "iExerciseID_FK", required = false) String iExerciseID_FK,
			@RequestParam(value = "iSmokeID_FK", required = false) String iSmokeID_FK,
			@RequestParam(value = "iDrinkID_FK", required = false) String iDrinkID_FK,
			@RequestParam(value = "iBodyArtID_FK", required = false) String iBodyArtID_FK,
			@RequestParam(value = "iProfessionID_FK", required = false) String iProfessionID_FK,
			@RequestParam(value = "iAnnualIncomeID_FK", required = false) String iAnnualIncomeID_FK,
			@RequestParam(value = "iReligiousAffiliationsID_FK", required = false) String iReligiousAffiliationsID_FK,
			@RequestParam(value = "iHaveChildrenID_FK", required = false) String iHaveChildrenID_FK,
			@RequestParam(value = "iHavePetsID_FK", required = false) String iHavePetsID_FK,
			@RequestParam(value = "dActivationDate", required = false) String dActivationDate,
			@RequestParam(value = "dLastLoginDate", required = false) String dLastLoginDate,
			@RequestParam(value = "bIsActive", required = false) String bIsActive,
			@RequestParam(value = "bIsDeleted", required = false) String bIsDeleted,
			@RequestParam(value = "bIsProfilePhotoAvailable", required = false) String bIsProfilePhotoAvailable,
			@RequestParam(value = "iDrugUserID_FK", required = false) String iDrugUserID_FK,
			@RequestParam(value = "iWantChildrenID_FK", required = false) String iWantChildrenID_FK,
			@RequestParam(value = "iContactEmailID", required = false) String iContactEmailID,
			@RequestParam(value = "sContactEmail", required = false) String sContactEmail,
			@RequestParam(value = "iWhoCanSeeInSearch", required = false) String iWhoCanSeeInSearch,
			@RequestParam(value = "sDisplayName", required = false) String sDisplayName,
			@RequestParam(value = "sAddress", required = false) String sAddress
			
			){
		
		ModelAndView mav = new ModelAndView();
		SolrInputDocument contacts = new SolrInputDocument();

		//Contacts contacts = new Contacts();
		contacts.addField("iMemberID_PK", iMemberID_PK);
		contacts.addField("sScreenName",sScreenName);
		contacts.addField("sEmailAddress",sEmailAddress);
		contacts.addField("sPassword",sPassword);
		contacts.addField("sZipCode",sZipCode);
		contacts.addField("dBirthDay",dBirthDay);
		contacts.addField("sCity",sCity);
		contacts.addField("sState",sState);
		contacts.addField("iGender",iGender);
		contacts.addField("sProfileImageName",sProfileImageName);
		contacts.addField("iCountryID_FK",iCountryID_FK);
		contacts.addField("iRelationshipStatusID_FK",iRelationshipStatusID_FK);
		contacts.addField("iBodyTypeID_FK",iBodyTypeID_FK);
		contacts.addField("iHeight",iHeight);
		contacts.addField("sEatingHabbits",sEatingHabbits);
		contacts.addField("sLanguage",sLanguage);
		contacts.addField("sEthnicBackground",sEthnicBackground);
		contacts.addField("iHairColorID_FK",iHairColorID_FK);
		contacts.addField("iEyeColorID_FK",iEyeColorID_FK);		
		contacts.addField("iSexualityID_FK",iSexualityID_FK);
		contacts.addField("iBestFeatureID_FK",iBestFeatureID_FK);
		contacts.addField("iEducationID_FK",iEducationID_FK);
		contacts.addField("iExerciseID_FK",iExerciseID_FK);
		contacts.addField("iSmokeID_FK",iSmokeID_FK);
		contacts.addField("iDrinkID_FK",iDrinkID_FK);
		contacts.addField("iBodyArtID_FK",iBodyArtID_FK);
		contacts.addField("iProfessionID_FK",iProfessionID_FK);
		contacts.addField("iAnnualIncomeID_FK",iAnnualIncomeID_FK);
		contacts.addField("iReligiousAffiliationsID_FK",iReligiousAffiliationsID_FK);
		contacts.addField("iHaveChildrenID_FK",iHaveChildrenID_FK);
		contacts.addField("iHavePetsID_FK",iHavePetsID_FK);
		contacts.addField("dActivationDate",dActivationDate);
		contacts.addField("dLastLoginDate",dLastLoginDate);
		contacts.addField("bIsActive",bIsActive);		
		contacts.addField("bIsDeleted",bIsDeleted);
		contacts.addField("bIsProfilePhotoAvailable",bIsProfilePhotoAvailable);
		contacts.addField("iDrugUserID_FK",iDrugUserID_FK);
		contacts.addField("iWantChildrenID_FK",iWantChildrenID_FK);
		contacts.addField("iContactEmailID",iContactEmailID);
		contacts.addField("sContactEmail",sContactEmail);
		contacts.addField("iWhoCanSeeInSearch",iWhoCanSeeInSearch);
		contacts.addField("sDisplayName",sDisplayName);
		contacts.addField("sAddress",sAddress);
		String result = "success";
		try
		{
			Adder.addProfiles(serverurlConstants.ADD_PROFILES_URL ,contacts);
		}
		catch(Exception exp)
		{
			result = "fault";
		}
		
		mav.addObject("result",result);
		return mav;
	}
	

}
