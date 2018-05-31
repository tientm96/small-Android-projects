package com.mtit.minhtien.fragmentdialogfragmentinterface;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements DeleteData{
    Button btnXoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnXoa = (Button) findViewById(R.id.buttonXoa);

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentDialog fragmentDialog = new FragmentDialog(); //là lớp vừa tạo
                fragmentDialog.show(getFragmentManager(), "dialog"); //tag: "dialog" đặt tùy ý

            }
        });

    }

    /*Làm thế nào để activity biết ta chọn "có/không" để xử lý.
    * -Dùng bundle: dùng để truyền dl và load lên thôi. Nhưng ở đây đang là trực tiếp ở activity...
    * -Dùng Interface: ta có thể nhận giá trị trực tiếp tại activity, không cần hiển thị qua FragmentDialog
    *       +Tạo class Interface như tạo class bt, nhưng ở mục kind chọn "interface"*/


    //PT implements từ interface
    @Override
    public void GiaTriXoa(boolean delete) {
        //khi ta chọn xóa/không thì FragmentDialog đã gửi true/false về DeleteData Interface rồi
        if (delete){
            Toast.makeText(this, "Chọn xóa!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Không xóa!", Toast.LENGTH_SHORT).show();
        }
    }


}