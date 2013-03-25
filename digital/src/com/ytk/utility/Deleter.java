package com.ytk.utility;

import java.net.MalformedURLException;
import java.io.IOException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.SolrServerException;

import com.ytk.models.Users;
import com.ytk.models.Member;
import com.ytk.models.MemberEvent;
import com.ytk.models.Community;
import com.ytk.models.CommunityEvent;
import com.ytk.models.Contact;
import com.ytk.models.Forum;
import com.ytk.models.ForumEvent;
import com.ytk.models.AllSearchableObject;



public class Deleter{
	
	 
	
/*	public void delete(Object obj){
		SolrServer solrServer = null;
		
		try{
			solrServer = new CommonsHttpSolrServer("http://localhost:8983/solr");
		}catch(MalformedURLException mue){
			mue.printStackTrace();
		}
		SolrInputDocument solrDoc = new SolrInputDocument();
			
		solrDoc.setField("id", member.getMember_id());
		solrDoc.setField("member_email", member.getMember_email());
		solrDoc.setField("first_name", member.getFirst_name());
		solrDoc.setField("middle_name", member.getMiddle_name());
		solrDoc.setField("last_name", member.getLast_name());
		solrDoc.setField("display_name", member.getDisplay_name());
		solrDoc.setField("screen_name", member.getScreen_name());
		solrDoc.setField("profile_image", member.getProfile_image());
		solrDoc.setField("city", member.getCity());
		solrDoc.setField("state", member.getState());
		solrDoc.setField("country", member.getCountry());
		solrDoc.setField("updateDate", member.getUpdateDate());
		
		    
		try{
			solrServer.deleteById(memberId);
			solrServer.commit();
		}catch(SolrServerException sse){
			sse.printStackTrace();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}*/
	
	public void delete(Object obj){
		SolrServer solrServer = null;
		if(obj instanceof Member){
			try{
				solrServer = new CommonsHttpSolrServer("http://192.168.0.153:8983/solr");
			}catch(MalformedURLException mue){
				mue.printStackTrace();
			}
			try{
				Member member = (Member)obj;
				solrServer.deleteById(member.getMember_id());
				solrServer.commit(true, true);
			}catch(SolrServerException sse){
				sse.printStackTrace();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
		}else if(obj instanceof MemberEvent){
			try{
				solrServer = new CommonsHttpSolrServer("http://192.168.0.153:8983/solr");
			}catch(MalformedURLException mue){
				mue.printStackTrace();
			}
			try{
				MemberEvent memEvent = (MemberEvent)obj;
				solrServer.deleteById(memEvent.getmemberEvent_UID());
				solrServer.commit(true, true);
			}catch(SolrServerException sse){
				sse.printStackTrace();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
		}else if(obj instanceof Community){
			try{
				solrServer = new CommonsHttpSolrServer("http://192.168.0.153:8983/solr");
			}catch(MalformedURLException mue){
				mue.printStackTrace();
			}
			try{
				Community comm = (Community)obj;
				solrServer.deleteById(comm.getcommunity_Business_id());
				solrServer.commit(true, true);
			}catch(SolrServerException sse){
				sse.printStackTrace();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
		}else if(obj instanceof CommunityEvent){
			try{
				solrServer = new CommonsHttpSolrServer("http://192.168.0.153:8983/solr");
			}catch(MalformedURLException mue){
				mue.printStackTrace();
			}
			try{
				CommunityEvent commEvent = (CommunityEvent)obj;
				solrServer.deleteById(commEvent.getcommunityEvent_Business_id());
				solrServer.commit(true, true);
			}catch(SolrServerException sse){
				sse.printStackTrace();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
		}else if(obj instanceof Contact){
			try{
				solrServer = new CommonsHttpSolrServer("http://192.168.0.153:8983/solr");
			}catch(MalformedURLException mue){
				mue.printStackTrace();
			}
			try{
				Contact contact = (Contact)obj;
				solrServer.deleteById(contact.getContact_id());
				solrServer.commit(true, true);
			}catch(SolrServerException sse){
				sse.printStackTrace();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
		}else if(obj instanceof Forum){
			try{
				solrServer = new CommonsHttpSolrServer("http://192.168.0.153:8983/solr");
			}catch(MalformedURLException mue){
				mue.printStackTrace();
			}
			try{
				Forum forum = (Forum)obj;
				solrServer.deleteById(forum.getforum_ForumID());
				solrServer.commit(true, true);
			}catch(SolrServerException sse){
				sse.printStackTrace();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
		}else if(obj instanceof ForumEvent){
			try{
				solrServer = new CommonsHttpSolrServer("http://192.168.0.153:8983/solr");
			}catch(MalformedURLException mue){
				mue.printStackTrace();
			}
			try{
				ForumEvent forumEvent = (ForumEvent)obj;
				solrServer.deleteById(forumEvent.getforumEvent_TopicID());
				solrServer.commit(true, true);
			}catch(SolrServerException sse){
				sse.printStackTrace();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
		}else if(obj instanceof AllSearchableObject){
			try{
				solrServer = new CommonsHttpSolrServer("http://192.168.0.153:8983/solr");
			}catch(MalformedURLException mue){
				mue.printStackTrace();
			}
			try{
				AllSearchableObject allSearchableObject = (AllSearchableObject)obj;
				solrServer.deleteById(allSearchableObject.getmember_Member_id());
				solrServer.commit(true, true);
			}catch(SolrServerException sse){
				sse.printStackTrace();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
		}
	}
}
















