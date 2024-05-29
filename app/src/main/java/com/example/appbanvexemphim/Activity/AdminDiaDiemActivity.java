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
import com.example.appbanvexemphim.Adapter.AdminDiaDiemAdapter;
import com.example.appbanvexemphim.Adapter.AdminNgayChieuAdapter;
import com.example.appbanvexemphim.Model.AdminDiaDiem;
import com.example.appbanvexemphim.Model.AdminNgayChieu;
import com.example.appbanvexemphim.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminDiaDiemActivity extends AppCompatActivity {
    private RecyclerView rcDiaDiem;
    private AdminDiaDiemAdapter adminDiaDiemAdapter;
    private Button btnAddDiaDiem;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.managent_diadiem);
        rcDiaDiem = findViewById(R.id.rcDiaDiem);
        rcDiaDiem.setLayoutManager(new LinearLayoutManager(this));
        btnAddDiaDiem = findViewById(R.id.btnAddDiaDiem);
        btnAddDiaDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDiaDiemActivity.this, ThemDiaDiemActivity.class);
                startActivity(intent);
            }
        });
        ApiPhimService.phimService.getAllDiaDiem().enqueue(new Callback<List<AdminDiaDiem>>() {
            @Override
            public void onResponse(Call<List<AdminDiaDiem>> call, Response<List<AdminDiaDiem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<AdminDiaDiem> listPhim = response.body();
                    adminDiaDiemAdapter = new AdminDiaDiemAdapter(listPhim, AdminDiaDiemActivity.this);
                    rcDiaDiem.setAdapter(adminDiaDiemAdapter);

                } else {
                    Toast.makeText(AdminDiaDiemActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<AdminDiaDiem>> call, Throwable t) {
                Toast.makeText(AdminDiaDiemActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("MainActivity", "onFailure: ", t);
            }
        });
    }
}
