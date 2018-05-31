package com.mtit.minhtien.androidwebservicedatabasemysql;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by MT IT on 11/21/2017.
 */

public class SinhVienAdapter extends BaseAdapter {
    /* - Ở SinhVienAdapter:
     *     + setPositiveButton: là đồng ý, nên ko có code nó cũng xóa, nhưng chỉ xóa trong tích tắc tại adapter,
     *       rồi nó sẽ được server cập nhật lại ngay. Nên coi như không thể xóa nếu như ko có code _POST lên php.
     *     + setNegativeButton: là phủ nhận ko cho xóa, ko code gì thì cũng được.
     *
     *  - Giải pháp tại Main: phải có Volley để _POST cập nhật dl lên php. Nhưng vì nút xóa là nằm ngay tại mh main,
     *       khi bấm vào thì nó chỉ hiện lên dialog hỏi có xóa hay ko? , chứ ko chuyển qua activity mới,
     *       nên ta sẽ tạo hàm dùng Volley ngay trong activity main, rồi chuyển hàm này qua SinhVienAdapter.
     *
     *  - Đẩy hàm từ "activity này" qua "activity khác". Bài này đã làm 1 lần rồi:
     *      + Ở main ("activity này"): chỉnh chế độ hàm tại main: sang public hoặc protected
     *      + Ở SinhVienAdapter ("activity khác"): Sửa các khai báo "Context context;" thành "MainActivity context;"
     *          và sửa đổi các dòng có liên quan như hàm tạo.
     * => như vậy thì hàm nào public ở main thì ở đây sẽ được thấy.
     */

//    private Context context;
    private MainActivity context;
    private int layout;
    private List<SinhVien> sinhVienList;

//    public SinhVienAdapter(Context context, int layout, List<SinhVien> sinhVienList) {
    public SinhVienAdapter(MainActivity context, int layout, List<SinhVien> sinhVienList) {
        this.context = context;
        this.layout = layout;
        this.sinhVienList = sinhVienList;  //trong main sẽ được gán cho arraylist
    }

    @Override
    public int getCount() {
        return sinhVienList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    } //ko dùng tới
    @Override
    public long getItemId(int position) {
        return 0;
    } //ko dùng tới


    //---------------------------------------------
    private class ViewHolder{
        TextView txtHoTen, txtNamSinh, txtDiaChi;
        ImageView imgDelete, imgEdit;
    }
    //trả về converview
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            //có view rồi, ánh xạ
            holder.txtHoTen = (TextView) convertView.findViewById(R.id.textviewHoTenCustom);
            holder.txtDiaChi = (TextView) convertView.findViewById(R.id.textviewDiaChiCustom);
            holder.txtNamSinh = (TextView) convertView.findViewById(R.id.textviewNamSinhCustom);
            holder.imgEdit = (ImageView) convertView.findViewById(R.id.imageviewEdit);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.imageviewDelete);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }


        //sinhVienList: trong main sẽ được gán cho arraylist.
        //Vậy nên dòng code dưới sẽ lấy ra được 1 dòng arrayList

        //Lấy từng dòng
        final SinhVien sinhVien = sinhVienList.get(position);

        holder.txtHoTen.setText(sinhVien.getHoTen());
        holder.txtNamSinh.setText("Năm sinh: " + sinhVien.getNamSinh()); //sinhvien.namsinh: int
        holder.txtDiaChi.setText(sinhVien.getDiaChi());


        //Vì xóa sửa luôn nằm trên mỗi dòng của listview, nên ta xét sự kiện của nó trong class Adapter.

        //Kích vô update thì nó tự chuyển sang màn hình mới. Trong đó ta đưa theo gói dữ liệu sinhvien cùng qua.
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Vì có id, nên ta sẽ gửi nguyên 1 object qua màn hình Update. */
                Intent intent = new Intent(context, UpdateSinhVienActivity.class);

                /* - intent.putExtra("dataSinhVien", sinhVien);: bị lỗi vì ct không hiểu pthức, và không chấp
                *   nhận việc truyền 1 object sinhvien bằng intent.
                *  - Giải pháp: ép về kiểu Serializable bằng cách "implement Serializable" trong class SinhVien,
                *   để cho object của SinhVien có thể truyền đc bằng intent*/
                intent.putExtra("dataSinhVien", sinhVien);

                context.startActivity(intent);

            }
        });

        //Xóa
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XacNhanXoa(sinhVien.getHoTen(), sinhVien.getId());
            }
        });

        return convertView;
    }


//    ----------------------------HÀM TỰ TẠO

    /*Vì kích nút xóa ở  dòng nào thì cũng sẽ hiện lên dialog hỏi, vậy nên dialog sẽ nằm trong mỗi dòng của
     *  listView, nên ta cho dialog nằm trong SinhVienAdapter*/

    //Hiện dialog xác nhận có xóa hay không
    private void XacNhanXoa(String ten, final int id){
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(context);
        dialogXoa.setMessage("Bạn có muốn xóa sinh viên " + ten + " không?");

       /* - Ở SinhVienAdapter:
     *     + setPositiveButton: là đồng ý, nên ko có code nó cũng xóa, nhưng chỉ xóa trong tích tắc tại adapter,
     *       rồi nó sẽ được server cập nhật lại ngay. Nên coi như không thể xóa nếu như ko có code _POST lên php.
     *     + setNegativeButton: là phủ nhận ko cho xóa, ko code gì thì cũng được.
     *
     *  - Giải pháp tại Main: phải có Volley để _POST cập nhật dl lên php. Nhưng vì nút xóa là nằm ngay tại mh main,
     *       khi bấm vào thì nó chỉ hiện lên dialog hỏi có xóa hay ko? , chứ ko chuyển qua activity mới,
     *       nên ta sẽ tạo hàm dùng Volley ngay trong activity main, rồi chuyển hàm này qua SinhVienAdapter.
     *
     *  - Đẩy hàm từ "activity này" qua "activity khác". Bài này đã làm 1 lần rồi:
     *      + Ở main ("activity này"): chỉnh chế độ hàm tại main: sang public hoặc protected
     *      + Ở SinhVienAdapter ("activity khác"): Sửa các khai báo "Context context;" thành "MainActivity context;"
     *          và sửa đổi các dòng có liên quan như hàm tạo.
     *
     * => như vậy thì hàm nào public ở main thì ở đây sẽ được thấy.
     */

        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //đọc kỹ những dòng cmt ở trên.

                //gọi đến method DeleteStudent ở main
                context.DeleteStudent(id);
            }
        });

        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialogXoa.show();
    }




}
