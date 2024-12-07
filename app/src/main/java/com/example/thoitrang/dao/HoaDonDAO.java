package com.example.thoitrang.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.thoitrang.database.DbHelper;
import com.example.thoitrang.model.HoaDon;

import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {
    private SQLiteDatabase db;

    public HoaDonDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(HoaDon ob){
        ContentValues values = new ContentValues();
        values.put("maNV", ob.maNV);
        values.put("maKH",ob.maKH);
        values.put("maGiay",ob.maGiay);
        values.put("ngay",ob.ngay);
        values.put("giaHD",ob.giaHD);
        values.put("trangThai",ob.trangThai);
        return db.insert("HoaDon",null,values);
    }

    public int update(HoaDon ob){
        ContentValues values = new ContentValues();
        values.put("maNV", ob.maNV);
        values.put("maKH",ob.maKH);
        values.put("maGiay",ob.maGiay);
        values.put("ngay",ob.ngay);
        values.put("giaHD",ob.giaHD);
        values.put("trangThai",ob.trangThai);
        return db.update("HoaDon",values,"maHD=?", new String[]{String.valueOf(ob.maHD)});
    }

    public int delete(String id){
        return db.delete("HoaDon","maHD=?", new String[]{id});
    }

    public List<HoaDon> getAll(){
        String sql = "SELECT * FROM HoaDon";
        return getData(sql);
    }

    public HoaDon getID(String id){
        String sql = "SELECT * FROM HoaDon WHERE maHD=?";
        List<HoaDon> list = getData(sql, id);
        return list.get(0);
    }

    private List<HoaDon> getData(String sql, String...selectionArgs){
        List<HoaDon> list = new ArrayList<HoaDon>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){
            HoaDon ob = new HoaDon();
            ob.maHD = Integer.parseInt(c.getString(c.getColumnIndex("maHD")));
            ob.maNV = Integer.parseInt(c.getString(c.getColumnIndex("maNV")));
            ob.maKH = Integer.parseInt(c.getString(c.getColumnIndex("maKH")));
            ob.maGiay = Integer.parseInt(c.getString(c.getColumnIndex("maGiay")));
            ob.ngay = c.getString(c.getColumnIndex("ngay"));
            ob.giaHD = c.getString(c.getColumnIndex("giaHD"));
            ob.trangThai = Integer.parseInt(c.getString(c.getColumnIndex("trangThai")));

            list.add(ob);
        }
        return list;
    }
}
