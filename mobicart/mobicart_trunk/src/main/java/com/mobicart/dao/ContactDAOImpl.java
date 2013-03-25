package com.mobicart.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.mobicart.model.Contact;
import com.mobicart.model.ContactExample;

//@Repository
public class ContactDAOImpl implements ContactDAO {

	@Autowired
	SqlMapClient sqlMapClient;
	
	
	public Long create(Contact newInstance) {
		Long id=null;
		try {
			id = (Long) sqlMapClient.insert("contacts.insert", newInstance);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public Contact find(Long id) {
		Contact contact=null; 
		ContactExample example=new ContactExample();
		example.createCriteria().andIdEqualTo(id);
		
		try {
			contact=(Contact) sqlMapClient.queryForObject("contacts.selectByExampleWithBLOBs", example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return contact;
	}

	@SuppressWarnings("unchecked")
	public List<Contact> findAll() {
		List<Contact> contacts=null; 
		ContactExample example=new ContactExample();
		example.setOrderByClause("d_sent_on ASC");
		try {
			contacts=sqlMapClient.queryForList("contacts.selectByExampleWithBLOBs", example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contacts;
	}

	public void update(Contact transientInstance) {
		try {
		 sqlMapClient.update("contacts.updateByPrimaryKeySelective", transientInstance);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Contact persistentInstance) {
		try {
			 sqlMapClient.update("contacts.deleteByPrimaryKey", persistentInstance);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

}

