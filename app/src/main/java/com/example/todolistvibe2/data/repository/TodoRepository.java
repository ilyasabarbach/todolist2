package com.example.todolistvibe2.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.sqlite.db.SimpleSQLiteQuery;

import com.example.todolistvibe2.data.local.AppDatabase;
import com.example.todolistvibe2.data.local.TodoDao;
import com.example.todolistvibe2.data.model.Todo;

import java.util.List;

public class TodoRepository {
    private TodoDao todoDao;

    public TodoRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        todoDao = database.todoDao();
    }

    public void insert(Todo todo) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            todoDao.insert(todo);
        });
    }

    public void update(Todo todo) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            todoDao.update(todo);
        });
    }

    public void delete(Todo todo) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            todoDao.delete(todo);
        });
    }

    public LiveData<List<Todo>> getAllTodos(String userId) {
        String query = "SELECT * FROM todos WHERE userId = ? ORDER BY createdAt DESC";
        return todoDao.getAllTodos(new SimpleSQLiteQuery(query, new Object[]{userId}));
    }

    public LiveData<List<Todo>> getActiveTodos(String userId) {
        String query = "SELECT * FROM todos WHERE userId = ? AND completed = 0 ORDER BY createdAt DESC";
        return todoDao.getActiveTodos(new SimpleSQLiteQuery(query, new Object[]{userId}));
    }

    public LiveData<List<Todo>> getCompletedTodos(String userId) {
        String query = "SELECT * FROM todos WHERE userId = ? AND completed = 1 ORDER BY createdAt DESC";
        return todoDao.getCompletedTodos(new SimpleSQLiteQuery(query, new Object[]{userId}));
    }

    // --- Methods for sorting by DueDate ---

    public LiveData<List<Todo>> getAllTodosOrderByDueDateAsc(String userId) {
        return todoDao.getAllTodosOrderByDueDateAsc(userId);
    }

    public LiveData<List<Todo>> getAllTodosOrderByDueDateDesc(String userId) {
        return todoDao.getAllTodosOrderByDueDateDesc(userId);
    }

    public LiveData<List<Todo>> getActiveTodosOrderByDueDateAsc(String userId) {
        return todoDao.getActiveTodosOrderByDueDateAsc(userId);
    }

    public LiveData<List<Todo>> getActiveTodosOrderByDueDateDesc(String userId) {
        return todoDao.getActiveTodosOrderByDueDateDesc(userId);
    }

    public LiveData<List<Todo>> getCompletedTodosOrderByDueDateAsc(String userId) {
        return todoDao.getCompletedTodosOrderByDueDateAsc(userId);
    }

    public LiveData<List<Todo>> getCompletedTodosOrderByDueDateDesc(String userId) {
        return todoDao.getCompletedTodosOrderByDueDateDesc(userId);
    }

    // --- Methods for sorting by Priority ---

    public LiveData<List<Todo>> getAllTodosOrderByPriorityAsc(String userId) {
        return todoDao.getAllTodosOrderByPriorityAsc(userId);
    }

    public LiveData<List<Todo>> getAllTodosOrderByPriorityDesc(String userId) {
        return todoDao.getAllTodosOrderByPriorityDesc(userId);
    }

    public LiveData<List<Todo>> getActiveTodosOrderByPriorityAsc(String userId) {
        return todoDao.getActiveTodosOrderByPriorityAsc(userId);
    }

    public LiveData<List<Todo>> getActiveTodosOrderByPriorityDesc(String userId) {
        return todoDao.getActiveTodosOrderByPriorityDesc(userId);
    }

    public LiveData<List<Todo>> getCompletedTodosOrderByPriorityAsc(String userId) {
        return todoDao.getCompletedTodosOrderByPriorityAsc(userId);
    }

    public LiveData<List<Todo>> getCompletedTodosOrderByPriorityDesc(String userId) {
        return todoDao.getCompletedTodosOrderByPriorityDesc(userId);
    }
} 