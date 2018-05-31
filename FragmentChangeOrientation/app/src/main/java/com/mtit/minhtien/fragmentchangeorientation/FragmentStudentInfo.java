package com.mtit.minhtien.fragmentchangeorientation;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by MT IT on 11/29/2017.
 */

public class FragmentStudentInfo extends Fragment {     //(android.app)

    TextView txtHoTen, txtNamSinh, txtDiaChi, txtEmail;
    View view; //khai báo toàn cục để dùng đc cho nhiều hàm.

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_student_info, container, false);

        AnhXa(); //buộc phải đưa view ra toàn cục.

        return view;
    }

    public void SetInfo(SinhVien sinhVien){ //bên main sẽ gọi, nên để public
        txtHoTen.setText(sinhVien.getHoten());
        txtNamSinh.setText("Năm sinh: " + sinhVien.getNamsinh());
        txtDiaChi.setText("Địa chỉ: " + sinhVien.getDiachi());
        txtEmail.setText("Email: " + sinhVien.getEmail());
    }


    private void AnhXa(){
        // tránh ánh xạ nhầm với textViewHoTen của row_student.xml
        txtHoTen    = (TextView) view.findViewById(R.id.textViewHoTenInfo);
        txtNamSinh  = (TextView) view.findViewById(R.id.textViewNamsinh);
        txtDiaChi   = (TextView) view.findViewById(R.id.textViewDiaChi);
        txtEmail    = (TextView) view.findViewById(R.id.textViewEmail);
    }
}
