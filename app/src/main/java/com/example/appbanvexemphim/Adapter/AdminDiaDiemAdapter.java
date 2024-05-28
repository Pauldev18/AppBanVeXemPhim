package com.example.appbanvexemphim.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanvexemphim.Model.AdminDiaDiem;
import com.example.appbanvexemphim.R;

import java.util.List;

public class AdminDiaDiemAdapter extends RecyclerView.Adapter<AdminDiaDiemAdapter.AdminDiaDiemViewHolder>{
    private List<AdminDiaDiem> diaDiemList;
    private Context context;

    public AdminDiaDiemAdapter(List<AdminDiaDiem> diaDiemList, Context context) {
        this.diaDiemList = diaDiemList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdminDiaDiemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.danhsachdiadiem, parent, false);
        return new AdminDiaDiemAdapter.AdminDiaDiemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminDiaDiemViewHolder holder, int position) {
        AdminDiaDiem adminDiaDiem = diaDiemList.get(position);
        int orderNumber = position + 1;

        // Lấy địa chỉ từ đối tượng AdminDiaDiem
        String address = adminDiaDiem.getDia_chi();

        // Kết hợp số thứ tự và địa chỉ
        String displayText = orderNumber + ". " + address;

        // Đặt giá trị vào TextView
        holder.txtDiaDiem.setText(displayText);

    }

    @Override
    public int getItemCount() {
        if(diaDiemList != null){
            return diaDiemList.size();
        }
        return 0;
    }

    public class AdminDiaDiemViewHolder extends RecyclerView.ViewHolder {
        private TextView txtDiaDiem;
        public AdminDiaDiemViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDiaDiem = itemView.findViewById(R.id.txt_dd);
        }
    }
}
