package com.mobicart.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.mobicart.model.ProductOption;
import com.mobicart.model.ProductOptionExample;

public class ProductOptionDAOImpl extends SqlMapClientDaoSupport implements ProductOptionDAO {

	
	public Long create(ProductOption newInstance) throws DataAccessException {
		return (Long) getSqlMapClientTemplate().insert("product_options.insert", newInstance);
	}

	public ProductOption find(Long id) throws DataAccessException {
			return (ProductOption) getSqlMapClientTemplate().queryForObject("product_options.selectByPrimaryKey", new ProductOption(id));
	}

	@SuppressWarnings("unchecked")
	public List<ProductOption> findAll()throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("product_options.selectAll", null);
	}

	public void update(ProductOption transientObject) throws DataAccessException{
		getSqlMapClientTemplate().update("product_options.updateByPrimaryKeySelective",transientObject);
	}

	public void delete(ProductOption persistentObject) throws DataAccessException{
			getSqlMapClientTemplate().delete("product_options.deleteByPrimaryKey",persistentObject);
		
	}

	@SuppressWarnings("unchecked")
	public List<ProductOption> findByExample (
			ProductOptionExample productOptionExample) throws DataAccessException {
			return getSqlMapClientTemplate().queryForList("product_options.selectByExample", productOptionExample);
	}

	public Integer deleteByExample(ProductOptionExample productOptionExample) throws DataAccessException {
			return getSqlMapClientTemplate().delete("product_options.deleteByExample", productOptionExample);
		
	}

	
	public Integer findUniqueTitleCount(ProductOptionExample productOptionExample)
			throws DataAccessException {
		return (Integer) getSqlMapClientTemplate().queryForObject("product_options.selectUniqueTitleCountByExample", productOptionExample);
	}
	
	
}
