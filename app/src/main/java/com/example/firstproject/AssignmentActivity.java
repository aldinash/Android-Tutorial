package com.example.firstproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

public class AssignmentActivity extends AppCompatActivity {

    RecyclerAssignmentAdapter adapter;
    RecyclerView recyclerView;
    FloatingActionButton addButton;
    Button sortDateButton;
    Button sortClassButton;
    ArrayList<Assignment> arrAssignment = DB.DB().assignments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);

        Toolbar toolbar = (Toolbar) findViewById(R.id.assignmentBar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.assignmentView);
        addButton = findViewById(R.id.addAssignmentButton);
        sortClassButton = findViewById(R.id.sortClassButton);
        sortDateButton = findViewById(R.id.sortDateButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(AssignmentActivity.this);
                dialog.setContentView(R.layout.activity_add_assignment);

                EditText editClassCode = dialog.findViewById(R.id.editClassCode);
                EditText editDueDate = dialog.findViewById(R.id.editDueDate);
                EditText editAssignment = dialog.findViewById(R.id.editAssignmentTitle);
                Button btnAddAssignment = dialog.findViewById(R.id.addAssignment);

                btnAddAssignment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!editClassCode.getText().toString().equals("") || !editDueDate.getText().toString().equals("") ||
                                !editAssignment.getText().toString().equals("")) {
                            Toast.makeText(AssignmentActivity.this, "Assignment Added Successfully!!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AssignmentActivity.this, "Please fill out all assignment info!", Toast.LENGTH_SHORT).show();
                        }

                        arrAssignment.add(new Assignment(editAssignment.getText().toString(), editDueDate.getText().toString(),
                                editClassCode.getText().toString()));
                        adapter.notifyItemInserted(arrAssignment.size() - 1);
                        recyclerView.scrollToPosition(arrAssignment.size() - 1);
                        dialog.dismiss();

                    }
                });
                dialog.show();
            }
        });

        sortDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Collections.sort(arrAssignment, new Comparator<Assignment>() {
                    @Override
                    public int compare(Assignment o1, Assignment o2) {
                        return o1.dueDate.compareToIgnoreCase(o2.dueDate);
                    }
                });
                adapter.notifyDataSetChanged();
                Toast.makeText(AssignmentActivity.this, "Assignments Sorted Sucessfully!!", Toast.LENGTH_SHORT).show();
            }
        });

        sortClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Collections.sort(arrAssignment, new Comparator<Assignment>() {
                    @Override
                    public int compare(Assignment o1, Assignment o2) {
                        return o1.course.compareToIgnoreCase(o2.course);
                    }
                });
                adapter.notifyDataSetChanged();
                Toast.makeText(AssignmentActivity.this, "Assignments Sorted Sucessfully!!", Toast.LENGTH_SHORT).show();
            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerAssignmentAdapter(this, arrAssignment);
        recyclerView.setAdapter(adapter);

    }

}
