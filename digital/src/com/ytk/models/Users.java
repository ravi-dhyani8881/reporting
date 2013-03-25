package com.ytk.models;

import org.apache.solr.client.solrj.beans.Field;


/**
 * Users entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Users implements java.io.Serializable {

	// Fields
	@Field("userId")
	private long id;
	@Field
	private String firstName;
	@Field
	private String lastName;
	@Field
	private String address;
	@Field
	private String telephone;
	@Field
	private String mobile;
	@Field
	private String country;
	@Field
	private String aptsuite;
	@Field
	private String state;
	@Field
	private String city;
	@Field
	private String postal;
	@Field
	private String gender;
	@Field
	private String dob;
	@Field
	private String facebookprof;
	@Field
	private String facebookid;
	@Field
	private String company;
	@Field
	private String fax;
	@Field
	private String password;
	@Field
	private String status;
	@Field
	private String type;
	@Field
	private String username;
	@Field
	private String enabled;
	@Field
	private String activationkey;

	public Users()
	{
	}

	public Users(String firstName, String lastName, String address, String telephone, long id, String mobile, String country, String aptsuite, String state, String city, String postal, String gender, String dob, String facebookprof, String facebookid, String company, String fax, String password, String status, String type, String username, String enabled, String activationkey)
	{
	  this.firstName = firstName;
	  this.lastName = lastName;
	  this.address = address;
	  this.telephone = telephone;
	  this.id = id;
	  this.mobile = mobile;
	  this.country = country;
	  this.aptsuite = aptsuite;
	  this.state = state;
	  this.city = city;
	  this.postal = postal;
	  this.gender = gender;
	  this.dob = dob;
	  this.facebookprof = facebookprof;
	  this.facebookid = facebookid;
	  this.company = company;
	  this.fax = fax;
	  this.password = password;
	  this.status = status;
	  this.type = type;
	  this.username = username;
	  this.enabled = enabled;
	  this.activationkey = activationkey;
	}

	public String getFirstName()
	{
	  return this.firstName;
	}
	public void setFirstName(String firstName) {
	  this.firstName = firstName;
	}
	public String getLastName() {
	  return this.lastName;
	}
	public void setLastName(String lastName) {
	  this.lastName = lastName;
	}
	public String getAddress() {
	  return this.address;
	}
	public void setAddress(String address) {
	  this.address = address;
	}
	public String getTelephone() {
	  return this.telephone;
	}
	public void setTelephone(String telephone) {
	  this.telephone = telephone;
	}
	public long getId() {
	  return this.id;
	}
	public void setId(long id) {
	  this.id = id;
	}
	public String getMobile() {
	  return this.mobile;
	}
	public void setMobile(String mobile) {
	  this.mobile = mobile;
	}
	public String getCountry() {
	  return this.country;
	}
	public void setCountry(String country) {
	  this.country = country;
	}
	public String getAptsuite() {
	  return this.aptsuite;
	}
	public void setAptsuite(String aptsuite) {
	  this.aptsuite = aptsuite;
	}
	public String getState() {
	  return this.state;
	}
	public void setState(String state) {
	  this.state = state;
	}
	public String getCity() {
	  return this.city;
	}
	public void setCity(String city) {
	  this.city = city;
	}
	public String getPostal() {
	  return this.postal;
	}
	public void setPostal(String postal) {
	  this.postal = postal;
	}
	public String getGender() {
	  return this.gender;
	}
	public void setGender(String gender) {
	  this.gender = gender;
	}
	public String getDob() {
	  return this.dob;
	}
	public void setDob(String dob) {
	  this.dob = dob;
	}
	public String getFacebookprof() {
	  return this.facebookprof;
	}
	public void setFacebookprof(String facebookprof) {
	  this.facebookprof = facebookprof;
	}
	public String getFacebookid() {
	  return this.facebookid;
	}
	public void setFacebookid(String facebookid) {
	  this.facebookid = facebookid;
	}
	public String getCompany() {
	  return this.company;
	}
	public void setCompany(String company) {
	  this.company = company;
	}
	public String getFax() {
	  return this.fax;
	}
	public void setFax(String fax) {
	  this.fax = fax;
	}
	public String getPassword() {
	  return this.password;
	}
	public void setPassword(String password) {
	  this.password = password;
	}
	public String getStatus() {
	  return this.status;
	}
	public void setStatus(String status) {
	  this.status = status;
	}
	public String getType() {
	  return this.type;
	}
	public void setType(String type) {
	  this.type = type;
	}
	public String getUsername() {
	  return this.username;
	}
	public void setUsername(String username) {
	  this.username = username;
	}
	public String getEnabled() {
	  return this.enabled;
	}
	public void setEnabled(String enabled) {
	  this.enabled = enabled;
	}
	public String getActivationkey() {
	  return this.activationkey;
	}
	public void setActivationkey(String activationkey) {
	  this.activationkey = activationkey;
	}}
