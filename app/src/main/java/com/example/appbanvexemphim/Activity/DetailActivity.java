package com.example.appbanvexemphim.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.appbanvexemphim.Model.Phim;
import com.example.appbanvexemphim.R;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phim_detail);
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        Phim detailPhim = (Phim) bundle.get("objPhim");
        ImageView imageView = findViewById(R.id.img_header);
        TextView tenPhim = findViewById(R.id.txt_title);
        TextView moTa = findViewById(R.id.txt_description);
        TextView theLoai = findViewById(R.id.txt_classification);
        TextView thoiLuong = findViewById(R.id.txt_format);
        TextView daoDien = findViewById(R.id.txt_director);
        TextView dienVien = findViewById(R.id.txt_cast);
        TextView khoiChieu = findViewById(R.id.txt_release_date);
        TextView ngonNgu = findViewById(R.id.txt_language);
        Button btnDatVe = findViewById(R.id.btn_buy_ticket);

        tenPhim.setText(detailPhim.getTenPhim());
        Glide.with(this)
                .load(detailPhim.getAnhPhim())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(imageView);
        moTa.setText("Mô tả: " + detailPhim.getNoiDung());
        theLoai.setText("Thể loại: " + detailPhim.getTheLoai());
        thoiLuong.setText("Thời lượng: " + detailPhim.getThoiLuong());
        daoDien.setText("Đạo diễn: " + detailPhim.getDaoDien());
        dienVien.setText("Diễn viên: " + detailPhim.getDienVien());
        khoiChieu.setText("Khởi chiếu: " + detailPhim.getKhoiChieu());
        ngonNgu.setText("Ngôn ngữ: " + detailPhim.getNgonNgu());

        btnDatVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, BookingActivity.class);
                intent.putExtra("MOVIE_ID", detailPhim.getId());
                startActivity(intent);
            }
        });

    }
}
