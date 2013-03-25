package com.mobicart.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mobicart.bo.ProductResponse;
import com.mobicart.dto.DepartmentDto;
import com.mobicart.dto.NewsDto;
import com.mobicart.model.Address;
import com.mobicart.model.Api;
import com.mobicart.model.App;
import com.mobicart.model.Category;
import com.mobicart.model.Department;
import com.mobicart.model.GalleryImage;
import com.mobicart.model.News;
import com.mobicart.model.Product;
import com.mobicart.model.ProductExample;
import com.mobicart.model.ProductFilter;
import com.mobicart.model.ProductImage;
import com.mobicart.model.ProductOption;
import com.mobicart.model.ProductOrder;
import com.mobicart.model.ProductOrderItem;
import com.mobicart.model.ProductOrderShippingDetail;
import com.mobicart.model.ProductReview;
import com.mobicart.model.Shipping;
import com.mobicart.model.State;
import com.mobicart.model.StaticPage;
import com.mobicart.model.Store;
import com.mobicart.model.Tax;
import com.mobicart.model.Territory;
import com.mobicart.model.User;
import com.mobicart.model.api.CategoryApi;
import com.mobicart.model.api.DepartmentApi;
import com.mobicart.model.api.ProductOrderApi;
import com.mobicart.util.Pager;
import com.mobicart.util.exception.ApiKeyGenerationException;
import com.mobicart.util.exception.CategoriesExistInDepartmentException;
import com.mobicart.util.exception.DuplicateDepartmentException;
import com.mobicart.util.exception.ProductsExistInCategoryException;
import com.mobicart.util.exception.ProductsExistInDepartmentException;
import com.mobicart.web.security.oauth.ConsumerSecretGenerationException;


public interface StoreService {
    
	
	/**
     * Creates new store
     * @param store {@link Store} instance
     * @return long value which is id of created store
     * @throws Exception
     */
	public Long createStore(Store store) throws Exception;
	
	
	/**
	 * Update an existing store
	 * @param store {@link Store} instance
	 * @return  {@link Store} updated instance
	 * @throws Exception
	 */
	public Store updateStore(Store store)  throws Exception;
	/**
	 * Find a store by user id
	 * @param userId
	 * @return  {@link Store} instance 
	 * @throws Exception
	 */
	public Store findStoreByUserId(Long userId) throws Exception;
	/**
	 * Find List of all store by user id
	 * @param userId
	 * @return list {@link List} of store {@link Store}}
	 * @throws Exception
	 */
	public List<Store> findStoresByUserId(Long userId) throws Exception ;
	/**
	 * Find store by its id
	 * @param storeId
	 * @return {@link Store} instance
	 * @throws Exception
	 */
	public Store findStoreById(Long storeId) throws Exception;
	/**
	 * Find territory by id
	 * @param id
	 * @return {@link Territory} instance  
	 */
	public Territory findTerritoryById(Long id);
	/**
	 * Create a new department
	 * @param department {@link Department} instance 
	 * @return id of created department.
	 * @throws DuplicateDepartmentException 
	 */
	public Long createDepartment(Department department) throws DuplicateDepartmentException ;

	/**
	 * create department for ajax call
	 * @param department
	 * @return
	 */
	public String createDepartmentAjax(Department department);
	/**
	 * update department
	 * @param transientInstance
	 * @return
	 */
	public Department updateDepartment(Department transientInstance);
	/**
	 *  Deletes an department
	 * @param department
	 * @throws CategoriesExistInDepartmentException Cate
	 * @throws ProductsExistInDepartmentException
	 */
	public void deleteDepartment(Department department) throws CategoriesExistInDepartmentException,ProductsExistInDepartmentException;
	
	/**
	 * find department
	 * @param departmentId
	 * @return
	 */
	public Department findDepartment(Long departmentId);
	
	
	public Department findDepartmentForApi(Long departmentId);
	
	/**
	 * find department by store and name
	 * @param storeId
	 * @param departmentName
	 * @return
	 */
	public Department findDepartmentByStoreAndName(Long storeId,String departmentName);
	
	/**
	 * create subdepartment
	 * @param category
	 * @return
	 * @throws Exception
	 */
	public Long createCategory(Category category) throws Exception;
	
