package com.mobicart.dao;

import java.sql.SQLException;
import java.util.List;

import com.mobicart.dto.LabelsDto;
import com.mobicart.model.LabelKeys;
import com.mobicart.model.LabelKeysExample;
import com.mobicart.model.User;

public interface LabelKeysDAO {

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table label_keys
	 * @ibatorgenerated  Tue Jun 28 17:25:47 IST 2011
	 */
	int countLabelKeysByExample(LabelKeysExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table label_keys
	 * @ibatorgenerated  Tue Jun 28 17:25:47 IST 2011
	 */
	int deleteLabelKeysByExample(LabelKeysExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table label_keys
	 * @ibatorgenerated  Tue Jun 28 17:25:47 IST 2011
	 */
	int deleteLabelKeysByPrimaryKey(Integer labelKeyId) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table label_keys
	 * @ibatorgenerated  Tue Jun 28 17:25:47 IST 2011
	 */
	void insertLabelKeys(LabelKeys record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table label_keys
	 * @ibatorgenerated  Tue Jun 28 17:25:47 IST 2011
	 */
	void insertLabelKeysSelective(LabelKeys record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table label_keys
	 * @ibatorgenerated  Tue Jun 28 17:25:47 IST 2011
	 */
	List selectLabelKeysByExample(LabelKeysExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table label_keys
	 * @ibatorgenerated  Tue Jun 28 17:25:47 IST 2011
	 */
	LabelKeys selectLabelKeysByPrimaryKey(Integer labelKeyId)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table label_keys
	 * @ibatorgenerated  Tue Jun 28 17:25:47 IST 2011
	 */
	int updateLabelKeysByExampleSelective(LabelKeys record,
			LabelKeysExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table label_keys
	 * @ibatorgenerated  Tue Jun 28 17:25:47 IST 2011
	 */
	int updateLabelKeysByExample(LabelKeys record, LabelKeysExample example)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table label_keys
	 * @ibatorgenerated  Tue Jun 28 17:25:47 IST 2011
	 */
	int updateLabelKeysByPrimaryKeySelective(LabelKeys record)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table label_keys
	 * @ibatorgenerated  Tue Jun 28 17:25:47 IST 2011
	 */
	int updateLabelKeysByPrimaryKey(LabelKeys record) throws SQLException;
	
	public List<LabelsDto> selectAllDefaultLabelKeyValues(User user) throws SQLException;
	
	public List<LabelsDto> selectAllLabels() throws SQLException;
	
}