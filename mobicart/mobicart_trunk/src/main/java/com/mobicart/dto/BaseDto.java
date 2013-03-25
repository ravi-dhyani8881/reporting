package com.mobicart.dto;

import java.io.Serializable;
/**
 * Base Dto copied from Appfuse code
 * @author jasdeep.singh
 *
 */
public abstract class BaseDto implements Serializable {    

    /**
	 * 
	 */
	private static final long serialVersionUID = 1094205726973330213L;

	/**
     * Returns a multi-line String with key=value pairs.
     * @return a String representation of this class.
     */
    public abstract String toString();

    /**
     * Compares object equality. 
     * @param o object to compare to
     * @return true/false based on equality tests
     */
    public abstract boolean equals(Object o);

    /**
     * When you override equals, you should override hashCode. See "Why are
     * equals() and hashCode() importation" for more information:
     * http://www.hibernate.org/109.html
     * @return hashCode
     */
    public abstract int hashCode();
}
