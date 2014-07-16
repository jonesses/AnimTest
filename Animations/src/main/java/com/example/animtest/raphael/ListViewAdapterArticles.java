package com.example.animtest.raphael;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.animtest.animations.R;

import java.util.ArrayList;

public class ListViewAdapterArticles extends ArrayAdapter<Article> {

	private ArrayList<Article> myArticles;
	private Context context;
	private String header;
	private Typeface font, font_bold;

	public ListViewAdapterArticles(Context context, ArrayList<Article> myArticles, String header) {
		super(context, R.layout.layout_article, myArticles);
		this.context = context;
		this.myArticles = myArticles;
		this.header = header;
        this.font = Typeface.createFromAsset(context.getAssets(), "font/Georgia.ttf");
		this.font_bold = Typeface.createFromAsset(context.getAssets(),"font/Georgia_Bold.ttf");
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View itemView = convertView;

		if (itemView == null) {
			itemView = ((Activity) context).getLayoutInflater().inflate(R.layout.layout_article, parent, false);
		}

		Article currentArticle = myArticles.get(position);
		
		TextView articleHeader = (TextView) itemView.findViewById(R.id.articleHeader);
		TextView articleContent = (TextView) itemView.findViewById(R.id.articleContent);
		TextView articleSubHeader = (TextView) itemView.findViewById(R.id.articleSubHeader);
		ImageView articleBitmap = (ImageView) itemView.findViewById(R.id.articleBitmap);
		articleHeader.setTypeface(font_bold);
		articleContent.setTypeface(font);
		articleSubHeader.setTypeface(font_bold);

		if(position==0&&header.length()>5){
			articleHeader.setVisibility(View.VISIBLE);
			articleHeader.setText(header);
		}else{
			articleHeader.setVisibility(View.GONE);
		}		
		if(currentArticle.getSubHeader().length()!=0){
			articleSubHeader.setVisibility(View.VISIBLE);
			articleSubHeader.setText(currentArticle.getSubHeader());
		}else{
			articleSubHeader.setVisibility(View.GONE);
		}
		if(currentArticle.getContent().length()!=0){
			articleContent.setVisibility(View.VISIBLE);
			articleContent.setText(currentArticle.getContent());
		}else{
			articleContent.setVisibility(View.GONE);
		}
		if(currentArticle.getImage()!=null){
			articleBitmap.setVisibility(View.VISIBLE);
			articleBitmap.setImageBitmap(currentArticle.getImage());
		}else{
			articleBitmap.setVisibility(View.GONE);
		}

		return itemView;
	}
}