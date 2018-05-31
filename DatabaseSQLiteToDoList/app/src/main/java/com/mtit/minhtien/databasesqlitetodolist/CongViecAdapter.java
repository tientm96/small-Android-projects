package com.mtit.minhtien.databasesqlitetodolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by MT IT on 11/16/2017.
 */

public class CongViecAdapter extends BaseAdapter{

    //muốn gọi funtion từ class Activity khác sang, thì phải public funtion đó, và phải truyền Context là chính Activity đó.
    //vd: dưới đây ta để Context=ActivityMain, để gọi funtion DialogSuaCongViec() từ main qua.
    //private Context context;
    private MainActivity context;
    private int layout;
    private List<CongViec> congViecList;

    public CongViecAdapter(MainActivity context, int layout, List<CongViec> congViecList) { //sửa Context thành MainActivity
        this.context = context;
        this.layout = layout;
        this.congViecList = congViecList;
    }


    @Override
    public int getCount() {
        return congViecList.size();
    } //trả về số lượng dòng
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }

//-------------------------------------------------------
    private class ViewHolder{ //holder là giữ tạm thời mỗi dòng cho listview
        TextView txtTen;
        ImageView imgDelete, imgEdit;
    }


    //Trả về convertView là 1 dòng, mỗi lần gọi là tạo 1 dòng
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder holder; //holder là giữ tạm thời mỗi dòng cho listview

       if(convertView == null){ //nếu dòng đầu chưa có thì set bằng holder
           holder = new ViewHolder();
           LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           convertView = inflater.inflate(layout, null); //gán layout cho biến convertView

           //có convertView rồi, nên ta ánh xạ
           holder.txtTen = (TextView) convertView.findViewById(R.id.textviewTen);
           holder.imgDelete = (ImageView) convertView.findViewById(R.id.imageviewDelete);
           holder.imgEdit = (ImageView) convertView.findViewById(R.id.imageviewEdit);

           convertView.setTag(holder);

       }else{   //nếu đã tồn tại thì lấy y chang từ dòng trên đưa xuống holder để giữ tạm.
           holder = (ViewHolder) convertView.getTag();      //Sử dụng lại view này cho dòng dưới
       }


        //gán giá trị. Không dùng 2 hàm getItem, getItemId ở trên, gán trực tiếp trong này luôn
        final CongViec congViec = congViecList.get(position); //lấy phần tử thứ i của mỗi item trả về

        //đọc từ list ra dòng trên listview
        holder.txtTen.setText(congViec.getTenCV()); //set tên cho txtTen của holder, với mỗi item thì
                                                    //holder.txtTen = nhận 1 giá trị khác nhau.


        //--------------------------------------------------------

        //Đối với THÊM: Ta gọi sự kiện trên menu, kích vô menuAdd, rồi thực thi trong Main luôn,
        //vì gọi hàm trong cùng 1 lớp, nên nó để private.----------------------

        /*NẾU LÀ ĐỔI INTENT SANG ACTIVITY KHÁC
        *Với xóa, sửa: 2 nút imageView này nằm trên mỗi dòng, nên phải bắt sk qua Adapter (Class chứa dòng của nó).
        * - Vì Listview đang nằm trong MainActivity, bấm vào Sửa hay Xóa mỗi dòng thì nó sẽ chuyển intent qua màn hình tương ứng.
        * - Mà mỗi dòng thì ko phải thuộc Context của MainActivity, nên sẽ ko thể dịch chuyển MainActivity được.
        * - Nên ta phải sửa lại các khai báo màn hình và hàm tạo của mỗi dòng Adapter từ "Context context" sang "MainActivity context".
        * - Lúc này vì mỗi dòng đã nằm trong Context là MainActivity, nên sẽ có thể chuyển Main qua mh khác bằng intent*/

        /*****TH NÀY KO ĐỔI ACTI, MÀ LÀ GỌI HÀM CỦA MAIN QUA:
        *Vì gọi hàm của main qua, nên phải để hàm bên main là public, và Context context khai báo trong mỗi dòng
        * phải đổi lại thành MainActivity context.
        * Thay đổi ở khai báo và hàm tạo của Adapter mỗi dòng.
        *
        * Vì vậy, phải gọi hàm thực thi 2 sk này bên CongViecAdapter, nên phải để public hàm Dialog ở Main.
        **/

        //bắt sự kiện sửa, xóa
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //vì khai báo MainActivity context, nên context trỏ đến được DialogSua... và DialogXoaCV
                context.DialogSuaCongViec(congViec.getTenCV(), congViec.getIdCV());
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogXoaCV(congViec.getIdCV(), congViec.getTenCV());
            }
        });


        return convertView;
    }
}
