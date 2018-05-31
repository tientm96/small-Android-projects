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

/**
 * Created by MT IT on 11/28/2017.
 */

public class FragmentB extends Fragment { //(Android.app)

    TextView txtB;
    EditText edtB;
    Button btnB;

    /*- Cũng giống như Adapter. Vì Fragment chỉ là một class, chứ không phải Activity,
    *      nên không thể ánh xạ trực tiếp được, mà phải thông qua 1 context/view nào đó.
    * - Hàm onCreateView trả về View chính là: đoạn code gán màn hình .xml vào .java
    * - Sau khi có view: ta có thể ánh xạ thông qua view.*/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //giống như ánh xạ; view sẽ đưa fragment .xml sang class Fragment.java
        View view = inflater.inflate(R.layout.fragment_b, container, false);

        txtB = (TextView) view.findViewById(R.id.textViewFragmentB);
        edtB = (EditText) view.findViewById(R.id.editTextFragmentB);
        btnB = (Button) view.findViewById(R.id.buttonFragmentB);


        /*Xét sự kiện button trên fragment: Gửi nội dung từ Fragment đến Activity
         * - Ở FragmentB là "2.Fragment tác động ngược lại MainActivity".
         *      Nên ta phải ánh xạ những phần cần tác động của Main vào đây, để set up sự kiện cho btnB.
         *
         * - Nhập text vào edt trong Fragment. Nhấn btnB của Fragment, thì text sẽ chuyển qua TextView của Main.
         *      nên trong này phải ánh xạ lại textViewMain.
         * */
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Bình thường sẽ getContext(). Nhưng trong Fragment phải rõ ràng, nên getActivity().
                       - Khi đó nếu là Toast thì sẽ Toast lên trên mh Activity đang chứa fragment đó.
                       - Còn nếu Ánh Xạ thì cũng sẽ Ánh Xạ lên mh Activity đang chứa fragment đó.
                     */
                TextView txtMain = (TextView) getActivity().findViewById(R.id.textViewMain);
                txtMain.setText(edtB.getText().toString());

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
        txtB.setText(noidung);
    }

}
