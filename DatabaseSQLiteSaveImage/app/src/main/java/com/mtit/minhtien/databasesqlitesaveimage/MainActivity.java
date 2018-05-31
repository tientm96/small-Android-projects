package com.mtit.minhtien.databasesqlitesaveimage;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /*VÌ LỠ CHỌN CREATE PROJ TRÊN API 24, NÊN KHI RUN PHẢI LỰA MÁY ẢO API 24 TRỞ LÊN*/

    Button btnThem;
    public static Database database; //đưa lên làm biến toàn cục, để có thể gọi nó trong ThemDoVatActivity

    ListView lvDoVat;
    ArrayList<DoVat> arrayDoVat;
    DoVatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnThem = (Button) findViewById(R.id.buttonThem);
        lvDoVat = (ListView) findViewById(R.id.listViewDoVat);

        arrayDoVat = new ArrayList<>();
        adapter = new DoVatAdapter(this, R.layout.dong_do_vat, arrayDoVat);

        lvDoVat.setAdapter(adapter);



        //1. Khởi tạo và đặt tên database---------------------------
        database = new Database(this, "QuanLy.sqlite", null, 1);

        //2. Tạo bảng
        //AUTOINCREMENT: khóa chính này ko nhập, tự động nó tăng dần. VARCHAR tối đa là 255
        //HinhAnh BLOB: trong SQLite hình ảnh được lưu kiểu BLOB.
        database.QueryData("CREATE TABLE IF NOT EXISTS DoVat(Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten VARCHAR(150), MoTa VARCHAR(250), HinhAnh BLOB)");


        //SELECT: getData, trả về con trỏ
        Cursor cursor = database.GetData("SELECT * FROM DoVat");

        //xóa mảng, cập nhật lại dl mới, để khi thêm phần tử thì không bị lặp do còn dl cũng trong mảng.
            /*Vòng while là đọc tất cả những dòng dl đang tồn tại trong database ra. Mỗi lần đọc như vậy
            *       nó add dòng đọc được vào arrayCongViec.
            *
            * -Giả sử có 2 dòng dl cần thêm vào.
            * +Lần đầu đọc ra (run) thì arrayCongViec add được 2 dòng dl.
            *
            * +Cũng giữ nguyên database đó, và đọc lần thứ 2(run máy áo) thì nó lại đọc lại 2 dòng đang có,
            *       và add thêm 2 dòng ấy vào arryCongViec. Như vậy arrayCV có đến 4 dòng trùng nhau.
            * Vậy nên  trước vòng while thì ta phải clear arrayCV đi.*/

            /*Nói cách khác, mỗi lần đọc tất cả database từ vòng while ra, thì add hết vào array,
            * Như vậy, nếu ko xóa đi array sau mỗi lần đọc lại, thì add vào sẽ bị trùng.=>dòng lệnh 67*/

        arrayDoVat.clear();
        while(cursor.moveToNext()){ //đọc từng dòng
            arrayDoVat.add(new DoVat(cursor.getInt(0),  //Id
                                     cursor.getString(1),
                                     cursor.getString(2),
                                     cursor.getBlob(3)
            ));
        }

        adapter.notifyDataSetChanged(); //cập nhật adapter



        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ThemDoVatActivity.class));
            }
        });

    }
}
