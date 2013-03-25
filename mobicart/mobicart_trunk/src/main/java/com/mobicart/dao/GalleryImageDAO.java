package com.mobicart.dao;

import java.util.List;

import com.mobicart.model.GalleryImage;
import com.mobicart.model.GalleryImageExample;

public interface GalleryImageDAO extends GenericDAO<GalleryImage, Long> {
	
	/**
	 * find by xample
	 * @param galleryImageExample
	 * @return
	 */
	List<GalleryImage> findByExample(GalleryImageExample galleryImageExample);

	List<GalleryImage> findAPIByExample(GalleryImageExample galleryImageExample); 

}
