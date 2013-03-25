package com.ytk.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ytk.models.CollectionList;
import com.ytk.models.Place;
import com.ytk.models.PlaceReview;
import com.ytk.utility.Adder;
import com.ytk.utility.ServerurlConstants;
import com.ytk.client.*;


@Controller
@RequestMapping("/placereview/*")
public class PlaceReviewController {
	
	@Autowired
	ServerurlConstants serverurlConstants;
	
	@Autowired
	PlaceReviewControllerClient PlaceReviewControllerClient;
	
	

	@RequestMapping(value = "/addPlaceReview")
	public ModelAndView addPlaceReview(@RequestParam(value = "ID", required = false) String ID,
			@RequestParam(value = "PlaceID", required = false) String PlaceID,
			@RequestParam(value = "AccountID", required = false) String AccountID,
			@RequestParam(value = "AccountType", required = false) String AccountType,
			@RequestParam(value = "Rating", required = false) String Rating,
			@RequestParam(value = "TotalComments", required = false) String TotalComments,
			@RequestParam(value = "Description", required = false) String Description,
			@RequestParam(value = "PriceRange", required = false) String PriceRange,
			@RequestParam(value = "AcceptsCreditCards", required = false) String AcceptsCreditCards,
			@RequestParam(value = "WheelChairAccessible", required = false) String WheelChairAccessible,
			@RequestParam(value = "GoodForKids", required = false) String GoodForKids,
			@RequestParam(value = "GoodForGroups", required = false) String GoodForGroups,
			@RequestParam(value = "WaiterService", required = false) String WaiterService,
			@RequestParam(value = "Attire", required = false) String Attire,
			@RequestParam(value = "OutdoorSeating", required = false) String OutdoorSeating,
			@RequestParam(value = "Alcohol", required = false) String Alcohol,
			@RequestParam(value = "TakeOut", required = false) String TakeOut,
			@RequestParam(value = "TakesReservation", required = false) String TakesReservation,
			@RequestParam(value = "ByAppointmentOnly", required = false) String ByAppointmentOnly,
			@RequestParam(value = "Music", required = false) String Music,
			@RequestParam(value = "BestNights", required = false) String BestNights,
			@RequestParam(value = "Smoking", required = false) String Smoking,
			@RequestParam(value = "CoatCheck", required = false) String CoatCheck,
			@RequestParam(value = "HappyHour", required = false) String HappyHour,
			@RequestParam(value = "WiFi", required = false) String WiFi,
			@RequestParam(value = "IsParkingStreet", required = false) String IsParkingStreet,
			@RequestParam(value = "IsParkingGarage", required = false) String IsParkingGarage,
			@RequestParam(value = "IsParkingValet", required = false) String IsParkingValet,
			@RequestParam(value = "IsParkingPrivateLot", required = false) String IsParkingPrivateLot,
			@RequestParam(value = "IsParkingValidated", required = false) String IsParkingValidated,
			@RequestParam(value = "IsMealBreakfast", required = false) String IsMealBreakfast,
			@RequestParam(value = "IsMealBrunch", required = false) String IsMealBrunch,
			@RequestParam(value = "IsMealLunch", required = false) String IsMealLunch,
			@RequestParam(value = "IsMealDinner", required = false) String IsMealDinner,
			@RequestParam(value = "IsMealLateNight", required = false) String IsMealLateNight,
			@RequestParam(value = "IsMealDessert", required = false) String IsMealDessert,
			@RequestParam(value = "IsGoodForMealBreakfast", required = false) String IsGoodForMealBreakfast,
			@RequestParam(value = "IsGoodForMealBrunch", required = false) String IsGoodForMealBrunch,
			@RequestParam(value = "IsGoodForMealLunch", required = false) String IsGoodForMealLunch,
			@RequestParam(value = "IsGoodForMealDinner", required = false) String IsGoodForMealDinner,
			@RequestParam(value = "IsGoodForMealLateNight", required = false) String IsGoodForMealLateNight,
			@RequestParam(value = "IsGoodForMealDessert", required = false) String IsGoodForMealDessert,
			@RequestParam(value = "CreatedDate", required = false) String CreatedDate,
			@RequestParam(value = "IsUpdated", required = false) String IsUpdated,
			@RequestParam(value = "Alcoholic", required = false,  defaultValue = "0") int Alcoholic,
			@RequestParam(value = "RestrictAge", required = false,  defaultValue = "0") int Age,
			@RequestParam(value = "RestrictCountry", required = false) String RestrictCountry,
			@RequestParam(value = "RestrictPost", required = false,  defaultValue = "0") int RestrictPost,
			@RequestParam(value = "Status", required = false) String Status){
		
		ModelAndView mav = new ModelAndView();
		SolrInputDocument placereview = new SolrInputDocument();
		
		placereview.addField("ID", ID);
		placereview.addField("PlaceID", PlaceID);
		placereview.addField("AccountID", AccountID);
		placereview.addField("AccountType", AccountType);
		placereview.addField("Rating", Rating);
		placereview.addField("TotalComments", TotalComments);
		placereview.addField("Description", Description);
		placereview.addField("PriceRange", PriceRange);
		placereview.addField("AcceptsCreditCards", AcceptsCreditCards);
		placereview.addField("WheelChairAccessible", WheelChairAccessible);
		placereview.addField("GoodForKids", GoodForKids);
		placereview.addField("GoodForGroups", GoodForGroups);
		placereview.addField("WaiterService", WaiterService);
		placereview.addField("Attire", Attire);
		placereview.addField("OutdoorSeating", OutdoorSeating);
		placereview.addField("Alcohol", Alcohol);
		placereview.addField("TakeOut", TakeOut);
		placereview.addField("TakesReservation", TakesReservation);
		placereview.addField("ByAppointmentOnly", ByAppointmentOnly);
		placereview.addField("Music", Music);
		placereview.addField("BestNights", BestNights);
		placereview.addField("Smoking", Smoking);
		placereview.addField("CoatCheck", CoatCheck);
		placereview.addField("HappyHour", HappyHour);
		placereview.addField("WiFi", WiFi);
		placereview.addField("IsParkingStreet", IsParkingStreet);
		placereview.addField("IsParkingGarage", IsParkingGarage);
		placereview.addField("IsParkingValet", IsParkingValet);
		placereview.addField("IsParkingPrivateLot", IsParkingPrivateLot);
		placereview.addField("IsParkingValidated", IsParkingValidated);
		placereview.addField("IsMealBreakfast", IsMealBreakfast);
		placereview.addField("IsMealBrunch", IsMealBrunch);
		placereview.addField("IsMealLunch", IsMealLunch);
		placereview.addField("IsMealDinner", IsMealDinner);
		placereview.addField("IsMealLateNight", IsMealLateNight);
		placereview.addField("IsMealDessert", IsMealDessert);
		placereview.addField("IsGoodForMealBreakfast", IsGoodForMealBreakfast);
		placereview.addField("IsGoodForMealBrunch", IsGoodForMealBrunch);
		placereview.addField("IsGoodForMealLunch", IsGoodForMealLunch);
		placereview.addField("IsGoodForMealDinner", IsGoodForMealDinner);
		placereview.addField("IsGoodForMealLateNight", IsGoodForMealLateNight);
		placereview.addField("IsGoodForMealDessert", IsGoodForMealDessert);
		placereview.addField("CreatedDate", CreatedDate);
		placereview.addField("IsUpdated", IsUpdated);
		placereview.addField("Alcoholic", Alcoholic);
		placereview.addField("RestrictAge", Age);
		placereview.addField("RestrictCountry", RestrictCountry);
		placereview.addField("Status", Status);
		placereview.addField("RestrictPost", RestrictPost);
		
		Adder.addPlacesReview(serverurlConstants.ADD_PLACES_REVIEW_URL, placereview);
		String result = "success";
		mav.addObject("result",result);
		
		return mav;
	}


