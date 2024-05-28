package com.example.appbanvexemphim.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanvexemphim.Activity.BookingActivity;
import com.example.appbanvexemphim.Model.Rap;
import com.example.appbanvexemphim.R;
import com.example.appbanvexemphim.Singleton.ChooseSeat;

import java.util.List;

public class RapAdapter extends RecyclerView.Adapter<RapAdapter.RapViewHolder>{
    private List<Rap> rapList;
    private Context context;

    public RapAdapter(List<Rap> rapList, Context context) {
        this.rapList = rapList;
        this.context = context;
    }

    @NonNull
    @Override
    public RapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rap, parent, false);
        return new RapAdapter.RapViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RapViewHolder holder, int position) {
        Rap rap = rapList.get(position);
        holder.btnRap.setText(rap.getTenRap());
        holder.btnRap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int t = rap.getId(); // Lấy ngày chiếu từ item được click
                ((BookingActivity) context).fetchDataDiaDiemAndChoNgoi(t);
                ChooseSeat.getInstance().setTenRap(rap.getTenRap());
            }
        });
    }

    @Override
    public int getItemCount() {
        if(rapList != null){
            return rapList.size();
        }
        return 0;
    }

    public class RapViewHolder extends RecyclerView.ViewHolder {
        Button btnRap;
        public RapViewHolder(@NonNull View itemView) {
            super(itemView);
            btnRap = itemView.findViewById(R.id.btn_rap);
        }
    }
}
