package com.jvaldiviab.lab8;

import android.content.SharedPreferences;

public class Util {

    public static String getUserEmailPreferences(SharedPreferences preferences){
        return preferences.getString("email","");
    }

    public static String getUserPasswordPreferences(SharedPreferences preferences){
        return preferences.getString("pass","");
    }
}
