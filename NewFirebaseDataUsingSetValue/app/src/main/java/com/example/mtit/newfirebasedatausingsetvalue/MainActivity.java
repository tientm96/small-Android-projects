package com.example.mtit.newfirebasedatausingsetvalue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        //TH1: lưu trữ đơn
        mDatabase.child("HoTen").setValue("Trần Minh Tiến");

        //TH2: lưu trữ object
        SinhVien sv = new SinhVien("Lê Hồng Phong", "Quận 1", 1995);
        mDatabase.child("SinhVien").setValue(sv);

        //TH3: dùng kiểu Map<keyString, object>
        Map<String, Integer> myMap = new HashMap<String, Integer>();
        myMap.put("XeMay", 2);
        mDatabase.child("PhuongTien").setValue(myMap);

        //TH4: dùng push(), đưa lên firebase theo key tự tạo,dùng để tạo nhiều đt trong cùng 1 child.
        //Mỗi lần push sẽ 1 đối tượng, và sẽ ko sửa được, vì mỗi lần sẽ tự tạo key khác nhau.
        SinhVien sinhVien = new SinhVien("Nguyen Van B", "Hội An", 2005);
        mDatabase.child("HocVien").push().setValue(sinhVien); //push sẽ cho key là child con của HocVien





        //----------------------------------------------------
        //Bắt trạng thái lưu dữ liệu với completionListener, kb ở tham số thứ 2.
                                                                                // new Com rồi chọn
        mDatabase.child("KhoaPhamTraining").setValue("Lập trình Android", new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if(databaseError == null){ //nếu lỗi rỗng
                    Toast.makeText(MainActivity.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Lưu thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        
    }
}
