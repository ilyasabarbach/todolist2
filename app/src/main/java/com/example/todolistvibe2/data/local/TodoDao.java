package com.example.todolistvibe2.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todolistvibe2.data.model.Todo;

import java.util.List;

@Dao
public interface TodoDao {
    @Insert
    void insert(Todo todo);

    @Update
    void update(Todo todo);

    @Delete
    void delete(Todo todo);

    @Query("SELECT * FROM todos WHERE userId = :userId ORDER BY createdAt DESC")
    LiveData<List<Todo>> getAllTodos(String userId);

    @Query("SELECT * FROM todos WHERE completed = 0 AND userId = :userId")
    LiveData<List<Todo>> getActiveTodos(String userId);

    @Query("SELECT * FROM todos WHERE completed = 1 AND userId = :userId")
    LiveData<List<Todo>> getCompletedTodos(String userId);
}