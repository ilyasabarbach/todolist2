package com.example.todolistvibe2.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.todolistvibe2.data.local.AppDatabase;
import com.example.todolistvibe2.data.local.UserDao;
import com.example.todolistvibe2.data.model.User;

public class UserRepository {
    private UserDao userDao;

    public UserRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        userDao = database.userDao();
    }

    public void insert(User user) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            userDao.insert(user);
        });
    }

    public LiveData<User> getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    public LiveData<User> login(String email, String password) {
        return userDao.login(email, password);
    }
} 