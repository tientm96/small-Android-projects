package com.mtit.minhtien.intentexplicitdata3objectinclass;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSend = (Button) findViewById(R.id.buttonMain);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                //TH truyền từ class
                HocSinh hocSinh = new HocSinh("Minh Tien", 1996);



                //intent.putExtra("dulieu", (Serializable) hocSinh); //(Serializable) ép kiểu class về intent
                                                                     //chỉ 1 TH truyền object thôi.
                //ép kiểu như vậy cũng báo lỗi, nên ta dùng implement Serializable trong class HS luôn

                intent.putExtra("dulieu", hocSinh); //như vậy không cần ép kiểu (Serializable) nữa





                startActivity(intent);
            }
        });
    }
}
