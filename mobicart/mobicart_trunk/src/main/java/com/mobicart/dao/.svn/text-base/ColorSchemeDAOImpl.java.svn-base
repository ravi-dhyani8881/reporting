package com.mobicart.dao;

import java.util.List;


import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.mobicart.model.AppColorScheme;
import com.mobicart.model.ColorScheme;

public class ColorSchemeDAOImpl extends SqlMapClientDaoSupport {

	
	@SuppressWarnings("unchecked")	
	public List<ColorScheme> findAllSchemes() throws DataAccessException {
			return getSqlMapClientTemplate().queryForList("color_schemes.selectAll",null);
	}

	public boolean saveAppColorScheme(AppColorScheme appColorScheme) throws DataAccessException{
		boolean retVal=false;		
		try {
			getSqlMapClientTemplate().insert("app_color_schemes.insert",appColorScheme);
			retVal=true;
		} catch (DataAccessException e) {
			retVal=false;
			throw e;
		}
		return retVal;
	}

	
	public boolean updateAppColorScheme(AppColorScheme appColorScheme) throws DataAccessException{
		
		boolean retVal=false;		
		try {
			getSqlMapClientTemplate().update("app_color_schemes.updateByAppId",appColorScheme);
			retVal=true;
		} catch (DataAccessException e) {
			retVal=false;
			throw e;
		}
		
		return retVal;
		
	}

	
	public ColorScheme findColorScheme(Long appId) throws DataAccessException {
			return (ColorScheme)getSqlMapClientTemplate().queryForObject("color_schemes.selectByAppId",appId);
	}

	public long saveColorScheme(ColorScheme colorscheme,long appid) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean deleteColorScheme(ColorScheme colorscheme) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
