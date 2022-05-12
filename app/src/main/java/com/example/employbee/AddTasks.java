package com.example.employbee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

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

        String taskSTR = taskText.getText().toString();
        String shiftSTR = shiftText.getText().toString();
        String posSTR = posText.getText().toString();

        int shiftINT = Integer.parseInt(shiftSTR);
        int posINT = Integer.parseInt(posSTR);

        db = Room.databaseBuilder(getBaseContext(), AppDatabase.class, "Tasks").allowMainThreadQueries().build();
        TaskDao taskdao = db.taskDao();

        Task task1 = new Task(taskSTR, shiftINT, posINT, false);
        taskdao.insertTasks(task1);

        //Toast here to let them know task was added
    }

    public void goHome(View v) {
        Intent intent = new Intent(this, Tasks1Activity.class);
        startActivity(intent);
    }

    public void removeTask(View v) {
        EditText taskText = findViewById(R.id.enterTaskField);
        EditText shiftText = findViewById(R.id.enterShiftField);
        EditText posText = findViewById(R.id.enterPositionField);

        String taskSTR = taskText.getText().toString();
        String shiftSTR = shiftText.getText().toString();
        String posSTR = posText.getText().toString();

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

    }

}