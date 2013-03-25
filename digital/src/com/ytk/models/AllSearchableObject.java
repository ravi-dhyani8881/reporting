package com.ytk.models;

import org.apache.solr.client.solrj.beans.Field;


public class AllSearchableObject{
	
	@Field
	private String community_business_id;
	@Field
	private String community_business_name;
	@Field
	private String community_safe_name;
	@Field
	private String community_city;
	@Field
	private String community_city_id;
	@Field
	private String community_country;
	@Field
	private String community_member_id;
	
	public String getcommunity_Business_id(){
		return this.community_business_id;
	}
	
	public void setcommunity_Business_id(String community_business_id){
		this.community_business_id = community_business_id;
	}
	
	public String getcommunity_Business_name(){
		return this.community_business_name;
	}
	
	public void setcommunity_Business_name(String community_business_name){
		this.community_business_name = community_business_name;
	}
	
	public String getcommunity_Safe_name(){
		return this.community_safe_name;
	}
	
	public void setcommunity_Safe_name(String community_safe_name){
		this.community_safe_name = community_safe_name;
	}
	
	public String getcommunity_City(){
		return this.community_city;
	}
	
	public void setcommunity_City(String community_city){
		this.community_city = community_city;
	}
	
	public String getcommunity_City_id(){
		return this.community_business_id;
	}
	
	public void setcommunity_City_id(String community_city_id){
		this.community_city_id = community_city_id;
	}
	
	public String getcommunity_Country(){
		return this.community_country;
	}
	
	public void setcommunity_Country(String community_country){
		this.community_country = community_country;
	}
	
	public String getcommunity_Member_id(){
		return this.community_member_id;
	}
	
	public void setcommunity_Member_id(String community_member_id){
		this.community_member_id = community_member_id;
	}

	
	
	
	
	
	@Field
	private String communityEvent_business_id;
	@Field
	private String communityEvent_SectionID;
	@Field
	private String communityEvent_CreatedBy;
	@Field
	private String communityEvent_CreatedFor;
	@Field
	private String communityEvent_Event;
	@Field
	private String communityEvent_TimeStamp;
	
	public String getcommunityEvent_Business_id(){
		return this.communityEvent_business_id;
	}
	public void setcommunityEvent_Business_id(String communityEvent_business_id){
		this.communityEvent_business_id = communityEvent_business_id;
	}
	
	public String getcommunityEvent_SectionID(){
		return this.communityEvent_SectionID;
	}
	public void setcommunityEvent_SectionID(String communityEvent_SectionID){
		this.communityEvent_SectionID = communityEvent_SectionID;
	}
	
	public String getcommunityEvent_CreatedBy(){
		return this.communityEvent_CreatedBy;
	}
	public void setcommunityEvent_CreatedBy(String communityEvent_CreatedBy){
		this.communityEvent_CreatedBy = communityEvent_CreatedBy;
	}
	
	public String getcommunityEvent_CreatedFor(){
		return this.communityEvent_CreatedFor;
	}
	public void setcommunityEvent_CreatedFor(String communityEvent_CreatedFor){
		this.communityEvent_CreatedFor = communityEvent_CreatedFor;
	}
	
	public String getcommunityEvent_Event(){
		return this.communityEvent_Event;
	}
	public void setcommunityEvent_Event(String communityEvent_Event){
		this.communityEvent_Event = communityEvent_Event;
	}
	
	public String getcommunityEvent_TimeStamp(){
		return this.communityEvent_TimeStamp;
	}
	public void setcommunityEvent_TimeStamp(String communityEvent_TimeStamp){
		this.communityEvent_TimeStamp = communityEvent_TimeStamp;
	}

	
	
	
	
	
	
	@Field
	private String contact_Contact_id;
	@Field
	private String contact_member_id;
	@Field
	private String contact_is_connected;
	@Field
	private String contact_is_blocked;
	@Field
	private String contact_is_message_sent;
	
	
	public String getcontact_Contact_id(){
		return this.contact_Contact_id;
	}
	
	public void setcontact_Contact_id(String contact_Contact_id){
		this.contact_Contact_id = contact_Contact_id;
	}
	
	public String getcontact_Member_id(){
		return this.contact_member_id;
	}
	
	public void setcontact_Member_id(String contact_member_id){
		this.contact_member_id = contact_member_id;
	}
	
	public String getcontact_Is_connected(){
		return this.contact_is_connected;
	}
	
	public void setcontact_Is_connected(String contact_is_connected){
		this.contact_is_connected = contact_is_connected;
	}
	
	public String getcontact_Is_blocked(){
		return this.contact_is_blocked;
	}
	
	public void setcontact_Is_blocked(String contact_is_blocked){
		this.contact_is_blocked = contact_is_blocked;
	}
	
	public String getcontact_Is_message_sent(){
		return this.contact_is_message_sent;
	}
	
