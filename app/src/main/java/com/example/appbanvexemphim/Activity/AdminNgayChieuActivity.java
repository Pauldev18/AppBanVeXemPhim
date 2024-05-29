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
import com.example.appbanvexemphim.Adapter.AdminNgayChieuAdapter;
import com.example.appbanvexemphim.Adapter.AdminPhimAdapter;
import com.example.appbanvexemphim.Model.AdminNgayChieu;
import com.example.appbanvexemphim.Model.Phim;
import com.example.appbanvexemphim.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminNgayChieuActivity extends AppCompatActivity {
    private RecyclerView rcNgayChieu;
    private AdminNgayChieuAdapter adminNgayChieuAdapter;
    private Button btnAddNgayChieu;
    private Button btnBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_ngaychieu);
        rcNgayChieu = findViewById(R.id.rcNgayChieu);
        rcNgayChieu.setLayoutManager(new LinearLayoutManager(this));
        btnAddNgayChieu = findViewById(R.id.btnAddNgayChieu);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminNgayChieuActivity.this, AdminDashboardActivity.class);
                startActivity(intent);
            }
        });


        ApiPhimService.phimService.getAllNgayChieu().enqueue(new Callback<List<AdminNgayChieu>>() {
            @Override
            public void onResponse(Call<List<AdminNgayChieu>> call, Response<List<AdminNgayChieu>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<AdminNgayChieu> listPhim = response.body();
                    adminNgayChieuAdapter = new AdminNgayChieuAdapter(listPhim, AdminNgayChieuActivity.this);
                    rcNgayChieu.setAdapter(adminNgayChieuAdapter);
                } else {
                    Toast.makeText(AdminNgayChieuActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<AdminNgayChieu>> call, Throwable t) {
                Toast.makeText(AdminNgayChieuActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("MainActivity", "onFailure: ", t);
            }
        });

        btnAddNgayChieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminNgayChieuActivity.this, ThemNgayChieuActivity.class);
                startActivity(intent);
            }
        });
    }

}
