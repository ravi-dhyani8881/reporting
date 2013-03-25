package com.mobicart.model;

import java.util.Date;
import java.util.List;

import com.mobicart.util.CountryUtil;

public class Store {
	
	private Long id;
	private Long userId;
	private String sSName;
	private String sSDescription;
	private String sOrderEmail;
	private String sPaypalEmail;
	private String sCurrency;
	private String sCurrencyCode;
	private String sCurrencySymbol;
	private Date dCreatedOn;
	private Date dUpdatedOn;
	private String sUserEmail;
	private Boolean bEnabled;
	
	private Long territoryId;
	private String territoryName;
	private String sPaypalToken;
	public String getsPaypalToken() {
		return sPaypalToken;
	}



	public void setsPaypalToken(String sPaypalToken) {
		this.sPaypalToken = sPaypalToken;
	}



	/**
	 * shiping calculated by address
	 */
	private Shipping shipping;
	/**
	 * tax calculated by user address
	 */
	private Tax tax;
	
	private List<Shipping> shippingList;
	private List<Tax> taxList;
	


	
	/**
	 *This field corresponds to the database column stores.b_include_tax
	 
	 */
	private Boolean bIncludeTax;


	/**
	 *This field corresponds to the database column stores.b_tax_shipping
	 
	 */
	private Boolean bTaxShipping;

	

	/**
	 *  This method sets the value of the database column stores.id
	 * @param id  the value for stores.id
	 
	 */
	public void setId(Long id) {
		this.id = id;
	}



	/**
	 *  This method returns the value of the database column stores.user_id
	 * @return  the value of stores.user_id
	 
	 */
	public Long getUserId() {
		return userId;
	}



	/**
	 *  This method sets the value of the database column stores.user_id
	 * @param userId  the value for stores.user_id
	 
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}






	/**
	 *  This method returns the value of the database column stores.s_order_email
	 * @return  the value of stores.s_order_email
	 
	 */
	public String getsOrderEmail() {
		return sOrderEmail;
	}



	/**
	 *  This method sets the value of the database column stores.s_order_email
	 * @param sOrderEmail  the value for stores.s_order_email
	 
	 */
	public void setsOrderEmail(String sOrderEmail) {
		this.sOrderEmail = sOrderEmail == null ? "" : sOrderEmail.trim();
	}



	/**
	 *  This method returns the value of the database column stores.s_paypal_email
	 * @return  the value of stores.s_paypal_email
	 
	 */
	public String getsPaypalEmail() {
		return sPaypalEmail;
	}



	/**
	 *  This method sets the value of the database column stores.s_paypal_email
	 * @param sPaypalEmail  the value for stores.s_paypal_email
	 
	 */
	public void setsPaypalEmail(String sPaypalEmail) {
		this.sPaypalEmail = sPaypalEmail == null ? "" : sPaypalEmail.trim();
	}



	/**
	 *  This method returns the value of the database column stores.s_currency
	 * @return  the value of stores.s_currency
	 
	 */
	public String getsCurrency() {
		return sCurrency;
	}



	/**
	 *  This method sets the value of the database column stores.s_currency
	 * @param sCurrency  the value for stores.s_currency
	 
	 */
	public void setsCurrency(String sCurrency) {
		this.sCurrency = sCurrency == null ? "" : sCurrency.trim();
	}



	/**
	 *  This method returns the value of the database column stores.d_created_on
	 * @return  the value of stores.d_created_on
	 
	 */
	public Date getdCreatedOn() {
		return dCreatedOn;
	}



	/**
	 *  This method sets the value of the database column stores.d_created_on
	 * @param dCreatedOn  the value for stores.d_created_on
	 
	 */
	public void setdCreatedOn(Date dCreatedOn) {
		this.dCreatedOn = dCreatedOn;
	}



	/**
	 *  This method returns the value of the database column stores.d_updated_on
	 * @return  the value of stores.d_updated_on
	 
	 */
	public Date getdUpdatedOn() {
		return dUpdatedOn;
	}



	/**
	 *  This method sets the value of the database column stores.d_updated_on
	 * @param dUpdatedOn  the value for stores.d_updated_on
	 
	 */
	public void setdUpdatedOn(Date dUpdatedOn) {
		this.dUpdatedOn = dUpdatedOn;
	}



	/**
	 *  This method returns the value of the database column stores.b_include_tax
	 * @return  the value of stores.b_include_tax
	 
	 */
	public Boolean getbIncludeTax() {
		return bIncludeTax;
	}



