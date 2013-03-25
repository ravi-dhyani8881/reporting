/**
 * 
 */
package com.mobicart.dao;

import java.util.List;

import com.mobicart.model.Order;
import com.mobicart.model.OrderExample;

/**
 * @author jasdeep.singh
 *
 */
public interface OrderDAO extends DAO<Order, Long> {

	List<Order> findByExample(OrderExample orderExample);
	
	List<Order> findByKeywordExample(OrderExample orderExample);
	
	long findCountByExample(OrderExample orderExample);
	
	List<Order> findByKeywordExample(OrderExample orderExample,Integer skipResults,Integer maxResults);
	
}
