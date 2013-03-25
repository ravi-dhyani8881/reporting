package com.mobicart.dao;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.mobicart.model.RecurringPaymentDetail;

@Repository 
public class RecurringPaymentDetailDAOImpl implements RecurringPaymentDetailDAO{

	@Autowired
	private SqlMapClient sqlMapClient;
	
	public Long save(RecurringPaymentDetail recurringPaymentDetail)
			throws SQLException {
		return (Long) sqlMapClient.insert("recurring_payment_detail.insert", recurringPaymentDetail);
		
	}

}
