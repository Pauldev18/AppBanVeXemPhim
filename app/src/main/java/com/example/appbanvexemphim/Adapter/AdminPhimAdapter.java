// src/main/java/com/example/appbanvexemphim/Adapter/AdminPhimAdapter.java
package com.example.appbanvexemphim.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appbanvexemphim.Model.Phim;
import com.example.appbanvexemphim.R;

import java.util.List;

public class AdminPhimAdapter extends RecyclerView.Adapter<AdminPhimAdapter.AdminPhimViewHolder>{
    private List<Phim> phimList;
    private Context context;

    public AdminPhimAdapter(List<Phim> phimList, Context context) {
        this.phimList = phimList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdminPhimViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_phim, parent, false);
        return new AdminPhimAdapter.AdminPhimViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminPhimViewHolder holder, int position) {
        Phim phim = phimList.get(position);
        holder.theLoai.setText(phim.getTheLoai());
        holder.tenPhim.setText(phim.getTenPhim());
        Glide.with(context)
                .load(phim.getAnhPhim())
                .into(holder.image);

        holder.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();
                if (clickedPosition != RecyclerView.NO_POSITION) {
                    // Retrieve the Phim object associated with the clicked position
                    Phim clickedPhim = phimList.get(clickedPosition);
                    // Hiển thị hộp thoại xác nhận
                    new AlertDialog.Builder(context)
                            .setTitle("Xác nhận xóa")
                            .setMessage("Bạn có chắc chắn muốn xóa phim này không?")
                            .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Hiển thị ID của phim
                                    Toast.makeText(context, "ID của phim: " + clickedPhim.getId(), Toast.LENGTH_SHORT).show();
                                    // Thực hiện xóa phim khỏi danh sách (nếu cần)
                                    phimList.remove(clickedPosition);
                                    notifyItemRemoved(clickedPosition);
                                }
                            })
                            .setNegativeButton("Không", null)
                            .show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if(phimList != null){
            return phimList.size();
        }
        return 0;
    }

    public class AdminPhimViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView tenPhim;
        private TextView theLoai;
        private Button btnXoa;
        public AdminPhimViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imgMovie);
            tenPhim = itemView.findViewById(R.id.tvMovieTitle);
            theLoai = itemView.findViewById(R.id.tvMovieGenre);
            btnXoa = itemView.findViewById(R.id.btnDeleteMovie);
        }
    }
}
