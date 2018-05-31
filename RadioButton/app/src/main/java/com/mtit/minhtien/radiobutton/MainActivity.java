package com.mtit.minhtien.radiobutton;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroupTime;
    RadioButton rdSang, rdTrua, rdToi;
    Button btnXacNhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ánh xạ
        AnhXa();

        //sự kiện
        radioGroupTime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                //i: trả về id của RadioButton đc chọn
                switch (i){
                    case R.id.radioButtonSang:
                        Toast.makeText(MainActivity.this, "Chọn sáng", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioButtonTrua:
                        Toast.makeText(MainActivity.this, "Chọn trưa", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioButtonToi:
                        Toast.makeText(MainActivity.this, "Chọn tối", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String luachon = "Bạn đã chọn: ";
                if(rdSang.isChecked()){
                    luachon += rdSang.getText();
                }
                if(rdTrua.isChecked()){
                    luachon += rdTrua.getText();
                }
                if(rdToi.isChecked()){
                    luachon += rdToi.getText();
                }

                Toast.makeText(MainActivity.this, luachon, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void AnhXa(){
        radioGroupTime = (RadioGroup) findViewById(R.id.radioGroupThoigian);
        btnXacNhan = (Button) findViewById(R.id.buttonXacNhan);
        rdSang = (RadioButton) findViewById(R.id.radioButtonSang);
        rdTrua = (RadioButton) findViewById(R.id.radioButtonTrua);
        rdToi = (RadioButton) findViewById(R.id.radioButtonToi);
    }
}
