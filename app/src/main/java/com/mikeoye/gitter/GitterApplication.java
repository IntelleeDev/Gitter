package com.mikeoye.gitter;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by lami on 4/21/2017.
 */

public class GitterApplication extends Application {

    @Override
    public void onCreate() {

        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Raleway-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());

    }
}
