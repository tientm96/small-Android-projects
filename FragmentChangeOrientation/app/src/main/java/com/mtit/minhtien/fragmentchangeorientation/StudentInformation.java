package com.mtit.minhtien.fragmentchangeorientation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StudentInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_information);

        //Nhận dl
        Intent intent = getIntent();
        SinhVien sinhVien = (SinhVien) intent.getSerializableExtra("thongtinSinhVien"); //phải trùng keyname

        //tham chiếu fragmentInfo đến màn hình Info dọc (mh này)
        FragmentStudentInfo fragmentStudentInfo = (FragmentStudentInfo) getFragmentManager().findFragmentById(R.id.fragmentInformation);

        //set giá trị hiển thị
        fragmentStudentInfo.SetInfo(sinhVien);
    }
}
