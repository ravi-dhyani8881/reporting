package com.mobicart.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.mobicart.model.AppDeviceTokens;
import com.mobicart.model.AppDeviceTokensExample;

public class AppDeviceTokensDAOImpl  extends SqlMapClientDaoSupport implements AppDeviceTokensDAO {
   
	
	public Long registerApp(AppDeviceTokens appDeviceTokens) throws DataAccessException{
			return (Long) getSqlMapClientTemplate().insert("app_device_tokens.insert", appDeviceTokens);
	}
	
	@SuppressWarnings("unchecked")
	public List<AppDeviceTokens> findDeviceByExample(AppDeviceTokensExample appDeviceTokensExample) throws DataAccessException{
		List<AppDeviceTokens> tokens=null;
			tokens= getSqlMapClientTemplate().queryForList("app_device_tokens.selectByExample", appDeviceTokensExample);
		return tokens;
	}

	@SuppressWarnings("unchecked")
	public List<AppDeviceTokens> getDevicesByRadius(Double latitude,Double longitude, String distance,Long appId) throws DataAccessException{
			List<AppDeviceTokens> tokens=null;
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("lat", latitude);
			paramMap.put("long", longitude);
			paramMap.put("radius", distance);
			paramMap.put("appID", appId);
			tokens= getSqlMapClientTemplate().queryForList("app_device_tokens.findDevicesInRadius",paramMap);
		return tokens;
	}
	
	
}