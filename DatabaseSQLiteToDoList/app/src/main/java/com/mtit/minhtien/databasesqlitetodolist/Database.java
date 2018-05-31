package com.mtit.minhtien.databasesqlitetodolist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MT IT on 11/16/2017.
 */

public class Database extends SQLiteOpenHelper {
    //context: gọi màn hình truyền vào
    //name: tên của database khi mình khởi tạo
    //CursorFactory: là con trỏ sử dụng để chuyển dữ liệu, lấy kết quả ra
    //version: mặc định từ
    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //truy vấn ko trả kết quả: CREATE, INSERT, UPDATE, DELETE...
    public void QueryData(String sql){
        SQLiteDatabase database = getWritableDatabase();     // ghi vào dữ liệu
        database.execSQL(sql);      //để thực thi lệnh
    }


    //truy vấn có trả kết quả: SELECT
    //Ở trên có nói: CursorFactory: là con trỏ sử dụng để chuyển dữ liệu, lấy kết quả ra.
    //Vậy nên để lấy kết quả ta dùng CON TRỎ.
    public Cursor GetData(String sql){
        SQLiteDatabase database = getReadableDatabase(); //getWritableDatabase(): vừa đọc vừa ghi, nên dùng ở đây cũng được.
                                                        //getReadableDatabase(): chỉ dùng để đọc ra.
        return database.rawQuery(sql, null); //trả về con trỏ
    }


    @Override
    public void onCreate(SQLiteDatabase db) {}
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