	@RequestMapping(value = "/deleteplacereview")
	public ModelAndView deletePlaceReview(
			@RequestParam(value = "ID", required = false) String ID)
	{
		ModelAndView mav = new ModelAndView();
		SolrInputDocument connection = new SolrInputDocument();
		connection.addField("ID", ID);	
		Adder.deletePlaceReview(serverurlConstants.DELETE_PLACES_REVIEW_URL ,connection);
		String result = "success";
		mav.addObject("result",result);
		return mav;
	}
	
	
	
	@RequestMapping(value = "/deleteallplacesreview")
	public ModelAndView deleteAllPlacesReview()
	{
		
		ModelAndView mav = new ModelAndView();
		Adder.deleteAllInstances(serverurlConstants.ADD_PLACES_REVIEW_URL);
		String result = "success";
		mav.addObject("result",result);
		return mav;
	}
	
	
	@RequestMapping(value = "/findplacesreview")
	public ModelAndView findPlacesReview(HttpServletRequest request,
			@RequestParam(value = "account_id", required = false) String accountId,			
			@RequestParam(value = "page", required = false) int page){
		ModelAndView mav = new ModelAndView();
		List<PlaceReview> contactsQuery = null;
		if(accountId == null || accountId.equals("")){
			String ErrorMessage = "Account-Id is required";
			mav.addObject("Error", ErrorMessage);
			return null;	
		}else {
			String queryText = "";
			Object[] resultArr = null;
			int pageCount = page * 10;
			String numFound  = "0";
			queryText="(AccountID:"+accountId+"*)";
			resultArr = PlaceReviewControllerClient.queryCheck(queryText,pageCount, request);
			numFound = resultArr[1].toString();
			contactsQuery =(List<PlaceReview>) resultArr[0] ;
		
			mav.addObject("Placereview",contactsQuery);
			mav.addObject("Records",numFound);
		}		
		return null;
	}
	
	
	
