package com.mobicart.util;



public class Month {
	
	private String label;
	private String value;

	Month(String label,String value){
		this.label=label;
		this.value=value;
	}
	
		
	public Month() {
		super();
		// TODO Auto-generated constructor stub
	}

	


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	@Override
	public String toString() {
		return "Month [label=" + label + ", value=" + value + "]";
	}


	

}
