package com.example.employbee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class sampleShelf extends AppCompatActivity {
    public void goBack(View view) {

        Intent intent = new Intent(this, sampleRoom.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_shelf);
    }
}