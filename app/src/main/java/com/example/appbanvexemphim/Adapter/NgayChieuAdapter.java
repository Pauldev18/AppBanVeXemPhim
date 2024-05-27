package com.example.appbanvexemphim.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanvexemphim.Activity.BookingActivity;
import com.example.appbanvexemphim.Model.NgayChieu;
import com.example.appbanvexemphim.Model.Phim;
import com.example.appbanvexemphim.R;

import java.util.List;

public class NgayChieuAdapter extends RecyclerView.Adapter<NgayChieuAdapter.NgayChieuViewHolder>{
    private List<NgayChieu> listNgayChieus;
    private Context context;

    public NgayChieuAdapter(List<NgayChieu> listNgayChieus, Context context) {
        this.listNgayChieus = listNgayChieus;
        this.context = context;
    }

    @NonNull
    @Override
    public NgayChieuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_date, parent, false);
        return new NgayChieuAdapter.NgayChieuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NgayChieuViewHolder holder, int position) {
        NgayChieu nc = listNgayChieus.get(position);
        holder.tvDate.setText(nc.getThoiGian());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ngay = nc.getId(); // Lấy ngày chiếu từ item được click
                ((BookingActivity) context).fetchDataTinh(ngay);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(listNgayChieus != null){
            return listNgayChieus.size();
        }
        return 0;
    }

    public class NgayChieuViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate;
        CardView layout;
        public NgayChieuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tv_date);
            layout = itemView.findViewById(R.id.item_date);
        }
    }
}
