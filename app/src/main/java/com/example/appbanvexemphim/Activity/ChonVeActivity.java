package com.example.appbanvexemphim.Activity;

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

import com.example.appbanvexemphim.Adapter.ChoNgoiAdapter;
import com.example.appbanvexemphim.Model.ChoNgoi;
import com.example.appbanvexemphim.R;
import com.example.appbanvexemphim.Singleton.ChooseSeat;

import java.util.List;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chonve);
        btnDatVe = findViewById(R.id.book_button);
        rv_choNgoi = findViewById(R.id.seat_grid);
        price = findViewById(R.id.textView2);
        rv_choNgoi.setLayoutManager(new GridLayoutManager(this, 10));

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

