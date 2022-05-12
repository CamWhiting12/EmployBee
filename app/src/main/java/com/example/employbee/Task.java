package com.example.employbee;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"task_name", "shift_name",  "position_name", "done_status"}, unique = true)})

public class Task {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "task_name")
    public String task;

    @ColumnInfo(name = "shift_name")
    public int shift;

    @ColumnInfo(name = "position_name")
    public int pos;

    @ColumnInfo(name = "done_status")
    public boolean done;

    public Task(){}
    public static int count = 0;

    public Task(String taskname, int shiftnum, int posnum, boolean isdone) {
        uid = count++;
        task = taskname;
        shift = shiftnum;
        pos = posnum;
        done = isdone;
    }

    public boolean equals(Task t) {
        if (task.equals(t.task) && shift == t.shift && pos == t.pos) {
            return true;
        }

        return false;
    }
    public String toString(){
        return task;
    }
}