package com.mtit.minhtien.nodejssocketio_connectapptoserver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class MainActivity extends AppCompatActivity {

    private Socket mSocket;      //Chọn của library io.socket.client

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Đường dẫn tới server là localhost:3000.
        Cũng giống như web Service, khi gọi trong app ta phải thay localhost bằng địa chỉ ip,
            thì app mới tìm ra đúng server */
        //Connect tới Server
        try {                       //CHÚ Ý: ĐỊA CHỈ ID THAY ĐỔI SAU MỖI LẦN CONNECT WIFI/DÂY =>NHỚ KIỂM TRA
            mSocket = IO.socket("http://192.168.0.104:3000/"); //thay vì localhost:3000
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        mSocket.connect();

        //Gửi dl lên Server //client-send-data: Tên sự kiện gửi từ app lên serve, phải trùng với bên nodejs
        mSocket.emit("client-send-data", "Lap trinh Android");  // sợ lỗi font trên server nên viết ko dấu


        //Nhận dl gửi về từ server
        // server-send-data: Tên sự kiện nhận từ server về app, phải trùng với bên nodejs
        // onRetrieveData: Hàm nhận dl từ server, viết hàm bên ngoài
        mSocket.on("server-send-data", onRetrieveData);

    }


    //Hàm nhận dl gửi về từ server.
    /*Kiểu trả về là một sự kiện lắng nghe hoạt động trả về từ server: Emitter.Listener*/
    private Emitter.Listener onRetrieveData = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() { //gọi tiến trình chạy trên giao diện
                @Override
                public void run() {
                    JSONObject jsonObject = (JSONObject) args[0]; //lấy phần tử thứ 0
                    try {
                        //noidung: là tên của JsonObject gửi về. Phải trùng với tên JsonObject trên code Nodejs
                        String ten = jsonObject.getString("noidung");
                        Toast.makeText(MainActivity.this, ten, Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };


}
