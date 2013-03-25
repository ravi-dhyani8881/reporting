package com.mobicart.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.mobicart.dto.SubDepartmentDto;
import com.mobicart.model.Category;
import com.mobicart.model.CategoryExample;
import com.mobicart.util.Pager;

public interface CategoryDAO extends GenericDAO<Category,Long> {

	/**
	 * Find by example
	 * @param categoryExample
	 * @return
	 * @throws DataAccessException
	 */
	List<Category> findByExample(CategoryExample categoryExample) throws DataAccessException;
	
	/**
	 * Find sucbdepartmentDao by example
	 * @param categoryExample
	 * @param pager
	 * @return
	 * @throws DataAccessException
	 */
	List<SubDepartmentDto> findByExample(CategoryExample categoryExample,Pager pager) throws DataAccessException;
}
