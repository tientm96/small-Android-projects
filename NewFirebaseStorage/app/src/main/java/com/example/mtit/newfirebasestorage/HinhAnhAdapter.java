package com.example.mtit.newfirebasestorage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by MTIT on 3/12/2018.
 */

public class HinhAnhAdapter extends BaseAdapter {

    Context myContext;
    int myLayout;
    List<HinhAnh> arrayHinh;

    public HinhAnhAdapter(Context myContext, int myLayout, List<HinhAnh> arrayHinh) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.arrayHinh = arrayHinh;
    }

    @Override
    public int getCount() {
        return arrayHinh.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayHinh.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    private class ViewHolder{
        ImageView imgHinh;
        TextView txtTen;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = view;
        ViewHolder holder = new ViewHolder();

        if(rowView == null){
            rowView = inflater.inflate(myLayout, null);
            holder.txtTen = (TextView) rowView.findViewById(R.id.textViewTenRow);
            holder.imgHinh = (ImageView) rowView.findViewById(R.id.imageViewHinhRow);

            rowView.setTag(holder);
        }else{
            holder = (ViewHolder) rowView.getTag();
        }

        //gán giá trị
        holder.txtTen.setText(arrayHinh.get(i).getTenHinh());

        //Hình: vì hình trả về đường link, nên ta sử dụng thư viện Picasso để load Hình.
        //http://square.github.io/picasso/ vào mục download, add 1 dòng code Gradle vào level-app.

        /*Ở mục Introduction, có dòng code mẫu dưới đây*/
        Picasso.get().load(arrayHinh.get(i).getLinkHinh()).into(holder.imgHinh);




        return rowView;
    }


}
