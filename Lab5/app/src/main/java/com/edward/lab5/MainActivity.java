package com.edward.lab5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.edward.lab5.dialong.DateDialog;
import com.edward.lab5.dialong.TimeDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.click).setOnClickListener(view -> {
            DialogFragment newFragment = new DateDialog();
            newFragment.show(getSupportFragmentManager(), "datePicker");
        });

        findViewById(R.id.click1).setOnClickListener(view -> {
            DialogFragment newFragment = new TimeDialog();
            newFragment.show(getSupportFragmentManager(), "timePicker");
        });
        findViewById(R.id.click2).setOnClickListener(view -> {
            ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.setTitle("ProgressDialog");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();
            progressDialog.setCancelable(false);
            new Thread(() -> {
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }).start();
        });
    }
    
}