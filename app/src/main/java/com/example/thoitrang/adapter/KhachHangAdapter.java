package com.example.thoitrang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.thoitrang.R;
import com.example.thoitrang.fragment.KhachHangFragment;
import com.example.thoitrang.model.KhachHang;

import java.util.ArrayList;

public class KhachHangAdapter extends ArrayAdapter<KhachHang> {
    private Context context;
    KhachHangFragment fragment;
    LinearLayout linearLayout;
    private ArrayList<KhachHang> lists;
    TextView tvMaKH, tvTenKH, tvsdtKH, tvdiaChi;
    ImageView imgDeleteKH;
    public KhachHangAdapter(@NonNull Context context, KhachHangFragment fragment, ArrayList<KhachHang> lists){
        super(context,0,lists);
        this.context = context;
        this.lists = lists;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.khach_hang_item,null);
        }
        final KhachHang item = lists.get(position);
        if(item != null){
            tvMaKH = v.findViewById(R.id.tvMaKH_item);
            tvMaKH.setText("ID: "+item.maKH);

            tvTenKH = v.findViewById(R.id.tvtenKH_item);
            tvTenKH.setText("Name: "+item.tenKH);

            tvsdtKH = v.findViewById(R.id.tv_SDT_KH_item);
            tvsdtKH.setText("Phone: "+item.sdtKH);

            tvdiaChi = v.findViewById(R.id.tvDiaChiKH_item);
            tvdiaChi.setText("Address: "+item.diaChi);

            imgDeleteKH = v.findViewById(R.id.img_delete_KH);

        }

        imgDeleteKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoaKhachHang(String.valueOf(item.maKH));
            }
        });

        return v;
    }


}
