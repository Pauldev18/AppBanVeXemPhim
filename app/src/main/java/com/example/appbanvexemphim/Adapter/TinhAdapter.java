package com.example.appbanvexemphim.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanvexemphim.Activity.BookingActivity;
import com.example.appbanvexemphim.Model.Tinh;
import com.example.appbanvexemphim.R;

import java.util.List;

public class TinhAdapter extends RecyclerView.Adapter<TinhAdapter.TinhViewHolder>{
    private List<Tinh> tinhList;
    private Context context;

    public TinhAdapter(List<Tinh> tinhList, Context context) {
        this.tinhList = tinhList;
        this.context = context;
    }

    @NonNull
    @Override
    public TinhViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_province, parent, false);
        return new TinhAdapter.TinhViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TinhViewHolder holder, int position) {
        Tinh tinh = tinhList.get(position);
        holder.btnTinh.setText(tinh.getDiaDiem());
        holder.btnTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int t = tinh.getId(); // Lấy ngày chiếu từ item được click
                ((BookingActivity) context).fetchDataRap(t);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(tinhList != null){
            return tinhList.size();
        }
        return 0;
    }

    public class TinhViewHolder extends RecyclerView.ViewHolder {
        Button btnTinh;
        public TinhViewHolder(@NonNull View itemView) {
            super(itemView);
            btnTinh = itemView.findViewById(R.id.btn_tinh);
        }
    }
}
