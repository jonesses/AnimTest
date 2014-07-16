package com.example.animtest.raphael;

import java.util.ArrayList;

public class GeneralContent {

	private ArrayList<Article> articles;
	private String header;
	
	public GeneralContent(String header, ArrayList<Article> articles){
		this.articles = articles;
		this.header = header;
	}
	
	public String getHeader(){
		return header;
	}
	
	public ArrayList<Article> getArticles(){
		return articles;
	}
}
