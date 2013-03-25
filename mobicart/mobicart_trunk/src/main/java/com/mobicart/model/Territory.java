package com.mobicart.model;

import com.mobicart.util.CountryUtil;

public class Territory {

	private Long id;
	private String sCode;
	private String sName;
	private String sCurrency;
	private String sNameAndCurrency;
	private String sCodeAndCurrency;
	private String sSymbol;
	private boolean isEUMember;
	private boolean isOther;
	
	
	
	public Territory() {
		super();
	}

	public Territory(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getsCode() {
		return sCode;
	}

	public void setsCode(String sCode) {
		this.sCode = sCode == null ? null : sCode.trim();
	}

	public String getsName() {
		return sName;
	}

	public String getsCurrency() {
		return sCurrency;
	}

	
	public String getsNameAndCurrency() {
		this.sNameAndCurrency=this.sName+" - "+this.sCurrency;
		return sNameAndCurrency;
	}

	public void setsNameAndCurrency(String sNameAndCurrency) {
		this.sNameAndCurrency = sNameAndCurrency;
	}

	public void setsCurrency(String sCurrency) {
		this.sCurrency = sCurrency == null ? null : sCurrency.trim();
	}


	public void setsName(String sName) {
		this.sName = sName == null ? null : sName.trim();
	}

	public boolean isEUMember() {
		isEUMember= CountryUtil.getEUCountries().contains(sName);
		return isEUMember;
	}

	public void setEUMember(boolean isEUMember) {
		this.isEUMember = isEUMember;
	}
	
	

	public boolean isOther() {
		isOther=(sCode.equals("OT")?true:false);
		return isOther;
	}

	public void setOther(boolean isOther) {
		this.isOther = isOther;
	}
	

	public String getsCodeAndCurrency() {
		this.sCodeAndCurrency=this.sCode+"-"+this.sCurrency;
		return sCodeAndCurrency;
	}

	public void setsCodeAndCurrency(String sCodeAndCurrency) {
		this.sCodeAndCurrency = sCodeAndCurrency;
	}

	public String getsSymbol() {
		sSymbol=CountryUtil.getCountrySymbolByCode(sCurrency);
		return sSymbol;
	}

	public void setsSymbol(String sSymbol) {
		this.sSymbol = sSymbol;
	}


	
	
}