package com.example.mtit.newfirebaseauthentication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button btnDangKy;
    private EditText edtEmail, edtPass;

    private Button btnDangNhap;
    private EditText edtEmailDangNhap, edtPassDangNhap;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        AnhXa();

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DangKy();
            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DangNhap();
            }
        });


    }


    //Enabled đăng ký bằng Email/pass, truy cập docs copy code vào hàm DangKy.
    //Code trong hàm copy từ: https://firebase.google.com/docs/auth/android/password-auth?authuser=1
    private void DangKy(){
        String email = edtEmail.getText().toString(); //tự sửa
        String password = edtPass.getText().toString(); //tự sửa

        //ĐĂNG KÝ USER, ĐỘ DÀI PASS >= 6 KÝ TỰ, USER ĐÚNG FORMAT EMAIL
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Đã custom code lại sau khi paste vào
                            Toast.makeText(MainActivity.this, "CreateUserWithEmail:success", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }


    //kéo docs xuống phần sign, có hướng dẫn, copy code về, cùng 1 link
    private void DangNhap(){
        String email = edtEmailDangNhap.getText().toString(); //tự sửa
        String password = edtPassDangNhap.getText().toString(); //tự sửa

        //copy code về, cùng 1 link docs ở trên. Có custom code lại
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "SignInWithEmail:success", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "SignInWithEmail:failure", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }



    private void AnhXa(){
        btnDangKy = (Button) findViewById(R.id.buttonDangKy);
        edtEmail = (EditText) findViewById(R.id.editTextEmail);
        edtPass = (EditText) findViewById(R.id.editTextPass);

        btnDangNhap = (Button) findViewById(R.id.buttonDangNhap);
        edtEmailDangNhap = (EditText) findViewById(R.id.editTextEmailDangNhap);
        edtPassDangNhap = (EditText) findViewById(R.id.editTextPassDangNhap);
    }

}
