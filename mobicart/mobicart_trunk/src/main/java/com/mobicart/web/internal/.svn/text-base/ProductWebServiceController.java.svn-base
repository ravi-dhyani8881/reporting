package com.mobicart.web.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mobicart.bo.ProductBO;
import com.mobicart.model.Product;
import com.mobicart.model.ProductExample;
import com.mobicart.model.Store;
import com.mobicart.model.User;
import com.mobicart.service.StoreService;
import com.mobicart.service.user.UserService;

/**
 * This controller class handles web service calls related to Product entity 
 * @author jasdeep.singh
 *
 */
@Controller
public class ProductWebServiceController {
	
	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	StoreService storeService;
	
	@Autowired
	UserService userService;
	
	

	@Autowired
	ProductBO productBO;
	
	private static final String PRODUCT = "product";

	
	/**
	 * Fetch Product Details
	 * 
	 * @param productId
	 * @return 'Product' and related objects wrapped in JSON
	 */
	@RequestMapping(value = "/product/{productId}/details")
	public ModelAndView getProductDetails(@PathVariable Long productId,
			@RequestParam(value="c",required=false) Long countryId,
			@RequestParam(value="s",required=false) Long stateId,
			HttpServletRequest request		
	) {
		
		
		Store store = null;
		User user = null;
		ModelAndView mav = new ModelAndView();
		
		try{
		
			//get product
			Product product = storeService.findProduct(productId);	
			
			if (product != null) {
				// get store
				store = storeService.findStoreById(product.getStoreId());

				// get owner of store
				user = userService.find(store.getUserId());


				// calculate tax on products
				List<Product> products = new ArrayList<Product>();
				products.add(product);
				
				products = productBO.calculateTax(products, store, countryId,
						stateId, user);

				
				mav.addObject(PRODUCT, products.get(0));
			} else {
				mav.addObject(PRODUCT, -1);
			}
		
		
		}catch(Exception e){
			logger.error("Could not find details for Product "+productId,e);
		}
		
		return mav;
	}

	@RequestMapping(value = "/product/details")
	public ModelAndView getProductsDetails(@RequestParam ArrayList<Long> productId,
			@RequestParam(value="c",required=false) Long countryId,
			@RequestParam(value="s",required=false) Long stateId,
			HttpServletRequest request		
	) {
		
		
		Store store = null;
		User user = null;
		ModelAndView mav = new ModelAndView();
		
		try{
					 
			
			List<Product> products = storeService.findProducts(productId);	
			
			if (products != null && products.size()>0) {
				// get store
				store = storeService.findStoreById(products.get(0).getStoreId());
				// get owner of store
				user = userService.find(store.getUserId());


				// calculate tax on products
				//List<Product> products = new ArrayList<Product>();
				//products.add(product);
				
				products = productBO.calculateTax(products, store, countryId,stateId, user);

				
				mav.addObject("products",products);
			} else {
				mav.addObject("products", -1);
			}
		
		
		}catch(Exception e){
			logger.error("Could not find details for Product "+productId,e);
			mav.addObject("products","");
		}
		
		return mav;
	}
	
	/**
	 * update product view count
	 * 
	 * @param productId
	 * @return 'Product' and related objects wrapped in JSON
	 */
	@RequestMapping(value = "/product/{productId}/view")
	public ModelAndView updateProductView(@PathVariable Long productId) {
		Map<String, String> responseMap = new HashMap<String, String>();
		try {

			Product product = storeService.findProduct(productId);
			Integer viewCount = product.getiViewCount() != null ? product
					.getiViewCount() : 0;
			// increment view count
			product.setiViewCount(viewCount + 1);
			// update product
			storeService.updateProduct(product);
			product = storeService.findProduct(productId);
			responseMap.put("update", "success");
		} catch (Exception e) {
			logger.error("updateProductView",e);
			responseMap.put("update", "failure");
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("response", responseMap);
		return mav;
	}

	
	
	
	
	
}
