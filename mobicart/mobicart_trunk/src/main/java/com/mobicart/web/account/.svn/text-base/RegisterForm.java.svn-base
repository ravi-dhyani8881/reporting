package com.mobicart.web.account;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mobicart.model.Address;
import com.mobicart.model.Api;
import com.mobicart.model.User;

public class RegisterForm {

	private Long userId;

	@NotEmpty
	@Length(min = 2, max = 50)
	private String firstName;

	private String lastName;

	@NotEmpty
	@Email
	private String username;

	private String heardFrom;

	private String oldPassword;

	private String password;
	private String passwordConfirmation;

	private String storeName;

	private String address;

	private String city;

	private String state;

	private String zip;

	private String country;

	private String terms;
	private String privacy;

	private boolean agreeToTerms;
 

	

	private boolean enabled;

	private String authKey;

	private String companyLogo;
	private String companyRegNo;
	private String taxRegNo;
	private Integer productCount;
	private CommonsMultipartFile companyLogoFile;
	private String customDomainName;

	
	private Integer thresholdGeneralCount;
	private Integer thresholdStoreCount;
	private Integer thresholdRefreshCount;
	private Integer storeProductLimit;
	private String phoneNo;
	
	private String customdomain="http://www.mobi-cart.com";	
	private String freedomain="http://www.mobiwebapp.com";
	
	
	
	
	@Length(max = 300)
	private String orderReturnUrl;
	
 
	
	public String getOrderReturnUrl() {
		return orderReturnUrl;
	}

	public void setOrderReturnUrl(String orderReturnUrl) {
		this.orderReturnUrl = orderReturnUrl;
	}
	
 
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHeardFrom() {
		return heardFrom;
	}

	public void setHeardFrom(String heardFrom) {
		this.heardFrom = heardFrom;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public String getTerms() {
		return terms;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}

	public String getPrivacy() {
		return privacy;
	}

	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}

	public boolean isAgreeToTerms() {
		return agreeToTerms;
	}

	public void setAgreeToTerms(boolean agreeToTerms) {
		this.agreeToTerms = agreeToTerms;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public Address getAddressModel() {
		return new Address("B", address, city, state, zip, country);
	}

	public User getUserModel() {
		return new User(username, password, firstName, lastName);
	}

	
	
	
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

	public String getAuthKey() {
		return authKey;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}

	public String getCompanyRegNo() {
		return companyRegNo;
	}

	public void setCompanyRegNo(String companyRegNo) {
		this.companyRegNo = companyRegNo;
	}

	public String getTaxRegNo() {
		return taxRegNo;
	}

	public void setTaxRegNo(String taxRegNo) {
		this.taxRegNo = taxRegNo;
	}

	public CommonsMultipartFile getCompanyLogoFile() {
		return companyLogoFile;
	}

	public void setCompanyLogoFile(CommonsMultipartFile companyLogoFile) {
		this.companyLogoFile = companyLogoFile;
	}

	public Integer getProductCount() {
		return productCount;
	}

	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}

	public String getCustomDomainName() {
		return customDomainName;
	}

	public void setCustomDomainName(String customDomainName) {
		this.customDomainName = customDomainName;
	}

	public Integer getThresholdGeneralCount() {
		return thresholdGeneralCount;
	}

	public void setThresholdGeneralCount(Integer thresholdGeneralCount) {
		this.thresholdGeneralCount = thresholdGeneralCount;
	}

	public Integer getThresholdStoreCount() {
		return thresholdStoreCount;
	}

	public void setThresholdStoreCount(Integer thresholdStoreCount) {
		this.thresholdStoreCount = thresholdStoreCount;
	}

	public Integer getThresholdRefreshCount() {
		return thresholdRefreshCount;
	}

	public void setThresholdRefreshCount(Integer thresholdRefreshCount) {
		this.thresholdRefreshCount = thresholdRefreshCount;
	}

	public Integer getStoreProductLimit() {
		return storeProductLimit;
	}

	public void setStoreProductLimit(Integer storeProductLimit) {
		this.storeProductLimit = storeProductLimit;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getCustomdomain() {
		return customdomain;
	}

	public void setCustomdomain(String customdomain) {
		this.customdomain = customdomain;
	}

	public String getFreedomain() {
		return freedomain;
	}

	public void setFreedomain(String freedomain) {
		this.freedomain = freedomain;
	}

}
