package com.mobicart.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class ValidationUtils {

	private static Logger logger=LoggerFactory.getLogger(FileUtils.class);

	public static boolean validateFeed(String url)  {
		
		
		SyndFeedInput sfi = new SyndFeedInput();
		SyndFeed feed=null;
		try {
			feed = sfi.build(new XmlReader(new URL(url)));
			feed.getTitle();
		} catch (IllegalArgumentException e) {
			logger.warn("error",e);
		    return false;
		} catch (MalformedURLException e) {
			logger.warn("error",e);
		    return false;
		} catch (FeedException e) {
			logger.warn("error",e);
		    return false;
		} catch (IOException e) {
			logger.warn("error",e);
		    return false;
		} catch (RuntimeException e) {
			logger.warn("error",e);
		    return false;
		}catch(Exception e){
			logger.warn("error",e);
		    return false;
		}
    	
        return true;
	}
}
