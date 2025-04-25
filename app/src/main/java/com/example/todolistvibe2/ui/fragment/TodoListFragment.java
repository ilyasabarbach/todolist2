package com.example.todolistvibe2.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistvibe2.R;
import com.example.todolistvibe2.adapter.TodoAdapter;
import com.example.todolistvibe2.data.model.Todo;
import com.example.todolistvibe2.viewmodel.TodoViewModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class TodoListFragment extends Fragment implements TodoAdapter.TodoClickListener {
    private static final String TAG = "TodoListFragment";
    private TodoViewModel viewModel;
    private TodoAdapter adapter;
    private String userId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userId = getArguments().getString("userId");
            if (userId == null) {
                Log.e(TAG, "No userId provided to fragment, using default");
                userId = "default_user";
            }
        } else {
            Log.e(TAG, "No arguments provided to fragment, using default userId");
            userId = "default_user";
        }
        Log.d(TAG, "Using userId: " + userId);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView called with userId: " + userId);
        return inflater.inflate(R.layout.fragment_todo_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated called");
        
        try {
            // Initialiser le ViewModel
            viewModel = new ViewModelProvider(this).get(TodoViewModel.class);
            viewModel.setCurrentUserId(userId);
            
            // Configurer le RecyclerView
            RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
            if (recyclerView == null) {
                Log.e(TAG, "RecyclerView not found in layout");
                return;
            }
            
            adapter = new TodoAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
            
            // Observer les changements de la liste des todos
            viewModel.getAllTodos().observe(getViewLifecycleOwner(), todos -> {
                Log.d(TAG, "Received todos: " + (todos != null ? todos.size() : "null"));
                adapter.setTodos(todos);
            });

            // Configurer le FAB
            FloatingActionButton fab = view.findViewById(R.id.fabAddTodo);
            if (fab == null) {
                Log.e(TAG, "FAB not found in layout");
                return;
            }
            fab.setOnClickListener(v -> showAddTodoDialog());
            
        } catch (Exception e) {
            Log.e(TAG, "Error in onViewCreated: ", e);
        }
    }

    private void showAddTodoDialog() {
        try {
            View dialogView = LayoutInflater.from(requireContext())
                    .inflate(R.layout.dialog_add_todo, null);
            
            TextInputEditText titleInput = dialogView.findViewById(R.id.titleInput);
            TextInputEditText descriptionInput = dialogView.findViewById(R.id.descriptionInput);

            new MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Nouvelle tâche")
                    .setView(dialogView)
                    .setPositiveButton("Ajouter", (dialog, which) -> {
                        String title = titleInput.getText().toString().trim();
                        String description = descriptionInput.getText().toString().trim();
                        if (!title.isEmpty()) {
                            viewModel.insert(title, description);
                        }
                    })
                    .setNegativeButton("Annuler", null)
                    .show();
        } catch (Exception e) {
            Log.e(TAG, "Error showing dialog: ", e);
        }
    }

    @Override
    public void onDeleteClick(Todo todo) {
        try {
            viewModel.delete(todo);
        } catch (Exception e) {
            Log.e(TAG, "Error deleting todo: ", e);
        }
    }

    @Override
    public void onCheckboxClick(Todo todo) {
        try {
            viewModel.toggleTodoComplete(todo);
        } catch (Exception e) {
            Log.e(TAG, "Error toggling todo: ", e);
        }
    }

    @Override
    public void onItemClick(Todo todo) {
        // Pour une future implémentation de l'édition
    }
} 