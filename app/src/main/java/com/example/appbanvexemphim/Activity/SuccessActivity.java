package com.example.appbanvexemphim.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appbanvexemphim.R;

public class SuccessActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_success);
        long transactionId = getIntent().getLongExtra("transactionId", -1);
        Button btnSuccess = findViewById(R.id.btnSuccess);
        btnSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuccessActivity.this, LichSuDatVeActivity.class);
                startActivity(intent);
            }
        });
        // Hiển thị thông báo thanh toán thành công
        TextView textView = findViewById(R.id.success_title);
        textView.setText("Thanh toán thành công");
    }
}
