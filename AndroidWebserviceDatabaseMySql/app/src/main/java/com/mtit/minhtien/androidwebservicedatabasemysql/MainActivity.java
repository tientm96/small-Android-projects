package com.mtit.minhtien.androidwebservicedatabasemysql;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView lvSinhVien;
    ArrayList<SinhVien> arraySinhVien;
    SinhVienAdapter adapter;

    /*TH1: dùng localhost:8080, lấy php từ  PC, và chỉ dùng được cho 1 mình máy mình thôi.
    *     Sau mỗi lần kết nối lại mạng nhớ kiểm tra lại địa chỉ ip thay đổi. cmd->ipconfig
    *     */
//    String urlGetdata = "http://192.168.0.105:8080/androidwebservice/getdata.php";
//    String urlDelete = "http://192.168.0.105:8080/androidwebservice/delete.php";


    /*TH2: đã up dữ liệu php lên server: https://www.000webhost.com/members/website/minhtien96/files
     *      Vì cả database và truy vấn file php đều đã đưa lên server, nên dùng được cho tất cả các máy.
     *      Sửa lại đường link, bằng link trên trang web của mình đã tạo trên hostinger*/
    String urlGetdata = "http://minhtien96.000webhostapp.com/getdata.php";
    String urlDelete = "http://minhtien96.000webhostapp.com/delete.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvSinhVien = (ListView) findViewById(R.id.listViewSinhVien);

        arraySinhVien = new ArrayList<>();
        adapter = new SinhVienAdapter(this, R.layout.dong_sinh_vien, arraySinhVien);
        lvSinhVien.setAdapter(adapter);


        //truyền vào link sau khi đổi localhost thành địa chỉ ip
        GetData(urlGetdata);
    }


    //----------------------------------HÀM TỰ TẠO

    //lấy dữ liệu về
    private void GetData(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) { //kết quả trả về là response

                        arraySinhVien.clear();
                        for(int i = 0; i < response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);  //đọc ra dòng từ response
                                arraySinhVien.add(new SinhVien(jsonObject.getInt("ID"),
                                                                jsonObject.getString("HoTen"),
                                                                jsonObject.getInt("NamSinh"),
                                                                jsonObject.getString("DiaChi"))
                                );
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged(); //cập nhật adapter
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }







    /* - Ở SinhVienAdapter:
    *     + setPositiveButton: là đồng ý, nên ko có code nó cũng xóa, nhưng chỉ xóa trong tích tắc tại adapter,
    *       rồi nó sẽ được server cập nhật lại ngay. Nên coi như không thể xóa nếu như ko có code _POST lên php.
    *     + setNegativeButton: là phủ nhận ko cho xóa, ko code gì thì cũng được.
    *
    *  - Giải pháp tại Main: phải có Volley để _POST cập nhật dl lên php. Nhưng vì nút xóa là nằm ngay tại mh main,
    *       khi bấm vào thì nó chỉ hiện lên dialog hỏi có xóa hay ko? , chứ ko chuyển qua activity mới,
    *       nên ta sẽ tạo hàm dùng Volley ngay trong activity main, rồi chuyển hàm này qua SinhVienAdapter.
    *
    *  - Đẩy hàm từ activity này qua activity khác. Bài này đã làm 1 lần rồi:
    *       + Ở main (activity này): chỉnh chế độ hàm tại main: sang public hoặc protected
    *       + Ở SinhVienAdapter (activity khác): Sửa các khai báo "Context context;" thành "MainActivity context;"
    *           và sửa đổi các dòng có liên quan như hàm tạo.
    *
    *    => như vậy thì hàm nào public ở main thì ở SinhVienAdapter sẽ được thấy.
    */
    public void DeleteStudent(final int id){ //vì theo kdl của SinhVien thì id là int
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //thay vì Request.Method.GET thì ta .POST để đẩy qua file PHP
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlDelete,
                new Response.Listener<String>() {
                    @Override
                    //sau khi POST lên, nó sẽ nhận kquả từ php trả về (còn php nhận kq từ server)
                    //ta dã code trên php là nếu delete thành công sẽ trả về succes, thì response sẽ nhận đc "succes".
                    //nếu php update thất bại sẽ trả về "error", nên response = "error".

                    public void onResponse(String response) {
                        if(response.trim().equals("success")){
                            Toast.makeText(MainActivity.this, "Xóa thành công!", Toast.LENGTH_SHORT).show();

                            //Load lại listview. Lấy về data mới sau khi xóa từ server
                            GetData(urlGetdata);

                            //xóa xong thì quay lại màn hình chính. Nhưng xóa thì vẫn đang ở main, nên ko cần.
                            //startActivity(new Intent(MainActivity.this, MainActivity.class));

                        }else{ //thất bại: php trả về "error"
                            Toast.makeText(MainActivity.this, "Lỗi xóa!", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() { //nhận lỗi trả về từ server
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Xẩy ra lỗi!", Toast.LENGTH_SHORT).show();

                        //hiện ra lỗi trên Logcat cho người lập trình xem
                        Log.d("AAAA", "Lỗi!\n" + error.toString());
                    }
                }

        ){ //để đẩy POST dữ liệu lên file php ta dùng hàm getParams(), bằng cách mở hàm {} ở giữa dấu )--;
            // Map<String, String>: key trùng với key nhận của php, dl đẩy lên.
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                //Khi POST ta chỉ POST String lên thôi, php sẽ tự động chuyển về kdl cho từng biến của nó.
                /* - Để put lên và php nhận được thì key "idSV" của 2 bên phải trùng nhau.
                 * - Trong php: 'idSV' đặt đúng theo KEY trong thao tác POST của android (post từ android lên php),
                 *   để đẩy dl từ .java lên file php.
                 */

                //ép kiểu id từ int sang String, vì POST lên là luôn POST trên String. Lên php sẽ tự đổi kiểu dl
                params.put("idSV", String.valueOf(id));

                return params;
            }
        };

        requestQueue.add(stringRequest);
    }







//----------------------------------MENU ADD
    //VÌ MENU nằm trong main, nên ta thao tác tại main
    //thao tác trên menu thêm
    @Override   //tạo menu
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_student, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override //bắt sự kiện menu
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.menuAddStudent){
            startActivity(new Intent(MainActivity.this, AddSinhVienActivity.class));
        }


        return super.onOptionsItemSelected(item);
    }
}
