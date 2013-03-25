package com.mobicart.model;

import java.math.BigDecimal;

public class Tax {
    
    /**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column tax.id
	 * @ibatorgenerated  Mon Nov 29 19:47:35 IST 2010
	 */
	private Long id;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column tax.store_id
	 * @ibatorgenerated  Mon Nov 29 19:47:35 IST 2010
	 */
	private Long storeId;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column tax.territory_id
	 * @ibatorgenerated  Mon Nov 29 19:47:35 IST 2010
	 */
	private Long territoryId;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column tax.state_id
	 * @ibatorgenerated  Mon Nov 29 19:47:35 IST 2010
	 */
	private Long stateId;
	
	
	private String sState;
	
	
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column tax.s_country
	 * @ibatorgenerated  Mon Nov 29 19:47:35 IST 2010
	 */
	private String sCountry;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column tax.s_type
	 * @ibatorgenerated  Mon Nov 29 19:47:35 IST 2010
	 */
	private String sType;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column tax.f_tax
	 * @ibatorgenerated  Mon Nov 29 19:47:35 IST 2010
	 */
	private BigDecimal fTax;


	
//	private Boolean bIncludeTax; 

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column tax.id
	 * @return  the value of tax.id
	 * @ibatorgenerated  Mon Nov 29 19:47:35 IST 2010
	 */
	public Long getId() {
		return id;
	}



	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column tax.id
	 * @param id  the value for tax.id
	 * @ibatorgenerated  Mon Nov 29 19:47:35 IST 2010
	 */
	public void setId(Long id) {
		this.id = id;
	}



	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column tax.store_id
	 * @return  the value of tax.store_id
	 * @ibatorgenerated  Mon Nov 29 19:47:35 IST 2010
	 */
	public Long getStoreId() {
		return storeId;
	}



	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column tax.store_id
	 * @param storeId  the value for tax.store_id
	 * @ibatorgenerated  Mon Nov 29 19:47:35 IST 2010
	 */
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}



	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column tax.territory_id
	 * @return  the value of tax.territory_id
	 * @ibatorgenerated  Mon Nov 29 19:47:35 IST 2010
	 */
	public Long getTerritoryId() {
		return territoryId;
	}



	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column tax.territory_id
	 * @param territoryId  the value for tax.territory_id
	 * @ibatorgenerated  Mon Nov 29 19:47:35 IST 2010
	 */
	public void setTerritoryId(Long territoryId) {
		this.territoryId = territoryId;
	}



	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column tax.state_id
	 * @return  the value of tax.state_id
	 * @ibatorgenerated  Mon Nov 29 19:47:35 IST 2010
	 */
	public Long getStateId() {
		return stateId;
	}



	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column tax.state_id
	 * @param stateId  the value for tax.state_id
	 * @ibatorgenerated  Mon Nov 29 19:47:35 IST 2010
	 */
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}



	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column tax.s_country
	 * @return  the value of tax.s_country
	 * @ibatorgenerated  Mon Nov 29 19:47:35 IST 2010
	 */
	public String getsCountry() {
		return sCountry;
	}



	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column tax.s_country
	 * @param sCountry  the value for tax.s_country
	 * @ibatorgenerated  Mon Nov 29 19:47:35 IST 2010
	 */
	public void setsCountry(String sCountry) {
		this.sCountry = sCountry == null ? null : sCountry.trim();
	}


	
	

	public String getsState() {
		return sState;
	}



	public void setsState(String sState) {
		this.sState = sState;
	}



	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column tax.s_type
	 * @return  the value of tax.s_type
	 * @ibatorgenerated  Mon Nov 29 19:47:35 IST 2010
	 */
	public String getsType() {
		return sType;
	}



	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column tax.s_type
	 * @param sType  the value for tax.s_type
	 * @ibatorgenerated  Mon Nov 29 19:47:35 IST 2010
	 */
	public void setsType(String sType) {
		this.sType = sType == null ? null : sType.trim();
	}



	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column tax.f_tax
	 * @return  the value of tax.f_tax
	 * @ibatorgenerated  Mon Nov 29 19:47:35 IST 2010
	 */
	public BigDecimal getfTax() {
		return fTax;
	}



	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column tax.f_tax
	 * @param fTax  the value for tax.f_tax
	 * @ibatorgenerated  Mon Nov 29 19:47:35 IST 2010
	 */
	public void setfTax(BigDecimal fTax) {
		this.fTax = fTax;
	}



	public Tax() {
		super();
		// TODO Auto-generated constructor stub
	}

    
    
	public Tax(Long id) {
		super();
		this.id = id;
	}



	
	




	public Tax(Long id, Long storeId, Long territoryId, Long stateId,
			String sState, String sCountry, String sType, BigDecimal fTax) {
		super();
		this.id = id;
		this.storeId = storeId;
		this.territoryId = territoryId;
		this.stateId = stateId;
		this.sState = sState;
		this.sCountry = sCountry;
		this.sType = sType;
		this.fTax = fTax;
	}
	
	
	public static Tax getDefaultValue(Long storeId, Long countryId, Long stateId){
		return new Tax(0L, storeId, countryId, stateId, "Other", "Other", "default", BigDecimal.ZERO);
	}



	@Override
	public String toString() {
		return "Tax [id=" + id + ", storeId=" + storeId + ", territoryId="
				+ territoryId + ", stateId=" + stateId + ", sState=" + sState
				+ ", sCountry=" + sCountry + ", sType=" + sType + ", fTax="
				+ fTax + "]";
	}
	
	
	
	
	
}