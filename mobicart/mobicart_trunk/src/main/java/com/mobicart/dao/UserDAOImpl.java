package com.mobicart.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.mobicart.model.Authority;
import com.mobicart.model.AuthorityExample;
import com.mobicart.model.Plans;
import com.mobicart.model.User;
import com.mobicart.model.UserDetail;
import com.mobicart.model.UserExample;
import com.mobicart.util.Pager;

public class UserDAOImpl extends SqlMapClientDaoSupport implements UserDAO {

	public User find(Long userId) throws DataAccessException{
			return  (User) getSqlMapClientTemplate().queryForObject("users.selectById", userId);
	}

	@SuppressWarnings("unchecked")
	public List<User> findAll() throws DataAccessException {
		return  getSqlMapClientTemplate().queryForList("users.selectAll", null);
	}

	

	public Long create(User user) throws DataAccessException{
			return  (Long) getSqlMapClientTemplate().insert("users.insert", user);
	}

	public User findByEmail(String email) throws DataAccessException {
			return (User) getSqlMapClientTemplate().queryForObject("users.selectByPrimaryKey",
					new User(email));
	}

    public User findByAuthKey(String authKey) throws DataAccessException{
    		return  (User) getSqlMapClientTemplate().queryForObject("users.selectByActivationKey",
					authKey);
	}

	
	public void updatePassword(User user) throws DataAccessException {
			getSqlMapClientTemplate().update("users.updateByPrimaryKeySelective", user);
	}

	public void update(User user) throws DataAccessException{
			getSqlMapClientTemplate().update("users.updateByPrimaryKeySelective", user);
	}

    public void delete(User user) throws DataAccessException{
            getSqlMapClientTemplate().delete("users.deleteByPrimaryKey", user);
    }

	@SuppressWarnings("unchecked")
	public List<User> findByExample(UserExample userExample) throws DataAccessException{
			return getSqlMapClientTemplate().queryForList("users.selectByExample", userExample);
	}


	public void createAuthority(Authority authority) throws DataAccessException{
			getSqlMapClientTemplate().update("authorities.insert", authority);
		
	}


	/**
	 * Filde users by role 
	 */
	@SuppressWarnings("unchecked")
	public List<User> findByRole(String authority)throws DataAccessException {
			return getSqlMapClientTemplate().queryForList("users.selectByRole", authority);
	}

	/* 
	 * @see com.mobicart.dao.UserDAO#findByAuthorityExample(com.mobicart.model.AuthorityExample)
	 */
	@SuppressWarnings("unchecked")
	public List<User> findByAuthorityExample(AuthorityExample authorityExample) throws DataAccessException{
			return getSqlMapClientTemplate().queryForList("authorities.selectUsersByAuthorityExample", authorityExample);
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<User> findAdminUsersByKeyword(String keyword) throws DataAccessException{
			return getSqlMapClientTemplate().queryForList("users.selectAdminUsersByKeyword", keyword);
	}

	
	/**
	 * 
	 */
	public Authority findAuthority(String username) throws DataAccessException{
			return (Authority) getSqlMapClientTemplate().queryForObject("authorities.selectAuthorityByUsername", username);
	}

	public void updateAuthority(Authority authority) throws DataAccessException{
		AuthorityExample authorityExample=new AuthorityExample();
		authorityExample.createCriteria().andUsernameEqualTo(authority.getUsername());
			getSqlMapClientTemplate().update("authorities.updateByExampleSelective", authorityExample);
			
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> findUsers(Pager pager,String userRole)
			throws DataAccessException {
			List<User> userList = null;
    		Map<String,Object> returnParam = new HashMap<String, Object>();
			
    		Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("txtSearch", pager.getKeyword()!=null?"%"+pager.getKeyword()+"%":"%%");
			paramMap.put("role", userRole);
			paramMap.put("orderByClause", pager.getOrderByClause());
			paramMap.put("lowerLimit", pager.getLowerLimit());
			paramMap.put("upperLimit", pager.getUpperLimit());
			
			userList = getSqlMapClientTemplate().queryForList("users.searchUsers",paramMap);
			 
			returnParam.put("userList",userList);
			returnParam.put("count",paramMap.get("totalCount"));			
			paramMap = null;
	
		return returnParam;
	}

	public UserDetail findUserDetailByUsername(String username)
			throws DataAccessException {
		return (UserDetail) getSqlMapClientTemplate().queryForObject("users.selectUserDetailByUsername", username);
	}

	@Override
	public boolean updateBrandingByUserId(Long userId,boolean val) throws Exception {
		 
      User user=new User();
      user.setId(userId);
      user.setbCustomCopyrightPage(val);
      
     
 		// TODO Auto-generated method stub
		try{
		  getSqlMapClientTemplate().update("users.removeBranding", user);
		  return true;
		  }
		catch(Exception e){
			
			throw e;
		}
		 
	}

 
	
	
	
	
}
