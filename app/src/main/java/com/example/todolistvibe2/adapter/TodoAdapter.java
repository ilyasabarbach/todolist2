package com.example.todolistvibe2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistvibe2.R;
import com.example.todolistvibe2.data.model.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    private List<Todo> todos = new ArrayList<>();
    private final TodoClickListener listener;

    public interface TodoClickListener {
        void onDeleteClick(Todo todo);
        void onCheckboxClick(Todo todo);
        void onItemClick(Todo todo);
    }

    public TodoAdapter(TodoClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_todo, parent, false);
        return new TodoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo currentTodo = todos.get(position);
        holder.bind(currentTodo);
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
        notifyDataSetChanged();
    }

    class TodoViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleTextView;
        private final TextView descriptionTextView;
        private final CheckBox checkBox;
        private final ImageButton deleteButton;

        TodoViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.todoTitle);
            descriptionTextView = itemView.findViewById(R.id.todoDescription);
            checkBox = itemView.findViewById(R.id.todoCheckbox);
            deleteButton = itemView.findViewById(R.id.deleteButton);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(todos.get(position));
                }
            });

            checkBox.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onCheckboxClick(todos.get(position));
                }
            });

            deleteButton.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onDeleteClick(todos.get(position));
                }
            });
        }

        void bind(Todo todo) {
            titleTextView.setText(todo.getTitle());
            descriptionTextView.setText(todo.getDescription());
            checkBox.setChecked(todo.isCompleted());
            
            // Style pour les tâches complétées
            if (todo.isCompleted()) {
                titleTextView.setAlpha(0.5f);
                descriptionTextView.setAlpha(0.5f);
            } else {
                titleTextView.setAlpha(1.0f);
                descriptionTextView.setAlpha(1.0f);
            }
        }
    }
} 