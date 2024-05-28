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
import com.example.appbanvexemphim.Adapter.AdminTinhAdapter;
import com.example.appbanvexemphim.Model.AdminDiaDiem;
import com.example.appbanvexemphim.Model.AdminTinh;
import com.example.appbanvexemphim.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminTinhActivity extends AppCompatActivity {
    private RecyclerView rcTinh;
    private AdminTinhAdapter adminTinhAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.managent_tinh);
        rcTinh = findViewById(R.id.rcTinh);
        rcTinh.setLayoutManager(new LinearLayoutManager(this));

        ApiPhimService.phimService.getAllTinh().enqueue(new Callback<List<AdminTinh>>() {
            @Override
            public void onResponse(Call<List<AdminTinh>> call, Response<List<AdminTinh>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<AdminTinh> listPhim = response.body();
                    adminTinhAdapter = new AdminTinhAdapter(listPhim, AdminTinhActivity.this);
                    rcTinh.setAdapter(adminTinhAdapter);
                } else {
                    Toast.makeText(AdminTinhActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<AdminTinh>> call, Throwable t) {
                Toast.makeText(AdminTinhActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("MainActivity", "onFailure: ", t);
            }
        });
    }
}
