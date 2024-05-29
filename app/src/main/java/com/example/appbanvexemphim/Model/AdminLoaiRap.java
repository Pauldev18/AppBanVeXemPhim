package com.example.appbanvexemphim.Model;

public class AdminLoaiRap {
    private int id_loai_rap;
    private String loai_rap;
    private float giaTien;

    public int getId_loai_rap() {
        return id_loai_rap;
    }

    public void setId_loai_rap(int id_loai_rap) {
        this.id_loai_rap = id_loai_rap;
    }

    public String getLoai_rap() {
        return loai_rap;
    }

    public void setLoai_rap(String loai_rap) {
        this.loai_rap = loai_rap;
    }

    public float getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(float giaTien) {
        this.giaTien = giaTien;
    }

    public AdminLoaiRap(int id_loai_rap, String loai_rap) {
        this.id_loai_rap = id_loai_rap;
        this.loai_rap = loai_rap;
    }

    @Override
    public String toString() {
        return loai_rap;
    }
}
