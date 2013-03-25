package com.mobicart.model.api;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonWriteNullProperties;



@JsonWriteNullProperties(false)
public class MainOrderApi implements Serializable{
	
	private int size;
	private List<ProductOrderApi> orders;
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public List<ProductOrderApi> getOrders() {
		return orders;
	}
	public void setOrders(List<ProductOrderApi> orders) {
		this.orders = orders;
	}
	
}