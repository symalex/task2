package com.symbysoft.task2;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MainListViewAdapter extends BaseAdapter
{
	private Context ctx;
	private LayoutInflater inflater;
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
		this.ctx = ctx;
		this.inflater = LayoutInflater.from(ctx);
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
		ImageView image;

		PlanetDetails item = getItem(position);
		if( convertView == null ){
			convertView = inflater.inflate(R.layout.main_list_item, parent, false);
			h = new Holder(convertView);
			h.title.setText(item.getTitle());
			h.summary.setText(item.getDescriptionShort());
			image = h.image;
			convertView.setTag(h);
		} else {
			h = (Holder)convertView.getTag();
			h.title.setText(item.getTitle());
			h.summary.setText(item.getDescriptionShort());
			image = h.image;
		}

		Glide.with(ctx)
				.load(item.getImgSmallUrl())
				//.load(R.drawable.test)
				//.load(Uri.parse("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg"))
				.into(image);

		return convertView;
	}
}
