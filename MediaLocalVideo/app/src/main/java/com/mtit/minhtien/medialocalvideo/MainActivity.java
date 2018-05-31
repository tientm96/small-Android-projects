package com.mtit.minhtien.medialocalvideo;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    Button btnMP4;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMP4 = (Button) findViewById(R.id.buttonMP4);
        videoView = (VideoView) findViewById(R.id.videoViewMP4);

        btnMP4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //ép kiểu về Uri
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.dreamn_save_me));
                videoView.start();

                //khung điều khiển kéo qua kéo lại, dừng hay play... CHỌN CỦA android.widget
                MediaController mediaController = new MediaController(MainActivity.this);
                mediaController.setMediaPlayer(videoView);
                videoView.setMediaController(mediaController);

            }
        });


    }
}
