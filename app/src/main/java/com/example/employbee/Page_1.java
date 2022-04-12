package com.example.employbee;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.*;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Page_1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Page_1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ListView lv;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Page_1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Page_1.
     */
    // TODO: Rename and change types and number of parameters
    public static Page_1 newInstance(String param1, String param2) {
        Page_1 fragment = new Page_1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        Context context = null;
        ArrayList<Task> tasks = new ArrayList<Task>;
        try {
           tasks = EditTasks.getTasks(context, "StyleSheet.xls");
        } catch (IOException e) {
            e.printStackTrace();
        }

       lv = (ListView) lv.findViewById(R.id.taskListView1);

        ArrayList<String> taskStrings = new ArrayList<String>();

        for (Task t: tasks) {
            taskStrings.add(t.getTask());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.SOMETHING, R.id.job1task1, taskStrings);
        lv.setAdapter(arrayAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page_1, container, false);
    }
}