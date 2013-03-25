package com.mobicart.web.external;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mobicart.bo.ApiBO;
import com.mobicart.bo.Error;
import com.mobicart.bo.Message;
import com.mobicart.model.App;
import com.mobicart.model.GalleryImage;
import com.mobicart.model.ProductImage;
import com.mobicart.model.Store;
import com.mobicart.model.User;
import com.mobicart.model.api.ImageApi;
import com.mobicart.model.api.MainImageApi;
import com.mobicart.service.AppService;
import com.mobicart.service.StoreService;
import com.mobicart.util.Constants;
import com.mobicart.util.DateTimeUtils;
import com.mobicart.util.FileUtils;
import com.mobicart.util.ImageUtils;
import com.mobicart.util.ImgMagicCmdExcecutor;
import com.mobicart.util.PathLocator;
import com.mobicart.util.ResourceProperties;

@Controller
@RequestMapping("/api/**")
public class ImageApiWebServiceController {
	
	
	@Autowired
    StoreService storeService;
	@Autowired
    ApiBO apiBO;
	@Autowired
    AppService appService;
	
	
	private static final String ERROR = "error";
	private static final String VALID = "valid";
	private static final String USER = "user";
	private static final String MESSAGE = "message";
	private static final String HOME_GALLERY_IMAGES = "GalleryImageList";
	
	
	/**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory
            .getLogger(ImageApiWebServiceController.class);
	
	
	
    /**
     * Service to fetch gallery images for store
     *
     * @param user_name
     * @param api_key
     * @param store_id
     * @return List of Gallery Images in Store
     */

