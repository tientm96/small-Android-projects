package com.mtit.minhtien.fragmentbyeventview;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by MT IT on 11/28/2017.
 */

public class FragmentA extends Fragment { //(Android.app)

    TextView txtA;
    EditText edtA;
    Button btnA;

    /*- Cũng giống như Adapter. Vì Fragment chỉ là một class, chứ không phải Activity,
    *      nên không thể ánh xạ trực tiếp được, mà phải thông qua 1 context/view nào đó.
    * - Hàm onCreateView trả về View chính là: đoạn code gán màn hình .xml vào .java
    * - Sau khi có view: ta có thể ánh xạ thông qua view.*/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //giống như ánh xạ; view sẽ đưa fragment .xml sang class Fragment.java
        View view = inflater.inflate(R.layout.fragment_a, container, false);


        txtA = (TextView) view.findViewById(R.id.textViewFragmentA);
        edtA = (EditText) view.findViewById(R.id.editTextFragmentA);
        btnA = (Button) view.findViewById(R.id.buttonFragmentA);


        /*Xét sự kiện button trên fragment: Gửi nội dung từ Activity qua Fragment
         * - Ở FragmentA là "1.Nhận tác động từ MainActivity", nên chỉ dùng hàm GanNoiDung() bên dưới.
         *
         * - Ở event btnA này, ta chỉ cần Toast lên giá trị nhập vào editext của Fragment thôi. */
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Bình thường sẽ getContext(). Nhưng trong Fragment phải rõ ràng, nên getActivity().
                    Khi đó nếu là Toast thì sẽ Toast lên trên mh Activity đang chứa fragment đó.
                     */
                Toast.makeText(getActivity(), edtA.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


    //-------------------------------HÀM TỰ TẠO

    /*Ở main, khi muốn tác động đến Fragment thì chỉ cần gọi đến phương thức của Fragment.
    * Như vậy code sẽ tập trung tại Fragment, không quy tụ trong main.
    *
    * --> Tạo pt để main gọi đến: public*/
    public void GanNoiDung(String noidung){
        txtA.setText(noidung);
    }



}
