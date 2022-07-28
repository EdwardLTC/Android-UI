package com.edward.lab5.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.edward.lab5.R;

public class DialogCustom extends Dialog {
    public Context context;

    public DialogCustom(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_component);

        findViewById(R.id.ClickYes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText user = findViewById(R.id.user);
                EditText pass = findViewById(R.id.pass);
                if (user.getText().toString().equals("abc") && pass.getText().toString().equals("123")){
                    Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(context, "Login False", Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }
        });

        findViewById(R.id.ClickNo).setOnClickListener(view -> dismiss());
    }

}
