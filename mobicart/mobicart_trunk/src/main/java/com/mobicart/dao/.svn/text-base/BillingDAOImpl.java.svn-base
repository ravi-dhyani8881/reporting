/**
 * 
 */
package com.mobicart.dao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.util.ClassUtils;

import com.mobicart.model.Billing;
import com.mobicart.model.BillingExample;
import com.mobicart.model.Plans;

/**
 * @author jasdeep.singh
 *
 */

public class BillingDAOImpl  extends SqlMapClientDaoSupport implements BillingDAO {


	
	
	/* (non-Javadoc)
	 * @see com.mobicart.dao.DAO#create(java.lang.Object)
	 */
	public Long create(Billing newInstance) {
		Long id=null;
		
		
			id=(Long) getSqlMapClientTemplate().insert("billing.insert", newInstance);
			
		
		if(id==null){
			throw new ObjectRetrievalFailureException(ClassUtils.getShortName(this.getClass()), id);
		}
		
		return id;
	}

	/* (non-Javadoc)
	 * @see com.mobicart.dao.DAO#find(java.io.Serializable)
	 */
	public Billing find(Long id) {
		Billing billing=null;
		
			 billing=(Billing) getSqlMapClientTemplate().queryForObject("billing.selectByPrimaryKey", new Billing(id));
			 
			 if(billing==null){
					throw new ObjectRetrievalFailureException(ClassUtils.getShortName(this.getClass()), id);
				}
		
		return  billing;
	}

	/* (non-Javadoc)
	 * @see com.mobicart.dao.DAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Billing> findAll() throws DataAccessException {
		List<Billing> billingList=null;
		
			 billingList= getSqlMapClientTemplate().queryForList("billing.selectAll", null);			 
		return billingList;
	}

	/* (non-Javadoc)
	 * @see com.mobicart.dao.DAO#update(java.lang.Object)
	 */
	public void update(Billing transientObject) {
		
		getSqlMapClientTemplate().update("billing.updateByPrimaryKeySelective", transientObject);
		
	}

	/* (non-Javadoc)
	 * @see com.mobicart.dao.DAO#delete(java.lang.Object)
	 */
	public void delete(Billing persistentObject) {
		getSqlMapClientTemplate().update("billing.updateByPrimaryKeySelective", persistentObject);

	}

	/* 
	 * @see com.mobicart.dao.BillingDAO#findByExample(com.mobicart.model.BillingExample)
	 */
	@SuppressWarnings("unchecked")
	public List<Billing> findByExample(BillingExample billingExample)throws Exception {
		List<Billing> billingList=null;
		billingList= getSqlMapClientTemplate().queryForList("billing.selectByExample", billingExample);
		return billingList;
	}
	
	/* 
	 * @see com.mobicart.dao.BillingDAO#findByExample(com.mobicart.model.BillingExample)
	 */
	@SuppressWarnings("unchecked")
	public List<Billing> findByExampleWithoutThrows(BillingExample billingExample) {
		List<Billing> billingList=null;
		try{
		billingList= getSqlMapClientTemplate().queryForList("billing.selectByExample", billingExample);
		}catch (Exception e) {
			//System.out.println("findByExampleWithoutThrows "+e);
			e.printStackTrace();
		}
		return billingList;
	}

	@Override
	public String findPlansByUserId(Long userId) throws Exception {
		// TODO Auto-generated method stubqueryObject("billing.findPlan", userId);;
		return (String)getSqlMapClientTemplate().queryForObject("billing.findPlan",userId);
	}

	@Override
	public int updateCreatPlansByUserId(Long userId, String s_type) throws Exception {
		// TODO Auto-generated method stub
		String stype=findPlansByUserId(userId);
		Billing billing=new Billing();
		billing.setUserId(userId);
		billing.setAppId(-1L);//not appid is set to -1 here
		billing.setfAmount(new BigDecimal(-1L));
		billing.setfAmount(new BigDecimal(-1L));
		billing.setdBillingDate(new java.sql.Date(System.currentTimeMillis()));
		billing.setsType(s_type);
		if(stype!=null){
			
			getSqlMapClientTemplate().update("billing.upadtePlan", billing);
		}else{
			
			getSqlMapClientTemplate().insert("billing.insert",billing);
		}
		return 0;
	}

}
