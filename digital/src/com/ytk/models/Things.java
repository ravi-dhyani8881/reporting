package com.ytk.models;

import org.codehaus.jackson.annotate.JsonProperty;


public class Things {
	private String 	ID;
	
	private String  ScreenName="";
	private String  ScreenNameStatus="";
	private String 	Name="";
	private String CategoryID="";
	private String SubCategoryID="";
	private String ParentCategoryID="";
	private String CategoryName="";
	private String SubCategoryName="";
	private String ParentCategoryName="";
	private String Address="";
	private String Email="";
	private String City="";
	private String State="";
	private String Country="";
	private String CityID="";
	private String StateID="";
	private String CountryID="";
	private String ZipCode="";
	private String Affiliation="";
	private String About="";
	private String Description="";
	private String Biography="";
	private String Awards="";
	private String Phone="";
	private String Website="";	
	private String YouTube="";
	private String ReleaseDate="";
	private String Genre="";
	private String ISBN="";
	private String Publisher="";
	private String Founded="";
	private String Products="";
	private String CompanyOverview="";
	private String Mission="";
	private String Built="";
	private String Features="";
	private String MPG="";
	private String GeneralInformation="";
	private String Studio="";
	private String PlotOutline="";
	private String Starring="";
	private String DirectedBy="";
	private String WrittenBy="";
	private String ScreenplayBy="";
	private String ProducedBy="";
	private String Members="";
	private String RecordLabel="";
	private String History="";
	private String Network="";
	private String Season="";
	private String ImageName="";
//	private String HasImage;
	private String CreatedDate="";
	private String ActivationDate="";
	private String UpdatedDate="";
	private String DeletionDate="";
	private String LastLoginDate="";
//	private String IsActive;
//	private String IsDeleted;
	private String IsFeatured="";
	private String IsCrawlerAllow="";
	private String Role="";
	private String OnlineStatus="";
	private String WhoCanSeeInSearch="";
	private String ShowWelcomePage="";
	private String ShowCompleteStatus="";
	private String IsFundermailSent="";
//	private String ContactDB;
//	private String UpdateDB;
//	private String MemberInfoDB;
//	private String CommentDB;
//	private String MemberContentDB;
//	private String MessageDB;
//	private String FolderDB;
//	private String NotificationSettingDB;
//	private String SearchDB;
	private String Status="";
	private String ProfileImage="";
	private String ProfileFileID="";
	private int OldWikiID = 0;
	private String WikiInfo="";
//	private String TotalRecords;
	
	private String RestrictAge="";
	private String RestrictCountry="";
	private String RestrictPost="";
	
	
	
	private String ImageJson="";
	private String ProfileImageJson="";
	

	
	
	@JsonProperty("ID")
	public String getID() {
		return ID;
	}
	
//	@JsonProperty("DBID")
//	public String getDBID() {
//		return DBID;
//	}
	
	@JsonProperty("ScreenName")
	public String getScreenName() {
		return ScreenName;
	}
	@JsonProperty("ScreenNameStatus")
	public String getScreenNameStatus() {
		return ScreenNameStatus;
	}
	
	@JsonProperty("Name")
	public String getName() {
		return Name;
	}
	
	@JsonProperty("CategoryID")
	public String getCategoryID() {
		return CategoryID;
	}
	
	@JsonProperty("SubCategoryID")
	public String getSubCategoryID() {
		return SubCategoryID;
	}
	
	@JsonProperty("ParentCategoryID")
	public String getParentCategoryID() {
		return ParentCategoryID;
	}
	
	@JsonProperty("CategoryName")
	public String getCategoryName() {
		return CategoryName;
	}
	
	@JsonProperty("SubCategoryName")
	public String getSubCategoryName() {
		return SubCategoryName;
	}
	
	@JsonProperty("ParentCategoryName")
	public String getParentCategoryName() {
		return ParentCategoryName;
	}
	
	@JsonProperty("Address")
	public String getAddress() {
		return Address;
	}
	
