package com.example.employbee;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.employbee.ui.main.SectionsPagerAdapter;
import com.example.employbee.databinding.ActivityTasks1Binding;

public class Tasks1Activity extends AppCompatActivity {

    private ActivityTasks1Binding binding;
    private AppDatabase db;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTasks1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = binding.homeButton;

        db = Room.databaseBuilder(getBaseContext(), AppDatabase.class, "Tasks").allowMainThreadQueries().build();
        TaskDao taskdao = db.taskDao();;
    }

    public void goToAddTask(View v) {
        Intent intent = new Intent(this, AddTasks.class);
        startActivity(intent);
    }

    public void goHome(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}