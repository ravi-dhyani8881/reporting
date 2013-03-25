package com.ytk.models;

import java.util.List;

import com.ytk.models.ListContact.Contacts;
import com.ytk.models.ListContact.ResponseComing;

public class ListMember  {

	public ListMember(){
		
	}
	
	private ResponseHeader responseHeader;
	private ResponseComing response;
	
	public ResponseHeader getResponseHeader() {
		return responseHeader;
	}

	public void setResponseHeader(ResponseHeader responseHeader) {
		this.responseHeader = responseHeader;
	}
	public ResponseComing getResponse() {
		return response;
	}
	public void setResponse(ResponseComing response) {
		this.response = response;
	}

	public static class ResponseComing {
		
		private Integer numFound;
		private Integer start;
		private List<Members> docs;

		public Integer getStart() {
			return start;
		}

		public void setStart(Integer start) {
			this.start = start;
		}

		public List<Members> getDocs() {
			return docs;
		}

		public void setDocs(List<Members> docs) {
			this.docs = docs;
		}

		public Integer getNumFound() {
			return numFound;
		}

		public void setNumFound(Integer numFound) {
			this.numFound = numFound;
		}

		

	}
	
	public static class Members implements Comparable {
		
		private String member_id;
		private String first_name;
		private String last_name;
		private String isMyFriend;
		private String isConnected;
		private String isMessageSent;
		private String isBlocked;
		
		
		public String getMember_id() {
			return member_id;
		}
		public void setMember_id(String memberId) {
			member_id = memberId;
		}
		public String getFirst_name() {
			return first_name;
		}
		public void setFirst_name(String firstName) {
			first_name = firstName;
		}
		public String getLast_name() {
			return last_name;
		}
		public void setLast_name(String lastName) {
			last_name = lastName;
		}
		public String getIsMyFriend() {
			return isMyFriend;
		}
		public void setIsMyFriend(String isMyFriend) {
			this.isMyFriend = isMyFriend;
		}
		public String getIsConnected() {
			return isConnected;
		}
		public void setIsConnected(String isConnected) {
			this.isConnected = isConnected;
		}
		public String getIsMessageSent() {
			return isMessageSent;
		}
		public void setIsMessageSent(String isMessageSent) {
			this.isMessageSent = isMessageSent;
		}
		public String getIsBlocked() {
			return isBlocked;
		}
		public void setIsBlocked(String isBlocked) {
			this.isBlocked = isBlocked;
		}

			/*[11:22:36 AM] Ramandeep Singh Bhamra: public enum Section
			        {
			            [XmlEnum(Name = "Member")]//Done
			            Member = 0,
			            [XmlEnum(Name = "Forum")]//Done
			            Forum = 1,
			            [XmlEnum(Name = "Communities")]//Done
			            Communities = 2,
			            [XmlEnum(Name = "Banter")]//Done
			            Banter = 3
			        }

			        public enum Type
			        {
			            [XmlEnum(Name = "Status")]
			            Status = 0,
			            [XmlEnum(Name = "FaceToFace")]
			            FaceToFace = 1,
			            [XmlEnum(Name = "Album")]
			            Album = 2,
			            [XmlEnum(Name = "Connection")]
			            Connection = 3,
			            [XmlEnum(Name = "Profile")]
			            Profile = 4,
			            [XmlEnum(Name = "ProfilePhoto")]
			            ProfilePhoto = 5,

			            [XmlEnum(Name = "RelationshipStatus")]
			            RelationshipStatus = 6,
			            [XmlEnum(Name = "LogIn")]
			            LogIn = 7,
			            [XmlEnum(Name = "PhotoUpload")]
			            PhotoUpload = 8,
			            [XmlEnum(Name = "VideoUpload")]
			            VideoUpload = 9,
			            [XmlEnum(Name = "MessageSent")]
			            MessageSent = 10,

			            [XmlEnum(Name = "CommentOnStatus")]
			            CommentOnStatus = 11,
			            [XmlEnum(Name = "CommentOnFaceToFace")]
			            CommentOnFaceToFace = 12,
			            [XmlEnum(Name = "CommentOnAlbum")]
			            CommentOnAlbum = 13,
			            [XmlEnum(Name = "CommentOnPhoto")]
			            CommentOnPhoto = 14,
			            [XmlEnum(Name = "CommentOnVideo")]
			            CommentOnVideo = 15,
			            [XmlEnum(Name = "CommentOnProfilePhoto")]
			            CommentOnProfilePhoto = 16,

			            //Community Section
			            [XmlEnum(Name = "BusinessAdded")]
			            BusinessAdded = 17,
			            [XmlEnum(Name = "BusinessReviewed")]
			            BusinessReviewed = 18,
			            /// <summary>
			            /// Not In Use
			            /// </summary>
			            [XmlEnum(Name = "BusinessRevieweRated")]
			            BusinessRevieweRated = 19,
			            /// <summary>
			            /// Not In Use
			            /// </summary>
			            [XmlEnum(Name = "BusinessBookmarked")]
			            BusinessBookmarked = 20,
			            /// <summary>
			            /// Not In Use
			            /// </summary>
			            [XmlEnum(Name = "BusinessCleverListAdded")]
			            BusinessCleverListAdded = 21,

			            //Forum Section
			            [XmlEnum(Name = "ForumThreadAdded")]
			            ForumThreadAdded = 22,
			            [XmlEnum(Name = "CommentOnForumThread")]
			            CommentOnForumThread = 23,
			            [XmlEnum(Name = "RatedOnForumThread")]
			            RatedOnForumThread = 24,

			            [XmlEnum(Name = "SharedPhoto")]
			            SharedPhoto = 25,
			            [XmlEnum(Name = "SharedVideo")]
			            SharedVideo = 26,

			            [XmlEnum(Name = "PostedPhotoOnOther")]
			            PostedPhotoOnOther = 27,
			            [XmlEnum(Name = "PostedVideoOnOther")]
			            PostedVideoOnOther = 28,

			            [XmlEnum(Name = "PostedLink")]
			            PostedLink = 29
			        }
			*/        
		public int compareTo(Object obj){
			if(obj instanceof Members){
				Members mem = (Members)obj;
				if(this.getFirst_name().toLowerCase().compareTo(mem.getFirst_name().toLowerCase()) < 1  )
				{
					int i = -1;
					if(this.isMessageSent.equals("1"))
						i = i-1;
					if(this.isBlocked.equals("0") )
						i = i-2;
					if(this.getIsMyFriend().equals("true"))
						i = i-3;
					else if(this.getIsMyFriend().equals("false"))
						i = i+(-i)+7;
					return i;
				}
				else
				{
					return 1;
				}
				 
			}else return 10;
		}


	}
	

}
