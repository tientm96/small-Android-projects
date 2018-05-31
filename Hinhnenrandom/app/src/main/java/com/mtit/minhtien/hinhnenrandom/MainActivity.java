package com.mtit.minhtien.hinhnenrandom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    ArrayList<Integer> arrayImage; //khởi tạo ở đây hay ở dưới đều đc

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = (LinearLayout) findViewById(R.id.myLinearLayout);

        arrayImage = new ArrayList<>();
        arrayImage.add(R.drawable.background2);
        arrayImage.add(R.drawable.background4);
        arrayImage.add(R.drawable.background5);
        arrayImage.add(R.drawable.background6);

        Random random = new Random();
        int vitri = random.nextInt(arrayImage.size());

        linearLayout.setBackgroundResource(arrayImage.get(vitri)); //lấy ảnh theo vị trí random


    }
}
