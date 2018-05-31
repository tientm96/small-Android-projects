package com.mtit.minhtien.intentimplicitwithactionview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imgBrowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgBrowser = (ImageView) findViewById(R.id.imageViewBrowser);

        imgBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(); //Không cần tham số Activity.this, Second.class. Tự nó sẽ chạy tới màn hình
                                                //ứng dụng của người dùng.

                //gán cho nó 1 hành động nào đó
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://khoapham.vn")); //ép sang kiểu đường link

                startActivity(intent);
            }
        });

    }
}
