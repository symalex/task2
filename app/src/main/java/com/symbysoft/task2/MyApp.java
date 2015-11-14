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
	public static MyApp myApp(){return mApp; }
	public static ResourceDataProvider getData(){return mData; }

	@Override
	public void onCreate()
	{
		super.onCreate();
		mApp = this;
		Log.d(TAG, this + ": onCreate()");

		mData = new ResourceDataProvider(this);
	}
}
