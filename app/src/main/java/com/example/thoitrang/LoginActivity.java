package com.example.thoitrang;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thoitrang.dao.NhanVienDAO;
import com.google.android.material.textfield.TextInputEditText;


public class LoginActivity extends AppCompatActivity {
TextInputEditText edUserName, edPassword;
Button btnLogin;
CheckBox chkRemmemberPass;
String strUser, strPass;
NhanVienDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUserName = findViewById(R.id.edUserName);
        edPassword = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);
        chkRemmemberPass = findViewById(R.id.chkRememberPass);
        dao = new NhanVienDAO(this);
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        edUserName.setText(pref.getString("USERNAME",""));
        edPassword.setText(pref.getString("PASSWORD",""));
        chkRemmemberPass.setChecked(pref.getBoolean("REMEMBER",false));
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });

    }
    public void checkLogin(){
        strUser = edUserName.getText().toString();
        strPass = edPassword.getText().toString();
        if(strUser.isEmpty()||strPass.isEmpty()){
            Toast.makeText(getApplicationContext(),"Yêu Cầu Nhập",Toast.LENGTH_SHORT).show();
        }else{
            if(dao.checkLogin(strUser,strPass)>0 || (strUser.equals("admin")&& strPass.equals("admin"))){
                Toast.makeText(getApplicationContext(),"Login thành công",Toast.LENGTH_SHORT).show();
                rememberUser(strUser, strPass, chkRemmemberPass.isChecked());
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                i.putExtra("user",strUser);
                startActivity(i);
                finish();
            }else if(dao.checkLogin(strUser,strPass)>0 || (strUser.equals("admin"))){
                Toast.makeText(getApplicationContext()," Sai Mật Khẩu",Toast.LENGTH_SHORT).show();
            }else if(dao.checkLogin(strUser,strPass)>0 || (strPass.equals("admin"))){
                Toast.makeText(getApplicationContext()," Sai Tên Đăng Nhập",Toast.LENGTH_SHORT).show();
            }

        }
    }
    public void rememberUser(String u, String p, boolean status){
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if(!status){
            edit.clear();
        }else{
            edit.putString("USERNAME",u);
            edit.putString("PASSWORD",p);
            edit.putBoolean("REMEMBER",status);
        }
        edit.commit();
    }

}
