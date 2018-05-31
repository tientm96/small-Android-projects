package com.mtit.minhtien.fragmentaddbycodejava;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by MT IT on 11/27/2017.
 */

/*extendt Fragment(Android.app): chức năng đủ  dùng;
*còn extends Fragment(Android support.v4.app): nhiều chức năng hơn.
* --Vì chưa cần nhiều chức năng nên ta chọn extends .app thôi.
* */
public class FragmentB extends Fragment {

    //gọi hàm onCreateView //gắn layout xml vào java
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_b, container, false); //gắn layout xml vào java
    }
}
