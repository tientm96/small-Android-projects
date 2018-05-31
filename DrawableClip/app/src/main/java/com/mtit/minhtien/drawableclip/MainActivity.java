package com.mtit.minhtien.drawableclip;

        import android.graphics.drawable.ClipDrawable;
        import android.os.Handler;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imgHinh;
    Button btnClip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ánh xạ
        imgHinh = (ImageView) findViewById(R.id.imageViewHinh);
        btnClip = (Button)findViewById(R.id.buttonClip);

        imgHinh.setImageLevel(1000);

        final ClipDrawable clipDrawable = (ClipDrawable) imgHinh.getDrawable();

        btnClip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Handler handler = new Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int currentLevel = clipDrawable.getLevel();
                        //nếu full hình thì bắt đầu lại từ đầu
                        if(currentLevel >= 10000){
                            currentLevel = 0;
                        }
                        imgHinh.setImageLevel(currentLevel + 1000);

                        //gọi đệ quy lại chính nó, chạy hoài kh dừng
                        //TH này thì lần đầu sẽ delay 2s, những lần sau chỉ 0.5s thôi, tại vì chưa
                        //thể ra ngoài đc, do đệ quy quay lại 0.5s mãi.
                        //Như vậy cứ chạy mãi khi ta tự tắt mới dừng.
                        handler.postDelayed(this, 500);
                    }
                }, 2000); // delay 2 giây r mới chạy vô trong hàm run()
            }
        });

    }
}
