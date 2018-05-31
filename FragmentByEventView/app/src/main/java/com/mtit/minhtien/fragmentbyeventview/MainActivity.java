package com.mtit.minhtien.fragmentbyeventview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtMain;
    Button btnMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMain = (TextView) findViewById(R.id.textViewMain);
        btnMain = (Button) findViewById(R.id.buttonMain);

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. Activity tác động lên Fragment; MainActivity --> FRAGMENTA
                /*Fragment là 1 file.xml riêng, chứ không phải 1 <tag>, nên ánh xạ theo cách khác.*/
                FragmentA fragmentA = (FragmentA) getFragmentManager().findFragmentById(R.id.fragmentA);
                fragmentA.GanNoiDung("Change by Activity"); //gửi dòng này từ MainActi lên Fragment


                //2. Fragment tác động lên lại Activity; FRAGMENTB -->  MainActivity
                //Xem trong sự kiện của class FragmentB


                //3. Fragment tác động lên Fragment khác; FRAGMENTC <--> FRAGMENTA
                //Xem trong sự kiện của class FragmentC
            }
        });

    }



}
