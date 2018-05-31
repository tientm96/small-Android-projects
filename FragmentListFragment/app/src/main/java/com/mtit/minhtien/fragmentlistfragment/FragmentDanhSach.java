package com.mtit.minhtien.fragmentlistfragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by MT IT on 11/28/2017.
 */

/*Vì ở .java ta extends ListFragment. Nên để tương thích ta phải đặt id bằng id có sẵn cho listView trong .xml.
 *      Khi đó class extends ListFragment mới chịu hiểu trong xml là list.
 *      Nếu đặt listView trong xml là id bình thường, thì lớp extends ListFragment sẽ ko chịu hiểu đó là list.
 *
 * Vì đã dùng extends ListFragment, và xml ta đặt id có sẵn, nên trong java không cần ánh xạ, thông qua
 *      ListFragment sẽ tự tìm thấy listView*/

public class FragmentDanhSach extends ListFragment {    //(android.app)

    String[] arrayCity = {"Hà Nội", "Hải Phòng", "Đà Nẵng", "Hồ Chí Minh", "Cần Thơ", "Tam Kỳ"};
    ArrayAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, arrayCity);
        /*Vì đã dùng extends ListFragment, và xml ta đặt id có sẵn, nên trong java không cần ánh xạ,
            thông qua ListFragment sẽ tự tìm thấy listView*/
        setListAdapter(adapter);

        return inflater.inflate(R.layout.fragment_danhsach, container, false);
    }


    /*Sự kiện click cho mỗi dòng listView.
    * Giống như lv bt, nó sẽ trả về position: vị trí của dòng trong listV*/
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        Toast.makeText(getActivity(), arrayCity[position], Toast.LENGTH_SHORT).show();

        super.onListItemClick(l, v, position, id);
    }
}
