package com.mobicart.service;

import java.sql.SQLException;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mobicart.dto.ColorSchemeDto;
import com.mobicart.dto.PageDto;
import com.mobicart.model.App;
import com.mobicart.model.AppColorScheme;
import com.mobicart.model.AppDeviceTokens;
import com.mobicart.model.Billing;
import com.mobicart.model.ColorScheme;
import com.mobicart.model.Feature;
import com.mobicart.model.News;
import com.mobicart.model.Order;
import com.mobicart.model.PushNotification;
import com.mobicart.model.StaticPage;
import com.mobicart.model.Territory;
import com.mobicart.model.User;
import com.mobicart.util.Pager;

public interface AppService {

	/**
	 * Finds list of all Feature instances available
	 * @return List of Feature instances
	 */
  	public List<Feature> findAllAppFeatures();

  	/**
  	 * Finds list of all Feature instances for an App
  	 * @param appId {@link App} appId
  	 * @return List of Feature instances
  	 */
    public List<Feature> findAppFeatures(Long appId);

    /**
     * Finds list of all Territory instances
     * @return List of Territory instances
     */
    //@Cacheable(cacheName = "countriesCache")
    public List<Territory> findAllTerritories();

    /**
     * Finds list of all Territory instances for an App
     * @param appId {@link App} id
     * @return List of Territory instances
     */
    public List<Territory> findAppTerritories(Long appId);

    /**
     * Creates an App instance for a particular User
     * @param userId {@link User} id
     * @return {@link App}
     * @throws SQLException
     */
    public App createApp(Long userId) throws SQLException;

    /**
     * Saves an App instance
     * @param app {@link App}
     * @return {@link App}
     * @throws SQLException
     */
    public App saveAppVitals(App app) throws SQLException;

    
    /**
     * Saves an App instance
     * @param app {@link App}
     * @return {@link App}
     * @throws SQLException
     */
    public void update(App app);
    
    /**
     * Finds an App of an User
     * @param user {@link User}
     * @return {@link App}
     * @throws SQLException
     */
    public App findAppByUser(User user) throws SQLException;
    
    /**
     * Finds an App 
     * @param appId {@link App}
     * @return {@link App}
     * @throws SQLException
     */
    public App findAppById(Long appId) throws SQLException;
    
    /**
     * Finds a list of App instances for an User
     * @param userId {@link User} id
     * @return List of App for an User
     */
    public List<App> findAppsByUserId(Long userId);
    
    /**
     * Finds a list of ColorScheme instances
     * @return List of ColorScheme instances
     */
    public List<ColorScheme> findAllColorSchemes();

    /**
     * Finds a ColorScheme instance for an App
     * @param appId {@link App} id
     * @return {@link ColorScheme}
     */
    public ColorScheme findAppColorScheme(Long appId);
    /**
     * Finds a ColorScheme instance for an App
     * @param appId {@link App} id
     * @return {@link ColorScheme}
     */
    public ColorSchemeDto findAppColorSchemeEnhance(Long appId);

    /**
     * Saves an AppColorScheme instance
     * @param appColorScheme {@link AppColorScheme}
     * @return boolean
     */
    public boolean saveAppColorScheme(AppColorScheme appColorScheme);

    /**
     * Saves Feature instances list for an App
     * @param appId {@link App} id
     * @param featureIdList List of id of Feature instances
     * @return boolean
     */
    public boolean saveAppFeatures(Long appId, List<Long> featureIdList);

    /**
     * Saves Territory instances list for an App
     * @param appId {@link App} id
     * @param territoryIds List of id of Territory instances
     * @return boolean
     */
    public boolean saveAppTerritories(Long appId, Long[] territoryIds);

    /**
     * Saves App preferences 
     * @param app {@link App}
     * @return int
     */
    public int saveAppSubmitPref(App app);

    /**
     * Finds list of all StaticPage instances
     * @return List of all StaticPage instances
     */
    public List<StaticPage> findAllStaticPages();

    /**
     * Finds list of all StaticPage instances for an User
     * @param userId {@link User}
     * @return List of all StaticPage instaces for an User
     */
    public List<StaticPage> findStaticPageByUser(Long userId);
    
    /**
     * Finds list of all StaticPage instances for an App
     * @param appId {@link App}
     * @return List of all StaticPage instances for an App
     */
    public List<StaticPage> findStaticPagesByApp(Long appId);
    
    /**
     * Finds list of all StaticPage instances for an App and a particular Feature
     * @param appId {@link App}
     * @param featureId {@link Feature} id 
     * @return List of all StaticPage instances for an App and a particular Feature
     */
    public List<StaticPage> findStaticPagesByAppAndFeatureId(Long appId,Long featureId); 

