package com.example.firstproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerCourseAdapter adapter;
    RecyclerView recyclerView;
    FloatingActionButton addButton;
    ArrayList<Course> arrCourses = DB.DB().courses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.courseView);
        addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.activity_add_course);

                EditText editCode = dialog.findViewById(R.id.editCode);
                EditText editName = dialog.findViewById(R.id.editName);
                EditText editProf = dialog.findViewById(R.id.editProf);
                Button btnAddCourse = dialog.findViewById(R.id.addCourse);

                btnAddCourse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String code = "", name = "", prof = "";
                        if (!editCode.getText().toString().equals("") || !editName.getText().toString().equals("") ||
                                !editProf.getText().toString().equals("")) {
//                            code = editCode.getText().toString();
//                            name = editName.getText().toString();
//                            prof = editProf.getText().toString();
                            Toast.makeText(MainActivity.this, "Course Added Successfully!!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Please fill out all course info!", Toast.LENGTH_SHORT).show();
                        }

                        arrCourses.add(new Course(editCode.getText().toString(), editName.getText().toString(),
                                editProf.getText().toString()));
                        adapter.notifyItemInserted(arrCourses.size()-1);
                        recyclerView.scrollToPosition(arrCourses.size() - 1);
                        dialog.dismiss();

                    }
                });
                dialog.show();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerCourseAdapter(this, arrCourses);
        recyclerView.setAdapter(adapter);
    }

}