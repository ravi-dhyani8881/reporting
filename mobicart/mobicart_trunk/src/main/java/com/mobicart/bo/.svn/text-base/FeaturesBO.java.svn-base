package com.mobicart.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mobicart.model.Feature;
import com.mobicart.model.StaticPage;
import com.mobicart.service.AppService;

public class FeaturesBO {

	@Autowired
	AppService appService;
	
	public List<Feature> getFeatureDetail(List<Feature> features,Long appId){
		
		List <StaticPage> pages=null;
		
		List<Feature> final_features=new ArrayList<Feature>();
		
		StaticPage page=null;
		
		for(Feature feature:features){
		
			page=null;
			try{
			//get page detail
			pages=appService.findStaticPagesByAppAndFeatureId(appId, feature.getId());
		
			//set detail in feature
			if(pages!=null&&pages.size()>0){
			
				page=pages.get(0);
				
				feature.setTabTitle(page.getsTitle());
				feature.setTabDescription(page.getsDescription());
			}
			}catch(Exception e){e.printStackTrace();}
			
			final_features.add(feature);
			
		}
		return final_features;
	}
	
}
