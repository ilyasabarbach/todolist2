package com.example.todolistvibe2.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistvibe2.R;
import com.example.todolistvibe2.data.model.Todo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    private List<Todo> todos = new ArrayList<>();
    private OnTodoItemInteractionListener listener;

    // Interface for click and check change events
    public interface OnTodoItemInteractionListener {
        void onItemClicked(Todo todo);
        void onTodoCheckedChange(Todo todo, boolean isChecked);
    }

    public TodoAdapter(OnTodoItemInteractionListener listener) {
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
        holder.textViewTodoTitle.setText(currentTodo.getTitle());
        holder.textViewTodoDescription.setText(currentTodo.getDescription());

        if (currentTodo.getDueDate() > 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
            holder.textViewTodoDueDate.setText("Due: " + sdf.format(currentTodo.getDueDate()));
        } else {
            holder.textViewTodoDueDate.setText("No due date");
        }

        holder.textViewTodoPriority.setText("Priority: " + currentTodo.getPriority());
        holder.checkBoxTodoCompleted.setChecked(currentTodo.isCompleted());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClicked(currentTodo);
            }
        });

        holder.checkBoxTodoCompleted.setOnCheckedChangeListener(null); // Clear listener first
        holder.checkBoxTodoCompleted.setChecked(currentTodo.isCompleted());
        holder.checkBoxTodoCompleted.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (listener != null) {
                listener.onTodoCheckedChange(currentTodo, isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
        notifyDataSetChanged(); // Consider using DiffUtil for better performance
    }

    static class TodoViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTodoTitle;
        TextView textViewTodoDescription;
        TextView textViewTodoDueDate;
        TextView textViewTodoPriority;
        CheckBox checkBoxTodoCompleted;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTodoTitle = itemView.findViewById(R.id.textViewTodoTitle);
            textViewTodoDescription = itemView.findViewById(R.id.textViewTodoDescription);
            textViewTodoDueDate = itemView.findViewById(R.id.textViewTodoDueDate);
            textViewTodoPriority = itemView.findViewById(R.id.textViewTodoPriority);
            checkBoxTodoCompleted = itemView.findViewById(R.id.checkBoxTodoCompleted);
        }
    }
}
