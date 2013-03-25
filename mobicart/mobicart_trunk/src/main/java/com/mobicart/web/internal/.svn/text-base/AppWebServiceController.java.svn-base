 package com.mobicart.web.internal;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mobicart.bo.FeaturesBO;
import com.mobicart.bo.ProductBO;
import com.mobicart.dto.ColorSchemeDto;
import com.mobicart.dto.PageDto;
import com.mobicart.model.App;
import com.mobicart.model.ColorScheme;
import com.mobicart.model.Feature;
import com.mobicart.model.GalleryImage;
import com.mobicart.model.News;
import com.mobicart.model.Product;
import com.mobicart.model.State;
import com.mobicart.model.StaticPage;
import com.mobicart.model.Store;
import com.mobicart.model.Territory;
import com.mobicart.model.User;
import com.mobicart.service.AppService;
import com.mobicart.service.StoreService;
import com.mobicart.service.user.UserService;

/**
 * This controller class handles web service calls related to App entity 
 * @author jasdeep.singh
 *
 */

@Controller
public class AppWebServiceController {
	
	
	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	AppService appService;
	
	@Autowired
	FeaturesBO featureBO;
	
	@Autowired
	StoreService storeService;
	
	@Autowired
	UserService userService;

	@Autowired
	ProductBO productBO;

	
	private static final String PRODUCTS = "products";
	private static final String FEATURES = "features";
	private static final String COLORSCHEME = "colorscheme";
	private static final String APP_VITALS = "app-vitals";
	private static final String HOME_GALLERY_IMAGES = "gallery-images";
	private static final String STATIC_PAGES = "static-pages";
	private static final String FEATURED_PRODUCTS = "featured-products";
	private static final String FEATURED_PRODUCTS_WEBAPP = "products";
	private static final String NEWS_ITEMS = "news-items";
	private static final String NEWS_ITEMS_WEBAPP = "newsItems";
	private static final String APP_COUNTRIES = "app-countries";

	/**
	 * Service to fetch features/ tab information of the App
	 * 
	 * @param appId
	 * @return list of 'Feature' objects in JSON
	 */

	@RequestMapping(value = "/app/{appId}/features")
	public ModelAndView getFeaturesByAppId(@PathVariable Long appId) {
		List<Feature> features = appService.findAppFeatures(appId);
		// get deatail of pages checked marked
		features = featureBO.getFeatureDetail(features, appId);
		ModelAndView mav = new ModelAndView();
		mav.addObject(FEATURES, features);
		return mav;
	}

	/**
	 * Service to fetch features/ tab information of the app
	 * 
	 * @param appId
	 * @return list of 'Feature' objects in JSON
	 */

