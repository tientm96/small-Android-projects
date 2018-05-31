package com.mtit.minhtien.databasesqlitetodolist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /*CHÚ Ý: APP NÀY LỠ CHỌN API 24, NÊN KHI RUN PHẢI RUN MÁY ẢO API 24 TRỞ LÊN.*/

    Database database;
    ListView lvCongViec;
    ArrayList<CongViec> arrayCongViec;
    CongViecAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvCongViec = (ListView) findViewById(R.id.listviewCongviec);
        arrayCongViec = new ArrayList<>();

        /*Class CongViecAdapter: là class list Adapter, trong đó các pt trả về dành cho 1 dòng, và các sự kiện
        * đều xét cho 1 dòng. Vậy nên ở Main ta chỉ cần khởi tạo adapter, đưa adapter vào listView. Còn các
        * pt trả về dòng, hay sự kiện cho dòng sẽ tự động truyền qua    */

        /* adapter = new CongViecAdapter: Khi khởi tạo CongViecAdapter, là mặc định đã gọi tới nguyên 1 lớp CongViecAdapter rồi.
        * Vậy nên các các sự kiện, hay phương thức trong adapter, sẽ tự động được gọi.
        * -Hàm trả về 1 dòng, hay hàm xét sự kiện đều nằm trong 1 dòng, nên sẽ tự động tương tác.
        * Ta chỉ cần khởi tạo ra adapter là đc */

        adapter = new CongViecAdapter(this, R.layout.dong_cong_viec, arrayCongViec);
        lvCongViec.setAdapter(adapter);

        //Tạo database GhiChu
        //Tham số: màn hình, tên database, con trỏ nhận kết quả, version thường =1
        database = new Database(this, "ghichu.sqlite", null, 1);

        //tạo bảng CongViec. Nếu bảng chưa tồn tại thì mới tạo. //AUTOINCREMENT: tự động tăng dần
        database.QueryData("CREATE TABLE IF NOT EXISTS CongViec(Id INTEGER PRIMARY KEY AUTOINCREMENT, TenCV VARCHAR(200))");

        /*CHÚ Ý: truy vấn ko trả kết quả: CREATE, INSERT, UPDATE, DELETE... chỉ chạy 1 lần thôi, run xong ta
        * phải comment dòng lệnh lại, tránh lúc sau run lại nó sẽ lỗi vì trùng. NẾU ĐỔI MÁY ẢO THÌ PHẢI MỞ CMT RA LẠI*/

        //insert data.
