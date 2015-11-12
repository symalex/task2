package com.symbysoft.task2;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.bumptech.glide.Glide;

public class MainActivity extends FragmentActivity
{
	private static final String TAG = MainActivity.class.getSimpleName();

	protected MainFragment list;
	protected DetailsFragment details;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		MyApp.UpdateScreenInfo();

		// create list fragment
		Fragment fr = getFragmentManager().findFragmentByTag(MainFragment.FTAG);
		if( fr == null )
		{
			Log.d(TAG, this + ": Existing fragment not found. ");
			list = (MainFragment)MainFragment.CreateInstance(this,R.id.fr_list_container);
		}
		else
		{
			Log.d(TAG, this + ": Existing fragment found.");
			list = (MainFragment)fr;
		}
		if( list != null )
		    list.setDebugText("W: " + String.valueOf(MyApp.IsWideScreen()) +
				    " T:" + String.valueOf(MyApp.IsTabletScreen()) +
		            " Q:" + getResources().getString(R.string.qualificator_str));

		if( MyApp.IsWideScreen() && findViewById(R.id.fr_details_container)!=null )
		{
			fr = getFragmentManager().findFragmentByTag(DetailsFragment.FTAG);
			if( fr == null )
			{
				Log.d(TAG, this + ": Existing fragment not found. ");
				details = (DetailsFragment)DetailsFragment.CreateInstance(this,R.id.fr_details_container);
			}
			else
			{
				Log.d(TAG, this + ": Existing fragment found.");
				details = (DetailsFragment)fr;
			}
		}
	}
}
