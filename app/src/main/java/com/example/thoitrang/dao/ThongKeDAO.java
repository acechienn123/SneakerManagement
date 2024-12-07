package com.example.thoitrang.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.thoitrang.database.DbHelper;
import com.example.thoitrang.model.Giay;
import com.example.thoitrang.model.Top;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDAO {
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public ThongKeDAO(Context context) {
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

//    thong ke TOP5
    public List<Top> getTop(){
        String sqlTop = "SELECT maGiay, count(maGiay) as slTOP FROM HoaDon GROUP BY maGiay ORDER BY slTOP DESC LIMIT 5";
        List<Top> list = new ArrayList<Top>();
        GiayDAO giayDAO = new GiayDAO(context);
        Cursor c = db.rawQuery(sqlTop, null);

        while (c.moveToNext()){
            Top top = new Top();
            Giay giay = giayDAO.getID(c.getString(c.getColumnIndex("maGiay")));
            top.tenGiay = giay.tenGiay;
            top.slTOP = Integer.parseInt(c.getString(c.getColumnIndex("slTOP")));
            list.add(top);
        }
      return list;
    }

    public int getDanhThu(String tuNgay, String denNgay){
        String sqlDoanhThu = "SELECT SUM(giaHD) as doanhThu FROM HoaDon WHERE ngay BETWEEN ? AND ?";
        List<Integer> list = new ArrayList<>();
        Cursor c = db.rawQuery(sqlDoanhThu, new String[]{tuNgay,denNgay});

        while (c.moveToNext()){
            try{
                list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhThu"))));
            }catch (Exception e){
                list.add(0);
            }
        }
        c.close();
        return list.get(0);
    }
}
