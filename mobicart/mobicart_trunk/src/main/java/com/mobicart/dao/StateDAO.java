package com.mobicart.dao;

import java.util.List;

import com.mobicart.model.State;
import com.mobicart.model.StateExample;

public interface StateDAO extends DAO<State, Long> {
	
	List<State> findByExample(StateExample stateExample);

}
