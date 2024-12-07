package com.example.thoitrang.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.thoitrang.LoginActivity;
import com.example.thoitrang.R;
import com.example.thoitrang.dao.NhanVienDAO;
import com.example.thoitrang.model.NhanVien;
import com.google.android.material.textfield.TextInputEditText;


public class ChangePassFragment extends Fragment {
TextInputEditText edPassOld, edPass, edRePass;
Button btnSave, btnCanel;
NhanVienDAO dao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_change_pass, container, false);
        edPassOld = v.findViewById(R.id.edPassOld);
        edPass = v.findViewById(R.id.edPassNew);
        edRePass = v.findViewById(R.id.edPassAgin);
        btnSave = v.findViewById(R.id.btnSavePass);
        btnCanel = v.findViewById(R.id.btnCanelPass);
        dao = new NhanVienDAO(getActivity());
        btnCanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edPassOld.setText("");
                edPass.setText("");
                edRePass.setText("");
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user = pref.getString("USERNAME","");
                if(validate()>0){
                    NhanVien thuThu = dao.getID(user);
                    thuThu.matKhau = edPass.getText().toString();
                    thuThu.matKhau = edRePass.getText().toString();
                    if(dao.updatePass(thuThu) > 0){
                        edPassOld.setText("");
                        edPass.setText("");
                        edRePass.setText("");

                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(getContext(), "Đổi Mật Khẩu Thành Công! Vui lòng đăng nhập lại...", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(),"Thay đổi mật khẩu thất bại",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return v;
    }
    public int validate(){
        int check = 1;
        if(edPassOld.getText().length()== 0 || edPass.getText().length()== 0 || edRePass.getText().length()==0){
            Toast.makeText(getContext(),"Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_LONG).show();
            check = -1;
        }else{
            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE",Context.MODE_PRIVATE);
            String passOld = pref.getString("PASSWORD","");
            String pass = edPass.getText().toString();
            String rePass = edRePass.getText().toString();
            if(!passOld.equals(edPassOld.getText().toString())){
                Toast.makeText(getContext(),"Mật khẩu cũ sai",Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if(!pass.equals(rePass)){
                Toast.makeText(getContext(),"Mật khẩu mới không trùng khớp",Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}