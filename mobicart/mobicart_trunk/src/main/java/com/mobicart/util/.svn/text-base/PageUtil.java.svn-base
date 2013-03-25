/**
 * 
 */
package com.mobicart.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
//import com.googlecode.ehcache.annotations.Cacheable;
//import com.googlecode.ehcache.annotations.TriggersRemove;
//import com.googlecode.ehcache.annotations.When;


import com.mobicart.model.StaticPage;

/**
 * @author jasdeep.singh
 *
 */
@Component
public class PageUtil {
	
	//@Cacheable(cacheName = "messageCache")
	public static List<StaticPage> getStaticPagesToFeed(Long userId,Long appId){
		
		List<StaticPage> staticPageTemplates=new ArrayList<StaticPage>();
		staticPageTemplates.add(new StaticPage(5L,userId,appId,"about","About Us") );
		staticPageTemplates.add(new StaticPage(6L,userId,appId,"contact","Contact Us") );
		staticPageTemplates.add(new StaticPage(7L,userId,appId,"terms","Terms & Conditions") );
		staticPageTemplates.add(new StaticPage(8L,userId,appId,"privacy","Privacy") );
		staticPageTemplates.add(new StaticPage(9L,userId,appId,"page1","Page 1") );
		staticPageTemplates.add(new StaticPage(10L,userId,appId,"page2","Page 2") );
		staticPageTemplates.add(new StaticPage(null,userId,appId,"copyright","Branding") );
		return staticPageTemplates;
	}

}
