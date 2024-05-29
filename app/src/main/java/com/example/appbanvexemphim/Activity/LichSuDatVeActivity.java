package com.example.appbanvexemphim.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanvexemphim.API.ApiPhimService;
import com.example.appbanvexemphim.Adapter.NgayChieuAdapter;
import com.example.appbanvexemphim.Adapter.VePhimAdapter;
import com.example.appbanvexemphim.Model.LichSuDatVe;
import com.example.appbanvexemphim.Model.NgayChieu;
import com.example.appbanvexemphim.R;
import com.example.appbanvexemphim.Singleton.ChooseSeat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LichSuDatVeActivity extends AppCompatActivity {
    private RecyclerView rcLS;
    private VePhimAdapter vePhimAdapter;
    private Button btnLogout;
    private Button btnHome;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ds_ve);
        rcLS = findViewById(R.id.recycler_view_tickets);
        rcLS.setLayoutManager(new LinearLayoutManager(this));
        btnLogout = findViewById(R.id.button_logout);
        btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LichSuDatVeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        ApiPhimService.phimService.getLichSuDatVe(ChooseSeat.getInstance().getUserID()).enqueue(new Callback<List<LichSuDatVe>>() {
            @Override
            public void onResponse(Call<List<LichSuDatVe>> call, Response<List<LichSuDatVe>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<LichSuDatVe> nc = response.body();
                    vePhimAdapter = new VePhimAdapter(nc, LichSuDatVeActivity.this);
                    rcLS.setAdapter(vePhimAdapter);
                } else {
                    Toast.makeText(LichSuDatVeActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<LichSuDatVe>> call, Throwable t) {
                Toast.makeText(LichSuDatVeActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("MainActivity", "onFailure: ", t);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LichSuDatVeActivity.this, LoginActivity.class);

                // Xóa tất cả các hoạt động trước đó khỏi ngăn xếp hoạt động
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                ChooseSeat.getInstance().setUserID(-1);

                startActivity(intent);
                finishAffinity();
            }
        });
    }

}