	/**
	 * update subdepartment
	 * @param transientInstance
	 * @return
	 * @throws Exception
	 */
	public Category updateCategory(Category transientInstance)  throws Exception ;
	
	
	/**
	 * delet sub department
	 * @param category
	 * @throws ProductsExistInCategoryException
	 * @throws Exception
	 */
	public void deleteCategory(Category category) throws ProductsExistInCategoryException,Exception;
	
	/**
	 * find sub department
	 * @param categoryId
	 * @return
	 * @throws Exception
	 */
	public Category findCategory(Long categoryId) throws Exception;
	
	/**
	 * find departments by store 
	 * @param storeId
	 * @return
	 */
	public List<Department> findDepartmentsByStore(Long storeId) ;
	
	/**
	 * find active department s by store
	 * @param storeId
	 * @return
	 */
	public List<Department> findActiveDepartmentsByStore(Long storeId);
	
	

	/**
	 * find active departments by store for inter
	 * @param storeId
	 * @return
	 */
	public List<Department> findActiveDepartmentsByStoreWithoutSubdepartments(Long storeId);
	
	
	/**
	 * find sub departments within a department
	 * @param departmentId
	 * @return
	 */
	public List<Category> findCategoriesByDepartment(Long departmentId) ;
	
	/**
	 * find sub departments within a department
	 * @param departmentId
	 * @return
	 */
	public List<CategoryApi> findCategoriesByDepartmentForApi(Long departmentId) ;
	
	/**
	 * find active sub departments with in a department
	 * @param departmentId
	 * @return
	 */
	public List<Category> findActiveCategoriesByDepartment(Long departmentId) ;
	public List<Category> findActiveCategoriesByDepartment(Department department);
	public List<Product>  findFeaturedProductsByAppAndMaxlimit(Product product);
	/**
	 * find sub department withi a se of departments
	 * @param departments
	 * @return
	 */
	public List<Category> findCategoriesByDepartments(List<Department> departments) ;

	/**
	 * create new product in database 
	 * @param newInstance
	 * @return
	 */
	public Long createProduct(Product newInstance);
	
	/**
	 * Update product
	 * @param transientInstance
	 */
	public void updateProduct(Product transientInstance);
	
	/**
	 * delete product 
	 * @param transientInstance
	 */
	public void deleteProduct(Product transientInstance);
	
	/**
	 * deleat prodcut for a;pi calls
	 * @param transientInstance
	 */
	public void deleteProductAPI(Product transientInstance);

	/**
	 * find product by id
	 * @param productId
	 * @return
	 */
	public Product findProduct(Long productId);
	/**
	 * find product by id
	 * @param productId
	 * @return
	 */
	public List<Product> findProducts(List<Long> productId);

	
	/**
	 * find product count in a store
	 * @param storeId
	 * @return
	 */
	public int findProductCountByStore(Long storeId);

	
	/**
	 * find product count in a app
	 * @param appId
	 * @return
	 */
	public int findProductCountByApp(Long appId);

	/**
	 * find product in a store
	 * @param storeId
	 * @return
	 */
	public List<Product> findProductsByStore(Long storeId);
	
	/**
	 * find product in a store
	 * @param storeId
	 * @return
	 */
	public List<Product> findProductsByStore(Long storeId,Pager pager);

	/**
	 * find products in an app
	 * @param appId
	 * @return
	 */
	public List<Product> findProductsByApp(Long appId);
	
	/**
	 * find products in an app
	 * @param appId
	 * @return
	 */
	public List<Product> findProductsByAppWithPaging(Long appId, Pager pager);
	
	/**
	 * find products with an helper
	 * @param productFilter
	 * @return
	 */
	public List<Product> findProductsByFilter(ProductFilter productFilter);
	
	/**
	 * find pucrchased products
	 * @param userId
	 * @return
	 */
	public List<Product> findPurchasedProducts(Long userId);
	
	/**
	 * find purchased products with pagination 
	 * @param userId
	 * @param pager
	 * @return
	 */
	public List<Product> findPurchasedProductsWithPagination(Long userId,Pager pager);
	
	
	public List<Product> selectPurchasedProductsWithPaging(Long userId,Pager pager) throws DataAccessException;
	
	/**
	 * find featured products by app id
	 * @param appId
	 * @return
	 */
	public List<Product> findFeaturedProductsByApp(Long appId);
	
	/**
	 * find products by department
	 * @param departmentId
	 * @return
	 */
	public List<Product> findProductsByDepartment(Long departmentId);
	
