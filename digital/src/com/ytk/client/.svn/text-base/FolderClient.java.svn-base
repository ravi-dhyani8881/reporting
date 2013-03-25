package com.ytk.client;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Component;

import com.ytk.models.Folder;
import com.ytk.models.Place;
import com.ytk.utility.Adder;
import com.ytk.utility.LogDetails;
import com.ytk.utility.ServerurlConstants;
import com.ytk.client.DateClient;

@Component("folderClient")
public class FolderClient {
	
	private static final Logger logger = LoggerFactory.getLogger(FolderClient.class);
	
	@Autowired
	DateClient dateClient;
	
	@Autowired
	LogDetails logDetails;
	
	public Object[] queryCheck(String serverURl, String query,int pageCount,int rows , HttpServletRequest request) {
	    try {
	    	 
	    	Object[] resultArr = new Object[2];
	    	 
	    	SolrServer server =  Adder.getSolrServer(serverURl);
		    ModifiableSolrParams params = new ModifiableSolrParams();
		    params.set("q", query);
		    params.set("start", ""+pageCount);
		    params.set("version", "2.2");
		    params.set("wt", "json");
		    params.set("indent", "on");
		    params.set("rows", rows);
		    QueryResponse response = server.query(params, SolrRequest.METHOD.POST);
		    List <Folder> folderList = new ArrayList<Folder>();
		    for (SolrDocument doc : response.getResults())
            {	
		    	Folder folder=new Folder();
		    	folder.setID(doc.getFieldValue("ID").toString());
		    	folder.setMemberID(doc.getFieldValue("MemberID").toString());
		    	folder.setName(doc.getFieldValue("Name").toString());
		    	folder.setFolderType(doc.getFieldValue("FolderType").toString());
		    	folder.setConnectionCount(doc.getFieldValue("ConnectionCount").toString());
		    	folder.setIsDeleted(doc.getFieldValue("IsDeleted").toString());
		    	folder.setCreatedDate(dateClient.addDateFromSolrToServiceforPdate(doc.getFieldValue("CreatedDate") , request));
		    	folder.setUpdatedDate(dateClient.addDateFromSolrToServiceforPdate(doc.getFieldValue("UpdatedDate") , request));
		    	folder.setOldID(doc.getFieldValue("OldID").toString());
		    	folderList.add(folder);
            }
		        String   numFound = response.getResults().getNumFound()+"";
		        resultArr[0] = folderList;
		        resultArr[1] = numFound;
	            
	    return  resultArr;  
    } catch (SolrServerException e) {
    	logDetails.getException(e , logger , request);
    	
        throw new DataAccessResourceFailureException(e.getMessage(), e);
    }
	}

}
