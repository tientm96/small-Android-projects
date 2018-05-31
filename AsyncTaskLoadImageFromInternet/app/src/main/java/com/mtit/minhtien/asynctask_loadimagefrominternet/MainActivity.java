package com.mtit.minhtien.asynctask_loadimagefrominternet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button btnLoad;
    ImageView imgHinh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLoad = (Button) findViewById(R.id.buttonLoad);
        imgHinh = (ImageView) findViewById(R.id.imageViewHinh);


        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoadImageInteret().execute("https://www.androidcentral.com/sites/androidcentral.com/files/styles/large/public/topic_images/2015/android-apps-topic.png?itok=gRZTqteM&timestamp=1444304563");
                                                    //gọi execute để thực thi class
                                                    //truyền vào đường dẫn hình ảnh. Google tìm ảnh, kích vào nút "Xem Hình Ảnh", hông bên phải mới ra link ảnh.
            }
        });

    }




    //String đầu tiên: quy định kiểu nhận vào của doInBackgroup, ở đây nhận vào đường link truyền đến hình ảnh, nên phải là chuỗi
    //Void thứ 2: quy định kiểu nhận vào của hàm publishProgress, nhận dl chạy song song với doInBackground, ở đây là Void: không nhận gì cả
    //Bitmap thứ 3: quy định kiểu trả về trong doInBackground; hay kiểu nhận vào của onPostExcute cũng sẽ tương ứng theo. ở đây là ảnh nên là Bitmap
    private class LoadImageInteret extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap = null;

            try {
                URL url = new URL(strings[0]); //chỉ cần đọc 1 đường dẫn, nên đọc đường dẫn đầu tiên của dãy(nếu có dãy chứa nhiều link), nên ta chọn [0]
                InputStream inputStream = url.openConnection().getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return bitmap;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            imgHinh.setImageBitmap(bitmap);
        }
    }
}
