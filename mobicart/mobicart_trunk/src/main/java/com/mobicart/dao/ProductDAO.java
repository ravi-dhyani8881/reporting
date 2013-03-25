package com.mobicart.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mobicart.dto.ProductDto;
import com.mobicart.model.Product;
import com.mobicart.model.ProductBean;
import com.mobicart.model.ProductExample;
import com.mobicart.model.ProductFilter;
import com.mobicart.model.Store;
import com.mobicart.model.User;
import com.mobicart.util.Pager;

public interface ProductDAO extends GenericDAO<Product, Long> {

	
	/**
	 * Find products by {@link ProductExample}
	 * @param productExample {@link ProductExample} instance
	 * @return List of {@link Product} instance
	 * @throws DataAccessException 
	 */
	List<Product> findByExample(ProductExample productExample ) throws DataAccessException;
	
	/**
	 * Find products by {@link ProductExample}
	 * @param productExample {@link ProductExample} instance
	 * @return List of {@link Product} instance
	 * @throws DataAccessException 
	 */
	Long countByExample(ProductExample productExample ) throws DataAccessException;
	
	/**
	 * Find products by {@link ProductExample}
	 * @param productExample {@link ProductExample} instance
	 * @return List of {@link Product} instance
	 * @throws DataAccessException 
	 */
	List<Product> findProductsByDepartmentAndMaxRowNum(Product pro);

	/**
	 * Find products by {@link ProductExample}
	 * @param productExample {@link ProductExample} instance
	 * @return List of {@link Product} instance
	 * @throws DataAccessException 
	 */
	List<Product> findByExampleWithPaging(ProductExample productExample, Pager pager) throws DataAccessException;
	
	/**
	 * Count of total Product found bye {@link ProductExample} 
	 * @param productExample {@link ProductExample} instance
	 * @return count 
	 * @throws DataAccessException 
	 */
	int findCountByExample(ProductExample productExample ) throws DataAccessException;
	
	
	/**
	 * Find products by {@link ProductExample} paginated
	 * @param productExample {@link ProductExample} instance
	 * @param pager {@link Pager} instance
	 * @return List of {@link Product} instance
	 * @throws DataAccessException
	 */
	List<Product> findByExample(ProductExample productExample,Pager pager) throws DataAccessException ;
	
	
	
	/**
	 * Find products by {@link ProductExample} paginated
	 * @param productExample {@link ProductExample} instance
	 * @param pager {@link Pager} instance
	 * @return List of {@link ProductBean} instance
	 * @throws DataAccessException
	 */
	List<ProductDto> findPreviewByExample(ProductExample productExample,Pager pager) throws DataAccessException ;
	
	
	
	
	List<ProductDto> findPreviewByDepartmentAndMaxRowNum(Product pro);
	
	/**
	 * Find products by {@link ProductExample} paginated
	 * @param productExample {@link ProductExample} instance
	 * @return List of {@link Product} instance
	 * @throws DataAccessException 
	 */
	List<Product> findActiveByExample(ProductExample productExample,Pager pager) throws DataAccessException ;

	
	/**
	 * Find products by {@link ProductExample}
	 * @param productExample {@link ProductExample} instance
	 * @return List of {@link Product} instance
	 * @throws DataAccessException 
	 */
	List<Product> findActiveByExample(ProductExample productExample) throws DataAccessException ;

	/**
	 * 
	 * Find featured products by {@link ProductExample} 
	 * @param productExample {@link ProductExample} instance
	 * @return List of {@link Product} instance
	 * @throws DataAccessException
	 */
	List<Product> findFeaturedByAppId(ProductExample productExample) throws DataAccessException;
	
	/**
	 * Find products by {@link ProductExample} and {@link ProductFilter}
	 * @param productFilter
	 * @param productExample
	 * @return List of {@link Product} instance
	 * @throws DataAccessException
	 */
	
	List<Product> findByFilteredExample(ProductFilter productFilter,ProductExample productExample ) throws DataAccessException;

	/**
	 * Find purchased products
	 * @param userId {@link User} id
	 * @return List of {@link Product} instances
	 * @throws DataAccessException
	 */
	List<Product> findPurchasedProducts(Long userId) throws DataAccessException;
	
	/**
	 * Find purchased products with pagination
	 * @param userId {@link User} id
	 * @param pager
	 * @return List of {@link Product} instances
	 * @throws DataAccessException
	 */
	List<Product> findPurchasedProductsWithPagination(Long userId,Pager pager) throws DataAccessException;
	
	/**
	 * Find purchased products with pagination
	 * @param userId {@link User} id
	 * @param pager
	 * @return List of {@link Product} instances
	 * @throws DataAccessException
	 */
	public List<Product> selectPurchasedProductsWithPaging(ProductExample productExample,Pager pager) throws DataAccessException;
	
	/**
	 * Find products in a store paginated, make procedure call 
	 * @param storeId {@link Store} id
	 * @param pager {@link Pager} instance
	 * @return Map of product Listing and totalCount
	 * @throws Exception
	 */
	public Map<String, Object> findStoreProductsWithPagination(Long storeId,Pager pager) throws Exception;


	/**
	 * Find products for API
	 * @param productExample {@link ProductExample} instance
	 * @return  List of {@link Product} instances
	 * @throws DataAccessException
	 */

	List<Product> findAPIByExample(ProductExample productExample) throws DataAccessException;
	/**
	 * Find products for API
	 * @param productExample {@link ProductExample} instance
	 * @return  List of {@link Product} instances
	 * @throws DataAccessException
	 */

	List<Product> findAPIByExample(ProductExample productExample,Pager pager) throws DataAccessException;

	/**
	 * Find {@link Product}  for API 
	 * @param productId {@link Product} id
	 * @return {@link Product} instance
	 * @throws DataAccessException
	 */
	Product apiFind(Long productId) throws DataAccessException;
	
	/**
	 * Find products by {@link ProductExample} paginated for API 
	 * @param productExample {@link ProductExample} instanfe
	 * @param page initial value int
	 * @param pageSize max limit int
	 * @return   List of {@link Product} instances 
	 * @throws DataAccessException 
	 */
	List<Product> findAPIByExampleByPage(ProductExample productExample, Integer page, Integer pageSize) throws DataAccessException;
	 
	String  findCountryCodeByCode(String code);
	String findTaxInfoByStoreId(Long storeId);
	String findTaxInfoByStoreId_Func(Long storeId);
	int findProductCountByDepartmentId(Long department_id);
	List<ProductDto> findPreviewByExample(long departmentId,Pager pager) throws DataAccessException ;
	public List<Product> findByFeaturedProductByAppAndMaxlimit(Product  product ) throws DataAccessException;
	//public  List<ProductDto> findFeaturedByStoreAndMaxlimit(Product product) throws DataAccessException;

}
