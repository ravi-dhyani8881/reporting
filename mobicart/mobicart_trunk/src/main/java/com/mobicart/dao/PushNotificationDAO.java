package com.mobicart.dao;

import com.mobicart.model.PushNotification;
import com.mobicart.model.PushNotificationExample;
import java.sql.SQLException;
import java.util.List;

public interface PushNotificationDAO {
	
	public Long save(PushNotification pushNotification) throws SQLException;	
	
	public List<PushNotification> getNotificationsByExample(PushNotificationExample pushNotificationExample) throws SQLException;
	
	public void updateNotification(PushNotification pushNotification) throws SQLException;
	
	public void deleteNotification(PushNotification pushNotification) throws SQLException;
	
	public PushNotification findNotificationById(Long id) throws SQLException;
}