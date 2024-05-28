package com.example.appbanvexemphim.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanvexemphim.Model.AdminNgayChieu;
import com.example.appbanvexemphim.Model.NgayChieu;
import com.example.appbanvexemphim.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AdminNgayChieuAdapter extends RecyclerView.Adapter<AdminNgayChieuAdapter.AdminNgayChieuViewHolder>{
    private List<AdminNgayChieu> ngayChieuList;
    private Context context;

    public AdminNgayChieuAdapter(List<AdminNgayChieu> ngayChieuList, Context context) {
        this.ngayChieuList = ngayChieuList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdminNgayChieuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.danhsachngaychieu, parent, false);
        return new AdminNgayChieuAdapter.AdminNgayChieuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminNgayChieuViewHolder holder, int position) {
        AdminNgayChieu ngayChieu = ngayChieuList.get(position);
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        try {
            Date date = inputFormat.parse(ngayChieu.getNgayChieu());
            String ngayChieuFormatted = outputFormat.format(date);
            // Tạo số thứ tự (bắt đầu từ 1)
            int orderNumber = position + 1;

            // Kết hợp số thứ tự và ngày chiếu đã định dạng
            String displayText = orderNumber + ". " + ngayChieuFormatted;

            // Đặt giá trị vào TextView
            holder.ngayChieu.setText(displayText);
        } catch (ParseException e) {
            e.printStackTrace(); // Handle the parsing exception as needed
        }
    }

    @Override
    public int getItemCount() {
        if(ngayChieuList != null){
            return ngayChieuList.size();
        }
        return 0;
    }

    public class AdminNgayChieuViewHolder extends RecyclerView.ViewHolder {
        private TextView ngayChieu;
        public AdminNgayChieuViewHolder(@NonNull View itemView) {
            super(itemView);
            ngayChieu = itemView.findViewById(R.id.txtNgayChieu);
        }
    }
}
