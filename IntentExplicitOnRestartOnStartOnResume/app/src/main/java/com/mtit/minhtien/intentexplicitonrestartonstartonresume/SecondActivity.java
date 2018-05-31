package com.mtit.minhtien.intentexplicitonrestartonstartonresume;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

//Tự động khi tạo là extends AppcompatActivity, ta nên bỏ Appcompat để dễ dàng dùng hàm hơn.
//thuận lợi trong việc dùng các onStart(), onResum(), ...
//public class SecondActivity extends AppCompatActivity {
public class SecondActivity extends Activity {
    Button btnSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnSecond= (Button) findViewById(R.id.buttonSecond);

        btnSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Log.d("AAA", "onCreate Second");
    }

    //--------------------------------- Chuột phải Generate->override methods-> chọn 6 "on#"
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("AAA", "onStart Second");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("AAA", "onRestart Second");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("AAA", "onResume Second");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("AAA", "onPause Second");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("AAA", "onStop Second");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("AAA", "onDestroy Second");

    }
}
