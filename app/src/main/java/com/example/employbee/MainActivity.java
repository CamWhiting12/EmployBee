package com.example.employbee;
import android.content.Intent;
import android.view.View;
import android.widget.TextClock;
import java.time.ZonedDateTime;
import java.time.LocalTime;
import android.widget.TextView;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread thread = new Thread() {

            @Override
            public void run() {
                try {
                    //should we fix this? the true thing
                    while (true) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setTime();
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        thread.start();
    }

    public void setTime() {
        TextView time = findViewById(R.id.clock);
        LocalTime now = LocalTime.now();

        String t = now.toString();
        if (t.charAt(0)=='0') {
            t = t.substring(1,5);
        }
        else
            t = t.substring(0,5);

        time.setText(t);
    }

    public void startTasks1(View v) {
        Intent intent = new Intent(this, Tasks1Activity.class);
        startActivity(intent);
    }

    public void startProtocol(View v) {
        Intent intent = new Intent(this, protocol_page.class);
        startActivity(intent);
    }

    public void startMap(View v) {
        Intent intent = new Intent(this, general_map.class);
        startActivity(intent);
    }




}