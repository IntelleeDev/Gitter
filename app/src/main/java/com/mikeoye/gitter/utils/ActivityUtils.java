package com.mikeoye.gitter.utils;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


/**
 * Created by lami on 2/24/2017.
 */

public class ActivityUtils {

    private ActivityUtils() throws Exception {
        throw new Exception("Cannot instantiate this class");
    }

    public static void addFragmentToActivity (@NonNull FragmentManager fragmentManager,
                                              @NonNull Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    public static void setUpToolbar(AppCompatActivity activity, Toolbar toolbar) {
        activity.setSupportActionBar(toolbar);
    }

    public static void setUpToolBarWithBackEnabled(AppCompatActivity activity, Toolbar toolbar) {
        setUpToolbar(activity, toolbar);
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static Snackbar makeSnackBar(ViewGroup rootLayout, String message) {
        Snackbar snackBar = Snackbar.make(rootLayout, message, Snackbar.LENGTH_LONG);
        return snackBar;
    }

    public static void changeStatusBarColor(Activity context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = context.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public static void switchToActivityAndDestroyCurrent(Activity currentActivity, Class targetActivity) {
        switchToActivity(currentActivity, targetActivity);
        destroyActivity(currentActivity);
    }

    public static void switchToActivity(Activity currentActivity, Class targetActivity) {
        Intent intent = new Intent(currentActivity, targetActivity);
        currentActivity.startActivity(intent);
    }

    public static void switchToActivityWithBundle(Activity currentActivity, Class targetActivity,
                                                  Bundle bundle, String bundleName) {
        Intent intent = new Intent(currentActivity, targetActivity);
        intent.putExtra(bundleName, bundle);
        currentActivity.startActivity(intent);
    }

    public static void destroyActivity(Activity currentActivity) {
        currentActivity.finish();
    }

    public static void inflateMenu(Toolbar toolbar, @MenuRes int menuResource) {
        toolbar.getMenu().clear();
        toolbar.inflateMenu(menuResource);
    }

}
