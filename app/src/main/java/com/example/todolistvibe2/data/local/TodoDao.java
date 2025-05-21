package com.example.todolistvibe2.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;
import androidx.sqlite.db.SupportSQLiteQuery;

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

    @RawQuery(observedEntities = Todo.class)
    LiveData<List<Todo>> getAllTodos(SupportSQLiteQuery query);

    @RawQuery(observedEntities = Todo.class)
    LiveData<List<Todo>> getActiveTodos(SupportSQLiteQuery query);

    @RawQuery(observedEntities = Todo.class)
    LiveData<List<Todo>> getCompletedTodos(SupportSQLiteQuery query);

    // --- Methods for sorting by DueDate ---

    @Query("SELECT * FROM todos WHERE userId = :userId ORDER BY dueDate ASC")
    LiveData<List<Todo>> getAllTodosOrderByDueDateAsc(String userId);

    @Query("SELECT * FROM todos WHERE userId = :userId ORDER BY dueDate DESC")
    LiveData<List<Todo>> getAllTodosOrderByDueDateDesc(String userId);

    @Query("SELECT * FROM todos WHERE completed = 0 AND userId = :userId ORDER BY dueDate ASC")
    LiveData<List<Todo>> getActiveTodosOrderByDueDateAsc(String userId);

    @Query("SELECT * FROM todos WHERE completed = 0 AND userId = :userId ORDER BY dueDate DESC")
    LiveData<List<Todo>> getActiveTodosOrderByDueDateDesc(String userId);

    @Query("SELECT * FROM todos WHERE completed = 1 AND userId = :userId ORDER BY dueDate ASC")
    LiveData<List<Todo>> getCompletedTodosOrderByDueDateAsc(String userId);

    @Query("SELECT * FROM todos WHERE completed = 1 AND userId = :userId ORDER BY dueDate DESC")
    LiveData<List<Todo>> getCompletedTodosOrderByDueDateDesc(String userId);

    // --- Methods for sorting by Priority ---

    @Query("SELECT * FROM todos WHERE userId = :userId ORDER BY priority ASC")
    LiveData<List<Todo>> getAllTodosOrderByPriorityAsc(String userId);

    @Query("SELECT * FROM todos WHERE userId = :userId ORDER BY priority DESC")
    LiveData<List<Todo>> getAllTodosOrderByPriorityDesc(String userId);

    @Query("SELECT * FROM todos WHERE completed = 0 AND userId = :userId ORDER BY priority ASC")
    LiveData<List<Todo>> getActiveTodosOrderByPriorityAsc(String userId);

    @Query("SELECT * FROM todos WHERE completed = 0 AND userId = :userId ORDER BY priority DESC")
    LiveData<List<Todo>> getActiveTodosOrderByPriorityDesc(String userId);

    @Query("SELECT * FROM todos WHERE completed = 1 AND userId = :userId ORDER BY priority ASC")
    LiveData<List<Todo>> getCompletedTodosOrderByPriorityAsc(String userId);

    @Query("SELECT * FROM todos WHERE completed = 1 AND userId = :userId ORDER BY priority DESC")
    LiveData<List<Todo>> getCompletedTodosOrderByPriorityDesc(String userId);
}