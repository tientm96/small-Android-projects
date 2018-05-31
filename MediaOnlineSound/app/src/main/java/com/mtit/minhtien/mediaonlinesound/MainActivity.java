package com.mtit.minhtien.mediaonlinesound;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button btnMp3;
    ProgressBar pbLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMp3 = (Button) findViewById(R.id.buttonMp3);
        pbLoading = (ProgressBar) findViewById(R.id.progressBarLoading); //hình tròn đang xoay: ý là đang loading

        //vừa mới vào thì progressBar chưa cần xoay, nên ẩn nó đi
        pbLoading.setVisibility(View.GONE);



        btnMp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://khoapham.vn/download/vietnamoi.mp3";
                MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

                try {
                    mediaPlayer.setDataSource(url);
                    mediaPlayer.prepareAsync(); //thay vì khởi tạo class AsyncTask thì ta viết dòng này để nó chuẩn bị lấy nguồn dl về

                    //lúc này đang chuẩn bị lấy nguồn dl từ prepareAsync, nên progressBar sẽ hiện lên là đang load.
                    pbLoading.setVisibility(View.VISIBLE);


                    //kiểm tra, nếu dl load về đc rồi thì mới play nó lên
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            //khi đã load xong rồi đưa vô đây, thì ẩn progressBar loading đi.
                            pbLoading.setVisibility(View.GONE);
                            mp.start();

                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
