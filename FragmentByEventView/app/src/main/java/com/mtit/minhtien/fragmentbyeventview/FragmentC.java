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

public class FragmentC extends Fragment { //(Android.app)

    TextView txtC;
    EditText edtC;
    Button btnC;

    /*- Cũng giống như Adapter. Vì Fragment chỉ là một class, chứ không phải Activity,
    *      nên không thể ánh xạ trực tiếp được, mà phải thông qua 1 context/view nào đó.
    * - Hàm onCreateView trả về View chính là: đoạn code gán màn hình .xml vào .java
    * - Sau khi có view: ta có thể ánh xạ thông qua view.*/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //giống như ánh xạ; view sẽ đưa fragment .xml sang class Fragment.java
        View view = inflater.inflate(R.layout.fragment_c, container, false);

        txtC = (TextView) view.findViewById(R.id.textViewFragmentC);
        edtC = (EditText) view.findViewById(R.id.editTextFragmentC);
        btnC = (Button) view.findViewById(R.id.buttonFragmentC);


        /*Xét sự kiện button trên fragment: Gửi nội dung từ FragmentC đến FragmentA
         * - Ở FragmentC là "3.FragmentC tác động lên FragmentA".
         *      Nên ta phải ánh xạ những phần cần tác động của FragmentA vào đây, để set up sự kiện cho btnC.
         *
         * - Nhập text vào edt trong FragmentC. Nhấn btnC của FragmentC, thì text sẽ chuyển qua TextView của FragmentA.
         *      nên trong này phải ánh xạ lại textViewFragmentA.
         * */
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Bình thường sẽ getContext(). Nhưng trong Fragment phải rõ ràng, nên getActivity().
                       - Khi đó nếu là Toast thì sẽ Toast lên trên mh Activity đang chứa fragment đó.
                       - Còn nếu Ánh Xạ thì cũng sẽ Ánh Xạ lên mh Activity đang chứa fragment đó.
                       - Còn nếu tác động lên 1 Fragment khác thuộc Activity đang chứa nó, thì cũng phải
                            lấy từ Activity đang chứa nó.
                     */
                TextView txtA = (TextView) getActivity().findViewById(R.id.textViewFragmentA);
                txtA.setText(edtC.getText().toString());

            }
        });

        return view;
    }

}

