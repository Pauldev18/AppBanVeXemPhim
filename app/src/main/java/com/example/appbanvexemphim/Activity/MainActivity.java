package com.example.appbanvexemphim.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanvexemphim.API.ApiPhimService;
import com.example.appbanvexemphim.Adapter.PhimAdapter;
import com.example.appbanvexemphim.Model.Phim;
import com.example.appbanvexemphim.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPhim;
    private PhimAdapter phimAdapter;
    private Button btnLs;
    private SearchView txt_tim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewPhim = findViewById(R.id.recycler_view);
        recyclerViewPhim.setLayoutManager(new LinearLayoutManager(this));
        btnLs = findViewById(R.id.buttonMyMovies);

        txt_tim = findViewById(R.id.search_phim);



        btnLs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LichSuDatVeActivity.class);
                startActivity(intent);
            }
        });

        fetchPhimData();
        txt_tim.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                phimAdapter.filterList(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Khi người dùng thay đổi văn bản trong SearchView, thực hiện tìm kiếm và cập nhật RecyclerView
                phimAdapter.filterList(newText);
                return false;
            }
        });

    }





    private void fetchPhimData() {
        ApiPhimService.phimService.getAllPhim().enqueue(new Callback<List<Phim>>() {
            @Override
            public void onResponse(Call<List<Phim>> call, Response<List<Phim>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Phim> listPhim = response.body();
                    phimAdapter = new PhimAdapter(listPhim, MainActivity.this);
                    recyclerViewPhim.setAdapter(phimAdapter);
                } else {
                    Toast.makeText(MainActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Phim>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("MainActivity", "onFailure: ", t);
            }
        });
    }
}