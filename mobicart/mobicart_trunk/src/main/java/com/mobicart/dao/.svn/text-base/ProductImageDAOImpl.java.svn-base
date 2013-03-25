package com.mobicart.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.mobicart.model.AppFeatures;
import com.mobicart.model.ProductImage;
import com.mobicart.model.ProductImageExample;

@Repository
public class ProductImageDAOImpl implements ProductImageDAO {

	@Autowired
	SqlMapClient sqlMapClient;
	
	public Long create(ProductImage newInstance) {
		Long productImageId=null;
		
		try {
			productImageId=(Long) sqlMapClient.insert("product_images.insert", newInstance);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return productImageId;
	}

	public ProductImage find(Long id) {
		ProductImage productImage=null;
		
		try {
			productImage=(ProductImage) sqlMapClient.queryForObject("product_images.selectByPrimaryKey",new ProductImage(id));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return productImage;
	}

	@SuppressWarnings("unchecked")
	public List<ProductImage> findAll() {
		List<ProductImage> productImages=null;

		try {
			productImages=sqlMapClient.queryForList("product_images.selectAll", null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return productImages;
	}

	public void update(ProductImage transientObject) {
		
		try {
			sqlMapClient.update("product_images.updateByPrimaryKeySelective", transientObject);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void delete(ProductImage persistentObject) {

		try {
			sqlMapClient.delete("product_images.deleteByPrimaryKey", persistentObject);
		} catch (SQLException e) {
			e.printStackTrace();
		}	

	}

	@SuppressWarnings("unchecked")
	public List<ProductImage> findByExample(
			ProductImageExample productImageExample) {
		List<ProductImage> productImages=null;

		try {
			productImages=sqlMapClient.queryForList("product_images.selectByExample", productImageExample);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return productImages;
	}

	public boolean create(List<ProductImage> productImages) {

		boolean retVal = false;
		try {
			sqlMapClient.startTransaction();
			sqlMapClient.startBatch();
			for (ProductImage productImage : productImages) {
				sqlMapClient.insert("product_images.insert", productImage);
			}
			int rows = sqlMapClient.executeBatch();
		 
			sqlMapClient.commitTransaction();
			retVal = true;
		} catch (Exception e) {
			e.printStackTrace();
			retVal = false;
		} finally {
			try {
				sqlMapClient.endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
				retVal = false;
			}
		}
		return retVal;
		
	}

	public Integer deleteByExample(ProductImageExample productImageExample) {
		Integer rows=0;
		try {
			rows=sqlMapClient.delete("product_images.deleteByExample", productImageExample);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}

}
