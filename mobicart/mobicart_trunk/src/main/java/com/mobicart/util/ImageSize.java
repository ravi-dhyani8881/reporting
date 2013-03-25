package com.mobicart.util;

public class ImageSize {

	private int width;
	private int height;
	
	
	
	public ImageSize() {
		super();
	}
	public ImageSize( int width,int height) {
		super();
		this.width = width;
		this.height = height;
	}
	
	public ImageSize(String text) {
		super();
		String size[]=text.split("x");
		this.width =Integer.parseInt(size[0]);
		this.height = Integer.parseInt(size[1]);
	}

	
	public int getHeight() {
		return height;
	}
	public void setHeeght(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	
	public static ImageSize parse(String text){
		String size[]=text.split("x");
		 
		return  new ImageSize(Integer.parseInt(size[0].trim()),Integer.parseInt(size[1].trim()));
	}
	
	
}
