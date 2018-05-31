package com.mtit.minhtien.intentexplicitdata1stringint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView txtKetQua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txtKetQua = (TextView) findViewById(R.id.textViewKetqua);

        //nhận dl từ intent
        Intent intent = getIntent();

        //TH truyền chuỗi
//        String noidung = intent.getStringExtra("dulieu"); //tên của chuỗi bên MH Main

        //TH trưyền số
        int noidung = intent.getIntExtra("dulieu", 1234); //khi mâu thuẫn kdl, hoặc
        //khác tên, không nhận đc giá trị, thì nó sẽ hiển thị gt default


        txtKetQua.setText(noidung + ""); //chuyển int sang String
    }
}
