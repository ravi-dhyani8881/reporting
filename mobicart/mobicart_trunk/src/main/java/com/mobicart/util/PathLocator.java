package com.mobicart.util;

import java.io.File;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class to find path 
 * @author jasdeep.singh
 *
 */

public class PathLocator {
	

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(PathLocator.class);

	
	private String operatingSystem=null;
	private String folder=null;
	
	public PathLocator() {
		 operatingSystem=ResourceProperties.getString("os");
		 folder=ResourceProperties.getString("folder");
	}
	
	
	/**
     * return path of project till /mobicart
     * @return
     */

    public String getRealPath() {
        String actualPath = getClass().getProtectionDomain().getCodeSource().getLocation().toString();
        String realPath = actualPath.substring(0, actualPath.indexOf("WEB-"));
        if (realPath.contains("file:/")) {
             if (operatingSystem.equalsIgnoreCase("windows")) {
                 realPath = realPath.substring(6); //for window
             } else if (operatingSystem.equalsIgnoreCase("linux")) {
                 realPath = realPath.substring(5);//for Linux
             }
        }
        return realPath;
    }
    
 
    
	public String getContextPath() {
		
		String actualPath = getClass().getProtectionDomain().getCodeSource().getLocation().toString();
		
		String realPath = actualPath;
		
		try {

			if (folder.trim().equalsIgnoreCase("mobi-cart")) {
				//realPath = actualPath.substring(0,actualPath.indexOf("WEB-INF"));
				realPath = actualPath.substring(0, actualPath.indexOf("mobi-cart"));
				if(logger.isDebugEnabled()){
					logger.debug("Real upload path live: "+realPath);
				}

			} else {
				realPath = actualPath.substring(0,actualPath.indexOf("mobicart"));
				if(logger.isDebugEnabled()){
					logger.debug("Real upload path local: "+realPath);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (realPath.contains("file:/")) {
			System.out.println(System.getProperty("os.name"));
			if (System.getProperty("os.name").contains("xp")||System.getProperty("os.name").contains("XP")||System.getProperty("os.name").contains("Windows")) {
				realPath = realPath.substring(6); // for window
			} else if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {
				realPath = realPath.substring(5);// for Linux
			}
		}
		
		
		return realPath;
		
		
		
		
		

	}
    
	public String getContextPath(HttpServletRequest request) {		
		String contextPath=request.getSession().getServletContext().getRealPath("/");
		return contextPath;
	}
	
	public  String getLocationToSaveAndroidApp(String userid) {		
	 
		String actualPath = getClass().getProtectionDomain().getCodeSource().getLocation().toString();
		String realPath = actualPath.substring(0, actualPath.indexOf("webapps"));
        if (realPath.contains("file:/")) {
             if (System.getProperty("os.name").contains("xp")) {
                 realPath = realPath.substring(6)+"webapps\\docs\\temp\\"+userid+"\\"; //for window
                
                 
             } else if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {
                 realPath = realPath.substring(5)+"webapps/docs/temp/"+userid+"/";//for Linux
             }
        }
        
        
        try{
        	
        	 File dirs=new File(realPath);
             if(dirs.isDirectory()){
            	 dirs.delete(); 
             }
             else{
            	 dirs.mkdirs();
             }
        }
        catch(Exception e1) {
			e1.printStackTrace();
		}
        
		return realPath;
	}
	
	public  String getDocumentsRootPath() {		
		 
		String actualPath = getClass().getProtectionDomain().getCodeSource().getLocation().toString();
		String realPath = actualPath.substring(0, actualPath.indexOf("webapps"));
        if (realPath.contains("file:/")) {
             if (System.getProperty("os.name").contains("xp")) {
                 realPath = realPath.substring(6)+"webapps\\docs\\"; //for window
                
                 
             } else if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {
                 realPath = realPath.substring(5)+"webapps/docs/"; //for Linux
             }
        }
	 
	
    return realPath;
    
}
}
