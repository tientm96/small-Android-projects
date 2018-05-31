package com.mtit.minhtien.listviewnangcao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by MT IT on 9/3/2017.
 */

public class TraiCayAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<TraiCay> traiCayList; // truyền vào arraylist từ class TraiCay

    public TraiCayAdapter(Context context, int layout, List<TraiCay> traiCayList) {
        this.context = context;
        this.layout = layout;
        this.traiCayList = traiCayList;
    }


    @Override
    public int getCount() {
        return traiCayList.size();      // trả về số dòng
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    //TẠO LỚP VIEWHOLDER ĐỂ TỐI ƯU VIỆC ÁNH XẠ LẠI NHIỀU LẦN
    //chức năng của ViewHolder là giữ trạng thái ánh xạ của các View, để kh ánh xạ nhiều lần.
    private class ViewHolder {
        ImageView imgHinh;
        TextView txtTen, txtMoTa;
    }


    @Override
    //hàm đổ về dữ liệu lên mỗi dòng item của mình
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if(view == null){
            //khai báo màn hình
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);


            //ánh xạ view. Vừa khai báo vừa ánh xạ
            holder = new ViewHolder();

            holder.txtTen = (TextView) view.findViewById(R.id.textviewTen);
            holder.txtMoTa = (TextView) view.findViewById(R.id.textviewMoTa);
            holder.imgHinh = (ImageView) view.findViewById(R.id.imageviewHinh);
            view.setTag(holder);  //truyền vào trạng thái ánh xạ của view

        }else{ // nghĩa là view đã đc ánh xạ rồi
            holder = (ViewHolder) view.getTag();
        }

        //gán giá trị
        TraiCay traiCay = traiCayList.get(i);

        holder.txtTen.setText(traiCay.getTen());
        holder.txtMoTa.setText(traiCay.getMota());
        holder.imgHinh.setImageResource(traiCay.getHinh());


        //Tạo hiệu ứng cho listView bằng cách gán animation vào
        //Load animation vào
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.scale_list);
        view.startAnimation(animation);


        return view;
    }
}
