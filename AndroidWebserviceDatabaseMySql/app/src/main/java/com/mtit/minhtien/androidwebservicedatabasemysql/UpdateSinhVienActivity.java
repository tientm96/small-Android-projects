package com.mtit.minhtien.androidwebservicedatabasemysql;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class UpdateSinhVienActivity extends AppCompatActivity {

    EditText edtHoTen, edtNamSinh, edtDiaChi;
    Button btnCapNhat, btnHuy;

    int id = 0;

    /*TH1: dùng localhost:8080, lấy php từ  PC, và chỉ dùng được cho 1 mình máy mình thôi.
    *     Sau mỗi lần kết nối lại mạng nhớ kiểm tra lại địa chỉ ip thay đổi. cmd->ipconfig
    *     */
//    String url = "http://192.168.0.105:8080/androidwebservice/update.php";


    /*TH2: đã up dữ liệu php lên server: https://www.000webhost.com/members/website/minhtien96/files
     *      Vì cả database và truy vấn file php đều đã đưa lên server, nên dùng được cho tất cả các máy.
     *      Sửa lại đường link, bằng link trên trang web của mình đã tạo trên hostinger*/
    String url = "http://minhtien96.000webhostapp.com/update.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sinh_vien);

        //Lấy intent từ thao tác sự kiện imgEdit bên SinhVienAdapter gửi qua.(Gửi qua nguyên object sinhvien)
        Intent intent = getIntent();
        SinhVien sinhVien = (SinhVien) intent.getSerializableExtra("dataSinhVien");


        //Ánh xạ, xử lý dữ liệu update
        Mapped();

        //set text để khi hiện ra ta thấy thông tin từng dòng mà update
        id = sinhVien.getId();
        edtHoTen.setText(sinhVien.getHoTen().toString());
        edtNamSinh.setText(sinhVien.getNamSinh() + "");     //vì namsinh là int
        edtDiaChi.setText(sinhVien.getDiaChi().toString());


        //set sự kiện 2 button
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //nhấn Cập Nhật thì sẽ đẩy dữ liệu lên, sử dụng volley để đẩy lên.
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hoten = edtHoTen.getText().toString().trim();
                String namsinh = edtNamSinh.getText().toString().trim();
                String diachi = edtDiaChi.getText().toString().trim();

                //kiểm tra 1 trong 3 cái rỗng thì không cho POST qua php.
                //Nhiều hàm ktra rỗng, dùng cái nào cũng đc
                //if(hoten.isEmpty() || namsinh.isEmpty() || diachi.isEmpty())
                if(hoten.matches("") || namsinh.equals("") || diachi.length() == 0){
                    Toast.makeText(UpdateSinhVienActivity.this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                }else{
                    CapNhapSinhVien(url);
                }

            }
        });

    }


    //------------------------------FUNTION TỰ TẠO

    //hàm sử dụng để POST từ .java sang php
    /* - Nhấn Update thì sẽ đẩy dữ liệu lên, sử dụng volley để đẩy lên. Đã có volley rồi,
    *    ta chỉ cần nhúng vào thôi, không khai báo lại thư viện.
    *  - POST dl lên để cho file php nhận vào, rồi php nó sẽ đưa lên service.
    *  - Khi POST ta chỉ POST String lên thôi, php sẽ tự động chuyển về kdl cho từng biến của nó.*/
    private void CapNhapSinhVien(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //thay vì Request.Method.GET thì ta .POST để đẩy qua file PHP
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    //sau khi POST lên, nó sẽ nhận kquả từ php trả về (còn php nhận kq từ server)
                    //trên php, nếu update thành công sẽ trả về succes, thì response sẽ nhận đc "succes".
                    //nếu php update thất bại sẽ trả về "error", nên response = "error".

                    public void onResponse(String response) {
                        if(response.trim().equals("success")){
                            Toast.makeText(UpdateSinhVienActivity.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();

                            //cập nhật xong thì quay lại màn hình chính
                            startActivity(new Intent(UpdateSinhVienActivity.this, MainActivity.class));

                        }else{ //thất bại: php trả về "error"
                            Toast.makeText(UpdateSinhVienActivity.this, "Lỗi cập nhật!", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() { //nhận lỗi trả về từ server
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UpdateSinhVienActivity.this, "Xẩy ra lỗi!", Toast.LENGTH_SHORT).show();

                        //hiện ra lỗi trên Logcat cho người lập trình xem
                        Log.d("AAAA", "Lỗi!\n" + error.toString());
                    }
                }

        ){ //để đẩy POST dữ liệu lên file php ta dùng hàm getParams(), bằng cách mở hàm {} ở giữa dấu )--;
            // Map<String, String>: key trùng với key nhận của php, dl đẩy lên.
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                //Khi POST ta chỉ POST String lên thôi, php sẽ tự động chuyển về kdl cho từng biến của nó.
                /* - Để put lên và php nhận được thì key "hotenSV" của 2 bên phải trùng nhau.
                 * - Trong php: 'hotenSV' đặt đúng theo KEY trong thao tác POST của android (post từ android lên php),
                 *   để đẩy dl từ .java lên file php.
                 * */

                //ép kiểu id từ int sang String, vì POST lên là luôn POST trên String. Lên php sẽ tự đổi kiểu dl
                params.put("idSV", String.valueOf(id));

                params.put("hotenSV", edtHoTen.getText().toString().trim());
                params.put("namsinhSV", edtNamSinh.getText().toString().trim());
                params.put("diachiSV", edtDiaChi.getText().toString().trim());

                return params;
            }
        };

        requestQueue.add(stringRequest);
    }



    private void Mapped() {
        btnCapNhat = (Button) findViewById(R.id.buttonCapNhatEdit);
        btnHuy = (Button) findViewById(R.id.buttonHuyEdit);

        edtDiaChi = (EditText) findViewById(R.id.editTextDiaChiEdit);
        edtNamSinh = (EditText) findViewById(R.id.editTextNamSinhEdit);
        edtHoTen = (EditText) findViewById(R.id.editTextHoTenEdit);
    }
}
