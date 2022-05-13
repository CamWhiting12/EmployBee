package com.example.employbee;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface ProtocolDao {
    @Query("SELECT * FROM protocol")
    List<Protocol> getAll();

    @Query("SELECT * FROM protocol WHERE prot_name LIKE :prot")
    Protocol findByName(String prot);

    @Insert
    void insertAll(Protocol... protocols);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertTasks(Protocol... protocols);

    @Delete
    void delete(Protocol protocol);
}