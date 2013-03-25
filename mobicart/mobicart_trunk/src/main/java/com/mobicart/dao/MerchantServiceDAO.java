package com.mobicart.dao;

import java.sql.SQLException;
import java.util.List;

import com.mobicart.model.MerchantService;
import com.mobicart.model.MerchantServiceExample;

public interface MerchantServiceDAO {

	public Long save(MerchantService merchantServic) throws SQLException;
	
	public List<MerchantService> getMerchantServiceByExample(MerchantServiceExample merchantServiceExample) throws SQLException;
	
	public void updateMerchantService(MerchantService pushNotification) throws SQLException;
	
	public void deleteMerchantService(MerchantService pushNotification) throws SQLException;
	
	public MerchantService findMerchantServiceById(Long id) throws SQLException;
}
