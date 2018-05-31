package com.mtit.minhtien.apptoancau;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtThongTin;
    Button btnXacNhan;
    EditText edtHoten, edtSoDT, edtEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoten = edtHoten.getText().toString();
                String email = edtEmail.getText().toString();
                String sodt = edtSoDT.getText().toString();

                //chỉ là text bình thường nên KH là text chứ ko là txt
                String textChaoBan = getResources().getString(R.string.text_ChaoBan);
                String textEmail = getResources().getString(R.string.text_Email);
                String textSoDT = getResources().getString(R.string.text_SoDT);


                txtThongTin.setText(textChaoBan + ": " + hoten + "\n" + textEmail + ": " + email
                        + "\n" + textSoDT + ": " + sodt);
            }
        });

    }

    private void AnhXa(){
        btnXacNhan = (Button) findViewById(R.id.buttonXacNhan);
        txtThongTin = (TextView) findViewById(R.id.textViewThongTin);
        edtEmail = (EditText) findViewById(R.id.editTextEmail);
        edtHoten = (EditText) findViewById(R.id.editTextHoTen);
        edtSoDT = (EditText) findViewById(R.id.editTextSoDienThoai);
    }
}
