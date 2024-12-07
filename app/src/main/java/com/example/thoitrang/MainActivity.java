package com.example.thoitrang;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.example.thoitrang.fragment.ChangePassFragment;
import com.example.thoitrang.fragment.DoanhThuFragment;
import com.example.thoitrang.fragment.GiayFragment;
import com.example.thoitrang.fragment.HoaDonFragment;
import com.example.thoitrang.fragment.KhachHangFragment;
import com.example.thoitrang.fragment.LoaiGiayFragment;
import com.example.thoitrang.fragment.NhanVienFragment;
import com.example.thoitrang.fragment.TopFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    MaterialToolbar toolbar;
    View mHeaderView;
    TextView edUser;
    private NavigationView nv;
    private BottomNavigationView bm;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

 //        FULL MÀN HÌNH
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//=================

        setContentView(R.layout.activity_main);
        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        nv = findViewById(R.id.nvView);
        bm = findViewById(R.id.btt_nav);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.menu_tab_bar);
        ab.setDisplayHomeAsUpEnabled(true);
        FragmentManager manager = getSupportFragmentManager();
        HoaDonFragment hoaDonFragment = new HoaDonFragment();
        manager.beginTransaction()
                .replace(R.id.flContent,hoaDonFragment)
                .commit();

        nv.setCheckedItem(R.id.nav_HoaDon);
        bm.getMenu().findItem(R.id.navb_HoaDon).setChecked(true);
        mHeaderView = nv.getHeaderView(0);
        edUser = mHeaderView.findViewById(R.id.tvUser);
        Intent i = getIntent();
        String user = i.getStringExtra("user");
        edUser.setText("Welcome "+user+"!");

        if(user.equalsIgnoreCase("admin")){
            nv.getMenu().findItem(R.id.nav_sub_NhanVien).setVisible(true);
        }
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull  MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();
                switch (item.getItemId()){
                    case R.id.nav_HoaDon:
                        HoaDonGE();
                        nv.setCheckedItem(R.id.nav_HoaDon);
                        bm.getMenu().findItem(R.id.navb_HoaDon).setChecked(true);
                        break;
                    case R.id.nav_LoaiGiay:
                        LoaiGiayGE();
                        nv.setCheckedItem(R.id.nav_LoaiGiay);
                        bm.getMenu().findItem(R.id.navb_LoaiGiay).setChecked(true);
                        break;
                    case R.id.nav_Giay:
                        GiayGE();
                        nv.setCheckedItem(R.id.nav_Giay);
                        bm.getMenu().findItem(R.id.navb_Giay).setChecked(true);
                        break;
                    case R.id.nav_KhachHang:
                        KhachHangGE();
                        nv.setCheckedItem(R.id.nav_KhachHang);
                        break;
                    case R.id.nav_sub_Top:
                        TopGE();
                        nv.setCheckedItem(R.id.nav_sub_Top);
                        bm.getMenu().findItem(R.id.navb_Top).setChecked(true);
                        break;
                    case R.id.nav_sub_DoanhThu:
                        DoanhThuGE();
                        nv.setCheckedItem(R.id.nav_sub_DoanhThu);
                        break;
                    case R.id.nav_sub_NhanVien:
                        NhanVienGE();
                        nv.setCheckedItem(R.id.nav_sub_NhanVien);
                        break;
                    case R.id.nav_sub_Pass:
                        ChangePassGE();
                        nv.setCheckedItem(R.id.nav_sub_Pass);
                        break;
                    case R.id.nav_sub_Logout:
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setIcon(android.R.drawable.ic_menu_set_as);
                        builder.setTitle( Html.fromHtml("<font color='#d73a31'>Bạn muốn đăng xuất khỏi ứng dụng ?</font>"));
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                finish();
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.show();
                        break;
                }
                drawer.closeDrawers();
                return false;
            }
        });
        bm.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();
                switch (item.getItemId()){
                    case R.id.navb_Giay:
                        GiayGE();
                        nv.setCheckedItem(R.id.nav_Giay);
                        break;
                    case R.id.navb_LoaiGiay:
                        LoaiGiayGE();
                        nv.setCheckedItem(R.id.nav_LoaiGiay);
                        break;
                    case R.id.navb_HoaDon:
                        HoaDonGE();
                        nv.setCheckedItem(R.id.nav_HoaDon);
                        break;
                    case R.id.navb_Top:
                        TopGE();
                        nv.setCheckedItem(R.id.nav_sub_Top);
                        break;
                }
                return true;
            }
        });

    }
    private void HoaDonGE(){
        FragmentManager manager = getSupportFragmentManager();
        setTitle("Quản Lý Hóa Đơn");
        HoaDonFragment hoaDonFragment = new HoaDonFragment();
        manager.beginTransaction()
                .replace(R.id.flContent,hoaDonFragment)
                .commit();
    }
    private void GiayGE(){
        FragmentManager manager = getSupportFragmentManager();
        setTitle("Quản Lý Sản Phẩm");
        GiayFragment giayFragment = new GiayFragment();
        manager.beginTransaction()
                .replace(R.id.flContent,giayFragment)
                .commit();
    }
    private void LoaiGiayGE(){
        FragmentManager manager = getSupportFragmentManager();
        setTitle("Quản Lý Loại ");
        LoaiGiayFragment loaiGiayFragment = new LoaiGiayFragment();
        manager.beginTransaction()
                .replace(R.id.flContent,loaiGiayFragment)
                .commit();
    }
    private void TopGE(){
        FragmentManager manager = getSupportFragmentManager();
        setTitle("Top 10 Mua Nhiều Nhất");
        TopFragment topFragment = new TopFragment();
        manager.beginTransaction()
                .replace(R.id.flContent,topFragment)
                .commit();
    }
    private void DoanhThuGE(){
        FragmentManager manager = getSupportFragmentManager();
        setTitle("Thông Kê Doanh Thu");
        DoanhThuFragment doanhThuFragment = new DoanhThuFragment();
        manager.beginTransaction()
                .replace(R.id.flContent,doanhThuFragment)
                .commit();
    }
    private void KhachHangGE() {
        FragmentManager manager = getSupportFragmentManager();
        setTitle("Quản Lý Khách Hàng");
        KhachHangFragment khachHangFragment = new KhachHangFragment();
        manager.beginTransaction()
                .replace(R.id.flContent, khachHangFragment)
                .commit();
    }
    private void NhanVienGE(){
        FragmentManager manager = getSupportFragmentManager();
        setTitle("Quản Lý Nhân Viên");
        NhanVienFragment nhanVienFragment = new NhanVienFragment();
        manager.beginTransaction()
                .replace(R.id.flContent,nhanVienFragment)
                .commit();
    }
    private void ChangePassGE(){
        FragmentManager manager = getSupportFragmentManager();
        setTitle("Thay Đổi Mật Khẩu");
        ChangePassFragment changePassFragment = new ChangePassFragment();
        manager.beginTransaction()
                .replace(R.id.flContent,changePassFragment)
                .commit();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home)
            drawer.openDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }
    //Out Ứng Dụng
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Click 2 Lần Để Thoát", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
