package com.mtit.minhtien.popupmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

public class MainActivity extends AppCompatActivity {

    Button btnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMenu = (Button) findViewById(R.id.buttonMenu);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowMenu();
            }
        });

    }

    //Do ko có sự kiện cho việc xổ meru ra của buttonMenu nên ta phải tự tạo phương thức
    private void ShowMenu(){
        PopupMenu popupMenu = new PopupMenu(this, btnMenu);
        popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());

        //set event cho cái item menu
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()){
                    case R.id.menuThem: btnMenu.setText("Menu thêm");
                        break;
                    case R.id.menuXoa: btnMenu.setText("Menu xóa");
                        break;
                    case R.id.menuSua: btnMenu.setText("Menu sửa");
                        break;
                }

                return false;
            }
        });

        popupMenu.show();
    }

}
