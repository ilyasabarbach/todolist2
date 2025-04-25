package com.example.todolistvibe2.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.todolistvibe2.data.dao.UserDao;
import com.example.todolistvibe2.data.database.AppDatabase;
import com.example.todolistvibe2.data.model.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserRepository {
    private UserDao userDao;
    private final ExecutorService executorService;

    public UserRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        userDao = database.userDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public void insert(User user) {
        executorService.execute(() -> userDao.insert(user));
    }

    public LiveData<User> getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    public LiveData<User> login(String email, String password) {
        return userDao.login(email, password);
    }
    
    public LiveData<User> getUserById(String id) {
        return userDao.getUserById(id);
    }
} 