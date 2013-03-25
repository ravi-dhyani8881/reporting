package com.mobicart.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.mobicart.model.Address;

public class AddressDAOImpl implements AddressDAO {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(AddressDAOImpl.class);

	@Autowired
	SqlMapClient sqlMapClient;
	
	
	public Address findAddressById(Long addressId) {
		Address address=null;
		
		try {
			address=(Address) sqlMapClient.queryForObject("users_address.selectByPrimaryKey", new Address(addressId));
		} catch (SQLException e) {
			logger.warn("findAddressById(Long) - exception ignored", e); //$NON-NLS-1$
			
		}
		return address;
	}

	public Address findAddressByUserId(Long userId) {
		Address address=null;
			try {
				address=(Address) sqlMapClient.queryForObject("users_address.selectByUserId",userId);
			} catch (SQLException e) {
			logger.error("findAddressByUserId(Long)", e); //$NON-NLS-1$
			}
		return address;
	}

	public Long save(Address address) {
		Long addressId=null;
		try {
			addressId=(Long) sqlMapClient.insert("users_address.insert", address);
		} catch (SQLException e) {
			logger.warn("save(Address) - exception ignored", e); //$NON-NLS-1$
		}
		
		return addressId;
	}

	public boolean update(Address address) {
		int rows=0;
		try {
			rows=sqlMapClient.update("users_address.updateByUserIdSelective", address);
		} catch (SQLException e) {
			logger.warn("update(Address) - exception ignored", e); //$NON-NLS-1$
		}
		
		return rows!=0?true:false;
	}

}