    @RequestMapping(value = "/store-gallery-images", method = RequestMethod.GET)
    public ModelAndView getGalleryImagesForStore(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "store_id", required = false) String storeId) {
        List<GalleryImage> galleryImages = null;
        Store store = null;
        ModelAndView mav = new ModelAndView();
        Error error = new Error();
        Long streId = null;
        User user = null;

        Map<String, Object> key_response = apiBO.validateApiKey(apiKey, userName);

        if (!(Boolean) key_response.get(VALID)) {
            error = (Error) key_response.get(ERROR);// get error
            mav.addObject(ERROR, error);
            return mav;
        }
        
        boolean hits = true;
        try{
        	hits = apiBO.validateGeneralApiHits(apiKey);
        }catch(Exception e){
        	mav.addObject(ERROR, error.generateError(1004));
        	return mav;
        }
        
        if(hits == false){
        	mav.addObject(ERROR, error.generateError(1005));
        	return mav;
        }
        
        List<String> missingParamList=new ArrayList<String>();
        if(StringUtils.isEmpty(storeId) ){
        	missingParamList.add("store_id");
        }
        if(storeId.length() > 10 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        try {
        	streId = Long.parseLong(storeId);
    		} catch (NumberFormatException nfe) {
    			mav.addObject(ERROR, error.generateError(2001));
    			return mav;
    		}

    	try {
			// get user
            user = (User) key_response.get(USER);
            store = storeService.findStoreById(streId);
            if(store == null){
				mav.addObject(ERROR, error.generateError(2003));
				return mav;
            }
			if(store.getUserId().longValue() != user.getId().longValue()){
				mav.addObject(ERROR, error.generateError(2004));
				return mav;
        	}

			galleryImages = storeService.findAPIGalleryImagesByStore(streId);
			
        	if (galleryImages == null || galleryImages.size() <= 0) {
        		mav.addObject(ERROR, error.generateError(4001));
        		return mav;
            	}

            } catch (Exception e) {
                error = error.generateError(1004);
                mav.addObject(ERROR, error);
                return mav;
            }

            List<ImageApi> listImageApi = new ArrayList<ImageApi>();
            if (galleryImages.size() > 0) {
                for (GalleryImage objGalleryImage : galleryImages) {
                    ImageApi imgApi = new ImageApi();
                    imgApi.setimageId(objGalleryImage.getId());
                    imgApi.setimageName(objGalleryImage.getsTitle());
                    imgApi.setimageUrl(ResourceProperties.getString("appUrl") + objGalleryImage.getsThumbnail());
                    listImageApi.add(imgApi);
                }
            }
            MainImageApi mainImage = new MainImageApi();
            mainImage.setSize(listImageApi.size());
            mainImage.setStoreId(store.getId());
            mainImage.setImages(listImageApi);
            mav.addObject(HOME_GALLERY_IMAGES, mainImage);
            return mav;
        
    }


    /**
     * Service to add Gallery Image
     *
     * @param user_name
     * @param api_key
     * @param store-id
     * @return in JSON or XML
     */
    @RequestMapping(value = "/add-gallery-image", method = RequestMethod.POST)
    public ModelAndView addGalleryImages(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "store_id", required = false) String storeId,
            @RequestParam(value = "gallery_image_url", required = false) String galleryImageUrl,
            @RequestParam(value = "gallery_image_path", required = false) MultipartFile galleryImagePath) {
        
        ModelAndView mav = new ModelAndView();
        Message message = new Message();
        Error error = new Error();
        List<Error> errors = new ArrayList<Error>();
        User user = null;
        Long streId = null;
        
        
        Map<String, Object> key_response = apiBO.validateApiKey(apiKey, userName);

        if (!(Boolean) key_response.get(VALID)) {
            error = (Error) key_response.get(ERROR);// get error
            mav.addObject(ERROR, error);
            return mav;
        }
        
        boolean hits = true;
        try{
        	hits = apiBO.validateGeneralApiHits(apiKey);
        }catch(Exception e){
        	mav.addObject(ERROR, error.generateError(1004));
        	return mav;
        }
        
        if(hits == false){
        	mav.addObject(ERROR, error.generateError(1005));
        	return mav;
        }
        
        List<String> missingParamList=new ArrayList<String>();
        
        if(StringUtils.isEmpty(storeId) ){
        	missingParamList.add("store_id");
        }
        if(storeId.length() > 10 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        
        if(StringUtils.isEmpty(galleryImageUrl) && galleryImagePath == null){
        	missingParamList.add("gallery_image_url Or gallery_image_path");
        }
  
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        try {
        	streId = Long.parseLong(storeId);
    		} catch (NumberFormatException nfe) {
    			mav.addObject(ERROR, error.generateError(2001));
    			return mav;
    		}
    		
    	
    	
        try {
			// get user
    		user = (User) key_response.get(USER);
    		Store store = storeService.findStoreById(streId);
    		if(store == null){
    			mav.addObject(ERROR, error.generateError(2003));
    			return mav;
    		}
		
    		if(store.getUserId().longValue() != user.getId().longValue()){
    			mav.addObject(ERROR, error.generateError(2004));
    			return mav;
    		}

            if(! StringUtils.isEmpty(galleryImageUrl)){
            	
            	if( ! galleryImageUrl.startsWith("http://")){
        			mav.addObject(ERROR, error.generateError(3015));
        			return mav;
            	}
            		
            	if(!(galleryImageUrl.endsWith("png") || galleryImageUrl.endsWith("jpg") || galleryImageUrl.endsWith("jpeg")
            			|| galleryImageUrl.endsWith("bmp") || galleryImageUrl.endsWith("gif"))){
            		mav.addObject(ERROR, error.generateError(3016));
            		return mav;
            	}
            	
            	PathLocator pathLocator = new PathLocator();
            	// change to online server for the time being
            	String rootPath = pathLocator.getContextPath();

            	String mobicartImagesFolderPath = "mobicartimages";
            	FileUtils.makeDirectoryIfItsNotThere(rootPath
            			+ mobicartImagesFolderPath);

            	String galleryFolderPath = "/gallery";
            	FileUtils.makeDirectoryIfItsNotThere(rootPath
            			+ mobicartImagesFolderPath + galleryFolderPath);
            	
            	String storeFolderPath = "/" + store.getId().toString();
            	FileUtils.makeDirectoryIfItsNotThere(rootPath
            			+ mobicartImagesFolderPath + galleryFolderPath
            			+ storeFolderPath);
            	
            	String dateWiseFolderPath = "/" + DateTimeUtils.getCurrentDate();
            	FileUtils.makeDirectoryIfItsNotThere(rootPath
            			+ mobicartImagesFolderPath + galleryFolderPath
            			+ storeFolderPath + dateWiseFolderPath);

            	String finalGalleryImagePath = mobicartImagesFolderPath
                    	+ galleryFolderPath + storeFolderPath + dateWiseFolderPath;
            	
            	int fileNameIndex = galleryImageUrl.lastIndexOf("/");
            	String fileName = galleryImageUrl.substring(fileNameIndex);
            	
            	
            	String galleryImageFileName = FileUtils.getJustTheFileName(fileName);
            	
            	String	pathForImageStorage= rootPath + finalGalleryImagePath+"/";
        		String targetFileName=pathForImageStorage+galleryImageUrl.substring(fileNameIndex+1);
            	
            	//finalGalleryImagePath += "/"+galleryImageFileName;
        		finalGalleryImagePath += fileName;
            	
            
            	
            	URL urlOfImage = null;
            	File outputFile = null;
            	
            	BufferedInputStream in = null;
            	FileOutputStream out = null;
            	try {
            		outputFile = new File(rootPath + finalGalleryImagePath);
            		urlOfImage = new URL(galleryImageUrl);
								
            		in = new BufferedInputStream(urlOfImage.openStream());
            		BufferedImage bufferedGalleryImage = ImageIO.read(in);
            		out = new FileOutputStream(outputFile);
            		String format = (galleryImageUrl.endsWith(".png")) ? "png" : "jpg";
            		ImageIO.write(bufferedGalleryImage, format, out);
            		
            		bufferedGalleryImage = ImageUtils.resize(bufferedGalleryImage, 320, 235, true);
            		
            		ImageUtils.saveImage(bufferedGalleryImage, rootPath + finalGalleryImagePath);
            		
            		in.close();
            		out.close();
            		
            		
            	 
    				 ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, fileName,Constants.GALLERY_IMAGE_ANDROID3_KEY,true);
 					 ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, fileName,Constants.GALLERY_IMAGE_ANDROID4_KEY,true);
 					 ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, fileName,Constants.GALLERY_IMAGE_ANDROID6_KEY,true);
 					 ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, fileName,Constants.GALLERY_IMAGE_IPAD_KEY,true);
 					 ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, fileName,Constants.GALLERY_IMAGE_IPHONE_KEY,true);
 				     ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, fileName,Constants.GALLERY_IMAGE_IPHONE4_KEY,true);
            		
            		
            		
            		GalleryImage galleryImage = new GalleryImage();
            		galleryImage.setsTitle(FileUtils.getJustTheFileName(galleryImageUrl));
            		galleryImage.setStoreId(store.getId());
            		galleryImage.setUserId(store.getUserId());
            		App app = appService.findAppByUser(new User(store.getUserId()));
            		galleryImage.setAppId(app.getId());
            		galleryImage.setsThumbnail("/"+finalGalleryImagePath);
            		try {
            			Long galleryImageId = storeService.saveGalleryImage(galleryImage);
            			message.setId(galleryImageId.toString());
            			message.setMessage("Gallery image added successfully.");
            			mav.addObject(MESSAGE, message);
            		} catch (Exception exp) {
            			logger.error("While saving image", exp);
            			error = error.generateError(1004);
            			mav.addObject(ERROR, error);
            			return mav;
            		}
            	} catch (MalformedURLException e) {
            		logger.error("Wrong URL", e);
            		mav.addObject(ERROR, error.generateError(3017));
            		return mav;
            	} catch (Exception e) {
            		logger.error("IOException", e);
            		error = error.generateError(1004);
            		mav.addObject(ERROR, error);
            		return mav;
            	}
            	finally{
            		if (in != null) in = null;
            		if (out != null) out = null;
            	}
            }else if(galleryImagePath != null){
        	
        		if (galleryImagePath != null && galleryImagePath.getSize() > 0) {
                    String contentType = galleryImagePath.getContentType();
                    if ((contentType.equals("image/png")) || contentType.equals("image/jpg") || contentType.equals("image/jpeg") || contentType.equals("image/bmp") || contentType.equals("image/gif")) {
                    } else {
                    	error = error.generateError(3016);
        				errors.add(error);
                    }

                    try {
                        MultipartFile galleryImageFile = galleryImagePath;
                        
                        PathLocator pathLocator = new PathLocator();
                        // change to online server for the time being
                        String rootPath = pathLocator.getContextPath();

                        String mobicartImagesFolderPath = "mobicartimages";
                        FileUtils.makeDirectoryIfItsNotThere(rootPath
                                + mobicartImagesFolderPath);

                        String galleryFolderPath = "/gallery";
                    	FileUtils.makeDirectoryIfItsNotThere(rootPath
                    			+ mobicartImagesFolderPath + galleryFolderPath);
                    	
                    	String storeFolderPath = "/" + store.getId().toString();
                    	FileUtils.makeDirectoryIfItsNotThere(rootPath
                    			+ mobicartImagesFolderPath + galleryFolderPath
                    			+ storeFolderPath);
                    	
                    	String dateWiseFolderPath = "/" + DateTimeUtils.getCurrentDate();
                    	FileUtils.makeDirectoryIfItsNotThere(rootPath
                    			+ mobicartImagesFolderPath + galleryFolderPath
                    			+ storeFolderPath + dateWiseFolderPath);

                    	String finalGalleryImagePath = mobicartImagesFolderPath
                            	+ galleryFolderPath + storeFolderPath + dateWiseFolderPath;
                    	
                    	
                    	
                    	
                    	String galleryImageFileName = galleryImagePath.getOriginalFilename();
                    	
                    	finalGalleryImagePath += "/"+galleryImageFileName;

                        InputStream galleryImageInputStream = null;
                        OutputStream galleryImageOutputStream = null;
                        if (galleryImageFile.getSize() > 0) {
                        	galleryImageInputStream = galleryImageFile.getInputStream();
                            BufferedImage bufferedGalleryImage = ImageIO
                                    .read(galleryImageInputStream);
                            galleryImageOutputStream = new FileOutputStream(rootPath
                                    + finalGalleryImagePath);
                            String format = (galleryImageFile.getOriginalFilename()
                                    .endsWith(".png")) ? "png" : "jpg";
                            ImageIO.write(bufferedGalleryImage, format, galleryImageOutputStream);
                            //resize image
                            bufferedGalleryImage = ImageUtils.resize(bufferedGalleryImage, 320, 235, true);
                    		
                    		ImageUtils.saveImage(bufferedGalleryImage, rootPath + finalGalleryImagePath);

                    		galleryImageInputStream.close();
                    		galleryImageOutputStream.close();
                        }

                        GalleryImage galleryImage = new GalleryImage();
                		galleryImage.setsTitle(galleryImagePath.getOriginalFilename());
                		galleryImage.setStoreId(store.getId());
                		galleryImage.setUserId(store.getUserId());
                		App app = appService.findAppByUser(new User(store.getUserId()));
                		galleryImage.setAppId(app.getId());
                		galleryImage.setsThumbnail("/"+finalGalleryImagePath);
                        try {
                        	Long galleryImageId = storeService.saveGalleryImage(galleryImage);
        					message.setId(galleryImageId.toString());
        					message.setMessage("Gallery image added successfully.");
        					mav.addObject(MESSAGE, message);

                        } catch (Exception exp) {
                            mav.addObject(ERROR, "Could not add gallery image");
                            return mav;

                        }

                    } catch (IOException ioe) {
                    }
                }
        	
        }
        } catch (Exception e) {
        	logger.error("Exception", e);
        	error = error.generateError(1004);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        return mav;
    }


    /**
     * Service to delete Gallery Image
     *
     * @param user_name
     * @param api_key
     * @param galleryImage-id
     * @return Delete Image Status String
     */
    @RequestMapping(value = "/delete-gallery-image", method = RequestMethod.DELETE)
    public ModelAndView deleteGalleryImages(
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = "image_id", required = false) String imageId) {
        GalleryImage gImage = null;
        ModelAndView mav = new ModelAndView();
        Message message = new Message();
        Error error = new Error();
        Long imgId;
        User user = null;

        Map<String, Object> key_response = apiBO.validateApiKey(apiKey, userName);

        if (!(Boolean) key_response.get(VALID)) {
            error = (Error) key_response.get(ERROR);// get error
            mav.addObject(ERROR, error);
            return mav;
        }
        
        boolean hits = true;
        try{
        	hits = apiBO.validateGeneralApiHits(apiKey);
        }catch(Exception e){
        	mav.addObject(ERROR, error.generateError(1004));
        	return mav;
        }
        
        if(hits == false){
        	mav.addObject(ERROR, error.generateError(1005));
        	return mav;
        }
        
        List<String> missingParamList=new ArrayList<String>();
        if(StringUtils.isEmpty(imageId) ){
        	missingParamList.add("image_id");
        }
        if(imageId.length() > 10 ){
        	mav.addObject(ERROR, error.generateError(1007));
            return mav;
        }
        
        
        if(missingParamList.size()>0){
        	error = new Error(1001, Error.MISSING_PARAMETERS+missingParamList);
            mav.addObject(ERROR, error);
            return mav;
        }
        
        try {
        	imgId = Long.parseLong(imageId);
    		} catch (NumberFormatException nfe) {
    			mav.addObject(ERROR, error.generateError(4003));
    			return mav;
    		}

        	try {
    			// get user
                user = (User) key_response.get(USER);
                gImage = storeService.findGalleryImage(imgId);
                
                if(gImage == null){
    				mav.addObject(ERROR, error.generateError(4004));
    				return mav;
                }
                
    			if(gImage.getUserId().longValue() != user.getId().longValue()){
    				mav.addObject(ERROR, error.generateError(4005));
    				return mav;
            		}
    			
                } catch (Exception e) {
                    error = error.generateError(1004);
                    mav.addObject(ERROR, error);
                    return mav;
                }
                
                try{
                	storeService.deleteGalleryImage(gImage);
                }catch(Exception e){
                	error = error.generateError(1004);
                    mav.addObject(ERROR, error);
                    return mav;
                }
                message.setMessage("Image deleted successfully.");
                mav.addObject(message);
                return mav;
                
    }



}
