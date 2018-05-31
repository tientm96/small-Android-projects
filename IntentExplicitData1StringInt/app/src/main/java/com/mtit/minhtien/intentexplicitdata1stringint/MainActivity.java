package com.mtit.minhtien.intentexplicitdata1stringint;

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

                //chuyển 1 chuỗi, vị trí là sau dòng khai báo và trước dòng start

                //TH truyền chuỗi
                //intent.putExtra("dulieu", "Nội dung chuỗi"); //tên của chuỗi và nội dung gửi đi


                //TH trưyền số
                intent.putExtra("dulieu", 2017); //tên của chuỗi và nội dung gửi đi
                //intent.putExtra("dulieu", 1.5); //TH hiện lên gt default vì xung đột kdl




                startActivity(intent);
            }
        });
    }
}
