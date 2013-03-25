package com.mobicart.dao;

import java.util.List;

import com.mobicart.model.AppFeatures;
import com.mobicart.model.AppFeaturesExample;
import com.mobicart.model.Feature;

public interface FeatureDAO {

	List<Feature> findFeaturesByApp(long appId);
	
	List<Feature> findAllFeatures();
	
	boolean removeAppFeaturesByAppId(long appId);
	
	boolean saveAppFeatures(List<AppFeatures> appFeatures);
	
	boolean updateAppFeaturesByExample(AppFeatures appFeatures,AppFeaturesExample appFeaturesExample);
	
	
}
