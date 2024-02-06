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

public class RecyclerAssignmentAdapter extends RecyclerView.Adapter<RecyclerAssignmentAdapter.ViewHolder> {
    Context context;
    ArrayList<Assignment> arrAssignments = DB.DB().assignments;
    RecyclerAssignmentAdapter(Context context, ArrayList<Assignment> arrAssignments) {
        this.context = context;
        this.arrAssignments = arrAssignments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.new_assignment, parent, false);
        RecyclerAssignmentAdapter.ViewHolder viewHolder = new RecyclerAssignmentAdapter.ViewHolder(view);
        return viewHolder;
    } // onCreateViewHolder

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtClassCode.setText(arrAssignments.get(position).course);
        holder.txtDueDate.setText(arrAssignments.get(position).dueDate);
        holder.txtAssignment.setText(arrAssignments.get(position).assignment);
        holder.assignmentRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.activity_update_remove_assignment);

                EditText editClassCode = dialog.findViewById(R.id.editClassCode);
                EditText editDueDate = dialog.findViewById(R.id.editDueDate);
                EditText editAssignment = dialog.findViewById(R.id.editAssignmentTitle);
                Button btnUpdateAssignment = dialog.findViewById(R.id.updateAssignment);
                Button btnDeleteAssignment = dialog.findViewById(R.id.deleteAssignment);

                btnUpdateAssignment.setText("Update Assignment");
                editClassCode.setText((arrAssignments.get(position)).course);
                editDueDate.setText((arrAssignments.get(position)).dueDate);
                editAssignment.setText((arrAssignments.get(position)).assignment);
                btnUpdateAssignment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String classCode = "", dueDate = "", assignment = "";
                        if (!editClassCode.getText().toString().equals("") || !editDueDate.getText().toString().equals("") ||
                                !editAssignment.getText().toString().equals("")) {
                            classCode = editClassCode.getText().toString();
                            dueDate = editDueDate.getText().toString();
                            assignment = editAssignment.getText().toString();
                            Toast.makeText(context, "Assignment Updated Successfully!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Please fill out all assignment info!", Toast.LENGTH_SHORT).show();
                        }

                        arrAssignments.set(position, new Assignment(assignment, dueDate, classCode));
                        notifyItemChanged(position);
                        dialog.dismiss();
                    }
                });

                btnDeleteAssignment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        arrAssignments.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(0,arrAssignments.size());
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
        return arrAssignments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtAssignment, txtDueDate, txtClassCode;
        ConstraintLayout assignmentRow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtDueDate = itemView.findViewById(R.id.txtDueDate);
            txtAssignment = itemView.findViewById(R.id.txtAssignment);
            txtClassCode = itemView.findViewById(R.id.txtClassCode);
            assignmentRow = itemView.findViewById(R.id.assignmentRow);
        }
    } // ViewHolder

}


