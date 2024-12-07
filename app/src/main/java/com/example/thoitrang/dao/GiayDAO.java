package com.example.thoitrang.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.thoitrang.database.DbHelper;
import com.example.thoitrang.model.Giay;

import java.util.ArrayList;
import java.util.List;

public class GiayDAO {
    private SQLiteDatabase db;

    public GiayDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insert(Giay ob){
        ContentValues values = new ContentValues();
        values.put("tenGiay",ob.tenGiay);
        values.put("giaMua",ob.giaMua);
        values.put("moTa",ob.moTa);
        values.put("soLuong",ob.soLuong);
        values.put("maLoai", ob.maLoai);
        values.put("hinh", ob.hinh);
        return db.insert("Giay",null,values);
    }
    public int update(Giay ob){
        ContentValues values = new ContentValues();
        values.put("tenGiay",ob.tenGiay);
        values.put("giaMua",ob.giaMua);
        values.put("moTa",ob.moTa);
        values.put("soLuong",ob.soLuong);
        values.put("maLoai", ob.maLoai);
        values.put("hinh", ob.hinh);
        return db.update("Giay",values,"maGiay=?", new String[]{String.valueOf(ob.maGiay)});
    }
    public int delete(String id){
        return db.delete("Giay","maGiay=?", new String[]{id});
    }

    public List<Giay> getAll(){
        String sql = "SELECT * FROM Giay";
        return getData(sql);
    }

    public Giay getID(String id){
        Giay objGiay = new Giay();
        String[] argss = new String[]{ id + ""};
    Cursor c = db.rawQuery("SELECT maGiay, tenGiay, giaMua, moTa, soLuong, maLoai, hinh FROM Giay WHERE maGiay=?", argss);
    if(c.moveToFirst()){
        objGiay.maGiay = c.getInt(0);
        objGiay.tenGiay = c.getString(1);
        objGiay.giaMua = c.getString(2);
        objGiay.moTa = c.getString(3);
        objGiay.soLuong = c.getString(4);
        objGiay.maLoai = c.getInt(5);
        objGiay.hinh = c.getBlob(6);
    }
    return objGiay;
}

//Get data nhieu tham so
   private List<Giay> getData(String sql, String...selectionArgs){
        List<Giay> list = new ArrayList<Giay>();
       Cursor c = db.rawQuery(sql, selectionArgs);
       while (c.moveToNext()){
          Giay ob = new Giay();
           ob.maGiay = Integer.parseInt(c.getString(c.getColumnIndex("maGiay")));
           ob.tenGiay = c.getString(c.getColumnIndex("tenGiay"));
           ob.giaMua = c.getString(c.getColumnIndex("giaMua"));
           ob.moTa = c.getString(c.getColumnIndex("moTa"));
           ob.soLuong = c.getString(c.getColumnIndex("soLuong"));
           ob.maLoai = Integer.parseInt(c.getString(c.getColumnIndex("maLoai")));
           ob.hinh = c.getBlob(c.getColumnIndex("hinh"));
           list.add(ob);
       }
       return list;
   }

   public List<Giay> getSearch_Giay(String tenGiay){
        String sql = "SELECT * FROM Giay WHERE tenGiay LIKE '%"+tenGiay+"%' ";
        return getData(sql);
    }
}
