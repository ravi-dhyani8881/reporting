package com.mobicart.util;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.process.ProcessStarter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * ImageMagick helper class
 * @author jasdeep.singh
 *
 */
public class MagicalPower {
	
	private static final Logger logger = LoggerFactory.getLogger(MagicalPower.class);

	
	BufferedImage bufferdImage;
	String rootPath;
	String originalImageFileName;
	String propertyKey;
	
	
	
	
	public MagicalPower() {
		super();
	}



	public MagicalPower(BufferedImage image, String rootPath,
			String originalImageFileName, String propertyKey) {
		super();
		this.bufferdImage = image;
		this.rootPath = rootPath;
		this.originalImageFileName = originalImageFileName;
		this.propertyKey = propertyKey;
	}



	public void resizeAndSave(){
		logger.debug("resizing it via executer : {}"+propertyKey);
		String pathToNewImage=rootPath+FileUtils.stuffedFilename(originalImageFileName,"_"+propertyKey );
		ImageSize imageSize= ImageSize.parse(ResourceProperties.getImageString(propertyKey));
		this.resizeAndSave(bufferdImage, pathToNewImage,  imageSize);
	}
	
	
	/**
	 * Resize buffered image
	 * @param image
	 * @param outfile
	 * @param imageSize
	 */
	public void resizeAndSave(BufferedImage image, String rootPath, String originalImageFileName , String propertyKey ){
		/*operations for key*/
		String pathToNewImage=rootPath+FileUtils.stuffedFilename(originalImageFileName,"_"+propertyKey );
		ImageSize imageSize= ImageSize.parse(ResourceProperties.getImageString(propertyKey));
		this.resizeAndSave(image, pathToNewImage,  imageSize);
	}
	
	
	
	/**
	 * Resize buffered image
	 * @param image
	 * @param outfile
	 * @param imageSize
	 */
	public void resizeAndSave(BufferedImage image, String outfile, ImageSize imageSize){
		this.resizeAndSave(image, outfile, imageSize.getWidth(),imageSize.getHeight());
	}
	
	
	/**
	 * Resize buffered image
	 * @param inputImage
	 * @param width
	 * @param height
	 */
	public void resizeAndSave(BufferedImage image, String outfile,  int width,
			int height) {

		try {
			
			logger.debug("resizing buffered image old width: {}, old height:{} ",image.getWidth(), image.getHeight());
			logger.debug("resizing buffered image new width: {}, new height: {}",width,height);
			logger.debug("resizing buffered image save location: {}",outfile);
			
			String myPath = ResourceProperties.getImageString(Constants.MAGIC_PATH_KEY);
			ProcessStarter.setGlobalSearchPath(myPath);
			IMOperation op = new IMOperation();
			op.addImage();                        // input
			op.resize(width, height);
			op.addImage();                        // output
			ConvertCmd convert = new ConvertCmd();
			convert.setAsyncMode(true);
			convert.run(op,image,outfile);
			
		} catch (IOException e) {
			logger.error("IO ", e);
		}  catch (InterruptedException e) {
			logger.error("Interrupted ", e);
		}catch (IM4JavaException e) {
			logger.error("IM4Java ", e);
		}catch (Exception e) {
			logger.error("general ", e);
		}

	}



	



	public BufferedImage getBufferdImage() {
		return bufferdImage;
	}



	public void setBufferdImage(BufferedImage bufferdImage) {
		this.bufferdImage = bufferdImage;
	}



	public String getRootPath() {
		return rootPath;
	}



	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}



	public String getOriginalImageFileName() {
		return originalImageFileName;
	}



	public void setOriginalImageFileName(String originalImageFileName) {
		this.originalImageFileName = originalImageFileName;
	}



	public String getPropertyKey() {
		return propertyKey;
	}



	public void setPropertyKey(String propertyKey) {
		this.propertyKey = propertyKey;
	}
	
	
	
	
    
    
}
