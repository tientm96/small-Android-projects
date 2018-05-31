package com.mtit.minhtien.api_youtubeapi_playlist;

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
 * Created by MT IT on 12/3/2017.
 */

public class VideoYoutubeAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<VideoYoutube> videoYoutubeList;

    public VideoYoutubeAdapter(Context context, int layout, List<VideoYoutube> videoYoutubeList) {
        this.context = context;
        this.layout = layout;
        this.videoYoutubeList = videoYoutubeList;
    }

    @Override
    public int getCount() {
        return videoYoutubeList.size();
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
        ImageView imgThumbnail;
        TextView txtTitle;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            //có view rồi thì ánh xạ
            holder.txtTitle = (TextView) convertView.findViewById(R.id.textviewTitle);
            holder.imgThumbnail = (ImageView) convertView.findViewById(R.id.imageviewThumbnail);
            convertView.setTag(holder);

        }else{ //đã tồn tại
            holder = (ViewHolder) convertView.getTag();
        }

        //gọi đối tượng thứ i vào, gán cho videoYoutube
        VideoYoutube videoYoutube = videoYoutubeList.get(position);

        holder.txtTitle.setText(videoYoutube.getTitle());

        /*Add ảnh vào imageview chỉ bằng link ảnh. Dùng thư viện Picasso.
         Lấy thư viện Picasso tại http://square.github.io/picasso/
         Chọn mục Download, copy dòng code ở GRADLE đưa vào build.gradle(Module:app).

         Code để lấy ảnh: kéo lên mục PLACE HOLDERS có code làm theo
        */
        Picasso.with(context)
                .load(videoYoutube.getThumbnail())      //url
                .placeholder(R.drawable.user_placeholder)   //hình mặc định khi đang load
                .error(R.drawable.user_placeholder_error)   //hình mặc định khi lỗi
                .into(holder.imgThumbnail);


        return convertView;
    }
}
