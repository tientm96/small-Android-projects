package com.mtit.minhtien.checkbox;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox cbAndroid, cbIOS, cbPHP;
    Button btnXacNhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        cbAndroid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Toast.makeText(MainActivity.this, "Bạn chọn Android", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Bạn bỏ chọn Android", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cbIOS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Toast.makeText(MainActivity.this, "Bạn chọn IOS", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Bạn bỏ chọn IOS", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cbPHP.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Toast.makeText(MainActivity.this, "Bạn chọn PHP", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Bạn bỏ chọn PHP", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String luachon = "Bạn đã chọn: ";
                if(cbAndroid.isChecked()){
                    luachon += cbAndroid.getText() + "\n";
                }
                if(cbIOS.isChecked()){
                    luachon += cbIOS.getText() + "\n";
                }
                if(cbPHP.isChecked()){
                    luachon += cbPHP.getText() + "\n";
                }

                Toast.makeText(MainActivity.this, luachon, Toast.LENGTH_LONG).show();
            }
        });

    }

    private void AnhXa(){
        cbAndroid = (CheckBox) findViewById(R.id.checkBoxAndroid);
        cbIOS = (CheckBox) findViewById(R.id.checkBoxIOS);
        cbPHP = (CheckBox) findViewById(R.id.checkBoxPHP);

        btnXacNhan = (Button) findViewById(R.id.buttonXacNhan);
    }
}
