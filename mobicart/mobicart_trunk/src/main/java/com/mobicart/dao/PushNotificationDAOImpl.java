package com.mobicart.dao;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.mobicart.model.PushNotification;
import com.mobicart.model.PushNotificationExample;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository 
public class PushNotificationDAOImpl implements PushNotificationDAO {
   
	@Autowired
	private SqlMapClient sqlMapClient;
	
	public Long save(PushNotification pushNotification) throws SQLException{
		return (Long) sqlMapClient.insert("push_notification.insert", pushNotification);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<PushNotification> getNotificationsByExample(PushNotificationExample pushNotificationExample)  throws SQLException{
			return sqlMapClient.queryForList("push_notification.selectByExample", pushNotificationExample);
	}
	
	public void updateNotification(PushNotification pushNotification) throws SQLException{
			sqlMapClient.update("push_notification.updateByPrimaryKeySelective", pushNotification);
	}
	
	
	public void deleteNotification(PushNotification pushNotification)  throws SQLException{
			sqlMapClient.update("push_notification.deleteByPrimaryKey", pushNotification);
	}
	
	 public PushNotification findNotificationById(Long id)  throws SQLException{
		 	return (PushNotification) sqlMapClient.queryForObject(
						"push_notification.selectByPrimaryKey", new PushNotification(id));
	 }
	
}