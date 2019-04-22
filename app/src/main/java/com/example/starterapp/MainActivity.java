package com.example.starterapp;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = findViewById(R.id.starter_start_button);
        start.setOnClickListener(this);

        Button stop = findViewById(R.id.starter_end_button);
        stop.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.starter_start_button:
                startRemoteService();
                break;
            case R.id.starter_end_button:
                endRemoteService();
                break;
        }
    }

    private void endRemoteService() {
        Log.d(TAG, "endRemoteService: Service Stop clicked!");
        Intent stopServiceIntent = new Intent();
        stopServiceIntent.putExtra("request", 102);
        stopServiceIntent.setComponent(new ComponentName("com.example.serviceapp", "com.example.serviceapp.MyJobService"));
        stopService(stopServiceIntent);
    }

    private void startRemoteService() {
        Log.d(TAG, "startRemoteService: Start Button clicked!");
        Intent startServiceIntent = new Intent();
        startServiceIntent.putExtra("request", 101);
        startServiceIntent.setComponent(new ComponentName("com.example.serviceapp", "com.example.serviceapp.MyJobService"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(startServiceIntent);
        } else {
            startService(startServiceIntent);
        }
    }

}
