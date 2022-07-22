package com.edward.assigment.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.assigment.model.TaskDAO;
import com.edward.assigment.model.TaskToDo;
import com.edward.assigment.R;

import java.util.ArrayList;
import java.util.Objects;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private final ArrayList<TaskToDo> _ListTask;
    private final TaskDAO _TD;

    public TaskAdapter(Context context) {
        _TD = new TaskDAO(context);
        _ListTask = _TD.getList();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        private final TextView _task;
        private final CheckBox _checkbox;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            _task = itemView.findViewById(R.id.textView);
            _checkbox = itemView.findViewById(R.id.checkbox);

        }
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View _view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleview_component, parent, false);
        return new TaskViewHolder(_view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        TaskToDo _taskToDo = _ListTask.get(position);

        if (_taskToDo == null) {
            return;
        }
        holder._task.setText(_taskToDo.getTask());
        Objects.requireNonNull(holder)._checkbox.setOnCheckedChangeListener((compoundButton, b) -> {
            if (compoundButton.isChecked()) {
                holder._task.setPaintFlags(holder._task.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
            if (!compoundButton.isChecked()) {
                holder._task.setPaintFlags(holder._task.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }
        });

    }

    public TaskToDo getTaskIndex(int index) {
        return _ListTask.get(index);
    }

    @Override
    public int getItemCount() {
        return _ListTask.size();
    }

    public boolean removeElement(int index) {
        if (_TD.deleteTask(_ListTask.get(index).getTask())){
            _ListTask.remove(index);
            notifyItemRemoved(index);
            return true;
        }
        return false;
    }

    @SuppressLint("NotifyDataSetChanged")
    public boolean AddTask(TaskToDo m_task) {
        if (_TD.insertTask(m_task)) {
            notifyDataSetChanged();
            return true;
        }
        return false;

    }

    @SuppressLint("NotifyDataSetChanged")
    public void undoItem(TaskToDo m_task) { //can used bool ?
        if (_TD.insertTask(m_task)) {
            _ListTask.add(m_task);
            notifyDataSetChanged();

        }
    }
}
