package com.mtit.minhtien.animationalpha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imgAlpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgAlpha = (ImageView) findViewById(R.id.imageViewAlpha);

        //khai báo và ánh xạ Animation
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);

        imgAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animAlpha); //hoặc imgAlpha.startAnimation(animAlpha);

            }
        });

    }
}
