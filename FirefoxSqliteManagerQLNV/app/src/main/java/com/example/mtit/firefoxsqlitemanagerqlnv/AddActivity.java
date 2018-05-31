package com.example.mtit.firefoxsqlitemanagerqlnv;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddActivity extends AppCompatActivity {
    /*ID TĂNG TỰ ĐỘNG(Đã cài đặt AUTOINCREMENT trong khi tạo table), KO CẦN ADD VÀO*/

    final String DATABASE_NAME = "EmployeeDB.sqlite";

    Button btnLibrary, btnCamera, btnSave, btnCancel;
    EditText edtTen, edtSDT;
    ImageView imgHinhUpdate;

    final int REQUEST_CODE_CAMERA = 123;
    final int REQUEST_CODE_FOLDER = 456;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        //Ánh xạ
        addControls();

        //Sự kiện chụp ảnh và chọn ảnh trong thư viện
        takePictureEvent();

        //Sự kiện Save và Cancel
        getDecisionsEvent();


    }



    /*--------------------------------------------------------------------------------------------*/

    private void addControls(){

        btnLibrary = (Button) findViewById(R.id.buttonLibraryAdd);
        btnCamera = (Button) findViewById(R.id.buttonCameraAdd);
        btnSave = (Button) findViewById(R.id.buttonLuuAdd);
        btnCancel = (Button) findViewById(R.id.buttonHuyAdd);

        edtTen = (EditText) findViewById(R.id.editTextNameAdd);
        edtSDT = (EditText) findViewById(R.id.editTextSdtAdd);
        imgHinhUpdate = (ImageView) findViewById(R.id.imageViewHinhAdd);
    }


    /*--------------------------------------------------------------------------------------------*/
    /*TODO: EVENT BUTTON CAMERA AND CHOOSE PICTURE IN FOLDER*/

    private void takePictureEvent() {

        btnCamera.setOnClickListener(new View.OnClickListener() {
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

        btnLibrary.setOnClickListener(new View.OnClickListener() {
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




    //CÁC HÀM ON, TỰ ĐỘNG CHẠY-----------------------------

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
            imgHinhUpdate.setImageBitmap(bitmap);
        }

        //kết quả hình lấy từ folder
        else if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData(); //lấy uri chứa đường dẫn tới hình ảnh được chọn
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri); //lấy đường dẫn từ uri
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream); //giải mã hình ảnh từ link local = uri
                imgHinhUpdate.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }



    //----------Hàm cần phải gọi, ko auto
    //Todo: gọi tới mainfest, hiện lên câu hỏi người dùng có cho phép sử dụng máy ảnh không?
    private void PermissionsCamera(){
        //Các tham số bao gồm:
        //Màn hình gọi; mảng chứa các quyền, nếu như cần nhiều quyền thì cứ gọi thêm quyền vào trong mảng;
        //Tham số REQUEST_CODE ...
        ActivityCompat.requestPermissions(AddActivity.this,
                new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_CAMERA);
    }

    //gọi tới mainfest, hiện lên câu hỏi người dùng có cho phép lấy dữ liệu không?
    private void PermissionsFolder(){
        ActivityCompat.requestPermissions(AddActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_FOLDER);
    }




    /*--------------------------------------------------------------------------------------------*/
    /*TODO: EVENT BUTTON SAVE AND CANCEL*/
    private void getDecisionsEvent(){    //Save update or Cancel

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //trở về lại màn hình main
                startActivity(new Intent(AddActivity.this, MainActivity.class));
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //đưa vào ảnh folder hoặc ảnh chụp, hàm sẽ trả về ảnh byte[] để lưu xuống database.
                //byte[] trên java tương ứng với Blob dưới server.
                byte[] hinhanh = getByteArrayFromImageView(imgHinhUpdate);

                /*Update vào firefoxDatabase*/
                //Đưa vào contentValues
                ContentValues contentValues = new ContentValues();

                contentValues.put("Ten", edtTen.getText().toString().trim()); //các keyName phải trùng với cột trong Database,
                contentValues.put("SDT", edtSDT.getText().toString().trim());   //theo thứ tự trong database
                contentValues.put("Anh", hinhanh);

                //insert vào database
                SQLiteDatabase database = Database.initDatabase(AddActivity.this , "EmployeeDB.sqlite");
                database.insert("NhanVien", null, contentValues);

                Toast.makeText(AddActivity.this, "Đã thêm!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddActivity.this, MainActivity.class)); //trở về lại màn hình main



                /*Vì là activity: thì việc load lại sẽ tự động sau mỗi lần chuyển activity bằng intent, nên ta ko cần
                 *      gọi hay viết lại hàm readData() để load lại dữ liệu listview.
                 *
                 *Còn nếu dialog: sẽ ko tự động nên sau khi thêm, xóa, sửa xong thì phải load lại dữ liệu, phải gọi hàm load.*/
            }
        });

    }


    /*Vì trong database hình ảnh sẽ kiểu Blob tương thích với kiểu byte[] của java.
    *Nên để đưa ảnh folder hoặc ảnh chụp xuống, hàm sẽ phải trả về ảnh byte[] để lưu xuống database
    */
    private byte[] getByteArrayFromImageView(ImageView imgUpdate){
        //chuyển data imageview -> byte[]
        BitmapDrawable bitmapDrawable = (BitmapDrawable) imgUpdate.getDrawable(); //lấy kiểu bitmapDrawable
        Bitmap bitmap = bitmapDrawable.getBitmap(); //chuyển từ bitmapDrawable về bitmap hình ảnh

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); //chuyển về mảng byte

        //định dạng lại đuôi hình ảnh in ra;
        //Todo: quality: chất lượng hình ảnh, càng nhỏ thì chất lượng càng cao. default = 100.
        bitmap.compress(Bitmap.CompressFormat.PNG,50, byteArrayOutputStream); //truyền dl lên byteArray

        //mảng byte để chứa dữ liệu
        byte[] hinhanh = byteArrayOutputStream.toByteArray();

        return hinhanh;
    }

/*------------------------------------------------------------------------------------------------*/





}
