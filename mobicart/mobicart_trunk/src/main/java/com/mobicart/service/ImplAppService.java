package com.mobicart.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mobicart.dao.AppDAO;
import com.mobicart.dao.AppDAOImpl;
import com.mobicart.dao.AppDeviceTokensDAO;
import com.mobicart.dao.BillingDAO;
import com.mobicart.dao.ColorSchemeDAO;
import com.mobicart.dao.FeatureDAO;
import com.mobicart.dao.NewsDAO;
import com.mobicart.dao.OrderDAO;
import com.mobicart.dao.PushNotificationDAO;
import com.mobicart.dao.StaticPageDAO;
import com.mobicart.dao.TerritoryDAO;
import com.mobicart.dao.UserDAO;
import com.mobicart.dao.impl.AppDaoImpl;
import com.mobicart.dto.ColorSchemeDto;
import com.mobicart.dto.PageDto;
import com.mobicart.model.App;
import com.mobicart.model.AppColorScheme;
import com.mobicart.model.AppDeviceTokens;
import com.mobicart.model.AppDeviceTokensExample;
import com.mobicart.model.AppExample;
import com.mobicart.model.AppFeatures;
import com.mobicart.model.AppTerritory;
import com.mobicart.model.Billing;
import com.mobicart.model.ColorScheme;
import com.mobicart.model.Feature;
import com.mobicart.model.News;
import com.mobicart.model.NewsExample;
import com.mobicart.model.Order;
import com.mobicart.model.OrderExample;
import com.mobicart.model.PushNotification;
import com.mobicart.model.PushNotificationExample;
import com.mobicart.model.StaticPage;
import com.mobicart.model.StaticPageExample;
import com.mobicart.model.Territory;
import com.mobicart.model.User;
import com.mobicart.util.DateTimeUtils;
import com.mobicart.util.Pager;


@Service
public class ImplAppService implements AppService {
	

	/**
	 * Logger for this class
	 */
 
	private static final Logger logger = LoggerFactory.getLogger(ImplAppService.class);

	@Autowired
	FeatureDAO featureDAO;

	@Autowired
	AppDaoImpl appDao;
	
	 
	
	@Autowired
	ColorSchemeDAO colorSchemeDAO;

	@Autowired
	TerritoryDAO territoryDAO;

	@Autowired
	StaticPageDAO staticPageDAO;

	@Autowired
	BillingDAO billingDAO;	

	@Autowired
	OrderDAO orderDAO;
	
	@Autowired
	NewsDAO newsDAO;
	

	@Autowired
	UserDAO userDAO;
	
	@Autowired
	PushNotificationDAO pushNotificationDAO;
	
	@Autowired
	AppDeviceTokensDAO appDeviceTokensDAO;


