package com.mtit.minhtien.androidwebservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Đọc bằng volley chỉ đọc trực tiếp với những link đã được public.
        //Còn những link localhost thì không thể đọc trực tiếp đc.
        //ReadJSON("http://localhost:8080/androidwebservice/demo.php");

        //Cách khắc phục: tìm địa chỉ ip của máy, thay thế cho localhost
        /*Win R -> gõ cmd enter: + gõ "ipconfig" để kiểm tra dùng mạng dây hay wifi
        * + Kéo xuống dòng wireless Lan Adapter Wi-fi: lấy địa chỉ ở dòng IPV4 Adress...
        * + Trên link web: thay localhost bằng địa chỉ ip vừa tìm được*/

        /*ĐỊA CHỈ IP CỦA MÁY THAY ĐỔI SAU MỖI LẦN BẬT TẮT MÁY, NHỚ KIỂM TRA UPDATE LẠI IP TRÊN NÀY*/
        ReadJSON("http://192.168.0.105:8080/androidwebservice/demo.php");
    }




    //HÀM TỰ VIẾT----------------------------
    //Dùng Volley để lấy JSon về
    private void ReadJSON(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() { //tham số thứ 4
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                    }
                }, //thêm vào dấu ,
                new Response.ErrorListener() { //tham số thứ 5
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

}
