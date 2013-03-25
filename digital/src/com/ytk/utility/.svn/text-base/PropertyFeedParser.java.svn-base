/*package com.jhProperty.utility;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.jhProperty.db.dao.AgentDAO;
import com.jhProperty.db.dao.PropertyAgentDAO;
import com.jhProperty.db.dao.PropertyDAO;
import com.jhProperty.db.dao.PropertyPictureDAO;
import com.jhProperty.db.pojo.Agent;
import com.jhProperty.db.pojo.Property;
import com.jhProperty.db.pojo.PropertyAgent;
import com.jhProperty.db.pojo.PropertyPicture;

public class PropertyFeedParser {
	
	public static void main(String args[]){
		
		PropertyFeedParser feed=new PropertyFeedParser();
		feed.parseXmlFile("http://131.103.92.4:8080/kbs-jhprope/propertyData.xml");
		
		
	}
	
	*//**
	 * 
	 * @param url
	 * @return
	 *//*
	public Document parseXmlFile(String url){
		Document dom=null;
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {			
			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();			
			
			try{
			dom=db.parse(url);
			
			List props=parseProperties(dom);
			
			processSoldProperties(props);
			
			insertProperties(props);
			
			updatePropertiesTextData(props);
			
			//updateProperties(props);
			
			//updatePropertiesTotalBathRooms(props);
			
			//this.updatePropertiesSquareFeet(props);
			
			//Delete's irrelevant region's.
			//PropertyDAO.getFromApplicationContext(ApplicationContextUtil.context).deleteIrrelevantRegions();
			
			}catch(Exception ee){				
				ee.printStackTrace();
			}

		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return dom;
	}
	
	public List returnPropListFromXmlUrl(String url){
		Document dom=null;
		List props = new ArrayList();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {			
			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();			
			
			try{
			dom=db.parse(url);
			
			props=parseProperties(dom);
			
			}catch(Exception ee){				
				ee.printStackTrace();
			}

		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return props;
	}
	
	
	public void processFeed(List propList){
		
		processSoldProperties(propList);
		insertProperties(propList);
		updatePropertiesSquareFeet(propList);
		updatePropertiesTextData(propList);
		
		
		deletePropertiesWithoutAgentAgencyInfo();
		//insertProperties(propList);
		
		//updateProperties(props);
		//updatePropertiesTotalBathRooms(props);
		//Delete's irrelevant region's.
		//PropertyDAO.getFromApplicationContext(ApplicationContextUtil.context).deleteIrrelevantRegions();
	}
	
	
	public void deletePropertiesWithoutAgentAgencyInfo(){
		
		//List<Property> propertyList = PropertyDAO.getFromApplicationContext(ApplicationContextUtil.context).findAll();
		List<Property> propertyList = PropertyDAO.getFromApplicationContext(ApplicationContextUtil.context).findPropertyExceptOwnerProperty();
			
				if(!propertyList.isEmpty()){
					for(Property p : propertyList){
						
						List<PropertyAgent>	propertyAgentList = PropertyAgentDAO.getFromApplicationContext(ApplicationContextUtil.context).findByProperty("property.propId", p.getPropId());
						
						if(propertyAgentList.isEmpty()){
							PropertyDAO.getFromApplicationContext(ApplicationContextUtil.context).deleteById(p);
						}
						
					}
				}
	}
	
	private List parseProperties(Document dom){
		
		
		List properties=new ArrayList();
		Property prop=null;
		
		if(dom!=null){
			try{
				
		//get the root elememt
		Element docEle = dom.getDocumentElement();	
		
		NodeList message=docEle.getElementsByTagName("property");
		
		
		
		mainLoop:
		for(int i=0;i<message.getLength();i++){
			try{
			prop=null;
			prop=new Property();
			prop.setPropIsFeatured("No");
			prop.setListedOn(new Date());
			
			prop.setNumBedrooms(0);
			prop.setNumFullBathrooms(0);
			prop.setNumHalfBathrooms(0);
			prop.setPropComesFrom("F");
			prop.setLotSize(0F);
			
			
			NodeList cnodes=message.item(i).getChildNodes();			
			for(int j=0;j<cnodes.getLength();j++){
				
				
				
				Node n=cnodes.item(j);
				if(n.getNodeType()==1){	
					Element e=(Element)n;
					
					if(e.getNodeName().equalsIgnoreCase("idnumber")){					
						prop.setFeedPropId(e.getTextContent().trim());
					}else if(e.getNodeName().equalsIgnoreCase("location")){
						
						NodeList itemChilds=n.getChildNodes();
						
						for(int k=0;k<itemChilds.getLength();k++){
							Node itemNodes=itemChilds.item(k);
							if(itemNodes.getNodeType()==1){
								Element e1=(Element)itemNodes;	
								
									if(e1.getNodeName().equalsIgnoreCase("region")){
										String region = e1.getTextContent().trim();
										String regionNames = "'Other Idaho','Star Valley, WY','Surrounding Wyoming','Teton Valley, ID'";
										
										
										if(!region.equalsIgnoreCase("Other Idaho") &&
										   !region.equalsIgnoreCase("Star Valley, WY") &&
										   !region.equalsIgnoreCase("Surrounding Wyoming") &&
										   !region.equalsIgnoreCase("Teton Valley, ID") &&
										   !region.equalsIgnoreCase("Driggs Area") &&
										   !region.equalsIgnoreCase("Dubois/Fremont County, WY") &&
										   !region.equalsIgnoreCase("N. Sublette County, WY") &&
										   !region.equalsIgnoreCase("Other") &&
										   !region.equalsIgnoreCase("Star Valley/Lincoln County, WY") &&
										   !region.equalsIgnoreCase("Swan Valley Area") &&
										   !region.equalsIgnoreCase("Tetonia Area") &&
										   !region.equalsIgnoreCase("Victor Area") &&
										   !region.equalsIgnoreCase("West Side Teton Valley")
										   ){
											prop.setRegion(region);
										}else{
											continue mainLoop;
										}
									}else if(e1.getNodeName().equalsIgnoreCase("street-address") ){
										prop.setStreetAddress(e1.getTextContent().trim());
									}else if(e1.getNodeName().equalsIgnoreCase("city-name") ){
										prop.setCityName(e1.getTextContent().trim());
									}else if(e1.getNodeName().equalsIgnoreCase("state-code") ){
										prop.setStateCode(e1.getTextContent().trim());
									}else if(e1.getNodeName().equalsIgnoreCase("zipcode") ){
										prop.setZipcode(e1.getTextContent().trim());
									}else if(e1.getNodeName().equalsIgnoreCase("longitude") ){
										prop.setLongitude(jhPropertyStringUtil.convertStringToFloat(e1.getTextContent().trim()));
									}else if(e1.getNodeName().equalsIgnoreCase("latitude") ){
										prop.setLatitude(jhPropertyStringUtil.convertStringToFloat(e1.getTextContent().trim()));
									}
							}
						}
						
					}
					
					else if(e.getNodeName().equalsIgnoreCase("details")){
						
						NodeList itemChilds=n.getChildNodes();
						for(int k=0;k<itemChilds.getLength();k++){
							Node itemNodes=itemChilds.item(k);
							if(itemNodes.getNodeType()==1){
								Element e1=(Element)itemNodes;	
								
									if(e1.getNodeName().equalsIgnoreCase("name")){
										prop.setPropName(e1.getTextContent().trim());
									}else if(e1.getNodeName().equalsIgnoreCase("status")){
										prop.setPropStatus(e1.getTextContent().trim());
									}else if(e1.getNodeName().equalsIgnoreCase("price")){
										prop.setPropPrice(jhPropertyStringUtil.replaceEmptyStringWithDouble(e1.getTextContent().trim()));
									}else if(e1.getNodeName().equalsIgnoreCase("num-bedrooms")){
										prop.setNumBedrooms(jhPropertyStringUtil.replaceEmptyStringWithInteger(e1.getTextContent().trim()));	
									}else if(e1.getNodeName().equalsIgnoreCase("num-full-bathrooms")){
										prop.setNumFullBathrooms(jhPropertyStringUtil.replaceEmptyStringWithInteger(e1.getTextContent().trim()));
									}else if(e1.getNodeName().equalsIgnoreCase("num-half-bathrooms")){
										prop.setNumHalfBathrooms(jhPropertyStringUtil.replaceEmptyStringWithInteger(e1.getTextContent().trim()));										
									}else if(e1.getNodeName().equalsIgnoreCase("total-bathrooms")){
										prop.setTotalBathrooms(jhPropertyStringUtil.replaceEmptyStringWithFloat(e1.getTextContent().trim()));										
									}else if(e1.getNodeName().equalsIgnoreCase("lot-size")){
										prop.setLotSize(Float.parseFloat((e1.getTextContent().trim())));
									}else if(e1.getNodeName().equalsIgnoreCase("square-feet")){
										prop.setSquareFeet(jhPropertyStringUtil.replaceEmptyStringWithFloat(e1.getTextContent().trim()));
									}else if(e1.getNodeName().equalsIgnoreCase("hide-price")){
										prop.setPropHidePrice(e1.getTextContent().trim());
									}else if(e1.getNodeName().equalsIgnoreCase("property-type")){
										prop.setPropType(e1.getTextContent().trim());
									}else if(e1.getNodeName().equalsIgnoreCase("attributes")){
										prop.setAttributes(e1.getTextContent().trim());
									}else if(e1.getNodeName().equalsIgnoreCase("amenities")){
										prop.setAmenities(e1.getTextContent().trim());
									}else if(e1.getNodeName().equalsIgnoreCase("features")){
										prop.setFeatures(e1.getTextContent().trim());
									}else if(e1.getNodeName().equalsIgnoreCase("description")){
										prop.setDescription(e1.getTextContent().trim());
									}else if(e1.getNodeName().equalsIgnoreCase("mlsId")){
										prop.setMlsId(e1.getTextContent().trim());
									}
							}
						}
						
						
						
					}else if(e.getNodeName().equalsIgnoreCase("pictures") ){
						
						HashSet pics=new HashSet(); 
						NodeList itemChilds=n.getChildNodes();
						
						//get picture
						for(int k=0;k<itemChilds.getLength();k++){
							Node itemNodes=itemChilds.item(k);
							if(itemNodes.getNodeType()==1){
								Element e1=(Element)itemNodes;
								
								if(e1.getNodeName().equalsIgnoreCase("picture")){
								
										NodeList itemChilds1=itemNodes.getChildNodes();
										
										//get picture-URL
										for(int kk=0;kk<itemChilds1.getLength();kk++){
											Node itemNodes1=itemChilds1.item(kk);
											if(itemNodes1.getNodeType()==1){
												Element e11=(Element)itemNodes1;
												
												if(e11.getNodeName().equalsIgnoreCase("picture-url")){
													pics.add(e11.getTextContent().trim());
												}
											}
										}
								}
								
							}
						}
						prop.setPropertyPictures(pics);
					}else if(e.getNodeName().equalsIgnoreCase("agent-idnumber") ){
						prop.setFeedAgentId(e.getTextContent().trim());
					}
				}
			}
			if(prop != null){
				//if(prop.getMlsId().equals("10-2062")){
				properties.add(prop);
				//}
			}
			//PropertyDAO.getFromApplicationContext(ApplicationContextUtil.context).save(prop);
		}catch(Exception e){
			e.printStackTrace();
		}
			
		}	
		
		
			}catch(Exception e){
				e.printStackTrace();
			}
		
		}
		return properties;
	}
	

	private void processSoldProperties(List <Property>props){
		List l=null;
		Set set=null;
		
		PropertyPicture pic=null;
		
		PropertyAgent pagent=null;
		Agent agent=null;
		String imagesFolder = "jhpropertydocs/jhproperty_prop";
		PathLocator locator = new PathLocator();	
		
		
		String mlsIdNotIn = "'0'";
		
		for(Property prop:props){
			mlsIdNotIn = mlsIdNotIn + ",'"+prop.getMlsId()+"'";
		}
		
		List<Property> soldPropertiesList = PropertyDAO.getFromApplicationContext(ApplicationContextUtil.context).findByMlsIdNotIn(mlsIdNotIn);
		
		
		String fileNameWithFullPath = "";
		
				if(soldPropertiesList != null && (!soldPropertiesList.isEmpty())){
					
							for(Property prop : soldPropertiesList){
								
								if(prop.getPropIsFeatured().equalsIgnoreCase("yes")){
									//update Archived field.Archive this property.And send mail.
									//if(!jhPropertyStringUtil.replaceNullStringWithEmpty(prop.getPropStatus()).equalsIgnoreCase("Sold")){
										prop.setPropStatus("Sold");
										PropertyDAO.getFromApplicationContext(ApplicationContextUtil.context).updatePropertyStatus(prop);
										//sendMailWhenSold(prop);
									//}
								}else{
									//Deleteproperties
									List<PropertyPicture> picList = PropertyPictureDAO.getFromApplicationContext(ApplicationContextUtil.context).findByProperty("property.propId", prop.getPropId());
									
									if(picList != null && (!picList.isEmpty())){
										for(PropertyPicture pp : picList){
											fileNameWithFullPath = locator.getContextPath() + pp.getPropPictureUrl();
											try{
											//jhPropertyStringUtil.deleteFile(fileNameWithFullPath);
											}catch (Exception e) {
												e.printStackTrace();
											}
										}
										
									}
									PropertyDAO.getFromApplicationContext(ApplicationContextUtil.context).deleteById(prop);
								}
							
							
							}
					
				}
	}
	
	
	
	private void insertProperties(List <Property>props){
		List l=null;
		Set set=null;
		
		PropertyPicture pic=null;
		
		PropertyAgent pagent=null;
		Agent agent=null;
		String imagesFolder = "jhpropertydocs/jhproperty_prop";
		PathLocator locator = new PathLocator();	
		
		
		for(Property prop:props){
			
			l=null;
			//check for existing property
			l=PropertyDAO.getFromApplicationContext(ApplicationContextUtil.context).findByField(" model.propStatus ","feedPropId", prop.getFeedPropId());
		
			if(l!=null&&l.size()>=1){	
				Property propertyObj = (Property)l.get(0);
				
				if(prop.getPropStatus().equalsIgnoreCase("Sold")){
					//update
					propertyObj.setPropStatus("Sold");
					PropertyDAO.getFromApplicationContext(ApplicationContextUtil.context).updatePropertyStatus(propertyObj);
					
					//sendMailWhenSold(propertyObj);
				}
				
				List ll=AgentDAO.getFromApplicationContext(ApplicationContextUtil.context).findByProperty("feedAgentId", propertyObj.getFeedAgentId());
				
				if(ll!=null&&ll.size()>=1){
					
					agent=(Agent)ll.get(0);
					
					pagent=new PropertyAgent();
					pagent.setProperty(propertyObj);
					pagent.setAgentType("Main");
					pagent.setIsFeaturedAgent("No");
					pagent.setAgent(agent);
					
					PropertyAgentDAO.getFromApplicationContext(ApplicationContextUtil.context).save(pagent);
				}
				
				
			}else{
				set=null;
				set=prop.getPropertyPictures();
				
				//insert property
				PropertyDAO.getFromApplicationContext(ApplicationContextUtil.context).save(prop);
				
				Iterator iterator = set.iterator();
				
				while(iterator.hasNext()){
				    String remoteFilePath = (String)iterator.next();
					//String fileName = System.currentTimeMillis()+jhPropertyStringUtil.getRandomPassword()+".jpg";
					String fileName = ResizeImage.returnFileName(remoteFilePath);
					String filePath = locator.getContextPath()+imagesFolder+"/"+fileName;
					
					pic=new PropertyPicture();
					pic.setProperty(prop);
					pic.setPropPictureUrl(imagesFolder+"/"+fileName);
					pic.setImageType(0);
					//pic.setPropPictureUrl((String)iterator.next());
					
					try{
						//Saves and creates custom size image.
						ResizeImage.resizeImage(remoteFilePath,filePath, 440, 330);
						//Thread.sleep(3000);
					}catch (Exception e) {
						e.printStackTrace();
					}
					
					PropertyPictureDAO.getFromApplicationContext(ApplicationContextUtil.context).save(pic);
				}
				
				
				
				//find agent for property
				l=AgentDAO.getFromApplicationContext(ApplicationContextUtil.context).findByProperty("feedAgentId", prop.getFeedAgentId());
				
				if(l!=null&&l.size()>=1){
					
					agent=(Agent)l.get(0);
					
					pagent=new PropertyAgent();
					pagent.setProperty(prop);
					pagent.setAgentType("Main");
					pagent.setIsFeaturedAgent("No");
					pagent.setAgent(agent);
					
					PropertyAgentDAO.getFromApplicationContext(ApplicationContextUtil.context).save(pagent);
				}
				
				
				
			}
		
		}
		
		
		
	}
	
	
	private void updateProperties(List <Property>props){
		List l=null;
		Set set=null;
		
		
		
		for(Property prop:props){
			l=null;
			l=PropertyDAO.getFromApplicationContext(ApplicationContextUtil.context).findByField(" model.feedPropId ","feedPropId", prop.getFeedPropId());
		
			if(l!=null&&l.size()>=1){
				PropertyDAO.getFromApplicationContext(ApplicationContextUtil.context).updateByMlsId(prop);
			}
		
		}
		
		
		
	}
	
	public void updatePropertiesSquareFeet(List <Property>props){
		for(Property prop:props){
				PropertyDAO.getFromApplicationContext(ApplicationContextUtil.context).updateSquareFeetByMlsId(prop);
		}
	}
	
	
	public void updatePropertiesTextData(List <Property>props){
		for(Property prop:props){
				PropertyDAO.getFromApplicationContext(ApplicationContextUtil.context).updatePropertiesTextData(prop);
		}
	}
	
	private void updatePropertiesTotalBathRooms(List <Property>props){
		List l=null;
		Set set=null;
		
		for(Property prop:props){
			l=null;
			l=PropertyDAO.getFromApplicationContext(ApplicationContextUtil.context).findByField(" model.feedPropId ","feedPropId", prop.getFeedPropId());
		
			if(l!=null&&l.size()>=1){
				PropertyDAO.getFromApplicationContext(ApplicationContextUtil.context).updateTotalBathroomsByMlsId(prop);
			}
		
		}
		
		
		
	}
	
	 public String parseDocument(Document document) {
	        String ss = "";
	        try {
	            DOMSource domSource = new DOMSource(document);
	            StringWriter writer = new StringWriter();
	            StreamResult result = new StreamResult(writer);
	            TransformerFactory tf = TransformerFactory.newInstance();
	            Transformer transformer = tf.newTransformer();
	            transformer.transform(domSource, result);
	            ss = writer.toString();
	        } catch (TransformerException ex) {
	            ex.printStackTrace();

	        }
	        return ss;
	    }

	 public void sendMailWhenSold(Property property){
			EmailGenerator email = new EmailGenerator();
	        email.setFromEmail(ResourceProperties.getString("emailFrom"));	        
	        email.setToEmail(ResourceProperties.getString("emailTo"));
	        
	        
	        email.setSubject("Link for the sold property");
	        PathLocator path = new PathLocator();
	        email.setTemplateName(System.getProperty("file.separator")+path.getRealPath() + "WEB-INF/emailTemplate/MAIL_WHEN_PROPERTY_SOLD");
	        HashMap<String, String> param = new HashMap<String, String>();
	        param.put("_RECIPIENT_NAME_",ResourceProperties.getString("recepientName"));	       	        
	        param.put("_PROPERTY_NAME_", property.getPropName());
	        param.put("_REGION_", property.getRegion());
	        param.put("_STREET_",property.getStreetAddress());
	        param.put("_CITY_",property.getCityName());
	        param.put("_ZIP_CODE_",property.getZipcode());
	        param.put("_LOGO_", ResourceProperties.getString("appUrl")+"jhhtml/graphics/logo_img.jpg");
	       
	        param.put("_PAGE_", ResourceProperties.getString("appUrl")+"property.do?cmd=doPropertyDetail&propid="+property.getPropId());
	        param.put("_EMAIL_FROM_", ResourceProperties.getString("emailFrom"));
	           
	        
	        email.setParam(param);
	        
	        try {
	            //send mail
	            SendMail.getFromApplicationContext(ApplicationContextUtil.context).sendMail(email);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
	 }

}


*/