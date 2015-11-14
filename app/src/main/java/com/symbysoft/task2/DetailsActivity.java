package com.symbysoft.task2;

import android.app.Activity;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class DetailsActivity extends Activity implements View.OnTouchListener
{
	private static final String TAG = DetailsActivity.class.getSimpleName();

	protected DetailsFragment details;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_details);
		MyApp.UpdateScreenInfo();

		// create list fragment
		Fragment fr = getFragmentManager().findFragmentByTag(MainFragment.FTAG);
		if( fr == null )
		{
			Log.d(TAG, this + ": Existing fragment not found. ");
			details = (DetailsFragment)DetailsFragment.newInstance(this, R.id.fr_details_container);
		}
		else
		{
			Log.d(TAG, this + ": Existing fragment found.");
			details = (DetailsFragment)fr;
		}
		Log.d(TAG, "W: " + String.valueOf(MyApp.IsWideScreen()) + " T:" + String.valueOf(MyApp.IsTabletScreen()) + " Q:" + getResources().getString(R.string.qualificator_str));

	}

	@Override
	protected void onStart()
	{
		super.onStart();

		RelativeLayout layout = (RelativeLayout)findViewById(R.id.fr_layout_details_id);
		layout.setOnTouchListener(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event)
	{
		Log.d(TAG, this + ": Touch screen");
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		return true;
	}
}
