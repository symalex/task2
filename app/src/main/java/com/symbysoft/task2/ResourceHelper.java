package com.symbysoft.task2;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.TypedArray;

public class ResourceHelper
{

	public static TypedArray getTypedArray(Context context, String key_format, int id)
	{
		TypedArray ta = null;
		try
		{
			Field field = R.array.class.getField(String.format(key_format, id));
			ta = context.getResources().obtainTypedArray(field.getInt(null));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return ta;
	}

	public static List<TypedArray> getMultiTypedArray(Context context, String key_format)
	{
		List<TypedArray> array = new ArrayList<>();
		TypedArray ta;
		int counter = 0;
		while ((ta = getTypedArray(context, key_format, counter)) != null)
		{
			array.add(ta);
			counter++;
		}
		return array;
	}

}
