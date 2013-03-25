package com.mobicart.geo;

public class Geometry {
	private Location location;
	private String locationType;
	private Area viewport;
	private Area bounds;

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * @return the locationType
	 */
	public String getLocationType() {
		return locationType;
	}

	/**
	 * @param locationType
	 *            the locationType to set
	 */
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	/**
	 * @return the viewport
	 */
	public Area getViewport() {
		return viewport;
	}

	/**
	 * @param viewport
	 *            the viewport to set
	 */
	public void setViewport(Area viewport) {
		this.viewport = viewport;
	}

	/**
	 * @return the bounds
	 */
	public Area getBounds() {
		return bounds;
	}

	/**
	 * @param bounds
	 *            the bounds to set
	 */
	public void setBounds(Area bounds) {
		this.bounds = bounds;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String s = "";
		s += "LOC:" + getLocation().toString() + " [" + getLocationType()
				+ "]\n";

		if (getViewport() != null)
			s += "VIEWPORT:" + getViewport().toString() + "\n";

		if (getBounds() != null)
			s += "BOUNDS:" + getBounds().toString() + "\n";

		return s;
	}
}
