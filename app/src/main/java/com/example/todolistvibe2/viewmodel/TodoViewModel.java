package com.example.todolistvibe2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todolistvibe2.data.model.Todo;
import com.example.todolistvibe2.data.repository.TodoRepository;

import java.util.List;

public class TodoViewModel extends AndroidViewModel {
    private TodoRepository repository;
    private String currentUserId;

    public TodoViewModel(@NonNull Application application) {
        super(application);
        repository = new TodoRepository(application);
    }

    public void setCurrentUserId(String userId) {
        this.currentUserId = userId;
    }

    public void insert(String title, String description) {
        Todo todo = new Todo(title, description, currentUserId);
        repository.insert(todo);
    }

    public void update(Todo todo) {
        repository.update(todo);
    }

    public void delete(Todo todo) {
        repository.delete(todo);
    }

    public void toggleTodoCompleted(Todo todo) {
        todo.setCompleted(!todo.isCompleted());
        repository.update(todo);
    }

    public LiveData<List<Todo>> getAllTodos() {
        return repository.getAllTodos(currentUserId);
    }

    public LiveData<List<Todo>> getActiveTodos() {
        return repository.getActiveTodos(currentUserId);
    }

    public LiveData<List<Todo>> getCompletedTodos() {
        return repository.getCompletedTodos(currentUserId);
    }
} 