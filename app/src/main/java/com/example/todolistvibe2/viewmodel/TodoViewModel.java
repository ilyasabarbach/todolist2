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

    public void insert(String title, String description, long dueDate, int priority) {
        Todo todo = new Todo(title, description, currentUserId, dueDate, priority);
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

    // --- Sorting Methods ---

    // Due Date Sorting
    public LiveData<List<Todo>> getAllTodosOrderByDueDateAsc() {
        return repository.getAllTodosOrderByDueDateAsc(currentUserId);
    }

    public LiveData<List<Todo>> getAllTodosOrderByDueDateDesc() {
        return repository.getAllTodosOrderByDueDateDesc(currentUserId);
    }

    public LiveData<List<Todo>> getActiveTodosOrderByDueDateAsc() {
        return repository.getActiveTodosOrderByDueDateAsc(currentUserId);
    }

    public LiveData<List<Todo>> getActiveTodosOrderByDueDateDesc() {
        return repository.getActiveTodosOrderByDueDateDesc(currentUserId);
    }

    public LiveData<List<Todo>> getCompletedTodosOrderByDueDateAsc() {
        return repository.getCompletedTodosOrderByDueDateAsc(currentUserId);
    }

    public LiveData<List<Todo>> getCompletedTodosOrderByDueDateDesc() {
        return repository.getCompletedTodosOrderByDueDateDesc(currentUserId);
    }

    // Priority Sorting
    public LiveData<List<Todo>> getAllTodosOrderByPriorityAsc() {
        return repository.getAllTodosOrderByPriorityAsc(currentUserId);
    }

    public LiveData<List<Todo>> getAllTodosOrderByPriorityDesc() {
        return repository.getAllTodosOrderByPriorityDesc(currentUserId);
    }

    public LiveData<List<Todo>> getActiveTodosOrderByPriorityAsc() {
        return repository.getActiveTodosOrderByPriorityAsc(currentUserId);
    }

    public LiveData<List<Todo>> getActiveTodosOrderByPriorityDesc() {
        return repository.getActiveTodosOrderByPriorityDesc(currentUserId);
    }

    public LiveData<List<Todo>> getCompletedTodosOrderByPriorityAsc() {
        return repository.getCompletedTodosOrderByPriorityAsc(currentUserId);
    }

    public LiveData<List<Todo>> getCompletedTodosOrderByPriorityDesc() {
        return repository.getCompletedTodosOrderByPriorityDesc(currentUserId);
    }
} 