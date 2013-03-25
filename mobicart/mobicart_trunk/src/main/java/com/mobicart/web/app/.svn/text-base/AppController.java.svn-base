package com.mobicart.web.app;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import oauth.signpost.http.HttpRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.mobicart.bo.UserBO;
import com.mobicart.model.App;
import com.mobicart.model.AppColorScheme;
import com.mobicart.model.Billing;
import com.mobicart.model.ColorScheme;
import com.mobicart.model.Feature;
import com.mobicart.model.News;
import com.mobicart.model.Order;
import com.mobicart.model.PushNotification;
import com.mobicart.model.SiteConstant;
import com.mobicart.model.StaticPage;
import com.mobicart.model.Store;
import com.mobicart.model.Territory;
import com.mobicart.model.User;
import com.mobicart.service.AdminService;
import com.mobicart.service.ApiService;
import com.mobicart.service.AppService;
import com.mobicart.service.LabelService;
import com.mobicart.service.StoreService;
import com.mobicart.service.user.UserService;
import com.mobicart.util.Constants;
import com.mobicart.util.DateTimeUtils;
import com.mobicart.util.DesEncrypter;
import com.mobicart.util.EmailGenerator;
import com.mobicart.util.FileUtils;
import com.mobicart.util.ImageSize;
import com.mobicart.util.ImageUtils;
import com.mobicart.util.ImgMagicCmdExcecutor;
import com.mobicart.util.MagicalPower;
import com.mobicart.util.PathLocator;
import com.mobicart.util.ResourceProperties;
import com.mobicart.util.Sender;
import com.mobicart.web.security.oauth.ConsumerSecretGenerationException;

/**
 * Handles {@link App} related actionsRu
 *  
 */
@Controller
@RequestMapping("/app/**")
@SessionAttributes({"app"})
public class AppController {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(AppController.class);
	/**
	 * {@link AppService} instance
	 */
	@Autowired
	AppService appService;
	/**
	 * {@link UserService} instance
	 */
	@Autowired
	UserService userService;

	/**
	 * {@link StoreService} instance
	 */
	@Autowired
	StoreService storeService;

	/**
	 * {@link AdminService} instance
	 * 
	 */
	@Autowired
	AdminService adminService;
	
	/**
	 * {@link LabelService} instance
	 * 
	 */
	@Autowired
	LabelService labelService;


	/**
	 * Mail {@link Sender} instance
	 */
	@Autowired
	Sender sender;
    
	@Autowired
	ApiService api;
	/*
	 * 
	 */
	
	 
	
 	public static final String APP_CREATE_VIEW_KEY = "redirect:appearance";
	public static final String APP_CREATE_VITALS_VIEW_KEY = "app/vitals";
	public static final String APP_CREATE_APPEARANCE_VIEW_KEY = "app/appearance_new";
	//public static final String APP_CREATE_APPEARANCE_VIEW_KEY = "app/appearance";
	public static final String APP_CREATE_APPEARANCE_REDIRECT_KEY = "redirect:appearance";
	public static final String APP_SUBMIT_APP_VIEW_KEY = "app/submit";

 
	 

	/**
	 * load app object from session to SessionAttribues
	 * @param session
	 * @return App object
	 */
	public App createApp(HttpSession session){
		return (App) session.getAttribute("app");
	}




	/**
	 * Redirect to appearance view
	 * @param app
	 * @param result
	 * @return path to appearance redirect
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String show(@ModelAttribute("app") App app, BindingResult result) {
		return APP_CREATE_VIEW_KEY;
	}

	/**
	 * Appearance view
	 * @param app
	 * @param result
	 * @param map
	 * @return path to views/appearance.jsp
	 */
	@RequestMapping(value = "appearance", method = RequestMethod.GET)
	public String appearance(@ModelAttribute("app") App app,
			BindingResult result,
			ModelMap map,
			HttpServletRequest request) {
		
		try {
			/**check which color scheme is selected (custom/default)**/
			
			
			
			
			List<ColorScheme> colorSchemes = appService.findAllColorSchemes();
			List<Feature> remainingFeatures = appService.findAllAppFeatures();

			map.put("colorSchemes", colorSchemes);
			map.put("features", remainingFeatures);
			
				
			List<Feature> selectedFeatures = null;
			ColorScheme selectedColorScheme = null;
			if (app.getId() != null) {
			 
				selectedColorScheme = appService.findAppColorScheme(app.getId());
				logger.debug("colorssheme {}",selectedColorScheme);
				selectedFeatures = appService.findAppFeatures(app.getId());
			}

			
			if (selectedFeatures != null) {
				for (Feature feature : selectedFeatures) {
					remainingFeatures.remove(feature);
				}
			}

			List<StaticPage> staticPages=appService.findStaticPagesByApp(app.getId());
			selectedFeatures = addTabTitles(selectedFeatures, staticPages);
			remainingFeatures = addTabTitles(remainingFeatures, staticPages);

			map.put("selectedColorScheme", selectedColorScheme);
			map.put("selectedFeatures", selectedFeatures);
			map.put("remainingFeatures", remainingFeatures);

			
		} catch (Exception e) {
			logger.error("Some exception" ,e);
			result.reject("Error.app.apperrance", "Some error occured " +e.getMessage());
			return APP_CREATE_APPEARANCE_VIEW_KEY;
		}

		return APP_CREATE_APPEARANCE_VIEW_KEY;
	}

