package com.ytk.models;

import java.util.List;

import com.ytk.models.ListContact.ResponseComing;

public class ListConnection  {

	public ListConnection(){
		
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
		private List<Connections> docs;

		public Integer getStart() {
			return start;
		}

		public void setStart(Integer start) {
			this.start = start;
		}

		public List<Connections> getDocs() {
			return docs;
		}

		public void setDocs(List<Connections> docs) {
			this.docs = docs;
		}

		public Integer getNumFound() {
			return numFound;
		}

		public void setNumFound(Integer numFound) {
			this.numFound = numFound;
		}

		

	}
	
	public static class Connections  {
		
		private String connectionid;
		private String active;
		private String contactid;
		private String hideupdates;
		private String invitation;
		private String memberid;
		

		
		public String getConnectionid() {
			return connectionid;
		}



		public void setConnectionid(String connectionid) {
			this.connectionid = connectionid;
		}



		public String getActive() {
			return active;
		}



		public void setActive(String active) {
			this.active = active;
		}



		public String getContactid() {
			return contactid;
		}



		public void setContactid(String contactid) {
			this.contactid = contactid;
		}



		public String getHideupdates() {
			return hideupdates;
		}



		public void setHideupdates(String hideupdates) {
			this.hideupdates = hideupdates;
		}



		public String getInvitation() {
			return invitation;
		}



		public void setInvitation(String invitation) {
			this.invitation = invitation;
		}



		public String getMemberid() {
			return memberid;
		}



		public void setMemberid(String memberid) {
			this.memberid = memberid;
		}


	}
	

}