	@RequestMapping(value = "/getbycityid")
	public ModelAndView getbyCityid(
			HttpServletRequest request,
			@RequestParam(value = "CityID", required = false ,  defaultValue = "0") String CityID,
			@RequestParam(value = "Status", required = false ,  defaultValue = "0") String Status,
			@RequestParam(value = "PageNo", required = false ,  defaultValue = "0") int PageNo,
			@RequestParam(value = "CategoryID", required = false) String CategoryID,
			@RequestParam(value = "RestrictAge", required = false) String RestrictAge,
			@RequestParam(value = "RestrictCountry", required = false) String RestrictCountry,
			@RequestParam(value = "IsAlcohlic", required = false ,  defaultValue = "0") int Alcohol,
			@RequestParam(value = "PageSize", required = false ,  defaultValue = "0") int PageSize,
			@RequestParam(value = "SortBy", required = false ,  defaultValue = "SortBy") String SortBy){
		ModelAndView mav = new ModelAndView();
		List contactsQuery = null;
		if(SortBy == null || SortBy.equals("")){
			SortBy="SortBy";
		}
		
		
			if(SortBy.equals("Date")){
				SortBy="CreatedDate";
			}else if(SortBy.equals("Rating")){
				SortBy="Rating";
			}else {
				SortBy="Description";
			}
		
		
		String result="success";
		if(CityID == null || CityID.equals("")){
			String ErrorMessage = "City-Id is required";
			mav.addObject("Error", ErrorMessage);
			return null;	
		}else {
			String queryText = "";
			Object[] resultArr = null;
		//	int pageCount = PageSize * 10;
			int numFound  = 0;
			if(CategoryID=="" || CategoryID==null){
			queryText="((CityID:"+CityID+") AND (Status:"+Status+") AND (RestrictAge:[* TO "+RestrictAge+" ]) AND Alcoholic:"+Alcohol+" AND -RestrictCountry:"+RestrictCountry+" )";
			System.out.println("queryText"+queryText);
			}else{
				queryText="(CityID:"+CityID+" AND Category1ID:"+CategoryID+" AND Status:"+Status+" AND (RestrictAge:[* TO "+RestrictAge+" ]) AND Alcoholic:"+Alcohol+" AND -RestrictCountry:"+RestrictCountry+" ) OR (CityID:"+CityID+" AND Category2ID:"+CategoryID+" AND Status:"+Status+" AND (RestrictAge:[* TO "+RestrictAge+" ]) AND Alcoholic:"+Alcohol+" AND -RestrictCountry:"+RestrictCountry+" ) OR (CityID:"+CityID+" AND Category3ID:"+CategoryID+" AND Status:"+Status+" AND (RestrictAge:[* TO "+RestrictAge+" ]) AND Alcoholic:"+Alcohol+" AND -RestrictCountry:"+RestrictCountry+" ) OR (CityID:"+CityID+" AND SubCategory1ID:"+CategoryID+" AND Status:"+Status+" AND (RestrictAge:[* TO "+RestrictAge+" ]) AND Alcoholic:"+Alcohol+" AND -RestrictCountry:"+RestrictCountry+" ) OR (CityID:"+CityID+" AND SubCategory2ID:"+CategoryID+" AND Status:"+Status+" AND (RestrictAge:[* TO "+RestrictAge+" ]) AND Alcoholic:"+Alcohol+" AND -RestrictCountry:"+RestrictCountry+" ) OR (CityID:"+CityID+" AND SubCategory3ID:"+CategoryID+" AND Status:"+Status+" AND (RestrictAge:[* TO "+RestrictAge+" ]) AND Alcoholic:"+Alcohol+" AND -RestrictCountry:"+RestrictCountry+" ) ";
				System.out.println("queryText"+queryText);
			
			}
			System.out.println("queryText------>"+queryText);
			resultArr = PlaceReviewControllerClient.queryCheck1(queryText,PageNo ,PageSize , SortBy, request);
			numFound = Integer.parseInt(resultArr[1].toString());
					
			mav.addObject("Collection",resultArr[0]);
			mav.addObject("TotalRecords",numFound);
			mav.addObject("result",result);
			mav.addObject("IsConnected","true");
		}		
		return mav;
	}
	
	
	@RequestMapping(value = "/addPlaceID")
	public ModelAndView addPlaceReview(@RequestParam(value = "PlaceID", required = false) String PlaceID,			
			@RequestParam(value = "Alcoholic", required = false) int Alcoholic,
			@RequestParam(value = "RestrictAge", required = false) String RestrictAge,					
			@RequestParam(value = "RestrictCountry", required = false) String RestrictCountry){
		
		ModelAndView mav = new ModelAndView();
		SolrInputDocument placereview = new SolrInputDocument();
		
		placereview.addField("PlaceID", PlaceID);
		placereview.addField("Alcoholic", Alcoholic);
		placereview.addField("RestrictAge", RestrictAge);
		placereview.addField("RestrictCountry", RestrictCountry);
		Adder.addPlacesReviewPlaceID(serverurlConstants.ADD_PLACES_REVIEW_URL, placereview);
		String result = "success";
		mav.addObject("result",result);
		
		return mav;
		
	}
	
	

}
