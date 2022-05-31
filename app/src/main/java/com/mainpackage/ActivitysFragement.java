package com.mainpackage;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.userinformation.UserActivities;
import com.example.userinformation.UserActivitiesArray;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import coil.ImageLoader;
import coil.request.ImageRequest;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

import static android.content.Context.LOCATION_SERVICE;

public class ActivitysFragement extends Fragment implements View.OnClickListener {
    ApplicationMy app;
    TextView temp;
    String lon, lat;
    TextView sensX, sensY, sensZ, timer_tv;
    Sensor sensor;
    SensorManager sensorManager;
    float x = 0, y = 0, z = 0;
    int tempMax = 0;

    boolean timerStarted = false;
    Timer timer;
    TimerTask timerTask;
    double time;
    Button start;

    private LocationManager locationManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragement_activitys, container, false);

        View v = inflater.inflate(R.layout.fragement_activitys, container, false);

        start = (Button) v.findViewById(R.id.startBtn);
        start.setOnClickListener(this);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        app = (ApplicationMy) getActivity().getApplication();

        temp = (TextView) getView().findViewById(R.id.tempLatLon);

        sensX = (TextView) getView().findViewById(R.id.sensAccX);
        sensY = (TextView) getView().findViewById(R.id.sensAccY);
        sensZ = (TextView) getView().findViewById(R.id.sensAccZ);

        timer_tv = (TextView) getView().findViewById(R.id.timerTV);

        timer = new Timer();

        sensorManager = (SensorManager) app.getSystemService(app.getBaseContext().SENSOR_SERVICE);

        if(sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).isEmpty()){
            Toast.makeText(app.getBaseContext(), "Accelerometer Unavailable", Toast.LENGTH_LONG).show();
        }else{
            sensor = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
        }

        sensorManager.registerListener(accelerationListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        //Initialise locationManager
        locationManager = (LocationManager) app.getSystemService(LOCATION_SERVICE);

        //Check permissions to use location
        if(ContextCompat.checkSelfPermission(app.getBaseContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(app.getBaseContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 44);
        }

        //Create LocationListener
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 1, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                lon = String.valueOf(location.getLongitude());
                lat = String.valueOf(location.getLatitude());

                if (timerStarted) {
                    temp.setText("Latitude: " + lat + "\n Longitude: " + lon);
                }else{
                    temp.setText("Latitude: STOPPED" + "\n Longitude: STOPPED");
                }

            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {

            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }
        });

    }

    private SensorEventListener accelerationListener = new SensorEventListener(){
        @Override
        public void onAccuracyChanged(Sensor sensor, int acc){
        }
        @Override
        public void onSensorChanged(SensorEvent event){
            x = event.values[0]; //X probably the best candidate to keep an eye on during road evaluation
            if(x > tempMax){
                tempMax = (int) x;
            }
            y = event.values[1];
            z = event.values[2];

            if(timerStarted){
                sensX.setText(String.valueOf(x));
                sensY.setText(String.valueOf(y));
                sensZ.setText(String.valueOf(z));
            }else{
                sensX.setText("STOPPED");
                sensY.setText("STOPPED");
                sensZ.setText("STOPPED");
            }

        }
    };

    private void startTimer(){
        timerTask = new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time++;
                        timer_tv.setText(getTimerText());
                    }
                });
            }
        };

        timer.scheduleAtFixedRate(timerTask, 0, 1000);

    }

    private String getTimerText() {
        int rounded = (int) Math.round(time);
        int seconds = ((rounded % 86400) % 3600) % 60;
        int minutes = ((rounded % 86400) % 3600) / 60;
        int hours = ((rounded % 86400) / 3600) ;
        return formatTime(seconds,minutes,hours);

    }
    private String formatTime(int seconds, int minutes, int hours){
        return String.format("%02d", hours) + " : " + String.format("%02d", minutes) + " : " + String.format("%02d", seconds);
    }

    /* //Replaced with OnClickListener
    public void startTracking(View view) {
        if(timerStarted == false) {
            timerStarted = true;
            start.setText("Končaj aktivnost");

            startTimer();
        }else{
            timerStarted = false;
            start.setText("Začni aktivnost");
            timerTask.cancel();

        }
    }*/

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.startBtn:
                if(timerStarted == false) {
                    timerStarted = true;
                    start.setText("Končaj sledenje");

                    app.startLat = lat;
                    app.startLon = lon;

                    startTimer();
                }else{
                    timerStarted = false;
                    start.setText("Začni sledenje");

                    app.endLat = lat;
                    app.endLon = lon;

                    app.roadQuality = tempMax;

                    timerTask.cancel();
                }
                break;
        }
    }
}