	/**
	 * find products by department
	 * @param departmentId
	 * @return
	 */
 
	
	public List<Product> findProductsByDepartmentAndMaxRowNum(Product product);
 
	 
 
	
	/**
	 * find products by department
	 * @param departmentId
	 * @return
	 */
	public List<Product> findProductsDirectUnderDepartment(Long departmentId);
	
	
	/**
	 * find active products in a department
	 * @param departmentId {@link Department} id
	 * @return List of {@link Product} instances
	 */
	public List<Product> findActiveProductsByDepartment(Long departmentId); 
	
	/**
	 * find active products in a department paginated
	 * @param departmentId {@link Department} id
	 * @return {@link ProductResponse} instance 
	 */
	public ProductResponse  findActiveProductsByDepartment(Long departmentId,Pager pager); 

	
	/**
	 * 
	 * @param departmentId {@link } id
	 * @return {@link ProductResponse} instance 
	 */
	public List<Product>   findProductsByName(String productName,Long dptId);
	
	/**
	 * 
	 * @param departmentId {@link } id
	 * @return {@link ProductResponse} instance 
	 */
	public Long findProductsCountByDepartmentAndName(String productName,Long dptId);

	/**
	 * Find all Products by category
	 * @param categoryId {@link Category} id
	 * @return List of {@link Product} instances
	 */
	public List<Product> findProductsByCategory(Long categoryId);

	/**
	 * Find Active products in a sub-department
	 * @param categoryId {@link Category} id
	 * @return List of {@link Product} instances
	 */
	public List<Product> findActiveProductsByCategory(Long categoryId);


	/**
	 * Find Active products in a sub-department
	 * @param categoryId {@link Category} id
	 * @return {@link ProductResponse} instance
	 */
	public ProductResponse findActiveProductsByCategory(Long categoryId,Pager pager);
	/**
	 * Find Active products by user id
	 * @param userId
	 * @return List {@link List } of product {@link Product}
	 */
	public List<Product> findActiveProductsByUser(Long userId);
	
