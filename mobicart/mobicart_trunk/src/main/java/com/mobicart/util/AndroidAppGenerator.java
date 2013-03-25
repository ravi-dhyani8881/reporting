package com.mobicart.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AndroidAppGenerator {
	private static final Logger logger = LoggerFactory.getLogger(MagicalPower.class);
	private String[] argList;
	
	public void compileAndSave(String location){
		
		PathLocator path=new PathLocator();
		String commondPath=path.getDocumentsRootPath()+"compile.sh";
		String  cmd=commondPath;
	    if(argList.length==5)
		cmd=cmd+" "+argList[0]+" "+argList[1]+" "+argList[2]+" "+argList[3]+" "+argList[4];
	    else
	    cmd=cmd+" help";
 		
		this.execute(cmd);
		
	}
	 
public AndroidAppGenerator( String key_file_path,
		                    String project_bin_directory,
		                    String name_of_release_unsigned_apk,
		                    String alias_name_of_keystore,
		                    String final_Apk_relased_filename_apk
		                     ){
	
	this.argList=new String[5];
	
	this.argList[0]=key_file_path;
	this.argList[1]=project_bin_directory;
	this.argList[2]=name_of_release_unsigned_apk;
	this.argList[3]=alias_name_of_keystore;
	this.argList[4]=final_Apk_relased_filename_apk;
}
	
	
 
		
		
	 
	private  void execute(String cmd){
		
		
		try 
		{ 
		   
	     
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

	}

}
