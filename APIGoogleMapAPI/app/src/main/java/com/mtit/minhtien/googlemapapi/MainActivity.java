package com.mtit.minhtien.googlemapapi;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap map;


    /*Search "google map android api" chọn https://developers.google.com/maps/documentation/android-api/?hl=vi
    *   + ở phần OVERVIEW có hướng dẫn add Marker(đánh dấu tọa độ), làm theo trong đấy.
    *   + tự khai báo thêm map.*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2 dòng khai báo fragment copy về:
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.fragmentMap);
        mapFragment.getMapAsync(this);
    }



    /*method implement: copy code về, method này sẽ tự động được gọi khi class Implement đc gọi.*/
    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap; // 1 dòng này tự gán sau khi khai báo toàn cục.


        /*Tọa kinh vĩ độ của địa chỉ muốn trỏ Marker vào. Search tọa độ rồi đưa vào cũng được.*/
        LatLng sydney = new LatLng(-33.867, 151.206);   //(vĩ độ latitude, kinh độ longitude)


        /*Xin quyền, tạm thời ko dùng đến*/
//        map.setMyLocationEnabled(true);


        /*TODO: Camera: là góc độ nhìn; 20: Zoom mặc định khi vừa mở app, số càng lớn thì vừa mở app lên ta thấy càng rõ.*/
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 16));


        /*TODO: Thêm vào chấm đỏ đỏ trỏ ở tọa độ. */
        map.addMarker(new MarkerOptions()
                .title("Sydney")
                .snippet("The most populous city in Australia.")
                .position(sydney));
    }
}
