package com.mtit.minhtien.intentexplicitsendreceivebundle;

import java.io.Serializable;

/**
 * Created by MT IT on 11/5/2017.
 */

public class HocSinh implements Serializable {  //để ép class phù hợp với Intent
    private String HoTen;
    private int NamSinh;

    public HocSinh(String hoTen, int namSinh) {
        HoTen = hoTen;
        NamSinh = namSinh;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public int getNamSinh() {
        return NamSinh;
    }

    public void setNamSinh(int namSinh) {
        NamSinh = namSinh;
    }
}
