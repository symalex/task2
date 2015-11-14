package com.symbysoft.task2;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class PlanetsList extends ArrayList<PlanetDetails> implements Parcelable
{
	private static final long serialVersionUID = 400000000000000001L;

	private int mSelectedIndex;

	public PlanetsList() {
	}

	@SuppressWarnings("unused")
	public PlanetsList(Parcel in) {
		this();
		readFromParcel(in);
	}

	public int getSelectedIndex()
	{
		return mSelectedIndex;
	}
	public void setSelectedIndex(int value)
	{
		mSelectedIndex = value;
	}
	public PlanetDetails getSelectedItem()
	{
		return mSelectedIndex<size()?this.get(mSelectedIndex):null;
	}

	private void readFromParcel(Parcel in) {
		this.clear();

		// First we have to read the list size
		int size = in.readInt();

		for (int i = 0; i < size; i++)
			this.add(new PlanetDetails(in));
	}

	@Override
	public int describeContents()
	{
		return 0;
	}

	public final Parcelable.Creator<PlanetsList> CREATOR = new Parcelable.Creator<PlanetsList>() {
		public PlanetsList createFromParcel(Parcel in) {
			return new PlanetsList(in);
		}

		public PlanetsList[] newArray(int size) {
			return new PlanetsList[size];
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