	@JsonProperty("Email")
	public String getEmail() {
		return Email;
	}
	
	@JsonProperty("City")
	public String getCity() {
		return City;
	}
	
	@JsonProperty("State")
	public String getState() {
		return State;
	}
	
	@JsonProperty("Country")
	public String getCountry() {
		return Country;
	}
	
	@JsonProperty("CityID")
	public String getCityID() {
		return CityID;
	}
	
	
	@JsonProperty("StateID")
	public String getStateID() {
		return StateID;
	}
	
	@JsonProperty("CountryID")
	public String getCountryID() {
		return CountryID;
	}
	
	@JsonProperty("ZipCode")
	public String getZipCode() {
		return ZipCode;
	}
	
	
	@JsonProperty("Affiliation")
	public String getAffiliation() {
		return Affiliation;
	}
	
	@JsonProperty("About")
	public String getAbout() {
		return About;
	}
	
	@JsonProperty("Description")
	public String getDescription() {
		return Description;
	}
	
	@JsonProperty("Biography")
	public String getBiography() {
		return Biography;
	}
	
	@JsonProperty("Awards")
	public String getAwards() {
		return Awards;
	}
	
	@JsonProperty("Phone")
	public String getPhone() {
		return Phone;
	}
	
	
	@JsonProperty("Website")
	public String getWebsite() {
		return Website;
	}
	
	
	@JsonProperty("YouTube")
	public String getYouTube() {
		return YouTube;
	}
	
	@JsonProperty("ReleaseDate")
	public String getReleaseDate() {
		return ReleaseDate;
	}
	
	
	@JsonProperty("Genre")
	public String getGenre() {
		return Genre;
	}
	
	
	@JsonProperty("ISBN")
	public String getISBN() {
		return ISBN;
	}
	
	@JsonProperty("Publisher")
	public String getPublisher() {
		return Publisher;
	}
	
	@JsonProperty("Founded")
	public String getFounded() {
		return Founded;
	}
	
	
	@JsonProperty("Products")
	public String getProducts() {
		return Products;
	}
	
	
	@JsonProperty("CompanyOverview")
	public String getCompanyOverview() {
		return CompanyOverview;
	}
	
	
	
	@JsonProperty("Mission")
	public String getMission() {
		return Mission;
	}
	
	
	@JsonProperty("Built")
	public String getBuilt() {
		return Built;
	}
	
	
	@JsonProperty("Features")
	public String getFeatures() {
		return Features;
	}
	
	
	@JsonProperty("MPG")
	public String getMPG() {
		return MPG;
	}
	
	
	@JsonProperty("GeneralInformation")
	public String getGeneralInformation() {
		return GeneralInformation;
	}
	
	
	@JsonProperty("Studio")
	public String getStudio() {
		return Studio;
	}	
	
	@JsonProperty("PlotOutline")
	public String getPlotOutline() {
		return PlotOutline;
	}
	
	@JsonProperty("Starring")
	public String getStarring() {
		return Starring;
	}
	
	
	@JsonProperty("DirectedBy")
	public String getDirectedBy() {
		return DirectedBy;
	}
	
	
	@JsonProperty("WrittenBy")
	public String getWrittenBy() {
		return WrittenBy;
	}
	
	@JsonProperty("ScreenplayBy")
	public String getScreenplayBy() {
		return ScreenplayBy;
	}
	
	
	@JsonProperty("ProducedBy")
	public String getProducedBy() {
		return ProducedBy;
	}
	
	
	@JsonProperty("Members")
	public String getMembers() {
		return Members;
	}
	
	
	@JsonProperty("RecordLabel")
	public String getRecordLabel() {
		return RecordLabel;
	}
	
	
	@JsonProperty("History")
	public String getHistory() {
		return History;
	}
	
	
	@JsonProperty("Network")
	public String getNetwork() {
		return Network;
	}
	
	@JsonProperty("Season")
	public String getSeason() {
		return Season;
	}
	
