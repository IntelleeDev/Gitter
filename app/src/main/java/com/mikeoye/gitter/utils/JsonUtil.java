package com.mikeoye.gitter.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by lami on 4/23/2017.
 */

public class JsonUtil {

    private JsonUtil() {
        throw new AssertionError("This class should not be instantiated");
    }

    private static Gson createGsonInstance() {
        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        return builder.create();
    }

    public static <T> T getObjectFromJsonString(String json, Class<T> classOfT) {
        return createGsonInstance().fromJson(json, classOfT);
    }

}
