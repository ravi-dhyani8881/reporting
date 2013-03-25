package com.ytk.models;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;
import org.springframework.web.bind.annotation.Mapping;


public class ListContact {
	
	public ListContact(){
		
	}
	
	private ResponseHeader responseHeader;
	private ResponseComing response;

	public ResponseHeader getResponseHeader() {
		return responseHeader;
	}

	public void setResponseHeader(ResponseHeader responseHeader) {
		this.responseHeader = responseHeader;
	}
	public ResponseComing getResponse() {
		return response;
	}
	public void setResponse(ResponseComing response) {
		this.response = response;
	}

	public static class ResponseComing {
		
		private Integer numFound;
		private Integer start;
		private List<Contacts> docs;

		public Integer getStart() {
			return start;
		}

		public void setStart(Integer start) {
			this.start = start;
		}

		public List<Contacts> getDocs() {
			return docs;
		}

		public void setDocs(List<Contacts> docs) {
			this.docs = docs;
		}

		public Integer getNumFound() {
			return numFound;
		}

		public void setNumFound(Integer numFound) {
			this.numFound = numFound;
		}

		

	}
	
	public static class Contacts implements Comparable{
		
		private String id;
		private String address ;
		private String birthday;
		private String city;
		private String displayname;
		private String email;
		private String firstname;
		private String image;
		private String lastname;
		private String middlename;
		private String screenname;
		private String state;
		private String website;
		private String zip;
		private String is_myFriend = "false";
		private String isemailnoteenable;
		private String isviewconnections;
		private String connectionList;
		private String contactList;
		private String mutualCount;
		private String totalRecords;
		
