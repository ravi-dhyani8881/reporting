package com.mobicart.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mobicart.dto.DepartmentDto;
import com.mobicart.model.Category;
import com.mobicart.model.Department;
import com.mobicart.model.DepartmentExample;
import com.mobicart.model.api.CategoryApi;
import com.mobicart.model.api.DepartmentApi;
import com.mobicart.util.Pager;

public interface DepartmentDAO extends GenericDAO<Department, Long> {
	/**
	 * Department  
	 * @param departmentExample
	 * @return
	 * @throws DataAccessException
	 */
	public List<Department> findByExample(DepartmentExample departmentExample) throws DataAccessException;
	
	
	/**
	 * Department  
	 * @param departmentExample
	 * @return
	 * @throws DataAccessException
	 */
	public List<Department> findByExampleWithoutSubDepartments(DepartmentExample departmentExample);
	
	
	/**
	 * find department to by example
	 * @param departmentExample
	 * @param pager
	 * @return
	 * @throws DataAccessException
	 */
	
	public List<DepartmentDto> findByExample(DepartmentExample departmentExample,Pager pager) throws DataAccessException;

	public List<DepartmentApi> findByExampleForApi(DepartmentExample departmentExample,Pager pager);
	
	public List<Category> findCategoriesByExample(DepartmentExample departmentExample) throws DataAccessException;
	
	public List<CategoryApi> findCategoriesByExampleForApi(DepartmentExample departmentExample) throws DataAccessException;

	public Map<String, Object> findStoreDepartmentsWithPagination(Long storeId, int lowerLimit, int upperLimit) throws Exception;
	
	public Department findForApi(Long id) throws DataAccessException;
	public List<Department> findAPIByExample(DepartmentExample departmentExample) throws DataAccessException; 
    public Long  findParentDepartmentId(Long subdepartment_id );
    
    public List<DepartmentDto> findDepartmetByIdAndMaxRowNum(DepartmentExample departmentExample,Pager pager) throws DataAccessException;
    public List<Category> findCategoriesByIdAndMaxRowNum(Department  department) throws DataAccessException;
}
