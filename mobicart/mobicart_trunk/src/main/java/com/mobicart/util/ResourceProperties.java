package com.mobicart.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ResourceProperties {

	private static final String BUNDLE_NAME = "messages"; //$NON-NLS-1$
	
	private static final String IMAGE_BUNDLE_NAME = "image"; //$NON-NLS-1$


	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);
	
	private static final ResourceBundle IMAGE_RESOURCE_BUNDLE = ResourceBundle
	.getBundle(IMAGE_BUNDLE_NAME);
	
	
	private ResourceProperties() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}

	public static String getImageString(String key) {
		try {
			return IMAGE_RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}

	

}
