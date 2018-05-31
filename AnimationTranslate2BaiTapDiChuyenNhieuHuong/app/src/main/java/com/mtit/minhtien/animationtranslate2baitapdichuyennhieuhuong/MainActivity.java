package com.mtit.minhtien.animationtranslate2baitapdichuyennhieuhuong;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imgTranslate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgTranslate = (ImageView) findViewById(R.id.imageViewTranslate);

        //khởi tạo hiệu ứng, và tham chiếu hiệu ứng từ file .xml
        final Animation animTranslate = AnimationUtils.loadAnimation(this, R.anim.anim_translate);

        imgTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animTranslate); //hoặc imgTranslate.startAnimation(animTranslate);

            }
        });

    }
}
