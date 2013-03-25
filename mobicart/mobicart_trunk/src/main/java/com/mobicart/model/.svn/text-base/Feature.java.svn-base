package com.mobicart.model;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("feature")
public class Feature {


	private Long id;
	private String sName;
	private String sDescription;
	private String sIphoneHandle;
	private Float fFee;
	private Boolean bEnabled;
	private String type;
	private Boolean selected;
	private String tabTitle;
	private String tabDescription;
	//private String title;


	public void setId(Long id) {
		this.id = id;
	}


	public String getsName() {
		return sName;
	}


	public void setsName(String sName) {
		this.sName = sName == null ? null : sName.trim();
	}


	public String getsDescription() {
		return sDescription;
	}



	public void setsDescription(String sDescription) {
		this.sDescription = sDescription == null ? null : sDescription.trim();
	}



	public String getsIphoneHandle() {
		return sIphoneHandle;
	}



	public void setsIphoneHandle(String sIphoneHandle) {
		this.sIphoneHandle = sIphoneHandle == null ? null : sIphoneHandle
				.trim();
	}



	public Float getfFee() {
		return fFee;
	}



	public void setfFee(Float fFee) {
		this.fFee = fFee;
	}


	public Boolean getbEnabled() {
		return bEnabled;
	}


	public void setbEnabled(Boolean bEnabled) {
		this.bEnabled = bEnabled;
	}

	
	public Long getId() {
		return id;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	

	
	public String getType() {
		List<Long> tabIds=new ArrayList<Long>();
		tabIds.add(1L);
		tabIds.add(2L);
		tabIds.add(3L);
		tabIds.add(4L);
		
		if(tabIds.contains(id)){
			type="tab";
		}else{
			type="page";
		}
		
		return type;
	}

	
	public Boolean getSelected() {
		
		List<Long> selectedIds=new ArrayList<Long>();
		selectedIds.add(1L);
		selectedIds.add(2L);
		selectedIds.add(4L);
		selectedIds.add(5L);
		
		if(selectedIds.contains(id)){
			selected=true;
		}else{
			selected=false;
		}
		
		return selected;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String getTabTitle() {
		return tabTitle;
	}



	public void setTabTitle(String tabTitle) {
		this.tabTitle = tabTitle == null ? "" : tabTitle;
	}



	public String getTabDescription() {
		return tabDescription;
	}



	public void setTabDescription(String tabDescription) {
		this.tabDescription = tabDescription == null? "" : tabDescription;
	}


	


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Feature other = (Feature) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Feature [id=" + id + ", sName=" + sName + ", sDescription="
				+ sDescription + ", sIphoneHandle=" + sIphoneHandle + ", fFee="
				+ fFee + ", bEnabled=" + bEnabled + "]";
	}
	

	
	
}