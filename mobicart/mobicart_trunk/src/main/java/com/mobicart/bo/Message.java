/**
 * 
 */
package com.mobicart.bo;

import java.util.List;

import org.codehaus.jackson.annotate.JsonWriteNullProperties;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author varun.rathore
 *
 */
@XStreamAlias("message")
@JsonWriteNullProperties(false)
public class Message {
	
	private String id;
	private String message;
	
	@XStreamAlias("errors")
	private List<Error> errors;
	
	
	public Message() {

	}
	
	public Message(String message) {
		this.setMessage(message);
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}
	
	
	
}
