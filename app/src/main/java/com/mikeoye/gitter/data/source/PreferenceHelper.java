package com.mikeoye.gitter.data.source;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.mikeoye.gitter.R;

/**
 * Created by lami on 4/26/2017.
 */

public class PreferenceHelper {

    private Context context;

    private SharedPreferences sharedPreferences;

    private PreferenceHelper(Context context) {
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public boolean isFirstTimeLaunch() {
        return sharedPreferences.getBoolean(context.getString(R.string.is_first_time_launch),
                Boolean.parseBoolean(context.getString(R.string.is_first_time_launch)));
    }

    public void setFirstTimeLaunch(boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.is_first_time_launch), value);
        editor.commit();
    }

    public static PreferenceHelper newInstance(Context context) {
        return new PreferenceHelper(context);
    }
}
