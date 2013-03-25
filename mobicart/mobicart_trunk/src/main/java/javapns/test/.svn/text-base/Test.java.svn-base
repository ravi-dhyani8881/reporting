package javapns.test;

import java.util.ArrayList;
import java.util.List;

import com.mobicart.model.AppDeviceTokensExample.Criteria;
import com.mobicart.util.PathLocator;

import javapns.back.PushNotificationManager;
import javapns.back.SSLConnectionHelper;
import javapns.data.Device;
import javapns.data.PayLoad;
import javapns.data.PayLoadCustomAlert;

public class Test {

	
	
	
	
	
	@SuppressWarnings("unchecked")
	public static void send() {
		try { 
			PathLocator locator=new PathLocator();
			String certPath=locator.getRealPath()+"WEB-INF/classes/ck_java.p12";
			PushNotificationManager.getInstance().initializeConnection("gateway.sandbox.push.apple.com", 2195, certPath, "1", SSLConnectionHelper.KEYSTORE_TYPE_PKCS12);
			
			// Get PushNotification Instance
			PushNotificationManager pushManager = PushNotificationManager.getInstance();
			
			// Link iPhone's UDID (64-char device token) to a stringName 
			pushManager.addDevice("my_iPhone", "808d5b5e3231186b2844d956ea79828837f9b95c8b61c76d1a2fcbd97e68ff2c");
			
			// Create a simple PayLoad with a simple alert
			PayLoad simplePayLoad = new PayLoad();
			simplePayLoad.addAlert("Mobicart alert messahe kaminee why wowwwwww");
			simplePayLoad.addBadge(49);
			simplePayLoad.addSound("default");
			
				
			
			
			
			Device client = PushNotificationManager.getInstance().getDevice("my_iPhone");
			
			
			PushNotificationManager.getInstance().sendNotification(client, simplePayLoad);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}