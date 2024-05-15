package com.example.appbanvexemphim.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanvexemphim.Model.Phim;
import com.example.appbanvexemphim.R;

public class PhimEditAdapter extends RecyclerView.Adapter<PhimEditAdapter.PhimEditViewHolder>{
    private Phim phim;
    private Context context;
    public PhimEditAdapter(Phim phim, Context context) {
        this.phim = phim;
        this.context = context;
    }


    @NonNull
    @Override
    public PhimEditViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.edit_phim, parent, false);
        return new PhimEditViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhimEditViewHolder holder, int position) {
        holder.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenPhim = holder.txtTenPhim.getText().toString();
                Log.d("ok", tenPhim);
                Toast.makeText(context, tenPhim, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(phim != null){
            return 1;
        }
        return 0;
    }

    public class PhimEditViewHolder extends RecyclerView.ViewHolder{

        EditText txtTenPhim;
        Button btnTroLai;
        Button btnResert;
        Button btnSave;
        public PhimEditViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenPhim = itemView.findViewById(R.id.txt_tenPhimEdit);
            btnTroLai = itemView.findViewById(R.id.btnTroLai);
            btnResert = itemView.findViewById(R.id.btnReset);
            btnSave = itemView.findViewById(R.id.btnSave);
        }
    }
}
