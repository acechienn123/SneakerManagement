package com.example.thoitrang.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "TBSD_SNEAKER1";
    public static final int VERSION = 1;
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//      Tao bang NHAN VIEN
        String tableNhanVien=
                "CREATE TABLE NhanVien (" +
                        "maNV INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "hoTenNV TEXT NOT NULL, " +
                        "tenDN TEXT NOT NULL, " +
                        "matKhau TEXT NOT NULL, " +
                        "sdtNV TEXT NOT NULL, " +
                        "hinhNV BLOB NOT NULL)";
        db.execSQL(tableNhanVien);

//      Tao bang KHACH HANG
        String tableKhachHang=
                "CREATE TABLE KhachHang (" +
                        "maKH INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "tenKH TEXT NOT NULL, " +
                        "diaChi TEXT NOT NULL , " +
                        "sdtKH TEXT NOT NULL)";
        db.execSQL(tableKhachHang);

//      Tao bang LOAI GIAY
        String tableLoaiGiay=
                "CREATE TABLE LoaiGiay (" +
                        "maLoai INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "tenLoai TEXT NOT NULL)" ;
        db.execSQL(tableLoaiGiay);

        //bảng Giầy
        String tableGiay=
                "CREATE TABLE Giay(" +
                        "maGiay INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "tenGiay TEXT NOT NULL," +
                        "giaMua TEXT NOT NULL, "+
                        "moTa TEXT NOT NULL," +
                        "soLuong TEXT NOT NULL," +
                        "maLoai INTEGER REFERENCES LoaiGiay(maLoai), "+
                        "hinh BLOB NOT NULL )";
        db.execSQL(tableGiay);

//      Tao bang HOADON
        String tableHoaDon=
                "CREATE TABLE HoaDon (" +
                        "maHD INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "maNV INTEGER REFERENCES NhanVien(maNV), " +
                        "maKH INTEGER REFERENCES KhachHang(maKH), "+
                        "maGiay INTEGER REFERENCES Giay(maGiay), "+
                        "ngay TEXT NOT NULL, "+
                        "giaHD TEXT NOT NULL, "+
                        "trangThai INTEGER NOT NULL)";
        db.execSQL(tableHoaDon);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableNhanVien = "DROP TABLE IF EXISTS NhanVien";
        db.execSQL(dropTableNhanVien);

        String dropTableKhachHang = "DROP TABLE IF EXISTS KhachHang";
        db.execSQL(dropTableKhachHang);

        String dropTableLoaiGiay = "DROP TABLE IF EXISTS LoaiGiay";
        db.execSQL(dropTableLoaiGiay);

        String dropTableGiay = "DROP TABLE IF EXISTS Giay";
        db.execSQL(dropTableGiay);

        String dropTableHoaDon = "DROP TABLE IF EXISTS HoaDon";
        db.execSQL(dropTableHoaDon);

        onCreate(db);
    }
}
