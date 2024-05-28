package com.example.appbanvexemphim.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanvexemphim.Model.LichSuDatVe;
import com.example.appbanvexemphim.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class VePhimAdapter extends RecyclerView.Adapter<VePhimAdapter.VePhimViewHolder>{
    private List<LichSuDatVe> listVe;
    private Context context;

    public VePhimAdapter(List<LichSuDatVe> listVe, Context context) {
        this.listVe = listVe;
        this.context = context;
    }

    @NonNull
    @Override
    public VePhimViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vephim, parent, false);
        return new VePhimAdapter.VePhimViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VePhimViewHolder holder, int position) {
        LichSuDatVe ve = listVe.get(position);
        holder.textViewMovieTitle.setText("Tên phim: " + ve.getTenPhim());

        // Chuyển đổi ngày mua về định dạng dd/MM/yyyy
        String ngayMua = convertDateFormat(ve.getNgayMua(), "yyyy-MM-dd", "dd/MM/yyyy");
        holder.textViewDate.setText("Ngày mua: " + ngayMua);

        // Chuyển đổi ngày chiếu về định dạng dd/MM/yyyy
        String ngayChieu = convertDateFormat(ve.getNgayChieu(), "yyyy-MM-dd", "dd/MM/yyyy");
        holder.textViewTime.setText("Thời gian chiếu: " + ngayChieu + " - " + ve.getGioChieu());

        holder.txtDiaDiem.setText("Địa điểm: " + ve.getDiaDiem());
        holder.txtRap.setText("Rạp: " + ve.getTenRap());
        holder.txtGhe.setText("Ghế: " + ve.getTenGhe());

        // Tạo hình ảnh QR từ dữ liệu mã QR trong đối tượng ve và hiển thị nó trong ImageView
        Bitmap qrBitmap = generateQRCode(ve.getMaVe());
        if (qrBitmap != null) {
            holder.imageViewQRCode.setImageBitmap(qrBitmap);
        } else {
            holder.imageViewQRCode.setImageResource(R.drawable.booked_seat_background); // Đặt hình ảnh mặc định nếu không thể tạo mã QR
        }
    }

    private String convertDateFormat(String inputDate, String inputFormat, String outputFormat) {
        SimpleDateFormat inputFormatter = new SimpleDateFormat(inputFormat, Locale.getDefault());
        SimpleDateFormat outputFormatter = new SimpleDateFormat(outputFormat, Locale.getDefault());

        try {
            Date date = inputFormatter.parse(inputDate);
            return outputFormatter.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return inputDate; // Trả về ngày không thể chuyển đổi nếu có lỗi
        }
    }
    private Bitmap generateQRCode(String data) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap qrBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

            // Lặp qua từng pixel trong bitMatrix và đặt màu tương ứng cho từng pixel trong hình ảnh QR
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    qrBitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            return qrBitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public int getItemCount() {
        if(listVe != null){
            return listVe.size();
        }
        return 0;
    }

    public class VePhimViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewMovieTitle;
        public TextView textViewDate;
        public TextView textViewTime;
        public TextView txtDiaDiem;
        public TextView txtRap;
        public TextView txtGhe;
        public ImageView imageViewQRCode;
        public VePhimViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMovieTitle = itemView.findViewById(R.id.textViewMovieTitle);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewTime = itemView.findViewById(R.id.textViewTime);
            txtDiaDiem = itemView.findViewById(R.id.txtDiaDiem);
            txtRap = itemView.findViewById(R.id.txtRap);
            txtGhe = itemView.findViewById(R.id.txtGhe);
            imageViewQRCode = itemView.findViewById(R.id.imageViewQRCode);
        }
    }
}
