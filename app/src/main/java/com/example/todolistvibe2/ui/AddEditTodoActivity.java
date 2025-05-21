package com.example.todolistvibe2.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.todolistvibe2.R;
import com.example.todolistvibe2.data.model.Todo;
import com.example.todolistvibe2.viewmodel.TodoViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddEditTodoActivity extends AppCompatActivity {

    public static final String EXTRA_TODO_ID = "com.example.todolistvibe2.EXTRA_TODO_ID";
    public static final String EXTRA_USER_ID = "com.example.todolistvibe2.EXTRA_USER_ID"; // For passing user ID

    private EditText editTextTodoTitle;
    private EditText editTextTodoDescription;
    private EditText editTextTodoDueDate;
    private EditText editTextTodoPriority;
    private Button buttonSaveTodo;

    private TodoViewModel todoViewModel;
    private String currentUserId;
    private boolean isEditMode = false;
    private int todoIdToEdit = -1; // Assuming Todo ID is int

    private Calendar dueDateCalendar;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_todo);

        editTextTodoTitle = findViewById(R.id.editTextTodoTitle);
        editTextTodoDescription = findViewById(R.id.editTextTodoDescription);
        editTextTodoDueDate = findViewById(R.id.editTextTodoDueDate);
        editTextTodoPriority = findViewById(R.id.editTextTodoPriority);
        buttonSaveTodo = findViewById(R.id.buttonSaveTodo);

        dueDateCalendar = Calendar.getInstance();
        dateFormatter = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

        todoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);

        currentUserId = getIntent().getStringExtra(EXTRA_USER_ID);
        if (currentUserId != null && !currentUserId.isEmpty()) {
            todoViewModel.setCurrentUserId(currentUserId); // Set user ID in ViewModel
        } else {
            Toast.makeText(this, "User ID not found. Cannot save todo.", Toast.LENGTH_LONG).show();
            finish(); // Or handle appropriately
            return;
        }

        if (getIntent().hasExtra(EXTRA_TODO_ID)) {
            isEditMode = true;
            todoIdToEdit = getIntent().getIntExtra(EXTRA_TODO_ID, -1); // Default to -1 if not found
            setTitle("Edit Todo");
            // TODO: Load Todo data and populate fields - requires getTodoById in ViewModel
            // For now, we will just set the title
            if (todoIdToEdit == -1) {
                 Toast.makeText(this, "Error: Invalid Todo ID for editing.", Toast.LENGTH_LONG).show();
                 finish();
                 return;
            }
            // todoViewModel.getTodoById(todoIdToEdit).observe(this, todo -> {
            // if (todo != null) {
            // editTextTodoTitle.setText(todo.getTitle());
            // editTextTodoDescription.setText(todo.getDescription());
            // if (todo.getDueDate() > 0) {
            // dueDateCalendar.setTimeInMillis(todo.getDueDate());
            // editTextTodoDueDate.setText(dateFormatter.format(dueDateCalendar.getTime()));
            // }
            // editTextTodoPriority.setText(String.valueOf(todo.getPriority()));
            // }
            // });
        } else {
            setTitle("Add Todo");
        }

        editTextTodoDueDate.setOnClickListener(v -> showDatePickerDialog());

        buttonSaveTodo.setOnClickListener(v -> saveTodo());
    }

    private void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            dueDateCalendar.set(Calendar.YEAR, year);
            dueDateCalendar.set(Calendar.MONTH, month);
            dueDateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            editTextTodoDueDate.setText(dateFormatter.format(dueDateCalendar.getTime()));
        };

        new DatePickerDialog(AddEditTodoActivity.this, dateSetListener,
                dueDateCalendar.get(Calendar.YEAR),
                dueDateCalendar.get(Calendar.MONTH),
                dueDateCalendar.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    private void saveTodo() {
        String title = editTextTodoTitle.getText().toString().trim();
        String description = editTextTodoDescription.getText().toString().trim();
        String dueDateStr = editTextTodoDueDate.getText().toString().trim();
        String priorityStr = editTextTodoPriority.getText().toString().trim();

        if (TextUtils.isEmpty(title)) {
            Toast.makeText(this, "Please enter a title", Toast.LENGTH_SHORT).show();
            return;
        }

        long dueDateMillis = 0;
        if (!TextUtils.isEmpty(dueDateStr)) {
            try {
                // Use the calendar which was set by DatePickerDialog
                dueDateMillis = dueDateCalendar.getTimeInMillis();
            } catch (Exception e) { // Catch generic exception if parsing from text was re-enabled.
                Toast.makeText(this, "Invalid date format. Use MM/dd/yyyy", Toast.LENGTH_SHORT).show();
                return;
            }
        } else {
             // Allow no due date, dueDateMillis remains 0 (or handle as error if required)
            Toast.makeText(this, "Due date not set. It's recommended to set one.", Toast.LENGTH_SHORT).show();
        }


        int priority = 0; // Default priority
        if (!TextUtils.isEmpty(priorityStr)) {
            try {
                priority = Integer.parseInt(priorityStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Please enter a valid priority number", Toast.LENGTH_SHORT).show();
                return;
            }
        }


        if (isEditMode) {
            // TODO: Implement update logic
            // Todo todoToUpdate = new Todo(title, description, currentUserId, dueDateMillis, priority);
            // todoToUpdate.setId(todoIdToEdit); // Make sure Todo has setId() and id field
            // todoViewModel.update(todoToUpdate);
            // Toast.makeText(this, "Todo updated", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Update functionality not fully implemented yet.", Toast.LENGTH_SHORT).show();
        } else {
            todoViewModel.insert(title, description, dueDateMillis, priority);
            Toast.makeText(this, "Todo saved", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}
