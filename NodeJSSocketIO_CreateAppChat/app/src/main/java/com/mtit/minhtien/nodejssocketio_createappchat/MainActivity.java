package com.mtit.minhtien.nodejssocketio_createappchat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class MainActivity extends AppCompatActivity {
    private Socket mSocket;      //Chọn của library io.socket.client: gửi nhận dl qua lại với server

    /*PHẦN TEST: NẾU TEST 2 MÁY ẢO THÌ BT, CÒN TEST 2 MÁY THẬT THÌ PHẢI CÙNG WIFI.
    * BỞI VÌ Ở ĐÂY LÀ LOCALHOST.*/

    ListView lvUser, lvChat;
    EditText edtContent;
    ImageButton ibtnAddUser, ibtnSendMess;

    ArrayList<String> arrayUser, arrayChat;
    ArrayAdapter adapterUser, adapterChat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Mapped();

        
        /*Đường dẫn tới server là localhost:3000.
        Cũng giống như web Service, khi gọi trong app ta phải thay localhost bằng địa chỉ ip,
            thì app mới tìm ra đúng server */
        //Connect tới Server
        try {                   //CHÚ Ý: ĐỊA CHỈ ID THAY ĐỔI SAU MỖI LẦN CONNECT WIFI/DÂY =>NHỚ KIỂM TRA
            mSocket = IO.socket("http://192.168.0.110:3000/"); //thay vì localhost:3000
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        mSocket.connect();  //kết nối

        //-------------------------------------------------
       /* //GỬI DL LÊN SERVER
        //client-send-data: Tên sự kiện gửi từ app lên serve, phải trùng với bên nodejs
        //"Lap trinh Android": data gửi lên server

                //TEST CHO BÀI 1
        mSocket.emit("client-send-data", "Lap trinh Android");  // sợ lỗi font trên server nên viết ko dấu

        //NHẬN DL GỬI VỀ TỪ SERVER
        // server-send-data: Tên sự kiện nhận từ server về app, phải trùng với bên nodejs
        // onRetrieveData: Hàm nhận dl từ server, viết hàm bên ngoài

                        //TEST CHO BÀI 1
        mSocket.on("server-send-data", onRetrieveData); */
        //--------------------------------------------------

        //GỬI DL LÊN SERVER: TEST CHO BÀI 2 - APP CHAT
        ibtnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtContent.getText().toString().trim().length() > 0){ //có nhập vào thì mới gửi
                    //gửi dl lên: tên sk, data
                    mSocket.emit("client-register-user", edtContent.getText().toString());
                }
            }
        });


        //GỬI DL LÊN SERVER: BÀI 2 APP CHAT -- Gửi message lên server
        ibtnSendMess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtContent.getText().toString().trim().length() > 0){ //có nhập vào thì mới gửi
                    //gửi dl lên: tên sk, data
                    mSocket.emit("client-send-chat", edtContent.getText().toString());
                }
            }
        });



        //NHẬN DL TRẢ VỀ TỪ SERVER-NHẬN TÍN HIỆU TRUE/FALSE: TEST CHO BÀI 2 - APP CHAT
        mSocket.on("server-send-result", onRetrieveResult);

        //NHẬN DL TRẢ VỀ TỪ SERVER-NHẬN MẢNG CHỨA USER: TEST CHO BÀI 2 - APP CHAT
        mSocket.on("server-send-user", onListUser);

        //NHẬN DL TRẢ VỀ TỪ SERVER-NHẬN NỘI DUNG CHAT: TEST CHO BÀI 2 - APP CHAT
        mSocket.on("server-send-chat", onListChat);



        //Khởi tạo thành phần đưa vào listview. Adapter sẽ được nhận trong hàm onListUser ở dưới.
        arrayUser = new ArrayList<>();
        adapterUser = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayUser);
        lvUser.setAdapter(adapterUser);

        arrayChat = new ArrayList<>();
        adapterChat = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayChat);
        lvChat.setAdapter(adapterChat);

    }





    //------------------------------HÀM TỰ TẠO------------------------------------------------------

    private void Mapped() {
        ibtnAddUser = (ImageButton) findViewById(R.id.imagebuttonAddUser);
        ibtnSendMess = (ImageButton) findViewById(R.id.imagebuttonSendMess);
        edtContent = (EditText) findViewById(R.id.editTextContent);
        lvChat = (ListView) findViewById(R.id.listviewChat);
        lvUser = (ListView) findViewById(R.id.listViewUser);
    }

        //---------------------------------------------------
    //Hàm nhận dl trả về từ server. TEST CHO BÀI 1
    /*Kiểu trả về là một sự kiện lắng nghe hoạt động trả về từ server: Emitter.Listener*/
