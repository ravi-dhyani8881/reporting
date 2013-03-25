package com.ytk.client;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ytk.utility.LogDetails;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;



@Component("mamCacheClient")
public class MamCacheClient {

	private static final Logger logger = LoggerFactory.getLogger(MamCacheClient.class);
	
	@Autowired
	LogDetails logDetails;
	
	public String mamCacheClient(String mamCacheUrl,int mamCachePort, String key ,  HttpServletRequest request){
		String memCacheData=null;
		try{
			  MemcachedClient client=new XMemcachedClient(mamCacheUrl,mamCachePort);
			  memCacheData=client.get(key);
			  client.shutdown(); 
		}catch (Exception e) {
			logDetails.getException(e , logger , request);
			e.printStackTrace();
		}
		return memCacheData;
	}
	
	public String mamCachefriendList(String mamCacheUrl,int mamCachePort, String key , HttpServletRequest request){
		String memCacheData=null;
		try{
			  MemcachedClient client=new XMemcachedClient(mamCacheUrl,mamCachePort);
			  memCacheData=client.get("fnd:"+key);
			  
			  if(memCacheData==null || memCacheData.equals("")){
				  memCacheData="";
			  }
			  client.shutdown(); 
		}catch (Exception e) {
			logDetails.getException(e , logger , request);
			e.printStackTrace();
			}
		return memCacheData;
	}
	
	
	public String mamCachefolderList(String mamCacheUrl,int mamCachePort, String key , HttpServletRequest request ){
		String memCacheData=null;
		try{
			  MemcachedClient client=new XMemcachedClient(mamCacheUrl,mamCachePort);
			  memCacheData=client.get("fld:"+key);
			  if(memCacheData==null || memCacheData.equals("")){
				  memCacheData="";
			  }
			  client.shutdown(); 
		}catch (Exception e) {
			logDetails.getException(e , logger , request);
			e.printStackTrace();
			}
		return memCacheData;
	}
	
	
	
	public String mamCacheblockList(String mamCacheUrl,int mamCachePort, String key , HttpServletRequest request ){
		String memCacheData=null;
		try{
			  MemcachedClient client=new XMemcachedClient(mamCacheUrl,mamCachePort);
			  memCacheData=client.get("blk:"+key);
			  if(memCacheData==null || memCacheData.equals("")){
				  memCacheData=""; 
			  }
			  client.shutdown(); 
		}catch (Exception e) {
			logDetails.getException(e , logger , request);
			e.printStackTrace();
			}
		return memCacheData;
	}
	
	
	public String mamCachecanSendMessages(String mamCacheUrl,int mamCachePort, String key , HttpServletRequest request){
		String memCacheData=null;
		try{
			  MemcachedClient client=new XMemcachedClient(mamCacheUrl,mamCachePort);
			  memCacheData=client.get("msg:"+key);
			  
			  if(memCacheData==null || memCacheData.equals("")){
				  memCacheData=""; 
			  }
			  
			  client.shutdown(); 
		}catch (Exception e) {
			logDetails.getException(e , logger , request);
			e.printStackTrace();
			}
		return memCacheData;
	}
	
	
	
	public String mamCacheConnectionList(String mamCacheUrl,int mamCachePort, String key , HttpServletRequest request ){
		String memCacheData=null;
		try{
			  MemcachedClient client=new XMemcachedClient(mamCacheUrl,mamCachePort);
			  memCacheData=client.get("fndupd:"+key);
			  
			  if(memCacheData==null || memCacheData.equals("")){
				  memCacheData="";
			  }
			  client.shutdown(); 
		}catch (Exception e) {
			
			logDetails.getException(e , logger , request);
			e.printStackTrace();
			}
		return memCacheData;
	}
	
	
	
}
