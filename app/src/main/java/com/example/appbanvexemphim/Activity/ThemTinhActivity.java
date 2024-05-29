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

public class ThemTinhActivity extends AppCompatActivity {
    private Button btnSaveTinh;
    private TextView txtTinh;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_tinh);
        btnSaveTinh = findViewById(R.id.buttonSaveTinh);
        txtTinh = findViewById(R.id.txt_addTinh);

        btnSaveTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiPhimService.phimService.createTinh(txtTinh.getText().toString()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(ThemTinhActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ThemTinhActivity.this, AdminTinhActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(ThemTinhActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(ThemTinhActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
