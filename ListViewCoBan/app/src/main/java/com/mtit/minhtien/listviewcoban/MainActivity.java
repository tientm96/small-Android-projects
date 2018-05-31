package com.mtit.minhtien.listviewcoban;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvMonHoc;
    Button btnThem, btnCapNhat, btnXoa;
    EditText edtMonHoc;

    ArrayList<String> arrCourse;
    int vitri = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();         //ánh xạ

        //tạo mảng chứa dữ liệu cần đổ ra
        arrCourse = new ArrayList<>();
        arrCourse.add("Android");
        arrCourse.add("PHP");
        arrCourse.add("IOS");
        arrCourse.add("Unity");
        arrCourse.add("ASP.NET");


        //tạo adapter sắp xếp dữ liệu
        final ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,
                android.R.layout.simple_list_item_1, arrCourse);
        //đưa adapter vào ListView
        lvMonHoc.setAdapter(adapter);



        //bắt sự kiện THÊM
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String monhoc = edtMonHoc.getText().toString();
                arrCourse.add(monhoc);

                //cập nhật lại dl cho adapter, rồi listView sẽ tự động update
                adapter.notifyDataSetChanged();

            }
        });

        //CẬP NHẬT, dùng sự kiện click lên từng item trên listView để chọn dòng muốn cập nhật
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrCourse.set(vitri, edtMonHoc.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });

        //bắt sự kiện click trên từng dòng của listView
        lvMonHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //i: trả về vị trí click trên listView, từ 0 -> n-1. Lấy ra dữ liệu tại vị trí i
                //Toast.makeText(MainActivity.this, arrCourse.get(i), Toast.LENGTH_SHORT).show();

                //click vào dòng nào thì dòng đó hiện lên ô text
                edtMonHoc.setText(arrCourse.get(i));
                vitri = i; // dùng để biết vị trí đang click để thao tác sửa xóa
            }
        });


        //bắt sự kiện XÓA từng item của listView, ở đây dùng long click, nhấn lâu sẽ xóa.
        //bắt sự kiện long click. Chận giữ lâu thì sẽ hiện ra tùy chỉnh cài đặt
        lvMonHoc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "Xóa: " + arrCourse.get(i), Toast.LENGTH_SHORT).show();

                arrCourse.remove(i); //xóa trong mảng vị trí chọn trên listView.
                adapter.notifyDataSetChanged();

                return false;
            }
        });

    }



    //ánh xạ
    private void AnhXa(){
        lvMonHoc = (ListView) findViewById(R.id.listViewMonHoc);
        btnThem = (Button) findViewById(R.id.buttonThem);
        btnCapNhat = (Button) findViewById(R.id.buttonCapNhat);
        edtMonHoc = (EditText) findViewById(R.id.editTextMonHoc);
    }
}
