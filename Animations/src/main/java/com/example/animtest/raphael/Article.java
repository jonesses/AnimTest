package com.example.animtest.raphael;

import android.graphics.Bitmap;

public class Article {
	
	private String subheader;
	private Bitmap image;
	private String content;
	
	public Article (String subheader, Bitmap image, String content){
		this.subheader = subheader;
		this.image = image;
		this.content = content;
	}

	public String getSubHeader(){
		return subheader;
	}
	
	public Bitmap getImage(){
		return image;
	}
	
	public String getContent(){
		return content;
	}
}
