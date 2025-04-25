package com.example.todolistvibe2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.todolistvibe2.data.model.User;
import com.example.todolistvibe2.data.repository.UserRepository;

public class AuthViewModel extends AndroidViewModel {
    private UserRepository repository;
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public AuthViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
    }

    public void register(String email, String password, String username) {
        if (email.isEmpty() || password.isEmpty() || username.isEmpty()) {
            errorMessage.setValue("Tous les champs sont obligatoires");
            return;
        }

        User newUser = new User(email, password, username);
        repository.insert(newUser);
    }

    public LiveData<User> login(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            errorMessage.setValue("Email et mot de passe sont obligatoires");
            return null;
        }
        return repository.login(email, password);
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }
} 