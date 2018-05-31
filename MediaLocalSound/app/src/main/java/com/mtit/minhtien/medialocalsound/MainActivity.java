package com.mtit.minhtien.medialocalsound;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnMP3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMP3 = (Button) findViewById(R.id.buttonMP3);

        btnMP3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //khai báo mediaplayer từ việc ánh xạ nguồn mp3
                MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.dreamn_save_me);

                mediaPlayer.start();
            }
        });
    }
}
