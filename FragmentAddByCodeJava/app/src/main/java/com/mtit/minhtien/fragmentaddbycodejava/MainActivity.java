package com.mtit.minhtien.fragmentaddbycodejava;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Gọi fragment trong code java:
        *   - Ban đầu do trong lúc tạo .java Fragment ta dùng extendt Fragment(Android.app) để lấy xml fragment,
        *           nên ở đây cũng phải dùng FragmentManager(Android.app) để gọi nó
        **/

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //gọi class chứa Fragment để khởi tạo
        FragmentA fragmentA = new FragmentA();
        FragmentB fragmentB = new FragmentB();

        //dùng fragmentTransaction để add vào
        fragmentTransaction.add(R.id.frameContent, fragmentA); //add fragment vào frameContent
        fragmentTransaction.add(R.id.frameContent, fragmentB);

        //xác nhận add
        //MỖI HÀM CHỈ GỌI ĐƯỢC 1 LẦN COMMIT
        fragmentTransaction.commit();

    }
}