		public String getTotalRecords() {
			return totalRecords;
		}
		public void setTotalRecords(String totalRecords) {
			this.totalRecords = totalRecords;
		}
		public String getMutualCount() {
			return mutualCount;
		}
		public void setMutualCount(String mutualCount) {
			this.mutualCount = mutualCount;
		}
		public String getContactList() {
			return contactList;
		}
		public void setContactList(String contactList) {
			this.contactList = contactList;
		}
		public String getIsemailnoteenable() {
			return isemailnoteenable;
		}
		public void setIsemailnoteenable(String isemailnoteenable) {
			this.isemailnoteenable = isemailnoteenable;
		}
		public String getIsviewconnections() {
			return isviewconnections;
		}
		public void setIsviewconnections(String isviewconnections) {
			this.isviewconnections = isviewconnections;
		}
		public String getConnectionList() {
			return connectionList;
		}
		public void setConnectionList(String connectionList) {
			this.connectionList = connectionList;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getBirthday() {
			return birthday;
		}
		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getDisplayname() {
			return displayname;
		}
		public void setDisplayname(String displayname) {
			this.displayname = displayname;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getFirstname() {
			return firstname;
		}
		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		public String getLastname() {
			return lastname;
		}
		public void setLastname(String lastname) {
			this.lastname = lastname;
		}
		public String getMiddlename() {
			return middlename;
		}
		public void setMiddlename(String middlename) {
			this.middlename = middlename;
		}
		public String getScreenname() {
			return screenname;
		}
		public void setScreenname(String screenname) {
			this.screenname = screenname;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getWebsite() {
			return website;
		}
		public void setWebsite(String website) {
			this.website = website;
		}
		public String getZip() {
			return zip;
		}
		public void setZip(String zip) {
			this.zip = zip;
		}
		public String getIs_myFriend() {
		return is_myFriend;
	}
	public void setIs_myFriend(String isMyFriend) {
		is_myFriend = isMyFriend;
	}
	public int compareTo(Object obj){
		int i = 0;
		if(obj instanceof Contacts){
			Contacts mem = (Contacts)obj;
			if(this.getDisplayname().toLowerCase().compareTo(mem.getDisplayname().toLowerCase()) < 1  )
			{
				i = -1;
				if(this.getIs_myFriend().equals("true"))
				i = -3;
				else if(this.getIs_myFriend().equals("false"))
				i = 1;
			}
			else
			{
				i = 1;
			}
		}
		return i;
	}
		
//		private String ConnectionId;
//		private String Contact_id;
//		private String member_id;
//		private String is_connected;
//		private String is_blocked;
//		private String is_message_sent;
//		private String is_myFriend = "false";
//		
//		public String getIs_myFriend() {
//			return is_myFriend;
//		}
//		public void setIs_myFriend(String isMyFriend) {
//			is_myFriend = isMyFriend;
//		}
//		public String getConnectionId() {
//			return ConnectionId;
//		}
//		public void setConnectionId(String connectionId) {
//			ConnectionId = connectionId;
//		}
//		public String getContact_id() {
//			return Contact_id;
//		}
//		public void setContact_id(String contactId) {
//			Contact_id = contactId;
//		}
//		public String getMember_id() {
//			return member_id;
//		}
//		public void setMember_id(String memberId) {
//			member_id = memberId;
//		}
//		public String getIs_connected() {
//			return is_connected;
//		}
//		public void setIs_connected(String isConnected) {
//			is_connected = isConnected;
//		}
//		public String getIs_blocked() {
//			return is_blocked;
//		}
//		public void setIs_blocked(String isBlocked) {
//			is_blocked = isBlocked;
//		}
//		public String getIs_message_sent() {
//			return is_message_sent;
//		}
//		public void setIs_message_sent(String isMessageSent) {
//			is_message_sent = isMessageSent;
//		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	
	
	
	
	
	
	
	@JsonWriteNullProperties(false)
	public static class ContactsOut {
		private String ID;
		private String Address ;
		private String City;
		private String DisplayName;
		private String EmailAddress;
		private String ProfileImageName;
		private String ScreenName;
		private String State;
		private String ZipCode;
		private String ContactList;
		private String MutualFriendCount;
		private String IsConnection;
		private String Country;
		private String IsEmailNoteEnalbe;
		private String isViewConnections;
		private String totalRecords;

		
		
		public String getTotalRecords() {
			return totalRecords;
		}
		public void setTotalRecords(String totalRecords) {
			this.totalRecords = totalRecords;
		}
		public String getIsEmailNoteEnalbe() {
			return IsEmailNoteEnalbe;
		}
		public void setIsEmailNoteEnalbe(String isEmailNoteEnalbe) {
			IsEmailNoteEnalbe = isEmailNoteEnalbe;
		}
		public String getIsViewConnections() {
			return isViewConnections;
		}
		public void setIsViewConnections(String isViewConnections) {
			this.isViewConnections = isViewConnections;
		}
		public String getID() {
			return ID;
		}
		public void setID(String iD) {
			ID = iD;
		}
		public String getAddress() {
			return Address;
		}
		public void setAddress(String address) {
			Address = address;
		}
		public String getCity() {
			return City;
		}
		public void setCity(String city) {
			City = city;
		}
		public String getDisplayName() {
			return DisplayName;
		}
		public void setDisplayName(String displayName) {
			DisplayName = displayName;
		}
		public String getEmailAddress() {
			return EmailAddress;
		}
		public void setEmailAddress(String emailAddress) {
			EmailAddress = emailAddress;
		}
		public String getProfileImageName() {
			return ProfileImageName;
		}
		public void setProfileImageName(String profileImageName) {
			ProfileImageName = profileImageName;
		}
		public String getScreenName() {
			return ScreenName;
		}
		public void setScreenName(String screenName) {
			ScreenName = screenName;
		}
		public String getState() {
			return State;
		}
		public void setState(String state) {
			State = state;
		}
		public String getZipCode() {
			return ZipCode;
		}
		public void setZipCode(String zipCode) {
			ZipCode = zipCode;
		}
		public String getContactList() {
			return ContactList;
		}
		public void setContactList(String contactList) {
			ContactList = contactList;
		}
		public String getMutualFriendCount() {
			return MutualFriendCount;
		}
		public void setMutualFriendCount(String mutualFriendCount) {
			MutualFriendCount = mutualFriendCount;
		}
		public String getIsConnection() {
			return IsConnection;
		}
		public void setIsConnection(String isConnection) {
			IsConnection = isConnection;
		}
		public String getCountry() {
			return Country;
		}
		public void setCountry(String country) {
			Country = country;
		}
		
		
		
		
		
		
		
	}

}
