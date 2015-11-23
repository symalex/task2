package com.symbysoft.task2;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailsFragment extends Fragment implements AbsListView.OnScrollListener
{
	private final String TAG = MainActivity.class.getSimpleName();
	public static final String FTAG = "fragment_details";

	LinearLayout mMainLayout;
	@Bind(R.id.main_list_big_item_id)
	RelativeLayout mBigItemLayout;
	@Bind(R.id.main_list_item_big_image)
	ImageView mImgTitle;
	@Bind(R.id.main_list_item_big_title)
	TextView mTitle;
	@Bind(R.id.main_list_item_big_summary)
	TextView mSummary;
	@Bind(R.id.fr_layout_details_list_id)
	ListView mListView;

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

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState)
	{
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
	{
		boolean hide = firstVisibleItem == 0;
		Activity activity = getActivity();
		if (activity != null)
		{
			hide = hide || ((MyBaseActivity) activity).isSmallScreen();
		}
		mBigItemLayout.setVisibility(hide ? View.GONE : View.VISIBLE);
		mBigItemLayout.invalidate();
	}

	public void updateDetailsView()
	{
		PlanetDetails details = ResourceDataProvider.list().getSelectedItem();

		mTitle.setText(details.getTitle());
		mTitle.setTypeface(Typeface.DEFAULT_BOLD);
		mTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				getResources().getDimension(R.dimen.list_item_title_text_size)
		);

		mSummary.setText(details.getDescriptionLong());
		mSummary.setTypeface(null, Typeface.BOLD_ITALIC);
		mSummary.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				getResources().getDimension(R.dimen.list_item_details_text_size)
		);

		Glide.with(this)
				.load(details.getImgSmallUrl())
				.asBitmap()
				.transform(new BackgroundChangeTranformation(getActivity(), getActivity().getResources().getColor(R.color.details_list_backgroud_color)))
				.placeholder(R.drawable.planet_background)
				.into(mImgTitle);

		for (int i = 0; i < details.getInfo().size(); i++)
		{
			ImageAndText item = details.getInfo().get(i);
			ViewLink v = mItems.get(i);
			v.text.setText(item.getText());
			v.text.setTypeface(null, Typeface.ITALIC);
			v.text.setTextColor(getResources().getColor(R.color.deafult_text_color));
			if (v.image != null)
			{
				Glide.with(this)
						.load(item.getImgUrl())
						.asBitmap()
						.placeholder(R.drawable.planet_background)
						.transform(new BackgroundChangeTranformation(getActivity(), getActivity().getResources().getColor(R.color.details_list_backgroud_color)))
						.into(v.image);
			}
		}

		((DetailsListViewAdapter) mListView.getAdapter()).notifyDataSetChanged();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fr_layout_details, container, false);

		mMainLayout = (LinearLayout) view;
		ButterKnife.bind(this, view);
		mListView.setOnScrollListener(this);

		mItems = new ArrayList<>();

		// dynamicaly create elements
		PlanetDetails details = ResourceDataProvider.list().getSelectedItem();
		for (int i = 0; i < details.getInfo().size(); i++)
		{
			ImageAndText item = details.getInfo().get(i);
			Log.d(TAG, this + ": item (img,text) ( " + item.getImgUrl() + ", " + item.getText() + ") ");

			TextView text = new TextView(getActivity());
			text.setGravity(Gravity.CENTER_HORIZONTAL);
			text.setPadding(4, 4, 4, 4);
			mMainLayout.addView(text);

			ImageView image = new ImageView(getActivity());
			image.setScaleType(ImageView.ScaleType.FIT_CENTER);
			image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
			mMainLayout.addView(image);

			mItems.add(new ViewLink(text, image));
		}

		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		DetailsListViewAdapter listViewAdapter = new DetailsListViewAdapter(getActivity(), ResourceDataProvider.list());
		mListView.setAdapter(listViewAdapter);

		updateDetailsView();
	}

}
