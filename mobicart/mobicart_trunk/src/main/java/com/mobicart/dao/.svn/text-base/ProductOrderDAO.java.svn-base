package com.mobicart.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.mobicart.model.ProductOrder;
import com.mobicart.model.ProductOrderExample;
import com.mobicart.model.api.ProductOrderApi;
import com.mobicart.util.Pager;

public interface ProductOrderDAO extends GenericDAO<ProductOrder,Long>{
	
	List<ProductOrder> findByExample(ProductOrderExample productOrderExample) throws DataAccessException;
	
	List<ProductOrder> findByExampleWithPaging(ProductOrderExample productOrderExample, Pager pager) throws DataAccessException;

	/***************   API METHODS ****************/
	ProductOrder apiFind(Long orderId) throws DataAccessException;

	List<ProductOrder> findAPIByExample(ProductOrderExample productOrderExample) throws DataAccessException;
	
	
	ProductOrderApi findForApi(long orderId);

	List<ProductOrderApi> findByExampleForApi(ProductOrderExample productOrderExample);
	
	Integer findCountByExample(ProductOrderExample productOrderExample)throws DataAccessException;

	
}
