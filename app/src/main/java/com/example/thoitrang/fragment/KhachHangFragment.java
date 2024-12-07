package com.example.thoitrang.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.thoitrang.R;
import com.example.thoitrang.adapter.KhachHangAdapter;
import com.example.thoitrang.dao.KhachHangDAO;
import com.example.thoitrang.model.KhachHang;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class KhachHangFragment extends Fragment {

ListView lv;
ArrayList<KhachHang> list;
FloatingActionButton fab;
Dialog dialog;
EditText edMaKH, edTenKH, eddiaChiKH, edSDTKH, edt_search;
Button btnSave, btnCancel;
ImageView img_search;

static KhachHangDAO dao;
KhachHangAdapter adapter;
KhachHang item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_khach_hang, container, false);
        lv = v.findViewById(R.id.lvKhachHang);
        fab = v.findViewById(R.id.fab_KH);
        edt_search = v.findViewById(R.id.ed_search_khacHang);
        img_search = v.findViewById(R.id.img_search_khachHang);
        dao = new KhachHangDAO(getActivity());
        capNhatLv();

//        tim kiem Khach hang theo hoTen Khach hang
        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_search.getText().length() == 0){
                    Toast.makeText(getContext(), "Vui lòng nhập thông tin trước khi Search", Toast.LENGTH_SHORT).show();
                }
                capNhatTen_khachHang();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               item = list.get(position);
               openDialog(getActivity(),1);
               return false;
            }
        });
        return v;
    }

    protected void openDialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.khach_hang_dialog);
        edMaKH = dialog.findViewById(R.id.edMaKH_dilog);
        edTenKH = dialog.findViewById(R.id.edTenKH_dialog);
        edSDTKH = dialog.findViewById(R.id.edSDT_KH_dialog);
        eddiaChiKH = dialog.findViewById(R.id.eddiaChiKH_dialog);
        btnCancel = dialog.findViewById(R.id.btnCancel_KH_dialog);
        btnSave = dialog.findViewById(R.id.btnSave_KH_dialog);

        edMaKH.setEnabled(false);
        if(type != 0){
            edMaKH.setText(String.valueOf(item.maKH));
            edTenKH.setText(item.tenKH);
            edSDTKH.setText(item.sdtKH);
            eddiaChiKH.setText(item.diaChi);
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new KhachHang();
                item.tenKH = edTenKH.getText().toString();
                item.diaChi = eddiaChiKH.getText().toString();
                item.sdtKH = edSDTKH.getText().toString();
                if(validate()>0){
                    if(type == 0){
                        if(dao.insert(item)>0){
                            Toast.makeText(context,"Thêm Thành Công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context,"Thêm Thất Bại",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        item.maKH = Integer.parseInt(edMaKH.getText().toString());
                        if(dao.update(item)>0){
                            Toast.makeText(context,"Cập Nhật Thành Công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context,"Cập Nhật Thất Bại",Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public void xoaKhachHang(final String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(Id);
                capNhatLv();
                Snackbar.make(getView(),"Bạn đã xóa thành công",Snackbar.LENGTH_LONG).show();
                dialog.cancel();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        builder.show();
    }

    void capNhatLv(){
        list = (ArrayList<KhachHang>)dao.getAll();
        adapter = new KhachHangAdapter(getActivity(), this,list);
        lv.setAdapter(adapter);
        return;
    }

    void capNhatTen_khachHang(){
        list = (ArrayList<KhachHang>)dao.getSearch_khachHang(edt_search.getText().toString());
        adapter = new KhachHangAdapter(getActivity(), this,list);
        lv.setAdapter(adapter);
        return;
    }

    public int validate(){
        int check = 1;
        if(edTenKH.getText().length() == 0||eddiaChiKH.getText().length()==0 || edSDTKH.getText().length() == 0){
            Toast.makeText(getContext(),"Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}