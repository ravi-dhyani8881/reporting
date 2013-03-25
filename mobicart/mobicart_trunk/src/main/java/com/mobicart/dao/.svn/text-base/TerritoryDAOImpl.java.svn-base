package com.mobicart.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.mobicart.model.AppTerritory;
import com.mobicart.model.AppTerritoryExample;
import com.mobicart.model.Territory;
import com.mobicart.model.TerritoryExample;

 
@Repository
public class TerritoryDAOImpl implements TerritoryDAO {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(TerritoryDAOImpl.class);

	@Autowired
	SqlMapClient sqlMapClient;
	
	
	
	
	public Territory findTerritoryById(Long id) {
		Territory territory =null;
		try {
			territory=(Territory) sqlMapClient.queryForObject("territories.selectByPrimaryKey", new Territory(id));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return territory;
	}

	@SuppressWarnings("unchecked")
	public List<Territory> findAllTerritories() {
		List<Territory> territories=null;
		try {
			 territories=sqlMapClient.queryForList("territories.selectAll", null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return territories;
	}

	@SuppressWarnings("unchecked")
	public List<Territory> findAppTerritories(Long appId) {
		List<Territory> territories=null;
		 try {
			territories=sqlMapClient.queryForList("territories.selectByAppId", appId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return territories;
	}

	public int removeAppTerritories(Long appId) {
		int rows=0;
		try {
			AppTerritoryExample appTerritoryExample=new AppTerritoryExample();
			appTerritoryExample.createCriteria().andAppIdEqualTo(appId);
			rows = sqlMapClient.delete("app_territories.deleteByExample", appTerritoryExample);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}
	
	
	
	public List<Territory> findTerritoriesByExample(TerritoryExample territoryExample)throws SQLException{
		
		return sqlMapClient.queryForList("territories.selectByExample", territoryExample);
		
	}
	
	



	public void saveAppTerritories(List<AppTerritory> appTerritories) {
		
		try {
			sqlMapClient.startTransaction();
			sqlMapClient.startBatch();
			
			for (AppTerritory appTerritory : appTerritories) {
				sqlMapClient.insert("app_territories.insert", appTerritory);
			}
			
			sqlMapClient.executeBatch();
			sqlMapClient.commitTransaction();
			
		} catch (Exception e) {
			logger.warn("saveAppTerritories(List<AppTerritory>) - exception ignored", e); //$NON-NLS-1$
		} finally {
			try {
				sqlMapClient.endTransaction();
			} catch (SQLException e) {
				logger.warn("saveAppTerritories(List<AppTerritory>) - exception ignored", e); //$NON-NLS-1$
			
			}
		}
		
		
	}

	@SuppressWarnings("unchecked")
	public List<AppTerritory> findAppTerritoriesRelation(Long appId) {
		List<AppTerritory> appTerritories=null;
		
		try {
			appTerritories=sqlMapClient.queryForList("app_territories.selectByAppId", appId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return appTerritories;
	}

	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	 

}
