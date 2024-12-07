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
import com.example.thoitrang.model.LoaiGiay;

import java.util.ArrayList;

public class LoaiGiaySpinnerAdapter extends ArrayAdapter<LoaiGiay> {
    private Context context;
    private ArrayList<LoaiGiay> lists;
    TextView tvMaLoaiGiaySP, tvTenLoaiGiaySP;

    public LoaiGiaySpinnerAdapter(@NonNull Context context , ArrayList<LoaiGiay> lists) {
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
            v = inflater.inflate(R.layout.loai_giay_item_spinner,null);
        }
        final LoaiGiay item = lists.get(position);
        if(item != null){
            tvMaLoaiGiaySP = v.findViewById(R.id.tvMaLoaiGiaySp);
            tvMaLoaiGiaySP.setText(item.maLoai+". ");
            tvTenLoaiGiaySP = v.findViewById(R.id.tvTenLoaiGiaySp);
            tvTenLoaiGiaySP.setText(item.tenLoai);
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable  View convertView, @NonNull  ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loai_giay_item_spinner,null);
        }
        final LoaiGiay item = lists.get(position);
        if(item != null){
            tvMaLoaiGiaySP = v.findViewById(R.id.tvMaLoaiGiaySp);
            tvMaLoaiGiaySP.setText(item.maLoai+". ");
            tvTenLoaiGiaySP = v.findViewById(R.id.tvTenLoaiGiaySp);
            tvTenLoaiGiaySP.setText(item.tenLoai);
        }
        return v;
    }
}
