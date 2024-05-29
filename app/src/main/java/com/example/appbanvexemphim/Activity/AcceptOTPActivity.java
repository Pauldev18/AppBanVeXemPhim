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

public class AcceptOTPActivity extends AppCompatActivity {
    private TextView txtOTP;
    private Button btnAccpet;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accept_otp);
        txtOTP = findViewById(R.id.txtOTP);
        btnAccpet = findViewById(R.id.buttonSendOTP);
        String email = getIntent().getStringExtra("email");
        btnAccpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiPhimService.phimService.validateOtp(email, Integer.parseInt(txtOTP.getText().toString())).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Intent intent = new Intent(AcceptOTPActivity.this, NewPassActivity.class);
                            intent.putExtra("email", email);
                            startActivity(intent);
                        } else {
                            Toast.makeText(AcceptOTPActivity.this, "Sai mã OTP", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(AcceptOTPActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("ForgotPassActivity", "onFailure: ", t);
                    }
                });
            }
        });
    }
}
