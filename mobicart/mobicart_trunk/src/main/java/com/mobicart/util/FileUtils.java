package com.mobicart.util;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;

public class FileUtils {

	/**
	 * make directory if its not there 
	 * @param path
	 */
	public static void makeDirectoryIfItsNotThere(String path) {
		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdir();
		}
	}

	/**
	 * Get juts the file name
	 * @param path
	 * @return
	 */
	
	public static String getJustTheFileName(String path){
		
		return FilenameUtils.getBaseName(path);
	}
	
	/**
	 * Stuffs text just before file extension
	 * 
	 * @param fileName
	 * @param textToStuff
	 * @return
	 */
	public static String stuffedFilename(String fileName, String textToStuff) {
		try{
		int stripStart = 0;
		int stripEnd = fileName.lastIndexOf(".");

		
		String justTheName = fileName.substring(stripStart, stripEnd);
		String justTheExtension = fileName.substring(stripEnd).toLowerCase();
		
		return justTheName + textToStuff + justTheExtension;
		
		}catch (Exception e) {
			 
			//e.printStackTrace();
		}
		return fileName;
	}
	

	/**
	 * Clean Special chars
	 * 
	 * @param fileName
	 * @param textToStuff
	 * @return
	 */
	public static String cleanSpecialChars(String fileName) {
		String REGEX = "([^a-zA-z0-9.])";
		String REPLACE = "-";

		Pattern p = Pattern.compile(REGEX);
		Matcher m = p.matcher(fileName); // get a matcher object
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, REPLACE);
		}
		m.appendTail(sb);
		return sb.toString();
	}

	

}
