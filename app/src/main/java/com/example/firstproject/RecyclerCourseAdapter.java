package com.example.firstproject;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerCourseAdapter extends RecyclerView.Adapter<RecyclerCourseAdapter.ViewHolder> {
    Context context;
    ArrayList<Course> arrCourses = DB.DB().courses;
    RecyclerCourseAdapter(Context context, ArrayList<Course> arrCourses) {
        this.context = context;
        this.arrCourses = arrCourses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.new_course, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtCode.setText(arrCourses.get(position).code);
        holder.txtName.setText(arrCourses.get(position).name);
        holder.txtProf.setText(arrCourses.get(position).prof);
        holder.courseRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.activity_update_remove_course);

                EditText editCode = dialog.findViewById(R.id.editCode);
                EditText editName = dialog.findViewById(R.id.editName);
                EditText editProf = dialog.findViewById(R.id.editProf);
                Button btnEditCourse = dialog.findViewById(R.id.addCourse);
                Button btnDeleteCourse = dialog.findViewById(R.id.deleteCourse);

                btnEditCourse.setText("Update Course");
                editCode.setText((arrCourses.get(position)).code);
                editName.setText((arrCourses.get(position)).name);
                editProf.setText((arrCourses.get(position)).prof);
                btnEditCourse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String code = "", name = "", prof = "";
                        if (!editCode.getText().toString().equals("") || !editName.getText().toString().equals("") ||
                                !editProf.getText().toString().equals("")) {
                            code = editCode.getText().toString();
                            name = editName.getText().toString();
                            prof = editProf.getText().toString();
                            Toast.makeText(context, "Course Updated Successfully!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Please fill out all course info!", Toast.LENGTH_SHORT).show();
                        }

                        arrCourses.set(position, new Course(code, name, prof));
                        notifyItemChanged(position);
                        dialog.dismiss();
                    }
                });

                btnDeleteCourse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        arrCourses.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(0,arrCourses.size());
                        Toast.makeText(context, "Course Removed Successfully!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }

        });
    }

    @Override
    public int getItemCount() {
        return arrCourses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtCode, txtName, txtProf;
        ConstraintLayout courseRow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCode = itemView.findViewById(R.id.txtCode);
            txtName = itemView.findViewById(R.id.txtName);
            txtProf = itemView.findViewById(R.id.txtProf);
            courseRow = itemView.findViewById(R.id.courseRow);
        }

    }
}