	/**
	 * {@inheritDoc}
	 */
	public List<Feature> findAllAppFeatures() {
		return featureDAO.findAllFeatures();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Feature> findAppFeatures(Long appId) {
		return featureDAO.findFeaturesByApp(appId);
	}

	/**
	 * {@inheritDoc}
	 */
	public App saveAppVitals(App app)  throws SQLException{
		Long appId = 0L;
		// update
		if (app.getId() != null) {
			appId = app.getId();
			app.setdUpdatedOn(DateTimeUtils.getCurrentTimestamp());
			appDao.update(app);
		} else {
			// insert
			app.setdCreatedOn(DateTimeUtils.getCurrentTimestamp());
			app.setbEnabled(true);
			appId = appDao.save(app);
		}
		return appDao.findAppById(appId);
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(App app){
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	public App createApp(Long userId) throws SQLException {
		 
		App app = new App();
		app.setUserId(userId);
		// save initial
		Long appId = appDao.save(app);
		// get from database
		 
		app = appDao.findAppById(app.getId());
		 
		return app;
	}

	/**
	 * {@inheritDoc}
	 */
	public App findAppByUser(User user)  throws SQLException{
		return appDao.findAppByUserId(user.getId());
	}

	
	/**
	 * {@inheritDoc}
	 */
	public List<ColorScheme> findAllColorSchemes() {
		return colorSchemeDAO.findAllSchemes();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean saveAppColorScheme(AppColorScheme appColorScheme) {
		 
		boolean retVal = true;
		
       try{
		/*ColorScheme colorScheme = colorSchemeDAO.findColorScheme(appColorScheme.getAppId());*/
    	Long existingColorScheme=colorSchemeDAO.findExistingColorScheme(appColorScheme.getAppId());	
		if (existingColorScheme != null) { 
							colorSchemeDAO.deleteColorScheme(existingColorScheme); 
			                colorSchemeDAO.updateAppColorScheme(appColorScheme);
		} else {
			colorSchemeDAO.saveAppColorScheme(appColorScheme);
		}
       }catch (Exception e) {
		 logger.error("save app colorscheme" ,e);
	}
		return retVal;
	}

	/**
	 * {@inheritDoc}
	 */
	public ColorScheme findAppColorScheme(Long appId) {
		return colorSchemeDAO.findColorScheme(appId);
	}
	/**
	 * {@inheritDoc}
	 */
 	public ColorSchemeDto findAppColorSchemeEnhance(Long appId) {
		return colorSchemeDAO.findColorSchemeEnhanced(appId);
	} 
	
	/**
	 * {@inheritDoc}
	 */
	public boolean saveAppFeatures(Long appId, List<Long> featureIdList) {
		boolean retVal = false;
		List<Feature> features = featureDAO.findFeaturesByApp(appId);
		if (features.size() > 0) {
			featureDAO.removeAppFeaturesByAppId(appId);
		}

		List<AppFeatures> appFeatureList = new ArrayList<AppFeatures>();
		for (Long featureId : featureIdList) {
			appFeatureList.add(new AppFeatures(appId, featureId));
		}

		retVal = featureDAO.saveAppFeatures(appFeatureList);
		return retVal;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Territory> findAllTerritories() {
		return territoryDAO.findAllTerritories();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Territory> findAppTerritories(Long appId) {
		return territoryDAO.findAppTerritories(appId);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean saveAppTerritories(Long appId, Long[] territoryIds) {

		List<AppTerritory> appTerritories = new ArrayList<AppTerritory>();

	if(territoryIds!=null){	
		for (Long territoryId : territoryIds) {
			appTerritories.add(new AppTerritory(appId, territoryId));
		}
	}
		//remove old territories
		territoryDAO.removeAppTerritories(appId);
		
		//insert newly selected
		if(appTerritories.size()>0)
			territoryDAO.saveAppTerritories(appTerritories);

		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public int saveAppSubmitPref(App app) {
		return appDao.update(app);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<StaticPage> findAllStaticPages() {
		return staticPageDAO.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	public StaticPage findStaticPageById(Long staticPageId) {
		return staticPageDAO.find(staticPageId);

	}

	/**
	 * {@inheritDoc}
	 */
	public void updateStaticPage(StaticPage staticPage) {
		staticPageDAO.update(staticPage);

	}

	/**
	 * {@inheritDoc}
	 */
	public void makeBillingTransaction(Billing billing) {
		billingDAO.create(billing);
		
	}

	/**
	 * {@inheritDoc}
	 */
	public void saveIntialStaticPages(List<StaticPage> staticPages) {
		staticPageDAO.createIntialStaticPages(staticPages);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<StaticPage> findStaticPageByUser(Long userId) {
		StaticPageExample staticPageExample=new StaticPageExample();
		staticPageExample.createCriteria().andUserIdEqualTo(userId);
		return staticPageDAO.findByExample(staticPageExample);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<StaticPage> findStaticPagesByApp(Long appId) {
		StaticPageExample staticPageExample=new StaticPageExample();
		staticPageExample.createCriteria().andAppIdEqualTo(appId);
		return staticPageDAO.findByExample(staticPageExample);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<StaticPage> findStaticPagesByAppAndFeatureId(Long appId,Long featureId) {
		StaticPageExample staticPageExample=new StaticPageExample();
		staticPageExample.createCriteria().andAppIdEqualTo(appId).andFeatureIdEqualTo(featureId);
		return staticPageDAO.findByExample(staticPageExample);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<Order> findOrdersByApp(Long appId) {
		OrderExample orderExample=new OrderExample();
		orderExample.createCriteria().andAppIdEqualTo(appId);
		return orderDAO.findByExample(orderExample);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<Order> findOrdersByUser(Long userId) {
		OrderExample orderExample=new OrderExample();
		orderExample.createCriteria().andUserIdEqualTo(userId);
		return orderDAO.findByExample(orderExample);
	}

	/**
	 * {@inheritDoc}
	 */
	public Map<String, Object>  findAllOrders(Pager pager)  {
		long totalCount=0;
		List<Order> orders = null;
		Map<String, Object> returnMap=null; 
		String value="";
		OrderExample orderExample = new OrderExample();

		if (pager.getSortBy() != null) {
			orderExample.setOrderByClause("" + pager.getSortBy() + " "+pager.getSortOrder());
			} else {
				orderExample.setOrderByClause("id DESC");
			}
			if (pager.getKeyword() != null && pager.getKeyword().length()>0 ) {
				try {
					Long orderNo = Long.parseLong(pager.getKeyword());
					orderExample.createCriteria().andIdEqualTo(orderNo);
				} catch (Exception e) {
					value=pager.getKeyword()!=null?"%"+pager.getKeyword()+"%":"%%";
					orderExample.createCriteria().andSAppNameLike(
							value);
				}
			}			
			orders = orderDAO.findByKeywordExample(orderExample,
					pager.getPage() * pager.getResults(), pager.getResults());
			
		totalCount=orderDAO.findCountByExample(orderExample);

		returnMap =new HashMap<String, Object>(); 
		returnMap.put("totalCount", totalCount);
		returnMap.put("orderList", orders);

		return returnMap;
	}

	/**
	 * {@inheritDoc}
	 */	
	public Order findOrder(Long id) {
		return orderDAO.find(id);
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateOrder(Order order) {
		orderDAO.update(order);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<App> findAppsByUserId(Long userId) {
		AppExample appExample=new AppExample();
		appExample.createCriteria().andUserIdEqualTo(userId);
		return appDao.findAppsByExample(appExample);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<News> findNewsItemsByAppId(Long appId) {
		NewsExample example=new NewsExample();
		example.createCriteria().andAppIdEqualTo(appId);
		example.setOrderByClause("d_date DESC");
		return newsDAO.findByExample(example);
	}

	/**
	 * {@inheritDoc}
	 */
	public Long makeAppOrderTransaction(Order order) {
		if(order.getId()!=null){
			orderDAO.update(order);
			return order.getId();
		}else{
			return orderDAO.create(order);	
		}
		
	}

	/**
	 * {@inheritDoc}
	 */
	public App findAppById(Long appId)  throws SQLException {
		return appDao.findAppById(appId);
	}

	
	/**
	 * {@inheritDoc}
	 */
	public Long scheduleNotification(PushNotification pushNotification) throws Exception{
		
		return pushNotificationDAO.save(pushNotification);
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	public AppDeviceTokens registerAppForNotifiction(AppDeviceTokens appDeviceTokens){
		
		//find already registered
		AppDeviceTokensExample appDeviceTokensExample=new AppDeviceTokensExample();		
		appDeviceTokensExample.createCriteria().andAppIdEqualTo(appDeviceTokens.getAppId()).andTokenIdEqualTo(appDeviceTokens.getTokenId()).andAppTypeEqualTo(appDeviceTokens.getAppType());
		
		List <AppDeviceTokens>tokens=appDeviceTokensDAO.findDeviceByExample(appDeviceTokensExample);
		
		
		if(tokens==null||tokens.size()<1){
		
		Long id= appDeviceTokensDAO.registerApp(appDeviceTokens);
		appDeviceTokens.setId(id);
		}else{
			appDeviceTokens=tokens.get(0);
		}
		
		return appDeviceTokens;
	}
	

	/**
	 * {@inheritDoc}
	 */
	public List<PushNotification> getNotificationsByStatus(String status) throws Exception{
		 
		 //find by status
		 PushNotificationExample pushNotificationExample=new PushNotificationExample();		 
		 pushNotificationExample.createCriteria().andStatusEqualTo(status);
		 
		 pushNotificationExample.setOrderByClause("id asc");
		 
		return pushNotificationDAO.getNotificationsByExample(pushNotificationExample);
		 
	 }
	 
	/**
	 * {@inheritDoc}
	 */
	 public List<PushNotification> getNotificationsByApp(Long appId)throws Exception {
		 //find by status
		 PushNotificationExample pushNotificationExample=new PushNotificationExample();		 
		 pushNotificationExample.createCriteria().andAppIdEqualTo(appId).andStatusNotEqualTo("deleted");
		 
		 pushNotificationExample.setOrderByClause("id desc");
		 
		return pushNotificationDAO.getNotificationsByExample(pushNotificationExample);
	 }
	 
	 /**
	  * {@inheritDoc}
	  */
	 public List<PushNotification> getAppNotificationsAfterDate(Date date,Long appId,String status) throws Exception{
		 
		 //find by status
		 PushNotificationExample pushNotificationExample=new PushNotificationExample();		 
		 pushNotificationExample.createCriteria().andDateGreaterThanOrEqualTo(date).andAppIdEqualTo(appId).andStatusNotEqualTo(status);
		 pushNotificationExample.setOrderByClause("id asc");
		 
		return pushNotificationDAO.getNotificationsByExample(pushNotificationExample);
		 
	 }

	 /**
	  * {@inheritDoc}
	  */
    public List<AppDeviceTokens> getDevicesByApp(Long appId){
    	
    	logger.debug("fetching devices for app id {}",appId);
    	//find by appId
    	AppDeviceTokensExample appDeviceTokensExample=new AppDeviceTokensExample();    	
    	appDeviceTokensExample.createCriteria().andAppIdEqualTo(appId);
    	
    	return appDeviceTokensDAO.findDeviceByExample(appDeviceTokensExample);
    }
    
    /**
	 * {@inheritDoc}
	 */
    public void updateNotification(PushNotification pushNotification) throws Exception{
    	pushNotificationDAO.updateNotification(pushNotification);
    }
    
    /**
	 * {@inheritDoc}
	 */
    public void deleteNotification(PushNotification pushNotification) throws Exception{
    	pushNotificationDAO.deleteNotification(pushNotification);
    }
    
    /**
	 * {@inheritDoc}
	 */    
    public PushNotification findNotificationById(Long id) throws Exception{
    	return pushNotificationDAO.findNotificationById(id);
    }
    
    /**
	 * {@inheritDoc}
	 */
    public List<AppDeviceTokens> getDevicesByRadius(Double latitude,Double longitude, String distance,Long appId) throws Exception{
    	return appDeviceTokensDAO.getDevicesByRadius(latitude,longitude,distance,appId);
    }
    
    
    /**
	 * {@inheritDoc}
	 */
	public List<PageDto> getSelectedPages(long appId) {
		try {
			List<Feature> features=featureDAO.findFeaturesByApp(appId);
			
			List<Long> featureIds=new ArrayList<Long>();
			
			for (Feature feature : features) {
				featureIds.add(feature.getId());
			}
			
			StaticPageExample staticPageExample = new StaticPageExample();
			staticPageExample.createCriteria().andAppIdEqualTo(appId).andFeatureIdIn(featureIds);

			return  staticPageDAO
					.findPageBeansByExample(staticPageExample);


		} catch (DataAccessException e) {
			logger.error("error", e);
		} catch (Exception e) {
			logger.error("error", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public PageDto getPage(long pageId) {
		try {
			return staticPageDAO
			.findPageBean(pageId);
		} catch (DataAccessException e) {
			logger.error("error", e);
		} catch (Exception e) {
			logger.error("error", e);
		}
		return null;
	}
	/**
	 * {@inheritDoc}
	 */
	public long saveColorScheme(ColorScheme colorscheme,long appid) {
		 
		return colorSchemeDAO.saveColorScheme(colorscheme,appid);
	}

	@Override
	public int updateAppVersion(App app) {
		// TODO Auto-generated method stub
		  return appDao.updateAppVersion(app);
	}
  
    
}
