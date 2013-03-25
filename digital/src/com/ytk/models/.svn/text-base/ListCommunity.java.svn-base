package com.ytk.models;

import java.util.List;

import org.codehaus.jackson.annotate.JsonWriteNullProperties;


public class ListCommunity {
	
	public ListCommunity(){
		
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
		private List<Communities> docs;

		public Integer getStart() {
			return start;
		}

		public void setStart(Integer start) {
			this.start = start;
		}

		public List<Communities> getDocs() {
			return docs;
		}

		public void setDocs(List<Communities> docs) {
			this.docs = docs;
		}

		public Integer getNumFound() {
			return numFound;
		}

		public void setNumFound(Integer numFound) {
			this.numFound = numFound;
		}

	}
	public static class Communities implements Comparable{
		private String id;
		private String nm;
		private String add1;
		private String ct;
		private String st;
		private String z;
		private String ph;
		private String web;
		private String pcat;
		private String scat;
		private String neigh;
		private String img;
		private String sfn;
		private String totalRecords;
		private String trat;
		private String trev;
		private String parentCategoryCount;
		private String SubCategoryCount;

		
		public String getParentCategoryCount() {
			return parentCategoryCount;
		}

		public void setParentCategoryCount(String parentCategoryCount) {
			this.parentCategoryCount = parentCategoryCount;
		}

		public String getSubCategoryCount() {
			return SubCategoryCount;
		}

		public void setSubCategoryCount(String subCategoryCount) {
			SubCategoryCount = subCategoryCount;
		}

		public String getTrat() {
			return trat;
		}

		public void setTrat(String trat) {
			this.trat = trat;
		}

		public String getPcat() {
			return pcat;
		}

		public void setPcat(String pcat) {
			this.pcat = pcat;
		}

		public String getScat() {
			return scat;
		}

		public void setScat(String scat) {
			this.scat = scat;
		}

		public String getNeigh() {
			return neigh;
		}

		public void setNeigh(String neigh) {
			this.neigh = neigh;
		}

		public String getTotalRecords() {
			return totalRecords;
		}

		public void setTotalRecords(String totalRecords) {
			this.totalRecords = totalRecords;
		}


		public String getTrev() {
			return trev;
		}

		public void setTrev(String trev) {
			this.trev = trev;
		}

		public String getSfn() {
			return sfn;
		}

		public void setSfn(String sfn) {
			this.sfn =sfn.toLowerCase();
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getNm() {
			return nm;
		}

		public void setNm(String nm) {
			this.nm = nm;
		}

		public String getAdd1() {
			return add1;
		}

		public void setAdd1(String add1) {
			this.add1 = add1;
		}



		public String getCt() {
			return ct;
		}

		public void setCt(String ct) {
			this.ct = ct;
		}

		public String getSt() {
			return st;
		}

		public void setSt(String st) {
			this.st = st;
		}

		public String getZ() {
			return z;
		}

		public void setZ(String z) {
			this.z = z;
		}

		public String getPh() {
			return ph;
		}

		public void setPh(String ph) {
			this.ph = ph;
		}

		public String getWeb() {
			return web;
		}

		public void setWeb(String web) {
			this.web = web;
		}

		public String getImg() {
			return img;
		}

		public void setImg(String img) {
			this.img = img;
		}


		public int compareTo(Object obj){
			int i = 0;
			if(obj instanceof Communities){
				Communities mem = (Communities)obj;
				if(this.getNm().toLowerCase().compareTo(mem.getNm().toLowerCase()) < 1  )
				{
					i = -1;
				}
				else
				{
					i = 1;
				}
			}
			return i;
		}
		
	}
	@JsonWriteNullProperties(false)
	public static class CommunitiesOut {
		private String ID;
		private String TotalRecords;
		private String Address1;
		private String City;
		private String State;
		private String ZipCode;
		private String ImageName;
		private String SafeName;
		private String Name;
		private String ParentCategoryCount;
		private String SubCategoryCount;
		private String ParentCategoryList;
		private String SubCategoryList;
		private String NeighborhoodList;
		private String Rating;
		private String TotalReviews;
		private String Web;
		private String Phone;
		private String NeighborhoodCount;
		
		
		
		public String getTotalRecords() {
			return TotalRecords;
		}
		public void setTotalRecords(String totalRecords) {
			TotalRecords = totalRecords;
		}
		public String getNeighborhoodCount() {
			return NeighborhoodCount;
		}
		public void setNeighborhoodCount(String neighborhoodCount) {
			NeighborhoodCount = neighborhoodCount;
		}
		public String getID() {
			return ID;
		}
		public void setID(String iD) {
			ID = iD;
		}
		public String getAddress1() {
			return Address1;
		}
		public void setAddress1(String address1) {
			Address1 = address1;
		}
		public String getCity() {
			return City;
		}
		public void setCity(String city) {
			City = city;
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
		public String getImageName() {
			return ImageName;
		}
		public void setImageName(String imageName) {
			ImageName = imageName;
		}
		public String getSafeName() {
			return SafeName;
		}
		public void setSafeName(String safeName) {
			SafeName = safeName;
		}
		public String getName() {
			return Name;
		}
		public void setName(String name) {
			Name = name;
		}
		public String getParentCategoryCount() {
			return ParentCategoryCount;
		}
		public void setParentCategoryCount(String parentCategoryCount) {
			ParentCategoryCount = parentCategoryCount;
		}
		public String getSubCategoryCount() {
			return SubCategoryCount;
		}
		public void setSubCategoryCount(String subCategoryCount) {
			SubCategoryCount = subCategoryCount;
		}
		public String getParentCategoryList() {
			return ParentCategoryList;
		}
		public void setParentCategoryList(String parentCategoryList) {
			ParentCategoryList = parentCategoryList;
		}
		public String getSubCategoryList() {
			return SubCategoryList;
		}
		public void setSubCategoryList(String subCategoryList) {
			SubCategoryList = subCategoryList;
		}
		public String getNeighborhoodList() {
			return NeighborhoodList;
		}
		public void setNeighborhoodList(String neighborhoodList) {
			NeighborhoodList = neighborhoodList;
		}
		public String getRating() {
			return Rating;
		}
		public void setRating(String rating) {
			Rating = rating;
		}
		public String getTotalReviews() {
			return TotalReviews;
		}
		public void setTotalReviews(String totalReviews) {
			TotalReviews = totalReviews;
		}
		public String getWeb() {
			return Web;
		}
		public void setWeb(String web) {
			Web = web;
		}
		public String getPhone() {
			return Phone;
		}
		public void setPhone(String phone) {
			Phone = phone;
		}
		
	}

}
