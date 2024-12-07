package com.example.thoitrang.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.thoitrang.database.DbHelper;
import com.example.thoitrang.model.KhachHang;

import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {
    private SQLiteDatabase db;

    public KhachHangDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(KhachHang ob){
        ContentValues values = new ContentValues();
        values.put("tenKH",ob.tenKH);
        values.put("diaChi",ob.diaChi);
        values.put("sdtKH",ob.sdtKH);
        return db.insert("KhachHang",null,values);
    }

    public int update(KhachHang ob){
        ContentValues values = new ContentValues();
        values.put("tenKH",ob.tenKH);
        values.put("diaChi",ob.diaChi);
        values.put("sdtKH",ob.sdtKH);
        return db.update("KhachHang",values,"maKH=?", new String[]{String.valueOf(ob.maKH)});
    }

    public int delete(String id){
        return db.delete("KhachHang","maKH=?", new String[]{id});
    }

    public List<KhachHang> getAll(){
        String sql = "SELECT * FROM KhachHang";
        return getData(sql);
    }

    public KhachHang getID(String id){
        KhachHang  obKhachHang = new KhachHang();
        String[] args = new String[]{ id + ""};
        Cursor c = db.rawQuery("SELECT maKH, tenKH, diaChi, sdtKH FROM KhachHang WHERE maKH=?", args);
        if(c.moveToFirst()){
            obKhachHang.maKH = c.getInt(0);
            obKhachHang.tenKH = c.getString(1);
            obKhachHang.sdtKH = c.getString(2);
            obKhachHang.diaChi = c.getString(3);
        }
        return obKhachHang;
    }

   private List<KhachHang> getData(String sql, String...selectionArgs){
        List<KhachHang> list = new ArrayList<KhachHang>();
       Cursor c = db.rawQuery(sql, selectionArgs);
       while (c.moveToNext()){
           KhachHang ob = new KhachHang();
           ob.maKH = Integer.parseInt(c.getString(c.getColumnIndex("maKH")));
           ob.tenKH = c.getString(c.getColumnIndex("tenKH"));
           ob.diaChi = c.getString(c.getColumnIndex("diaChi"));
           ob.sdtKH = c.getString(c.getColumnIndex("sdtKH"));
           list.add(ob);
       }
       return list;
   }

   public List<KhachHang> getSearch_khachHang(String tenKH){
        String sql = "SELECT * FROM KhachHang WHERE tenKH LIKE '%"+tenKH+"%' ";
        return getData(sql);
   }
}
