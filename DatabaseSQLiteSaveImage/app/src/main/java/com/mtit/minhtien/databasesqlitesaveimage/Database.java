package com.mtit.minhtien.databasesqlitesaveimage;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

/**
 * Created by MT IT on 11/20/2017.
 */


public class Database extends SQLiteOpenHelper{
    //constutor ta chọn 1 cái thôi. alt + enter: chọn dòng đầu tiên
    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //không trả kq: CREATE, INSERT, UPDATE, DELETE, ...
    //(Thêm bt dùng hàm ở đây, nhưng nếu có kiểu đặc biệt như BLOB(hình ảnh) thì dùng hàm dưới)
    public void QueryData(String sql){
        SQLiteDatabase database = getWritableDatabase(); //getWrite vừa đọc ra vừa ghi vào.
        database.execSQL(sql);
    }


    /*Vì có hình ảnh là kiểu dl Blob đặc biệt, nên ta dùng hàm INSERT riêng, để tránh lỗi.*/
    public void INSERT_DOVAT(String ten, String mota, byte[] hinh){ //những tham số người dùng nhập vào.
                                                                    // BLOB trong tham số là mảng byte[]
        SQLiteDatabase database = getWritableDatabase();

        //id: ko nhập vào nên để null.
        //Các thành phần còn lại ta nhận trên tham số ở trên, nhưng không nhận trực tiếp,
        //ta sẽ biên dịch nó sau, nên để ?,?,?.
        String sql = "INSERT INTO DoVat VALUES(null, ?, ?, ?)";

        //Biên dịch chuỗi lệnh sql. Nhưng nó sẽ không hiểu ?,?,? là gì, nên ta cần phân tích 3 dấu ? đó ra.
        SQLiteStatement statement = database.compileStatement(sql);

        //Trước khi phân tích ta clearBindings: để xóa các phần đã phân tích xong ở trước
        statement.clearBindings();

        //Phân tích 3 dấu ?,?,?
        /*Index gồm 0, 1, 2, 3.
        * 0: là Id tự động tăng, nên ko cần phải nhập
        * 1, 2, 3: thứ tự 3 dấu ?, lần lượt là ten, mota, hinh*/
        statement.bindString(1, ten);
        statement.bindString(2, mota);
        statement.bindBlob(3, hinh);
        statement.executeInsert(); //thực thi lệnh insert
    }



    //truy vấn có trả kết quả về con trỏ: SELECT
    public Cursor GetData(String sql){
        SQLiteDatabase database = getReadableDatabase(); //getRead: chỉ đọc ra. Dùng hàm getWrite (vừa đọc vừa ghi) cũng đc.
        return database.rawQuery(sql, null);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
