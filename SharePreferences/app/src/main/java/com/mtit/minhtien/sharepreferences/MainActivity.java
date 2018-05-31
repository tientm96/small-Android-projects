package com.mtit.minhtien.sharepreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnConfirm;
    EditText edtUser, edtPass;
    CheckBox cbRemember;

    SharedPreferences sharedPreferences; //khai báo sha... để lưu dl



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ánh xạ xong rồi mới gán giá trị
        Mapped();



        //khởi tạo shared..., lưu giá trị vào nó, lấy giá trị ra từ nó
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);

        //lấy giá trị từ shared...đã lưu, đổ ra màn hình
        edtUser.setText(sharedPreferences.getString("taikhoan", "")); //nếu ko có thì để rỗng default
        edtPass.setText(sharedPreferences.getString("matkhau", ""));
        cbRemember.setChecked(sharedPreferences.getBoolean("checked", false)); //nếu ko có thì chọn false


        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUser.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();

                if(user.equals("minhtien") && pass.equals("1234")){
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();

                    //nếu đăng nhập thành công, mà người dùng có check vào checkbox y/c lưu thì mới lưu
                    if(cbRemember.isChecked()){
                        SharedPreferences.Editor editor = sharedPreferences.edit(); //gọi = shared.. ở trên
                        editor.putString("taikhoan", user);
                        editor.putString("matkhau", pass);

                        //nếu lần sau up tài khoản mới, nhưng người dùng lại không muốn lưu, lúc đó tài khoản cũ
                        //cứ đc lưu mãi, mỗi lần login mất tgian xóa r nhập lại => phải lưu luôn trạng thái của check
                        editor.putBoolean("checked", true); //nếu có checked chọn lưu thì để là true.

                        editor.commit();
                    }else{
                       SharedPreferences.Editor editor = sharedPreferences.edit();
                       editor.remove("taikhoan"); //name phải trùng với name khi put vào ở trên
                       editor.remove("matkhau");
                       editor.remove("checked");

                       editor.commit();
                    }

                }else{
                    Toast.makeText(MainActivity.this, "Lỗi đăng nhập!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    //--------------------------------------------------------
    private void Mapped(){
        btnConfirm = (Button) findViewById(R.id.buttonXacNhan);
        edtUser = (EditText) findViewById(R.id.editTextUser);
        edtPass = (EditText) findViewById(R.id.editTextPass);
        cbRemember = (CheckBox) findViewById(R.id.checkBoxRemember);
    }
}
