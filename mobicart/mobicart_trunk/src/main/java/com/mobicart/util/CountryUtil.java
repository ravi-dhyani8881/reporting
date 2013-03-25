package com.mobicart.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountryUtil {
	
	public static List<String> getEUCountries(){
		List<String> countries=new ArrayList<String>();
		 countries.add("Austria");
		 countries.add("Belgium");
		 countries.add("Bulgaria");
		 
		 //countries.add("Croatia");
		 countries.add("Czech Republic");
		 countries.add("Denmark");
		 countries.add("Estonia");
		 countries.add("Finland");
		 
		 countries.add("France");
		 countries.add("Germany");
		 
		 countries.add("Greece");
		 countries.add("Hungary");
		 countries.add("Ireland");
		 countries.add("Italy");
		 countries.add("Latvia");
		 
		 countries.add("Lithuania");
		 countries.add("Luxembourg");
		 //countries.add("Macedonia");
		 countries.add("Malta");
		 //countries.add("Moldova");
		 countries.add("Netherlands");
		 //countries.add("Norway");
		 countries.add("Poland");
		 countries.add("Portugal");
		 //countries.add("Romania");
		 //countries.add("Russian Federation");
		 countries.add("Slovakia");
		 countries.add("Slovenia");
		 countries.add("Spain");
		 countries.add("Sweden");
		 countries.add("Switzerland");
		 countries.add("United Kingdom");
		return countries;
	}
	 

	
	public static String getCountrySymbolByCode(String currencyCode){
		final Map<String,String> symbolChart= new HashMap<String,String>();
		symbolChart.put("USD", "&#36;");
		symbolChart.put("GBP", "&#163;");
		symbolChart.put("EUR", "&#8364;");
		if(symbolChart.containsKey(currencyCode)){
			return symbolChart.get(currencyCode);
		}else{
			return currencyCode+"&nbsp;";
		}
		
	}
	
	
	
	
	public static String getCurrencySymbol(String currencyCode){
		final Map<String,String> symbolChart= new HashMap<String,String>();
		symbolChart.put("USD", "$");
		symbolChart.put("GBP", "£");
		symbolChart.put("EUR", "€");
		
		if(symbolChart.containsKey(currencyCode)){
			return symbolChart.get(currencyCode);
		}else{
			return currencyCode;
		}
		
	}
	
	
	
}
