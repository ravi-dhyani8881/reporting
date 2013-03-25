package com.mobicart.util.validators;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class FeedValidator implements ConstraintValidator<Feed, String> {

	private  Logger logger=LoggerFactory.getLogger(this.getClass());
	
	public void initialize(Feed constraintAnnotation) {
		
    }

    public boolean isValid(String object,
        ConstraintValidatorContext constraintContext) {
    	
    	logger.debug("feed validity check");
    	
    	SyndFeedInput sfi = new SyndFeedInput();
		SyndFeed feed=null;
		try {
			feed = sfi.build(new XmlReader(new URL(object)));
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
