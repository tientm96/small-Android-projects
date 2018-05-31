package com.mtit.minhtien.ungdungdemngay;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button btnTinh;
    EditText edtNgay1, edtNgay2;
    TextView txtKetqua;

    Calendar calendarOne, calendarTwo;
    SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        //Xét sk
        edtNgay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonNgay1();
            }
        });

        edtNgay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonNgay2();
            }
        });

        btnTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //trả về mili giây, nên ta phải chuyển về lại ngày
                int khoangcachngay = (int) ((calendarTwo.getTimeInMillis() - calendarOne.getTimeInMillis())
                                                        / (1000 * 60 * 60 * 24));

                if(khoangcachngay < 0){
                    Toast.makeText(MainActivity.this, "Ngày 2 phải sau ngày 1", Toast.LENGTH_SHORT).show();
                }else{
                    txtKetqua.setText("Số ngày xa nhau là: " + khoangcachngay);
                }
            }
        });

    }

    private void ChonNgay1(){
        calendarOne = Calendar.getInstance();
        int ngay = calendarOne.get(Calendar.DATE);
        int thang = calendarOne.get(Calendar.MONTH);
        int nam = calendarOne.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendarOne.set(year, month, dayOfMonth);//gán cái ngày vừa kích chọn vào calendar
                                                        //để tí sẽ hiển thị lên ngày vừa chọn.
                edtNgay1.setText(simpleDateFormat.format(calendarOne.getTime()));
            }
        }, nam, thang, ngay); //năm tháng ngày

        datePickerDialog.show();
    }

    private void ChonNgay2(){
        calendarTwo = Calendar.getInstance();
        int ngay = calendarTwo.get(Calendar.DATE);
        int thang = calendarTwo.get(Calendar.MONTH);
        int nam = calendarTwo.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendarTwo.set(year, month, dayOfMonth);//gán cái ngày vừa kích chọn vào calendar
                                                            //để tí sẽ hiển thị lên ngày vừa chọn.
                edtNgay2.setText(simpleDateFormat.format(calendarTwo.getTime()));
            }
        }, nam, thang, ngay);

        datePickerDialog.show();
    }


    private void AnhXa(){
        btnTinh = (Button) findViewById(R.id.buttonTinh);
        edtNgay1 = (EditText) findViewById(R.id.editTextDateOne);
        edtNgay2 = (EditText) findViewById(R.id.editTextDateTwo);
        txtKetqua = (TextView) findViewById(R.id.textViewResult);
    }
}
