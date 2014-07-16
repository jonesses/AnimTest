package com.example.animtest.raphael;

import android.graphics.Bitmap;

public class Logos {
	
	private Bitmap bitmapLogo;
	private String deText;
	
	public Logos(Bitmap bitmapLogo, String deText){
		this.deText = deText;
		this.bitmapLogo = bitmapLogo;
	}
	
	public Bitmap getBitmapLogo(){
		return bitmapLogo;
	}
	
	public String getGermanText(){
		return deText;
	}

}
