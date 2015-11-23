package com.symbysoft.task2;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class ImageAndTextList extends ArrayList<ImageAndText> implements Parcelable
{
	private static final long serialVersionUID = 200000000000000001L;

	public ImageAndTextList()
	{
	}

	@SuppressWarnings("unused")
	public ImageAndTextList(Parcel in)
	{
		this();
		readFromParcel(in);
	}

	private void readFromParcel(Parcel in)
	{
		this.clear();

		// First we have to read the list size
		int size = in.readInt();

		for (int i = 0; i < size; i++)
			this.add(new ImageAndText(in));
	}

	@Override
	public int describeContents()
	{
		return 0;
	}

	public final Creator<ImageAndTextList> CREATOR = new Creator<ImageAndTextList>()
	{
		public ImageAndTextList createFromParcel(Parcel in)
		{
			return new ImageAndTextList(in);
		}

		public ImageAndTextList[] newArray(int size)
		{
			return new ImageAndTextList[size];
		}
	};

	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		int size = this.size();

		// We have to write the list size, we need him recreating the list
		dest.writeInt(size);

		for (int i = 0; i < size; i++)
			this.get(i).writeToParcel(dest, flags);
	}
}