	public void setcontact_Is_message_sent(String contact_is_message_sent){
		this.contact_is_message_sent = contact_is_message_sent;
	}
	
	
	
	
	
	
	@Field
	private String forum_ForumID;
	@Field
	private String forum_ForumName;
	@Field
	private String forum_TopicID;
	@Field
	private String forum_TopicSubject;
	@Field
	private String forum_TopicDescription;
	@Field
	private String forum_CommentCount;
	@Field
	private String forum_RatingCount;
	@Field
	private String forum_RatingAverage;
	@Field
	private String forum_PostedByMemberEmail;
	@Field
	private String forum_PostedBy;
	@Field
	private String forum_UpdatedDate;
	@Field
	private String forum_CategoryID;
	@Field
	private String forum_CategoryName;
	
	
	public String getforum_ForumID(){
		return this.forum_ForumID;
	}
	public void setforum_ForumID(String forum_ForumID){
		this.forum_ForumID = forum_ForumID;
	}
	
	public String getforum_ForumName(){
		return this.forum_ForumName;
	}
	public void setforum_ForumName(String forum_ForumName){
		this.forum_ForumName = forum_ForumName;
	}
	
	public String getforum_TopicID(){
		return this.forum_TopicID;
	}
	public void setforum_TopicID(String forum_TopicID){
		this.forum_TopicID = forum_TopicID;
	}
	
	public String getforum_TopicSubject(){
		return this.forum_TopicSubject;
	}
	public void setforum_TopicSubject(String forum_TopicSubject){
		this.forum_TopicSubject = forum_TopicSubject;
	}
	
	public String getforum_TopicDescription(){
		return this.forum_TopicDescription;
	}
	public void setforum_TopicDescription(String forum_TopicDescription){
		this.forum_TopicDescription = forum_TopicDescription;
	}
	
	public String getforum_CommentCount(){
		return this.forum_CommentCount;
	}
	public void setforum_CommentCount(String forum_CommentCount){
		this.forum_CommentCount = forum_CommentCount;
	}
	
	public String getforum_RatingCount(){
		return this.forum_RatingCount;
	}
	public void setforum_RatingCount(String forum_RatingCount){
		this.forum_RatingCount = forum_RatingCount;
	}
	
	public String getforum_RatingAverage(){
		return this.forum_RatingAverage;
	}
	public void setforum_RatingAverage(String forum_RatingAverage){
		this.forum_RatingAverage = forum_RatingAverage;
	}
	
	public String getforum_PostedByMemberEmail(){
		return this.forum_PostedByMemberEmail;
	}
	public void setforum_PostedByMemberEmail(String forum_PostedByMemberEmail){
		this.forum_PostedByMemberEmail = forum_PostedByMemberEmail;
	}
	
	public String getforum_PostedBy(){
		return this.forum_PostedBy;
	}
	public void setforum_PostedBy(String forum_PostedBy){
		this.forum_PostedBy = forum_PostedBy;
	}
	
	public String getforum_UpdatedDate(){
		return this.forum_UpdatedDate;
	}
	public void setforum_UpdatedDate(String forum_UpdatedDate){
		this.forum_UpdatedDate = forum_UpdatedDate;
	}
	
	public String getforum_CategoryID(){
		return this.forum_CategoryID;
	}
	public void setforum_CategoryID(String forum_CategoryID){
		this.forum_CategoryID = forum_CategoryID;
	}
	
	public String getforum_CategoryName(){
		return this.forum_CategoryName;
	}
	public void setforum_CategoryName(String forum_CategoryName){
		this.forum_CategoryName = forum_CategoryName;
	}

	
	
	
	
	@Field
	private String forumEvent_TopicID;
	@Field
	private String forumEvent_SectionID;
	@Field
	private String forumEvent_CreatedBy;
	@Field
	private String forumEvent_CreatedFor;
	@Field
	private String forumEvent_Event;
	@Field
	private String forumEvent_TimeStamp;
	
	
	public String getforumEvent_TopicID(){
		return this.forumEvent_TopicID;
	}
	public void setforumEvent_TopicID(String forumEvent_TopicID){
		this.forumEvent_TopicID = forumEvent_TopicID;
	}
	
	public String getforumEvent_SectionID(){
		return this.forumEvent_SectionID;
	}
	public void setforumEvent_SectionID(String forumEvent_SectionID){
		this.forumEvent_SectionID = forumEvent_SectionID;
	}
	
	public String getforumEvent_CreatedBy(){
		return this.forumEvent_CreatedBy;
	}
	public void setforumEvent_CreatedBy(String forumEvent_CreatedBy){
		this.forumEvent_CreatedBy = forumEvent_CreatedBy;
	}
	
	public String getforumEvent_CreatedFor(){
		return this.forumEvent_CreatedFor;
	}
	public void setforumEvent_CreatedFor(String forumEvent_CreatedFor){
		this.forumEvent_CreatedFor = forumEvent_CreatedFor;
	}
	
