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
import com.example.appbanvexemphim.Singleton.ChooseSeat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    String formattedDate;
    @Override
    public void onBindViewHolder(@NonNull NgayChieuViewHolder holder, int position) {
        NgayChieu nc = listNgayChieus.get(position);

        // Assuming the original date format is yyyy-MM-dd
        String originalDate = nc.getThoiGian();
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat targetFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        try {
            Date date = originalFormat.parse(originalDate);
            formattedDate = targetFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            formattedDate = originalDate; // Fallback to original if parsing fails
        }

        holder.tvDate.setText(formattedDate);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ngay = nc.getId(); // Lấy ngày chiếu từ item được click
                ((BookingActivity) context).fetchDataTinh(ngay);
                ChooseSeat.getInstance().setNgayChieu(formattedDate);
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
