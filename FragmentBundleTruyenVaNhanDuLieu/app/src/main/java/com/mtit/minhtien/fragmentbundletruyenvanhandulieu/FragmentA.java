package com.mtit.minhtien.fragmentbundletruyenvanhandulieu;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by MT IT on 11/28/2017.
 */

public class FragmentA extends Fragment { //(android.app)

    TextView txtNoiDung;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);

        //vì ở đây là class, chứ ko phải activity, nên phải ánh xạ thông qua view/context nào đó.
        txtNoiDung = (TextView) view.findViewById(R.id.textViewNoiDung);

        //Nhận dl truyền từ main
        Bundle bundle = getArguments();
        if(bundle != null){     //tránh TH dữ liệu lỗi
            txtNoiDung.setText(bundle.getString("hotenSinhVien")); //key của bundle gửi
        }


        return view;
    }
}






