package com.mtit.minhtien.seekbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    SeekBar skSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ánh xạ
        skSound = (SeekBar) findViewById(R.id.seekBarSound);

        skSound.getProgress(); //lấy ra giá trị hiện tại của seekbar.

        skSound.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //i: giá trị của seekbar, đang đc kích tới 40 hay 80 hay 100...
                Log.d("MTIT", "Giá trị: " + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d("MTIT", "START");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("MTIT", "STOP");
            }

        });

    }
}
