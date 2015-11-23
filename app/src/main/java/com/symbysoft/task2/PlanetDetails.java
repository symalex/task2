package com.symbysoft.task2;

import android.os.Parcel;
import android.os.Parcelable;

public class PlanetDetails implements Parcelable
{
	private static final long serialVersionUID = 300000000000000001L;

	private final int max_shot_length = 50;

	private String mTitle;
	private String mImgSmallUrl;
	private String mDescriptionShort;
	private String mDescriptionLong;
	private ImageAndTextList mInfo;

	public PlanetDetails(String img_small_url, String title, String description)
	{
		super();
		mImgSmallUrl = img_small_url;
		mTitle = title;
		mDescriptionShort = description;
		mDescriptionLong = description;
		mInfo = new ImageAndTextList();
	}

	public PlanetDetails()
	{
		super();
		mInfo = new ImageAndTextList();
	}

	public String getTitle()
	{
		return mTitle;
	}

	public void setTitle(String value)
	{
		this.mTitle = value;
	}

	public String getImgSmallUrl()
	{
		return mImgSmallUrl;
	}

	public void setImgSmallUrl(String value)
	{
		this.mImgSmallUrl = value;
	}

	public String getDescriptionShort()
	{
		return shortStr(mDescriptionShort, max_shot_length);
	}

	public String getDescriptionShort(int max)
	{
		return shortStr(mDescriptionShort, max);
	}

	public void setDescriptionShort(String value)
	{
		this.mDescriptionShort = value;
	}

	public String getDescriptionLong()
	{
		return mDescriptionLong;
	}

	public void setDescriptionLong(String value)
	{
		this.mDescriptionLong = value;
	}

	public ImageAndTextList getInfo()
	{
		return mInfo;
	}

	@SuppressWarnings("unused")
	public PlanetDetails(Parcel in)
	{
		this();
		readFromParcel(in);
	}

	protected String shortStr(String value, int max)
	{
		String ret = value.substring(0, value.length() > max ? max : value.length()).trim();
		int wl = 0;
		boolean full_word = value.length() <= ret.length();
		for (int i = ret.length() - 1; i >= 0; i--)
		{
			if (ret.charAt(i) == ' ')
			{
				if (wl >= 3 && full_word)
				{
					ret = value.substring(0, i + 1 + wl);
					break;
				}
				else
				{
					wl = 0;
					full_word = true;
				}
			}
			else
			{
				wl += 1;
			}
		}
		ret = ret.trim();
		if (value.length() > ret.length())
			ret += " ...";
		return ret;
	}

	private void readFromParcel(Parcel in)
	{
		mTitle = in.readString();
		mImgSmallUrl = in.readString();
		mDescriptionShort = in.readString();
		mDescriptionLong = in.readString();
		mInfo = new ImageAndTextList(in);
	}

	@Override
	public int describeContents()
	{
		return 0;
	}

	public final Parcelable.Creator<PlanetDetails> CREATOR = new Parcelable.Creator<PlanetDetails>()
	{
		public PlanetDetails createFromParcel(Parcel in)
		{
			return new PlanetDetails(in);
		}

		public PlanetDetails[] newArray(int size)
		{
			return new PlanetDetails[size];
		}
	};

	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		dest.writeString(mTitle);
		dest.writeString(mImgSmallUrl);
		dest.writeString(mDescriptionShort);
		dest.writeString(mDescriptionLong);
		mInfo.writeToParcel(dest, flags);
	}
}
