package com.mobicart.dao;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.mobicart.model.AppFeatures;
import com.mobicart.model.AppFeaturesExample;
import com.mobicart.model.Feature;

@Repository
public class FeatureDAOImpl implements FeatureDAO {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(FeatureDAOImpl.class);

	@Autowired
	SqlMapClient sqlMapClient;

	@SuppressWarnings("unchecked")
	public List<Feature> findFeaturesByApp(long appId) {

		List<Feature> features = null;

		try {
			features = (List<Feature>) sqlMapClient.queryForList(
					"features.selectByAppId", appId);
		} catch (SQLException e) {
			logger
					.warn(
							"findFeaturesByApp(long) - exception ignored {}", e.getLocalizedMessage()); //$NON-NLS-1$
		}

		return features;
	}

	@SuppressWarnings("unchecked")
	public List<Feature> findAllFeatures() {

		List<Feature> features = null;
		try {
			features = (List<Feature>) sqlMapClient.queryForList(
					"features.selectAll", null);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return features;

	}

	public boolean saveAppFeatures(List<AppFeatures> appFeatures) {
		boolean retVal = false;
		try {
			sqlMapClient.startTransaction();
			sqlMapClient.startBatch();
			for (AppFeatures appFeature : appFeatures) {
				sqlMapClient.insert("app_features.insert", appFeature);
			}
			int rows = sqlMapClient.executeBatch();
			logger.debug("added " + rows+" app features"); 
			sqlMapClient.commitTransaction();
			retVal = true;
		} catch (Exception e) {
			logger.debug("saveAppFeautres(List<AppFeatures>)", e); 
			retVal = false;
		} finally {
			try {
				sqlMapClient.endTransaction();
			} catch (SQLException e) {
				logger.debug("saveAppFeautres(List<AppFeatures>)", e);
				retVal = false;
			}
		}
		return retVal;
	}

	public boolean updateAppFeaturesByExample(AppFeatures record,
			AppFeaturesExample example) {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = 0;
		try {
			rows = sqlMapClient.update("app_features.updateByExampleSelective", parms);
		} catch (SQLException e) {
			logger.warn("updateAppFeaturesByExample(AppFeatures, AppFeaturesExample) - exception ignored", e.getMessage()); //$NON-NLS-1$
		}
		return rows != 0 ? true : false;
	}

	private static class UpdateByExampleParms extends AppFeaturesExample {
		private Object record;

		public UpdateByExampleParms(Object record, AppFeaturesExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}

	public boolean removeAppFeaturesByAppId(long appId) {
		int rows=0;
		try {
			rows=sqlMapClient.delete("app_features.deleteByAppId", appId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows!=0?true:false;
	}

}
