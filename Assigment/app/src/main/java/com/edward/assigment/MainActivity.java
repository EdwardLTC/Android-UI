package com.edward.assigment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.edward.assigment.ui.gallery.GalleryFragment;
import com.edward.assigment.ui.home.HomeFragment;
import com.edward.assigment.ui.setting.SlideshowFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.edward.assigment.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private static final float END_SCALE = 0.7f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.edward.assigment.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id  = item.getItemId();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fragment = null;
        if (id == R.id.nav_home) {
            fragment = new HomeFragment();
        } else if (id == R.id.nav_gallery) {
           fragment = new GalleryFragment();
        } else if (id == R.id.nav_slideshow) {
           fragment = new SlideshowFragment();
        }
        ft.replace(R.id.nav_host_fragment_content_main, Objects.requireNonNull(fragment));
        ft.addToBackStack(null);
        ft.commit();
        return true;
    }

}