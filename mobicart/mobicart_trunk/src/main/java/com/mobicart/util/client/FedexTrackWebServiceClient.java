package com.mobicart.util.client;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fedex.ws.track.v4.Address;
import com.fedex.ws.track.v4.ClientDetail;
import com.fedex.ws.track.v4.Notification;
import com.fedex.ws.track.v4.NotificationSeverityType;
import com.fedex.ws.track.v4.TrackDetail;
import com.fedex.ws.track.v4.TrackEvent;
import com.fedex.ws.track.v4.TrackIdentifierType;
import com.fedex.ws.track.v4.TrackPackageIdentifier;
import com.fedex.ws.track.v4.TrackPortType;
import com.fedex.ws.track.v4.TrackReply;
import com.fedex.ws.track.v4.TrackRequest;
import com.fedex.ws.track.v4.TrackServiceLocator;
import com.fedex.ws.track.v4.TransactionDetail;
import com.fedex.ws.track.v4.VersionId;
import com.fedex.ws.track.v4.WebAuthenticationCredential;
import com.fedex.ws.track.v4.WebAuthenticationDetail;
import com.fedex.ws.track.v4.Weight;
import com.mobicart.util.ResourceProperties;
import com.mobicart.util.ShipmentTrackDetail;

/** 
 * Demo of using the Track service with Axis 
 * to track a shipment.
 * <p>
 * com.fedex.track.stub is generated via WSDL2Java, like this:<br>
 * <pre>
 * java org.apache.axis.wsdl.WSDL2Java -w -p com.fedex.track.stub http://www.fedex.com/...../TrackService?wsdl
 * </pre>
 * 
 * This sample code has been tested with JDK 5 and Apache Axis 1.4
 */
public class FedexTrackWebServiceClient {
	//
	
	private static final Logger logger=LoggerFactory.getLogger(FedexTrackWebServiceClient.class);
	
	
	public static List<ShipmentTrackDetail>  findDetails(String trackingNumber) throws Exception {   

		/*returns list details*/
		List<ShipmentTrackDetail> shipmentTrackDetails=null;
		
		
		//
	    TrackRequest request = new TrackRequest();

        request.setClientDetail(createClientDetail());
        request.setWebAuthenticationDetail(createWebAuthenticationDetail());
        //
        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setCustomerTransactionId("java sample - Tracking Request"); //This is a reference field for the customer.  Any value can be used and will be provided in the response.
        request.setTransactionDetail(transactionDetail);
        VersionId versionId = new VersionId("trck", 4, 1, 0);
        request.setVersion(versionId);
        TrackPackageIdentifier packageIdentifier = new TrackPackageIdentifier();
        packageIdentifier.setValue(trackingNumber); // tracking number
        packageIdentifier.setType(TrackIdentifierType.TRACKING_NUMBER_OR_DOORTAG); // Track identifier types are TRACKING_NUMBER_OR_DOORTAG, TRACKING_CONTROL_NUMBER, ....
        request.setPackageIdentifier(packageIdentifier);
        request.setIncludeDetailedScans(new Boolean(true));
	    //
		try {
			// Initializing the service
			TrackServiceLocator service;
			TrackPortType port;
			service = new TrackServiceLocator();
			updateEndPoint(service);
			port = service.getTrackServicePort();
			TrackReply reply = port.track(request); // This is the call to the web service passing in a request object and returning a reply object
			//
			if (isResponseOk(reply.getHighestSeverity())) // check if the call was successful
			{
				logger.info("Tracking detail\n");
				
				shipmentTrackDetails=new ArrayList<ShipmentTrackDetail>();
				
				TrackDetail td[] = reply.getTrackDetails();
				for (int i=0; i< td.length; i++) { // package detail information
					
					ShipmentTrackDetail shipmentTrackDetail=new ShipmentTrackDetail();
					shipmentTrackDetail.setCarrierCode(td[i].getCarrierCode().getValue());
					
					shipmentTrackDetail.setStatusCode(td[i].getStatusCode());
					shipmentTrackDetail.setStatusDescription(td[i].getStatusDescription());
					
					shipmentTrackDetail.setShipTimestamp(td[i].getShipTimestamp().getTime());
					shipmentTrackDetail.setEstimatedDeliveryTimestamp(td[i].getEstimatedDeliveryTimestamp().getTime());
					
					shipmentTrackDetail.setTotalTransitDistance(td[i].getTotalTransitDistance().getValue());
					
					shipmentTrackDetail.setTrackingNumber(trackingNumber);
					shipmentTrackDetail.setTrackingNumberUnique(td[i].getTrackingNumberUniqueIdentifier());
				
					shipmentTrackDetails.add(shipmentTrackDetail);
					
					logger.info("Package # : " + td[i].getPackageSequenceNumber() 
								+ " and Package count: " + td[i].getPackageCount());
					logger.info("Tracking number: " + td[i].getTrackingNumber() 
								+ " and Tracking number unique identifier: " + td[i].getTrackingNumberUniqueIdentifier());
					logger.info("Status: " + td[i].getStatusCode() 
							+ " and description: " + td[i].getStatusDescription());
					
					if(td[i].getOtherIdentifiers() != null)
					{
						TrackPackageIdentifier[] tpi = td[i].getOtherIdentifiers();
						for (int j=0; j< tpi.length; j++) {
							logger.info(tpi[j].getType() + " " + tpi[j].getValue());
						}
					}
					print("Packaging", td[i].getPackaging());
					printWeight("Package weight", td[i].getPackageWeight());
					printWeight("Shipment weight", td[i].getShipmentWeight());
					print("Ship date & time", td[i].getShipTimestamp().getTime());
					logger.info("Destination: " + td[i].getDestinationAddress().getCity() 
							+ " " + td[i].getDestinationAddress().getPostalCode()
							+ " " + td[i].getDestinationAddress().getCountryCode());
					

					TrackEvent[] trackEvents = td[i].getEvents();
					if (trackEvents != null) {
						logger.info("Events:");
						for (int k = 0; k < trackEvents.length; k++) {
							logger.info("  Event no.: " + (k+1));
							TrackEvent trackEvent = trackEvents[k];
							if (trackEvent != null) {
								print("    Timestamp", trackEvent.getTimestamp().getTime());
								print("    Description", trackEvent.getEventDescription());
								Address address = trackEvent.getAddress();
								if (address != null) {
									logger.info(address.getCity());
									print("    City", address.getCity());
									print("    State", address.getStateOrProvinceCode());
								}
							}
						}
					}
				}
			}
			logger.info("");

			printNotifications(reply.getNotifications());

			
		} catch (Exception e) {
			logger.error("error in getting Fedex details",e);
		}
		
		return shipmentTrackDetails;
	}
	