//        database.QueryData("INSERT INTO CongViec VALUES(null, 'Làm bài tập android')");
//        database.QueryData("INSERT INTO CongViec VALUES(null, 'Viết ứng dụng ghi chú')");
//        database.QueryData("INSERT INTO CongViec VALUES(null, 'Học tiếng anh giao tiếp')");


        //select data. truy vấn này có trả kết quả về là con trỏ
        GetDataCongViec();

    }

    //Lấy dữ liệu từ database
    private void GetDataCongViec(){
        //select data. truy vấn này có trả kết quả về là con trỏ Cursor
        Cursor dataCongViec = database.GetData("SELECT * FROM CongViec");

        arrayCongViec.clear(); //xóa mảng, cập nhật lại dl mới, để khi thêm phần tử thì không bị lặp do còn dl cũng trong mảng.
        while(dataCongViec.moveToNext()){ //di chuyển tới thành phần kế bên. Nếu hết dl nó sẽ dừng di chuyển
            int id = dataCongViec.getInt(0); //Id: cột thứ 0, TenCV: cột thứ 1
            String ten = dataCongViec.getString(1);

            /*Vòng while là đọc tất cả những dòng dl đang tồn tại trong database ra. Mỗi lần đọc như vậy
            *       nó add dòng đọc được vào arrayCongViec.
            *
            * -Giả sử có 2 dòng dl cần thêm vào.
            * +Lần đầu đọc ra (run) thì arrayCongViec add được 2 dòng dl.
            *
            * +Cũng giữ nguyên database đó, và đọc lần thứ 2(run máy áo) thì nó lại đọc lại 2 dòng đang có,
            *       và add thêm 2 dòng ấy vào arryCongViec. Như vậy arrayCV có đến 4 dòng trùng nhau.
            * Vậy nên  trước vòng while thì ta phải clear arrayCV đi.*/

            /*Nói cách khác, mỗi lần đọc tất cả database từ vòng while ra, thì add hết vào array,
            * Như vậy, nếu ko xóa đi array sau mỗi lần đọc lại, thì add vào sẽ bị trùng.=>dòng lệnh 67*/
            arrayCongViec.add(new CongViec(id, ten));
        }
        adapter.notifyDataSetChanged(); //cập nhật adapter
    }


    //Add menu, chỉ cần tạo xml menu, rồi add vào Activity bằng java
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_congviec, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //Đối với THÊM: Ta gọi sự kiện trên menu, kích vô menuAdd, rồi thực thi trong Main luôn,
    //vì gọi hàm trong cùng 1 lớp, nên nó để private: private void DialogThem().-----------------------
    //Còn với xóa, sửa: 2 nút imageView này nằm trong dong_cong_viec của Adapter, nên phải qua Adapter bắt sk.
    //Vì vậy, phải gọi hàm thực thi 2 sk này bên CongViecAdapter, nên phải để public.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.memuAdd){
            DialogThem(); //nếu kích vô menu thì hiện ra Dialog Thêm
        }
        return super.onOptionsItemSelected(item);
    }




    //-------------------------------------------------------------------

    //Đối với THÊM: Ta gọi sự kiện trên menu, kích vô menuAdd, rồi thực thi trong Main luôn,
    //vì gọi hàm trong cùng 1 lớp, nên nó để private. ------------------

    //Còn với xóa, sửa: 2 nút imageView này nằm trong dong_cong_viec của Adapter, nên phải qua Adapter bắt sk.
    //Vì vậy, phải gọi hàm thực thi 2 sk này bên CongViecAdapter, nên phải để public.

    /*HAI HÀM XÓA VÀ SỬA ĐỀU CÓ SỬ DỤNG HÀM GetDataCongViec(), nên ta để 2 hàm này trong main*/

    //Xóa dòng công việc. Hiện lên dòng AlerDialog hỏi có xóa hay không thôi, ko cần phải có màn hình dialog như
    //thêm hoặc sửa.


    //Thêm dòng công việc
    private void DialogThem(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //bỏ dòng title trống không ở trên dialog
        dialog.setContentView(R.layout.dialog_them_cong_viec);

        final EditText edtTen = (EditText) dialog.findViewById(R.id.editTextTenCV);
        Button btnThem = (Button) dialog.findViewById(R.id.buttonThem);
        Button btnHuy = (Button) dialog.findViewById(R.id.buttonHuy);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tencv = edtTen.getText().toString();

                if(tencv.equals("")){
                    Toast.makeText(MainActivity.this, "Vui lòng nhập tên công việc!", Toast.LENGTH_SHORT).show();
                }else {
                    database.QueryData("INSERT INTO CongViec VALUES(null, '"+ tencv +"')");
                    Toast.makeText(MainActivity.this, "Đã thêm!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss(); //hoặc .cancel() : dùng cái nào cũng đc, xong thì thoát dialog

                    /*Vì dialog: sau khi thêm xong thì phải load lại dữ liệu, phải gọi hàm load.
                    * Còn nếu là activity: thì việc load lại sẽ tự động sau mỗi lần chuyển activity bằng intent, nên ta ko cần
                    *       gọi hàm load.*/
                    GetDataCongViec();
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss(); //tắt dialog
            }
        });

        dialog.show();
    }




    public void DialogXoaCV(final int id, final String tencv){
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this); //hiện lên dialog hỏi
        dialogXoa.setMessage("Xóa công việc " + tencv + " ?");              //có xóa hay không?

        //nếu chọn Có
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { //id và tencv khi đưa vào 1 onClick khác thì phải có final. Alt Enter là xong.
                database.QueryData("DELETE FROM CongViec WHERE Id = '" + id + "'");
                Toast.makeText(MainActivity.this, "Đã xóa: " + tencv, Toast.LENGTH_SHORT).show();

                /*Vì dialog: sau khi thêm xong thì phải load lại dữ liệu, phải gọi hàm load.
                 * Còn nếu là activity: thì việc load lại sẽ tự động sau mỗi lần chuyển activity bằng intent, nên ta ko cần
                 *      gọi hàm load.*/
                GetDataCongViec();
            }
        });
        //nếu chọn không
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //cho đủ thôi, ko cần tương tác. Chọn không thì tự nó thoát
            }
        });

        dialogXoa.show();
    }


    //sửa dòng công việc
    public void DialogSuaCongViec(String ten, final int id){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//bỏ title trắng ở trên dialog
        dialog.setContentView(R.layout.dialog_sua_cong_viec);

        final EditText edtTenCV = (EditText) dialog.findViewById(R.id.editTextTenCvEdit);
        Button btnXacNhan = (Button) dialog.findViewById(R.id.buttonXacNhanEdit);
        Button btnHuy = (Button) dialog.findViewById(R.id.buttonHuyEdit);

        //vừa hiện dialog lên thì trong editext đã có sẵn tên công việc cần sửa hiện lên rồi
        edtTenCV.setText(ten);

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenMoi = edtTenCV.getText().toString().trim();
                database.QueryData("UPDATE CongViec SET TenCV = '"+ tenMoi +"' WHERE Id = '"+ id +"'");
                Toast.makeText(MainActivity.this, "Đã cập nhật!", Toast.LENGTH_SHORT).show();
                dialog.dismiss(); //tắt hộp thoại dialog

                /*Vì dialog: sau khi thêm xong thì phải load lại dữ liệu, phải gọi hàm load.
                 * Còn nếu là activity: thì việc load lại sẽ tự động sau mỗi lần chuyển activity bằng intent, nên ta ko cần
                 *      gọi hàm load.*/
                GetDataCongViec();
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}
