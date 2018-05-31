package com.mtit.minhtien.cuocduakythu;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtDiem;
    ImageButton ibtnPlay;
    CheckBox cbOne, cbTwo, cbThree;
    SeekBar skOne, skTwo, skThree;

    int soDiem = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ánh xạ
        AnhXa();
        txtDiem.setText(soDiem + "");

        //tắt chức năng kéo thả của seekBar. Tắt mọi lúc nên đặt luôn ngoài này
        DisabledSeekBar();

        //cho con vật chạy tự động.
        //Thời gian đếm ngược 60s, sau 0.3s sẽ tăng lên 1 nấc(thời gian nghĩ)
        //60s là con số lớn cho ra để trừ hao, thực tế ta cài đặt khi con nào đến trước sẽ dừng cả 3.
        final CountDownTimer countDownTimer = new CountDownTimer(60000, 100) {
            @Override
            public void onTick(long l) { //hàm chứa hành động lặp lại sau 0.1s, số càng nhỏ chạy càng nhanh
                int number = 4;
                Random random = new Random();
                int one = random.nextInt(number); //random ra từ 0 -> 3
                int two = random.nextInt(number);
                int three = random.nextInt(number);

                //Kiểm tra WIN One
                if(skOne.getProgress() >= skOne.getMax()){ // >= bởi vì lỡ đã 98 rồi + 4 thì 102 > 100
                    this.cancel(); //this chính là nguyên cái countDownTimer.

                    ibtnPlay.setVisibility(View.VISIBLE); // Hiện nút play lên lại
                    Toast.makeText(MainActivity.this, "ONE WIN", Toast.LENGTH_SHORT).show();

                    //Nếu one về nhất thì kiểm tra đặt cược
                    if(cbOne.isChecked()){
                        soDiem += 10;
                        Toast.makeText(MainActivity.this, "Đoán chính xác: +10 điểm", Toast.LENGTH_SHORT).show();
                    }else {
                        soDiem -= 5;
                        Toast.makeText(MainActivity.this, "Đoán sai rồi: -5 điểm", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(soDiem + "");
                    EnableCheckBox();
                }
                //Kiểm tra WIN Two
                if(skTwo.getProgress() >= skTwo.getMax()){
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "TWO WIN", Toast.LENGTH_SHORT).show();

                    //Nếu two về nhất thì kiểm tra đặt cược
                    if(cbTwo.isChecked()){
                        soDiem += 10;
                        Toast.makeText(MainActivity.this, "Đoán chính xác: +10 điểm", Toast.LENGTH_SHORT).show();
                    }else {
                        soDiem -= 5;
                        Toast.makeText(MainActivity.this, "Đoán sai rồi: -5 điểm", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(soDiem + "");
                    EnableCheckBox();
                }
                //Kiểm tra WIN Three
                if(skThree.getProgress() >= skThree.getMax()){
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "THREE WIN", Toast.LENGTH_SHORT).show();

                    //Nếu three về nhất thì kiểm tra đặt cược
                    if(cbThree.isChecked()){
                        soDiem += 10;
                        Toast.makeText(MainActivity.this, "Đoán chính xác: +10 điểm", Toast.LENGTH_SHORT).show();
                    }else {
                        soDiem -= 5;
                        Toast.makeText(MainActivity.this, "Đoán sai rồi: -5 điểm", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(soDiem + "");
                    EnableCheckBox();
                }



                skOne.setProgress(skOne.getProgress() + one); //như v, sau mỗi 0.3s thì mỗi con sẽ
                skTwo.setProgress(skTwo.getProgress() + two); //chạy nhanh hơn mỗi độ dài khác nhau.
                skThree.setProgress(skThree.getProgress() + three);
            }

            @Override
            public void onFinish() {

            }
        };

        ibtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //nếu 1 trong 3 checkbox đc check thì "countDownTimer" mới đc start
                if(cbOne.isChecked() || cbTwo.isChecked() || cbThree.isChecked()){
                    skOne.setProgress(0);
                    skTwo.setProgress(0);
                    skThree.setProgress(0);

                    ibtnPlay.setVisibility(View.INVISIBLE); // Sau khi nhấn play thì Ẩn đi
                    countDownTimer.start();

                    DisableCheckBox();
                }else{
                    Toast.makeText(MainActivity.this, "Vui lòng đặt cược trước khi chơi!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //set check 3 check box, chỉ đc check 1 trong 3. bt checkbox check đc nhiều vẫn đc.
        cbOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){ //nếu check 1 đc check thì
                    //bỏ check 2, 3
                    cbTwo.setChecked(false);
                    cbThree.setChecked(false);
                }
            }
        });
        cbTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    //bỏ check 1, 3
                    cbOne.setChecked(false);
                    cbThree.setChecked(false);
                }
            }
        });
        cbThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    //bỏ check 1, 2
                    cbOne.setChecked(false);
                    cbTwo.setChecked(false);
                }
            }
        });



    }

    //Sửa TH check đang chạy vẫn có thể kích chọn checkbox, ta sử dụng 2 hàm, cho check và không cho check
    private void EnableCheckBox(){
        cbOne.setEnabled(true);   //cho đến khi 1 trong các con chạy đến 100% thì mới đc check lại
        cbTwo.setEnabled(true);
        cbThree.setEnabled(true);
    }

    //Disable <=> ta click Play thì ko đc chọn checkbox nữa, cho đến khi 1 con chạy hết thì mới đc chọn lại
    private void DisableCheckBox(){
        cbOne.setEnabled(false);
        cbTwo.setEnabled(false);
        cbThree.setEnabled(false);
    }

    //Tắt chức năng kéo thả tùy vị trí của SeekBar, để tránh lúc đang chạy người dùng lại kéo thả tùy ý
    private  void DisabledSeekBar(){
        skOne.setEnabled(false);
        skTwo.setEnabled(false);
        skThree.setEnabled(false);
    }


    private void AnhXa(){
        txtDiem = (TextView) findViewById(R.id.textviewDiemSo);
        ibtnPlay = (ImageButton) findViewById(R.id.imagebuttonPlay);

        cbOne = (CheckBox) findViewById(R.id.checkboxOne);
        cbTwo = (CheckBox) findViewById(R.id.checkboxTwo);
        cbThree = (CheckBox) findViewById(R.id.checkboxThree);

        skOne = (SeekBar)findViewById(R.id.seekbarOne);
        skTwo = (SeekBar) findViewById(R.id.seekbarTwo);
        skThree = (SeekBar) findViewById(R.id.seekbarThree);
    }
}
