package com.example.appbanvexemphim.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.appbanvexemphim.R;
import com.google.android.material.navigation.NavigationView;

public class AdminDashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_manage_screenings) {
            // Handle navigation to Manage Screenings
            Intent intent = new Intent(this, AdminNgayChieuActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage_movies) {
            // Handle navigation to Manage Movies
            Intent intent = new Intent(this, ManagentPhimActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_diadiem) {
            // Handle navigation to Manage Movies
            Intent intent = new Intent(this, AdminDiaDiemActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_tinh) {
            // Handle navigation to Manage Movies
            Intent intent = new Intent(this, AdminTinhActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_giochieu) {
            // Handle navigation to Manage Movies
            Intent intent = new Intent(this, AdminGioChieuActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_loairap) {
            // Handle navigation to Manage Movies
            Intent intent = new Intent(this, AdminLoaiRapActivity.class);
            startActivity(intent);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}