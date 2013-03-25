/**
 * 
 */
package com.mobicart.util;

import java.awt.Color;

/**
 * @author jasdeep.singh
 *
 */
public class ColorUtils {
	
	
	
	public static String hexToRGB(String hexColor){
		
		Color color=new Color(0, 0, 0);
		try{
		if(hexColor!=null)
		color=Color.decode("0x"+hexColor);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "("+color.getRed()+","+color.getGreen()+","+color.getGreen()+")";
		
	}
	
	
	
	public static String hexToRGBGradient(String hexColor){
		
		Color color=new Color(0, 0, 0);
		try{
		if(hexColor!=null)
		color=Color.decode("0x"+hexColor);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "("+minus20(color.getRed())+","+minus20(color.getGreen())+","+minus20(color.getGreen())+")";
		
	}
	
	
	
	public static int minus20(int original){
		return original>=20?original-20:original; 
	}
	

}
