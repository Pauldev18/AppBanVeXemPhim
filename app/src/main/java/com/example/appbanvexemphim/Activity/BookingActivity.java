package com.example.appbanvexemphim.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanvexemphim.API.ApiPhimService;
import com.example.appbanvexemphim.Adapter.NgayChieuAdapter;
import com.example.appbanvexemphim.Adapter.PhimAdapter;
import com.example.appbanvexemphim.Adapter.RapAdapter;
import com.example.appbanvexemphim.Adapter.ShowtimesAdapter;
import com.example.appbanvexemphim.Adapter.TinhAdapter;
import com.example.appbanvexemphim.Model.DiaDiemAndGioChieu;
import com.example.appbanvexemphim.Model.NgayChieu;
import com.example.appbanvexemphim.Model.Phim;
import com.example.appbanvexemphim.Model.Rap;
import com.example.appbanvexemphim.Model.Tinh;
import com.example.appbanvexemphim.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingActivity extends AppCompatActivity {
    private RecyclerView rv_dates;
    private RecyclerView rv_tinh;
    private RecyclerView rv_rap;
    private RecyclerView rv_giochieu;
    private NgayChieuAdapter ngayChieuAdapter;
    private TinhAdapter tinhAdapter;
    private RapAdapter rapAdapter;
    private ShowtimesAdapter showtimesAdapter;
    private int movieID;
    private int NgayChieuID;
    private int TinhID;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        rv_dates = findViewById(R.id.rv_dates);
        rv_dates.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rv_tinh = findViewById(R.id.rv_provinces);
        rv_tinh.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        rv_rap = findViewById(R.id.rv_rap);
        rv_rap.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        rv_giochieu = findViewById(R.id.rv_giochieu);
        rv_giochieu.setLayoutManager(new LinearLayoutManager(this));
        movieID = getIntent().getIntExtra("MOVIE_ID", -1);
        fetchPhimData();
    }

    private void fetchPhimData() {
        ApiPhimService.phimService.getData(String.valueOf(movieID)).enqueue(new Callback<List<NgayChieu>>() {
            @Override
            public void onResponse(Call<List<NgayChieu>> call, Response<List<NgayChieu>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<NgayChieu> nc = response.body();
                    ngayChieuAdapter = new NgayChieuAdapter(nc, BookingActivity.this);
                    rv_dates.setAdapter(ngayChieuAdapter);
                } else {
                    Toast.makeText(BookingActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<NgayChieu>> call, Throwable t) {
                Toast.makeText(BookingActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("MainActivity", "onFailure: ", t);
            }
        });
    }

    public void fetchDataTinh(int idNgayChieu) {
        ApiPhimService.phimService.getTinh(idNgayChieu, movieID).enqueue(new Callback<List<Tinh>>() {
            @Override
            public void onResponse(Call<List<Tinh>> call, Response<List<Tinh>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Tinh> t = response.body();
                    tinhAdapter = new TinhAdapter(t, BookingActivity.this);
                    rv_tinh.setAdapter(tinhAdapter);
                    NgayChieuID = idNgayChieu;
                } else {
                    Toast.makeText(BookingActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Tinh>> call, Throwable t) {
                Toast.makeText(BookingActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("MainActivity", "onFailure: ", t);
            }
        });
    }
    public void fetchDataRap(int idTinh) {
        ApiPhimService.phimService.getRap(NgayChieuID, movieID, idTinh).enqueue(new Callback<List<Rap>>() {
            @Override
            public void onResponse(Call<List<Rap>> call, Response<List<Rap>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Rap> rapList = response.body();
                    rapAdapter = new RapAdapter(rapList, BookingActivity.this);
                    rv_rap.setAdapter(rapAdapter);
                    TinhID = idTinh;
                } else {
                    Toast.makeText(BookingActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Rap>> call, Throwable t) {
                Toast.makeText(BookingActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("MainActivity", "onFailure: ", t);
            }
        });
    }

    public void fetchDataDiaDiemAndChoNgoi(int idRap) {
        ApiPhimService.phimService.geDiaDiemAndGioChieu(NgayChieuID, movieID, TinhID, idRap).enqueue(new Callback<List<DiaDiemAndGioChieu>>() {
            @Override
            public void onResponse(Call<List<DiaDiemAndGioChieu>> call, Response<List<DiaDiemAndGioChieu>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<DiaDiemAndGioChieu> diaDiemAndGioChieus = response.body();
                    showtimesAdapter = new ShowtimesAdapter(BookingActivity.this, diaDiemAndGioChieus);
                    rv_giochieu.setAdapter(showtimesAdapter);
                } else {
                    Toast.makeText(BookingActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DiaDiemAndGioChieu>> call, Throwable t) {
                Toast.makeText(BookingActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("MainActivity", "onFailure: ", t);
            }
        });
    }

}
