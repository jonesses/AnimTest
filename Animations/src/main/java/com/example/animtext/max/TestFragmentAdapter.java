package com.example.animtext.max;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.animtest.raphael.Geschichte;

import java.util.ArrayList;


public class TestFragmentAdapter extends FragmentStatePagerAdapter {

	private KKViewPager mPager;
	private Context context;
	protected ArrayList<Geschichte> weltenburg_geschichte;
	private int mCount;

	@Override
	public int getItemPosition(Object object) {
		return PagerAdapter.POSITION_NONE;
	}

	public KKViewPager getmPager() {
		return mPager;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		super.destroyItem(container, position, object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, final int position) {
		Object obj = super.instantiateItem(container, position);
		mPager.setObjectForPosition(obj, position);
		return obj;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		if (object != null) {
			return ((Fragment) object).getView() == view;
		} else {
			return false;
		}
	}

	public TestFragmentAdapter(FragmentManager fm, KKViewPager mPager, Context context, ArrayList<Geschichte> weltenburg_geschichte) {
		super(fm);
		this.mPager = mPager;
		this.context = context;
		this.weltenburg_geschichte = weltenburg_geschichte;
		mCount = weltenburg_geschichte.size();
	}

	@Override
	public Fragment getItem(int position) {
		return TestFragment.newInstance(weltenburg_geschichte.get(position % weltenburg_geschichte.size()), context);
	}

	@Override
	public int getCount() {
		return mCount;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return "";
	}

	public void setCount(int count) {
		mCount = count;
		notifyDataSetChanged();
	}
}