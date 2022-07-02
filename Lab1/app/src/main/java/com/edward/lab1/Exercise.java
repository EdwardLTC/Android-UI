package com.edward.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Exercise extends AppCompatActivity {
    FrameLayout frm;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exsrcise);

        frm = findViewById(R.id.cardFarm);
        int top = 0 ;
        for (int i = 1; i <= 4; i++) {
            int left = 0;
            for (int j = 1; j <= 13; j++) {
                String str ="c"+(i)+(j);
                Drawable drawable1 = getResources().getDrawable(getResources()
                        .getIdentifier(str, "drawable", getPackageName()));

                left+=30;

                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(250,250);

                params.setMargins(left,top,0,0);

                ImageView imageView = new ImageView(this);
                imageView.setImageDrawable(drawable1);

                imageView.setLayoutParams(params);
                frm.addView(imageView);
                if (j==13)
                    top+=250;
            }
        }
    }
}
