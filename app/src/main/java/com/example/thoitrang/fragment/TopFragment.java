package com.example.thoitrang.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.thoitrang.R;
import com.example.thoitrang.adapter.TopAdapter;
import com.example.thoitrang.dao.ThongKeDAO;
import com.example.thoitrang.model.Top;

import java.util.ArrayList;


public class TopFragment extends Fragment {
ListView lv;
ArrayList<Top> list;
TopAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_top, container, false);
        ThongKeDAO thongKeDAO = new ThongKeDAO(getActivity());
        list = (ArrayList<Top>) thongKeDAO.getTop();
        lv = v.findViewById(R.id.lvTop);
        adapter = new TopAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
        return v;

    }
//    package com.example.mau.fragment;
//
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import com.example.mau.R;
//import com.example.mau.adapter.LoaiGiayAdapter;
//import com.example.mau.adapter.NhanVienAdapter;
//import com.example.mau.dao.NhanVienDAO;
//import com.example.mau.model.LoaiGiay;
//import com.example.mau.model.NhanVien;
//import com.google.android.material.textfield.TextInputEditText;
//
//import java.util.ArrayList;
//
//
//    public class NhanVienFragment extends Fragment {
//        TextInputEditText edUser, edHoTen, edPass, edRePass;
//        Button btnSave, btnCanel;
//        NhanVienDAO dao;
//        ListView lv;
//        ArrayAdapter<NhanVien> adapter;
//        ArrayList<NhanVien> list;
//        NhanVien item;
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//            final View v = inflater.inflate(R.layout.fragment_add_user, container, false);
//            edUser = v.findViewById(R.id.edUserName);
//            edHoTen = v.findViewById(R.id.edFullName);
//            edPass = v.findViewById(R.id.edPassword);
//            lv = v.findViewById(R.id.lvadd);
//            edRePass = v.findViewById(R.id.edRePassword);
//            btnSave = v.findViewById(R.id.btnAddUser);
//            btnCanel = v.findViewById(R.id.btnCancelUser);
//            dao = new NhanVienDAO(getActivity());
//            btnCanel.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    edUser.setText("");
//                    edPass.setText("");
//                    edRePass.setText("");
//                    edHoTen.setText("");
//                }
//            });
//            btnSave.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    NhanVien thuThu = new NhanVien();
//                    thuThu.maTT = edUser.getText().toString();
//                    thuThu.hoTen = edHoTen.getText().toString();
//                    thuThu.matKhau = edPass.getText().toString();
//                    if(validate()>0){
//                        if(dao.insert(thuThu) > 0){
//                            Toast.makeText(getActivity(),"Lưu Thành Công",Toast.LENGTH_SHORT).show();
//                            edUser.setText("");
//                            edHoTen.setText("");
//                            edPass.setText("");
//                            edRePass.setText("");
//                        }else{
//                            Toast.makeText(getActivity(),"Lưu Thất Bại",Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//                }
//            });
//            return v;
//        }
//        public int validate(){
//            int check =1;
//            if(edUser.getText().length() == 0 || edHoTen.getText().length() == 0 || edPass.getText().length() == 0 || edRePass.getText().length() == 0){
//                Toast.makeText(getContext(),"Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
//                check = -1;
//            }else{
//                String pass = edPass.getText().toString();
//                String rePass = edRePass.getText().toString();
//                if(!pass.equals(rePass)){
//                    Toast.makeText(getContext(),"Mật Khẩu không trùng khớp",Toast.LENGTH_SHORT).show();
//                    check = -1;
//                }
//            }
//            return check;
//        }
//
//    }
}