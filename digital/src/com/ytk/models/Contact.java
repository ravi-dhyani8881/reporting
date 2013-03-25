package com.ytk.models;

import org.apache.solr.client.solrj.beans.Field;


public class Contact implements Comparable{
	
	
	@Field
	private String Connection_id;
	@Field
	private String Contact_id;
	@Field
	private String member_id;
	@Field
	private String is_connected;
	@Field
	private String is_blocked;
	@Field
	private String is_message_sent;
	
	
	public Contact(){}
	
	public Contact(String Connection_id, String Contact_id, String member_id, String is_connected, String is_blocked, String is_message_sent){
		this.Connection_id = Connection_id;
		this.Contact_id = Contact_id;
		this.member_id = member_id;
		this.is_connected = is_connected;
		this.is_blocked = is_blocked;
		this.is_message_sent = is_message_sent;
	}
	
	
	
	public String getConnection_id(){
		return this.Connection_id;
	}
	
	public void setConnection_id(String Connection_id){
		this.Connection_id = Connection_id;
	}
	
	public String getContact_id(){
		return this.Contact_id;
	}
	
	public void setContact_id(String Contact_id){
		this.Contact_id = Contact_id;
	}
	
	public String getMember_id(){
		return this.member_id;
	}
	
	public void setMember_id(String member_id){
		this.member_id = member_id;
	}
	
	public String getIs_connected(){
		return this.is_connected;
	}
	
	public void setIs_connected(String is_connected){
		this.is_connected = is_connected;
	}
	
	public String getIs_blocked(){
		return this.is_blocked;
	}
	
	public void setIs_blocked(String is_blocked){
		this.is_blocked = is_blocked;
	}
	
	public String getIs_message_sent(){
		return this.is_message_sent;
	}
	
	public void setIs_message_sent(String is_message_sent){
		this.is_message_sent = is_message_sent;
	}
	
    public int compareTo(Object obj){
    	
    	if(obj instanceof Contact){
    		Contact con = (Contact)obj;
    		String is_connected = con.getIs_connected();
    		String is_message_sent = con.getIs_message_sent();
    		int is_concted = 0;
    		int is_msg_snt = 0;
    		try{
    			is_concted = Integer.parseInt(is_connected);
    			is_msg_snt = Integer.parseInt(is_message_sent);
    			
    		}catch(NumberFormatException nfe){
    			nfe.printStackTrace();
    		}
    		return is_concted+is_msg_snt;
    
    	}else return -1;
    }
}