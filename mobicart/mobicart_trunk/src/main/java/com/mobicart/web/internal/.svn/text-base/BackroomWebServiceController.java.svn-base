package com.mobicart.web.internal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mobicart.bo.Message;
import com.mobicart.model.Address;
import com.mobicart.model.Product;
import com.mobicart.model.ProductOrder;
import com.mobicart.model.ProductOrderItem;
import com.mobicart.model.ProductOrderItemMultiple;
import com.mobicart.model.ProductOrderItemWebapp;
import com.mobicart.model.ProductOrderItemWebappExample;
import com.mobicart.model.ProductOrderItemWebappMultiple;
import com.mobicart.model.ProductOrderWebapp;
import com.mobicart.model.ProductOrderWebappExample;
import com.mobicart.model.ProductReview;
import com.mobicart.model.State;
import com.mobicart.model.Store;
import com.mobicart.model.Tax;
import com.mobicart.model.Territory;
import com.mobicart.model.User;
import com.mobicart.service.AdminService;
import com.mobicart.service.LabelService;
import com.mobicart.service.StoreService;
import com.mobicart.service.user.UserService;
import com.mobicart.util.EmailGenerator;
import com.mobicart.util.PathLocator;
import com.mobicart.util.ResourceProperties;
import com.mobicart.util.Sender;

/**
 * This controller class handles web service calls related to backroom tasks
 * such as product order , product order item , product view  and product history  
 * @author jasdeep.singh
 *
 */
@Controller
public class BackroomWebServiceController {

	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	AdminService adminService;

	@Autowired
	Sender sender;
	
	@Autowired
	StoreService storeService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	LabelService labelService;
	
