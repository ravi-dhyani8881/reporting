/**
 * 
 */
package com.mobicart.dao;

import java.util.List;

import com.mobicart.model.SiteConstant;
import com.mobicart.model.SiteConstantExample;

/**
 * @author jasdeep.singh
 *
 */
public interface SiteConstantDAO extends DAO<SiteConstant, Long> {
	
	public List<SiteConstant> findByExample(SiteConstantExample siteConstantExample);

}
