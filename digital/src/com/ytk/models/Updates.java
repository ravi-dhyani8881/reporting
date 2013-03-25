package com.ytk.models;

import org.codehaus.jackson.annotate.JsonProperty;

public class Updates {
	
	private String  ID;
	private String  Type ;	
	private String	Section; 	
	private String	EventID; 	
	private String	EventSectionID; 	
	private String	AccountType; 	
	private String	AccountID;	
	
	private String	GeneratorAccountType; 	
	private String	GeneratorAccountID; 	

	private String	IsRelatedToUpdates; 	
	private String	IsRelatedToSearch; 
	private String  CommentSection;
	private String	TimeStamp;
	
	
	private String	AccountRestrictAge;
	private String	AccountRestrictCountry;
	private String	GeneratorAccountRestrictAge;
	private String	GeneratorAccountRestrictCountry;
	
	
	private String	FoldersHideList;
	private String	ContactsHideList;
	private String	ContactsShowList;
	private String	FoldersShowList;
	private String	Everyone;
	private String	OnlyMe;
	private String  IsCustom;
	private String  Status="";
	
	private String  HideBy;
	
	private String  CommentCount;
	private String  CommentJson;
	
	
	
	
	@JsonProperty("CommentSection")
	public String getCommentSection() {
		return CommentSection;
	}
	public void setCommentSection(String commentSection) {
		CommentSection = commentSection;
	}
	@JsonProperty("ID")
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	@JsonProperty("Type")
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	@JsonProperty("Section")
	public String getSection() {
		return Section;
	}
	public void setSection(String section) {
		Section = section;
	}
	@JsonProperty("EventID")
	public String getEventID() {
		return EventID;
	}
	public void setEventID(String eventID) {
		EventID = eventID;
	}
	@JsonProperty("EventSectionID")
	public String getEventSectionID() {
		return EventSectionID;
	}
	public void setEventSectionID(String eventSectionID) {
		EventSectionID = eventSectionID;
	}
	@JsonProperty("AccountType")
	public String getAccountType() {
		return AccountType;
	}
	public void setAccountType(String accountType) {
		AccountType = accountType;
	}
	@JsonProperty("AccountID")
	public String getAccountID() {
		return AccountID;
	}
	public void setAccountID(String accountID) {
		AccountID = accountID;
	}
	
	@JsonProperty("GeneratorAccountType")
	public String getGeneratorAccountType() {
		return GeneratorAccountType;
	}
	public void setGeneratorAccountType(String generatorAccountType) {
		GeneratorAccountType = generatorAccountType;
	}
	@JsonProperty("GeneratorAccountID")
	public String getGeneratorAccountID() {
		return GeneratorAccountID;
	}
	public void setGeneratorAccountID(String generatorAccountID) {
		GeneratorAccountID = generatorAccountID;
	}
	
	@JsonProperty("IsRelatedToUpdates")
	public String getIsRelatedToUpdates() {
		return IsRelatedToUpdates;
	}
	public void setIsRelatedToUpdates(String isRelatedToUpdates) {
		IsRelatedToUpdates = isRelatedToUpdates;
	}
	@JsonProperty("IsRelatedToSearch")
	public String getIsRelatedToSearch() {
		return IsRelatedToSearch;
	}
	public void setIsRelatedToSearch(String isRelatedToSearch) {
		IsRelatedToSearch = isRelatedToSearch;
	}
	@JsonProperty("TimeStamp")
	public String getTimeStamp() {
		return TimeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		TimeStamp = timeStamp;
	}
	
	@JsonProperty("AccountRestrictAge")
	public String getAccountRestrictAge() {
		return AccountRestrictAge;
	}
	public void setAccountRestrictAge(String accountRestrictAge) {
		AccountRestrictAge = accountRestrictAge;
	}
	
	@JsonProperty("AccountRestrictCountry")
	public String getAccountRestrictCountry() {
		return AccountRestrictCountry;
	}
	public void setAccountRestrictCountry(String accountRestrictCountry) {
		AccountRestrictCountry = accountRestrictCountry;
	}
	
	@JsonProperty("GeneratorAccountRestrictAge")
	public String getGeneratorAccountRestrictAge() {
		return GeneratorAccountRestrictAge;
	}
	public void setGeneratorAccountRestrictAge(String generatorAccountRestrictAge) {
		GeneratorAccountRestrictAge = generatorAccountRestrictAge;
	}
	
	@JsonProperty("GeneratorAccountRestrictCountry")
	public String getGeneratorAccountRestrictCountry() {
		return GeneratorAccountRestrictCountry;
	}
	
	
	
	
	
	
	
	public void setGeneratorAccountRestrictCountry(
			String generatorAccountRestrictCountry) {
		GeneratorAccountRestrictCountry = generatorAccountRestrictCountry;
	}
	
	@JsonProperty("FoldersHideList")
	public String getFoldersHideList() {
		return FoldersHideList;
	}
	public void setFoldersHideList(String foldersHideList) {
		FoldersHideList = foldersHideList;
	}
	@JsonProperty("ContactsHideList")
	public String getContactsHideList() {
		return ContactsHideList;
	}
	public void setContactsHideList(String contactsHideList) {
		ContactsHideList = contactsHideList;
	}
	@JsonProperty("ContactsShowList")
	public String getContactsShowList() {
		return ContactsShowList;
	}
	public void setContactsShowList(String contactsShowList) {
		ContactsShowList = contactsShowList;
	}
	@JsonProperty("FoldersShowList")
	public String getFoldersShowList() {
		return FoldersShowList;
	}
	public void setFoldersShowList(String foldersShowList) {
		FoldersShowList = foldersShowList;
	}
	@JsonProperty("Everyone")
	public String getEveryone() {
		return Everyone;
	}
	public void setEveryone(String everyone) {
		Everyone = everyone;
	}
	@JsonProperty("OnlyMe")
	public String getOnlyMe() {
		return OnlyMe;
	}
	public void setOnlyMe(String onlyMe) {
		OnlyMe = onlyMe;
	}
	@JsonProperty("isCustom")
	public String getIsCustom() {
		return IsCustom;
	}
	public void setIsCustom(String isCustom) {
		IsCustom = isCustom;
	}
	@JsonProperty("Status")
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
	@JsonProperty("HideBy")
	public String getHideBy() {
		return HideBy;
	}
	public void setHideBy(String hideBy) {
		HideBy = hideBy;
	}
	
	@JsonProperty("CommentCount")
	public String getCommentCount() {
		return CommentCount;
	}
	public void setCommentCount(String commentCount) {
		CommentCount = commentCount;
	}
	
	@JsonProperty("CommentJson")
	public String getCommentJson() {
		return CommentJson;
	}
	public void setCommentJson(String commentJson) {
		CommentJson = commentJson;
	}
	
	
	
	
	

}
