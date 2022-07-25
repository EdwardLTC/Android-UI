package com.edward.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv = findViewById(R.id.lv);
        Spinner spinner = findViewById(R.id.spinner);

        ArrayList<HashMap<String, Object>> list = new ArrayList<>();

        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("name","hancock");
        hashMap.put("age","18");
        hashMap.put("img",R.drawable.hancock);
        list.add(hashMap);

        HashMap<String,Object> hashMap2 = new HashMap<>();
        hashMap2.put("name","Shanks");
        hashMap2.put("age","35");
        hashMap2.put("img",R.drawable.shank);
        list.add(hashMap2);

        HashMap<String,Object> hashMap3 = new HashMap<>();
        hashMap3.put("name","Android");
        hashMap3.put("age","Unknown");
        hashMap3.put("img",R.drawable.android);
        list.add(hashMap3);

        lv.setAdapter(new SimpleAdapter(this, list, R.layout.lv_component,
                new String[]{"name", "age", "img"}, new int[]{R.id.name, R.id.age, R.id.image}));

        spinner.setAdapter(new SimpleAdapter(this, list, R.layout.spinet_component,
                new String[]{"name", "img"}, new int[]{R.id.nameSp, R.id.imgSp}));
    }
}