	/**
	 * save appearance for {@link App}
	 * @param app {@link App} object
	 * @param result
	 * @param colorSchemeId 
	 * @param featureIds
	 * @param modelMap
	 * @return path to views/appearance.jsp
	 */
	@RequestMapping(value = "appearance/save", method = RequestMethod.POST)
	public String saveAppearance(@ModelAttribute("app") @Valid App app,
			BindingResult result,
			@RequestParam(value = "colorSchemeId", required = false) Long colorSchemeId,
			@RequestParam(value = "featureIds", required = false) Long[] featureIds,
			@RequestParam(value = "themeType", required = false) String themeType,
			ModelMap modelMap,
			HttpServletRequest request,
			@RequestParam(value = "bgcolor", required = false) String bgcolor,
			@RequestParam(value = "fgcolor", required = false) String fgcolor,
			@RequestParam(value = "pcolor", required = false) String pcolor,
			@RequestParam(value = "ptbcolor", required = false) String ptbcolor,
			@RequestParam(value = "sbcolor", required = false) String sbcolor
	        ) {
           	

		try {

			logger.debug("color: are "+themeType+"("+bgcolor+","+fgcolor+","+pcolor+")"+"colorSchemeId:"+colorSchemeId+",appid:"+app.getId());
			
			boolean someError=false;

			/*if (colorSchemeId == null || themeType == null) {
				modelMap.put("error", "Please select a color scheme");
				someError=true;
			}*/
			if (colorSchemeId == null || themeType == null) {
			modelMap.put("error", "Please select a color scheme");
			someError=true;
		    }
			if (featureIds == null) {
				modelMap.put("error", "Please select a Tab");
				someError=true;
			}
		 

			if(!someError){
				
		 
				
				if("custom".equalsIgnoreCase(themeType))
				{
					ColorScheme colorScheme=new ColorScheme();
					colorScheme.setsBgColor(bgcolor);
					colorScheme.setsFgColor(fgcolor);
					colorScheme.setsProductItemBgColor(pcolor);
					colorScheme.setsSearchButtonBgColor(sbcolor);
					colorScheme.setsBgImage(ptbcolor);
					colorScheme.setsType(themeType);
					colorScheme.setsTitle(themeType);
					/*save it to database*/
					 logger.debug("ColorScheme:"+colorScheme);
					appService.saveColorScheme(colorScheme,app.getId());
					colorSchemeId=colorScheme.getId();
					 
				}
				
				
				AppColorScheme appColorScheme = new AppColorScheme();
				appColorScheme.setAppId(app.getId());
				appColorScheme.setColorSchemeId(colorSchemeId);
				boolean done = appService.saveAppColorScheme(appColorScheme);
				if (!done) {
					result.reject("Error.app.colorscheme", "Some error in saving colorscheme");
				}
              

				List<Long> featureIdList = Arrays.asList(featureIds);

				done = appService.saveAppFeatures(app.getId(), featureIdList);
				if (!done) {
					result.reject("Error.app.features", "Some error in saving features");
				}
				
				modelMap.put("success", "Your settings have been updated successfully.");
			}

			List<ColorScheme> colorSchemes = appService.findAllColorSchemes();
			List<Feature> features = appService.findAllAppFeatures();
			modelMap.put("colorSchemes", colorSchemes);
			modelMap.put("features", features);

			ColorScheme selectedColorScheme = appService.findAppColorScheme(app.getId());
			logger.debug("colorssheme:"+selectedColorScheme.getsType()+"("+selectedColorScheme.getsFgColor()+","+selectedColorScheme.getsBgColor()+","+selectedColorScheme.getsProductItemBgColor());
			List<Feature> selectedFeatures = appService.findAppFeatures(app.getId());
			List<Feature> remainingFeatures = features;
			
			List<StaticPage> staticPages=appService.findStaticPagesByApp(app.getId());
			selectedFeatures = addTabTitles(selectedFeatures, staticPages);
			remainingFeatures = addTabTitles(remainingFeatures, staticPages);
			
			modelMap.put("selectedColorScheme", selectedColorScheme);
			modelMap.put("selectedFeatures", selectedFeatures);

			for (Feature feature : selectedFeatures) {
				remainingFeatures.remove(feature);
			}
			modelMap.put("remainingFeatures", remainingFeatures);
			
			//upload app logo...............
			/**/
			User user = (User) request.getSession().getAttribute("user");
			if(app.getCompanyLogoFile()!=null && app.getCompanyLogoFile().getFileItem().getSize() >0 ){
	            	
	            	MultipartFile companyLogoFile=app.getCompanyLogoFile();

	    			PathLocator pathLocator = new PathLocator();
	    			// change to online server for the time being
	    			String rootPath = pathLocator.getContextPath();

	    			String mobicartImagesFolderPath = "mobicartimages";
	    			FileUtils.makeDirectoryIfItsNotThere(rootPath
	    					+ mobicartImagesFolderPath);

	    			String companyFolderPath = "/company";
	    			FileUtils.makeDirectoryIfItsNotThere(rootPath
	    					+ mobicartImagesFolderPath + companyFolderPath);

	    			//hard code
	    			String userFolderPath = "/"+user.getId();
	    			FileUtils.makeDirectoryIfItsNotThere(rootPath
	    					+ mobicartImagesFolderPath + companyFolderPath
	    					+ userFolderPath);

	    			String dateWiseFolderPath = "/" + DateTimeUtils.getCurrentDate();
	    			FileUtils.makeDirectoryIfItsNotThere(rootPath
	    					+ mobicartImagesFolderPath + companyFolderPath
	    					+ userFolderPath + dateWiseFolderPath);

	    			String finalCompanyLogoImagePath = mobicartImagesFolderPath
	    					+ companyFolderPath + userFolderPath + dateWiseFolderPath;

	    			String companyLogoImageFileName=FileUtils.cleanSpecialChars(companyLogoFile.getOriginalFilename());
	    			companyLogoImageFileName=FileUtils.stuffedFilename(companyLogoImageFileName, "");
	    			String websiteCompanyLogoImagePath=finalCompanyLogoImagePath+"/"+FileUtils.stuffedFilename(companyLogoImageFileName, "_website");
	    			String ipadCompanyLogoImagePath=finalCompanyLogoImagePath+"/"+FileUtils.stuffedFilename(companyLogoImageFileName, "_ipad");
	    			
	    			//finalCompanyLogoImagePath += "/"+ companyLogoImageFileName;

	    			try{
	    			 
	    			/*InputStream companyLogoInputStream = null;
	    			OutputStream companyLogoOutputStream = null;
	    			OutputStream companyLogoWebsiteOutputStream = null;
	    			OutputStream companyLogoIpadOutputStream = null;*/
	    			if (companyLogoFile.getSize() > 0) {
	    				/*
	    				companyLogoInputStream = companyLogoFile.getInputStream();

	    				
	    				
	    				
	    				BufferedImage bufferedImage = ImageIO.read(companyLogoInputStream);
	    				
	    				BufferedImage websiteBufferedImage = ImageUtils.resize(bufferedImage,Constants.COMPANY_LOGO_WIDTH,Constants.COMPANY_LOGO_HEIGHT, false);
	    				BufferedImage iphoneBufferedImage = ImageUtils.resize(bufferedImage,Constants.COMPANY_LOGO_IPHONE_WIDTH, Constants.COMPANY_LOGO_IPHONE_HEIGHT, false);
	    				
	    				companyLogoOutputStream = new FileOutputStream(rootPath+ finalCompanyLogoImagePath);
	    				companyLogoWebsiteOutputStream = new FileOutputStream(rootPath+ websiteCompanyLogoImagePath);
	    				companyLogoIpadOutputStream = new FileOutputStream(rootPath+ ipadCompanyLogoImagePath);
	    				
	    				String format = (companyLogoFile.getOriginalFilename()
	    						.endsWith(".png")) ? "png" : "jpg";
	    				ImageIO.write(iphoneBufferedImage, format, companyLogoOutputStream);
	    				ImageIO.write(websiteBufferedImage, format, companyLogoWebsiteOutputStream);
	    				ImageIO.write(bufferedImage, format, companyLogoIpadOutputStream);
	    				
	    				companyLogoInputStream.close();
	    				companyLogoOutputStream.close();
	    				companyLogoWebsiteOutputStream.close();
 
	    				*/ 
	    				 	
	    			String finalPathLocation=rootPath+finalCompanyLogoImagePath+"/";
	    			String finalLocationOfOutFile=rootPath+finalCompanyLogoImagePath+"/"+FileUtils.stuffedFilename(companyLogoImageFileName, "_temp");
	    			logger.debug("finalPathLocation2:"+finalLocationOfOutFile);
	    			
	    			String tempCopy=ImgMagicCmdExcecutor.saveTempFile(companyLogoFile.getInputStream(), finalLocationOfOutFile);
	    			
	    			ImgMagicCmdExcecutor.resizeAndSave(tempCopy,rootPath+finalCompanyLogoImagePath+"/"+companyLogoImageFileName,Constants.COMPANY_LOGO_WIDTH,Constants.COMPANY_LOGO_HEIGHT,true);
	    			ImgMagicCmdExcecutor.resizeAndSave(tempCopy,rootPath+websiteCompanyLogoImagePath,Constants.COMPANY_LOGO_IPHONE_WIDTH, Constants.COMPANY_LOGO_IPHONE_HEIGHT,false);
	    			ImgMagicCmdExcecutor.resizeAndSave(tempCopy,rootPath+ipadCompanyLogoImagePath,Constants.COMPANY_LOGO_IPHONE_WIDTH, Constants.COMPANY_LOGO_IPHONE_HEIGHT,false);
	    			
	    			
	    			ImgMagicCmdExcecutor.resizeAndSaveFile(tempCopy,finalPathLocation,companyLogoImageFileName ,Constants.COMPANY_LOGO_ANDROID3_KEY,true);
	    			ImgMagicCmdExcecutor.resizeAndSaveFile(tempCopy,finalPathLocation,companyLogoImageFileName ,Constants.COMPANY_LOGO_ANDROID4_KEY,true);
	    			ImgMagicCmdExcecutor.resizeAndSaveFile(tempCopy,finalPathLocation,companyLogoImageFileName ,Constants.COMPANY_LOGO_ANDROID6_KEY,true);
	    			ImgMagicCmdExcecutor.resizeAndSaveFile(tempCopy,finalPathLocation,companyLogoImageFileName ,Constants.COMPANY_LOGO_IPAD_KEY,true);
	    			ImgMagicCmdExcecutor.resizeAndSaveFile(tempCopy,finalPathLocation,companyLogoImageFileName ,Constants.COMPANY_LOGO_IPHONE_KEY,true);
	    			ImgMagicCmdExcecutor.resizeAndSaveFile(tempCopy,finalPathLocation,companyLogoImageFileName ,Constants.COMPANY_LOGO_IPHONE4_KEY,true);
	    			ImgMagicCmdExcecutor.resizeAndSaveFile(tempCopy,finalPathLocation,companyLogoImageFileName ,Constants.COMPANY_LOGO_WEBSITE_KEY,true);	
	    			finalCompanyLogoImagePath += "/"+ companyLogoImageFileName;	 
	    		    user.setsCompanyLogo("/"+finalCompanyLogoImagePath);
	    		    logger.debug("Uploaded and updated successfully");
	    				
	    				
	    				//test magical power 
	    				
	    				//MagicalPower.resize(companyLogoInputStream, rootPath+websiteCompanyLogoImagePath, Constants.COMPANY_LOGO_WIDTH,Constants.COMPANY_LOGO_HEIGHT);

	    				
	    			}
	    			}catch(IOException ioe){
	    				logger.error("Cant save company image",ioe);
	    				modelMap.put("error", "Some error occured saving company image");
	    			}catch(Exception e){
	    				logger.error("Cant save company image",e);
	    				modelMap.put("error", "Some error occured saving company image");
	    			}
	    			userService.updateUser(user);
	    	    }
			/**/
			
			

		} catch (Exception e) {
			logger.error("Error Occured in method saveAppearance",e);
			result.reject("Error.app.apperrance", "Some error occured " +e.getMessage());
			return APP_CREATE_APPEARANCE_VIEW_KEY;
		}

		return APP_CREATE_APPEARANCE_VIEW_KEY;
	}

	/**
	 * Show  {@link App} vitals view
	 * @param app {@link App} instance
	 * @param result
	 * @param modelMap
	 * @return path to views/app/vitals.jsp
	 */
	@RequestMapping(value = "vitals", method = RequestMethod.GET)
	public String appVitals(@ModelAttribute("app") @Valid App app,
			BindingResult result, ModelMap modelMap) {

		List<Territory> territories = appService.findAllTerritories();
		List<Territory> appTerritories = appService.findAppTerritories(app.getId());

		modelMap.put("territories", territories);
		modelMap.put("appTerritories", appTerritories);

		return APP_CREATE_VITALS_VIEW_KEY;
	}

	/**
	 * Save {@link App} vitals
	 * @param app {@link App} instance
	 * @param result
	 * @param territoryIds
	 * @param modelMap
	 * @return path to views/app/vitals.jsp
	 */
	@RequestMapping(value = "vitals/submit", method = RequestMethod.POST)
	public String saveAppVitals(@ModelAttribute("app") @Valid App app,
			BindingResult result,
			@RequestParam(value = "territoryIds", required = false) Long[] territoryIds, ModelMap modelMap,HttpServletRequest request) {
		try {

			app = appService.saveAppVitals(app);

			appService.saveAppTerritories(app.getId(), territoryIds);

			List<Territory> territories = appService.findAllTerritories();
			List<Territory> appTerritories = appService.findAppTerritories(app.getId());

			modelMap.put("territories", territories);
			modelMap.put("appTerritories", appTerritories);

		} catch (Exception e) {
			logger.error("Error Occured in method saveAppVitals {}", e);
			result.reject("Error.app.apperrance", "Some error occured " +e.getMessage());
			return APP_CREATE_VITALS_VIEW_KEY;
		}
		logger.info("vitals saved successfully");
		modelMap.put("success", "Your settings have been updated successfully.");

		return APP_CREATE_VITALS_VIEW_KEY;
	}

