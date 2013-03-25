package com.mobicart.bo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javapns.back.PushNotificationManager;
import javapns.back.SSLConnectionHelper;
import javapns.data.Device;
import javapns.data.PayLoad;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.xml.transform.stream.StreamSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.castor.CastorMarshaller;
import org.springframework.web.client.RestTemplate;
import com.mobicart.geo.Geocode;
import com.mobicart.geo.GeocodeResponse;
import com.mobicart.model.App;
import com.mobicart.model.AppDeviceTokens;
import com.mobicart.model.PushNotification;
import com.mobicart.service.AppService;
import com.mobicart.util.PathLocator;
import com.mobicart.util.ResourceProperties;

/**
 * Push notification helper class
 * @author jasdeep.singh
 *
 */
 
public class PushNotificationBO  {

	/**
	 * {@link Logger}
	 */
	private static final Logger logger = LoggerFactory.getLogger(PushNotificationBO.class);

	/**
	 * {@link AppService} instance
	 */
	@Autowired
	AppService appService;
	
	/**
	 * Default notify count
	 */
	private  Integer NOTIFY_COUNT=2;
	
	
	
	/**
	 * Send notifications to iPhone Or iPad devices
	 * @throws Exception
	 */
 
	public void sendNotifications() throws Exception {
		//System.out.println("appService:"+appService); 
		/**
		 * Fetch Notify count from properties files
		 */
		try{
			NOTIFY_COUNT = Integer.parseInt(ResourceProperties.getString("PUSH_NOTIFICATION_NOTIFY_COUNT"));
		}catch (Exception e) {
			logger.error("notify count error {}",e.getMessage());
		}
		
		logger.info("Notify count:{}",NOTIFY_COUNT);
		
		
		List<PushNotification> completed_notification = null;
		List<AppDeviceTokens> device_tokens = null;

		// get all pending notifications //
		List<PushNotification> pending_notification = appService.getNotificationsByStatus("pending");
           
		logger.info(" pending notifications to be sent :{}",pending_notification.size());
		
		Long appId = 0L;
		Calendar cal = null;
		Date notificationDate = null;

		PushNotificationManager pushManager = null;
		PayLoad simplePayLoad = null;
		Device client = null;

		int notificationCount=0;
		int androidnotificationCount=0;
		
		// iterate through notification
		for (PushNotification notification : pending_notification) {
			// get completed notifications for app within last 24hrs
			cal = Calendar.getInstance();
			cal.add(Calendar.HOUR, -24);
			notificationDate = new Date(cal.getTimeInMillis());

			// get completed notifications
			completed_notification = appService.getAppNotificationsAfterDate(
					notificationDate, appId, "pending");

			if (completed_notification != null
					&& completed_notification.size() >= NOTIFY_COUNT) {
				// skip records as notifuy count has exceeded
				logger.debug("Skip for record no {}  of app {}", notification.getId(), notification.getAppId());
				continue;
			}

			/**
			 * if notification messages is marked sent to all
			 */
			if (notification.getSendTo().equalsIgnoreCase("A")) { 
				
				logger.debug("Fetching all devices for notification {}, app id {}", notification.getId(),notification.getAppId());
				/** get all devices **/
				device_tokens = appService.getDevicesByApp(notification.getAppId());

			} else { 
				/**
				 *  if notification is marked to be sent in radius
				 */
				device_tokens = new ArrayList<AppDeviceTokens>();

				logger.debug("Fetching specific devices for notification {}, app id {}", notification.getId(),notification.getAppId());

				Float actualRadius = 0f;
				Double latitude = 0d;
				Double longitude = 0d;
				try {
				
					String zipCode = notification.getZipcode();
					int radius = notification.getRadius()!=null?notification.getRadius():1;// get area of KM or miles
					String units = notification.getUnits()	;// get KM or units
					

				if (units.equalsIgnoreCase("M")) {// convert Miles into KM
					actualRadius = 1.6f * radius;
				}

				
					// get latitude of zip
				if(zipCode!=null&&zipCode.length()>2)
				{
					ArrayList<Double> points = getLatitude(zipCode);
					if(points!=null && points.size()>1){
						latitude = points.get(0);
						longitude = points.get(1);

					device_tokens = appService.getDevicesByRadius(latitude,
							longitude, String.valueOf(actualRadius), notification.getAppId());
					}else{
						logger.debug("Zip code ({})is not valid.",zipCode);
					}
				}else{
					logger.debug("Zip code ({})is not valid.",zipCode);
					
				}

				} catch (Exception e) {
					logger.error("error in sending notification in a radius {}",e.getMessage());
					continue;
				}

			}

			
			
			logger.debug("no. of devices notification will be sent: {}", device_tokens.size());

			// add devices to map to send notification
			for (AppDeviceTokens device_token : device_tokens) {
				
				appId=device_token.getAppId();
				App app=appService.findAppById(appId);//get certificate path of particular user
				
				if("ios".equals(device_token.getAppType())){
				//logger.info(device_token.getId());
				try {
					
					String certificatePath=app.getsPnCertificatePath();
					String certificatePass=app.getsPnPassword();
					//System.out.println("certificatePath:"+certificatePath+"\n App:"+app);
					if(certificatePath==null ){
						logger.debug("Skip for app {} as there is no certificate ", notification.getAppId());
						continue;
					}
					else if(certificatePass==null){
						logger.debug("Skip for app {} as there is no certificate password available :", notification.getAppId());
						continue; 	
					}
					else{
					
					PathLocator locator = new PathLocator();
					String certFullPath = locator.getContextPath()+certificatePath ;
					//logger.debug("certificate  path {}",certFullPath);
					pushManager=PushNotificationManager.getInstance();
					pushManager.initializeConnection("gateway.sandbox.push.apple.com", 2195, certFullPath,certificatePass,
							SSLConnectionHelper.KEYSTORE_TYPE_PKCS12);
					logger.debug("deviseTocken:msg:"+device_token.getTokenId()+"("+notification.getMessage()+"\n");
					simplePayLoad = new PayLoad();
					simplePayLoad.addAlert(notification.getMessage());
					simplePayLoad.addBadge(45);
					simplePayLoad.addSound("default");
					//(device name should be unique in iteration ,device tocken )
					pushManager.addDevice(device_token.getAppId() + "-"+ device_token.getTokenId(),device_token.getTokenId());					 
					client =pushManager.getDevice(device_token.getAppId() + "-"+ device_token.getTokenId());//should be unique for every iteration
					//pushManager.addDevice("iPhone",device_token.getTokenId());					 
					//client =pushManager.getDevice("iPhone"); 
					//logger.debug("deviseTocken:client:payload"+device_token.getTokenId()+"\n"+client+"\n"+simplePayLoad);
					pushManager.sendNotification(client, simplePayLoad);
					//logger.debug("notification sent to DEVICE:",device_token.getTokenId()+",MSG:"+notification.getMessage());
					pushManager.removeAllDevices();
					pushManager.stopConnection();
					
				 
					}
				} catch (Exception e) {
					logger.error("error in adding device{}",e);	
				}
				
				}
				else{
					/*push notification to android device*/
					sendAndroidNotification(app.getRegisteredAndroidUserEmail(),app.getRegisteredAndroidUserPass(), device_token.getTokenId(), notification.getMessage());
					androidnotificationCount++;
					logger.debug("notification sended to andoroid devices",androidnotificationCount);	
					
				} 
				
				notificationCount++;
			}

			// mark complete 
			if(notificationCount>0){
				notification.setStatus("complete");
				notification.setNotifiedCount(notificationCount);
				appService.updateNotification(notification);
				logger.debug(" {} notifications sent for app id {}",notificationCount,notification.getAppId());
			}else{
				logger.debug("no notification sent for app id {}",notification.getAppId());
			}

		}

		

	}

	
	/**
	 * Get GeoCode LatLong for zipcode
	 * @param zipcode
	 * @return ArrayList of Lat long point
	 * @throws XmlMappingException
	 * @throws IOException
	 */
	
