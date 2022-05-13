package com.example.employbee;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Protocol.class}, version = 1)
public abstract class ProtDatabase extends RoomDatabase {
    public abstract ProtocolDao protDao();
}