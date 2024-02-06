package com.example.firstproject;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ToDoListActivity extends AppCompatActivity {

    RecyclerTaskAdapter adapter;
    RecyclerView recyclerView;
    FloatingActionButton addButton;
    ArrayList<Task> arrTasks = DB.DB().tasks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.taskBar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.taskView);
        addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(ToDoListActivity.this);
                dialog.setContentView(R.layout.activity_add_task);

                EditText editTask = dialog.findViewById(R.id.editTaskName);
                Button btnAddTask = dialog.findViewById(R.id.addTask);

                btnAddTask.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!editTask.getText().toString().equals("")) {
                            Toast.makeText(ToDoListActivity.this, "Task Added Successfully!!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ToDoListActivity.this, "Please fill out all task info!", Toast.LENGTH_SHORT).show();
                        }

                        arrTasks.add(new Task(editTask.getText().toString()));
                        adapter.notifyItemInserted(arrTasks.size()-1);
                        recyclerView.scrollToPosition(arrTasks.size() - 1);
                        dialog.dismiss();

                    }
                });
                dialog.show();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerTaskAdapter(this, arrTasks);
        recyclerView.setAdapter(adapter);
    }


}
