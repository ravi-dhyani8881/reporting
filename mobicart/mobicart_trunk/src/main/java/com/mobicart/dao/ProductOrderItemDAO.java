package com.mobicart.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.mobicart.model.ProductOrderItem;
import com.mobicart.model.ProductOrderItemExample;

public interface ProductOrderItemDAO extends GenericDAO<ProductOrderItem, Long> {

	List<ProductOrderItem> findByExample(ProductOrderItemExample productOrderItemExample) throws DataAccessException;	
	
	List<Long> findOrderIdsByProductId(Long productId) throws DataAccessException;

}	
