package com.mobicart.dao;

import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.util.ClassUtils;

import com.mobicart.model.App;
import com.mobicart.model.AppExample;

public class AppDAOImpl extends SqlMapClientDaoSupport  {

	
	public App findAppById(Long appId)  {
		App app=new App(appId);
			app=(App) getSqlMapClientTemplate().queryForObject("app.selectById", app);
			
			if(app==null){
				throw new ObjectRetrievalFailureException(ClassUtils.getShortName(this.getClass()), appId);
			}
		return app;
		
	}

	public App findAppByUserId(Long userId) {
		//System.out.println("Hi from AppDAOImpl.findAppByUserId");
		App app=new App();
		app.setUserId(userId);
		app=(App) getSqlMapClientTemplate().queryForObject("app.selectByUser", app);
		
		if(app==null){
			throw new ObjectRetrievalFailureException(ClassUtils.getShortName(this.getClass()), userId);
		}
		return app;
	}

	public Long save(App app) {
		Long appId=0L; 
			app.setbEnabled(true);
			appId=(Long) getSqlMapClientTemplate().insert("app.insert", app);
		return appId;
	}

	public int update(App app) {
		int rowsUpdated=0;

		rowsUpdated=getSqlMapClientTemplate().update("app.updateByPrimaryKeySelective", app);
		
		return rowsUpdated;
	}

	@SuppressWarnings("unchecked")
	public List<App> findAppsByExample(AppExample appExample) {
		List<App> apps=null;
			apps= getSqlMapClientTemplate().queryForList("app.selectByExample", appExample);
			
			if(apps==null){
				throw new ObjectRetrievalFailureException(ClassUtils.getShortName(this.getClass()), appExample);
			}
			
		return apps;
	}
	
	@Deprecated
	public int updateAppVersion(App  app){
		return 0; 
	}

}
