package com.mtit.minhtien.volleystringrequest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue requestQueue = Volley.newRequestQueue(this); //kiểu chung

        String ulr = "http://online.khoapham.vn/";


        //kiểu StringRequest cụ thể
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ulr,

                //tham số thứ 3: lắng nghe sự kiện, đọc được những gì
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                },

                //tham số thứ 4: lắng nghe lỗi
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Lỗi rồi. Vào logcat mà xem.", Toast.LENGTH_SHORT).show();
                        Log.d("AAA", "Lỗi\n" + error.toString());
                    }
                });
                //add string lấy đc vào Request
                requestQueue.add(stringRequest);
    }
}
