package com.mtit.minhtien.intentexplicitonrestartonstartonresume;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

//Tự động khi tạo là extends AppcompatActivity, ta nên bỏ Appcompat để dễ dàng dùng hàm hơn.
//thuận lợi trong việc dùng các onStart(), onResum(), ...
//public class MainActivity extends AppCompatActivity {
public class MainActivity extends Activity {

    Button btnMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMain = (Button) findViewById(R.id.buttonMain);

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //màn hình hiện tại.this, màn hình tiếp theo .class
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);

            }
        });

        Log.d("AAA", "onCreate Main");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("AAA", "onStart Main");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("AAA", "onRestart Main");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("AAA", "onResume Main");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("AAA", "onPause Main");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("AAA", "onStop Main");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("AAA", "onDestroy Main");

    }
}
