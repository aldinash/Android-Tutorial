package com.example.firstproject;

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

public class RecyclerTaskAdapter extends RecyclerView.Adapter<RecyclerTaskAdapter.ViewHolder> {

    Context context;
    ArrayList<Task> arrTasks = DB.DB().tasks;
    RecyclerTaskAdapter(Context context, ArrayList<Task> arrTasks) {
        this.context = context;
        this.arrTasks = arrTasks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.new_task, parent, false);
        RecyclerTaskAdapter.ViewHolder viewHolder = new RecyclerTaskAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerTaskAdapter.ViewHolder holder, int position) {
        holder.txtTask.setText(arrTasks.get(position).task);
        holder.taskRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.activity_update_remove_task);

                EditText editTaskName = dialog.findViewById(R.id.editTaskName);
                Button btnEditTask = dialog.findViewById(R.id.updateTask);
                Button btnDeleteTask = dialog.findViewById(R.id.deleteTask);

                btnEditTask.setText("Update Task");
                editTaskName.setText((arrTasks.get(position)).task);
                btnEditTask.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String taskName = "";
                        if (!editTaskName.getText().toString().equals("")) {
                            taskName = editTaskName.getText().toString();
                            Toast.makeText(context, "Task Updated Successfully!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Please fill out all task info!", Toast.LENGTH_SHORT).show();
                        }

                        arrTasks.set(position, new Task(taskName));
                        notifyItemChanged(position);
                        dialog.dismiss();
                    }
                });

                btnDeleteTask.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        arrTasks.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(0,arrTasks.size());
                        Toast.makeText(context, "Task Removed Successfully!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }

        });
    }


    @Override
    public int getItemCount() {
        return arrTasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTask;
        ConstraintLayout taskRow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTask = itemView.findViewById(R.id.taskName);
            taskRow = itemView.findViewById(R.id.taskRow);
        }

    }



}