	/**
	 * Find products whose name are like {keyword} .
	 * @param appId  {@link Long}
	 * @param keyword {@link String}
	 * @return List {@link List} of product {@link Product}
	 */
	public List<Product> findProductsByKeywordSearch(Long appId,String keyword);
	/**
	 * Creates product image  
	 * @param productImage {@link ProductImage} instance
	 * @return id of created image.
	 */
	public Long createProductImage(ProductImage productImage);
  /**
   * 
   * @param List{@link List} of {@link ProductImage } instances
   * @return boolean ,true indicates all product images created and false indicate  failure. 
   */
	public boolean createProductImages( List<ProductImage> productImages );
  /**
   * Find product images by product id 
   * @param productId 
   * @return list {@link List} of product images {@link ProductImage}
    */
	public List<ProductImage> findProductImages(Long productId);
	/**
	 * Find product image by id
	 * @param id 
	 * @return  instance {@link ProductImage} 
	 */
	public ProductImage findProductImage(Long id);
	/**
	 * Update product images
	 * @param productImage  of type   {@link ProductImage}  
	 */
	public void updateProductImage(ProductImage productImage);
	/**
	 * Delete   productImage  instance of {@link ProductImage} 
	 * @param productImage of type {@link ProductImage).
	 */
	public void deleteProductImage(ProductImage productImage);
	/**
	 * Create product option 
	 * @param productOption instance of {@link ProductOption}
	 * @return id of created option
	 * @throws Exception
	 */
	public Long createProductOption(ProductOption productOption) throws Exception;
  /**
  * Update product option by  {@link ProductOption} productOption
  * @param product Option {@link ProductOption} productOption
  * @return updated instance {@link ProductOption}
  * @throws Exception
  */
	public ProductOption updateProductOption(ProductOption productOption) throws Exception;
	/**
	 * Find product option by id 
	 * @param id({@link Long}) (product id)
	 * @return instance {@link ProductOption} 
	 * @throws Exception
	 */
	public ProductOption findProductOption(Long id) throws Exception;
	/**
	 * Find product option by example 
	 * @param productOption({@link ProductOption}) (productOption)
	 * @return instance {@link ProductOption} 
	 * @throws Exception
	 */
	public List<ProductOption> findProductOptionByExample(ProductOption productOption) throws Exception;
	/**
	 * find list of product options by productId {@link Long}
	 * @param productId {@link Long}
	 * @return  list {@link List}  of {@link ProductOption}
	 * @throws Exception
	 */
	public List<ProductOption> findProductOptions(Long productId) throws Exception;
	/**
	 * check whether the product option have unique titles in valid limit(>=4). 
	 * @param productOption {@link ProductOption}
	 * @return boolean
	 * @throws Exception
	 */
	public boolean isProductOptionUniqueTitleLimitValid(ProductOption productOption) throws Exception;
   /**
    * Delete product option .
    * @param productOption {@link ProductOption}
    * @throws Exception
    */
	public void deleteProductOption(ProductOption productOption) throws Exception;
    /**
     * Find the list of gallery images.
     * @return list {@link List} of  {@link GalleryImage}
     */
	public List<GalleryImage> findAllGalleryImages();
   /**
    * Find gallery images by store id
    * @param storeId {@link Long}
    * @return list {@link List} of {@link GalleryImage}
    * 
    */
	public List<GalleryImage> findGalleryImagesByStore(Long storeId);
	/**
	 * Find gallery images by  appId
	 * @param userId {@link Long}
	 * @return list {@link List } of {@link GalleryImage} 
 	 * @throws Exception
	 */
	public List<GalleryImage> findGalleryImagesByApp(Long appId);
	/**
	 * Find gallery images by imageId
	 * @param imageId {@link Long}
	 * @return instance {@link GalleryImage}
	 */
	public GalleryImage findGalleryImage(Long imageId);
	/**
	 * Save gallery images . 
	 * @param instance {@link GalleryImage}
	 * @return id {@link Long} of saved image gallery
	 */
	public Long saveGalleryImage(GalleryImage galleryImage);
	/**
	 * Delete gallery images .
	 * @param galleryImage instance of {@link GalleryImage}
	 */
	public void deleteGalleryImage(GalleryImage galleryImage);
	/**
	 * Create shipping if a shipping don't exist having storeId don't equals to 
	 * newInstance.storeId and stateId is not equal to newInstance.stateId otherwise a update operation 
	 * will be performed
	 * @param newInstance {@link Shipping}
	 * @return instance  {@link Shipping}
	 */
	public Shipping createShipping(Shipping newInstance);
	/**
	 * Update shipping  
	 * @param transientInstance {@link Shipping}
	 * @return updated Shipping
	 */
	public Shipping updateShipping(Shipping transientInstance);
	/**
	 * update order Items 
	 * @param transientInstance {@link ProductOrderItem}
	 * @return id{@link Long} of updated item
	 */
	public Long updateOrderItem(ProductOrderItem transientInstance);
	 
	 /**
	  *  Delete shipping 
	  * @param transientInstance {@link Shipping}
	  */
	public void deleteShipping(Shipping transientInstance);
	
	/**
	  *  Find shipping 
	  * @param transientInstance {@link Long}
	  */
	public Shipping findShippingById(Long id);
	 /**
      * 
      * @param storeId
      * @return
      */
	public List<Shipping> findShippingByStore(Long storeId);
	/**
	 * Update tax 
	 * @param transientInstance {@link Tax}
	 * @return updated instance {@link Tax} 
	 */
	public Tax updateTax(Tax transientInstance);
  /**
   * Creates tax
   * @param transientInstance {@link Tax}
   * @return new instance{@link Tax} of tax
   */
	public Tax createTax(Tax transientInstance);
	/**
	 * delete tax
	 * @param transientInstance{@link Tax}
	 */
	public void deleteTax(Tax transientInstance);
	/**
	 * find tax by store id
	 * @param storeId {@link Long}
	 * @return list {@link List} of {@link Tax}
	 */
	public List<Tax> findTaxByStore(Long storeId);
	/**
	 * Find all news items.
	 * @return list {@link List} of {@link News}
	 */
	public List<News> findAllNewsItems();
	
	/**
	 * Find news items by store id
	 * @param storeId {@link Long}
	 * @return list {@link List} of {@link News}
	 */
	public List<News> findNewsItemsByStore(Long storeId);
	
	
	
	/**
	 * Find custom news items by store id
	 * @param storeId {@link Long}
	 * @return list {@link List} of {@link News}
	 */
	public List<News> findCustomNewsItemsByStore(long storeId);
	
	
	
