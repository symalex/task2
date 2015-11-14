package com.symbysoft.task2;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailsFragment extends Fragment
{
	private static final String TAG = MainActivity.class.getSimpleName();
	public static final String FTAG = "fragment_details";

	LinearLayout mMainLayout;
	ImageView mImgTitle;
	TextView mTitle;
	TextView mSummary;

	private class ViewLink
	{
		TextView text;
		ImageView image;
		ViewLink(TextView txt, ImageView img)
		{
			this.text = txt;
			this.image = img;
		}
	}

	ArrayList<ViewLink> mItems;

	public static Fragment newInstance(Activity activity, int id)
	{
		DetailsFragment fragment = new DetailsFragment();

		FragmentManager fm = activity.getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.add(id, fragment, FTAG);
		ft.commit();

		return fragment;
	}

	public void Update()
	{
		PlanetDetails details = ResourceDataProvider.List().getSelectedItem();
		mTitle.setText(details.getTitle());
		mSummary.setText(details.getDescriptionLong());

		Glide.with(this)
				.load(details.getImgSmallUrl())
						//.load(R.drawable.test)
						//.load(Uri.parse("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg"))
				.into(mImgTitle);

		for(int i=0; i<details.getInfo().size(); i++)
		{
			ImageAndText item = details.getInfo().get(i);
			ViewLink v = mItems.get(i);
			v.text.setText(item.getText());
			v.text.setTextColor(getResources().getColor(R.color.deafult_text_color));
			if( v.image != null )
			{
				Glide.with(this)
						.load(item.getImgUrl())
						.into(v.image);
			}
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fr_layout_details, container, false);

		mMainLayout = (LinearLayout)view;
		mImgTitle = (ImageView)view.findViewById(R.id.main_list_item_big_image);
		mTitle = (TextView)view.findViewById(R.id.main_list_item_big_title);
		mSummary = (TextView)view.findViewById(R.id.main_list_item_big_summary);

		mItems = new ArrayList<ViewLink>();

		// dynamicaly create elements
		PlanetDetails details = ResourceDataProvider.List().getSelectedItem();
		for(int i=0; i<details.getInfo().size(); i++)
		{
			ImageAndText item = details.getInfo().get(i);
			Log.d(TAG, this + ": item (img,text) ( " + item.getImgUrl() + ", " + item.getText() + ") ");

			TextView text = new TextView(getActivity());
			text.setGravity(Gravity.CENTER_HORIZONTAL);
			text.setPadding(4,4,4,4);
			mMainLayout.addView(text);

			ImageView image = new ImageView(getActivity());
			mMainLayout.addView(image);

			mItems.add(new ViewLink(text,image));
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
