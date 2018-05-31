package com.example.mtit.newfirebaseretrievedata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase; //khai báo
    private TextView txtKhoaHoc;

    private ListView lvXe;
    private ArrayList<String> arrayPhuongTien;
    private ArrayAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference(); //khởi tạo
        txtKhoaHoc = (TextView) findViewById(R.id.textViewKhoaHoc);

        lvXe = (ListView) findViewById(R.id.listViewPhuongTien);
        arrayPhuongTien = new ArrayList<String>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayPhuongTien);
        lvXe.setAdapter(adapter);
        //-------------------------------------------------------


        //Mỗi lần chạy sẽ push() lên liên tục, nên ko muốn push nữa phải cmt lại.
//        mDatabase.child("KhoaHoc").push().setValue("Lập trình enity");

        //Sự kiện addChildEvent, Đọc tất cả dữ liệu của 1 nốt về, cơ chế chạy tương tự vòng lặp for.
        //Đọc từng key của 1 nốt.
        mDatabase.child("KhoaHoc").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //Đọc về lần lượt từng môn liên tục.
//                Toast.makeText(MainActivity.this, dataSnapshot.getValue().toString() + "\n", Toast.LENGTH_SHORT).show();

                //Vì đọc thêm vào liên tục nên nếu dùng textView thì ta append(Thêm vào).
                txtKhoaHoc.append(dataSnapshot.getValue().toString() + "\n");
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


        //------------------------------------------------------------
        //addChildEvent với push Object
//        PhuongTien phuongTien = new PhuongTien("Xe Khách", 12);
//        mDatabase.child("PhuongTien").push().setValue(phuongTien);

        mDatabase.child("PhuongTien").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                PhuongTien pt = dataSnapshot.getValue(PhuongTien.class);

                arrayPhuongTien.add(pt.getTen() + " - " + pt.getBanh() + " bánh");
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
}
