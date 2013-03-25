package com.ytk.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ytk.utility.LogDetails;

@Component("privacyClient")
public class PrivacyClient {
	
	private static final Logger logger = LoggerFactory.getLogger(PrivacyClient.class);
	
	@Autowired
	LogDetails logDetails;
	
	public String commonPrivacyQuery(String RestrictAge , String RestrictCountry , int Alcohol , String profileId){
		String RestAge="((RestrictAge:[* TO "+RestrictAge+"])";
		String Not=" NOT (RestrictCountry:"+RestrictCountry+")";
		String IsAlcohol=" OR (RestrictAge:1000 )";
		String OnlyMe="OR ID:"+profileId;
		//String OnlyMeNot=" -ID:"+profileId;
		if(Alcohol==1){
			RestAge=RestAge+IsAlcohol;	
		}
		if(RestrictAge.equals("0") && Alcohol==0 && !RestrictCountry.equals("0")){
			Not=" NOT (RestrictCountry:"+RestrictCountry+" AND -ID:"+profileId+")";			
			return Not+OnlyMe;		
		}
		if(profileId.equals("0")){
			return	"AND RestrictAge:0 AND -RestrictCountry:[\"\" TO *]";
		}
		return " AND "+RestAge+Not+")";
	}


	
	
	public String commonPrivacyQueryHeaderSearch(String RestrictAge , String RestrictCountry , int Alcohol , String profileId){
		String RestAge="((RestrictAge:[* TO "+RestrictAge+"])";
		String Not=" NOT (RestrictCountry:"+RestrictCountry+")";
		String IsAlcohol=" OR (RestrictAge:1000 )";
		String OnlyMe="OR ID:"+profileId;
		//String OnlyMeNot=" -ID:"+profileId;
		if(Alcohol==1){
			RestAge=RestAge+IsAlcohol;	
		}
		if(RestrictAge.equals("0") && Alcohol==0 && !RestrictCountry.equals("0")){
			Not=" NOT (RestrictCountry:"+RestrictCountry+" AND -ID:"+profileId+")";			
			return Not;		
		}
		if(profileId.equals("0")){
			return	"AND RestrictAge:0 AND -RestrictCountry:[\"\" TO *]";
		}
		return " AND "+RestAge+Not+")";
	}


	public String privacyUpdateAccountRestrictAge(String AccountRestrictAge , String AccountRestrictCountry , int Alcohol , String profileId){
		String RestAge="((AccountRestrictAge:[* TO "+AccountRestrictAge+"])";
		String Not=" NOT (AccountRestrictCountry:"+AccountRestrictCountry+")";
		String IsAlcohol=" OR (AccountRestrictAge:1000 )";
		String OnlyMe="OR ID:"+profileId;
		//String OnlyMeNot=" -ID:"+profileId;
		if(Alcohol==1){
			RestAge=RestAge+IsAlcohol;	
		}
		if(AccountRestrictAge.equals("0") && Alcohol==0 && !AccountRestrictCountry.equals("0")){
			Not=" NOT (AccountRestrictCountry:"+AccountRestrictCountry+" AND -ID:"+profileId+")";			
			return Not+OnlyMe;		
		}
		if(profileId.equals("0")){
			return	"AND AccountRestrictAge:0 AND -AccountRestrictCountry:[\"\" TO *]";
		}
		return " AND "+RestAge+Not+")";
	}
	
	
	public String privacyUpdateGeneratorAccountRestrictAge(String GeneratorAccountRestrictAge , String GeneratorAccountRestrictCountry , int Alcohol , String LoginID){
		String RestAge="((GeneratorAccountRestrictAge:[* TO "+GeneratorAccountRestrictAge+"])";
		String Not=" NOT (GeneratorAccountRestrictCountry:"+GeneratorAccountRestrictCountry+")";
		String IsAlcohol=" OR (AccountRestrictAge:1000 )";
		String OnlyMe="OR ID:"+LoginID;
		//String OnlyMeNot=" -ID:"+profileId;
		if(Alcohol==1){
			RestAge=RestAge+IsAlcohol;	
		}
		if(GeneratorAccountRestrictAge.equals("0") && Alcohol==0 && !GeneratorAccountRestrictCountry.equals("0")){
			Not=" NOT (GeneratorAccountRestrictCountry:"+GeneratorAccountRestrictCountry+" AND -ID:"+LoginID+")";			
			return Not+OnlyMe;		
		}
		if(LoginID.equals("0")){
			return	"AND GeneratorAccountRestrictAge:0 AND -GeneratorAccountRestrictCountry:[\"\" TO *]";
		}
		return " AND "+RestAge+Not+")";
	}
	
	
	
	public String privacyUpdateAccountRestrictAge2(String AccountRestrictAge , String AccountRestrictCountry , int Alcohol , String profileId){
		String RestAge="((AccountRestrictAge:[* TO "+AccountRestrictAge+"])";
		String Not=" NOT (AccountRestrictCountry:"+AccountRestrictCountry+")";
		String IsAlcohol=" OR (AccountRestrictAge:1000 )";
	//	String OnlyMe="OR ID:"+profileId;
		//String OnlyMeNot=" -ID:"+profileId;
		if(Alcohol==1){
			RestAge=RestAge+IsAlcohol;	
		}
		if(AccountRestrictAge.equals("0") && Alcohol==0 && !AccountRestrictCountry.equals("0")){
			Not=" NOT (AccountRestrictCountry:"+AccountRestrictCountry+" AND -ID:"+profileId+")";			
			return Not;		
		}
		if(profileId.equals("0")){
			return	"AND AccountRestrictAge:0 AND -AccountRestrictCountry:[\"\" TO *]";
		}
		return " AND "+RestAge+Not+")";
	}
	
	
	public String privacyUpdateGeneratorAccountRestrictAge2(String GeneratorAccountRestrictAge , String GeneratorAccountRestrictCountry , int Alcohol , String LoginID){
		String RestAge="((GeneratorAccountRestrictAge:[* TO "+GeneratorAccountRestrictAge+"])";
		String Not=" NOT (GeneratorAccountRestrictCountry:"+GeneratorAccountRestrictCountry+")";
		String IsAlcohol=" OR (AccountRestrictAge:1000 )";
	//	String OnlyMe="OR ID:"+LoginID;
		//String OnlyMeNot=" -ID:"+profileId;
		if(Alcohol==1){
			RestAge=RestAge+IsAlcohol;	
		}
		if(GeneratorAccountRestrictAge.equals("0") && Alcohol==0 && !GeneratorAccountRestrictCountry.equals("0")){
			Not=" NOT (GeneratorAccountRestrictCountry:"+GeneratorAccountRestrictCountry+" AND -ID:"+LoginID+")";			
			return Not;		
		}
		if(LoginID.equals("0")){
			return	"AND GeneratorAccountRestrictAge:0 AND -GeneratorAccountRestrictCountry:[\"\" TO *]";
		}
		return " AND "+RestAge+Not+")";
	}
	
	
	
}
