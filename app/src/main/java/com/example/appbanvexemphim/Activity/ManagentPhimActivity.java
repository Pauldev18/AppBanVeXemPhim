package com.example.appbanvexemphim.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanvexemphim.API.ApiPhimService;
import com.example.appbanvexemphim.Adapter.AdminPhimAdapter;
import com.example.appbanvexemphim.Adapter.PhimAdapter;
import com.example.appbanvexemphim.Model.Phim;
import com.example.appbanvexemphim.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagentPhimActivity extends AppCompatActivity {
    private RecyclerView recyclerViewPhim;
    private AdminPhimAdapter phimAdapter;
    private Button btnThem;
    private Button btnBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.managent_phim);
        recyclerViewPhim = findViewById(R.id.rvMovies);
        recyclerViewPhim.setLayoutManager(new LinearLayoutManager(this));
        btnThem = findViewById(R.id.btnAddMovie);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagentPhimActivity.this, AdminDashboardActivity.class);
                startActivity(intent);
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagentPhimActivity.this, ThemPhimActivity.class);
                startActivity(intent);
            }
        });

        ApiPhimService.phimService.getAllPhim().enqueue(new Callback<List<Phim>>() {
            @Override
            public void onResponse(Call<List<Phim>> call, Response<List<Phim>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Phim> listPhim = response.body();
                    phimAdapter = new AdminPhimAdapter(listPhim, ManagentPhimActivity.this);
                    recyclerViewPhim.setAdapter(phimAdapter);
                } else {
                    Toast.makeText(ManagentPhimActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Phim>> call, Throwable t) {
                Toast.makeText(ManagentPhimActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("MainActivity", "onFailure: ", t);
            }
        });
    }
}
