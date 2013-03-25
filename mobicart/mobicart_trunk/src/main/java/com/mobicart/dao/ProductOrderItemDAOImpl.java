package com.mobicart.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.mobicart.model.ProductOrderItem;
import com.mobicart.model.ProductOrderItemExample;


public class ProductOrderItemDAOImpl extends SqlMapClientDaoSupport implements ProductOrderItemDAO {
	
	
	
	public Long create(ProductOrderItem newInstance) throws DataAccessException {
		return (Long) getSqlMapClientTemplate().insert("product_order_items.insert", newInstance);

	}

	public ProductOrderItem find(Long id) throws DataAccessException  {
		ProductOrderItemExample example=new ProductOrderItemExample();
		example.createCriteria().andIdEqualTo(id);
		return (ProductOrderItem)getSqlMapClientTemplate().queryForObject("product_order_items.selectByExample", example);
	}

	@SuppressWarnings("unchecked")
	public List<ProductOrderItem> findAll() throws DataAccessException  {
		return (List<ProductOrderItem>) getSqlMapClientTemplate().queryForList("product_order_items.selectAll", null);
	}

	public void update(ProductOrderItem transientInstance) throws DataAccessException  {
			getSqlMapClientTemplate().update("product_order_items.updateByPrimaryKeySelective", transientInstance);

	}

	public void delete(ProductOrderItem persistentInstance) throws DataAccessException  {
			getSqlMapClientTemplate().delete("product_order_items.deleteByPrimaryKeySelective", persistentInstance);
	}

	@SuppressWarnings("unchecked")
	public List<ProductOrderItem> findByExample (ProductOrderItemExample productOrderItemExample) throws DataAccessException{
		return (List<ProductOrderItem>) getSqlMapClientTemplate().queryForList("product_order_items.selectByExample",productOrderItemExample);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Long> findOrderIdsByProductId(Long productId) throws DataAccessException {
		Map<String,Object> paramMap=new HashMap<String, Object>();
		paramMap.put("productId", productId);	
		return (List<Long>) getSqlMapClientTemplate().queryForList("product_order_items.selectOrderIdsByProductId", paramMap);
	}
}
