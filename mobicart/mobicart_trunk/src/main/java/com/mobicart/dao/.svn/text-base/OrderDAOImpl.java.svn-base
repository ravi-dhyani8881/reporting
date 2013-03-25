package com.mobicart.dao;
import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.util.ClassUtils;

import com.mobicart.model.Order;
import com.mobicart.model.OrderExample;


/**
 * @author jasdeep.singh
 *
 */
public class OrderDAOImpl extends SqlMapClientDaoSupport  implements OrderDAO {

	public Long create(Order newInstance) {
			Long id=(Long) getSqlMapClientTemplate().insert("orders.insert",newInstance);
			
			return id;
	}

	public Order find(Long id) {
		Order order=null;
		order=(Order) getSqlMapClientTemplate().queryForObject("orders.selectByPrimaryKey", new Order(id));
		if(order==null){
				throw new ObjectRetrievalFailureException(ClassUtils.getShortName(this.getClass()), id);
		}
		return order;
		
	}

	@SuppressWarnings("unchecked")
	public List<Order> findAll() {
		List<Order> orders=null;
			orders=getSqlMapClientTemplate().queryForList("orders.selectAll", null);
		return orders;
	}

	public void update(Order transientObject) {
			getSqlMapClientTemplate().update("orders.updateByPrimaryKeySelective",transientObject);
	}

	
	public void delete(Order persistentObject) {
		getSqlMapClientTemplate().update("orders.deleteByPrimaryKey",persistentObject);

	}


	@SuppressWarnings("unchecked")
	public List<Order> findByExample(OrderExample orderExample) {
		List<Order> orders = null;
		orders = getSqlMapClientTemplate().queryForList("orders.selectByExample", orderExample);
		if (orders == null) {
			throw new ObjectRetrievalFailureException(ClassUtils.getShortName(this.getClass()), orderExample);
		}
		return orders;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Order> findByKeywordExample(OrderExample orderExample) {
		List<Order> orders = null;
			orders = getSqlMapClientTemplate().queryForList("orders.selectByExample",orderExample);
			if (orders == null) {
				throw new ObjectRetrievalFailureException(ClassUtils.getShortName(this.getClass()), orderExample);
			}
		return orders;
	}

	@SuppressWarnings("unchecked")
	public List<Order> findByKeywordExample(OrderExample orderExample,
			Integer skipResults, Integer maxResults) {
			List<Order> orders = null;
			orders = getSqlMapClientTemplate().queryForList("orders.selectByExample",
					orderExample,skipResults,maxResults);
			if (orders == null) {
				throw new ObjectRetrievalFailureException(ClassUtils.getShortName(this.getClass()), orderExample);
			}

		return orders;

	}

	public long findCountByExample(OrderExample orderExample) {
		return (Long) getSqlMapClientTemplate().queryForObject("orders.selectCountByExample",orderExample);
	}
	
	

}
