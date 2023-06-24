package com.example.apptodolist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptodolist.R;
import com.example.apptodolist.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{
    private final ArrayList<Task> tasks;

    public TaskAdapter(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view,parent,false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.bind(task);
    }

    @Override
    public int getItemCount() {
        try {
            int size = tasks.size();
        } catch (Exception e) {
            return 0;
        }
        return tasks.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView title_task;
        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            title_task = itemView.findViewById(R.id.title_tasks);
        }

        public void bind(Task task) {
            title_task.setText(task.getTitle());
        }
    }
}
