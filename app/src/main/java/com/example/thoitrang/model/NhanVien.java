package com.example.thoitrang.model;

public class NhanVien {
    public int maNV;
    public String hoTenNV;
    public String tenDN;
    public String matKhau;
    public String sdtNV;
    public byte[] hinhNV;

    public NhanVien() {
    }

    public NhanVien(int maNV, String hoTenNV, String tenDN, String matKhau, String sdtNV, byte[] hinhNV) {
        this.maNV = maNV;
        this.hoTenNV = hoTenNV;
        this.tenDN = tenDN;
        this.matKhau = matKhau;
        this.sdtNV = sdtNV;
        this.hinhNV = hinhNV;
    }

}