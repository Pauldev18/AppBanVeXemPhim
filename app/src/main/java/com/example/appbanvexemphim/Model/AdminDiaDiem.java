package com.example.appbanvexemphim.Model;

public class AdminDiaDiem {
    private int id_dia_diem;
    private String dia_chi;

    public int getId_dia_diem() {
        return id_dia_diem;
    }

    public void setId_dia_diem(int id_dia_diem) {
        this.id_dia_diem = id_dia_diem;
    }

    public String getDia_chi() {
        return dia_chi;
    }

    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }

    public AdminDiaDiem(int id_dia_diem, String dia_chi) {
        this.id_dia_diem = id_dia_diem;
        this.dia_chi = dia_chi;
    }

    @Override
    public String toString() {
        return dia_chi;
    }
}
