package com.mtit.minhtien.fragmentchangeorientation;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by MT IT on 11/29/2017.
 */

/*id của listview trong xml ta đã để mặc định, nên ko cần qua ánh xạ thì lớp extends ListFragment
* cũng hiểu đó là listview*/
public class FragmentStudentList extends ListFragment {     //(android.app)

    ArrayList<SinhVien> arraySinhVien;
    StudentAdapter adapter;

    TruyenSinhVienInterface truyenSinhVienInterface;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //interface lấy mh nào. Như vậy biến này đang ở Main, vậy nó sẽ chiếu đến những hàm implement tại Main.
        truyenSinhVienInterface = (TruyenSinhVienInterface) getActivity();

        arraySinhVien = new ArrayList<>();
        AddArraySV();

        adapter = new StudentAdapter(getActivity(), R.layout.row_student, arraySinhVien);
        setListAdapter(adapter); //setListAdapter # setAdapter

        return inflater.inflate(R.layout.fragment_student_list, container, false);
    }


    //sự kiện chọn 1 dòng của listview
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

//        Toast.makeText(getActivity(), arraySinhVien.get(position).getHoten(), Toast.LENGTH_SHORT).show();
        truyenSinhVienInterface.DataStudent(arraySinhVien.get(position));
    }


    private void AddArraySV(){
        arraySinhVien.add(new SinhVien("Nguyễn Văn A", 1990, "Hà Nội", "Anv@gmail.com"));
        arraySinhVien.add(new SinhVien("Nguyễn Văn B", 1991, "Hải Phòng", "Bnv@gmail.com"));
        arraySinhVien.add(new SinhVien("Nguyễn Văn C", 1992, "Đà Nẵng", "Cnv@gmail.com"));
        arraySinhVien.add(new SinhVien("Nguyễn Văn D", 1993, "Nha Trang", "Dnv@gmail.com"));
        arraySinhVien.add(new SinhVien("Nguyễn Văn E", 1994, "Hồ Chí Minh", "Env@gmail.com"));
        arraySinhVien.add(new SinhVien("Nguyễn Văn F", 1995, "Cần Thơ", "Fnv@gmail.com"));
        arraySinhVien.add(new SinhVien("Nguyễn Văn G", 1996, "Tam Kỳ", "Gnv@gmail.com"));
        arraySinhVien.add(new SinhVien("Nguyễn Văn H", 1997, "Hội An", "Hnv@gmail.com"));
        arraySinhVien.add(new SinhVien("Nguyễn Văn I", 1998, "Quy Nhơn", "Inv@gmail.com"));
        arraySinhVien.add(new SinhVien("Nguyễn Văn J", 1999, "Phan Ran", "Jnv@gmail.com"));
        arraySinhVien.add(new SinhVien("Nguyễn Văn K", 2000, "Phan Thiết", "Knv@gmail.com"));
    }

}
