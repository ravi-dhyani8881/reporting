package com.mobicart.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.mobicart.model.ApiPartner;
import com.mobicart.model.ApiPartnerExample;
import com.mobicart.model.App;
import com.mobicart.model.MerchantPartner;
import com.mobicart.model.MerchantPartnerExample;
import com.mobicart.model.Order;
import com.mobicart.model.Product;
import com.mobicart.model.ProductOrder;
import com.mobicart.model.ProductOrderItem;
import com.mobicart.model.ProductOrderItemWebapp;
import com.mobicart.model.ProductOrderItemWebappExample;
import com.mobicart.model.ProductOrderWebapp;
import com.mobicart.model.ProductOrderWebappExample;
import com.mobicart.model.SiteConstant;
import com.mobicart.model.Store;
import com.mobicart.model.User;

/**
 * it deals with super admin task and other backroom chores
 * @author jasdeep.singh
 *
 */
public interface AdminService {
	
	/**
	 * Finds a SiteConstant instance
	 * @return {@link SiteConstant} instance
	 */
	public SiteConstant findSiteConstants();
	
	/**
	 * Updates a SiteConstant instance
	 * @param siteConstant {@link SiteConstant} instance
	 */
	public void updateSiteConstants(SiteConstant siteConstant);
	
	/**
	 * Saves a ProductOrder instance
	 * @param newInstance {@link ProductOrder} instance
	 * @return Long {@link ProductOrder} Id 
	 * @throws Exception
	 */
	public Long saveProductOrder(ProductOrder newInstance) throws Exception;
	
	/**
	 * Saves a ProductOrderItem instance
	 * @param newInstance {@link ProductOrderItem} instance
	 * @return Long {@link ProductOrderItem} Id
	 * @throws Exception
	 */
	public Long saveProductOrderItem(ProductOrderItem newInstance) throws Exception;
	
	/**
	 * Finds an Order instance
	 * @param payerId Payer Id
	 * @param appId {@link App} Id
	 * @return {@link Order} instance
	 */
	public Order findAppOrderByPayerIdAppId(String payerId,Long appId);
	
	/**
	 * Finds an Order instance
	 * @param txId Transaction Id
	 * @return {@link Order} instance
	 */
	public Order findAppOrderByTransactionId(String txId);
	
	/**
	 * Manages product inventory
	 * @param productOrderItem {@link ProductOrderItem} instance
	 * @return boolean
	 */
	public boolean manageProductInventory(ProductOrderItem productOrderItem);
	
	/**
	 * Finds ProductOrder instance
	 * @param id {@link ProductOrder} Id
	 * @return {@link ProductOrder} instance
	 */
	public ProductOrder findProductOrder(Long id);
	
	/**
	 * Finds ProductOrderItem instance
	 * @param id {@link ProductOrderItem} Id
	 * @return {@link ProductOrderItem} instance
	 */
	public ProductOrderItem findProductOrderItem(Long id);

	/**
	 * Finds a list of the ProductOrder instances
	 * @param storeId {@link Store} Id
	 * @param appId {@link App} Id
	 * @param buyerEmail Buyer email
	 * @return List of ProductOrder instances
	 */
	public List<ProductOrder> getProductOrderHistory(Long storeId,Long appId,String buyerEmail);
	
	/**
	 * Finds a list of ProductOrder instances
	 * @param storeId {@link Store} Id
	 * @return List of ProductOrder instances
	 */
	public List<ProductOrder> getProductOrderHistoryAPI(Long storeId);

	
	
	
	/***************   API METHODS ****************/
	
	/**
	 * Finds a ProductOrder instance
	 * @param orderId {@link ProductOrder} Id
	 * @return {@link ProductOrder} instance
	 */
	public ProductOrder findAPIProductOrder(Long orderId) throws SQLException;
	
	/**
	 * Finds a list of ProductOrder instances
	 * @param fromDate Start date
	 * @param toDate End date
	 * @param userId User Id
	 * @return List of {@link ProductOrder} instances
	 * @throws SQLException
	 */
	public List<ProductOrder> findAPIProductOrderByDate(Date fromDate, Date toDate, Long userId) throws SQLException;
	
	/**
	 * Finds a list of ProductOrder instances
	 * @param userId {@link User} Id
	 * @return List of ProductOrder instances
	 * @throws SQLException
	 */
	public List<ProductOrder> findAPIProductOrderByUser(Long userId)throws SQLException;
	
	/**
	 * Updates Merchant profile
	 * @param user {@link User} instance
	 * @throws SQLException
	 */
	public void updateMercahntProfile(User user)throws SQLException;
	
	/**
	 * Finds a list of ProductOrder Id
	 * @param productId {@link Product} id
	 * @return List of ProductOrder Id
	 */
	public List<Long> findOrderIdsByProductId(Long productId);
	
	/**
	 * Saves a ProductOrder instance
	 * @param newInstance {@link ProductOrder} instance
	 * @return Long {@link ProductOrder} Id 
	 * @throws Exception
	 */
	public Long saveProductOrderWebapp(ProductOrderWebapp newInstance) throws Exception;
	
	/**
	 * Saves a ProductOrderItem instance
	 * @param newInstance {@link ProductOrderItem} instance
	 * @return Long {@link ProductOrderItem} Id
	 * @throws Exception
	 */
	public Long saveProductOrderItemWebapp(ProductOrderItemWebapp newInstance) throws Exception;


	/**
	 * Finds ProductOrderWebapp instance
	 * @param id {@link ProductOrderWebapp} Id
	 * @return {@link ProductOrderWebapp} instance
	 */
	public List<ProductOrderWebapp> findProductOrderWebappByExample(ProductOrderWebappExample productOrderWebappExample) throws Exception;
	
	/**
	 * Finds ProductOrderItemWebapp instance
	 * @param id {@link ProductOrderItemWebapp} Id
	 * @return {@link ProductOrderItemWebapp} instance
	 */
	public List<ProductOrderItemWebapp> findProductOrderItemWebappByExample(ProductOrderItemWebappExample productOrderItemWebappExample) throws Exception;
	
	/**
	 * delete ProductOrderWebapp instance
	 * @param id {@link ProductOrderWebapp} Id
	 * @return {@link ProductOrderWebapp} instance
	 */
	public void deleteProductOrderWebapp(ProductOrderWebapp productOrderWebapp);
	
	/**
	 * delete ProductOrderItemWebapp instance
	 * @param id {@link ProductOrderItemWebapp} Id
	 * @return {@link ProductOrderItemWebapp} instance
	 */
	public void deleteProductOrderItemWebapp(ProductOrderItemWebapp productOrderItemWebapp);
	
	 
	public void deleteMobicart(String Path);
	
	
	/**
	 * Saves a ProductOrder instance
	 * @param newInstance {@link ProductOrder} instance
	 * @return Long {@link ProductOrder} Id 
	 * @throws Exception
	 */
	public Integer saveApiPartner(ApiPartner apiPartner) throws Exception;
	
	public ApiPartner findApiPartner(ApiPartnerExample apiPartnerExample) throws Exception;
	
	public Integer saveMerchantPartner(MerchantPartner merchantPartner) throws Exception;
	
	public List<MerchantPartner> findMerchantsForPartner(MerchantPartnerExample merchantPartnerExample) throws Exception;
}