	@JsonProperty("ImageName")
	public String getImageName() {
		return ImageName;
	}
	
	
//	@JsonProperty("HasImage")
//	public String getHasImage() {
//		return HasImage;
//	}
//	
	
	
	@JsonProperty("CreatedDate")
	public String getCreatedDate() {
		return CreatedDate;
	}
	
	
	
	@JsonProperty("ActivationDate")
	public String getActivationDate() {
		return ActivationDate;
	}
	
	@JsonProperty("UpdatedDate")
	public String getUpdatedDate() {
		return UpdatedDate;
	}
	
	@JsonProperty("DeletionDate")
	public String getDeletionDate() {
		return DeletionDate;
	}
	
	@JsonProperty("LastLoginDate")
	public String getLastLoginDate() {
		return LastLoginDate;
	}
	
//	@JsonProperty("IsActive")
//	public String getIsActive() {
//		return IsActive;
//	}
//	
//	@JsonProperty("IsDeleted")
//	public String getIsDeleted() {
//		return IsDeleted;
//	}
	
	@JsonProperty("IsFeatured")
	public String getIsFeatured() {
		return IsFeatured;
	}
	
	
	@JsonProperty("IsCrawlerAllow")
	public String getIsCrawlerAllow() {
		return IsCrawlerAllow;
	}
	
	
	@JsonProperty("Role")
	public String getRole() {
		return Role;
	}
	
	@JsonProperty("OnlineStatus")
	public String getOnlineStatus() {
		return OnlineStatus;
	}
	
	@JsonProperty("WhoCanSeeInSearch")
	public String getWhoCanSeeInSearch() {
		return WhoCanSeeInSearch;
	}
	
	
	
	@JsonProperty("ShowWelcomePage")
	public String getShowWelcomePage() {
		return ShowWelcomePage;
	}
	
	
	@JsonProperty("ShowCompleteStatus")
	public String getShowCompleteStatus() {
		return ShowCompleteStatus;
	}
	
	
	@JsonProperty("IsFundermailSent")
	public String getIsFundermailSent() {
		return IsFundermailSent;
	}
	
	
//	@JsonProperty("ContactDB")
//	public String getContactDB() {
//		return ContactDB;
//	}
//	
//	@JsonProperty("UpdateDB")
//	public String getUpdateDB() {
//		return UpdateDB;
//	}
//	
//	
//	@JsonProperty("MemberInfoDB")
//	public String getMemberInfoDB() {
//		return MemberInfoDB;
//	}
//	
//	@JsonProperty("CommentDB")
//	public String getCommentDB() {
//		return CommentDB;
//	}
//	
//	
//	@JsonProperty("MemberContentDB")
//	public String getMemberContentDB() {
//		return MemberContentDB;
//	}
//	
//	
//	@JsonProperty("MessageDB")
//	public String getMessageDB() {
//		return MessageDB;
//	}
//	
	
//	@JsonProperty("FolderDB")
//	public String getFolderDB() {
//		return FolderDB;
//	}
//	
//	
//	@JsonProperty("NotificationSettingDB")
//	public String getNotificationSettingDB() {
//		return NotificationSettingDB;
//	}
//	
//	@JsonProperty("SearchDB")
//	public String getSearchDB() {
//		return SearchDB;
//	}
	
	@JsonProperty("Status")
	public String getStatus() {
		return Status;
	}
	
	
	@JsonProperty("ProfileImage")
	public String getProfileImage() {
		return ProfileImage;
	}
	
	
	@JsonProperty("ProfileFileID")
	public String getProfileFileID() {
		return ProfileFileID;
	}
	
	@JsonProperty("OldWikiID")
	public int getOldWikiID() {
		return OldWikiID;
	}
	
	@JsonProperty("WikiInfo")
	public String getWikiInfo() {
		return WikiInfo;
	}
	
//	@JsonProperty("TotalRecords")
//	public String getTotalRecords() {
//		return TotalRecords;
//	}

