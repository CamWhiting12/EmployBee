package com.example.employbee;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Page_2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Page_2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView lv;
    private ArrayList<Task> tasks;
    private ArrayList<String> taskStrings;

    public Page_2() {
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
        Log.d("Henry", "starting oncreate");
//        Log.d("Henry", "" + getArguments());
//
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//            Log.d("Henry", "" + mParam1 + " " + mParam2);
//        }

        Log.d("Henry", "getArgs null");

        Context context = getActivity().getApplicationContext();
        taskStrings = new ArrayList<String>();

        Log.d("Henry", "before try");
//        try {
//            tasks = EditTasks.getTasks(context, "stylesheet.xls");
//            Log.d("Henry", "" + tasks.size());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        for (Task t: tasks) {
//            taskStrings.add(t.getTask());
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_page_2, container, false);

        // Test array
        ArrayList<String> sampleList = new ArrayList<String>();
        sampleList.add("task 1");
        sampleList.add("task 2");
        sampleList.add("task 78");

        lv = (ListView) v.findViewById(R.id.taskListView1);
        ArrayAdapter adapter = new ArrayAdapter(getActivity().getBaseContext(), R.layout.taskrow, sampleList);

        lv.setAdapter(adapter);
        return v;
    }

}