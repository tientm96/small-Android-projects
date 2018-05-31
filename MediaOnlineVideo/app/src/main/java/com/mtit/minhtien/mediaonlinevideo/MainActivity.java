package com.mtit.minhtien.mediaonlinevideo;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    Button btnMp4;
    VideoView videoView;
    ProgressBar pbLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMp4 = (Button) findViewById(R.id.buttonMp4);
        videoView = (VideoView) findViewById(R.id.videoViewMp4);
        pbLoading = (ProgressBar) findViewById(R.id.progressBarLoading); //hình tròn xoay, loading

        //vừa mới vào chưa cần loading, ẩn đi
        pbLoading.setVisibility(View.GONE);


        btnMp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://khoapham.vn/download/vuoncaovietnam.mp4"); //chuyển qua kiểu uri
                videoView.setVideoURI(uri);
                videoView.setMediaController(new MediaController(MainActivity.this));

                //lúc này đang lấy nguồn dl, hiện loading
                pbLoading.setVisibility(View.VISIBLE);

                //kiểm tra, nếu dl load về đc rồi thì mới play nó lên
                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        //khi đã load xong rồi đưa vô đây, thì ẩn loading đi.
                        pbLoading.setVisibility(View.GONE);

                        videoView.start();
                    }
                });
            }
        });

    }
}
