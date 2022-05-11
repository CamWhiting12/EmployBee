package com.example.employbee;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextClock;
import java.time.ZonedDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity {

    private boolean loggedIn;
    private ListView lv;
    private AppDatabase db;

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

        db = Room.databaseBuilder(getBaseContext(), AppDatabase.class, "Tasks").allowMainThreadQueries().build();
        TaskDao taskdao = db.taskDao();

        List<Task> tasks = taskdao.getAll();

        ArrayList<ArrayList<Task>> allTasks = new ArrayList<ArrayList<Task>>();
        allTasks.add(new ArrayList<Task>());
        allTasks.add(new ArrayList<Task>());
        allTasks.add(new ArrayList<Task>());

        for (int i = 0; i < tasks.size(); i++) {
            int val = tasks.get(i).shift;

            allTasks.get(val-1).add(tasks.get(i));
        }

        Log.e("Henry", tasks.toString());
        lv = (ListView) findViewById(R.id.homeTaskListView1);
        ArrayAdapter adapter = new ArrayAdapter(getBaseContext(), R.layout.taskrow1, allTasks.get(0));
        lv.setAdapter(adapter);

        lv = (ListView) findViewById(R.id.homeTaskListView2);
        adapter = new ArrayAdapter(getBaseContext(), R.layout.taskrow1, allTasks.get(1));
        lv.setAdapter(adapter);

        lv = (ListView) findViewById(R.id.homeTaskListView3);
        adapter = new ArrayAdapter(getBaseContext(), R.layout.taskrow1, allTasks.get(2));
        lv.setAdapter(adapter);
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