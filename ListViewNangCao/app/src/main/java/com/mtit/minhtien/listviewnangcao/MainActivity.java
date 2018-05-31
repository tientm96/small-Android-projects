package com.mtit.minhtien.listviewnangcao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvTraiCay;
    ArrayList<TraiCay> arrayTraiCay;
    TraiCayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        adapter = new TraiCayAdapter(this, R.layout.dong_trai_cay, arrayTraiCay); // this: màn hình

        //Quan trọng là dòng này. ListView set dữ liệu từ adapter
        lvTraiCay.setAdapter(adapter);
    }

    private void AnhXa(){
        lvTraiCay = (ListView) findViewById(R.id.listviewTraiCay);

        arrayTraiCay = new ArrayList<>();

        arrayTraiCay.add(new TraiCay("Táo đỏ", "Táo nhập khẩu Mỹ", R.drawable.apple));
        arrayTraiCay.add(new TraiCay("Quả mâm xôi", "Mâm xôi nhập khẩu Úc", R.drawable.blackberry));
        arrayTraiCay.add(new TraiCay("Quả măng cụt", "Măng cụt miền tây", R.drawable.mangosteen));
        arrayTraiCay.add(new TraiCay("Quả dừa sáp", "Đặc sản Trà Vinh", R.drawable.coconut_sap));
        arrayTraiCay.add(new TraiCay("Quả cam", "Cam sành Nghệ An", R.drawable.orange));
        arrayTraiCay.add(new TraiCay("Quả chery", "Cherry nhập khẩu Mỹ", R.drawable.cherry));
        arrayTraiCay.add(new TraiCay("Quả Lựu", "Lựu nhập khẩu Thái", R.drawable.pomegranate));


        //nhân 2 lên cho nó nhiều, để ta dễ thấy hiệu ứng của nó
        arrayTraiCay.add(new TraiCay("Táo đỏ", "Táo nhập khẩu Mỹ", R.drawable.apple));
        arrayTraiCay.add(new TraiCay("Quả mâm xôi", "Mâm xôi nhập khẩu Úc", R.drawable.blackberry));
        arrayTraiCay.add(new TraiCay("Quả măng cụt", "Măng cụt miền tây", R.drawable.mangosteen));
        arrayTraiCay.add(new TraiCay("Quả dừa sáp", "Đặc sản Trà Vinh", R.drawable.coconut_sap));
        arrayTraiCay.add(new TraiCay("Quả cam", "Cam sành Nghệ An", R.drawable.orange));
        arrayTraiCay.add(new TraiCay("Quả chery", "Cherry nhập khẩu Mỹ", R.drawable.cherry));
        arrayTraiCay.add(new TraiCay("Quả Lựu", "Lựu nhập khẩu Thái", R.drawable.pomegranate));
    }
}
