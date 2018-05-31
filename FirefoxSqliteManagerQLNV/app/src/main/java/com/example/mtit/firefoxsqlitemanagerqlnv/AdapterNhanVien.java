package com.example.mtit.firefoxsqlitemanagerqlnv;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by MTIT on 12/15/2017.
 */

public class AdapterNhanVien extends BaseAdapter {

    //Bấm vào nút sửa ở Main, thì sẽ chuyển từ ActiMain sang ActiUpdate.
    //Vì để có quyền chuyển MainActi qua UpdateActi, nên phải khai báo context là MainActi,
    //Khi đó nằm trong main rồi thì mới có thể dịch chuyển nó được.

    private MainActivity context;
    private int layout;
    private List<NhanVien> nhanVienList;

    public AdapterNhanVien(MainActivity context, int layout, List<NhanVien> nhanVienList) { //đổi ở khai báo và hàm tạo
        this.context = context;
        this.layout = layout;
        this.nhanVienList = nhanVienList;
    }

    @Override
    public int getCount() {
        return nhanVienList.size();
    }



    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }




    //trả về view
    private class ViewHolder {
        ImageView imgHinh;

        TextView txtID;
        TextView txtTen;
        TextView txtSDT;
        Button btnSua;
        Button btnXoa;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            //có view rồi thì ánh xạ
            holder.imgHinh = (ImageView) convertView.findViewById(R.id.imageViewHinhDaiDien);

            holder.txtID = (TextView) convertView.findViewById(R.id.textViewID);
            holder.txtTen = (TextView) convertView.findViewById(R.id.textViewTen);
            holder.txtSDT = (TextView) convertView.findViewById(R.id.textViewSDT);
            holder.btnSua = (Button) convertView.findViewById(R.id.buttonSua);
            holder.btnXoa = (Button) convertView.findViewById(R.id.buttonXoa);

            convertView.setTag(holder);

        }else{ //đã tồn tại
            holder = (ViewHolder) convertView.getTag();
        }


        //gán nhanvien = 1 dòng trong list, lấy theo vị trí position
        final NhanVien nhanVien = nhanVienList.get(position);

        //đọc từng dòng từ list, gán ra listview
        holder.txtID.setText(nhanVien.getId() + ""); //vì ID ở firefoxData là int, phải ép kiểu về
        holder.txtTen.setText(nhanVien.getTen());
        holder.txtSDT.setText(nhanVien.getSdt());

        //vì image ko gán trực tiếp =  mảng byte[] được, nên ta chuyển mảng byte[] sang kiểu bitmap
        byte[] hinhAnh = nhanVien.getAnh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);  //0: decode tất cả
        holder.imgHinh.setImageBitmap(bitmap);

        /*------------------------------------------------*/

        /*Còn với xóa, sửa: 2 nút imageView này nằm trên mỗi dòng, nên phải bắt sk qua Adapter (Class chứa dòng của nó).
        * - Vì Listview đang nằm trong MainActivity, bấm vào Sửa hay Xóa mỗi dòng thì nó sẽ chuyển intent qua màn hình tương ứng.
        * - Mà mỗi dòng thì ko phải thuộc Context của MainActivity, nên sẽ ko thể dịch chuyển MainActivity được.
        * - Nên ta phải sửa lại các khai báo màn hình và hàm tạo của mỗi dòng Adapter từ "Context context" sang "MainActivity context".
        * - Lúc này vì mỗi dòng đã nằm trong Context là MainActivity, nên sẽ có thể chuyển Main qua mh khác bằng intent*/

        //Vì vậy, phải gọi hàm thực thi 2 sk này bên CongViecAdapter, nên phải để public hàm Dialog ở Main.


        //bắt sự kiện sửa, xóa, chỉ cần chuyển qua mh xóa sửa tương ứng là được.
        holder.btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);

                /*Chuyền thêm id qua*/
                intent.putExtra("ID", nhanVien.getId());

                context.startActivity(intent);
            }
        });


        /*----------------------------------------------------------------------------------------*/
        //Xóa 1 dòng
        holder.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogXoa = new AlertDialog.Builder(context); //đang ở context nào thì hiện lên ở đó
                dialogXoa.setMessage("Xóa nhân viên " + nhanVien.getTen() + " ?");

                //nếu chọn Có
                dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { //id và tencv khi đưa vào 1 onClick khác thì phải có final. Alt Enter là xong.

                        delete(nhanVien.getId());
                        Toast.makeText(context, "Đã xóa: " + nhanVien.getTen(), Toast.LENGTH_SHORT).show();
                    }
                });
                //nếu chọn không
                dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //cho đủ thôi, ko cần tương tác. Chọn không thì tự nó thoát
                    }
                });

                dialogXoa.show();

            }
        });



        return convertView;

    }


    //Hàm tự mình tạo
    private void delete(int id){
        //Vào database
        SQLiteDatabase database = Database.initDatabase(context, "EmployeeDB.sqlite");

        //Xóa 1 nhân viên theo id
        database.delete("NhanVien", "ID = ?", new String[]{id + ""});



        /*Todo: Vì dialog: sau khi thêm xong thì phải load lại dữ liệu. Gọi lại hàm readData() ở MainActi hoặc code lại tương tự.
         *
         *  Còn nếu là activity: thì việc load lại sẽ tự động sau mỗi lần chuyển activity bằng intent, nên ta ko cần
         *      gọi hàm load.*/

       //viết code tương tự hàm readData() ở MainActivity để cập nhật lại dl cho listview.
        Cursor cursor = database.rawQuery("SELECT * FROM NhanVien", null);

        nhanVienList.clear();
        while(cursor.moveToNext()){
            nhanVienList.add(new NhanVien(cursor.getInt(0),  //Cột 0: Id, 1: Tên, 2: SDT, 3: ảnh
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getBlob(3)   //trả về byte[]
            ));
        }
        notifyDataSetChanged(); //cập nhật adapter

        /*adapter chính là mỗi dòng, ở main vì gọi đến mỗi dòng nên ta dùng adapter.notifyDataSetChanged().
        * Còn ở đây đã là mỗi dòng rồi, nên ta chỉ cần gọi pt notifyDataSetChanged() ra thôi */
    }
}
