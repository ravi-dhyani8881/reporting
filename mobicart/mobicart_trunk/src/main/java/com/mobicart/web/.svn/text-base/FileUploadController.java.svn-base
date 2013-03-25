package com.mobicart.web;

import java.awt.image.BufferedImage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import com.mobicart.dto.AppleCertificateUploadDto;
import com.mobicart.dto.UploadResponse;
import com.mobicart.model.App;
import com.mobicart.model.ProductImage;
import com.mobicart.service.AppService;
import com.mobicart.service.StoreService;
import com.mobicart.util.Constants;
import com.mobicart.util.DateTimeUtils;
import com.mobicart.util.FileUtils;
import com.mobicart.util.ImageSize;
import com.mobicart.util.ImageUploadTaskExecuter;
import com.mobicart.util.ImageUtils;
import com.mobicart.util.ImgMagicCmdExcecutor;
import com.mobicart.util.MagicalPower;
import com.mobicart.util.PathLocator;
import com.mobicart.util.ProductImageUploadBean;
import com.mobicart.util.ResourceProperties;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Controller
public class FileUploadController {
	
	@Autowired
	StoreService storeService;

	@Autowired
	AppService appService;
	
	@Autowired
	ImageUploadTaskExecuter imageUploadTaskExecuter;
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	
	
