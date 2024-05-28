package com.example.appbanvexemphim.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanvexemphim.Model.AdminDiaDiem;
import com.example.appbanvexemphim.Model.AdminTinh;
import com.example.appbanvexemphim.R;

import java.util.List;

public class AdminTinhAdapter extends RecyclerView.Adapter<AdminTinhAdapter.AdminTinhViewHolder>{
    private List<AdminTinh> tinhList;
    private Context context;

    public AdminTinhAdapter(List<AdminTinh> tinhList, Context context) {
        this.tinhList = tinhList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdminTinhViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.danhsachtinh, parent, false);
        return new AdminTinhAdapter.AdminTinhViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminTinhViewHolder holder, int position) {
        AdminTinh adminDiaDiem = tinhList.get(position);
        int orderNumber = position + 1;

        // Lấy địa chỉ từ đối tượng AdminDiaDiem
        String address = adminDiaDiem.getTenTinh();

        // Kết hợp số thứ tự và địa chỉ
        String displayText = orderNumber + ". " + address;

        // Đặt giá trị vào TextView
        holder.txtTenTinh.setText(displayText);
    }

    @Override
    public int getItemCount() {
        if(tinhList != null){
            return tinhList.size();
        }
        return 0;
    }

    public class AdminTinhViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTenTinh;
        public AdminTinhViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenTinh = itemView.findViewById(R.id.txt_tinh);
        }
    }
}
