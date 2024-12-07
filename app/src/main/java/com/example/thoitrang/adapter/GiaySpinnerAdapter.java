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
import com.example.thoitrang.model.Giay;

import java.util.ArrayList;

public class GiaySpinnerAdapter extends ArrayAdapter<Giay> {
    private Context context;
    private ArrayList<Giay> lists;
    TextView tvMaGiaysp, tvTenGiaysp;

    public GiaySpinnerAdapter(@NonNull Context context , ArrayList<Giay> lists) {
        super(context, 0,lists);
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.giay_item_spinner,null);
        }
        final Giay item = lists.get(position);
        if(item != null){
            tvMaGiaysp = v.findViewById(R.id.tvMaGiaySp);
            tvMaGiaysp.setText(item.maGiay+". ");
            tvTenGiaysp = v.findViewById(R.id.tvTenGiaySp);
            tvTenGiaysp.setText(item.tenGiay);
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable  View convertView, @NonNull  ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.giay_item_spinner,null);
        }
        final Giay item = lists.get(position);
        if(item != null){
            tvMaGiaysp = v.findViewById(R.id.tvMaGiaySp);
            tvMaGiaysp.setText(item.maGiay+". ");
            tvTenGiaysp = v.findViewById(R.id.tvTenGiaySp);
            tvTenGiaysp.setText(item.tenGiay);
        }
        return v;
    }
}
