package com.mobicart.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mobicart.model.Product;
import com.mobicart.model.Store;
import com.mobicart.model.Tax;
import com.mobicart.model.User;
import com.mobicart.service.StoreService;

/**
 * Class to calculate tax
 * @author jasdeep.singh
 *
 */
public class ProductBO {

	
	/**
	 * Logger for this class
	 */
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	StoreService storeService;
	
	
	
	/**
	 * Calculate tax on products 
	 * @param products List of {@link Product} objects
	 * @param store
	 * @param territotyId
	 * @param stateID
	 * @param storeUser
	 * @return
	 */
	public List<Product> calculateTax(List<Product> products,Store store, Long territotyId, Long stateID, User storeUser){
		List<Product> taxedProducts=null;
		Tax tax=null;
		if(territotyId==null){
			territotyId=0L;
		}
		if(stateID==null){
			stateID=0L;
		}
		try{
			//if user not logged in 
			if(territotyId==0 && stateID==0){
				//get default tax of store country when user not logged in
				tax=storeService.findTaxByStoreCountryState(store.getId(),store.getTerritoryId(), stateID);
			}else{
				tax=storeService.findTaxByStoreCountryState(store.getId(),territotyId, stateID);
			}
			if(tax==null){
				tax=Tax.getDefaultValue(store.getId(), territotyId, stateID);
			}
			
			taxedProducts=new ArrayList<Product>();
			for (Product p:products){
				try{
					p.setbTaxable(store.getbIncludeTax());
					p.setsTaxType(tax.getsType());				
					p.setfTax(p.getfDiscountedPrice()!=null?p.getfDiscountedPrice().multiply(tax.getfTax()).divide(new BigDecimal(100)):p.getfPrice().multiply(tax.getfTax()).divide(new BigDecimal(100)));
					p.setfTaxOnFPrice(p.getfPrice().multiply(tax.getfTax()).divide(new BigDecimal(100)));
				}catch(Exception e){
					p.setsTaxType("default");				
					p.setfTax(BigDecimal.valueOf(0L));
					p.setfTaxOnFPrice(BigDecimal.valueOf(0L));
				}
				taxedProducts.add(p);
			}
			
			
			
		}catch(Exception e){
			logger.error("error in cacluationg tax",e);
		}
			
		return taxedProducts;
	}
	
	
}
