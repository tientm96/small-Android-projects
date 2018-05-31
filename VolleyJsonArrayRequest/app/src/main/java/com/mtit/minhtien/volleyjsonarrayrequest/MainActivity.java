package com.mtit.minhtien.volleyjsonarrayrequest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String ulr = "https://khoapham.vn/KhoaPhamTraining/json/tien/demo4.json";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, ulr, null,
                //tham số thứ 4: lấy về nguyên JSONArray từ web
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) { // trả về response là nguyên 1 JSONArray

                        //JSONArray có chứa nhiều JSONObject trong đó nên ta dùng for
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject object = response.getJSONObject(i);

                                String khoahoc = object.getString("khoahoc"); //key lấy từ JSON trên web
                                String hocphi = object.getString("hocphi");

                                Toast.makeText(MainActivity.this, khoahoc + "-" + hocphi, Toast.LENGTH_SHORT).show();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }


                    }
                },

                //tham số 5: xét lỗi
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Lỗi!", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);

    }
}
