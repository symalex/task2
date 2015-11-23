package com.symbysoft.task2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MyBaseActivity extends FragmentActivity
{
	private Boolean mIsWideScreen;
	private Boolean mIsTabletScreen;
	private Boolean mTwoFragment;

	public Boolean isWideScreen()
	{
		return mIsWideScreen;
	}

	public Boolean isSmallScreen()
	{
		return !isWideScreen();
	}

	public Boolean isTabletScreen()
	{
		return mIsTabletScreen;
	}

	public Boolean isTwoFragment()
	{
		return mTwoFragment;
	}

	protected void setTwoFragment(Boolean value)
	{
		mTwoFragment = value;
	}

	public void updateScreenInfo()
	{
		mIsWideScreen = getResources().getBoolean(R.bool.is_wide_screen);
		mIsTabletScreen = getResources().getBoolean(R.bool.is_tablet_screen);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		updateScreenInfo();
	}
}
