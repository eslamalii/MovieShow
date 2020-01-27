package com.example.Movie.Util;

import android.app.Activity;
import android.content.SharedPreferences;

public class Prefs {
    SharedPreferences sharedPreferences;

    public Prefs(Activity activity) {
        sharedPreferences = activity.getPreferences(activity.MODE_PRIVATE);
    }

    public void setPrefs(String search){
        sharedPreferences.edit().putString("search", search).apply();
    }

    public String getPrefs(){
        return sharedPreferences.getString("search", "Batman");
    }
}

