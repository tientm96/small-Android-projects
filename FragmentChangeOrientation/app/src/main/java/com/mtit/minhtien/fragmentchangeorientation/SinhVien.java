package com.mtit.minhtien.fragmentchangeorientation;

import java.io.Serializable;

/**
 * Created by MT IT on 11/29/2017.
 */

/*TH màn hình dọc, ta phải chuyển Object SinhVien từ activity này, sang activity khác để show ra info,.
        nên sẽ dùng intent để chuyển Object.
* -Nhưng intent để chuyển Object từ activity này sang activity khác thì phải implements Serializable
        cho class enity chứa Object đó => là class SinhVien, nên ở đây ta phải implements Serializable.*/
public class SinhVien implements Serializable{
    private String hoten;
    private int namsinh;
    private String diachi;
    private String email;

    public SinhVien(String hoten, int namsinh, String diachi, String email) {
        this.hoten = hoten;
        this.namsinh = namsinh;
        this.diachi = diachi;
        this.email = email;
    }


    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public int getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(int namsinh) {
        this.namsinh = namsinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
