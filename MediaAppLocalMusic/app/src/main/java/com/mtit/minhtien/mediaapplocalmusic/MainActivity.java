package com.mtit.minhtien.mediaapplocalmusic;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView txtTitle, txtTimeSong, txtTimeTotal;
    SeekBar skSong;
    ImageView imgHinh;
    ImageButton ibtnPre, ibtnNext, ibtnPlay, ibtnStop;  //ibtn tích hợp luôn Pause, xen kẻ

    ArrayList<Song> arraySong;
    int position = 0;
    MediaPlayer mediaPlayer;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Mapped(); //ánh xạ
        AddSong(); //ánh xạ bài hát vào trong arraylist
        animation = AnimationUtils.loadAnimation(this, R.anim.disc_rotate);

        CreateMediaPlayer(); //gọi hàm khởi tạo media, hàm tự viết ở dưới


        ibtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                if(position > arraySong.size()-1){ //vì pos bắt đầu từ 0
                    //nếu đã tới bài cuối thì quay về bài hát đầu tiên khi bấm next
                    position = 0;
                }

                //sau khi bấm next xong tự động phát bài tiếp theo. để tránh việc hát chồng nhiều bài, phải kiểm tra có đang hát ko.
                //Nếu có thì stop lại, rồi mới chuyển sang bài mới
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();                  //nếu có đang hát thì cho nó stop lại
                }
                CreateMediaPlayer();        //rồi mới khởi tạo lại mediaPlayer cho hát bài
                mediaPlayer.start();

                //Như vậy vừa vào ta bấm next hoặc preview liền(chưa bấm nút play),
                //thì nó vẫn chuyển bài và tự động phát (app bt cũng vậy), nhưng hình nút play thì vẫn chưa đổi thành hình pause;
                //Vậy nên phải chuyển thành hình pause khi bấm next hoặc pre
                ibtnPlay.setImageResource(R.drawable.pause);

                //set thời gian của bài hát
                SetTimeTotal();

                //cập nhật tgian đang chạy
                UpdateTimeSong();

                //gọi hiệu ứng quay quay cho đĩa
                imgHinh.startAnimation(animation);
            }
        });


        ibtnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                if(position < 0){
                                    //nếu đang ở bài đầu tiên rồi, mà lại bấm quay về thì vòng lại bài hát cuối
                    position = arraySong.size()-1;          //vì pos bắt đầu từ 0
                }

                //sau khi bấm pre xong tự động phát bài sau nó. để tránh việc hát chồng nhiều bài, phải kiểm tra có đang hát ko.
                //Nếu có thì stop lại, rồi mới chuyển sang bài mới
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();                  //nếu có đang hát thì cho nó stop lại
                }
                CreateMediaPlayer();        //rồi mới khởi tạo lại mediaPlayer cho hát bài
                mediaPlayer.start();

                //Như vậy vừa vào ta bấm next hoặc preview liền(chưa bấm nút play),
                //thì nó vẫn chuyển bài và tự động phát (app bt cũng vậy), nhưng hình nút play thì vẫn chưa đổi thành hình pause;
                //Vậy nên phải chuyển thành hình pause khi bấm next hoặc pre
                ibtnPlay.setImageResource(R.drawable.pause);


                //set thời gian của bài hát
                SetTimeTotal();

                //cập nhật tgian đang chạy
                UpdateTimeSong();

                //gọi hiệu ứng quay quay cho đĩa
                imgHinh.startAnimation(animation);
            }
        });


        ibtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop(); //sau khi stop thì icon play chuyển về hình play để có thể bấm phát lại
                mediaPlayer.release(); //sau khi stop thì sẽ dừng hẵn ct, nên sẽ giải phóng ct. dòng này có hay ko đều đc.

                ibtnPlay.setImageResource(R.drawable.play);

                //ibtnPlay: nếu chưa phát thì gọi mediaPlayer.start();, chứ nó không khởi tạo, vì đã đưa khởi tạo ra bên ngoài rồi.
                //sau khi stop thì dừng hẵn luôn, nên sau mỗi lần stop mà nếu muốn play lại thì phải khởi tạo lại.
                //Vì ở dưới ibtnPlay khi bấm play chỉ gọi .start(), nếu ở đây ta ko khởi tạo lại thì bấm play vẫn ko phát được.
                CreateMediaPlayer();

                //làm dừng hiệu ứng đĩa quay
                imgHinh.clearAnimation();
            }
        });


        //để tránh mỗi lần bấm play lại mỗi lần khởi tạo mediaPlayer, nên ta đưa ra hàm,
        //và gọi hàm khởi tạo nó ngay khi chạy ct, để ở ngoài cùng.
        //vậy mỗi khi khi bấm play chỉ cần gọi .start() là được.
        ibtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){ //nếu đang phát => bấm vào chính là bấm vào pause
                    mediaPlayer.pause();    //Sau khi pause xong, đổi hình nút play lại thành hình play
                    ibtnPlay.setImageResource(R.drawable.play);

                    //làm dừng hiệu ứng đĩa quay
                    imgHinh.clearAnimation();
                }else{
                    mediaPlayer.start(); //nếu chưa phát thì bấm vào chính là bấm phát
                    ibtnPlay.setImageResource(R.drawable.pause); //sau khi bấm phát thì nút play đổi hình thành pause

                    //set thời gian của bài hát
                    SetTimeTotal();

                    //cập nhật tgian đang chạy
                    UpdateTimeSong();

                    //gọi hiệu ứng quay quay cho đĩa
                    imgHinh.startAnimation(animation);
                }
            }
        });


        //mỗi lần seekbar chạy hay ta kéo seekbar thì thời gian chạy cũng thay đổi theo
        skSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            //khi kéo seekbar liên tục, cập nhật giá trị liên tục
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}

            @Override
            //vừa chạm vào seekbar, nó lấy khoảnh khắc đó
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            //kéo qua, kéo lại, kéo thoải mái, cho đến khi đã chọn xong thả ra thì nó mới lấy khoảnh khắc tại chỗ thả ra
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Lúc nãy trong SetTimeTotal(): đã gán max của seekbar là max thời gian của bài hát
                //Vậy nên lúc này thời gian phát hiện hành của bài hát và thông số chạy của seekbar đã tương đồng.
                //skSong.getProgress(): lấy ra thông số chạy tới đâu của seekbar.
                //Bỏ thông số đó vào seekto() của mediaPlayer: thì mediaPlayer cũng sẽ tự chuyển tới thời gian bằng với thông số đó.
                mediaPlayer.seekTo(skSong.getProgress());

            }
        });
    }





    /*Dùng quản lý tiến trình handler: lặp đi lặp lại, kiểm tra và update thời gian chạy liên tục. */
    private void UpdateTimeSong(){
        final Handler handler = new Handler(); //chọn của android.os
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                txtTimeSong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));

                //update progress cho thanh seekbar
                skSong.setProgress(mediaPlayer.getCurrentPosition());


                //kiểm tra thời gian bài hát -> nếu kết thúc thì next. Hàm này sẽ tự động thực hiện việc này,
                //nên ta chỉ cần đưa code của sự kiện next xuống là đc.
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) { //COPY HẾT CODE TRONG buttonNext xuống
                        position++;
                        if(position > arraySong.size()-1){ //vì pos bắt đầu từ 0
                            //nếu đã tới bài cuối thì quay về bài hát đầu tiên khi bấm next
                            position = 0;
                        }

                        //sau khi bấm next xong tự động phát bài tiếp theo. để tránh việc hát chồng nhiều bài, phải kiểm tra có đang hát ko.
                        //Nếu có thì stop lại, rồi mới chuyển sang bài mới
                        if(mediaPlayer.isPlaying()){
                            mediaPlayer.stop();                  //nếu có đang hát thì cho nó stop lại
                        }
                        CreateMediaPlayer();        //rồi mới khởi tạo lại mediaPlayer cho hát bài
                        mediaPlayer.start();

                        //Như vậy vừa vào ta bấm next hoặc preview liền(chưa bấm nút play),
                        //thì nó vẫn chuyển bài và tự động phát (app bt cũng vậy), nhưng hình nút play thì vẫn chưa đổi thành hình pause;
                        //Vậy nên phải chuyển thành hình pause khi bấm next hoặc pre
                        ibtnPlay.setImageResource(R.drawable.pause);

                        //set thời gian của bài hát
                        SetTimeTotal();

                        //cập nhật tgian đang chạy
                        UpdateTimeSong();
                    }
                });


                handler.postDelayed(this, 500); //cứ mỗi 0,5s thì nó cập nhật thời gian lại
            }
        }, 100); //vừa click play 1 cái thì bắt đầu cập nhật thời gian liền luôn, nên thời gian delay
                            //ta để rất nhỏ, ở đây để 0,1s


    }

    private void SetTimeTotal(){
        //định dạng giời, in ra phút:giây
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTimeTotal.setText(simpleDateFormat.format(mediaPlayer.getDuration()));


        //THỜI GIAN CHẠY: khi ta kéo seekbar thì thời gian chạy phải thay đổi theo.
        //=> phải gán max của skSong = mediaPlayer.getDuration(), để thời gian chạy đúng với mức thời gian total.
        skSong.setMax(mediaPlayer.getDuration());

    }

    private void CreateMediaPlayer(){
        mediaPlayer = MediaPlayer.create(MainActivity.this, arraySong.get(position).getFile());
        txtTitle.setText(arraySong.get(position).getTitle());  //lấy ra tên bài hát
    }

    private void AddSong(){
        arraySong = new ArrayList<>();
        arraySong.add(new Song("Ánh nắng của anh", R.raw.anh_nang_cua_anh));
        arraySong.add(new Song("Anh nhớ em", R.raw.anh_nho_em));
        arraySong.add(new Song("Anh nhớ mùa đông ấy", R.raw.anh_nho_mua_dong_ay));
        arraySong.add(new Song("Anh sẽ tốt mà", R.raw.anh_se_tot_ma));
        arraySong.add(new Song("Anh yêu người khác rồi", R.raw.anh_yeu_nguoi_khac_roi));
        arraySong.add(new Song("Hãy là một kỷ niệm", R.raw.hay_la_mot_ky_niem));
    }

    private void Mapped(){
        txtTitle = (TextView) findViewById(R.id.textviewTitle);
        txtTimeSong = (TextView) findViewById(R.id.textViewTimeSong);
        txtTimeTotal= (TextView) findViewById(R.id.textViewTimeTotal);
        skSong = (SeekBar) findViewById(R.id.seekBarSong);
        ibtnPre = (ImageButton) findViewById(R.id.imageButtonPre);
        ibtnNext = (ImageButton) findViewById(R.id.imageButtonNext);
        ibtnPlay = (ImageButton) findViewById(R.id.imageButtonPlay);
        ibtnStop = (ImageButton) findViewById(R.id.imageButtonStop);
        imgHinh = (ImageView) findViewById(R.id.imageViewDisc);
    }
}
