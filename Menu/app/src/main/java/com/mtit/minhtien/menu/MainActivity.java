package com.mtit.minhtien.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_demo, menu); // hiện ra menu 3 chấm

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){  // hàm getItemId: trả về id menu đc click
            case R.id.menuSettings:
                Toast.makeText(this, "Bạn chọ Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuEmail:
                Toast.makeText(this, "Bạn chọ Email", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menuPhone:
                Toast.makeText(this, "Bạn chọ Phone", Toast.LENGTH_SHORT).show();
                break;
                
            case R.id.menuSearch:
                Toast.makeText(this, "Bạn chọ Search", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menuShare:
                Toast.makeText(this, "Bạn chọ Share", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
