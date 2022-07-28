package com.edward.lab5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.edward.lab5.dialog.DialogCustom;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        findViewById(R.id.SimpleDialog).setOnClickListener(view -> {
            AlertDialog alertDialog = new AlertDialog.Builder(DialogActivity.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("Hello con ga");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Cancel",
                    (dialog, which) -> dialog.dismiss());
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK",
                    (dialog, which) -> dialog.dismiss());
            alertDialog.show();
        });

        findViewById(R.id.ListDialog).setOnClickListener(view -> {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(DialogActivity.this, android.R.layout.select_dialog_item);
            arrayAdapter.add("Non");
            arrayAdapter.add("Con ga");
            arrayAdapter.add("Tuoi");
            arrayAdapter.add("Solo yasuo");
            arrayAdapter.add("Rung khong gank ");

            AlertDialog.Builder builderSingle = new AlertDialog.Builder(DialogActivity.this);
            builderSingle.setTitle("Hay chon cau de gay:-");
            builderSingle.setNegativeButton("cancel", (dialog, which) -> dialog.dismiss());

            builderSingle.setAdapter(arrayAdapter, (dialog, which) -> {
                String strName = arrayAdapter.getItem(which);
                AlertDialog.Builder builderInner = new AlertDialog.Builder(DialogActivity.this);
                builderInner.setMessage(strName);
                builderInner.setTitle("Cau con ga nay gay la");
                builderInner.setPositiveButton("Ok", (dialog1, which1) -> dialog1.dismiss());
                builderInner.show();
            });
            builderSingle.show();
        });

        findViewById(R.id.SingleChoiceDialog).setOnClickListener(view -> {
            String[] items = {"Non", "Con ga", "Tuoi", "Solo yasuo", "Rung Khong gank"};
            new AlertDialog.Builder(this)
                    .setSingleChoiceItems(items, 0, null)
                    .setPositiveButton("Ga", (dialog, whichButton) -> {
                        dialog.dismiss();

                    }).show();
        });

        findViewById(R.id.MultiChoiceDialog).setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(DialogActivity.this);
            String[] gayArrays = new String[]{"Non", "Con ga", "Tuoi", "Solo yasuo", "Rung Khong gank"};
            boolean[] checkedGayArray = new boolean[]{true, false, false, false, false, false};
            builder.setTitle("Select colors");
            builder.setMultiChoiceItems(gayArrays, checkedGayArray, (dialog, which, isChecked) -> checkedGayArray[which] = isChecked);
            builder.setPositiveButton("OK", (dialog, which) -> {

            });
            builder.setNeutralButton("Cancel", (dialog, which) -> {

            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });

        findViewById(R.id.CustomDialog).setOnClickListener(view -> {
            DialogCustom dialogCustom = new DialogCustom(DialogActivity.this);
            dialogCustom.show();
        });
    }
}