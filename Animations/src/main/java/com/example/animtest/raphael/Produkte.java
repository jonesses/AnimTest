package com.example.animtest.raphael;

import android.graphics.Bitmap;

public class Produkte {
	
	private Bitmap produktBitmap;
	private String produktTextDe;
	private String produktHeaderDe;
	
	public Produkte(Bitmap produktBitmap, String produktTextDe, String produktHeaderDe){
		this.produktTextDe = produktTextDe;
		this.produktBitmap = produktBitmap;
		this.produktHeaderDe = produktHeaderDe;
	}
	
	public Bitmap getProduktBitmap(){
		return produktBitmap;
	}
	
	public String getProduktDeText(){
		return produktTextDe;
	}
	
	public String getHeader(){
		return produktHeaderDe;
	}
}
