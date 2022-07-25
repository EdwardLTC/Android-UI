package com.edward.lab3.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.edward.lab3.ModelData;
import com.edward.lab3.R;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    private ArrayList<ModelData> list;
    private Context context;

    public GridAdapter(ArrayList<ModelData> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public static class ViewHolder{
        TextView textView;
        ImageView imageView;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        adapter.ViewHolder viewHolder;
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        if (view == null){
            view = inflater.inflate(R.layout.lv_component_grid,viewGroup,false);
            viewHolder = new adapter.ViewHolder();
            viewHolder.imageView = view.findViewById(R.id.imgGrid);
            viewHolder.textView= view.findViewById(R.id.textGrid);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (adapter.ViewHolder) view.getTag();
        }

        viewHolder.imageView.setImageResource(list.get(i).getImg());
        viewHolder.textView.setText(list.get(i).getTxt());

        return view;
    }
}
