package com.example.thoitrang.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.thoitrang.database.DbHelper;
import com.example.thoitrang.model.LoaiGiay;

import java.util.ArrayList;
import java.util.List;

public class LoaiGiayDAO {
    private SQLiteDatabase db;

    public LoaiGiayDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(LoaiGiay ob){
        ContentValues values = new ContentValues();
        values.put("tenLoai", ob.tenLoai);
        return db.insert("LoaiGiay",null,values);
    }

    public int update(LoaiGiay ob){
        ContentValues values = new ContentValues();
        values.put("tenLoai",ob.tenLoai);
        return db.update("LoaiGiay",values,"maLoai=?", new String[]{String.valueOf(ob.maLoai)});
    }

    public int delete(String id){
        return db.delete("LoaiGiay","maLoai=?", new String[]{id});
    }

    public List<LoaiGiay> getAll(){
        String sql = "SELECT * FROM LoaiGiay";
        return getData(sql);
    }

    public LoaiGiay getID(String id){
        LoaiGiay objLoaiGiay = new LoaiGiay();
        String[] args = new String[]{id + ""};
        Cursor c = db.rawQuery("SELECT maLoai, tenLoai FROM LoaiGiay WHERE maLoai=?", args);
        if(c.moveToFirst()){
            objLoaiGiay.maLoai = c.getInt(0);
            objLoaiGiay.tenLoai = c.getString(1);
        }
        return objLoaiGiay;
    }

   private List<LoaiGiay> getData(String sql, String...selectionArgs){
        List<LoaiGiay> list = new ArrayList<LoaiGiay>();
       Cursor c = db.rawQuery(sql, selectionArgs);
       while (c.moveToNext()){
           LoaiGiay ob = new LoaiGiay();
           ob.maLoai = Integer.parseInt(c.getString(c.getColumnIndex("maLoai")));
           ob.tenLoai = c.getString(c.getColumnIndex("tenLoai"));
           list.add(ob);
       }
       return list;
   }

   public List<LoaiGiay> getSearch_loaiGiay(String tenLoai){
        String sql = "SELECT * FROM LoaiGiay WHERE tenLoai LIKE '%"+tenLoai+"%' ";
        return getData(sql);
   }

}
