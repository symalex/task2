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
	private final String TAG = MainActivity.class.getSimpleName();

	protected MainFragment mList;
	protected DetailsFragment mDetails;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);

		// create list fragment
		Fragment fr = getFragmentManager().findFragmentByTag(MainFragment.FTAG);
		if( fr == null )
		{
			Log.d(TAG, this + ": Existing fragment not found. ");
			mList = (MainFragment)MainFragment.newInstance(this, R.id.fr_list_container);
		}
		else
		{
			Log.d(TAG, this + ": Existing fragment found.");
			mList = (MainFragment)fr;
		}
		Log.d(TAG, "W: " + String.valueOf(isWideScreen()) + " T:" + String.valueOf(isTabletScreen()) + " Q:" + getResources().getString(R.string.qualificator_str));

		setTwoFragment(isWideScreen() && findViewById(R.id.fr_details_container)!=null);
		if( isTwoFragment() )
		{
			fr = getFragmentManager().findFragmentByTag(DetailsFragment.FTAG);
			if( fr == null )
			{
				Log.d(TAG, this + ": Existing fragment not found. ");
				mDetails = (DetailsFragment)DetailsFragment.newInstance(this,R.id.fr_details_container);
			}
			else
			{
				Log.d(TAG, this + ": Existing fragment found.");
				mDetails = (DetailsFragment)fr;
			}
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		ResourceDataProvider.list().setSelectedIndex(position);
		Log.d(TAG, this + ": onItemSelected = " + String.valueOf(position));

		if( ! isTwoFragment() )
		{
			Intent intent = new Intent(this, DetailsActivity.class);
			startActivity(intent);
		}
		else
		{
			if( mDetails != null ) mDetails.updateDetailsView();
		}
	}
}
