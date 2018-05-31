package com.mtit.minhtien.api_youtubeapi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

//public class MainActivity extends AppCompatActivity {
/*implement class này để gọi đến video youtube.
* Class này có 2 method AUTO chạy trả về thành công hoặc thất bại*/
public class MainActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    //key của Api
    String API_KEY = "AIzaSyAdQBhP7dkGFIPO9kNU5ASFlnI-BIkCiJc";
    YouTubePlayerView youTubePlayerView;

    /*Kiểm tra có phải lỗi người dùng ko. Nếu là lỗi của người dùng thì trả về REQUEST_CODE_VIDEO.
    *   Sau đó nếu giá trị trả về là == REQUEST_CODE thì người dùng sẽ khử khởi tạo lại video.
    *   Còn nếu != REQUES_CODE thì thoát.*/
    int REQUEST_CODE_VIDEO = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.myYouTube);

        /*Khởi tạo dịch vụ player. Nhưng nếu thất bại thì sẽ gọi lại 2 hàm con này. Như vậy sẽ dài dòng.
        * ==>Giải pháp: bỏ khởi tạo này, đầu class ta đưa implements YouTubePlayer.OnInitializedListener{} vào,
        *           để có đc 2 phương thức con ở dưới*/
//        youTubePlayerView.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
//            @Override
//            //thành công
//            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//            }
//            @Override
//            //thất bại
//            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//            }
//        });

        /*this sẽ tự động gọi đến class implements, 2 method implements con sẽ được AUTO chạy
        * tùy thuộc vào API_KEY thành công hay thất bại.
        *   => CHỈ CẦN GỌI MỖI DÒNG CODE NÀY, CÁC PT LIÊN QUAN ĐẾN CLASS IMPLEMENTS SẼ TỰ ĐỘNG CHẠY*/
        youTubePlayerView.initialize(API_KEY, this);

    }

    //-----------------------------------------------------------------------
    /*Các method implements từ YouTubePlayer.OnInitializedListener{}.
    * Các hàm này nhận tín hiệu từ API_KEY, rồi AUTO chạy, ko cần gọi đến.*/
    @Override   //thành công
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        /*thành công: thì gọi đến video theo id của nó. Id của video là vế đằng sau vế watch?v= của link của nó,
        * ở đây vd chọn video này https://www.youtube.com/watch?v=zLMqeLGCCzY thì ID là zLMqeLGCCzY*/
        youTubePlayer.cueVideo("zLMqeLGCCzY"); //ID video đc phát

    }

    @Override  //thất bại
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

        /*Kiểm tra có phải lỗi người dùng ko. Nếu là lỗi của người dùng thì trả về REQUEST_CODE_VIDEO.
        *   Sau đó nếu giá trị trả về là == REQUEST_CODE thì người dùng sẽ khử khởi tạo lại video.*/
        if(youTubeInitializationResult.isUserRecoverableError()){

            //biến youTubeInitializationResult ở khai báo tham số hàm: Biến trả về lỗi từ người dùng
            youTubeInitializationResult.getErrorDialog(MainActivity.this, REQUEST_CODE_VIDEO);

        }else{ //ngược lại nếu như ko phải lỗi của người dùng thì thoát
            Toast.makeText(this, "Error!!!", Toast.LENGTH_SHORT).show();
        }
    }

    //-----------------------------------------------------------
    /* TẠI HÀM NHẬN KẾT QUẢ AUTO
        Nếu như lỗi người dùng thì giá trị trả về từ hàm lỗi là == REQUES_CODE,
            khi đó sẽ khử khởi tạo lại bằng cách gọi lệnh chính: youTubePlayerView.initialize(API_KEY, this);
            khi đó mọi việc sẽ được tự động chạy lại. Vì lệnh đó sẽ tự động gọi đến class implements và các hàm imple.

        Ở lệnh này: this sẽ tự động gọi đến class implements, 2 method implements con sẽ được AUTO chạy
            * tùy thuộc vào API_KEY thành công hay thất bại.
            *   => CHỈ CẦN GỌI MỖI DÒNG CODE NÀY, CÁC PT LIÊN QUAN ĐẾN CLASS IMPLEMENTS SẼ TỰ ĐỘNG CHẠY*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_VIDEO){
            //Gọi lại lệnh chính ở onCreate()
            youTubePlayerView.initialize(API_KEY, this);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }



}