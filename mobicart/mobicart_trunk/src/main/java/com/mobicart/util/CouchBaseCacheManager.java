package com.mobicart.util;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.spy.memcached.internal.GetFuture;
import net.spy.memcached.internal.OperationFuture;

import com.couchbase.client.CouchbaseClient;
import com.mobicart.web.StoreController;


public class CouchBaseCacheManager {
	private static CouchbaseClient client = null;
	public static int EXP_TIME = 60*60*5;
 
	private static final Logger logger = LoggerFactory.getLogger(CouchBaseCacheManager.class);
	private static CouchbaseClient getCacheClient(){
		
		try{
			
				if(client==null){
						List<URI> uris = new LinkedList<URI>();
								uris.add(URI.create(ResourceProperties.getString("couchcascheurl")));
								client=new CouchbaseClient(uris,"default","");
					}
		
		}
		catch (Exception e) {
			logger.error("error while creating cache client", e);
			 
		}
		
		return client;
	}
  
	
	public static boolean setObject(String key,int expTime,Object value){
		try{
			
			  OperationFuture<Boolean> setOp = getCacheClient().set(key, expTime, value);
			  if (setOp.get().booleanValue()) {
				  logger.info("Set Succeeded");
				  return true;
			      } else {
			    	  logger.info("Set failed: "+ setOp.getStatus().getMessage());
			    	  return false;
			      }
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	} 
	public static Object getObject(String key){
		Object getObject=null;
		try{
			 
			 GetFuture getOp = getCacheClient().asyncGet(key);
			 if ((getObject = getOp.get()) != null) {
				 logger.info("Asynchronous Get Succeeded: "+ getObject);
			      } else {
			    	  logger.info("Asynchronous Get failed: "+ getOp.getStatus().getMessage());
			      }
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return getObject;
	} 
	
	public static boolean  deleteObject(String key){
		try{
			  OperationFuture<Boolean> delOp = null;
		      delOp = getCacheClient().delete(key);
		      if (delOp.get().booleanValue()) {
		    	  logger.info("Delete Succeeded");
		          return true;
		        } else {
		        	logger.info("Delete failed: " +delOp.getStatus().getMessage());
		          return false;
		        }
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	} 
	
	static{
		
		getCacheClient();
		
		try{
			
			CouchBaseCacheManager.EXP_TIME=Integer.parseInt(ResourceProperties.getString("couchcaschetime"));
			
		}
		catch (Exception e) {
			logger.info("Reading cache Time from properties",e); 
		}
	}
 
	
}
