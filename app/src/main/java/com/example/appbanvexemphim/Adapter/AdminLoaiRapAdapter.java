package com.example.appbanvexemphim.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanvexemphim.Model.AdminGioChieu;
import com.example.appbanvexemphim.Model.AdminLoaiRap;
import com.example.appbanvexemphim.R;

import java.util.List;

public class AdminLoaiRapAdapter extends RecyclerView.Adapter<AdminLoaiRapAdapter.AdminLoaiRapViewHolder>{
    private List<AdminLoaiRap> loaiRapList;
    private Context context;

    public AdminLoaiRapAdapter(List<AdminLoaiRap> loaiRapList, Context context) {
        this.loaiRapList = loaiRapList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdminLoaiRapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.danhsachloairap, parent, false);
        return new AdminLoaiRapAdapter.AdminLoaiRapViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminLoaiRapViewHolder holder, int position) {
        AdminLoaiRap adminDiaDiem = loaiRapList.get(position);
        int orderNumber = position + 1;

        // Lấy địa chỉ từ đối tượng AdminDiaDiem
        String address = adminDiaDiem.getLoai_rap();

        // Kết hợp số thứ tự và địa chỉ
        String displayText = orderNumber + ". " + address + " - "+ adminDiaDiem.getGiaTien() + " VND";

        // Đặt giá trị vào TextView
        holder.txtLoaiRap.setText(displayText);
    }

    @Override
    public int getItemCount() {
        if(loaiRapList != null){
            return loaiRapList.size();
        }
        return 0;
    }

    public class AdminLoaiRapViewHolder extends RecyclerView.ViewHolder {
        private TextView txtLoaiRap;
        public AdminLoaiRapViewHolder(@NonNull View itemView) {
            super(itemView);
            txtLoaiRap = itemView.findViewById(R.id.txt_loairap);
        }
    }
}
