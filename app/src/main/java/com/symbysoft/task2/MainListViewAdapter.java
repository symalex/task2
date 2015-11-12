package com.symbysoft.task2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainListViewAdapter extends BaseAdapter
{
	Context ctx;
	LayoutInflater inflater;

	static class Holder{
		TextView view;
		TextView summary;
		RelativeLayout item;
		Holder(View v){
			this.item = (RelativeLayout)v.findViewById(R.id.main_list_item_id);
			this.view = (TextView)v.findViewById(R.id.main_list_item_title);
			this.summary = (TextView)v.findViewById(R.id.main_list_item_summary);
		}
	}

	public MainListViewAdapter(Context ctx) {
		this.ctx = ctx;
		this.inflater = LayoutInflater.from(ctx);
	}

	@Override
	public int getCount()
	{
		return 30;
	}

	@Override
	public Object getItem(int position)
	{
		return "item: " + String.valueOf(position);
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
		if( convertView == null ){
			convertView = inflater.inflate(R.layout.main_list_item, parent, false);
			h = new Holder(convertView);
			convertView.setTag(h);
		} else  h = (Holder)convertView.getTag(position);
		if ( h != null && h.view != null )
		{
			h.view.setText(String.valueOf(getItem(position)));
			h.summary.setText("summary: "+String.valueOf(getItem(position)));
		}
		return convertView;
	}

}
