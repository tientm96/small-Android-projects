package com.example.mtit.newfirebasestorage;

/**
 * Created by MTIT on 3/11/2018.
 */

public class HinhAnh {
    private String TenHinh;
    private String LinkHinh;

    //constuctor rỗng, mặc định cho firebase
    public HinhAnh() {
    }

    public HinhAnh(String tenHinh, String linkHinh) {
        TenHinh = tenHinh;
        LinkHinh = linkHinh;
    }

    public void setTenHinh(String tenHinh) {
        TenHinh = tenHinh;
    }

    public void setLinkHinh(String linkHinh) {
        LinkHinh = linkHinh;
    }

    public String getTenHinh() {
        return TenHinh;
    }

    public String getLinkHinh() {
        return LinkHinh;
    }
}
