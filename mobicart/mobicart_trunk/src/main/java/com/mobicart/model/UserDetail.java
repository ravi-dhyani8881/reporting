package com.mobicart.model;

import com.mobicart.util.FileUtils;


public class UserDetail {
	
	private String sFirstName;
	
	private String sLastName;
	
	private String sCompanyLogo;
	
	private String sCompanyLogoWebsite;

	public String getsFirstName() {
		return sFirstName;
	}

	public void setsFirstName(String sFirstName) {
		this.sFirstName = sFirstName;
	}

	public String getsLastName() {
		return sLastName;
	}

	public void setsLastName(String sLastName) {
		this.sLastName = sLastName;
	}

	public String getsCompanyLogo() {
		return sCompanyLogo;
	}

	public void setsCompanyLogo(String sCompanyLogo) {
		this.sCompanyLogo = sCompanyLogo;
	}

	public String getsCompanyLogoWebsite() {
		try{
			if(sCompanyLogo!=null)
				sCompanyLogoWebsite=FileUtils.stuffedFilename(sCompanyLogo, "_website");
			}catch(Exception e){
				sCompanyLogoWebsite=null;
			}

		return sCompanyLogoWebsite;
	}

	public void setsCompanyLogoWebsite(String sCompanyLogoWebsite) {
		this.sCompanyLogoWebsite = sCompanyLogoWebsite;
	}
	

}
