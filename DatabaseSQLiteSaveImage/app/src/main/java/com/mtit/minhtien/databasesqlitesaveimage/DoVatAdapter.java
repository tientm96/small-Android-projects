package com.mtit.minhtien.databasesqlitesaveimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by MT IT on 11/20/2017.
 */

public class DoVatAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<DoVat> doVatList;

    public DoVatAdapter(Context context, int layout, List<DoVat> doVatList) {
        this.context = context;
        this.layout = layout;
        this.doVatList = doVatList;
    }




    @Override
    public int getCount() {
        return doVatList.size(); //trả về size của list
    }

    @Override
    public Object getItem(int position) {
        return null; //không dùng tới
    }

    @Override
    public long getItemId(int position) {
        return 0;//không dùng tới
    }

    //-----------------------------------------
    private class ViewHolder{
        TextView txtTen, txtMota;
        ImageView imgHinh;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            //có convertView rồi, ta ánh xạ cho đối tượng của viewHolder
            holder.txtTen = (TextView) convertView.findViewById(R.id.textviewTenCustom);
            holder.txtMota = (TextView) convertView.findViewById(R.id.textviewMotaCustom);
            holder.imgHinh = (ImageView) convertView.findViewById(R.id.imageViewHinhCustom);
            convertView.setTag(holder);

        }else{ //holder đã được tạo
            holder = (ViewHolder) convertView.getTag();
        }

        //gán doVat = 1 dòng trong list, lấy theo vị trí position
        DoVat doVat = doVatList.get(position);
        holder.txtTen.setText(doVat.getTen()); //bắt đầu lấy dl từ dòng ra
        holder.txtMota.setText(doVat.getMota());

        //vì image ko gán trực tiếp =  mảng byte[] được, nên ta chuyển mảng byte[] sang kiểu bitmap
        byte[] hinhAnh = doVat.getHinh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);//0: decode tất cả
        holder.imgHinh.setImageBitmap(bitmap);


        return convertView;
    }
}
