package com.symbysoft.task2;

import android.app.Application;
import android.app.Fragment;
import android.content.Context;
import android.util.Log;

public class MyApp extends Application
{
	private static final String TAG = MyApp.class.getSimpleName();
	private static MyApp mApp;
    private static Boolean mIsWideScreen;
	private static Boolean mIsTabletScreen;
	public static Boolean IsWideScreen(){return mIsWideScreen; }
	public static Boolean IsTabletScreen(){return mIsTabletScreen; }

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
	}
}
