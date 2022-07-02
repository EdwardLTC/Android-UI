package com.edward.assigment;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.edward.assigment.Adapter.DialogCustom;
import com.edward.assigment.Adapter.TaskAdapter;
import com.edward.assigment.ui.home.HomeFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.edward.assigment.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private TaskAdapter _TaskAdapter;
    private static final float END_SCALE = 0.7f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        _TaskAdapter=  new TaskAdapter(getBaseContext());

        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        View view = findViewById(R.id.app_bar_main);
        drawer.setScrimColor(Color.TRANSPARENT);
        drawer.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
               @Override
               public void onDrawerSlide(View drawerView, float slideOffset) {
                   navigationView.setVisibility(slideOffset > 0 ? View.VISIBLE : View.GONE);

                   final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                   final float offsetScale = 1 - diffScaledOffset;
                   view.setScaleX(offsetScale);
                   view.setScaleY(offsetScale);

                   final float xOffset = drawerView.getWidth() * slideOffset;
                   final float xOffsetDiff = view.getWidth() * diffScaledOffset / 2;
                   final float xTranslation = xOffset - xOffsetDiff;
                   view.setTranslationX(xTranslation);
               }

               @Override
               public void onDrawerClosed(View drawerView) {
                   navigationView.setVisibility(View.GONE);
               }
           }
        );

        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEditDialog();
            }
        });

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        DialogCustom editNameDialogFragment =  DialogCustom.newInstance();
        editNameDialogFragment.show(fm, "dialog_component");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}