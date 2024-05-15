package com.example.appbanvexemphim.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appbanvexemphim.Activity.DetailActivity;
import com.example.appbanvexemphim.Model.Phim;
import com.example.appbanvexemphim.R;

import java.util.List;

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

        // Gán sự kiện OnTouchListener cho layOut

    }


    private void onClickGotoDetail(Phim phim) {
        Intent instant = new Intent(context, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("objPhim", phim);
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
        private boolean isSwipedLeft = false;
        public PhimViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhim = itemView.findViewById(R.id.imgPhim);
            txtTenPhim = itemView.findViewById(R.id.txtTenPhim);
            layOut = itemView.findViewById(R.id.itemPhimID);
            btnedit = itemView.findViewById(R.id.btnEdit);
        }
        public boolean isSwipedLeft() {
            return isSwipedLeft;
        }

        public void setSwipedLeft(boolean swipedLeft) {
            isSwipedLeft = swipedLeft;
        }
    }
}