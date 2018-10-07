package com.mikastamm.chesstime.Game;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PersistenceManager {

    private static final String firebaseIdKey ="firebase_id";
    public static void storeFirebaseId(String id, Context context)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(firebaseIdKey, id);
    }

    public static String getFirebaseId(Context context)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(firebaseIdKey, null);
    }
}
