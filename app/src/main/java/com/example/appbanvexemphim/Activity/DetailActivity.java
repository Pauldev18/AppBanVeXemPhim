package com.example.appbanvexemphim.Activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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
        ImageView imageView = findViewById(R.id.txt_anhPhim);
        TextView textView = findViewById(R.id.txt_tenPhim);
        textView.setText(detailPhim.getTenPhim());

        Glide.with(this)
                .load(detailPhim.getAnhPhim()) // Đường dẫn ảnh từ đối tượng Phim
                .placeholder(R.drawable.ic_launcher_background) // Ảnh placeholder nếu không tải được ảnh
                .error(R.drawable.ic_launcher_background) // Ảnh hiển thị khi có lỗi
                .into(imageView);



    }
}
