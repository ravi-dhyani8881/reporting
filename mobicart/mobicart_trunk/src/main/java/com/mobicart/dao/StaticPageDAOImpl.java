package com.mobicart.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.util.ClassUtils;

import com.mobicart.dto.PageDto;
import com.mobicart.model.StaticPage;
import com.mobicart.model.StaticPageExample;

/**
 *
 * @author manpreet.randhawa
 * @author jasdeep.singh
 */
public class StaticPageDAOImpl extends SqlMapClientDaoSupport implements StaticPageDAO {
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(StaticPageDAOImpl.class);

    
    /**
     * {@inheritDoc}
     */
    public Long create(StaticPage newInstance) throws DataAccessException{
        	return (Long) getSqlMapClientTemplate().insert("static_pages.insert", newInstance);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(StaticPage persistentObject) throws DataAccessException {
            getSqlMapClientTemplate().delete("static_pages.deleteByPrimaryKey", persistentObject);

    }

    public StaticPage find(Long id) throws DataAccessException{
        StaticPage staticPage = null;
            staticPage = (StaticPage) getSqlMapClientTemplate().queryForObject("static_pages.selectByPrimaryKey", new StaticPage(id));

    		if(staticPage==null){
    			throw new ObjectRetrievalFailureException(ClassUtils.getShortName(this.getClass()), id);
    		}

        return staticPage;
    }

    /**
     * {@inheritDoc}
     */
    public void update(StaticPage transientObject) throws DataAccessException {
            getSqlMapClientTemplate().update("static_pages.updateByPrimaryKeySelective", transientObject);
    }

    @SuppressWarnings("unchecked")
    public List<StaticPage> findAll() {
        List<StaticPage> staticPages = null;
            staticPages = getSqlMapClientTemplate().queryForList("static_pages.selectAll", null);
        if(staticPages==null){
			throw new ObjectRetrievalFailureException(ClassUtils.getShortName(this.getClass()), null);
		}
        return staticPages;
    }

    @SuppressWarnings("unchecked")
    public List<StaticPage> findByExample(StaticPageExample staticPageExample) {
        List<StaticPage> staticPages = null;
            staticPages = getSqlMapClientTemplate().queryForList("static_pages.selectByExampleWithBLOBs", staticPageExample);
            if(staticPages==null){
    			throw new ObjectRetrievalFailureException(ClassUtils.getShortName(this.getClass()), staticPageExample);
    		}
            return staticPages;
    }

    /**
     * {@inheritDoc}
     */
	@SuppressWarnings("unchecked")
	public List<PageDto> findPageBeansByExample(
			StaticPageExample staticPageExample) {
		List<PageDto> pageBeans = null;
		pageBeans = getSqlMapClientTemplate().queryForList("static_pages.selectPreviewByExample", staticPageExample);
        if(pageBeans==null){
			throw new ObjectRetrievalFailureException(ClassUtils.getShortName(this.getClass()), staticPageExample);
		}
        return pageBeans;
	}

	
	/**
	 * {@inheritDoc}
	 */
	public PageDto findPageBean(long pageId) {
		PageDto pageBean = null;
		pageBean = (PageDto) getSqlMapClientTemplate().queryForObject("static_pages.selectPreviewByPrimaryKey", pageId);
        if(pageBean==null){
			throw new ObjectRetrievalFailureException(ClassUtils.getShortName(this.getClass()), pageId);
		}
        return pageBean;
	}

	/**
	 * {@inheritDoc}
	 */
	public void createIntialStaticPages(List<StaticPage> staticPages) {
		try {
			getSqlMapClient().startTransaction();
			getSqlMapClient().startBatch();
			
			for (StaticPage staticPage : staticPages) {
				getSqlMapClient().insert("static_pages.insert", staticPage);
			}
			
			getSqlMapClient().executeBatch();
			getSqlMapClient().commitTransaction();
			
		} catch (Exception e) {
			logger.warn("saveAppTerritories(List<AppTerritory>) - exception ignored", e); 
			throw new InvalidDataAccessApiUsageException(ClassUtils.getShortName(this.getClass()), e);
		} finally {
			try {
				getSqlMapClient().endTransaction();
			} catch (SQLException e) {
				logger.warn("saveAppTerritories(List<AppTerritory>) - exception ignored", e); 
				throw new DataAccessResourceFailureException(ClassUtils.getShortName(this.getClass()), e);
			}
		}
		
		
	}

	public boolean updateStatus(Long pageId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("pageId", pageId);			
			getSqlMapClientTemplate().update("static_pages.updatePageStatus",paramMap);
		return (Boolean) paramMap.get("enabled");
	}

    
    
    
}
