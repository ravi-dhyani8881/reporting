package com.mobicart.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.mobicart.model.ProductOrderItemWebapp;
import com.mobicart.model.ProductOrderItemWebappExample;

public interface ProductOrderItemWebappDAO extends GenericDAO<ProductOrderItemWebapp, Long> {
	/**
	 * find by example
	 * @param productOrderItemExample
	 * @return
	 * @throws DataAccessException
	 */
	List<ProductOrderItemWebapp> findByExample(ProductOrderItemWebappExample productOrderItemExample) throws DataAccessException;
	
	/**
	 * find order ids by product id
	 * @param productId
	 * @return
	 * @throws DataAccessException
	 */
	List<Long> findOrderIdsByProductId(Long productId) throws DataAccessException;
}	
