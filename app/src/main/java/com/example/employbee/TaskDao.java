package com.example.employbee;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task")
    List<Task> getAll();

    @Query("SELECT * FROM task WHERE uid IN (:taskIds)")
    List<Task> loadAllByIds(int[] taskIds);

    @Query("SELECT * FROM task WHERE task_name LIKE :task AND " + "position_name LIKE :pos LIMIT 1")
    Task findByName(String task, int pos);

    @Insert
    void insertAll(Task... tasks);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertTasks(Task... tasks);

    @Delete
    void delete(Task task);
}
