package com.symbysoft.task2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailsListViewAdapter extends BaseAdapter {
    private Context ctx;
    private LayoutInflater inflater;
    private PlanetsList mList;

    static class Holder {
        @Bind(R.id.details_list_big_item_image)
        ImageView image;
        @Bind(R.id.details_list_big_item_text)
        TextView text;

        Holder(View v) {
            ButterKnife.bind(this, v);
        }
    }

    public DetailsListViewAdapter(Context ctx, PlanetsList list) {
        this.ctx = ctx;
        this.inflater = LayoutInflater.from(ctx);
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
        return mList.getSelectedItem().getInfo().size();
    }

    @Override
    public ImageAndText getItem(int position) {
        return mList.getSelectedItem().getInfo().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder h;

        final ImageView image;
        ImageAndText item = getItem(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.details_list_big_item, parent, false);
            h = new Holder(convertView);
            image = h.image;

            convertView.setTag(h);
        } else {
            h = (Holder) convertView.getTag();
            image = h.image;
        }

        h.text.setText(item.getText());

        String url = item.getImgUrl();
        Glide.with(ctx)
                .load(url).placeholder(R.drawable.planet_background)
                .into(image);

        return convertView;
    }
}
