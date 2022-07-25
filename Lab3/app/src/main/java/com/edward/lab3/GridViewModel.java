package com.edward.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.edward.lab3.Adapter.GridAdapter;

import java.util.ArrayList;

public class GridViewModel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        GridView gridView = findViewById(R.id.grid);

        ArrayList<ModelData> list = new ArrayList<>();
        list.add(new ModelData("test String",R.drawable.hancock));
        list.add(new ModelData("test String",R.drawable.shank));
        list.add(new ModelData("test String",R.drawable.hancock));
        list.add(new ModelData("test String",R.drawable.shank));
        list.add(new ModelData("test String",R.drawable.hancock));
        list.add(new ModelData("test String",R.drawable.shank));

        gridView.setAdapter(new GridAdapter(list,this));
    }
}