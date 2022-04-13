package com.example.employbee;

public class Task {
    private String task; // Description of task
    private int shift; // The shift that the task belongs to
    private int pos; // The role that needs to complete the task
    private boolean done; // Whether or not the task is completed

    public Task(String taskText, int shiftNum, int posNum, boolean isDone) {
        task = taskText;
        shift = shiftNum;
        pos = posNum;
        done = isDone;
    }

    public String getTask() {
        return task;
    }

    public int getShift() {
        return shift;
    }

    public int getPos() {
        return pos;
    }

    public boolean getDone() {
        return done;
    }

    public void changeDone() {
        done = !done;
    }
}
