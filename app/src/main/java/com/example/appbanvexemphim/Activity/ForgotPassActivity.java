package com.example.appbanvexemphim.Activity;

import android.app.ProgressDialog;
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

public class ForgotPassActivity extends AppCompatActivity {
    private TextView txtEmail;
    private Button btnSendOtp;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_pass);

        txtEmail = findViewById(R.id.editTextEmail);
        btnSendOtp = findViewById(R.id.buttonSendOTP);

        // Initialize the ProgressDialog
        progressDialog = new ProgressDialog(ForgotPassActivity.this);
        progressDialog.setMessage("Đang gửi...");
        progressDialog.setCancelable(false);

        btnSendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString().trim();
                if (!email.isEmpty()) {
                    sendOtp(email);
                } else {
                    Toast.makeText(ForgotPassActivity.this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendOtp(String email) {

        progressDialog.show();

        ApiPhimService.phimService.generateOtp(email).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                progressDialog.dismiss();

                if (response.isSuccessful() && response.body() != null) {
                    Intent intent = new Intent(ForgotPassActivity.this, AcceptOTPActivity.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                } else {
                    Toast.makeText(ForgotPassActivity.this, "Không tìm thấy tài khoản", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                progressDialog.dismiss();

                Toast.makeText(ForgotPassActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("ForgotPassActivity", "onFailure: ", t);
            }
        });
    }
}
