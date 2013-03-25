package com.mobicart.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.mobicart.dto.DepartmentDto;
import com.mobicart.model.Category;
import com.mobicart.model.Department;
import com.mobicart.model.DepartmentExample;
import com.mobicart.model.api.CategoryApi;
import com.mobicart.model.api.DepartmentApi;
import com.mobicart.util.Pager;


public class DepartmentDAOImpl extends SqlMapClientDaoSupport implements DepartmentDAO {

	/**
	 * {@inheritDoc}
	 */
	public Long create(Department newInstance) throws DataAccessException {
		return (Long) getSqlMapClientTemplate().insert("departments.insertSelective", newInstance);
	}

	public void delete(Department persistentObject) throws DataAccessException {
			getSqlMapClientTemplate().delete("departments.deleteByPrimaryKey", persistentObject);
	}

	public Department find(Long id) throws DataAccessException {
			return (Department) getSqlMapClientTemplate().queryForObject("departments.selectByPrimaryKey", new Department(id));
	}
	
	public Department findForApi(Long id) throws DataAccessException {
		return (Department) getSqlMapClientTemplate().queryForObject("departments.selectByPrimaryKeyForApi", new Department(id));
	}

	public void update(Department transientObject) throws DataAccessException {
			getSqlMapClientTemplate().update("departments.updateByPrimaryKeySelective",transientObject);
	}

	@SuppressWarnings("unchecked")
	public List<Department> findAll() throws DataAccessException{
		return getSqlMapClientTemplate().queryForList("departments.selectAll", null);
	}

	@SuppressWarnings("unchecked")
	public List<Department> findByExample(DepartmentExample departmentExample) throws DataAccessException {
			return getSqlMapClientTemplate().queryForList("departments.selectByExample", departmentExample);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Department> findByExampleWithoutSubDepartments(
			DepartmentExample departmentExample) {
		return getSqlMapClientTemplate().queryForList("departments.selectByExampleForInternalApi", departmentExample);
	}

	@SuppressWarnings("unchecked")
	public List<DepartmentDto> findByExample(DepartmentExample departmentExample,Pager pager) throws DataAccessException {
			return getSqlMapClientTemplate().queryForList("departments.selectPreviewByExample", departmentExample,pager.getPage(),pager.getResults());
	}

	@SuppressWarnings("unchecked")
	public List<DepartmentDto> findDepartmetByIdAndMaxRowNum(DepartmentExample departmentExample,Pager pager) throws DataAccessException {
			return getSqlMapClientTemplate().queryForList("departments.selectPreviewByIdAndMaxRowNum", departmentExample,pager.getPage(),pager.getResults());
	}

	/********************API METHODS***************/
	
	@SuppressWarnings("unchecked")
	public List<DepartmentApi> findByExampleForApi(DepartmentExample departmentExample,Pager pager)  {
			return getSqlMapClientTemplate().queryForList("departments.selectByExampleForApi", departmentExample);
	}

	@SuppressWarnings("unchecked")
	public List<Category> findCategoriesByExample(
			DepartmentExample departmentExample) throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("departments.selectCategoryByExample", departmentExample);
	}
	
	@SuppressWarnings("unchecked")
	public List<Category> findCategoriesByIdAndMaxRowNum(Department department )  throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("departments.selectCategoryByidAndMaxRowNum",department);
	}
	
	@SuppressWarnings("unchecked")
	public List<CategoryApi> findCategoriesByExampleForApi(
			DepartmentExample departmentExample) throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("departments.selectByExampleForCategoryApi", departmentExample);
	}
	
	
	
	

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findStoreDepartmentsWithPagination(Long storeId, int lowerLimit, int upperLimit) throws Exception {
		List<Department> list = null;
    	Map<String,Object> returnParam = new HashMap<String, Object>();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("storeId", storeId);
			paramMap.put("lowerLimit",lowerLimit);
			paramMap.put("upperLimit",upperLimit);
			list = getSqlMapClientTemplate().queryForList("departments.searchStoreDepartments",paramMap);
			returnParam.put("departmentList",list);
			returnParam.put("count",paramMap.get("totalCount"));			
			paramMap = null;
		return returnParam;
	}
	
	@SuppressWarnings("unchecked")
	public List<Department> findAPIByExample(DepartmentExample departmentExample) throws DataAccessException {
			return getSqlMapClientTemplate().queryForList("departments.apiSelectByExample", departmentExample);
	}

	@Override
	public Long findParentDepartmentId(Long subdepartment_id) {
		 
		return (Long)getSqlMapClientTemplate().queryForObject("departments.findParentDepartment", subdepartment_id);
	}
	
	
}
