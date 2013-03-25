package com.mobicart.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.mobicart.model.MerchantService;
import com.mobicart.model.MerchantServiceExample;

@Repository 
public class MerchantServiceDAOImpl implements MerchantServiceDAO{

	@Autowired
	private SqlMapClient sqlMapClient;
	
	public Long save(MerchantService merchantService) throws SQLException{
		return (Long) sqlMapClient.insert("merchant_services.insert", merchantService);
	}
	
	@SuppressWarnings("unchecked")
	public List<MerchantService> getMerchantServiceByExample(MerchantServiceExample merchantServiceExample)  throws SQLException{
			return sqlMapClient.queryForList("merchant_services.selectByExample", merchantServiceExample);
	}
	
	public void updateMerchantService(MerchantService pushNotification) throws SQLException{
			sqlMapClient.update("merchant_services.updateByPrimaryKeySelective", pushNotification);
	}
	
	
	public void deleteMerchantService(MerchantService pushNotification)  throws SQLException{
			sqlMapClient.update("merchant_services.deleteByPrimaryKey", pushNotification);
	}
	
	 public MerchantService findMerchantServiceById(Long id)  throws SQLException{
		 	return (MerchantService) sqlMapClient.queryForObject(
						"merchant_services.selectByPrimaryKey", new MerchantService(id));
	 }
}
