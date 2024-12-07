package com.example.thoitrang.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.thoitrang.R;
import com.example.thoitrang.adapter.GiaySpinnerAdapter;
import com.example.thoitrang.adapter.HoaDonAdapter;
import com.example.thoitrang.adapter.KhachHangSpinnerAdapter;
import com.example.thoitrang.adapter.NhanVienSpinnerAdapter;
import com.example.thoitrang.dao.GiayDAO;
import com.example.thoitrang.dao.HoaDonDAO;
import com.example.thoitrang.dao.KhachHangDAO;
import com.example.thoitrang.dao.NhanVienDAO;
import com.example.thoitrang.model.Giay;
import com.example.thoitrang.model.HoaDon;
import com.example.thoitrang.model.KhachHang;
import com.example.thoitrang.model.NhanVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class HoaDonFragment extends Fragment {
    int mYear,mMonth,mDay;
    ListView lv;
    ArrayList<HoaDon> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaHD, edNgayMua, edGiaMua;
    Spinner spKH, spGiay,spNhanVien;
//    TextView  tvGiaMua;
    CheckBox chkTrangThai;
    Button btnSave, btnCancel;

    static HoaDonDAO dao;
    HoaDonAdapter adapter;
    HoaDon item;


    KhachHangSpinnerAdapter khachHangSpinnerAdapter;
    ArrayList<KhachHang> listKhachHang;
    KhachHangDAO khachHangDAO;


    NhanVienSpinnerAdapter nhanVienSpinnerAdapter;
    NhanVienDAO NhanVienDAO;
    ArrayList<NhanVien> listNhanVien;

    GiaySpinnerAdapter giaySpinnerAdapter;
    ArrayList<Giay> listGiay;
    GiayDAO giayDAO;

    int maKhachHang, maGiay, maNV;
    String tienMua;
    int positionKH, positionGiay, positionNV;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_hoa_don, container, false);
        lv = v.findViewById(R.id.lvHoaDon);
        fab = v.findViewById(R.id.fab_HD);
        dao = new HoaDonDAO(getActivity());
        capNhatLv();
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
        dialog.setContentView(R.layout.hodon_dialog);
        edMaHD = dialog.findViewById(R.id.edMaHD);
        spKH = dialog.findViewById(R.id.spMaKH);
        spGiay = dialog.findViewById(R.id.spMaGiay);
        spNhanVien = dialog.findViewById(R.id.spMaNV);
        edNgayMua = dialog.findViewById(R.id.edNgayMua_HD);
        edGiaMua = dialog.findViewById(R.id.edGiaMua_HD);
        chkTrangThai = dialog.findViewById(R.id.chkTrangThai);
        btnCancel = dialog.findViewById(R.id.btnCancel_HD_dialog);
        btnSave = dialog.findViewById(R.id.btnSave_HD_dialog);
        khachHangDAO = new KhachHangDAO(context);
        listKhachHang = new ArrayList<KhachHang>();
        listKhachHang = (ArrayList<KhachHang>) khachHangDAO.getAll();
        khachHangSpinnerAdapter = new KhachHangSpinnerAdapter(context,listKhachHang);
        spKH.setAdapter(khachHangSpinnerAdapter);
        spKH.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maKhachHang = listKhachHang.get(position).maKH;
                Toast.makeText(context,"Chọn"+listKhachHang.get(position).tenKH,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        giayDAO = new GiayDAO(context);
        listGiay = new ArrayList<Giay>();
        listGiay = (ArrayList<Giay>) giayDAO.getAll();
        giaySpinnerAdapter = new GiaySpinnerAdapter(context, listGiay);
        spGiay.setAdapter(giaySpinnerAdapter);
        spGiay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maGiay = listGiay.get(position).maGiay;
                tienMua = String.valueOf(listGiay.get(position).giaMua);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        NhanVienDAO = new NhanVienDAO(context);
        listNhanVien = new ArrayList<NhanVien>();
        listNhanVien = (ArrayList<NhanVien>) NhanVienDAO.getAll();
        nhanVienSpinnerAdapter = new NhanVienSpinnerAdapter(context, listNhanVien);
        spNhanVien.setAdapter(nhanVienSpinnerAdapter);
        spNhanVien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maNV = listNhanVien.get(position).maNV;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        edNgayMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(), 0,mDateNgay,mYear,mMonth,mDay);
                d.show();
            }
        });

        edMaHD.setEnabled(false);
        if(type != 0){
            edMaHD.setText(String.valueOf(item.maHD));
            edNgayMua.setText(item.ngay);
            for(int i=0;i<listKhachHang.size();i++)
                if(item.maKH == (listKhachHang.get(i).maKH)){
                    positionKH = i;
                }
            spKH.setSelection(positionKH);
            for(int i=0;i<listGiay.size();i++){
                if(item.maGiay == (listGiay.get(i).maGiay)){
                    positionGiay = i;
                }
            }
            spGiay.setSelection(positionGiay);
            for(int i=0;i<listNhanVien.size();i++){
                if(item.maNV == (listNhanVien.get(i).maNV)){
                    positionNV = i;
                }
            }
            spNhanVien.setSelection(positionNV);
            edGiaMua.setText(item.giaHD);
            if(item.maGiay==1){
                chkTrangThai.setChecked(true);
            }else{
                chkTrangThai.setChecked(false);
            }
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                item = new HoaDon();
                item.maGiay=maGiay;
                item.maKH=maKhachHang;
                item.maNV=maNV;
//                item.setMaGiay(maGiay);
//                item.setMaKH(maKhachHang);
//                item.setMaNV(maNV);
                item.ngay = edNgayMua.getText().toString();
                item.giaHD = tienMua;
                if(chkTrangThai.isChecked()){
                    item.trangThai=1;
                }else{
                    item.trangThai=0;
                }
                if(valible()>0){
                    if(type==0){
                        if(dao.insert(item)>0){
                            Toast.makeText(context,"Thêm Thành Công",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context,"Thêm Thất Bại",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        item.maHD = Integer.parseInt(edMaHD.getText().toString());
                        if(dao.update(item)>0){
                            Toast.makeText(context,"Sửa Thành Công",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context,"Sửa Thất Bại",Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public void xoaHoaDon(final String Id){
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
        list = (ArrayList<HoaDon>) dao.getAll();
        adapter = new HoaDonAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
        return;
    }
    public int valible(){
        int check = 1;
        if(edNgayMua.getText().length() == 0){
            Toast.makeText(getContext(),"Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
    DatePickerDialog.OnDateSetListener mDateNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(mYear, mMonth,mDay);
            edNgayMua.setText(sdf.format(c.getTime()));
        }
    };
}


