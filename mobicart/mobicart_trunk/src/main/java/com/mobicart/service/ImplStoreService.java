package com.mobicart.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.axis.utils.StringUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.Status;

import com.mobicart.bo.ProductResponse;
import com.mobicart.dao.AddressDAO;
import com.mobicart.dao.ApiDao;
import com.mobicart.dao.AppDAO;
import com.mobicart.dao.CategoryDAO;
import com.mobicart.dao.DepartmentDAO;
import com.mobicart.dao.GalleryImageDAO;
import com.mobicart.dao.NewsDAO;
import com.mobicart.dao.ProductDAO;
import com.mobicart.dao.ProductImageDAO;
import com.mobicart.dao.ProductOptionDAO;
import com.mobicart.dao.ProductOrderDAO;
import com.mobicart.dao.ProductOrderItemDAO;
import com.mobicart.dao.ProductOrderShippingDetailDAO;
import com.mobicart.dao.ProductReviewDAO;
import com.mobicart.dao.ShippingDAO;
import com.mobicart.dao.SiteConstantDAO;
import com.mobicart.dao.StateDAO;
import com.mobicart.dao.StaticPageDAO;
import com.mobicart.dao.StoreDAO;
import com.mobicart.dao.TaxDAO;
import com.mobicart.dao.TerritoryDAO;
import com.mobicart.dao.UserDAO;
import com.mobicart.dto.DepartmentDto;
import com.mobicart.dto.FeedDto;
import com.mobicart.dto.NewsDto;
import com.mobicart.dto.NewsSearchDto;
import com.mobicart.dto.PageDto;
import com.mobicart.dto.ProductDto;
import com.mobicart.dto.TweetDto;
import com.mobicart.model.Address;
import com.mobicart.model.Api;
import com.mobicart.model.ApiExample;
import com.mobicart.model.App;
import com.mobicart.model.Category;
import com.mobicart.model.CategoryExample;
import com.mobicart.model.Department;
import com.mobicart.model.DepartmentExample;
import com.mobicart.model.GalleryImage;
import com.mobicart.model.GalleryImageExample;
import com.mobicart.model.News;
import com.mobicart.model.NewsExample;
import com.mobicart.model.Product;
import com.mobicart.model.ProductExample;
import com.mobicart.model.ProductFilter;
import com.mobicart.model.ProductImage;
import com.mobicart.model.ProductImageExample;
import com.mobicart.model.ProductOption;
import com.mobicart.model.ProductOptionExample;
import com.mobicart.model.ProductOrder;
import com.mobicart.model.ProductOrderExample;
import com.mobicart.model.ProductOrderItem;
import com.mobicart.model.ProductOrderShippingDetail;
import com.mobicart.model.ProductOrderShippingDetailExample;
import com.mobicart.model.ProductReview;
import com.mobicart.model.ProductReviewExample;
import com.mobicart.model.Shipping;
import com.mobicart.model.ShippingExample;
import com.mobicart.model.SiteConstant;
import com.mobicart.model.State;
import com.mobicart.model.StateExample;
import com.mobicart.model.StaticPage;
import com.mobicart.model.StaticPageExample;
import com.mobicart.model.Store;
import com.mobicart.model.StoreExample;
import com.mobicart.model.Tax;
import com.mobicart.model.TaxExample;
import com.mobicart.model.Territory;
import com.mobicart.model.TerritoryExample;
import com.mobicart.model.User;
import com.mobicart.model.api.CategoryApi;
import com.mobicart.model.api.DepartmentApi;
import com.mobicart.model.api.ProductOrderApi;
import com.mobicart.util.CountryUtil;
import com.mobicart.util.DateTimeUtils;
import com.mobicart.util.DesEncrypter;
import com.mobicart.util.FeedParserUtils;
import com.mobicart.util.JsonUtils;
import com.mobicart.util.Pager;
import com.mobicart.util.ResourceProperties;
import com.mobicart.util.ShipmentTrackDetail;
import com.mobicart.util.client.FedexTrackWebServiceClient;
import com.mobicart.util.exception.ApiKeyGenerationException;
import com.mobicart.util.exception.CategoriesExistInDepartmentException;
import com.mobicart.util.exception.DuplicateDepartmentException;
import com.mobicart.util.exception.ProductsExistInCategoryException;
import com.mobicart.util.exception.ProductsExistInDepartmentException;
import com.mobicart.web.security.oauth.ConsumerSecretGenerationException;



 
@Service
public class ImplStoreService implements StoreService {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(ImplStoreService.class);

	@Autowired
	private SiteConstantDAO siteConstantDAO;

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private StoreDAO storeDAO;
	
	@Autowired
	protected RestTemplate restTemplate;
	
	@Autowired
	private DepartmentDAO departmentDAO;

	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired 
	private ProductDAO productDAO;
	
	@Autowired
	private ProductImageDAO productImageDAO;

	@Autowired
	private ProductOptionDAO productOptionDAO;
	
	@Autowired
	private ProductOrderDAO productOrderDAO;
	
	@Autowired
	private ShippingDAO shippingDAO;
	
	@Autowired
	private TaxDAO taxDAO;
	
	@Autowired
	private GalleryImageDAO galleryImageDAO;
	
	@Autowired
	private NewsDAO newsDAO;
	

	@Autowired
	private StaticPageDAO staticPageDAO;

	@Autowired
	private StateDAO stateDAO;
	
	@Autowired
	private AddressDAO addressDAO;

	@Autowired
	private  TerritoryDAO territoryDAO;
	
	@Autowired
	private ProductReviewDAO productReviewDAO;
	
	@Autowired
	private ProductOrderItemDAO productOderItemDAO;

	
	@Autowired
	private ProductOrderShippingDetailDAO productOrderShippingDetailDAO;

	
	@Autowired
	private  AppDAO appDao;
	
	@Autowired
	private ApiDao apiDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	/**
	 * {@inheritDoc}
	 */
	public Long createStore(Store store) throws Exception{
		store.setsCurrency("US-USD");
		return storeDAO.save(store);
	}

