package com.ytk.models;

import java.util.List;

import com.ytk.models.ListMember.Members;


public class ListCollege {

	public ListCollege(){
		
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
		private List<College> docs;

		public Integer getStart() {
			return start;
		}

		public void setStart(Integer start) {
			this.start = start;
		}

		public List<College> getDocs() {
			return docs;
		}

		public void setDocs(List<College> docs) {
			this.docs = docs;
		}

		public Integer getNumFound() {
			return numFound;
		}

		public void setNumFound(Integer numFound) {
			this.numFound = numFound;
		}

		

	}
	
	public static class College  {
		
		private String collegeId;
		private String cName;
		private String cUrl;
		private String cType ;
		private String cAddress;
		
		public String getCollegeId() {
			return collegeId;
		}
		public void setCollegeId(String collegeId) {
			this.collegeId = collegeId;
		}
		public String getcName() {
			return cName;
		}
		public void setcName(String cName) {
			this.cName = cName;
		}
		public String getcUrl() {
			return cUrl;
		}
		public void setcUrl(String cUrl) {
			this.cUrl = cUrl;
		}
		public String getcType() {
			return cType;
		}
		public void setcType(String cType) {
			this.cType = cType;
		}
		public String getcAddress() {
			return cAddress;
		}
		public void setcAddress(String cAddress) {
			this.cAddress = cAddress;
		}
		
//		public int compareTo(Object obj){
//			if(obj instanceof College){
//				College mem = (College)obj;
//				if(this.getcName().toLowerCase().compareToIgnoreCase(mem.getcName().toLowerCase()) < 1  )
//				{
//					if(this.getcName().charAt(0) == mem.getcName().charAt(0))
//					return -4;
//					else
//					return -3;
//				}
//				else
//				{
//					return 1;
//				}
//				 
//			}else return 10;
//		}
		

	}

}
