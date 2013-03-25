package com.mobicart.util;

import java.util.List;

import org.springframework.beans.support.PagedListHolder;

/**
 * Extends {@link PagedListHolder} to support database handled pagination
 * Its used primarly to show pagination on front end  
 * @author jasdeep.singh
 *
 * @param <E>
 */
@SuppressWarnings("serial")
public class CustomPagedListHolder<E> extends PagedListHolder<E> {
	
	/**
	 * total number of elements for particular call
	 */
	int nrOfElements;
	
	@Override
	public int getNrOfElements() {
		return this.nrOfElements>0?this.nrOfElements:super.getNrOfElements();
	}
	
	public void setNrOfElements(int nrOfElements) {
		this.nrOfElements=nrOfElements;
	}

	/**
	 * As we are fetching paginated records via database, so we dont need to fetch sublist for particular page.
	 * see #getPageList  
	 */
	@Override
	public List<E> getPageList() {
		return getSource();
	}
	
	
	
	
	
	
}
