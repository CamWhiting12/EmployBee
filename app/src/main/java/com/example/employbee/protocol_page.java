package com.example.employbee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class protocol_page extends AppCompatActivity {
    SearchView searchView;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String > adapter;


    public void goHome(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_protocol_page);

                searchView = (SearchView) findViewById(R.id.searchView);
                listView = (ListView) findViewById(R.id.lv1);

                list = new ArrayList<>();
                list.add("Apple");
                list.add("Banana");
                list.add("Pineapple");
                list.add("Orange");
                list.add("Lychee");
                list.add("Guava");
                list.add("Peach");
                list.add("Melon");
                list.add("Watermelon");
                list.add("Papaya");

                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
                listView.setAdapter(adapter);

                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {

                        if(list.contains(query)){
                            adapter.getFilter().filter(query);
                        }else{
                            Toast.makeText(protocol_page.this, "No Match found",Toast.LENGTH_LONG).show();
                        }
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        //    adapter.getFilter().filter(newText);
                        return false;
                    }
                });
            }
        }

