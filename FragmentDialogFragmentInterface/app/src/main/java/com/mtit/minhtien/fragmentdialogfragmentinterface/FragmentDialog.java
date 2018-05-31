package com.mtit.minhtien.fragmentdialogfragmentinterface;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by MT IT on 11/29/2017.
 */

//Gọi tới fragment sẽ hiện lên dialog, nên kiểu trả về của nó là dilog, chứ  ko phải View
//Vì chỉ gọi ra dialog, nên ta không cần phải tạo xml cho fragment. (Thích thì custom layout dạng dialog)
public class FragmentDialog extends DialogFragment{

    DeleteData deleteData; //khai báo interface

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //interface
        deleteData = (DeleteData) getActivity(); //sẽ cho biết nó đc gọi ở activity nào

        //Dialog
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Xác Nhận");
        dialog.setMessage("Bạn có muốn xóa?");

        dialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getActivity(), "Xóa", Toast.LENGTH_SHORT).show();

                //Ở đây ta ko Toast nữa, mà sẽ gửi dữ liệu về interface
                deleteData.GiaTriXoa(true);
            }
        });
        dialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Ở đây ta ko Toast nữa, mà sẽ gửi dữ liệu về interface
                deleteData.GiaTriXoa(false);
            }
        });
//        dialog.show(); //không show, để qua bên main sẽ show



        /*Vì hàm trả về dialog, mà ta đang làm việc với Buider, nên nếu trả về trực tiếp là Buider dialog sẽ lỗi.
        * Vậy nên đưa về kiểu thuần Dialog bằng cách: */
        Dialog dialogThuan = dialog.create();


        return dialogThuan; //Ở đây phải ép về kiểu Dialog thuần
    }
}
