package com.example.todolistvibe2.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.todolistvibe2.R;

public class MainActivity extends AppCompatActivity {
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupérer l'ID de l'utilisateur
        userId = getIntent().getStringExtra("USER_ID");
    }
} 