package com.mobicart.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.util.ClassUtils;

import com.mobicart.dto.NewsDto;
import com.mobicart.dto.NewsSearchDto;
import com.mobicart.model.News;
import com.mobicart.model.NewsExample;
import com.mobicart.util.Pager;

public class NewsDAOImpl extends SqlMapClientDaoSupport implements NewsDAO {

	/**
	 * {@inheritDoc}
	 */
	public Long create(News newInstance) throws DataAccessException {
			return (Long) getSqlMapClientTemplate().insert("news.insert", newInstance);
	}

	public News find(Long id) throws DataAccessException {
		News news=null;
			news=(News) getSqlMapClientTemplate().queryForObject("news.selectByPrimaryKey",new News(id) );
		
			if(news==null){
    			throw new ObjectRetrievalFailureException(ClassUtils.getShortName(this.getClass()), id);
    		}
			
		return news;
	}

	@SuppressWarnings("unchecked")
	public List<News> findAll() throws DataAccessException {
		List<News> newsList=null;
			newsList=getSqlMapClientTemplate().queryForList("news.selectAll", null);
		
			if(newsList==null){
    			throw new ObjectRetrievalFailureException(ClassUtils.getShortName(this.getClass()),null);
    		}
		return newsList;
	}

	public void update(News transientObject) throws DataAccessException {
		getSqlMapClientTemplate().update("news.updateByPrimaryKeySelective",transientObject);
	}

	public void delete(News persistentObject) throws DataAccessException {
		getSqlMapClientTemplate().delete("news.deleteByPrimaryKey",persistentObject);
	}
	
	public void deleteNewsById(Long newsId){
		getSqlMapClientTemplate().delete("news.deleteByPrimaryKeyAsId", newsId);
	}


	@SuppressWarnings("unchecked")
	public List<News> findByExample(NewsExample newsExample) {
		List<News> newsList=null;
			newsList=getSqlMapClientTemplate().queryForList("news.selectByExampleWithBLOBs", newsExample);
			
			if(newsList==null){
    			throw new ObjectRetrievalFailureException(ClassUtils.getShortName(this.getClass()), null);
    		}
			
		return newsList;
	}

	@SuppressWarnings("unchecked")
	public List<NewsDto> findByExample(NewsExample newsExample, Pager pager) {
		List<NewsDto> newsList=null;
		newsList=getSqlMapClientTemplate().queryForList("news.selectPreviewByExample", newsExample,pager.getPage(),pager.getResults());
		if(newsList==null){
			throw new ObjectRetrievalFailureException(ClassUtils.getShortName(this.getClass()), null);
		}
		return newsList;
	}

	public NewsDto findNewsBean(long newsId) {
		NewsDto news=null;
		news=(NewsDto) getSqlMapClientTemplate().queryForObject("news.selectPreviewByPrimaryKey",newsId );
		if(news==null){
			throw new ObjectRetrievalFailureException(ClassUtils.getShortName(this.getClass()), newsId);
		}
		return news;
	}

	@SuppressWarnings("unchecked")
	public List<News> searchNewsByKeyword(NewsExample newsExample) {
		List<News> newsList=null;
		
		newsList=getSqlMapClientTemplate().queryForList("news.selectByExample",newsExample);
		if(newsList==null){
			throw new ObjectRetrievalFailureException(ClassUtils.getShortName(this.getClass()), null);
		}
		return newsList;
	
	}
	@SuppressWarnings("unchecked")
	public List<NewsDto> searchNewsByKeyword(NewsSearchDto news) {
		List<NewsDto> newsList=null;
		
		newsList=getSqlMapClientTemplate().queryForList("news.serchNews",news);
		
		if(newsList==null){
			throw new ObjectRetrievalFailureException(ClassUtils.getShortName(this.getClass()), null);
		}
		return newsList;
	
	}
	
	
}
