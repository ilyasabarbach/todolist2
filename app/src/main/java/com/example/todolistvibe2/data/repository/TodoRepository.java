package com.example.todolistvibe2.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

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
        return todoDao.getAllTodos(userId);
    }

    public LiveData<List<Todo>> getActiveTodos(String userId) {
        return todoDao.getActiveTodos(userId);
    }

    public LiveData<List<Todo>> getCompletedTodos(String userId) {
        return todoDao.getCompletedTodos(userId);
    }
} 