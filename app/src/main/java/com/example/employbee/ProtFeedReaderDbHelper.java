package com.example.employbee;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProtFeedReaderDbHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ProtFeedReaderContract.FeedEntry.TABLE_NAME + " (" +
                    ProtFeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    ProtFeedReaderContract.FeedEntry.COLUMN_NAME_TITLE1 + " TEXT," +
                    ProtFeedReaderContract.FeedEntry.COLUMN_NAME_TITLE2 + " TEXT)" ;

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ProtFeedReaderContract.FeedEntry.TABLE_NAME;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ProtFeedReader.db";

    public ProtFeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}