	/**
	 * @param iD the iD to set
	 */
	public void setID(String iD) {
		ID = iD;
	}

	/**
	 * @param dBID the dBID to set
	 */
//	public void setDBID(String dBID) {
//		DBID = dBID;
//	}

	/**
	 * @param screenName the screenName to set
	 */
	public void setScreenName(String screenName) {
		ScreenName = screenName;
	}

	/**
	 * @param screenNameStatus the screenNameStatus to set
	 */
	public void setScreenNameStatus(String screenNameStatus) {
		ScreenNameStatus = screenNameStatus;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * @param categoryID the categoryID to set
	 */
	public void setCategoryID(String categoryID) {
		CategoryID = categoryID;
	}

	/**
	 * @param subCategoryID the subCategoryID to set
	 */
	public void setSubCategoryID(String subCategoryID) {
		SubCategoryID = subCategoryID;
	}

	/**
	 * @param parentCategoryID the parentCategoryID to set
	 */
	public void setParentCategoryID(String parentCategoryID) {
		ParentCategoryID = parentCategoryID;
	}

	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}

	/**
	 * @param subCategoryName the subCategoryName to set
	 */
	public void setSubCategoryName(String subCategoryName) {
		SubCategoryName = subCategoryName;
	}

	/**
	 * @param parentCategoryName the parentCategoryName to set
	 */
	public void setParentCategoryName(String parentCategoryName) {
		ParentCategoryName = parentCategoryName;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		Address = address;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		Email = email;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		City = city;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		State = state;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		Country = country;
	}

	/**
	 * @param cityID the cityID to set
	 */
	public void setCityID(String cityID) {
		CityID = cityID;
	}

	/**
	 * @param stateID the stateID to set
	 */
	public void setStateID(String stateID) {
		StateID = stateID;
	}

	/**
	 * @param countryID the countryID to set
	 */
	public void setCountryID(String countryID) {
		CountryID = countryID;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		ZipCode = zipCode;
	}

	/**
	 * @param affiliation the affiliation to set
	 */
	public void setAffiliation(String affiliation) {
		Affiliation = affiliation;
	}

	/**
	 * @param about the about to set
	 */
	public void setAbout(String about) {
		About = about;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		Description = description;
	}

	/**
	 * @param biography the biography to set
	 */
	public void setBiography(String biography) {
		Biography = biography;
	}

	/**
	 * @param awards the awards to set
	 */
	public void setAwards(String awards) {
		Awards = awards;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		Phone = phone;
	}

	/**
	 * @param website the website to set
	 */
	public void setWebsite(String website) {
		Website = website;
	}

	/**
	 * @param youTube the youTube to set
	 */
	public void setYouTube(String youTube) {
		YouTube = youTube;
	}

	/**
	 * @param releaseDate the releaseDate to set
	 */
	public void setReleaseDate(String releaseDate) {
		ReleaseDate = releaseDate;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		Genre = genre;
	}

	/**
	 * @param iSBN the iSBN to set
	 */
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(String publisher) {
		Publisher = publisher;
	}

