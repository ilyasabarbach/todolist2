package com.example.todolistvibe2.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todos")
public class Todo {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String title;
    private String description;
    private boolean isCompleted;
    private String userId;

    public Todo(String title, String description, String userId) {
        this.title = title;
        this.description = description;
        this.isCompleted = false;
        this.userId = userId;
    }

    // Getters
    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String getUserId() {
        return userId;
    }

    // Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
} 