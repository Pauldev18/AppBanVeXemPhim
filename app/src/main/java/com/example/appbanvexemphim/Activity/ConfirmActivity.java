package com.example.appbanvexemphim.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appbanvexemphim.API.ApiPhimService;
import com.example.appbanvexemphim.Model.Url;
import com.example.appbanvexemphim.R;
import com.example.appbanvexemphim.Singleton.ChooseSeat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmActivity extends AppCompatActivity {
    private TextView txtTenPhim;
    private TextView txtSuatChieu;
    private TextView txtTenRap;
    private TextView txtPhongChieu;
    private TextView txtGhe;
    private TextView txtPrice;
    private TextView txtTongTien;
    private int IDDatCho;
    private Button btnPayment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm);
        txtTenPhim = findViewById(R.id.txtTenPhim);
        txtSuatChieu = findViewById(R.id.txtSuatChieu);
        txtTenRap = findViewById(R.id.txtTenRap);
        txtPhongChieu = findViewById(R.id.txtPhongChieu);
        txtGhe = findViewById(R.id.txtGhe);
        txtPrice = findViewById(R.id.txtGiaVe);
        txtTongTien = findViewById(R.id.txtTongTien);
        btnPayment = findViewById(R.id.btnPayment);

        IDDatCho = getIntent().getIntExtra("IDDatCho", -1);

        txtTenPhim.setText(ChooseSeat.getInstance().getTenPhim());
        txtSuatChieu.setText(ChooseSeat.getInstance().getNgayChieu() + " - " + ChooseSeat.getInstance().getGioChieu());
        txtTenRap.setText(ChooseSeat.getInstance().getTenRap());
        txtPhongChieu.setText(ChooseSeat.getInstance().getPhongChieu());
        txtGhe.setText(ChooseSeat.getInstance().getTenGhe());
        txtPrice.setText(String.valueOf(ChooseSeat.getInstance().getPrice()));
        txtTongTien.setText(String.valueOf(ChooseSeat.getInstance().getPrice()));

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiPhimService.phimService.getUrl((long)ChooseSeat.getInstance().getPrice(), IDDatCho, ChooseSeat.getInstance().getUserID()).enqueue(new Callback<Url>() {
                    @Override
                    public void onResponse(Call<Url> call, Response<Url> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Url responseBody = response.body();
                            Log.d("ConfirmActivity", "Response Body: " + responseBody);
                            String url = responseBody.getUrl();
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(intent);
                        } else {
                            Toast.makeText(ConfirmActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Url> call, Throwable t) {
                        Toast.makeText(ConfirmActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("ConfirmActivity", "onFailure: ", t);
                    }
                });
            }
        });
    }
    private String extractUrlFromResponse(String responseBody) {
        String url = null;
        // Define a regex pattern to match URLs
        Pattern pattern = Pattern.compile("https?://\\S+");
        Matcher matcher = pattern.matcher(responseBody);
        // Find the first URL match
        if (matcher.find()) {
            url = matcher.group();
        }
        return url;
    }
}
