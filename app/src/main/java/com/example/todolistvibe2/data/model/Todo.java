package com.example.todolistvibe2.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todos")
public class Todo {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    private boolean completed;
    private long createdAt;
    private String userId;
    private long dueDate;
    private int priority;

    public Todo(String title, String description, String userId, long dueDate, int priority) {
        this.title = title;
        this.description = description;
        this.completed = false;
        this.createdAt = System.currentTimeMillis();
        this.userId = userId;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
    public long getCreatedAt() { return createdAt; }
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public long getDueDate() { return dueDate; }
    public void setDueDate(long dueDate) { this.dueDate = dueDate; }
    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }
} 