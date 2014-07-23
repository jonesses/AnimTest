package com.example.animtext.max;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.animtest.animations.R;
import com.example.animtest.raphael.Geschichte;

public final class TestFragment extends Fragment {

	private Typeface font, fontBold;
	
	private TextView title, text;
	private ScrollView content;
	private RelativeLayout card;
	private Point p;
	private Display display;
	private Geschichte geschichtePoint;
	private int blurrFaktor = 6;
	private int alphaBackground = 255;
	private int alphaTextBackground = 195;
//	private ImageView bitmap;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}
	
	
	private void setTypeFaces() {		
		font = Typeface.createFromAsset(getActivity().getAssets(), "font/Georgia.ttf");
		fontBold = Typeface.createFromAsset(getActivity().getAssets(), "font/Georgia_Bold.ttf");
		title.setTypeface(fontBold);
		text.setTypeface(font);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ViewGroup Rootview = (ViewGroup) inflater.inflate(R.layout.layout_weltenburg_geschichte_item, container, false);
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);	
		title = (TextView) Rootview.findViewById(R.id.weltenburg_geschichte_year);
		content = (ScrollView) Rootview.findViewById(R.id.weltenburg_geschichte_content);
		text = (TextView) Rootview.findViewById(R.id.weltenburg_geschichte_contentTextView);		
		card = (RelativeLayout) Rootview.findViewById(R.id.weltenburg_geschichte_item);
		
		p = new Point();
		setTypeFaces();
		content.getBackground().setAlpha(alphaTextBackground);
		title.getBackground().setAlpha(alphaTextBackground);
		
		display = getActivity().getWindowManager().getDefaultDisplay();		
		display.getSize(p);		
		lp.rightMargin = lp.leftMargin = ((p.x * 6) / 100);		
		card.setLayoutParams(lp);	
		
		title.setText(geschichtePoint.getGeschichteJahr()+"");		
		text.setText(geschichtePoint.getGeschichteDeText());
		if(geschichtePoint.getGeschichteBitmap()!=null){
			card.setBackground(new BitmapDrawable(geschichtePoint.getGeschichteBitmap()));
			card.getBackground().setAlpha(alphaBackground);
		}
		return Rootview;
	}
	
	public static TestFragment newInstance(Geschichte geschichtePoint, Context c) {
		TestFragment fragment = new TestFragment();
		fragment.geschichtePoint = geschichtePoint;
		return fragment;
	}
	
	
	
	
}
