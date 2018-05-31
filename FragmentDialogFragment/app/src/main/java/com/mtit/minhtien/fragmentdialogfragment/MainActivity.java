package com.mtit.minhtien.fragmentdialogfragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnXoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnXoa = (Button) findViewById(R.id.buttonXoa);

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentDialog fragmentDialog = new FragmentDialog(); //là lớp vừa tạo
                fragmentDialog.show(getFragmentManager(), "dialog"); //tag: "dialog" đặt tùy ý

            }
        });

    }
}
