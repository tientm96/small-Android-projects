package com.example.mtit.newfirebasestorage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    ListView lvHinhAnh;
    ArrayList<HinhAnh> mangHinhAnh;
    HinhAnhAdapter adapter = null;



    Button btnSave;
    ImageView imgHinh;
    EditText edtTenHinh;

    int REQUEST_CODE_IMAGE = 123;

    FirebaseStorage storage = FirebaseStorage.getInstance();

    DatabaseReference mData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Khởi tạo storageRef để upload file from data
        //Create a storage reference from our app
        final StorageReference storageRef = storage.getReference();

        //khởi tạo firebase database
        mData = FirebaseDatabase.getInstance().getReference();

        AnhXa();

        adapter = new HinhAnhAdapter(this, R.layout.dong_hinh_anh, mangHinhAnh);
        lvHinhAnh.setAdapter(adapter);

        LoadData();

        imgHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE_IMAGE);
            }
        });



        //UpLoads file from data in memory to server
        //copy code từ https://firebase.google.com/docs/storage/android/upload-files?authuser=1
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Để mỗi lần tạo hình thì tên tự đổi, ta cộng thêm mốc thời gian vào.
                Calendar calendar = Calendar.getInstance();

                //Khởi tạo nốt con, click rồi mới tạo
                // Create a reference to "mountains.jpg"
                StorageReference mountainsRef = storageRef.child("image" + calendar.getTimeInMillis() + ".png");


                //code copy về và custom một ít
                // Get the data from an ImageView as bytes
                imgHinh.setDrawingCacheEnabled(true);
                imgHinh.buildDrawingCache();
                Bitmap bitmap = imgHinh.getDrawingCache();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos); //sửa format JPEG to PNG
                byte[] data = baos.toByteArray();


                UploadTask uploadTask = mountainsRef.putBytes(data);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        Toast.makeText(MainActivity.this, "Upload failed", Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        Toast.makeText(MainActivity.this, "Successed", Toast.LENGTH_SHORT).show();

                        Log.d("AAAA", downloadUrl + ""); //link lưu ảnh trên server


                        //Tạo node trên phần database, lưu vào database
                        //Nếu lưu thành công, sẽ trả về link hình downloadUrl, chuyển url thành chuỗi link
                        HinhAnh hinhAnh = new HinhAnh(edtTenHinh.getText().toString(), String.valueOf(downloadUrl));
                        mData.child("HinhAnh").push().setValue(hinhAnh, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                if(databaseError == null){
                                    Toast.makeText(MainActivity.this, "Lưu xuống database thành công", Toast.LENGTH_SHORT).show();
                                    edtTenHinh.setText("");
                                    imgHinh.setImageResource(R.drawable.no);
                                }else{
                                    Toast.makeText(MainActivity.this, "Lỗi lưu xuống database", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
            }
        });




    }


    //load hình ảnh ra listview.
    //Nhận Object từ firebase, đưa về máy
    private void LoadData(){
        mData.child("HinhAnh").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                HinhAnh hinhAnh = dataSnapshot.getValue(HinhAnh.class);
                mangHinhAnh.add(new HinhAnh(hinhAnh.getTenHinh(), hinhAnh.getLinkHinh()));

                //mỗi lần load về một hình, adapter phải có sự kiện thay đổi
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK && data != null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data"); //key name default "data"
            imgHinh.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }





    private void AnhXa(){
        btnSave = (Button) findViewById(R.id.buttonSave);
        imgHinh = (ImageView) findViewById(R.id.imageViewHinh);
        edtTenHinh = (EditText) findViewById(R.id.editTextTenHinh);

        lvHinhAnh = (ListView) findViewById(R.id.listViewHinh);
    }
}
