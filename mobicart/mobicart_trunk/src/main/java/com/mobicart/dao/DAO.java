package com.mobicart.dao;

import java.io.Serializable;
import java.util.List;

public interface DAO<T, PK extends Serializable> {
	
	/** Persist the newInstance object into database */
    PK create(T newInstance);

    /** Retrieve an object that was previously persisted to the database using
     *   the indicated id as primary key
     */
    T find(PK id);

    
    /** Retrieve all the objects from database 
     *  the indicated id as primary key
     */
    List<T> findAll();

    
    /** Save changes made to a persistent object.  */
    void update(T transientInstance);

    /** Remove an object from persistent storage in the database */
    void delete(T persistentInstance);

    
}
