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
import com.example.appbanvexemphim.Adapter.AdminGioChieuAdapter;
import com.example.appbanvexemphim.Adapter.AdminLoaiRapAdapter;
import com.example.appbanvexemphim.Model.AdminGioChieu;
import com.example.appbanvexemphim.Model.AdminLoaiRap;
import com.example.appbanvexemphim.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminLoaiRapActivity extends AppCompatActivity {
    private RecyclerView rcLoaiRap;
    private AdminLoaiRapAdapter adminLoaiRapAdapter;
    private Button btnAddLoaiRap;
    private Button btnBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.managent_loairap);
        rcLoaiRap = findViewById(R.id.rcLoaiRap);
        rcLoaiRap.setLayoutManager(new LinearLayoutManager(this));
        btnAddLoaiRap = findViewById(R.id.btnAddLoaiRap);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLoaiRapActivity.this, AdminDashboardActivity.class);
                startActivity(intent);
            }
        });
        btnAddLoaiRap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(AdminLoaiRapActivity.this, ThemLoaiRapActivity.class);
                startActivity(intent);
            }
        });
        ApiPhimService.phimService.getAllLoaiRap().enqueue(new Callback<List<AdminLoaiRap>>() {
            @Override
            public void onResponse(Call<List<AdminLoaiRap>> call, Response<List<AdminLoaiRap>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<AdminLoaiRap> listPhim = response.body();
                    adminLoaiRapAdapter =  new AdminLoaiRapAdapter(listPhim, AdminLoaiRapActivity.this);
                    rcLoaiRap.setAdapter(adminLoaiRapAdapter);
                } else {
                    Toast.makeText(AdminLoaiRapActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<AdminLoaiRap>> call, Throwable t) {
                Toast.makeText(AdminLoaiRapActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("MainActivity", "onFailure: ", t);
            }
        });
    }
}
