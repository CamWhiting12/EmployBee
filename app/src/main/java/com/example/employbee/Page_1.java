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
import android.widget.AdapterView;
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
    private ListView lv1;
    private ListView lv2;
    private ListView lv3;
    private ListView lv4;

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

        for (ArrayList<Task> list: posTasks) {
            int checked = 0;
            int counter = 0;
            while (checked < list.size()) {
                if (list.get(counter).done) {
                    Task t = list.remove(counter);
                    list.add(t);
                    counter--;
                }
                counter++;
                checked ++;
            }
        }

        // Displaying tasks
        lv1 = (ListView) v.findViewById(R.id.listViewTasks1_1);
        c = getActivity().getBaseContext();
        ArrayAdapter adapter = new ArrayAdapter(c, R.layout.taskrow, posTasks.get(0));
        lv1.setAdapter(adapter);

        lv2 = (ListView) v.findViewById(R.id.listViewTasks1_2);
        adapter = new ArrayAdapter(c, R.layout.taskrow, posTasks.get(1));
        lv2.setAdapter(adapter);

        lv3 = (ListView) v.findViewById(R.id.listViewTasks1_3);
        adapter = new ArrayAdapter(c, R.layout.taskrow, posTasks.get(2));
        lv3.setAdapter(adapter);

        lv4 = (ListView) v.findViewById(R.id.listViewTasks1_4);
        adapter = new ArrayAdapter(c, R.layout.taskrow, posTasks.get(3));
        lv4.setAdapter(adapter);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                CheckBox check = (CheckBox) lv1.getChildAt(position);
                Task t = posTasks.get(0).get(position);
                Task t1 = new Task(t.task, t.shift, t.pos, !t.done);
                taskdao.delete(t);
                taskdao.insertTasks(t1);
                check.setChecked(!t.done);
            }
        });

        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                CheckBox check = (CheckBox) lv2.getChildAt(position);
                Task t = posTasks.get(1).get(position);
                Task t1 = new Task(t.task, t.shift, t.pos, !t.done);
                taskdao.delete(t);
                taskdao.insertTasks(t1);
                check.setChecked(!t.done);
            }
        });

        lv3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                CheckBox check = (CheckBox) lv3.getChildAt(position);
                Task t = posTasks.get(2).get(position);
                Task t1 = new Task(t.task, t.shift, t.pos, !t.done);
                taskdao.delete(t);
                taskdao.insertTasks(t1);
                check.setChecked(!t.done);
            }
        });

        lv4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                CheckBox check = (CheckBox) lv4.getChildAt(position);
                Task t = posTasks.get(3).get(position);
                Task t1 = new Task(t.task, t.shift, t.pos, !t.done);
                taskdao.delete(t);
                taskdao.insertTasks(t1);
                check.setChecked(!t.done);
            }
        });

        // Add this Runnable
        lv1.post(new Runnable() {
            @Override
            public void run() {
                setChecks(posTasks.get(0), lv1);
            }
        });

        lv2.post(new Runnable() {
            @Override
            public void run() {
                setChecks(posTasks.get(1), lv2);
            }
        });

        lv3.post(new Runnable() {
            @Override
            public void run() {
                setChecks(posTasks.get(2), lv3);
            }
        });

        lv4.post(new Runnable() {
            @Override
            public void run() {
                setChecks(posTasks.get(3), lv4);
            }
        });

        return v;
    }

    public void goHome(View v) {
        Intent intent = new Intent(getActivity().getBaseContext(), MainActivity.class);
        startActivity(intent);
    }

    private void setChecks(ArrayList<Task> tasks, ListView lv) {

        for (int i = lv.getFirstVisiblePosition(); i < lv.getChildCount(); i++) {
            CheckBox check = (CheckBox) lv.getChildAt(i);

            Task t = (Task) lv.getAdapter().getItem(i);
            if (t.done) {
                check.setChecked(true);
            }
        }


    }
}