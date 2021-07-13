package com.shockwave.nha_cai_java.utils;

import android.content.Context;

import com.google.gson.Gson;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ShareUtils {

    public static <T> T get(Context context, String key, Class<T> type) {
        String json = PreferenceManager.getDefaultSharedPreferences(context).getString(key, "");
        return new Gson().fromJson(json, type);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (preferences.contains(key)) {
            return preferences.getBoolean(key, defValue);
        } else {
            return defValue;
        }
    }
}
