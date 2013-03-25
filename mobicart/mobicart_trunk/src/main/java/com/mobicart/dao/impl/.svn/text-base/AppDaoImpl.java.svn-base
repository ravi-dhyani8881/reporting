package com.mobicart.dao.impl;

import java.util.List;


import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.util.ClassUtils;

import com.mobicart.dao.AppDAO;
import com.mobicart.model.App;
import com.mobicart.model.AppExample;

public class AppDaoImpl  extends SqlSessionDaoSupport implements AppDAO {

	private static final String NAMESPACE="app.";
	private static final String STMT_LOAD_BY_ID=NAMESPACE + "selectAppById";
	private static final String STMT_LOAD_BY_USER_ID=NAMESPACE + "selectByUserId";
	private static final String STMT_LOAD_BY_EXAMPLE=NAMESPACE + "selectByExample";
	private static final String STMT_INSERT=NAMESPACE + "insertSelective";
	private static final String STMT_UPDATE=NAMESPACE + "updateByPrimaryKeyWithBLOBs";
	

 
	public App findAppById(Long appId) {
		App app=null;
		app=(App) getSqlSession().selectOne(STMT_LOAD_BY_ID,appId);
		if(app==null){
			throw new ObjectRetrievalFailureException(ClassUtils.getShortName(this.getClass()), appId);
		}
		return app;
	}

 
	public App findAppByUserId(Long userId) {
		App app=null;
		
		app=(App) getSqlSession().selectOne(STMT_LOAD_BY_USER_ID,userId);
	     
	    
		/*if(app==null){
			throw new ObjectRetrievalFailureException(ClassUtils.getShortName(this.getClass()), userId);
		}*/
		return app;
	}

	@SuppressWarnings("unchecked")
	 
	public List<App> findAppsByExample(AppExample appExample) {
		List<App> apps=null;
		apps=getSqlSession().selectList(STMT_LOAD_BY_EXAMPLE,appExample);
		if(apps==null){
			throw new ObjectRetrievalFailureException(ClassUtils.getShortName(this.getClass()), appExample);
		}
		return apps;
	}

 
	public Long save(App app) {
		Long id=null;
		id=(long) getSqlSession().insert(STMT_INSERT,app);
		return id;
	}

 
	public int update(App app) {
		return getSqlSession().update(STMT_UPDATE,app);
	}


	@Override
	public int getRemoveBrandingStatus(String  username) {
		// TODO Auto-generated method stub
		
		int flag=(Integer)getSqlSession().selectOne("app.get_custom_copyright_status",username);
		
		return flag;
	}


	@Override
	public int updateAppVersion(App  app){
		int rowsUpdated=0;
		rowsUpdated=getSqlSession().update("app.updateAppVersion", app);
		return rowsUpdated;
	}
}

