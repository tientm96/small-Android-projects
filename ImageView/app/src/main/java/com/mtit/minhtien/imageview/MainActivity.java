package com.mtit.minhtien.imageview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private ImageView imgHinh;
    private RelativeLayout manhinh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ánh xạ
        imgHinh = (ImageView) findViewById(R.id.imageViewHinh);
        manhinh = (RelativeLayout) findViewById(R.id.manHinh);

        imgHinh.setImageResource(R.drawable.android_logo1);
        manhinh.setBackgroundResource(R.drawable.background1);
    }
}
