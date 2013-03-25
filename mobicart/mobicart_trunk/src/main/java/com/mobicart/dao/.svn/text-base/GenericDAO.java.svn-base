package com.mobicart.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

public interface GenericDAO<T, PK extends Serializable> {

	/** Persist the newInstance object into database 
	 * 
	 * */
	PK create(T newInstance) throws DataAccessException;

	/**
	 * Retrieve an object that was previously persisted to the database using
	 * the indicated id as primary key
	 */
	T find(PK id) throws DataAccessException;

	/**
	 * Retrieve all the objects from database the indicated id as primary key
	 */
	List<T> findAll() throws DataAccessException;

	/** Save changes made to a persistent object. */
	void update(T transientInstance) throws DataAccessException;

	/** Remove an object from persistent storage in the database */
	void delete(T persistentInstance) throws DataAccessException;

}
