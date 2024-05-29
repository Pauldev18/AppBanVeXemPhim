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

public class ThemDiaDiemActivity extends AppCompatActivity {
    private Button btnSaveDiaDiem;
    private TextView txtDiaDiem;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_diadiem);
        txtDiaDiem = findViewById(R.id.txt_addDiaDiem);
        btnSaveDiaDiem = findViewById(R.id.btnSaveDiaDiem);
        btnSaveDiaDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiPhimService.phimService.createDiaDiem(txtDiaDiem.getText().toString()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(ThemDiaDiemActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ThemDiaDiemActivity.this, AdminDiaDiemActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(ThemDiaDiemActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(ThemDiaDiemActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
