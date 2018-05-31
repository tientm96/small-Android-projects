package com.example.mtit.newfirebaserealtime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private TextView txtKhoaHoc;
    private Button btnAndroid, btnIOS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        txtKhoaHoc = (TextView) findViewById(R.id.textviewKhoaHoc);

        btnAndroid = (Button) findViewById(R.id.buttonAndroid);
        btnIOS = (Button) findViewById(R.id.buttonIOS);

        mDatabase.child("KhoaHoc").setValue("Lập trình android");


        //addValueEvent: Sự kiện trả về sự thay đổi từ server về máy.
        // Server thay đổi thì máy thay đổi ngay lập tức.
        mDatabase.child("KhoaHoc").addValueEventListener(new ValueEventListener() {

            @Override //nhánh KhoaHoc có thay đổi, thì sẽ đưa chuỗi kết quả về dataSnapshot ngay
            public void onDataChange(DataSnapshot dataSnapshot) {
                txtKhoaHoc.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        //Kiểm tra xem từ app đẩy dl lên server, thì server có nhận thay đổi dl ngay lập tức?
        btnAndroid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child("KhoaHoc").setValue("Android");
            }
        });
        btnIOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child("KhoaHoc").setValue("iOS");
            }
        });


    }
}
