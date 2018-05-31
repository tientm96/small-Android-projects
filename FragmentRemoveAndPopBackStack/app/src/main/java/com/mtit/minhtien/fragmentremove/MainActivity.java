package com.mtit.minhtien.fragmentremove;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //biến toàn cục để gọi sử dụng trong nhiều hàm bên dưới
    FragmentManager fragmentManager = getFragmentManager();    //(android.app)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //------------------------HÀM TỰ TẠO

    /*- Ở đây ta ko làm theo cách ánh xạ rồi mới xét sự kiện (làm cũng đc), mà làm theo cách xét sự kiện với xml.
     * - Tương tự bài 161:
     *       + Ở file xml: ta mở thuộc tính <Button onClick = "AAAA"...>
     *       + Ở file java: ta tạo phương thức public void AAAA(phải trùng text ở trên).
     *                      Chỉ code hàm thôi, ko cần phải gọi hàm đó ra bằng 1 đối tượng nào cả.
     *      Khi đó, ta kích vào btn, thì .xml sẽ thực thi lệnh onClick bằng cách gọi về hàm tự tạo trong .java
     *              bằng tên trùng tên với text của nó.
     *      Khi đó sự kiện btn sẽ được gọi theo một cách mới.*/

    public void AddA(View view){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();   //(andoid.app)

        //Cài tag cho fragment, để khi remove ta gọi đến nó => thêm vào làm tham số thứ 3 của lệnh add
        fragmentTransaction.add(R.id.frameContent, new FragmentA(), "fragA");

        //để cho fragment được add vào stack, để khi Back lại sẽ back theo thứ tự stack(vào sau ra trước)
        fragmentTransaction.addToBackStack("aaa"); //đặt name key cho mỗi lần add vào stack

        fragmentTransaction.commit();
    }
    public void AddB(View view){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();   //(andoid.app)

        //Cài tag cho fragment, để khi remove ta gọi đến nó => thêm vào làm tham số thứ 3 của lệnh add
        fragmentTransaction.add(R.id.frameContent, new FragmentB(), "fragB");

        //để cho fragment được add vào stack, để khi Back lại sẽ back theo thứ tự stack(vào sau ra trước)
        fragmentTransaction.addToBackStack("bbb"); //đặt name key cho mỗi lần add vào stack

        fragmentTransaction.commit();
    }
    public void AddC(View view){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();   //(andoid.app)

        //Cài tag cho fragment, để khi remove ta gọi đến nó => thêm vào làm tham số thứ 3 của lệnh add
        fragmentTransaction.add(R.id.frameContent, new FragmentC(), "fragC");

        //để cho fragment được add vào stack, để khi Back lại sẽ back theo thứ tự stack(vào sau ra trước)
        fragmentTransaction.addToBackStack("ccc"); //đặt name key cho mỗi lần add vào stack

        fragmentTransaction.commit();
    }

    //Remove(): xóa fragment đã add vào
    public void RemoveA(View view){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();   //(andoid.app)

        /*Vì fragment đã được add vào nên không thể ánh xạ được.
          Nên muốn xóa ta phải tìm đến đúng fragment đó bằng cách ánh xạ khác qua tag của nó,
                tag được thêm khi ta add fragment vào.
          Như vậy, ta tìm được fragment muốn xóa theo tag của nó    */
        FragmentA fragmentA = (FragmentA) getFragmentManager().findFragmentByTag("fragA");

        //để tránh việc không có fragment mà cũng xóa.
        if(fragmentA != null){
            fragmentTransaction.remove(fragmentA);
            fragmentTransaction.commit();
        }else{
            Toast.makeText(this, "Fragment A không tồn tại!", Toast.LENGTH_SHORT).show();
        }
    }
    public void RemoveB(View view){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();   //(andoid.app)
        FragmentB fragmentB = (FragmentB) getFragmentManager().findFragmentByTag("fragB");

        if(fragmentB != null){
            fragmentTransaction.remove(fragmentB);
            fragmentTransaction.commit();
        }else{
            Toast.makeText(this, "Fragment B không tồn tại!", Toast.LENGTH_SHORT).show();
        }
    }
    public void RemoveC(View view){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();   //(andoid.app)
        FragmentC fragmentC = (FragmentC) getFragmentManager().findFragmentByTag("fragC");

        if(fragmentC != null){
            fragmentTransaction.remove(fragmentC);
            fragmentTransaction.commit();
        }else{
            Toast.makeText(this, "Fragment C không tồn tại!", Toast.LENGTH_SHORT).show();
        }
    }


    //Back: mỗi lần nhấn back thì sẽ quay về fragment trước nó.
    public void Back(View view){
        getFragmentManager().popBackStack(); //Stack: ngăn xếp, cái nào add sau thì lùi về sẽ thấy trước

        /*Như vậy, back lại theo thứ tự stack(vào sau ra trước), thì khi add, ta phải add kèm theo
        * mỗi fragment đó vào stack, để lưu thứ tự vào stack.
        *
        * Xem trong pt Add() có dòng thêm vào stack ở trên*/
    }


    //Pop: nhảy tới FragmentA theo yêu cầu ngay lập tức, không tuần tự như Back.
    //cũng sử dụng stack(), gọi tới fragment bằng name key trong stack
    public void PopA(View view){
        getFragmentManager().popBackStack("aaa", 0); //flags default = 0;
    }


    /*Hàm mặc định auto chạy.
    *   Hàm này sẽ thực thi khi ta nhấn nút Back bên ngoài màn hình (Là nút Back cứng).
    * Ở đây ta set mỗi lần nhấn nút Back cứng thì cũng sẽ quay về fragment trước đó, theo thứ tự stack*/
    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() > 0){ //trong stack ít nhất là 1 cái thì mới back về đc.
             getFragmentManager().popBackStack();             // Nếu là 1 thì sẽ back về mh CHÍNH
        }else {
            //TH nếu = 0 mà vẫn bấm nút Back thì thoát ct.
            super.onBackPressed();
        }
    }
}
