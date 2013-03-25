package com.mobicart.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.mobicart.dto.SubDepartmentDto;
import com.mobicart.model.Category;
import com.mobicart.model.CategoryExample;
import com.mobicart.util.Pager;

public class CategoryDAOImpl extends SqlMapClientDaoSupport implements CategoryDAO {

	/**
	 * {@inheritDoc}
	 * 
	 */
	public Long create(Category newInstance) throws DataAccessException {
			//return (Long) getSqlMapClientTemplate().insert("categories.insert", newInstance);
		return null;
	
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	public void delete(Category persistentObject) throws DataAccessException {
			//getSqlMapClientTemplate().delete("categories.deleteByPrimaryKey", persistentObject);
	
	
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	public Category find(Long id) throws DataAccessException {
			//return (Category) getSqlMapClientTemplate().queryForObject("categories.selectByPrimaryKey", new Category(id));
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Category> findAll() throws DataAccessException{
			//return 	getSqlMapClientTemplate().queryForList("categories.selectAll",null );
	
		return null;
	}

	
	/**
	 * {@inheritDoc}
	 * 
	 */
	public void update(Category transientObject) throws DataAccessException{
			//getSqlMapClientTemplate().update("categories.updateByPrimaryKeySelective",transientObject);
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Category> findByExample(CategoryExample categoryExample) throws DataAccessException{
		return null;
		//return getSqlMapClientTemplate().queryForList("categories.selectByExample", categoryExample);
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<SubDepartmentDto> findByExample(CategoryExample categoryExample, Pager pager)
			throws DataAccessException {
		    
		  return getSqlMapClientTemplate().queryForList("categories.selectPreviewByExample", categoryExample,pager.getPage(),pager.getResults());
	//return null;
	
	}

	
}
