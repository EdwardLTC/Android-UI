package com.edward.assigment.Adapter;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.assigment.Model.TaskToDo;
import com.edward.assigment.R;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{

    private Context _context;
    private ArrayList<TaskToDo> _ListTask;

    public TaskAdapter(Context context){
       this._context=context;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View _view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleview_component,parent,false);

        return new TaskViewHolder(_view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        TaskToDo _taskToDo =_ListTask.get(position);

        if (_taskToDo == null){
            return;
        }
        holder._task.setText(_taskToDo.getTask());
        holder._checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()){
                    holder._task.setPaintFlags(holder._task.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
                }
                if (!compoundButton.isChecked()) {
                    holder._task.setPaintFlags(holder._task.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return _ListTask.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout _linearLayout;
        private  TextView _task;
        private CheckBox _checkbox;
        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            _task = itemView.findViewById(R.id.textView);
            _checkbox =  itemView.findViewById(R.id.checkbox);
            _linearLayout =itemView.findViewById(R.id.layout_component);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(ArrayList<TaskToDo> m_ListTask){
        _ListTask = m_ListTask;
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addTask(TaskToDo m_task){
        _ListTask.add(0,m_task);
        notifyDataSetChanged();
    }

    public void removeElement(int index){
        _ListTask.remove(index);
        notifyItemRemoved(index);
    }

}
