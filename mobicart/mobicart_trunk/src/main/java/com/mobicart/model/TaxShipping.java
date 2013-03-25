package com.mobicart.model;

public class TaxShipping {

	private Tax tax;
	private Shipping shipping;

	public Tax getTax() {
		return tax;
	}
	public void setTax(Tax tax) {
		this.tax = tax;
	}
	public Shipping getShipping() {
		return shipping;
	}
	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}
	@Override
	public String toString() {
		return "TaxShipping [tax=" + tax + ", shipping=" + shipping + "]";
	}
	

	
	
}
