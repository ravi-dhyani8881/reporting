package com.mobicart.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.mobicart.model.GalleryImage;
import com.mobicart.model.Store;
import com.mobicart.model.StoreExample;

public interface StoreDAO {

	public Store findStoreById(Long storeId)  throws DataAccessException;

	public List<Store> findStoresByExample(StoreExample storeExample)  throws DataAccessException;
	
	public Store findStoreByUserId(Long userId)  throws DataAccessException;
	
	public Long save(Store store)throws DataAccessException ; 
	
	public boolean update(Store store) throws DataAccessException;
	
	public Long saveGalleryImage(GalleryImage galleryImage) throws DataAccessException;
	
	public List<GalleryImage> findAllGalleryImages() throws DataAccessException;
	
	
	/***API METHODS**/
	public List<Store> findAPIStoresByExample(StoreExample storeExample)  throws DataAccessException;
	
	public Store findAPIStoreById(Long storeId)  throws DataAccessException;
	
	public Store findAPIStoreShipping(Long storeId)  throws DataAccessException;
	
	public Store findAPIStoreTax(Long storeId)  throws DataAccessException;
}
