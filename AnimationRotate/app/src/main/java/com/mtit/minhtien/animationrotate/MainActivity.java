package com.mtit.minhtien.animationrotate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imgRotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgRotate = (ImageView) findViewById(R.id.imageViewRotate);

        final Animation animRotate = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);

        imgRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgRotate.startAnimation(animRotate);
                //v.startAnimation(animRotate);  //ĐẶC BIỆT VỚI IMAGEVIEW, GỌI THEO 2 CÁCH ĐỀU ĐC
            }
        });

    }
}
