package com.mobicart.dao;


import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.mobicart.dto.ProductDto;
import com.mobicart.model.Product;
import com.mobicart.model.ProductExample;
import com.mobicart.model.ProductFilter;
import com.mobicart.util.Pager;

public class ProductDAOImpl extends SqlMapClientDaoSupport implements ProductDAO {
	
	/**
	 * {@inheritDoc}
	 */
	public Long create(Product newInstance) throws DataAccessException {
			return (Long) getSqlMapClientTemplate().insert("products.insertSelective", newInstance);
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(Product persistentObject)  throws DataAccessException {
			getSqlMapClientTemplate().delete("products.deleteByPrimaryKey", persistentObject);
	}

	/**
	 * {@inheritDoc}
	 */
	public Product find(Long id) throws DataAccessException{
			return (Product) getSqlMapClientTemplate().queryForObject("products.selectByPrimaryKey", new Product(id) );
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Product> findAll() throws DataAccessException{
			return getSqlMapClientTemplate().queryForList("products.selectAll", null);
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(Product transientObject) throws DataAccessException{
			getSqlMapClientTemplate().update("products.updateByPrimaryKeySelective",transientObject);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Product> findByExample(ProductExample productExample) throws DataAccessException{
			return getSqlMapClientTemplate().queryForList("products.selectByExampleWithBLOBs", productExample);
	}
	@SuppressWarnings("unchecked")
	public List<Product> findByFeaturedProductByAppAndMaxlimit(Product  product ) throws DataAccessException{
			return getSqlMapClientTemplate().queryForList("products.selectFeaturedProductByAppIdAndMaxRowNum", product);
	}
	
	public Long countByExample(ProductExample productExample ) throws DataAccessException{
		return (Long)getSqlMapClientTemplate().queryForObject("products.selectCountByExampleWithBLOBs", productExample);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Product> findByExampleWithPaging(ProductExample productExample, Pager pager) throws DataAccessException{
			return getSqlMapClientTemplate().queryForList("products.selectByExampleWithBLOBs", productExample, pager.getPage(),pager.getResults());
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int findCountByExample(ProductExample productExample)
			throws DataAccessException {
		return (Integer) getSqlMapClientTemplate().queryForObject("products.countByExample", productExample);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Product> findByExample(ProductExample productExample,
			Pager pager) throws DataAccessException {
				return getSqlMapClientTemplate().queryForList("products.selectByExampleWithBLOBs", productExample,pager.getPage(),pager.getResults() );
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Product> selectPurchasedProductsWithPaging(ProductExample productExample,
			Pager pager) throws DataAccessException {
				return getSqlMapClientTemplate().queryForList("products.selectPurchasedProductsWithPaging", productExample,pager.getPage(),pager.getResults() );
	}
	
	@SuppressWarnings("unchecked")
	public List<ProductDto> findPreviewByExample(
			ProductExample productExample, Pager pager)
			throws DataAccessException {
		
		return getSqlMapClientTemplate().queryForList("products.selectPreviewByExample", productExample,pager.getPage(),pager.getResults() );
		//return getSqlMapClientTemplate().queryForList("products.selectProductList", productExample,pager.getPage(),pager.getResults() );
		//return getSqlMapClientTemplate().queryForList("products.selectPreviewByExample", productExample );
	}

	/*@SuppressWarnings("unchecked")
	@Deprecated
	public  List<ProductDto> findFeaturedByStoreAndMaxlimit(Product product) throws DataAccessException {
		
		
		return getSqlMapClientTemplate().queryForList("products.selectFeaturedProductByStoreIdAndMaxRowNum", product);
		//return getSqlMapClientTemplate().queryForList("products.selectPreviewByExample", productExample,pager.getPage(),pager.getResults() );	
		
	}*/
	
	@SuppressWarnings("unchecked")
	public  List<ProductDto> findPreviewByDepartmentAndMaxRowNum(Product product) throws DataAccessException {
		
		
		return getSqlMapClientTemplate().queryForList("products.selectPreviewByDepartmentAndMaxRowNum", product);
		//return getSqlMapClientTemplate().queryForList("products.selectPreviewByExample", productExample,pager.getPage(),pager.getResults() );	
		
	}
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Product> findActiveByExample(ProductExample productExample) throws DataAccessException{
			return getSqlMapClientTemplate().queryForList("products.selectActiveByExample", productExample);
	}

	
	
	@SuppressWarnings("unchecked")
	public List<Product> findActiveByExample(ProductExample productExample,
			Pager pager) throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("products.selectActiveByExample", productExample,pager.getPage(),pager.getResults());
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Product> findFeaturedByAppId(ProductExample productExample) throws DataAccessException {
			return getSqlMapClientTemplate().queryForList("products.selectFeatured", productExample);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Product> findProductsByDepartmentAndMaxRowNum(Product product) throws DataAccessException {
			return getSqlMapClientTemplate().queryForList("products.selectByDepartmentAndMaxRowNum", product);
	}
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Product> findByFilteredExample(ProductFilter productFilter,
			ProductExample productExample) throws DataAccessException{
			return getSqlMapClientTemplate().queryForList("products.selectByExampleWithBLOBs", productExample,productFilter.getSkipResults(),productFilter.getMaxResults());
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Product> findPurchasedProducts(Long userId) throws DataAccessException{
			return getSqlMapClientTemplate().queryForList("products.selectPurchasedByUserId", userId);

	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Product> findPurchasedProductsWithPagination(Long userId,Pager pager) throws DataAccessException{
		return getSqlMapClientTemplate().queryForList("products.selectPurchasedByUserId", userId,pager.getLowerLimit(),pager.getUpperLimit());
	}
	
	/**********************API Methods************************/

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Product> findAPIByExample(ProductExample productExample) throws DataAccessException{
			return getSqlMapClientTemplate().queryForList("products.apiSelectByExampleWithBLOBs", productExample);
	}
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Product> findAPIByExample(ProductExample productExample,Pager pager) throws DataAccessException{
			return getSqlMapClientTemplate().queryForList("products.apiSelectByExampleWithBLOBs", productExample,pager.getPage(),pager.getResults());
	}
	/**
	 * {@inheritDoc}
	 */
	public Product apiFind(Long productId) throws DataAccessException{
			return (Product) getSqlMapClientTemplate().queryForObject("products.selectByPrimaryKey", new Product(productId) );
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Product> findAPIByExampleByPage(ProductExample productExample, Integer page, Integer pageSize) throws DataAccessException{
			return getSqlMapClientTemplate().queryForList("products.apiSelectByExampleWithBLOBs", productExample, page, pageSize);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> findStoreProductsWithPagination(Long storeId,Pager pager) throws Exception{
		List<Product> list = null;
		/**
		 * call stored procedure
		 */
    	Map<String,Object> returnParam = new HashMap<String, Object>();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("txtSearch", pager.getKeyword()!=null?"%"+pager.getKeyword()+"%":"%%");
			paramMap.put("sortOrder", pager.getSortOrder());
			paramMap.put("sortBy", pager.getSortBy());
			paramMap.put("storeId", storeId);
			paramMap.put("lowerLimit",pager.getLowerLimit());
			paramMap.put("upperLimit",pager.getUpperLimit());
			
			list = getSqlMapClientTemplate().queryForList("products.searchStoreProducts",paramMap);
			 
			returnParam.put("productList",list);
			returnParam.put("count",paramMap.get("totalCount"));			
			paramMap = null;
	
		return returnParam;
	}
	@Override
	public String findCountryCodeByCode(String code) {
		// TODO Auto-generated method stub
		 return (String)getSqlMapClientTemplate().queryForObject("products.findCountryCodeByCode",code);
	}
	@Override
	public String findTaxInfoByStoreId(Long storeId) {
		  
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", storeId);
		paramMap.put("result", storeId);
		 
		 
	     String  result=null;
		 try{
		    result=(String)getSqlMapClientTemplate().queryForObject("products.findTaxInfoByStoreId",paramMap);
		 }
		 catch (Exception e) {
			e.printStackTrace();
		}
		  
		
		
		 
		 
		 return   result;
	}
	public String HelloDao(){
		
		return "Hello From:"+this.toString();
	}
	@Override
	public String findTaxInfoByStoreId_Func(Long storeId) {
		 return (String)getSqlMapClientTemplate().queryForObject("products.findTaxByStoreId_fun",storeId);
	}

	@Override
	public int findProductCountByDepartmentId(Long department_id) {
		
		return  (Integer)getSqlMapClientTemplate().queryForObject("products.selectProductCountByDepartment",department_id);
	}
    
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductDto> findPreviewByExample(long departmentId, Pager pager)
			throws DataAccessException {
		 
		return (List<ProductDto>)getSqlMapClientTemplate().queryForList("products.selectProductList", departmentId,pager.getPage(),pager.getResults());
	}
	
}