	/**
	 * Find feed news item by store id
	 * @param storeId {@link Long}
	 * @return  {@link News} instance
	 */
	public News findFeedNewsItemByStore(long storeId);
	
	
	/**
	 * Update news items if news.id is null otherwise a new news will be created. 
	 * @param news {@link News}
	 * @return id{@link Long} of updated/created news
	 */
	public Long saveNewsItem(News news);
	/**
	 * Delete news item
	 * @param news {@link News}
	 */
	public void deleteNewsItem(News news);
	/**
	 * Delete news image
	 * @param news{@link News}
	 */
	public void deleteNewsImage(News news);
	/**
	 * Delete logo image
	 * @param user {@link User}
	 */
	public void deleteLogoImage(User user);
   /**
    * Find a news by id 
    * @param newsId{@link Long}
    * @return instance of {@link News}
    */
	public News findNewsById(Long newsId);
	/**
	 * Delete static page
	 * @param page {@link StaticPage}
	 */
	public void deleteStaticPage(StaticPage page);
	/**
	 * Update static page
	 * @param page {@link StaticPage}
	 */
	public void updateStaticPage(StaticPage page);
	/**
	 * update the status of static page by pageId
	 * @param pageId  {@link Long} 
	 * @return boolean
	 */
	public boolean updateStaticPageStatus(Long pageId);
	/**
	 * Find product order by order id
	 * @param orderId {@link Long}
	 * @return instance of {@link ProductOrder}
	 */
	public ProductOrder findProductOrder(Long orderId); 
	
	
	/**
	 * Find product order by order id
	 * @param orderId {@link Long}
	 * @return instance of {@link ProductOrder}
	 */
	public ProductOrderApi findProductOrderForApi(long orderId); 
	/**
	 * Update product order 
	 * @param productOrder{@link ProductOrder}
	 */
	public void updateProductOrder(ProductOrder productOrder); 
    /**
     * Find list of total amount by country 
     * @param territory {@link Territory}
     * @return {@link List}<{@link Comparable}>  
     */
	@SuppressWarnings("rawtypes")
	public List findTotalAmountByCountry(Territory territory);
  /**
   * Find state by territory id
   * @param id{@link Long}
   * @return list{@link List} of state{@link State}
   */
	public List<State> findStatesByTerritory(Long id);
	/**
	 * Find state by id 
	 * @param id {@link Long}
	 * @return instance {@link State }
	 */
	public State findStateById(Long id);
	
	
	/**
	 * Find shipping using storeId, countryId and stateId.
	 * @param storeId {@link Long}
	 * @param countryId {@link Long}
	 * @param stateId {@link Long}
	 * @return instance {@link Shipping}
	 */
	public Shipping findShippingByStoreCountryState(Long storeId,Long countryId, Long stateId);
	/**
	 * Find shipping by storeId,countryId,stateId
	 * @param storeId {@link Long}
	 * @param countryId {@link Long}
	 * @param stateId {@link Long}
	 * @return instance {@link Shipping}
	 */
	public Shipping findShippingByStoreIdCountryIdStateId(Long storeId,Long countryId, Long stateId);
	
	/**
	 * Find shipping by storeId,countryName,stateName
	 * @param storeId {@link Long}
	 * @param countryId {@link String}
	 * @param stateId {@link String}
	 * @return instance {@link Shipping}
	 */
	public Shipping findShippingByStoreIdCountryNameStateName(Long storeId, String countryName, String stateName)throws Exception;
	
	
	/**
	 * Find tax by store country state
	 * @param storeId
	 * @param countryId
	 * @param stateId
	 * @return {@link Tax} instance
	 */
	public Tax findTaxByStoreCountryState(Long storeId,Long countryId, Long stateId);

	/**
	 * Save Product Review
	 * @param productReview
	 * @return 
	 */
	public long saveProductReview(ProductReview productReview );
	
	
	/*   API METHODS */
	
	
	
	/**
	 * get Api instance
	 * 
	 */
	public Api findApiByAPIKey(String apiKey) throws Exception;
	
	/**
	 * Update Api instance
	 * @param api
	 * @return {@link Api} instane
	 * @throws Exception
	 */
	public Api updateApi(Api api);
	
	/**
	 * Find Api by UserId
	 * @param userId
	 * @return {@link Api} instance 
	 */
	public Api findApiByUserId(long userId) ;
	
	/**
	 * All Store instance by user ids 
	 * @param userId
	 * @return List of {@link Store} instance
	 * @throws Exception
	 */
	public List<Store> findAPIStoresByUserId(Long userId) throws Exception ;
	
