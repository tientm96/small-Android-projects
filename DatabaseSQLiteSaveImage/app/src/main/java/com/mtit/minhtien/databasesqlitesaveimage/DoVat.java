package com.mtit.minhtien.databasesqlitesaveimage;

/**
 * Created by MT IT on 11/20/2017.
 */

public class DoVat {

    private int Id;
    private String ten;
    private String mota;
    private byte[] hinh;

    public DoVat(int id, String ten, String mota, byte[] hinh) {
        Id = id;
        this.ten = ten;
        this.mota = mota;
        this.hinh = hinh;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }
}
