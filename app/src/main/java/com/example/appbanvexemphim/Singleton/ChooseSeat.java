package com.example.appbanvexemphim.Singleton;

public class ChooseSeat {

    private static ChooseSeat instance;  // Static instance variable
    private int selectedSeat;  // Object to store selected seat information
    private float price;
    private String tenPhim;
    private String ngayChieu;
    private String gioChieu;
    private String tenRap;
    private String tenTinh;
    private String tenGhe;
    private String phongChieu;
    private int userID;
    private String movieGenre;
    private String movieDirector;
    private String movieCast;
    private String cinemaName;
    private String cinemaLocation;

    private ChooseSeat() { }  // Private constructor to prevent direct instantiation

    public static ChooseSeat getInstance() {
        if (instance == null) {
            instance = new ChooseSeat();  // Create instance if not already created
        }
        return instance;
    }


    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public String getMovieCast() {
        return movieCast;
    }

    public void setMovieCast(String movieCast) {
        this.movieCast = movieCast;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getCinemaLocation() {
        return cinemaLocation;
    }

    public void setCinemaLocation(String cinemaLocation) {
        this.cinemaLocation = cinemaLocation;
    }

    public void setSelectedSeat(int selectedSeat) {
        this.selectedSeat = selectedSeat;
    }

    public int getSelectedSeat() {
        return selectedSeat;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public String getPhongChieu() {
        return phongChieu;
    }

    public void setPhongChieu(String phongChieu) {
        this.phongChieu = phongChieu;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getNgayChieu() {
        return ngayChieu;
    }

    public void setNgayChieu(String ngayChieu) {
        this.ngayChieu = ngayChieu;
    }

    public String getGioChieu() {
        return gioChieu;
    }

    public void setGioChieu(String gioChieu) {
        this.gioChieu = gioChieu;
    }

    public String getTenRap() {
        return tenRap;
    }

    public void setTenRap(String tenRap) {
        this.tenRap = tenRap;
    }

    public String getTenTinh() {
        return tenTinh;
    }

    public void setTenTinh(String tenTinh) {
        this.tenTinh = tenTinh;
    }

    public String getTenGhe() {
        return tenGhe;
    }

    public void setTenGhe(String tenGhe) {
        this.tenGhe = tenGhe;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
