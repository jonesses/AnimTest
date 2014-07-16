package com.example.animtest.raphael;

import android.graphics.Bitmap;

public class Geschichte {

	Bitmap geschichteBitmap;
	String geschichteDeText, geschichteChText;
	int geschichteJahr;
	
	public Geschichte(Bitmap geschichteBitmap, String geschichteDeText, int geschichteJahr){
		this.geschichteJahr = geschichteJahr;
		this.geschichteDeText = geschichteDeText;
		this.geschichteBitmap = geschichteBitmap;
	}	
	
	public Bitmap getGeschichteBitmap(){
		return geschichteBitmap;
	}
	
	public String getGeschichteDeText(){
		return geschichteDeText;
	}
	
	public int getGeschichteJahr(){
		return geschichteJahr;
	}
}
