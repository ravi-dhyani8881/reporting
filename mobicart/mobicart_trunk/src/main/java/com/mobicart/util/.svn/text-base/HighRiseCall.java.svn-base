package com.mobicart.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

public class HighRiseCall {

	private String highriseUrl = "https://forevermanagementtest.highrisehq.com";
	private String apiToken = "2ece5989d711078140d3be2b0c2c9958";
	private String password = "tilakraj77";

	
	
	public String addContact(String xmlInput) throws HttpException, IOException {

		// Get target URL
		String strURL = highriseUrl + "/people.xml";

		PostMethod post = new PostMethod(strURL);

		StringRequestEntity entity = null;
		try {
			entity = new StringRequestEntity(xmlInput, "application/xml",
					"ISO-8859-1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		post.setRequestEntity(entity);
		// Get HTTP client
		HttpClient httpclient = new HttpClient();

		HttpState state = httpclient.getState();
		// Set credentials on the client
		state.setCredentials(new AuthScope(null, 443, null),
				new UsernamePasswordCredentials(apiToken, password));
		post.setDoAuthentication(true);
		// Execute request

		int result = httpclient.executeMethod(post);
		// Display status code
		 
		// Display response
	 

		// Release current connection to the connection pool once you are
		// done
		post.releaseConnection();

		return post.getResponseBodyAsString();
	}

	
	

	public void setHighriseUrl(String highriseUrl) {
		this.highriseUrl = highriseUrl;
	}

	public String getHighriseUrl() {
		return highriseUrl;
	}

	public String getApiToken() {
		return apiToken;
	}

	public void setApiToken(String apiToken) {
		this.apiToken = apiToken;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	/**
	 * 
	 * @param args
	 *            command line arguments Argument 0 is a URL to a web server
	 * 
	 */

	public static void main(String[] args) throws Exception {
		StringBuffer input = new StringBuffer();
		input.append("<person>" +
				"<first-name>Ishan </first-name>"
				+ "<last-name>Mehta</last-name>" + 
				  "<title>Mr.</title>"
				+ "<contact-data>" +
				"<email-addresses type='array'>"
				+ "<email-address><address>ishan@gmail.com</address>"
				+ "<location>Work</location>" + "</email-address>"
				+ "</email-addresses>" + "<addresses type='array'>"
				+ "<address><city>Mohali</city>" + "<country>India</country>"
				+ "<location>Work</location>" + "<state>54</state>"
				+ "<street></street>" + "<zip>234242424</zip>"
				+ "</address></addresses>" + "<phone-numbers type='array'>"
				+ "<phone-number><location>Work</location>"
				+ "<number>9848456123</number>"
				+ "</phone-number></phone-numbers>" + "</contact-data>"
				+ "</person>");

	HighRiseCall highRiseCall=new HighRiseCall();
	highRiseCall.addContact(input.toString());
	

	}

	
	
}
