package com.mtit.minhtien.asynctaskjsonobject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

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

        //đọc trang web có chứa nội dung là JSON Object
        new ReadJSONObject().execute("https://khoapham.vn/KhoaPhamTraining/json/tien/demo1.json");
    }


    //đọc nội dung từ website để phân tích là xml hay là json
    private class ReadJSONObject extends AsyncTask<String, Void, String>{

        StringBuilder content = new StringBuilder(); //biến chứa dữ liệu nội dung đọc về

        @Override
        protected String doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]); //nhận url truyền vào
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream()); //lấy dữ liệu từ url
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader); //đọc dl từ inputStreamReader

                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    content.append(line); //đọc dồn từng dòng vào content
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
        protected void onPostExecute(String s) { // s nhận chuỗi trả về từ doInBack...
            super.onPostExecute(s);

            //vì trang web có nd là JSON Object, nên ta dùng JSON Ob để phân tích nội dung đọc được từ doInBack...
            try {
                JSONObject jsonObject = new JSONObject(s); //đưa nội dung s vào

                String monhoc = jsonObject.getString("monhoc"); //viết đúng định dạng của các thẻ trong JSON
                String noihoc = jsonObject.getString("noihoc");
                String website = jsonObject.getString("website");

                Toast.makeText(MainActivity.this, monhoc + "\n" + noihoc + "\n" + website, Toast.LENGTH_LONG).show();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
