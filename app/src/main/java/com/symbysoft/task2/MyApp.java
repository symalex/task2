package com.symbysoft.task2;

import java.util.ArrayList;

import android.app.Application;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Log;

public class MyApp extends Application
{
	private static final String TAG = MyApp.class.getSimpleName();
	private static MyApp mApp;
    private static ResourceDataProvider mData;

    private static Boolean mIsWideScreen;
	private static Boolean mIsTabletScreen;
	private static Boolean mTwoFragment;
	public static Boolean IsWideScreen(){return mIsWideScreen; }
	public static Boolean IsTabletScreen(){return mIsTabletScreen; }

	public static Boolean IsTwoFragment(){return mTwoFragment; }
	public static void setTwoFragment(Boolean value){mTwoFragment = value; }

	public static ResourceDataProvider getData(){return mData; }

	public static void UpdateScreenInfo() {
		if( mApp != null )
		{
			mIsWideScreen = mApp.getResources().getBoolean(R.bool.is_wide_screen);
			mIsTabletScreen = mApp.getResources().getBoolean(R.bool.is_tablet_screen);
		}
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
		mApp = this;
		Log.d(TAG, this + ": onCreate()");

		UpdateScreenInfo();

		mData =new ResourceDataProvider(this);
	}
}
