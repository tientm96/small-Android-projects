package com.mtit.minhtien.calendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView txtTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTime = (TextView) findViewById(R.id.textviewTime);

        //chọn thư viện java.util
        Calendar calendar = Calendar.getInstance(); //Khởi tạo calendar

        //txtTime.setText(calendar.getTime() + "");
        //Hoặc dùng append, cũng như setText, nhưng nó lưu lại đc trạng thái.
        txtTime.append(calendar.getTime() + "\n");//getTime, trả ra toàn bộ: ngày giờ, múi giờ

        txtTime.append(calendar.get(calendar.DATE) + "\n"); //trả về 1 cái nhỏ
        txtTime.append(calendar.get(calendar.MONTH) + "\n");
        txtTime.append(calendar.get(calendar.YEAR) + "\n");

        //IN RA NGUYÊN ĐỊNH DẠNG dd/MM/yyyy
        SimpleDateFormat dinhdangngay = new SimpleDateFormat("dd/MM/yyyy"); //15/10/2017
        txtTime.append(dinhdangngay.format(calendar.getTime()) + "\n");


        //GIỜ PHÚT GIÂY
        txtTime.append(calendar.get(Calendar.HOUR) + "\n"); //múi 12h, không có pm hay am.
        txtTime.append(calendar.get(Calendar.HOUR_OF_DAY) + "\n"); //muối 24h

        //Định dạng giời
        SimpleDateFormat dinhdanggio = new SimpleDateFormat("hh:mm:ss a"); //a: là pm/am
        txtTime.append(dinhdanggio.format(calendar.getTime()) + "\n");



    }
}
