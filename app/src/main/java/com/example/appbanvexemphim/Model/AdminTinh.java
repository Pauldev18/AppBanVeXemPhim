package com.example.appbanvexemphim.Model;

public class AdminTinh {
    private int id;
    private String tenTinh;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenTinh() {
        return tenTinh;
    }

    public void setTenTinh(String tenTinh) {
        this.tenTinh = tenTinh;
    }

    public AdminTinh(int id, String tenTinh) {
        this.id = id;
        this.tenTinh = tenTinh;
    }

    @Override
    public String toString() {
        return tenTinh;
    }
}
