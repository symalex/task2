package com.symbysoft.task2;


import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Log;

public class ResourceDataProvider {
    private static final String TAG = ResourceDataProvider.class.getSimpleName();

    private static MyApp mApp;
    private static PlanetsList mList;
    public static PlanetsList List(){return mList; }

    ResourceDataProvider(MyApp app)
    {
        super();
        mApp = app;

        mList= new PlanetsList();
        LoadApplicationData();
    }

    private PlanetDetails ReadPlanetDetailsFromResource(Resources res, int id)
    {
        PlanetDetails details = new PlanetDetails();

        TypedArray ta = res.obtainTypedArray(id);
        int n = ta.length();
        String[][] array = new String[n][];
        for (int i = 0; i < n; ++i) {
            id = ta.getResourceId(i, 0);
            Log.d(TAG, "array: (" + String.valueOf(i) + ") " + String.valueOf(id));
            if (id > 0) {
                array[i] = res.getStringArray(id);
                for(int j=0; j<array[i].length; j++)
                {
                    if( array[i][j] != null )
                    {
                        Log.d(TAG, "array: " + array[i][j]);
                        switch( j )
                        {
                            case 0:
                                details.setImgSmallUrl(array[i][j]);
                                break;
                            case 1:
                                details.setTitle(array[i][j]);
                                break;
                            case 2:
                                details.setDescriptionShort(array[i][j]);
                                details.setDescriptionLong(array[i][j]);
                                break;
                        }
                    }

                    if( array[i][j] == null )
                    {
                        TypedArray in = res.obtainTypedArray(id);
                        int nn = in.length();
                        String[][] array_in = new String[nn][];
                        for (int k = 0; k < nn; ++k)
                        {
                            id = in.getResourceId(k, 0);
                            if (id >0 )
                            {
                                Log.d(TAG, "in_array: (" + String.valueOf(k) + ") " + String.valueOf(id));
                                array[k] = res.getStringArray(id);
                                ImageAndText info = new ImageAndText();
                                for(int l=0; l<array[k].length; l++)
                                {
                                    if( array[k][l] != null )
                                    {
                                        Log.d(TAG, "in__in_array: " + array[k][l]);
                                        switch ( l )
                                        {
                                            case 0:
                                                info.setImgUrl(array[k][l]);
                                                break;
                                            case 1:
                                                info.setText(array[k][l]);
                                                break;
                                        }
                                    }
                                }
                                details.getInfo().add(info);
                            }
                        }
                    }
                }
            } else {
                // something wrong with the XML
            }
        }
        ta.recycle(); // Important!

        return details;
    }

    private void LoadApplicationData()
    {
        // read data from resource
        Resources res = mApp.getResources();

        TypedArray ta = res.obtainTypedArray(R.array.data_items);
        int len = ta.length();
        int[] ids = new int[len];
        for(int i = 0; i < len; i++)
            ids[i] = ta.getResourceId(i, 0);
        ta.recycle();

        //int[] ids = res.getIntArray(R.array.data_items);
        for(int i=0; i<ids.length; i++)
        {
            PlanetDetails details = ReadPlanetDetailsFromResource(res, ids[i]); // R.array.array0
            mList.add(details);
        }
    }

    /*private void LoadApplicationData()
	{
		for(int i=0; i<30; i++)
		{
			PlanetDetails details = new PlanetDetails(
					"",
					"Title: " + String.valueOf(i),
					"Description: " + String.valueOf(i)
			);

			// two images
			for(int j=1; j<=2; j++)
				details.getInfo().add( new ImageAndText("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg", "") );

			// three text's
			for(int j=1; j<=3; j++)
				details.getInfo().add( new ImageAndText("", "Info text: "+String.valueOf(j)) );

			mList.add(details);
		}
	}*/

}
