package com.ytk.models;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;


@JsonWriteNullProperties(false)
public class Faces {
	
	private String  ID; 
	private String	DBID;
	private String	CategoryID;
	private String	CategoryName;
	private String	ScreenName;
	private String	ScreenNameStatus;
	private String	FirstName; 	
	private String	MiddleName; 
	private String	LastName ;	
	private String	Name ;	
	private String	AlternateName;
	private String	DisplayAlternateName; 
	private String	Email ;	
	private String	ZipCode ;
	private String	BirthDay;
	private String	BirthdayOptions;
	private String 	AnniversaryDay;
	private String	Gender;
	private String	City; 
	private String	State ;
	private String	Country ;
	private String	CityID ;
	private String	StateID; 
	private String	CountryID;
	private String  CityCurrent;
	private String  StateCurrent;	
	private String  CountryCurrent;
	private String  CityIDCurrent;
	private String  StateIDCurrent;
	private String  CountryIDCurrent;
	private String  CityHome; 
	private String  StateHome; 
	private String  CountryHome; 
	private String  CityIDHome;
	private String  StateIDHome; 
	private String  CountryIDHome;	
	private String	Timezone ;
	private String	ProfileImage;
	private String	RelationshipStatus;
	private String	BodyType;
	private String	Height;
	private String	HeightViewType;
	private String	HairColor; 
	private String	EyeColor;
	private String	Sexuality ;
	private String	BestFeature;
	private String	Exercise ;	
	private String	Smoke; 	
	private String	Drink ;
	private String	DrugUser;
	private String	BodyArt; 
	private String	Profession ;
	private String	AnnualIncome; 	
	private String	ReligiousAffiliations ;	
	private String	HaveChildren ;	
	private String	WantChildren; 
	private String	HavePets ;	
	private String	CreatedDate ;
	private String	ActivationDate; 
	private String	UpdatedDate;
	private String	DeletionDate;
	private String	LastLoginDate; 
	private String	Status; 
	private String	IsCrawlerAllow; 
	private String	Role ;	
	private String	OnlineStatus; 	
	private String	ZodiacSign ;
	private String	ZodiacAnimal ;	
	private String	InMyOwnWords ;
	private String	WhoCanSeeInSearch; 
	private String	Address ;
	private String	Neighborhood ;	
	private String	Website;
	private String	ShowWelcomePage ;
	private String	ShowCompleteStatus; 
	private String	IsFundermailSent; 	
	private String	MusicListen ;	
	private String	MoviesWatch; 
	private String	BooksRead ;	
	private String	Passions ;	
	private String	OtherInterests; 
	private String	GetsMeExcited ;	
	private String	ContactDB ;	
	private String	UpdateDB ;
	private String	InfoDB ;
	private String	CommentDB; 
	private String	ContentDB ;	
	private String	MessageDB ;
	private String	FolderDB ;	
	private String	NotificationDB; 
	private String	SearchDB;
	private String ProfileFileID;
	private String	YTKSearch;
	private String	PublicSearch;
	
	private String	ProfileFileJson;
	
	
	@JsonProperty("ID")
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	
	@JsonProperty("DBID")
	public String getDBID() {
		return DBID;
	}
	public void setDBID(String dBID) {
		DBID = dBID;
	}
	
	@JsonProperty("CategoryID")
	public String getCategoryID() {
		return CategoryID;
	}
	public void setCategoryID(String categoryID) {
		CategoryID = categoryID;
	}
	
