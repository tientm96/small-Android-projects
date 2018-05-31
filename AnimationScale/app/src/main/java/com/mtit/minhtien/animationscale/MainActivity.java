package com.mtit.minhtien.animationscale;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imgScale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgScale = (ImageView) findViewById(R.id.imageViewScale);

        //khởi tạo thành phần lưu hiệu ứng scale và tham chiếu đến file xml chứa scale
        final Animation animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);

        imgScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animScale); //hoặc imgScale.startAnimation(animScale);

            }
        });



    }
}
