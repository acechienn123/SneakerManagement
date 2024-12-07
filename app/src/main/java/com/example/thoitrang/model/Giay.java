package com.example.thoitrang.model;

public class Giay {
//    them thuoc tinh hinh anh vao day
    public int maGiay;
    public String tenGiay;
    public String giaMua;
    public String moTa;
    public String soLuong;
    public int maLoai;
    public byte[] hinh;

    public Giay() {
    }

    public Giay(int maGiay, String tenGiay, String giaMua, String moTa, String soLuong, int maLoai, byte[] hinh) {
        this.maGiay = maGiay;
        this.tenGiay = tenGiay;
        this.giaMua = giaMua;
        this.moTa = moTa;
        this.soLuong = soLuong;
        this.maLoai = maLoai;
        this.hinh = hinh;
    }

    public int getMaGiay() {
        return maGiay;
    }

    public void setMaGiay(int maGiay) {
        this.maGiay = maGiay;
    }

    public String getTenGiay() {
        return tenGiay;
    }

    public void setTenGiay(String tenGiay) {
        this.tenGiay = tenGiay;
    }

    public String getGiaMua() {
        return giaMua;
    }

    public void setGiaMua(String giaMua) {
        this.giaMua = giaMua;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }
}