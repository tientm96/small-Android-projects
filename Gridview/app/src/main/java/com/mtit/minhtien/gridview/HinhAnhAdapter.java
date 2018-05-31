package com.mtit.minhtien.gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by MT IT on 9/5/2017.
 */

public class HinhAnhAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<HinhAnh> hinhAnhList;

    public HinhAnhAdapter(Context context, int layout, List<HinhAnh> hinhAnhList) {
        this.context = context;
        this.layout = layout;
        this.hinhAnhList = hinhAnhList;
    }

    //2 pt này kh sử dụng
    @Override
    public int getCount() {
        return hinhAnhList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    //Tạo class viewHolder, và pt trả về đt view
    private class ViewHolder{
        ImageView imgHinh;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            //ánh xạ
            holder.imgHinh = (ImageView) view.findViewById(R.id.imageviewHinhAnh);
            view.setTag(holder);

        }else {
            holder = (ViewHolder) view.getTag();
        }

        //gán giá trị
        HinhAnh hinhAnh = hinhAnhList.get(i);
        holder.imgHinh.setImageResource(hinhAnh.getHinh());

        return view;
    }



}
