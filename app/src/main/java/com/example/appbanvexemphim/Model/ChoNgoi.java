package com.example.appbanvexemphim.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class ChoNgoi implements Parcelable {

    private int id;
    private String choNgoi;
    private int trangThai;
    private float price;

    // Getters and setters (omitted for brevity)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChoNgoi() {
        return choNgoi;
    }

    public void setChoNgoi(String choNgoi) {
        this.choNgoi = choNgoi;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(choNgoi);
        dest.writeInt(trangThai);
        dest.writeFloat(price);
    }

    public static final Parcelable.Creator<ChoNgoi> CREATOR = new Creator<ChoNgoi>() {
        @Override
        public ChoNgoi createFromParcel(Parcel in) {
            return new ChoNgoi(in);
        }

        @Override
        public ChoNgoi[] newArray(int size) {
            return new ChoNgoi[size];
        }
    };

    private ChoNgoi(Parcel in) {
        id = in.readInt();
        choNgoi = in.readString();
        trangThai = in.readInt();
        price = in.readFloat();
    }
}

