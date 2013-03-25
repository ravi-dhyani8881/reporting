package com.mobicart.dto;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class AppleCertificateUploadDto {
	
	private CommonsMultipartFile file;
	
	private Long appId;
	private String certPassword;


	public String getCertPassword() {
		return certPassword;
	}

	public void setCertPassword(String certPassword) {
		this.certPassword = certPassword;
	}

	public CommonsMultipartFile getFile() {
		return file;
	}

	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}
	
	

}
