/**
 * 
 */
package com.mobicart.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.mobicart.dto.LabelsDto;

/**
 * 
 * @author siddhartha.bhatia
 *
 */

public class CommonUtils extends StringUtils{
	
	/**
	 * Converts labelsList into HashMap key/value pairs.
	 * 
	 * @param labelsList
	 * @return Key/Values in a HashMap
	 */
	public static HashMap<String, String> returnLabelKeyValue(List<LabelsDto> labelsList) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		for (LabelsDto dto : labelsList) {
			map.put(dto.getLabelKey(), dto.getLabelValue());
		}
		
		return map;
	}
	
	
	
	public static String shortfyString(String originalString  ,int length){
		 
		String str=StringUtils.left(originalString,length);
		if(str!=null)
		return str.concat("...");
		
		return str;
	}
	
	
	
	public static int getWidth(String text){
		return Integer.parseInt(text.split("x")[0]);
	}
	
	public static int getHeight(String text){
		return Integer.parseInt(text.split("x")[1]);
	}
	

	
	public static String replaceNullStringWithEmpty(String str){
		if(str == null){
			return "";
		}
		return str;
	}
	
	
    /**
     * Replace empty string with null.
     * 
     * @param str
     *            the str
     * 
     * @return the string
     */
    public static String replaceEmptyStringWithNull(final String str) {

        if ((str != null) && (str.equals(""))) {
            return null;
        }
        return CommonUtils.trim(str);
    }
    
    /**
     * @author Siddhartha Bhatia
     * Converts String Date pattern to timestamp universal
     */
	public static Date formatStringToTimestamp(String pattern,String date){

        // Format date for XSQL call
        DateFormat df = new SimpleDateFormat(pattern);
        Date dt = null;
        Timestamp timestamp = null;
        try
        {
        	date = replaceEmptyStringWithNull(date);
        	if(date != null){
        		dt = df.parse(date);
        	}
        	timestamp = new Timestamp(dt.getTime());
            
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        return timestamp;
    }
	
    public static String formatDateToString(final java.util.Date date,final String pattern) {

        // Format date for XSQL call
        SimpleDateFormat formatter;
        
        formatter = new SimpleDateFormat(pattern);
        final String formatDate = formatter.format(date);
        return formatDate;
    }
	
	
}
