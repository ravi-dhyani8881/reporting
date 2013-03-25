package com.mobicart.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mobicart.dto.FeedDto;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class FeedParserUtils {

	private static final Logger logger = LoggerFactory.getLogger(FeedParserUtils.class);
	
	/**
	 * Get feed postes for 
	 * @param feedUrl
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<FeedDto> getFeedPosts(String feedUrl) {
		List<FeedDto> feedPosts = null;
		XmlReader reader=null;
		try {
			reader = new XmlReader(new URL(feedUrl));
			SyndFeed feed = new SyndFeedInput().build(reader);
			feedPosts = new ArrayList<FeedDto>();
			for (SyndEntry entry : (List<SyndEntry>) feed.getEntries()) {
			 
				FeedDto feedDto = new FeedDto();
				feedDto.setAuthor(entry.getAuthor());
				feedDto.setTitle(entry.getTitle());
				//feedDto.setShortDescription(CommonUtils.shortfyString(entry.getDescription().getValue(), 100));
				feedDto.setShortDescription(CommonUtils.shortfyString((entry.getDescription()!=null)?entry.getDescription().getValue():null, 100));
				feedDto.setLongDescription((entry.getDescription()!=null)?entry.getDescription().getValue():null);
				feedDto.setPublishedOn(entry.getPublishedDate());
				feedPosts.add(feedDto);
				reader.close();
			}

		} catch (MalformedURLException e1) {
			logger.error("malformed url{}",e1.getMessage());
		} catch (IOException e1) {
			logger.error("io exception{}",e1.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("illegel argument in feed {}",e.getMessage());

		} catch (FeedException e) {
			logger.error("feed exception {}",e.getMessage());
		}
		finally{
			if(reader!=null){
				try{
				reader.close();}
				catch (Exception e) {
					logger.error("reader close exception {}",e.getMessage());
					reader=null; 
				}
			}
		}

		return feedPosts;
	}
}
