package com.mobicart.dao;

import java.util.List;

import com.mobicart.dto.PageDto;
import com.mobicart.model.StaticPage;
import com.mobicart.model.StaticPageExample;

/**
 *
 * @author jasdeep.singh
 */
public interface StaticPageDAO extends GenericDAO<StaticPage, Long> {

	/**
	 * Find Static Pages 
	 * @param staticPageExample
	 * @return List {@link StaticPage} instances
	 */
    public List<StaticPage> findByExample(StaticPageExample staticPageExample);
    
    /**
     * 
     * @param staticPageExample
     * @return
     */
    public List<PageDto> findPageBeansByExample(StaticPageExample staticPageExample);
    
    /**
     * Pange page dto
     * @param pageId
     * @return
     */
    public PageDto findPageBean(long pageId);
    
    public boolean updateStatus(Long pageId);

    public void createIntialStaticPages(List<StaticPage> staticPages);

}
