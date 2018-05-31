package com.mtit.minhtien.intentbaitapangrybirds;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.Collections;

//khi muốn đổi theme thì trong manifest chọn theme cho màn hình muốn đổi
//rồi sửa lại chỉ extends Activity thôi.

//public class ImageActivity extends AppCompatActivity {
public class ImageActivity extends Activity {

    TableLayout myTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        myTable = (TableLayout) findViewById(R.id.tableLayoutImage);

        int sodong = 8;
        int socot = 3;

        //Trộn mảng, mỗi lần chạy lên vị trí các hình thay đổi
        Collections.shuffle(MainActivity.arrayName);

        //tạo dòng và cột
        for(int i = 1; i <= sodong; i++){
            TableRow tableRow = new TableRow(this);

            //tạo cột -> imageview
            for(int j = 1; j <= socot; j++){
                ImageView imageView = new ImageView(this); //imageview tạo = code java, nên phải kb cỡ


                //ĐỔI TỪ dp SANG pixel
                Resources r = getResources();                               //GIÁ TRỊ dp CẦN ĐỔI LÀ 100
                int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, r.getDisplayMetrics());
                //chỉnh kích cỡ img
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(px, px); //đv: pixel
                imageView.setLayoutParams(layoutParams);


                //Gán hình
                final int vitri = socot * (i-1) + (j-1); //1 dạng CT để lấy vị trí hình ra
                int idHinh = getResources().getIdentifier(MainActivity.arrayName.get(vitri), "drawable", getPackageName());
                imageView.setImageResource(idHinh);

                //add imageView vào tablerow. Thêm hình vào dòng
                tableRow.addView(imageView); //dòng này vẫn nằm trong code, ngoài layout nó chỉ là cái bảng thôi

                //bắt sk click vào từng hình
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra("tenhinhchon", MainActivity.arrayName.get(vitri));
                        setResult(RESULT_OK, intent);

                        finish();
                    }
                });
            }

            //add tablerow vào table
            myTable.addView(tableRow);

        }

    }
}
