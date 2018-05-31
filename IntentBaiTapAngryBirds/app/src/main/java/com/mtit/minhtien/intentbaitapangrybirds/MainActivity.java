package com.mtit.minhtien.intentbaitapangrybirds;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    //mảng chứa tên hình
    public static ArrayList<String> arrayName;//màn hình khác có thể truy cập đến mảng này
    ImageView imgGoc, imgNhan;
    TextView txtDiem;
    int REQUEST_CODE_IMAGE = 123; //code gửi đi để tí kiểm tra lại
    String tenHinhGoc = "";

    int total = 100; // mặc định 100 điểm

    //khai báo sharedPreferences để lưu điểm số
    SharedPreferences luudiemso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgGoc = (ImageView) findViewById(R.id.imageViewGoc);
        imgNhan = (ImageView) findViewById(R.id.imageViewNhan);
        txtDiem = (TextView) findViewById(R.id.textViewDiem);

        //khởi tạo lưu điểm số
        luudiemso = getSharedPreferences("DiemSoGame", MODE_PRIVATE);

        //get điểm số, sau khi tắt mở lại thì điểm số vẫn còn nguyên
        //để ở đây, nếu dài thì dùng onStart cũng đc
        total = luudiemso.getInt("diem", 100); //nếu ko có thì gán = 100
        txtDiem.setText(total + "");

        //lấy tên của từng ảnh trong "string.xml", đưa vào 1 mangTen bt
        String[] mangTen = getResources().getStringArray(R.array.list_name);

        //chuyển mangTen của mảng bt, thành arraylist. Arraylist lúc này chỉ chứa tên hình thôi, vì kiểu String
        arrayName = new ArrayList<>(Arrays.asList(mangTen));


        //Thay vì ramdom, ta dùng cách khác là trộn mảng, như vậy mỗi lần chạy, phần tử thứ 5 sẽ thay đổi.
        Collections.shuffle(arrayName);
        tenHinhGoc = arrayName.get(5);


        //gán hình, ghép tên của hình và loại hình thành 1 id của tấm hình
        int idHinh = getResources().getIdentifier(arrayName.get(5), "drawable", getPackageName());
                                                                           //tên hình của phần tử số 6;
                                                                            //lọai của hình là trong "drawable"

        //truyền id hình vào imageview, ta đc cả hình lẫn tên.
        imgGoc.setImageResource(idHinh);



        imgNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, ImageActivity.class),
                        REQUEST_CODE_IMAGE);
            }
        });
    }

    //Nhận kết quả
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK && data != null) {
            String tenHinhNhan = data.getStringExtra("tenhinhchon");
            int idHinhNhan = getResources().getIdentifier(tenHinhNhan, "drawable", getPackageName());
            imgNhan.setImageResource(idHinhNhan);

            //so sánh theo tên hình
            if (tenHinhGoc.equals(tenHinhNhan)) {
                Toast.makeText(this, "Chính xác: +10 điểm", Toast.LENGTH_SHORT).show();
                //CỘNG ĐIỂM
                total += 10;
                LuuDiem(); //lưu điểm bằng shared...


                //nếu chính xác, đổi hình khác
                new CountDownTimer(2000, 100) { //đợi 2 giây
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                            //COPY NGUYÊN ĐOẠN TRỘN MẢNG Ở TRÊN
                        Collections.shuffle(arrayName);
                        tenHinhGoc = arrayName.get(5);
                        int idHinh = getResources().getIdentifier(arrayName.get(5), "drawable", getPackageName());

                        imgGoc.setImageResource(idHinh);
                    }
                }.start();

            }
            else {
                Toast.makeText(this, "Sai rồi: -5 điểm", Toast.LENGTH_SHORT).show();

                //trừ điểm
                total -= 5;
                LuuDiem();
            }

            txtDiem.setText(total + ""); //set lại điểm
        }



        //HOẶC LÀ ELSE HOẶC LÀ IF THÊM CÁI NỮA
        /*TH người chơi kích qua mh imageActivity mà ko chọn, bấm nút quay lại màn hình main xem lại là
        hình gì rồi mới chọn thì đó là ăn gian => xem như chọn sai, trừ điểm*/

        /*TH nếu như chọn sai hoặc không chọn, kết quả trả về không phải resultCode == RESULT_OK, mà phải
        mà resultCode == RESULT_CANCELED*/
        if(requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_CANCELED){
            Toast.makeText(this, "Bạn chưa chọn hình? \n Sai luật: -15 điểm!", Toast.LENGTH_SHORT).show();
            total -= 15;
            LuuDiem();
            txtDiem.setText(total + "");
        }


        super.onActivityResult(requestCode, resultCode, data);
    }


    //menu Reload
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.reload, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //bắt sk cho menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menuReload){
            //COPY NGUYÊN ĐOẠN TRỘN MẢNG Ở TRÊN
            Collections.shuffle(arrayName);
            tenHinhGoc = arrayName.get(5);
            int idHinh = getResources().getIdentifier(arrayName.get(5), "drawable", getPackageName());

            imgGoc.setImageResource(idHinh);
        }

        return super.onOptionsItemSelected(item);
    }


    //hàm lưu điểm số
    private void LuuDiem(){
        SharedPreferences.Editor editor = luudiemso.edit();
        editor.putInt("diem", total);
        editor.commit();
    }

}
