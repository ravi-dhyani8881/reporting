package com.mobicart.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.ClassUtils;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.mobicart.model.App;
import com.mobicart.model.DiskSpacePricing;
import com.mobicart.model.DiskSpacePricingExample;
import com.mobicart.model.MerchantService;

 
public class DiskSpacePricingDAOImpl  extends SqlMapClientDaoSupport implements DiskSpacePricingDAO {

	
	
	
	public Long save(DiskSpacePricing diskSpacePricing) {
		// TODO Auto-generated method stub
	
			
			Long id=(Long)getSqlMapClientTemplate().insert("disk_space_pricing.insert", diskSpacePricing);
		return id;
	}

	public List<DiskSpacePricing> getDiskSpacePricingByExample(
			DiskSpacePricingExample diskSpacePricingExample){
		
		List<DiskSpacePricing> diskSpacePricing=null;
		
		diskSpacePricing=getSqlMapClientTemplate().queryForList("disk_space_pricing.selectByExample", diskSpacePricingExample);
		if(diskSpacePricing == null)
		{
			throw new ObjectRetrievalFailureException(ClassUtils.getShortName(this.getClass()), diskSpacePricingExample);
		}
		return diskSpacePricing;
	}
	
	
	
	public void updateDiskSpacePricing(DiskSpacePricing diskSpacePricing)
			{
		getSqlMapClientTemplate().update("disk_space_pricing.updateByPrimaryKeySelective", diskSpacePricing);

	}
	
	
	

	public void deleteDiskSpacePricing(DiskSpacePricing diskSpacePricing)
			 {
		// TODO Auto-generated method stub

	}
	
	

	public DiskSpacePricing findDiskSpacePricingById(Long id)
			 {
		DiskSpacePricing diskSpacePricing=new DiskSpacePricing(id);   
		diskSpacePricing=(DiskSpacePricing) getSqlMapClientTemplate().queryForObject(
				"disk_space_pricing.selectByPrimaryKey", diskSpacePricing);
		/*if(diskSpacePricing== null)
		{
			throw new ObjectRetrievalFailureException(ClassUtils.getShortName(this.getClass()), id);
		}*/
		return diskSpacePricing;
	}
	
	public DiskSpacePricing findDiskSpacePricingByFPrice(BigDecimal fPrice)
	 {
		DiskSpacePricing discDiskSpacePricing=new DiskSpacePricing();
		discDiskSpacePricing.setfPrice(fPrice);
		
		DiskSpacePricing discSpacePricingFound=(DiskSpacePricing) getSqlMapClientTemplate().queryForObject(
				"disk_space_pricing.selectByFPrice",discDiskSpacePricing);
		if(discSpacePricingFound== null)
		{
			throw new ObjectRetrievalFailureException(ClassUtils.getShortName(this.getClass()), fPrice);
		}
		
		return discSpacePricingFound;
}

}
