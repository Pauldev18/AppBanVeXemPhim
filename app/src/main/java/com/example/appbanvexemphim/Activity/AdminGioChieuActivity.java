package com.example.appbanvexemphim.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanvexemphim.API.ApiPhimService;
import com.example.appbanvexemphim.Adapter.AdminDiaDiemAdapter;
import com.example.appbanvexemphim.Adapter.AdminGioChieuAdapter;
import com.example.appbanvexemphim.Model.AdminDiaDiem;
import com.example.appbanvexemphim.Model.AdminGioChieu;
import com.example.appbanvexemphim.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminGioChieuActivity extends AppCompatActivity {
    private RecyclerView rcGioChieu;
    private AdminGioChieuAdapter adminGioChieuAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.managent_giochieu);
        rcGioChieu = findViewById(R.id.rcGioChieu);
        rcGioChieu.setLayoutManager(new LinearLayoutManager(this));
        ApiPhimService.phimService.getAllGioChieu().enqueue(new Callback<List<AdminGioChieu>>() {
            @Override
            public void onResponse(Call<List<AdminGioChieu>> call, Response<List<AdminGioChieu>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<AdminGioChieu> listPhim = response.body();
                    adminGioChieuAdapter = new AdminGioChieuAdapter(listPhim, AdminGioChieuActivity.this);
                    rcGioChieu.setAdapter(adminGioChieuAdapter);
                } else {
                    Toast.makeText(AdminGioChieuActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<AdminGioChieu>> call, Throwable t) {
                Toast.makeText(AdminGioChieuActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("MainActivity", "onFailure: ", t);
            }
        });
    }
}
