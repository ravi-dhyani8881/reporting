package com.mobicart.dao;

import com.mobicart.model.Address;


public interface AddressDAO {

	public Address findAddressById(Long addressId);
	
	public Address findAddressByUserId(Long userId);
	
	public Long save(Address address); 
	
	public boolean update(Address address);
	
}
