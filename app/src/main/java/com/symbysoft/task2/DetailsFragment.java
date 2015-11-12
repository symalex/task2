package com.symbysoft.task2;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class DetailsFragment extends Fragment
{
	private static final String TAG = MainActivity.class.getSimpleName();
	public static final String FTAG = "fragment_details";

	protected Activity mActivity;

	public static Fragment CreateInstance(Activity activity, int id)
	{
		DetailsFragment fragment = new DetailsFragment();
		fragment.mActivity = activity;

		FragmentManager fm = activity.getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.add(id, fragment , FTAG);
		ft.commit();

		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fr_layout_details, container, false);
	}
}
