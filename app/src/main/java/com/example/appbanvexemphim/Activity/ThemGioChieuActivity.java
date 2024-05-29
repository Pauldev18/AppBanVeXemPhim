package com.example.appbanvexemphim.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appbanvexemphim.API.ApiPhimService;
import com.example.appbanvexemphim.R;

import java.sql.Time;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThemGioChieuActivity extends AppCompatActivity {
    private Button btnSaveGioChieu;
    private TextView txtGioChieu;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_giochieu);
        btnSaveGioChieu = findViewById(R.id.btnSaveGioChieu);
        txtGioChieu = findViewById(R.id.txt_addGioChieu);
        btnSaveGioChieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiPhimService.phimService.createGioChieu(Time.valueOf(txtGioChieu.getText().toString())).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(ThemGioChieuActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ThemGioChieuActivity.this, AdminGioChieuActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(ThemGioChieuActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(ThemGioChieuActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
