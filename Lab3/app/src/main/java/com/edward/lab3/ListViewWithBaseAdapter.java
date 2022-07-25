package com.edward.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.edward.lab3.Adapter.adapter;

import java.util.ArrayList;

public class ListViewWithBaseAdapter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_with_base_adapter);

        ArrayList<ModelData> list = new ArrayList<>();
        list.add(new ModelData("test String",R.drawable.hancock));
        list.add(new ModelData("test String",R.drawable.shank));
        list.add(new ModelData("test String",R.drawable.hancock));
        list.add(new ModelData("test String",R.drawable.shank));
        list.add(new ModelData("test String",R.drawable.hancock));
        list.add(new ModelData("test String",R.drawable.shank));

        ListView lv = findViewById(R.id.Lv);
        lv.setAdapter(new adapter(list,this));
    }
}