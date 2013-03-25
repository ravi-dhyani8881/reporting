package com.ytk.models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;
@JsonWriteNullProperties(false)
public class DiscussionQuestion implements Comparable<DiscussionQuestion> {
	
	private String ID;
	private String QuestionText="";
	private String CreatedDate="";
	private String CreatedUserID="";
	private String CreatedUserDisplayName="";
	private String CreatedUserScreenName="";
	private String CreatedUserEmailAddress="";
	private String CreatedUserImage="";
	private String CategoryId="";
	private String CategoryName="";
	private String SubCategoryID="";
	private String SubCategoryName="";
	private String SubCategorySafeName="";
	private String AnswerCount="";
	private String Rating="";	
	private String Tags="";
	private String LastRepliedUserId="";
	private String LastRepliedUserDisplayName="";
	private String LastRepliedUserScreenName="";
	private String LastRepliedUserEmailAddress="";
	private String LastRepliedUserImage="";
	private String LastRepliedDate="";
	private String InitialAnswer="";
	private String CreatedUserType="";
	private String LastRepliedUserType="";
	private String IsFeatured="";
	
	/*Privacy Field added*/
	private String Everyone="";
	private String Onlyme="";
	private String FoldersShowList="";
	private String FoldersHideList="";
	private String ContactsShowList="";
	private String ContactsHideList="";
	private String  IsCustom="";

	
	private String CreatedUserImageJson="";
	private String LastRepliedUserImageJson="";
	
	
	
	@JsonProperty("IsCustom")
	public String getIsCustom() {
		return IsCustom;
	}
	public void setIsCustom(String isCustom) {
		IsCustom = isCustom;
	}
	@JsonProperty("ID")
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	@JsonProperty("CreatedUserID")
	public String getCreatedUserID() {
		return CreatedUserID;
	}
	public void setCreatedUserID(String createdUserID) {
		CreatedUserID = createdUserID;
	}
	@JsonProperty("QuestionText")
	public String getQuestionText() {
		return QuestionText;
	}
	public void setQuestionText(String questionText) {
		QuestionText = questionText;
	}
	
	@JsonProperty("CreatedDate")
	public String getCreatedDate() {
		return CreatedDate;
	}
	public void setCreatedDate(String createdDate) {
		CreatedDate = createdDate;
	}
	
	@JsonProperty("Tags")
	public String getTags() {
		return Tags;
	}
	public void setTags(String tags) {
		Tags = tags;
	}
	
	@JsonProperty("Rating")
	public String getRating() {
		return Rating;
	}
	public void setRating(String rating) {
		Rating = rating;
	}
	
	@JsonProperty("CreatedUserDisplayName")
	public String getCreatedUserDisplayName() {
		return CreatedUserDisplayName;
	}
	public void setCreatedUserDisplayName(String createdUserDisplayName) {
		CreatedUserDisplayName = createdUserDisplayName;
	}
	
	@JsonProperty("CreatedUserScreenName")
	public String getCreatedUserScreenName() {
		return CreatedUserScreenName;
	}
	public void setCreatedUserScreenName(String createdUserScreenName) {
		CreatedUserScreenName = createdUserScreenName;
	}
	
