package com.example.appbanvexemphim.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appbanvexemphim.API.ApiPhimService;
import com.example.appbanvexemphim.Activity.DetailActivity;
import com.example.appbanvexemphim.Activity.PhimEditActivity;
import com.example.appbanvexemphim.Model.Phim;
import com.example.appbanvexemphim.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhimAdapter extends RecyclerView.Adapter<PhimAdapter.PhimViewHolder> {
    private List<Phim> listPhim;
    private Context context;

    public PhimAdapter(List<Phim> listPhim, Context context) {
        this.listPhim = listPhim;
        this.context = context;
    }

    @NonNull
    @Override
    public PhimViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phim, parent, false);
        return new PhimViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull PhimViewHolder holder, int position) {
        Phim phim = listPhim.get(position);
        holder.txtTenPhim.setText(phim.getTenPhim());
        Glide.with(context).load(phim.getAnhPhim()).into(holder.imgPhim);

        // Khai báo sự kiện click
        holder.layOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGotoDetail(phim);
            }
        });
        holder.btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePhim(phim);
            }
        });

        holder.btnEditReal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGotoEdit(phim);
            }
        });
    }

    private void deletePhim(Phim phim) {
        Log.d("DeletePhim", "ID của phim cần xóa: " + phim.getId());
        ApiPhimService.phimService.deletePhim(phim.getId()).enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    loadPhimData();
                    Toast.makeText(context, "Xóa phim thành công", Toast.LENGTH_SHORT).show();
                } else {
                    // Xóa không thành công
                    Toast.makeText(context, "Xóa phim thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Xử lý khi gặp lỗi kết nối hoặc lỗi từ server
                Toast.makeText(context, "Lỗi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadPhimData() {
        ApiPhimService.phimService.getAllPhim().enqueue(new Callback<List<Phim>>() {
            @Override
            public void onResponse(Call<List<Phim>> call, Response<List<Phim>> response) {
                if (response.isSuccessful()) {
                    listPhim.clear(); // Xóa danh sách phim hiện tại
                    listPhim.addAll(response.body()); // Thêm dữ liệu mới vào danh sách
                    notifyDataSetChanged(); // Load lại giao diện với dữ liệu mới
                } else {
                    // Xử lý khi không thể tải dữ liệu phim
                    Toast.makeText(context, "Không thể tải dữ liệu phim", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Phim>> call, Throwable t) {
                // Xử lý khi gặp lỗi kết nối hoặc lỗi từ server
                Toast.makeText(context, "Lỗi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onClickGotoDetail(Phim phim) {
        Intent instant = new Intent(context, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("objPhim", phim);
        instant.putExtras(bundle);
        context.startActivity(instant);
    }
    private void onClickGotoEdit(Phim phim) {
        Intent instant = new Intent(context, PhimEditActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("objPhimEdit", phim);
        instant.putExtras(bundle);
        context.startActivity(instant);
    }
    @Override
    public int getItemCount() {
        return listPhim.size();
    }

    //Khai báo các thành phần
    public class PhimViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhim;
        TextView txtTenPhim;
        private ConstraintLayout layOut;
        Button btnedit;
        Button btnEditReal;

        public PhimViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhim = itemView.findViewById(R.id.imgPhim);
            txtTenPhim = itemView.findViewById(R.id.txtTenPhim);
            layOut = itemView.findViewById(R.id.itemPhimID);
            btnedit = itemView.findViewById(R.id.btnEdit);
            btnEditReal = itemView.findViewById(R.id.btnEditReal);
        }

    }
}