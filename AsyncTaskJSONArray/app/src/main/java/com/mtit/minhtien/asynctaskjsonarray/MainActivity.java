package com.mtit.minhtien.asynctaskjsonarray;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new ReadJSON().execute("https://khoapham.vn/KhoaPhamTraining/json/tien/demo2.json");
    }



    private class ReadJSON extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder(); //chuỗi có thể nối liên tiếp bằng hàm append
            try {
                URL url = new URL(strings[0]); //lấy ra ulr đầu tiên trong dãy url nếu có
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream()); //lấy dl từ link
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader); //đọc dl từ inpu....

                String line = "";
                while ((line = bufferedReader.readLine()) != null){ //nếu chưa đọc hết
                    content.append(line);
                }
                bufferedReader.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return content.toString();
        }


        @Override
        protected void onPostExecute(String s) { //chuỗi s: nhận kết quả trả về từ doInBackground
            super.onPostExecute(s);

            //Vì ta để JSON Array [], nằm trong JSON Object {}, nên khai báo theo JSON Object bọc lấy JSON Array
            try {
                JSONObject jsonObject = new JSONObject(s); //truyền vào chuỗi kết quả s
                JSONArray jsonArray = jsonObject.getJSONArray("danhsach"); //tên id của JSON Array, xem trang link

                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObjectKH = jsonArray.getJSONObject(i);//đọc mỗi JSON Object nằm trong JSON Array
                    String ten = jsonObjectKH.getString("khoahoc"); //chuỗi id của mỗi JSONObject{} nằm trong []

                    Toast.makeText(MainActivity.this, ten, Toast.LENGTH_SHORT).show();
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}