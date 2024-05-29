package com.example.appbanvexemphim.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appbanvexemphim.API.ApiPhimService;
import com.example.appbanvexemphim.Model.AdminDiaDiem;
import com.example.appbanvexemphim.Model.AdminGioChieu;
import com.example.appbanvexemphim.Model.AdminLoaiRap;
import com.example.appbanvexemphim.Model.AdminNgayChieu;
import com.example.appbanvexemphim.Model.AdminTinh;
import com.example.appbanvexemphim.Model.NewSuatChieu;
import com.example.appbanvexemphim.Model.Phim;
import com.example.appbanvexemphim.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminThemSuatChieuActivity extends AppCompatActivity {
    private List<Phim> phimItems;
    private List<AdminNgayChieu> ngayChieuList;
    private List<AdminTinh> tinhList;
    private List<AdminDiaDiem> diaDiemList;
    private List<AdminGioChieu> gioChieuList;
    private List<AdminLoaiRap> loaiRapList;
    private  Spinner spNgayChieu;
    private Spinner spTinh;
    private Spinner spDiaDiem;
    private Spinner spGioChieu;
    private Spinner spLoaiRap;
    private Button btnAddShow;

    private int IDPhim;
    private int idNgayChieu;
    private int idTinh;
    private int idDiaDiem;
    private int idGioChieu;
    private int idLoaiRap;
    private Button btnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_themsuatchieu);
        Spinner spinnerMovies = findViewById(R.id.spinner_movies);
        spNgayChieu = findViewById(R.id.spNgayChieu);
        spTinh = findViewById(R.id.spTinh);
        spDiaDiem = findViewById(R.id.spDiaDiem);
        spGioChieu = findViewById(R.id.spGioChieu);
        spLoaiRap = findViewById(R.id.spLoaiRap);
        btnAddShow = findViewById(R.id.btnAddShow);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminThemSuatChieuActivity.this, AdminDashboardActivity.class);
                startActivity(intent);
            }
        });
        LoadCbxNgayChieu();
        LoadCbxTinh();
        LoadCbxDiaDiem();
        LoadCbxGioChieu();
        LoadCbxLoaiRap();
        btnAddShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewSuatChieu newSuatChieu = new NewSuatChieu();
                newSuatChieu.setIdGioChieu(idGioChieu);
                newSuatChieu.setIdNgayChieu(idNgayChieu);
                newSuatChieu.setIdDiaDiem(idDiaDiem);
                newSuatChieu.setIdPhim(IDPhim);
                newSuatChieu.setIdTinh(idTinh);
                newSuatChieu.setIdLoaiRap(idLoaiRap);
                ApiPhimService.phimService.createSuatChieu(newSuatChieu).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(AdminThemSuatChieuActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AdminThemSuatChieuActivity.this, "Lỗi, suất chiếu đã tồn tại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(AdminThemSuatChieuActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        ApiPhimService.phimService.getAllPhim().enqueue(new Callback<List<Phim>>() {
            @Override
            public void onResponse(Call<List<Phim>> call, Response<List<Phim>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Phim> listPhim = response.body();
                    phimItems = new ArrayList<>();
                    for (Phim phim : listPhim) {
                        phimItems.add(new Phim(phim.getTenPhim(), phim.getId()));
                    }

                    // Set up the adapter for the Spinner
                    ArrayAdapter<Phim> moviesAdapter = new ArrayAdapter<>(AdminThemSuatChieuActivity.this, android.R.layout.simple_spinner_item, phimItems);
                    moviesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerMovies.setAdapter(moviesAdapter);

                    // Set the item selected listener for the spinner
                    spinnerMovies.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Phim selectedPhim = (Phim) parent.getItemAtPosition(position);
                            int selectedPhimId = selectedPhim.getId();
                            IDPhim = selectedPhimId;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Do nothing
                        }
                    });
                } else {
                    Toast.makeText(AdminThemSuatChieuActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Phim>> call, Throwable t) {
                Toast.makeText(AdminThemSuatChieuActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("AdminThemSuatChieuActivity", "onFailure: ", t);
            }
        });
    }

    public static String convertDateFormat(String inputDate) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = inputFormat.parse(inputDate);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    private void LoadCbxNgayChieu(){
        ApiPhimService.phimService.getAllNgayChieu().enqueue(new Callback<List<AdminNgayChieu>>() {
            @Override
            public void onResponse(Call<List<AdminNgayChieu>> call, Response<List<AdminNgayChieu>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<AdminNgayChieu> listPhim = response.body();
                    ngayChieuList = new ArrayList<>();
                    for (AdminNgayChieu phim : listPhim) {
                        String convertDate = convertDateFormat(phim.getNgayChieu());
                        ngayChieuList.add(new AdminNgayChieu(phim.getId(), convertDate));
                    }

                    // Set up the adapter for the Spinner
                    ArrayAdapter<AdminNgayChieu> moviesAdapter = new ArrayAdapter<>(AdminThemSuatChieuActivity.this, android.R.layout.simple_spinner_item, ngayChieuList);
                    moviesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spNgayChieu.setAdapter(moviesAdapter);

                    // Set the item selected listener for the spinner
                    spNgayChieu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            AdminNgayChieu selectedPhim = (AdminNgayChieu) parent.getItemAtPosition(position);
                            int selectedPhimId = selectedPhim.getId();
                            idNgayChieu = selectedPhimId;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Do nothing
                        }
                    });
                } else {
                    Toast.makeText(AdminThemSuatChieuActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<AdminNgayChieu>> call, Throwable t) {
                Toast.makeText(AdminThemSuatChieuActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("AdminThemSuatChieuActivity", "onFailure: ", t);
            }
        });
    }

    private void LoadCbxTinh(){
        ApiPhimService.phimService.getAllTinh().enqueue(new Callback<List<AdminTinh>>() {
            @Override
            public void onResponse(Call<List<AdminTinh>> call, Response<List<AdminTinh>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<AdminTinh> listPhim = response.body();
                    tinhList = new ArrayList<>();
                    for (AdminTinh phim : listPhim) {
                        tinhList.add(new AdminTinh(phim.getId(), phim.getTenTinh()));
                    }

                    // Set up the adapter for the Spinner
                    ArrayAdapter<AdminTinh> moviesAdapter = new ArrayAdapter<>(AdminThemSuatChieuActivity.this, android.R.layout.simple_spinner_item, tinhList);
                    moviesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spTinh.setAdapter(moviesAdapter);

                    // Set the item selected listener for the spinner
                    spTinh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            AdminTinh selectedPhim = (AdminTinh) parent.getItemAtPosition(position);
                            int selectedPhimId = selectedPhim.getId();
                            idTinh = selectedPhimId;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Do nothing
                        }
                    });
                } else {
                    Toast.makeText(AdminThemSuatChieuActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<AdminTinh>> call, Throwable t) {
                Toast.makeText(AdminThemSuatChieuActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("AdminThemSuatChieuActivity", "onFailure: ", t);
            }
        });
    }

    private void LoadCbxDiaDiem(){
        ApiPhimService.phimService.getAllDiaDiem().enqueue(new Callback<List<AdminDiaDiem>>() {
            @Override
            public void onResponse(Call<List<AdminDiaDiem>> call, Response<List<AdminDiaDiem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<AdminDiaDiem> listPhim = response.body();
                    diaDiemList = new ArrayList<>();
                    for (AdminDiaDiem phim : listPhim) {
                        diaDiemList.add(new AdminDiaDiem(phim.getId_dia_diem(), phim.getDia_chi()));
                    }

                    // Set up the adapter for the Spinner
                    ArrayAdapter<AdminDiaDiem> moviesAdapter = new ArrayAdapter<>(AdminThemSuatChieuActivity.this, android.R.layout.simple_spinner_item, diaDiemList);
                    moviesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spDiaDiem.setAdapter(moviesAdapter);

                    // Set the item selected listener for the spinner
                    spDiaDiem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            AdminDiaDiem selectedPhim = (AdminDiaDiem) parent.getItemAtPosition(position);
                            int selectedPhimId = selectedPhim.getId_dia_diem();
                            idDiaDiem = selectedPhimId;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Do nothing
                        }
                    });
                } else {
                    Toast.makeText(AdminThemSuatChieuActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<AdminDiaDiem>> call, Throwable t) {
                Toast.makeText(AdminThemSuatChieuActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("AdminThemSuatChieuActivity", "onFailure: ", t);
            }
        });
    }

    private void LoadCbxGioChieu(){
        ApiPhimService.phimService.getAllGioChieu().enqueue(new Callback<List<AdminGioChieu>>() {
            @Override
            public void onResponse(Call<List<AdminGioChieu>> call, Response<List<AdminGioChieu>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<AdminGioChieu> listPhim = response.body();
                    gioChieuList = new ArrayList<>();
                    for (AdminGioChieu phim : listPhim) {
                        gioChieuList.add(new AdminGioChieu(phim.getId_gio_chieu(), phim.getGio_chieu()));
                    }

                    // Set up the adapter for the Spinner
                    ArrayAdapter<AdminGioChieu> moviesAdapter = new ArrayAdapter<>(AdminThemSuatChieuActivity.this, android.R.layout.simple_spinner_item, gioChieuList);
                    moviesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spGioChieu.setAdapter(moviesAdapter);

                    // Set the item selected listener for the spinner
                    spGioChieu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            AdminGioChieu selectedPhim = (AdminGioChieu) parent.getItemAtPosition(position);
                            int selectedPhimId = selectedPhim.getId_gio_chieu();
                            idGioChieu =  selectedPhimId;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Do nothing
                        }
                    });
                } else {
                    Toast.makeText(AdminThemSuatChieuActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<AdminGioChieu>> call, Throwable t) {
                Toast.makeText(AdminThemSuatChieuActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("AdminThemSuatChieuActivity", "onFailure: ", t);
            }
        });
    }

    private void LoadCbxLoaiRap(){
        ApiPhimService.phimService.getAllLoaiRap().enqueue(new Callback<List<AdminLoaiRap>>() {
            @Override
            public void onResponse(Call<List<AdminLoaiRap>> call, Response<List<AdminLoaiRap>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<AdminLoaiRap> listPhim = response.body();
                    loaiRapList = new ArrayList<>();
                    for (AdminLoaiRap phim : listPhim) {
                        loaiRapList.add(new AdminLoaiRap(phim.getId_loai_rap(), phim.getLoai_rap()));
                    }

                    // Set up the adapter for the Spinner
                    ArrayAdapter<AdminLoaiRap> moviesAdapter = new ArrayAdapter<>(AdminThemSuatChieuActivity.this, android.R.layout.simple_spinner_item, loaiRapList);
                    moviesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spLoaiRap.setAdapter(moviesAdapter);

                    // Set the item selected listener for the spinner
                    spLoaiRap.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            AdminLoaiRap selectedPhim = (AdminLoaiRap) parent.getItemAtPosition(position);
                            int selectedPhimId = selectedPhim.getId_loai_rap();
                            idLoaiRap = selectedPhimId;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Do nothing
                        }
                    });
                } else {
                    Toast.makeText(AdminThemSuatChieuActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<AdminLoaiRap>> call, Throwable t) {
                Toast.makeText(AdminThemSuatChieuActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("AdminThemSuatChieuActivity", "onFailure: ", t);
            }
        });
    }
}
