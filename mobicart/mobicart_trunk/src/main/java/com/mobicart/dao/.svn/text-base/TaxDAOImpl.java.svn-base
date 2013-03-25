package com.mobicart.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.mobicart.model.Tax;
import com.mobicart.model.TaxExample;

@Repository
public class TaxDAOImpl implements TaxDAO {

	@Autowired
	SqlMapClient sqlMapClient;
	
	public Long create(Tax newInstance) {
		Long taxId=null;
		try {
			taxId=(Long) sqlMapClient.insert("tax.insert", newInstance);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return taxId;
	}

	public Tax find(Long id) {
		Tax tax=null;
		try {
			tax=(Tax) sqlMapClient.queryForObject("tax.selectByPrimaryKey", new Tax(id));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tax;
	}

	@SuppressWarnings("unchecked")
	public List<Tax> findAll() {
		List<Tax> taxList=null;
		
		try {
			taxList=sqlMapClient.queryForList("tax.selectAll",null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return taxList;
	}

	public void update(Tax transientObject) {
	
		try {
			sqlMapClient.update("tax.updateByPrimaryKeySelective",transientObject);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void delete(Tax persistentObject) {
		try {
			sqlMapClient.update("tax.deleteByPrimaryKey",persistentObject);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Tax> findByExample(TaxExample taxExample) {
		List<Tax> taxList = null;
		try {
			taxList = sqlMapClient.queryForList("tax.selectByExample",taxExample);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return taxList;
	}

}
