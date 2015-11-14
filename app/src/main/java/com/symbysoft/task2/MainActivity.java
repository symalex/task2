package com.symbysoft.task2;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.bumptech.glide.Glide;

public class MainActivity extends MyBaseActivity implements AdapterView.OnItemClickListener
{
	private static final String TAG = MainActivity.class.getSimpleName();

	protected MainFragment list;
	protected DetailsFragment details;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		UpdateScreenInfo();

		// create list fragment
		Fragment fr = getFragmentManager().findFragmentByTag(MainFragment.FTAG);
		if( fr == null )
		{
			Log.d(TAG, this + ": Existing fragment not found. ");
			list = (MainFragment)MainFragment.newInstance(this, R.id.fr_list_container);
		}
		else
		{
			Log.d(TAG, this + ": Existing fragment found.");
			list = (MainFragment)fr;
		}
		Log.d(TAG, "W: " + String.valueOf(IsWideScreen()) + " T:" + String.valueOf(IsTabletScreen()) + " Q:" + getResources().getString(R.string.qualificator_str));

		setTwoFragment(IsWideScreen() && findViewById(R.id.fr_details_container)!=null);
		if( IsTwoFragment() )
		{
			fr = getFragmentManager().findFragmentByTag(DetailsFragment.FTAG);
			if( fr == null )
			{
				Log.d(TAG, this + ": Existing fragment not found. ");
				details = (DetailsFragment)DetailsFragment.newInstance(this,R.id.fr_details_container);
			}
			else
			{
				Log.d(TAG, this + ": Existing fragment found.");
				details = (DetailsFragment)fr;
			}
		}
	}

	@Override
	protected void onStart()
	{
		super.onStart();

		list.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		MyApp.getData().List().setSelectedIndex(position);
		Log.d(TAG, this + ": onItemSelected = " + String.valueOf(position));
		if( ! IsTwoFragment() )
		{
			Intent intent = new Intent(this, DetailsActivity.class);
			startActivity(intent);
		}
		else
		{
			if( details != null ) details.Update();
		}
	}
}