	@RequestMapping(value = "/app/{appId}/disabled-features")
	public ModelAndView getDisabledFeaturesByAppId(@PathVariable Long appId) {
		List<Feature> all_features = appService.findAllAppFeatures();
		
		List<Feature> features = appService.findAppFeatures(appId);
		
		//get deatail of pages checked marked
		features=featureBO.getFeatureDetail(features, appId);
		
		List<Feature> dis_features =new ArrayList<Feature>();
		
		try{
		boolean isSelected=false;
		
		for(Feature a_feat:all_features){
			 isSelected=false;
			for(Feature f_feat:features){
				
				if(String.valueOf(a_feat.getId()).equalsIgnoreCase(String.valueOf(f_feat.getId()))){
					isSelected=true;
					break;
				}
				
			}
			
			if(!isSelected){
				dis_features.add(a_feat);
			}
			
		}
		}catch(Exception e){
			logger.error("getDisabledFeaturesByAppId ",e);
			e.printStackTrace();
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject(FEATURES, dis_features);
		return mav;
	}
	
	
	/**
	 * Service to fetch colorscheme
	 * 
	 * @param appId
	 * @return 'ColorScheme' object in JSON
	 */
	@RequestMapping(value = "/app/{appId}/colorscheme")
	public ModelAndView getColorSchemeByAppId(@PathVariable Long appId) {
		ColorScheme colorScheme = appService.findAppColorScheme(appId);
		ModelAndView mav = new ModelAndView();
		mav.addObject(COLORSCHEME, colorScheme);
		return mav;
	}
   
	/**
	 * Service to fetch colorschem .Enhanced version  service {/app/{appId}/colorscheme}  
	 * 
	 * @param appId
	 * @return 'ColorScheme' object in JSON
	 */
	 
	@RequestMapping(value = "/app/{appId}/encolorscheme")
	public ModelAndView getEnhancedColorSchemeByAppId(@PathVariable Long appId) {
	 	ColorSchemeDto colorScheme = appService.findAppColorSchemeEnhance(appId);
		ModelAndView mav = new ModelAndView();
		mav.addObject(COLORSCHEME, colorScheme);
		return mav; 
	} 
	 
	/**
	 * Service to fetch App vitals
	 * 
	 * @param storeId
	 * @return App object in JSON
	 */
	@RequestMapping(value = "/app/{appId}/vitals")
	public ModelAndView getAppVitals(@PathVariable Long appId) {
		App app = null;
		try {
			app = appService.findAppById(appId);
		} catch (Exception e) {
			logger.error("getAppVitals",e);
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject(APP_VITALS, app);
		return mav;
	}

	

	/**
	 * Service to fetch list of gallery images
	 * 
	 * @param appId
	 * @return list of GalleryImage objects in JSON
	 */
	@RequestMapping(value = "/app/{appId}/gallery-images")
	public ModelAndView getHomeGalleriesbyApp(@PathVariable Long appId) {
		List<GalleryImage> galleryImages = null;
		try {
			galleryImages = storeService
					.findGalleryImagesByApp(appId);
		} catch (Exception e) {
			logger.error("getHomeGalleriesbyApp",e);
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject(HOME_GALLERY_IMAGES, galleryImages);
		return mav;
	}

	

	/**
	 * Service to fetch list of static pages
	 * 
	 * @param storeId
	 * @return List of StaticPage objects in JSON
	 */
	@RequestMapping(value = "/app/{appId}/static-pages")
	public ModelAndView getStaticPagesByStore(@PathVariable Long appId) {
		List<StaticPage> pages = appService.findStaticPagesByApp(appId);
		ModelAndView mav = new ModelAndView();
		mav.addObject(STATIC_PAGES, pages);
		return mav;
	}

	
	
	/**
	 * Service to fetch list of "selected"  static pages for webapp
	 * @param appId {@link App} id
	 * @return List of {@link PageBean} instances in JSON
	 */
	@RequestMapping(value = "/app/{appId}/static-pages-webapp")
	public @ResponseBody List<PageDto>  getStaticPagesByAppWebapp(@PathVariable Long appId) {
		return appService.getSelectedPages(appId);
	}


	
	/**
	 * Service to fetch page details
	 * @param appId {@link App} id
	 * @return  {@link PageBean} instance
	 */
	@RequestMapping(value = "/page/{pageId}/details")
	public @ResponseBody PageDto  getStaticPageByPage(@PathVariable Long pageId) {
		return appService.getPage(pageId);
	}

	
	
	/**
	 * Service to fetch list of news items
	 * 
	 * @param appId
	 */
	@RequestMapping(value = "/app/{appId}/news-items")
	public ModelAndView getNewsItems(@PathVariable Long appId) {
		List<News> newsItems = appService.findNewsItemsByAppId(appId);
		ModelAndView mav = new ModelAndView();
		mav.addObject(NEWS_ITEMS, newsItems);
		return mav;
	}

	
	
	/**
	 * Service to fetch list of news items for webapp
	 * 
	 * @param appId
	 */
	@RequestMapping(value = "/app/{appId}/news-items-webapp")
	public ModelAndView getNewsItemsForWebapp(@PathVariable Long appId) {
		List<News> newsItems = appService.findNewsItemsByAppId(appId);
		ModelAndView mav = new ModelAndView();
		mav.addObject(NEWS_ITEMS_WEBAPP, newsItems);
		return mav;
	}
	
	
	/**
	 * Service to fetch list of featured products
	 * 
	 * @param appId
	 */
	@RequestMapping(value = "/app/{appId}/featured-products")
	public ModelAndView getFeaturedProducts(
			@PathVariable Long appId, 
			@RequestParam(value="c",required=false) Long countryId,
			@RequestParam(value="s",required=false) Long stateId,
			HttpServletRequest request) {
		
		App app = null;
		Store store = null;
		User user = null;
		ModelAndView mav = new ModelAndView();
		try {

			app = appService.findAppById(appId);

			List<Product> products = null;

			if (app != null) {
				// get owner of store
				user = userService.find(app.getUserId());

				store = storeService.findStoreByUserId(user.getId());

				// get products for app
				Product product=new Product();
					product.setAppId(appId);
					product.setUserId(user.getId());
					product.setMaxRowNum(Long.parseLong(user.getStoreProductLimit().toString()));
				//products = storeService.findFeaturedProductsByApp(appId);
					products = storeService.findFeaturedProductsByAppAndMaxlimit(product); 

				// calculate tax on products
				products = productBO.calculateTax(products, store, countryId,
						stateId, user);

			}

			mav.addObject(FEATURED_PRODUCTS, products);
		} catch (Exception e) {
			logger.error("getFeaturedProducts", e);
		}
		return mav;
	}
	
	
	/**
	 * Service to fetch list of featured products for web app
	 * 
	 * @param appId
	 */
	@RequestMapping(value = "/app/{appId}/featured-products-webapp")
	public ModelAndView getFeaturedProductsForWebApp(
			@PathVariable Long appId, 
			@RequestParam(value="c",required=false) Long countryId,
			@RequestParam(value="s",required=false) Long stateId,
			HttpServletRequest request) {
		
		App app = null;
		Store store = null;
		User user = null;
		ModelAndView mav = new ModelAndView();
		try {

			app = appService.findAppById(appId);

			List<Product> products = null;

			if (app != null) {
				// get owner of store
				user = userService.find(app.getUserId());

				store = storeService.findStoreByUserId(user.getId());

				// get products for app
				products = storeService.findFeaturedProductsByApp(appId);

				// calculate tax on products
				products = productBO.calculateTax(products, store, countryId,
						stateId, user);

			}

			mav.addObject(FEATURED_PRODUCTS_WEBAPP, products);
		} catch (Exception e) {
			logger.error("getFeaturedProducts", e);
		}
		return mav;
	}

	
	
	/**
	 * Service to fetch list of products in a sub departments
	 * 
	 * @param categoryId
	 * @return List of 'Product' objects in JSON
	 */
	@RequestMapping(value = "/app/{appId}/search/{keyword}/products")
	public ModelAndView getProductsBySearch(@PathVariable Long appId,
			@PathVariable String keyword,
			@RequestParam(value="c",required=false) Long countryId,
			@RequestParam(value="s",required=false) Long stateId,
			HttpServletRequest request) {
		
		App app=null;
		Store store = null;
		User user = null;
		ModelAndView mav = new ModelAndView();
		
		try{
		List<Product> products=null;
		//get app
		app=appService.findAppById(appId);	
		
		if(app!=null){
		//get owner of store
		user= userService.find(app.getUserId());
		
		//get store			
		store = storeService.findStoreByUserId(user.getId());
		
		
		//get products
		 products = storeService.findProductsByKeywordSearch(appId, keyword);
		
		//calculate tax on products
		products=productBO.calculateTax(products, store, countryId, stateId	, user);
		}
		
		mav.addObject(PRODUCTS, products);
		
       }catch(Exception e){
    	   logger.error("getProductsBySearch",e);
		}
		
		return mav;
	}

	
	
	
	/**
	 * Service to fetch App countries
	 * 
	 * @param appId
	 * @return list of 'Territory' objects in JSON
	 */

	@RequestMapping(value = "/app/{appId}/countries")
	public ModelAndView getAppCountries(@PathVariable Long appId) {
		List<Territory> countries = appService.findAppTerritories(appId);
		ModelAndView mav = new ModelAndView();
		mav.addObject(APP_COUNTRIES, countries);
		return mav;
	}

	


	/**
	 * Get All countries
	 * 
	 * @return list of 'Territory' objects
	 */
	@RequestMapping(value = "/countries")
	public @ResponseBody
	List<Territory> findAllCountries() {
		return appService.findAllTerritories();
	}
	
	/**
	 * Get All states for a country
	 * 
	 * @param countryId
	 * @return list of 'State' objects
	 */
	@RequestMapping(value = "/country/{countryId}/states")
	public @ResponseBody
	List<State> findAllStatesByCountry(@PathVariable Long countryId) {
		return storeService.findStatesByTerritory(countryId);
	}


	

}
