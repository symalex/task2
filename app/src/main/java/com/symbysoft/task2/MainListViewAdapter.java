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

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainListViewAdapter extends BaseAdapter {
    private Context mCtx;
    private LayoutInflater mInflater;
    private PlanetsList mList;

    static class Holder {
        @Bind(R.id.main_list_item_id)
        RelativeLayout item;
        @Bind(R.id.main_list_item_image)
        ImageView image;
        @Bind(R.id.main_list_item_title)
        TextView title;
        @Bind(R.id.main_list_item_summary)
        TextView summary;

        Holder(View v) {
            ButterKnife.bind(this, v);
        }
    }

    public MainListViewAdapter(Context ctx, PlanetsList list) {
        mCtx = ctx;
        mInflater = LayoutInflater.from(ctx);
        setList(list);
    }

    public void setList(PlanetsList list) {
        mList = list;
    }

    public PlanetsList getList() {
        return mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public PlanetDetails getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder h;

        final ImageView image;
        PlanetDetails item = getItem(position);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.main_list_item, parent, false);
            h = new Holder(convertView);
            image = h.image;
            convertView.setTag(h);
        } else {
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
                .load(item.getImgSmallUrl()).placeholder(R.drawable.planet_background)
                .into(image);

        return convertView;
    }
}