	@RequestMapping(value="/store/product/image/upload",method=RequestMethod.POST)
	public void productImageUpload(@ModelAttribute ProductImageUploadBean productImageUploadBean,BindingResult result,
			ModelMap modelMap,HttpServletRequest request,HttpServletResponse response) {

		ProductImage productImage = null;
		InputStream productInputStream=null;
		BufferedImage bufferedImage=null;
		String pathForImageStorage=null;
		String productImageFileName=null;
		
		List<MagicalPower> magicalPowerItems=null;
		try {
			MultipartFile productImageFile=productImageUploadBean.getFile();
		
			PathLocator pathLocator = new PathLocator();
			// change to online server for the time being
			String rootPath = pathLocator.getContextPath();

			String mobicartImagesFolderPath = "mobicartimages";
			FileUtils.makeDirectoryIfItsNotThere(rootPath
					+ mobicartImagesFolderPath);

			String productFolderPath = "/productimages";
			FileUtils.makeDirectoryIfItsNotThere(rootPath
					+ mobicartImagesFolderPath + productFolderPath);

			//hard code
			String storeFolderPath = "/"+productImageUploadBean.getStoreId();
			FileUtils.makeDirectoryIfItsNotThere(rootPath
					+ mobicartImagesFolderPath + productFolderPath
					+ storeFolderPath);

			String dateWiseFolderPath = "/" + DateTimeUtils.getCurrentDate();
			FileUtils.makeDirectoryIfItsNotThere(rootPath
					+ mobicartImagesFolderPath + productFolderPath
					+ storeFolderPath + dateWiseFolderPath);

			String finalProductImagePath = mobicartImagesFolderPath
					+ productFolderPath + storeFolderPath + dateWiseFolderPath;

			productImageFileName=FileUtils.cleanSpecialChars(productImageFile.getOriginalFilename());
			
			String mediumGalleryImagePath=rootPath+finalProductImagePath+"/"+FileUtils.stuffedFilename(productImageFileName, "_medium");
			String smallGalleryImagePath=rootPath+finalProductImagePath+"/"+FileUtils.stuffedFilename(productImageFileName, "_small");
			
			pathForImageStorage= rootPath + finalProductImagePath+"/";
			
			finalProductImagePath += "/"
					+ productImageFileName;
			
			if(logger.isDebugEnabled()){
				logger.debug("product image upload path: {}"+finalProductImagePath);
			}
			productInputStream = null;
			if (productImageFile.getSize() > 0) {
				
			     /*
				productInputStream = productImageFile.getInputStream();
				bufferedImage = ImageIO.read(productInputStream);
					
					 
					// save other variations 
					MagicalPower magicalPower=new MagicalPower();

					//	save the the old file	
					ImageUtils.saveImage(bufferedImage, rootPath+ finalProductImagePath);

					//old files
					magicalPower.resizeAndSave(bufferedImage, smallGalleryImagePath, ImageSize.parse(ResourceProperties.getImageString(Constants.PRODUCT_IMAGE_SMALL_IPHONE_KEY)));
					magicalPower.resizeAndSave(bufferedImage, mediumGalleryImagePath, ImageSize.parse(ResourceProperties.getImageString(Constants.PRODUCT_IMAGE_MEDIUM_IPHONE_KEY)));
					
					
					// save other variations according to new excel sheet
//					magicalPower.resizeAndSave(bufferedImage, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_SMALL_ANDROID3_KEY );
//					magicalPower.resizeAndSave(bufferedImage, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_SMALL_ANDROID4_KEY );
//					magicalPower.resizeAndSave(bufferedImage, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_SMALL_ANDROID6_KEY );
//					magicalPower.resizeAndSave(bufferedImage, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_SMALL_IPAD_KEY );
//					magicalPower.resizeAndSave(bufferedImage, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_SMALL_IPHONE_KEY );
//					
//					magicalPower.resizeAndSave(bufferedImage, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_MEDIUM_ANDROID3_KEY );
//					magicalPower.resizeAndSave(bufferedImage, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_MEDIUM_ANDROID4_KEY );
//					magicalPower.resizeAndSave(bufferedImage, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_MEDIUM_ANDROID6_KEY );
//					magicalPower.resizeAndSave(bufferedImage, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_MEDIUM_IPAD_KEY );
//					magicalPower.resizeAndSave(bufferedImage, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_MEDIUM_IPHONE_KEY );
//					
//					magicalPower.resizeAndSave(bufferedImage, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_COVER_FLOW_ANDROID3_KEY );
//					magicalPower.resizeAndSave(bufferedImage, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_COVER_FLOW_ANDROID4_KEY );
//					magicalPower.resizeAndSave(bufferedImage, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_COVER_FLOW_ANDROID6_KEY );
//					magicalPower.resizeAndSave(bufferedImage, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_COVER_FLOW_IPAD_KEY );
//					magicalPower.resizeAndSave(bufferedImage, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_COVER_FLOW_IPHONE_KEY );
//					
					// for window xp
					magicalPowerItems=new ArrayList<MagicalPower>();	
					magicalPowerItems.add(new MagicalPower(bufferedImage, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_SMALL_ANDROID3_KEY ));
					magicalPowerItems.add(new MagicalPower(bufferedImage, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_SMALL_ANDROID4_KEY ));
					magicalPowerItems.add(new MagicalPower(bufferedImage, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_SMALL_ANDROID6_KEY ));
					magicalPowerItems.add(new MagicalPower(bufferedImage, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_SMALL_IPAD_KEY ));
					magicalPowerItems.add(new MagicalPower(bufferedImage, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_SMALL_IPHONE_KEY ));
					
					magicalPowerItems.add(new MagicalPower(bufferedImage, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_MEDIUM_ANDROID3_KEY ));
					magicalPowerItems.add(new MagicalPower(bufferedImage, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_MEDIUM_ANDROID4_KEY ));
					magicalPowerItems.add(new MagicalPower(bufferedImage, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_MEDIUM_ANDROID6_KEY ));
					magicalPowerItems.add(new MagicalPower(bufferedImage, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_MEDIUM_IPAD_KEY ));
					magicalPowerItems.add(new MagicalPower(bufferedImage, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_MEDIUM_IPHONE_KEY ));
					
					magicalPowerItems.add(new MagicalPower(bufferedImage, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_COVER_FLOW_ANDROID3_KEY ));
					magicalPowerItems.add(new MagicalPower(bufferedImage, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_COVER_FLOW_ANDROID4_KEY ));
					magicalPowerItems.add(new MagicalPower(bufferedImage, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_COVER_FLOW_ANDROID6_KEY ));
					magicalPowerItems.add(new MagicalPower(bufferedImage, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_COVER_FLOW_IPAD_KEY ));
					magicalPowerItems.add(new MagicalPower(bufferedImage, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_COVER_FLOW_IPHONE_KEY ));					
					magicalPowerItems.add(new MagicalPower(bufferedImage, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_DETAIL ));				
					productInputStream.close();
					  */ 
				
				 
				productInputStream = productImageFile.getInputStream();
				bufferedImage = ImageIO.read(productInputStream);
				ImageUtils.saveImage(bufferedImage, rootPath+ finalProductImagePath);
				productInputStream.close();
				String targetFileName=rootPath+ finalProductImagePath;
				logger.debug("targetFileName:"+targetFileName+"\n"+"productImageFileName:"+productImageFileName);
				 
				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage,productImageFileName,"small",true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage,productImageFileName,"medium",true);
 
				//ImgMagicCmdExcecutor.resizeAndSaveFile(tempFile, rootPath, originalImageFileName, propertyKey,true)
				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_SMALL_ANDROID3_KEY ,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_SMALL_ANDROID4_KEY ,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_SMALL_ANDROID6_KEY ,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_SMALL_IPAD_KEY ,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_SMALL_IPHONE_KEY ,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_SMALL_IPHONE4_KEY ,true);
				
				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_MEDIUM_ANDROID3_KEY ,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_MEDIUM_ANDROID4_KEY ,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_MEDIUM_ANDROID6_KEY ,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_MEDIUM_IPAD_KEY ,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_MEDIUM_IPHONE_KEY ,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_MEDIUM_IPHONE4_KEY ,true);
				
				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_COVER_FLOW_ANDROID3_KEY ,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_COVER_FLOW_ANDROID4_KEY ,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_COVER_FLOW_ANDROID6_KEY ,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_COVER_FLOW_IPAD_KEY ,true);
				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_COVER_FLOW_IPHONE_KEY ,true);	
				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_COVER_FLOW_IPHONE4_KEY ,true);
				
				ImgMagicCmdExcecutor.resizeAndSaveFile(targetFileName, pathForImageStorage, productImageFileName,Constants.PRODUCT_IMAGE_DETAIL ,true);				
				 
					 
			}

			productImage = new ProductImage();
			productImage.setsTitle(productImageUploadBean.getTitle());
			productImage.setsLocation("/"+FileUtils.stuffedFilename(finalProductImagePath,""));
			//System.out.println("finalProductImagePath"+finalProductImagePath);
			
			Long pId = 0L;
			
			if(request.getParameter("productId") != null){
				try{
					pId = Long.parseLong(request.getParameter("productId"));
				}catch (Exception e) {
					pId = 0L;
				}
			}
			productImage.setProductId(pId);			
			Long id=storeService.createProductImage(productImage);
			
			productImage.setId(id);
			productImage.getsLocationMedium();
			productImage.getsLocationSmall();
			
			
			//uncommnet it for xp
			//imageUploadTaskExecuter.uploadImages(magicalPowerItems);
		} catch (IOException ioe) {
			logger.error("error in uploading product image",ioe);
		 
		}catch (Exception e) {
			logger.error("error",e);
		 
		}

		
		List<ProductImage> productImages=new ArrayList<ProductImage>();
		productImages.add(productImage);
		
		XStream xstream = new XStream(new DomDriver()); // does not require XPP3 library
		xstream.alias("productImages", List.class);
		xstream.alias("productImage", ProductImage.class);
		String xml = xstream.toXML(productImages);
		
		response.setContentType("text/xml");
		
		try {
			
			response.getWriter().write(xml);
		} catch (IOException e) {
			logger.warn("whatever",e);
		}
		
	}

	
	
	
	@RequestMapping(value="/app/certificate/upload",method=RequestMethod.POST)
	public void appleCertificateUpload(@ModelAttribute AppleCertificateUploadDto appleCertificateUploadDto
			,HttpServletResponse response								
			) {

       logger.debug("password:"+appleCertificateUploadDto.getCertPassword());
		App app = null;
		
		UploadResponse uploadResponse=new UploadResponse();
		
		if(logger.isDebugEnabled()){
			logger.debug("uploading certificate....  ");
		}
		
		try {
			app=appService.findAppById(appleCertificateUploadDto.getAppId());
			MultipartFile certificateFile=appleCertificateUploadDto.getFile();
		if(certificateFile!=null){
			String extension=FilenameUtils.getExtension(certificateFile.getOriginalFilename());
			
			if(!extension.equals("p12")){
				uploadResponse.setStatus("invalid");
			}else{
			
			if(logger.isDebugEnabled()){
				logger.debug("valid certificate file....  ");
			}
			
			PathLocator pathLocator = new PathLocator();
			// change to online server for the time being
			String rootPath = pathLocator.getContextPath();
		    logger.debug("rootPath:"+rootPath);
			String mobicartImagesFolderPath = "mobicartimages";
			FileUtils.makeDirectoryIfItsNotThere(rootPath
					+ mobicartImagesFolderPath);

			String certificateFolderPath = "/certificates";
			FileUtils.makeDirectoryIfItsNotThere(rootPath
					+ mobicartImagesFolderPath + certificateFolderPath );

			//hard code
			String appFolderPath = "/"+appleCertificateUploadDto.getAppId();
			FileUtils.makeDirectoryIfItsNotThere(rootPath
					+ mobicartImagesFolderPath + certificateFolderPath 
					+ appFolderPath);

			
          
            String certificateFileName=FileUtils.cleanSpecialChars(certificateFile.getOriginalFilename());
            String pathExceptRoot=mobicartImagesFolderPath+certificateFolderPath+appFolderPath+"/"+ certificateFileName ;
			String finalCertPath = rootPath+pathExceptRoot;
			
			 
			 
			 
			 
			 
			if(logger.isDebugEnabled()){
				logger.debug("certificate upload path: "+pathExceptRoot);
				logger.debug("certificate upload path: "+finalCertPath);
			}

			InputStream inputStream = null;
	        OutputStream outputStream = null;
			if (certificateFile.getSize() > 0) {
				inputStream = certificateFile.getInputStream();
	            outputStream = new FileOutputStream(finalCertPath);
	            int readBytes = 0;
	            byte[] buffer = new byte[10000];
	            while ((readBytes = inputStream.read(buffer, 0 , 10000))!=-1){
	 	                outputStream.write(buffer, 0, readBytes);
	            }
	            outputStream.close();
	            inputStream.close();
			}
		
			
			app.setsPnCertificatePath(pathExceptRoot);
			app.setsPnPassword(appleCertificateUploadDto.getCertPassword().trim());
			appService.saveAppVitals(app);
			}	
		}		
      uploadResponse.setStatus("success");
			
		} catch (Exception ioe) {
			logger.error("error in uploading product image",ioe);
			uploadResponse.setStatus("failure");
			ioe.printStackTrace();
		}
		
		
		List<UploadResponse> responses=new ArrayList<UploadResponse>();
		responses.add(uploadResponse);
		
		XStream xstream = new XStream(new DomDriver()); // does not require XPP3 library
		xstream.alias("responses", List.class);
		xstream.alias("uploadResponse", UploadResponse.class);
		String xml = xstream.toXML(responses);
		
		response.setContentType("text/xml");
		
		try {
			response.getWriter().write(xml);
		} catch (IOException e) {
			
		}
	
	}
	
	
	
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
	}

}