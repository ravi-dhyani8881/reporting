package com.mobicart.util;


import com.vipan.util.ExpiringCache;

/**
 *
 * @author Siddhartha.bhatia
 * @createdOn Feb 22, 2012   
 */
public class CacheManager {


   
  private static ExpiringCache cache=null;
  
  public static void initilaizeCache(){//called only once by init servlet
       System.out.println("cache init");
       
       //timeToLive, accessTimeout, maximumCachedQuantity, timerInterval (millisecs)
       
       //month*day*hours*min*sec*milli
       //timer interval=cleaning time
       
       cache = new ExpiringCache(1*15*24*60*60*1000, 1*15*24*60*60*1000, 100*1000,1*1*1*60*60*1000);
       
   }
    
  public static void resetCache(){
	  cache.clear();
  }
   
   public static Object getFromCache(String key){
       
       Object myCachedObject = cache.recover(key);
       
       return myCachedObject;

       
   }
   
   public static void setToCache(String key,Object obj){       
       cache.admit(key, obj);
       //System.out.println("Cache Update :"+key);
        
   	   
       
   }
   
   
   public static void setToCache(String key,Object obj,long timeToLive,long timeToIdle){       
       cache.admit(key, obj,timeToLive,timeToIdle);
       //System.out.println("Cache Update :"+key);
   }
   
}

