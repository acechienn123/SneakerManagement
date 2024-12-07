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
import com.example.thoitrang.model.NhanVien;

import java.util.ArrayList;

public class NhanVienSpinnerAdapter extends ArrayAdapter<NhanVien> {
    private Context context;
    private ArrayList<NhanVien> lists;
    TextView tvmaNV_Sp, tvhoTen_NV_Sp;

    public NhanVienSpinnerAdapter(@NonNull Context context , ArrayList<NhanVien> lists) {
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
            v = inflater.inflate(R.layout.nhan_vien_item_spinner,null);
        }

        final NhanVien item = lists.get(position);
        if(item != null){
            tvmaNV_Sp = v.findViewById(R.id.tvmaNV_Sp);
            tvmaNV_Sp.setText(item.maNV + ". ");

            tvhoTen_NV_Sp = v.findViewById(R.id.tvhoTen_NV_Sp);
            tvhoTen_NV_Sp.setText(item.hoTenNV);
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable  View convertView, @NonNull  ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.nhan_vien_item_spinner,null);
        }
        final NhanVien item = lists.get(position);
        if(item != null){
            tvmaNV_Sp = v.findViewById(R.id.tvmaNV_Sp);
            tvmaNV_Sp.setText(item.maNV + ". ");

            tvhoTen_NV_Sp = v.findViewById(R.id.tvhoTen_NV_Sp);
            tvhoTen_NV_Sp.setText(item.hoTenNV);
        }
        return v;
    }
}
