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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanvexemphim.API.ApiPhimService;
import com.example.appbanvexemphim.Adapter.ChoNgoiAdapter;
import com.example.appbanvexemphim.Adapter.NgayChieuAdapter;
import com.example.appbanvexemphim.Model.ChoNgoi;
import com.example.appbanvexemphim.Model.DatCho;
import com.example.appbanvexemphim.Model.NgayChieu;
import com.example.appbanvexemphim.R;
import com.example.appbanvexemphim.Singleton.ChooseSeat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChonVeActivity extends AppCompatActivity {
    private static final String TAG = "ChonVeActivity";

    private List<ChoNgoi> listChoNgoi;
    private ChoNgoiAdapter choNgoiAdapter;
    private RecyclerView rv_choNgoi;
    private Button btnDatVe;
    private int movieID;
    private int NgayChieuID;
    private int TinhID;
    private int RapID;
    private int DiaDiemID;
    private int GioChieuID;
    private TextView price;
    private TextView movieTitle;
    private TextView movieGenre;
    private TextView movieDirector;
    private TextView movieCast;
    private TextView cinemaName;
    private TextView cinemaLocation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chonve);
        btnDatVe = findViewById(R.id.book_button);
        rv_choNgoi = findViewById(R.id.seat_grid);
        price = findViewById(R.id.textView2);
        movieTitle = findViewById(R.id.movie_title);
        movieGenre = findViewById(R.id.movie_genre);
        movieDirector = findViewById(R.id.movie_director);
        movieCast = findViewById(R.id.movie_cast);
        cinemaName = findViewById(R.id.cinema_name);
        cinemaLocation = findViewById(R.id.cinema_location);
        rv_choNgoi.setLayoutManager(new GridLayoutManager(this, 10));

        movieTitle.setText("Tên phim: " + ChooseSeat.getInstance().getTenPhim());
        movieGenre.setText("Thể loại: " + ChooseSeat.getInstance().getMovieGenre());
        movieDirector.setText("Đạo diễn: " + ChooseSeat.getInstance().getMovieDirector());
        movieCast.setText("Diễn viên: " + ChooseSeat.getInstance().getMovieCast());
        cinemaName.setText("Loại rạp: " + ChooseSeat.getInstance().getTenRap());
        cinemaLocation.setText("Địa điểm: " + ChooseSeat.getInstance().getPhongChieu());
        // Retrieve the data from the Intent
        if (getIntent().hasExtra("listChoNgoi")) {
             movieID = getIntent().getIntExtra("movieID", -1);  // Handle potential missing value
             NgayChieuID = getIntent().getIntExtra("NgayChieuID", -1);
             TinhID = getIntent().getIntExtra("TinhID", -1);
             RapID = getIntent().getIntExtra("RapID", -1);
             DiaDiemID = getIntent().getIntExtra("DiaDiemID", -1);
             GioChieuID = getIntent().getIntExtra("GioChieuID", -1);
             listChoNgoi = getIntent().getParcelableArrayListExtra("listChoNgoi");
            if (listChoNgoi != null) {
                setupRecyclerView();
            } else {

            }
        } else {
            // Handle the case where the data is not present in the Intent
        }

        btnDatVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String logMessage = "Selected seat: " + ChooseSeat.getInstance().getSelectedSeat();
                logMessage += "\nMovie ID: " + movieID;
                logMessage += "\nNgay Chieu ID: " + NgayChieuID;
                logMessage += "\nTinh ID: " + TinhID;
                logMessage += "\nRap ID: " + RapID;
                logMessage += "\nDia Diem ID: " + DiaDiemID;
                logMessage += "\nGio Chieu ID: " + GioChieuID;
                Log.d(TAG, logMessage);  // Log message
                ApiPhimService.phimService.getIDDatCho(NgayChieuID, movieID, TinhID, RapID,
                        DiaDiemID, GioChieuID, ChooseSeat.getInstance().getSelectedSeat()).enqueue(new Callback<List<DatCho>>() {
                    @Override
                    public void onResponse(Call<List<DatCho>> call, Response<List<DatCho>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            List<DatCho> datcho = response.body();
                            Intent intent = new Intent(ChonVeActivity.this, ConfirmActivity.class);
                            intent.putExtra("IDDatCho", datcho.get(0).getIddatCho());
                            // Khởi động ConfirmActivity
                            startActivity(intent);

                        } else {
                            Toast.makeText(ChonVeActivity.this, "Đặt vé thất bại, vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<DatCho>> call, Throwable t) {
                        Toast.makeText(ChonVeActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("MainActivity", "onFailure: ", t);
                    }
                });

            }
        });
    }

    private void setupRecyclerView() {
        choNgoiAdapter = new ChoNgoiAdapter(this, listChoNgoi);
        rv_choNgoi.setAdapter(choNgoiAdapter);

    }
    public void setTongtien(float tt) {
        price.setText("" + tt);
    }
}

