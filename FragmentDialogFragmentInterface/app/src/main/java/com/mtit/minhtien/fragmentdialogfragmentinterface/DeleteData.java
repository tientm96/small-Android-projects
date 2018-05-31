package com.mtit.minhtien.fragmentdialogfragmentinterface;

/**
 * Created by MT IT on 11/29/2017.
 */


/*Làm thế nào để activity biết ta chọn "có/không" để xử lý.
* -Dùng bundle: dùng để truyền dl và load lên thôi. Nhưng ở đây đang là trực tiếp ở activity
* -Dùng Interface: tạo như tạo class bt, nhưng ở mục kind chọn "interface"*/

public interface DeleteData {

    public void GiaTriXoa(boolean delete);
}
