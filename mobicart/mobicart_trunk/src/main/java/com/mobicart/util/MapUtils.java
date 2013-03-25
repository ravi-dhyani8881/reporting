package com.mobicart.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.stream.StreamSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.castor.CastorMarshaller;
import org.springframework.web.client.RestTemplate;

import com.mobicart.dto.LatLong;
import com.mobicart.geo.Geocode;
import com.mobicart.geo.GeocodeResponse;

public class MapUtils {

	
	
	public static LatLong  getLatLong(String city) throws XmlMappingException, IOException {
		LatLong latLong=new LatLong(); 
		
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> vars = new HashMap<String, String>();

		vars.put("city", city);
		vars.put("sensor", "false");
		String result = restTemplate
				.getForObject(
						"http://maps.googleapis.com/maps/api/geocode/xml?address={city}&sensor={sensor}",
						String.class, vars);

		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"classpath:com/mobicart/geo/geoContext.xml");

		CastorMarshaller marshaller = ctx.getBean("marshaller",
				CastorMarshaller.class);

		StreamSource s = new StreamSource(new StringReader(result));
		GeocodeResponse response = (GeocodeResponse) marshaller.unmarshal(s);

		for (Geocode code : response.getGeocodes()) {
			latLong.setLatitude(code.getGeometry().getLocation().getLatitude());
			latLong.setLongitude(code.getGeometry().getLocation().getLongitude());
		}

		return latLong;

	}
	
	
	
}
