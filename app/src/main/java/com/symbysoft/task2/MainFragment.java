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
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MainFragment extends Fragment
{
	public static final String FTAG = "fragment_list";
    protected ListView mListView;

	public static Fragment CreateInstance(Activity activity, int id)
	{
		MainFragment fragment = new MainFragment();

		FragmentManager fm = activity.getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.add(id, fragment, FTAG);
		ft.commit();

		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fr_layout_main, container, false);
		mListView = (ListView)view.findViewById(R.id.fr_layout_main_list);
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		MainListViewAdapter listViewAdapter = new MainListViewAdapter(getActivity());
		mListView.setAdapter(listViewAdapter);

		/*Glide.with(this)
				.load("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg")
				//.load(R.drawable.test)
				//.load(Uri.parse("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg"))
				.into(view);*/
	}
}
