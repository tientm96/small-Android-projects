package com.mtit.minhtien.animationintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMain = (Button) findViewById(R.id.buttonMain);

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));

                //trong intent, để diễn ra hiệu ứng Animation thì phải gọi đến hàm...
                overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit); //nó tự động lấy anim sao cho hợp lý
            }
        });
    }
}