    /**
     * Finds a StaticPage instance 
     * @param staticPageId {@link StaticPage} id
     * @return {@link StaticPage}
     */
    public StaticPage findStaticPageById(Long staticPageId);

    /**
     * Updates a StaticPage instance
     * @param staticPage {@link StaticPage}
     */
    public void updateStaticPage(StaticPage staticPage);

    /**
     * Saves a list of StaticPage instances
     * @param staticPages List of StaticPage instances
     */
    public void saveIntialStaticPages(List<StaticPage> staticPages);
    
    /**
     * 
     * @param billing
     */
    public void makeBillingTransaction(Billing billing);
    
    public Long makeAppOrderTransaction(Order order);
    
    /**
     * Finds a list of Order instances for a particular App
     * @param appId {@link App}
     * @return List of Order instances for a particular App
     */
    public List<Order> findOrdersByApp(Long appId);
    
    /**
     * Finds a list of Order instances for a particular User
     * @param userId {@link User}
     * @return List of Order instances for a particular User
     */
    public List<Order> findOrdersByUser(Long userId);
    
    /**
     * 
     * @param pager
     * @return
     */
    public Map<String, Object> findAllOrders(Pager pager);
    
    /**
     * Finds an Order instance by id
     * @param id {@link Order} id
     * @return {@link Order}
     */
    public Order findOrder(Long id);
    
    /**
     * Updates an Order instance
     * @param order {@link Order}
     */
    public void updateOrder(Order order);

    /**
     * Finds list of News instances by App id
     * @param appId {@link App}
     * @return List of News instances by App id
     */
    public List<News> findNewsItemsByAppId(Long appId);
    
    /**
     * Registers an App for Notification 
     * @param appDeviceTokens {@link AppDeviceTokens}
     * @return {@link AppDeviceTokens}
     */
    public AppDeviceTokens registerAppForNotifiction(AppDeviceTokens appDeviceTokens);
  
    /**
     * Finds a list of AppDeviceTokens instances for an App
     * @param appId {@link App}
     * @return List of AppDeviceTokens instances for an App
     */
    public List<AppDeviceTokens> getDevicesByApp(Long appId);
    
    
    public Long scheduleNotification(PushNotification pushNotification) throws Exception;
    
    /**
     * Finds a list of PushNotification instances by status
     * @param status String
     * @return List of PushNotification instances
     * @throws Exception
     */
    public List<PushNotification> getNotificationsByStatus(String status)throws Exception;
    
    /**
     * Finds a list of PushNotification instances by App id
     * @param appId {@link App}
     * @return List of PushNotification instances 
     * @throws Exception
     */
    public List<PushNotification> getNotificationsByApp(Long appId) throws Exception;
    
    /**
     * Finds a list of PushNotification instances according to date App id and status
     * @param date Date
     * @param appId {@link App}
     * @param status String
     * @return List of PushNotification instances according to date App id and status
     * @throws Exception Generic Exception
     */
    public List<PushNotification> getAppNotificationsAfterDate(Date date,Long appId,String status) throws Exception;
    
    /**
     * Updates a PushNotification instance
     * @param pushNotification {@link PushNotification}
     * @throws Exception
     */
    public void updateNotification(PushNotification pushNotification) throws Exception;
    
    /**
     * Deletes a PushNotification instance
     * @param pushNotification {@link PushNotification}
     * @throws Exception
     */
    public void deleteNotification(PushNotification pushNotification) throws Exception;
    
    /**
     * Finds a PushNotification instance by id
     * @param id {@link PushNotification} id
     * @return {@link PushNotification}
     * @throws Exception
     */
    public PushNotification findNotificationById(Long id) throws Exception;
    
    /**
     * Finds a list of AppDeviceTokens instances by latitude, longitude, distance and App id
     * @param latitude Double
     * @param longitude Double
     * @param distance String 
     * @param appId {@link App} id
     * @return List of AppDeviceTokens instances 
     * @throws Exception
     */
    public List<AppDeviceTokens> getDevicesByRadius(Double latitude,Double longitude, String distance,Long appId) throws Exception;

    
    
    
    /**
	 * Get selected pages for an app
	 * @param appId
	 * @return List of {@link PageBean} instances
	 */
	public List<PageDto> getSelectedPages(long appId) ;
	
	
	/**
	 * Get Page details
	 * @param pageId
	 * @return {@link PageBean} instance
	 */
	public PageDto getPage(long pageId);
    
	/**
	 * save color scheme
	 * @param colorscheme {@link ColorScheme } instance
	 * @return long,  color scheme id
	 */
    public  long saveColorScheme(ColorScheme colorscheme,long appid) ;
    
    public  int  updateAppVersion(App app) ; 	
    	 
    
}
