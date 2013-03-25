package com.mobicart.geo;

import java.util.ArrayList;
import java.util.Collection;

public class Geocode {
	private Collection<String> types = new ArrayList<String>();
	private String formattedAddress;
	private Collection<AddressComponent> addressComponents = new ArrayList<AddressComponent>();
	private Geometry geometry;
	private boolean partialMatch;

	/**
	 * @return the types
	 */
	public Collection<String> getTypes() {
		return types;
	}

	/**
	 * @param types
	 *            the types to set
	 */
	public void setTypes(Collection<String> types) {
		this.types = types;
	}

	/**
	 * @return the formattedAddress
	 */
	public String getFormattedAddress() {
		return formattedAddress;
	}

	/**
	 * @param formattedAddress
	 *            the formattedAddress to set
	 */
	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

	/**
	 * @return the addressComponents
	 */
	public Collection<AddressComponent> getAddressComponents() {
		return addressComponents;
	}

	/**
	 * @param addressComponents
	 *            the addressComponents to set
	 */
	public void setAddressComponents(
			Collection<AddressComponent> addressComponents) {
		this.addressComponents = addressComponents;
	}

	/**
	 * @return the geometry
	 */
	public Geometry getGeometry() {
		return geometry;
	}

	/**
	 * @param geometry
	 *            the geometry to set
	 */
	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	/**
	 * @return the partialMatch
	 */
	public boolean isPartialMatch() {
		return partialMatch;
	}

	/**
	 * @param partialMatch
	 *            the partialMatch to set
	 */
	public void setPartialMatch(boolean partialMatch) {
		this.partialMatch = partialMatch;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String s = "";

		s += getFormattedAddress() + "\n";

		s += "Types: ";
		int i = 0;
		for (String t : getTypes()) {
			if (i > 0)
				s += ", ";
			s += t;
			i++;
		}

		s += "\n\n";

		i = 0;
		for (AddressComponent component : getAddressComponents()) {
			if (i > 0)
				s += "\n";
			s += component.toString();
			i++;
		}

		s += "\n";

		s += getGeometry().toString();

		s += "PARTIAL: " + (isPartialMatch() ? "true" : "false");

		s += "\n";
		return s;
	}
}
