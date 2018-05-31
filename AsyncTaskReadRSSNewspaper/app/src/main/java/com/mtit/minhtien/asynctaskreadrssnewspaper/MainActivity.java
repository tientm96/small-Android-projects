package com.mtit.minhtien.asynctaskreadrssnewspaper;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvTieuDe;
    ArrayList<String> arrayTitle, arrayLink;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvTieuDe = (ListView) findViewById(R.id.listViewTieuDe);
        arrayTitle = new ArrayList<>();
        arrayLink = new ArrayList<>();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayTitle);
        lvTieuDe.setAdapter(adapter);



        new ReadRSS().execute("https://vnexpress.net/rss/so-hoa.rss");

        //khi kích vô mỗi mục, sẽ chuyển sang nội dung báo của mục đó
        lvTieuDe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                intent.putExtra("linkTinTuc", arrayLink.get(position));
                startActivity(intent);
            }
        });


    }




    //String đầu tiên: quy định kiểu nhận vào của doInBackgroup, ở đây nhận vào đường link truyền đến web, nên phải là chuỗi
    //Void thứ 2: quy định kiểu nhận vào của hàm publishProgress, nhận dl chạy song song với doInBackground, ở đây là Void: không nhận gì cả
    //String thứ 3: quy định kiểu trả về trong doInBackground; hay kiểu nhận vào của onPostExcute cũng sẽ tương ứng theo. ở đây là nội dung web nên là String
    private class ReadRSS extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();    //content chứa nội dung

            try {
                //khởi tạo url nhận đường dẫn
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line = "";
                while ((line = bufferedReader.readLine()) != null){ //chưa phải null thì thêm vào
                    content.append(line);//đưa thêm text vào
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
        protected void onPostExecute(String s) { //chuỗi s: nhận giá trị trả về từ doInBackground. là tập html của trang web
            super.onPostExecute(s);

            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);


            //khai báo nodelist để chứa danh sách các item, mỗi mục báo trên xml của RSS là 1 item
            NodeList nodeList = document.getElementsByTagName("item"); //item: tên mỗi item của mỗi mục báo

            //vòng lặp lấy ra từng item và thông tin cơ bản của từng item
            String tieude = "", duongdan = "";
            for(int i = 0; i < nodeList.getLength(); i++){
                Element element = (Element) nodeList.item(i);   //Chọn của bộ thư viện: org.w3c.com
                tieude = parser.getValue(element, "title"); //lấy ra <title>
                duongdan = parser.getValue(element, "link"); //lấy ra <link> trong mỗi mục <item> trong RSS

                arrayTitle.add(tieude); //add mỗi mục vào listview, ở đây chỉ lấy 2 mục tiêu biểu
                arrayLink.add(duongdan);

            }

            adapter.notifyDataSetChanged(); //cập nhật lại dữ liệu đã thay đổi

            //Toast.makeText(MainActivity.this, tieude, Toast.LENGTH_LONG).show(); //để cho hiện lên cho biết thôi
        }
    }

}
