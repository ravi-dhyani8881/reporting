package com.mobicart.dto;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 
 * @author siddhartha.bhatia
 *
 */

public class IphoneLabelsDto implements Serializable{

	private static final long serialVersionUID = 1L;

	HashMap<String, String> labelsMap = null;

	String timestamp;

	public HashMap<String, String> getLabelsMap() {
		return labelsMap;
	}

	public void setLabelsMap(HashMap<String, String> labelsMap) {
		this.labelsMap = labelsMap;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}


}