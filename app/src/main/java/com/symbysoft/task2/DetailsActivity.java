package com.symbysoft.task2;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class DetailsActivity extends MyBaseActivity
{
	private static final String TAG = DetailsActivity.class.getSimpleName();

	protected DetailsFragment details;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		Log.d(TAG, "W: " + String.valueOf(IsWideScreen()) + " T:" + String.valueOf(IsTabletScreen()) + " Q:" + getResources().getString(R.string.qualificator_str));
		if( IsWideScreen() && IsTabletScreen() )
			finish();
		else
		{
			setContentView(R.layout.layout_details);

			// create list fragment
			Fragment fr = getFragmentManager().findFragmentByTag(MainFragment.FTAG);
			if (fr == null)
			{
				Log.d(TAG, this + ": Existing fragment not found. ");
				details = (DetailsFragment)DetailsFragment.newInstance(this, R.id.fr_details_container);
			}
			else
			{
				Log.d(TAG, this + ": Existing fragment found.");
				details = (DetailsFragment)fr;
			}
		}
	}
}
