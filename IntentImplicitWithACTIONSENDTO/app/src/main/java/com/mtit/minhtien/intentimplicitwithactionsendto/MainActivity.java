package com.mtit.minhtien.intentimplicitwithactionsendto;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imgMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgMessage = (ImageView) findViewById(R.id.imageViewMessage);

        imgMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                //tạo hành động cho intent
                intent.setAction(Intent.ACTION_SENDTO);

                //tên mặc định luôn là sms_body
                intent.putExtra("sms_body", "Chào bạn!!!"); //tên mặc định, nd tn

                intent.setData(Uri.parse("sms:01663538331")); //định dạng sms, sdt nhận tn

                startActivity(intent);
            }
        });
    }
}
