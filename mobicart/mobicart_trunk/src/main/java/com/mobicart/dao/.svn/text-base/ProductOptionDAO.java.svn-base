package com.mobicart.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.mobicart.model.ProductOption;
import com.mobicart.model.ProductOptionExample;

public interface ProductOptionDAO extends GenericDAO<ProductOption, Long> {
	
	public List<ProductOption> findByExample(ProductOptionExample productOptionExample) throws DataAccessException;
	
	public Integer findUniqueTitleCount(ProductOptionExample productOptionExample) throws DataAccessException;
	
	public Integer deleteByExample(ProductOptionExample productOptionExample) throws DataAccessException; 
	
}
