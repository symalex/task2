package com.symbysoft.task2;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.provider.SyncStateContract;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

public class MainListViewAdapter extends BaseAdapter
{
	private Context mCtx;
	private LayoutInflater mInflater;
	private PlanetsList mList;

	private class Holder{
		RelativeLayout item;
		ImageView image;
		TextView title;
		TextView summary;
		Holder(View v){
			item = (RelativeLayout)v.findViewById(R.id.main_list_item_id);
			image = (ImageView)v.findViewById(R.id.main_list_item_image);
			title = (TextView)v.findViewById(R.id.main_list_item_title);
			summary = (TextView)v.findViewById(R.id.main_list_item_summary);
		}
	}

	public MainListViewAdapter(Context ctx, PlanetsList list) {
		mCtx = ctx;
		mInflater = LayoutInflater.from(ctx);
		setList(list);
	}

	public void setList(PlanetsList list)
	{
		mList = list;
	}
	public PlanetsList getList()
	{
		return mList;
	}

	@Override
	public int getCount()
	{
		return mList.size();
	}

	@Override
	public PlanetDetails getItem(int position)
	{
		return mList.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		Holder h;

		final ImageView image;
		PlanetDetails item = getItem(position);
		if( convertView == null ){
			convertView = mInflater.inflate(R.layout.main_list_item, parent, false);
			h = new Holder(convertView);
			image = h.image;

			/////////////////////////////////////// create backgound bitmap ///////////////////////////////////////
			BitmapDrawable bg = new BitmapDrawable(BitmapFactory.decodeResource(mCtx.getResources(), R.drawable.planet_background));
			float dp = mCtx.getResources().getDimension(R.dimen.list_item_height);
			int wp = mCtx.getResources().getDisplayMetrics().widthPixels;
			int hp = mCtx.getResources().getDisplayMetrics().heightPixels;

			int w = wp<hp?Math.round(dp *wp/hp):Math.round(dp *hp/wp);
			Bitmap scaledBmp = Bitmap.createScaledBitmap(bg.getBitmap(), w, w, false);
			BitmapDrawable scaledBmpdrawable = new BitmapDrawable(mCtx.getResources(), scaledBmp);
			scaledBmpdrawable.setGravity(Gravity.CENTER);

			if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
				image.setBackgroundDrawable(scaledBmpdrawable);
			} else {
				image.setBackground(scaledBmpdrawable);
			}
			///////////////////////////////////////////////////////////////////////////////////////////////////////

			convertView.setTag(h);
		} else
		{
			h = (Holder) convertView.getTag();
			image = h.image;
		}

		h.title.setText(item.getTitle());
		h.title.setTypeface(Typeface.DEFAULT_BOLD);
		h.title.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				mCtx.getResources().getDimension(R.dimen.list_item_title_text_size)
		);

		h.summary.setText(item.getDescriptionShort(mCtx.getResources().getInteger(R.integer.main_list_item_details_max_chars)));
		h.summary.setTypeface(null, Typeface.BOLD_ITALIC);
		h.summary.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				mCtx.getResources().getDimension(R.dimen.list_item_details_text_size)
		);

		Glide.with(mCtx)
				.load(item.getImgSmallUrl())
				.asBitmap()
				//.centerCrop()
				.into(new BitmapImageViewTarget(image)
				      {
					      @Override
					      protected void setResource(Bitmap resource)
					      {
						      RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(mCtx.getResources(), resource);
						      drawable.setColorFilter(new PorterDuffColorFilter(mCtx.getResources().getColor(R.color.main_list_backgroud_color), PorterDuff.Mode.LIGHTEN));
						      image.setImageDrawable(drawable);
					      }
				      }
				);

		return convertView;
	}
}
