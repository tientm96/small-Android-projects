package com.mtit.minhtien.fragmentchangeorientation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by MT IT on 11/29/2017.
 */

public class StudentAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<SinhVien> sinhVienList;

    public StudentAdapter(Context context, int layout, List<SinhVien> sinhVienList) {
        this.context = context;
        this.layout = layout;
        this.sinhVienList = sinhVienList;
    }




    @Override
    public int getCount() {
        return sinhVienList.size();
    }

    //2 hàm getItem không dùng đến
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }


    private class ViewHolder {
        TextView txtTen;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            //có view rồi ta ánh xạ
            holder.txtTen = (TextView) convertView.findViewById(R.id.textviewHoTenRow); //Tránh nhầm với textviewHoTenInfo
            convertView.setTag(holder); //lưu vào tag để nếu đã tồn tại thì còn có cái để lấy ra

        }else {
            holder = (ViewHolder) convertView.getTag();
        }


        //Lấy phần tử thứ i trong list
        SinhVien sinhVien = sinhVienList.get(position);
        holder.txtTen.setText(sinhVien.getHoten().toString());


        return convertView;
    }
}
