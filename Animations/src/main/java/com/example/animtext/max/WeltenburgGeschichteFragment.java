package com.example.animtext.max;

import java.util.ArrayList;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.animtest.animations.R;
import com.example.animtest.raphael.BischofshofApplikation;
import com.example.animtest.raphael.Geschichte;

import de.ur.mi.projektion.bischofshof.listeners.OnFragmentInteractionListener;

public class WeltenburgGeschichteFragment extends Fragment implements ViewPager.OnPageChangeListener, OnClickListener {
    View v; 
	private Typeface font, fontBold;
	private ArrayList<Geschichte> weltenburg_geschichte;
	private Point p;
	private Display display;
	private KKViewPager mPager;
	private TestFragmentAdapter mAdapter;
	private Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12, button13,
			button14, button15, button16, button17;
	private ArrayList<Button> buttons;
	private int alphaButtons = 255;
	private int currentPage;
	private HorizontalScrollView sv;
	private int arg2Old;
	private RelativeLayout timelineLayout;
	private LinearLayout backLayout;
	private int firstButtonId; 
	private int currentTimelinePos = 0;
	private boolean scrollInForLoop = false;
	private int savedButtonPosX;
	int[] loc = new int[2];
	private Button currentButton;
	private int scrollingByUser;
	private Handler mHandler = new Handler();
	private Boolean disablePageDetection = false;
	private Runnable runnable;
    private OnFragmentInteractionListener fragmentInteractionListener;
    private BischofshofApplikation app;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.layout_weltenburg_geschichte, container, false);
        referenceClasses();
		referenceFonts();
		referenceUIElements();

		setButtonYear();
		setKKViewPager();
		getFirstButtonPos();
        return v;
    }
    
    private void getFirstButtonPos() {		
		final Handler handler = new Handler();
	    handler.postDelayed(new Runnable() {
		      @Override
		      public void run() {		    	  		
		    	  button1.getLocationOnScreen(loc);
		    	  savedButtonPosX = loc[0];
		      }
	    }, 400);
		
	}

	private void referenceFonts() {
		font = Typeface.createFromAsset(getActivity().getAssets(), "font/Georgia.ttf");
		fontBold = Typeface.createFromAsset(getActivity().getAssets(), "font/Georgia_Bold.ttf");

	}
	
	private void scrollToPage(int currentPos, int newPos) {
		int differenz = 0;
		boolean scrollLeft = false;
		
		if(newPos > currentPos){
			differenz = newPos - currentPos;
			scrollLeft = false;
		}else if(currentPos > newPos){
			differenz = currentPos - newPos;
			scrollLeft = true;
		}

		final int mDif = differenz;
		final boolean scrollPos = scrollLeft;
		disablePageDetection = true;
		final Handler mHandler = new Handler();
		mHandler.postDelayed(new Runnable() {			
			private int pos = 0;
		    @Override
		    public void run() {	
		    	if(pos==mDif){
		    		mHandler.removeCallbacks(runnable);
		    		disablePageDetection = false;
		    	}else{
		    		if(scrollPos){
						mPager.scrollOneLeft();
					}else{
						mPager.scrollOneRight();
					}		      
		    		mHandler.postDelayed(this, 50);
		    		pos++;
		    	}	    	  
		    }
	    }, 0);			
	}

	private void setButtonYear() {
		for (int i = 0; i < 17; i++) {
			buttons.get(i).setText(String.valueOf(weltenburg_geschichte.get(i).getGeschichteJahr()));
		}
	}
	
	private void referenceUIElements() {
		sv = (HorizontalScrollView) v.findViewById(R.id.timeLine);	
		mPager = (KKViewPager) v.findViewById(R.id.kk_pager);
		p = new Point();
		referenceButtons();
		setTimelineButtonSelected(currentTimelinePos);
	}

	private void referenceButtons() {
		button1 = (Button) v.findViewById(R.id.Anno43);
		button1.setOnClickListener(this);
		buttons.add(button1);
		button2 = (Button) v.findViewById(R.id.Anno617);
		button2.setOnClickListener(this);
		buttons.add(button2);
		button3 = (Button) v.findViewById(R.id.Anno800);
		button3.setOnClickListener(this);
		buttons.add(button3);
		button4 = (Button) v.findViewById(R.id.Anno899);
		button4.setOnClickListener(this);
		buttons.add(button4);
		button5 = (Button) v.findViewById(R.id.Anno932);
		button5.setOnClickListener(this);
		buttons.add(button5);
		button6 = (Button) v.findViewById(R.id.Anno1050);
		button6.setOnClickListener(this);
		buttons.add(button6);
		button7 = (Button) v.findViewById(R.id.Anno1191);
		button7.setOnClickListener(this);
		buttons.add(button7);
		button8 = (Button) v.findViewById(R.id.Anno1441);
		button8.setOnClickListener(this);
		buttons.add(button8);
		button9 = (Button) v.findViewById(R.id.Anno1546);
		button9.setOnClickListener(this);
		buttons.add(button9);
		button10 = (Button) v.findViewById(R.id.Anno1553);
		button10.setOnClickListener(this);
		buttons.add(button10);
		button11 = (Button) v.findViewById(R.id.Anno1626);
		button11.setOnClickListener(this);
		buttons.add(button11);
		button12 = (Button) v.findViewById(R.id.Anno1686);
		button12.setOnClickListener(this);
		buttons.add(button12);
		button13 = (Button) v.findViewById(R.id.Anno1713);
		button13.setOnClickListener(this);
		buttons.add(button13);
		button14 = (Button) v.findViewById(R.id.Anno1803);
		button14.setOnClickListener(this);
		buttons.add(button14);
		button15 = (Button) v.findViewById(R.id.Anno1858);
		button15.setOnClickListener(this);
		buttons.add(button15);
		button16 = (Button) v.findViewById(R.id.Anno1913);
		button16.setOnClickListener(this);
		buttons.add(button16);
		button17 = (Button) v.findViewById(R.id.Anno2014);
		button17.setOnClickListener(this);
		buttons.add(button17);
		currentButton = button1;
	}

	private void referenceClasses() {
		app = (BischofshofApplikation) getActivity().getApplication();
		weltenburg_geschichte = sort(app.getWeltenburgGeschichte());
		backLayout = (LinearLayout) v.findViewById (R.id.backLayoutGeschichte);
		backLayout.setOnClickListener(this);
		buttons = new ArrayList<Button>();
		Point size = new Point();	
		Display display = getActivity().getWindowManager().getDefaultDisplay();
		display.getSize(size);
		int width = size.x;
		timelineLayout = (RelativeLayout) v.findViewById (R.id.layouttimeline);
		timelineLayout.setPadding(width/2-100,0,width/2-150,0);
	}

	// Mist - �bergangsweise
	private ArrayList<Geschichte> sort(
			ArrayList<Geschichte> weltenburgGeschichte) {
		ArrayList<Geschichte> sortedArray = new ArrayList<Geschichte>();
		for (int i = 1; i < 2100; i++) {
			for (int m = 0; m < weltenburgGeschichte.size(); m++) {
				if (i == weltenburgGeschichte.get(m).getGeschichteJahr()) {
					sortedArray.add(weltenburgGeschichte.get(m));
				}
			}
		}
		return sortedArray;
	}

	private void setKKViewPager() {
		display = getActivity().getWindowManager().getDefaultDisplay();
		display.getSize(p);
		mAdapter = new TestFragmentAdapter(getActivity().getSupportFragmentManager(), mPager, getActivity().getApplicationContext(), weltenburg_geschichte);
		mPager.setDisplay(display);
		mPager.setAdapter(mAdapter);
		mPager.setOnPageChangeListener(this);
		mPager.setPageMargin(-((p.x * 18) / 90));
	}

