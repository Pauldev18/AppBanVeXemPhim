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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThemNgayChieuActivity extends AppCompatActivity {
    private Button btnSave;
    private TextView txtNgayChieu;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_ngaychieu);
        txtNgayChieu = findViewById(R.id.txt_addNgayChieu);
        btnSave = findViewById(R.id.buttonSaveNgayChieu);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ngayChieuStr = txtNgayChieu.getText().toString();

                // 2. Chuyển đổi giá trị ngày chiếu thành định dạng Date
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                Date ngayChieu = null;
                try {
                    ngayChieu = dateFormat.parse(ngayChieuStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                // 3. Gọi API với Retrofit
                if (ngayChieu != null) {
                    ApiPhimService.phimService.createNgayChieu(ngayChieu).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(ThemNgayChieuActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ThemNgayChieuActivity.this, AdminNgayChieuActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(ThemNgayChieuActivity.this, "Lỗi, suất chiếu đã tồn tại", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(ThemNgayChieuActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