	private User user;
	/**
	 * Service to add product review
	 * 
	 * @param productReview : {@link ProductReview} object
	 * @return id of  {@link ProductReview} object e.g. ("id":0)
	 */
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = "/product-review-rating/save")
	public @ResponseBody Map<String, ? extends Object> createProductReview(
			@RequestBody ProductReview productReview) {

		Map<String, ? extends Object> returnMap = null;
		try {

			Long id = 0L;
			id = storeService.saveProductReview(productReview);
			returnMap = Collections.singletonMap("id", id);

		} catch (Exception e) {
			logger.error("Error in creating product review", e);
		}
		return returnMap;
	}
	
	
	
	
	/**
	 * Service to add product order
	 * 
	 * @param productOrder
	 *            : {@link ProductOrder} object in json
	 * @return id of product order item e.g. ("id":0)
	 */
	
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = "/product-order/save")
	public @ResponseBody
	Map<String, ? extends Object> createOrder(
			@RequestBody ProductOrder productOrder) {

		Map<String, ? extends Object> returnMap = null;
		
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("incoming order:"+ productOrder);
			}

			
			logger.debug("before saving product order:");
			productOrder.setsStatus("processing");
			Long id = adminService.saveProductOrder(productOrder);
			logger.debug("after saving product order:");

			returnMap = Collections.singletonMap("id", id);

			if (logger.isDebugEnabled()) {
				logger.debug("genreated order id:"+ id);
			}

		} catch (Exception e) {
			logger.error("Error in creating product order", e);
		}
		user=userService.find(productOrder.getMerchantId());
		return returnMap;
	}

	/**
	 * add product order item
	 * 
	 * @param productOrderItem : {@link ProductOrderItem} object
	 * @return id of product order item e.g. ("id":0)
	 * @see ProductOrderItem 
	 */
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = "/product-order-item/save")
	public @ResponseBody
	Map<String, ? extends Object> createProductOrderItem(
			@RequestBody ProductOrderItem productOrderItem) {
		
		Map<String, ? extends Object> returnMap = null;
		
		try {
			Long id = 0L;
			returnMap = Collections.singletonMap("id", id);
			
			if(logger.isDebugEnabled()){
				logger.debug("incoming order item:"+ productOrderItem);
			}
			
			id = adminService.saveProductOrderItem(productOrderItem);
			productOrderItem.setId(id);
			// manage inventory
			adminService.manageProductInventory(productOrderItem);
			returnMap = Collections.singletonMap("id", id); // return "success";
			
			if(logger.isDebugEnabled()){
				logger.debug("genreated order item id:"+ id);
			}
			
		} catch (Exception e) {
			logger.error("could not save product order item", e);
		}
		return returnMap;
	}

	
	
	
	/**
	 * add product order item for multiple select opetion
	 * 
	 * @param productOrderItem : {@link ProductOrderItem} object
	 * @return id of product order item e.g. ("id":0)
	 * @see ProductOrderItem 
	 */
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = "/product-order-item-multiple/save")
	public @ResponseBody
	Map<String, ? extends Object> createProductOrderItemMultiple(
			@RequestBody ProductOrderItemMultiple productOrderItemMultiple) {
		
		Map<String, ? extends Object> returnMap = null;
		
		
		try {
			Long id = 0L;
			returnMap = Collections.singletonMap("id", id);
			String url=user.getOrderReturnUrl();
			if(logger.isDebugEnabled()){
				logger.debug("incoming order item:"+ productOrderItemMultiple);
			}
			// for product
			
			List<Long> ids=new ArrayList<Long>();
			
			String productOptionIdsCSV=productOrderItemMultiple.getProductOptionId();
			
			if(StringUtils.isNotEmpty(productOptionIdsCSV)){
				String[] productOptionIds= productOptionIdsCSV.split(",");
				
			for (String strProductOptionId : productOptionIds) {
				ProductOrderItem productOrderItem=new ProductOrderItem();
				productOrderItem.setProductId(productOrderItemMultiple.getProductId());
				productOrderItem.setOrderId(productOrderItemMultiple.getOrderId());
				long productOptionId=Long.parseLong(strProductOptionId);
				productOrderItem.setProductOptionId(productOptionId);
				productOrderItem.setiQuantity(productOrderItemMultiple.getiQuantity());
				productOrderItem.setfAmount(productOrderItemMultiple.getfAmount());
				id = adminService.saveProductOrderItem(productOrderItem);
				productOrderItem.setId(id);
				// manage inventory
				try {
					if(url!=null && !url.equals(""))
					{
						url=url+ "?" + "merchantId=" +user.getId()
								+ "&productId=" +productOrderItemMultiple.getProductId()
								+ "&orderId=" + productOrderItemMultiple.getOrderId()
								+ "&productOptionId=" + Long.parseLong(strProductOptionId)
								+"&itemQuantity=" + productOrderItemMultiple.getiQuantity();
								
					  logger.info(url);
				      HttpClient client = new HttpClient();
					  GetMethod method = new GetMethod(url);
							
					try {
						  int statusCode = client.executeMethod(method);
						} catch (Exception e) {
					e.printStackTrace();
					}
					
				}
					}
				catch (Exception e) {
					e.printStackTrace();
					}
				adminService.manageProductInventory(productOrderItem);
				ids.add(id);
			} 	
				/*Long userId=productOrderItemMultiple.getId();*/
				
			}else{
				return returnMap;
			}

			
			returnMap = Collections.singletonMap("id", ids); // return "success";
			
			if(logger.isDebugEnabled()) logger.debug("genreated order item id:"+ ids);
			
			
		} catch (Exception e) {
			logger.error("Could not save product order item : {}", e.getLocalizedMessage());
		}
		return returnMap;
	}

	
	
	/**
	 * add product order
	 * 
	 * @param orderId ProductOrder id 
	 * @return success or failure e.g. ("result":"success") 
	 * 
	 */
	@RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET, org.springframework.web.bind.annotation.RequestMethod.POST}, value={"/product-order/{orderId}/notify"})
	  @ResponseBody
	  public Map<String, ? extends Object> sendProductOrderInvoice(@PathVariable Long orderId)
	  {
	    try
	    {
	      if (this.logger.isDebugEnabled()) {
	        this.logger.debug("processing order :" + orderId);
	      }

	      ProductOrder productOrder = this.storeService.findProductOrder(orderId);
	      
	      Territory territory = storeService.getTerritoryForPaymentTotals(productOrder.getsShippingCountry());
	      
	      State stte = storeService.getStateForPaymentTotals(productOrder.getsShippingState(), territory.getId());
	      Tax tax = new Tax();
	      tax = storeService.findTaxByStoreCountryState(productOrder.getStoreId(), territory.getId(), stte.getId());
	      BigDecimal fTax = tax.getfTax();

	      if (this.logger.isDebugEnabled()) {
	        this.logger.debug("sending notifications for :" + productOrder);
	      }

	      User merchant = this.userService.find(productOrder.getMerchantId());

	      HashMap labelsMap = this.labelService.getAllWebLabels(productOrder.getMerchantId());

	      Store store = this.storeService.findStoreByUserId(merchant.getId());
	      store.getsCurrencyCode();
	      String currencySymbol = store.getsCurrencySymbol();

	      String companyName = merchant.getStoreName();

	      EmailGenerator emailToBuyer = new EmailGenerator();
	      emailToBuyer.setFromEmail(merchant.getUsername());
	      emailToBuyer.setToEmail(productOrder.getsBuyerEmail());
	      emailToBuyer.setSubject(companyName + " - Order #" + orderId);
	      PathLocator path = new PathLocator();
	      emailToBuyer.setTemplateName(path.getRealPath() + 
	        "emailTemplate/PRODUCT_ORDER_TO_BUYER");
	      HashMap param = new HashMap();
	      String name = "";
	      name = productOrder.getsBuyerName();

	      BigDecimal subTotal = new BigDecimal(0);
	      List<ProductOrderItem> productOrderItems = productOrder
	        .getProductOrderItems();
	      StringBuffer orderItems = new StringBuffer();
	      StringBuffer orderPrice = new StringBuffer();
	      String productSKU = null;
		  Long productId = null;
		  Product product = new Product();
	      for (ProductOrderItem item : productOrderItems)
	      {
	        subTotal = subTotal.add(item.getProductPriceAfterDiscount());
	        productId = item.getProductId();
	        orderItems.append("<tr><td>" + 
	          item.getiQuantity() + "-" + 
	          " " + item.getProductName() + item.getOptionDescription() + "</td>");

	        orderPrice.append("<td>" + currencySymbol + item.getProductPriceAfterDiscount() + "</td></tr>");
	      }

	      Address merchantAddress = this.userService.findAddressByUserId(merchant
	        .getId());
	      
			Long buyerPhoneNumber = productOrder.getiBuyerPhone();
			String emptyPhone = "";
			
			

			product = storeService.findProduct(productId);
			productSKU = product.getsSaleLabel();
			if(productSKU == null || productSKU.equals("")){
				productSKU = "NA";
			}		
			if(fTax == null){
	        	fTax = new BigDecimal(0);
	        }

	      param.put("_DEAR_", (String)labelsMap.get("key.email.product.order.buyer.dear"));
	      param.put("_THANK_YOU_FOR_USING_", (String)labelsMap.get("key.email.product.order.buyer.thank"));
	      param.put("_THIS_IS_AN_INVOICE_FOR_YOUR_RECENT_ORDER_", (String)labelsMap.get("key.email.product.order.buyer.recent.invoice"));
	      param.put("_INVOICE_", (String)labelsMap.get("key.email.product.order.buyer.invoice"));
	      param.put("_INVOICENO_", (String)labelsMap.get("key.email.product.order.buyer.invoice.no"));
	      param.put("_INVOICEDATE_", (String)labelsMap.get("key.email.product.order.buyer.date"));
	      param.put("_TRANSACTION_ID_", (String)labelsMap.get("key.email.product.order.buyer.transaction.id"));
	      param.put("_BILL_TO_", (String)labelsMap.get("key.email.product.order.buyer.bill.to"));
	      param.put("_BUYER_TO_", (String)labelsMap.get("key.email.product.order.buyer.to"));
	      param.put("_QUANTITY_", (String)labelsMap.get("key.email.product.order.buyer.quantity"));
	      param.put("_DESCRIPTION_", (String)labelsMap.get("key.email.product.order.buyer.description"));
	      param.put("_PRICE_", (String)labelsMap.get("key.email.product.order.buyer.price"));
	      param.put("_SUB_TOTAL_", (String)labelsMap.get("key.email.product.order.buyer.sub.total"));
	      param.put("_TOTAL_", (String)labelsMap.get("key.email.product.order.buyer.total"));
	      param.put("_WE_APPRECIATE_YOUR_BUSINESS_", (String)labelsMap.get("key.email.product.order.buyer.appreciate.business"));

	      param.put("_NAME_", name);
	      param.put("_COMPANYNAME_", companyName);
	      param.put("_COMPANYADDRESS_", merchantAddress.toStringFormatted());
	      param.put("_COMPANY_EMAIL_", merchant.getUsername());
	      param.put("_BUYERADDRESS_", productOrder.getBillingAddressFormatted());
	      if(buyerPhoneNumber == null){			
				param.put("_BUYERPHONE_", emptyPhone);
			}else{
				param.put("_BUYERPHONE_", buyerPhoneNumber.toString());
			}	
	      param.put("_BUYEREMAIL_", productOrder.getsBuyerEmail());
	      param.put("_ORDERNUMBER_", orderId.toString());
	      param.put("_ORDERTAXLABEL_", "VAT (" +fTax + " %)");
	      //param.put("_ORDERTAXLABEL_", "VAT (" +this.adminService.findSiteConstants().getfEuVat() + " %)");
	      param.put("_ORDERSHIPPINGLABEL_", "Shipping");
	      param.put("_ORDERDATE_", productOrder.getFormattedOrderDate());

	      String orderAmount = subTotal.toPlainString();

	      param.put("_ORDERAMOUNT_", currencySymbol + orderAmount);
	      param.put("_ORDERITEMS_", orderItems.toString());

	      BigDecimal fShippingAmount = productOrder.getfTotalAmount().subtract(subTotal.add(productOrder.getfTaxAmount()));

	      param.put("_ORDERSHIPPINGAMOUNT_", currencySymbol + fShippingAmount.toString());
	      String taxAmount = productOrder.getfTaxAmount() != null ? ""
					+ productOrder.getfTaxAmount() : "0.00";

		  param.put("_ORDERTAXAMOUNT_", currencySymbol + taxAmount);
	      String totalAmount = productOrder.getfTotalAmount() != null ? ""
					+ productOrder.getfTotalAmount() : "0.00";

	      param.put("_ORDERTOTALAMOUNT_", currencySymbol + totalAmount);
	      
	      emailToBuyer.setParam(param);
	      
	      this.sender.sendMail(emailToBuyer);

	      EmailGenerator emailToMerchant = new EmailGenerator();
	      emailToMerchant.setFromEmail(
	        ResourceProperties.getString("adminEmail"));
	      emailToMerchant.setToEmail(merchant.getUsername());
	      emailToMerchant.setSubject(companyName + " - Order #" + orderId);

	      emailToMerchant.setTemplateName(path.getRealPath() + 
	        "emailTemplate/PRODUCT_ORDER_TO_MERCHANT");

	      name = new String();
	        /*merchant.getsLastName() != null || !(merchant.getsLastName().equals(" ")) ? " " + 
	        merchant.getsLastName() : merchant.getsFirstName() != null ? merchant.getsFirstName() : 
	        " ";*/
	        
	        if(StringUtils.isEmpty(merchant.getsLastName())){
	        	name = merchant.getsFirstName();
	        }else{
	        	name = merchant.getsLastName();
	        }
	        
	        
	        
	        param.put("_NAME_", name);			
			param.put("_BUYER_TO_", labelsMap.get("key.email.product.order.buyer.to"));
			param.put("_BUYERNAME_", productOrder.getsBuyerName());
			param.put("_COMPANYNAME_", companyName);
			param.put("_ORDERNUMBER_", orderId.toString());
			param.put("_ORDERDATE_", productOrder.getFormattedOrderDate());
			param.put("_BASEPATH_", ResourceProperties.getString("appUrl"));
			param.put("_QUANTITY_", labelsMap.get("key.email.product.order.buyer.quantity"));
			param.put("_DESCRIPTION_", labelsMap.get("key.email.product.order.buyer.description"));
			param.put("_PRICE_", labelsMap.get("key.email.product.order.buyer.price"));
			param.put("_SUB_TOTAL_", labelsMap.get("key.email.product.order.buyer.sub.total"));
			param.put("_TOTAL_", labelsMap.get("key.email.product.order.buyer.total"));
			param.put("_BUYERADDRESS_", productOrder.getBillingAddressFormatted());
			param.put("_BUYERSHIPPINGADDRESS_", productOrder.toShippingAddress());
			if(buyerPhoneNumber == null){			
				param.put("_BUYERPHONE_", emptyPhone);
			}else{
				param.put("_BUYERPHONE_", buyerPhoneNumber.toString());
			}	
			
			param.put("_BUYEREMAIL_", productOrder.getsBuyerEmail());
			param.put("_ORDERAMOUNT_", currencySymbol + orderAmount);
			param.put("_ORDERITEMS_", orderItems.toString());
			param.put("_ORDERSHIPPINGAMOUNT_", currencySymbol + fShippingAmount.toString());
			param.put("_ORDERTAXAMOUNT_", currencySymbol + taxAmount);
			param.put("_ORDERTOTALAMOUNT_", currencySymbol + totalAmount);
			param.put("_PRODUCT_SKU_", productSKU);
			param.put("_ORDERTAXLABEL_", "VAT (" +fTax.toString()+ " %)");
	      
	      emailToMerchant.setParam(param);
	      this.sender.sendMail(emailToMerchant);
	    }
	    catch (Exception e) {
	      this.logger.error("Error in sending invoice", e);
	      return Collections.singletonMap("result", "failure");
	    }
	    return Collections.singletonMap("result", "success");
	  }

	
	/**
	 * view product order
	 * 
	 * @param productOrder
	 * @param response
	 * @return 'ProducOrder' in JSON
	 * @see ProductOrder
	 */
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = "/product-order/{id}/view")
	public @ResponseBody
	ProductOrder findProductOrder(@PathVariable Long id) {
		ProductOrder productOrder=null;
		try{
		 productOrder = adminService.findProductOrder(id);
		
		}catch(Exception e){
			logger.error("Error in fetching product order",e);
		}
		return productOrder;
	}

	/**
	 * view product order item
	 * 
	 * @param id : Product Order Item id
	 * @return 'ProductOrderItem' object in JSON
	 * @see ProductOrderItem
	 */
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = "/product-order-item/{id}/view")
	public @ResponseBody
	ProductOrderItem findProductOrderItem(@PathVariable Long id) {
		ProductOrderItem productOrderItem =null;
		try{
		productOrderItem = adminService
				.findProductOrderItem(id);
		}catch(Exception e){
			logger.error("Error in fetching product order item",e);
		}
		return productOrderItem;
	}

	/**
	 * view product order history
	 * 
	 * @param Store id  
	 * @param app Id
	 * @param  buyerEmail
	 * @return List of 'ProductOrder' objects in JSON
	 * @see ProductOrder
	 */
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.HEAD}, value = "/app/{appId}/store/{storeId}/buyer/{buyerEmail}/product-order-history")
	public ModelAndView getOrderHistory(@PathVariable Long storeId,
			@PathVariable Long appId, @PathVariable String buyerEmail) {
		List<ProductOrder> productOrders = adminService.getProductOrderHistory(
				storeId, appId, buyerEmail);
		ModelAndView mav = new ModelAndView();
		mav.addObject("product-orders", productOrders);
		return mav;
	}
	
	
	/**
	 * view product order history
	 * 
	 * @param Store id  
	 * @param app Id
	 * @param  buyerEmail
	 * @return List of 'ProductOrder' objects in JSON
	 * @see ProductOrder
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/app/{appId}/store/{storeId}/buyer/{buyerEmail}/product-order-history-webapp")
	public ModelAndView getOrderHistoryForWebapp(@PathVariable Long storeId,
			@PathVariable Long appId, @PathVariable String buyerEmail) {
		List<ProductOrder> productOrders = adminService.getProductOrderHistory(
				storeId, appId, buyerEmail);
		ModelAndView mav = new ModelAndView();
		mav.addObject("productorders", productOrders);
		return mav;
	}


	/**
	 * Service to get average product rating
	 * 
	 * @param productReview : {@link ProductReview} object
	 * @return id of  {@link ProductReview} object e.g. ("id":0)
	 */
	@RequestMapping(value = "/product/{productId}/get-rating")
	public ModelAndView getProductRating(
			@PathVariable Long productId) {

		ModelAndView mav = new ModelAndView();
		Integer rating=0;
		try{
		 rating=storeService.getProductRating(productId);
		}catch (DataAccessException e) {
			logger.error("Data Access Error in product rating {} for product id {}", e.getMessage(),productId);
		}catch (Exception e) {
			logger.error("Error in product rating {} for product id {}", e.getMessage(),productId);
		}
		mav.addObject("rating", rating);
		return mav;
	}
	/**
	 * Service to add product review
	 * 
	 * @param productReview : {@link ProductReview} object
	 * @return id of  {@link ProductReview} object e.g. ("id":0)
	 */
	@RequestMapping(value = "/{stateId}/state/{countryId}/country")
	public ModelAndView getStateAndCountryName(
			@PathVariable Long stateId,
			@PathVariable Long countryId) {
		
		ModelAndView mav = new ModelAndView();
		String[] value= new String[2];
		try{
			State state=storeService.findStateById(stateId);
			Territory territory=storeService.findTerritoryById(countryId);
			value[0]=state.getsName();
			value[1]=territory.getsName();
		}catch (Exception e) {
			e.printStackTrace();
		}
		mav.addObject("value", value);
		return mav;
	}
	
	/**
	 * Service to add product order for webapp
	 * 
	 * @param productOrderWebapp
	 *            : {@link ProductOrderWebapp} object in json
	 * @return id of product order webapp item e.g. ("id":0)
	 */
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = "/product-order-webapp/save")
	public @ResponseBody
	Map<String, ? extends Object> createOrderForWebapp(
			@RequestBody ProductOrderWebapp productOrderWebapp) {

		Map<String, ? extends Object> returnMap = null;
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("incoming order:"+ productOrderWebapp);
			}
			logger.debug("before saving product order for webapp:");
			productOrderWebapp.setsStatus("processing");
			Long id = adminService.saveProductOrderWebapp(productOrderWebapp);
			logger.debug("after saving product order webapp:");

			returnMap = Collections.singletonMap("id", id);
			if (logger.isDebugEnabled()) {
				logger.debug("genreated product order webapp id:"+ id);
			}
		} catch (Exception e) {
			logger.error("Error in creating product order webapp", e);
		}
		return returnMap;
	}
	
	
	/**
	 * add product order webapp item
	 * 
	 * @param productOrderWebappItem : {@link ProductOrderWebappItem} object
	 * @return id of product order webapp item e.g. ("id":0)
	 * @see ProductOrderWebappItem 
	 */
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = "/product-order-item-webapp/save")
	public @ResponseBody
	Map<String, ? extends Object> createProductOrderWebappItem(
			@RequestBody ProductOrderItemWebapp productOrderItemWebapp) {
		Map<String, ? extends Object> returnMap = null;
		try {
			Long id = 0L;
			returnMap = Collections.singletonMap("id", id);
			
			if(logger.isDebugEnabled()){
				logger.debug("incoming order item webapp:"+ productOrderItemWebapp);
			}
			id = adminService.saveProductOrderItemWebapp(productOrderItemWebapp);
			productOrderItemWebapp.setId(id);
			// manage inventory
			returnMap = Collections.singletonMap("id", id); // return "success";
			
			if(logger.isDebugEnabled()){
				logger.debug("genreated order item webapp id:"+ id);
			}
			
		} catch (Exception e) {
			logger.error("could not save product order item", e);
		}
		return returnMap;
	}
	
	
	/**
	 * add product order item for multiple select opetion
	 * 
	 * @param productOrderItem : {@link ProductOrderItem} object
	 * @return id of product order item e.g. ("id":0)
	 * @see ProductOrderItem 
	 */
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = "/product-order-item-webapp-multiple/save")
	public @ResponseBody
	Map<String, ? extends Object> createProductOrderItemWebappMultiple(
			@RequestBody ProductOrderItemWebappMultiple productOrderItemWebappMultiple) {
		Map<String, ? extends Object> returnMap = null;
		try {
			Long id = 0L;
			returnMap = Collections.singletonMap("id", id);

			if(logger.isDebugEnabled()){
				logger.debug("incoming order item:"+ productOrderItemWebappMultiple);
			}
			// for product
			
			List<Long> ids=new ArrayList<Long>();
			
			String productOptionIdsCSV=productOrderItemWebappMultiple.getProductOptionId();
			
			if(StringUtils.isNotEmpty(productOptionIdsCSV)){
				String[] productOptionIds= productOptionIdsCSV.split(",");
				
			for (String strProductOptionId : productOptionIds) {
				ProductOrderItemWebapp  productOrderItem=new ProductOrderItemWebapp();
				productOrderItem.setProductId(productOrderItemWebappMultiple.getProductId());
				productOrderItem.setOrderId(productOrderItemWebappMultiple.getOrderId());
				long productOptionId=Long.parseLong(strProductOptionId);
				productOrderItem.setProductOptionId(productOptionId);
				productOrderItem.setiQuantity(productOrderItemWebappMultiple.getiQuantity());
				productOrderItem.setfAmount(productOrderItemWebappMultiple.getfAmount());
				id = adminService.saveProductOrderItemWebapp(productOrderItem);
				productOrderItem.setId(id);
				// manage inventory
				ids.add(id);
			} 	
				
				
			}else{
				return returnMap;
			}

			
			returnMap = Collections.singletonMap("id", ids); // return "success";
			
			if(logger.isDebugEnabled()) logger.debug("genreated order item id:"+ ids);
			
			
		} catch (Exception e) {
			logger.error("Could not save product order item : {}", e.getLocalizedMessage());
		}
		return returnMap;
	}
	
	
	/**
	 * add product order
	 * 
	 * @param orderId ProductOrder id 
	 * @return success or failure e.g. ("result":"success") 
	 * 
	 */
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = "/product-order-webapp/{sOrderSource}/notify")
	public @ResponseBody
	Map<String, ? extends Object> sendProductOrderInvoice(@PathVariable String sOrderSource) {
		try {
			if(logger.isDebugEnabled()){
				logger.debug("processing order for webapp :"+ sOrderSource);
			}
			ProductOrderWebappExample productOrderWebappExample=new ProductOrderWebappExample();
			productOrderWebappExample.createCriteria().andSOrderSourceEqualTo(sOrderSource);
			
			List<ProductOrderWebapp> productOrderWebappList=adminService.findProductOrderWebappByExample(productOrderWebappExample);
			
			if(productOrderWebappList != null && productOrderWebappList.size() > 0){
				ProductOrder productOrder = new ProductOrder();
				ProductOrderWebapp productOrderWebapp=productOrderWebappList.get(0);
				productOrder.setAppId(productOrderWebapp.getAppId());
				productOrder.setStoreId(productOrderWebapp.getStoreId());
				productOrder.setMerchantId(productOrderWebapp.getMerchantId());
				productOrder.setfAmount(productOrderWebapp.getfAmount());
				productOrder.setfShippingAmount(productOrderWebapp.getfShippingAmount());
				productOrder.setfTaxAmount(productOrderWebapp.getfTaxAmount());
				productOrder.setfTotalAmount(productOrderWebapp.getfTotalAmount());
				productOrder.setiBuyerPhone(productOrderWebapp.getiBuyerPhone());
				productOrder.setsBillingCity(productOrderWebapp.getsBillingCity());
				productOrder.setsBillingCountry(productOrderWebapp.getsBillingCountry());
				productOrder.setsBillingPostalCode(productOrderWebapp.getsBillingPostalCode());
				productOrder.setsBillingState(productOrderWebapp.getsBillingState());
				productOrder.setsBillingStreet(productOrderWebapp.getsBillingStreet());
				productOrder.setsBuyerEmail(productOrderWebapp.getsBuyerEmail());
				productOrder.setsBuyerName(productOrderWebapp.getsBuyerName());
				productOrder.setsMerchantPaypalEmail(productOrderWebapp.getsMerchantPaypalEmail());
				productOrder.setsShippingCity(productOrderWebapp.getsShippingCity());
				productOrder.setsShippingCountry(productOrderWebapp.getsShippingCountry());
				productOrder.setsShippingPostalCode(productOrderWebapp.getsShippingPostalCode());
				productOrder.setsShippingState(productOrderWebapp.getsShippingState());
				productOrder.setsShippingStreet(productOrderWebapp.getsShippingStreet());
				
				Map<String, ? extends Object> map=createOrder(productOrder);
				Long orderId=(Long)map.get("id");
				
				ProductOrderItemWebappExample productOrderItemWebappExample=new ProductOrderItemWebappExample();
				productOrderItemWebappExample.createCriteria().andSOrderSourceEqualTo(sOrderSource);
				
				List<ProductOrderItemWebapp> productOrderItemWebappList=adminService.findProductOrderItemWebappByExample(productOrderItemWebappExample);
				
				if(productOrderItemWebappList !=null && productOrderItemWebappList.size() > 0){
					for(ProductOrderItemWebapp productOrderItemWebapp:productOrderItemWebappList){
						ProductOrderItem productOrderItem= new ProductOrderItem();
						
						productOrderItem.setfAmount(productOrderItemWebapp.getfAmount());
						productOrderItem.setiQuantity(productOrderItemWebapp.getiQuantity());
						productOrderItem.setProductId(productOrderItemWebapp.getProductId());
						productOrderItem.setProductOptionId(productOrderItemWebapp.getProductOptionId());
						productOrderItem.setOrderId(orderId);
						createProductOrderItem(productOrderItem);
						adminService.deleteProductOrderItemWebapp(productOrderItemWebapp);
					}
				}
				
				if(orderId !=null && orderId > 0){
					sendProductOrderInvoice(orderId);
					adminService.deleteProductOrderWebapp(productOrderWebapp);
					
				}
			}
		} catch (Exception e) {
			logger.error("Error in sending invoice",e);
			return Collections.singletonMap("result", "failure"); // return
		}
		return Collections.singletonMap("result", "success"); // return
	}
	
	
	
	/**
	 * search the tweets
	 * 
	 * @param storeId Store id 
	 * @return tweets 
	 * 
	 */

    @RequestMapping(value = "/search/{storeId}/tweets", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView getTwitterTweets(@PathVariable Long storeId) {
    	
    	ModelAndView mav = new ModelAndView();
        //Message message = new Message();
        List tweets = storeService.getJsonStringOfTweetsIphone(storeId,10);
        mav.addObject("results", tweets);		
        return mav;
    }

	
	
	

}
