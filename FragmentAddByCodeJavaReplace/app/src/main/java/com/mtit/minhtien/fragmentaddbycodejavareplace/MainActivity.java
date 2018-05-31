package com.mtit.minhtien.fragmentaddbycodejavareplace;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnAddA, btnAddB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddA = (Button) findViewById(R.id.buttonAddA);
        btnAddB = (Button) findViewById(R.id.buttonAddB);
    }


    //------------------------------HÀM TỰ TẠO


    /*- Vì hàm .commit() chỉ được gọi 1 lần trong activity, nên sẽ không thể gọi qua lại nhiều fragment được.
     *
     * - Giải pháp: Lệnh gọi hàm từ .xml gọi đến hàm trong .java:
     *    +B1: trong main.xml, tại mỗi <button > ta để thuộc tính onClick="AddFragmentAAA"
     *         thuộc tính này có tác dụng gọi đến hàm "AddFragmentAAA()" trong main.java khi ta click button;
     *       "Tên hàm" cần gọi đến trong main.xml, phải trùng với tên hàm được gọi trong main.java, thì mới gọi được.
     *
     *    +B2: Vào main.java để tạo hàm AddFragmentAAA(); trùng tên với onClick="AddFragmentAAA"
     *         Vì hàm này đc chạy dựa trên việc gọi đến hàm bằng thuộc tính onClick trong xml, bằng việc nhấn button;
     *              nên ở main.java nó chỉ cần viết nd hàm thôi, chứ không cần gọi đến nó qua đối tượng nào cả.
     **/


    //Tự động chạy khi xml gọi bằng click button, nên chỉ cần viết hàm thôi, hàm sẽ tự động chạy, ta ko cần gọi.
    public void AddFragmentAAA(View view){
        /*Gọi fragment java: Trong class Fragment extendt Fragment(Android.app)
         * nên ở đây cũng phải dùng ...(Android.app) để gọi nó. */

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        /*Vì gọi nhiều Fragment, nên ta tạo Fragment mặc định.
            thay vì FragmentA fragmentA = new FragmentA();  FragmentA fragmentA = new FragmentA();*/
        Fragment fragment = null;


        switch(view.getId()){
            case R.id.buttonAddA: fragment = new FragmentA();
                break;

            case R.id.buttonAddB: fragment = new FragmentB();

                break;
        }


        /* Để add vào không bị chồng fragment kích thước nhỏ hơn với fragment khác lớn hơn trong mọi TH,
         *      ta không dùng add như dòng dưới, mà sẽ .replace() sẽ xóa fragment cũ trước khi add mới,
         *          như vậy sẽ không bị chồng. */

        
//        fragmentTransaction.add(R.id.frameContent, fragment); //add fragment vào frameContent,dùng .add()
        fragmentTransaction.replace(R.id.frameContent, fragment); //add fragment vào frameContent,dùng .replace()


        fragmentTransaction.commit();         //xác nhận add

    }
}
