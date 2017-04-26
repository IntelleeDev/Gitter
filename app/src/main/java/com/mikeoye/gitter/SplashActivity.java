package com.mikeoye.gitter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mikeoye.gitter.data.source.PreferenceHelper;
import com.mikeoye.gitter.gitters.GittersActivity;
import com.mikeoye.gitter.utils.ActivityUtils;

public class SplashActivity extends AppCompatActivity {

    private final Activity activity = this;

    private PreferenceHelper preferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        preferenceHelper = PreferenceHelper.newInstance(this);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                checkForFirstTimeLaunch();
            }
            
        }, 2000);

    }

    private void checkForFirstTimeLaunch() {
        boolean isFirstTimeLaunch = preferenceHelper.isFirstTimeLaunch();
        if (isFirstTimeLaunch) {
            preferenceHelper.setFirstTimeLaunch(false);
            ActivityUtils.switchToActivityAndDestroyCurrent(activity, WelcomeActivity.class);
            return;
        }
        ActivityUtils.switchToActivityAndDestroyCurrent(activity, GittersActivity.class);
    }
}
