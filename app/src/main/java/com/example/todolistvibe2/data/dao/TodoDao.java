package com.example.todolistvibe2.data.dao;

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
    long insert(Todo todo);

    @Update
    void update(Todo todo);

    @Delete
    void delete(Todo todo);

    @Query("SELECT * FROM todos WHERE userId = :userId ORDER BY id DESC")
    LiveData<List<Todo>> getAllTodos(String userId);

    @Query("SELECT * FROM todos WHERE id = :id")
    LiveData<Todo> getTodoById(long id);

    @Query("DELETE FROM todos WHERE userId = :userId")
    void deleteAllTodos(String userId);

    @Query("SELECT * FROM todos WHERE userId = :userId AND isCompleted = 0")
    LiveData<List<Todo>> getActiveTodos(String userId);

    @Query("SELECT * FROM todos WHERE userId = :userId AND isCompleted = 1")
    LiveData<List<Todo>> getCompletedTodos(String userId);
} 