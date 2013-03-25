package com.mobicart.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.mobicart.dto.IphoneLabelsDto;
import com.mobicart.dto.LabelsDto;
import com.mobicart.model.Labels;
import com.mobicart.model.User;

/**
 * 
 * @author siddhartha.bhatia
 *
 */

public interface LabelService {
	
	public HashMap<String, String> getAllIphoneLabels(Long merchantId) throws SQLException;
	
	public HashMap<String, String> getAllWebLabels(Long merchantId) throws SQLException;
	
	public void insertLabels(Labels label) throws SQLException;
	
	public int deleteByMerchantID(Long merchantId) throws SQLException;
	
	public HashMap<String, String> selectAllDefaultLabelKeyValues(User user) throws SQLException;
	
	public HashMap<String, String> selectAllDefaultLabels() throws SQLException;
	
	public User setDefaultLabelKeyValuesInUser(User user) throws SQLException;
	
	public Boolean isLabelInSync(Long merchantId,String dateAdded) throws SQLException;
	
	public List<IphoneLabelsDto> getAllExclusiveIphoneLabels(Long merchantId) throws SQLException;
	
	public String getKeyValueByMerchantID(String labelKey, Long userId) throws SQLException;

}
