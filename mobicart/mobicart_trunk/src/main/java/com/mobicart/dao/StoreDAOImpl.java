package com.mobicart.dao;

import java.util.List;


import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.mobicart.model.GalleryImage;
import com.mobicart.model.Store;
import com.mobicart.model.StoreExample;

public class StoreDAOImpl extends SqlMapClientDaoSupport implements StoreDAO {

	public Long save(Store store) throws DataAccessException{
		return (Long) getSqlMapClientTemplate().insert("stores.insert", store);
	}

	public boolean update(Store store) throws DataAccessException {
			boolean retVal = false;
			int rows = getSqlMapClientTemplate().update(
					"stores.updateByPrimaryKeySelective", store);
			if (rows != 0) {
				retVal = true;
			}
		return retVal;
	}

	public Store findStoreById(Long storeId) throws DataAccessException {
		return (Store) getSqlMapClientTemplate().queryForObject("stores.selectByPrimaryKey", new Store(storeId));
	}

	public Store findStoreByUserId(Long userId) throws DataAccessException  {
		
		Store store= (Store) getSqlMapClientTemplate().queryForObject(
					"stores.selectByUserId", userId);
		
		
		return store;
	}
	
	public Long saveGalleryImage(GalleryImage galleryImage) throws DataAccessException {
		Long galleryImageId = null;
		galleryImageId = (Long) getSqlMapClientTemplate().insert("gallery_images.insert", galleryImage);
		return galleryImageId;

	}
	
	@SuppressWarnings("unchecked")
	public List<GalleryImage> findAllGalleryImages() throws  DataAccessException{
		List<GalleryImage> galleryImages = null;
		galleryImages = getSqlMapClientTemplate().queryForList("gallery_images.selectAll", null);
		return galleryImages;
	}

	
	@SuppressWarnings("unchecked")
	public List<Store> findStoresByExample(StoreExample storeExample) throws  DataAccessException {
		List<Store> stores = null;
		stores =  getSqlMapClientTemplate().queryForList("stores.selectByExample", storeExample);
		return stores;
	}

	/** API Methods**/
	@SuppressWarnings("unchecked")
	public List<Store> findAPIStoresByExample(StoreExample storeExample) throws  DataAccessException {
		List<Store> stores = null;
			stores =  getSqlMapClientTemplate().queryForList(
					"stores.apiSelectByExample", storeExample);
		return stores;
	}
	
	
	public Store findAPIStoreById(Long storeId) throws  DataAccessException {
		Store store = null;
			store = (Store) getSqlMapClientTemplate().queryForObject(
					"stores.apiSelectByPrimaryKey", new Store(storeId));
		return store;
	}
	
	
	public Store findAPIStoreShipping(Long storeId) throws  DataAccessException {
		Store store = null;
			store = (Store) getSqlMapClientTemplate().queryForObject(
					"stores.apiSelectStoreShipping", new Store(storeId));
		return store;
	}
	
	public Store findAPIStoreTax(Long storeId) throws DataAccessException{
		Store store = null;
			store = (Store) getSqlMapClientTemplate().queryForObject(
					"stores.apiSelectStoreTax", new Store(storeId));
		return store;
	}
	

}
