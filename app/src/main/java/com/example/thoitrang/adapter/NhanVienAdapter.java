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
import com.example.thoitrang.fragment.NhanVienFragment;
import com.example.thoitrang.model.NhanVien;

import java.util.ArrayList;

public class NhanVienAdapter extends ArrayAdapter<NhanVien> {

    private Context context;
    NhanVienFragment fragment;
    private ArrayList<NhanVien> lists;
    TextView tvMaNV, tvhoTenNV, tvMatKhau, tvtenDN, tvsdtNV;
    ImageView imgDeleteNV, img_Hinh_NV;

    public NhanVienAdapter(@NonNull Context context, NhanVienFragment fragment, ArrayList<NhanVien> lists){
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
            v = inflater.inflate(R.layout.nhanvien_item,null);
        }

        final NhanVien item = lists.get(position);
        if(item != null){
            tvMaNV = v.findViewById(R.id.tvMaNV_item);
            tvMaNV.setText("ID: "+item.maNV);

            tvhoTenNV = v.findViewById(R.id.tvTenNV_item);
            tvhoTenNV.setText("Full name: "+item.hoTenNV);

            tvMatKhau = v.findViewById(R.id.tvMatKhau_item);
            tvMatKhau.setText("Password: "+item.matKhau);

            tvtenDN = v.findViewById(R.id.tv_tenDN_item);
            tvtenDN.setText("Username: "+item.tenDN);

            tvsdtNV = v.findViewById(R.id.tv_SDT_NV_item);
            tvsdtNV.setText("Phone: "+item.sdtNV);

            img_Hinh_NV = v.findViewById(R.id.img_nhanVien_item);
            byte[] hinh_NV = item.hinhNV;
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinh_NV, 0, hinh_NV.length);
            img_Hinh_NV.setImageBitmap(bitmap);

            imgDeleteNV = v.findViewById(R.id.img_delete_nhanVien);

//            if(so%2 == 0){
//                v.setBackgroundColor(Color.GREEN);
//            }else{
//                v.setBackgroundColor(Color.RED);
//            }

        }

        imgDeleteNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.maNV));
            }
        });
        return v;

    }
}
