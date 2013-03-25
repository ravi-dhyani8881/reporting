package com.ytk.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ytk.models.Faces;
import com.ytk.models.Messages;
import com.ytk.models.PCommunity;
import com.ytk.utility.Adder;
import com.ytk.utility.ServerurlConstants;

@Controller
@RequestMapping("/searchmessages/*")
public class SearchMessagesController {
	
	@Autowired
	ServerurlConstants serverurlConstants;
	/**
	 * Service to Search Messages with Relevance
	 * @param member_id
	 * @return a List of Updates in response
	 */

	@RequestMapping(value = "/findmessages")
	public  ModelAndView findUpdates(
			@RequestParam(value = "Message", required = false) String AccountID,
			@RequestParam(value = "Page", required = false) int Page,
			@RequestParam(value = "Size", required = false) int Size,
			@RequestParam(value = "Id", required = false) String ID){
			ModelAndView mav = new ModelAndView();
			List messageList = null;
			Object[] resultArr = null;
			String numFound  = "0";
			String queryText = "((SenderName:"+AccountID+"*) OR (ReceiverName:"+AccountID+"*)  OR (Subject:"+AccountID+"*) AND (SenderID:"+ID+" OR ReceiverID:"+ID+") AND (IsReceiverDeleted:0) )";
			resultArr = this.fetchMessages(queryText,Page,Size);
			
			numFound    = resultArr[1].toString();
			messageList = (List)(resultArr[0]) ;
			
			mav.addObject("TotalRecords",numFound);
			mav.addObject("StatusOutput","0");
			mav.addObject("Collection",messageList);
			return mav;	
	}	
	
	
	/***
	 * Service to ADD Messages
	 * @param ID
	 * @param ThreadID
	 * @param SenderID
	 * @param SenderName
	 * @param SenderScreenName
	 * @param SenderType
	 * @param SenderProfileImage
	 * @param ReceiverID
	 * @param ReceiverName
	 * @param ReceiverScreenName
	 * @param ReceiverType
	 * @param ReceiverProfileImage
	 * @param Subject
	 * @param Description
	 * @param IsGroupMessage
	 * @param IsReceiverRead
	 * @param IsSenderDeleted
	 * @param IsReceiverDeleted
	 * @param IsReplied
	 * @param CreatedDate
	 * @param UpdatedDate
	 * @return
	 */
	
	@RequestMapping(value = "/addmessage")
	public ModelAndView addConnection(
			@RequestParam(value = "ID", required = false) String ID,
			@RequestParam(value = "ThreadID", required = false) String ThreadID,
			@RequestParam(value = "SenderID", required = false) String SenderID,
			@RequestParam(value = "SenderName", required = false) String SenderName,
			@RequestParam(value = "SenderScreenName", required = false) String SenderScreenName,
			@RequestParam(value = "SenderType", required = false) String SenderType,
			@RequestParam(value = "SenderProfileImage", required = false) String SenderProfileImage,
			@RequestParam(value = "ReceiverID", required = false) String ReceiverID,
			@RequestParam(value = "ReceiverName", required = false) String ReceiverName,
			@RequestParam(value = "ReceiverScreenName", required = false) String ReceiverScreenName,
			@RequestParam(value = "ReceiverType", required = false) String ReceiverType,
			@RequestParam(value = "ReceiverProfileImage", required = false) String ReceiverProfileImage,
			@RequestParam(value = "Subject", required = false) String Subject,	
			@RequestParam(value = "Description", required = false) String Description,	
			@RequestParam(value = "IsGroupMessage", required = false) String IsGroupMessage,	
			@RequestParam(value = "IsReceiverRead", required = false) String IsReceiverRead,	
			@RequestParam(value = "IsSenderDeleted", required = false) String IsSenderDeleted,	
			@RequestParam(value = "IsReceiverDeleted", required = false) String IsReceiverDeleted,	
			@RequestParam(value = "IsReplied", required = false) String IsReplied,				
			@RequestParam(value = "CreatedDate", required = false) String CreatedDate,
			@RequestParam(value = "UpdatedDate", required = false) String UpdatedDate)
			{
		
		ModelAndView mav = new ModelAndView();
		SolrInputDocument messages = new SolrInputDocument();
		messages.addField("ID", ID);
		messages.addField("ThreadID", ThreadID);
		messages.addField("SenderID", SenderID);
		messages.addField("SenderName", SenderName);
		messages.addField("SenderScreenName", SenderScreenName);
		messages.addField("SenderType", SenderType);
		messages.addField("SenderProfileImage", SenderProfileImage);
		messages.addField("ReceiverID", ReceiverID);
		messages.addField("ReceiverName", ReceiverName);
		messages.addField("ReceiverScreenName", ReceiverScreenName);
		messages.addField("ReceiverType", ReceiverType);
		messages.addField("ReceiverProfileImage", ReceiverProfileImage);
		messages.addField("Subject", Subject);
		messages.addField("Description", Description);
		messages.addField("IsGroupMessage", IsGroupMessage);
		messages.addField("IsReceiverRead", IsReceiverRead);
		messages.addField("IsSenderDeleted", IsSenderDeleted);
		messages.addField("IsReceiverDeleted", IsReceiverDeleted);
		messages.addField("IsReplied", IsReplied);
		messages.addField("CreatedDate", CreatedDate);
		messages.addField("UpdatedDate", UpdatedDate);
		Adder.addMessages(serverurlConstants.ADD_MESSAGES_URL , messages);
		String result = "success";
		mav.addObject("result",result);
		return mav;
	}
	
	/**
	 * Service to DELETE Messages
	 * @param ID
	 * @return result String
	 */
	
	@RequestMapping(value = "/deletemessage")
	public ModelAndView deleteMessage(
			@RequestParam(value = "ID", required = false) String ID)
	{
		ModelAndView mav = new ModelAndView();
		SolrInputDocument updates = new SolrInputDocument();
		updates.addField("ID", ID);	
		Adder.deleteMessages(serverurlConstants.ADD_MESSAGES_URL , ID);
		String result = "success";
		mav.addObject("result",result);
		return mav;
	}
	
	/***
	 * 
	 * @param query
	 * @param pageCount
	 * @param rows
	 * @returns the list of date sorted updates 
	 */
	private Object[] fetchMessages(String query,int pageCount,int rows) {
	    try {
	    	String url = serverurlConstants.ADD_MESSAGES_URL;
	    	SolrServer server =  Adder.getSolrServer(url);
		    ModifiableSolrParams params = new ModifiableSolrParams();
		    params.set("q", query);
		    params.set("start", ""+pageCount);
		    params.set("version", "2.2");
		    params.set("wt", "json");
		    params.set("indent", "on");
		    params.set("rows", rows);

		    QueryResponse response = server.query(params, SolrRequest.METHOD.GET);
		    

	            Object[] resultArr = new Object[2];
			    String   numFound = response.getResults().getNumFound()+"";
		        resultArr[0] = response.getResults();
		        resultArr[1] = numFound;
	            
	    return  resultArr;  
    } catch (SolrServerException e) {
        throw new DataAccessResourceFailureException(e.getMessage(), e);
    }
	}
	

}
