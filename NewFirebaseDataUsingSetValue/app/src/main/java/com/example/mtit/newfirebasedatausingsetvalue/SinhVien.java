package com.example.mtit.newfirebasedatausingsetvalue;

/**
 * Created by MTIT on 3/9/2018.
 */

public class SinhVien {

    private String HoTen;
    private String DiaChi;
    private int NamSinh;

    public SinhVien(){
        //mặc định của firebase, khi nhận data về thì cần có hàm tạo rỗng.
    }

    public SinhVien(String HoTen, String DiaChi, int NamSinh) {
        this.HoTen = HoTen;
        this.DiaChi = DiaChi;
        this.NamSinh = NamSinh;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public void setNamSinh(int namSinh) {
        NamSinh = namSinh;
    }

    public String getHoTen() {
        return HoTen;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public int getNamSinh() {
        return NamSinh;
    }
}
