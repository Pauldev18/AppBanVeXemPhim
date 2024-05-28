package com.example.appbanvexemphim.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanvexemphim.Activity.BookingActivity;
import com.example.appbanvexemphim.Model.DiaDiemAndGioChieu;
import com.example.appbanvexemphim.Model.GioChieu;
import com.example.appbanvexemphim.R;
import com.example.appbanvexemphim.Singleton.ChooseSeat;

import java.util.List;

public class ShowtimesAdapter extends RecyclerView.Adapter<ShowtimesAdapter.ShowtimesViewHolder> {

    private Context context;
    private List<DiaDiemAndGioChieu> showtimeList;

    public ShowtimesAdapter(Context context, List<DiaDiemAndGioChieu> showtimeList) {
        this.context = context;
        this.showtimeList = showtimeList;
    }

    @NonNull
    @Override
    public ShowtimesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_giochieu, parent, false);
        return new ShowtimesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowtimesViewHolder holder, int position) {
        DiaDiemAndGioChieu showtime = showtimeList.get(position);
        holder.tvCinemaName.setText(showtime.getDiaDiem());

        holder.llShowtimes.removeAllViews(); // Clear previous views

        for (GioChieu gioChieu : showtime.getListGioChieu()) {
            // Create Button for each showtime
            Button btnShowtime = new Button(context);
            btnShowtime.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            btnShowtime.setPadding(16, 8, 16, 8);
            btnShowtime.setText(gioChieu.getTime());
            btnShowtime.setTextSize(14);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) btnShowtime.getLayoutParams();
            params.setMargins(8, 0, 8, 0);
            btnShowtime.setLayoutParams(params);
             int idDiaDiem = showtime.getIdDiaDiem();
             int idGioChieu = gioChieu.getId();
            btnShowtime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((BookingActivity) context).fetchDataChoNgoi(idDiaDiem, idGioChieu);
                    ChooseSeat.getInstance().setGioChieu(showtime.getListGioChieu().get(0).getTime());
                    ChooseSeat.getInstance().setPhongChieu(showtime.getDiaDiem());
                }
            });

            holder.llShowtimes.addView(btnShowtime); // Add Button to layout
        }
    }

    @Override
    public int getItemCount() {
        return showtimeList.size();
    }

    public static class ShowtimesViewHolder extends RecyclerView.ViewHolder {
        TextView tvCinemaName;
        LinearLayout llShowtimes;

        public ShowtimesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCinemaName = itemView.findViewById(R.id.tvCinemaName);
            llShowtimes  = itemView.findViewById(R.id.llShowtimes);
        }
    }
}