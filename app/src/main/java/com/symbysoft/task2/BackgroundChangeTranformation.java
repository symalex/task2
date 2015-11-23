package com.symbysoft.task2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;

public class BackgroundChangeTranformation implements Transformation<Bitmap>
{
	private BitmapPool mBitmapPool;

	private int mColor;

	public BackgroundChangeTranformation(Context context, int color)
	{
		this(Glide.get(context).getBitmapPool(), color);
	}

	public BackgroundChangeTranformation(BitmapPool pool, int color)
	{
		mBitmapPool = pool;
		mColor = color;
	}

	@Override
	public Resource<Bitmap> transform(Resource<Bitmap> resource, int outWidth, int outHeight)
	{
		Bitmap source = resource.get();

		int width = source.getWidth();
		int height = source.getHeight();

		Bitmap.Config config = source.getConfig() != null ? source.getConfig() : Bitmap.Config.ARGB_8888;
		Bitmap bitmap = mBitmapPool.get(width, height, config);
		if (bitmap == null)
		{
			bitmap = Bitmap.createBitmap(width, height, config);
		}

		Canvas canvas = new Canvas(bitmap);
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColorFilter(new PorterDuffColorFilter(mColor, PorterDuff.Mode.LIGHTEN));
		//canvas.drawRect(0,0, width,height, paint);
		canvas.drawBitmap(source, 0, 0, paint);

		return BitmapResource.obtain(bitmap, mBitmapPool);
	}

	@Override
	public String getId()
	{
		return "BackgroundChangeTranformation(color=" + mColor + ")";
	}
}
