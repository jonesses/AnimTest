package com.example.animtest.raphael;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Transformation;
import android.widget.BaseAdapter;

import de.ur.mi.ux.weltenburg.fragments.BischofshofApplikation;


public class Carousel extends CarouselSpinner implements GestureDetector.OnGestureListener {
    private int mAnimationDuration = 200;
	private Camera mCamera = new Camera();    	
    private int mDownTouchPosition;
    private View mDownTouchView;
    private FlingRotateRunnable mFlingRunnable = new FlingRotateRunnable();
    private GestureDetector mGestureDetector;
    private int mGravity;
    private boolean mShouldCallbackOnUnselectedItemClick = true;
    private boolean mShouldStopFling;
    private float mTheta = (float)(40f*(Math.PI/180.0));
    private boolean moving = false;
    private boolean scrollingLeft = false;    
    private int selectedPosition = 0;
    private float mDownKeyXPosition;
    private float mWindowWidth;
    private boolean scrolling = false;
    private boolean flinging = false;
    private ImageAdapter adapter;

	public Carousel(Context context)  {
		this(context, null);
	}
	
	public Carousel(Context context, AttributeSet attrs)  {
		this(context, attrs, 0);
	}
	
	public Carousel(Context context, AttributeSet attrs, int defStyle) {		
		super(context, attrs, defStyle);
		
		setChildrenDrawingOrderEnabled(true);
		
		mGestureDetector = new GestureDetector(this.getContext(), this);
		mGestureDetector.setIsLongpressEnabled(true);
		setStaticTransformationsEnabled(true);	
		adapter = new ImageAdapter(getContext());
		adapter.SetImages();
		
	    setAdapter(adapter);
	    setNextSelectedPositionInt(0);
	}
	
	public void refreshShit(){
		adapter.notifyDataSetChanged();
	}

