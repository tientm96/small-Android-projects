package com.mtit.minhtien.progressbar;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnDownload;
    ProgressBar pbXuly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ánh xạ
        btnDownload = (Button) findViewById(R.id.buttonDownload);
        pbXuly = (ProgressBar) findViewById(R.id.progressBarXuly);

        //sự kiện
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //CountDownTimer: giải pháp cho việc click 1 lần sẽ tự chạy trong khoảng tgian nào đó
                CountDownTimer countDownTimer = new CountDownTimer(10000, 500) {
                    @Override
                    public void onTick(long l) {
                        int current = pbXuly.getProgress();
                        if(current >= pbXuly.getMax()) {//khi nó tới 100 sẽ ngừng, phải đưa về lại 0 để chạy lại
                            current = 0;
                        }

                        pbXuly.setProgress(current+10); // mỗi lần nhích lên 10 đơn vị
                    }

                    @Override
                    public void onFinish() { //Khi hết 10.000 milis chạy, thì nó dừng
                        this.start(); //hết gian chạy sẽ tạo ra 1 vòng tgian mới để chạy tiếp lần nữa.
                        //đệ quy, gọi lại chính nó.
                        Toast.makeText(MainActivity.this, "Hết giờ!", Toast.LENGTH_SHORT).show();
                    }
                }.start();
                //hoặc countDownTimer.start();



            }
        });
    }
}
