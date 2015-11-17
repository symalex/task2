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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

public class DetailsFragment extends Fragment implements AdapterView.OnItemClickListener
{
	private final String TAG = MainActivity.class.getSimpleName();
	public static final String FTAG = "fragment_details";

	LinearLayout mMainLayout;
	RelativeLayout mBigItemLayout;
	ImageView mImgTitle;
	TextView mTitle;
	TextView mSummary;
	ListView mListView;

	Timer mTimer;
	final Handler mHandler = new Handler();
	final Runnable mRunnableUpdateBigItemTimer = new Runnable() {
		public void run() {
			updateVisibility();
		}
	};

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

	class UpdateBigItemTimerTask extends TimerTask
	{
		public void run() {
			Log.d(TAG, "UpdateBigItemTimerTask:run()");
			if( mBigItemLayout != null )
			{
				mHandler.post(mRunnableUpdateBigItemTimer);
			}
		}
	}

	public static Fragment newInstance(Activity activity, int id)
	{
		DetailsFragment fragment = new DetailsFragment();

		FragmentManager fm = activity.getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.add(id, fragment, FTAG);
		ft.commit();

		return fragment;
	}

	private boolean listIsAtTop()   {
		if( mListView.getChildCount() == 0 ) return true;
		return mListView.getChildAt(0).getTop() == 0;
	}

	private void updateVisibility()
	{
		if( mBigItemLayout != null )
		{
			boolean hide = listIsAtTop();
			Activity activity = getActivity();
			if( activity != null )
			{
				if( activity instanceof MainActivity )
					hide = hide || MyBaseActivity.isSmallScreen();
				else if( activity instanceof DetailsActivity )
					hide = hide || MyBaseActivity.isSmallScreen();
			}

			mBigItemLayout.setVisibility(hide?View.GONE:View.VISIBLE);
			mBigItemLayout.invalidate();
		}
	}

	protected void startBigItemTimer()
	{
		if( mTimer != null )
		{
			mTimer.cancel();
			mTimer.purge();
		}
		mTimer = new Timer();
		TimerTask updateBigItem = new UpdateBigItemTimerTask();
		mTimer.schedule(updateBigItem, 5000);
		Log.d(TAG, "StartBigItemTimer()");
		updateVisibility();
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
				//.centerCrop()
				.into(new BitmapImageViewTarget(mImgTitle)
				{
					@Override
					protected void setResource(Bitmap resource)
					{
						RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(getResources(), resource);
						drawable.setColorFilter(new PorterDuffColorFilter(getResources().getColor(R.color.details_list_backgroud_color), PorterDuff.Mode.LIGHTEN));
						mImgTitle.setImageDrawable(drawable);
					}
				});

		for(int i=0; i<details.getInfo().size(); i++)
		{
			ImageAndText item = details.getInfo().get(i);
			ViewLink v = mItems.get(i);
			v.text.setText(item.getText());
			v.text.setTypeface(null, Typeface.ITALIC);
			v.text.setTextColor(getResources().getColor(R.color.deafult_text_color));
			if( v.image != null )
			{
				Glide.with(this)
						.load(item.getImgUrl())
						.into(v.image);
			}
		}

		((DetailsListViewAdapter)mListView.getAdapter()).notifyDataSetChanged();

		startBigItemTimer();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fr_layout_details, container, false);

		mMainLayout = (LinearLayout)view;
		mBigItemLayout = (RelativeLayout)view.findViewById(R.id.main_list_big_item_id);
		mImgTitle = (ImageView)view.findViewById(R.id.main_list_item_big_image);
		mTitle = (TextView)view.findViewById(R.id.main_list_item_big_title);
		mSummary = (TextView)view.findViewById(R.id.main_list_item_big_summary);
		mListView = (ListView)view.findViewById(R.id.fr_layout_details_list_id);
		mListView.setOnItemClickListener(this);

		mItems = new ArrayList<ViewLink>();

		// dynamicaly create elements
		PlanetDetails details = ResourceDataProvider.list().getSelectedItem();
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

		mTimer = new Timer();

		return view;
	}

	@Override
	public void onDestroyView()
	{
		super.onDestroyView();

		mTimer.cancel();
		mTimer.purge();
		mTimer = null;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		DetailsListViewAdapter listViewAdapter = new DetailsListViewAdapter(getActivity(), ResourceDataProvider.list());
		mListView.setAdapter(listViewAdapter);

		updateDetailsView();
	}

	@Override
	public void onStop()
	{
		super.onStop();

		mTimer.cancel();
		mTimer.purge();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		updateVisibility();
		startBigItemTimer();
	}
}
