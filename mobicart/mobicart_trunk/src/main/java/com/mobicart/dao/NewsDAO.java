package com.mobicart.dao;

import java.util.List;

import com.mobicart.dto.NewsDto;
import com.mobicart.dto.NewsSearchDto;
import com.mobicart.model.News;
import com.mobicart.model.NewsExample;
import com.mobicart.util.Pager;
public interface NewsDAO extends GenericDAO<News, Long> {

	/**
	 * Find news by Example
	 * @param newsExample
	 * @return
	 */
	List<News> findByExample(NewsExample newsExample);
	
	List<NewsDto> findByExample(NewsExample newsExample,Pager pager);
	
	NewsDto  findNewsBean(long newsId);
	
	public void deleteNewsById(Long newsId);
	
	public List<News> searchNewsByKeyword(NewsExample newsExample);
	
	public List<NewsDto> searchNewsByKeyword(NewsSearchDto news) ;
}
