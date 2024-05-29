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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThemLoaiRapActivity extends AppCompatActivity {
     private Button btnSaveLoaiRap;
     private TextView txtLoaiRap;
     private TextView txtSoTien;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_loairap);
        btnSaveLoaiRap = findViewById(R.id.btnSaveLoaiRap);
        txtLoaiRap = findViewById(R.id.txt_addLoaiRap);
        txtSoTien = findViewById(R.id.txt_addSoTien);
        btnSaveLoaiRap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiPhimService.phimService.createLoaiRap(txtLoaiRap.getText().toString(), Float.parseFloat(txtSoTien.getText().toString())).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(ThemLoaiRapActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ThemLoaiRapActivity.this, AdminLoaiRapActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(ThemLoaiRapActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(ThemLoaiRapActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
