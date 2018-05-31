package com.mtit.minhtien.dailongcustom;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txtLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLogin = (TextView) findViewById(R.id.textviewLogin);

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogLogin();
            }
        });
    }

    private void DialogLogin(){
        final Dialog dialog = new Dialog(this); //tính chất của dialog giống như Toast

        //dialog.setTitle("Thông báo"); //nếu muốn hiện title, TÙY MÁY CÓ HAY KHÔNG

        //set lại title cho dialog, ở trong khuôn dialog luôn.
        //một số máy tự động bỏ, số còn lại vẫn hiện title, ko đẹp lắm, nên set lại.
        //DÒNG NÀY LUÔN Ở TRÊN DÒNG "setContentView"
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //cũng như trong onCreate, ta dùng setContentView để set 1 xml ra màn hình
        dialog.setContentView(R.layout.dialog_custom);

        //false: xử lý xong trong dialog rồi mới được bấm ra bên ngoài
        dialog.setCanceledOnTouchOutside(false);

        //Ánh xạ
        final EditText edtUsername = (EditText) dialog.findViewById(R.id.editTextUsername);
        final EditText edtPassword = (EditText) dialog.findViewById(R.id.editTextPassword);
        Button btnDongY = (Button) dialog.findViewById(R.id.buttonDongY);
        Button btnHuy = (Button) dialog.findViewById(R.id.buttonHuy);

        //Bắt sự kiện
        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                if(username.equals("tien") && password.equals("123")){//giả lập thôi
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Lỗi đăng nhập", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dialog.cancel(); //Tắt hộp thoại đi
                dialog.dismiss(); //Là tắt cái gì đó. 2 cách, dùng cách nào cũng đc.
            }
        });



        dialog.show();
    }
}