	/**
	 * store by id
	 * @param storeId
	 * @return {@link Store} instance
	 * @throws Exception
	 */
	public Store findAPIStoreById(Long storeId) throws Exception ;
	
	/**
	 * store by id
	 * @param storeId
	 * @return {@link Store} instance
	 * @throws Exception
	 */
	public Store findAPIStoreShipping(Long storeId) throws Exception  ;
	
	/**
	 * store by id
	 * @param storeId
	 * @return {@link Store} instance
	 * @throws Exception
	 */
	public Store findAPIStoreTax(Long storeId) throws Exception  ;
	
	/**
	 * Find active departement by store
	 * @param storeId
	 * @return 
	 * @throws Exception
	 */
	public List<Department> findAPIActiveDepartmentsByStore(Long storeId) throws Exception ;
    public List<DepartmentApi> findDepartmentsByStoreForApi(Long storeId,Pager pager);
	public List<GalleryImage> findAPIGalleryImagesByStore(Long storeId) throws Exception ;

	public List<Product> findAPIProductsByStore(Long storeId) throws Exception ;
	public List<Product> findAPIProductsByStore(Long storeId,Pager page) throws Exception ;
	 

	public List<Product> findAPIProductsByDepartment(Long departmentId) throws Exception;

	public List<Product> findAPIProductsByCategory(Long categoryId) throws Exception;

	public Product findAPIProduct(Long productId) throws Exception;
	
    public App findAPIAppByUser(Long userId) throws Exception;
    
    public String generateAPIKey(User user) throws ApiKeyGenerationException;
    
    public String generatePartnerAPIKey(String partnerName, String partnerEmail) throws ApiKeyGenerationException;
    
    public User findUserByAPIKey(String apiKey) throws Exception;
    
    public String deleteAPIKey(User user) throws Exception;

	public List<Product> findProductsByUserId(Long userId, Integer page,
			Integer pageSize) throws Exception;
	
	/**
	 * Get featured products for preview 
	 * @param storeId {@link Store} id
	 * @param limit number of products to be returned
	 * @return String of featured products
	 * @throws Exception
	 */
	public String getStringOfFeaturedProducts(Long storeId,  Integer limit) throws Exception;
	/**
	 * Get gallery images for preview
	 * @param storeId {@link Store} id
	 * @param limit number of products to be returned
	 * @return String of gallery images
	 * @throws Exception
	 */
	public String getStringOfGalleryImages(Long storeId,  Integer limit) throws Exception;
	
	
	/**
	 * Get departments in  the store
	 * @param storeId {@link Store} id
	 * @param limit number of departemntss to be returned
	 * @return String of departmends
	 */
	public String getJsonStringOfDepartments(long storeId,int limit);
	
	
	/**
	 * Get departments in  the store
	 * @param storeId {@link Store} id
	 * @param  
	 * @return String of departmends
	 */
	public List<DepartmentDto> getJsonStringOfAllDepartmentsByStore(long storeId);
	
	/**
	 * Get sub-departments in the department 
	 * @param departmentId {@link Department} id
	 * @param limit number of sub departments to be returned
	 * @return String of sub-departmentds
	 */
	public String getJsonStringOfSubDepartments(long departmentId,int limit);
	
	
	
	/**
	 * Get products in a department
	 * @param departmentId {@link Department}
	 * @param limit number of products to be returned
	 * @return String of products 
	 */
	 public String getJsonStringOfProductsByDepartment(long departmentId, int limit);
	 
	 
	 
	 
	 /**
	  * Get products in a store
	  * @param departmentId {@link Department}
	  * @param limit number of products to be returned
	  * @return String of products 
	  */
	 public String getJsonStringOfProductsByStore(long storeId, int limit);
	 
	 
	 
	 /**
	  * Get products in a sub-department
	  * @param subDepartmentId {@link Category} id
	  * @param limit number of products to be returned
	  * @return String of products
	  */
	 public String getJsonStringOfProductsBySubDepartment(long subDepartmentId, int limit);
	
	
	
	 
	 /**
		 * Get enabled pages in  the store
		 * @param storeId {@link Store} id
		 * @return String of pages
		 */
	 public String getJsonStringOfPages(long userId,List<Long> featureIds );
	 
	
		
		
		/**
		 * Get enabled pages in  the store
		 * @param pageId {@link StaticPage} id
		 * @return String of page
		 */
		public String getJsonStringOfPage(long pageId);
	
	
		
