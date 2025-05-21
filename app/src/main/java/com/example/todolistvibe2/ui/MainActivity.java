package com.example.todolistvibe2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistvibe2.R;
import com.example.todolistvibe2.data.model.Todo;
import com.example.todolistvibe2.viewmodel.TodoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements TodoAdapter.OnTodoItemInteractionListener {
    private String userId;
    private TodoViewModel todoViewModel;
    private RecyclerView recyclerViewTodos;
    private TodoAdapter todoAdapter;
    private FloatingActionButton fabAddTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupérer l'ID de l'utilisateur
        userId = getIntent().getStringExtra("USER_ID");

        // Initialize Views
        recyclerViewTodos = findViewById(R.id.recyclerViewTodos);
        fabAddTodo = findViewById(R.id.fabAddTodo);

        // Setup ViewModel
        todoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);
        if (userId != null && !userId.isEmpty()) {
            todoViewModel.setCurrentUserId(userId);
        } else {
            // Handle error: userId is null or empty.
            // Maybe show a Toast and finish the activity.
            Toast.makeText(this, "User ID not found. Cannot load todos.", Toast.LENGTH_LONG).show();
            // finish(); // Optional: close activity if userId is critical
            // For now, let it proceed but ViewModel might not fetch user-specific data
        }


        // Setup RecyclerView and Adapter
        todoAdapter = new TodoAdapter(this);
        recyclerViewTodos.setAdapter(todoAdapter);
        recyclerViewTodos.setLayoutManager(new LinearLayoutManager(this));

        // Observe LiveData
        // Ensure currentUserId is set in ViewModel before observing
        if (userId != null && !userId.isEmpty()) {
            todoViewModel.getAllTodos().observe(this, todos -> {
                if (todos != null) {
                    todoAdapter.setTodos(todos);
                }
            });
        }


        // FAB OnClickListener
        fabAddTodo.setOnClickListener(view -> {
            // Toast.makeText(MainActivity.this, "Add new To-Do clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, AddEditTodoActivity.class);
            intent.putExtra(AddEditTodoActivity.EXTRA_USER_ID, userId);
            startActivity(intent);
        });
    }

    @Override
    public void onItemClicked(Todo todo) {
        Toast.makeText(this, "Clicked: " + todo.getTitle(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, AddEditTodoActivity.class);
        intent.putExtra(AddEditTodoActivity.EXTRA_USER_ID, userId);
        intent.putExtra(AddEditTodoActivity.EXTRA_TODO_ID, todo.getId()); // Pass todo id for editing
        startActivity(intent);
    }

    @Override
    public void onTodoCheckedChange(Todo todo, boolean isChecked) {
        todo.setCompleted(isChecked);
        todoViewModel.update(todo); // Assuming update method in ViewModel handles this.
        String status = isChecked ? "completed" : "active";
        Toast.makeText(this, todo.getTitle() + " marked as " + status, Toast.LENGTH_SHORT).show();
    }
} 