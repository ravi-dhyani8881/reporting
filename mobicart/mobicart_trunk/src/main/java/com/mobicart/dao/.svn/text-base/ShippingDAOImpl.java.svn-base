package com.mobicart.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.mobicart.model.Shipping;
import com.mobicart.model.ShippingExample;

@Repository
public class ShippingDAOImpl implements ShippingDAO {

	@Autowired
	SqlMapClient sqlMapClient;
	
	public Long create(Shipping newInstance) {
		Long shippingId=null;
		try {
			shippingId=(Long) sqlMapClient.insert("shipping.insert",newInstance);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return shippingId;
	}

	public Shipping find(Long id) {
		Shipping shipping=null;
		
		try {
			shipping=(Shipping) sqlMapClient.queryForObject("shipping.selectByPrimaryKey", new Shipping(id));
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return shipping;
	}

	@SuppressWarnings("unchecked")
	public List<Shipping> findAll() {
		List<Shipping> shippingList=null;
		
		try {
			shippingList=sqlMapClient.queryForList("shipping.selectAll", null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return shippingList;
	}

	public void update(Shipping transientObject) {
		
		try {
			sqlMapClient.update("shipping.updateByPrimaryKeySelective",transientObject);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void delete(Shipping persistentObject) {

		try {
			sqlMapClient.delete("shipping.deleteByPrimaryKey",persistentObject);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public List<Shipping> findByExample(ShippingExample shippingExample) {
		List<Shipping> shippingList=null;
		try {
			shippingList=sqlMapClient.queryForList("shipping.selectByExample", shippingExample);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return shippingList;
	}

}
