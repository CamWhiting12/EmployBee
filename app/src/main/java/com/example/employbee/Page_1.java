package com.example.employbee;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.provider.BaseColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Page_2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Page_1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView lv;
    private ArrayList<Task> tasks;
    private AppDatabase db;
    private Context c;

    public Page_1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Page_2.
     */
    // TODO: Rename and change types and number of parameters
    public static Page_2 newInstance(String param1, String param2) {
        Page_2 fragment = new Page_2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_page_1, container, false);
        db = Room.databaseBuilder(getActivity().getBaseContext(), AppDatabase.class, "Tasks").allowMainThreadQueries().build();
        TaskDao taskdao = db.taskDao();

        List<Task> tasks = taskdao.getAll();

        // Selecting Shift
        int thisShift = 1;

        ArrayList<Task> shiftTasks = new ArrayList<Task>();

        for (Task t: tasks) {
            if (t.shift == thisShift) {
                shiftTasks.add(t);
            }
        }

        // Splitting by position
        ArrayList<ArrayList<Task>> posTasks = new ArrayList<ArrayList<Task>>();
        for (int i = 0; i < 4; i++) {
            posTasks.add(new ArrayList<Task>());
        }

        for (Task t: shiftTasks) {
            int pos = t.pos - 1; // Depends on posNum range

            posTasks.get(pos).add(t);
        }

        // Displaying tasks
        lv = (ListView) v.findViewById(R.id.listViewTasks1_1);
        c = getActivity().getBaseContext();
        ArrayAdapter adapter = new ArrayAdapter(c, R.layout.taskrow, posTasks.get(0));
        lv.setAdapter(adapter);

        lv = (ListView) v.findViewById(R.id.listViewTasks1_2);
        adapter = new ArrayAdapter(c, R.layout.taskrow, posTasks.get(1));
        lv.setAdapter(adapter);

        lv = (ListView) v.findViewById(R.id.listViewTasks1_3);
        adapter = new ArrayAdapter(c, R.layout.taskrow, posTasks.get(2));
        lv.setAdapter(adapter);

        lv = (ListView) v.findViewById(R.id.listViewTasks1_4);
        adapter = new ArrayAdapter(c, R.layout.taskrow, posTasks.get(3));
        lv.setAdapter(adapter);

        return v;
    }

    public void goHome(View v) {
        Intent intent = new Intent(getActivity().getBaseContext(), MainActivity.class);
        startActivity(intent);
    }

}