	public ArrayList<Double> getLatitude(String zipcode) throws XmlMappingException, IOException {
		ArrayList<Double> points = new ArrayList<Double>();
		Double latitude = 0d;
		Double longitude = 0d;

		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> vars = new HashMap<String, String>();

		vars.put("address", zipcode);
		vars.put("sensor", "false");
		String result = restTemplate
				.getForObject(
						"http://maps.googleapis.com/maps/api/geocode/xml?address={address}&sensor={sensor}",
						String.class, vars);

		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"classpath:com/mobicart/geo/geoContext.xml");

		CastorMarshaller marshaller = ctx.getBean("marshaller",
				CastorMarshaller.class);

		StreamSource s = new StreamSource(new StringReader(result));
		GeocodeResponse response = (GeocodeResponse) marshaller.unmarshal(s);

		for (Geocode code : response.getGeocodes()) {

			latitude = code.getGeometry().getLocation().getLatitude();
			longitude = code.getGeometry().getLocation().getLongitude();

			points.add(latitude);
			points.add(longitude);

		}

		return points;

	}

    
	 private boolean  CheckAndroidValidation(String[] RegistrationIDs, String SenderID, String Password, String Message)
     {
         boolean  RetValue = true;

         if (RegistrationIDs.length>0)
         {
             RetValue = false;
         }

         if (SenderID.trim() == "")
         {
             RetValue = false;
         }

         if (Password.trim() == "")
         {
             RetValue = false;
         }

         if (Message.trim() == "")
         {
             RetValue = false;
         }

         return RetValue;
     }
	 
	 
	 
	 public static String checkAuthentication(String senderID, String password)
     {
         String Array = "";
         String requstData="";
         String url = "https://www.google.com/accounts/ClientLogin?";
         Properties reponseAsList=new Properties();
         Properties  payload=new Properties();
         			payload.put("Email",senderID);
         			payload.put("Passwd",password);
         			payload.put("accountType","GOOGLE");
         			payload.put("source","Company-App-Version");
         			payload.put("service","ac2dm");
         try{
        	 /*encode the payloads*/        	
        	 Enumeration enuma=payload.keys();
        		while(enuma.hasMoreElements()){
        			Object key= enuma.nextElement();
        			requstData=requstData+URLEncoder.encode(key.toString(), "UTF-8")+"="+URLEncoder.encode(payload.get(key).toString(), "UTF-8")+"&";
        		
        		}
        	   
        	    String fullURL = url+requstData;
        		URL neturl = new URL(fullURL); 
        		HttpURLConnection connection = (HttpURLConnection) neturl.openConnection();
        		connection.setDoInput(true);  
        		connection.setDoOutput(true); 
        		connection.setRequestMethod("POST");
        		OutputStream output = null;
        		output = connection.getOutputStream();
        		
        		InputStreamReader in = new InputStreamReader((InputStream) connection.getContent());
        	    BufferedReader buff = new BufferedReader(in);
        	    String line = buff.readLine();
        	    StringBuffer text = new StringBuffer();
        	    while (line != null) {
        	        text.append(line + "\n");
        	        line = buff.readLine();
        	        if(line==null)
        	        	continue;
        	        String values[]=line.split("=");
        	        if(values!=null &&values.length>0){
        	        	reponseAsList.put(values[0], values[1]);
        	        }
        	    } 
        		 
        		}
        		catch (Exception e) {
        			 e.printStackTrace();
        		}
         if(reponseAsList.size()>0)
         {
        	return  reponseAsList.getProperty("Auth");
        	 
         }
         return Array;
     }
	 
	 
	public static String sendMessage(String registrationID, String message, String authString)
     {
		 
		    String requstData="";
		    Properties reponseAsList=new Properties();
		    /*build paylods*/
		    Properties  payload=new Properties();
			payload.put("registration_id",registrationID);
			payload.put("collapse_key","1");
			payload.put("delay_while_idle", "0");
			//payload.put("data.message",message);
			payload.put("data.payload", message);
			payload.put("data.title", "New Offer");
	   String url = "https://android.clients.google.com/c2dm/send";	   
	   
	   try{
      	 /*encode the payloads*/        	
      	 Enumeration enuma=payload.keys();
      		while(enuma.hasMoreElements()){
      			Object key= enuma.nextElement();
      			requstData=requstData+URLEncoder.encode(key.toString(), "UTF-8")+"="+URLEncoder.encode(payload.get(key).toString(), "UTF-8")+"&";
      		
      		}
      	   
      	    String fullURL = url+requstData;
      		URL neturl = new URL(url); 
      		HttpsURLConnection connection = (HttpsURLConnection) neturl.openConnection();
      		connection.setDoInput(true);  
      		connection.setDoOutput(true); 
      		connection.setRequestMethod("POST");
      		connection.setRequestProperty("Authorization", "GoogleLogin auth=" + authString);
      		connection.setRequestProperty("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");
      		//connection.setDefaultHostnameVerifier(new CustomizedHostnameVerifier() );
      		connection.setHostnameVerifier(new CustomizedHostnameVerifier() );
      		OutputStream output = null;
      		output = connection.getOutputStream();
      		output.write(requstData.getBytes());
      		InputStreamReader in = new InputStreamReader((InputStream) connection.getContent());
      	    BufferedReader buff = new BufferedReader(in);
      	    String line = buff.readLine();
      	    StringBuffer text = new StringBuffer();
      	    while (line != null) {
      	        text.append(line + "\n");
      	        line = buff.readLine();
      	      if(line==null)
  	        	continue;
      	      	String values[]=line.split("=");
      	      		if(values!=null &&values.length>0){
      	      				reponseAsList.put(values[0], values[1]);
      	      			}
      	    	}  
      		}
      		catch (Exception e) {
      			 e.printStackTrace();
      		}	    
	   if(reponseAsList.size()>0)
       {
      	return  reponseAsList.getProperty("id");
      	 
       }
	   
		 return null;
     }
	 private static class CustomizedHostnameVerifier implements HostnameVerifier {
		 public boolean verify(String hostname, SSLSession session) {
		 return true;
		 }
		 }
	 
	 public void  sendAndroidNotification(String marketuseremail,String marketuserpass,String device_token,String msg){
		 
		 if(marketuseremail!=null&&marketuserpass!=null&&device_token!=null){
		 String Authtoken=checkAuthentication(marketuseremail,marketuserpass);
			//System.out.println("token:"+token);
			sendMessage(device_token, msg, Authtoken);
			logger.debug("Notification successfully sended to ..");
		 }
		 else{
			 
			 logger.debug("invalid parameters for android push notification..");
		 }
	 }
 
	
}
