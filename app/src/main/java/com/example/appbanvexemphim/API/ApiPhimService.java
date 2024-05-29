package com.example.appbanvexemphim.API;

import com.example.appbanvexemphim.Model.AdminDiaDiem;
import com.example.appbanvexemphim.Model.AdminGioChieu;
import com.example.appbanvexemphim.Model.AdminLoaiRap;
import com.example.appbanvexemphim.Model.AdminNgayChieu;
import com.example.appbanvexemphim.Model.AdminTinh;
import com.example.appbanvexemphim.Model.ChoNgoi;
import com.example.appbanvexemphim.Model.DatCho;
import com.example.appbanvexemphim.Model.DiaDiemAndGioChieu;
import com.example.appbanvexemphim.Model.LichSuDatVe;
import com.example.appbanvexemphim.Model.LoginReponse;
import com.example.appbanvexemphim.Model.LoginRequest;
import com.example.appbanvexemphim.Model.NewSuatChieu;
import com.example.appbanvexemphim.Model.NgayChieu;
import com.example.appbanvexemphim.Model.Phim;
import com.example.appbanvexemphim.Model.Rap;
import com.example.appbanvexemphim.Model.Tinh;
import com.example.appbanvexemphim.Model.Url;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiPhimService {
    Gson gson = new GsonBuilder()
            .setLenient()
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

    @POST("getChoNgoi")
    Call<List<ChoNgoi>> getChoNgoi(
            @Query("idNgayChieu") int idNgayChieu,
            @Query("idPhim") int idPhim,
            @Query("idTinh") int idTinh,
            @Query("idRap") int idRap,
            @Query("idDiaDiem") int idDiaDiem,
            @Query("idGioChieu") int idGioChieu
    );
    @POST("IDDatCho")
    Call<List<DatCho>> getIDDatCho(
            @Query("idNgayChieu") int idNgayChieu,
            @Query("idPhim") int idPhim,
            @Query("idTinh") int idTinh,
            @Query("idRap") int idRap,
            @Query("idDiaDiem") int idDiaDiem,
            @Query("idGioChieu") int idGioChieu,
            @Query("idChoNgoi") int idChoNgoi
    );

    @POST("pay")
    Call<Url> getUrl(
            @Query("price") long price,
            @Query("idDatCho") int idDatCho,
            @Query("IDUser") int IDUser
    );

    @GET("lsdv/{IDUser}")
    Call<List<LichSuDatVe>> getLichSuDatVe(@Path("IDUser") int IDUser);

    @POST("login")
    Call<LoginReponse> login(@Body LoginRequest login);

    @DELETE("deletePhim/{IDPhim}")
    Call<Void> deletePhim(@Path("IDPhim") int IDPhim);

    @Multipart
    @POST("upfilm")
    Call<Object> upFilm(
            @Query("tenPhim") String tenPhim,
            @Part MultipartBody.Part anhPhimUrl, // Change the parameter type here
            @Query("theLoai") String theLoai,
            @Query("thoiLuong") String thoiLuong,
            @Query("khoiChieu") String khoiChieu,
            @Query("daoDien") String daoDien,
            @Query("dienVien") String dienVien,
            @Query("ngonNgu") String ngonNgu,
            @Query("danhGia") String danhGia,
            @Query("noiDung") String noiDung
    );

    @GET("getAllNgayChieu")
    Call<List<AdminNgayChieu>> getAllNgayChieu();
    @GET("getAllDiaDiem")
    Call<List<AdminDiaDiem>> getAllDiaDiem();
    @GET("getAllTinh")
    Call<List<AdminTinh>> getAllTinh();
    @GET("getAllGioChieu")
    Call<List<AdminGioChieu>> getAllGioChieu();
    @GET("getAllLoaiRap")
    Call<List<AdminLoaiRap>> getAllLoaiRap();

    @POST("/newListSuatChieu")
    Call<Void> createSuatChieu(@Body NewSuatChieu newSuatChieu);

    @POST("newNgayChieu")
    Call<Void> createNgayChieu(
            @Query("ngayChieu") Date ngayChieu
    );

    @POST("newDiaDiem")
    Call<Void> createDiaDiem(
            @Query("diaDiem") String diaDiem
    );

    @POST("newTinh")
    Call<Void> createTinh(
            @Query("Tinh") String tinh
    );
    @POST("newGioChieu")
    Call<Void> createGioChieu(
            @Query("gioChieu") Time gioChieu
    );
    @POST("newLoaiRap")
    Call<Void> createLoaiRap(
            @Query("loaiRap") String loaiRap,
            @Query("price") Float price
    );
}
