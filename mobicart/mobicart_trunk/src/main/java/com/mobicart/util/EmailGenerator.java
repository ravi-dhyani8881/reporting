package com.mobicart.util;



import java.util.Map;

public class EmailGenerator {

	private String fromEmail; 
	private String toEmail;
	private String bccEmail;
	private String ccEmail;
	private String subject;
	private String templateName;
	private Map<String, String> param;
	//In case of miltiple of receipents.
	private String[] toEmails;
	private Map<String, String>[] params;
	
	
	public String getFromEmail() {
		return fromEmail;
	}
	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}
	
	
	public String getBccEmail() {
		return bccEmail;
	}
	public void setBccEmail(String bccEmail) {
		this.bccEmail = bccEmail;
	}
	
	public String getCcEmail() {
		return ccEmail;
	}
	public void setCcEmail(String ccEmail) {
		this.ccEmail = ccEmail;
	}
	public Map<String, String> getParam() {
		return param;
	}
	public void setParam(Map<String, String> param) {
		this.param = param;
	}
	public Map<String, String>[] getParams() {
		return params;
	}
	public void setParams(Map<String, String>[] params) {
		this.params = params;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getToEmail() {
		return toEmail;
	}
	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}
	public String[] getToEmails() {
		return toEmails;
	}
	public void setToEmails(String[] toEmails) {
		this.toEmails = toEmails;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
	
	
	
	
}

