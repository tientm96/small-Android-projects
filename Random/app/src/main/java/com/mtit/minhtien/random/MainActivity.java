package com.mtit.minhtien.random;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView txtNumber;
    private EditText edtNumber1, edtNumber2;
    private Button btnRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ánh xạ
        AnhXa();

        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String chuoi1 = edtNumber1.getText().toString().trim();
                String chuoi2 = edtNumber2.getText().toString().trim();

                //trường hợp bỏ trống ô sẽ bị báo lỗi
                if(chuoi1.isEmpty() || chuoi2.length() == 0){
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đủ số", Toast.LENGTH_SHORT).show();
                }else{

                    //tạo số ngẫu nhiên từ [số thứ 1, số thứ 2]
                    //mà 2 số này nhập vào ban đầu là chuỗi, phải chuyển sang int
                    int min = Integer.parseInt(chuoi1);
                    int max = Integer.parseInt(chuoi2);

                    Random random = new Random();
                    int number = random.nextInt(max - min + 1) + min; //đọc vào 1 số random từ [min, max]
                    //txtNumber.setText(number + "");
                    txtNumber.setText(String.valueOf(number)); // hay number + ""
                }
            }
        });
    }

    private void AnhXa(){
        txtNumber = (TextView) findViewById(R.id.textViewNumber);
        btnRandom = (Button) findViewById(R.id.buttonRandom);
        edtNumber1 = (EditText) findViewById(R.id.editTextNumberOne);
        edtNumber2 = (EditText) findViewById(R.id.editTextNumberTwo);
    }
}
