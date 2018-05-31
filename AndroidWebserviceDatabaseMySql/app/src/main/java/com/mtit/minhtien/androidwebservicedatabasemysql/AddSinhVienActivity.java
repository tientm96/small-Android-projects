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

public class AddSinhVienActivity extends AppCompatActivity {

    EditText edtHoTen, edtNamSinh, edtDiaChi;
    Button btnThem, btnHuy;

    /*TH1: dùng localhost:8080, lấy php từ  PC, và chỉ dùng được cho 1 mình máy mình thôi.
    *     Sau mỗi lần kết nối lại mạng nhớ kiểm tra lại địa chỉ ip thay đổi. cmd->ipconfig
    *     */
//    String urlInsert = "http://192.168.0.105:8080/androidwebservice/insert.php";


    /*TH2: đã up dữ liệu php lên server: https://www.000webhost.com/members/website/minhtien96/files
     *      Vì cả database và truy vấn file php đều đã đưa lên server, nên dùng được cho tất cả các máy.
     *      Sửa lại đường link, bằng link trên trang web của mình đã tạo trên hostinger*/
    String urlInsert = "http://minhtien96.000webhostapp.com/insert.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sinh_vien);

        Mapped();

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //nhấn Thêm thì sẽ đẩy dữ liệu lên, sử dụng volley để đẩy lên.
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hoten = edtHoTen.getText().toString().trim();
                String namsinh = edtNamSinh.getText().toString().trim();
                String diachi = edtDiaChi.getText().toString().trim();

                //kiểm tra 1 trong 3 cái rỗng thì không cho POST qua php
                if(hoten.isEmpty() || namsinh.isEmpty() || diachi.isEmpty()){
                    Toast.makeText(AddSinhVienActivity.this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                }else{
                    ThemSinhVien(urlInsert);
                }

            }
        });
    }


    //----------------------FUNTION TỰ TẠO

    /* - Nhấn Thêm thì sẽ đẩy dữ liệu lên, sử dụng volley để đẩy lên. Đã có volley rồi,
    *    ta chỉ cần nhúng vào thôi, không khai báo lại thư viện
    *  - POST dl lên để cho file php nhận vào, rồi php nó sẽ đưa lên service.
    *  - Khi POST ta chỉ POST String lên thôi, php sẽ tự động chuyển về kdl cho từng biến của nó.*/
    private void ThemSinhVien(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //thay vì Request.Method.GET thì ta .POST để đẩy qua file PHP
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override               //sau khi POST lên, nó sẽ nhận kquả từ php trả về (php nhận kq từ server)
                    public void onResponse(String response) {
                        //trên php, nếu insert thành công sẽ trả về succes, thì response sẽ nhận đc "succes"
                        if(response.trim().equals("success")){
                            Toast.makeText(AddSinhVienActivity.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();

                            //xong thì quay lại màn hình chính
                            startActivity(new Intent(AddSinhVienActivity.this, MainActivity.class));

                        }else{//response = error trả về từ php
                            Toast.makeText(AddSinhVienActivity.this, "Lỗi thêm!", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() { //nhận lỗi trả về từ server
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddSinhVienActivity.this, "Xẩy ra lỗi!", Toast.LENGTH_SHORT).show();

                        //hiện ra lỗi trên Logcat cho người lập trình xem
                        Log.d("AAAA", "Lỗi!\n" + error.toString());
                    }
                }

        ){ //để đẩy POST dữ liệu lên file php ta dùng hàm getParams(), bằng cách mở hàm {} ở giữa dấu )--;
           // Map<String, String>: key trùng với key nhận của php, dl đẩy lên
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                //Khi POST ta chỉ POST String lên thôi, php sẽ tự động chuyển về kdl cho từng biến của nó.
                /* - Để put lên và php nhận được thì key "hotenSV" của 2 bên phải trùng nhau.
                 * - Trong php: 'hotenSV' đặt đúng theo KEY trong thao tác POST của android (post từ android lên php),
                 *   để đẩy dl từ .java lên file php.
                 * */
                params.put("hotenSV", edtHoTen.getText().toString().trim());
                params.put("namsinhSV", edtNamSinh.getText().toString().trim());
                params.put("diachiSV", edtDiaChi.getText().toString().trim());




                return params;
            }
        };

        requestQueue.add(stringRequest);
    }



    private void Mapped() {
        btnThem = (Button) findViewById(R.id.buttonThemAddSv);
        btnHuy = (Button) findViewById(R.id.buttonHuyAddSV);
        edtDiaChi = (EditText) findViewById(R.id.editTextDiaChiAddSV);
        edtHoTen = (EditText) findViewById(R.id.editTextHoTenAddSV);
        edtNamSinh = (EditText) findViewById(R.id.editTextNamSinhAddSV);
    }
}
