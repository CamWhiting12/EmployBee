package com.example.employbee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class protocol_page extends AppCompatActivity {
    SearchView searchView;
    ListView lv;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    private ArrayList<Task> tasks;
    private ProtDatabase db;
    private Context c;

    public void goHome(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protocol_page);

        // Hardcoded adding protocols


        searchView = (SearchView) findViewById(R.id.searchView);
        lv = (ListView) findViewById(R.id.lv1);

        db = Room.databaseBuilder(getBaseContext(), ProtDatabase.class, "Protocols").allowMainThreadQueries().build();
        ProtocolDao protdao = db.protDao();

        protdao.insertTasks(new Protocol("How to use a coupon", "Scan the coupon"));
        protdao.insertTasks(new Protocol("How to perform a citizens arrest", "I have no idea"));
        protdao.insertTasks(new Protocol("How to change the lightbulbs", "Screw the lightbulb in"));
        protdao.insertTasks(new Protocol("How to clean the refrigerator", "With cleaning supplies"));

        List<Protocol> protocols = protdao.getAll();

        ArrayAdapter adapter = new ArrayAdapter(getBaseContext(), R.layout.taskrow1, protocols);
        lv.setAdapter(adapter);

        TextView title = (TextView) findViewById(R.id.titleView);
        TextView info = (TextView) findViewById(R.id.infoView);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                TextView textview = (TextView) lv.getChildAt(position);
                Protocol p = protocols.get(position);

                title.setText(p.prot);
                info.setText(p.protInfo);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                query = query.toLowerCase(Locale.ROOT);

                if (protocols.contains(query.toUpperCase(Locale.ROOT))) {
                    adapter.getFilter().filter(query);
                } else {
                    Toast.makeText(protocol_page.this, "No Match found", Toast.LENGTH_LONG).show();
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