	/**
	 * {@inheritDoc}
	 */
	public Store updateStore(Store store) throws Exception {
		storeDAO.update(store);
		store=findStoreById(store.getId());
		
		WebContext ctx = WebContextFactory.get();
		if(ctx!=null){
			HttpServletRequest request = ctx.getHttpServletRequest();
			request.getSession().setAttribute("store", store);
		}
		
		return store;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Store findStoreByUserId(Long userId)  throws Exception{
		return storeDAO.findStoreByUserId(userId);
	}

	/**
	 * {@inheritDoc}
	 */
	public Store findStoreById(Long storeId)  throws Exception {
		return storeDAO.findStoreById(storeId);
	}

	
	/**
	 * {@inheritDoc}
	 */
	public Territory findTerritoryById(Long id) {
		return territoryDAO.findTerritoryById(id);	
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Category> findCategoriesByDepartment(Long departmentId) {
		DepartmentExample example=new DepartmentExample();
		example.createCriteria().andParentDepartmentIdEqualTo(departmentId);
		example.setOrderByClause("id DESC");
		return departmentDAO.findCategoriesByExample(example);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<CategoryApi> findCategoriesByDepartmentForApi(Long departmentId) {
		DepartmentExample example=new DepartmentExample();
		example.createCriteria().andParentDepartmentIdEqualTo(departmentId);
		example.setOrderByClause("id DESC");
		return departmentDAO.findCategoriesByExampleForApi(example);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<Category> findActiveCategoriesByDepartment(Long departmentId)  {
		DepartmentExample example=new DepartmentExample();
		example.createCriteria().andParentDepartmentIdEqualTo(departmentId).andSStatusEqualTo("active");
		example.setOrderByClause("id DESC");
		return departmentDAO.findCategoriesByExample(example);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Category> findActiveCategoriesByDepartment(Department department)  {
		 
		return departmentDAO.findCategoriesByIdAndMaxRowNum(department);
	}
	/**
	 * {@inheritDoc}
	 */
	public List<Department> findDepartmentsByStore(Long storeId) {
		DepartmentExample departmentExample=new DepartmentExample();
		departmentExample.createCriteria().andStoreIdEqualTo(storeId).andParentDepartmentIdEqualTo(0L);
		departmentExample.setOrderByClause("id DESC");
		return departmentDAO.findByExample(departmentExample);
	}

	/**
	 * {@inheritDoc}
	 */
	
	public List<Department> findDepartmentsByParent(Long parentDepartmentId) {
		DepartmentExample departmentExample=new DepartmentExample();
		departmentExample.createCriteria().andParentDepartmentIdEqualTo(parentDepartmentId);
		departmentExample.setOrderByClause("id DESC");
		return departmentDAO.findByExample(departmentExample);
	}
	
	
	
	
	public Long createCategory(Category category) throws Exception{
		return categoryDAO.create(category);
	}

	/**
	 * {@inheritDoc}
	 */
	public Long createDepartment(Department department) throws DuplicateDepartmentException {
		
		 
		
		Department existingDepartment=findDepartmentByStoreAndName(department.getStoreId(), department.getsName());
		if(existingDepartment!=null){
			throw new DuplicateDepartmentException("Sub-department already exist under this department.");
		}
		
		int ProductCount=productDAO.findProductCountByDepartmentId(department.getParentDepartmentId());
		
		if(ProductCount>0){
			
			
			throw  new DuplicateDepartmentException("A product already exist under this department.");
			
		}
		 
		
		return departmentDAO.create(department);
	}
     
 
	/**
	 * {@inheritDoc}
	 */
	public void deleteCategory(Category category) throws ProductsExistInCategoryException,Exception{
		Category oldCategory =categoryDAO.find(category.getId());
		if(oldCategory.getiProductCount()>0){
			String error=ResourceProperties.getString("Error.products.exist.sub-department");
			throw new ProductsExistInCategoryException(error);
		}
		categoryDAO.delete(category);
		
	}

	/**
	 * {@inheritDoc}
	 */
	public void deleteDepartment(Department department) throws CategoriesExistInDepartmentException,ProductsExistInDepartmentException {
		Department oldDepartment= departmentDAO.find(department.getId());
		if(oldDepartment.getiCategoryCount()>0){
			String error=ResourceProperties.getString("Error.sub-departments.exist.department");
			throw new CategoriesExistInDepartmentException(error);
		}
		if(oldDepartment.getiProductCount()>0){
			String error=ResourceProperties.getString("Error.products.exist.department");
			throw new ProductsExistInDepartmentException(error);
		}
		departmentDAO.delete(department);
		
	}

	/**
	 * {@inheritDoc}
	 */
	public Category updateCategory(Category category) throws Exception{
			categoryDAO.update(category);
		return categoryDAO.find(category.getId());
		
	}

	/**
	 * {@inheritDoc}
	 */
	public Department updateDepartment(Department department) {
		try {
			if(logger.isDebugEnabled())logger.debug("updating department.. {} ",department);
			
			departmentDAO.update(department);
			
			return departmentDAO.find(department.getId());
			
		} catch (ObjectRetrievalFailureException e) {
			logger.error("obrfe", e);
			throw e;
		}catch (DataAccessException e) {
			logger.error("ee",e);
			throw e;
		}
		//return departmentDAO.find(department.getId());
	}

	/**
	 * {@inheritDoc}
	 */
	public Category findCategory(Long categoryId) throws Exception {
		return categoryDAO.find(categoryId);
	}
	
	public List<Category> findAllCategories(){
		return categoryDAO.findAll();
	}
	
	public List<Department> findAllDepartments(){
		return departmentDAO.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	public Department findDepartment(Long departmentId) {
		return departmentDAO.find(departmentId);
	}
	
	public Department findDepartmentForApi(Long departmentId) {
		return departmentDAO.findForApi(departmentId);
	}

	/**
	 * {@inheritDoc}
	 */
	public Department findDepartmentByStoreAndName(Long storeId,
			String departmentName) {
			
			DepartmentExample departmentExample=new DepartmentExample();
			departmentExample.createCriteria().andSNameEqualTo(departmentName).andStoreIdEqualTo(storeId);
			Department department=null;
			
			List<Department> departments=departmentDAO.findByExample(departmentExample);
			if(departments!=null){
				if(departments.size()>0){
				if (logger.isInfoEnabled()) {
					logger.info("findDepartmentByStoreAndName(Long, String) - List<Department> departments=" + departments.size()); //$NON-NLS-1$
				}
					department=departments.get(0);
				}
			}
			
		return department; 
	}

	/**
	 * {@inheritDoc}
	 */
	public Long createProduct(Product newInstance) {
		
		return productDAO.create(newInstance);
	}

	/**
	 * {@inheritDoc}
	 */
	public void deleteProductAPI(Product transientInstance) {
		//delete the product
		productDAO.delete(transientInstance);
		//delete related records too
		ProductOptionExample productOptionExample=new ProductOptionExample();
		productOptionExample.createCriteria().andProductIdEqualTo(transientInstance.getId());
		productOptionDAO.deleteByExample(productOptionExample);

		ProductImageExample productImageExample=new ProductImageExample();
		productImageExample.createCriteria().andProductIdEqualTo(transientInstance.getId());
		productImageDAO.deleteByExample(productImageExample);
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void deleteProduct(Product transientInstance) {
	
		//delete the product
		productDAO.delete(transientInstance);
	
		//delete related records too
		ProductOptionExample productOptionExample=new ProductOptionExample();
		productOptionExample.createCriteria().andProductIdEqualTo(transientInstance.getId());
		productOptionDAO.deleteByExample(productOptionExample);

		ProductImageExample productImageExample=new ProductImageExample();
		productImageExample.createCriteria().andProductIdEqualTo(transientInstance.getId());
		productImageDAO.deleteByExample(productImageExample);
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateProduct(Product transientInstance) {
		productDAO.update(transientInstance);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Product> findProductsByCategory(Long categoryId) {
		ProductExample productExample=new ProductExample();
		productExample.createCriteria().andCategoryIdEqualTo(categoryId);
		productExample.setOrderByClause("id DESC");
		return productDAO.findByExample(productExample);
	}

	
	
	/**
	 * {@inheritDoc}
	 */
	public List<Product> findActiveProductsByUser(Long userId) {
		ProductExample productExample=new ProductExample();
		productExample.createCriteria().andUserIdEqualTo(userId).andSStatusEqualTo("active");
		productExample.setOrderByClause("id DESC");
		return productDAO.findByExample(productExample);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<Product> findProductsByDepartment(Long departmentId) {
		ProductExample productExample=new ProductExample();
		productExample.createCriteria().andDepartmentIdEqualTo(departmentId);
		productExample.setOrderByClause("id DESC");
		return productDAO.findByExample(productExample);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Product> findProductsByDepartmentAndMaxRowNum(Product product) {
		 
		 
		return productDAO.findProductsByDepartmentAndMaxRowNum(product);
	}

	
	
	
	/**
	 * {@inheritDoc}
	 */
	public List<Product> findProductsDirectUnderDepartment(Long departmentId){
		ProductExample productExample=new ProductExample();
		productExample.createCriteria().andDepartmentIdEqualTo(departmentId).andCategoryIdEqualTo(0L);
		productExample.setOrderByClause("id DESC");
		return productDAO.findByExample(productExample);
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	public List<Product> findActiveProductsByDepartment(Long departmentId) {
		ProductExample productExample=new ProductExample();
		productExample.createCriteria().andDepartmentIdEqualTo(departmentId).andSStatusNotEqualTo("hidden");//.andCategoryIdEqualTo(0L)
		productExample.setOrderByClause("id DESC");
		return productDAO.findByExample(productExample);
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	public ProductResponse  findActiveProductsByDepartment(Long departmentId,
			Pager pager) {
		ProductExample productExample=new ProductExample();
		productExample.createCriteria().andDepartmentIdEqualTo(departmentId).andSStatusNotEqualTo("hidden");//.andCategoryIdEqualTo(0L)
		productExample.setOrderByClause("id DESC");
		
		ProductResponse  productResponse=new ProductResponse();
		List<Product> products= productDAO.findActiveByExample(productExample,pager);
		int totalCount=productDAO.findCountByExample(productExample);
		productResponse.setMaxLimit(pager.getUpperLimit());
		productResponse.setStart(pager.getLowerLimit());
		productResponse.setTotalCount(totalCount);
		productResponse.setProductList(products);
		
		return productResponse;
	}

 
	public int findProductCountByStore(Long storeId) {
		ProductExample productExample = new ProductExample();
		productExample.createCriteria().andStoreIdEqualTo(storeId);
		return productDAO.findCountByExample(productExample);
	}
	
	public int findProductCountByApp(Long appId) {
		ProductExample productExample = new ProductExample();
		productExample.createCriteria().andAppIdEqualTo(appId);
		return productDAO.findCountByExample(productExample);
	}
	
/**
 * {@inheritDoc}
 */
	public List<Product> findProductsByStore(Long storeId) {
		ProductExample productExample=new ProductExample();
		productExample.createCriteria().andStoreIdEqualTo(storeId);
		productExample.setOrderByClause("id DESC");
		return productDAO.findByExample(productExample);
	}
	
	public List<Product> findProductsByStore(Long storeId,Pager pager) {
		ProductExample productExample=new ProductExample();
		productExample.createCriteria().andStoreIdEqualTo(storeId);
		productExample.setOrderByClause("id DESC");
		return productDAO.findByExample(productExample, pager);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Category> findCategoriesByDepartments(List<Department> departments)  {
		
		List<Category> categories=null;
	
		List<Long> departmentIdList=new ArrayList<Long>();
		for (Department department : departments) {
			departmentIdList.add(department.getId() );
		}
		CategoryExample categoryExample=new CategoryExample();
		categoryExample.createCriteria().andDepartmentIdIn(departmentIdList);
		categoryExample.setOrderByClause("id DESC");
		categories =categoryDAO.findByExample(categoryExample);
		
		
		return categories;
	}

	

	/**
	 * {@inheritDoc}
	 */
	public List<Product> findActiveProductsByCategory(Long categoryId) {
		ProductExample productExample=new ProductExample();
		productExample.createCriteria().andCategoryIdEqualTo(categoryId);
		productExample.setOrderByClause("id DESC");
		return productDAO.findActiveByExample(productExample);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public ProductResponse findActiveProductsByCategory(Long categoryId,
			Pager pager) {
		ProductExample productExample=new ProductExample();
		productExample.createCriteria().andCategoryIdEqualTo(categoryId);
		productExample.setOrderByClause("id DESC");
		
		ProductResponse  productResponse=new ProductResponse();
		List<Product> products= productDAO.findActiveByExample(productExample,pager);
		int totalCount=productDAO.findCountByExample(productExample);
		productResponse.setMaxLimit(pager.getUpperLimit());
		productResponse.setStart(pager.getLowerLimit());
		productResponse.setTotalCount(totalCount);
		productResponse.setProductList(products);
		
		return productResponse;
	}
	
	public String createDepartmentAjax(Department department) {
		StringBuffer html=new StringBuffer();
		return html.toString();
	}

	public Long createProductImage(ProductImage productImage) {
		return productImageDAO.create(productImage);
	}

	public boolean createProductImages(List<ProductImage> productImages) {
		return productImageDAO.create(productImages);
	}

	public List<ProductImage> findProductImages(Long productId) {
		ProductImageExample productImageExample=new ProductImageExample();
		productImageExample.createCriteria().andProductIdEqualTo(productId);
		productImageExample.setOrderByClause("id DESC");
		return productImageDAO.findByExample(productImageExample);
	}

	
	public List<GalleryImage> findAllGalleryImages(){
		return galleryImageDAO.findAll();
	}
	
	public Long saveGalleryImage(GalleryImage galleryImage){
		galleryImage.setdCreatedOn(DateTimeUtils.getCurrentTimestamp());
		Long galleryImageId = galleryImageDAO.create(galleryImage);
		return galleryImageId;		
	}
	
	public Long createProductOption(ProductOption productOption) throws Exception {
		return productOptionDAO.create(productOption);
	}
	

	public void updateProductImage(ProductImage productImage) {
		 productImageDAO.update(productImage);
	}


	public Product findProduct(Long productId) {
		return productDAO.find(productId);
	}

	public List<ProductOption> findProductOptions(Long productId) throws Exception {
		ProductOptionExample productOptionExample=new ProductOptionExample();
		productOptionExample.createCriteria().andProductIdEqualTo(productId);
		productOptionExample.setOrderByClause("id DESC");
		return productOptionDAO.findByExample(productOptionExample);
	
	}

	
	
	
	public boolean isProductOptionUniqueTitleLimitValid(ProductOption productOption)
			throws Exception {
		ProductOptionExample productOptionExample=new ProductOptionExample();
		productOptionExample.createCriteria().andProductIdEqualTo(productOption.getProductId()).andsTitleNotEqualTo(productOption.getsTitle());
		Integer count = productOptionDAO.findUniqueTitleCount(productOptionExample);
		if (count >= 4) {
			return false;
		} else {
			return true;
		}
	}

	public Shipping createShipping(Shipping newInstance) {
		ShippingExample shippingExample=new ShippingExample();
		shippingExample.createCriteria().andStoreIdEqualTo(newInstance.getStoreId()).andStateIdEqualTo(newInstance.getStateId());
		List<Shipping> instances=shippingDAO.findByExample(shippingExample);
		if(instances.size()>0){
			Shipping persistedInstance=instances.get(0);
			newInstance.setId(persistedInstance.getId());
			return updateShipping(newInstance);
		}else{
			Long shippingId=shippingDAO.create(newInstance);
			return shippingDAO.find(shippingId);
		}
		
	}

	public Shipping updateShipping(Shipping transientInstance) {
			shippingDAO.update(transientInstance);
			return shippingDAO.find(transientInstance.getId());
	}
	
	public Long updateOrderItem(ProductOrderItem transientInstance) {
		productOderItemDAO.update(transientInstance);
		return transientInstance.getId();
}
	

	public void deleteShipping(Shipping transientInstance) {
			shippingDAO.delete(transientInstance);
	}

	public List<Shipping> findShippingByStore(Long storeId) {
		ShippingExample shippingExample=new ShippingExample();
		shippingExample.createCriteria().andStoreIdEqualTo(storeId);
		shippingExample.setOrderByClause("id DESC");
		return shippingDAO.findByExample(shippingExample);
	}
	
	public Shipping findShippingById(Long id){
		ShippingExample shippingExample = new ShippingExample();
		shippingExample.createCriteria().andIdEqualTo(id);
		return shippingDAO.find(id);
	}

	public Tax createTax(Tax newInstance) {
		TaxExample taxExample=new TaxExample();
		taxExample.createCriteria().andStoreIdEqualTo(newInstance.getStoreId()).andStateIdEqualTo(newInstance.getStateId());
		List<Tax> taxes=taxDAO.findByExample(taxExample);
		if(taxes.size()>0){
			Tax oldInstance=taxes.get(0);
			newInstance.setId(oldInstance.getId());
			return updateTax(newInstance);
		}else{
			Long id=taxDAO.create(newInstance);
			return taxDAO.find(id);
		}
	}

	public Tax updateTax(Tax transientInstance) {
         taxDAO.update(transientInstance);
         return taxDAO.find(transientInstance.getId());
	}

	public void deleteTax(Tax persistantInstance) {
		taxDAO.delete(persistantInstance);
	}

	public List<Tax> findTaxByStore(Long storeId) {
		TaxExample taxExample=new TaxExample();
		taxExample.createCriteria().andStoreIdEqualTo(storeId);
		taxExample.setOrderByClause("id DESC");
		return taxDAO.findByExample(taxExample);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<News> findAllNewsItems(){
		return newsDAO.findAll();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public News findNewsById(Long newsId){
		return  newsDAO.find(newsId);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Long saveNewsItem(News news){
		Long newsImageId=null;
		if(news.getId()!=null){
			newsDAO.update(news);
			newsImageId=news.getId();
		}else{
			newsImageId=newsDAO.create(news);
		}
		return newsImageId;		
	}

	
	/**
	 * {@inheritDoc}
	 */
	public List<News> findNewsItemsByStore(Long storeId) {
		NewsExample newsExample=new NewsExample();
		newsExample.createCriteria().andStoreIdEqualTo(storeId);
		newsExample.setOrderByClause("id DESC");
		return newsDAO.findByExample(newsExample);
	}


	
 
	public List<News> findCustomNewsItemsByStore(long storeId) {
		try {
			NewsExample newsExample = new NewsExample();
			newsExample.createCriteria().andStoreIdEqualTo(storeId)
					.andSTypeEqualTo("custom");
			newsExample.setOrderByClause("id DESC");
			return newsDAO.findByExample(newsExample);
		} catch (DataAccessException e) {
			logger.error("dae",e);
		} catch (Exception e) {
			logger.error("e",e);
		}

		return null;
	}

 
	public News findFeedNewsItemByStore(long storeId) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public void deleteNewsItem(News news) {
		newsDAO.delete(news);
	}

	
	/**
	 * {@inheritDoc}
	 */
	public List<GalleryImage> findGalleryImagesByStore(Long storeId) {
		GalleryImageExample galleryImageExample=new GalleryImageExample();
		galleryImageExample.createCriteria().andStoreIdEqualTo(storeId);
		galleryImageExample.setOrderByClause("id DESC");
		return galleryImageDAO.findByExample(galleryImageExample);
	}


		/**
		 * {@inheritDoc}
		 */
	public List<GalleryImage> findGalleryImagesByApp(Long appId) {
		GalleryImageExample galleryImageExample=new GalleryImageExample();
		galleryImageExample.createCriteria().andAppIdEqualTo(appId);
		galleryImageExample.setOrderByClause("id DESC");
		return galleryImageDAO.findByExample(galleryImageExample);
	}

	
	public void deleteGalleryImage(GalleryImage galleryImage) {
		galleryImageDAO.delete(galleryImage);
		
	}
	
	/*
	 * Find the GalleryImage by ImageId.
	 * 
	 * @see com.mobicart.service.StoreService#findGalleryImage(java.lang.Long)
	 */
	public GalleryImage findGalleryImage(Long galleryImageId) {
		return galleryImageDAO.find(galleryImageId);
	}
	

	public void deleteProductOption(ProductOption productOption) throws Exception {
		productOptionDAO.delete(productOption);
	}

	/* 
	 * @see com.mobicart.service.StoreService#deleteStaticPage(com.mobicart.model.StaticPage)
	 */
	public void deleteStaticPage(StaticPage page) {
		staticPageDAO.delete(page);
	}

	
	public void updateStaticPage(StaticPage page) {
		staticPageDAO.update(page);
	}
	
	/* 
	 * @see com.mobicart.service.StoreService#findProductsByApp(java.lang.Long)
	 */
	public List<Product> findProductsByApp(Long appId) {
		ProductExample productExample=new ProductExample();
		productExample.createCriteria().andAppIdEqualTo(appId).andSStatusNotLike("hidden");
		productExample.setOrderByClause("i_view_count DESC");
		return productDAO.findByExample(productExample);
	}

	
	public List<Product> findProductsByAppWithPaging(Long appId, Pager pager) {
		ProductExample productExample=new ProductExample();
		productExample.createCriteria().andAppIdEqualTo(appId).andSStatusNotLike("hidden");
		productExample.setOrderByClause("i_view_count DESC");
		return productDAO.findByExampleWithPaging(productExample, pager);
	}
	
	public List<Product> selectPurchasedProductsWithPaging(Long userId,Pager pager) throws DataAccessException{
		ProductExample productExample=new ProductExample();
		productExample.createCriteria().andUserIdEqualTo(userId).andSStatusNotLike("hidden");//andAppIdEqualTo(appId).andSStatusNotLike("hidden");
		productExample.setOrderByClause("i_purchase_count DESC");
		return productDAO.selectPurchasedProductsWithPaging(productExample, pager);
	}
	
	
	
	public List<Product> findProductsByFilter(ProductFilter productFilter) {
		ProductExample productExample=new ProductExample();
		productExample.createCriteria().andAppIdEqualTo(productFilter.getAppId()).andSStatusNotLike("hidden").andIViewCountGreaterThan(0);
		productExample.setOrderByClause("i_view_count DESC");
		return productDAO.findByFilteredExample(productFilter,productExample);
	}

	
	
	
	public void deleteProductImage(ProductImage productImage) {
		productImageDAO.delete(productImage);
	}

	
	public List<Product> findFeaturedProductsByApp(Long appId) {
		ProductExample productExample=new ProductExample();
		productExample.createCriteria().andAppIdEqualTo(appId).andBFeaturedEqualTo(true);
		productExample.setOrderByClause("i_view_count DESC");
		return productDAO.findByExample(productExample);
	}
	
	public List<Product> findFeaturedProductsByAppAndMaxlimit(Product product) {
		 
		return productDAO.findByFeaturedProductByAppAndMaxlimit(product);
	}
	

	/**
	 * Find products by keyword
	 */
	public List<Product> findProductsByKeywordSearch(Long appId,String keyword) {
		keyword="%"+keyword+"%";
		ProductExample productExample=new ProductExample();
		productExample.createCriteria().andAppIdEqualTo(appId).andSNameLike(keyword);
		productExample.setOrderByClause("i_view_count DESC");
		return productDAO.findActiveByExample(productExample);
		}

	
	
	/**
	 * 
	 */
	public List<Store> findStoresByUserId(Long userId) throws Exception {
		StoreExample storeExample=new StoreExample();
		storeExample.createCriteria().andUserIdEqualTo(userId);
		return storeDAO.findStoresByExample(storeExample);
	}

	public void deleteNewsImage(News news) {
		news.setsImage("");
		newsDAO.update(news);
	}
	
	public void deleteLogoImage(User user) {
		user.setsCompanyLogo("");
		userDAO.update(user);
		
		User updatedUser=userDAO.find(user.getId());
		

		WebContext ctx = WebContextFactory.get();
		HttpServletRequest request = ctx.getHttpServletRequest();
		request.getSession().setAttribute("user", updatedUser);
		
	}

	
	public List<Department> findActiveDepartmentsByStore(Long storeId) {
		DepartmentExample departmentExample=new DepartmentExample();
		//added parent department id check for sub departments
		departmentExample.createCriteria().andStoreIdEqualTo(storeId).andParentDepartmentIdEqualTo(0L).andSStatusEqualTo("active");
		departmentExample.setOrderByClause("id DESC");
		return departmentDAO.findByExample(departmentExample);
	}
	
	public ProductImage findProductImage(Long id) {
		return productImageDAO.find(id);
	}

	public ProductOption findProductOption(Long id) throws Exception {
		return productOptionDAO.find(id);
	}

	public boolean updateStaticPageStatus(Long pageId) {
		return staticPageDAO.updateStatus(pageId);
	}

	public ProductOrder findProductOrder(Long orderId) {
		 
		ProductOrderExample productOrderExample=new ProductOrderExample();
		productOrderExample.createCriteria().andIdEqualTo(orderId);
		productOrderExample.setOrderByClause("id DESC");
		List<ProductOrder> productOrders=productOrderDAO.findByExample(productOrderExample);
		 
		try{
 		for(int i=0;i<productOrders.size();i=i+1){
 			
 			 
 			for(int j=0;j<productOrders.get(i).getProductOrderItems().size();j++){
 			
 				
 				ProductOrderItem  firstOpt=productOrders.get(i).getProductOrderItems().get(0);
 				
 				if(firstOpt!=null){
 				//System.out.println("firstOpt"+firstOpt);
 				String sTitleMatch=(firstOpt.getProductOption()!=null)?firstOpt.getProductOption().getsTitle():"";
 				//System.out.println(productOrders.get(i).getProductOrderItems());
 				////System.out.println(productOrders.get(i).getProductOrderItems().get(j).getId());
 				//System.out.println(productOrders.get(i).getProductOrderItems().get(j).getProductOption());
 				
 				ProductOption productOption=productOrders.get(i).getProductOrderItems().get(j).getProductOption();
 				String candidate=(productOption!=null)?productOption.getsTitle():"";
 				 if(!sTitleMatch.equalsIgnoreCase(candidate)){
 					productOrders.get(i).getProductOrderItems().get(j).setProductName("");
					//productOrders.get(i).getProductOrderItems().get(j).setProductOption(null);
 					productOrders.get(i).getProductOrderItems().get(j).setfAmount(null);
 					//productOrders.get(i).getProductOrderItems().get(j).setiQuantity(null);
 					
 				} 
 			}
 			}
 			
 		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		 
		ProductOrder productOrder=null;
		if(productOrders!=null && productOrders.size()!=0){
			productOrder=productOrders.get(0);
		}
		return productOrder;
	}

	
	public ProductOrderApi findProductOrderForApi(long orderId) {
		return productOrderDAO.findForApi(orderId);
		
	}
	
	public void updateProductOrder(ProductOrder productOrder) {
		productOrderDAO.update(productOrder);
	}

	public List<Product> findPurchasedProducts(Long userId) {
		return productDAO.findPurchasedProducts(userId);
	}

	public List<Product> findPurchasedProductsWithPagination(Long userId,Pager pager) {
		return productDAO.findPurchasedProductsWithPagination(userId,pager);
	}

	public ProductOption updateProductOption(ProductOption productOption) throws Exception{
		
		productOptionDAO.update(productOption);
		return productOptionDAO.find(productOption.getId());
	}

	@SuppressWarnings("rawtypes")
	public List findTotalAmountByCountry(Territory territory) {
		BigDecimal totalAmount=new BigDecimal(0); 
		
		List<Comparable> arr=new ArrayList<Comparable>(); 
		
		try{
			SiteConstant siteConstant=siteConstantDAO.findAll().get(0);
			BigDecimal amount = siteConstant.getfAppSubmissionFee();
			if(territory.isEUMember()){
				BigDecimal taxPercentage=siteConstant.getfEuVat();
				BigDecimal hundred=new BigDecimal(100); 
				totalAmount=amount.add(amount.multiply(taxPercentage.divide(hundred)));
			}else{
				totalAmount=amount;
			}
			
			WebContext ctx = WebContextFactory.get();
			HttpServletRequest request = ctx.getHttpServletRequest();
			request.getSession().setAttribute("totalAmount",totalAmount );
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		DesEncrypter encrypter=new DesEncrypter("pay-mobi-cart");		
		String enc_val="";
		
		
		try{
			totalAmount=new BigDecimal(String.valueOf(totalAmount)).setScale(2, RoundingMode.HALF_EVEN);
			enc_val=encrypter.encrypt(String.valueOf(totalAmount));
			
		}catch(Exception e){
			
		}
		
		arr.add(totalAmount);
		arr.add(enc_val);
		
		return arr;
	}

	public List<State> findStatesByTerritory(Long id) {
		List<State> states=null;
		StateExample stateExample=new StateExample();
		stateExample.createCriteria().andTerritoryIdEqualTo(id);
		stateExample.setOrderByClause("s_name ASC");
		states=stateDAO.findByExample(stateExample);
		
		logger.debug("state:"+states);
		// change Other element to last
		State noneState=null;
		State otherState = null;
		Iterator<State> iterator = states.iterator();
		while (iterator.hasNext()) {
			State state = iterator.next();
			if (state.isOther()) {
				otherState = state;
				iterator.remove();
			}
			if(state.isNone()){
				noneState=state;
				iterator.remove();
			}
		}
		if (otherState != null)
			states.add(otherState);
		
		if (noneState != null)
			states.add(0,noneState);
		return states;
	}

	public State findStateById(Long id) {
		return stateDAO.find(id);
	}
	
	

	
	public Shipping findShippingByStoreCountryState_BACK(Long storeId,Long countryId, Long stateId){
		Store store=null;
		State state=null;
		Territory country=null;
		
		try {
			store= findStoreById(storeId);
			for (Shipping shipping : store.getShippingList()) {
			
				if(shipping.getTerritoryId().equals(countryId)&&shipping.getStateId().equals(stateId)){
					return shipping;
 				}else if ((state = findStateById(shipping.getStateId())) != null) {
 					if (state.isOther())
						return shipping;
				} else if ((country = findTerritoryById(shipping.getTerritoryId())) != null) {
					if (country.isOther())
						return shipping;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// send zero valued
		return Shipping.getDefaultValue(storeId, countryId, stateId);
	}
	
	
	public Shipping findShippingByStoreCountryState(Long storeId,Long countryId, Long stateId){
		Store store=null;
		State state=null;
		Territory country=null;
		
		try {
			store= findStoreById(storeId);
			
		
			for (Shipping shipping : store.getShippingList()) {
				if(shipping.getTerritoryId().equals(countryId)&&shipping.getStateId().equals(stateId)){
					return shipping;
 				}
			}
		
			
			for (Shipping shipping : store.getShippingList()) {
				if ((state = findStateById(shipping.getStateId())) != null) {
					if (state.isOther()&&shipping.getTerritoryId().equals(countryId)) 						
						return shipping;
				} 
			}		
			
		
			for (Shipping shipping : store.getShippingList()) {
			if ((country = findTerritoryById(shipping.getTerritoryId())) != null) {
					if (country.isOther())						
						return shipping;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// send zero valued
		return Shipping.getDefaultValue(storeId, countryId, stateId);
	}
	
	
	
	public Shipping findShippingByStoreIdCountryNameStateName(Long storeId, String countryName, String stateName)throws Exception{
		TerritoryExample territoryExample = new TerritoryExample();
		territoryExample.createCriteria().andSNameEqualTo(countryName);
		List<Territory> territoryList = territoryDAO.findTerritoriesByExample(territoryExample);
		if(territoryList == null || territoryList.size() <= 0) return null;
		Territory territory = territoryList.get(0);
		if(territory == null) return null;
		
		StateExample stateExample = new StateExample();
		stateExample.createCriteria().andTerritoryIdEqualTo(territory.getId()).andSNameEqualTo(stateName);
		List<State> stateList = stateDAO.findByExample(stateExample);
		if(stateList == null || stateList.size() <= 0) return null;
		State state = stateList.get(0);
		if(state == null) return null;
		
		ShippingExample shippingExample = new ShippingExample();
		shippingExample.createCriteria().andStoreIdEqualTo(storeId).andTerritoryIdEqualTo(territory.getId()).andStateIdEqualTo(state.getId());
		List<Shipping> shippingList = shippingDAO.findByExample(shippingExample);
		return shippingList.get(0);
	}
	
	
	
	
	
	
	public Shipping findShippingByStoreIdCountryIdStateId(Long storeId,Long countryId, Long stateId){
		Store store=null;
		State state=null;
		Territory country=null;
		
		try {
			store= findStoreById(storeId);
			
		
			for (Shipping shipping : store.getShippingList()) {
				if(shipping.getTerritoryId().equals(countryId)&&shipping.getStateId().equals(stateId)){
					return shipping;
 				}
			}
		
			
			for (Shipping shipping : store.getShippingList()) {
				if ((state = findStateById(shipping.getStateId())) != null) {
					if (state.isOther()&&shipping.getTerritoryId().equals(countryId)) 						
						return shipping;
				} 
			}		
			
		
			for (Shipping shipping : store.getShippingList()) {
			if ((country = findTerritoryById(shipping.getTerritoryId())) != null) {
					if (country.isOther())						
						return shipping;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// send zero valued
		return null;
	}
	
	
	
	

	public Tax findTaxByStoreCountryState(Long storeId, Long countryId,
			Long stateId) {
		Store store=null;
		State state=null;
		Territory country=null;
		
		
		try {
			store= findStoreById(storeId);
			
			for (Tax tax : store.getTaxList()) {
				try{
				if (tax.getTerritoryId().equals(countryId)&& tax.getStateId().equals(stateId)) {
					return tax;
				}
				}catch(Exception e){
					logger.error("some error {} ",e.getLocalizedMessage());
				}
			}	
			
			for (Tax tax : store.getTaxList()) {
				try{
				if ((state = findStateById(tax.getStateId())) != null) {
					if (state.isOther()&&tax.getTerritoryId().equals(countryId))
						return tax;
				}
				}catch(Exception e){
					logger.error("some error {} ",e.getLocalizedMessage());
				}
			}	
			
			for (Tax tax : store.getTaxList()) {
			try{
				if ((country = findTerritoryById(tax.getTerritoryId())) != null) {
					if (country.isOther())
						return tax;
				}
			}catch(Exception e){
				logger.error("some error {} ",e.getLocalizedMessage());
			}
			}
			
		} catch (Exception e) {
			logger.error("some error {}",e.getLocalizedMessage());
		}
		return Tax.getDefaultValue(storeId, countryId, stateId);
	}

	/**
	 * {@inheritDoc} 
	 */
	public long saveProductReview(ProductReview productReview) {
		
		List <ProductReview> reviews=null;
		ProductReview orig_productReview=null;
		
		Long id=null;
		//if review is already there update it
		if(productReview.getId()!=null){
			id=productReview.getId();
			productReviewDAO.update(productReview);
			return id;
		}
		
		
		//if reviewer email is null return 0
		if(productReview.getsReviewerEmail()==null){
			return 0L;
		}
		
		ProductReviewExample productReviewExample=new ProductReviewExample();
		
		//check if user has revied earlier also
		productReviewExample.createCriteria().andSReviewerEmailEqualTo(productReview.getsReviewerEmail()).andProductIdEqualTo(productReview.getProductId());
		reviews=productReviewDAO.findByExample(productReviewExample);
		if(reviews!=null&&reviews.size()>0){
			orig_productReview=reviews.get(0);
			
			id=orig_productReview.getId();
			productReview.setId(id);
			productReviewDAO.update(productReview);
			return id;
		}else{
			return productReviewDAO.create(productReview);
		}
	}
	
	

	//**API Methods**
	
	/**
	 * 
	 */
	public List<Store> findAPIStoresByUserId(Long userId) throws Exception {
		StoreExample storeExample=new StoreExample();
		storeExample.createCriteria().andUserIdEqualTo(userId);
		return storeDAO.findAPIStoresByExample(storeExample);
	}


	public Store findAPIStoreById(Long storeId)  throws Exception {
		return storeDAO.findAPIStoreById(storeId);
	}
	
	public Store findAPIStoreShipping(Long storeId)  throws Exception {
		return storeDAO.findAPIStoreShipping(storeId);
	}
	
	public Store findAPIStoreTax(Long storeId)  throws Exception {
		return storeDAO.findAPIStoreTax(storeId);
	}

     
	

	@Override
	public List<Department> findActiveDepartmentsByStoreWithoutSubdepartments(
			Long storeId) {
		DepartmentExample departmentExample=new DepartmentExample();
		departmentExample.createCriteria().andStoreIdEqualTo(storeId).andParentDepartmentIdEqualTo(0L).andSStatusEqualTo("active");
		departmentExample.setOrderByClause("id DESC");
		return departmentDAO.findByExampleWithoutSubDepartments(departmentExample);
	}

	
	public List<Department> findAPIActiveDepartmentsByStore(Long storeId) throws Exception {
		DepartmentExample departmentExample=new DepartmentExample();
		departmentExample.createCriteria().andStoreIdEqualTo(storeId).andSStatusEqualTo("active");
		departmentExample.setOrderByClause("id DESC");
		return departmentDAO.findAPIByExample(departmentExample);
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DepartmentApi> findDepartmentsByStoreForApi(Long storeId,
			Pager pager) {
		DepartmentExample departmentExample=new DepartmentExample();
		departmentExample.createCriteria().andStoreIdEqualTo(storeId).andParentDepartmentIdEqualTo(0L);
		departmentExample.setOrderByClause("id DESC");
		return departmentDAO.findByExampleForApi(departmentExample,null);
	}

	public List<GalleryImage> findAPIGalleryImagesByStore(Long storeId) throws Exception {
		
		GalleryImageExample galleryImageExample=new GalleryImageExample();
		galleryImageExample.createCriteria().andStoreIdEqualTo(storeId);
		galleryImageExample.setOrderByClause("d_created_on DESC");
		return galleryImageDAO.findAPIByExample(galleryImageExample);
	}
	
	public List<Product> findAPIProductsByStore(Long storeId) throws Exception {
		ProductExample productExample=new ProductExample();
		productExample.createCriteria().andStoreIdEqualTo(storeId);
		productExample.setOrderByClause("id DESC");
		return productDAO.findAPIByExample(productExample);
	}
	public List<Product> findAPIProductsByStore(Long storeId,Pager page) throws Exception {
		ProductExample productExample=new ProductExample();
		productExample.createCriteria().andStoreIdEqualTo(storeId);
		productExample.setOrderByClause("id DESC");
		return productDAO.findAPIByExample(productExample,page);
	}

	public List<Product> findAPIProductsByDepartment(Long departmentId) throws Exception {
		ProductExample productExample=new ProductExample();
		productExample.createCriteria().andDepartmentIdEqualTo(departmentId);
		productExample.setOrderByClause("id DESC");
		return productDAO.findAPIByExample(productExample);
	}

	public List<Product> findAPIProductsByCategory(Long categoryId) throws Exception {
		ProductExample productExample=new ProductExample();
		productExample.createCriteria().andCategoryIdEqualTo(categoryId);
		productExample.setOrderByClause("id DESC");
		return productDAO.findAPIByExample(productExample);
	}

	public Product findAPIProduct(Long productId) throws Exception {
		return productDAO.apiFind(productId);
	}

	public App findAPIAppByUser(Long userId) throws Exception {
		return appDao.findAppByUserId(userId);
	}


	
	public List<Product> findProductsByUserId(Long userId, Integer page, Integer pageSize) throws Exception {
		ProductExample productExample=new ProductExample();
		productExample.createCriteria().andUserIdEqualTo(userId);
		productExample.setOrderByClause("id DESC");
		return productDAO.findAPIByExampleByPage(productExample, page, pageSize);
	}
	
	public String generateAPIKey(User user) throws ApiKeyGenerationException {
		String apiKey = "none";
		List<Api> returnList = null;
		long apiId=0;
		try {
			ApiExample example = new ApiExample();
			example.createCriteria().andUserIdEqualTo(user.getId());
			returnList = apiDao.findByExample(example);
		} catch (ObjectRetrievalFailureException orfe) {
			logger.warn(orfe.getMessage());
		}
		 apiKey = user.getId() + "mobicart" + new Date().getTime()
				+ UUID.randomUUID();
		 apiKey = passwordEncoder.encodePassword(apiKey, null);
		try {
			if (returnList != null && returnList.size() > 0) {
				Api oldInstance = returnList.get(0);
				oldInstance.setApiKey(apiKey);
				apiDao.update(oldInstance);
				apiId=oldInstance.getId();
			} else {
				Api newInstance = new Api();
				newInstance.setUserId(user.getId());
				newInstance.setApiKey(apiKey);
				apiId = apiDao.create(newInstance);
			}
			
			Api freshInstance = apiDao.find(apiId);
			apiKey = freshInstance.getApiKey();

		} catch (DataAccessException e) {
			logger.error("error", e);
			throw new ApiKeyGenerationException(e);
		} catch (Exception e) {
			logger.error("error", e);
			throw new ApiKeyGenerationException(e);
		}
		if (StringUtils.isEmpty(apiKey)) {
			throw new ApiKeyGenerationException();
		}
		return apiKey;
	}
	
	
	public String generatePartnerAPIKey(String partnerName, String partnerEmail) throws ApiKeyGenerationException {
		String apiKey = "";
		
		 apiKey = partnerName.hashCode() + partnerEmail.hashCode() + "mobicart" + "partner" + new Date().getTime() + UUID.randomUUID();
		 apiKey = passwordEncoder.encodePassword(apiKey, null);
		
		return apiKey;
	}
	
	
	public User findUserByAPIKey(String apiKey) throws Exception {
		
		ApiExample apiExample=new ApiExample();
		apiExample.createCriteria().andApiKeyEqualTo(apiKey);
		List<Api> returnList = apiDao.findByExample(apiExample);
		Api api = null;
		if(returnList.size() > 0)
		{
			api = returnList.get(0);
		}
		return userDAO.find(api.getUserId());
	}
	
	public String deleteAPIKey(User user) throws Exception {
		
		ApiExample apiExample =new ApiExample();
		apiExample.createCriteria().andUserIdEqualTo(user.getId());
		List<Api> resultList = apiDao.findByExample(apiExample);
		if(resultList.size() > 0)
		{
			for (Api appi : resultList)
			{
				apiDao.delete(appi);
			}
		}

		return "API key deleted succesfully";
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getStringOfFeaturedProducts(Long storeId, Integer limit)   {
		
		try{
		ProductExample productExample=new ProductExample();
		productExample.createCriteria().andStoreIdEqualTo(storeId).andBFeaturedEqualTo(true);

		 Store  store=storeDAO.findStoreById(storeId);
		 User user=userDAO.find(store.getUserId());
		 
 
		    Pager pager=new Pager();
			pager.setResults((limit<=user.getStoreProductLimit())?limit:user.getStoreProductLimit());
		
		List<ProductDto> products= productDAO.findPreviewByExample(productExample, pager);
			
		logger.debug("storeId:"+storeId+",limit:"+limit+"no of getStringOfFeaturedProducts:- "+products.size());
		 
		String currencySymbol=CountryUtil.getCurrencySymbol(store.getsCurrencyCode());
		StringBuffer prodString =new StringBuffer();
		//calculate tax type and amount
		
		//String tax=productDAO.findTaxInfoByStoreId(storeId);
		String taxInfo=productDAO.findTaxInfoByStoreId_Func(storeId);
		String infoList[]=taxInfo.split("@");
		logger.debug(taxInfo+":"+infoList.length);
		double tax=0.0;
		String taxType="";
		 
		if(infoList.length>0)
		{
			try{
				  taxType=infoList[0];
				String taxAmount=infoList[1];
				tax=Double.parseDouble(taxAmount);
			}
			catch (NumberFormatException e) {
				logger.error("error",e);
			}
			
			
		}
		 
		
		
		
		
		for (ProductDto product:products) {
			String imagePath="";
			if(product.getImageSmall()!=null){
				imagePath=ResourceProperties.getString("rootUrl")+product.getImageMedium();
			}else{
				imagePath="";
			}
			
			prodString.append(imagePath);
			prodString.append("#");  // add diffrenctiator
			prodString.append(currencySymbol.trim()); // add currency 
			prodString.append(""); // space between currency code and actual amount
			//prodString.append(product.getPrice()); // actual price
			
			double priceIncludingTax=product.getDiscountedPrice()+(product.getDiscountedPrice()*tax)/100;
			 
			logger.debug("debugging:name"+product.getName()+",price:"+new DecimalFormat("0.00").format(priceIncludingTax));
			prodString.append( new DecimalFormat("0.00").format(priceIncludingTax)+"");	
			if(store.getbIncludeTax())
				{
				if(taxType!=null || (!"".equals(taxType) )){
				prodString.append(" Inc ");
				prodString.append(taxType);}
				
				}
			prodString.append("#"+product.getName());
			
			prodString.append(",");
		}
		 
		if(prodString.length()>1)
		prodString.deleteCharAt(prodString.lastIndexOf(","));
		
		
		return prodString.toString();
		}
		catch (Exception e) {
			 
			logger.error("error",e);
			 //e.printStackTrace();
		}
		return null;
		
	}

	/**
	 * {@inheritDoc}
	 */
	public String getStringOfGalleryImages(Long storeId, Integer limit) throws Exception {
		GalleryImageExample galleryImageExample =new GalleryImageExample();
		galleryImageExample.createCriteria().andStoreIdEqualTo(storeId);
		List<GalleryImage> galleryImages=galleryImageDAO.findAPIByExample(galleryImageExample);
		Integer count=0;
		StringBuffer galleryImagesString= new StringBuffer();
		for(GalleryImage galleryImage: galleryImages){
			count++;
			galleryImagesString.append(	ResourceProperties.getString("rootUrl")+galleryImage.getsThumbnail());
			galleryImagesString.append(",");
			if(count>limit)
				break;
		}
		if(galleryImagesString.length()>0)
		galleryImagesString.deleteCharAt(galleryImagesString.lastIndexOf(","));
		return galleryImagesString.toString();
	}


	
	
	/**
	 * {@inheritDoc}
	 */
	public String getJsonStringOfDepartments(long storeId, int limit) {

		try {
			DepartmentExample departmentExample = new DepartmentExample();
			departmentExample.createCriteria().andStoreIdEqualTo(storeId).andParentDepartmentIdEqualTo(0L)
					.andSStatusEqualTo("active");

			
	 
			Pager pager = new Pager();
			pager.setPage(0);
			pager.setResults(limit);

			
			//List<DepartmentDto> departments= departmentDAO.findByExample(departmentExample, pager);
			List<DepartmentDto> departments= departmentDAO.findDepartmetByIdAndMaxRowNum(departmentExample, pager);
			
			return JsonUtils.jsonFromObject(departments);
			
		} catch (DataAccessException e) {
			logger.error("error", e);
		} catch (Exception e) {
			logger.error("error", e);
		}
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<DepartmentDto> getJsonStringOfAllDepartmentsByStore(long storeId) {

		try {
			DepartmentExample departmentExample = new DepartmentExample();
			departmentExample.createCriteria().andStoreIdEqualTo(storeId).andParentDepartmentIdEqualTo(0L)
					.andSStatusEqualTo("active");

			Pager pager = new Pager();
			pager.setPage(0);
			pager.setResults(3000);/**/

			
			List<DepartmentDto> departments= departmentDAO.findByExample(departmentExample, pager);
			
			return departments;
			//return JsonUtils.jsonFromObject(departments);
			
		} catch (DataAccessException e) {
			logger.error("error", e);
		} catch (Exception e) {
			logger.error("error", e);
		}
		return null;
	}
	/**
	 * {@inheritDoc}
	 */
	
	 
	public String getJsonStringOfSubDepartments(long departmentId, int limit) {
		
		try {
			/*CategoryExample categoryExample = new CategoryExample();
			categoryExample.createCriteria().andDepartmentIdEqualTo(departmentId)
					.andSStatusEqualTo("active");*/
			
			DepartmentExample departmentexmp=new DepartmentExample();
			departmentexmp.createCriteria().andParentDepartmentIdEqualTo(departmentId).andSStatusEqualTo("active");

			Pager pager = new Pager();
			pager.setPage(0);
			pager.setResults(limit);
			
			Department department=departmentDAO.find(departmentId);
			User user=userDAO.find(department.getUserId());
			
			
			//List<SubDepartmentDto> subDepartments= categoryDAO.findByExample(categoryExample, pager);
			//return JsonUtils.jsonFromObject(subDepartments);
			List<DepartmentDto> subDepartments= departmentDAO.findDepartmetByIdAndMaxRowNum(departmentexmp, pager);
			
			
			
			
			
			
			return JsonUtils.jsonFromObject(subDepartments);
		} catch (DataAccessException e) {
			logger.error("error", e);
		} catch (Exception e) {
			logger.error("error", e);
		}
		return null;
		
	}

	/**
	 * {@inheritDoc}
	 */
	public String getJsonStringOfProductsByDepartment(long departmentId, int limit) {
		try {
			ProductExample productExample = new ProductExample();
			productExample .createCriteria().andDepartmentIdEqualTo(departmentId);
			
			
			 Department department=departmentDAO.find(departmentId);
			 User user=userDAO.find(department.getUserId());
			 
           Product product=new Product();
           product.setDepartmentId(departmentId);
           product.setUserId(department.getUserId()) ;
           product.setMaxRowNum(Long.parseLong(user.getStoreProductLimit().toString()));
			
			Pager pager = new Pager(); 
			pager.setPage(0);
			pager.setResults(limit);
			
			List<ProductDto> products= null;
			int productsCounts= user.getiProductCount();
			if(productsCounts>=user.getStoreProductLimit()){
				 
				pager.setPage(0);
				pager.setResults((limit<=user.getStoreProductLimit())?limit:user.getStoreProductLimit()); 
				products = productDAO.findPreviewByDepartmentAndMaxRowNum(product);
				
			}else
				products = productDAO.findPreviewByExample(productExample, pager);
			
			//List<ProductDto> products= productDAO.findPreviewByExample(productExample, pager);
			
			

			return JsonUtils.jsonFromObject(products);
			
		} catch (DataAccessException e) {
			logger.error("error", e);
		} catch (Exception e) {
			logger.error("error", e);
		}
		return null;
		
	}

	 
	
	/**
	 * {@inheritDoc}
	 */
	public String getJsonStringOfProductsByStore(long storeId, int limit) {
		try {
			ProductExample productExample = new ProductExample();
			productExample .createCriteria().andStoreIdEqualTo(storeId);

			Store store=storeDAO.findStoreById(storeId);
			User user=userDAO.find(store.getId());
			
			
			Pager pager = new Pager();
			pager.setPage(0);
			pager.setResults((limit<=user.getStoreProductLimit())?limit:user.getStoreProductLimit());
			
			List<ProductDto> products= productDAO.findPreviewByExample(productExample, pager);

			return JsonUtils.jsonFromObject(products);
			
		} catch (DataAccessException e) {
			logger.error("error", e);
		} catch (Exception e) {
			logger.error("error", e);
		}
		return null;
		
	}

	
	/**
	 * {@inheritDoc}
	 */
	public String getJsonStringOfProductsBySubDepartment(long subDepartmentId,
			int limit) {
		try {
			//ProductExample productExample = new ProductExample();
			//productExample .createCriteria().andCategoryIdEqualTo(subDepartmentId);
					

			Pager pager = new Pager();
			pager.setPage(0);
			pager.setResults(limit);
			
			//List<ProductDto> products= productDAO.findPreviewByExample(productExample, pager);
			List<ProductDto> products= productDAO.findPreviewByExample(subDepartmentId, pager);
			return JsonUtils.jsonFromObject(products);
			
		} catch (DataAccessException e) {
			logger.error("error", e);
		} catch (Exception e) {
			logger.error("error", e);
		}
		return null;
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getJsonStringOfPages(long userId,List<Long> featureIds ) {
		try {

			StaticPageExample staticPageExample = new StaticPageExample();
			staticPageExample.createCriteria().andUserIdEqualTo(userId).andFeatureIdIn(featureIds);

			List<PageDto> pageBeans = staticPageDAO
					.findPageBeansByExample(staticPageExample);

			return JsonUtils.jsonFromObject(pageBeans);

		} catch (DataAccessException e) {
			logger.error("error", e);
		} catch (Exception e) {
			logger.error("error", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getJsonStringOfPage(long pageId) {
		try {
			PageDto pageBean = staticPageDAO
					.findPageBean(pageId);

			return JsonUtils.jsonFromObject(pageBean);

		} catch (DataAccessException e) {
			logger.error("error", e);
		} catch (Exception e) {
			logger.error("error", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getJsonStringOfNewsItems(long storeId, int limit) {
		try {

			NewsExample newsExample = new NewsExample();
			newsExample.createCriteria().andStoreIdEqualTo(storeId).andSTypeEqualTo("custom");

			Pager pager=new Pager();
			pager.setResults(limit);
			
			List<NewsDto> newsBeans = newsDAO.findByExample(newsExample, pager);

			return JsonUtils.jsonFromObject(newsBeans);

		} catch (DataAccessException e) {
			logger.error("error", e);
		} catch (Exception e) {
			logger.error("error", e);
		}
		return null;
	}
    
	/**
	 * {@inheritDoc}
	 */
	public List<NewsDto> findNewsItemsByStore(long storeId,String keyword) {
		try {
			
			 NewsSearchDto serch=new NewsSearchDto();
			 serch.setStoreId(storeId);
			 serch.setKeyword("%"+keyword+"%");
			 
			List<NewsDto> newsList = newsDAO.searchNewsByKeyword(serch);
			
			return newsList;

		} catch (DataAccessException e) {
			logger.error("error", e);
		} catch (Exception e) {
			logger.error("error", e);
		}
		return null;
	}

	
	/**
	 * {@inheritDoc}
	 */
	public String getJsonStringOfNews(long newsId) {
		try {
			NewsDto newsBean = newsDAO.findNewsBean(newsId);
			return JsonUtils.jsonFromObject(newsBean);
		} catch (DataAccessException e) {
			logger.error("error", e);
		} catch (Exception e) {
			logger.error("error", e);
		}
		return null;
	}

	
	
	/**
	 * {@inheritDoc}
	 */
	
	
	public String getJsonStringOfTweets(long storeId, int limit) {
		try {
		//get twitter username for store
		String twitterUsername=getTwitterUsername(storeId);
		

		//System.setProperty("http.proxyHost", "192.168.0.254");
		///System.setProperty("http.proxyPort", "3128");
		//System.setProperty("http.nonProxyHosts", "localhost|127.0.0.1");
		
		//fetch tweets vi api
		Twitter twitter = new TwitterFactory().getInstance();
		ResponseList<Status> statuses;
		
		statuses = twitter.getUserTimeline(twitterUsername);
		
		List<TweetDto> tweets=new ArrayList<TweetDto>();  
		
		for (Status status : statuses) {
			TweetDto tweet=new TweetDto();
			tweet.setName(status.getUser().getScreenName());
			tweet.setImage(status.getUser().getProfileImageURL().toString());
			tweet.setText(status.getText());
			tweet.setCreatedAt(status.getCreatedAt());
			tweets.add(tweet);
		} 
		
		return JsonUtils.jsonFromObject(tweets);
		
		} catch (TwitterException e) {
			logger.warn("unable to get tweets twitter exception {}", e.getLocalizedMessage());
		}catch (Exception e) {
			logger.error("unable to get tweets {}", e.getLocalizedMessage());
		}
		
		
		
		return null;
	}
	
	
	
	public List getJsonStringOfTweetsIphone(long storeId, int limit) {
		try {
		//get twitter username for store
		String twitterUsername=getTwitterUsername(storeId);
		

		//System.setProperty("http.proxyHost", "192.168.0.254");
		///System.setProperty("http.proxyPort", "3128");
		//System.setProperty("http.nonProxyHosts", "localhost|127.0.0.1");
		
		//fetch tweets vi api
		Twitter twitter = new TwitterFactory().getInstance();
		ResponseList<Status> statuses;
		
		statuses = twitter.getUserTimeline(twitterUsername);
		
		List<TweetDto> tweets=new ArrayList<TweetDto>();  
		
		for (Status status : statuses) {
			TweetDto tweet=new TweetDto();
			tweet.setName(status.getUser().getScreenName());
			tweet.setImage(status.getUser().getProfileImageURL().toString());
			tweet.setText(status.getText());
			tweet.setCreatedAt(status.getCreatedAt());
			tweets.add(tweet);
		} 
		
		//return JsonUtils.jsonFromObject(tweets);
		
		
		return tweets;
		
		} catch (TwitterException e) {
			logger.warn("unable to get tweets twitter exception {}", e.getLocalizedMessage());
		}catch (Exception e) {
			logger.error("unable to get tweets {}", e.getLocalizedMessage());
		}
		
		
		
		return null;
	}
	
	
	
	/**
	 * {@inheritDoc}
	 */
	public String getTwitterUsername(long storeId) {
		try {
		//fetch twitter username
		NewsExample newsExample = new NewsExample();
		newsExample.createCriteria().andStoreIdEqualTo(storeId).andSTypeEqualTo("feed");
		List<News> newsItems= newsDAO.findByExample(newsExample);
		
		
		for (News news : newsItems) {
				// it will always be one record hence it will be return as it is
				return news.getsTwitterUsername();
		}
		
		} catch (DataAccessException e) {
			logger.error("get twitter username dae {}", e.getMostSpecificCause());
		} catch (Exception e) {
			logger.error("get twitter username e {}", e.getCause());
		}
		return null;
	}

	
	/**
	 * {@inheritDoc}
	 */
	 
	public String getJsonStringOfFeedPosts(long storeId, int limit) {
		try {
			 logger.debug("getJsonStringOfFeedPosts:");
			NewsExample newsExample = new NewsExample();
			newsExample.createCriteria().andStoreIdEqualTo(storeId).andSTypeEqualTo("feed");
			List<News> newsItems= newsDAO.findByExample(newsExample);
			
			if(logger.isDebugEnabled())logger.debug("getting feed posts for store id {} ",storeId);
			
			List<FeedDto> feedPosts=null;
			
			String feedUrl=null;
			
			for (News news : newsItems) {
					// it will always be one record hence it will be return as it is
				feedUrl= news.getsFeedUrl();
			}
			
			if(logger.isDebugEnabled())logger.debug("getting feed posts for url {}", feedUrl );
			
			if(feedUrl!=null){
				feedPosts= FeedParserUtils.getFeedPosts(feedUrl);
			    return JsonUtils.jsonFromObject(feedPosts);
			}
			
			  if(logger.isDebugEnabled())logger.debug("nothing found in feed posts" );
			
			} catch (DataAccessException e) {
				logger.error("get feed posts dae{}", e.getMostSpecificCause());
			} catch (Exception e) {
				logger.error("get feed posts e {}", e.getCause());
			}
			return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public Map<String,Object> findStoreProductsWithPagination(Long storeId,Pager pager) throws Exception{
			return productDAO.findStoreProductsWithPagination(storeId,pager);
	}

	/**
	 * {@inheritDoc}
	 */
	public Long createProductOrderShippingDetail(
			ProductOrderShippingDetail productOrderShippingDetail) throws Exception {
		return (Long)productOrderShippingDetailDAO.create(productOrderShippingDetail);
	}
	
	/**
	 * {@inheritDoc}
	 */

	public ProductOrderShippingDetail findProductOrderShippingDetail(
			String trackingNumber) throws Exception {
		
		List<ShipmentTrackDetail> shipmentTrackDetails=	FedexTrackWebServiceClient.findDetails(trackingNumber);
		if(shipmentTrackDetails!=null){
			String shippingRemarks=new String();
			for(ShipmentTrackDetail shipmentTrackDetail:shipmentTrackDetails){
				shippingRemarks=shipmentTrackDetail.getStatusDescription();
			}
			ProductOrderShippingDetailExample example=new ProductOrderShippingDetailExample();
			example.createCriteria().andSTrackingNumberLike(trackingNumber);
			ProductOrderShippingDetail detail=productOrderShippingDetailDAO.findByExample(example);
			detail.setsShippingRemarks(shippingRemarks);
			productOrderShippingDetailDAO.update(detail);	
		}
		
		ProductOrderShippingDetailExample example=new ProductOrderShippingDetailExample();
		example.createCriteria().andSTrackingNumberLike(trackingNumber);	
		return productOrderShippingDetailDAO.findByExample(example);
		
	}
	
	/**
	 * 	{@inheritDoc}
	 */
	public void updateProductOrderShippingDetail(
			ProductOrderShippingDetail productOrderShippingDetail) throws Exception {
		productOrderShippingDetailDAO.update(productOrderShippingDetail);
		
	}
	
	/**
	 * {@inheritDoc}
	 */

	public Integer getProductRating(Long productId) {
		return productReviewDAO.getProductRating(productId);
	}		

	/**
	 * {@inheritDoc}
	 */
	public String generateOauthSecret(User user)
			throws ConsumerSecretGenerationException {

		String consumerSecret = "none";
		List<Api> returnList = null;
		try {

			ApiExample example = new ApiExample();
			example.createCriteria().andUserIdEqualTo(user.getId());
			returnList = apiDao.findByExample(example);

		} catch (ObjectRetrievalFailureException orfe) {
			logger.warn(orfe.getMessage());

		}

		String apiKey = user.getId() + "mobicart" + new Date().getTime()
				+ UUID.randomUUID();
		consumerSecret = passwordEncoder.encodePassword(apiKey, user.getId());

		try {
			if (returnList != null && returnList.size() > 0) {
				Api oldInstance = returnList.get(0);
				// update only if the secret does not exists
				if (StringUtils.isEmpty(oldInstance.getOauthSecret())) {
					oldInstance.setOauthSecret(consumerSecret);
					apiDao.update(oldInstance);
				} else {
					consumerSecret = oldInstance.getOauthSecret();
				}

			} else {
				Api newInstance = new Api();
				newInstance.setUserId(user.getId());
				newInstance.setOauthSecret(consumerSecret);
				long apiId = apiDao.create(newInstance);
				Api freshInstance = apiDao.find(apiId);
				consumerSecret = freshInstance.getOauthSecret();

			}
		} catch (DataAccessException e) {
			logger.error("error {}", e.getMostSpecificCause());
			throw new ConsumerSecretGenerationException(e);
		} catch (Exception e) {
			logger.error("error {}", e.getCause());
			throw new ConsumerSecretGenerationException(e);
		}
		if (StringUtils.isEmpty(consumerSecret)) {
			throw new ConsumerSecretGenerationException();
		}

		return consumerSecret;
	}

	 

 

	@Override
	public Api findApiByAPIKey(String apiKey) throws Exception {
		ApiExample example=new ApiExample();
		example.createCriteria().andApiKeyEqualTo(apiKey);
		return apiDao.findByExample(example).get(0);
	}

	/**
	 * {@inheritDoc}
	 */
 
	public Api updateApi(Api api) {
		try {
			if(logger.isDebugEnabled())logger.debug("updating api.. {}", api);
				apiDao.update(api);
			return apiDao.find(api.getId());
		} catch (DataAccessException e) {
			logger.error("dae api update", e);
		} catch (Exception e) {
			logger.error("update api errro ", e);
		}
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 */
 
	public Api findApiByUserId(long userId) {
		try {
			ApiExample example = new ApiExample();
			example.createCriteria().andUserIdEqualTo(userId);
			List<Api> apiList=apiDao.findByExample(example);
			if(apiList!=null && apiList.size()>0) return apiList.get(0);
		} catch (DataAccessException e) {
			logger.error("find api ae", e);
		} catch (Exception e) {
			logger.error("find api", e);
		}
		return null;
	}

	@Override
	public List<ProductOption> findProductOptionByExample(
			ProductOption productOption) throws Exception {
		
		ProductOptionExample productOptionExample = new ProductOptionExample();
		productOptionExample.createCriteria().andProductIdEqualTo(productOption.getProductId())
			.andsTitleEqualTo(productOption.getsTitle()).andSNameEqualTo(productOption.getsName());
		List<ProductOption> productOptions = productOptionDAO.findByExample(productOptionExample);
		return productOptions;
	}

	@Override
	public Long findParentDepartmentId(Long subDepartmentId){
		return   departmentDAO.findParentDepartmentId(subDepartmentId);
	}
	
	public Boolean updateAddress(Address address)  throws Exception{
		return addressDAO.update(address);
	}

	@Override
	public boolean getBrandingFlag(String  username) {
		int flag=appDao.getRemoveBrandingStatus(username);
		
		if(flag==1)
			return false;
		 
		 
		return true;
	}
	public String getCurrencyCodeByStoreId(Long  storeId)
	{
		Store store=null;
		try{
	    store=findStoreById(storeId);		 		
	             }
		catch (Exception e) {
			logger.error("error",e); 
		}
		
		return store.getsCurrencyCode();
	}
	public String getTaxInfoByStoreId(Long  storeId)
	{
		String taxInfo=productDAO.findTaxInfoByStoreId_Func(storeId);
		String infoList[]=taxInfo.split("@");
		logger.debug(taxInfo+":"+infoList.length);
		double tax=0.0;
		String taxtType="default";
		 
		if(infoList.length>0)
		{
			try{
				  taxtType=infoList[0];
				String textAmount=infoList[1];
				tax=Double.parseDouble(textAmount);
			}
			catch (NumberFormatException e) {
				logger.error("error",e);
			}
			
			
		}
		return tax+","+taxtType;
	}

	@Override
	public List<Product> findProductsByName(String productName,Long dptid) {
		 ProductExample pro=new ProductExample();
		 pro.createCriteria().andSNameEqualTo(productName).andDepartmentIdEqualTo(dptid);
		 
		 
		 List<Product> productList=(List<Product>)productDAO.findByExample(pro);
		 
		return productList;
	}
	
	
	public Long findProductsCountByDepartmentAndName(String productName,Long dptId){
		
		ProductExample pro=new ProductExample();
		 pro.createCriteria().andSNameEqualTo(productName).andDepartmentIdEqualTo(dptId);
		 
		 
		 Long productCount = productDAO.countByExample(pro);
		 
		return productCount;
	}

	@Override
	public List<String> getProductImagesListByUserId(Long userId) throws Exception {

		List<String> bulkListForProductsImages=new  ArrayList<String>(); 
	    
		   ProductExample proexmp=new ProductExample();
		      proexmp.createCriteria().andUserIdEqualTo(userId);
		      List<Product> productList=productDAO.findByExample(proexmp);
	     
	     for(Product product:productList){
	   
	     
	      if(product==null)
	    	  continue;
	      List<ProductImage> pimg=product.getProductImages();
	      
	           for(ProductImage proimg:pimg){
	        	   if(proimg==null)
	        		   continue;
	        	   bulkListForProductsImages.add(proimg.getsLocation());
	           }
	     }
	     
	     
	     
		return bulkListForProductsImages;
	}

	@Override
	public List<GalleryImage> getGalleryImageByUserId(Long userId) throws Exception {
		GalleryImageExample gime=new GalleryImageExample();   
	    gime.createCriteria().andUserIdEqualTo(userId);
	    List<GalleryImage> bulkListForGallary=galleryImageDAO.findByExample(gime);
		return bulkListForGallary;
	}
 

	@Override
	public Territory getTerritoryForPaymentTotals(String shippingTerritoryName) throws SQLException {
		// TODO Auto-generated method stub	
		Territory territory = null;
    	TerritoryExample territoryExample = new TerritoryExample();
    	territoryExample.createCriteria().andSNameEqualTo(shippingTerritoryName);
    	List territories = territoryDAO.findTerritoriesByExample(territoryExample);
    	if(territories.size() > 0)
    	territory = (Territory)(territories.get(0));
    	
		return territory;
	}

	@Override
	public State getStateForPaymentTotals(String state, Long territoryId)
			throws SQLException {
		// TODO Auto-generated method stub
		
		State stte = null;
    	StateExample stateExample = new StateExample();
    	stateExample.createCriteria().andSNameEqualTo(state).andTerritoryIdEqualTo(territoryId);
    	List sttes = stateDAO.findByExample(stateExample);
    	
    	if(sttes.size() > 0)
    	stte = (State)(sttes.get(0));
    	
		return stte;
	}

	@Override
	public BigDecimal getShippingAmountForPayment(BigDecimal fshippingAmount,Long storeId,
			Territory territory, Integer quntity, State stte) throws SQLException {
		// TODO Auto-generated method stub
		
		ShippingExample shippingExample = new ShippingExample();
    	shippingExample.createCriteria().andStoreIdEqualTo(storeId).andTerritoryIdEqualTo(territory.getId()).andStateIdEqualTo(stte.getId());
    	BigDecimal shiPPingAmount = null;
    	List shippings = shippingDAO.findByExample(shippingExample);
    	
    	if(shippings != null && shippings.size() > 0){
        	Shipping shipping = (Shipping)shippings.get(0);
        	
        	if(shipping != null){
	        	if(quntity == 1)
	        		if(fshippingAmount == null){
	        			BigDecimal sShipAmount = new BigDecimal("0.0");
	        			shiPPingAmount = new BigDecimal(shipping.getfAlone().doubleValue() + sShipAmount.doubleValue());
	        			//productOrder.setfShippingAmount(new BigDecimal("0.0"));
	        		}else{
	        			shiPPingAmount = new BigDecimal(shipping.getfAlone().doubleValue()+fshippingAmount.doubleValue());
	        		}
	        	else if(quntity > 1)
	        		if(fshippingAmount == null){
	        			BigDecimal sShipAmount = new BigDecimal("0.0");
	        			shiPPingAmount = new BigDecimal(shipping.getfOthers().doubleValue() + sShipAmount.doubleValue());
	        			//productOrder.setfShippingAmount(new BigDecimal("0.0"));
	        		}else{
	        			shiPPingAmount = new BigDecimal(shipping.getfOthers().doubleValue()+fshippingAmount.doubleValue());
	        		}
	        	else if(quntity == 0)
	        		shiPPingAmount = new BigDecimal("0.0");
        	}else{
        		shiPPingAmount = new BigDecimal("0.0");
        	}
    	}else{
    		shiPPingAmount = new BigDecimal("0.0");
    	}
		return shiPPingAmount;
	}

	@Override
	public BigDecimal getFTaxAmount(Long storeId, Long territoryId, Long stateId,Double priceValue,Integer quntity,BigDecimal ftaxAmount) {
		TaxExample taxExample = new TaxExample();
		Tax storeTax = null;
    	taxExample.createCriteria().andStoreIdEqualTo(storeId).andTerritoryIdEqualTo(territoryId).andStateIdEqualTo(stateId);
    	List taxList = taxDAO.findByExample(taxExample);
    	if(taxList != null && taxList.size() > 0){
    		storeTax = (Tax)(taxList.get(0));
    		BigDecimal storeTaxValue = storeTax.getfTax();
    		BigDecimal fTaxAmount = new BigDecimal((priceValue*quntity*storeTaxValue.doubleValue())/100);
    		if(ftaxAmount == null){
    			BigDecimal fTaxVal = new BigDecimal("0.0");
    			ftaxAmount = new BigDecimal(fTaxVal.doubleValue() + fTaxAmount.doubleValue());
    		}else{
    			ftaxAmount = new BigDecimal(ftaxAmount.doubleValue() + fTaxAmount.doubleValue());
    	}
    	}
		return ftaxAmount;
	}

	@Override
	public List<Product> findProducts(List<Long> productId) {
		ProductExample productExmpl=new ProductExample();
		productExmpl.createCriteria().andIdIn(productId);
		List<Product> productlist=productDAO.findByExample(productExmpl);
		return productlist;
	}	
 
	
}