	public String getforumEvent_Event(){
		return this.forumEvent_Event;
	}
	public void setforumEvent_Event(String forumEvent_Event){
		this.forumEvent_Event = forumEvent_Event;
	}
	
	public String getforumEvent_TimeStamp(){
		return this.forumEvent_TimeStamp;
	}
	public void setforumEvent_TimeStamp(String forumEvent_TimeStamp){
		this.forumEvent_TimeStamp = forumEvent_TimeStamp;
	}
	

	
	
	
	
	
	
	@Field
	private String member_member_id;
	@Field
	private String member_member_email;
	@Field
	private String member_first_name;
	@Field
	private String member_middle_name;
	@Field
	private String member_last_name;
	@Field
	private String member_display_name;
	@Field
	private String member_screen_name;
	@Field
	private String member_profile_image;
	@Field
	private String member_city;
	@Field
	private String member_state;
	@Field
	private String member_country;
	@Field
	private String member_updateDate;
	
	
	
	public void setmember_Member_id(String member_member_id){
		this.member_member_id = member_member_id;
	}
	
	public String getmember_Member_id(){
		return this.member_member_id;
	}
    
	public void setmember_Member_email(String member_member_email){
		this.member_member_email = member_member_email;
	}
	
	public String getmember_Member_email(){
		return this.member_member_email;
	}
	
	public void setmember_First_name(String member_first_name){
		this.member_first_name = member_first_name;
	}
	
	public String getmember_First_name(){
		return this.member_first_name;
	}
	
	public void setmember_Middle_name(String member_middle_name){
		this.member_middle_name = member_middle_name;
	}
	
	public String getmember_Middle_name(){
		return this.member_middle_name;
	}
	
	public void setmember_Last_name(String member_last_name){
		this.member_last_name = member_last_name;
	}
	
	public String getmember_Last_name(){
		return this.member_last_name;
	}
	
	public void setmember_Display_name(String member_display_name){
		this.member_display_name = member_display_name;
	}
	
	public String getmember_Display_name(){
		return this.member_display_name;
	}
	
	public void setmember_Screen_name(String member_screen_name){
		this.member_screen_name = member_screen_name;
	}
	
	public String getmember_Screen_name(){
		return this.member_screen_name;
	}
	
	public void setmember_Profile_image(String member_profile_image){
		this.member_profile_image = member_profile_image;
	}
	
	public String getmember_Profile_image(){
		return this.member_profile_image;
	}
	
	public void setmember_City(String member_city){
		this.member_city = member_city;
	}
	
	public String getmember_City(){
		return this.member_city;
	}
	
	public void setmember_State(String member_state){
		this.member_state = member_state;
	}
	
	public String getmember_State(){
		return this.member_state;
	}
	
	public void setmember_Country(String member_country){
		this.member_country = member_country;
	}
	
	public String getmember_Country(){
		return this.member_country;
	}
	
	public void setmember_UpdateDate(String member_updateDate){
		this.member_updateDate = member_updateDate;
	}
	
	public String getmember_UpdateDate(){
		return this.member_updateDate;
	}

	
	
	
	
	
	
	
	
	@Field
	private String memberEvent_UID;
	@Field
	private String memberEvent_CreatedBy;
	@Field
	private String memberEvent_CreatedFor;
	@Field
	private String memberEvent_Event;
	@Field
	private String memberEvent_EventDetail;
	@Field
	private String memberEvent_TimeStamp;
	

	public void setmemberEvent_UID(String memberEvent_UID){
		this.memberEvent_UID = memberEvent_UID;
	}
	
	public String getmemberEvent_UID(){
		return this.memberEvent_UID;
	}
	
	public void setmemberEvent_CreatedBy(String memberEvent_CreatedBy){
		this.memberEvent_CreatedBy = memberEvent_CreatedBy;
	}
	
	public String getmemberEvent_CreatedBy(){
		return this.memberEvent_CreatedBy;
	}
	
	public void setmemberEvent_CreatedFor(String memberEvent_CreatedFor){
		this.memberEvent_CreatedFor = memberEvent_CreatedFor;
	}
	
	public String getmemberEvent_CreatedFor(){
		return this.memberEvent_CreatedFor;
	}
	
	public void setmemberEvent_Event(String memberEvent_Event){
		this.memberEvent_Event = memberEvent_Event;
	}
	
	public String getmemberEvent_Event(){
		return this.memberEvent_Event;
	}
	
	public void setmemberEvent_EventDetail(String memberEvent_EventDetail){
		this.memberEvent_EventDetail = memberEvent_EventDetail;
	}
	
	public String getmemberEvent_EventDetail(){
		return this.memberEvent_EventDetail;
	}
	
	public void setmemberEvent_TimeStamp(String memberEvent_TimeStamp){
		this.memberEvent_TimeStamp = memberEvent_TimeStamp;
	}
	
	public String getmemberEvent_TimeStamp(){
		return this.memberEvent_TimeStamp;
	}
	
	


	
	
	
	
 
}