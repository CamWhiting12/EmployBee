package com.example.employbee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class general_map extends MainActivity {

   String[] rooms =
   {"null", "null", "null", "null", "null", "null", "null", "null", "null"};

    public void goHome(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openShelfSample(View view) {

        Intent intent = new Intent(this, sampleShelf.class);
        startActivity(intent);
    }

    public void openRoom1(View view) {
        if (rooms[0] == "null" && super.loggedInQ()) {
            Intent intent = new Intent(this, create_room.class);
            startActivity(intent);
        }
        if (rooms[0] == "null" && !super.loggedInQ()) {
           Intent intent = new Intent(this, login_attempt.class);
           startActivity(intent);
            /* command that communicated to other class and opens rooms[0] data? */
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_map2);
    }
}