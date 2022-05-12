package com.example.employbee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class AddTasks extends AppCompatActivity {

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tasks);
    }

    public void addTask(View v) {
        EditText taskText = findViewById(R.id.enterTaskField);
        EditText shiftText = findViewById(R.id.enterShiftField);
        EditText posText = findViewById(R.id.enterPositionField);

        Log.e("Henry", "" + shiftText);

        String taskSTR = taskText.getText().toString();
        String shiftSTR = shiftText.getText().toString();
        String posSTR = posText.getText().toString();

        Toast toast;

        if (!isEmpty(taskSTR) && !isEmpty(shiftSTR) && !isEmpty(posSTR)) {


            int shiftINT = Integer.parseInt(shiftSTR);
            int posINT = Integer.parseInt(posSTR);

            db = Room.databaseBuilder(getBaseContext(), AppDatabase.class, "Tasks").allowMainThreadQueries().build();
            TaskDao taskdao = db.taskDao();

            Task task1 = new Task(taskSTR, shiftINT, posINT, false);
            taskdao.insertTasks(task1);

            toast = Toast.makeText(getApplicationContext(), "Task added successfully", Toast.LENGTH_SHORT);
        }

        else {
            toast = Toast.makeText(getApplicationContext(), "The field is empty. Please try again.", Toast.LENGTH_SHORT);
        }

        toast.show();
    }

    public void goHome(View v) {
        Intent intent = new Intent(this, Tasks1Activity.class);
        startActivity(intent);
    }

    public void removeTask(View v) {
        EditText taskText = findViewById(R.id.enterTaskField);
        EditText shiftText = findViewById(R.id.enterShiftField);
        EditText posText = findViewById(R.id.enterPositionField);

        Log.e("Henry", "" + shiftText);

        String taskSTR = taskText.getText().toString();
        String shiftSTR = shiftText.getText().toString();
        String posSTR = posText.getText().toString();

        Toast toast;

        if (!isEmpty(taskSTR) && !isEmpty(shiftSTR) && !isEmpty(posSTR)) {

            int shiftINT = Integer.parseInt(shiftSTR);
            int posINT = Integer.parseInt(posSTR);

            db = Room.databaseBuilder(getBaseContext(), AppDatabase.class, "Tasks").allowMainThreadQueries().build();
            TaskDao taskdao = db.taskDao();

            Task t = new Task(taskSTR, shiftINT, posINT, false);
            List<Task> tasks = taskdao.getAll();

            for (int i = 0; i < tasks.size(); i++) {
                if (t.equals(tasks.get(i))) {
                    taskdao.delete(tasks.get(i));
                }
            }

            toast = Toast.makeText(getApplicationContext(), "Task removed", Toast.LENGTH_SHORT);
        }

        else {
            toast = Toast.makeText(getApplicationContext(), "The field is empty. Please try again.", Toast.LENGTH_SHORT);
        }

        toast.show();
    }

    private boolean isEmpty(String s) {
        return s.equals("");
    }
}