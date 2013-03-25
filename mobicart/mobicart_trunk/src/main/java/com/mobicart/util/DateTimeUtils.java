package com.mobicart.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateTimeUtils {

	// static variable to assign date for the day
	private static Date today;
	
	
	/**
	 * Get current timestamp
	 * @return current sql timestamp
	 */
	public static java.sql.Timestamp getCurrentTimestamp(){
		today = new Date();		
		return new java.sql.Timestamp(today.getTime());
	}
	
	/**
	 * Gets current date
	 * @return String ddMMyyyy 
	 */
	public static String getCurrentDate(){
		today = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
		return formatter.format(today);
	}
	
	
	
	/**
	 * Gets current date
	 * @return String ddMMyyyy 
	 */
	public static Date getDateToday(){
		return  new Date();
	}
	
	
	
	
	public static List<Month> getAllMonths(){
		List<Month> months=new ArrayList<Month>();
		months.add(new Month("January","01"));
		months.add(new Month("February","02"));
		months.add(new Month("March","03"));
		months.add(new Month("April","04"));
		months.add(new Month("May","05"));
		months.add(new Month("June","06"));
		months.add(new Month("July","07"));
		months.add(new Month("August","08"));
		months.add(new Month("September","09"));
		months.add(new Month("October","10"));
		months.add(new Month("November","11"));
		months.add(new Month("December","12"));
		return months;
	}
	
	public static List<Year> getComingYears(){
		List<Year> years=new ArrayList<Year>();
		
		for(Integer year=2010;year<2050;year++   ){
			years.add(new Year(year.toString()));
		}
		
		return years;
	}
	
	
}
