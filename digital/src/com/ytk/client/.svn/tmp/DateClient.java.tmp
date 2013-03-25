package com.ytk.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ytk.controller.demoControler;
import com.ytk.utility.LogDetails;

/**
 * 
 * @author ravi.dhyani
 * 
 */
@Component("dateClient")
public class DateClient {
	
	private static final Logger logger = LoggerFactory.getLogger(DateClient.class);
	
	@Autowired
	LogDetails logDetails;
	
	public String addDateToSolr(String parseDate , HttpServletRequest request){
		Object dateObject = null;
		SimpleDateFormat dateFormatSimple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat dateFormatSolr = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		try {
			dateObject = dateFormatSimple.parse(parseDate);
		} catch (ParseException e) {
			logDetails.getException(e , logger , request);
			e.printStackTrace();
		}
		Date dateString = (Date) dateObject;
		return dateFormatSolr.format(dateString);
	}

	public String addDateFromSolrToServiceforPdate(Object SolrDate , HttpServletRequest request){		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	String solrDate=	dateFormat.format(SolrDate);
		Object formatdate = null;
		try {
			formatdate = dateFormat.parse(solrDate);
		} catch (ParseException e) {
			logDetails.getException(e , logger ,request);
			e.printStackTrace();
		}
		Date FormatTimeStamp = (Date) formatdate;
		SimpleDateFormat dateFormatToService = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormatToService.format(FormatTimeStamp);
	}
	
	
	
	
	
	
	
	
	
	
	
	/*
	public String addDateFromSolrToService(Object SolrDate){		
		Date dateObject=null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
	//	 dateObject=(Date)SolrDate;
	//	 return dateFormat.format(dateObject);
		 
		 return "1900-10-10 01:22:22";
	}

*/
	public String addDateFromSolrToService(Object SolrDate , HttpServletRequest request){		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		String solrDate=SolrDate.toString();
		Object formatdate = null;
		try {
			formatdate = dateFormat.parse(solrDate);
		} catch (ParseException e) {
			logDetails.getException(e , logger , request);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date FormatTimeStamp = (Date) formatdate;
		SimpleDateFormat dateFormatToService = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		
		return dateFormatToService.format(FormatTimeStamp);
	}
	
	
	
}
