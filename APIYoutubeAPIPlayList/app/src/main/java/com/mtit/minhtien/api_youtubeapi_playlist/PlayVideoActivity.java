package com.mtit.minhtien.api_youtubeapi_playlist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


/*Để gửi khung màn hình youtube từ Main qua  bằng intent thì phải sửa extends lại là lớp này.*/

/*Để youtube khởi tạo lại nếu do lỗi (tương tự projec API Youtube trước):
*   implements lớp dưới và 2 pt success và fail của nó*/

public class PlayVideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    YouTubePlayerView youTubePlayerView;
    String id = "";
    int REQUEST_CODE_VIDEO = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        //Nhận intent
        Intent intent = getIntent();
        id = intent.getStringExtra("idVideoYoutube"); //phải trùng với name bên mh gửi

        //Ánh xạ
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.myYoutube);

        //Khởi tạo video
        youTubePlayerView.initialize(MainActivity.API_KEY, this);
    }




    //2 pt của lớp implements
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        /*Thành công: vừa chuyển qua thì play luôn*/
        youTubePlayer.loadVideo(id);

        //Vừa phát thì nó full mh luôn, cái này để cho biết thôi
        youTubePlayer.setFullscreen(true);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        //nếu lỗi người dùng
        if (youTubeInitializationResult.isUserRecoverableError()){
            //khởi tạo lại dựa vào REQUES_CODE
            youTubeInitializationResult.getErrorDialog(PlayVideoActivity.this, REQUEST_CODE_VIDEO);

        }else{  //nếu lỗi khác
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
        }
    }


    /*Hàm lấy kết quả trả về. Hàm này chạy AUTO.
    * Nếu Request_code bằng nhau thì có nghĩa lỗi người dùng, khi đó gọi khởi tạo lại video.*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_VIDEO){
            youTubePlayerView.initialize(MainActivity.API_KEY, PlayVideoActivity.this);

        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
