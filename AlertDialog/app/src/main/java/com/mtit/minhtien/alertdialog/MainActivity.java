package com.mtit.minhtien.alertdialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvName;
    ArrayList<String> arrayName;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvName = (ListView) findViewById(R.id.listviewName);
        arrayName = new ArrayList<>();
        AddArrayName();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayName);
        lvName.setAdapter(adapter);

        //bắt sự kiện cho listview
        lvName.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String subject = arrayName.get(position).toString(); //lấy ra tên môn học tại vị trí pos...

                XacNhanXoa(position, subject); //position ở đây cũng tên là position

                return false;
            }
        });
    }

    // XÁC NHẬN XÓA
    private void XacNhanXoa(final int position, String subject){ //vị trí xóa
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Thông báo");
        alertDialog.setIcon(R.mipmap.ic_launcher);

        //câu hỏi xác nhận
        alertDialog.setMessage("Bạn có muốn xóa môn học " + subject +" này không?");

        //Sự kiện khi chọn có, 2 button riêng biệt, set 2 button này
        alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                arrayName.remove(position);

                adapter.notifyDataSetChanged();//cập nhật lại adapter
            }
        });

        alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //chọn không thì ko cần làm gì cả, nên ko cần code trong button không.
            }
        });

        alertDialog.show();
    }


    private void AddArrayName(){
        arrayName.add("Android");
        arrayName.add("iOS");
        arrayName.add("PHP");
        arrayName.add("ASP.NET");
        arrayName.add("Unity 3D");
        arrayName.add("Cocos2dx");
        arrayName.add("NodeJS");
    }
}
