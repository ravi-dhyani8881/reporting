package com.mobicart.model;

public class ProductVideo {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column product_videos.product_id
	 * @ibatorgenerated  Mon Aug 16 19:46:37 IST 2010
	 */
	private Long productId;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column product_videos.video_id
	 * @ibatorgenerated  Mon Aug 16 19:46:37 IST 2010
	 */
	private Long videoId;

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column product_videos.product_id
	 * @return  the value of product_videos.product_id
	 * @ibatorgenerated  Mon Aug 16 19:46:37 IST 2010
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column product_videos.product_id
	 * @param productId  the value for product_videos.product_id
	 * @ibatorgenerated  Mon Aug 16 19:46:37 IST 2010
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column product_videos.video_id
	 * @return  the value of product_videos.video_id
	 * @ibatorgenerated  Mon Aug 16 19:46:37 IST 2010
	 */
	public Long getVideoId() {
		return videoId;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column product_videos.video_id
	 * @param videoId  the value for product_videos.video_id
	 * @ibatorgenerated  Mon Aug 16 19:46:37 IST 2010
	 */
	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}
}