package com.mobicart.util;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream; 

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class  ImgMagicCmdExcecutor  
{ 
	private static final Logger logger = LoggerFactory.getLogger(MagicalPower.class);
	private static String imageMagicPath=null; 
public static boolean   resizeAndSave( String tempfile, String outfile,  int width,int height,boolean maintaioRatio) {
	boolean isSmallImage=false;
	
	
	try{
		  BufferedImage bimg = ImageIO.read(new File(tempfile));
		  int imgActualwidth          = bimg.getWidth();
		  int imgActualheight         = bimg.getHeight();
		  
		  if((imgActualwidth<width)&&(imgActualheight<height))
			  isSmallImage=true; 
		  
		   
		}
		catch (Exception e) {
			// TODO: handle exception
		}

	
	String cmd="";
	try 
	{ 
	   
	logger.debug("new height:"+height);	
	logger.debug("new  width:"+width);	
	if(!isSmallImage)
	  cmd=((imageMagicPath!=null)?imageMagicPath:"")+"convert -resize "+width+"x"+height+((maintaioRatio)?"":"!")+" "+tempfile+" "+outfile ;
	else
		{
		cmd="cp -r "+tempfile+" "+outfile ;//small image just skip it from convert.
		
		}
	logger.debug(cmd);
	Process p=Runtime.getRuntime().exec(cmd); 
	p.waitFor(); 
	if(logger.isDebugEnabled()){
	BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 
	String line=reader.readLine(); 
	

	while(line!=null) 
	{ 
	logger.debug(line); 
	line=reader.readLine(); 
	} 
	
	}
     p.getInputStream().close();
     p.destroy();
	} 
	catch(IOException e1) {
		logger.debug("",e1); 
		 
		} 
	catch(InterruptedException e2) {logger.debug("",e2);
	 
	} 

	logger.debug("Done"); 
	return true;
}
public static  void resizeAndSaveFile(String tempFile, String rootPath, String originalImageFileName , String propertyKey,boolean maintaioRatio ){
	/*operations for key*/
	 String pathToNewImage=rootPath+FileUtils.stuffedFilename(originalImageFileName,"_"+propertyKey );
	logger.debug(propertyKey);
	ImageSize imageSize= ImageSize.parse(ResourceProperties.getImageString(propertyKey));
	resizeAndSave(tempFile, pathToNewImage,  imageSize.getWidth(),imageSize.getHeight(),maintaioRatio);
}
public static  void resizeAndSaveFile(String rootPath,String tempFile , String propertyKey,boolean maintaioRatio ) throws Exception{
	/*operations for key*/
	 String pathToNewImage=rootPath+FileUtils.stuffedFilename(tempFile,"_"+propertyKey );
	logger.debug(propertyKey);
	ImageSize imageSize= ImageSize.parse(ResourceProperties.getImageString(propertyKey));
	resizeAndSave(rootPath+tempFile, pathToNewImage,  imageSize.getWidth(),imageSize.getHeight(),maintaioRatio);

}

public static String   saveTempFile(InputStream inputStream, String outfile){
	//String tempFilePath=outfile.replace(".", "_temp.");
	
	try 
	{ 
        OutputStream outputStream = null;		 
        outputStream = new FileOutputStream(outfile);
            int readBytes = 0;
            byte[] buffer = new byte[10000];
            while ((readBytes = inputStream.read(buffer, 0 , 10000))!=-1){
 	                outputStream.write(buffer, 0, readBytes);
            }
            outputStream.close();
            inputStream.close();
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	
	return outfile;
}
private static boolean  isWindowXp(){
	
	String osName=System.getProperty("os.name"); 
	logger.info("os name is:"+osName);
	if(osName.toLowerCase().contains("xp"))
		return true;
	return  false;
}

static{
	try{
		if(isWindowXp())	 
		imageMagicPath=ResourceProperties.getImageString(Constants.MAGIC_PATH_KEY)+"\\";
		 
		else{
			
			imageMagicPath=ResourceProperties.getImageString(Constants.MAGIC_PATH_KEY)+"/";
			
		}
		 }
		 catch (Exception e) {
			logger.error("error during loding Image Magic Path", e);
		}
		
		 if(imageMagicPath!=null){
			 
			 if(imageMagicPath.contains(Constants.MAGIC_PATH_KEY))
					imageMagicPath=null;
		 }
	
}
	
}