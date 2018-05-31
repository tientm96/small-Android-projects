package com.mtit.minhtien.intentexplicitsendreceivebundle;

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

                String[] arrayName = {"Hồ Chí Minh", "Nha Trang", "Cần Thơ", "Đà Lạt"};
                HocSinh hocSinh = new HocSinh("Minh Tiến", 1996);

                //Khai báo hộp Bundle
                Bundle bundle = new Bundle();

                bundle.putString("chuoi", "Tien Dep Trai"); //(key, value)
                bundle.putInt("conso", 12345);

                        //muốn put 1 mảng thì phải tạo mảng ở trên trước
                bundle.putStringArray("mangTen", arrayName);
                        //put 1 Object, tạo đt ở trên, dùng putSeria...
                bundle.putSerializable("doiTuong", hocSinh);

                //Đưa vào trong Intent, put để gửi đi 1 gói lớn là gói bundle.
                intent.putExtra("dulieu", bundle);

                startActivity(intent);
            }
        });



    }
}
