package com.mtit.minhtien.contextmenu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnChonMau;
    ConstraintLayout manHinh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnChonMau = (Button) findViewById(R.id.buttonChonMau);
        manHinh = (ConstraintLayout) findViewById(R.id.manHinh);

        //đăng ký view cho context Menu
        registerForContextMenu(btnChonMau);
    }

    //sự kiện xổ ra context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context, menu);

        //set tiêu đề trên context menu
        menu.setHeaderTitle("Chọn Màu");
        menu.setHeaderIcon(R.drawable.a); //lỗi ko hiển thị được hình này, sửa sau

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    //bắt sự kiện click các item
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menuDo: manHinh.setBackgroundColor(Color.RED);
                break;
            case R.id.menuVang: manHinh.setBackgroundColor(Color.YELLOW);
                break;
            case R.id.menuXanh: manHinh.setBackgroundColor(Color.BLUE);
                break;
        }

        return super.onContextItemSelected(item);
    }
}
