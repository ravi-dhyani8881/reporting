package com.mobicart.model;

public class AppTerritory {

	private Long appId;
	private Long territoryId;

	
	public AppTerritory() {
		super();
	}

	public AppTerritory(Long appId, Long territoryId) {
		super();
		this.appId = appId;
		this.territoryId = territoryId;
	}

	public Long getAppId() {
		return appId;
	}

	
	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public Long getTerritoryId() {
		return territoryId;
	}

	public void setTerritoryId(Long territoryId) {
		this.territoryId = territoryId;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appId == null) ? 0 : appId.hashCode());
		result = prime * result
				+ ((territoryId == null) ? 0 : territoryId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppTerritory other = (AppTerritory) obj;
		if (appId == null) {
			if (other.appId != null)
				return false;
		} else if (!appId.equals(other.appId))
			return false;
		if (territoryId == null) {
			if (other.territoryId != null)
				return false;
		} else if (!territoryId.equals(other.territoryId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AppTerritory [appId=" + appId + ", territoryId=" + territoryId
				+ "]";
	}
	
	
	
}