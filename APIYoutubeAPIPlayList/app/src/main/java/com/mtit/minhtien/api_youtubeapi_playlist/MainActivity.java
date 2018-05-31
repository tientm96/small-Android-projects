package com.mtit.minhtien.api_youtubeapi_playlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static String API_KEY = "AIzaSyB0u5ZuypXsi7vKakgMBOTPXQxlZhGth3o";

    //link list: youtube.com/playlist?list=PLCvW8x1GtW0YQWr9sCO8MjH6D6Mn2R4Sd
    String ID_PLAYLIST = "PLCvW8x1GtW0YQWr9sCO8MjH6D6Mn2R4Sd";

    /*Vào stackoverflow.com/questions/22613903/youtube-api-v3-get-list-of-users-videos
    *   lấy đoạn link get Json của list về. Tay Key với ID lại, thêm thêm maxResults vào.
    *   Rồi đưa lên web, link đó sẽ cho ra JSON Object.
    *   Get Json Object đó về bằng hàm get bên dưới.
    *Link chứa JSON đó: */
    String urlGetJson = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId="+ID_PLAYLIST+"&key="+API_KEY+"&maxResults=50";

    ListView lvVideo;
    ArrayList<VideoYoutube> arrayVideo;
    VideoYoutubeAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvVideo = (ListView) findViewById(R.id.listviewVideo);
        arrayVideo = new ArrayList<>();

        adapter = new VideoYoutubeAdapter(this, R.layout.row_listvideo_youtube, arrayVideo);
        lvVideo.setAdapter(adapter);

        GetJsonYoutube(urlGetJson);

        //sự kiện kích vào 1 dòng thì sẽ chuyển sang mh mới, và mang theo id Video đó qua
        lvVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =  new Intent(MainActivity.this, PlayVideoActivity.class);
                //gửi kèm id video qua
                intent.putExtra("idVideoYoutube", arrayVideo.get(position).getIdVideo());
                startActivity(intent);
            }
        });
    }


    //---------------------------HÀM TỰ TẠO------------------------------

    private void GetJsonYoutube(final String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() { //tham số thứ 4
                    @Override
                    public void onResponse(JSONObject response) {
                        /*Thứ tự Json. Ở đây ta chỉ cần đọc "items" chứa các video thôi.
                        *
                        *   {...    //OBJECT: trả về nguyên object response
                        *       "items": [          //ARRAY
                        *               {video 1    "snippet":{toàn bộ thông tin của video1}  //obj}   //OBJECT
                        *               {video 2    "snippet":{toàn bộ thông tin của video2}}
                        *               {video 3    "snippet":{toàn bộ thông tin của video3}}
                        *               ...
                        *       ]
                        *  }
                        *  ==> như vậy cuối cùng cũng chỉ là lấy thông tin video trong snippet.
                        *       vào trong snippet, tùy thuộc vào tính chất của mỗi thành phần mà ta lấy
                        *       cho thích hợp (Obj hay là biến).*/
                        try {
                            JSONArray jsonArrayItems = response.getJSONArray("items"); //viết đúng key name của items
                            String title = "";
                            String url = "";
                            String idVideo = "";

                            for (int i = 0; i < jsonArrayItems.length(); i++){
                                JSONObject jsonObjectItem = jsonArrayItems.getJSONObject(i);

                                JSONObject jsonObjectSnippet = jsonObjectItem.getJSONObject("snippet");
                                //1. "snippet" -> "title" lấy tiêu đề
                                title = jsonObjectSnippet.getString("title"); //lấy tiêu đề

                                //2. "snippet" -> "thumbnails" -> "medium" lấy url chứa hình thu nhỏ
                                // Ở "thumbnails" có nhiều Object con, ta chọn Object loại medium.
                                JSONObject jsonObjectthumbnail = jsonObjectSnippet.getJSONObject("thumbnails");
                                JSONObject jsonObjectMedium = jsonObjectthumbnail.getJSONObject("medium");
                                url = jsonObjectMedium.getString("url");

                                //3. "snippet" -> "resourceId" -> "videoId" lấy ID của video
                                JSONObject jsonObjectResourceId = jsonObjectSnippet.getJSONObject("resourceId");
                                idVideo = jsonObjectResourceId.getString("videoId");


                                arrayVideo.add(new VideoYoutube(title, url, idVideo));
                            }

                            //cập nhật lại add
                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {  //tham số thứ 5
                     @Override
                    public void onErrorResponse(VolleyError error) {
                         Toast.makeText(MainActivity.this, "Lỗi!", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);
    }


}
