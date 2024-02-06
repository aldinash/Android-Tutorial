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

public class RecyclerExamAdapter extends RecyclerView.Adapter<RecyclerExamAdapter.ViewHolder> {
    Context context;
    ArrayList<Exam> arrExams = DB.DB().exams;
    RecyclerExamAdapter(Context context, ArrayList<Exam> arrExams) {
        this.context = context;
        this.arrExams = arrExams;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.new_exam, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtCourseCode.setText(arrExams.get(position).course);
        holder.txtDate.setText(arrExams.get(position).date);
        holder.txtLocation.setText(arrExams.get(position).location);
        holder.txtTime.setText(arrExams.get(position).time);
        holder.examRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.activity_update_remove_exam);

                EditText editCourseCode = dialog.findViewById(R.id.editCourseCode);
                EditText editDate = dialog.findViewById(R.id.editDate);
                EditText editLocation = dialog.findViewById(R.id.editLocation);
                EditText editTime = dialog.findViewById(R.id.editTime);
                Button btnEditExam = dialog.findViewById(R.id.addExam);
                Button btnDeleteExam = dialog.findViewById(R.id.deleteExam);

                btnEditExam.setText("Update Exam");
                editCourseCode.setText((arrExams.get(position)).course);
                editDate.setText((arrExams.get(position)).date);
                editLocation.setText((arrExams.get(position)).location);
                editTime.setText((arrExams.get(position)).time);
                btnEditExam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String courseCode = "", date = "", location = "", time="";
                        if (!editCourseCode.getText().toString().equals("") || !editDate.getText().toString().equals("") ||
                                !editLocation.getText().toString().equals("") || !editTime.getText().toString().equals("")) {
                            courseCode = editCourseCode.getText().toString();
                            date = editDate.getText().toString();
                            location = editLocation.getText().toString();
                            time = editTime.getText().toString();
                            Toast.makeText(context, "Exam Updated Successfully!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Please fill out all exam info!", Toast.LENGTH_SHORT).show();
                        }

                        arrExams.set(position, new Exam(courseCode, date, location, time));
                        notifyItemChanged(position);
                        dialog.dismiss();
                    }
                });

                btnDeleteExam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        arrExams.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(0,arrExams.size());
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
        return arrExams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtCourseCode, txtDate, txtLocation, txtTime;
        ConstraintLayout examRow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCourseCode = itemView.findViewById(R.id.txtCourseCode);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtLocation = itemView.findViewById(R.id.txtLocation);
            txtTime = itemView.findViewById(R.id.txtTime);
            examRow = itemView.findViewById(R.id.examRow);
        }

    }
}
