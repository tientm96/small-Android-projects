package com.mtit.minhtien.asynctaskjsonarrayobject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvKhoaHoc;
    ArrayList<String> arrayCourse; //vì JSON chỉ chứa khóa học và học phí đều là string, nên chọn Sting
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvKhoaHoc = (ListView) findViewById(R.id.listviewKhoahoc);
        arrayCourse = new ArrayList<>();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayCourse);
        lvKhoaHoc.setAdapter(adapter);

        //cập nhật dl bằng cách gọi class
        new ReadJSON().execute("https://khoapham.vn/KhoaPhamTraining/json/tien/demo4.json");
    }


    private class ReadJSON extends AsyncTask<String, Void, String>{
        StringBuilder content = new StringBuilder(); //tạo chuỗi có thể dùng hàm để cộng dồn

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]); //lấy ra link đầu tiên trong danh sách các link
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream()); //lấy dl từ link
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  //đọc dl từ inputStream

                String line = "";
                while ((line = bufferedReader.readLine()) != null){ //nếu chưa lấy hết thì tiếp tục lấy và cộng dồn
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
        protected void onPostExecute(String s) {  //chuỗi s: là chuỗi trả về từ doInBackground
            super.onPostExecute(s);

            //lấy từng mục dl trong json đã được đọc về
            try {
                JSONArray array = new JSONArray(s);

                for(int i = 0; i < array.length(); i++){  //đọc từng mục {...,...} nằm trong [...]
                    JSONObject object = array.getJSONObject(i);
                    String khoahoc = object.getString("khoahoc"); //truyền vào đúng key của dữ liệu JSON
                    String hocphi = object.getString("hocphi"); //truyền vào đúng key của dữ liệu JSON

                    //đổ dl vào arraylist
                    arrayCourse.add(khoahoc + "-" + hocphi);
                }

                adapter.notifyDataSetChanged(); //cập nhật lại dl adapter

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
