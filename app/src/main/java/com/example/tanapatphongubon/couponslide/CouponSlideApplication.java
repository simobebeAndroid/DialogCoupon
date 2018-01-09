package com.example.tanapatphongubon.couponslide;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by tanapatphongubon on 9/1/2018 AD.
 */

public class CouponSlideApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