	@Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean retValue = mGestureDetector.onTouchEvent(event); 
        int action = MotionEventCompat.getActionMasked(event);        
        switch(action) {
            case (MotionEvent.ACTION_UP):
            	if(scrolling&&!flinging){
            		moving = false;
            		scrollIntoSlots();
            	}
            	scrolling = false;
            	flinging = false;
                return true;
            default:
            	return retValue;
        }
    } 
    
    //Daf�r zust�ndig, dass die vorderen Bilder die hinteren �berdecken (z-index)
    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
    	ArrayList<CarouselItem> sl = new ArrayList<CarouselItem>();
    	for(int j = 0; j < childCount; j++){
    		CarouselItem view = (CarouselItem)getAdapter().getView(j,null, null);
    		if(i == 0)view.setDrawn(false);
    		sl.add((CarouselItem)getAdapter().getView(j,null, null));
    	}

    	Collections.sort(sl);
    	
    	int idx = 0;
    	
    	for(CarouselItem civ : sl){
    		if(!civ.isDrawn()){
    			civ.setDrawn(true);
    			idx = civ.getIndex();
    			break;
    		}
    	}    	
    	return idx;
    }
    
    //Ordnet die Bilder im Kreis an
	@Override
	protected boolean getChildStaticTransformation(View child, Transformation transformation) {
		transformation.clear();
		transformation.setTransformationType(Transformation.TYPE_MATRIX);
		float centerX = (float)getWidth()/2, centerY = (float)getHeight()/2;
		mWindowWidth = getWidth();
		mCamera.save();
		final Matrix matrix = transformation.getMatrix();				
		mCamera.translate(((CarouselItem)child).getItemX(), ((CarouselItem)child).getItemY(), ((CarouselItem)child).getItemZ());
		mCamera.getMatrix(matrix);				
		matrix.preTranslate(-centerX, -centerY);
		matrix.postTranslate(centerX, centerY);		
		float[] values = new float[9];
		matrix.getValues(values);
		mCamera.restore();
		Matrix mm = new Matrix();
		mm.setValues(values);
		((CarouselItem)child).setCIMatrix(mm);
		child.invalidate();
		return true;
	}   
	
	//Initialisiert die Bilder
	public void layout(int delta, boolean animate){	
        recycleAllViews();        
        detachAllViewsFromParent();      
        
        int count = getAdapter().getCount();
        float angleUnit = 360.0f / count;

        float angleOffset = mSelectedPosition * angleUnit;
        for(int i = 0; i< getAdapter().getCount(); i++){
        	float angle = angleUnit * i - angleOffset;
        	if(angle < 0.0f)
        		angle = 360.0f + angle;
           	makeAndAddView(i, angle);        	
        }
        
        mRecycler.clear();
        invalidate();
        setNextSelectedPositionInt(mSelectedPosition);        
        checkSelectionChanged();        
        mNeedSync = false;           
	}
	
	//Scrollt ein Item nach links
	public void scrollOneItemLeft(int id){
		float count = getAdapter().getCount();
        float angleUnit = -360.0f / count;
        startScrollingIntoSlots(angleUnit);
		setSelectedPositionInt(id);
		selectedPosition = id;
	}
	
	//Scrollt ein Item nach rechts
	public void scrollOneItemRight(int id){
		float count = getAdapter().getCount();
        float angleUnit = 360.0f / count;
        startScrollingIntoSlots(angleUnit);
		setSelectedPositionInt(id);
		selectedPosition = id;
	}
    
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(getAdapter()!=null){
	        mInLayout = true;
	        layout(0, false);
	        mInLayout = false;
        }
    }	   
	
    @Override
    void setSelectedPositionInt(int position) {
        super.setSelectedPositionInt(position);
        super.setNextSelectedPositionInt(position);
    }
    
	public boolean onDown(MotionEvent e){
        mDownTouchPosition = pointToPosition((int) e.getX(), (int) e.getY());        
        if (mDownTouchPosition >= 0) {
            mDownTouchView = getChildAt(mDownTouchPosition - mFirstPosition);
            mDownTouchView.setPressed(true);
        }
        return true;
	}	

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {  
    	flinging = true;
    	mFlingRunnable.startUsingVelocity((int) velocityX);        
        return true;
    }	
    
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {       	
    	scrolling = true;
    	if(distanceX>0){
			scrollingLeft = true;
		}else{
			scrollingLeft = false;
		}
        trackMotionScroll(distanceX/7);   
        
        moving = true;
        return true;
    }			
	
	public void onLongPress(MotionEvent e) {                
	}
	
	public boolean onSingleTapUp(MotionEvent e) {
        mDownKeyXPosition = e.getX();
		if (mDownTouchPosition >= 0) {
        	if (mShouldCallbackOnUnselectedItemClick || mDownTouchPosition == mSelectedPosition) {
                performItemClick(mDownTouchView, mDownTouchPosition, mAdapter.getItemId(mDownTouchPosition));
            }
        	return true;
        }        
        return false;
    }
	
	public void onShowPress(MotionEvent e) {
	}		
	
    private void Calculate3DPosition(CarouselItem child, int diameter, float angleOffset){
        float newDiameter = diameter * 1.5f;
    	angleOffset = angleOffset * (float)(Math.PI/180.0f);    	
    	float x = - (float)(newDiameter/2  * android.util.FloatMath.sin(angleOffset)) + newDiameter/2 - child.getWidth()/2;
    	float z = newDiameter/2 * (1.0f - (float)android.util.FloatMath.cos(angleOffset));
    	float y = - getHeight()/2 + (float) (z * android.util.FloatMath.sin(mTheta));
    	child.setItemX(x - newDiameter / 6);
    	child.setItemZ(z);
    	child.setItemY(y+250);
    }
    	
    private int calculateTop(View child, boolean duringLayout) {
        int myHeight = duringLayout ? getMeasuredHeight() : getHeight();
        int childHeight = duringLayout ? child.getMeasuredHeight() : child.getHeight(); 
        
        int childTop = 0;

        switch (mGravity) {
        case Gravity.TOP:
            childTop = mSpinnerPadding.top;
            break;
        case Gravity.CENTER_VERTICAL:
            int availableSpace = myHeight - mSpinnerPadding.bottom - mSpinnerPadding.top - childHeight;
            childTop = mSpinnerPadding.top + (availableSpace / 2);
            break;
        case Gravity.BOTTOM:
            childTop = myHeight - mSpinnerPadding.bottom - childHeight;
            break;
        }
        return childTop;
    }     	      
	    
    private void makeAndAddView(int position, float angleOffset) {    	
    	CarouselItem child;  
        if (!mDataChanged) {
            child = (CarouselItem)mRecycler.get(position);
            if (child != null) {
                setUpChild(child, child.getIndex(), angleOffset);
            }else{
                child = (CarouselItem)mAdapter.getView(position, null, this);
                setUpChild(child, child.getIndex(), angleOffset);            	
            }
            return;
        }
        child = (CarouselItem)mAdapter.getView(position, null, this);
        setUpChild(child, child.getIndex(), angleOffset);

    }   
    
    public int getSelectedPosition(){
    	return selectedPosition;
    }  
   
    public void scrollIntoSlots(){    	
    	float angle; 
    	
    	ArrayList<CarouselItem> arr = new ArrayList<CarouselItem>();
    	
        for(int i = 0; i < getAdapter().getCount(); i++)
        	arr.add(((CarouselItem)getAdapter().getView(i, null, null)));
        
        Collections.sort(arr, new Comparator<CarouselItem>(){

        	public int compare(CarouselItem c1, CarouselItem c2) {
				int a1 = (int)c1.getCurrentAngle();
				if(a1 > 180)
					a1 = 360 - a1;
				int a2 = (int)c2.getCurrentAngle();
				if(a2 > 180)
					a2 = 360 - a2;
				return (a1 - a2) ;
			}
        	
        });
        
        angle = arr.get(0).getCurrentAngle();
                
        if(angle > 180.0f){
    		angle = -(360.0f - angle);
    	}
    
		if(scrollingLeft){	
    		float currentAngle = 0;
			int currentIndex = arr.get(0).getIndex();
			for(int i = 0; i < arr.size(); i++){
				if(arr.get(i).getCurrentAngle()>currentAngle&&arr.get(i).getCurrentAngle()<350){
					currentAngle = arr.get(i).getCurrentAngle();
					currentIndex = arr.get(i).getIndex();
				}
			};
			currentAngle = 360 - currentAngle;
			startScrollingIntoSlots(currentAngle);
    		setSelectedPositionInt(currentIndex);
    		selectedPosition = currentIndex;
    	}else{
    		float currentAngle = 360;
			int currentIndex = arr.get(0).getIndex();
			for(int i = 0; i < arr.size(); i++){
				if(arr.get(i).getCurrentAngle()<currentAngle&&arr.get(i).getCurrentAngle()>10){
					currentAngle = arr.get(i).getCurrentAngle();
					currentIndex = arr.get(i).getIndex();
				}
			};
			startScrollingIntoSlots(-currentAngle);
    		setSelectedPositionInt(currentIndex);
    		selectedPosition = currentIndex;
    	}        
    }
    
    private void startScrollingIntoSlots(float deltaAngle){
       	mFlingRunnable.startUsingDistance(deltaAngle);
    }   
    
    private void setUpChild(CarouselItem child, int index, float angleOffset) {
        addViewInLayout(child, -1, generateDefaultLayoutParams());
        child.setSelected(index == mSelectedPosition);
        
        int h;
        int w;
        int d;
        
        if(mInLayout){
            w = child.getMeasuredWidth();
            h = child.getMeasuredHeight();
            d = getMeasuredWidth();	        
        }else{
            w = child.getMeasuredWidth();
            h = child.getMeasuredHeight();
            d = getWidth();        	
        }
        
        child.setCurrentAngle(angleOffset);
        child.measure(w, h);        
        int childLeft;
        int childTop = calculateTop(child, true);        
        childLeft = 0;
        child.layout(childLeft, childTop, w, h);        
        Calculate3DPosition(child, d, angleOffset);        
    } 

    void trackMotionScroll(float deltaAngle) {                
        for(int i = 0; i < getAdapter().getCount(); i++){        	
        	CarouselItem child = (CarouselItem)getAdapter().getView(i, null, null);        	
        	float angle = child.getCurrentAngle();
        	angle += deltaAngle;        	
        	while(angle > 360.0f)
        		angle -= 360.0f;
        	
        	while(angle < 0.0f)
        		angle += 360.0f;
        	
        	child.setCurrentAngle(angle);
            Calculate3DPosition(child, getWidth(), angle);            
        }
        mRecycler.clear();           
        invalidate();
    }	

	public void setScrollLeft() {
		scrollingLeft = false;		
	}

	public void setScrollRight() {
		scrollingLeft = true;		
	}    
	
	public Boolean touchPositionIsCorrect(){
		if(mDownKeyXPosition>mWindowWidth/2-200&&mDownKeyXPosition<mWindowWidth/2+200){
			return true;
		}else{
			return false;
		}
	}	
	   
    private class FlingRotateRunnable implements Runnable {
		private Rotator mRotator;
        private float mLastFlingAngle;
        
        public FlingRotateRunnable(){
        	mRotator = new Rotator(getContext());
        }
        
        private void startCommon() {
            removeCallbacks(this);
        }
        
        public void startUsingVelocity(float initialVelocity) {
            if (initialVelocity == 0) return;            
            startCommon();                        
            mLastFlingAngle = 0.0f;            
           	mRotator.fling(initialVelocity);                        
            post(this);
        }                
        
        public void startUsingDistance(float deltaAngle) {
            if (deltaAngle == 0) return;            
            startCommon();            
            mLastFlingAngle = 0;
            synchronized(this){
            	mRotator.startRotate(0.0f, -deltaAngle, mAnimationDuration);
            }
            post(this);
        }              
        
        private void endFling(boolean scrollIntoSlots) {
        	synchronized(this){
        		mRotator.forceFinished(true);
        		if(moving){
        			moving=false;
        			scrollIntoSlots();
        		}
        	}
        }
                		
		public void run() {	            
            mShouldStopFling = false;            
            final Rotator rotator;
            final float angle;
            boolean more;
            synchronized(this){
	            rotator = mRotator;
	            more = rotator.computeAngleOffset();
	            angle = rotator.getCurrAngle();	            
            }
            float delta = mLastFlingAngle - angle;  
            trackMotionScroll(delta);            
            if (more && !mShouldStopFling) {
                mLastFlingAngle = angle;
                post(this);
            } else {
                mLastFlingAngle = 0.0f;
                endFling(true);
            }             
		}		
	}
	
	private class ImageAdapter extends BaseAdapter {
		private Context mContext;
		private CarouselItem[] mImages;	
		private BischofshofApplikation app;
		
		public ImageAdapter(Context c) {
			mContext = c;
			app = (BischofshofApplikation) mContext.getApplicationContext();
		}	
		
		public void SetImages(){						
			mImages = new CarouselItem[app.getWeltenburgProdukte().size()];			
			
			for(int i = 0; i< app.getWeltenburgProdukte().size(); i++){ 
				Bitmap originalImage = app.getWeltenburgProdukte().get(i).getProduktBitmap();									
				CarouselItem item = new CarouselItem(mContext);
				item.setIndex(i);
				item.setImageBitmap(originalImage);
				item.setText(app.getWeltenburgProdukte().get(i).getProduktDeText());				
				item.setHeader(app.getWeltenburgProdukte().get(i).getHeader());
				mImages[i] = item;						
			}		
		}

		public int getCount() {
			if(mImages == null)
				return 0;
			else
				return mImages.length;
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			return mImages[position];
		}
	}
}
