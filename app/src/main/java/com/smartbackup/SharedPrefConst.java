package com.smartbackup;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SharedPrefConst {

    public static final String SharedPref = "SharedPref";
    //    public static final String Password = "Password";
//    public static final String Login = "Login";
    static SharedPrefConst instance;
    SharedPreferences sharedPreferences;
    Editor editor;
    //List<String> filename;
    static Gson mGson;

    public SharedPrefConst(Context context) {
        sharedPreferences = context.getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
    }

    public static SharedPrefConst getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPrefConst(context);
        }
        if (mGson == null)
            mGson = new Gson();
        return instance;
    }

    public void setIsLoggedIn(boolean islogin) {
        editor = sharedPreferences.edit();
        editor.putBoolean("islogin", islogin);
        editor.commit();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean("islogin", false);
    }

    public boolean isFirstLaunch() {
        return sharedPreferences.getBoolean("isFirstLaunch", true);
    }

    public void setIsFirstLaunch(boolean isFirstLaunch) {
        editor = sharedPreferences.edit();
        editor.putBoolean("isFirstLaunch", isFirstLaunch);
        editor.commit();
    }


    public void clearSharedPref() {
        editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }




    public boolean isReconnect() {
        return sharedPreferences.getBoolean("reconnect", true);
    }
    public void setReconnect(boolean reconnect) {
        editor = sharedPreferences.edit();
        editor.putBoolean("reconnect", reconnect);
        editor.commit();

    }
}