	/**
	 * @param founded the founded to set
	 */
	public void setFounded(String founded) {
		Founded = founded;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(String products) {
		Products = products;
	}

	/**
	 * @param companyOverview the companyOverview to set
	 */
	public void setCompanyOverview(String companyOverview) {
		CompanyOverview = companyOverview;
	}

	/**
	 * @param mission the mission to set
	 */
	public void setMission(String mission) {
		Mission = mission;
	}

	/**
	 * @param built the built to set
	 */
	public void setBuilt(String built) {
		Built = built;
	}

	/**
	 * @param features the features to set
	 */
	public void setFeatures(String features) {
		Features = features;
	}

	/**
	 * @param mPG the mPG to set
	 */
	public void setMPG(String mPG) {
		MPG = mPG;
	}

	/**
	 * @param generalInformation the generalInformation to set
	 */
	public void setGeneralInformation(String generalInformation) {
		GeneralInformation = generalInformation;
	}

	/**
	 * @param studio the studio to set
	 */
	public void setStudio(String studio) {
		Studio = studio;
	}

	/**
	 * @param plotOutline the plotOutline to set
	 */
	public void setPlotOutline(String plotOutline) {
		PlotOutline = plotOutline;
	}

	/**
	 * @param starring the starring to set
	 */
	public void setStarring(String starring) {
		Starring = starring;
	}

	/**
	 * @param directedBy the directedBy to set
	 */
	public void setDirectedBy(String directedBy) {
		DirectedBy = directedBy;
	}

	/**
	 * @param writtenBy the writtenBy to set
	 */
	public void setWrittenBy(String writtenBy) {
		WrittenBy = writtenBy;
	}

	/**
	 * @param screenplayBy the screenplayBy to set
	 */
	public void setScreenplayBy(String screenplayBy) {
		ScreenplayBy = screenplayBy;
	}

	/**
	 * @param producedBy the producedBy to set
	 */
	public void setProducedBy(String producedBy) {
		ProducedBy = producedBy;
	}

	/**
	 * @param members the members to set
	 */
	public void setMembers(String members) {
		Members = members;
	}

	/**
	 * @param recordLabel the recordLabel to set
	 */
	public void setRecordLabel(String recordLabel) {
		RecordLabel = recordLabel;
	}

	/**
	 * @param history the history to set
	 */
	public void setHistory(String history) {
		History = history;
	}

	/**
	 * @param network the network to set
	 */
	public void setNetwork(String network) {
		Network = network;
	}

	/**
	 * @param season the season to set
	 */
	public void setSeason(String season) {
		Season = season;
	}

	/**
	 * @param imageName the imageName to set
	 */
	public void setImageName(String imageName) {
		ImageName = imageName;
	}

	/**
	 * @param hasImage the hasImage to set
	 */
//	public void setHasImage(String hasImage) {
//		HasImage = hasImage;
//	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		CreatedDate = createdDate;
	}

	/**
	 * @param activationDate the activationDate to set
	 */
	public void setActivationDate(String activationDate) {
		ActivationDate = activationDate;
	}

	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(String updatedDate) {
		UpdatedDate = updatedDate;
	}

	/**
	 * @param deletionDate the deletionDate to set
	 */
	public void setDeletionDate(String deletionDate) {
		DeletionDate = deletionDate;
	}

	/**
	 * @param lastLoginDate the lastLoginDate to set
	 */
	public void setLastLoginDate(String lastLoginDate) {
		LastLoginDate = lastLoginDate;
	}

	/**
	 * @param isActive the isActive to set
	 */
//	public void setIsActive(String isActive) {
//		IsActive = isActive;
//	}

	/**
	 * @param isDeleted the isDeleted to set
	 */
//	public void setIsDeleted(String isDeleted) {
//		IsDeleted = isDeleted;
//	}

	/**
	 * @param isFeatured the isFeatured to set
	 */
	public void setIsFeatured(String isFeatured) {
		IsFeatured = isFeatured;
	}

	/**
	 * @param isCrawlerAllow the isCrawlerAllow to set
	 */
	public void setIsCrawlerAllow(String isCrawlerAllow) {
		IsCrawlerAllow = isCrawlerAllow;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		Role = role;
	}

	/**
	 * @param onlineStatus the onlineStatus to set
	 */
	public void setOnlineStatus(String onlineStatus) {
		OnlineStatus = onlineStatus;
	}

	/**
	 * @param whoCanSeeInSearch the whoCanSeeInSearch to set
	 */
	public void setWhoCanSeeInSearch(String whoCanSeeInSearch) {
		WhoCanSeeInSearch = whoCanSeeInSearch;
	}

	/**
	 * @param showWelcomePage the showWelcomePage to set
	 */
	public void setShowWelcomePage(String showWelcomePage) {
		ShowWelcomePage = showWelcomePage;
	}

