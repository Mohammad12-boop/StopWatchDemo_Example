package com.example.stopwatchdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private int seconds= 0;
    private boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        checkSavedInstance(savedInstanceState);
        runTimer();
    }

    private void checkSavedInstance(Bundle savedInstanceState) {

        if (savedInstanceState!=null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putInt("seconds", seconds);
        outState.putBoolean("running", running);
    }

    private void runTimer() {

        final TextView txtTime= findViewById(R.id.txtTime);
        final Handler handler= new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                if (running){
                    seconds++;
                }
                int hours= seconds/3600;
                int minutes= (seconds%3600)/60;
                int secs= seconds%60;
                String time = hours +" : "+ minutes +" : "+ secs;
                txtTime.setText(time);

                handler.postDelayed(this, 1000);
            }
        });
    }

    public void btStartOnClick(View view) {
        running = true;
    }

    public void btStopOnClick(View view) {
        running = false;
    }

    public void btResetOnClick(View view) {
        running = false;
        seconds = 0;
    }
}