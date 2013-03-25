package com.mobicart.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.mobicart.model.ProductOrderShippingDetail;
import com.mobicart.model.ProductOrderShippingDetailExample;

public class ProductOrderShippingDetailDAOImpl extends SqlMapClientDaoSupport implements ProductOrderShippingDetailDAO {

	
	private static final String NAMESPACE="product_order_shipping_details.";
	private static final String STMT_INSERT=NAMESPACE + "insertSelective";
	private static final String STMT_UPDATE=NAMESPACE + "updateByPrimaryKeySelective";
	private static final String STMT_DELETE=NAMESPACE + "deleteByPrimaryKey";
	private static final String STMT_LOAD_BY_ID=NAMESPACE + "selectByPrimaryKey";
	private static final String STMT_LOAD_ALL=NAMESPACE + "selectAll";
	private static final String STMT_LOAD_BY_EXAMPLE=NAMESPACE + "selectByExampleWithBLOBs";

	
	public Long create(ProductOrderShippingDetail newInstance)
			throws DataAccessException {
		return (Long)getSqlMapClientTemplate().insert(STMT_INSERT ,newInstance);
		
	}

	public ProductOrderShippingDetail find(Long id) throws DataAccessException {
		return (ProductOrderShippingDetail)getSqlMapClientTemplate().queryForObject(STMT_LOAD_BY_ID ,new ProductOrderShippingDetail(id));
	}

	@SuppressWarnings("unchecked")
	public List<ProductOrderShippingDetail> findAll()
			throws DataAccessException {
		return (List<ProductOrderShippingDetail>)getSqlMapClientTemplate().queryForList(STMT_LOAD_ALL );
	}

	public void update(ProductOrderShippingDetail transientInstance)
			throws DataAccessException {
		getSqlMapClientTemplate().update(STMT_UPDATE ,transientInstance);
	}

	public void delete(ProductOrderShippingDetail persistentInstance)
			throws DataAccessException {
		getSqlMapClientTemplate().update(STMT_DELETE ,persistentInstance);
	}

	public ProductOrderShippingDetail findByExample(
			ProductOrderShippingDetailExample productOrderShippingDetailExample)
			throws DataAccessException {
		return (ProductOrderShippingDetail)getSqlMapClientTemplate().queryForObject(STMT_LOAD_BY_EXAMPLE ,productOrderShippingDetailExample);
	}

	
}