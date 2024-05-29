package com.example.appbanvexemphim.Model;

import java.io.Serializable;

public class Phim implements Serializable {
    private String tenPhim;
    private String anhPhim;
    private String theLoai;
    private String thoiLuong;
    private String khoiChieu;
    private String daoDien;
    private String dienVien;
    private String ngonNgu;
    private String danhGia;
    private String noiDung;
    private int tinhTrang;
    private int id;

    public Phim() {
    }

    public Phim(String tenPhim, int id) {
        this.tenPhim = tenPhim;
        this.id = id;
    }

    public Phim(String tenPhim, String anhPhim, String theLoai, String thoiLuong, String khoiChieu, String daoDien, String dienVien, String ngonNgu, String danhGia, String noiDung, int tinhTrang, int id) {
        this.tenPhim = tenPhim;
        this.anhPhim = anhPhim;
        this.theLoai = theLoai;
        this.thoiLuong = thoiLuong;
        this.khoiChieu = khoiChieu;
        this.daoDien = daoDien;
        this.dienVien = dienVien;
        this.ngonNgu = ngonNgu;
        this.danhGia = danhGia;
        this.noiDung = noiDung;
        this.tinhTrang = tinhTrang;
        this.id = id;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getAnhPhim() {
        return anhPhim;
    }

    public void setAnhPhim(String anhPhim) {
        this.anhPhim = anhPhim;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public String getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(String thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public String getKhoiChieu() {
        return khoiChieu;
    }

    public void setKhoiChieu(String khoiChieu) {
        this.khoiChieu = khoiChieu;
    }

    public String getDaoDien() {
        return daoDien;
    }

    public void setDaoDien(String daoDien) {
        this.daoDien = daoDien;
    }

    public String getDienVien() {
        return dienVien;
    }

    public void setDienVien(String dienVien) {
        this.dienVien = dienVien;
    }

    public String getNgonNgu() {
        return ngonNgu;
    }

    public void setNgonNgu(String ngonNgu) {
        this.ngonNgu = ngonNgu;
    }

    public String getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(String danhGia) {
        this.danhGia = danhGia;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return tenPhim;
    }
}
