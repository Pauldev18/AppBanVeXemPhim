package com.example.appbanvexemphim.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class NewPassActivity extends AppCompatActivity {
    private TextView txtNewPass;
    private Button btnNewPass;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_pass);
        txtNewPass = findViewById(R.id.txtNewPass);
        btnNewPass = findViewById(R.id.btnNewPass);
        String email = getIntent().getStringExtra("email");
        btnNewPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiPhimService.phimService.newPass(email, txtNewPass.getText().toString()).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Intent intent = new Intent(NewPassActivity.this, LoginActivity.class);
                            startActivity(intent);
                            Toast.makeText(NewPassActivity.this, "Cập nhật mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(NewPassActivity.this, "Loi", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(NewPassActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("ForgotPassActivity", "onFailure: ", t);
                    }
                });
            }
        });
    }
}
