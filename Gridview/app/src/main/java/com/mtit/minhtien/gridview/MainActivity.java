package com.mtit.minhtien.gridview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gvHinhAnh;
    ArrayList<HinhAnh> arrayImage;
    HinhAnhAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        adapter = new HinhAnhAdapter(this, R.layout.dong_hinh_anh, arrayImage);
        gvHinhAnh.setAdapter(adapter);


        //sự kiện click lên từng item
        gvHinhAnh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, arrayImage.get(i).getTen(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void AnhXa(){
        gvHinhAnh = (GridView) findViewById(R.id.gridviewHinhAnh);

        arrayImage = new ArrayList<>();
        arrayImage.add(new HinhAnh("Quả cam", R.drawable.orange_quacam));
        arrayImage.add(new HinhAnh("Quả táo đỏ", R.drawable.apple_taodo));
        arrayImage.add(new HinhAnh("Quả mơ", R.drawable.apricots_quamo));
        arrayImage.add(new HinhAnh("Quả bơ", R.drawable.avocado_quabo));
        arrayImage.add(new HinhAnh("Quả cherry", R.drawable.cherry_quacherry));
        arrayImage.add(new HinhAnh("Quả dừa sáp", R.drawable.coconut_quadua));
        arrayImage.add(new HinhAnh("Quả nho", R.drawable.grape_quanho));
        arrayImage.add(new HinhAnh("Quả ổi", R.drawable.guava_quaoi));
        arrayImage.add(new HinhAnh("Quả kiwi", R.drawable.kiwigreen_quakiwixanh));
        arrayImage.add(new HinhAnh("Quả chanh nướm", R.drawable.lemon_quachanh));
        arrayImage.add(new HinhAnh("Quả chanh dây", R.drawable.lemon2_chanhday));
        arrayImage.add(new HinhAnh("Quả măng cụt", R.drawable.mangosteen_quamangcut));
        arrayImage.add(new HinhAnh("Quả lựu", R.drawable.pomegranate_qualuu));
        arrayImage.add(new HinhAnh("Quả chôm chôm", R.drawable.rambutan_quachomchom));
        arrayImage.add(new HinhAnh("Quả nhãn long", R.drawable.timthumb_quanhanglong));
        arrayImage.add(new HinhAnh("Quả dâu tây", R.drawable.strawberry_quadau));
        arrayImage.add(new HinhAnh("Quả vãi thiều", R.drawable.litchi_quavaithieu));

    }
}
