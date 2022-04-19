package com.example.employbee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class sampleRoom extends AppCompatActivity {
    public void goBack(View view) {

        Intent intent = new Intent(this, general_map.class);
        startActivity(intent);
    }

    public void openShelfSample(View view) {

        Intent intent = new Intent(this, sampleShelf.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_room);
    }
}