	@JsonProperty("CategoryName")
	public String getCategoryName() {
		return CategoryName;
	}
	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}
	
	@JsonProperty("ScreenName")
	public String getScreenName() {
		return ScreenName;
	}
	public void setScreenName(String screenName) {
		ScreenName = screenName;
	}
	
	@JsonProperty("ScreenNameStatus")
	public String getScreenNameStatus() {
		return ScreenNameStatus;
	}
	public void setScreenNameStatus(String screenNameStatus) {
		ScreenNameStatus = screenNameStatus;
	}
	
	@JsonProperty("FirstName")
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	
	@JsonProperty("MiddleName")
	public String getMiddleName() {
		return MiddleName;
	}
	public void setMiddleName(String middleName) {
		MiddleName = middleName;
	}
	
	@JsonProperty("LastName")
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	
	@JsonProperty("Name")
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	@JsonProperty("AlternateName")
	public String getAlternateName() {
		return AlternateName;
	}
	public void setAlternateName(String alternateName) {
		AlternateName = alternateName;
	}
	
	@JsonProperty("DisplayAlternateName")
	public String getDisplayAlternateName() {
		return DisplayAlternateName;
	}
	public void setDisplayAlternateName(String displayAlternateName) {
		DisplayAlternateName = displayAlternateName;
	}
	
	@JsonProperty("Email")
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	
	@JsonProperty("ZipCode")
	public String getZipCode() {
		return ZipCode;
	}
	public void setZipCode(String zipCode) {
		ZipCode = zipCode;
	}
	
	@JsonProperty("BirthDay")
	public String getBirthDay() {
		return BirthDay;
	}
	public void setBirthDay(String birthDay) {
		BirthDay = birthDay;
	}
	
	@JsonProperty("Gender")
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	
	@JsonProperty("City")
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	
	@JsonProperty("State")
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	
	@JsonProperty("Country")
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	
	@JsonProperty("CityID")
	public String getCityID() {
		return CityID;
	}
	public void setCityID(String cityID) {
		CityID = cityID;
	}
	
	@JsonProperty("StateID")
	public String getStateID() {
		return StateID;
	}
	public void setStateID(String stateID) {
		StateID = stateID;
	}
	
	@JsonProperty("CountryID")
	public String getCountryID() {
		return CountryID;
	}
	public void setCountryID(String countryID) {
		CountryID = countryID;
	}
	
	@JsonProperty("Timezone")
	public String getTimezone() {
		return Timezone;
	}
	public void setTimezone(String timezone) {
		Timezone = timezone;
	}
	
	@JsonProperty("ProfileImage")
	public String getProfileImage() {
		return ProfileImage;
	}
	public void setProfileImage(String profileImage) {
		ProfileImage = profileImage;
	}
	
	@JsonProperty("RelationshipStatus")
	public String getRelationshipStatus() {
		return RelationshipStatus;
	}
	public void setRelationshipStatus(String relationshipStatus) {
		RelationshipStatus = relationshipStatus;
	}
	
	@JsonProperty("BodyType")
	public String getBodyType() {
		return BodyType;
	}
	public void setBodyType(String bodyType) {
		BodyType = bodyType;
	}
	
	@JsonProperty("Height")
	public String getHeight() {
		return Height;
	}
	public void setHeight(String height) {
		Height = height;
	}
	
	@JsonProperty("HeightViewType")
	public String getHeightViewType() {
		return HeightViewType;
	}
	public void setHeightViewType(String heightViewType) {
		HeightViewType = heightViewType;
	}
	
	@JsonProperty("HairColor")
	public String getHairColor() {
		return HairColor;
	}
	public void setHairColor(String hairColor) {
		HairColor = hairColor;
	}
	
	@JsonProperty("EyeColor")
	public String getEyeColor() {
		return EyeColor;
	}
	public void setEyeColor(String eyeColor) {
		EyeColor = eyeColor;
	}
	
	@JsonProperty("Sexuality")
	public String getSexuality() {
		return Sexuality;
	}
	public void setSexuality(String sexuality) {
		Sexuality = sexuality;
	}
	
	@JsonProperty("BestFeature")
	public String getBestFeature() {
		return BestFeature;
	}
	public void setBestFeature(String bestFeature) {
		BestFeature = bestFeature;
	}
	
	@JsonProperty("Exercise")
	public String getExercise() {
		return Exercise;
	}
	public void setExercise(String exercise) {
		Exercise = exercise;
	}
	
	@JsonProperty("Smoke")
	public String getSmoke() {
		return Smoke;
	}
	public void setSmoke(String smoke) {
		Smoke = smoke;
	}
	
	@JsonProperty("Drink")
	public String getDrink() {
		return Drink;
	}
	public void setDrink(String drink) {
		Drink = drink;
	}
	
	@JsonProperty("DrugUser")
	public String getDrugUser() {
		return DrugUser;
	}
	public void setDrugUser(String drugUser) {
		DrugUser = drugUser;
	}
	
	@JsonProperty("BodyArt")
	public String getBodyArt() {
		return BodyArt;
	}
	public void setBodyArt(String bodyArt) {
		BodyArt = bodyArt;
	}
	
	@JsonProperty("Profession")
	public String getProfession() {
		return Profession;
	}
	public void setProfession(String profession) {
		Profession = profession;
	}
	
	@JsonProperty("AnnualIncome")
	public String getAnnualIncome() {
		return AnnualIncome;
	}
	public void setAnnualIncome(String annualIncome) {
		AnnualIncome = annualIncome;
	}
	
	@JsonProperty("ReligiousAffiliations")
	public String getReligiousAffiliations() {
		return ReligiousAffiliations;
	}
	public void setReligiousAffiliations(String religiousAffiliations) {
		ReligiousAffiliations = religiousAffiliations;
	}
	
	@JsonProperty("HaveChildren")
	public String getHaveChildren() {
		return HaveChildren;
	}
	public void setHaveChildren(String haveChildren) {
		HaveChildren = haveChildren;
	}
	
	@JsonProperty("WantChildren")
	public String getWantChildren() {
		return WantChildren;
	}
	public void setWantChildren(String wantChildren) {
		WantChildren = wantChildren;
	}
	
	@JsonProperty("HavePets")
	public String getHavePets() {
		return HavePets;
	}
	public void setHavePets(String havePets) {
		HavePets = havePets;
	}
	
	@JsonProperty("CreatedDate")
	public String getCreatedDate() {
		return CreatedDate;
	}
	public void setCreatedDate(String createdDate) {
		CreatedDate = createdDate;
	}
	
	@JsonProperty("ActivationDate")
	public String getActivationDate() {
		return ActivationDate;
	}
	public void setActivationDate(String activationDate) {
		ActivationDate = activationDate;
	}
	
	@JsonProperty("UpdatedDate")
	public String getUpdatedDate() {
		return UpdatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		UpdatedDate = updatedDate;
	}
	
	@JsonProperty("DeletionDate")
	public String getDeletionDate() {
		return DeletionDate;
	}
	public void setDeletionDate(String deletionDate) {
		DeletionDate = deletionDate;
	}
	
	@JsonProperty("LastLoginDate")
	public String getLastLoginDate() {
		return LastLoginDate;
	}
	public void setLastLoginDate(String lastLoginDate) {
		LastLoginDate = lastLoginDate;
	}
	
	@JsonProperty("Status")
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
	@JsonProperty("IsCrawlerAllow")
	public String getIsCrawlerAllow() {
		return IsCrawlerAllow;
	}
	
	public void setIsCrawlerAllow(String isCrawlerAllow) {
		IsCrawlerAllow = isCrawlerAllow;
	}
	
	@JsonProperty("Role")
	public String getRole() {
		return Role;
	}
	
	public void setRole(String role) {
		Role = role;
	}
	
	@JsonProperty("OnlineStatus")
	public String getOnlineStatus() {
		return OnlineStatus;
	}
	public void setOnlineStatus(String onlineStatus) {
		OnlineStatus = onlineStatus;
	}
	
	@JsonProperty("ZodiacSign")
	public String getZodiacSign() {
		return ZodiacSign;
	}
	public void setZodiacSign(String zodiacSign) {
		ZodiacSign = zodiacSign;
	}
	
	@JsonProperty("ZodiacAnimal")
	public String getZodiacAnimal() {
		return ZodiacAnimal;
	}
	public void setZodiacAnimal(String zodiacAnimal) {
		ZodiacAnimal = zodiacAnimal;
	}
	
	@JsonProperty("InMyOwnWords")
	public String getInMyOwnWords() {
		return InMyOwnWords;
	}
	public void setInMyOwnWords(String inMyOwnWords) {
		InMyOwnWords = inMyOwnWords;
	}
	
	@JsonProperty("WhoCanSeeInSearch")
	public String getWhoCanSeeInSearch() {
		return WhoCanSeeInSearch;
	}
	public void setWhoCanSeeInSearch(String whoCanSeeInSearch) {
		WhoCanSeeInSearch = whoCanSeeInSearch;
	}
	
	@JsonProperty("Address")
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	
	@JsonProperty("Neighborhood")
	public String getNeighborhood() {
		return Neighborhood;
	}
	public void setNeighborhood(String neighborhood) {
		Neighborhood = neighborhood;
	}
	
	@JsonProperty("Website")
	public String getWebsite() {
		return Website;
	}
	public void setWebsite(String website) {
		Website = website;
	}
	
	@JsonProperty("ShowWelcomePage")
	public String getShowWelcomePage() {
		return ShowWelcomePage;
	}
	public void setShowWelcomePage(String showWelcomePage) {
		ShowWelcomePage = showWelcomePage;
	}
	
	@JsonProperty("ShowCompleteStatus")
	public String getShowCompleteStatus() {
		return ShowCompleteStatus;
	}
	public void setShowCompleteStatus(String showCompleteStatus) {
		ShowCompleteStatus = showCompleteStatus;
	}
	
	@JsonProperty("IsFundermailSent")
	public String getIsFundermailSent() {
		return IsFundermailSent;
	}
	public void setIsFundermailSent(String isFundermailSent) {
		IsFundermailSent = isFundermailSent;
	}
	
	@JsonProperty("MusicListen")
	public String getMusicListen() {
		return MusicListen;
	}
	public void setMusicListen(String musicListen) {
		MusicListen = musicListen;
	}
	
	@JsonProperty("MoviesWatch")
	public String getMoviesWatch() {
		return MoviesWatch;
	}
	public void setMoviesWatch(String moviesWatch) {
		MoviesWatch = moviesWatch;
	}
	
	@JsonProperty("BooksRead")
	public String getBooksRead() {
		return BooksRead;
	}
	public void setBooksRead(String booksRead) {
		BooksRead = booksRead;
	}
	
	@JsonProperty("Passions")
	public String getPassions() {
		return Passions;
	}
	public void setPassions(String passions) {
		Passions = passions;
	}
	
	@JsonProperty("OtherInterests")
	public String getOtherInterests() {
		return OtherInterests;
	}
	public void setOtherInterests(String otherInterests) {
		OtherInterests = otherInterests;
	}
	
	@JsonProperty("GetsMeExcited")
	public String getGetsMeExcited() {
		return GetsMeExcited;
	}
	public void setGetsMeExcited(String getsMeExcited) {
		GetsMeExcited = getsMeExcited;
	}
	
	@JsonProperty("ContactDB")
	public String getContactDB() {
		return ContactDB;
	}
	public void setContactDB(String contactDB) {
		ContactDB = contactDB;
	}
	
	@JsonProperty("UpdateDB")
	public String getUpdateDB() {
		return UpdateDB;
	}
	public void setUpdateDB(String updateDB) {
		UpdateDB = updateDB;
	}
	
	@JsonProperty("InfoDB")
	public String getInfoDB() {
		return InfoDB;
	}
	public void setInfoDB(String infoDB) {
		InfoDB = infoDB;
	}
	
	@JsonProperty("CommentDB")
	public String getCommentDB() {
		return CommentDB;
	}
	public void setCommentDB(String commentDB) {
		CommentDB = commentDB;
	}
	
	@JsonProperty("ContentDB")	
	public String getContentDB() {
		return ContentDB;
	}
	public void setContentDB(String contentDB) {
		ContentDB = contentDB;
	}
	
	@JsonProperty("MessageDB")
	public String getMessageDB() {
		return MessageDB;
	}
	public void setMessageDB(String messageDB) {
		MessageDB = messageDB;
	}
	
	@JsonProperty("FolderDB")
	public String getFolderDB() {
		return FolderDB;
	}
	public void setFolderDB(String folderDB) {
		FolderDB = folderDB;
	}
	
	@JsonProperty("NotificationDB")
	public String getNotificationDB() {
		return NotificationDB;
	}
	public void setNotificationDB(String notificationDB) {
		NotificationDB = notificationDB;
	}
	
	@JsonProperty("SearchDB")
	public String getSearchDB() {
		return SearchDB;
	}
	public void setSearchDB(String searchDB) {
		SearchDB = searchDB;
	}
	
	@JsonProperty("CityCurrent")
	public String getCityCurrent() {
		return CityCurrent;
	}
	public void setCityCurrent(String cityCurrent) {
		CityCurrent = cityCurrent;
	}
	
	@JsonProperty("StateCurrent")
	public String getStateCurrent() {
		return StateCurrent;
	}
	public void setStateCurrent(String stateCurrent) {
		StateCurrent = stateCurrent;
	}
	
	@JsonProperty("CountryCurrent")
	public String getCountryCurrent() {
		return CountryCurrent;
	}
	public void setCountryCurrent(String countryCurrent) {
		CountryCurrent = countryCurrent;
	}
	
	@JsonProperty("CityIDCurrent")
	public String getCityIDCurrent() {
		return CityIDCurrent;
	}
	public void setCityIDCurrent(String cityIDCurrent) {
		CityIDCurrent = cityIDCurrent;
	}
	
	@JsonProperty("StateIDCurrent")
	public String getStateIDCurrent() {
		return StateIDCurrent;
	}
	public void setStateIDCurrent(String stateIDCurrent) {
		StateIDCurrent = stateIDCurrent;
	}
	
	@JsonProperty("CountryIDCurrent")
	public String getCountryIDCurrent() {
		return CountryIDCurrent;
	}
	public void setCountryIDCurrent(String countryIDCurrent) {
		CountryIDCurrent = countryIDCurrent;
	}
	
	@JsonProperty("CityHome")
	public String getCityHome() {
		return CityHome;
	}
	public void setCityHome(String cityHome) {
		CityHome = cityHome;
	}
	
	@JsonProperty("StateHome")
	public String getStateHome() {
		return StateHome;
	}
	public void setStateHome(String stateHome) {
		StateHome = stateHome;
	}
	
	@JsonProperty("CountryHome")
	public String getCountryHome() {
		return CountryHome;
	}
	public void setCountryHome(String countryHome) {
		CountryHome = countryHome;
	}
	
	@JsonProperty("CityIDHome")
	public String getCityIDHome() {
		return CityIDHome;
	}
	public void setCityIDHome(String cityIDHome) {
		CityIDHome = cityIDHome;
	}
	
	@JsonProperty("StateIDHome")
	public String getStateIDHome() {
		return StateIDHome;
	}
	public void setStateIDHome(String stateIDHome) {
		StateIDHome = stateIDHome;
	}
	
	@JsonProperty("CountryIDHome")
	public String getCountryIDHome() {
		return CountryIDHome;
	}
	public void setCountryIDHome(String countryIDHome) {
		CountryIDHome = countryIDHome;
	}
	
	@JsonProperty("BirthdayOptions")
	public String getBirthdayOptions() {
		return BirthdayOptions;
	}
	public void setBirthdayOptions(String birthdayOptions) {
		BirthdayOptions = birthdayOptions;
	}
	
	@JsonProperty("AnniversaryDay")
	public String getAnniversaryDay() {
		return AnniversaryDay;
	}
	public void setAnniversaryDay(String anniversaryDay) {
		AnniversaryDay = anniversaryDay;
	}
	
	@JsonProperty("YTKSearch")
	public String getYTKSearch() {
		return YTKSearch;
	}
	public void setYTKSearch(String yTKSearch) {
		YTKSearch = yTKSearch;
	}
	
	@JsonProperty("PublicSearch")
	public String getPublicSearch() {
		return PublicSearch;
	}
	public void setPublicSearch(String publicSearch) {
		PublicSearch = publicSearch;
	}
	
	@JsonProperty("ProfileFileID")
	public String getProfileFileID() {
		return ProfileFileID;
	}
	public void setProfileFileID(String profileFileID) {
		ProfileFileID = profileFileID;
	}
	
	@JsonProperty("ProfileFileJson")
	public String getProfileFileJson() {
		return ProfileFileJson;
	}
	public void setProfileFileJson(String profileFileJson) {
		ProfileFileJson = profileFileJson;
	}
	
	
	
	
}
