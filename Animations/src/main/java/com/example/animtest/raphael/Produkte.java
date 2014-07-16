package com.example.animtest.raphael;

import android.graphics.Bitmap;

public class Produkte {
	
	private Bitmap produktBitmap;
	private String produktTextDe;
	
	public Produkte(Bitmap produktBitmap, String produktTextDe){
		this.produktTextDe = produktTextDe;
		this.produktBitmap = produktBitmap;
	}
	
	public Bitmap getProduktBitmap(){
		return produktBitmap;
	}
	
	public String getProduktDeText(){
		return produktTextDe;
	}
}
