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

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainFragment extends Fragment implements AdapterView.OnItemClickListener
{
	private final String TAG = MainActivity.class.getSimpleName();

	public static final String FTAG = "fragment_list";
	@Bind(R.id.fr_layout_main_list)
	protected ListView mListView;

	public static Fragment newInstance(Activity activity, int id)
	{
		MainFragment fragment = new MainFragment();

		FragmentManager fm = activity.getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.add(id, fragment, FTAG);
		ft.commit();

		return fragment;
	}

	public ListView getListView()
	{
		return mListView;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fr_layout_main, container, false);
		ButterKnife.bind(this, view);
		mListView.setOnItemClickListener(this);
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		MainListViewAdapter listViewAdapter = new MainListViewAdapter(getActivity(), ResourceDataProvider.list());
		mListView.setAdapter(listViewAdapter);
	}

	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		Activity activity = getActivity();
		if (activity instanceof AdapterView.OnItemClickListener)
			((AdapterView.OnItemClickListener) activity).onItemClick(parent, view, position, id);
	}
}
