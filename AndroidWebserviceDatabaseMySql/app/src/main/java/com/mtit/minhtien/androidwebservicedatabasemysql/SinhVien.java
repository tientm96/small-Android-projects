package com.mtit.minhtien.androidwebservicedatabasemysql;

import java.io.Serializable;

/**
 * Created by MT IT on 11/21/2017.
 */

/*Trong SinhVienAdapter. Sự kiện cho imgEdit, phải truyền đi 1 object sinhvien thông qua intent.
* Nên ở đây phải implements Serializable để nó có thể truyền.*/

public class SinhVien implements Serializable {
    private int Id;
    private String HoTen;
    private int NamSinh;
    private String DiaChi;

    public SinhVien(int id, String hoTen, int namSinh, String diaChi) {
        Id = id;
        HoTen = hoTen;
        NamSinh = namSinh;
        DiaChi = diaChi;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }
}
