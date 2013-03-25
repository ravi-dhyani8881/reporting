package com.mobicart.geo;

import java.util.ArrayList;
import java.util.Collection;

public class AddressComponent {
	private String longName;
	private String shortName;
	private Collection<String> types = new ArrayList<String>();

	/**
	 * @return the longName
	 */
	public String getLongName() {
		return longName;
	}

	/**
	 * @param longName
	 *            the longName to set
	 */
	public void setLongName(String longName) {
		this.longName = longName;
	}

	/**
	 * @return the shortName
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * @param shortName
	 *            the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String s = "";

		s += "Short name: " + getShortName() + "\n";
		s += "Long name: " + getLongName() + "\n";
		s += "Types: ";
		int i = 0;
		for (String t : getTypes()) {
			if (i > 0)
				s += ", ";
			s += t;
			i++;
		}
		s += "\n";

		return s;
	}
}
