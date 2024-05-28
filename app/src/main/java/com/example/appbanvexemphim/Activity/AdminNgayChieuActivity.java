package com.example.appbanvexemphim.Activity;

import android.os.Bundle;
import android.util.Log;
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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_ngaychieu);
        rcNgayChieu = findViewById(R.id.rcNgayChieu);
        rcNgayChieu.setLayoutManager(new LinearLayoutManager(this));

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

    }
}
