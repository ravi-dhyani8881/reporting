package com.mobicart.dao;

import java.sql.SQLException;
import java.util.List;

import com.mobicart.model.AppTerritory;
import com.mobicart.model.Territory;
import com.mobicart.model.TerritoryExample;



public interface TerritoryDAO {

	public List<Territory> findAllTerritories();
	
	public Territory findTerritoryById(Long id);
	
	public List<Territory> findAppTerritories(Long appId);
	
	public List<AppTerritory> findAppTerritoriesRelation(Long appId);
	
	public void saveAppTerritories(List<AppTerritory> appTerritories); 
	
	public int removeAppTerritories(Long appId);

	public List<Territory> findTerritoriesByExample(TerritoryExample territoryExample)throws SQLException;
	 
	
	
}
