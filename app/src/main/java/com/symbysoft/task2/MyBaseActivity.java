package com.symbysoft.task2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MyBaseActivity extends FragmentActivity
{
    private static Boolean mIsWideScreen;
    private static Boolean mIsTabletScreen;
    private static Boolean mTwoFragment;
    public static Boolean IsWideScreen(){return mIsWideScreen; }
    public static Boolean IsTabletScreen(){return mIsTabletScreen; }

    public static Boolean IsTwoFragment(){return mTwoFragment; }
    public static void setTwoFragment(Boolean value){mTwoFragment = value; }

    public static void UpdateScreenInfo() {
        if( MyApp.myApp() != null )
        {
            mIsWideScreen = MyApp.myApp().getResources().getBoolean(R.bool.is_wide_screen);
            mIsTabletScreen = MyApp.myApp().getResources().getBoolean(R.bool.is_tablet_screen);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UpdateScreenInfo();
    }
}

