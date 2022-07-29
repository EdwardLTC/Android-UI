package com.edward.assigment.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.edward.assigment.R;
import com.edward.assigment.model.ExpandableChild;

import java.util.ArrayList;

public class GridCustom extends BaseAdapter {
    private ArrayList<ExpandableChild> list;
    private Context context;

    public GridCustom(ArrayList<ExpandableChild> list, Context context) {
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
        GridCustom.ViewHolder viewHolder;
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        if (view == null){
            view = inflater.inflate(R.layout.grid_component,viewGroup,false);
            viewHolder = new GridCustom.ViewHolder();
            viewHolder.imageView = view.findViewById(R.id.imgGrid);
            viewHolder.textView= view.findViewById(R.id.textGrid);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (GridCustom.ViewHolder) view.getTag();
        }

        viewHolder.imageView.setImageResource(list.get(i).get_icon());
        viewHolder.textView.setText(list.get(i).get_text());

        return view;
    }
}
