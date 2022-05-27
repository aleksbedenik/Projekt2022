package com.mainpackage;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.FileUtils;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.userinformation.UserActivitiesArray;
import com.example.userinformation.UserInfoArray;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ApplicationMy extends Application {
    private static final String TAG = ApplicationMy.class.getSimpleName();
    public static final String UUID_KEY = "UUID";
    public final String APP_UUID = UUID.randomUUID().toString().replace("-", "");
    SharedPreferences sp;
    public int testVariable;
    private Gson gson;
    public UserActivitiesArray userActivitiesArray;
    private static final String MY_FILE = "podatki.json";
    private File file;
    public int position;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public UserActivitiesArray getUserActivitiesArray() {
        return userActivitiesArray;
    }
    public int size() {
        return userActivitiesArray.getArrayList().size();
    }




}
