package com.mobicart.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.mobicart.model.State;
import com.mobicart.model.StateExample;

@Repository
public class StateDAOImpl implements StateDAO {

	
	@Autowired
	private SqlMapClient sqlMapClient;
	
	public Long create(State newInstance) {
		Long id = null;
		try {
			id = (Long) sqlMapClient.insert("states.insert",
					newInstance);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public State find(Long id) {
		State state=null;
		try{
			state=(State) sqlMapClient.queryForObject("states.selectByPrimaryKey", new State(id));
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return state;
	}

	@SuppressWarnings("unchecked")
	public List<State> findAll() {
		List<State> states=null;
		try {
			states= sqlMapClient.queryForList("states.selectAll", null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return states;
	}

	public void update(State transientInstance) {
		
		try {
			sqlMapClient.update("states.updateByPrimaryKeySelective", transientInstance);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void delete(State persistentInstance) {
		try {
			sqlMapClient.update("states.deleteByPrimaryKeySelective", persistentInstance);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public List<State> findByExample(StateExample stateExample) {
		List<State> states=null;
		try {
			states= sqlMapClient.queryForList("states.selectByExample", stateExample);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return states;
	}
	
	

}
