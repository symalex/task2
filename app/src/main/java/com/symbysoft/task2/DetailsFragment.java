package com.symbysoft.task2;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailsFragment extends Fragment
{
	private static final String TAG = MainActivity.class.getSimpleName();
	public static final String FTAG = "fragment_details";

	RelativeLayout mMainLayout;
	ImageView mImgTitle;
	TextView mTitle;
	TextView mSummary;

	public static Fragment newInstance(Activity activity, int id)
	{
		DetailsFragment fragment = new DetailsFragment();

		FragmentManager fm = activity.getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.add(id, fragment , FTAG);
		ft.commit();

		return fragment;
	}

	public void Update()
	{
		PlanetDetails details = MyApp.getData().List().getSelectedItem();
		mTitle.setText(details.getTitle());
		mSummary.setText(details.getDescriptionLong());

		Glide.with(this)
				.load("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg")
						//.load(R.drawable.test)
						//.load(Uri.parse("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg"))
				.into(mImgTitle);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fr_layout_details, container, false);
		mMainLayout = (RelativeLayout)view;
		mImgTitle = (ImageView)view.findViewById(R.id.main_list_item_big_image);
		mTitle = (TextView)view.findViewById(R.id.main_list_item_big_title);
		mSummary = (TextView)view.findViewById(R.id.main_list_item_big_summary);


		// dynamicaly create elements
		PlanetDetails details = MyApp.getData().List().getSelectedItem();
		for(int i=0; i<details.getInfo().size(); i++)
		{
			ImageAndText item = details.getInfo().get(i);
			//item.getText();
			//item.getImgUrl();
			Log.d(TAG, this + ": item (img,text) ( " + item.getImgUrl() + ", " + item.getText() + ") ");

			LinearLayout layout = new LinearLayout(getActivity());

			TextView text = new TextView(getActivity());
			text.setText("Test text");
			text.setGravity(Gravity.BOTTOM);
			LinearLayout.LayoutParams rg_params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.MATCH_PARENT
			);
			layout.setLayoutParams(rg_params);

			layout.addView(text);

			mMainLayout.addView(layout);

		}

		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		Update();
	}
}
