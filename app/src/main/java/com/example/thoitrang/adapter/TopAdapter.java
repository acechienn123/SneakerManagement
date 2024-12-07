package com.example.thoitrang.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.thoitrang.R;
import com.example.thoitrang.fragment.TopFragment;
import com.example.thoitrang.model.Top;

import java.util.ArrayList;

public class TopAdapter extends ArrayAdapter<Top> {
    private Context context;
    TopFragment fragment;
    private ArrayList<Top> lists;
    TextView tvTenGiay, tvSL;

    public TopAdapter(@NonNull Context context, TopFragment fragment, ArrayList<Top> lists) {
        super(context, 0,lists);
        this.context = context;
        this.lists = lists;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.top_item,null);
        }
        final Top item = lists.get(position);
        if(item != null){
            tvTenGiay = v.findViewById(R.id.tvTenGiayT);
            tvTenGiay.setText("Tên: "+item.tenGiay);
            tvSL = v.findViewById(R.id.tvsoLuong);
            tvSL.setText("Số Lượng: "+item.slTOP);
            tvTenGiay.setTextColor(Color.BLUE);
            tvSL.setTextColor(Color.BLUE);
        }
        return v;
    }

}
