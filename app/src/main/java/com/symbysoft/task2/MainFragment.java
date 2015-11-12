package com.symbysoft.task2;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainFragment extends Fragment
{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fr_layout_main, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		final ImageView image = (ImageView)view.findViewById(R.id.main_img_view);
		/*Glide.with(this)
				.load("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg")
				.into(image);*/
	}
}
