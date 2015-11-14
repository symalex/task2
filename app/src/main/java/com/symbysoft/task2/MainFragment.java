package com.symbysoft.task2;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MainFragment extends Fragment implements AdapterView.OnItemClickListener
{
	private static final String TAG = MainActivity.class.getSimpleName();

	public static final String FTAG = "fragment_list";
    protected ListView mListView;
	private AdapterView.OnItemClickListener mOnItemClickListener;

	public static Fragment newInstance(Activity activity, int id)
	{
		MainFragment fragment = new MainFragment();

		FragmentManager fm = activity.getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.add(id, fragment, FTAG);
		ft.commit();

		return fragment;
	}

	public ListView getListView(){return mListView; }
    public void setOnItemClickListener(AdapterView.OnItemClickListener listener)
    {
	    mOnItemClickListener = listener;
    }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fr_layout_main, container, false);
		mListView = (ListView)view.findViewById(R.id.fr_layout_main_list);
		mListView.setOnItemClickListener(this);
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		MainListViewAdapter listViewAdapter = new MainListViewAdapter(getActivity(), MyApp.getData().List());
		mListView.setAdapter(listViewAdapter);

		/*Glide.with(this)
				.load("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg")
				//.load(R.drawable.test)
				//.load(Uri.parse("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg"))
				.into(view);*/
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		if( mOnItemClickListener != null ) mOnItemClickListener.onItemClick(parent, view, position, id);
	}
}
