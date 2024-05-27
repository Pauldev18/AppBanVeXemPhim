package com.example.appbanvexemphim.API;

import com.example.appbanvexemphim.Model.DiaDiemAndGioChieu;
import com.example.appbanvexemphim.Model.NgayChieu;
import com.example.appbanvexemphim.Model.Phim;
import com.example.appbanvexemphim.Model.Rap;
import com.example.appbanvexemphim.Model.Tinh;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiPhimService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiPhimService phimService = new Retrofit.Builder()
            .baseUrl("http://192.168.80.1:8080/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiPhimService.class);

    @GET("allphim")
    Call<List<Phim>> getAllPhim();

    @GET("getNgayPhim/{pathVariable}")
    Call<List<NgayChieu>> getData(@Path("pathVariable") String pathVariable);

    @GET("getTinhPhim")
    Call<List<Tinh>> getTinh(
            @Query("idNgayChieu") int idNgayChieu,
            @Query("idPhim") int idPhim
    );

    @GET("getRapPhim")
    Call<List<Rap>> getRap(
            @Query("idNgayChieu") int idNgayChieu,
            @Query("idPhim") int idPhim,
            @Query("idTinh") int idTinh
    );
    @GET("getDiaDiaAndGioChieu")
    Call<List<DiaDiemAndGioChieu>> geDiaDiemAndGioChieu(
            @Query("idNgayChieu") int idNgayChieu,
            @Query("idPhim") int idPhim,
            @Query("idTinh") int idTinh,
            @Query("idRap") int idRap
    );

    @DELETE("deletePhim/{IDPhim}")
    Call<Void> deletePhim(@Path("IDPhim") int IDPhim);
}
