package com.mtit.minhtien.fragmentbundletruyenvanhandulieu;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.buttonAdd);
        FragmentManager fragmentManager = getFragmentManager();    //(android.app)
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();    //(android.app)


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentA fragmentA = new FragmentA();


                //để truyền dl, ta dùng tới Bundle
                Bundle bundle = new Bundle();
                bundle.putString("hotenSinhVien", "Nguyễn Văn A"); //key, value

                //gửi dữ liệu từ Main qua Fragment
                fragmentA.setArguments(bundle);




                /*Vì .xml ta dùng LinearLayout với vertical. Fragment add vào tự động sx từ trên xuống,
                *   nên ta không cần đưa fragment vào frameLayout.
                *   + nhưng buộc phải có id cho linearLayout ở trong .xml, để gọi đến khi add trực tiếp vào.
                *=>Còn nếu layout khác, thì ta phải dùng frameLayout để cố định vị trí fragment add vào.*/
                fragmentTransaction.add(R.id.myLinearLayout, fragmentA);
                fragmentTransaction.commit();

            }
        });
    }
}