	private static boolean isResponseOk(NotificationSeverityType notificationSeverityType) {
		if (notificationSeverityType == null) {
			return false;
		}
		if (notificationSeverityType.equals(NotificationSeverityType.WARNING) ||
			notificationSeverityType.equals(NotificationSeverityType.NOTE)    ||
			notificationSeverityType.equals(NotificationSeverityType.SUCCESS)) {
			return true;
		}
 		return false;
	}
    

	private static ClientDetail createClientDetail() {
        ClientDetail clientDetail = new ClientDetail();
        String accountNumber = getProperty("accountNumber");
        String meterNumber = getProperty("meterNumber");
        
        //
        // See if the accountNumber and meterNumber properties are set,
        // if set use those values, otherwise default them to "XXX"
        //
        if (accountNumber == null) {
        	accountNumber = "510087887"; // Replace "XXX" with clients account number
        }
        if (meterNumber == null) {
        	meterNumber = "118541445"; // Replace "XXX" with clients meter number
        }
        clientDetail.setAccountNumber(accountNumber);
        clientDetail.setMeterNumber(meterNumber);
        return clientDetail;
	}
	
	private static WebAuthenticationDetail createWebAuthenticationDetail() {
        WebAuthenticationCredential wac = new WebAuthenticationCredential();
        String key = getProperty("accountKey");
        String password = getProperty("accountPassword");
        
        //
        // See if the key and password properties are set,
        // if set use those values, otherwise default them to "XXX"
        //
        if (key == null) {
        	key = "yiPeWQ0HDcl30RFI"; // Replace "XXX" with clients key
        }
        if (password == null) {
        	password = "1V7ucLwXfR57RlXanubV8Uaxc"; // Replace "XXX" with clients password
        }
        wac.setKey(key);
        wac.setPassword(password);
		return new WebAuthenticationDetail(wac);
	}
	
	private static void printNotifications(Notification[] notifications) {
		logger.info("Notifications:");
		if (notifications == null || notifications.length == 0) {
			logger.info(" No notifications returned");
		}
		for (int i=0; i < notifications.length; i++){
			Notification n = notifications[i];
			logger.info("  Notification no. " + i + ": ");
			if (n == null) {
				logger.info("null");
				continue;
			} else {
				logger.info("");
			}
			NotificationSeverityType nst = n.getSeverity();

			logger.info("    Severity: " + (nst == null ? "null" : nst.getValue()));
			logger.info("    Code: " + n.getCode());
			logger.info("    Message: " + n.getMessage());
			logger.info("    Source: " + n.getSource());
		}
	}
	
	private static void updateEndPoint(TrackServiceLocator serviceLocator) {
		String endPoint = System.getProperty("endPoint");
		if (endPoint != null) {
			serviceLocator.setTrackServicePortEndpointAddress(endPoint);
		}
	}

	private static void print(String msg, Object obj) {
		if (msg == null || obj == null) {
			return;
		}
		logger.info(msg + ": " + obj.toString());
	}
	
	
	private static void printWeight(String msg, Weight weight) {
		if (msg == null || weight == null) {
			return;
		}
		logger.info(msg + ": " + weight.getValue() + " " + weight.getUnits());
	}

	private static String getProperty(String property){
		String returnProperty = ResourceProperties.getString(property);
		if (returnProperty == null){
			return "XXX";
		}
		return returnProperty;
	}

}
