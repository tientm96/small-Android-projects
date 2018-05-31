package com.mtit.minhtien.intentexplicitdata2array;

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

        //TH Nhận mảng
        String[] mangMonHoc = intent.getStringArrayExtra("dulieu");

        txtKetQua.setText(mangMonHoc.length + mangMonHoc[3]);
    }
}
