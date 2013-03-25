package com.mobicart.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.im4java.core.ImageMagickCmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobicart.dao.OrderDAO;
import com.mobicart.dao.ProductDAO;
import com.mobicart.dao.ProductOptionDAO;
import com.mobicart.dao.ProductOrderDAO;
import com.mobicart.dao.ProductOrderItemDAO;
import com.mobicart.dao.ProductOrderItemWebappDAO;
import com.mobicart.dao.ProductOrderWebappDAO;
import com.mobicart.dao.SiteConstantDAO;
import com.mobicart.dao.UserDAO;
import com.mobicart.dao.impl.ApiPartnerDAO;
import com.mobicart.dao.impl.MerchantPartnerDAO;
import com.mobicart.model.ApiPartner;
import com.mobicart.model.ApiPartnerExample;
import com.mobicart.model.MerchantPartner;
import com.mobicart.model.MerchantPartnerExample;
import com.mobicart.model.Order;
import com.mobicart.model.OrderExample;
import com.mobicart.model.Product;
import com.mobicart.model.ProductOption;
import com.mobicart.model.ProductOptionExample;
import com.mobicart.model.ProductOrder;
import com.mobicart.model.ProductOrderExample;
import com.mobicart.model.ProductOrderItem;
import com.mobicart.model.ProductOrderItemWebapp;
import com.mobicart.model.ProductOrderItemWebappExample;
import com.mobicart.model.ProductOrderWebapp;
import com.mobicart.model.ProductOrderWebappExample;
import com.mobicart.model.SiteConstant;
import com.mobicart.model.User;

@Service
public class ImplAdminService implements AdminService {
	
	private final Logger logger=LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SiteConstantDAO siteConstantDAO;

	@Autowired
	private ProductOrderDAO productOrderDAO;

	@Autowired
	private ProductOrderItemDAO productOrderItemDAO;


	@Autowired
	private ProductOptionDAO productOptionDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private ApiPartnerDAO apiPartnerDAO;
	
	@Autowired
	private MerchantPartnerDAO merchantPartnerDAO;
	
	@Autowired
	OrderDAO orderDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	private ProductOrderWebappDAO productOrderWebappDAO;
	
	@Autowired
	private ProductOrderItemWebappDAO productOrderItemWebappDAO;

