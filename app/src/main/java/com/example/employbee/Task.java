package com.example.employbee;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
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
}
