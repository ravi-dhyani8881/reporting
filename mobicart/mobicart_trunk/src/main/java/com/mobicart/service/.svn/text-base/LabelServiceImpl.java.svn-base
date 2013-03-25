package com.mobicart.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobicart.dao.LabelKeysDAO;
import com.mobicart.dao.LabelsDAO;
import com.mobicart.dto.IphoneLabelsDto;
import com.mobicart.dto.LabelsDto;
import com.mobicart.model.Labels;
import com.mobicart.model.User;
import com.mobicart.util.CommonUtils;

/**
 * 
 * @author siddhartha.bhatia
 *
 */

@Service
public class LabelServiceImpl implements LabelService{
	
	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LabelsDAO labelsDAO;
	
	@Autowired
	private LabelKeysDAO labelKeysDAO;
	
	
	public HashMap<String, String> getAllIphoneLabels(Long merchantId) throws SQLException {
		List<LabelsDto> labelsList = null;
		labelsList = labelsDAO.getAllIphoneLabelsByMerchantId(merchantId);
		
		if(labelsList.isEmpty()){
			labelsList = labelsDAO.getAllIphoneLabels();
		}
		
		return CommonUtils.returnLabelKeyValue(labelsList);
	}
	
	public List<IphoneLabelsDto> getAllExclusiveIphoneLabels(Long merchantId) throws SQLException {
		List<LabelsDto> labelsList = null;
		HashMap<String, String> labelsMap = null;
		IphoneLabelsDto iphoneLabelsDto = new IphoneLabelsDto();
		LabelsDto labelsDto = new LabelsDto();
		List<IphoneLabelsDto> returnLabelList = new ArrayList<IphoneLabelsDto>();
		
		
		labelsList = labelsDAO.getAllIphoneLabelsByMerchantId(merchantId);
		
		if(labelsList.isEmpty()){
			labelsList = labelsDAO.getAllIphoneLabels();
			iphoneLabelsDto.setTimestamp("1990-07-19 18:29:31");
		}else{
			labelsDto = (LabelsDto)labelsList.get(0);
			iphoneLabelsDto.setTimestamp(CommonUtils.formatDateToString(labelsDto.getDateAdded(), "yyyy-MM-dd HH:mm:ss")); 
		}
		
		labelsMap = CommonUtils.returnLabelKeyValue(labelsList);
		
		iphoneLabelsDto.setLabelsMap(labelsMap);
		
		returnLabelList.add(iphoneLabelsDto);
		
		return returnLabelList;
	}
	
	public HashMap<String, String> getAllWebLabels(Long merchantId) throws SQLException {
		
		List<LabelsDto> labelsList = null;
		labelsList = labelsDAO.getAllWebLabelsByMerchantId(merchantId);
		
		if(labelsList.isEmpty()){
			labelsList = labelsDAO.getAllWebLabels();
		}
		
		return CommonUtils.returnLabelKeyValue(labelsList);
	}
	
	
	public String getKeyValueByMerchantID(String labelKey, Long userId) throws SQLException {
		String labelsList = "";
		labelsList = labelsDAO.getWebKeyLabelValueByMerchantId(labelKey, userId);	
		//if(labelsList.isEmpty()){
			//labelsList = labelsDAO.getDefaultKeyLabelValueByMerchantId(labelKey, userId);
		//}
		return labelsList;
	}
	
	
	public void insertLabels(Labels label) throws SQLException {
		labelsDAO.insertLabels(label);
	}
	
	public int deleteByMerchantID(Long merchantId) throws SQLException {
		int label;
		label = labelsDAO.deleteLabelsByMerchantId(merchantId);
		return label;
	}
	
	
	public HashMap<String, String> selectAllDefaultLabelKeyValues(User user) throws SQLException {
		
		List<LabelsDto> labelsList = null;
		labelsList = labelKeysDAO.selectAllDefaultLabelKeyValues(user);
		
		if(labelsList.isEmpty()){
			labelsList = labelKeysDAO.selectAllLabels();
		}
		
		
		return CommonUtils.returnLabelKeyValue(labelsList);
	}

	public User setDefaultLabelKeyValuesInUser(User user) throws SQLException {
		user.setDefaultLabelKeyValuesMap(selectAllDefaultLabelKeyValues(user));
		return user;
	}

	@Override
	public HashMap<String, String> selectAllDefaultLabels() throws SQLException {
		// TODO Auto-generated method stub
		List<LabelsDto> labelsList = null;
		labelsList = labelKeysDAO.selectAllLabels();
		return CommonUtils.returnLabelKeyValue(labelsList);
	}
	
	
	public Boolean isLabelInSync(Long merchantId,String dateAdded) throws SQLException {
		return labelsDAO.isLabelInSync(merchantId, dateAdded);
	}
	
}
