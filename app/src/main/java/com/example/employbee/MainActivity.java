package com.example.employbee;
import android.content.Intent;
import android.view.View;
import android.widget.TextClock;
import java.time.ZonedDateTime;
import java.time.LocalTime;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private boolean loggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(!loggedInQ()) {
            Toast toast = Toast.makeText(getApplicationContext(),"You SUCK",Toast.LENGTH_LONG);
            toast.show();
        };


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

        //TODO: time.setText(t);
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

    public void startLogin(View v) {

         if(!loggedInQ()) {
             Intent intent = new Intent(this, login_attempt.class);
             startActivity(intent);

         }

         else if (loggedInQ()) {
                 Toast toast = Toast.makeText(getApplicationContext(),"You Have Logged Out",Toast.LENGTH_LONG);
                 toast.show();
                 logOut();
             }
         }

    public void logIn() {
        loggedIn = true;
    }

    public void logOut() {
        loggedIn = false;
    }

    public boolean loggedInQ() {
        return loggedIn;
    }


}