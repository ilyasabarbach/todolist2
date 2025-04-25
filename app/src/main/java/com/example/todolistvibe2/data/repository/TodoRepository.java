package com.example.todolistvibe2.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.todolistvibe2.data.dao.TodoDao;
import com.example.todolistvibe2.data.database.AppDatabase;
import com.example.todolistvibe2.data.model.Todo;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TodoRepository {
    private TodoDao todoDao;
    private final ExecutorService executorService;

    public TodoRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        todoDao = database.todoDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public void insert(Todo todo) {
        executorService.execute(() -> todoDao.insert(todo));
    }

    public void update(Todo todo) {
        executorService.execute(() -> todoDao.update(todo));
    }

    public void delete(Todo todo) {
        executorService.execute(() -> todoDao.delete(todo));
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
    
    public LiveData<Todo> getTodoById(long id) {
        return todoDao.getTodoById(id);
    }

    public void deleteAllTodos(String userId) {
        executorService.execute(() -> todoDao.deleteAllTodos(userId));
    }
} 