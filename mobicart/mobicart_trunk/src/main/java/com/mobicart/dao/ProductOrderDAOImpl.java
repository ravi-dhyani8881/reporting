package com.mobicart.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.util.ClassUtils;

import com.mobicart.model.ProductExample;
import com.mobicart.model.ProductOrder;
import com.mobicart.model.ProductOrderExample;
import com.mobicart.model.api.ProductOrderApi;
import com.mobicart.util.DateTimeUtils;
import com.mobicart.util.Pager;



public class ProductOrderDAOImpl extends SqlMapClientDaoSupport implements ProductOrderDAO {

	
	public Long create(ProductOrder newInstance) throws DataAccessException {
			newInstance.setdOrderDate(DateTimeUtils.getCurrentTimestamp());
			return (Long) getSqlMapClientTemplate().insert("product_orders.insert", newInstance);
	}

	public ProductOrder find(Long id) throws DataAccessException  {
		ProductOrderExample example=new ProductOrderExample();
		example.createCriteria().andIdEqualTo(id);
		return (ProductOrder) getSqlMapClientTemplate().queryForObject("product_orders.selectByExample", example);
	}

	@SuppressWarnings("unchecked")
	public List<ProductOrder> findAll() throws DataAccessException  {
			return  (List<ProductOrder>) getSqlMapClientTemplate().queryForList("product_orders.selectAll", null);
	}

	public void update(ProductOrder transientInstance) throws DataAccessException  {
			getSqlMapClientTemplate().update("product_orders.updateByPrimaryKeySelective", transientInstance);
	}
	
	public Integer findCountByExample(ProductOrderExample productOrderExample)throws DataAccessException {
		return (Integer) getSqlMapClientTemplate().queryForObject("product_orders.countByExample", productOrderExample);
	}

	public void delete(ProductOrder persistentInstance) throws DataAccessException  {
			getSqlMapClientTemplate().delete("product_orders.deleteByPrimaryKeySelective", persistentInstance);
	}

	@SuppressWarnings("unchecked")
	public List<ProductOrder> findByExample (ProductOrderExample productOrderExample) throws DataAccessException {
		return  (List<ProductOrder>) getSqlMapClientTemplate().queryForList("product_orders.selectByExample",productOrderExample);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProductOrder> findByExampleWithPaging (ProductOrderExample productOrderExample, Pager pager) throws DataAccessException {
		return  (List<ProductOrder>) getSqlMapClientTemplate().queryForList("product_orders.selectByExample",productOrderExample,pager.getPage(),pager.getResults());
	}

	/**********************API Methods*********************/
	
	public ProductOrder apiFind(Long orderId) throws DataAccessException {
		ProductOrderExample example=new ProductOrderExample();
		example.createCriteria().andIdEqualTo(orderId);
		return (ProductOrder) getSqlMapClientTemplate().queryForObject("product_orders.apiSelectByExample", example);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProductOrder> findAPIByExample(ProductOrderExample productOrderExample) throws DataAccessException{
			return  (List<ProductOrder>) getSqlMapClientTemplate().queryForList("product_orders.apiSelectByExample", productOrderExample);
	}

	 
	public ProductOrderApi findForApi(long orderId) {
		ProductOrderApi productOrderApi=null;
			productOrderApi=(ProductOrderApi) getSqlMapClientTemplate().queryForObject("product_orders.selectByPrimaryKeyForApi", orderId);
			
			if(productOrderApi==null){
				throw new ObjectRetrievalFailureException(ClassUtils.getShortName(this.getClass()), orderId);
			}	
			
		return productOrderApi;
	}

	@SuppressWarnings("unchecked")
	public List<ProductOrderApi> findByExampleForApi(
			ProductOrderExample productOrderExample) {
		List<ProductOrderApi> productOrderApiList=null;
		
		productOrderApiList = (List<ProductOrderApi>) getSqlMapClientTemplate().queryForList("product_orders.selectByExampleForApi", productOrderExample);
		
		if(productOrderApiList==null){
			throw new ObjectRetrievalFailureException(ClassUtils.getShortName(this.getClass()), productOrderExample);
		}	
		return productOrderApiList;
	}
	
	

}
