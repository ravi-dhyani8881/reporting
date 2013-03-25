package com.ytk.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.net.URL;
import java.io.InputStream;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ytk.models.Users;
import com.ytk.models.Member;
import com.ytk.models.MemberEvent;
import com.ytk.models.Community;
import com.ytk.models.CommunityEvent;
import com.ytk.models.Contact;
import com.ytk.models.Forum;
import com.ytk.models.ForumEvent;
import com.ytk.models.AllSearchableObject;
import com.ytk.service.AddingService;
import com.ytk.service.DeletingService;

@Controller
@RequestMapping("/submit/*")
public class UserActionController {
	
	private static org.apache.log4j.Logger log = Logger.getLogger(UserActionController.class);
	
		
	@Autowired
	public AddingService addingService;
	
	@Autowired
	public DeletingService deletingService;
	
	
	
	/**
	 * Service to Add Member to the Solr Search Server
	 * 
	 * @param member_id
	 * @param member_email
	 * @param first_name
	 * @param middle_name
	 * @param last_name
	 * @param display_name
	 * @param screen_name
	 * @param profile_image
	 * @param city
	 * @param state
	 * @param country
	 * @param updateDate
	 * 
	 * @return a String Object "SuccessfullyAdded"
	 */

	@RequestMapping(value = "/addMember")
	public String addMember(
			@RequestParam(value = "member_id", required = false) String memberId,
			@RequestParam(value = "member_email", required = false) String memberEmail,
			@RequestParam(value = "first_name", required = false) String firstName,
			@RequestParam(value = "middle_name", required = false) String middleName,
			@RequestParam(value = "last_name", required = false) String lastName,
			@RequestParam(value = "display_name", required = false) String displayName,
			@RequestParam(value = "screen_name", required = false) String screenName,
			@RequestParam(value = "profile_image", required = false) String profileImage,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "country", required = false) String country,
			@RequestParam(value = "updateDate", required = false) String updateDate){
		
		
		if(memberId == null || memberId.equals("")){
			return "Required Id field is missing.";	
		}else {
			Member member = new Member();
			member.setMember_id(memberId);
			member.setMember_email(memberEmail);
			member.setFirst_name(firstName);
			member.setMiddle_name(middleName);
			member.setLast_name(lastName);
			member.setDisplay_name(displayName);
			member.setScreen_name(screenName);
			member.setProfile_image(profileImage);
			member.setCity(city);
			member.setState(state);
			member.setCountry(country);
			member.setUpdateDate(updateDate);
			
			addingService.add(member);
			
		}
		
		return "SuccessfullyAdded";
	}


	
	
	
	/**
	 * Service to Delete Member from the Solr Search Server
	 * 
	 * @param member_id
	 * 
	 * @return a String Object "SuccessfullyDeleted"
	 */

	@RequestMapping(value = "/deleteMember")
	public String deleteMember(
			@RequestParam(value = "member_id", required = false) String memberId){
		
		
		if(memberId == null || memberId.equals("")){
			return "Required Id field is missing.";	
		}else {
			Member member = new Member();
			member.setMember_id(memberId);

			deletingService.delete(member);
			
		}
		
		return "SuccessfullyDeleted";
	}
	
	
	/**
	 * Service to Add MemberEvent to the Solr Search Server
	 * 
	 * @param UID
	 * @param CreatedBy
	 * @param CreatedFor
	 * @param Event
	 * @param EventDetail
	 * @param TimeStamp
	 * 
	 * @return a String Object "SuccessfullyAdded"
	 */
	
	@RequestMapping(value = "/addMemberEvent")
	public String addMemberEvent(
			@RequestParam(value = "UID", required = false) String uId,
			@RequestParam(value = "CreatedBy", required = false) String createdBy,
			@RequestParam(value = "CreatedFor", required = false) String createdFor,
			@RequestParam(value = "Event", required = false) String event,
			@RequestParam(value = "EventDetail", required = false) String eventDetail,
			@RequestParam(value = "TimeStamp", required = false) String timeStamp){
		
		
		if(uId == null || uId.equals("")){
			return "Required Id field is missing.";	
		}else {
			MemberEvent memEvent = new MemberEvent();
			memEvent.setmemberEvent_UID(uId);
			memEvent.setmemberEvent_CreatedBy(createdBy);
			memEvent.setmemberEvent_CreatedFor(createdFor);
			memEvent.setmemberEvent_Event(event);
			memEvent.setmemberEvent_EventDetail(eventDetail);
			memEvent.setmemberEvent_TimeStamp(timeStamp);
			
			addingService.add(memEvent);
			
		}
		
		return "SuccessfullyAdded";
	}


	
	/**
	 * Service to Delete MemberEvent from the Solr Search Server
	 * 
	 * @param uid
	 * 
	 * @return a String Object "SuccessfullyDeleted"
	 */

	@RequestMapping(value = "/deleteMemberEvent")
	public String deleteMemberEvent(
			@RequestParam(value = "UID", required = false) String uId){
		
		
		if(uId == null || uId.equals("")){
			return "Required Id field is missing.";	
		}else {
			MemberEvent memEvent = new MemberEvent();
			memEvent.setmemberEvent_UID(uId);

			deletingService.delete(memEvent);
			
		}
		
		return "SuccessfullyDeleted";
	}

	
	
	/**
	 * Service to Add Community to the Solr Search Server
	 * 
	 * @param business_id
	 * @param business_name
	 * @param safe_name
	 * @param city
	 * @param city_id
	 * @param country
	 * @param member_id
	 * 
	 * @return a String Object "SuccessfullyAdded"
	 */
	
	@RequestMapping(value = "/addCommunity")
	public String addMemberEvent(
			@RequestParam(value = "business_id", required = false) String businessId,
			@RequestParam(value = "business_name", required = false) String businessName,
			@RequestParam(value = "safe_name", required = false) String safeName,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "city_id", required = false) String cityId,
			@RequestParam(value = "country", required = false) String country,
			@RequestParam(value = "member_id", required = false) String memberId){
		
		
		if(businessId == null || businessId.equals("")){
			return "Required Id field is missing.";	
		}else {
			Community comm = new Community();
			comm.setcommunity_Business_id(businessId);
			comm.setcommunity_Business_name(businessName);
			comm.setcommunity_Safe_name(safeName);
			comm.setcommunity_City(city);
			comm.setcommunity_City_id(cityId);
			comm.setcommunity_Country(country);
			comm.setcommunity_Member_id(memberId);
			
			addingService.add(comm);
			
		}
		
		return "SuccessfullyAdded";
	}

	
	/**
	 * Service to Delete Community from the Solr Search Server
	 * 
	 * @param business_id
	 * 
	 * @return a String Object "SuccessfullyDeleted"
	 */

	@RequestMapping(value = "/deleteCommunity")
	public String deleteCommunity(
			@RequestParam(value = "business_id", required = false) String businessId){
		
		
		if(businessId == null || businessId.equals("")){
			return "Required Id field is missing.";	
		}else {
			Community comm = new Community();
			comm.setcommunity_Business_id(businessId);

			deletingService.delete(comm);
			
		}
		
		return "SuccessfullyDeleted";
	}
	
	
	
	/**
	 * Service to Add CommunityEvent to the Solr Search Server
	 * 
	 * @param business_id
	 * @param SectionID
	 * @param CreatedBy
	 * @param CreatedFor
	 * @param Event
	 * @param TimeStamp
	 * 
	 * @return a String Object "SuccessfullyAdded"
	 */
	
	@RequestMapping(value = "/addCommunityEvent")
	public String addCommunityEvent(
			@RequestParam(value = "business_id", required = false) String businessId,
			@RequestParam(value = "SectionID", required = false) String sectionId,
			@RequestParam(value = "CreatedBy", required = false) String createdBy,
			@RequestParam(value = "CreatedFor", required = false) String createdFor,
			@RequestParam(value = "Event", required = false) String event,
			@RequestParam(value = "TimeStamp", required = false) String timeStamp){
		
		
		if(businessId == null || businessId.equals("")){
			return "Required Id field is missing.";	
		}else {
			CommunityEvent commEvent = new CommunityEvent();
			commEvent.setcommunityEvent_Business_id(businessId);
			commEvent.setcommunityEvent_SectionID(sectionId);
			commEvent.setcommunityEvent_CreatedBy(createdBy);
			commEvent.setcommunityEvent_CreatedFor(createdFor);
			commEvent.setcommunityEvent_Event(event);
			commEvent.setcommunityEvent_TimeStamp(timeStamp);
			
			addingService.add(commEvent);
			
		}
		
		return "SuccessfullyAdded";
	}
	
	
	

	/**
	 * Service to Delete CommunityEvent from the Solr Search Server
	 * 
	 * @param business_id
	 * 
	 * @return a String Object "SuccessfullyDeleted"
	 */

	@RequestMapping(value = "/deleteCommunityEvent")
	public String deleteCommunityEvent(
			@RequestParam(value = "business_id", required = false) String businessId){
		
		
		if(businessId == null || businessId.equals("")){
			return "Required Id field is missing.";	
		}else {
			CommunityEvent commEvent = new CommunityEvent();
			commEvent.setcommunityEvent_Business_id(businessId);

			deletingService.delete(commEvent);
			
		}
		
		return "SuccessfullyDeleted";
	}
	
	
	/**
	 * Service to Add Contact to the Solr Search Server
	 * 
	 * @param Contact_id
	 * @param member_id
	 * @param is_connected
	 * @param is_blocked
	 * @param is_message_sent
	 * 
	 * @return a String Object "SuccessfullyAdded"
	 */
	
	@RequestMapping(value = "/addContact")
	public String addContact(
			@RequestParam(value = "Connection_id", required = false) String connectionId,
			@RequestParam(value = "Contact_id", required = false) String contactId,
			@RequestParam(value = "member_id", required = false) String memberId,
			@RequestParam(value = "is_connected", required = false) String isConnected,
			@RequestParam(value = "is_blocked", required = false) String isBlocked,
			@RequestParam(value = "is_message_sent", required = false) String isMessageSent){
		
		
		if(connectionId == null || connectionId.equals("")){
			return "Required Id field is missing.";	
		}else {
			Contact contact = new Contact();
			
			contact.setConnection_id(connectionId);
			contact.setContact_id(contactId);
			contact.setMember_id(memberId);
			contact.setIs_connected(isConnected);
			contact.setIs_blocked(isBlocked);
			contact.setIs_message_sent(isMessageSent);
			
			
			addingService.add(contact);
			
		}
		
		return "SuccessfullyAdded";
	}
	
	
	

	/**
	 * Service to Delete Contact from the Solr Search Server
	 * 
	 * @param Contact_id
	 * 
	 * @return a String Object "SuccessfullyDeleted"
	 */

	@RequestMapping(value = "/deleteContact")
	public String deleteContact(
			@RequestParam(value = "Connection_id", required = false) String connectionId){
		
		
		if(connectionId == null || connectionId.equals("")){
			return "Required Id field is missing.";	
		}else {
			Contact contact = new Contact();
			
			contact.setConnection_id(connectionId);

			deletingService.delete(contact);
			
		}
		
		return "SuccessfullyDeleted";
	}


	
	
	/**
	 * Service to Add Forum to the Solr Search Server
	 * 
	 * @param ForumID
	 * @param ForumName
	 * @param TopicID
	 * @param TopicSubject
	 * @param TopicDescription
	 * @param CommentCount
	 * @param RatingCount
	 * @param RatingAverage
	 * @param PostedByMemberEmail
	 * @param PostedBy
	 * @param UpdatedDate
	 * @param CategoryID
	 * @param CategoryName
	 * 
	 * @return a String Object "SuccessfullyAdded"
	 */
	
	@RequestMapping(value = "/addForum")
	public String addForum(
			@RequestParam(value = "ForumID", required = false) String forumId,
			@RequestParam(value = "ForumName", required = false) String forumName,
			@RequestParam(value = "TopicID", required = false) String topicId,
			@RequestParam(value = "TopicSubject", required = false) String topicSubject,
			@RequestParam(value = "TopicDescription", required = false) String topicDescription,
			@RequestParam(value = "CommentCount", required = false) String commentCount,
			@RequestParam(value = "RatingCount", required = false) String ratingCount,
			@RequestParam(value = "RatingAverage", required = false) String ratingAverage,
			@RequestParam(value = "PostedByMemberEmail", required = false) String postedByMemberEmail,
			@RequestParam(value = "PostedBy", required = false) String postedBy,
			@RequestParam(value = "UpdatedDate", required = false) String updatedDate,
			@RequestParam(value = "CategoryID", required = false) String categoryId,
			@RequestParam(value = "CategoryName", required = false) String categoryName){
		
		
		if(forumId == null || forumId.equals("")){
			return "Required Id field is missing.";	
		}else {
			Forum forum = new Forum();
			
			forum.setforum_ForumID(forumId);
			forum.setforum_ForumName(forumName);
			forum.setforum_TopicID(topicId);
			forum.setforum_TopicSubject(topicSubject);
			forum.setforum_TopicDescription(topicDescription);
			forum.setforum_CommentCount(commentCount);
			forum.setforum_RatingCount(ratingCount);
			forum.setforum_RatingAverage(ratingAverage);
			forum.setforum_PostedByMemberEmail(postedByMemberEmail);
			forum.setforum_PostedBy(postedBy);
			forum.setforum_UpdatedDate(updatedDate);
			forum.setforum_CategoryID(categoryId);
			forum.setforum_CategoryName(categoryName);
			
			
			addingService.add(forum);
			
		}
		
		return "SuccessfullyAdded";
	}
	
	
	

	/**
	 * Service to Delete Forum from the Solr Search Server
	 * 
	 * @param ForumID
	 * 
	 * @return a String Object "SuccessfullyDeleted"
	 */

	@RequestMapping(value = "/deleteForum")
	public String deleteForum(
			@RequestParam(value = "ForumID", required = false) String forumId){
		
		
		if(forumId == null || forumId.equals("")){
			return "Required Id field is missing.";	
		}else {
			Forum forum = new Forum();
			
			forum.setforum_ForumID(forumId);

			deletingService.delete(forum);
			
		}
		
		return "SuccessfullyDeleted";
	}
	
	
	
	
	
	/**
	 * Service to Add ForumEvent to the Solr Search Server
	 * 
	 * @param TopicID
	 * @param SectionID
	 * @param CreatedBy
	 * @param CreatedFor
	 * @param Event
	 * @param TimeStamp
	 * 
	 * @return a String Object "SuccessfullyAdded"
	 */
	
	@RequestMapping(value = "/addForumEvent")
	public String addForumEvent(
			@RequestParam(value = "TopicID", required = false) String topicID,
			@RequestParam(value = "SectionID", required = false) String sectionID,
			@RequestParam(value = "CreatedBy", required = false) String createdBy,
			@RequestParam(value = "CreatedFor", required = false) String createdFor,
			@RequestParam(value = "Event", required = false) String event,
			@RequestParam(value = "TimeStamp", required = false) String timeStamp){
		
		
		if(topicID == null || topicID.equals("")){
			return "Required Id field is missing.";	
		}else {
			ForumEvent forumEvent = new ForumEvent();
			
			forumEvent.setforumEvent_TopicID(topicID);
			forumEvent.setforumEvent_SectionID(sectionID);
			forumEvent.setforumEvent_CreatedBy(createdBy);
			forumEvent.setforumEvent_CreatedFor(createdFor);
			forumEvent.setforumEvent_Event(event);
			forumEvent.setforumEvent_TimeStamp(timeStamp);
			
			
			
			addingService.add(forumEvent);
			
		}
		
		return "SuccessfullyAdded";
	}
	
	
	

	/**
	 * Service to Delete ForumEvent from the Solr Search Server
	 * 
	 * @param TopicID
	 * 
	 * @return a String Object "SuccessfullyDeleted"
	 */

	@RequestMapping(value = "/deleteForumEvent")
	public String deleteForumEvent(
			@RequestParam(value = "TopicID", required = false) String topicID){
		
		
		if(topicID == null || topicID.equals("")){
			return "Required Id field is missing.";	
		}else {
			ForumEvent forumEvent = new ForumEvent();
			
			forumEvent.setforumEvent_TopicID(topicID);

			deletingService.delete(forumEvent);
			
		}
		
		return "SuccessfullyDeleted";
	}



	
	
	
	
	
	/**
	 * Service to Add Member to the Solr Search Server
	 * 
	 * @param member_id
	 * @param member_email
	 * @param first_name
	 * @param middle_name
	 * @param last_name
	 * @param display_name
	 * @param screen_name
	 * @param profile_image
	 * @param city
	 * @param state
	 * @param country
	 * @param updateDate
	 * 
	 * @return a String Object "SuccessfullyAdded"
	 */

	@RequestMapping(value = "/addObject")
	public String addObject(
			
			@RequestParam(value = "community_business_id", required = false) String communityBusinessId,
			@RequestParam(value = "community_business_name", required = false) String communityBusinessName,
			@RequestParam(value = "community_safe_name", required = false) String communitySafeName,
			@RequestParam(value = "community_city", required = false) String communityCity,
			@RequestParam(value = "community_city_id", required = false) String communityCityId,
			@RequestParam(value = "community_country", required = false) String communityCountry,
			@RequestParam(value = "community_member_id", required = false) String communityMemberId,
			
			@RequestParam(value = "communityEvent_business_id", required = false) String communityEventBusinessId,
			@RequestParam(value = "communityEvent_SectionID", required = false) String communityEventSectionID,
			@RequestParam(value = "communityEvent_CreatedBy", required = false) String communityEventCreatedBy,
			@RequestParam(value = "communityEvent_CreatedFor", required = false) String communityEventCreatedFor,
			@RequestParam(value = "communityEvent_Event", required = false) String communityEventEvent,
			@RequestParam(value = "communityEvent_TimeStamp", required = false) String communityEventTimeStamp,
			
			@RequestParam(value = "contact_Contact_id", required = false) String contactContactId,
			@RequestParam(value = "contact_member_id", required = false) String contactMemberId,
			@RequestParam(value = "contact_is_connected", required = false) String contactIsConnected,
			@RequestParam(value = "contact_is_blocked", required = false) String contactIsBlocked,
			@RequestParam(value = "contact_is_message_sent", required = false) String contactIsMessageSent,
			
			@RequestParam(value = "forum_ForumID", required = false) String forumForumID,
			@RequestParam(value = "forum_ForumName", required = false) String forumForumName,
			@RequestParam(value = "forum_TopicID", required = false) String forumTopicID,
			@RequestParam(value = "forum_TopicSubject", required = false) String forumTopicSubject,
			@RequestParam(value = "forum_TopicDescription", required = false) String forumTopicDescription,
			@RequestParam(value = "forum_CommentCount", required = false) String forumCommentCount,
			@RequestParam(value = "forum_RatingCount", required = false) String forumRatingCount,
			@RequestParam(value = "forum_RatingAverage", required = false) String forumRatingAverage,
			@RequestParam(value = "forum_PostedByMemberEmail", required = false) String forumPostedByMemberEmail,
			@RequestParam(value = "forum_PostedBy", required = false) String forumPostedBy,
			@RequestParam(value = "forum_UpdatedDate", required = false) String forumUpdatedDate,
			@RequestParam(value = "forum_CategoryID", required = false) String forumCategoryID,
			@RequestParam(value = "forum_CategoryName", required = false) String forumCategoryName,
			
			@RequestParam(value = "forumEvent_TopicID", required = false) String forumEventTopicID,
			@RequestParam(value = "forumEvent_SectionID", required = false) String forumEventSectionID,
			@RequestParam(value = "forumEvent_CreatedBy", required = false) String forumEventCreatedBy,
			@RequestParam(value = "forumEvent_CreatedFor", required = false) String forumEventCreatedFor,
			@RequestParam(value = "forumEvent_Event", required = false) String forumEventEvent,
			@RequestParam(value = "forumEvent_TimeStamp", required = false) String forumEventTimeStamp,
			
			@RequestParam(value = "member_member_id", required = false) String memberMemberId,
			@RequestParam(value = "member_member_email", required = false) String memberMemberEmail,
			@RequestParam(value = "member_first_name", required = false) String memberFirstName,
			@RequestParam(value = "member_middle_name", required = false) String memberMiddleName,
			@RequestParam(value = "member_last_name", required = false) String memberLastName,
			@RequestParam(value = "member_display_name", required = false) String memberDisplayName,
			@RequestParam(value = "member_screen_name", required = false) String memberScreenName,
			@RequestParam(value = "member_profile_image", required = false) String memberProfileImage,
			@RequestParam(value = "member_city", required = false) String memberCity,
			@RequestParam(value = "member_state", required = false) String memberState,
			@RequestParam(value = "member_country", required = false) String memberCountry,
			@RequestParam(value = "member_updateDate", required = false) String memberUpdateDate,
			
			@RequestParam(value = "memberEvent_UID", required = false) String memberEventUID,
			@RequestParam(value = "memberEvent_CreatedBy", required = false) String memberEventCreatedBy,
			@RequestParam(value = "memberEvent_CreatedFor", required = false) String memberEventCreatedFor,
			@RequestParam(value = "memberEvent_Event", required = false) String memberEventEvent,
			@RequestParam(value = "memberEvent_EventDetail", required = false) String memberEventEventDetail,
			@RequestParam(value = "memberEvent_TimeStamp", required = false) String memberEventTimeStamp){
		
		
		if(memberMemberId == null || memberMemberId.equals("")){
			return "Required Id field is missing.";	
		}else {
			AllSearchableObject allSearchableObject = new AllSearchableObject();
			
			allSearchableObject.setcommunity_Business_id(communityBusinessId);
			allSearchableObject.setcommunity_Business_name(communityBusinessName);
			allSearchableObject.setcommunity_Safe_name(communitySafeName);
			allSearchableObject.setcommunity_City(communityCity);
			allSearchableObject.setcommunity_City_id(communityCityId);
			allSearchableObject.setcommunity_Country(communityCountry);
			allSearchableObject.setcommunity_Member_id(communityMemberId);
			
			allSearchableObject.setcommunityEvent_Business_id(communityEventBusinessId);
			allSearchableObject.setcommunityEvent_SectionID(communityEventSectionID);
			allSearchableObject.setcommunityEvent_CreatedBy(communityEventCreatedBy);
			allSearchableObject.setcommunityEvent_CreatedFor(communityEventCreatedFor);
			allSearchableObject.setcommunityEvent_Event(communityEventEvent);
			allSearchableObject.setcommunityEvent_TimeStamp(communityEventTimeStamp);
			
			allSearchableObject.setcontact_Contact_id(contactContactId);
			allSearchableObject.setcontact_Member_id(contactMemberId);
			allSearchableObject.setcontact_Is_connected(contactIsConnected);
			allSearchableObject.setcontact_Is_blocked(contactIsBlocked);
			allSearchableObject.setcontact_Is_message_sent(contactIsMessageSent);
			
			allSearchableObject.setforum_ForumID(forumForumID);
			allSearchableObject.setforum_ForumName(forumForumName);
			allSearchableObject.setforum_TopicID(forumTopicID);
			allSearchableObject.setforum_TopicSubject(forumTopicSubject);
			allSearchableObject.setforum_TopicDescription(forumTopicDescription);
			allSearchableObject.setforum_CommentCount(forumCommentCount);
			allSearchableObject.setforum_RatingCount(forumRatingCount);
			allSearchableObject.setforum_RatingAverage(forumRatingAverage);
			allSearchableObject.setforum_PostedByMemberEmail(forumPostedByMemberEmail);
			allSearchableObject.setforum_PostedBy(forumPostedBy);
			allSearchableObject.setforum_UpdatedDate(forumUpdatedDate);
			allSearchableObject.setforum_CategoryID(forumCategoryID);
			allSearchableObject.setforum_CategoryName(forumCategoryName);
			
			allSearchableObject.setforumEvent_TopicID(forumEventTopicID);
			allSearchableObject.setforumEvent_SectionID(forumEventSectionID);
			allSearchableObject.setforumEvent_CreatedBy(forumEventCreatedBy);
			allSearchableObject.setforumEvent_CreatedFor(forumEventCreatedFor);
			allSearchableObject.setforumEvent_Event(forumEventEvent);
			allSearchableObject.setforumEvent_TimeStamp(forumEventTimeStamp);
			
			allSearchableObject.setmember_Member_id(memberMemberId);
			allSearchableObject.setmember_Member_email(memberMemberEmail);
			allSearchableObject.setmember_First_name(memberFirstName);
			allSearchableObject.setmember_Middle_name(memberMiddleName);
			allSearchableObject.setmember_Last_name(memberLastName);
			allSearchableObject.setmember_Display_name(memberDisplayName);
			allSearchableObject.setmember_Screen_name(memberScreenName);
			allSearchableObject.setmember_Profile_image(memberProfileImage);
			allSearchableObject.setmember_City(memberCity);
			allSearchableObject.setmember_State(memberState);
			allSearchableObject.setmember_Country(memberCountry);
			allSearchableObject.setmember_UpdateDate(memberUpdateDate);
			
			allSearchableObject.setmemberEvent_UID(memberEventUID);
			allSearchableObject.setmemberEvent_CreatedBy(memberEventCreatedBy);
			allSearchableObject.setmemberEvent_CreatedFor(memberEventCreatedFor);
			allSearchableObject.setmemberEvent_Event(memberEventEvent);
			allSearchableObject.setmemberEvent_EventDetail(memberEventEventDetail);
			allSearchableObject.setmemberEvent_TimeStamp(memberEventTimeStamp);
			
			addingService.add(allSearchableObject);
			
		}
		
		return "SuccessfullyAdded";
	}



	

	/**
	 * Service to Delete ForumEvent from the Solr Search Server
	 * 
	 * @param member_member_id
	 * 
	 * @return a String Object "SuccessfullyDeleted"
	 */

	@RequestMapping(value = "/deleteObject")
	public String deleteObject(
			@RequestParam(value = "member_member_id", required = false) String memberMemberId){
		
		
		if(memberMemberId == null || memberMemberId.equals("")){
			return "Required Id field is missing.";	
		}else {
			AllSearchableObject allSearchableObject = new AllSearchableObject();
			
			allSearchableObject.setmember_Member_id(memberMemberId);

			deletingService.delete(allSearchableObject);
			
		}
		
		return "SuccessfullyDeleted";
	}



	
	
	
	
	@RequestMapping(value = "/searchContent")
	public ModelAndView searchContent(
			@RequestParam(value = "member_id", required = false) String contactMemberId,
			@RequestParam(value = "query", required = false) String queryString,
			@RequestParam(value = "format", required = false) String format){
		
		
		System.out.println("hi");
		System.out.println("heeeeei");
		queryString = queryString.toLowerCase();

		ModelAndView mav = new ModelAndView();
		
		try {
			
			List<String> ids = new ArrayList<String>();
			ids.add(contactMemberId);
			List<Contact> friendsFriendsList = new ArrayList<Contact>();
			System.out.println(ids);
			List<Contact> friendsList = this.getContacts(ids);
			Collections.sort(friendsList);
			System.out.println(friendsList.size());
			//Collections.reverse(friendsList);
			//System.out.println(friendsList.size());
			//for(Contact frnd:friendsList)System.out.println(frnd.getcontact_Contact_id() );
				List<Member> friendsDetailsList = new ArrayList<Member>();
				List<Member> friendsDetailsListWithSpeNameInitials = new ArrayList<Member>();
				for(Contact frnd:friendsList){
					Member memberFriend = this.getContactDetails(frnd.getContact_id());
					System.out.println("varun"+frnd.getContact_id());
					//if(memberFriend.getmember_First_name().toLowerCase().startsWith(queryString))
					friendsDetailsList.add(memberFriend);
					
					
				}
				for(Member m:friendsDetailsList){
					if(m.getFirst_name().toLowerCase().startsWith(queryString) || m.getLast_name().toLowerCase().startsWith(queryString))
						friendsDetailsListWithSpeNameInitials.add(m);
				}
				
				List<String> contList = new ArrayList<String>();
				for(Member mem:friendsDetailsList){
					
					contList.add(mem.getMember_id());
					
				}
				friendsFriendsList = this.getContacts(contList);
				for(Contact frndsFrnd:friendsFriendsList){
					
					Member memberFriendsFriend = this.getContactDetails(frndsFrnd.getContact_id());
					
					if(memberFriendsFriend.getFirst_name().toLowerCase().startsWith(queryString)|| memberFriendsFriend.getLast_name().toLowerCase().startsWith(queryString))friendsDetailsListWithSpeNameInitials.add(memberFriendsFriend);
					
				}
				System.out.println(friendsFriendsList.size());
				Collections.sort(friendsDetailsListWithSpeNameInitials);
				mav.addObject(friendsDetailsListWithSpeNameInitials);
		} catch (Exception e) {
		e.printStackTrace();
		}
		
	return mav;
	}
	
	@RequestMapping(value = "/searchMembers")
	public ModelAndView searchMembers(
			@RequestParam(value = "member_id", required = false) String contactMemberId,
			@RequestParam(value = "query", required = false) String queryString	)
	{
		ModelAndView mav = new ModelAndView();
		return mav;
	}
	
	
	private List<Contact> getContacts(List<String> memberIds){
		List<Contact> friendsList = new ArrayList<Contact>();
		String memberIdsList = "";
		try{
			for(int i=0; i<memberIds.size()-1; i++){
				
				memberIdsList = "member_id%3A"+memberIds.get(i)+"+OR+";
				System.out.println(""+memberIdsList);
			}
			if(memberIds.size() == 0)
			{
				return friendsList;
			}
			memberIdsList = memberIdsList+"member_id%3A"+memberIds.get(memberIds.size()-1);
			System.out.println(memberIdsList);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse("http://192.168.0.153:8983/solr/select/?q="+memberIdsList+"&version=2.2&start=0&rows=10&indent=on");
			doc.getDocumentElement().normalize();
			//System.out.println("Root element " + doc.getDocumentElement().getNodeName());
			NodeList nodeLst = doc.getElementsByTagName("doc");
			//System.out.println("Information of all doc"+nodeLst.getLength());
			 
			String Connection_id = null;
			String Contact_id = null;
			String member_id = null;
			String is_connected = null;
			String is_blocked = null;
			String is_message_sent = null;
			int i = nodeLst.getLength();
			for(int x=0; x<i; x++){
				Node docs = nodeLst.item(x);
				//System.out.println(docs.getNodeName());
				NodeList strLst = docs.getChildNodes();
				//System.out.println(strLst.getLength());
				for(int j=1; j<strLst.getLength(); j+=2){
					switch(j){
						case 1: Connection_id = strLst.item(j).getTextContent(); break;
						case 3: Contact_id = strLst.item(j).getTextContent(); break;
						case 5: is_blocked = strLst.item(j).getTextContent(); break;
						case 7: is_connected = strLst.item(j).getTextContent(); break;
						case 9: is_message_sent = strLst.item(j).getTextContent(); break;
						case 11: member_id = strLst.item(j).getTextContent(); break;
					}
					/*if(j == 1)Connection_id = strLst.item(j).getTextContent();
					else if(j == 3)Contact_id = strLst.item(j).getTextContent();
					else if(j == 5)is_blocked = strLst.item(j).getTextContent();
					else if(j == 7)is_connected = strLst.item(j).getTextContent();
					else if(j == 9)is_message_sent = strLst.item(j).getTextContent();
					else if(j == 11)member_id = strLst.item(j).getTextContent();*/
					
					//System.out.println(strLst.item(j).getTextContent());
					
				}
				
				/*System.out.println(Connection_id);
				System.out.println(Contact_id);
				System.out.println(is_blocked);
				System.out.println(is_connected);
				System.out.println(is_message_sent);
				System.out.println(member_id);*/
				Contact friend = new Contact(Connection_id, Contact_id, member_id, is_connected, is_blocked, is_message_sent);
				friendsList.add(friend);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return friendsList;
	}
	
	
	private Member getContactDetails(String memberId){
		Member member = new Member();
		try{
			/*System.out.println(frnd.getConnection_id());
			System.out.println(frnd.getContact_id());
			System.out.println(frnd.getMember_id());
			System.out.println(frnd.getIs_connected());
			System.out.println(frnd.getIs_blocked());
			System.out.println(frnd.getIs_message_sent());
			URL xmlUrl = new URL("http://localhost:8987/solr/select/?q=100000486053610&version=2.2&start=0&rows=10&indent=on");
			InputStream in = xmlUrl.openStream();
			Document doc = parse(in);*/
			
			//URL url = new URL("http://localhost:8987/solr/select/?q=100000837194060&version=2.2&start=0&rows=10&indent=on");
			DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbuilder = dbfactory.newDocumentBuilder();
			//System.out.println(memberId);
			Document memberDoc = dbuilder.parse("http://192.168.0.153:8984/solr/select/?q=Contact_id:"+memberId+"&version=2.2&start=0&rows=10&indent=on");
			memberDoc.getDocumentElement().normalize();
			//System.out.println("Root element " + memberDoc.getDocumentElement().getNodeName());
			NodeList nodeList = memberDoc.getElementsByTagName("doc");
			//System.out.println("Information of all doc"+nodeList.getLength());
			 
			String member_id = null;
			String member_email = null;
			String first_name = null;
			String middle_name = null;
			String last_name = null;
			String display_name = null;
			String screen_name = null;
			String profile_image = null;
			String city = null;
			String state = null;
			String country = null;
			String updateDate = null;
			int i = nodeList.getLength();
			for(int x=0; x<i; x++){
			//System.out.println("Something*******");
			
				Node memberDocs = nodeList.item(x);
				//System.out.println("Something......");
				NodeList memberStrLst = memberDocs.getChildNodes();
				//System.out.println(memberStrLst.getLength());
				for(int c=1; c<memberStrLst.getLength(); c++){
					switch(c){
						//case 1:city = memberStrLst.item(c).getTextContent(); break;
						//case 3:country = memberStrLst.item(c).getTextContent(); break;
						//case 5:display_name = memberStrLst.item(c).getTextContent(); break;
						case 1:first_name = memberStrLst.item(c).getTextContent(); break;
						case 3:last_name = memberStrLst.item(c).getTextContent(); break;
						//case 11:member_email = memberStrLst.item(c).getTextContent(); break;
						case 5:member_id = memberStrLst.item(c).getTextContent(); break;
						//case 15:middle_name = memberStrLst.item(c).getTextContent(); break;
						//case 17:profile_image = memberStrLst.item(c).getTextContent(); break;
						//case 19:screen_name = memberStrLst.item(c).getTextContent(); break;
						//case 21:state = memberStrLst.item(c).getTextContent(); break;
						//case 23:updateDate = memberStrLst.item(c).getTextContent(); break;
					}
					//System.out.println(memberStrLst.item(c).getTextContent());
					
				}
				member = new Member(member_id,first_name,last_name);
				
			}
				
		}catch(Exception e){
			e.printStackTrace();
		}
		
	return member;
	}


}
	

