package com.mainpackage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class NewActivityActivity extends AppCompatActivity {
    Button buttonStart;
    Button buttonEnd;
    TextView timerText;
    boolean timerStarted = false;
    Timer timer;
    TimerTask timerTask;
    double time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_activity);
        timerText = (TextView) findViewById(R.id.timerText);
        buttonStart = (Button) findViewById(R.id.startButton);
       // buttonEnd = (Button) findViewById(R.id.endButton);

        timer = new Timer();
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