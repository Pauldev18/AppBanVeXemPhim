package com.example.appbanvexemphim.API;

import com.example.appbanvexemphim.Model.Phim;
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
    @DELETE("deletePhim/{IDPhim}")
    Call<Void> deletePhim(@Path("IDPhim") int IDPhim);
}
