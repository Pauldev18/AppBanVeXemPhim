// src/main/java/com/example/appbanvexemphim/Activity/ThemPhimActivity.java
package com.example.appbanvexemphim.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.appbanvexemphim.API.ApiPhimService;
import com.example.appbanvexemphim.Adapter.TinhAdapter;
import com.example.appbanvexemphim.Model.RealPathUtil;
import com.example.appbanvexemphim.Model.Tinh;
import com.example.appbanvexemphim.R;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThemPhimActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private EditText etTenPhim, etTheLoai, etThoiLuong, etKhoiChieu, etDaoDien, etDienVien, etNgonNgu, etDanhGia, etNoiDung;
    private Button btnChonAnh, btnThemPhim;
    private ImageView imageViewPreview;
    private Uri imageUri;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_phim);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Đang thêm...");
        progressDialog.setCancelable(false);

        etTenPhim = findViewById(R.id.etTenPhim);
        etTheLoai = findViewById(R.id.etTheLoai);
        etThoiLuong = findViewById(R.id.etThoiLuong);
        etKhoiChieu = findViewById(R.id.etKhoiChieu);
        etDaoDien = findViewById(R.id.etDaoDien);
        etDienVien = findViewById(R.id.etDienVien);
        etNgonNgu = findViewById(R.id.etNgonNgu);
        etDanhGia = findViewById(R.id.etDanhGia);
        etNoiDung = findViewById(R.id.etNoiDung);
        btnChonAnh = findViewById(R.id.btnChonAnh);
        btnThemPhim = findViewById(R.id.btnThemPhim);
        imageViewPreview = findViewById(R.id.imageViewPreview);

        btnChonAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý chọn ảnh
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });
        // Xử lý sau khi chọn ảnh từ thư viện


        btnThemPhim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu từ các EditText
                String tenPhim = etTenPhim.getText().toString();
                String theLoai = etTheLoai.getText().toString();
                String thoiLuong = etThoiLuong.getText().toString();
                String khoiChieu = etKhoiChieu.getText().toString();
                String daoDien = etDaoDien.getText().toString();
                String dienVien = etDienVien.getText().toString();
                String ngonNgu = etNgonNgu.getText().toString();
                String danhGia = etDanhGia.getText().toString();
                String noiDung = etNoiDung.getText().toString();

                // Kiểm tra dữ liệu nhập vào (có thể thêm các điều kiện kiểm tra khác)
                if (tenPhim.isEmpty() || theLoai.isEmpty() || thoiLuong.isEmpty() || khoiChieu.isEmpty() || daoDien.isEmpty() || dienVien.isEmpty() || ngonNgu.isEmpty() || danhGia.isEmpty() || noiDung.isEmpty()) {
                    Toast.makeText(ThemPhimActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    uploadFilmToServer(tenPhim, imageUri, theLoai, thoiLuong, khoiChieu, daoDien, dienVien, ngonNgu, danhGia, noiDung);
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();

            // Hiển thị ảnh đã chọn trong ImageView
            Glide.with(this)
                    .load(imageUri)
                    .apply(new RequestOptions().override(300, 300)) // Có thể điều chỉnh kích thước ảnh
                    .into(imageViewPreview);
        }
    }
    private void uploadFilmToServer(String tenPhim, Uri imageUri, String theLoai, String thoiLuong, String khoiChieu, String daoDien, String dienVien, String ngonNgu, String danhGia, String noiDung) {

        if (imageUri != null) {
            String realPath;
                realPath = RealPathUtil.getRealPathFromURI_API19(this, imageUri);
            if (realPath != null && !realPath.isEmpty()) {
                File file = new File(realPath);
                RequestBody requestFile = RequestBody.create(MediaType.parse(getContentResolver().getType(imageUri)), file);
                MultipartBody.Part imagePart = MultipartBody.Part.createFormData("anhPhim", file.getName(), requestFile);
                progressDialog.show();
                ApiPhimService.phimService.upFilm(tenPhim, imagePart, theLoai, thoiLuong, khoiChieu, daoDien, dienVien, ngonNgu, danhGia, noiDung)
                        .enqueue(new Callback<Object>() {
                            @Override
                            public void onResponse(Call<Object> call, Response<Object> response) {
                                progressDialog.dismiss();
                                if (response.isSuccessful() && response.body() != null) {
                                    Intent intent = new Intent(ThemPhimActivity.this, ManagentPhimActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(ThemPhimActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Object> call, Throwable t) {
                                progressDialog.dismiss();
                                Toast.makeText(ThemPhimActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.e("MainActivity", "onFailure: ", t);
                            }
                        });
            } else {
                progressDialog.dismiss();
                Toast.makeText(ThemPhimActivity.this, "Invalid file path", Toast.LENGTH_SHORT).show();
            }
        } else {
            progressDialog.dismiss();
            Toast.makeText(ThemPhimActivity.this, "Image URI is null", Toast.LENGTH_SHORT).show();
        }
    }






}
