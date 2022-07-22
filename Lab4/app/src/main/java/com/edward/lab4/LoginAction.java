package com.edward.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginAction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_action);
        findViewById(R.id.hehe).setOnClickListener(view -> {
            Intent intent = new Intent(LoginAction.this,MainActivity.class);
            startActivity(intent);
        });
    }
}