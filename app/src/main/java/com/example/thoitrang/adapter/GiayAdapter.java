package com.example.thoitrang.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.thoitrang.R;
import com.example.thoitrang.dao.LoaiGiayDAO;
import com.example.thoitrang.fragment.GiayFragment;
import com.example.thoitrang.model.Giay;
import com.example.thoitrang.model.LoaiGiay;

import java.util.ArrayList;

public class GiayAdapter extends ArrayAdapter<Giay> {

    private Context context;
    GiayFragment fragment;
    private ArrayList<Giay> lists;
    TextView tvMaGiay, tvTenGiay, tvGiaMua, tvMaLoaiGiay, tvMoTa, tvSoLuong;
    ImageView imgDeleteGiay, imgHinh;

    public GiayAdapter(@NonNull Context context, GiayFragment fragment, ArrayList<Giay> lists){
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
            v = inflater.inflate(R.layout.giay_item,null);
        }
        final Giay item = lists.get(position);
        if(item != null){
            LoaiGiayDAO loaiGiayDAO = new LoaiGiayDAO(context);
            LoaiGiay loaiGiay = loaiGiayDAO.getID(String.valueOf(item.maLoai));

            tvMaGiay = v.findViewById(R.id.tv_maGiay_item);
            tvMaGiay.setText("Mã : "+item.maGiay);

            tvTenGiay = v.findViewById(R.id.tv_tenGiay_item);
            tvTenGiay.setText("Tên: "+item.tenGiay);

            tvSoLuong = v.findViewById(R.id.tv_soLuongG_item);
            tvSoLuong.setText("So Lượng: "+item.soLuong);

            tvGiaMua = v.findViewById(R.id.tv_giaMua_item);
            tvGiaMua.setText("Giá Mua: "+item.giaMua+" VNĐ");

            tvMaLoaiGiay = v.findViewById(R.id.tv_maLoaiGiay_item);
            tvMaLoaiGiay.setText("Loại : "+loaiGiay.tenLoai);

            tvMoTa = v.findViewById(R.id.tv_moTa_item);
            tvMoTa.setText("Mô tả: "+item.moTa);

            imgHinh = v.findViewById(R.id.img_giay_item);
            byte[] hinhGiay = item.hinh;
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhGiay, 0, hinhGiay.length);
            imgHinh.setImageBitmap(bitmap);

            imgDeleteGiay = v.findViewById(R.id.img_delete_giay_item);
        }

        imgDeleteGiay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment.xoaGiay(String.valueOf(item.maGiay));
            }
        });

//        if (position % 2 == 0){
//
//        }

        return v;

    }

}
