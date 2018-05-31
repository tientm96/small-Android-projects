package com.example.mtit.firefoxsqlitemanagerqlnv;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /*Chọn foler java, new Folder, chọn Assets folder. Add database đã tạo bằng firefox vào thư mục đó.
     *Tạo class Database để đọc. Copy hàm initDatabase() từ https://github.com/vanpho93/ManageEmployee/blob/master/COPY%20ME về.
     */

    final String DATABASE_NAME = "EmployeeDB.sqlite"; /*Phải trùng tên với tên của firefoxDatabase đã add vào folder assets*/
    SQLiteDatabase database;


    ListView lvNhanVien;
    ArrayList<NhanVien> arrayNhanVien;
    AdapterNhanVien adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Gán giá trị listview
        addListView();


        readData();


        /*Test thử database bằng cách đọc dòng đầu tiên*/
//        database = Database.initDatabase(this, DATABASE_NAME); /*Gọi đến class.gọi hàm của Class Database*/
//        Cursor cursor = database.rawQuery("SELECT * FROM NhanVien", null);
//
//        cursor.moveToFirst();
//        Toast.makeText(this, cursor.getString(1), Toast.LENGTH_SHORT).show(); /*"columnIndex: 1": là lấy cột thứ 1+1: là Tên*/


    }

    /*--------------------------------------------------------------------------------------------*/

    private void addListView(){
        lvNhanVien = (ListView) findViewById(R.id.listviewNhanVien);
        arrayNhanVien = new ArrayList<>();
        adapter = new AdapterNhanVien(this, R.layout.listview_row, arrayNhanVien);
        lvNhanVien.setAdapter(adapter);
    }

    private void readData(){
        database = Database.initDatabase(this, DATABASE_NAME); /*Gọi đến clas Database*/
        Cursor cursor = database.rawQuery("SELECT * FROM NhanVien", null);

        arrayNhanVien.clear();
        while(cursor.moveToNext()){ //đọc từng dòng
            arrayNhanVien.add(new NhanVien(cursor.getInt(0),  //Cột 0: Id, 1: Tên, 2: SDT, 3: ảnh
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getBlob(3)   //trả về kiểu byte[]
            ));
        }
        adapter.notifyDataSetChanged(); //cập nhật adapter
    }


    /*--------------------------------------------------------------------------------------------*/
    //Add menu, chỉ cần tạo xml menu, rồi add vào Activity bằng java.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_nhanvien, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //add xong ta tạo sk cho menu.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.memuAdd){
            startActivity(new Intent(MainActivity.this, AddActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }



}
