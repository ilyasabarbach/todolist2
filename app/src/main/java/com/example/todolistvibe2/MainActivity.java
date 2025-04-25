package com.example.todolistvibe2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.todolistvibe2.ui.fragment.TodoListFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupérer l'ID de l'utilisateur de l'intent
        String userId = getIntent().getStringExtra("USER_ID");
        Log.d(TAG, "Received userId: " + userId);

        if (userId == null) {
            Log.e(TAG, "No user ID provided, using default user");
            userId = "default_user"; // Utiliser un ID par défaut si aucun n'est fourni
        }

        if (savedInstanceState == null) {
            // Créer une instance du fragment avec l'ID utilisateur
            TodoListFragment fragment = new TodoListFragment();
            Bundle args = new Bundle();
            args.putString("userId", userId);
            fragment.setArguments(args);

            // Charger le fragment
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }
    }
} 