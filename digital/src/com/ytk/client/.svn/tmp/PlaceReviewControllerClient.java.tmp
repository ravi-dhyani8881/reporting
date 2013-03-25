package com.ytk.client;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ytk.models.Place;
import com.ytk.models.PlaceReview;
import com.ytk.utility.Adder;
import com.ytk.utility.LogDetails;
import com.ytk.utility.ServerurlConstants;


@Component("PlaceReviewControllerClient")
public class PlaceReviewControllerClient {

	private static final Logger logger = LoggerFactory.getLogger(PlaceReviewControllerClient.class);
	
	@Autowired
	ServerurlConstants serverurlConstants;
	
	
	@Autowired
	LogDetails logDetails;
	
	public  Object[] queryCheck1(String query,int pageCount ,int rows , String sortBy , HttpServletRequest request) {
		SolrQuery solrQuery = null;
		//QueryResponse response = null;
	    try {
	    //	String url = "http://localhost:8992/solr";
	    	
	    	
	    	SolrServer server =  Adder.getSolrServer(serverurlConstants.ADD_PLACES_REVIEW_URL);
		    ModifiableSolrParams params = new ModifiableSolrParams();
		    params.set("q", query);
		    params.set("start", ""+pageCount);
		    params.set("version", "2.2");
		    params.set("wt", "json");
		    params.set("sort", ""+sortBy+" desc");
		    params.set("indent", "on");
		    params.set("rows", ""+rows);
		    Object[] resultArr = new Object[2];
		    QueryResponse response = server.query(params, SolrRequest.METHOD.POST);
		    String   numFound = response.getResults().getNumFound()+"";
	        resultArr[0] = response.getResults();
	    
	        resultArr[1] = numFound;
	        return  resultArr;  
	    } catch (SolrServerException e) {
	    	logDetails.getException(e , logger , request);
	        throw new DataAccessResourceFailureException(e.getMessage(), e);
	    }
	   // return response;
	} 	
	
