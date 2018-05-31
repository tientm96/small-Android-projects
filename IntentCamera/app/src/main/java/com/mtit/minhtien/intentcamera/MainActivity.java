package com.mtit.minhtien.intentcamera;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnCamera;
    ImageView imgHinh;

    int REQUEST_CODE_CAMERA = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ánh xạ
        Mapped();

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Hỏi người dùng cho phép dùng camera hay không.
                Permissions();


                //kiểm tra sự cho phép của người dùng. Nếu cho phép, thì sử dụng intent camera luôn trong đó
                //Sửa dụng hàm "on" tự chạy: onRequestPermissionsResult()


                //Thu về kết quả hình đã chụp, sử dụng hàm "on" tự chạy: onActivityResult()

            }
        });
    }


    //Lấy câu trả lời của người dùng bằng hàm "onRequestPermissionsResult" tự chạy, cho phép hay không.
    //Khi hỏi là ActivityCompat.requestPermissions(); thì hàm trả lời onRequestPermissionsResult.
    //NẾU ĐƯỢC CHO PHÉP, TA GỌI LUÔN INTENT CAMERA LUÔN TRONG HÀM NÀY
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //3 điều kiện đủ để biết ng dùng cho phép: + request code ==
        //                                          + có câu tl => length  câu trả lời > 0
        //                                          + kiểm tra câu tl có thỏa ...
        if(requestCode == REQUEST_CODE_CAMERA && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            //Đc cho phép nên ta mở luôn intent camera trong này
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQUEST_CODE_CAMERA);
        }else{
            Toast.makeText(this, "Bạn không cho phép mở camera!", Toast.LENGTH_SHORT).show();
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }



    //Thu về kết quả, hàm này là 1 trong  các "on" của andr, nên sẽ tự chạy, không cần gọi nó
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data"); // data: key mặc định để nhận ảnh
            imgHinh.setImageBitmap(bitmap);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    //-----------------------PT BT, TA PHẢI GỌI NÓ KHI CẦN DÙNG

    //Ánh xạ
    private void Mapped(){
        btnCamera = (Button) findViewById(R.id.buttonCamera);
        imgHinh = (ImageView) findViewById(R.id.imageViewPicture);
    }


    //Hỏi người dùng cho phép dùng camera hay không. Còn lấy câu trả lời thì dùng hàm "on" tự chạy của andr
    private void Permissions(){
        //Các tham số bao gồm:
        //Màn hình gọi; mảng chứa các quyền, nếu như cần nhiều quyền thì cứ gọi thêm quyền vào trong mảng;
        //Tham số REQUEST_CODE ...
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_CAMERA);
    }

}
