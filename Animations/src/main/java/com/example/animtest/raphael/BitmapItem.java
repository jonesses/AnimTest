package com.example.animtest.raphael;

import android.graphics.Bitmap;

public class BitmapItem {
	
	private String id;
	private Bitmap bitmap;
	
	public BitmapItem(String id, Bitmap bitmap){
		this.id = id;
		this.bitmap = bitmap;
	}
	
	public String getId(){
		return id;
	}
	
	public Bitmap getBitmap(){
		return bitmap;
	}

}
