package com.mobicart.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.mobicart.model.AppDeviceTokens;
import com.mobicart.model.AppDeviceTokensExample;

public interface AppDeviceTokensDAO {
    
	public Long registerApp(AppDeviceTokens appDeviceTokens) throws DataAccessException;
	public List<AppDeviceTokens> findDeviceByExample(AppDeviceTokensExample appDeviceTokensExample)  throws DataAccessException;
	public List<AppDeviceTokens> getDevicesByRadius(Double latitude,Double longitude, String distance,Long appId)  throws DataAccessException;

}