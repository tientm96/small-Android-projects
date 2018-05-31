package com.example.mtit.newfirebaseretrievedata;

/**
 * Created by MTIT on 3/10/2018.
 */

public class PhuongTien {
    private String Ten;
    private Integer Banh;

    //constructor mặc định dùng firebase
    public PhuongTien() {
    }

    public PhuongTien(String ten, Integer banh) {
        Ten = ten;
        Banh = banh;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public void setBanh(Integer banh) {
        Banh = banh;
    }

    public String getTen() {
        return Ten;
    }

    public Integer getBanh() {
        return Banh;
    }
}
