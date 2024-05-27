package com.example.appbanvexemphim.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanvexemphim.Activity.BookingActivity;
import com.example.appbanvexemphim.Activity.ChonVeActivity;
import com.example.appbanvexemphim.Model.ChoNgoi;
import java.util.List;
import com.example.appbanvexemphim.R;
import com.example.appbanvexemphim.Singleton.ChooseSeat;

public class ChoNgoiAdapter extends RecyclerView.Adapter<ChoNgoiAdapter.ChoNgoiViewHolder> {

    private List<ChoNgoi> choNgoiList;
    private Context context;

    public ChoNgoiAdapter(Context context, List<ChoNgoi> choNgoiList) {
        this.context = context;
        this.choNgoiList = choNgoiList;
    }

    @NonNull
    @Override
    public ChoNgoiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seat_item, parent, false);
        return new ChoNgoiAdapter.ChoNgoiViewHolder(view);
    }

    int selectedSeatId = -1;  // Initialize to -1 (no selection)
    @Override
    public void onBindViewHolder(@NonNull ChoNgoiViewHolder holder, int position) {
        ChoNgoi choNgoi = choNgoiList.get(position);
        // Set background color and enable/disable seat based on status
        switch (choNgoi.getTrangThai()) {
            case 0: // Available
                holder.seatButton.setBackgroundColor(Color.GREEN);
                holder.seatButton.setEnabled(true);
                break;
            case 1: // Booked
                holder.seatButton.setBackgroundColor(Color.RED);
                holder.seatButton.setEnabled(false);
                break;
            case 2: // Selected
                holder.seatButton.setBackgroundColor(Color.YELLOW);
                holder.seatButton.setEnabled(true);
                selectedSeatId = choNgoi.getId();  // Update selected seat ID
                break;
            default:
                // Handle other statuses (similar to previous code)
                break;
        }

        // Set seat name
        holder.seatButton.setText(choNgoi.getChoNgoi());

        // Set click listener for seat selection
        holder.seatButton.setOnClickListener(v -> {
            if (choNgoi.getTrangThai() == 0) { // Available seat clicked
                // Deselect previously selected seat (if any)
                if (selectedSeatId != -1) {
                    int previousSelectedSeatIndex = choNgoiList.indexOf(
                            choNgoiList.stream()
                                    .filter(s -> s.getId() == selectedSeatId)
                                    .findFirst()
                                    .orElse(null)
                    );
                    if (previousSelectedSeatIndex != -1) {
                        choNgoiList.get(previousSelectedSeatIndex).setTrangThai(0);
                        notifyItemChanged(previousSelectedSeatIndex);
                    }
                }
                choNgoi.setTrangThai(2); // Set current seat to selected state
                holder.seatButton.setBackgroundColor(Color.YELLOW);
                selectedSeatId = choNgoi.getId();  // Update selected seat ID
                ChooseSeat.getInstance().setSelectedSeat(selectedSeatId);
                ((ChonVeActivity) context).setTongtien(choNgoi.getPrice());
            }
        });
    }






    @Override
    public int getItemCount() {
        return choNgoiList.size();
    }

    public class ChoNgoiViewHolder extends RecyclerView.ViewHolder {
        TextView seatButton;
        public ChoNgoiViewHolder(View itemView) {
            super(itemView);
            seatButton = itemView.findViewById(R.id.seat_button);
        }
    }
}