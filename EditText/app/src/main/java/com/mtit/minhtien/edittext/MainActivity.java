package com.mtit.minhtien.edittext;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtNoiDung;
    Button btnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ánh xạ
        edtNoiDung = (EditText) findViewById(R.id.editTextHoTen);
        btnClick = (Button) findViewById(R.id.buttonClick);

        //nếu muốn thay đổi text
        edtNoiDung.setText("Tiến đập choai");

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String noidung = edtNoiDung.getText().toString();
                Toast.makeText(MainActivity.this, noidung, Toast.LENGTH_LONG).show();
            }
        });
    }
}
