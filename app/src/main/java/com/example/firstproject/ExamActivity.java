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

public class ExamActivity extends AppCompatActivity {

    RecyclerExamAdapter adapter;
    RecyclerView recyclerView;
    FloatingActionButton addButton;
    ArrayList<Exam> arrExams = DB.DB().exams;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.examView);
        addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(ExamActivity.this);
                dialog.setContentView(R.layout.activity_add_exam);

                EditText editCourseCode = dialog.findViewById(R.id.editCourseCode);
                EditText editDate = dialog.findViewById(R.id.editDate);
                EditText editLocation = dialog.findViewById(R.id.editLocation);
                EditText editTime = dialog.findViewById(R.id.editTime);
                Button btnAddCourse = dialog.findViewById(R.id.addExam);

                btnAddCourse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!editCourseCode.getText().toString().equals("") || !editDate.getText().toString().equals("") ||
                                !editLocation.getText().toString().equals("") || !editTime.getText().toString().equals("")) {
                            Toast.makeText(ExamActivity.this, "Course Added Successfully!!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ExamActivity.this, "Please fill out all course info!", Toast.LENGTH_SHORT).show();
                        }

                        arrExams.add(new Exam(editCourseCode.getText().toString(), editDate.getText().toString(),
                                editLocation.getText().toString(), editTime.getText().toString()));
                        adapter.notifyItemInserted(arrExams.size()-1);
                        recyclerView.scrollToPosition(arrExams.size() - 1);
                        dialog.dismiss();

                    }
                });
                dialog.show();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerExamAdapter(this, arrExams);
        recyclerView.setAdapter(adapter);
    }

}