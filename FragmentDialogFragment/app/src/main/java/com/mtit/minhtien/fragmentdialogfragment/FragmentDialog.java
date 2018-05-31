package com.mtit.minhtien.fragmentdialogfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by MT IT on 11/29/2017.
 */

//Gọi tới fragment sẽ hiện lên dialog, nên kiểu trả về của nó là dilog, chứ  ko phải View
//Vì chỉ gọi ra dialog, nên ta không cần phải tạo xml cho fragment. (Thích thì custom layout dạng dialog)
public class FragmentDialog extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Xác Nhận");
        dialog.setMessage("Bạn có muốn xóa?");

        dialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Xóa", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //không cần làm
            }
        });
//        dialog.show(); //không show, để qua bên main sẽ show



        /*Vì hàm trả về dialog, mà ta đang làm việc với Buider, nên nếu trả về trực tiếp là Buider dialog sẽ lỗi.
        * Vậy nên đưa về kiểu thuần Dialog bằng cách: */
        Dialog dialogThuan = dialog.create();


        return dialogThuan; //Ở đây phải ép về kiểu Dialog thuần
    }
}