		/**
		 * Get enabled pages in  the store
		 * @param storeId {@link Store} id
		 * @param limit number of news items to be returned
		 * @return String of pages
		 */
		public String getJsonStringOfNewsItems(long storeId,int limit);
		
	 /**
	  * get news in Json format
	  * @param kyword
	  * @return string of news
	  */
		public List<NewsDto> findNewsItemsByStore(long storeId,String keyword);
	
		
		
		/**
		 * Get enabled pages in  the store
		 * @param pageId {@link StaticPage} id
		 * @return String of page
		 */
		public String getJsonStringOfNews(long newsId);
	
		
		
	
		/**
		 * Get tweets in a store if feed url is saved 
		 * @param storeId {@link Store} id
		 * @param limit number of news items to be returned
		 * @return String of pages
		 */
		public String getJsonStringOfTweets(long storeId,int limit);
		
		
		public List getJsonStringOfTweetsIphone(long storeId,int limit);
	
		
		/**
		 * Get get twitter username
		 * @param storeId {@link Store} id
		 * @param limit number of news items to be returned
		 * @return String of pages
		 */
		public String getTwitterUsername(long storeId);
	
		
		
		/**
		 * Get feed posts in a store if feed url is saved 
		 * @param storeId {@link Store} id
		 * @param limit number of news items to be returned
		 * @return String of pages
		 */
		public String getJsonStringOfFeedPosts(long storeId,int limit);
	
		
	/**
	 * Get store products with pagination
	 * @param storeId {@link Store} id
	 * @param pager {@link Pager} instance
	 * @return Map of total count and product listing
	 * @throws Exception
	 */
	public Map<String,Object> findStoreProductsWithPagination(Long storeId ,Pager pager) throws Exception;

	/**
	 * Create product order shipping details 
	 * @param productOrderShippingDetail {@link ProductOrderShippingDetail} instance
	 * @return {@link ProductOrderShippingDetail} id
	 * @throws Exception
	 */
	public Long createProductOrderShippingDetail(ProductOrderShippingDetail productOrderShippingDetail) throws Exception;
	
	/**
	 * find profuct order shipping details 
	 * @param trackingNumber Trabing number provided by carrier service 
	 * @return {@link ProductOrderShippingDetail} instance
	 * @throws Exception
	 */
	public ProductOrderShippingDetail findProductOrderShippingDetail(String trackingNumber) throws Exception;	
	/**`
	 * Update product order shipping detail
	 * @param productOrderShippingDetail {@link ProductOrderShippingDetail} instance 
	 * @throws Exception
	 */
	public void updateProductOrderShippingDetail(ProductOrderShippingDetail productOrderShippingDetail) throws Exception;
	
	/**
	 * Get average rating for a product
	 * @param productId {@link Product} id 
	 * @return 
	 * @throws Exception
	 */
	public Integer getProductRating(Long productId) ;
	
	/**
	 * Genreate oauth secret for a user if it does not exists
	 * @param user {@link User} instance
	 * @return Secret
	 * @throws ConsumerSecretGenerationException
	 */
	public String generateOauthSecret(User user) throws ConsumerSecretGenerationException;
	
	/**
	 * Update an existing Address
	 * @param Address {@link Address} instance
	 * @return Boolean  
	 * @throws Exception
	 */
	public Boolean updateAddress(Address address)  throws Exception;
	
	
	public Long findParentDepartmentId(Long subDepartmentId) ;
	
	public boolean getBrandingFlag(String  username);
	
	public List<String>  getProductImagesListByUserId(Long userId) throws Exception;
	public  List<GalleryImage> getGalleryImageByUserId(Long userId) throws Exception;
	public String getTaxInfoByStoreId(Long  storeId);
	public Territory getTerritoryForPaymentTotals(String shippingTerritoryName) throws SQLException;
	public State getStateForPaymentTotals(String state, Long territoryId) throws SQLException;
	public BigDecimal getShippingAmountForPayment(BigDecimal fshippingAmount, Long storeId,Territory territory, Integer quntity, State stte) throws SQLException;
	public BigDecimal getFTaxAmount(Long storeId, Long territoryId, Long stateId, Double priceValue,Integer quntity,BigDecimal fTaxAmount);
	
	
}
