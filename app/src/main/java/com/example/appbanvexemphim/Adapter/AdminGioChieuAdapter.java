package com.example.appbanvexemphim.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanvexemphim.Model.AdminDiaDiem;
import com.example.appbanvexemphim.Model.AdminGioChieu;
import com.example.appbanvexemphim.R;

import java.util.List;

public class AdminGioChieuAdapter extends RecyclerView.Adapter<AdminGioChieuAdapter.AdminGioChieuViewHolder>{
    private List<AdminGioChieu> gioChieuList;
    private Context context;

    public AdminGioChieuAdapter(List<AdminGioChieu> gioChieuList, Context context) {
        this.gioChieuList = gioChieuList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdminGioChieuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.danhsachgiochieu, parent, false);
        return new AdminGioChieuAdapter.AdminGioChieuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminGioChieuViewHolder holder, int position) {
        AdminGioChieu adminDiaDiem = gioChieuList.get(position);
        int orderNumber = position + 1;

        // Lấy địa chỉ từ đối tượng AdminDiaDiem
        String address = adminDiaDiem.getGio_chieu();

        // Kết hợp số thứ tự và địa chỉ
        String displayText = orderNumber + ". " + address;

        // Đặt giá trị vào TextView
        holder.txtGioChieu.setText(displayText);
    }

    @Override
    public int getItemCount() {
        if(gioChieuList != null){
            return gioChieuList.size();
        }
        return 0;
    }

    public class AdminGioChieuViewHolder extends RecyclerView.ViewHolder {
        private TextView txtGioChieu;
        public AdminGioChieuViewHolder(@NonNull View itemView) {
            super(itemView);
            txtGioChieu = itemView.findViewById(R.id.txt_giochieu);
        }
    }
}
