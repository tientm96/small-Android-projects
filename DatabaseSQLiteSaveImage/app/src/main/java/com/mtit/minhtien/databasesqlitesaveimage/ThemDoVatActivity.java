package com.mtit.minhtien.databasesqlitesaveimage;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ThemDoVatActivity extends AppCompatActivity {

    Button btnAdd, btnCancel;
    EditText edtName, edtDescribe; //Describe: miêu tả
    ImageButton ibtnCamera, ibtnFolder;
    ImageView imgHinh;

    final int REQUEST_CODE_CAMERA = 123;
    final int REQUEST_CODE_FOLDER = 456;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_do_vat);

        Mapped();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //trở về lại màn hình main
                startActivity(new Intent(ThemDoVatActivity.this, MainActivity.class));
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //chuyển data imageview -> byte[]
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgHinh.getDrawable(); //lấy kiểu bitmapDrawable
                Bitmap bitmap = bitmapDrawable.getBitmap(); //chuyển từ bitmapDrawable về bitmap hình ảnh
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); //chuyển về mảng byte

                //định dạng lại đuôi hình ảnh in ra;
                //Todo: quality: chất lượng hình ảnh, càng nhỏ thì chất lượng càng cao. default = 100.
                bitmap.compress(Bitmap.CompressFormat.PNG,50, byteArrayOutputStream); //truyền dl lên byteArray

                //mảng byte để chứa dữ liệu
                byte[] hinhanh = byteArrayOutputStream.toByteArray();

                //Gọi đến database trong main, có static nên gọi trực tiếp
                MainActivity.database.INSERT_DOVAT(edtName.getText().toString().trim(),
                                                    edtDescribe.getText().toString().trim(),
                                                    hinhanh); //Insert vào database
                Toast.makeText(ThemDoVatActivity.this, "Đã thêm", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ThemDoVatActivity.this, MainActivity.class)); //trở về lại màn hình main
            }
        });


        ibtnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gọi tới hàm xin trong mainfest, hiện lên câu hỏi người dùng có cho phép sử dụng máy ảnh không?
                PermissionsCamera();
                /*-Ta sẽ có hàm onRequestPermissionsResult tự động chạy lấy câu trả lời của người dùng.
                * -Nếu kết quả là đồng ý thì nó thực hiện luôn việc gọi đến intent camera và thực thi chụp.
                *  Vậy nên ta đưa luôn 2 dòng code dưới đây vào trong hàm onRequestPermissionsResult.
                *
                * -Như vậy, chỉ cần gọi hàm Permission xin ý kiến người dùng, phần còn lại hàm onRequestPermissionsResult
                *  sẽ tự động chạy và onActivityResult tự động lấy hình kết quả.*/

                //Mở intent và chụp ảnh (đã đc đưa vào trong onRequestPermissionsResult)
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent, REQUEST_CODE_CAMERA);
            }
        });

        ibtnFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Tương tự với camera ở trên, ta gọi hàm PermissionFolder
                //gọi tới xin trong mainfest, hiện lên câu hỏi người dùng có cho phép lấy dữ liệu không?
                PermissionsFolder();

                /*Chỉ cần gọi hàm PermissionsFolder() sẽ hỏi ý kiến người dùng?
                * Sau đó onRequestPermissionsResult() sẽ tự động được gọi tới để lấy câu trả lời của người dùng,
                * nếu đồng ý thì sẽ mở và thực thi intent lấy dữ liệu ngay trong nó.
                * Kết quả sẽ tự động đc trả về trong onActivityResult*/

                //Mở intent lấy dữ liệu (đã đc đưa vào trong onRequestPermissionsResult)
//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setType("image/*");                   //mở ra file chung, nhưng chỉ lấy hình ảnh
//                startActivityForResult(intent, REQUEST_CODE_FOLDER);
            }
        });

    }


    //CÁC HÀM ON, TỰ ĐỘNG CHẠY-----------------------------------------------

    //Lấy kết quả trả lời của người dùng. Có cho phép dùng camera hay không? Có cho phép lấy dl hay không?
    //NẾU ĐƯỢC CHO PHÉP, TA GỌI INTENT THỰC THI Ở LUÔN TRONG HÀM NÀY
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //3 điều kiện đủ để biết ng dùng cho phép:  + request code ==
        //                                          + có câu tl => length  câu trả lời > 0
        //                                          + kiểm tra câu tl có thỏa ...

        //Ở SWITCH THÌ 2 REQUEST_CODE PHẢI KHAI BÁO FINAL (Cách 2: dùng if bọc bên ngoài cũng đc)
        switch (requestCode){
            case REQUEST_CODE_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Đc cho phép nên ta mở luôn intent camera trong này
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CODE_CAMERA);
                } else {
                    Toast.makeText(this, "Bạn không cho phép mở camera!", Toast.LENGTH_SHORT).show();
                }
                break;

            case REQUEST_CODE_FOLDER:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Đc cho phép nên ta mở luôn intent lấy dữ liệu luôn trong này
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");                   //mở ra file chung, nhưng chỉ lấy hình ảnh
                    startActivityForResult(intent, REQUEST_CODE_FOLDER);
                } else {
                    Toast.makeText(this, "Bạn không cho phép lấy dữ liệu!", Toast.LENGTH_SHORT).show();
                }
                break;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }



    //Trả về kết quả là hình ảnh sau khi chụp. Hoặc sau khi chọn hình từ folder. HÀM ON NÊN TỰ ĐỘNG CHẠY
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //kết quả chụp từ camera
        if(requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data"); //key name luôn là data
            imgHinh.setImageBitmap(bitmap);
        }

        //kết quả hình lấy từ folder
        else if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData(); //lấy uri chứa đường dẫn tới hình ảnh được chọn
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri); //lấy đường dẫn từ uri
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream); //giải mã hình ảnh từ link local = uri
                imgHinh.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }




    //Hàm phải gọi----------------------------------------------------------

    //gọi tới mainfest, hiện lên câu hỏi người dùng có cho phép sử dụng máy ảnh không?
    private void PermissionsCamera(){
        //Các tham số bao gồm:
        //Màn hình gọi; mảng chứa các quyền, nếu như cần nhiều quyền thì cứ gọi thêm quyền vào trong mảng;
        //Tham số REQUEST_CODE ...
        ActivityCompat.requestPermissions(ThemDoVatActivity.this,
                new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_CAMERA);
    }

    //gọi tới mainfest, hiện lên câu hỏi người dùng có cho phép lấy dữ liệu không?
    private void PermissionsFolder(){
        ActivityCompat.requestPermissions(ThemDoVatActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_FOLDER);
    }

    private void Mapped(){
        btnAdd = (Button) findViewById(R.id.buttonAddThem);
        btnCancel = (Button) findViewById(R.id.buttonCancel);
        edtDescribe = (EditText) findViewById(R.id.editTextMoTa); //Describe: mô tả
        edtName = (EditText) findViewById(R.id.editTextTenDoVat);
        ibtnCamera = (ImageButton) findViewById(R.id.imageButtonCamera);
        ibtnFolder = (ImageButton) findViewById(R.id.imageButtonFolder);
        imgHinh = (ImageView) findViewById(R.id.imageViewPicture);
    }

}
