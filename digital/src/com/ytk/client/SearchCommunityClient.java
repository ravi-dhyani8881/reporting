package com.ytk.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.ytk.models.ListCommunity;
import com.ytk.models.ListCommunity.Communities;
import com.ytk.models.ListCommunity.CommunitiesOut;
import com.ytk.utility.LogDetails;


@Component("searchCommunityClient")
public class SearchCommunityClient {

	private static final Logger logger = LoggerFactory.getLogger(SearchCommunityClient.class);
	
	@Autowired
	LogDetails logDetails;
	
	public String totalRecord  = "0";
	
	public ListCommunity getCommunity(String CommunityName,Number limit,int rows)
	{
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("CommunityName", CommunityName);
		vars.put("setLimit", limit.toString());
		vars.put("setRows", rows+"");
		String result = restTemplate
		.getForObject(
				"http://localhost:8985/solr/select/?q={CommunityName}&version=2.2&start={setLimit}&rows={setRows}&indent=on&wt=json",
				String.class, vars);
		ListCommunity communityList = new Gson().fromJson(result, ListCommunity.class);
		return communityList;
		
	}
	
	public List<CommunitiesOut> CreateListing(List<Communities> fullCommunity)
	{
		List<CommunitiesOut> outCommunities = new ArrayList<CommunitiesOut>();
		CommunitiesOut community = null;
		for (Communities communityComing : fullCommunity)
		{
			community = new CommunitiesOut();
			community.setID(communityComing.getId());
			community.setName(communityComing.getNm());
			community.setSafeName(communityComing.getSfn());
			community.setAddress1(communityComing.getAdd1());
			community.setCity(communityComing.getCt());
			community.setState(communityComing.getSt());
			community.setZipCode(communityComing.getZ());
			community.setPhone(communityComing.getPh());
			community.setWeb(communityComing.getWeb());
			community.setImageName(communityComing.getImg());
			community.setParentCategoryList(communityComing.getPcat());
			community.setSubCategoryList(communityComing.getScat());
			community.setNeighborhoodList(communityComing.getNeigh());
			community.setRating(communityComing.getTrat());
			community.setTotalReviews(communityComing.getTrev());
			if(communityComing.getScat() != "")
			{
				String countSun = communityComing.getScat();
				String[] countSunArr =  countSun.split(",");
				if(countSunArr.length == 1 && countSun.length() > 0)
				{
					community.setSubCategoryCount("1");
				}
				else
				{
					if(countSunArr.length == 1)
					community.setSubCategoryCount("0");
					else
					community.setSubCategoryCount(""+countSunArr.length);	
				}
			}
			else
			{
				community.setSubCategoryCount("0");
			}
			
			if(communityComing.getPcat() != "")
			{
				String countPar = communityComing.getPcat();
				String[] countParArr =  countPar.split(",");
				if(countParArr.length == 1 && countPar.length() > 0)
				{
					community.setParentCategoryCount("1");
				}
				else
				{
					if(countParArr.length == 1)
					community.setParentCategoryCount("0");
					else
					community.setParentCategoryCount(""+countParArr.length);	
				}
			}
			else
			{
				community.setParentCategoryCount("0");
			}
			
			if(communityComing.getNeigh() != "")
			{
				String countNeigh = communityComing.getNeigh();
				String[] countNebArr =  countNeigh.split(",");
				if(countNebArr.length == 1 && countNeigh.length() > 2)
				{
					community.setNeighborhoodCount("1");
				}
				else
				{
					if(countNebArr.length == 1)
					community.setNeighborhoodCount("0");
					else
					community.setNeighborhoodCount(""+countNebArr.length);	
				}
			}
			else
			{
				community.setNeighborhoodCount("0");
			}
			
			community.setTotalRecords(""+totalRecord);
			outCommunities.add(community);
		}
		return outCommunities;
	}
}
