package com.ytk.utility;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*public class XMLReader {

 public static void main(String argv[]) {

  try {
  File file = new File("c:\\MyXMLFile.xml");
  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
  DocumentBuilder db = dbf.newDocumentBuilder();
  Document doc = db.parse(file);
  doc.getDocumentElement().normalize();
  System.out.println("Root element " + doc.getDocumentElement().getNodeName());
  NodeList nodeLst = doc.getElementsByTagName("result");
  System.out.println("Information of all doc");

  for (int s = 0; s < nodeLst.getLength(); s++) {

    Node fstNode = nodeLst.item(s);
    
    if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
  
           Element fstElmnt = (Element) fstNode;
      NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("str");
      Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
      NodeList fstNm = fstNmElmnt.getChildNodes();
      System.out.println("First Name : "  + ((Node) fstNm.item(0)).getNodeValue());
      NodeList lstNmElmntLst = fstElmnt.getElementsByTagName("str");
      Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
      NodeList lstNm = lstNmElmnt.getChildNodes();
      System.out.println("Last Name : " + ((Node) lstNm.item(0)).getNodeValue());
    }

  }
  } catch (Exception e) {
    e.printStackTrace();
  }
 }
}*/


public class XMLReader {

 public static void main(String argv[]) {

  try {
  File file = new File("d:\\ytk\\localhost.xml");
  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
  DocumentBuilder db = dbf.newDocumentBuilder();
  Document doc = db.parse(file);
  doc.getDocumentElement().normalize();
  System.out.println("Root element " + doc.getDocumentElement().getNodeName());
  NodeList nodeLst = doc.getElementsByTagName("doc");
  System.out.println("Information of all doc"+nodeLst.getLength());
  int i = nodeLst.getLength();
  for(int x=0; x<i; x++){
	Node docs = nodeLst.item(x);
	System.out.println(docs.getNodeName());
	NodeList strLst = docs.getChildNodes();
	for(int j=0; j<strLst.getLength(); j++){
		System.out.println(strLst.item(j).getTextContent());
		
	}
  }
 
  /*(for (int s = 0; s < nodeLst.getLength(); s++) {

    Node fstNode = nodeLst.item(s);
    
    if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
  
           Element fstElmnt = (Element) fstNode;
      NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("str");
      Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
      NodeList fstNm = fstNmElmnt.getChildNodes();
      System.out.println("First Name : "  + ((Node) fstNm.item(1)).getNodeValue());
      
    }

  }*/
  } catch (Exception e) {
    e.printStackTrace();
  }
 }
}