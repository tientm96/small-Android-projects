package com.mtit.minhtien.intentexplicitsendreceivebundle;

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

                 //nhận bundle, ko new Bundle(), vì đây chỉ cần khai báo ra và nhận từ intent thôi, ko cần khởi tạo
        Bundle bundle = intent.getBundleExtra("dulieu"); //key

                 //nếu bundle lỗi: key sai, hoặc null dữ liệu thì ở dưới ten sẽ lấy ra ko đc, ct lỗi => Kiểm tra
        if(bundle != null){
                 //lúc này chuỗi, số, mảng, object đã nằm trong bundle rồi, nên ta chỉ cần gọi từ bundle ra là đc.
            String ten = bundle.getString("chuoi");

            int so = bundle.getInt("conso", 123); //giá trị default có hay không cũng đc.

            String[] mang = bundle.getStringArray("mangTen");

            HocSinh hocSinh = (HocSinh) bundle.getSerializable("doiTuong");//vì gán cho nó, nên khai báo, ko new()


            txtKetQua.setText(ten + "\n" + so + "\n" + mang[0] + "\n" + hocSinh.getHoTen());
        }


    }
}