	/**
	 *  This method sets the value of the database column stores.b_include_tax
	 * @param bIncludeTax  the value for stores.b_include_tax
	 
	 */
	public void setbIncludeTax(Boolean bIncludeTax) {
		this.bIncludeTax = bIncludeTax == null ? false : bIncludeTax;
	}



	/**
	 *  This method returns the value of the database column stores.b_tax_shipping
	 * @return  the value of stores.b_tax_shipping
	 
	 */
	public Boolean getbTaxShipping() {
		return bTaxShipping;
	}



	/**
	 *  This method sets the value of the database column stores.b_tax_shipping
	 * @param bTaxShipping  the value for stores.b_tax_shipping
	 
	 */
	public void setbTaxShipping(Boolean bTaxShipping) {
		this.bTaxShipping = bTaxShipping == null ? false : bTaxShipping;
	}



	/**
	 *  This method returns the value of the database column app.b_enabled
	 * @return  the value of app.b_enabled

	 */
	public Boolean getbEnabled() {
		return bEnabled;
	}


	/**
	 *  This method sets the value of the database column app.b_enabled
	 * @param bEnabled  the value for app.b_enabled

	 */
	public void setbEnabled(Boolean bEnabled) {
		this.bEnabled = bEnabled;
	}


	
	public Store() {
		super();
		//taxList.add(new Tax());
	}

	
	
	public Store(Long id, String sSName) {
		super();
		this.id = id;
		this.sSName = sSName;
	}



	public Store(Long id) {
		super();
		this.id = id;
	}

	
	public Long getId() {
		return id;
	}

	public String getsSName() {
		return sSName;
	}

	public void setsSName(String sSName) {
		this.sSName = sSName;
	}

	public String getsSDescription() {
		return sSDescription;
	}

	public void setsSDescription(String sSDescription) {
		this.sSDescription = sSDescription == null ? "" : sSDescription;
	}

	
	

	public List<Shipping> getShippingList() {
		
		return shippingList;
	}

	public void setShippingList(List<Shipping> shippingList) {
		this.shippingList = shippingList;
	}

	public List<Tax> getTaxList() {
		return taxList;
	}

	public void setTaxList(List<Tax> taxList) {
		this.taxList = taxList;
	}

	
	public String getsUserEmail() {
		return sUserEmail;
	}

	public void setsUserEmail(String sUserEmail) {
		this.sUserEmail = sUserEmail;
	}

	
	



	public Long getTerritoryId() {
		return territoryId!=null?territoryId:0L;
	}



	public void setTerritoryId(Long territoryId) {
		this.territoryId = territoryId;
	}



	public String getTerritoryName() {
		return territoryName!=null?territoryName:"";
	}



	public void setTerritoryName(String territoryName) {
		this.territoryName = territoryName;
	}



	public String getsCurrencyCode() {
		
		try{
			sCurrencyCode=sCurrency.split("-")[1];
		}catch (Exception e) {
			//logger.error("getsCurrencyCode()", e); 
			sCurrencyCode=sCurrency;
		}
		return sCurrencyCode;
	}

	public void setsCurrencyCode(String sCurrencyCode) {
		this.sCurrencyCode = sCurrencyCode;
	}

	public String getsCurrencySymbol() {
		sCurrencySymbol=CountryUtil.getCountrySymbolByCode(sCurrencyCode);
		return sCurrencySymbol;
	}

	public void setsCurrencySymbol(String sCurrencySymbol) {
		this.sCurrencySymbol = sCurrencySymbol;
	}



	



	public Shipping getShipping() {
		return shipping;
	}



	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}



	public Tax getTax() {
		return tax;
	}



	public void setTax(Tax tax) {
		this.tax = tax;
	}



	@Override
	public String toString() {
		return "Store [id=" + id + ", userId=" + userId + ", sSName=" + sSName
				+ ", sSDescription=" + sSDescription + ", sOrderEmail="
				+ sOrderEmail + ", sPaypalEmail=" + sPaypalEmail
				+ ", sCurrency=" + sCurrency + ", sCurrencyCode="
				+ sCurrencyCode + ", sCurrencySymbol=" + sCurrencySymbol
				+ ", dCreatedOn=" + dCreatedOn + ", dUpdatedOn=" + dUpdatedOn
				+ ", sUserEmail=" + sUserEmail + ", shippingList="
				+ shippingList + ", taxList=" + taxList + "]";
	}

	
	
	
	
	
}