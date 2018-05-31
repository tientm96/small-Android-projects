package com.mtit.minhtien.intentexplicitdata3objectinclass;

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

        //Nhận dl từ intent
        Intent intent = getIntent();

        //Vì kdt là class tự định nghĩa nên phải get theo Serializable
        HocSinh hocSinh = (HocSinh) intent.getSerializableExtra("dulieu");

        txtKetQua.setText(hocSinh.getHoTen() + " - " + hocSinh.getNamSinh());
    }
}
