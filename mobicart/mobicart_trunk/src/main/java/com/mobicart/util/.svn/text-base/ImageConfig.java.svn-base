package com.mobicart.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ImageConfig {

	/**
	 * Using property 'EL' syntax to load values from the imageProperties value
	 */

	@Value("#{imageProperties['magicPath']}")
	private String magicPath;

	@Value("#{imageProperties['appIconAndroid3']}")
	private String appIconAndroid3;
	@Value("#{imageProperties['appIconAndroid4']}")
	private String appIconAndroid4;
	@Value("#{imageProperties['appIconAndroid6']}")
	private String appIconAndroid6;
	@Value("#{imageProperties['appIconIpad']}")
	private String appIconIpad;
	@Value("#{imageProperties['appIconIphone']}")
	private String appIconIphone;

	public String getMagicPath() {
		return magicPath;
	}

	public String getAppIconAndroid3() {
		return appIconAndroid3;
	}

	public String getAppIconAndroid4() {
		return appIconAndroid4;
	}

	public String getAppIconAndroid6() {
		return appIconAndroid6;
	}

	public String getAppIconIpad() {
		return appIconIpad;
	}

	public String getAppIconIphone() {
		return appIconIphone;
	}

	
	
	
}