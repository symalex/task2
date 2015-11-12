package com.symbysoft.task2;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MainFragment extends Fragment
{
	public static final String FTAG = "fragment_list";

	protected Activity mActivity;

	// Debug TextView
	protected TextView mDebugText;
	protected String mDebugTextValue;

	// Debug ImageView
	protected ImageView mImageView;

	public static Fragment CreateInstance(Activity activity, int id)
	{
		MainFragment fragment = new MainFragment();
		fragment.mActivity = activity;

		FragmentManager fm = activity.getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.add(id, fragment, FTAG);
		ft.commit();

		return fragment;
	}

	public void setDebugText(String value)
	{
		mDebugTextValue = value;
		if( mDebugText != null )
			mDebugText.setText(value);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fr_layout_main, container, false);
		mDebugText = (TextView)view.findViewById(R.id.txt_layout_main);
		mImageView = (ImageView)view.findViewById(R.id.main_img_view);
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		setDebugText(mDebugTextValue);
		Glide.with(this)
				.load("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg")
				//.load(R.drawable.test)
				//.load(Uri.parse("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg"))
				.into(mImageView);
	}
}
