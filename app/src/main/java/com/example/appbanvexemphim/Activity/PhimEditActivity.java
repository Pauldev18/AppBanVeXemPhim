package com.example.appbanvexemphim.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appbanvexemphim.Adapter.PhimEditAdapter;
import com.example.appbanvexemphim.Model.Phim;
import com.example.appbanvexemphim.R;

public class PhimEditActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_phim);
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        Phim detailPhim = (Phim) bundle.get("objPhimEdit");
        ImageView imageView = findViewById(R.id.img_phimedit);
        EditText textView = findViewById(R.id.txt_tenPhimEdit);
        textView.setText(detailPhim.getTenPhim());

        Glide.with(this)
                .load(detailPhim.getAnhPhim()) // Đường dẫn ảnh từ đối tượng Phim
                .placeholder(R.drawable.ic_launcher_background) // Ảnh placeholder nếu không tải được ảnh
                .error(R.drawable.ic_launcher_background) // Ảnh hiển thị khi có lỗi
                .into(imageView);
        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenPhim = textView.getText().toString();
                Log.d("ok", tenPhim);
                Toast.makeText(PhimEditActivity.this, tenPhim, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
