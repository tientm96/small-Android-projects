package com.mtit.minhtien.volleyjsonobjectrequest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        String url = "https://khoapham.vn/KhoaPhamTraining/json/tien/demo1.json";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,

                //tham số thứ 4: chạy chính, lấy kq và trả về nguyên 1 JSON Object
                new Response.Listener<JSONObject>() {       //dl lấy về kiểu JSONObject
                    @Override
                    public void onResponse(JSONObject response) { //response: đang là json nên tostring cho về chuỗi
                        try {
                            String monhoc = response.getString("monhoc"); //mã key trong JSON Ob
                            String noihoc = response.getString("noihoc");

                            Toast.makeText(MainActivity.this, monhoc + "\n" + noihoc, Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                //tham số thứ 5: nhận lỗi
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Lỗi!", Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);

    }
}
