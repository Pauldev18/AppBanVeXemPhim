package com.example.appbanvexemphim.Model;

public class AdminNgayChieu {
    private int id;
    private String ngayChieu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNgayChieu() {
        return ngayChieu;
    }

    public void setNgayChieu(String ngayChieu) {
        this.ngayChieu = ngayChieu;
    }

    public AdminNgayChieu(int id, String ngayChieu) {
        this.id = id;
        this.ngayChieu = ngayChieu;
    }

    @Override
    public String toString() {
        return ngayChieu;
    }
}