//    private Emitter.Listener onRetrieveData = new Emitter.Listener() {
//        @Override
//        public void call(final Object... args) {
//            runOnUiThread(new Runnable() { //gọi tiến trình chạy trên giao diện
//                @Override
//                public void run() {
//                    JSONObject jsonObject = (JSONObject) args[0]; //lấy phần tử thứ 0
//                    try {
//                        //noidung: là tên của JsonObject gửi về. Phải trùng với tên JsonObject trên code Nodejs
//                        String ten = jsonObject.getString("noidung");
//                        Toast.makeText(MainActivity.this, ten, Toast.LENGTH_LONG).show();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
//    };
        //---------------------------------------------------


    //Hàm nhận dl trả về từ server: Nhận tín hiệu true/false. TEST CHO BÀI 2 - APP CHAT
    private Emitter.Listener onRetrieveResult = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() { //gọi tiến trình chạy trên giao diện
                @Override
                public void run() {
                    JSONObject jsonObject = (JSONObject) args[0]; //lấy phần tử thứ 0

                    //ketqua: là tên của JsonObject gửi về. Phải trùng với tên JsonObject trên code Nodejs
                    try {
                        boolean exists = jsonObject.getBoolean("ketqua");

                        //Ở code server nếu biến tontai=true thì đã tồn tại, tontai=false thì ko tồn tại.
                        if(exists){ //tontai== true
                            Toast.makeText(MainActivity.this, "Tài khoản này đã tồn tại!", Toast.LENGTH_SHORT).show();
                        }else{ //tontai==false
                            Toast.makeText(MainActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };

    //Hàm nhận dl trả về từ server: Nhận mảng chứa user. TEST CHO BÀI 2 - APP CHAT
    private Emitter.Listener onListUser = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() { //gọi tiến trình chạy trên giao diện
                @Override
                public void run() {
                    JSONObject jsonObject = (JSONObject) args[0]; //lấy phần tử thứ 0

                    //danhsach: là tên của JsonObject gửi về. Phải trùng với tên JsonObject trên code Nodejs
                    try {
                        //Do trả về mảng user. Nên phải tạo array để nhận
                        JSONArray array = jsonObject.getJSONArray("danhsach");

                        /*Vì mỗi lần gửi dl về app, qua vòng for thì sẽ add vào toàn bộ user.
                        * nếu không clear mảng trước khi add, thì lần add thứ 2 trở đi sẽ gây ra chồng user. */
                        arrayUser.clear();
                        for(int i = 0; i < array.length(); i++){
                            String userName = array.getString(i);
                            arrayUser.add(userName);    //đưa vào listView
                        }
                        adapterUser.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };


    //Hàm nhận dl trả về từ server: Nhận nội dung Chat. TEST CHO BÀI 2 - APP CHAT
    private Emitter.Listener onListChat = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() { //gọi tiến trình chạy trên giao diện
                @Override
                public void run() {
                    JSONObject jsonObject = (JSONObject) args[0]; //lấy phần tử thứ 0

                    //chatContent: là tên của JsonObject gửi về. Phải trùng với tên JsonObject trên code Nodejs
                    try {
                        //Do trả về nội dung chat, nên là String. Nên phải tạo array để nhận
                        String noidungchat = jsonObject.getString("chatContent");
                        arrayChat.add(noidungchat);

                        adapterChat.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };


}
