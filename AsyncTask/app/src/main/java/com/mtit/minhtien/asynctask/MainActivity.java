package com.mtit.minhtien.asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnXuly;
    TextView txtThongtin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnXuly = (Button) findViewById(R.id.buttonXuly);
        txtThongtin = (TextView) findViewById(R.id.textViewThongtin);

        btnXuly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CongViec().execute(); //gọi class CongViec thực thi bằng cách gọi đến execute

            }
        });
    }

    //Void đầu tiên: quy định kiểu nhận vào của doInBackground, ở đây không nhận vào gì cả, đặc biệt ở đây viết In chữ V
    //String thứ 2: quy định kiểu nhận vào của hàm publishProgress, nhận dl chạy song song với doInBackground
    //String thứ 3: quy định kiểu trả về trong doInBackground; hay kiểu nhận vào của onPostExcute cũng sẽ tương ứng theo
    private class CongViec extends AsyncTask<Void, String, String>{
        @Override
        protected void onPreExecute() { //bắt đầu chuẩn bị thực hiện
            super.onPreExecute();

            txtThongtin.setText("Bắt đầu." + "\n");
        }

        @Override
        protected String doInBackground(Void... voids) { //xử lý chính, nhưng ko thay đổi giao diện đc

            for(int i = 1; i <= 5; i++){  //doIn.. không thể in ra giao diện, nên truyền qua publishProgress để đưa xuống onProgressUpdate

                //Gọi tiến trình cho ngủ 2 giây, để xem chậm quá trình chạy
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                publishProgress("Xong việc" + i + "\n"); //TA CÓ THỂ TRUYỀN NHIỀU PHẦN TỬ BẰNG DẤU ,
            }

            return "Xong rồi.\n";
        }

        @Override
        protected void onPostExecute(String s) { //nhận kết quả trả về từ xử lý chính. Suy ra nhận về là String
            super.onPostExecute(s);             //biến chuỗi s: nhận kết quả từ doInBack...

            txtThongtin.append(s); // append: giữ text lại
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

            txtThongtin.append(values[0]); //Phần tử thứ 0 trong dãy các pt cần truyền. Ở đây chỉ truyền 1 pt,
                                            //nên ta để pt thứ 0.

        }
    }
}
