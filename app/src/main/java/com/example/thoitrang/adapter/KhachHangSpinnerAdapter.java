package com.example.thoitrang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.thoitrang.R;
import com.example.thoitrang.model.KhachHang;

import java.util.ArrayList;

public class KhachHangSpinnerAdapter extends ArrayAdapter<KhachHang> {

   private Context context;
   private ArrayList<KhachHang> lists;
   TextView tvMaKHsp, tvTenKHsp;
   public KhachHangSpinnerAdapter(@NonNull Context context, ArrayList<KhachHang> lists){
       super(context, 0,lists);
       this.context = context;
       this.lists = lists;
   }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       View v = convertView;
       if(v == null){
           LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           v = inflater.inflate(R.layout.khach_hang_item_spinner,null);
       }
       final KhachHang item = lists.get(position);
       if(item != null){
           tvMaKHsp = v.findViewById(R.id.tvMaKH_Sp);
           tvMaKHsp.setText(item.maKH + ". ");

           tvTenKHsp = v.findViewById(R.id.tvTenKH_Sp);
           tvTenKHsp.setText(item.tenKH);
       }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable  View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.khach_hang_item_spinner,null);
        }
        final KhachHang item = lists.get(position);
        if(item != null){
            tvMaKHsp = v.findViewById(R.id.tvMaKH_Sp);
            tvMaKHsp.setText(item.maKH + ". ");

            tvTenKHsp = v.findViewById(R.id.tvTenKH_Sp);
            tvTenKHsp.setText(item.tenKH);
        }
        return v;
    }
}