//	@Override
//	public void onWindowFocusChanged(boolean hasFocus) {
//		super.onWindowFocusChanged(hasFocus);
//		mPager.onWindowFocusChanged(hasFocus);
//	}

	public void onPageScrollStateChanged(int scrollFactor, final int newPos, final boolean buttonTimeline) {							
		final int oldPos = currentTimelinePos;
		scrollingByUser = 0;
		loc = new int[2];
		currentButton.getLocationOnScreen(loc);
		scrollingByUser = loc[0]-savedButtonPosX;

        Log.e(this.getClass().getName(), scrollingByUser + " - "+scrollFactor);

		sv.smoothScrollBy(scrollingByUser+(scrollFactor*300), 0);
		
		currentTimelinePos = newPos;
		setTimelineButtonSelected(currentTimelinePos);	
		
		final Handler handler = new Handler();
	    handler.postDelayed(new Runnable() {
		      @Override
		      public void run() {		    	 	    	  		
		  		  currentButton = buttons.get(currentTimelinePos);
		  		  currentButton.getLocationOnScreen(loc);
		  		  savedButtonPosX = loc[0];		  		 
		  		  if(buttonTimeline){
		  			  scrollToPage(oldPos, currentTimelinePos);
		  		  }			  		  
		      }
	    }, 100);
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2){		

	}

	@Override
	public void onPageSelected(int arg0) {
		if(!disablePageDetection){
			Log.e(this.getClass().getName(), "Disabled");
			onPageScrollStateChanged(arg0-currentTimelinePos, arg0, false);
			mPager.getReadyWithNextTwoView(arg0);
		}	
	}

	@Override
	public void onClick(View v) {
		 switch (v.getId()) {
	     case R.id.Anno43:
	    	 onPageScrollStateChanged((v.getId()-button1.getId())-currentTimelinePos, (v.getId()-button1.getId()), true);
	    	 break;
	     case R.id.Anno617:
	    	 onPageScrollStateChanged((v.getId()-button1.getId())-currentTimelinePos, (v.getId()-button1.getId()), true);
	    	 break;
	     case R.id.Anno800:
	    	 onPageScrollStateChanged((v.getId()-button1.getId())-currentTimelinePos, (v.getId()-button1.getId()), true);
	    	 break;
	     case R.id.Anno899:
	    	 onPageScrollStateChanged((v.getId()-button1.getId())-currentTimelinePos, (v.getId()-button1.getId()), true);
	    	 break;
	     case R.id.Anno932:
	    	 onPageScrollStateChanged((v.getId()-button1.getId())-currentTimelinePos, (v.getId()-button1.getId()), true);
	    	 break;
	     case R.id.Anno1050:
	    	 onPageScrollStateChanged((v.getId()-button1.getId())-currentTimelinePos, (v.getId()-button1.getId()), true);
	    	 break;
	     case R.id.Anno1191:
	    	 onPageScrollStateChanged((v.getId()-button1.getId())-currentTimelinePos, (v.getId()-button1.getId()), true);
	    	 break;
	     case R.id.Anno1441:
	    	 onPageScrollStateChanged((v.getId()-button1.getId())-currentTimelinePos, (v.getId()-button1.getId()), true);
	    	 break;
	     case R.id.Anno1546:
	    	 onPageScrollStateChanged((v.getId()-button1.getId())-currentTimelinePos, (v.getId()-button1.getId()), true);
	    	 break;
	     case R.id.Anno1553:
	    	 onPageScrollStateChanged((v.getId()-button1.getId())-currentTimelinePos, (v.getId()-button1.getId()), true);
	    	 break;
	     case R.id.Anno1626:
	    	 onPageScrollStateChanged((v.getId()-button1.getId())-currentTimelinePos, (v.getId()-button1.getId()), true);
	    	 break;
	     case R.id.Anno1686:
	    	 onPageScrollStateChanged((v.getId()-button1.getId())-currentTimelinePos, (v.getId()-button1.getId()), true);
	    	 break;
	     case R.id.Anno1713:
	    	 onPageScrollStateChanged((v.getId()-button1.getId())-currentTimelinePos, (v.getId()-button1.getId()), true);
	    	 break;
	     case R.id.Anno1803:
	    	 onPageScrollStateChanged((v.getId()-button1.getId())-currentTimelinePos, (v.getId()-button1.getId()), true);
	    	 break;
	     case R.id.Anno1858:
	    	 onPageScrollStateChanged((v.getId()-button1.getId())-currentTimelinePos, (v.getId()-button1.getId()), true);
	    	 break;
	     case R.id.Anno1913:
	    	 onPageScrollStateChanged((v.getId()-button1.getId())-currentTimelinePos, (v.getId()-button1.getId()), true);
	    	 break;
	     case R.id.Anno2014:
	    	 onPageScrollStateChanged((v.getId()-button1.getId())-currentTimelinePos, (v.getId()-button1.getId()), true);
	    	 break;
	     case R.id.backLayoutGeschichte:
	    	 fragmentInteractionListener.onTransitionFromFullscreenRequested();
	    	 break;
	     default:
	    	 break;
	     }
	}
	
	public void setTimelineButtonSelected(int id){
		for(int i = 0; i < buttons.size(); i++){
			if(i!=id){
				buttons.get(i).setTypeface(font);
				buttons.get(i).setTextColor(Color.BLACK);
			}else{
				buttons.get(currentTimelinePos).setTypeface(fontBold);
				buttons.get(currentTimelinePos).setTextColor(Color.RED);
			}
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}

    public void setFragmentInteractionListener(OnFragmentInteractionListener fragmentInteractionListener) {
        this.fragmentInteractionListener = fragmentInteractionListener;
    }
}

