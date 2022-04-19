package com.example.employbee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class general_map extends AppCompatActivity {
    public void goHome(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openShelfSample(View view) {

        Intent intent = new Intent(this, sampleShelf.class);
        startActivity(intent);
    }

    public void openRoomSample(View view) {

        Intent intent = new Intent(this, sampleRoom.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_map2);
    }
}