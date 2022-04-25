package com.example.employbee;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.employbee.Task;

public class DataBaseHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Tasksdb";
    private static final String TABLE_NAME = "Tasks";
    private static final String KEY_TASK = "task";
    private static final String KEY_SHIFT = "shift";
    private static final String KEY_POSITION = "position";
    private static final String KEY_DONE = "done";
    private static final String[] COLUMNS = {KEY_TASK, KEY_SHIFT, KEY_POSITION, KEY_DONE };

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("Henry", "on create");
        String CREATION_TABLE = "CREATE TABLE Tasks ( "
                + "task TEXT, " + "shift INT, "
                + "position INT, " + "done BOOLEAN )";

        db.execSQL(CREATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you can implement here migration process
        Log.e("Henry", "on upgrade");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public Task getTask(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, // a. table
                COLUMNS, // b. column names
                " id = ?", // c. selections
                new String[]{String.valueOf(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor != null)
            cursor.moveToFirst();

        Task task = new Task(cursor.getString(0), Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3))==1);

        return task;
    }

    public List<Task> allTasks() {

        List<Task> tasks = new LinkedList<Task>();
        String query = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Log.e("Henry", "on create6");
        Task task = null;

        if (cursor.moveToFirst()) {
            do {
                task = new Task(cursor.getString(0), Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3))==1);
                tasks.add(task);
            } while (cursor.moveToNext());
        }

        return tasks;
    }

    public void addTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TASK, task.getTask());
        values.put(KEY_POSITION, task.getPos());
        values.put(KEY_SHIFT, task.getShift());
        values.put(KEY_DONE, task.getDone());

        // insert
        db.insert(TABLE_NAME,null, values);
        db.close();
    }

}