	/**
	 * @param showCompleteStatus the showCompleteStatus to set
	 */
	public void setShowCompleteStatus(String showCompleteStatus) {
		ShowCompleteStatus = showCompleteStatus;
	}

	/**
	 * @param isFundermailSent the isFundermailSent to set
	 */
	public void setIsFundermailSent(String isFundermailSent) {
		IsFundermailSent = isFundermailSent;
	}

	/**
	 * @param contactDB the contactDB to set
	 */
//	public void setContactDB(String contactDB) {
//		ContactDB = contactDB;
//	}

	/**
	 * @param updateDB the updateDB to set
	 */
//	public void setUpdateDB(String updateDB) {
//		UpdateDB = updateDB;
//	}

	/**
	 * @param memberInfoDB the memberInfoDB to set
	 */
//	public void setMemberInfoDB(String memberInfoDB) {
//		MemberInfoDB = memberInfoDB;
//	}

	/**
	 * @param commentDB the commentDB to set
	 */
//	public void setCommentDB(String commentDB) {
//		CommentDB = commentDB;
//	}

	/**
	 * @param memberContentDB the memberContentDB to set
	 */
//	public void setMemberContentDB(String memberContentDB) {
//		MemberContentDB = memberContentDB;
//	}

	/**
	 * @param messageDB the messageDB to set
	 */
//	public void setMessageDB(String messageDB) {
//		MessageDB = messageDB;
//	}

	/**
	 * @param folderDB the folderDB to set
	 */
//	public void setFolderDB(String folderDB) {
//		FolderDB = folderDB;
//	}

	/**
	 * @param notificationSettingDB the notificationSettingDB to set
	 */
//	public void setNotificationSettingDB(String notificationSettingDB) {
//		NotificationSettingDB = notificationSettingDB;
//	}

	/**
	 * @param searchDB the searchDB to set
	 */
//	public void setSearchDB(String searchDB) {
//		SearchDB = searchDB;
//	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		Status = status;
	}

	/**
	 * @param profileImage the profileImage to set
	 */
	public void setProfileImage(String profileImage) {
		ProfileImage = profileImage;
	}

	/**
	 * @param profileFileID the profileFileID to set
	 */
	public void setProfileFileID(String profileFileID) {
		ProfileFileID = profileFileID;
	}

	/**
	 * @param oldWikiID the oldWikiID to set
	 */
	public void setOldWikiID(int oldWikiID) {
		OldWikiID = oldWikiID;
	}

	/**
	 * @param wikiInfo the wikiInfo to set
	 */
	public void setWikiInfo(String wikiInfo) {
		WikiInfo = wikiInfo;
	}

	/**
	 * @param totalRecords the totalRecords to set
	 */
	
	
//	public void setTotalRecords(String totalRecords) {
//		TotalRecords = totalRecords;
//	}

	@JsonProperty("RestrictAge")
	public String getRestrictAge() {
		return RestrictAge;
	}

	public void setRestrictAge(String restrictAge) {
		RestrictAge = restrictAge;
	}

	@JsonProperty("RestrictCountry")
	public String getRestrictCountry() {
		return RestrictCountry;
	}

	public void setRestrictCountry(String restrictCountry) {
		RestrictCountry = restrictCountry;
	}

	@JsonProperty("RestrictPost")
	public String getRestrictPost() {
		return RestrictPost;
	}

	public void setRestrictPost(String restrictPost) {
		RestrictPost = restrictPost;
	}

	@JsonProperty("ImageJson")
	public String getImageJson() {
		return ImageJson;
	}

	public void setImageJson(String imageJson) {
		ImageJson = imageJson;
	}

	@JsonProperty("ProfileImageJson")
	public String getProfileImageJson() {
		return ProfileImageJson;
	}

	public void setProfileImageJson(String profileImageJson) {
		ProfileImageJson = profileImageJson;
	}

	
	
	
	
	
	
}
