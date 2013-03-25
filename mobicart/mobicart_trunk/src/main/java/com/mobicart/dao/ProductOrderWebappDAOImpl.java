package com.mobicart.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.mobicart.model.ProductOrderWebapp;
import com.mobicart.model.ProductOrderWebappExample;
import com.mobicart.util.DateTimeUtils;



public class ProductOrderWebappDAOImpl extends SqlMapClientDaoSupport implements ProductOrderWebappDAO {

	/**
	 * {@inheritDoc}
	 */
	public Long create(ProductOrderWebapp newInstance) throws DataAccessException {
			newInstance.setdOrderDate(DateTimeUtils.getCurrentTimestamp());
			return (Long) getSqlMapClientTemplate().insert("product_orders_temp_webapp.insert", newInstance);
	}

	/**
	 * {@inheritDoc}
	 */
	public ProductOrderWebapp find(Long id) throws DataAccessException  {
		ProductOrderWebappExample example=new ProductOrderWebappExample();
		example.createCriteria().andIdEqualTo(id);
		return (ProductOrderWebapp) getSqlMapClientTemplate().queryForObject("product_orders_temp_webapp.selectByExample", example);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<ProductOrderWebapp> findAll() throws DataAccessException  {
			return  (List<ProductOrderWebapp>) getSqlMapClientTemplate().queryForList("product_orders_temp_webapp.selectAll", null);
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(ProductOrderWebapp transientInstance) throws DataAccessException  {
			getSqlMapClientTemplate().update("product_orders_temp_webapp.updateByPrimaryKeySelective", transientInstance);
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(ProductOrderWebapp persistentInstance) throws DataAccessException  {
			getSqlMapClientTemplate().delete("product_orders_temp_webapp.deleteByPrimaryKeySelective", persistentInstance);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<ProductOrderWebapp> findByExample (ProductOrderWebappExample ProductOrderWebappExample) throws DataAccessException {
		return  (List<ProductOrderWebapp>) getSqlMapClientTemplate().queryForList("product_orders_temp_webapp.selectByExample",ProductOrderWebappExample);
	}

}
