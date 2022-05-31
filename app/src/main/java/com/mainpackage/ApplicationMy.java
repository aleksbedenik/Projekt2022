package com.mainpackage;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.FileUtils;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.example.userinformation.UserActivitiesArray;
import com.example.userinformation.UserInfoArray;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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

    String startLon, startLat, endLon, endLat;
    int roadQuality;

    @Override
    public void onCreate() {
        super.onCreate();
        startLon = "default";
        startLat = "default";
        endLon = "default";
        endLat = "default";
    }

    public UserActivitiesArray getUserActivitiesArray() {
        return userActivitiesArray;
    }
    public int size() {
        return userActivitiesArray.getArrayList().size();
    }




}
