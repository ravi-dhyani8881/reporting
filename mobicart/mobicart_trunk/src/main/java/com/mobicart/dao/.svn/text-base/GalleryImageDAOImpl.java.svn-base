package com.mobicart.dao;

import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.util.ClassUtils;

import com.mobicart.model.GalleryImage;
import com.mobicart.model.GalleryImageExample;

public class GalleryImageDAOImpl extends SqlMapClientDaoSupport implements
		GalleryImageDAO {

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<GalleryImage> findByExample(
			GalleryImageExample galleryImageExample) {
		List<GalleryImage> galleryImages = null;

		galleryImages = getSqlMapClientTemplate().queryForList(
				"gallery_images.selectByExample", galleryImageExample);

		if (galleryImages == null) {
			throw new ObjectRetrievalFailureException(
					ClassUtils.getShortName(this.getClass()),
					galleryImageExample);
		}

		return galleryImages;
	}


	/**
	 * {@inheritDoc}
	 */

	public Long create(GalleryImage newInstance) {
		return (Long) getSqlMapClientTemplate().insert("gallery_images.insert",
				newInstance);
	}



	/**
	 * {@inheritDoc}
	 */
	public void delete(GalleryImage persistentObject) {
		getSqlMapClientTemplate().delete("gallery_images.deleteByPrimaryKey",
				persistentObject);
	}


	/**
	 * {@inheritDoc}
	 */

	public GalleryImage find(Long id) {
		GalleryImage object = null;
		object = (GalleryImage) getSqlMapClientTemplate().queryForObject(
				"gallery_images.selectByPrimaryKey", new GalleryImage(id));

		if (object == null) {
			throw new ObjectRetrievalFailureException(
					ClassUtils.getShortName(this.getClass()), id);
		}

		return object;
	}


	/**
	 * {@inheritDoc}
	 */

	@SuppressWarnings("unchecked")
	public List<GalleryImage> findAll() {
		List<GalleryImage> galleryImages = null;

		galleryImages = getSqlMapClientTemplate().queryForList(
				"gallert_images.selectAll", null);
		if (galleryImages == null) {
			throw new ObjectRetrievalFailureException(
					ClassUtils.getShortName(this.getClass()), null);
		}
		return galleryImages;

	}

	public void update(GalleryImage transientObject) {
		getSqlMapClientTemplate().update(
				"gallery_images.updateByPrimaryKeySelective", transientObject);
	}

	/******************** API METHODS ***************/

	@SuppressWarnings("unchecked")
	public List<GalleryImage> findAPIByExample(
			GalleryImageExample galleryImageExample) {
		List<GalleryImage> galleryImages = null;
		galleryImages = getSqlMapClientTemplate().queryForList(
				"gallery_images.selectByExample", galleryImageExample);
		if (galleryImages == null) {
			throw new ObjectRetrievalFailureException(
					ClassUtils.getShortName(this.getClass()), null);
		}

		return galleryImages;
	}

}
