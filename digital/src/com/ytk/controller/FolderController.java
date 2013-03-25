package com.ytk.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ytk.models.CollectionList;
import com.ytk.models.Faces;
import com.ytk.models.Folder;
import com.ytk.models.PCommunity;
import com.ytk.models.Place;
import com.ytk.utility.Adder;
import com.ytk.utility.ServerurlConstants;
import com.ytk.client.FolderClient;
import com.ytk.client.DateClient;


@Controller
@RequestMapping("/searchfolder/*")
public class FolderController {
	
	
	@Autowired
	ServerurlConstants serverurlConstants;	
	
	@Autowired
	FolderClient folderClient;	
	
	@Autowired
	DateClient dateClient;	
	
	
	@RequestMapping(value = "/addFolder")
	public ModelAndView  addFolder(
			HttpServletRequest request,
			@RequestParam(value = "ID", required = false) String ID,
			@RequestParam(value = "MemberID", required = false) String MemberID,
			@RequestParam(value = "Name", required = false) String Name,
			@RequestParam(value = "FolderType", required = false) String FolderType,
			@RequestParam(value = "ConnectionCount", required = false) String ConnectionCount,
			@RequestParam(value = "IsDeleted", required = false) String IsDeleted,
			@RequestParam(value = "CreatedDate", required = false) String CreatedDate,
			@RequestParam(value = "UpdatedDate", required = false) String UpdatedDate,
			@RequestParam(value = "OldID", required = false , defaultValue = "0") String OldID,			
			@RequestParam(value = "jsoncallback", required = false) String search){
		
		ModelAndView mav = new ModelAndView();
		SolrInputDocument folder = new SolrInputDocument();
		
		folder.addField("ID", ID);
		folder.addField("MemberID", MemberID);
		folder.addField("Name", Name);
		folder.addField("FolderType", FolderType);
		folder.addField("ConnectionCount", ConnectionCount);
		folder.addField("IsDeleted", IsDeleted);
		folder.addField("CreatedDate", dateClient.addDateToSolr(CreatedDate , request));
		folder.addField("UpdatedDate", dateClient.addDateToSolr(UpdatedDate, request));
		folder.addField("OldID", OldID);
		Adder.addFolder(serverurlConstants.ADD_FOLDER_URL ,folder);
		String result = "success";
		mav.addObject("result",result);
		return mav;
	}
	
	
	
	@RequestMapping(value = "/findFolders")
	public ModelAndView  findFolder(
			HttpServletRequest request,
			@RequestParam(value = "MemberID", required = false) String MemberID,
			@RequestParam(value = "Name", required = false) String Name,
			@RequestParam(value = "Page", required = false) int Page,
			@RequestParam(value = "Size", required = false) int Size){
		
		ModelAndView mav = new ModelAndView();
		
		List<Folder> folder = new ArrayList<Folder>();
		String queryText = "";
		Object[] resultArr = null;
		
		Name=Name.toLowerCase();
		
		queryText = "((MemberID:"+MemberID+") AND (Name:"+Name+"*))";
		
		resultArr=folderClient.queryCheck(serverurlConstants.ADD_FOLDER_URL ,queryText,Page ,Size, request);
		
		folder= (List<Folder>) resultArr[0];
		
		mav.addObject("TotalRecords",folder.size());
		mav.addObject("StatusOutput","");
		mav.addObject("IsConnected ","1");
		mav.addObject("Collection",folder);
		return mav;
	}
	
	@RequestMapping(value = "/deletefolder")
	public ModelAndView deleteFace(
			@RequestParam(value = "folderID", required = false) String FolderID)
	{
		ModelAndView mav = new ModelAndView();
		SolrInputDocument folder = new SolrInputDocument();
		folder.addField("ID", FolderID);	
		Adder.deleteFolder(serverurlConstants.ADD_FOLDER_URL , FolderID);
		String result = "success";
		mav.addObject("result",result);
		return mav;
	}
	
	
	@RequestMapping(value = "/deleteallfolder")
	public ModelAndView deleteAllFaces()
	{
		
		ModelAndView mav = new ModelAndView();
		Adder.deleteAllInstances(serverurlConstants.ADD_FOLDER_URL);
		String result = "success";
		mav.addObject("result",result);
		return mav;
	}
	

}