	public  Object[] queryCheck(String query,int pageCount, HttpServletRequest request) {
		SolrQuery solrQuery = null;
		//QueryResponse response = null;
	    try {
	    	
	    	SolrServer server =  Adder.getSolrServer(serverurlConstants.ADD_PLACES_REVIEW_URL);
		    ModifiableSolrParams params = new ModifiableSolrParams();
		    params.set("q", query);
		    params.set("start", ""+pageCount);
		    params.set("version", "2.2");
		    params.set("wt", "json");
		    params.set("indent", "on");
		    params.set("rows", "10");
		    Object[] resultArr = new Object[2];
		    QueryResponse response = server.query(params, SolrRequest.METHOD.POST);
		    String   numFound = response.getResults().getNumFound()+"";
		    List <PlaceReview> fullPlaces = new ArrayList<PlaceReview>();
	            for (SolrDocument doc : response.getResults())
	            {
	            	PlaceReview placeReview = new PlaceReview();
	            	placeReview.setID(doc.getFieldValue("ID").toString());
	            	placeReview.setPlaceID(doc.getFieldValue("PlaceID").toString());
	            	placeReview.setID(doc.getFieldValue("CityID").toString());
	            	placeReview.setAccountID(doc.getFieldValue("AccountID").toString());
	            	placeReview.setAccountType(doc.getFieldValue("AccountType").toString());
	            	placeReview.setRating(doc.getFieldValue("Rating").toString());
	            	placeReview.setTotalComments(doc.getFieldValue("TotalComments").toString());
	            	placeReview.setDescription(doc.getFieldValue("Description").toString());
	            	placeReview.setPriceRange(doc.getFieldValue("PriceRange").toString());
	            	placeReview.setAcceptsCreditCards(doc.getFieldValue("AcceptsCreditCards").toString());
	            	placeReview.setWheelChairAccessible(doc.getFieldValue("WheelChairAccessible").toString());
	            	placeReview.setGoodForKids(doc.getFieldValue("GoodForKids").toString());
	            	placeReview.setGoodForGroups(doc.getFieldValue("GoodForGroups").toString());
	            	placeReview.setWaiterService(doc.getFieldValue("WaiterService").toString());
	            	placeReview.setAttire(doc.getFieldValue("Attire").toString());
	            	placeReview.setOutdoorSeating(doc.getFieldValue("OutdoorSeating").toString());
	            	placeReview.setAlcohol(doc.getFieldValue("Alcohol").toString());
	            	placeReview.setTakeOut(doc.getFieldValue("TakeOut").toString());
	            	placeReview.setTakesReservation(doc.getFieldValue("TakesReservation").toString());
	            	placeReview.setByAppointmentOnly(doc.getFieldValue("ByAppointmentOnly").toString());
	            	placeReview.setMusic(doc.getFieldValue("Music").toString());
	            	placeReview.setBestNights(doc.getFieldValue("BestNights").toString());
	            	placeReview.setSmoking(doc.getFieldValue("Smoking").toString());
	            	placeReview.setCoatCheck(doc.getFieldValue("CoatCheck").toString());
	            	placeReview.setHappyHour(doc.getFieldValue("HappyHour").toString());
	            	placeReview.setWiFi(doc.getFieldValue("WiFi").toString());
	            	placeReview.setIsParkingStreet(doc.getFieldValue("IsParkingStreet").toString());
	            	placeReview.setIsParkingGarage(doc.getFieldValue("IsParkingGarage").toString());
	            	placeReview.setIsParkingValet(doc.getFieldValue("IsParkingValet").toString());
	            	placeReview.setIsParkingPrivateLot(doc.getFieldValue("IsParkingPrivateLot").toString());
	            	placeReview.setIsParkingValidated(doc.getFieldValue("IsParkingValidated").toString());
	            	placeReview.setIsMealBreakfast(doc.getFieldValue("IsMealBreakfast").toString());
	            	placeReview.setIsMealBrunch(doc.getFieldValue("IsMealBrunch").toString());
	            	placeReview.setIsMealLunch(doc.getFieldValue("IsMealLunch").toString());
	            	placeReview.setIsMealDinner(doc.getFieldValue("IsMealDinner").toString());
	            	placeReview.setIsMealLateNight(doc.getFieldValue("IsMealLateNight").toString());
	            	placeReview.setIsMealDessert(doc.getFieldValue("IsMealDessert").toString());
	            	placeReview.setIsGoodForMealBreakfast(doc.getFieldValue("IsGoodForMealBreakfast").toString());
	            	placeReview.setIsGoodForMealBrunch(doc.getFieldValue("IsGoodForMealBrunch").toString());
	            	placeReview.setIsGoodForMealLunch(doc.getFieldValue("IsGoodForMealLunch").toString());
	            	placeReview.setIsGoodForMealDinner(doc.getFieldValue("IsGoodForMealDinner").toString());
	            	placeReview.setIsGoodForMealLunch(doc.getFieldValue("IsGoodForMealLateNight").toString());	            	
	            	placeReview.setIsGoodForMealDessert(doc.getFieldValue("IsGoodForMealDessert").toString());
	            	placeReview.setCreatedDate(doc.getFieldValue("CreatedDate").toString());
	            	placeReview.setIsUpdated(doc.getFieldValue("IsUpdated").toString());
	            	placeReview.setStatus(doc.getFieldValue("Status").toString());
	            	
	            	fullPlaces.add(placeReview);
	            }
	            resultArr[0] = fullPlaces;
	            resultArr[1] = numFound;
	         return  resultArr;  
	    } catch (SolrServerException e) {
	    	logDetails.getException(e , logger , request);
	        throw new DataAccessResourceFailureException(e.getMessage(), e);
	    }
	   // return response;
	} 	
	
	
	
}
