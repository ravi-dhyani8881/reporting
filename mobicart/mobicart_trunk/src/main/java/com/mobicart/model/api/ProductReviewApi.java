package com.mobicart.model.api;



public class ProductReviewApi {
	
	private Long reviewId;
	private String reviewerName;
	private String reviewerEmail;
	private Integer reviewerRating;
	private String reviewerComment;
	
	
	public Long getReviewId() {
		return reviewId;
	}
	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}
	public String getReviewerName() {
		return reviewerName;
	}
	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}
	public String getReviewerEmail() {
		return reviewerEmail;
	}
	public void setReviewerEmail(String reviewerEmail) {
		this.reviewerEmail = reviewerEmail;
	}
	public Integer getReviewerRating() {
		return reviewerRating;
	}
	public void setReviewerRating(Integer reviewerRating) {
		this.reviewerRating = reviewerRating;
	}
	public String getReviewerComment() {
		return reviewerComment;
	}
	public void setReviewerComment(String reviewerComment) {
		this.reviewerComment = reviewerComment;
	}
	
	
}