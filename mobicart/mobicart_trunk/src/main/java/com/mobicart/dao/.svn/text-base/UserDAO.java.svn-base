package com.mobicart.dao;


import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mobicart.model.Authority;
import com.mobicart.model.AuthorityExample;
import com.mobicart.model.Plans;
import com.mobicart.model.User;
import com.mobicart.model.UserDetail;
import com.mobicart.model.UserExample;
import com.mobicart.util.Pager;

public interface UserDAO extends GenericDAO<User,Long>{
	
	public Long create(User user) throws DataAccessException;
    
    public List<User> findByExample(UserExample userExample) throws DataAccessException;
    
    public List<User> findAdminUsersByKeyword(String keyword) throws DataAccessException;
    
    public List<User> findByAuthorityExample(AuthorityExample authorityExample) throws DataAccessException;
    
    public List<User> findByRole(String authority) throws DataAccessException;

    public Map<String, Object> findUsers(Pager pager,String userRole) throws DataAccessException;
    
    public User findByEmail(String email) throws DataAccessException;
    
    public User findByAuthKey(String authKey) throws DataAccessException;
       
    public void updatePassword(User user) throws DataAccessException;

    public void createAuthority(Authority authority) throws DataAccessException;
    
    public void updateAuthority(Authority authority) throws DataAccessException;
    
    public Authority findAuthority(String username) throws DataAccessException;

    public UserDetail findUserDetailByUsername(String username) throws DataAccessException;
    public boolean   updateBrandingByUserId(Long userId,boolean val) throws Exception;
    
    
}