package com.mobicart.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.mobicart.model.ProductOrderItemWebapp;
import com.mobicart.model.ProductOrderItemWebappExample;


public class ProductOrderItemWebappDAOImpl extends SqlMapClientDaoSupport implements ProductOrderItemWebappDAO {
	
	
	/**
	 * {@inheritDoc}
	 */
	public Long create(ProductOrderItemWebapp newInstance) throws DataAccessException {
		return (Long) getSqlMapClientTemplate().insert("product_order_items_temp_webapp.insert", newInstance);

	}
	/**
	 * {@inheritDoc}
	 */
	public ProductOrderItemWebapp find(Long id) throws DataAccessException  {
		ProductOrderItemWebappExample example=new ProductOrderItemWebappExample();
		example.createCriteria().andIdEqualTo(id);
		return (ProductOrderItemWebapp)getSqlMapClientTemplate().queryForObject("product_order_items_temp_webapp.selectByExample", example);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<ProductOrderItemWebapp> findAll() throws DataAccessException  {
		return (List<ProductOrderItemWebapp>) getSqlMapClientTemplate().queryForList("product_order_items_temp_webapp.selectAll", null);
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(ProductOrderItemWebapp transientInstance) throws DataAccessException  {
			getSqlMapClientTemplate().update("product_order_items_temp_webapp.updateByPrimaryKeySelective", transientInstance);

	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(ProductOrderItemWebapp persistentInstance) throws DataAccessException  {
			getSqlMapClientTemplate().delete("product_order_items_temp_webapp.deleteByPrimaryKeySelective", persistentInstance);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<ProductOrderItemWebapp> findByExample (ProductOrderItemWebappExample productOrderItemWebappExample) throws DataAccessException{
		return (List<ProductOrderItemWebapp>) getSqlMapClientTemplate().queryForList("product_order_items_temp_webapp.selectByExample",productOrderItemWebappExample);
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Long> findOrderIdsByProductId(Long productId) throws DataAccessException {
		Map<String,Object> paramMap=new HashMap<String, Object>();
		paramMap.put("productId", productId);	
		return (List<Long>) getSqlMapClientTemplate().queryForList("product_order_items_temp_webapp.selectOrderIdsByProductId", paramMap);
	}
}
