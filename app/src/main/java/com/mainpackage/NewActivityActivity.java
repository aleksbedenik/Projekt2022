package com.mainpackage;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class NewActivityActivity extends AppCompatActivity {
    Button buttonStart;
    Button buttonEnd;
    TextView timerText;
    boolean timerStarted = false;
    Timer timer;
    TimerTask timerTask;

    TextView sensX, sensY, sensZ;
    SensorManager sensorManager;
    Sensor sensor;
    Sensor gyro_sensor;
    float x = 0, y = 0, z = 0;
    float gyro_x = 0, gyro_y = 0, gyro_z = 0;

    double time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_activity);
        timerText = (TextView) findViewById(R.id.timerText);
        buttonStart = (Button) findViewById(R.id.startButton);
        sensX = (TextView) findViewById(R.id.sensAccelX);
        sensY = (TextView) findViewById(R.id.sensAccelY);
        sensZ = (TextView) findViewById(R.id.sensAccelZ);
       // buttonEnd = (Button) findViewById(R.id.endButton);

        timer = new Timer();

        sensorManager = (SensorManager) getSystemService(getBaseContext().SENSOR_SERVICE);

        if(sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).isEmpty()){
            Toast.makeText(getBaseContext(), "Accelerometer Unavailable", Toast.LENGTH_LONG).show();
        }else{
            sensor = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
        }


        if(sensorManager.getSensorList(Sensor.TYPE_GYROSCOPE).isEmpty()){
            Toast.makeText(getBaseContext(), "GYRO Unavailable", Toast.LENGTH_LONG).show();
        }else{
            gyro_sensor = sensorManager.getSensorList(Sensor.TYPE_GYROSCOPE).get(0);
        }


        sensorManager.registerListener(accelerationListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(gyroListener, gyro_sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private SensorEventListener accelerationListener = new SensorEventListener(){
        @Override
        public void onAccuracyChanged(Sensor sensor, int acc){
        }
        @Override
        public void onSensorChanged(SensorEvent event){
            x = event.values[0];
            y = event.values[1];
            z = event.values[2];
            updateTextView();
        }
    };

    private SensorEventListener gyroListener = new SensorEventListener() {
      @Override
      public void onAccuracyChanged(Sensor sensor, int acc){
      }
      @Override
      public void onSensorChanged(SensorEvent event){
          gyro_x = event.values[0];
          gyro_y = event.values[1];
          gyro_z = event.values[2];
          updateTextView();
      }
    };

    private void updateTextView(){
        sensX.setText("Accelorometer x: " + String.valueOf(x) + "\nGyro x: " + String.valueOf(gyro_x));
        sensY.setText("Accelerometer y: " + String.valueOf(y) + "\nGyro y: " + String.valueOf(gyro_y));
        sensZ.setText("Accelerometer z: " + String.valueOf(z) + "\nGyro z: " + String.valueOf(gyro_z));
    }

    private void startTimer(){
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time++;
                        timerText.setText(getTimerText());
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

    public void startActivityButton(View view) {
        if(timerStarted == false) {
            timerStarted = true;
            buttonStart.setText("Končaj aktivnost");

            startTimer();
        }else{
            timerStarted = false;
            buttonStart.setText("Začni aktivnost");
            timerTask.cancel();

        }
    }


}