package com.mobicart.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.dao.DataAccessException;

import com.mobicart.dao.ColorSchemeDAO;
import com.mobicart.dto.ColorSchemeDto;
import com.mobicart.model.AppColorScheme;
import com.mobicart.model.ColorScheme;

public class ColorSchemeDaoImpl extends SqlSessionDaoSupport implements ColorSchemeDAO {

	
	private static final String NAMESPACE="color_schemes.";
	private static final String STMT_INSERT_APP_COLOR_SCHEME=NAMESPACE + "insertAppColorScheme";
	private static final String STMT_UPDATE_APP_COLOR_SCHEME=NAMESPACE + "updateAppColorScheme";
	
	private static final String STMT_LOAD_BY_APP_ID=NAMESPACE + "selectByAppId";
	private static final String LOAD_EN_SCheme_BY_APP_ID=NAMESPACE + "selectEnColorSchemeByid";
	private static final String STMT_LOAD_ALL_DEFAULT=NAMESPACE + "selectAllDefault";
	
	
	@SuppressWarnings("unchecked")	
	public List<ColorScheme> findAllSchemes(){
			return getSqlSession().selectList(STMT_LOAD_ALL_DEFAULT);
	}
	
	

	public boolean saveAppColorScheme(AppColorScheme appColorScheme){
		boolean retVal=false;		
		try {
			getSqlSession().insert(STMT_INSERT_APP_COLOR_SCHEME,appColorScheme);
			retVal=true;
		} catch (DataAccessException e) {
			retVal=false;
			throw e;
		}
		return retVal;
	}

	
	public boolean updateAppColorScheme(AppColorScheme appColorScheme) {
		boolean retVal=false;		
		try {
			getSqlSession().update(STMT_UPDATE_APP_COLOR_SCHEME,appColorScheme);
			retVal=true;
		} catch (DataAccessException e) {
			retVal=false;
			throw e;
		}
		return retVal;
	}

	
	public ColorScheme findColorScheme(Long appId)  {
			return (ColorScheme)getSqlSession().selectOne(STMT_LOAD_BY_APP_ID,appId);
	}
	public ColorSchemeDto findColorSchemeEnhanced(Long appId)  {
		return (ColorSchemeDto)getSqlSession().selectOne(LOAD_EN_SCheme_BY_APP_ID,appId);

	}

	public long saveColorScheme(ColorScheme colorscheme,long appid) {
  
		return getSqlSession().insert("color_schemes.insert",colorscheme);
		 //return (Long)getSqlSession().selectOne("color_schemes.selectMaxId");
	}
	
	public int deleteColorScheme(Long  colorschemeId){
		int flag=-1;
		//getSqlSession().delete("color_schemes.deleteByPrimaryKey",colorscheme.getId());
		flag=getSqlSession().delete("color_schemes.deleteAppsExistingColorScheme",colorschemeId);
		 
		
		return flag;
	}



	public Long findExistingColorScheme(Long appId) {	
		return  (Long)getSqlSession().selectOne("color_schemes.fingExistingScheme",appId);
	}
	
}
