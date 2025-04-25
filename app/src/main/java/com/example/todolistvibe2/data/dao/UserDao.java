package com.example.todolistvibe2.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.todolistvibe2.data.model.User;

@Dao
public interface UserDao {
    @Insert
    long insert(User user);

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    LiveData<User> login(String email, String password);

    @Query("SELECT * FROM users WHERE email = :email")
    LiveData<User> getUserByEmail(String email);

    @Query("SELECT * FROM users WHERE id = :id")
    LiveData<User> getUserById(String id);
} 