	/**
	 * {@inheritDoc}
	 */
	public SiteConstant findSiteConstants() {
		SiteConstant siteConstant = null;
		List<SiteConstant> siteConstants = siteConstantDAO.findAll();
		if (siteConstants.size() > 0) {
			siteConstant = siteConstants.get(0);
		}
		return siteConstant;
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateSiteConstants(SiteConstant siteConstant) {
		siteConstantDAO.update(siteConstant);
	}

	/**
	 * {@inheritDoc}
	 */
	public Long saveProductOrder(ProductOrder newInstance) throws Exception {
		// if order date is not set, do it now
		return productOrderDAO.create(newInstance);
	}

	/**
	 * {@inheritDoc}
	 */
	public Long saveProductOrderItem(ProductOrderItem newInstance) throws Exception {
		return productOrderItemDAO.create(newInstance);
	}

	/**
	 * {@inheritDoc}
	 */
	public ProductOrder findProductOrder(Long id) {
		return productOrderDAO.find(id);
	}

	/**
	 * {@inheritDoc}
	 */
	public ProductOrderItem findProductOrderItem(Long id) {
		return productOrderItemDAO.find(id);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ProductOrder> getProductOrderHistoryAPI(Long storeId) {
		ProductOrderExample example = new ProductOrderExample();
		example.createCriteria().andStoreIdEqualTo(storeId);
		example.setOrderByClause("d_order_date desc");
		return productOrderDAO.findByExample(example);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<ProductOrder> getProductOrderHistory(Long storeId, Long appId,
			String buyerEmail) {
		ProductOrderExample example = new ProductOrderExample();
		example.createCriteria().andStoreIdEqualTo(storeId)
				.andAppIdEqualTo(appId).andSBuyerEmailEqualTo(buyerEmail);
		example.setOrderByClause("d_order_date desc");
		return productOrderDAO.findByExample(example);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean manageProductInventory(ProductOrderItem productOrderItem) {
		
		if(logger.isDebugEnabled())logger.debug("manage product inventory");
		try {
			Product product = productDAO.find(productOrderItem.getProductId());

			boolean bStockControlOnProduct=product.getbStockControl()!=null?product.getbStockControl():false;
			
			boolean bStockControlOnOptions=product.getbUseOptions()!=null?product.getbUseOptions():false;

			int soldQuantity = productOrderItem.getiQuantity() != null ? productOrderItem
					.getiQuantity() : 0;
			
			if (bStockControlOnProduct) {

				if(logger.isDebugEnabled())logger.debug("stock control on product ");
				
				int aggregateQuantity = product.getiAggregateQuantity()!= null ? product.getiAggregateQuantity() : 0;

				// decrement product
				aggregateQuantity = aggregateQuantity - soldQuantity;
				
				if (aggregateQuantity >= 0) {
					product.setiAggregateQuantity(aggregateQuantity);
				}
				if (aggregateQuantity == 0) {
					product.setsStatus("sold");
				}
				productDAO.update(product);
				
				return true;
				
			}
			
			if( bStockControlOnOptions){
				
				if(logger.isDebugEnabled())logger.debug("stock control on options  ");
				
				Long optionId= productOrderItem.getProductOptionId()!=null?productOrderItem.getProductOptionId():0L;
				
				ProductOptionExample productOptionExample = new ProductOptionExample();
				productOptionExample.createCriteria()
						.andProductIdEqualTo(productOrderItem.getProductId())
						.andIdEqualTo(optionId);
				List<ProductOption> productOptions = productOptionDAO
						.findByExample(productOptionExample);

				ProductOption productOption = null;
				if (productOptions != null && productOptions.size() > 0) {
					productOption = productOptions.get(0);
				}
				// dicriment quantity
				if (productOption != null) {

					int oldAvailableQuantity = productOption
							.getiAvailableQuantity() != null ? productOption
							.getiAvailableQuantity() : 0;
					int newAvailableQuantity = oldAvailableQuantity
							- soldQuantity;

					
					int oldToBeShippedQuantity = productOption
							.getiToBeShippedQuantity() != null ? productOption
							.getiToBeShippedQuantity() : 0;
					int newToBeShippedQuantity = oldToBeShippedQuantity
							+ soldQuantity;

					productOption.setiAvailableQuantity(newAvailableQuantity);
					productOption
							.setiToBeShippedQuantity(newToBeShippedQuantity);

					productOptionDAO.update(productOption);

					return true;
					
				} else {
					
					return false;
				}
			}
		} catch (Exception e) {
			logger.error("Error in managing product inventory",e);
		}

		return true;
	}

	
	/**
	 * {@inheritDoc}
	 */
	public Order findAppOrderByPayerIdAppId(String payerId,Long appId) {
		Order order=null;
		OrderExample  orderExample=new OrderExample();
		orderExample.createCriteria().andPayerIdEqualTo(payerId).andAppIdEqualTo(appId);
		orderExample.setOrderByClause("d_order_date DESC");
		List<Order> orders=orderDAO.findByExample(orderExample);
		if(orders!=null&&orders.size()>0){
			order=orders.get(0);
		}
		return order;
	}

	/**
	 * {@inheritDoc}
	 */
	public Order findAppOrderByTransactionId(String txId) {
		Order order=null;
		OrderExample  orderExample=new OrderExample();
		orderExample.createCriteria().andTxnIdEqualTo(txId);
		orderExample.setOrderByClause("d_order_date DESC");
		List<Order> orders=orderDAO.findByExample(orderExample);
		if(orders!=null&&orders.size()>0){
			order=orders.get(0);
		}
		return order;
	}
	
	/***************   API METHODS ****************/
	
	/**
	 * {@inheritDoc}
	 */
	public ProductOrder findAPIProductOrder(Long orderId) throws SQLException {
		return productOrderDAO.apiFind(orderId);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ProductOrder> findAPIProductOrderByDate(Date fromDate, Date toDate, Long userId) throws SQLException {
		ProductOrderExample productOrderExample = new ProductOrderExample();
		
		Calendar fromcalendar = Calendar.getInstance();
		fromcalendar.setTime(fromDate);
		fromcalendar.add(Calendar.DATE, -1);

		Calendar tocalendar = Calendar.getInstance();
		tocalendar.setTime(toDate);
		tocalendar.add(Calendar.DATE, +1);
		
		fromDate = fromcalendar.getTime();
		toDate = tocalendar.getTime();
		
		List<Date> dateList = new ArrayList<Date>();
		dateList.add(fromDate);
		dateList.add(toDate);
		productOrderExample.createCriteria().andDOrderDateBetween(fromDate, toDate).andMerchantIdEqualTo(userId);
		
		return productOrderDAO.findAPIByExample(productOrderExample);
	}

	
	
	/**
	 * {@inheritDoc}
	 */
	public List<ProductOrder> findAPIProductOrderByUser(Long userId) throws SQLException {
		ProductOrderExample productOrderExample = new ProductOrderExample();
		
		productOrderExample.createCriteria().andMerchantIdEqualTo(userId);
		return productOrderDAO.findAPIByExample(productOrderExample);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void updateMercahntProfile(User user) throws SQLException {
		userDAO.update(user);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<Long> findOrderIdsByProductId(Long productId){
		
		return productOrderItemDAO.findOrderIdsByProductId(productId);
	}


	/**
	 * {@inheritDoc}
	 */
	public Long saveProductOrderWebapp(ProductOrderWebapp newInstance)
			throws Exception {
		return productOrderWebappDAO.create(newInstance);
	}


	/**
	 * {@inheritDoc}
	 */
	public Long saveProductOrderItemWebapp(ProductOrderItemWebapp newInstance)
			throws Exception {
		return productOrderItemWebappDAO.create(newInstance);
	}


	/**
	 * {@inheritDoc}
	 */
	public List<ProductOrderWebapp> findProductOrderWebappByExample(ProductOrderWebappExample productOrderWebappExample)
			throws Exception {
		
		return productOrderWebappDAO.findByExample(productOrderWebappExample);
	}


	/**
	 * {@inheritDoc}
	 */
	public List<ProductOrderItemWebapp> findProductOrderItemWebappByExample(
			ProductOrderItemWebappExample productOrderItemWebappExample)
			throws Exception {
		
		return productOrderItemWebappDAO.findByExample(productOrderItemWebappExample);
	}


	/**
	 * {@inheritDoc}
	 */
	public void deleteProductOrderWebapp(ProductOrderWebapp productOrderWebapp) {
		productOrderWebappDAO.delete(productOrderWebapp);
	}


	/**
	 * {@inheritDoc}
	 */
	public void deleteProductOrderItemWebapp(ProductOrderItemWebapp productOrderItemWebapp) {
		productOrderItemWebappDAO.delete(productOrderItemWebapp);
	}

	@Override
	public void deleteMobicart(String path) {
		// TODO Auto-generated method stub
		 try 
			{ 
			   
			logger.debug("going to delete mobicart:"+path);	
			
			/*find the war file path*/
			
			String pathforWarFile;
			String cmd;
			if(isWindowXp()){ 
				
				int start=0;
				int end=path.lastIndexOf('\\');
				String justTheName = path.substring(start, end);
				 				
				pathforWarFile=justTheName + ".war" ;
				
			  cmd="del /f/s/q/a "+path+"\\request_failed.jsp";
			  }
			else {
				int start=0;
				int end=path.indexOf('/');
				String justTheName = path.substring(start, end);
			 				
				pathforWarFile=justTheName + ".war" ;
			   cmd="rm -rf "+path;
			   
			   /*stop the service */
			   try{
			   Process p3=Runtime.getRuntime().exec("service tomcat6 stop"); 
				//p3.waitFor(); 
			   }
				catch (Exception e) {
					e.printStackTrace();
				}
								
			    }
			/*remove war if exist*/
			//Process p2=Runtime.getRuntime().exec(pathforWarFile); 
			//p2.waitFor(); 
			
			
			/*remove project*/			
			logger.debug(cmd);
			Process p=Runtime.getRuntime().exec(cmd); 
			p.waitFor(); 
			BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 
			String line=reader.readLine(); 
			while(line!=null) 
			{ 
			logger.debug(line); 
			line=reader.readLine(); 
			} 

			
			
			
			
			} 
			catch(IOException e1) {} 
			catch(InterruptedException e2) {} 

			logger.debug("Done"); 
			 
	}
	private static boolean  isWindowXp(){
		
		String osName=System.getProperty("os.name"); 
		//logger.info("os name is:"+osName);
		if(osName.toLowerCase().contains("xp"))
			return true;
		return  false;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	public Integer saveApiPartner(ApiPartner apiPartner) throws Exception {
		// if order date is not set, do it now
		return apiPartnerDAO.insertApiPartnerSelective(apiPartner);
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	public ApiPartner findApiPartner(ApiPartnerExample apiPartnerExample) throws Exception {
		// if order date is not set, do it now
		return (ApiPartner)apiPartnerDAO.selectApiPartnerByExample(apiPartnerExample).get(0);
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Integer saveMerchantPartner(MerchantPartner merchantPartner) throws Exception {
		// if order date is not set, do it now
		return merchantPartnerDAO.insertMerchantPartnerSelective(merchantPartner);
		
	}

	/**
	 * {@inheritDoc}
	 */
	public List<MerchantPartner> findMerchantsForPartner(MerchantPartnerExample merchantPartnerExample) throws Exception {
		// if order date is not set, do it now
		return merchantPartnerDAO.selectMerchantPartnerByExample(merchantPartnerExample);
		
	}
}
