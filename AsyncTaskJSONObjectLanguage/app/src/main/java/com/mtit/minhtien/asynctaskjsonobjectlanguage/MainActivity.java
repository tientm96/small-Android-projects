package com.mtit.minhtien.asynctaskjsonobjectlanguage;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageButton ibtnVN, ibtnUS;
    TextView txtInfo;

    String noiDung = ""; //dùng để lấy nội dung đọc từ web về từ doInBackground, hay chính là biến s trong onPostExecute

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Mapped();

        new ReadJSON().execute("https://khoapham.vn/KhoaPhamTraining/json/tien/demo3.json");


        //mới đầu trong onPostExcute ta để mặc định là "vn", nên lần đầu tiên vừa mới gọi AsyncTask thì nó dùng "vn".
        //sau khi click event, set lại thì textview nó sẽ đổi sau
        ibtnVN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadJSONLanguageOfonPostExecute("vn");
            }
        });

        ibtnUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadJSONLanguageOfonPostExecute("en");
            }
        });



    }

    private class ReadJSON extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder(); //tạo chuỗi có thể dùng hàm append để cộng dồn

            try {
                URL url = new URL(strings[0]); //lấy link đầu tiên trong dãy link
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line = "";
                while ((line = bufferedReader.readLine()) != null){ //nếu chưa hết thì vẫn cộng dồn tiếp
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
        protected void onPostExecute(String s) { //chuỗi s: nhận giá trị trả về từ doInBackground
            super.onPostExecute(s);

            noiDung = s;

            //gọi hàm, lấy ra từng country
            ReadJSONLanguageOfonPostExecute("vn"); //mới đầu trong onPostExcute ta để mặc định là "vn",
                                                    // nên lần đầu tiên vừa mới gọi AsyncTask thì nó dùng "vn".
                                                    //Sau khi click event, set lại thì textview sẽ đổi sau
        }
    }


    private void ReadJSONLanguageOfonPostExecute(String lang){ //tham số truyền vào đọc "vn" hay "en"
        try {
            JSONObject object = new JSONObject(noiDung); // đưa nội dung JSON đã lấy được từ doInBackground return về, hay
                                                    //chính là biến s trong onPostExecute.
                                                    //Ở đây hàm ngoài, nên ta linh động...

            JSONObject objectLanguage = object.getJSONObject("language"); //mã id lấy từ file JSON tương ứng

            JSONObject objectCountry = objectLanguage.getJSONObject(lang); //chính là "vn" hoặc "en", ta truyền vào khi gọi hàm

            String ten = objectCountry.getString("name"); //các string ip lấy từ link web chứ JSON, phải 100% đúng
            String diachi = objectCountry.getString("address");
            String khoahoc1 = objectCountry.getString("course1");
            String khoahoc2 = objectCountry.getString("course2");
            String khoahoc3 = objectCountry.getString("course3");

            txtInfo.setText(ten + "\n" + diachi + "\n" + khoahoc1 + "\n" + khoahoc2 + "\n" + khoahoc3 + "\n");



        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void Mapped(){
        ibtnVN = (ImageButton) findViewById(R.id.imageButtonVn);
        ibtnUS = (ImageButton) findViewById(R.id.imageButtonUs);
        txtInfo = (TextView) findViewById(R.id.textViewInfo);
    }
}