	@JsonProperty("CategoryName")
	public String getCategoryName() {
		return CategoryName;
	}
	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}
	
	@JsonProperty("AnswerCount")
	public String getAnswerCount() {
		return AnswerCount;
	}
	public void setAnswerCount(String answerCount) {
		AnswerCount = answerCount;
	}
	
	@JsonProperty("LastRepliedDate")
	public String getLastRepliedDate() {
		return LastRepliedDate;
	}
	public void setLastRepliedDate(String lastRepliedDate) {
		LastRepliedDate = lastRepliedDate;
	}
	
	
	
	@JsonProperty("CreatedUserEmailAddress")
	public String getCreatedUserEmailAddress() {
		return CreatedUserEmailAddress;
	}
	public void setCreatedUserEmailAddress(String createdUserEmailAddress) {
		CreatedUserEmailAddress = createdUserEmailAddress;
	}
	
	@JsonProperty("CreatedUserImage")
	public String getCreatedUserImage() {
		return CreatedUserImage;
	}
	public void setCreatedUserImage(String createdUserImage) {
		CreatedUserImage = createdUserImage;
	}
	@JsonProperty("CategoryId")
	public String getCategoryId() {
		return CategoryId;
	}
	public void setCategoryId(String categoryId) {
		CategoryId = categoryId;
	}
	@JsonProperty("SubCategoryID")
	public String getSubCategoryID() {
		return SubCategoryID;
	}
	public void setSubCategoryID(String subCategoryID) {
		SubCategoryID = subCategoryID;
	}
	
	@JsonProperty("SubCategoryName")
	public String getSubCategoryName() {
		return SubCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		SubCategoryName = subCategoryName;
	}
	
	@JsonProperty("SubCategorySafeName")
	public String getSubCategorySafeName() {
		return SubCategorySafeName;
	}
	public void setSubCategorySafeName(String subCategorySafeName) {
		SubCategorySafeName = subCategorySafeName;
	}
	
	@JsonProperty("LastRepliedUserId")
	public String getLastRepliedUserId() {
		return LastRepliedUserId;
	}
	public void setLastRepliedUserId(String lastRepliedUserId) {
		LastRepliedUserId = lastRepliedUserId;
	}
	
	@JsonProperty("LastRepliedUserDisplayName")
	public String getLastRepliedUserDisplayName() {
		return LastRepliedUserDisplayName;
	}
	public void setLastRepliedUserDisplayName(String lastRepliedUserDisplayName) {
		LastRepliedUserDisplayName = lastRepliedUserDisplayName;
	}
	
	@JsonProperty("LastRepliedUserScreenName")
	public String getLastRepliedUserScreenName() {
		return LastRepliedUserScreenName;
	}
	public void setLastRepliedUserScreenName(String lastRepliedUserScreenName) {
		LastRepliedUserScreenName = lastRepliedUserScreenName;
	}
	
	@JsonProperty("LastRepliedUserEmailAddress")
	public String getLastRepliedUserEmailAddress() {
		return LastRepliedUserEmailAddress;
	}
	public void setLastRepliedUserEmailAddress(String lastRepliedUserEmailAddress) {
		LastRepliedUserEmailAddress = lastRepliedUserEmailAddress;
	}
	
	@JsonProperty("LastRepliedUserImage")
	public String getLastRepliedUserImage() {
		return LastRepliedUserImage;
	}
	
	
	public void setLastRepliedUserImage(String lastRepliedUserImage) {
		LastRepliedUserImage = lastRepliedUserImage;
	}
	
	@JsonProperty("InitialAnswer")
	public String getInitialAnswer() {
		return InitialAnswer;
	}
	public void setInitialAnswer(String initialAnswer) {
		InitialAnswer = initialAnswer;
	}
	
	@JsonProperty("LastRepliedUserType")
	public String getLastRepliedUserType() {
		return LastRepliedUserType;
	}
	public void setLastRepliedUserType(String lastRepliedUserType) {
		LastRepliedUserType = lastRepliedUserType;
	}
	
	@JsonProperty("IsFeatured")
	public String getIsFeatured() {
		return IsFeatured;
	}
	public void setIsFeatured(String isFeatured) {
		IsFeatured = isFeatured;
	}
	
	
	@JsonProperty("Everyone")
	public String getEveryone() {
		return Everyone;
	}
	public void setEveryone(String everyone) {
		Everyone = everyone;
	}
	
	@JsonProperty("Onlyme")
	public String getOnlyme() {
		return Onlyme;
	}
	public void setOnlyme(String onlyme) {
		Onlyme = onlyme;
	}
	
	@JsonProperty("FoldersShowList")
	public String getFoldersShowList() {
		return FoldersShowList;
	}
	public void setFoldersShowList(String foldersShowList) {
		FoldersShowList = foldersShowList;
	}
	
	@JsonProperty("FoldersHideList")
	public String getFoldersHideList() {
		return FoldersHideList;
	}
	public void setFoldersHideList(String foldersHideList) {
		FoldersHideList = foldersHideList;
	}
	
	@JsonProperty("ContactsShowList")
	public String getContactsShowList() {
		return ContactsShowList;
	}
	public void setContactsShowList(String contactsShowList) {
		ContactsShowList = contactsShowList;
	}
	
	@JsonProperty("ContactsHideList")
	public String getContactsHideList() {
		return ContactsHideList;
	}
	public void setContactsHideList(String contactsHideList) {
		ContactsHideList = contactsHideList;
	}
	
	@JsonProperty("CreatedUserType")
	 public String getCreatedUserType() {
		return CreatedUserType;
	}
	public void setCreatedUserType(String createdUserType) {
		CreatedUserType = createdUserType;
	}
	
	public int compareTo(DiscussionQuestion o) {
		 DateFormat formatter ; 
		 Date mydate = new Date(); 
		 Date odate = new Date(); ; 
		 formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		 try {
			mydate =  (Date)formatter.parse(this.LastRepliedDate);
			odate  =  (Date)formatter.parse(o.LastRepliedDate);  
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		 
 		
//		 if(Integer.parseInt(this.Rating) - Integer.parseInt(o.Rating) > 1)
//		 {
//	        return -2 ;
//		 }
//		 else if(mydate.compareTo(odate) > 1)
//		 {
//			return -3;
//		 }
		 if(Integer.parseInt(this.Rating) - Integer.parseInt(o.Rating) < 1)
		 {
	        return -3 ;
		 }
		 if(Integer.parseInt(this.AnswerCount) - Integer.parseInt(o.AnswerCount) < 1)
		 {
			 return -4;
		 }
		 else
			 return -1 ; 
			 
	    }
	
	
	@JsonProperty("CreatedUserImageJson")
	public String getCreatedUserImageJson() {
		return CreatedUserImageJson;
	}
	public void setCreatedUserImageJson(String createdUserImageJson) {
		CreatedUserImageJson = createdUserImageJson;
	}
	
	@JsonProperty("LastRepliedUserImageJson")
	public String getLastRepliedUserImageJson() {
		return LastRepliedUserImageJson;
	}
	public void setLastRepliedUserImageJson(String lastRepliedUserImageJson) {
		LastRepliedUserImageJson = lastRepliedUserImageJson;
	}	

	
	
	
	
	
	
	
	
}
