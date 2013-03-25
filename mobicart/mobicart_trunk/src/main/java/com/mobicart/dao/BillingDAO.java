package com.mobicart.dao;

import java.util.List;

import com.mobicart.model.Billing;
import com.mobicart.model.BillingExample;
import com.mobicart.model.Plans;

public interface BillingDAO extends DAO<Billing, Long> {
	
	List<Billing> findByExample(BillingExample billingExample) throws Exception;
	List<Billing> findByExampleWithoutThrows(BillingExample billingExample);
	public String findPlansByUserId(Long userId) throws Exception;
	public int updateCreatPlansByUserId(Long userId,String s_type) throws Exception;
}
