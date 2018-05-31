package com.mtit.minhtien.fragmentchangeorientation;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements TruyenSinhVienInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //phương thức implement từ Interface
    @Override
    public void DataStudent(SinhVien sinhVien) {
        //nhận thông tin 1sv vào thì đổ ra fragmentInfo
        FragmentStudentInfo fragmentStudentInfo = (FragmentStudentInfo) getFragmentManager().findFragmentById(R.id.fragmentInfo);

        /*phân biệt khi set mh ngang và dọc. Nếu ko có dòng này thì nó chỉ đưa vào mh ngang thôi.
        * - Mặc định nó sẽ set vô mh ngang, nếu run lên thì mh ngang sẽ có giá trị ngay, ko null.
        * - Còn mh dọc ko được mặc định set vào, nên khi run lên nó chỉ có list hiển thị danh sách tên
        *           mà ko có nội dung bên trong mỗi dòng tên đó ==> mh dọc ban đầu khi run sẽ bị null.*/


        /* Nếu chỉ có 1 đk != null trong if:::
        * Nếu TH bấm theo thứ tự Dọc, Ngang, Dọc.
        *   - Thì lần đầu nó sẽ xét đc  != null nên nó vô dọc.
        *   - Lần sau khi bấm Ngang nó sẽ vô Ngang bt, như vậy layout lúc này đã tồn tại dl, ko còn là null nữa,
        *   - nên lúc sau khi bấm dọc lại thì nó ko vô được phần else của dọc, mà sẽ vô phần if(!=null) của ngang,
        *          *   => sẽ gây ra lỗi vì fragment dọc ko có trong layout của ngang.
        *
        *   => Giải pháp:
        *         +Cách 1: thêm đk là view đó phải có trong Layout của ngang thì mới vô được: isInLayout()
        *         +Cách 2: thêm đk màn hình đó phải là đang ở ngang(landscape),
        *                   thì nó mới vô ngang, ko thì nó qua dọc.
        */
        //CÁCH 1: CHỈ THÊM 1 ĐK
//        if(fragmentStudentInfo != null && fragmentStudentInfo.isInLayout()){

        //CÁCH 2: lấy vị trí, rồi mới xét thêm đk Landscape: là ngang, còn Portrait: là dọc
        Configuration configuration = getResources().getConfiguration();  //lấy vị trí mh hiện tại: ngang hay dọc

        if(fragmentStudentInfo != null && configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            fragmentStudentInfo.SetInfo(sinhVien); //NẾU LÀ MH NGANG, set giá trị cho nó.

        }else{

            /*TH này = null nên sẽ làm mh dọc. Lúc này kích vào dòng thì nó chuyển sang activity khác nên dùng intent.
            *-Nhưng intent để chuyển Object từ activity này sang activity khác thì phải implements Serializable
            *       cho class enity chứa Object đó => là class SinhVien, qua class SinhVien ta implements.*/
            Intent intent = new Intent(MainActivity.this, StudentInformation.class);
            intent.putExtra("thongtinSinhVien", sinhVien);
            startActivity(intent);
        }



    }
}
