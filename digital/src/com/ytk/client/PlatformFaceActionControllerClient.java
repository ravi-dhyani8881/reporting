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

import com.ytk.models.Custom;
import com.ytk.models.DiscussionQuestion;
import com.ytk.models.Faces;
import com.ytk.models.HeaderFace;
import com.ytk.utility.Adder;
import com.ytk.utility.LogDetails;
import com.ytk.utility.ServerurlConstants;



@Component("platformFaceActionControllerClient")
public class PlatformFaceActionControllerClient {

	private static final Logger logger = LoggerFactory.getLogger(PlatformFaceActionControllerClient.class);
	
	@Autowired
	ServerurlConstants serverurlConstants;	
	
	
	@Autowired
	LogDetails logDetails;
	
	public  Object[] queryCheck(String query,int pageCount,int size, HttpServletRequest request) 
	{
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    try 
	    {
	    	String url = serverurlConstants.ADD_FACES_URL;
	    	SolrServer server =  Adder.getSolrServer(url);
		    ModifiableSolrParams params = new ModifiableSolrParams();
		    params.set("q", query);
		    params.set("start", ""+pageCount);		    
		    params.set("version", "2.2");
		//    params.set("sort", "Name desc");
		    params.set("wt", "json");
		    params.set("indent", "on");
		    params.set("rows", ""+size);
		    QueryResponse response = server.query(params, SolrRequest.METHOD.POST);
		    
		     String BirthDay = null;
			 String AnniversaryDay = null;
			 String CreatedDate = null;
			 String ActivationDate = null;
			 String UpdatedDate = null;
			 String DeletionDate = null;
			 String LastLoginDate = null;
		    
		    
		    
		    Date birthDay=null;
		    Date anniversaryDay=null;
		    Date createdDate=null;
		    Date activationDate=null;
		    Date updatedDate=null;
		    Date deletionDate=null;
		    Date lastLoginDate=null;
		    
		    List <Faces> facesList = new ArrayList<Faces>();
		    for (SolrDocument faceDoc : response.getResults())
            {
		    	
		    	 dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		    	birthDay=(Date)faceDoc.getFieldValue("BirthDay");
		    	anniversaryDay=(Date)faceDoc.getFieldValue("AnniversaryDay");
		    	createdDate=(Date)faceDoc.getFieldValue("CreatedDate");
		    	activationDate=(Date)faceDoc.getFieldValue("ActivationDate");
		    	updatedDate=(Date)faceDoc.getFieldValue("UpdatedDate");
		    	deletionDate=(Date)faceDoc.getFieldValue("DeletionDate");
		    	lastLoginDate=(Date)faceDoc.getFieldValue("LastLoginDate");		    	
		    	
               Faces faces=new Faces();
               faces.setID(faceDoc.getFieldValue("ID").toString());
               faces.setDBID(faceDoc.getFieldValue("DBID").toString());
               faces.setCategoryID(faceDoc.getFieldValue("CategoryID").toString());
               faces.setCategoryName(faceDoc.getFieldValue("CategoryName").toString());
               faces.setScreenName(faceDoc.getFieldValue("ScreenName").toString());
               faces.setScreenNameStatus(faceDoc.getFieldValue("ScreenNameStatus").toString());               
               faces.setFirstName(faceDoc.getFieldValue("FirstName").toString());
               faces.setMiddleName(faceDoc.getFieldValue("MiddleName").toString());
               faces.setLastName(faceDoc.getFieldValue("LastName").toString());
               faces.setName(faceDoc.getFieldValue("Name").toString());
               faces.setAlternateName(faceDoc.getFieldValue("AlternateName").toString());
               faces.setDisplayAlternateName(faceDoc.getFieldValue("DisplayAlternateName").toString());
               faces.setEmail(faceDoc.getFieldValue("Email").toString());
               faces.setZipCode(faceDoc.getFieldValue("ZipCode").toString());
               faces.setBirthDay(dateFormat.format(birthDay));
               faces.setBirthdayOptions(faceDoc.getFieldValue("BirthdayOptions").toString());
               faces.setAnniversaryDay(dateFormat.format(anniversaryDay));
               faces.setGender(faceDoc.getFieldValue("Gender").toString());
               faces.setCity(faceDoc.getFieldValue("City").toString());
               faces.setState(faceDoc.getFieldValue("State").toString());               
               faces.setCountry(faceDoc.getFieldValue("Country").toString());
               faces.setCityID(faceDoc.getFieldValue("CityID").toString());
               faces.setStateID(faceDoc.getFieldValue("StateID").toString());
               faces.setCountryID(faceDoc.getFieldValue("CountryID").toString());
               faces.setCityCurrent(faceDoc.getFieldValue("CityCurrent").toString());               
               faces.setStateCurrent(faceDoc.getFieldValue("StateCurrent").toString());              
               faces.setCountryCurrent(faceDoc.getFieldValue("CountryCurrent").toString());
               faces.setCityIDCurrent(faceDoc.getFieldValue("CityIDCurrent").toString());
               faces.setStateIDCurrent(faceDoc.getFieldValue("StateIDCurrent").toString());
               faces.setCountryIDCurrent(faceDoc.getFieldValue("CountryIDCurrent").toString());
               faces.setCityHome(faceDoc.getFieldValue("CityHome").toString());
               faces.setStateHome(faceDoc.getFieldValue("StateHome").toString());
               faces.setCountryHome(faceDoc.getFieldValue("CountryHome").toString());
               faces.setCityIDHome(faceDoc.getFieldValue("CityIDHome").toString());
               faces.setStateIDHome(faceDoc.getFieldValue("StateIDHome").toString());
               faces.setCountryIDHome(faceDoc.getFieldValue("CountryIDHome").toString());
               faces.setTimezone(faceDoc.getFieldValue("Timezone").toString());               
               faces.setProfileImage(faceDoc.getFieldValue("ProfileImage").toString());
               faces.setRelationshipStatus(faceDoc.getFieldValue("RelationshipStatus").toString());
               faces.setBodyType(faceDoc.getFieldValue("BodyType").toString());
               faces.setHeight(faceDoc.getFieldValue("Height").toString());
               faces.setHeightViewType(faceDoc.getFieldValue("HeightViewType").toString());
               faces.setHairColor(faceDoc.getFieldValue("HairColor").toString());
               faces.setEyeColor(faceDoc.getFieldValue("EyeColor").toString());
               faces.setSexuality(faceDoc.getFieldValue("Sexuality").toString());
               faces.setBestFeature(faceDoc.getFieldValue("BestFeature").toString());
               faces.setExercise(faceDoc.getFieldValue("Exercise").toString());
               faces.setSmoke(faceDoc.getFieldValue("Smoke").toString());
               faces.setDrink(faceDoc.getFieldValue("Drink").toString());
               faces.setDrugUser(faceDoc.getFieldValue("DrugUser").toString());
               faces.setBodyArt(faceDoc.getFieldValue("BodyArt").toString());
               faces.setProfession(faceDoc.getFieldValue("Profession").toString());
               faces.setAnnualIncome(faceDoc.getFieldValue("AnnualIncome").toString());
               faces.setReligiousAffiliations(faceDoc.getFieldValue("ReligiousAffiliations").toString());
               faces.setHaveChildren(faceDoc.getFieldValue("HaveChildren").toString());
               faces.setWantChildren(faceDoc.getFieldValue("WantChildren").toString());               
               faces.setHavePets(faceDoc.getFieldValue("HavePets").toString());
               faces.setCreatedDate(dateFormat.format(createdDate));
               faces.setActivationDate(dateFormat.format(activationDate));
               faces.setUpdatedDate(dateFormat.format(updatedDate));
               faces.setDeletionDate(dateFormat.format(deletionDate));
               faces.setLastLoginDate(dateFormat.format(lastLoginDate));
               faces.setStatus(faceDoc.getFieldValue("Status").toString());
               faces.setIsCrawlerAllow(faceDoc.getFieldValue("IsCrawlerAllow").toString());
               faces.setRole(faceDoc.getFieldValue("Role").toString());
               faces.setOnlineStatus(faceDoc.getFieldValue("OnlineStatus").toString());
               faces.setZodiacSign(faceDoc.getFieldValue("ZodiacSign").toString());
               faces.setZodiacAnimal(faceDoc.getFieldValue("ZodiacAnimal").toString());               
               faces.setInMyOwnWords(faceDoc.getFieldValue("InMyOwnWords").toString());
               faces.setWhoCanSeeInSearch(faceDoc.getFieldValue("WhoCanSeeInSearch").toString());
               faces.setAddress(faceDoc.getFieldValue("Address").toString());
               faces.setNeighborhood(faceDoc.getFieldValue("Neighborhood").toString());
               faces.setWebsite(faceDoc.getFieldValue("Website").toString());
               faces.setShowWelcomePage(faceDoc.getFieldValue("ShowWelcomePage").toString());
               faces.setShowCompleteStatus(faceDoc.getFieldValue("ShowCompleteStatus").toString());
               faces.setIsFundermailSent(faceDoc.getFieldValue("IsFundermailSent").toString());
               faces.setMusicListen(faceDoc.getFieldValue("MusicListen").toString());               
               faces.setMoviesWatch(faceDoc.getFieldValue("MoviesWatch").toString());
               faces.setBooksRead(faceDoc.getFieldValue("BooksRead").toString());
               faces.setPassions(faceDoc.getFieldValue("Passions").toString());
               faces.setOtherInterests(faceDoc.getFieldValue("OtherInterests").toString());
               faces.setGetsMeExcited(faceDoc.getFieldValue("GetsMeExcited").toString());
               faces.setContactDB(faceDoc.getFieldValue("ContactDB").toString());
               faces.setUpdateDB(faceDoc.getFieldValue("UpdateDB").toString());              
               faces.setInfoDB(faceDoc.getFieldValue("InfoDB").toString());
               faces.setCommentDB(faceDoc.getFieldValue("CommentDB").toString());
               faces.setContentDB(faceDoc.getFieldValue("ContentDB").toString());
               faces.setMessageDB(faceDoc.getFieldValue("MessageDB").toString());
               
               
               faces.setProfileFileJson(faceDoc.getFieldValue("ProfileFileJson").toString());
               
               
               faces.setFolderDB(faceDoc.getFieldValue("FolderDB").toString());
               faces.setNotificationDB(faceDoc.getFieldValue("NotificationDB").toString());
               faces.setSearchDB(faceDoc.getFieldValue("SearchDB").toString());
               faces.setProfileFileID(faceDoc.getFieldValue("ProfileFileID").toString());
               faces.setYTKSearch(faceDoc.getFieldValue("YTKSearch").toString());
               faces.setPublicSearch(faceDoc.getFieldValue("PublicSearch").toString());
               facesList.add(faces);  
            }		    
		    
		    Object[] resultArr = new Object[2];		   
		    String   numFound = response.getResults().getNumFound()+"";
	        resultArr[0] = facesList;
	        resultArr[1] = numFound;
	         return  resultArr;  
	    } 
	    catch (SolrServerException e) 
	    {
	    	logDetails.getException(e , logger , request);
	        throw new DataAccessResourceFailureException(e.getMessage(), e);
	    }
	    catch (NullPointerException e) 
	    {
	    	logDetails.getException(e , logger, request);
	    	throw new DataAccessResourceFailureException(e.getMessage(), e);
		}
	 
	} 	
	
	
	
