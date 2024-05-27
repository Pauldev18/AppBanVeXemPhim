package com.example.appbanvexemphim.Model;

import java.util.List;

public class DiaDiemAndGioChieu {
    private int idDiaDiem;
    private String diaDiem;
    private List<GioChieu> listGioChieu;

    public int getIdDiaDiem() {
        return idDiaDiem;
    }

    public void setIdDiaDiem(int idDiaDiem) {
        this.idDiaDiem = idDiaDiem;
    }

    public String getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }

    public List<GioChieu> getListGioChieu() {
        return listGioChieu;
    }

    public void setListGioChieu(List<GioChieu> listGioChieu) {
        this.listGioChieu = listGioChieu;
    }
}
