/**
 * 
 */
package com.mobicart.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.mobicart.model.SiteConstant;
import com.mobicart.model.SiteConstantExample;

/**
 * @author jasdeep.singh
 *
 */
@Repository
public class SiteConstantDAOImpl implements SiteConstantDAO {

	/**
	 * Sql Map Client instance 
	 */
	@Autowired
	private SqlMapClient sqlMapClient;
	
	/**
	 * create new site constant record  
	 */
	public Long create(SiteConstant newInstance) {
		Long id = null;
		try {
			id = (Long) sqlMapClient.insert("site_constants.insert",
					newInstance);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	/**
	 * find site content 
	 */
	public SiteConstant find(Long id) {
		SiteConstant siteConstant=null;
		try {
			siteConstant =  (SiteConstant) sqlMapClient.queryForObject("site_constants.selectByPrimaryKey",
					new SiteConstant(id));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return siteConstant;
	}

	@SuppressWarnings("unchecked")
	public List<SiteConstant> findAll() {
		List<SiteConstant> siteConstants=null;
		try{
			siteConstants=sqlMapClient.queryForList("site_constants.selectAll", null);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return siteConstants;
	}

	public void update(SiteConstant transientObject) {
		 try {
			sqlMapClient.update("site_constants.updateByPrimaryKeySelective",transientObject);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void delete(SiteConstant persistentObject) {
		try {
			sqlMapClient.delete("site_constants.deleteByExample",persistentObject);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public List<SiteConstant> findByExample(
			SiteConstantExample siteConstantExample) {
		List<SiteConstant> siteConstants=null;
		try{
			siteConstants=sqlMapClient.queryForList("site_constants.selectByExample",siteConstantExample);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return siteConstants;	
		}

}