	/**
	 * Save {@link App} vitals for resubmit
	 * @param app {@link App} instance
	 * @param result
	 * @param modelMap
	 * @return path to views/app/vitals.jsp
	 */
	/*
	@RequestMapping(value = "vitals/resubmit", method = RequestMethod.POST)
	public String resubmitAppVitals(@ModelAttribute("app") @Valid App app,
			BindingResult result, ModelMap modelMap,HttpServletRequest request) {
		
		try {
			modelMap.put("tabSelect", "1");

			MultipartFile iconImageFile = app.getFlIconImage();
			MultipartFile loaderImageFile = app.getFlLoaderImage();

			PathLocator pathLocator = new PathLocator();
			String rootPath = pathLocator.getContextPath();

			String mobicartImagesFolderPath = "mobicartimages";
			FileUtils.makeDirectoryIfItsNotThere(rootPath + mobicartImagesFolderPath);

			String appFolderPath = "/" + app.getId().toString();

			if (iconImageFile.getSize() > 0) {

				if (!iconImageFile.getContentType().contains("image")) {
					modelMap.put("error", "Please upload valid icon image file");
					logger.error("error in upload valid icon image file");
					return APP_CREATE_VITALS_VIEW_KEY;
				}

				String iconFolderPath = "/icon";
				FileUtils.makeDirectoryIfItsNotThere(rootPath + mobicartImagesFolderPath + iconFolderPath);

				FileUtils.makeDirectoryIfItsNotThere(rootPath + mobicartImagesFolderPath + iconFolderPath + appFolderPath);

				String finalIconImagePath = mobicartImagesFolderPath + iconFolderPath + appFolderPath + "/" + FileUtils.cleanSpecialChars(iconImageFile.getOriginalFilename());
				String finalIconImage57Path = mobicartImagesFolderPath + iconFolderPath + appFolderPath + "/" + FileUtils.stuffedFilename(FileUtils.cleanSpecialChars(iconImageFile.getOriginalFilename()),"_icon");
				String finalIconImage72x72Path=mobicartImagesFolderPath + iconFolderPath + appFolderPath + "/" + FileUtils.stuffedFilename(FileUtils.cleanSpecialChars(iconImageFile.getOriginalFilename()),"_72x72");	
				
				InputStream iconInputStream = null;
				OutputStream iconOutputStream = null;
				iconInputStream = iconImageFile.getInputStream();
				BufferedImage bufferedIconImage = ImageIO.read(iconInputStream);
				boolean validImage = true;
				if (bufferedIconImage.getHeight() < 512) {
					validImage = false;
				}
				if (bufferedIconImage.getWidth() < 512) {
					validImage = false;
				}
				if (!validImage) {
					iconInputStream.close();
					modelMap.put("error", "Icon Image must be more than 512 x 512");
					logger.error("icon image must be more than 512 x 512");
					return APP_CREATE_VITALS_VIEW_KEY;
				}
				
                 
               	MagicalPower magicalPower=new MagicalPower();
				//perserv old names as they are
				
				magicalPower.resizeAndSave(bufferedIconImage,rootPath+ finalIconImagePath, ImageSize.parse(ResourceProperties.getImageString(Constants.APP_ICON_IPHONE_KEY)));
				magicalPower.resizeAndSave(bufferedIconImage,rootPath+ finalIconImage57Path, ImageSize.parse(ResourceProperties.getImageString(Constants.APP_ICON_IPHONE_KEY)));
				magicalPower.resizeAndSave(bufferedIconImage,rootPath+ finalIconImage72x72Path, ImageSize.parse(ResourceProperties.getImageString(Constants.APP_ICON_IPAD_KEY)));
                	//save images according to new excel sheet
				magicalPower.resizeAndSave(bufferedIconImage, pathToSaveIcons,iconFileName ,Constants.APP_ICON_ANDROID3_KEY);
				magicalPower.resizeAndSave(bufferedIconImage, pathToSaveIcons,iconFileName ,Constants.APP_ICON_ANDROID4_KEY);
				magicalPower.resizeAndSave(bufferedIconImage, pathToSaveIcons,iconFileName ,Constants.APP_ICON_ANDROID6_KEY);
				magicalPower.resizeAndSave(bufferedIconImage, pathToSaveIcons,iconFileName ,Constants.APP_ICON_IPAD_KEY);
				magicalPower.resizeAndSave(bufferedIconImage, pathToSaveIcons,iconFileName ,Constants.APP_ICON_IPHONE_KEY);




                
				

				BufferedImage  bufferedIconImage512=ImageUtils.resize(bufferedIconImage,Constants.APP_ICON_512_WIDTH, Constants.APP_ICON_512_HEIGHT, true);

				BufferedImage  bufferedIconImage57=ImageUtils.resize(bufferedIconImage, Constants.APP_ICON_57_WIDTH, Constants.APP_ICON_57_HEIGHT, true);

				BufferedImage  bufferedIconImage72x72=ImageUtils.resize(bufferedIconImage, Constants.APP_ICON_IPAD_WIDTH, Constants.APP_ICON_IPAD_HEIGHT, true);

				
				
				iconOutputStream = new FileOutputStream(rootPath
						+ finalIconImagePath);
				String format = (iconImageFile.getOriginalFilename().endsWith(".png")) ? "png" : "jpg";
				ImageIO.write(bufferedIconImage512, format, iconOutputStream);

				OutputStream icon57OutputStream = new FileOutputStream(rootPath+ finalIconImage57Path);
				ImageIO.write(bufferedIconImage57, format, icon57OutputStream);

				
				OutputStream icon72x72OutputStream = new FileOutputStream(rootPath
						+ finalIconImage72x72Path);

				ImageIO.write(bufferedIconImage72x72, format, icon72x72OutputStream);


				iconInputStream.close();
				iconOutputStream.close();
				icon57OutputStream.close();
				icon72x72OutputStream.close();
				
				app.setsIconImage("/" + finalIconImagePath);
			}

			if (loaderImageFile.getSize() > 0) {

				if (!loaderImageFile.getContentType().contains("image")) {
					modelMap.put("error", "Please upload valid icon image file");
					return APP_CREATE_VITALS_VIEW_KEY;
				}

				String loaderFolderPath = "/loader";
				FileUtils.makeDirectoryIfItsNotThere(rootPath + mobicartImagesFolderPath + loaderFolderPath);
				FileUtils.makeDirectoryIfItsNotThere(rootPath + mobicartImagesFolderPath + loaderFolderPath + appFolderPath);

				String finalLoaderImagePath = mobicartImagesFolderPath + loaderFolderPath + appFolderPath + "/" + FileUtils.cleanSpecialChars(loaderImageFile.getOriginalFilename());
				String finalLoaderImageIpadPath = mobicartImagesFolderPath + loaderFolderPath + appFolderPath + "/" + FileUtils.stuffedFilename(FileUtils.cleanSpecialChars(loaderImageFile.getOriginalFilename()),"_768x1024");

				
				
				InputStream loaderInputStream = null;

				loaderInputStream = loaderImageFile.getInputStream();

				BufferedImage loaderImage=ImageIO.read(loaderImageFile.getInputStream());
				
				BufferedImage loaderIphoneImage=ImageUtils.resize(loaderImage, Constants.APP_LOADER_WIDTH, Constants.APP_LOADER_HEIGHT, true);
				
				ImageUtils.saveImage(loaderIphoneImage,rootPath+finalLoaderImagePath );
				
				BufferedImage  bufferedLoaderIpadImage=ImageUtils.resize(loaderImage, Constants.APP_LOADER_IPAD_WIDTH, Constants.APP_LOADER_IPAD_HEIGHT, true);
				ImageUtils.saveImage(bufferedLoaderIpadImage,rootPath+finalLoaderImageIpadPath );
				
				loaderInputStream.close();
				app.setsLoaderImage("/" + finalLoaderImagePath);
			}
			app = appService.saveAppVitals(app);

		} catch (Exception e) {
			logger.error("ResubmitAppVitals  error : {} " ,e); 
			ObjectError error = new ObjectError("error",
					"Some error occured : " + e.getLocalizedMessage());
			result.addError(error);

			return APP_CREATE_VITALS_VIEW_KEY;
		}

		List<Territory> territories = appService.findAllTerritories();
		List<Territory> appTerritories = appService.findAppTerritories(app.getId());

		modelMap.put("territories", territories);
		modelMap.put("appTerritories", appTerritories);

		modelMap.put("success", "Your settings have been updated successfully.");
		logger.info("settings have been updated successfully.");
		return APP_CREATE_VITALS_VIEW_KEY;
	}
      */
	@RequestMapping(value = "vitals/resubmit", method = RequestMethod.POST)
	public String resubmitAppVitals(@ModelAttribute("app") @Valid App app,
			BindingResult result, ModelMap modelMap,HttpServletRequest request) {
		
		try {
			modelMap.put("tabSelect", "1");

			MultipartFile iconImageFile = app.getFlIconImage();
			MultipartFile loaderImageFile = app.getFlLoaderImage();

			PathLocator pathLocator = new PathLocator();
			String rootPath = pathLocator.getContextPath();

			String mobicartImagesFolderPath = "mobicartimages";
			FileUtils.makeDirectoryIfItsNotThere(rootPath + mobicartImagesFolderPath);

			String appFolderPath = "/" + app.getId().toString();

			if (iconImageFile.getSize() > 0) {

				if (!iconImageFile.getContentType().contains("image")) {
					modelMap.put("error", "Please upload valid icon image file");
					logger.error("error in upload valid icon image file");
					return APP_CREATE_VITALS_VIEW_KEY;
				}

				String iconFolderPath = "/icon";
				FileUtils.makeDirectoryIfItsNotThere(rootPath + mobicartImagesFolderPath + iconFolderPath);

				FileUtils.makeDirectoryIfItsNotThere(rootPath + mobicartImagesFolderPath + iconFolderPath + appFolderPath);

				String iconFileName=FileUtils.cleanSpecialChars(iconImageFile.getOriginalFilename());
				 
				String pathToSaveIcons=rootPath+mobicartImagesFolderPath + iconFolderPath + appFolderPath + "/";
				
				String finalIconImagePath = mobicartImagesFolderPath + iconFolderPath + appFolderPath + "/" + FileUtils.stuffedFilename(iconFileName,"");;
				String finalIconImage57Path = mobicartImagesFolderPath + iconFolderPath + appFolderPath + "/" +FileUtils.stuffedFilename(iconFileName,"_icon");
				String finalIconImage72x72Path=mobicartImagesFolderPath + iconFolderPath + appFolderPath + "/" +FileUtils.stuffedFilename(iconFileName,"_72x72");	
				
				InputStream iconInputStream = null;
				iconInputStream = iconImageFile.getInputStream();
				BufferedImage bufferedIconImage = ImageIO.read(iconInputStream);
				boolean validImage = true;
				if (bufferedIconImage.getHeight() < 72) {
					validImage = false;
				}
				if (bufferedIconImage.getWidth() < 72) {
					validImage = false;
				}
				if (!validImage) {
					iconInputStream.close();
					modelMap.put("error", "Icon Image must be more than 512 x 512");
					logger.error("icon image must be more than 512 x 512");
					return APP_CREATE_VITALS_VIEW_KEY;
				}
				/*
				MagicalPower magicalPower=new MagicalPower();
				//perserv old names as they are
				magicalPower.resizeAndSave(bufferedIconImage,rootPath+ finalIconImagePath,new ImageSize(Constants.APP_ICON_512_WIDTH, Constants.APP_ICON_512_HEIGHT));
				//magicalPower.resizeAndSave(bufferedIconImage,rootPath+ finalIconImage57Path,new ImageSize(Constants.APP_ICON_57_WIDTH, Constants.APP_ICON_57_HEIGHT));
				//magicalPower.resizeAndSave(bufferedIconImage,rootPath+ finalIconImage72x72Path,new ImageSize(Constants.APP_ICON_IPAD_WIDTH, Constants.APP_ICON_IPAD_HEIGHT));
				//save images according to new excel sheet
				magicalPower.resizeAndSave(bufferedIconImage, pathToSaveIcons,iconFileName ,Constants.APP_ICON_ANDROID3_KEY);
				magicalPower.resizeAndSave(bufferedIconImage, pathToSaveIcons,iconFileName ,Constants.APP_ICON_ANDROID4_KEY);
				magicalPower.resizeAndSave(bufferedIconImage, pathToSaveIcons,iconFileName ,Constants.APP_ICON_ANDROID6_KEY);
				magicalPower.resizeAndSave(bufferedIconImage, pathToSaveIcons,iconFileName ,Constants.APP_ICON_IPAD_KEY);
				magicalPower.resizeAndSave(bufferedIconImage, pathToSaveIcons,iconFileName ,Constants.APP_ICON_IPHONE_KEY);
				iconInputStream.close();
				*/
				
				//logger.debug("\n in imgg:"+iconImageFile.getInputStream()+" \n pathToSaveIcons+iconFileName:"+pathToSaveIcons+iconFileName);
				String tempFile=ImgMagicCmdExcecutor.saveTempFile(iconImageFile.getInputStream(), pathToSaveIcons+iconFileName);
				ImgMagicCmdExcecutor.resizeAndSaveFile(tempFile, pathToSaveIcons,iconFileName ,Constants.APP_ICON_ANDROID3_KEY,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(tempFile, pathToSaveIcons,iconFileName ,Constants.APP_ICON_ANDROID4_KEY,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(tempFile, pathToSaveIcons,iconFileName ,Constants.APP_ICON_ANDROID6_KEY,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(tempFile, pathToSaveIcons,iconFileName ,Constants.APP_ICON_IPAD_KEY,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(tempFile, pathToSaveIcons,iconFileName ,Constants.APP_ICON_IPHONE_KEY,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(tempFile, pathToSaveIcons,iconFileName ,Constants.APP_ICON_IPHONE4_KEY,true);
				iconInputStream.close();
				 
				
				
				app.setsIconImage("/" + finalIconImagePath);
			}

			if (loaderImageFile.getSize() > 0) {

				if (!loaderImageFile.getContentType().contains("image")) {
					modelMap.put("error", "Please upload valid icon image file");
					return APP_CREATE_VITALS_VIEW_KEY;
				}

				String loaderFolderPath = "/loader";
				FileUtils.makeDirectoryIfItsNotThere(rootPath + mobicartImagesFolderPath + loaderFolderPath);
				FileUtils.makeDirectoryIfItsNotThere(rootPath + mobicartImagesFolderPath + loaderFolderPath + appFolderPath);

				String pathToSaveLoaders= rootPath+ mobicartImagesFolderPath + loaderFolderPath + appFolderPath + "/";
				
				String loaderFileName=FileUtils.cleanSpecialChars(loaderImageFile.getOriginalFilename());
				loaderFileName=FileUtils.stuffedFilename(loaderFileName,"");
				String finalLoaderImagePath = mobicartImagesFolderPath + loaderFolderPath + appFolderPath + "/" + loaderFileName;
				String finalLoaderImageIpadPath = mobicartImagesFolderPath + loaderFolderPath + appFolderPath + "/" + FileUtils.stuffedFilename(loaderFileName,"_768x1024");

				
				 /*
				InputStream loaderInputStream = null;
				loaderInputStream = loaderImageFile.getInputStream();
				BufferedImage loaderImage=ImageIO.read(loaderImageFile.getInputStream());
               
				MagicalPower magicalPower=new MagicalPower();
				//old images 
				magicalPower.resizeAndSave(loaderImage, rootPath+finalLoaderImagePath ,ImageSize.parse(ResourceProperties.getImageString(Constants.APP_LOADER_IPHONE_KEY)));
				magicalPower.resizeAndSave(loaderImage, rootPath+finalLoaderImageIpadPath ,ImageSize.parse(ResourceProperties.getImageString(Constants.APP_LOADER_IPAD_KEY)));
				//news images according to given image matrix 
				magicalPower.resizeAndSave(loaderImage, pathToSaveLoaders,loaderFileName ,Constants.APP_LOADER_ANDROID3_KEY);
				magicalPower.resizeAndSave(loaderImage, pathToSaveLoaders,loaderFileName ,Constants.APP_LOADER_ANDROID4_KEY);
				magicalPower.resizeAndSave(loaderImage, pathToSaveLoaders,loaderFileName ,Constants.APP_LOADER_ANDROID6_KEY);
				magicalPower.resizeAndSave(loaderImage, pathToSaveLoaders,loaderFileName ,Constants.APP_LOADER_IPAD_KEY);
				magicalPower.resizeAndSave(loaderImage, pathToSaveLoaders,loaderFileName ,Constants.APP_LOADER_IPHONE_KEY);
				loaderInputStream.close();
				*/
			    //logger.debug("pathToSaveLoaders:-"+pathToSaveLoaders);
				String tempFile=ImgMagicCmdExcecutor.saveTempFile(loaderImageFile.getInputStream(), rootPath+finalLoaderImagePath);
				ImgMagicCmdExcecutor.resizeAndSaveFile(tempFile, pathToSaveLoaders,loaderFileName ,Constants.APP_ICON_ANDROID3_KEY,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(tempFile, pathToSaveLoaders,loaderFileName ,Constants.APP_LOADER_ANDROID3_KEY,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(tempFile, pathToSaveLoaders,loaderFileName ,Constants.APP_LOADER_ANDROID4_KEY,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(tempFile, pathToSaveLoaders,loaderFileName ,Constants.APP_LOADER_ANDROID6_KEY,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(tempFile, pathToSaveLoaders,loaderFileName ,Constants.APP_LOADER_IPAD_KEY,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(tempFile, pathToSaveLoaders,loaderFileName ,Constants.APP_LOADER_IPHONE_KEY,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(tempFile, pathToSaveLoaders,loaderFileName ,Constants.APP_LOADER_IPHONE4_KEY,true);
				 
				 
				app.setsLoaderImage("/" + finalLoaderImagePath);
			}
			app = appService.saveAppVitals(app);

		} catch (Exception e) {
			logger.error("ResubmitAppVitals  error : {} " ,e); 
			ObjectError error = new ObjectError("error",
					"Some error occured : " + e.getLocalizedMessage());
			result.addError(error);

			return APP_CREATE_VITALS_VIEW_KEY;
		}

		List<Territory> territories = appService.findAllTerritories();
		List<Territory> appTerritories = appService.findAppTerritories(app.getId());

		modelMap.put("territories", territories);
		modelMap.put("appTerritories", appTerritories);

		modelMap.put("success", "Your settings have been updated successfully.");
		logger.info("settings have been updated successfully.");
		return APP_CREATE_VITALS_VIEW_KEY;
	}


	/**
	 * Show submit {@link App}
	 * @param app {@link App}
	 * @param paymentForm {@link PaymentForm}
	 * @param result {@link BindingResult}
	 * @param modelMap {@link ModelMap}
	 * @return path to views/app/submit.jsp
	 */
	@RequestMapping(value = "submit", method = RequestMethod.GET)
	public String showSubmitApp(@ModelAttribute("app") App app, @ModelAttribute PaymentForm paymentForm,
			
			BindingResult result, ModelMap modelMap) {
		if (app.getbSubmissionToApple() == null) {
			app.setbSubmissionToApple(true);
		}
		return APP_SUBMIT_APP_VIEW_KEY;
	}



	/**
	 * Show submit {@link App} license
	 * @param app {@link App}
	 * @param result {@link BindingResult}
	 * @param paymentForm {@link PaymentForm}
	 * @param modelMap {@link ModelMap}
	 * @return path to views/app/submit.jsp
	 */
	@RequestMapping(value = "submit/license", method = RequestMethod.POST)
	public String showSubmitAppLicense(@ModelAttribute("app") App app,
			BindingResult result, @ModelAttribute PaymentForm paymentForm, ModelMap modelMap,
			@RequestParam(value = "iphoneapp", required = false) String iphoneapp,
			@RequestParam(value = "ipadapp", required = false) String ipadapp,
			@RequestParam(value = "android", required = false) String android,
			@RequestParam(value = "paypallivemode", required = false,defaultValue="true") boolean  paypalLiveMode
			
	   ) {
		//select the license tab,iphoneapp,ipadapp,android
		modelMap.put("iphoneapp",iphoneapp);
		modelMap.put("ipadapp",ipadapp);
		modelMap.put("android",android);
		modelMap.put("paypallivemode",paypalLiveMode);
		boolean invalid=false;
		//update from database fields
		try {
			app=appService.findAppById(app.getId());
		} catch (SQLException e) {
			logger.error(" exxception in find app by id {} ",e);
		}
		List<String> mandFields=new ArrayList<String>(); 

		if(app.getsName()==null){
			mandFields.add("Application name");
			invalid=true;
		}

		if(app.getsName()!=null){
			if(app.getsName().length()==0){
				mandFields.add("Application name");
				invalid=true;
			}
		}

		/*if(app.getsIconImage()==null){
			mandFields.add("Icon");
			invalid=true;
		}
		if(app.getsLoaderImage()==null){
			mandFields.add("Loader image");
			invalid=true;
		}

		if(app.getTerritories().size()==0){
			mandFields.add("App territories");
			invalid=true;
		}*/
		String message="App vitals "+mandFields+" has to be entered to submit the app.";
		if(invalid){
			modelMap.put("error",message);
			modelMap.put("tabSelect", 0);
			return APP_SUBMIT_APP_VIEW_KEY;
		}else{
			modelMap.put("tabSelect", 1);
			return APP_SUBMIT_APP_VIEW_KEY;
		}
	}


	/**
	 * Show submit {@link App} license
	 * @param app {@link App}
	 * @param result {@link BindingResult}
	 * @param paymentForm {@link PaymentForm}
	 * @param modelMap {@link ModelMap}
	 * @return path to views/app/submit.jsp
	 */
	@RequestMapping(value = "/save/androidappsettings", method = RequestMethod.POST)
	public String saveAndroidAppSettings(ModelMap modelMap,HttpServletRequest request,
			@RequestParam(value = "appId", required = false)  Long  appId,
			@RequestParam(value = "registeredAndroidUserEmail", required = false)  String  registeredAndroidUserEmail,
			@RequestParam(value = "registeredAndroidUserPass", required = false)  String  registeredAndroidUserPass
			
	   ) {
		 
		
		modelMap.put("tabSelect", 0);

		Store store = (Store) request.getSession().getAttribute("store");
		User user = (User) request.getSession().getAttribute("user");

		try{
			App app=appService.findAppByUser(user);
			
			
			
			
			if(registeredAndroidUserEmail!=null && registeredAndroidUserPass!=null){
				app.setRegisteredAndroidUserEmail(registeredAndroidUserEmail);
				app.setRegisteredAndroidUserPass(registeredAndroidUserPass);
				app=appService.saveAppVitals(app);
			}
			
			

			List<News> newsItems = storeService.findNewsItemsByStore(store.getId());
			List <PushNotification> notification= appService.getNotificationsByApp(app.getId());

			Boolean bShowNewsTable = false;
			News feedItem = null;
			News twitterItem = null;
			for (Iterator<News> i = newsItems.iterator(); i.hasNext();) {
				News item = i.next();
				if (item.getsType().equals("custom")) {
					bShowNewsTable = true;
				} else if (item.getsType().equals("feed")) {
					feedItem = item;
					i.remove();
				} else if (item.getsType().equals("twitter")) {
					twitterItem = item;
					i.remove();
				}
			}
			modelMap.put("newsItems", newsItems);
			modelMap.put("bShowNewsTable", bShowNewsTable);
			modelMap.put("feedItem", feedItem);
			modelMap.put("twitterItem", twitterItem);
			modelMap.put("notifications", notification);
			
		   String crtificateFileName=app.getsPnCertificatePath();
		   if(crtificateFileName!=null){
		   crtificateFileName=crtificateFileName.substring(crtificateFileName.lastIndexOf("/")+1);
		         
		        }
		   
			
			modelMap.put("crtificateFileName",crtificateFileName);

			modelMap.put("news", new News());
			modelMap.put("androidpAppPackage",UserBO.androidAppPackageName(app.getsName(), app.getUserId()));
			modelMap.put("pushNotification", new PushNotification());
			modelMap.put("app",app);
			
			
		}catch(Exception e){
			logger.error("showNewsHome",e);
		}
		return "news/home";
		
		}
 

	
	
	/**
	 * view submit {@link App} 
	 * @param app {@link App} 
	 * @param result {@link BindingResult}
	 * @param paymentForm {@link PaymentForm}
	 * @param modelMap {@link ModelMap}
	 * @param request {@link HttpServletRequest}
	 * @return path to views/app/submit.jsp
	 */
	@RequestMapping(value = "submit/final", method = RequestMethod.POST)
	public String showSubmitAppThanks(@ModelAttribute("app") @Valid App app,
			BindingResult result, @ModelAttribute PaymentForm paymentForm, 
			ModelMap modelMap,HttpServletRequest request,
			@RequestParam(value = "iphoneapp", required = false) String iphoneapp,
			@RequestParam(value = "ipadapp", required = false) String ipadapp,
			@RequestParam(value = "android", required = false) String android,
			@RequestParam(value = "paypallivemode", required = false,defaultValue="true") boolean  paypalLiveMode
				
	) {
		modelMap.put("iphoneapp",iphoneapp);
		modelMap.put("ipadapp",ipadapp);
		modelMap.put("android",android);
		modelMap.put("paypallivemode",paypalLiveMode);
		
		User user = (User) request.getSession().getAttribute("user");
		StringBuffer fileLink=new StringBuffer("");
		String applicationUrl="http://"+request.getServerName()+":"+request.getServerPort();
		
		
		/*
		 * compile android files */
		if(android!=null)
		{
		try{
		  App appvital=appService.findAppById(app.getId());
		  Store store=storeService.findStoreByUserId(user.getId());
		  Double oldversion=appvital.getVersion();
		  Double newversion=app.getVersion();
		  if(newversion>oldversion){
	      appService.updateAppVersion(app);
	      appvital.setVersion(newversion);
		  }else{
			  
			  app.setVersion(oldversion);
		  }
		  
		String address=ResourceProperties.getString("android.mobicart");
		String use_sh_file=ResourceProperties.getString("sh");
		/*String finalApkName=request.getParameter("apkname");
		String username=request.getParameter("username");
		String userid=request.getParameter("user_id");
		String apikey=request.getParameter("api_key");
		String payPalEmail=request.getParameter("pay_pal_email");*/
		
		String authkey=api.getAuthSecretByUserId(user.getId());
		
		//String apkFileName=appvital.getsName()+"-v"+appvital.getVersion();
		//String apkFilePackegeName=apkFileName.replace(" ", "_").replace(".", "_").replace("-", "_")+user.getId();
		
		Properties argList=new Properties();
		argList.put("username", user.getUsername());
		argList.put("user_id", user.getId());
		argList.put("api_key", authkey+"");
		argList.put("pay_pal_email", user.getsPaypalAddress()+"");
		argList.put("pay_pal_token", store.getsPaypalToken()+"");
		argList.put("notification_sender_id",appvital.getRegisteredAndroidUserEmail());
		argList.put("apkname", appvital.getsName().replace(" ", "")+"-v"+appvital.getVersion());
		argList.put("packageName", UserBO.androidAppPackageName(appvital.getsName(), user.getId()));
		argList.put("iconimg", applicationUrl+"/"+appvital.getsIconImage());
		argList.put("loaderimg", applicationUrl+"/"+appvital.getsLoaderImage());
		argList.put("version", appvital.getVersion().toString());
		argList.put("live_paypal_mode", paypalLiveMode+"");
		argList.put("sh", use_sh_file.equals("on")?use_sh_file:"");
		 
	   
		 /*encode the argList*/
		String data="";
     	 Enumeration enuma=argList.keys();
     		while(enuma.hasMoreElements()){
     			Object key= enuma.nextElement();
     			data=data+URLEncoder.encode(key.toString(), "UTF-8")+"="+URLEncoder.encode(argList.get(key).toString(), "UTF-8")+"&";
     		}
		
		
		/*String data="username="+user.getUsername()
				    +"&user_id="+user.getId()
				    +"&api_key="+authkey
				    +"&pay_pal_email="+user.getsPaypalAddress()
				    +"&pay_pal_token="+store.getsPaypalToken()
				    +"&apkname="+appvital.getsName()+"-v"+appvital.getVersion()
				    +"&packageName="+UserBO.androidAppPackageName(appvital.getsName(), user.getId())
					+"&iconimg="+URLEncoder.encode(applicationUrl+"/"+appvital.getsIconImage(), "UTF-8")
					+"&loaderimg="+URLEncoder.encode(applicationUrl+"/"+appvital.getsLoaderImage(), "UTF-8")
					+"&version="+URLEncoder.encode(appvital.getVersion().toString(), "UTF-8")
					+"&live_paypal_mode="+URLEncoder.encode(paypalLiveMode+"", "UTF-8");
		
		
		
		data=(use_sh_file.equals("on")?"sh="+use_sh_file+"&":"")+data;*/
				 
		    URL page = new URL(address+"?"+data);
		    StringBuffer text = new StringBuffer("");
		    HttpURLConnection conn = (HttpURLConnection) page.openConnection();
		    conn.setRequestMethod("GET");
		    conn.connect();
		    InputStreamReader in = new InputStreamReader((InputStream) conn.getContent()); 
		    BufferedReader buff = new BufferedReader(in);
		    String line = buff.readLine();
		    while (line != null) {
		        text.append(line + "\n");
		        line = buff.readLine();
		    } 
		    System.out.println(text);
		    fileLink=text;
		}
		catch(Exception e){
			logger.debug("Error has occured",e);
		     fileLink.append("#");
			
		}
		}
		/*obtain constant from property file*/
		/*
    this.argList[0]=key_file_path;
	this.argList[1]=project_bin_directory;
	this.argList[2]=name_of_release_unsigned_apk;
	this.argList[3]=alias_name_of_keystore;
	this.argList[4]=final_Apk_relased_filename_apk;
		 */ 
		 
		
		boolean isMultipleLink=false;
		
		if(iphoneapp!=null){
			if(ipadapp!=null){
				isMultipleLink=true;
			}
			else{
				if(android!=null)
					isMultipleLink=true;
			}
		}
		else{
			
			if(ipadapp!=null){
				if(android!=null)
					isMultipleLink=true;
			}
			 
			
		}
		
		modelMap.put("isMultipleLink",isMultipleLink);
		
		/*String msg=user.getDefaultLabelKeyValuesMap().get("key.application.submitapp.congrats.msg");
		if(isMultipleLink){
			msg=msg.replace("app", "apps");	
			user.getDefaultLabelKeyValuesMap().put("key.application.submitapp.congrats.msg", msg);
		}*/
		
		
		if (paymentForm.getMobicartTerms()==null) {
			try{
				result.reject("NotEmpty.app.mobicart_terms", labelService.getKeyValueByMerchantID("key.NotEmpty.app.mobicart_terms", user.getId()));
			} catch (SQLException e) {
				logger.error(" exxception in find app by id {} ",e);
			}
		}
		if (result.hasErrors()) {
			modelMap.put("tabSelect", 1);
			String message = "You must agree to MobiCart terms and conditions";
			modelMap.put("error", message);
			return APP_SUBMIT_APP_VIEW_KEY;

		}else{
			//select the final tab
			if(logger.isDebugEnabled())logger.debug("pre payment notification");

			SiteConstant siteConstant=adminService.findSiteConstants();
			//User user=(User) request.getSession().getAttribute("user");
			if (app.getbSubmissionToApple()) {
				
				if(user !=null){
				Store store=(Store) request.getSession().getAttribute("store");
				if(logger.isDebugEnabled())logger.debug(user.getUsername()+" opted for Paid App : MobiCart will submit this application to Apple");
				if(logger.isDebugEnabled())logger.debug("App Id   	:"+app.getId());	
				if(logger.isDebugEnabled())logger.debug("App Name 	:"+app.getsName());	
				if(logger.isDebugEnabled())logger.debug("Store Id	:"+store.getId());	
				if(logger.isDebugEnabled())logger.debug("Store Name :"+store.getsSName());
				if(logger.isDebugEnabled())logger.debug("------------------------------");
				}
				
				if (paymentForm.getAppleTerms()==null) {
					result.reject("NotEmpty.app.apple_terms", "You must agree to Apple terms and conditions");
				}
				if (result.hasErrors()) {
					modelMap.put("tabSelect", 1);
					String message = "You must agree to Apple terms and conditions";
					modelMap.put("error", message);
					logger.error(message);
					return APP_SUBMIT_APP_VIEW_KEY;
				}else{

					modelMap.put("tabSelect", 2);
					//show payment form
					modelMap.put("thanks", "none");
					modelMap.put("payment", "block");
					modelMap.put("paymentThanks", "none");

					BigDecimal totalAmountToPay=siteConstant.getfAppSubmissionFee();

					paymentForm.setPpCountry("US");
					paymentForm.setPpAmount(totalAmountToPay.toString());

					//BigDecimal recurringAmountToPay=siteConstant.getfAppSubmissionRecurringFee();
					//paymentForm.setPpRecurringAmount(recurringAmountToPay.toString());
					
					String userName = user.getUsername();
					paymentForm.setPpEmail(userName);
					List<Territory> countries = appService.findAllTerritories();
					modelMap.put("countries", countries);
					modelMap.put("years", DateTimeUtils.getComingYears());
					modelMap.put("months", DateTimeUtils.getAllMonths());

					String taxPercentage=""+siteConstant.getfEuVat();
					modelMap.put("taxPercentage",taxPercentage);

					modelMap.put("paymentForm",paymentForm);

				}

			} else {

				if(user !=null)
					if(logger.isDebugEnabled())logger.debug(user.getUsername()+" opted for FREE App  to submit this application to Apple himself");
				
				String billingType = "submission";
				try{
				List<Billing> billings = userService.findBillingDetails(app
						.getUserId());
				if (billings.size() > 0) {
					billingType = "resubmission";
				}
				}catch (Exception e) {
					logger.info("fetching billing details ",e);
				}

				boolean error=false;
				try{
					Billing billing = new Billing();
					billing.setAppId(app.getId());
					billing.setUserId(app.getUserId());
					billing.setdBillingDate(DateTimeUtils.getCurrentTimestamp());
					billing.setfAmount(new BigDecimal(0));
					billing.setsType(billingType);
					appService.makeBillingTransaction(billing);

					logger.info("billing trasaction saved successfully");

					Order order = new Order();
					order.setAppId(app.getId());
					order.setdOrderDate(DateTimeUtils.getCurrentTimestamp());
					order.setfAmount(new BigDecimal(0));
					order.setUserId(user.getId());
					try {
						Store store = storeService.findStoreByUserId(user.getId());
						order.setStoreId(store.getId());
					} catch (Exception e) {
						logger.error("Error in finding store by user id or store is null {} ",e);
					}

					order.setsOrderStatus(Constants.ORDER_STATUS_PROCESSING);
					order.setsPaymentStatus(Constants.ORDER_STATUS_COMPLETED);
					appService.makeAppOrderTransaction(order);
					logger.info("order saved successfully");
				}catch(Exception e){
					logger.error(e.getLocalizedMessage());
					error=true;
					modelMap.put("tabSelect", 1);
					String message = "Some error occured in app submission";
					modelMap.put("error", message);
					return APP_SUBMIT_APP_VIEW_KEY;

				}
				
	 
				if(!error){
					PathLocator path = new PathLocator();

					// send mail to free merchant for iphone
					EmailGenerator emailToMerchent = new EmailGenerator();
					emailToMerchent.setFromEmail(ResourceProperties.getString("adminEmail"));
					emailToMerchent.setToEmail(user.getUsername());
					emailToMerchent.setSubject("MobiCart - IOS App submission");
					emailToMerchent.setTemplateName(path.getRealPath() + "emailTemplate/APP_SUBMISSION_TO_FREE_MERCHANT");
					
					
					
					/*EmailGenerator emailToPaidMerchent = new EmailGenerator();
					emailToPaidMerchent.setFromEmail(ResourceProperties.getString("adminEmail"));
					emailToPaidMerchent.setToEmail(user.getUsername());
					emailToPaidMerchent.setSubject("MobiCart - App submission");
					emailToPaidMerchent.setTemplateName(path.getRealPath() + "emailTemplate/APP_SUBMISSION_TO_STARTER_PLAN");*/
					
					
					
					
					EmailGenerator emailToMerchentHavingAdrodOnly = new EmailGenerator();
					emailToMerchentHavingAdrodOnly.setFromEmail(ResourceProperties.getString("adminEmail"));
					emailToMerchentHavingAdrodOnly.setToEmail(user.getUsername());
					emailToMerchentHavingAdrodOnly.setSubject("MobiCart - Android App submission");
					emailToMerchentHavingAdrodOnly.setTemplateName(path.getRealPath() + "emailTemplate/APP_SUBMISSION_TO_PAID_MERCHANT_FOR_ANDROID");
					String consumerSecret=null;
					try {
						consumerSecret=storeService.generateOauthSecret(user);
					} catch (ConsumerSecretGenerationException e1) {
						logger.warn("could not genrate oauth secret",e1);
					}
					

					String name= user.getsFirstName()!=null?user.getsFirstName():"" +
							user.getsLastName()!=null? " "+ user.getsLastName():" ";

							HashMap<String, String> param = new HashMap<String, String>(); 		
							param.put("_NAME_",name);
							param.put("_BASEPATH_", ResourceProperties.getString("appUrl"));
							
							
							emailToMerchent.setParam(param);
							//emailToPaidMerchent.setParam(param);
							emailToMerchentHavingAdrodOnly.setParam(param);
						 
							 if("android".equals(android)&& ("ipad".equals(ipadapp) || "iphone".equals(iphoneapp)))
								{
								 //sender.sendMail(emailToPaidMerchent);
								 sender.sendMail(emailToMerchent);
								 sender.sendMail(emailToMerchentHavingAdrodOnly);
								 
								}
							else if("android".equals(android)&& ipadapp==null && iphoneapp==null)
								sender.sendMail(emailToMerchentHavingAdrodOnly);
							else 
								sender.sendMail(emailToMerchent);
								
							 
								 
							
							
							if(logger.isDebugEnabled())logger.debug("email sent to merchant");
							
							
							EmailGenerator emailToAdmin = new EmailGenerator();
							emailToAdmin.setFromEmail(ResourceProperties.getString("adminEmail"));
							emailToAdmin.setSubject("FREE APP SUBMISSION");
							emailToAdmin.setTemplateName(path.getRealPath() + "emailTemplate/FREE_APP_SUBMISSION_TO_ADMIN");
							emailToAdmin.setToEmail(ResourceProperties.getString("adminEmail"));

							Store store=null;
							try {
								store = storeService.findStoreByUserId(user.getId());
							} catch (Exception e) {
								logger.error("find excpetion in store {} ",e);
							}
							param.put("_MERCHANTNAME_",name);
							param.put("_DEVEMAIL_",ResourceProperties.getString("devEmail"));
							param.put("_APPID_",app.getId().toString());
							param.put("_BASEPATH_", ResourceProperties.getString("appUrl"));
							param.put("_MERCHANTNAME_",name);
							param.put("_MERCHANTEMAIL_",user.getUsername());
							param.put("_APPID_",app.getId().toString());
							param.put("_APPNAME_",app.getsName());
							String storeId=store!=null?store.getId().toString():"0";
							param.put("_STOREID_",storeId);
							String iconImage512Path=app.getsIconImage()!=null?app.getsIconImage():Constants.DEFAULT_APP_ICON_512_PATH; 
							String iconImage57Path=app.getsIconImage57()!=null?app.getsIconImage57():Constants.DEFAULT_APP_ICON_57_PATH; 
							String loaderImagePath=app.getsLoaderImage()!=null?app.getsLoaderImage():Constants.DEFAULT_APP_LOADER_PATH; 
							param.put("_ICONPATH512_",ResourceProperties.getString("appUrl")+iconImage512Path);
							param.put("_ICONPATH57_",ResourceProperties.getString("appUrl")+iconImage57Path);
							param.put("_LOADERPATH_",ResourceProperties.getString("appUrl")+loaderImagePath);
							param.put("_BASEPATH_", ResourceProperties.getString("appUrl"));
							if(consumerSecret!=null){		
								param.put("_CONSUMERKEY_", user.getUsername());
								param.put("_CONSUMERSECRET_", consumerSecret);
								}
							emailToAdmin.setParam(param);
							sender.sendMail(emailToAdmin);
							logger.info("email sent to admin");
						

				}

				modelMap.put("thanks", "block");
				modelMap.put("payment", "none");
				modelMap.put("paymentThanks", "none");
				modelMap.put("tabSelect", 2);
				modelMap.put("androidapklink", fileLink.toString());
			 

			}

		}

		appService.saveAppSubmitPref(app);

		return APP_SUBMIT_APP_VIEW_KEY;
	}



	



	/**
	 * Paypal Return url
	 * 
	 * @param result {@link BindingResult}
	 * @param request {@link HttpServletRequest}
	 * @param modelMap {@link ModelMap}
	 * @return path to views/app/submit.jsp
	 * @throws Exception 
	 */
	@RequestMapping("payment/return")
	public String showPaypalReturnPost(ModelMap modelMap, HttpServletRequest request) throws Exception {
    
		BigDecimal paymentGross=BigDecimal.valueOf(0L);
		String userAppPair=request.getParameter("cm");
		if(userAppPair==null){
			userAppPair=request.getParameter("custom");
		}
		try{
			
			try{
				paymentGross=BigDecimal.valueOf(Double.valueOf(request.getParameter("payment_gross")));
			}catch(Exception e){
				paymentGross=null;
				logger.error(e.getLocalizedMessage());
			}
			if(paymentGross==null){
				paymentGross=BigDecimal.valueOf(Double.valueOf(request.getParameter("amt")));
			}
		}catch(Exception e){
			logger.error(e.getLocalizedMessage());
		}
		String userAppPairs[]=userAppPair.split(",");
		String encAmount=userAppPairs[2];


		DesEncrypter encrypter=new DesEncrypter("pay-mobi-cart");
		encAmount=encrypter.decrypt(encAmount);

		logger.info("encAmount is "+encAmount +"  paymentGross is "+paymentGross);
		Double encAmt = Double.parseDouble(encAmount);
		if(String.valueOf(paymentGross).equalsIgnoreCase(encAmount)){
		//if(paymentGross.doubleValue() == encAmt.doubleValue()){
			modelMap.put("tabSelect", 2);
			modelMap.put("paymentForm", new PaymentForm());
			modelMap.put("success","Payment has been sent");
			modelMap.put("thanks", "none");
			modelMap.put("payment", "none");
			modelMap.put("paymentTinkered", "none");
			modelMap.put("paymentThanks", "none");
			modelMap.put("paymentSent", "block");
			logger.info("payment done");
		}else{
			logger.info("payment tinkered");
			modelMap.put("tabSelect", 2);
			modelMap.put("paymentForm", new PaymentForm());
			modelMap.put("error","Payment failed");
			modelMap.put("thanks", "none");
			modelMap.put("payment", "none");
			modelMap.put("paymentTinkered", "block");
			modelMap.put("paymentThanks", "none");
			modelMap.put("paymentSent", "none");
		}
		return APP_SUBMIT_APP_VIEW_KEY;
	}


	/**
	 * Deal with payment notification
	 * @param modelMap
	 * @param request
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping("/payment/notification")
	public @ResponseBody String showPaypalNotifyPost(ModelMap modelMap,HttpServletRequest request) throws Exception {
		
		String message="Success";
		logger.info("paypal notify hit");

		try{
			BigDecimal paymentGross=BigDecimal.valueOf(0L);
			BigDecimal paymentFee=BigDecimal.valueOf(0L);

			String paymentStatus=request.getParameter("payment_status");
			String payerId=request.getParameter("payer_id");
			String userAppPair=request.getParameter("custom");
			String payerStatus=request.getParameter("payer_status");

			String paymentDate=request.getParameter("payment_date");
			String txnId=request.getParameter("txn_id");
			String receiptId=request.getParameter("receipt_id");

			String pendingReason=request.getParameter("pending_reason");

			try{
				paymentGross=BigDecimal.valueOf(Double.valueOf(request.getParameter("payment_gross")));
			}catch(Exception e){
				logger.error("gross payment",e);
			}
			try{
				paymentFee=BigDecimal.valueOf(Double.valueOf(request.getParameter("payment_fee")));
			}catch(Exception e){
				logger.error("payment fee",e);
			}
			
			logger.info("payer_status:" + payerStatus);
			logger.info("payment_status:" + paymentStatus);
			logger.info("custom:" + userAppPair);
			logger.info("payment_gross:" + paymentGross);
			logger.info("payment_date:" + paymentDate);
			logger.info("txn_id:" + txnId);


			BigDecimal totalAmount = paymentGross;
			String[] userApp = userAppPair.split(",");
			String username = userApp[0];
			Long appId = Long.parseLong(userApp[1]);
			String actualAmount=String.valueOf(userApp[2]);//this is the encrypted amount to prevent user tinkering with amount with firebug on web
	
			// This date we used to distinguish between recurring amount and initial amount gap.
			
			//String subscriptionDateString=String.valueOf(userApp[3]); 

			//SiteConstant siteConstant=adminService.findSiteConstants();

			//DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
			//DateTime subscriptionDate = formatter.parseDateTime(subscriptionDateString);
			
			//DateTime currentDate=new DateTime();
			//if(currentDate.equals(subscriptionDate)){
				DesEncrypter encrypter=new DesEncrypter("pay-mobi-cart");
				actualAmount=encrypter.decrypt(actualAmount);
			//}else{
				//BigDecimal recurringAmountForApp=siteConstant.getfAppSubmissionRecurringFee();
				//actualAmount= recurringAmountForApp.toString();
			//}
			
			if(paymentGross.compareTo(new BigDecimal(actualAmount))==0){
				//if payment not tinkered
				boolean oldOrder=false;

				User user = userService.findByEmail(username);
				App app = appService.findAppById(appId);        
                if(!(payerStatus==null ||"".equals(payerStatus)) && !(paymentStatus==null || "".equals(paymentStatus))){
			//	if(!paymentStatus.equalsIgnoreCase("Pending")){
	            if(payerStatus.equalsIgnoreCase("verified") && paymentStatus.equalsIgnoreCase("completed")){

					Order order=adminService.findAppOrderByTransactionId(txnId);
					if(order!=null){
						logger.info("post payment notification");
						logger.info("payment details are : UserName : "+username+" App ID :"+appId+" payer_status: " + payerStatus+" payment status: " + paymentStatus+" transaction id:" + txnId);
						return "duplicate hit, order already exists";
						//return "success";
					}

					
				}


				if(!oldOrder){
					
					logger.info("post payment notification");
					logger.info("making new order entry");
					logger.info("payment details are : UserName : "+username+" App ID :"+appId+" payer_status: " + payerStatus+" payment status: " + paymentStatus+" transaction id:" + txnId);


					Order order= new Order();
					order.setAppId(app.getId());
					order.setdOrderDate(DateTimeUtils.getCurrentTimestamp());
					order.setfAmount(paymentGross);
					order.setUserId(user.getId());
					try {
						Store store = storeService.findStoreByUserId(user.getId());
						order.setStoreId(store.getId());
					} catch (Exception e) {
						logger.error("Unable to find store",e);
					}
					order.setsOrderStatus(Constants.ORDER_STATUS_PROCESSING);
					String sPaymentStatus=Constants.ORDER_STATUS_PROCESSING;
					if(paymentStatus.equalsIgnoreCase("Pending")){
						sPaymentStatus=Constants.ORDER_STATUS_PENDING;
					}else if(paymentStatus.equalsIgnoreCase("Completed")){
						sPaymentStatus=Constants.ORDER_STATUS_COMPLETED;
					}else if(paymentStatus.equalsIgnoreCase("Reversed")){
						sPaymentStatus=Constants.ORDER_STATUS_CANCEL;
					}
					order.setfPaymentFee(paymentFee);
					order.setsPaymentStatus(sPaymentStatus);
					order.setsPayerStatus(payerStatus);
					order.setdPaymentDate(new Date());
					order.setsPaymentRemarks(pendingReason);
					order.setTxnId(txnId);
					order.setPayerId(payerId);
					order.setReceiptId(receiptId);
					order.setsAppIconImage(app.getsIconImage());
					order.setsAppLoaderImage(app.getsLoaderImage());
					order.setsAppName(app.getsName());
					order.setsAppTerritories(app.getAppTerritoryIds());
					order.setbAppSubmissionToApple(app.getbSubmissionToApple());

					appService.makeAppOrderTransaction(order);


					String billingType = "submission";
					List<Billing> billings = userService.findBillingDetails(app
							.getUserId());
					if (billings.size() > 0) {
						billingType = "resubmission";
					}
					Billing billing = new Billing();
					billing.setAppId(app.getId());
					billing.setUserId(app.getUserId());
					billing.setdBillingDate(DateTimeUtils.getCurrentTimestamp());
					billing.setfAmount(totalAmount);
					billing.setsType(billingType);
					appService.makeBillingTransaction(billing);

					logger.info("Billing transaction is done");
					try {
						String consumerSecret=null;
						try {
							consumerSecret=storeService.generateOauthSecret(user);
						} catch (ConsumerSecretGenerationException e1) {
							logger.error("could not genrate oauth secret",e1);
						}

						
						
						PathLocator path = new PathLocator();
						/**  mail to merchant**/
						EmailGenerator emailToMerchent = new EmailGenerator();
						emailToMerchent.setFromEmail(ResourceProperties
								.getString("adminEmail"));
						emailToMerchent.setToEmail(user.getUsername());
						emailToMerchent.setSubject("MobiCart App Store Submission");
						emailToMerchent.setTemplateName(path.getRealPath()
								+ "emailTemplate/APP_SUBMISSION_TO_PAID_MERCHANT");
						String name = user.getsFirstName() != null ? user
								.getsFirstName()
								: "" + user.getsLastName() != null ? " "
										+ user.getsLastName() : " ";

										HashMap<String, String> param = new HashMap<String, String>();
										param.put("_NAME_", name);
										param.put("_AMOUNT_", totalAmount.toString());
										param.put("_BASEPATH_", ResourceProperties.getString("appUrl"));
										emailToMerchent.setParam(param);
										sender.sendMail(emailToMerchent);
										logger.info("app submitted to merchant");

										/**  mail to super admin**/
										Store store = storeService.findStoreByUserId(user.getId());
										EmailGenerator emailToAdmin = new EmailGenerator();
										emailToAdmin.setFromEmail(ResourceProperties
												.getString("adminEmail"));
										emailToAdmin.setSubject("PAID APP SUBMISSION");
										emailToAdmin.setTemplateName(path.getRealPath()
												+ "emailTemplate/PAID_APP_SUBMISSION_TO_ADMIN_PROCESSING");
										emailToAdmin.setToEmail(ResourceProperties
												.getString("adminEmail"));
										emailToAdmin.setBccEmail(ResourceProperties
												.getString("bccEmail"));

										param.put("_MERCHANTNAME_", name);
										param.put("_APPID_", app.getId().toString());
										param.put("_APPNAME_", app.getsName());
										param.put("_AMOUNT_", totalAmount.toString());
										param.put("_DEVEMAIL_",
												ResourceProperties.getString("devEmail"));
										param.put("_MERCHANTEMAIL_", user.getUsername());
										param.put("_APPID_", app.getId().toString());
										param.put("_STOREID_", store.getId().toString());
										String iconImage512Path = app.getsIconImage() != null ? app
												.getsIconImage() : Constants.DEFAULT_APP_ICON_512_PATH;
												String iconImage57Path = app.getsIconImage57() != null ? app
														.getsIconImage57() : Constants.DEFAULT_APP_ICON_57_PATH;
														String loaderImagePath = app.getsLoaderImage() != null ? app
																.getsLoaderImage() : Constants.DEFAULT_APP_LOADER_PATH;
																param.put("_ICONPATH512_",
																		ResourceProperties.getString("appUrl")
																		+ iconImage512Path);
																param.put("_ICONPATH57_",
																		ResourceProperties.getString("appUrl")
																		+ iconImage57Path);
																param.put("_LOADERPATH_",
																		ResourceProperties.getString("appUrl")
																		+ loaderImagePath);

																param.put("_BASEPATH_", ResourceProperties.getString("appUrl"));

																emailToAdmin.setParam(param);
																sender.sendMail(emailToAdmin);

																logger.info("app submitted to admin");

																/**  mail to developer**/

																EmailGenerator emailToDeveloper = new EmailGenerator();
																emailToDeveloper.setFromEmail(ResourceProperties
																		.getString("adminEmail"));
																emailToDeveloper.setSubject("PAID APP SUBMISSION");
																emailToDeveloper.setTemplateName(path.getRealPath()
																		+ "emailTemplate/APP_SUBMISSION_PAID_TO_DEVELOPER");
																emailToDeveloper.setToEmail(ResourceProperties
																		.getString("devEmail"));

																param.put("_AMOUNT_", totalAmount.toString());
																param.put("_MERCHANTNAME_", name);
																param.put("_MERCHANTEMAIL_", user.getUsername());
																param.put("_APPID_", app.getId().toString());
																param.put("_APPNAME_", app.getsName());
																param.put("_STOREID_", store.getId().toString());
																iconImage512Path = app.getsIconImage() != null ? app
																		.getsIconImage() : Constants.DEFAULT_APP_ICON_512_PATH;
																		iconImage57Path = app.getsIconImage57() != null ? app
																				.getsIconImage57() : Constants.DEFAULT_APP_ICON_57_PATH;
																				loaderImagePath = app.getsLoaderImage() != null ? app
																						.getsLoaderImage() : Constants.DEFAULT_APP_LOADER_PATH;
																						param.put("_ICONPATH512_",
																								ResourceProperties.getString("appUrl")
																								+ iconImage512Path);
																						param.put("_ICONPATH57_",
																								ResourceProperties.getString("appUrl")
																								+ iconImage57Path);
																						param.put("_LOADERPATH_",
																								ResourceProperties.getString("appUrl")
																								+ loaderImagePath);
																						param.put("_BASEPATH_", ResourceProperties.getString("appUrl"));
																						if(consumerSecret!=null){		
																							param.put("_CONSUMERKEY_", user.getUsername());
																							param.put("_CONSUMERSECRET_", consumerSecret);
																						}
																						emailToDeveloper.setParam(param);
																						sender.sendMail(emailToDeveloper);
																logger.info("app submission mail sent to developer");

					} catch (Exception e) {
						logger.error("some error occured in sending email",e);
						message="some error occured in sending email";
					}
				}else{
					logger.info("not a new order");
					message="not a new order";
				}
			}else{
				logger.info("post payment tinckered");
				message="post payment tinkered";
			}
        }
		}catch(Exception e){
			logger.error("some error occured in post payment procesing",e);
			message="some error occured in post payment procesing";
		}
		
		return message; // return
	}



	public List<Feature> addTabTitles(List<Feature> features, List<StaticPage> staticPages ){
	
	for (ListIterator<Feature> iterator = features.listIterator(); iterator
			.hasNext();) {
		Feature feature = (Feature) iterator.next();
		for (StaticPage staticPage : staticPages) {
			if (staticPage.getFeatureId()!=null ) {
				//logger.debug("feature id:"+feature.getId()+"staticPage feature id:"+staticPage.getFeatureId()+"result:"+feature.getId().compareTo(staticPage.getFeatureId())+"("+feature.getTabTitle()+","+staticPage.getsTitle()+")");
				
				if(feature.getId().compareTo(staticPage.getFeatureId())==0){
					//logger.debug("featureId:"+feature.getId()+",staticpAge"+staticPage.getFeatureId()+","+"result:"+(feature.getId().compareTo(staticPage.getFeatureId()))+",title set:"+staticPage.getsTitle());
					feature.setTabTitle(staticPage.getsTitle());
					}
			} else{
				feature.setTabTitle(feature.getsName());
			}
		}
		iterator.set(feature);
	}
		
		/*for (int i=0;i<features.size();i++) {
	Feature feature = (Feature) features.get(i);
	for (StaticPage staticPage : staticPages) {
		if (staticPage.getFeatureId()!=null ) {
			//logger.debug("feature id:"+feature.getId()+"staticPage feature id:"+staticPage.getFeatureId()+"result:"+feature.getId().compareTo(staticPage.getFeatureId())+"("+feature.getTabTitle()+","+staticPage.getsTitle()+")");
			
			if(feature.getId().compareTo(staticPage.getFeatureId())==0){
				logger.debug("featureId:4:"+feature.getId()+",staticpAge"+staticPage.getFeatureId()+","+"result:"+(feature.getId().compareTo(staticPage.getFeatureId()))+",title set:"+staticPage.getsTitle());
				feature.setTabTitle(staticPage.getsTitle());
				 
				}
			else{
				
				feature.setTabTitle(feature.getsName());
			}
			 
		} 
	}
	features.set(i, feature);
}*/
		

	return features;
}
	


	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss MMM dd, yyyy z");
		dateFormat.setLenient(false);
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

}
