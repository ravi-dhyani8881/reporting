package com.main.test;

import java.util.UUID;

public class UuidTest {

	public String getUniqueId(){
        return UUID.randomUUID().toString().toLowerCase();                
    }
    
	public static void main(String[] args) {
	
		UuidTest obj=new UuidTest();
		System.out.println(obj.getUniqueId());
	}

}
