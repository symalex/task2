package com.symbysoft.task2;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Log;
import android.util.TypedValue;

public class ResourceDataProvider {
    private final String TAG = ResourceDataProvider.class.getSimpleName();

    private enum ResPlanetItemEnum {
        PLANET_ITEM_SMALL_IMG_URL_INDEX,
        PLANET_ITEM_TITLE_INDEX,
        PLANET_ITEM_DESCRIPTION_INDEX
    }

    private enum ResPlanetItemDetailsEnum
    {
        PLANET_ITEM_DETAILS_IMG_URL_INDEX,
        PLANET_ITEM_DETAILS_TEXT_INDEX
    }

    private static PlanetsList mList;
    public static PlanetsList list(){return mList; }

    ResourceDataProvider(Context ctx)
    {
        super();

        mList= new PlanetsList();
        loadApplicationData(ctx);
    }

    private void loadApplicationData(Context ctx)
    {
        int i = 0;
        for(TypedArray item: ResourceHelper.getMultiTypedArray(ctx, "panet_item_%d") ) {

            PlanetDetails details = new PlanetDetails(
                    item.getString(ResPlanetItemEnum.PLANET_ITEM_SMALL_IMG_URL_INDEX.ordinal()),
                    item.getString(ResPlanetItemEnum.PLANET_ITEM_TITLE_INDEX.ordinal()),
                    item.getString(ResPlanetItemEnum.PLANET_ITEM_DESCRIPTION_INDEX.ordinal())
            );

            details.getInfo().add(new ImageAndText(
                    item.getString(ResPlanetItemEnum.PLANET_ITEM_SMALL_IMG_URL_INDEX.ordinal()),
                    item.getString(ResPlanetItemEnum.PLANET_ITEM_DESCRIPTION_INDEX.ordinal())
            ));

            for(TypedArray info: ResourceHelper.getMultiTypedArray(ctx, String.format("panet_item_%d",i)+"_details_%d") )
            {
                details.getInfo().add(new ImageAndText(
                                info.getString(ResPlanetItemDetailsEnum.PLANET_ITEM_DETAILS_IMG_URL_INDEX.ordinal()),
                                info.getString(ResPlanetItemDetailsEnum.PLANET_ITEM_DETAILS_TEXT_INDEX.ordinal())
                ));
                info.recycle();
            }
            item.recycle();

            mList.add(details);
            i++;
        }
    }

}
