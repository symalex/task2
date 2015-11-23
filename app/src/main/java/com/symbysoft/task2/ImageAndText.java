package com.symbysoft.task2;

import android.os.Parcel;
import android.os.Parcelable;

public class ImageAndText implements Parcelable
{
	private static final long serialVersionUID = 100000000000000001L;

	private String mImgUrl;
	private String mText;

	public ImageAndText(String img_url, String text)
	{
		super();
		this.mImgUrl = img_url;
		this.mText = text;
	}

	public ImageAndText()
	{
		super();
	}

	public String getImgUrl()
	{
		return mImgUrl;
	}

	public void setImgUrl(String value)
	{
		this.mImgUrl = value;
	}

	public String getText()
	{
		return mText;
	}

	public void setText(String value)
	{
		this.mText = value;
	}

	@SuppressWarnings("unused")
	public ImageAndText(Parcel in)
	{
		this();
		readFromParcel(in);
	}

	private void readFromParcel(Parcel in)
	{
		this.mImgUrl = in.readString();
		this.mText = in.readString();
	}

	@Override
	public int describeContents()
	{
		return 0;
	}

	public final Creator<ImageAndText> CREATOR = new Creator<ImageAndText>()
	{
		public ImageAndText createFromParcel(Parcel in)
		{
			return new ImageAndText(in);
		}

		public ImageAndText[] newArray(int size)
		{
			return new ImageAndText[size];
		}
	};

	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		dest.writeString(mImgUrl);
		dest.writeString(mText);
	}
}
