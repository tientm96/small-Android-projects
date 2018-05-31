package com.mtit.minhtien.asynctaskreadcontentwebsiteinternet;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new ReadContentWebsite().execute("https://khoapham.vn");

    }




    //String đầu tiên: quy định kiểu nhận vào của doInBackgroup, ở đây nhận vào đường link truyền đến web, nên phải là chuỗi
    //Void thứ 2: quy định kiểu nhận vào của hàm publishProgress, nhận dl chạy song song với doInBackground, ở đây là Void: không nhận gì cả
    //String thứ 3: quy định kiểu trả về trong doInBackground; hay kiểu nhận vào của onPostExcute cũng sẽ tương ứng theo. ở đây là nội dung web nên là String
    private class ReadContentWebsite extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {

            //chứa dữ liệu đọc được
            StringBuilder stringBuilder = new StringBuilder();

            try {
                URL url = new URL(strings[0]); //truyền vào phần tử thứ 0 của dãy các phần truyền vào
                URLConnection urlConnection = url.openConnection(); //mở kết nối

                InputStream inputStream = urlConnection.getInputStream(); //lấy dữ liệu từ kết nối (link) đã mở
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream); //đọc dl từ inputStream ra


                BufferedReader bufferedReader = new BufferedReader(inputStreamReader); //đọc dl liên tục từ inputStreamReader để đưa ra màn hình
                String line = "";
                while((line = bufferedReader.readLine()) != null){  //đọc tới dòng cuối thì = null thì dừng
                    stringBuilder.append(line + "\n"); //append: giữ 1 text lại
                }
                bufferedReader.close(); //đóng bufer.. lại

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) { //s: là chuỗi trả về từ doInBack...
            super.onPostExecute(s);

            Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
        }
    }
}