	public  Object[] queryCheckForSpecificfield(String query,int pageCount,int size , HttpServletRequest request) 
	{
	    try 
	    {
	    	String url = serverurlConstants.ADD_FACES_URL;
	    	SolrServer server =  Adder.getSolrServer(url);
		    ModifiableSolrParams params = new ModifiableSolrParams();
		    params.set("q", query);
		    params.set("fl", "Email , ProfileFileID , DisplayAlternateName , ScreenName , ProfileImage");
		    params.set("start", ""+pageCount);
		    params.set("version", "2.2");
		    params.set("wt", "json");
		    params.set("indent", "on");
		    params.set("rows", ""+size);
		   
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
	    catch (NullPointerException e) 
	    {
	    	logDetails.getException(e , logger , request);
	    	throw new DataAccessResourceFailureException(e.getMessage(), e);
		}
	  
	} 	
	
	public  Object[] updateFace(String query , HttpServletRequest request) {
	
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String rows="1";
		    try 
		    {
		    	String url = serverurlConstants.ADD_FACES_URL;
		    	SolrServer server =  Adder.getSolrServer(url);
			    ModifiableSolrParams params = new ModifiableSolrParams();
			    params.set("q", query);
			  	    
			    params.set("version", "2.2");
			    params.set("wt", "json");
			    params.set("indent", "on");
			    params.set("rows", ""+rows);
			    QueryResponse response = server.query(params, SolrRequest.METHOD.POST);
			    
			     String BirthDay = null;
				 String AnniversaryDay = null;
				 String CreatedDate = null;
				 String ActivationDate = null;
				 String UpdatedDate = null;
				 String DeletionDate = null;
				 String LastLoginDate = null;
			    
			    
			    
			    Date birthDay=null;
			    Date anniversaryDay=null;
			    Date createdDate=null;
			    Date activationDate=null;
			    Date updatedDate=null;
			    Date deletionDate=null;
			    Date lastLoginDate=null;
			    
			    List <Faces> facesList = new ArrayList<Faces>();
			    for (SolrDocument faceDoc : response.getResults())
	            {
			    	
			    	 dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
			    	birthDay=(Date)faceDoc.getFieldValue("BirthDay");
			    	anniversaryDay=(Date)faceDoc.getFieldValue("AnniversaryDay");
			    	createdDate=(Date)faceDoc.getFieldValue("CreatedDate");
			    	activationDate=(Date)faceDoc.getFieldValue("ActivationDate");
			    	updatedDate=(Date)faceDoc.getFieldValue("UpdatedDate");
			    	deletionDate=(Date)faceDoc.getFieldValue("DeletionDate");
			    	lastLoginDate=(Date)faceDoc.getFieldValue("LastLoginDate");		    	
			    	
	               Faces faces=new Faces();
	               faces.setID(faceDoc.getFieldValue("ID").toString());
	               faces.setDBID(faceDoc.getFieldValue("DBID").toString());
	               faces.setCategoryID(faceDoc.getFieldValue("CategoryID").toString());
	               faces.setCategoryName(faceDoc.getFieldValue("CategoryName").toString());
	               faces.setScreenName(faceDoc.getFieldValue("ScreenName").toString());
	               faces.setScreenNameStatus(faceDoc.getFieldValue("ScreenNameStatus").toString());               
	               faces.setFirstName(faceDoc.getFieldValue("FirstName").toString());
	               faces.setMiddleName(faceDoc.getFieldValue("MiddleName").toString());
	               faces.setLastName(faceDoc.getFieldValue("LastName").toString());
	               faces.setName(faceDoc.getFieldValue("Name").toString());
	               faces.setAlternateName(faceDoc.getFieldValue("AlternateName").toString());
	               faces.setDisplayAlternateName(faceDoc.getFieldValue("DisplayAlternateName").toString());
	               faces.setEmail(faceDoc.getFieldValue("Email").toString());
	               faces.setZipCode(faceDoc.getFieldValue("ZipCode").toString());
	               faces.setBirthDay(dateFormat.format(birthDay));
	               faces.setBirthdayOptions(faceDoc.getFieldValue("BirthdayOptions").toString());
	               faces.setAnniversaryDay(dateFormat.format(anniversaryDay));
	               faces.setGender(faceDoc.getFieldValue("Gender").toString());
	               faces.setCity(faceDoc.getFieldValue("City").toString());
	               faces.setState(faceDoc.getFieldValue("State").toString());               
	               faces.setCountry(faceDoc.getFieldValue("Country").toString());
	               faces.setCityID(faceDoc.getFieldValue("CityID").toString());
	               faces.setStateID(faceDoc.getFieldValue("StateID").toString());
	               faces.setCountryID(faceDoc.getFieldValue("CountryID").toString());
	               faces.setCityCurrent(faceDoc.getFieldValue("CityCurrent").toString());               
	               faces.setStateCurrent(faceDoc.getFieldValue("StateCurrent").toString());              
	               faces.setCountryCurrent(faceDoc.getFieldValue("CountryCurrent").toString());
	               faces.setCityIDCurrent(faceDoc.getFieldValue("CityIDCurrent").toString());
	               faces.setStateIDCurrent(faceDoc.getFieldValue("StateIDCurrent").toString());
	               faces.setCountryIDCurrent(faceDoc.getFieldValue("CountryIDCurrent").toString());
	               faces.setCityHome(faceDoc.getFieldValue("CityHome").toString());
	               faces.setStateHome(faceDoc.getFieldValue("StateHome").toString());
	               faces.setCountryHome(faceDoc.getFieldValue("CountryHome").toString());
	               faces.setCityIDHome(faceDoc.getFieldValue("CityIDHome").toString());
	               faces.setStateIDHome(faceDoc.getFieldValue("StateIDHome").toString());
	               faces.setCountryIDHome(faceDoc.getFieldValue("CountryIDHome").toString());
	               faces.setTimezone(faceDoc.getFieldValue("Timezone").toString());               
	               faces.setProfileImage(faceDoc.getFieldValue("ProfileImage").toString());
	               faces.setRelationshipStatus(faceDoc.getFieldValue("RelationshipStatus").toString());
	               faces.setBodyType(faceDoc.getFieldValue("BodyType").toString());
	               faces.setHeight(faceDoc.getFieldValue("Height").toString());
	               faces.setHeightViewType(faceDoc.getFieldValue("HeightViewType").toString());
	               faces.setHairColor(faceDoc.getFieldValue("HairColor").toString());
	               faces.setEyeColor(faceDoc.getFieldValue("EyeColor").toString());
	               faces.setSexuality(faceDoc.getFieldValue("Sexuality").toString());
	               faces.setBestFeature(faceDoc.getFieldValue("BestFeature").toString());
	               faces.setExercise(faceDoc.getFieldValue("Exercise").toString());
	               faces.setSmoke(faceDoc.getFieldValue("Smoke").toString());
	               faces.setDrink(faceDoc.getFieldValue("Drink").toString());
	               faces.setDrugUser(faceDoc.getFieldValue("DrugUser").toString());
	               faces.setBodyArt(faceDoc.getFieldValue("BodyArt").toString());
	               faces.setProfession(faceDoc.getFieldValue("Profession").toString());
	               faces.setAnnualIncome(faceDoc.getFieldValue("AnnualIncome").toString());
	               faces.setReligiousAffiliations(faceDoc.getFieldValue("ReligiousAffiliations").toString());
	               faces.setHaveChildren(faceDoc.getFieldValue("HaveChildren").toString());
	               faces.setWantChildren(faceDoc.getFieldValue("WantChildren").toString());               
	               faces.setHavePets(faceDoc.getFieldValue("HavePets").toString());
	               faces.setCreatedDate(dateFormat.format(createdDate));
	               faces.setActivationDate(dateFormat.format(activationDate));
	               faces.setUpdatedDate(dateFormat.format(updatedDate));
	               faces.setDeletionDate(dateFormat.format(deletionDate));
	               faces.setLastLoginDate(dateFormat.format(lastLoginDate));
	               faces.setStatus(faceDoc.getFieldValue("Status").toString());
	               faces.setIsCrawlerAllow(faceDoc.getFieldValue("IsCrawlerAllow").toString());
	               faces.setRole(faceDoc.getFieldValue("Role").toString());
	               faces.setOnlineStatus(faceDoc.getFieldValue("OnlineStatus").toString());
	               faces.setZodiacSign(faceDoc.getFieldValue("ZodiacSign").toString());
	               faces.setZodiacAnimal(faceDoc.getFieldValue("ZodiacAnimal").toString());               
	               faces.setInMyOwnWords(faceDoc.getFieldValue("InMyOwnWords").toString());
	               faces.setWhoCanSeeInSearch(faceDoc.getFieldValue("WhoCanSeeInSearch").toString());
	               faces.setAddress(faceDoc.getFieldValue("Address").toString());
	               faces.setNeighborhood(faceDoc.getFieldValue("Neighborhood").toString());
	               faces.setWebsite(faceDoc.getFieldValue("Website").toString());
	               faces.setShowWelcomePage(faceDoc.getFieldValue("ShowWelcomePage").toString());
	               faces.setShowCompleteStatus(faceDoc.getFieldValue("ShowCompleteStatus").toString());
	               faces.setIsFundermailSent(faceDoc.getFieldValue("IsFundermailSent").toString());
	               faces.setMusicListen(faceDoc.getFieldValue("MusicListen").toString());               
	               faces.setMoviesWatch(faceDoc.getFieldValue("MoviesWatch").toString());
	               faces.setBooksRead(faceDoc.getFieldValue("BooksRead").toString());
	               faces.setPassions(faceDoc.getFieldValue("Passions").toString());
	               faces.setOtherInterests(faceDoc.getFieldValue("OtherInterests").toString());
	               faces.setGetsMeExcited(faceDoc.getFieldValue("GetsMeExcited").toString());
	               faces.setContactDB(faceDoc.getFieldValue("ContactDB").toString());
	               faces.setUpdateDB(faceDoc.getFieldValue("UpdateDB").toString());              
	               faces.setInfoDB(faceDoc.getFieldValue("InfoDB").toString());
	               faces.setCommentDB(faceDoc.getFieldValue("CommentDB").toString());
	               faces.setContentDB(faceDoc.getFieldValue("ContentDB").toString());
	               faces.setMessageDB(faceDoc.getFieldValue("MessageDB").toString());
	               
	               
	               faces.setProfileFileJson(faceDoc.getFieldValue("ProfileFileJson").toString());
	               
	               
	               faces.setFolderDB(faceDoc.getFieldValue("FolderDB").toString());
	               faces.setNotificationDB(faceDoc.getFieldValue("NotificationDB").toString());
	               faces.setSearchDB(faceDoc.getFieldValue("SearchDB").toString());
	               faces.setProfileFileID(faceDoc.getFieldValue("ProfileFileID").toString());	               
	               faces.setYTKSearch(faceDoc.getFieldValue("YTKSearch").toString());
	               faces.setPublicSearch(faceDoc.getFieldValue("PublicSearch").toString());
	               facesList.add(faces);  
	            }		    
			    
			    Object[] resultArr = new Object[2];		   
			    String   numFound = response.getResults().getNumFound()+"";
		        resultArr[0] = facesList;
		        resultArr[1] = numFound;
		         return  resultArr;  
		    } 
		    catch (SolrServerException e) 
		    {
		    	logDetails.getException(e , logger, request);
		        throw new DataAccessResourceFailureException(e.getMessage(), e);
		    }
		    catch (NullPointerException e) 
		    {
		    	logDetails.getException(e , logger, request);
		    	throw new DataAccessResourceFailureException(e.getMessage(), e);
			}
	}
	
	
	public  Object[] headerFace(String query,int pageCount,int size , HttpServletRequest request) 
	{
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    try 
	    {
	    	String url = serverurlConstants.ADD_FACES_URL;
	    	SolrServer server =  Adder.getSolrServer(url);
		    ModifiableSolrParams params = new ModifiableSolrParams();
		    params.set("q", query);
		    params.set("start", ""+pageCount);		
		    params.set("fl", "ID , ProfileFileID , Name , ScreenName , ProfileImage , CategoryName , City , State , FirstName , LastName , ProfileFileJson");
		    params.set("version", "2.2");
		    params.set("wt", "json");
		    params.set("indent", "on");
		    params.set("rows", ""+size);
		    QueryResponse response = server.query(params, SolrRequest.METHOD.POST);
		    
		  
		    
		    
		    
		    List <HeaderFace> facesList = new ArrayList<HeaderFace>();
		    for (SolrDocument faceDoc : response.getResults())
            {	 
               HeaderFace faces=new HeaderFace();
               if(faceDoc.getFieldValue("ID")!=null)
               faces.setID(faceDoc.getFieldValue("ID").toString());               
               if(faceDoc.getFieldValue("CategoryName")!=null)
               faces.setCategoryName(faceDoc.getFieldValue("CategoryName").toString());
               if(faceDoc.getFieldValue("ScreenName")!=null)
               faces.setScreenName(faceDoc.getFieldValue("ScreenName").toString());                              
               if(faceDoc.getFieldValue("FirstName")!=null)
               faces.setFirstName(faceDoc.getFieldValue("FirstName").toString());
               if(faceDoc.getFieldValue("LastName")!=null)
               faces.setLastName(faceDoc.getFieldValue("LastName").toString());
               if(faceDoc.getFieldValue("Name")!=null)
               faces.setName(faceDoc.getFieldValue("Name").toString());               
               if(faceDoc.getFieldValue("City")!=null)
               faces.setCity(faceDoc.getFieldValue("City").toString());
               if(faceDoc.getFieldValue("State")!=null)
               faces.setState(faceDoc.getFieldValue("State").toString());                             
               if(faceDoc.getFieldValue("ProfileImage")!=null)
               faces.setProfileImage(faceDoc.getFieldValue("ProfileImage").toString());
               if(faceDoc.getFieldValue("ProfileFileID")!=null)
               faces.setProfileFileID(faceDoc.getFieldValue("ProfileFileID").toString());
               if(faceDoc.getFieldValue("ProfileFileJson")!=null)
                   faces.setProfileFileJson(faceDoc.getFieldValue("ProfileFileJson").toString());
               facesList.add(faces);  
            }		    
		    
		    Object[] resultArr = new Object[2];		   
		    String   numFound = response.getResults().getNumFound()+"";
	        resultArr[0] = facesList;
	        resultArr[1] = numFound;
	         return  resultArr;  
	    } 
	    catch (SolrServerException e) 
	    {
	    	logDetails.getException(e , logger , request);
	        throw new DataAccessResourceFailureException(e.getMessage(), e);
	    }
	    catch (NullPointerException e) 
	    {
	    	logDetails.getException(e , logger , request);
	    	throw new DataAccessResourceFailureException(e.getMessage(), e);
		}
	 
	} 	
	
	
	
	
	}
	
	

