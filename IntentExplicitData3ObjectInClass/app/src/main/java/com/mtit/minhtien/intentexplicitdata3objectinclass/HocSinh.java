package com.mtit.minhtien.intentexplicitdata3objectinclass;

import java.io.Serializable;

/**
 * Created by MT IT on 10/18/2017.
 */

public class HocSinh implements Serializable {
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
