package com.ytk.models;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;

import com.ytk.models.ListCollege.College;
import com.ytk.models.ListCommunity.CommunitiesOut;
import com.ytk.models.ListContact.ContactsOut;
import com.ytk.models.ListMessage.Messages;
@JsonWriteNullProperties(false)
public class ResultDoc {
	private int size;
	private List<CommunitiesOut> community;
	private List <ContactsOut> contacts;
	private List<Messages> messages;
	private List<Things> things;
	
	public List<ContactsOut> getContacts() {
		return contacts;
	}
	public void setContacts(List<ContactsOut> contacts) {
		this.contacts = contacts;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public List<CommunitiesOut> getCommunity() {
		return community;
	}
	public void setCommunity(List<CommunitiesOut> community) {
		this.community = community;
	}
	public List<Messages> getMessages() {
		return messages;
	}
	public void setMessages(List<Messages> messages) {
		this.messages = messages;
	}
	public List<Things> getThings() {
		return things;
	}
	public void setThings(List<Things> things) {
		this.things = things;
	}


}
